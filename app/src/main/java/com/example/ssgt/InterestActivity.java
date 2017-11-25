package com.example.ssgt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    String tmp;
    JSONArray retJson;
    //ArrayList<InterestData> interests;
    String url;
    RequestForm req;
    ArrayList<String> checkedInterest;

    ArrayList<Boolean> isChecked;

    JSONObject userinfo;

    private RecyclerView mRecyclerView;
    private InterestAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<InterestData> myDataset;


    DownloadWebPageTask dwTask = new DownloadWebPageTask(new DownloadWebPageTask.AsyncResponse() {
        @Override
        public void processFinish(JSONArray ret) throws JSONException {

            Log.e("err","processFinish");
            //Toast.makeText(getApplicationContext(),"processFinish:",Toast.LENGTH_SHORT).show();
            retJson = ret;





            for(int i = 0 ; i< retJson.length(); i++){

                JSONObject json = retJson.getJSONObject(i);

                String area = json.getString("area");

                isChecked.add(false);
                myDataset.add(new InterestData(area));

             //   Toast.makeText(getApplicationContext(),"되나:"+area,Toast.LENGTH_SHORT).show();
            }

            mAdapter.notifyDataSetChanged();

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        try {
            userinfo = new JSONObject(getIntent().getStringExtra("userinfo")); //signin 액티비티에서 데이터 받아옴

            Log.e("UserInfo", "Passed UserInfo: " + userinfo.getString("Nickname"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        init();

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new InterestAdapter(myDataset, new InterestAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {


                Toast.makeText(getApplicationContext(),String.valueOf(isChecked.get(position)),Toast.LENGTH_SHORT);

                if(!isChecked.get(position))
                {
                    checkedInterest.add(myDataset.get(position).area.toString());
                    isChecked.set(position,true);
                    Toast.makeText(getApplicationContext(),""+myDataset.get(position).area.toString()+"add",Toast.LENGTH_SHORT).show();

                }else{
                    checkedInterest.remove(myDataset.get(position).area.toString());
                    isChecked.set(position,false);
                    Toast.makeText(getApplicationContext(),""+myDataset.get(position).area.toString()+"delete",Toast.LENGTH_SHORT).show();

                }

                Log.e("interests",position+checkedInterest.toString());

            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }



    Button complete;



    void init(){

        myDataset = new ArrayList<>();
        checkedInterest = new ArrayList<>();
        isChecked = new ArrayList<>();

        url = "http://13.124.85.122:52273/getInterest";
        req = new RequestForm(url);


        dwTask.execute(req);

        complete = (Button)findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray interests = new JSONArray();

                for(int i = 0 ; i < checkedInterest.size();i++){

                    JSONObject iObject = new JSONObject();

                    try {
                        iObject.put("checkedArea",checkedInterest.get(i));
                        interests.put(iObject);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                try {
                    userinfo.put("interests",interests);
                    Log.e("jsoninput",userinfo.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                InsertDataTask insertTask = new InsertDataTask(userinfo);

                url = "http://13.124.85.122:52273/pushData";
                req = new RequestForm(url);


//                for ( int i = 0; i <interests.length(); i++){
//                    JSONObject json = interests.getJSONObject(i);
//
//                    if(json.has("ID"))
//                        id = json.getString("ID");
//                    if(json.has("PW"))
//                        name = json.getString("PW");
//                    if(json.has("Name"))
//                        quantity = json.getString("Name");
//                    if(json.has("no"))
//                        no = json.getInt("no");
//                    if(json.has("area"))
//                        area = json.getString("area");


                    insertTask.execute(req);

                Log.e("userinfo 전송","userinfo 전송");
                }
        });







    }


    public void selInterest(View v){

        if(((ToggleButton)v).isChecked())
            Toast.makeText(this,v.getId()+ "checked",Toast.LENGTH_SHORT ).show();
        else
            Toast.makeText(this,v.getId()+ "not checked",Toast.LENGTH_SHORT ).show();

        //배열같은곳에 누를때마다 getText해서 뭘 골랐는지 넣어두고
        //선택완료 버튼을 누를때 디비에 넣으면 될것.
    }


}


