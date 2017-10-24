package com.example.ssgt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //메인메뉴 가는 코드
        Toast.makeText(getApplicationContext(),"dd",Toast.LENGTH_LONG);
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class );
        startActivity(intent);

        btn_login = (Button)findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        //hi branch되나볼까나 흠 합쳐지나



                Intent i = new Intent(getApplicationContext(),InterestActivity.class);
                startActivity(i);

            }
        });
    }





}
