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

package com.eseiya.argbinding.sample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eseiya.argbinding.ArgBinding;
import com.eseiya.argbinding.sample.model.ParcelableUser;
import com.eseiya.argbinding.sample.model.SerializableUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ArgBinding.get().setDebug(true);
        ArgBinding.get().setCheckRequiredArg(true);

        final ArrayList<String> nameList = new ArrayList<>();
        nameList.add("iii");
        nameList.add("ppp");
        final ArrayList<ParcelableUser> pList = new ArrayList<>();
        pList.add(new ParcelableUser("李四2", 000));
        pList.add(new ParcelableUser("李四2", 111));

        findViewById(R.id.test_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dd", "onClick:");
                Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_LONG).show();

                TestActivityArgBuilder.newBuilder()
                        .setAge(25)
                        .setAgeOther(10)
                        .setName("kk")
                        .setAgeBase(1000)
                        .setP(new ParcelableUser("zhangsan", 15))
                        .setMS1(new SerializableUser("李四", 20))
                        .setAgeArray(new int[]{1, 2, 3})
                        .setAge2Array(new Integer[]{45, 5})
                        .setNameArray(new String[]{"dj,sss"})
                        .setNameList(nameList)
                        .setPArray(new ParcelableUser[]{new ParcelableUser("ddsd", 12), new ParcelableUser("wee", 13)})
                        .setSArray(new SerializableUser[]{new SerializableUser("李四2", 21), new SerializableUser("李四3", 200)})
                        .setPList(pList)
                        .setIntentFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .setContext(MainActivity.this)
                        .startForResult(11);

//               Intent intent = TestActivityIntentBuilder.newBuilder().setAge(25).build();
//               startActivity(intent);
            }
        });

        findViewById(R.id.test_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = TestArgFragmentArgBuilder.newBuilder()
                        .setAge(25)
                        .setAgeOther(10)
                        .setName("kk")
                        .setAgeBase(1000)
                        .setP(new ParcelableUser("zhangsan", 15))
                        .setMS1(new SerializableUser("李四", 20))
                        .setAgeArray(new int[]{1, 2, 3})
                        .setAge2Array(new Integer[]{45, 5})
                        .setNameArray(new String[]{"dj,sss"})
                        .setNameList(nameList)
                        .setPArray(new ParcelableUser[]{new ParcelableUser("ddsd", 12), new ParcelableUser("wee", 13)})
                        .setSArray(new SerializableUser[]{new SerializableUser("李四2", 21), new SerializableUser("李四3", 200)})
                        .setPList(pList)
                        .build();
                replaceFragment(R.id.common_content, fragment);
            }
        });

        findViewById(R.id.test_support_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.Fragment fragment = TestSupportFragmentArgBuilder.newBuilder()
                        .setAge(25)
                        .setAgeOther(10)
                        .setName("kk")
                        .setP(new ParcelableUser("zhangsan", 15))
                        .setMS1(new SerializableUser("李四", 20))
                        .setAgeArray(new int[]{1, 2, 3})
                        .setAge2Array(new Integer[]{45, 5})
                        .setNameArray(new String[]{"dj,sss"})
                        .setNameList(nameList)
                        .setPArray(new ParcelableUser[]{new ParcelableUser("ddsd", 12), new ParcelableUser("wee", 13)})
                        .setSArray(new SerializableUser[]{new SerializableUser("李四2", 21), new SerializableUser("李四3", 200)})
                        .setPList(pList)
                        .build();
                replaceFragment(R.id.support_common_content, fragment);
            }
        });

        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SampleService.class);
//                startService(intent);
                SampleServiceArgBuilder.newBuilder()
                        .setAge(25)
                        .setAgeOther(10)
                        .setName("kk")
                        .setP(new ParcelableUser("zhangsan", 15))
                        .setMS1(new SerializableUser("李四", 20))
                        .setAgeArray(new int[]{1, 2, 3})
                        .setAge2Array(new Integer[]{45, 5})
                        .setNameArray(new String[]{"dj,sss"})
                        .setNameList(nameList)
                        .setPArray(new ParcelableUser[]{new ParcelableUser("ddsd", 12), new ParcelableUser("wee", 13)})
                        .setSArray(new SerializableUser[]{new SerializableUser("李四2", 21), new SerializableUser("李四3", 200)})
                        .setPList(pList)
                        .setContext(MainActivity.this).start();
            }
        });

        findViewById(R.id.stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleService.class);
                stopService(intent);
            }
        });

        findViewById(R.id.bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SampleService.class);
//                bindService(intent, sc, BIND_AUTO_CREATE);

                SampleServiceArgBuilder.newBuilder()
                        .setAge(25)
                        .setAgeOther(10)
                        .setName("kk")
                        .setP(new ParcelableUser("zhangsan", 15))
                        .setMS1(new SerializableUser("李四", 20))
                        .setAgeArray(new int[]{1, 2, 3})
                        .setAge2Array(new Integer[]{45, 5})
                        .setNameArray(new String[]{"dj,sss"})
                        .setNameList(nameList)
                        .setPArray(new ParcelableUser[]{new ParcelableUser("ddsd", 12), new ParcelableUser("wee", 13)})
                        .setSArray(new SerializableUser[]{new SerializableUser("李四2", 21), new SerializableUser("李四3", 200)})
                        .setPList(pList)
                        .setContext(MainActivity.this).bind(sc, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(sc);
            }
        });
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.w("MainActivity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.w("MainActivity", "onServiceDisconnected");
        }
    };

    protected void replaceFragment(@IdRes int id, Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(id, fragment);
        transaction.commitAllowingStateLoss();
    }

    protected void replaceFragment(@IdRes int id, android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("dd", "onActivityResult " + requestCode);
        Toast.makeText(MainActivity.this, "onActivityResult " + requestCode, Toast.LENGTH_LONG).show();
    }
}
