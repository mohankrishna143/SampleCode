package com.android.dynamictabs;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){

        viewPager = findViewById(R.id.viewpager);
        mTabLayout =  findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout(1);

    }

    /*
     *  updating Fragments to Viewpager
     */
    public void setDynamicFragmentToTabLayout(int tabCount) {
        for (int i = 0; i < tabCount; i++) {
            int position=i+1;
            mTabLayout.addTab(mTabLayout.newTab().setText("Step: " + position));

        }
        DynamicFragmentAdapter mDynamicFragmentAdapter = new DynamicFragmentAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);

     }

    /*
     * Updating Tabs
     */
    public void refreshTabs(int tabCount) {
        /*if (tabCount > 3) {
            tabCount = 3;
        }*/
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.removeAllTabs();
        setDynamicFragmentToTabLayout(tabCount);

    }

    /*
     * navigate to next page
     */
    public  void nextFragment(int position){
        if(viewPager.getChildCount()>position) {
            viewPager.setCurrentItem(position);
        }
    }
    /*
     * to get tab count
     */
    public int getPagerCOunt(){
        return mTabLayout.getTabCount();
    }


}
