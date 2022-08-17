package com.example.pratice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button deep_link_task,asyn_task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deep_link_task=(Button) findViewById(R.id.deep_link_task);
        deep_link_task.setOnClickListener(this);
        asyn_task=(Button) findViewById(R.id.asyn_task);
        asyn_task.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deep_link_task:
                startActivity(new Intent(MainActivity.this,DeepLinkActivity.class));
                break;
            case R.id.asyn_task:
                startActivity(new Intent(MainActivity.this,AsyncTaskActivity.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + deep_link_task);
        }
    }
}