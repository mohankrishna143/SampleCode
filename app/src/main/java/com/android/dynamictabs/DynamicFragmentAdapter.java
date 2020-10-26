package com.android.dynamictabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    int previousPosition,currentPosition;


    DynamicFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        //currentPosition=;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putInt("position", position);
        Fragment frag = DynamicFragment.newInstance();
        frag.setArguments(b);

        return frag;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
