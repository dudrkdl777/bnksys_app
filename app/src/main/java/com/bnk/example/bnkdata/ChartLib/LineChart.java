package com.bnk.example.bnkdata.ChartLib;

import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.bnk.example.bnkdata.Model.DepositModel;
import com.bnk.example.bnkdata.Tab3Fragment;

import java.util.ArrayList;
import java.util.List;

public class LineChart {
    public Cartesian makeLineChart(List<DepositModel> list){

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)

                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("2019 기간별 예금액 상승률 ");

        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);



        List<DataEntry> seriesData = new ArrayList<>();
        //월 , data1(요구불),data2(저축성)
       // seriesData.add(new CustomDataEntry("1986", 3.6, 2.3));
        double predata1 = 0;
        double predata2 = 0;
        for (int i = 0; i < list.size(); ) {
            String date = (list.get(i).getDt()).substring(0,7);
            double data1 = list.get(i++).getAmount();
            double data2 = list.get(i++).getAmount();
            Log.d("chart!!",data1+"/"+data2);

            if(i>2){
                Log.d("chart!!","here!!!!!!!!!!!!!!!!!!");
                double newdata1 = (data1-predata1)/predata1*100;
                double newdata2 = (data2-predata2)/predata2*100;
                Log.d("chart!!",newdata1+"/"+newdata2);
                seriesData.add(new CustomDataEntry(date,newdata1,newdata2 ));
                predata1 = data1;
                predata2 = data2;
            }

            //Log.d("chart2",list.get(i).getDpstyp()+list.get(i).getAmount()+"/"+list.get(i).getDt());
        }


        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("요구불 예금");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("저축성 예금");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);


        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        return cartesian;
    }
}
