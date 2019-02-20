package org.seiya.argbinding.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.seiya.argbinding.ArgBinding;
import org.seiya.argbinding.annotation.BindArg;
import org.seiya.argbinding.sample.model.ParcelableUser;
import org.seiya.argbinding.sample.model.SerializableUser;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSupportFragment extends Fragment {
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

        TextView textView = view.findViewById(R.id.tv);
        textView.setText(sBuilder.toString());
        return view;
    }
}
