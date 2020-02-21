package com.bnk.example.bnkdata;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Credentials;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.bnk.example.bnkdata.ChartLib.BarChart;
import com.bnk.example.bnkdata.ChartLib.PieChart;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//차트-금융 탭
public class Tab1Fragment extends Fragment {
    CardView card1;
    LinearLayout fin1_layout;

    Spinner selSector;
    String[] sectors;
    AnyChartView selSectorChartView;
    Cartesian cartesian;
    Set set;
    Mapping series1Mapping;
    Line series1;
    ProgressBar loading;
    CardView dynamic;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        valInit(view);
        createSpinner();
        drawSelChart(false);
        drawChart(view);


        return view;
    }

    private int selSector(){
        int[] sec = {2,8,9,12,17,18,21,24,28,32,35,36,39,40,41};
        return sec[selSector.getSelectedItemPosition()];
    }

    private void drawSelChart(boolean redraw){
        if(redraw){
            dynamic.removeAllViews();
            selSectorChartView = new AnyChartView(dynamic.getContext());
            dynamic.addView(selSectorChartView);
        }
        APIlib.getInstance().setActiveAnyChartView(selSectorChartView);
        cartesian = AnyChart.line();
        selSectorChartView.setProgressBar(loading);
        List<CrdStrModel> list = DBManager.crdStrs.stream().filter(t->t.getSector()==selSector()).collect(Collectors.toList());

        cartesian.animation(true);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(DBManager.sectors.get(selSector()).getNm() +" 월간 신용거래량");

        cartesian.yAxis(0).title("(일평균) 백만원");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);


        List<DataEntry> seriesData = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            seriesData.add(new CustomDataEntry(list.get(i).getDt().substring(2,7), list.get(i).getVolume()));
        }

        set = Set.instantiate();
        set.data(seriesData);
        series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        series1 = cartesian.line(series1Mapping);
        series1.name(DBManager.sectors.get(selSector()).getNm());
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
        //cartesian.autoRedraw();
        //cartesian.draw(true);
        //selSectorChartView.clear();
        selSectorChartView.setChart(cartesian);

    }

    private void drawChart(View view){

        ///pie 차트
        AnyChartView anyChartView =view.findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        //pie차트에 들어갈 데이터 가져오기
        //TODO: 11월 전체 산업별 거래정보 파이차트에 넣기
        //산업별거래정보:crdStr
        List<CrdStrModel> cdr_list = DBManager.crdStrs.stream().filter(t->t.getDt().contains("2019-11-01")).collect(Collectors.toList());

        //pie차트 호출
        PieChart test = new PieChart();
        Pie pie = test.makePie(cdr_list);
        anyChartView.setChart(pie);

        //월별 차트
        List<CrdStrModel> crd_list2 = DBManager.crdStrs.stream().filter(t->t.getDt().contains("2019-11-01")).collect(Collectors.toList());

        AnyChartView anyChartView3 =view.findViewById(R.id.any_chart_view2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView3);
        BarChart bar = new BarChart();
        Cartesian cart = bar.makeBar(crd_list2);
        anyChartView3.setChart(cart);


        //TODO: 카드 클릭시 대화상자로 차트 그리기
        //합계버튼
        card1 =view.findViewById(R.id.cardView1);
        ArrayList<CrdStrModel> arr_crdlist = new ArrayList<>();

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CrdStrModel> crdlist = DBManager.crdStrs.stream().filter(t->t.getSector()==1).collect(Collectors.toList());
                ArrayList<CrdStrModel> nlist = new ArrayList<>();
                nlist.addAll(crdlist);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",nlist);

                Intent intent = new Intent(getContext(),DetailChartActivity.class);

                startActivity(intent);
            }
        });
    }

    private void valInit(View v){
        selSector = v.findViewById(R.id.spinner_sector);
        selSectorChartView = v.findViewById(R.id.any_chart_viewsel);
        loading = v.findViewById(R.id.progressBar2);
        sectors = getResources().getStringArray(R.array.sector);
        dynamic = v.findViewById(R.id.dynamic);
    }

    private void createSpinner(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_text_small, sectors);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selSector.setAdapter(adapter);
        selSector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                drawSelChart(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value) {
            super(x, value);
        }
    }
}
