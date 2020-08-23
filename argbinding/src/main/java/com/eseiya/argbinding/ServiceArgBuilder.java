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

package com.eseiya.argbinding;

import android.content.Intent;
import android.content.ServiceConnection;

/**
 * The base class of Service arg builder.
 *
 * @author ZhengAn
 * @date 2019/5/12
 */
public abstract class ServiceArgBuilder<T extends ServiceArgBuilder<T>> extends IntentArgBuilder<T> {

    /**
     * Start Service use build intent, context can't be null.
     */
    @Override
    public void start() {
        checkContextNull();
        Intent intent = build();
        context.startService(intent);
    }

    /**
     * Bind Service use build intent, context can't be null.
     */
    public void bind(ServiceConnection connection, int flags) {
        checkContextNull();
        Intent intent = build();
        context.bindService(intent, connection, flags);
    }
}
