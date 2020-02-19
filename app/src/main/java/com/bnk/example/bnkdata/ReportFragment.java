package com.bnk.example.bnkdata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//리포트 조회화면
public class ReportFragment extends Fragment{
    SQLiteDatabase db;
    MyDBHelper mDBHelper;
    Cursor cur;
    String[] projection = { "rno", "ename", "dept","title","content" };
    ReportAdapter reportAdapter;
    ArrayList<Report> reportlist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        //db selectAll
        //listView에 db 한 줄씩.........어케넣지?
        //섹션 클릭시 해당 id를 인텐트로 넘겨주기
        mDBHelper = new MyDBHelper(getActivity());
        db = mDBHelper.getReadableDatabase();
        cur = db.query("report", projection, null,
                null, null, null, null);
        if (cur != null) {
            reportlist = makeList(cur);        //리스트에 정보를 다 넣기
            // 2. adapter 만들기
            db= mDBHelper.getWritableDatabase();
           reportAdapter = new ReportAdapter(getContext(),R.layout.list_item,reportlist);
            // 3. ListView 만들기 (선언, setAdapter)
            ListView lv = (ListView)view.findViewById(R.id.list_view);
            lv.setAdapter(reportAdapter);
            cur.close();
        }
        mDBHelper.close();
        return view;
    }
    private ArrayList<Report> makeList(Cursor cur) {//커서는 유효한 데이터 이전을 가리킨다.
        // String[] projection = { "rno", "ename", "dept","title","content" };
        ArrayList<Report> list = new ArrayList<>();
        int rno_col = cur.getColumnIndex("rno");
        int ename_col = cur.getColumnIndex("ename");
        int dept_col = cur.getColumnIndex("dept");
        int title_col = cur.getColumnIndex("title");
        int content_col = cur.getColumnIndex("content");

        while (cur.moveToNext()) {      //유효한 데이터가 있을때까지 커서가 레코드를 가리킨다.
            int cno = cur.getInt(rno_col);
            String ename = cur.getString(ename_col);
            String dept= cur.getString(dept_col);
            String title= cur.getString(title_col);
            String content= cur.getString(content_col);
            list.add(new Report(cno,ename,dept,title,content));
        }
        return list;
    }
}
