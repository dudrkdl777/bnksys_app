package com.bnk.example.bnkdata.ChartLib;

import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;
import com.bnk.example.bnkdata.Model.SectorModel;

import java.util.ArrayList;
import java.util.List;

public class PieChart {

    public PieChart() {
        super();
    }

    public Pie makePie(List<CrdStrModel> crdlist){

        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();

        for (int i = 0; i<crdlist.size() ; i++) {
            int nowSec = crdlist.get(i).getSector();
            String name = DBManager.sectors.get(nowSec).getNm();
            int n = DBManager.sectors.get(crdlist.get(0).getSector()).getPid();
            //대분류만 차트에 넣기
            int pid = DBManager.sectors.get(nowSec).getPid();
            if(pid == 0 && pid != -1 ) {
                data.add(new ValueDataEntry(name, crdlist.get(i).getVolume()));
                Log.d("chart",name);
            }
        }
        pie.data(data);
        return pie;
    }
}
