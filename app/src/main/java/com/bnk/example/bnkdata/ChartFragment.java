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

//차트화면
public class ChartFragment extends Fragment {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        mContext = getActivity().getApplicationContext();
        mTabLayout = view.findViewById(R.id.layout_tab);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("금융")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("예금")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("신용")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("경제")));

        mViewPager = (ViewPager) view.findViewById(R.id.pager_content);

        mContentPagerAdapter = new ContentsPagerAdapter(
                getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mContentPagerAdapter);
//        mViewPager.setOffscreenPageLimit(2);    //이전 혹은 다음 페이지 1개를 미리 로딩하기.(속도)
        mViewPager.addOnPageChangeListener(

                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override

            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

            }



            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override

            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }


    private View createTabView(String tabName) {

        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);

        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);

        txt_name.setText(tabName);

        return tabView;

    }
}
