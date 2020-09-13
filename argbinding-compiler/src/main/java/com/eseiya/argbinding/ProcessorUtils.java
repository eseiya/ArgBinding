/*
 * Copyright (C) 2019 AndyZheng.
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

import java.util.Set;

/**
 * Processor Utils.
 *
 * @author AndyZheng
 * @since 2019/2/12
 */
class ProcessorUtils {

    private ProcessorUtils() {
    }

    public static String toFirstLetterUpperCase(String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    public static void error(String msg, Object... args) {
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        throw new RuntimeException(msg);
    }
}
