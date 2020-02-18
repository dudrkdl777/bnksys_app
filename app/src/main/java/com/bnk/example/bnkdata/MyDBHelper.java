package com.bnk.example.bnkdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, "report.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE report(rno INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "dept TEXT, ename TEXT, title TEXT, content TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS report;");
        onCreate(db);
    }
}
