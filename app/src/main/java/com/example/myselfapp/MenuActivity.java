package com.example.myselfapp;
/**
 * 05/05/2019 10116522 Johan Chandra AKB12
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private DailyFragment dailyFragment;
    private GalleryFragment galleryFragment;
    private MusicFragment musicFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        dailyFragment = new DailyFragment();
        galleryFragment = new GalleryFragment();
        musicFragment = new MusicFragment();
        profileFragment = new ProfileFragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home :
                        mMainNav.setItemBackgroundResource(R.color.bg_menu_tab);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_daily :
                        mMainNav.setItemBackgroundResource(R.color.bg_menu_tab);
                        setFragment(dailyFragment);
                        return true;

                    case R.id.nav_gallery :
                        mMainNav.setItemBackgroundResource(R.color.bg_menu_tab);
                        setFragment(galleryFragment);
                        return true;

                    case R.id.nav_music :
                        mMainNav.setItemBackgroundResource(R.color.bg_menu_tab);
                        setFragment(musicFragment);
                        return true;

                    case R.id.nav_profile :
                        mMainNav.setItemBackgroundResource(R.color.bg_menu_tab);
                        setFragment(profileFragment);
                        return true;

                        default:
                            return false;

                }
            }

            private void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.commit();
            }
        });

    }
}
