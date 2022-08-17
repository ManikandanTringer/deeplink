package com.example.pratice;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends AsyncTask<HttpCall,String,String> {

    @Override
    protected String doInBackground(HttpCall... httpCalls) {

        HttpURLConnection urlConnection=null;
        HttpCall httpCall=httpCalls[0];
        StringBuilder response=new StringBuilder();
        try{
            String dataParams=getData(httpCall.getParams(),httpCall.getMethodType());
            URL url=new URL(httpCall.getMethodType() == HttpCall.GET ?  httpCall.getUrl()+dataParams: httpCall.getUrl());
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(httpCall.getMethodType() == HttpCall.GET ? "GET" : "POST");
            if(httpCall.getParams() != null && httpCall.getMethodType()==HttpCall.POST){
                OutputStream outputStream= urlConnection.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                writer.append(dataParams);
                writer.flush();
                writer.close();
                outputStream.close();
            }

            int responseCode=urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while((line=bufferedReader.readLine())!=null){
                    response.append(line);
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResponse(s);
    }

    public void onResponse(String response){

    }

    private String getData(HashMap<String,String> params, int methodType){
        StringBuilder  result=new StringBuilder();

        boolean isFirst=true;
        for(Map.Entry<String,String> entry:params.entrySet()){
            if(isFirst){
                isFirst=false;
                if(methodType == HttpCall.GET){
                    result.append("?");

                }else{
                    result.append("&");
                }
                try {
                    result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }
}
