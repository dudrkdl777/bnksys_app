package com.bnk.example.bnkdata.ChartLib;

import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;
import com.bnk.example.bnkdata.Model.DepositModel;

import java.util.ArrayList;
import java.util.List;

public class BarChart {

    public BarChart() {
    }

    public Cartesian dps_makeBar(List<DepositModel> list){
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data3 = new ArrayList<>();

//        data3.add(new ValueDataEntry(name, list.get(i).getVolume()));

        for (int i = 0; i < list.size(); i++) {
            String name = DBManager.dpsTyps.get(list.get(i).getDpstyp()).getNm();
            Log.d("chart!! barchart",name+"/"+list.get(i).getDpstyp()+"/"+list.get(i).getAmount());
            data3.add(new ValueDataEntry(name,list.get(i).getAmount()));
        }
        Column column = cartesian.column(data3);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }백만원");

        cartesian.animation(true);
        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }백만원");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("예금상품");
        cartesian.yAxis(0).title("금액");
        return cartesian;
    }


    public Cartesian makeBar(List<CrdStrModel> list){
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data2 = new ArrayList<>();

        for (int i = 0; i<list.size() ; i++) {
            int nowSec = list.get(i).getSector();
            String name = DBManager.sectors.get(nowSec).getNm();
            int n = DBManager.sectors.get(list.get(0).getSector()).getPid();
            //대분류만 차트에 넣기
            int pid = DBManager.sectors.get(nowSec).getPid();
            if(pid == 0 && pid != -1 ) {
                data2.add(new ValueDataEntry(name, list.get(i).getVolume()));
            }
        }

        Column column = cartesian.column(data2);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }백만원");

        cartesian.animation(true);
        cartesian.title("소비 유형별 일 평균 신용카드 거래 금액");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }백만원");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("소비 업종");
        cartesian.yAxis(0).title("금액");

        return cartesian;
    }
}
