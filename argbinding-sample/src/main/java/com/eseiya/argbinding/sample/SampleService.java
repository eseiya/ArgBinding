package com.eseiya.argbinding.sample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.eseiya.argbinding.ArgBinding;
import com.eseiya.argbinding.annotation.BindArg;
import com.eseiya.argbinding.sample.model.ParcelableUser;
import com.eseiya.argbinding.sample.model.SerializableUser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author AndyZheng
 * @since 2019/5/12
 */
public class SampleService extends Service {
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
    public void onCreate() {
        super.onCreate();
        Log.w("SampleService", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.w("SampleService", "onBind " + intent);
        bindExtra(intent);
        return null;
    }

    private void bindExtra(Intent intent) {
        ArgBinding.get().bind(this, intent);

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

        Toast.makeText(this, sBuilder.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w("SampleService", "onUnbind " + intent);
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("SampleService", "onStartCommand " + intent);
        bindExtra(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("SampleService", "onDestroy");
    }
}
