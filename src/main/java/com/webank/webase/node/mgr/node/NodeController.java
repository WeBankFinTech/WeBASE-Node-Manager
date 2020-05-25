/**
 * Copyright 2014-2020  the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.node.mgr.node;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.webank.webase.node.mgr.base.code.ConstantCode;
import com.webank.webase.node.mgr.base.code.RetCode;
import com.webank.webase.node.mgr.base.entity.BasePageResponse;
import com.webank.webase.node.mgr.base.entity.BaseResponse;
import com.webank.webase.node.mgr.base.exception.NodeMgrException;

import lombok.extern.log4j.Log4j2;

/**
 * Controller for node data.
 */
@Log4j2
@RestController
@RequestMapping("node")
public class NodeController {

    @Autowired private NodeService nodeService;

    /**
     * qurey node info list.
     */
    @GetMapping(value = "/nodeList/{groupId}/{pageNumber}/{pageSize}")
    public BasePageResponse queryNodeList(@PathVariable("groupId") Integer groupId,
                                          @PathVariable("pageNumber") Integer pageNumber,
                                          @PathVariable("pageSize") Integer pageSize,
                                          @RequestParam(value = "nodeName", required = false) String nodeName)
            throws NodeMgrException {
        BasePageResponse pagesponse = new BasePageResponse(ConstantCode.SUCCESS);
        Instant startTime = Instant.now();
        log.info(
                "start queryNodeList startTime:{} groupId:{}  pageNumber:{} pageSize:{} nodeName:{}",
                startTime.toEpochMilli(), groupId, pageNumber,
                pageSize, nodeName);

        // param
        NodeParam queryParam = new NodeParam();
        queryParam.setGroupId(groupId);
        queryParam.setPageSize(pageSize);
        queryParam.setNodeName(nodeName);

        //check node status before query
        try {
            nodeService.checkAndUpdateNodeStatus(groupId);
        } catch (Exception e) {
            log.error("queryNodeList checkAndUpdateNodeStatus groupId:{}, error: []", groupId, e);
        }
        Integer count = nodeService.countOfNode(queryParam);
        if (count != null && count > 0) {
            Integer start = Optional.ofNullable(pageNumber).map(page -> (page - 1) * pageSize)
                    .orElse(null);
            queryParam.setStart(start);

            List<TbNode> listOfnode = nodeService.qureyNodeList(queryParam);
            pagesponse.setData(listOfnode);
            pagesponse.setTotalCount(count);

        }

        log.info("end queryNodeList useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(), JSON.toJSONString(pagesponse));
        return pagesponse;
    }

    /**
     * get node info.
     */
    @GetMapping(value = "/nodeInfo/{groupId}")
    public BaseResponse getNodeInfo(@PathVariable("groupId") Integer groupId)
            throws NodeMgrException {

        Instant startTime = Instant.now();
        log.info("start addNodeInfo startTime:{} groupId:{}",
                startTime.toEpochMilli(), groupId);

        // param
        NodeParam param = new NodeParam();
        param.setGroupId(groupId);

        // query node row
        TbNode tbNode = nodeService.queryNodeInfo(param);

        BaseResponse baseResponse = new BaseResponse(ConstantCode.SUCCESS);
        baseResponse.setData(tbNode);

        log.info("end addNodeInfo useTime:{} result:{}",
                Duration.between(startTime, Instant.now()).toMillis(), JSON.toJSONString(baseResponse));
        return baseResponse;
    }

    /**
     * Deploy by ipconf and tagId.
     */
    @PostMapping(value = "/deploy")
    public BaseResponse deploy(
            @RequestParam(value = "ipconf", required = true) String[] ipConf,
            @RequestParam(value = "tagId", required = true, defaultValue = "0") int tagId,
            @RequestParam(value = "rootDirOnHost", required = false, defaultValue = "/opt/fisco") String rootDirOnHost,
            @RequestParam(value = "chainName", required = false, defaultValue = "default_chain") String chainName
    ) throws NodeMgrException {
        Instant startTime = Instant.now();
        log.info("start deploy startTime:[{}], tagId:[{}], ipconf:[{}]",
                startTime.toEpochMilli(), tagId, ipConf);

        Pair<RetCode, String> deployResult = this.nodeService.deploy(chainName, ipConf, tagId,rootDirOnHost);
        return new BaseResponse(deployResult.getKey(),deployResult.getKey());
    }


}
