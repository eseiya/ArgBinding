# ArgBinding
[中文文档](README_CN.md)

ArgBinding is an Android tool to simplify `Activity` and `Fragment` parameter passing.Compared with the conventional method, it has the following advantages:

- Pass parameters through the builder mode to avoid adding parameter constants and factory methods.
- Check that the parameter's type and name is correct during compilation.
- Check if the required parameters are passed.

##### Instructions

The `Fragment` example of ArgBinding.

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
TestFrTestFragment fragment = TestFragmentBuilder.newBuilder()
        .setId("001")
        .setName("ZhangSan")
        .setAge(12)
        .build();
```

The `Activity` example of ArgBinding.

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
TestActivityIntentBuilder.newBuilder()
                .setId("001")
                .setName("ZhangSan")
                .setAge(12)
                .setContext(this)
                .setIntentFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .startActivity();
```

In the conventional method, the `Fragment` parameter is passed using the officially recommended `setArguments` method, because in many scenarios, the `Fragment` re-creation will occur. When re-creating, the system calls the default constructor of the `Fragment`. The parameters passed by other constructors at this time will disappear. Below is an example of regular method.

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

###### Dependencies

```groovy
dependencies {
    implementation 'org.seiya:argbinding:1.0.0'
    annotationProcessor 'org.seiya:argbinding-compiler:1.0.0'
}
```

If you are using `Kotlin`, replace `annotationProcessor` with `kapt`.

This library relies on the `android support` . If there is a conflict, you can exclude `support` library of this library.

###### Proguard rules

```
-keep class * extends org.seiya.argbinding.ArgBinder
```

More usage reference [argbinding-sample](https://github.com/hbzha/ArgBinding/tree/master/argbinding-sample).

##### Special Instructions

- `ArgBinding.get().setCheckRequiredArg()`sets whether to check the required parameter are passed. The default is to check.
- `BindArg`'s method `required` set whether parameter is required, the default is true; `value` set parameter's alias, witch can modify the parameter `Key` and the generated `Builder` method name.
- `BindTarget`，you need to annotate the class that needs to generate the `Builder` when there is no `BindArg` annotated field.
- `Builder`和`Binder` class is automatically generated during the build process. After the code is compiled or modified, it will not take effect until the build project.

##### License

ArgBinding is under the Apache-2.0 license. See the [LICENSE](LICENSE) file for details.



