package com.example.ssgt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Donghyun on 2017-11-17.
 */

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder>{

    private ArrayList<InterestData> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ToggleButton mBtn1;
      //  public ToggleButton mBtn2;

        public ViewHolder(View view){
            super(view);

            mBtn1 = (ToggleButton)view.findViewById(R.id.toggle1);
        //    mBtn2 = (ToggleButton)view.findViewById(R.id.toggle2);



        }

    }

    public InterestAdapter(ArrayList<InterestData> myDataset){

        mDataset = myDataset;
    }

    @Override
    public InterestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_view,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mBtn1.setText(mDataset.get(position).area);
        holder.mBtn1.setTextOn(mDataset.get(position).area);
        holder.mBtn1.setTextOff(mDataset.get(position).area);

//        holder.mBtn2.setText(mDataset.get(position).area);
//        holder.mBtn2.setTextOn(mDataset.get(position).area);
//        holder.mBtn2.setTextOff(mDataset.get(position).area);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class InterestData{

    public String area;

    public InterestData(String area){

        this.area = area;
    }

}
