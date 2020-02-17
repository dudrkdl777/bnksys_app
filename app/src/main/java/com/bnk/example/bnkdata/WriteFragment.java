package com.bnk.example.bnkdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

//리포트작성화면,db에 insert 하기
public class WriteFragment extends Fragment {

    private MyDBHelper mDBHelper;
    private EditText mEditTitle;
    private EditText mEditEname;
    private EditText mEditDept;
    private EditText mEditContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        //db insert하기

        return view;
    }
}
class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, "mytest.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE report(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "dept TEXT, ename TEXT, title TEXT, content TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS report;");
        onCreate(db);
    }
}
