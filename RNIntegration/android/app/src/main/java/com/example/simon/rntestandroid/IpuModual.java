package com.example.simon.rntestandroid;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

/**
 * Created by simon on 2017/8/31.
 */

public class IpuModual extends ReactContextBaseJavaModule {

    private static final int REQUSET_CODE  = 3223;

    private Callback resultCallback;

    private ActivityEventListener activityEventListener = new ActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUSET_CODE && resultCode == Activity.RESULT_OK){
                if (resultCallback != null){
                    String result = data.getExtras().getString("result");
                    if (resultCallback != null){
                        resultCallback.invoke(result);
                    }
                }
            }
        }

        @Override
        public void onNewIntent(Intent intent) {

        }
    };

    public IpuModual(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(activityEventListener);
    }

    @Override
    public String getName() {
        return "IpuBasic";
    }

    @ReactMethod
    public void startActivity(String className,ReadableMap params){
        try {

            Activity currentActivity = getCurrentActivity();

            if (currentActivity == null) {
                Log.e("TAG","Activity doesn't exist");
                return;
            }

            Intent intent = new Intent(getCurrentActivity(),Class.forName(className));
            intent.putExtra("params",params.toHashMap());
            currentActivity.startActivity(intent);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void startActivityForResult(String className, ReadableMap params, Callback resultCallback){
        try {

            Activity currentActivity = getCurrentActivity();

            if (currentActivity == null) {
                Log.e("TAG","Activity doesn't exist");
                return;
            }

            this.resultCallback = resultCallback;

            Intent intent = new Intent(getCurrentActivity(),Class.forName(className));
            intent.putExtra("params",params.toHashMap());
            currentActivity.startActivityForResult(intent,REQUSET_CODE);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void setResultAndFinish(String resultText){
        Intent intent = new Intent();
        intent.putExtra("result",resultText);
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.setResult(Activity.RESULT_OK,intent);
            currentActivity.finish();
        }
    }
}
