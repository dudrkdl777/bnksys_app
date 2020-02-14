package com.bnk.example.bnkdata;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdatper extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdatper(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Tab1Fragment fragment1 = new Tab1Fragment();
                return fragment1;
            case 1:
                Tab2Fragment fragment2 = new Tab2Fragment();
                return fragment2;

                default:
                    return null;
        }
    }



    @Override
    public int getCount() {
        return tabCount;
    }
}
