# Android  ReactNative和Activity间互相调用


因为项目中需要集成ReactNative，涉及到Android 原生Activity和RN之间的交互，就需要提前做预研，写了这个测试的demo；

1. 将ReactNative集成到现有项目参考的[ReactNative官网](http://reactnative.cn/docs/0.47/integration-with-existing-apps.html#content)的教程

在编译时会存在一个错误，因为RN使用的findbug和Android中的版本不一致，会报错：
`Warning:Conflict with dependency 'com.google.code.findbugs:jsr305'. Resolved versions for app (3.0.1) and test app (2.0.1) differ. See http://g.co/androidstudio/app-test-app-conflict for details.
`

解决办法：

在项目的app根目录中build.gradle中的
```
android{
……
configurations.all {
    resolutionStrategy.force 'com.google.code.findbugs:jsr305:3.0.0'
}
}
```

2. ReactNative和原生互相调用
    1. RN唤起原生的Activity，参考[Android原生模块](https://facebook.github.io/react-native/docs/native-modules-android.html)创建一个插件；
        在插件添加一个`public void startActivityForResult(String className, ReadableMap params, Callback resultCallback)`的方法，调用context.startActivityForResult启动目标Activity；
    这里需要注意重新ReactNative的onActivityResult方法：
    ```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*这里网上很多资料都没说明*/
        mReactInstanceManager.onActivityResult(this,requestCode,resultCode,data);
    }
    ```
    2. Activity调用RN中的界面，页面跳转参考[react-navigator](https://reactnavigation.org/docs/guides/linking),主要是在ReactNative使用的Activity的<intent-filter>中添加上对应的scheme、host属性；并在react-navigator的StackNavigator注册页面；


具体的实现参考[demo](https://github.com/Shelomi/RNIntegration)
        
