package com.kayya.twitterfollower;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.kayya.twitterfollower.fragments.FollowerDetailFragment;
import com.kayya.twitterfollower.models.User;

public class FollowerDetailActivity extends AppCompatActivity {

    public static final String ARG_FOLLOWER = "follower.detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            User follower = getIntent().getParcelableExtra(ARG_FOLLOWER);
            FollowerDetailFragment fragment = FollowerDetailFragment.newInstance(follower);
            getFragmentManager().beginTransaction()
                    .add(R.id.detailFragmentContainer, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, FollowerListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
