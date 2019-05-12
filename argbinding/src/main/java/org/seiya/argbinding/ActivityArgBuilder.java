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

package org.seiya.argbinding;

import android.app.Activity;
import android.content.Intent;

/**
 * The base class of Activity arg builder.
 *
 * @author ZhengAn
 * @date 2019/5/12
 */
public abstract class ActivityArgBuilder<T extends ActivityArgBuilder<T>> extends IntentArgBuilder<T> {

    /**
     * Start activity use build intent, context can't be null.
     */
    @Override
    public void start() {
        checkContextNull();
        Intent intent = build();
        context.startActivity(intent);
    }

    /**
     * Start activity use build intent, context must be Activity and can't be null.
     */
    public void startForResult(int requestCode) {
        checkContextNull();
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Context is not Activity, can't use startForResult.");
        }
        Intent intent = build();
        ((Activity) context).startActivityForResult(intent, requestCode);
    }
}
