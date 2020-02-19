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
    public static ArrayList<CltRgnModel> cltRgns;
    public static ArrayList<CondstModel> condsts;
    public static ArrayList<CrByAgeModel> crByAges;
    public static ArrayList<CrByRgnModel> crByRgns;
    public static ArrayList<CrdStrModel> crdStrs;
    public static ArrayList<DepositModel> deposits;
    public static ArrayList<DpsTermModel> dpsTerms;
    public static ArrayList<DpsTypModel> dpsTyps;
    public static ArrayList<SectorModel> sectors;

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
        /*
        Class typ;
        switch(tablename){
            case "CrdStrModel":
                typ = CrdStrModel[].class;
                break;
            case "DepositModel":
                typ = DepositModel[].class;
                break;
            case "DpsTermModel":
                typ = DpsTermModel[].class;
                break;
            case "DpsTypModel":
                typ = DpsTypModel[].class;
                break;
            case "CondstModel":
                typ = CondstModel[].class;
                break;
            case "SectorModel":
                typ = SectorModel[].class;
                break;
            case "CltRgnModel":
                typ = CltRgnModel[].class;
                break;
            case "CrByAgeModel":
                typ = CrByAgeModel[].class;
                break;
            case "CrByRgnModel":
                typ = CrByRgnModel[].class;
                break;
            default:
                Log.e("DB","Talbe Name not exist");
                return null;
        }
        try {
            String body="", json="";
            json = new HttpUtil().execute("http://192.168.0.43:8081/example/getDB?table=" + tablename,body).get();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<T> arr = new ArrayList<>();
            List<?> lst = Arrays.asList(mapper.readValue(json,typ));
            arr.addAll((ArrayList<T>)lst);
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        */
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
                    arr4.addAll(lst4);
                    return (ArrayList<T>)arr4;
                case "CondstModel":
                    ArrayList<CondstModel> arr5 = new ArrayList<>();
                    List<CondstModel> lst5 = Arrays.asList(mapper.readValue(json, CondstModel[].class));
                    arr5.addAll(lst5);
                    return (ArrayList<T>)arr5;
                case "SectorModel":
                    ArrayList<SectorModel> arr6 = new ArrayList<>();
                    List<SectorModel> lst6 = Arrays.asList(mapper.readValue(json, SectorModel[].class));
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
