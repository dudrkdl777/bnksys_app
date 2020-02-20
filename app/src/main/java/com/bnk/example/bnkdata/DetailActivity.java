package com.bnk.example.bnkdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    Button btn;
    TextView enameView,deptView,titleView,contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        enameView = findViewById(R.id.ename_view);
        deptView = findViewById(R.id.dept_view);
        titleView = findViewById(R.id.title_view);
        contentView = findViewById(R.id.content_view);

        btn = findViewById(R.id.btnok);

        intent = getIntent();
        enameView.setText(intent.getStringExtra("ename"));
        titleView.setText(intent.getStringExtra("title"));
        contentView.setText(intent.getStringExtra("content"));
        deptView.setText(intent.getStringExtra("dept"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
