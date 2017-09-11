package com.example.simon.rntestandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class NativeActivity extends AppCompatActivity {

    private static final int REQUEST_RN = 8383;
    private TextView getResultFromRN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        getResultFromRN = findViewById(R.id.getResultFromRN);

        findViewById(R.id.openChatComponent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("mychat://mychat/chat/Taylor"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        findViewById(R.id.finishToRN).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("result","从Native返回给RN");
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null && null != extras.getSerializable("params")){
            HashMap json = (HashMap) extras.getSerializable("params");
            TextView textView = findViewById(R.id.textFromRN);
            textView.setText("从RN获取到的值:"+json.toString());
        }


        findViewById(R.id.openChatComponentAndGetresult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("mychat://mychat/chat/ReturnValue"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivityForResult(intent,REQUEST_RN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RN){
            String result = data.getExtras().getString("result");
            if (result != null){
                this.getResultFromRN.setText(this.getResultFromRN.getText()+":" + result);
            }
        }

    }
}
