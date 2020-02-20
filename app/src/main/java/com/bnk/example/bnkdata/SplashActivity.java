package com.bnk.example.bnkdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bnk.example.bnkdata.DB.DBManager;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(SplashActivity.this, ProgressDialogActivity.class);
        startActivity( intent );
        try{
            DBManager.getInstance().setStaticDB();
        }catch(Exception e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
