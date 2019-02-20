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

import android.os.Bundle;
import android.support.annotation.CallSuper;

/**
 * The base class of arg binder.
 *
 * @author ZhengAn
 * @date 2019/2/12
 */
public abstract class ArgBinder<T> {

    /**
     * Bind args to target.
     *
     * @param target
     * @param args
     * @param checkRequiredArg Whether to check the required args
     */
    public void bindArgs(T target, Bundle args, boolean checkRequiredArg) {
        if (checkRequiredArg) {
            checkRequiredArg(args);
        }
        if (args != null) {
            bindArgs(target, args);
        }
    }

    @CallSuper
    protected abstract void bindArgs(T target, Bundle args);

    /**
     * Check required arg, if there are no required arg, the method will be empty.
     *
     * @param args
     */
    @CallSuper
    protected abstract void checkRequiredArg(Bundle args);
}
