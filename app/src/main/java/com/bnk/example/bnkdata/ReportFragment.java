package com.bnk.example.bnkdata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//리포트 조회화면
public class ReportFragment extends Fragment{
    SQLiteDatabase db;
    MyDBHelper mDBHelper;
    Cursor cur;
    String[] projection = { "rno", "ename", "dept","title","content" };
    MyAdapter adapter;
    ArrayList<Report> al = new ArrayList<>();
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

            // 2. adapter 만들기
//            adapter = new MyAdapter(getContext(),al,R.layout.list_item);
            // 3. ListView 만들기 (선언, setAdapter)
            ListView lv = (ListView)view.findViewById(R.id.list_view);
            lv.setAdapter(adapter);
            cur.close();
        }
        mDBHelper.close();
        return view;
    }
//    private void showResult(Cursor cur) {//커서는 유효한 데이터 이전을 가리킨다.
//        mTextResult.setText("");
//        int name_col = cur.getColumnIndex("name");
//        int age_col = cur.getColumnIndex("age");
//        while (cur.moveToNext()) {      //유효한 데이터가 있을때까지 커서가 레코드를 가리킨다.
//            String name = cur.getString(name_col);
//            String age = cur.getString(age_col);
//            mTextResult.append(name + ", " + age + "\n");
//        }
//    }
}
