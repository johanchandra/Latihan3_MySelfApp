package com.example.myselfapp;
/**
 * 05/05/2019 10116522 Johan Chandra AKB12
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[]dotstv;
    private int[]layouts;
    private Button btnSkip;
    private Button btnNext;
    private MyPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.view_pager);
        layoutDot = findViewById(R.id.dotLayout);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        setStatusBarTransparent();

        //ketika user press skip, start main activity
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startMainActivity();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length){
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startMainActivity();
                }
            }
        });

        layouts = new int[]{R.layout.slider_1,R.layout.slider_2,R.layout.slider_3};
        pagerAdapter = new MyPagerAdapter(layouts,getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == layouts.length-1 ){
                    //last page
                    btnNext.setText("START");
                    btnSkip.setVisibility(View.GONE);
                } else {
                    btnNext.setText("NEXT");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setDotStatus(0);
    }

    private void setDotStatus (int page){
        layoutDot.removeAllViews();
        dotstv = new TextView[layouts.length];
        for (int i = 0; i < dotstv.length; i++){
            dotstv[i] = new TextView(this );
            dotstv[i].setText(Html.fromHtml("&#8226"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);
        }

        if(dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void startMainActivity(){
        startActivity(new Intent(WelcomeActivity.this, MenuActivity.class));
        finish();
    }
    private void setStatusBarTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
