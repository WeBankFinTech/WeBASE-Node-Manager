/**
 * Copyright 2014-2020 the original author or authors.
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

package com.webank.webase.node.mgr.base.enums;

/**
 * related with constant-deployType in application.yml
 */
public enum DeployType {
    MANUAL(0), VISUAL_DEPLOY(1);

    private int value;

    private DeployType(Integer type) {
        this.value = type;
    }

    public int getValue() {
        return this.value;
    }

}
