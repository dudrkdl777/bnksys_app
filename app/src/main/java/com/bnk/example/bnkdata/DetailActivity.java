package com.bnk.example.bnkdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    TextView enameView,deptView,titleView,contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        enameView = findViewById(R.id.ename_view);
        deptView = findViewById(R.id.dept_view);
        titleView = findViewById(R.id.title_view);
        contentView = findViewById(R.id.content_view);

        intent = getIntent();
        enameView.setText(intent.getStringExtra("ename"));
        titleView.setText(intent.getStringExtra("title"));
        contentView.setText(intent.getStringExtra("content"));
        deptView.setText(intent.getStringExtra("dept"));

    }
}
