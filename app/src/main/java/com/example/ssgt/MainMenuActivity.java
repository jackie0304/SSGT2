package com.example.ssgt;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    //FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }
    public void init(){

    }
    public void gotoMyPageMenu(View v){
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frameLayout,new Fragment_mypage())
//                .commit();
    }

    public void gotoWantedMenu(View v){
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frameLayout,new Fragment_lecture())
//                .commit();
        Intent intent = new Intent(getApplicationContext(), WantedActivity.class);
        startActivity(intent);
    }
    public void gotoMentoringMenu(View v){
        Intent intent = new Intent(getApplicationContext(), MentoringActivity.class);
        startActivity(intent);
    }
    public void gotoClubMenu(View v){
        Intent intent = new Intent(getApplicationContext(), ClubActivity.class);
        startActivity(intent);
    }
}
