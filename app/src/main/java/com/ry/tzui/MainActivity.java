package com.ry.tzui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ry.tzui.test.TestActivity;


public class MainActivity extends AppCompatActivity {

    private TextView tve, tvz, tvl, tvj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvz = findViewById(R.id.tv_zhong);
        tvl = findViewById(R.id.tv_liu);
        tvj = findViewById(R.id.tv_jiu);

//        tvz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TestActivity.class);
//                startActivity(intent);
//            }
//        });


        tvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainTodActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

        tvj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainTodActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });

    }

}
