package com.example.ssgt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 혜진 on 2017-10-20.
 */

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

//        View v = inflater.inflate(R.layout.fragment_1,container,false);
//        ViewPager viewPager = (ViewPager)v.findViewById(R.id.viewpager);
//        setupViewPaget(viewPager);
//        ((MyPageActivity)getActivity()).tabLayout.setupWithViewPager(viewPager);
//        return v;
        int resId = R.layout.fragment_1;
        return inflater.inflate(resId, null);

    }

    private void setupViewPaget(ViewPager viewPager){
        MyAdapter adapter = new MyAdapter(this.getChildFragmentManager());
        adapter.addFragment(new MyFragment(),"게시판");
        adapter.addFragment(new MyFragment(), "공지글");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
}
