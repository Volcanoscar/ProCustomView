package me.chenqichao.procustomview.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.view.SimpleCustomView01;
import me.chenqichao.procustomview.view.SimpleCustomView02;

public class SimpleCustomView extends ActionBarActivity {

    private SimpleCustomView01 scv01;
    private SimpleCustomView02 scv02;
    private Button btnDemo01, btnDemo02;
    private boolean isView01Show = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_custiom_view);
        scv01 = (SimpleCustomView01) findViewById(R.id.scv01);
        scv02 = (SimpleCustomView02) findViewById(R.id.scv02);
        btnDemo01 = (Button) findViewById(R.id.btn_demo1);
        btnDemo02 = (Button) findViewById(R.id.btn_demo2);
        btnDemo01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isView01Show) {
                    isView01Show = true;
                    scv02.stop();
                    scv02.setVisibility(View.GONE);
                    scv01.setVisibility(View.VISIBLE);
                }
            }
        });
        btnDemo02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isView01Show) {
                    isView01Show = false;
                    scv01.setVisibility(View.GONE);
                    scv02.setVisibility(View.VISIBLE);
                    scv02.start();
                }
            }
        });
    }
}
