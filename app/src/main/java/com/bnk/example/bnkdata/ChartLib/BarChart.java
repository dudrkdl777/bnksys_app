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

import java.util.ArrayList;
import java.util.List;

public class BarChart {

    public BarChart() {
    }

    public Cartesian makeBar(List<CrdStrModel> crdlist){
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data2 = new ArrayList<>();

        for (int i = 0; i<crdlist.size() ; i++) {
            int nowSec = crdlist.get(i).getSector();
            String name = DBManager.sectors.get(nowSec).getNm();
            int n = DBManager.sectors.get(crdlist.get(0).getSector()).getPid();
            //대분류만 차트에 넣기
            int pid = DBManager.sectors.get(nowSec).getPid();
            if(pid == 0 && pid != -1 ) {
                data2.add(new ValueDataEntry(name, crdlist.get(i).getVolume()));
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
