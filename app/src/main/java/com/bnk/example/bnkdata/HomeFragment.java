package com.bnk.example.bnkdata;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//홈화면(프로젝트 소개 및 메인페이지)
public class HomeFragment extends Fragment {

    private WebView mWebView,mWebView2; // 웹뷰 선언
    private WebSettings mWebSettings,mWebSettings2; //웹뷰세팅

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 웹뷰 시작
        mWebView = (WebView) view.findViewById(R.id.home_webView);
        mWebView2 = (WebView) view.findViewById(R.id.home_webView2);

        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(true); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(true); // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
        mWebView.setInitialScale(26);
        mWebView.loadUrl("http://kosis.kr/regionState/statePriceCustom.do"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작


        mWebView2.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings2 = mWebView2.getSettings(); //세부 세팅 등록
        mWebSettings2.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSettings2.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings2.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings2.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings2.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings2.setSupportZoom(true); // 화면 줌 허용 여부
        mWebSettings2.setBuiltInZoomControls(true); // 화면 확대 축소 허용 여부
        mWebSettings2.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings2.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSettings2.setDomStorageEnabled(true); // 로컬저장소 허용 여부
        mWebView2.setInitialScale(26);
        mWebView2.loadUrl("https://ecos.bok.or.kr/jsp/vis/GDP/#/gdp"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작

        return view;
    }

}
