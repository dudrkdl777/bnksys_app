package com.bnk.example.bnkdata;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.bnk.example.bnkdata.ChartLib.PieChart;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//차트-금융 탭
public class Tab1Fragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        //sectors에 무슨 데이터가 있는 지 확인하기

        Log.d("chart!",DBManager.sectors.get(0).getNm()+"/"+DBManager.sectors.get(0).getId()+"/"+DBManager.sectors.get(1).getPid());

        ///pie 차트
        AnyChartView anyChartView =view.findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        //pie차트에 들어갈 데이터 가져오기
        //TODO: 11월 전체 산업별 거래정보 파이차트에 넣기
        //산업별거래정보:crdStr
        List<CrdStrModel> cdr_list = DBManager.crdStrs.stream().filter(t->t.getDt().contains("2019-11-01")).collect(Collectors.toList());
//        Log.d("chartest!",list.get(0).getSector()+"/"+list.get(0).getVolume()+"/"+list.get(0).getDt()+"/");
        //pie차트 호출
        PieChart test = new PieChart();
        Pie pie = test.makePie(cdr_list);
        anyChartView.setChart(pie);

        /////////////////////////



        AnyChartView anyChartView2 = view.findViewById(R.id.any_chart_view2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);


        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data2 = new ArrayList<>();
        data2.add(new ValueDataEntry("Rouge", 80540));
        data2.add(new ValueDataEntry("Foundation", 94190));
        data2.add(new ValueDataEntry("Mascara", 102610));
        data2.add(new ValueDataEntry("Lip gloss", 110430));
        data2.add(new ValueDataEntry("Lipstick", 128000));
        data2.add(new ValueDataEntry("Nail polish", 143760));
        data2.add(new ValueDataEntry("Eyebrow pencil", 170670));
        data2.add(new ValueDataEntry("Eyeliner", 213210));
        data2.add(new ValueDataEntry("Eyeshadows", 249980));

        Column column = cartesian.column(data2);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Top 10 Cosmetic Products by Revenue");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Product");
        cartesian.yAxis(0).title("Revenue");

        anyChartView2.setChart(cartesian);

        return view;
    }
}
