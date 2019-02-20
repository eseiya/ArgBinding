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
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;

import org.seiya.argbinding.annotation.BindArg;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The util for arg binding.
 *
 * @author ZhengAn
 * @date 2019/2/12
 */
public class ArgBinding {

    private static ArgBinding instance;
    private static final String TAG = "ArgBinding";

    private Map<Class<?>, Class<? extends ArgBinder>> binderClass = new LinkedHashMap<>();
    /**
     * Whether to check the required arg.
     */
    private boolean checkRequiredArg = true;
    private boolean debug = true;

    private ArgBinding() {
    }

    public static ArgBinding get() {
        if (instance == null) {
            synchronized (ArgBinding.class) {
                if (instance == null) {
                    instance = new ArgBinding();
                }
            }
        }
        return instance;
    }


    /**
     * Whether debug logging is enabled.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * Whether to check the required arg.
     */
    public void setCheckRequiredArg(boolean checkRequiredArg) {
        this.checkRequiredArg = checkRequiredArg;
    }

    /**
     * Bind annotated fields and methods in the specified {@link BindArg}.
     *
     * @param target Target class for view binding.
     */
    @NonNull
    @UiThread
    public void bind(@NonNull Activity target) {
        bind(target, target.getIntent().getExtras());
    }

    /**
     * Bind annotated fields in the specified {{@link BindArg}.
     *
     * @param target Target class for arg binding.
     */
    @NonNull
    @UiThread
    public void bind(@NonNull android.app.Fragment target) {
        bind(target, target.getArguments());
    }

    /**
     * Bind annotated fields in the specified {@link BindArg}.
     *
     * @param target Target class for arg binding.
     */
    @NonNull
    @UiThread
    public void bind(@NonNull android.support.v4.app.Fragment target) {
        bind(target, target.getArguments());
    }

    /**
     * Bind annotated fields in the specified {@code target} .
     *
     * @param target Target class for arg binding.
     */
    @NonNull
    @UiThread
    private void bind(@NonNull Object target, Bundle args) {
        Class<?> targetClass = target.getClass();
        if (debug) Log.d(TAG, "Looking up binding for " + targetClass.getName());
        Class<? extends ArgBinder> bindingClass = findBinderClass(target.getClass());
        if (bindingClass == null) {
            return;
        }
        try {
            ArgBinder binder = bindingClass.newInstance();
            binder.bindArgs(target, args, checkRequiredArg);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @CheckResult
    @Nullable
    @UiThread
    private Class<? extends ArgBinder> findBinderClass(Class<?> targetCls) {
        Class<? extends ArgBinder> binderCls = binderClass.get(targetCls);
        if (binderCls != null || binderClass.containsKey(targetCls)) {
            if (debug) Log.d(TAG, "HIT: Cached in binding map.");
            return binderCls;
        }

        String clsName = targetCls.getName();
        if (CommonUtils.isFrameworkPackage(clsName)) {
            if (debug) Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            return null;
        }
        String binderClassName = clsName + CommonConstants.BINDER_NAME_SUFFIX;
        try {
            binderCls = (Class<? extends ArgBinder>) Class.forName(binderClassName);
            if (debug) Log.d(TAG, "HIT: Loaded binding class and method.");
        } catch (ClassNotFoundException e) {
            if (debug)
                Log.d(TAG, "Not found. Trying superclass " + targetCls.getSuperclass().getName());
            binderCls = findBinderClass(targetCls.getSuperclass());
        }
        //If don't find it, put null in.
        binderClass.put(targetCls, binderCls);
        return binderCls;
    }
}
