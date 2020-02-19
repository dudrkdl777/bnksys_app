package com.bnk.example.bnkdata.DB;

import android.util.Log;

import com.bnk.example.bnkdata.HttpUtil;
import com.bnk.example.bnkdata.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    public static ArrayList<CltRgnModel> cltRgns; // 군/구별 신용거래 데이터
    public static ArrayList<CondstModel> condsts; // 군/구 데이터
    public static ArrayList<CrByAgeModel> crByAges; // 군/구 나이별 평균 신용
    public static ArrayList<CrByRgnModel> crByRgns; // 군/구 우량 신용수
    public static ArrayList<CrdStrModel> crdStrs; // 업종별 신용거래량
    public static ArrayList<DepositModel> deposits; // 예금종류별 예금액
    public static ArrayList<DpsTermModel> dpsTerms; // 예금 기간별 예금액
    public static ArrayList<DpsTypModel> dpsTyps; // 예금 종류
    public static ArrayList<SectorModel> sectors; // 업종 데이터

    private static DBManager singletonInstance = new DBManager();

    public DBManager() {
    }

    public static DBManager getInstance() {
        return singletonInstance;
    }

    public void setStaticDB() {
        cltRgns = getTable("CltRgnModel");
        condsts = getTable("CondstModel");
        crByAges = getTable("CrByAgeModel");
        crByRgns = getTable("CrByRgnModel");
        crdStrs = getTable("CrdStrModel");
        deposits = getTable("DepositModel");
        dpsTerms = getTable("DpsTermModel");
        dpsTyps = getTable("DpsTypModel");
        sectors = getTable("SectorModel");
    }

    public <T> ArrayList<T> getTable(String tablename) {
        try {
            String body = "", json = "";
            json = new HttpUtil().execute("http://192.168.0.43:8081/example/getDB?table=" + tablename, body).get();
            ObjectMapper mapper = new ObjectMapper();
            switch (tablename) {
                case "CrdStrModel":
                    ArrayList<CrdStrModel> arr = new ArrayList<>();
                    List<CrdStrModel> lst = Arrays.asList(mapper.readValue(json, CrdStrModel[].class));
                    arr.addAll(lst);
                    return (ArrayList<T>)arr;
                case "DepositModel":
                    ArrayList<DepositModel> arr2 = new ArrayList<>();
                    List<DepositModel> lst2 = Arrays.asList(mapper.readValue(json, DepositModel[].class));
                    arr2.addAll(lst2);
                    return (ArrayList<T>)arr2;
                case "DpsTermModel":
                    ArrayList<DpsTermModel> arr3 = new ArrayList<>();
                    List<DpsTermModel> lst3 = Arrays.asList(mapper.readValue(json, DpsTermModel[].class));
                    arr3.addAll(lst3);
                    return (ArrayList<T>)arr3;
                case "DpsTypModel":
                    ArrayList<DpsTypModel> arr4 = new ArrayList<>();
                    List<DpsTypModel> lst4 = Arrays.asList(mapper.readValue(json, DpsTypModel[].class));
                    arr4.add(new DpsTypModel()); //dpstyp의 id는 1번부터 시작하므로 더미 0번부터 추가
                    arr4.addAll(lst4);
                    return (ArrayList<T>)arr4;
                case "CondstModel":
                    ArrayList<CondstModel> arr5 = new ArrayList<>();
                    List<CondstModel> lst5 = Arrays.asList(mapper.readValue(json, CondstModel[].class));
                    arr5.add(new CondstModel()); //condst의 id는 1번부터 시작하므로 더미 0번부터 추가
                    arr5.addAll(lst5);
                    return (ArrayList<T>)arr5;
                case "SectorModel":
                    ArrayList<SectorModel> arr6 = new ArrayList<>();
                    List<SectorModel> lst6 = Arrays.asList(mapper.readValue(json, SectorModel[].class));
                    arr6.add(new SectorModel());//sector의 id는 1번부터 시작하므로 더미 0번부터 추가
                    arr6.addAll(lst6);
                    return (ArrayList<T>)arr6;
                case "CltRgnModel":
                    ArrayList<CltRgnModel> arr7 = new ArrayList<>();
                    List<CltRgnModel> lst7 = Arrays.asList(mapper.readValue(json, CltRgnModel[].class));
                    arr7.addAll(lst7);
                    return (ArrayList<T>)arr7;
                case "CrByAgeModel":
                    ArrayList<CrByAgeModel> arr8 = new ArrayList<>();
                    List<CrByAgeModel> lst8 = Arrays.asList(mapper.readValue(json, CrByAgeModel[].class));
                    arr8.addAll(lst8);
                    return (ArrayList<T>)arr8;
                case "CrByRgnModel":
                    ArrayList<CrByRgnModel> arr9 = new ArrayList<>();
                    List<CrByRgnModel> lst9 = Arrays.asList(mapper.readValue(json, CrByRgnModel[].class));
                    arr9.addAll(lst9);
                    return (ArrayList<T>)arr9;
                default:
                    Log.e("DB", "Talbe Name not exist");
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
