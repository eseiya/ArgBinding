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

package org.seiya.argbinding.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import org.seiya.argbinding.ArgBinding;
import org.seiya.argbinding.annotation.BindArg;
import org.seiya.argbinding.sample.base.BaseArgFragment;
import org.seiya.argbinding.sample.model.ParcelableUser;
import org.seiya.argbinding.sample.model.SerializableUser;

public class TestArgFragment extends BaseArgFragment {

    /**
     * 年龄.
     */
    @BindArg
    int age;
    @BindArg(value = "ageOther", required = false)
    Integer age2;
    @BindArg
    String name;
    @BindArg(required = false)
    ParcelableUser p;
    @BindArg
    SerializableUser mS1;
    @BindArg(required = false)
    int[] ageArray;
    @BindArg(required = false)
    Integer[] age2Array;
    @BindArg(required = false)
    String[] nameArray;
    @BindArg(required = false)
    ParcelableUser[] pArray;
    @BindArg(required = false)
    SerializableUser[] sArray;
    @BindArg(required = false)
    ArrayList<String> nameList;
    @BindArg(required = false)
    ArrayList<ParcelableUser> pList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArgBinding.get().bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);

        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("name:" + name + "\n");
        sBuilder.append("age:" + age + "\n");
        sBuilder.append("age2:" + age2 + "\n");
        sBuilder.append("p:" + p + "\n");
        sBuilder.append("mS1:" + mS1 + "\n");

        sBuilder.append("ageArray:" + Arrays.toString(ageArray) + "\n");
        sBuilder.append("nameArray:" + Arrays.toString(nameArray) + "\n");
        sBuilder.append("pArray:" + Arrays.toString(pArray) + "\n");
        sBuilder.append("sArray:" + Arrays.toString(sArray) + "\n");
        sBuilder.append("nameList:" + nameList + "\n");
        sBuilder.append("pList:" + pList + "\n");
        sBuilder.append("ageBase:" + ageBase + "\n");

        TextView textView = view.findViewById(R.id.tv);
        textView.setText(sBuilder.toString());
        return view;
    }
}
