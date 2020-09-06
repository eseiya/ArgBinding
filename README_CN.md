# ArgBinding
ArgBinding是一个Android上简化`Activity`和`Fragment`参数传递的工具，与常规方法相比有以下优势：

- 通过构建者模式传递参数，避免添加参数常量和工厂方法。
- 编译期间检查参数类型和名称是否正确。
- 检查必传参数是否传递。

##### 使用方法

`Fragment`的ArgBinding示例。

```java
//definition the Fragment
public class TestFragment extends Fragment {

    @BindArg
    String id;
    @BindArg("name")
    String name;
    @BindArg(required = false)
    int age;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArgBinding.get().bind(this);
    }
}

// user the Fragment
TestFragment fragment = TestArgFragmentArgBuilder.newBuilder()
        .setId("001")
        .setName("ZhangSan")
        .setAge(12)
        .build();
```

`Activity`的ArgBinding示例。

```java
//definition the Activity
public class TestActivity extends AppCompatActivity {

    @BindArg
    String id;
    @BindArg
    String name;
    @BindArg
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArgBinding.get().bind(this);
    }
}

// user the Activity
TestActivityArgBuilder.newBuilder()
                .setId("001")
                .setName("ZhangSan")
                .setAge(12)
                .setContext(this)
                .setIntentFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .start();
```

常规方法，`Fragment`参数传递要使用官方推荐的`setArguments`方法，因为在很多场景下都会出现`Fragment`重新创建的情况，重新创建的时候系统调用的是`Fragment`的默认构造器，这个时候通过其他构造器传递的参数将会消失。下面是常规方法示例。

```java
// definition the Fragment
public class TestFragment extends Fragment {
    private static final String EXTRA_ID = "id";
    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_AGE = "age";
    private String id;
    private String name;
    private int age;

    public static TestFragment newInstance(String id, String name, int age) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, id);
        args.putString(EXTRA_NAME, name);
        args.putInt(EXTRA_AGE, age);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) 	  {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        id = args.getString(EXTRA_ID);
        name = args.getString(EXTRA_NAME);
        age = args.getInt(EXTRA_AGE);
    }
}
//user the Fragment
TestFragment fragment = TestFragment.newInstance("001", "ZhangSan", 12);
```

###### 依赖配置

```groovy
dependencies {
    implementation 'com.eseiya.argbinding:argbinding:1.1.2'
    annotationProcessor 'com.eseiya.argbinding:argbinding-compiler:1.1.2'
}
```

如果使用`Kotlin`，用`kapt`代替`annotationProcessor`。

本库依赖`android support`包，如果有冲突，可以排除本库的`support`包。

###### 混淆规则

```
-keep class * extends com.eseiya.argbinding.ArgBinder
```

更多使用方法请参考[argbinding-sample](https://github.com/hbzha/ArgBinding/tree/master/argbinding-sample)。

##### 特殊说明

- `ArgBinding.get().setCheckRequiredArg()`方法设置是否检查必传参数是否传递，默认是检查。
- `BindArg`注解的`required`方法设置是否必传参数，默认为true，配合；`value`方法设置参数别名，可以修改参数`Key`和生成的`Builder`方法名称。
- `BindTarget`注解，在没有`BindArg`注解的字段时才需要注解到需要生成`Builder`的类上。
- `Builder`和`Binder`类是在build过程中自动生成的，编写完代码或者修改后，build工程才会生效。

##### License

ArgBinding is under the Apache-2.0 license. See the [LICENSE](LICENSE) file for details.



