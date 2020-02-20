package com.bnk.example.bnkdata;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.bnk.example.bnkdata.ChartLib.BarChart;
import com.bnk.example.bnkdata.ChartLib.LineChart;
import com.bnk.example.bnkdata.ChartLib.PieChart;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;
import com.bnk.example.bnkdata.Model.DepositModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tab3Fragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab33, container, false);
        //예금
        //TODO: 각 월별로 요구불예금, 저축성 예금 라인차트로 그리기 (1번차트) 상승률을 써보까? - 실패 - pie차트
        List<DepositModel> dpsTotalList = DBManager.deposits.stream().filter(t->(t.getDpstyp()==1 ||t.getDpstyp()==9 )&&(t.getDt().contains("2019-11"))).collect(Collectors.toList());

        //1번차트 그리기
        AnyChartView anyChartView = view.findViewById(R.id.chart1);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);

        PieChart ch = new PieChart();
        Pie pie = ch.makeBar_deposit(dpsTotalList);
        anyChartView.setChart(pie);

        //TODO: 요구불 11월 금액 BAR차트, 저축성 11월 BAR 차트 (2,3번차트)

        List<DepositModel> depositsmallList1 = DBManager.deposits.stream().filter(t->(t.getDt().contains("2019-11")&&(t.getDpstyp() >1 && t.getDpstyp()<9 ))).collect(Collectors.toList());

        //2번차트 그리기
        AnyChartView anyChartView2 = view.findViewById(R.id.chart2_1);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);

        BarChart bar = new BarChart();
        Cartesian cartesian1 = bar.dps_makeBar(depositsmallList1);
        anyChartView2.setChart(cartesian1);

        List<DepositModel> depositsmallList2 = DBManager.deposits.stream().filter(t->(t.getDt().contains("2019-11")&&(t.getDpstyp()>9 ))).collect(Collectors.toList());

//        //3번차트 그리기
        AnyChartView anyChartView3 = view.findViewById(R.id.chart2_2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView3);
        Cartesian cartesian2 = bar.dps_makeBar(depositsmallList2);
        anyChartView3.setChart(cartesian2);

        //TODO : 기간별 예금액 (6개월, 1년, 2년, 3년) 기간별로 각각 TOP1인 기간,금액 출력 가로 바차트 (4번차트)
        return view;
    }


}