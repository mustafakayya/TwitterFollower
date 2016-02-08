package com.kayya.twitterfollower.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kayya.twitterfollower.R;
import com.kayya.twitterfollower.communications.FollowerListListener;
import com.kayya.twitterfollower.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mustafakaya on 07/02/16.
 */
public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.ViewHolder> {

    List<User> followerList;
    Context context;
    FollowerListListener listener;


    public FollowerListAdapter(List<User> followerList, Context context, FollowerListListener listener) {
        if (followerList == null)
            followerList = new ArrayList<>();
        this.followerList = followerList;
        this.context = context ;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listrow_followerlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public void addFollower(List<User> newFollowers){
        if (newFollowers == null)
            newFollowers = new ArrayList<>();
        followerList.addAll(newFollowers);
        notifyDataSetChanged();
    }

    public void setFollowerList(List<User> followerList) {
        if (followerList == null)
            followerList = new ArrayList<>();
        this.followerList = followerList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User follower = followerList.get(position);
        holder.lblName.setText(follower.getName());
        holder.lblScreenName.setText("@"+follower.getScreen_name());
        Picasso.with(context).load(follower.getProfile_image_url()).into(holder.ivProfileImage);
        holder.cardViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onFollowerSelected(follower);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return followerList.size();
    }


    public List<User> getFollowerList() {
        return followerList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView lblName;
        public TextView lblScreenName;
        public ImageView ivProfileImage;
        public CardView cardViewContainer;
        public ViewHolder(View v) {
            super(v);
            cardViewContainer = (CardView)v.findViewById(R.id.cardView_followerListItem);
            ivProfileImage=(ImageView)v.findViewById(R.id.ivProfileImage_followerListItem);
            lblName = (TextView) v.findViewById(R.id.lblName_followerListItem);
            lblScreenName = (TextView) v.findViewById(R.id.lblScreenName_followerListItem);
        }
    }
}
