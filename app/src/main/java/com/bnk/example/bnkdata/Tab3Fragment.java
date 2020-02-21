package com.bnk.example.bnkdata;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    Spinner selDeposit;
    String[] deposits;
    AnyChartView selDepositChartView;
    Cartesian cartesian;
    Set set;
    Mapping series1Mapping;
    Line series1;
    CardView dynamic;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab33, container, false);

        valInit(view);
        createSpinner();
        drawSelChart();
        drawChart(view);

        //TODO : 기간별 예금액 (6개월, 1년, 2년, 3년) 기간별로 각각 TOP1인 기간,금액 출력 가로 바차트 (4번차트)
        return view;
    }

    private int selDeposit(){
        return selDeposit.getSelectedItemPosition()+1;
    }

    private void valInit(View v){
        selDeposit = v.findViewById(R.id.spinner_deposit);
        deposits = getResources().getStringArray(R.array.deposit);
        dynamic = v.findViewById(R.id.dynamic);
    }

    private void drawSelChart(){
        dynamic.removeAllViews();
        selDepositChartView = new AnyChartView(dynamic.getContext());
        dynamic.addView(selDepositChartView);
        APIlib.getInstance().setActiveAnyChartView(selDepositChartView);
        cartesian = AnyChart.line();
        List<DepositModel> list = DBManager.deposits.stream().filter(t->t.getDpstyp()==selDeposit()).collect(Collectors.toList());

        cartesian.animation(true);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(DBManager.dpsTyps.get(selDeposit()).getNm() +" 월간 예금액");

        cartesian.yAxis(0).title("(월말잔) 십억원");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);


        List<DataEntry> seriesData = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            seriesData.add(new CustomDataEntry(list.get(i).getDt().substring(2,7), list.get(i).getAmount()));
        }

        set = Set.instantiate();
        set.data(seriesData);
        series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        series1 = cartesian.line(series1Mapping);
        series1.name(DBManager.dpsTyps.get(selDeposit()).getNm());
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        selDepositChartView.setChart(cartesian);

    }

    private void createSpinner(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, deposits);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selDeposit.setAdapter(adapter);
        selDeposit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                drawSelChart();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void drawChart(View view){
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
    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value) {
            super(x, value);
        }
    }
}