package com.bnk.example.bnkdata.ChartLib;

import com.anychart.chart.common.dataentry.ValueDataEntry;

public class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }