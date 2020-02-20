package com.bnk.example.bnkdata;

import android.app.AlertDialog;
import android.content.Context;
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
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.bnk.example.bnkdata.ChartLib.BarChart;
import com.bnk.example.bnkdata.ChartLib.PieChart;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//차트-금융 탭
public class Tab1Fragment extends Fragment {
    CardView card1;
    LinearLayout fin1_layout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        Spinner spinner = view.findViewById(R.id.spinenr_fin);
//        LinearLayout layout = view.findViewById(R.id.fin_layout);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.fin_catgory, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                View veiw2 = inflater.inflate(R.layout.view_fin1,container,false);
//            }
//        });

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

        //dialog_chartview

        //TODO: 카드 클릭시 대화상자로 차트 그리기
        //합계버튼
        card1 =view.findViewById(R.id.cardView1);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater2 = getLayoutInflater();
                View dialog = inflater2.inflate(R.layout.view_fin1, container, false);
                //chart그리기
                AnyChartView anyChartView2 =view.findViewById(R.id.dialog_chartview);
                APIlib.getInstance().setActiveAnyChartView(anyChartView2);
                BarChart bar = new BarChart();
                Cartesian cartesian = bar.makeBar();
                anyChartView2.setChart(cartesian);


                //anyChartView2.setChart(cartesian);

                //취소버튼 + dialog 나타내기
                builder.setNegativeButton(android.R.string.cancel,null);
                builder.setView(dialog);
                builder.create().show();

            }
        });

        return view;
    }
}
