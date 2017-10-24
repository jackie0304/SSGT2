package com.example.ssgt;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 혜진 on 2017-10-23.
 */

public class InsertDataTask extends AsyncTask<String,Integer,String> {

    @Override
    protected String doInBackground(String... urls) {
        try{
            return downloadUrl((String) urls[0]);
        }catch (IOException e){
            e.printStackTrace();
            return "다운로드 실패";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("InsertOK")){
            Log.d("ddd","ddddddddddddddddddddddddddddddd");
            new DownloadWebPageTask().execute("http://13.124.85.122:52273/search");
        }
    }

    public String downloadUrl(String myurl) throws IOException{
        HttpURLConnection conn = null;
        String response = "InsertFailed";
        try{
            URL url = new URL(myurl);
            conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            JSONObject job = new JSONObject();

            //insert하려는 데이터 셋팅 필요. 변수 넘겨야 할듯..
            job.put("ID", "jackie0304");
            job.put("PW", "1127");
            job.put("Name", "안동현");
            OutputStream os  = conn.getOutputStream();
            os.write(job.toString().getBytes());
            Log.d("ddd","ddddddddddddddddddddddddddddddd3");
            os.flush();

            int responseCode = conn.getResponseCode();
            Log.d("ddd","ddddddddddddddddddddddddddddddd4");
            if(responseCode == HttpURLConnection.HTTP_OK){
                response = "InsertOK";
            }

        }catch (JSONException e){
            Log.d("ddd","ddddddddddddddddddddddddddddddd3");
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }

        return  response;
    }

}
