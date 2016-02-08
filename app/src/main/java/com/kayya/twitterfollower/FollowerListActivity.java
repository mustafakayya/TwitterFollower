package com.kayya.twitterfollower;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.kayya.twitterfollower.adapters.FollowerListAdapter;
import com.kayya.twitterfollower.communications.FollowerListListener;

import com.kayya.twitterfollower.fragments.FollowerDetailFragment;
import com.kayya.twitterfollower.models.FollowersResponse;
import com.kayya.twitterfollower.models.User;
import com.kayya.twitterfollower.utils.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;



public class FollowerListActivity extends AppCompatActivity implements FollowerListListener{


    private String CURRENT_FOLLOWERS_ARG = "current.followers";
    private String CURRENT_NEXTCURSOR_ARG = "current.nextcursor";
    public static String USERNAME_ARG = "user.name";

    private boolean mTwoPane;
    String nextCursor = "-1";
    LinearLayoutManager followerRecyclerViewLayoutManager;
    FollowerListAdapter adapter;
    List<User> currentFollowers;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_follower_list);

        currentFollowers = new ArrayList<>();
        if(savedInstanceState!=null){
            nextCursor = savedInstanceState.getString(CURRENT_NEXTCURSOR_ARG,"-1");
            if (savedInstanceState.containsKey(CURRENT_FOLLOWERS_ARG)){
                User[] followerList = (User[]) savedInstanceState.getParcelableArray(CURRENT_FOLLOWERS_ARG);
                currentFollowers = new ArrayList<User>(Arrays.asList(followerList));
            }
            userName = savedInstanceState.getString(USERNAME_ARG);

        }else{
            userName = getIntent().getStringExtra(USERNAME_ARG);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        View recyclerView = findViewById(R.id.follower_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.follower_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView followerRecyclerView) {
        followerRecyclerView.setHasFixedSize(true);
        followerRecyclerViewLayoutManager = new LinearLayoutManager(this);
        followerRecyclerView.setLayoutManager(followerRecyclerViewLayoutManager);
        adapter = new FollowerListAdapter(currentFollowers,this,this);
        followerRecyclerView.setAdapter(adapter);
        followerRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(followerRecyclerViewLayoutManager) {
            @Override
            public void onLoadMore() {
                if (!nextCursor.equalsIgnoreCase("0"))
                    getFollowers();
            }
        });
        if(currentFollowers.size()==0)
            getFollowers();
    }

    private void getFollowers(){
        TFNetworker.getInstance().getFollowers(nextCursor,userName).enqueue(new retrofit2.Callback<FollowersResponse>() {
            @Override
            public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {
                if(response.isSuccess()){
                    adapter.addFollower(response.body().getUsers());
                    nextCursor = response.body().getNext_cursor_str();
                }

            }

            @Override
            public void onFailure(Call<FollowersResponse> call, Throwable t) {
                Log.i("Retrofit","Fail");
            }
        });
    }

    @Override
    public void onFollowerSelected(User follower) {
        if (mTwoPane){
            getFragmentManager().beginTransaction().replace(R.id.follower_detail_container, FollowerDetailFragment.newInstance(follower)).commit();
        }else {
            currentFollowers = adapter.getFollowerList();
            Intent openDetail = new Intent(this, FollowerDetailActivity.class);
            openDetail.putExtra(FollowerDetailActivity.ARG_FOLLOWER, follower);
            startActivity(openDetail);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentFollowers = adapter.getFollowerList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setFollowerList(currentFollowers);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_NEXTCURSOR_ARG,nextCursor);
        currentFollowers = adapter.getFollowerList();
        User[] parcelableArr = new User[currentFollowers.size()];
        for (int i = 0 ;i< currentFollowers.size();i++){
            parcelableArr[i]=currentFollowers.get(i);
        }
        outState.putParcelableArray(CURRENT_FOLLOWERS_ARG, parcelableArr);
        outState.putString(USERNAME_ARG,userName);
    }
}
