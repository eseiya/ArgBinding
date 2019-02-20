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

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * Log util.
 *
 * @author ZhengAn
 * @date 2019/2/12
 */
public class Logger {
    private Messager messager;

    public Logger(Messager messager) {
        this.messager = messager;
    }

    public void info(String msg) {
        printMessage(Diagnostic.Kind.NOTE, msg);
    }

    private void note(String msg) {
        printMessage(Diagnostic.Kind.NOTE, msg);
    }

    public void warning(String msg) {
        printMessage(Diagnostic.Kind.WARNING, msg);
    }

    public void error(String msg) {
        printMessage(Diagnostic.Kind.ERROR, msg);
    }

    public void error(Throwable error) {
        printMessage(Diagnostic.Kind.ERROR, getStackTrace(error));
    }

    private void printMessage(Diagnostic.Kind kind, String msg) {
        if (msg == null || msg.isEmpty()) {
            return;
        }
        messager.printMessage(kind, msg);
    }

    private String getStackTrace(Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage()).append("\n");
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
