package com.ry.tzui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainTodActivity extends AppCompatActivity {

    private TextView tvPre, tvNow;
    private long todayEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPre = findViewById(R.id.tv_pre);
        tvNow = findViewById(R.id.tv_now);

        int type = getIntent().getIntExtra("type", -1);

        long now = System.currentTimeMillis() / 1000L;
        long daySecond = 60 * 60 * 24;

        if(type ==1){
            todayEnd = now - (now + 8 * 3600) % daySecond + 3600*12;
        }else if(type ==2){
            todayEnd = now - (now + 8 * 3600) % daySecond + 3600*18;
        }else {
            todayEnd = now - (now + 8 * 3600) % daySecond + 3600*21;
        }

        tvNow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                TestUtils.updateStart();
                return true;
            }
        });
    }


    protected void updateT() {
        String[] strs = TestUtils.showTodTime(todayEnd);

        tvPre.setText(strs[0]);
        tvNow.setText(strs[1]);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        updateT();
        return super.onTouchEvent(event);
    }
}
