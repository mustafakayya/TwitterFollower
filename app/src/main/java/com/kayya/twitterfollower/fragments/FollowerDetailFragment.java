package com.kayya.twitterfollower.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kayya.twitterfollower.FollowerDetailActivity;
import com.kayya.twitterfollower.FollowerListActivity;
import com.kayya.twitterfollower.R;
import com.kayya.twitterfollower.adapters.FollowerDetailAdapter;
import com.kayya.twitterfollower.models.Tuple;
import com.kayya.twitterfollower.models.User;
import com.kayya.twitterfollower.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Follower detail screen.
 * This fragment is either contained in a {@link FollowerListActivity}
 * in two-pane mode (on tablets) or a {@link FollowerDetailActivity}
 * on handsets.
 */
public class FollowerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */


    private String CURRENT_FOLLEWER_ARG = "current.follower";
    User follower;
    boolean singlePane;
    RecyclerView detailRecyclerView;
    LinearLayoutManager detailRecyclerViewLayoutManager;
    FollowerDetailAdapter adapter;
    ImageView ivUserBG;
    ImageView ivUserImage;


    public static FollowerDetailFragment newInstance(User follower) {
        FollowerDetailFragment fragment = new FollowerDetailFragment();
        fragment.follower = follower;
        return fragment;
    }

    public FollowerDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            follower = savedInstanceState.getParcelable(CURRENT_FOLLEWER_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_followerdetail, container, false);
        detailRecyclerView = (RecyclerView) mainView.findViewById(R.id.detailRecyclerView_fFollowerDetail);
        ivUserImage = (ImageView) mainView.findViewById(R.id.ivProfileImage_fFollowerDetail);
        ivUserBG = (ImageView) mainView.findViewById(R.id.ivUserBG_fFollowerDetail);
        if (follower.isProfile_use_background_image())
            Picasso.with(getActivity()).load(follower.getProfile_banner_url()).into(ivUserBG);
        Picasso.with(getActivity()).load(follower.getProfile_image_url()).transform(new CircleTransform()).into(ivUserImage);
        initRecyclerView();
        return mainView;
    }


    protected void initRecyclerView() {
        detailRecyclerView.setHasFixedSize(true);
        detailRecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        detailRecyclerView.setLayoutManager(detailRecyclerViewLayoutManager);
        adapter = new FollowerDetailAdapter(generateInfo());
        detailRecyclerView.setAdapter(adapter);
    }


    protected List<Tuple> generateInfo() {
        List<Tuple> infoList = new ArrayList<>();
        infoList.add(new Tuple("Name", follower.getName()));
        infoList.add(new Tuple("Screen Name", "@" + follower.getScreen_name()));
        infoList.add(new Tuple("Location", follower.getLocation()));
        infoList.add(new Tuple("Follower Count", follower.getFollowers_count() + ""));
        infoList.add(new Tuple("Friend Count", follower.getFriends_count() + ""));
        return infoList;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_FOLLEWER_ARG,follower);
    }
}
