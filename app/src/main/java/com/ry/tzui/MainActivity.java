package com.ry.tzui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ry.tzui.link.LinkUtil;
import com.ry.tzui.ui.MainActivity4;
import com.ry.tzui.video.MainActivity3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test:
                test();
                break;
            case R.id.next:
                startActivity(new Intent(this, MainActivity4.class));
                break;
        }
    }

    //仅供测试
    private void test() {

        StringBuilder stringBuilder = new StringBuilder("");
//            stringBuilder.append("-").append("1");
            stringBuilder.append("-").append("2");
            stringBuilder.append("-").append("3");

        String select = stringBuilder.toString();
        String select2 =select;
        Log.e("eee", "se1="+select);
        if(select.startsWith("-")){

            Log.e("eee", "se2=");
            select2 = select.replaceFirst("-", "");
        }
        Log.e("eee", "se="+select2);
    }

}
