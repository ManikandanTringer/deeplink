package com.example.pratice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AsyncTaskActivity extends AppCompatActivity {

    private TextView textView;

    private static final String TAG = "AsyncTaskActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        textView=(TextView) findViewById(R.id.api_txt);

        HttpCall httpCall=new HttpCall();
        httpCall.setMethodType(HttpCall.GET);
        httpCall.setUrl("https://reqres.in/api/users?page=2");
        HashMap<String,String> params=new HashMap<>();
        params.put("name","MAnikandann");
        httpCall.setParams(params);
        new HttpRequest(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject != null) {
                    try {
                        String name=jsonObject.getJSONObject(
                                "data").toString();
                        Log.d(TAG, "onResponse: "+name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                textView.setText("GET:"+response);
            }
        }.execute(httpCall);

//        HttpCall httpCallPost=new HttpCall();
//        httpCallPost.setMethodType(HttpCall.POST);
//        httpCallPost.setUrl("https://reqres.in/api/users?page=2");
//        HashMap<String,String> paramsPost=new HashMap<>();
//        paramsPost.put("name","Mani");
//        httpCallPost.setParams(paramsPost);
//        new HttpRequest(){
//            @Override
//            public void onResponse(String response) {
//                super.onResponse(response);
//                Log.d(TAG, "onResponse: "+response);
//
//                textView.setText(textView.getText()+"\nPOST:"+response);
//            }
//        }.execute(httpCallPost);

    }
}