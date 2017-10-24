package com.example.ssgt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MyPageActivity extends AppCompatActivity {


    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("빠르게 배우는 바둑 교실");

        NestedScrollView scrollView = (NestedScrollView) findViewById (R.id.nestedview);
        scrollView.setFillViewport (true);

        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        init();
    }

    public void init(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyFragment(), "게시판");
        adapter.addFragment(new MyFragment(), "공지");
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    public void classInfo(View view) {

    }


    public void classMember(View view) {

    }


}




//<android.support.design.widget.AppBarLayout
//        android:layout_marginTop="20dp"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
//
//<android.support.design.widget.TabLayout
//        android:id="@+id/tabs"
//        android:layout_width="match_parent"
//        android:layout_height="60dp"
//        android:layout_gravity="bottom"
//        app:tabGravity="fill"
//        app:tabMode="fixed" />
//</android.support.design.widget.AppBarLayout>
//
//<android.support.v4.view.ViewPager
//        android:id="@+id/viewpager"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:layout_marginBottom="60dp"
//        app:layout_behavior="@string/appbar_scrolling_view_behavior" />


