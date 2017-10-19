package com.example.ssgt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class InterestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);


        init();
    }



    Button complete;



    void init(){

        complete = (Button)findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),SecondActivity.class);
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
