package com.example.ssgt;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 혜진 on 2017-10-23.
 */
public class DownloadWebPageTask extends AsyncTask< String, Integer, String> {

        @Override
        protected String doInBackground(String... urls) {
            try{
                return (String)downdloadUrl((String) urls[0]);
            }catch(IOException e){
                e.printStackTrace();
                return "다운로드 실패";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            jsonParsing(s);
        }


        public String downdloadUrl(String myurl) throws IOException{
            HttpURLConnection conn = null;
            try{
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf,"utf-8"));
                String line = null;
                String page = "";
                while ((line = bufreader.readLine()) != null) {
                    page += line;
                }
                Log.i("page : " , page);
                return page;
            }finally {
                conn.disconnect();
            }
        }

        void jsonParsing (String file) {

            try {
                JSONArray jsonArray = new JSONArray(file);
                for ( int i = 0; i <jsonArray.length(); i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    String id = json.getString("ID");
                    String name = json.getString("PW");
                    String quantity = json.getString("Name");
                }
            }catch (Exception e) {
                Log.i("err :", e.getMessage());
            }
        }
}

