package com.bnk.example.bnkdata.ChartLib;

import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;
import com.bnk.example.bnkdata.Model.DepositModel;
import com.bnk.example.bnkdata.Model.SectorModel;

import java.util.ArrayList;
import java.util.List;

public class PieChart {

    public PieChart() {
        super();
    }
    //예금 pie
    public Pie makeBar_deposit(List<DepositModel> dpslist){
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
//        data.add(new ValueDataEntry(name, crdlist.get(i).getVolume()));

        for (int i = 0; i <dpslist.size() ; i++) {
            Log.d("chart!!",dpslist.get(i).getDpstyp()+"/"+dpslist.get(i).getAmount()+"/"+dpslist.get(i).getDt());
            data.add(new ValueDataEntry(DBManager.dpsTyps.get(dpslist.get(i).getDpstyp()).getNm(),dpslist.get(i).getAmount()));
        }
        pie.data(data);
        return pie;
    }
    //신용
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
                //Log.d("chart",name);
            }
        }
        pie.data(data);
        return pie;
    }
}
