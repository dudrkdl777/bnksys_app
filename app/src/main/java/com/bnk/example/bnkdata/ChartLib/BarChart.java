package com.bnk.example.bnkdata.ChartLib;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

public class BarChart {

    public BarChart() {
    }

    public Cartesian makeBar(){
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

        return cartesian;
    }
}
