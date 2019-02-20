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

/**
 * Common Utils.
 *
 * @author ZhengAn
 * @date 2019/2/12
 */
class CommonUtils {
    /**
     * Whether it's in Android or Java framework package.
     *
     * @param clsName
     * @return
     */
    public static boolean isFrameworkPackage(String clsName) {
        if (clsName.startsWith("android.") || clsName.startsWith("java.")
                || clsName.startsWith("androidx.")) {
            return true;
        }
        return false;
    }
}