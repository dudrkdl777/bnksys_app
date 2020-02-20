package com.bnk.example.bnkdata;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override

    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return tab1Fragment;
            case 1:
                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return tab2Fragment;
//            case 2://예금
//                Tab3Fragment tab3Fragment = new Tab3Fragment();
//                return tab3Fragment;
            case 3://경제 - 웹뷰
                Tab4Fragment tab4Fragment = new Tab4Fragment();
                return tab4Fragment;

            default:
                return null;
        }

    }



    @Override

    public int getCount() {

        return mPageCount;

    }

}