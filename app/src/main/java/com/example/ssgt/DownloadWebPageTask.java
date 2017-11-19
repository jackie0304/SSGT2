package com.example.ssgt;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
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


        JSONArray retObj;

        public AsyncResponse delegate = null;

        DownloadWebPageTask(AsyncResponse delegate){

            this.delegate = delegate;

        }

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

            Log.i("onPostExecute",s);
            jsonParsing(s);
            try {
                delegate.processFinish(retObj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        public String downdloadUrl(String myurl) throws IOException{
            HttpURLConnection conn = null;
            try{
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("POST");
//
//                conn.setRequestProperty("Content-Type","application/json");
//                conn.setDoOutput(true);

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

            String id = "";
            String name = "";
            String quantity = "";
            int  no = 0;
            String area = "";

            try {
                retObj = new JSONArray(file);
                for ( int i = 0; i <retObj.length(); i++){
                    JSONObject json = retObj.getJSONObject(i);

                    if(json.has("ID"))
                     id = json.getString("ID");
                    if(json.has("PW"))
                     name = json.getString("PW");
                    if(json.has("Name"))
                    quantity = json.getString("Name");
                    if(json.has("no"))
                    no = json.getInt("no");
                    if(json.has("area"))
                     area = json.getString("area");

                    Log.i("jsonParsing:",no+area);
                }

            }catch (Exception e) {
                Log.i("err :", e.getMessage());
            }
        }

        public interface AsyncResponse{

            void processFinish(JSONArray ret) throws JSONException;
        }




}

