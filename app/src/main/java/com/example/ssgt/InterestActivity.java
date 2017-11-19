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


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<InterestData> myDataset;


    DownloadWebPageTask dwTask = new DownloadWebPageTask(new DownloadWebPageTask.AsyncResponse() {
        @Override
        public void processFinish(JSONArray ret) throws JSONException {

            Log.e("err","processFinish");
            //Toast.makeText(getApplicationContext(),"processFinish:",Toast.LENGTH_SHORT).show();
            retJson = ret;


            while(retJson.get(0).equals(null)); //jsonArray가 NULL이면 대기  retJson.isNULL은 안되는지 해보기

            for(int i = 0 ; i< retJson.length(); i++){

                JSONObject json = retJson.getJSONObject(i);

                String area = json.getString("area");

                myDataset.add(new InterestData(area));

                Toast.makeText(getApplicationContext(),"되나:"+area,Toast.LENGTH_SHORT).show();
            }

            mAdapter.notifyDataSetChanged();

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        init();

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new InterestAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);





    }



    Button complete;



    void init(){

        myDataset = new ArrayList<>();

        dwTask.execute("http://13.124.85.122:52273/getInterest");

        complete = (Button)findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(i);

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
