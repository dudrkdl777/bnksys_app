package com.bnk.example.bnkdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bnk.example.bnkdata.DB.DBManager;
import com.bnk.example.bnkdata.Model.CrdStrModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailChartActivity extends AppCompatActivity implements Serializable{

    private  static final long serialVersionUID = 1L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chart);
        Intent intent = getIntent();
//        ArrayList<CrdStrModel> nlist =intent.getStringArrayExtra("list");
        Serializable intentListData = intent.getSerializableExtra("list");
        ArrayList<CrdStrModel> getArrayList = (ArrayList<CrdStrModel>) intentListData;

        Log.d("test",getArrayList.get(0).getSector()+"/"+getArrayList.get(0).getVolume()+"/"+getArrayList.get(0).getDt()+"/");


    }
}
