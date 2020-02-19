package com.bnk.example.bnkdata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends ArrayAdapter<Report> {
    int resourceLayout;
    Context mContext;
    ArrayList<Report> items;

    public ReportAdapter(Context context, int resource, ArrayList<Report> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.items = items;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Report getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(resourceLayout, null);
        }
            Report rpt = items.get(position);

            if(rpt !=null){
                TextView title = v.findViewById(R.id.item_title);
                TextView dept = v.findViewById(R.id.item_dept);
                TextView ename = v.findViewById(R.id.item_ename);
                if(title !=null){
                    title.setText(rpt.getTitle());
                }
                if(dept !=null){
                    dept.setText(rpt.getDept());
                }
                if(ename!=null){
                    ename.setText(rpt.getEname());
                }
            }

        return v;
    }
}
