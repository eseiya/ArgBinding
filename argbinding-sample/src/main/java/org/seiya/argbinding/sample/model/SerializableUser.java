/*
 * Copyright (C) 2019 ZhengAn.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.seiya.argbinding.sample.model;

import java.io.Serializable;

/**
 * Created by @author joker on 2018/7/10.
 */
public class SerializableUser implements Serializable {
    public String name;
    public int age;

    public SerializableUser() {
    }

    public SerializableUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "SerializableUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
