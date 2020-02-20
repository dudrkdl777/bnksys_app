package com.bnk.example.bnkdata;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//리포트작성화면,db에 insert 하기
public class WriteFragment extends Fragment {

    MyDBHelper mDBHelper;
    Context context;
    Button insbtn;
    Button clrbtn;
    EditText ename;
    EditText dept;
    EditText title;
    EditText content;

    SQLiteDatabase db;
    ContentValues values;
    // String[] projection = { "rno", "ename", "dept","title","content" };
    Cursor cur;

    ArrayList<Report> reports = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        context = container.getContext();
        insbtn = view.findViewById(R.id.btnInsert);
        clrbtn = view.findViewById(R.id.btnclear);

        ename = view.findViewById(R.id.ename_in);
        dept = view.findViewById(R.id.dept_in);
        title = view.findViewById(R.id.title_in);
        content = view.findViewById(R.id.content_in);

        clearText();

        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });

        insbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("test","onclick!");
                if (ename.getText().length() > 0 && dept.getText().length() > 0 && title.getText().length() > 0&&content.getText().length() > 0 ) {
                    Log.d("test","onclick! 22222");
                    reports.add(new Report(ename.getText().toString(),dept.getText().toString(),title.getText().toString(),content.getText().toString()));
                    Log.d("test",reports.toString());
                    mDBHelper = new MyDBHelper(getActivity());
                    db = mDBHelper.getWritableDatabase();
                    values = new ContentValues();
                    values.put("ename", ename.getText().toString());
                    values.put("dept", dept.getText().toString());
                    values.put("title", title.getText().toString());
                    values.put("content", content.getText().toString());
                    Log.d("test","insert onclick!!!");
                    db.insert("report", null, values);
                    mDBHelper.close();
                    Toast.makeText(context,"보고서가 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    clearText();
                }else{
                    Log.d("test","onclick! failed!");
                    Toast.makeText(context,"정보를 올바르게 입력했는지 확인하세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //db insert하기
        return view;
    }
    public void clearText(){
        ename.setText("");
        dept.setText("");
        title.setText("");
        content.setText("");
    }
}
