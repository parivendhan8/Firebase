package com.example.test.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.firebase.Fragments.DrawableFragment;
import com.example.test.firebase.Fragments.ExpandableFragment;
import com.example.test.firebase.Fragments.HomeFragments;

import static android.view.Gravity.RIGHT;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {


    ImageView toolbar_ivNavigation;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    TextView toolbar_tvTitle;
    Animation animation;
    Animation animation_reverse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user);


        toolbar_ivNavigation = (ImageView) findViewById(R.id.toolbar_ivNavigation);
        toolbar_tvTitle = (TextView) findViewById(R.id.toolbar_tvTitle);
        toolbar_ivNavigation.setOnClickListener(this);




        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(UserProfile.this,
                drawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        replaceNavigationFragment();
        setToolbarTitle("");
        replaceFragment(0);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right);





    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(RIGHT)) {
            drawerLayout.closeDrawer(RIGHT);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.toolbar_ivNavigation:
                openCloseDrawer();
                anim();
                break;
        }

    }

    private void openCloseDrawer() {
        if (drawerLayout.isDrawerOpen(RIGHT)) drawerLayout.closeDrawer(RIGHT);
        else drawerLayout.openDrawer(RIGHT);
    }

    public void replaceNavigationFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainerNavigationMenu, new DrawableFragment(), "Navigation").commit();

    }
    public void replaceFragment(int position)
    {
        Fragment fragment = null;
        String fragment_name = null;

        switch (position)
        {
            case 0:
                fragment = new HomeFragments();
                fragment_name = "Home";

                break;


            default:
                fragment = new HomeFragments();
                fragment_name = "Home";


        }

        if (position == 1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainerNavigationMenu, new ExpandableFragment(), "Expandable").commit();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.flContainerFragment, fragment, fragment_name).commit();
        setToolbarTitle(fragment_name);
        if (drawerLayout.isDrawerOpen(RIGHT)) drawerLayout.closeDrawer(RIGHT);




    }

    public void setToolbarTitle(String title) {
        toolbar_tvTitle.setText(title);
    }

    public void anim()
    {
        toolbar_ivNavigation.setImageResource(R.drawable.arrow);
        toolbar_ivNavigation.startAnimation(animation);
    }

}
