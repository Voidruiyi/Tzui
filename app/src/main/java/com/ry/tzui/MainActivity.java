package com.ry.tzui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void test(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        Callable callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        };

    }
}
