package com.bnk.example.bnkdata;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//리포트 조회화면
public class Menu3Fragment extends Fragment {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabPagerAdatper mtabPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);

//        mViewPager = (ViewPager) view.findViewById(R.id.pager_content);
//
//        mtabPagerAdapter = new TabPagerAdatper(
//
//                getSupportFragmentManager(), mTabLayout.getTabCount());
//
//        mViewPager.setAdapter(mtabPagerAdapter);
//
//
//        mViewPager.addOnPageChangeListener(
//
//                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                mViewPager.setCurrentItem(tab.getPosition());
//
//            }
//            @Override
//
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//
//        });
//
//    }
//
//
//    private View createTabView(String tabName) {
//
//        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
//
//
//        return tabView;
//
//    }
        return view;
    }

}
