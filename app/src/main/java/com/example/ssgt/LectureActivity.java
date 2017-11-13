package com.example.ssgt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by seyeon on 2017-10-30.
 */

public class LectureActivity extends AppCompatActivity {

    ArrayList<Lecture> items;
    ListView listView;
    LectureAdapter lectureAdapter;
    Toolbar toolbar;
    TextView textView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lec);
        init();
    }

    void init(){
        textView = (TextView)findViewById(R.id.textView);
        //title = (TextView)findViewById(R.id.title);
        //title.setText("[ LECTURE LIST ]");
        //title.setTextSize(30);
        //title.setBackgroundColor(getResources().getColor(R.color.pink));
        //title.setGravity(Gravity.CENTER);
        //toolbar = (Toolbar)findViewById(R.id.app_bar);
        //toolbar.setBackgroundColor(getResources().getColor(R.color.pink));
        //setSupportActionBar(toolbar);
        //toolbar.setTitle(textView.getImeActionId());
        //getSupportActionBar().setTitle("test5");
        listView = (ListView)findViewById(R.id.listview);
        items = new ArrayList<Lecture>();
        items.add(new Lecture("1","Let's study KOREAN","2017-11-30","Rotterdaam"));
        items.add(new Lecture("2","Special Dancing Practice","2017-12-02","Amsterdam"));
        items.add(new Lecture("3","한자를 공부합시다","2017-10-23","금천구"));
        items.add(new Lecture("4","노래! 어렵지 않아요~","2017-08-31","강남구"));
        items.add(new Lecture("5","다함께 배드민턴","2017-09-03","광진구"));
        items.add(new Lecture("6","빠르게 배우는 바둑교실","2017-11-22","성북구"));
        lectureAdapter = new LectureAdapter(getApplicationContext(),R.layout.row_lec,items);
        listView.setAdapter(lectureAdapter);
    }
}
