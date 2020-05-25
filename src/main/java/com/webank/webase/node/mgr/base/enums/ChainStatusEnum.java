/**
 * Copyright 2014-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.webase.node.mgr.base.enums;

/**
 *
 */

public enum ChainStatusEnum {
    INITIALIZED((byte) 0, "Initialized."),
    DEPLOYING((byte) 1, "Deploying."),
    FAILED((byte) 2, "Deploy failed."),
    SUCCESS((byte) 3, "Deploy success."),
    ;

    private byte id;
    private String description;

    /**
     * @param id
     * @param description
     */
    ChainStatusEnum(byte id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * @param id
     * @return
     */
    public static ChainStatusEnum getById(byte id) {
        for (ChainStatusEnum value : ChainStatusEnum.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return null;
    }

    public byte getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "ChainStatusEnum{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
