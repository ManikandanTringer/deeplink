package com.example.pratice;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.List;

public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);

        Uri uri=getIntent().getData();
        if(uri!=null){
//            String path=uri.toString();
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

            List<String> params=uri.getPathSegments();
            String id=params.get(params.size()-1);
            Toast.makeText(this, "is="+id, Toast.LENGTH_SHORT).show();
        }
    }
}