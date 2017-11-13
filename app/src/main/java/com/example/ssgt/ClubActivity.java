package com.example.ssgt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by seyeon on 2017-10-30.
 */

public class ClubActivity extends AppCompatActivity {
    View syView;
    ArrayList<Lecture> items;
    ListView listView;
    LectureAdapter lectureAdapter;
    Button createbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        init();
    }

    void init(){
        items = new ArrayList<Lecture>();
        listView = (ListView)findViewById(R.id.listview);
        createbtn = (Button)findViewById(R.id.createbtn);
        items.add(new Lecture("1","English Chocolate study","2017-11-20","Rotterdam"));
        items.add(new Lecture("2","Funny tennis Practicing","2017-02-02","Amsterdam"));
        items.add(new Lecture("3","Let's study mathmatics","2018-11-23","Rotterdam"));
        items.add(new Lecture("4","노래! 어렵지 않아요~","2017-08-31","강남구"));
        items.add(new Lecture("5","다함께 배드민턴","2017-09-03","광진구"));
        items.add(new Lecture("6","빠르게 배우는 바둑교실","2017-11-22","성북구"));
        lectureAdapter = new LectureAdapter(getApplicationContext(),R.layout.row_lec,items);
        listView.setAdapter(lectureAdapter);
    }

    public void addClubBtn(View v){
        //Intent intent = new Intent(getApplicationContext(), ClubAddActivity.class);
        //startActivity(intent);
    }
    //fragment 로 clubAddFragment 랑 club detailViewFragment 만들기
}
