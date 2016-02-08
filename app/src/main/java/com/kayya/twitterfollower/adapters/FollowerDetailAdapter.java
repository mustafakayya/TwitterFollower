package com.kayya.twitterfollower.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.kayya.twitterfollower.R;
import com.kayya.twitterfollower.models.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mustafakaya on 08/02/16.
 */
public class FollowerDetailAdapter extends RecyclerView.Adapter<FollowerDetailAdapter.ViewHolder> {

    List<Tuple> infoList;

    public FollowerDetailAdapter(List<Tuple> infoList) {
        if(infoList == null)
            infoList = new ArrayList<>();
        this.infoList = infoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listrow_followerdetail, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tuple info = infoList.get(position);
        holder.lblKey.setText(info.getKey());
        holder.lblValue.setText(info.getValue());
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblKey;
        TextView lblValue;
        public ViewHolder(View itemView) {
            super(itemView);
            lblKey = (TextView)itemView.findViewById(R.id.lblKey_followerDetailItem);
            lblValue = (TextView)itemView.findViewById(R.id.lblValue_followerDetailItem);
        }
    }
}
