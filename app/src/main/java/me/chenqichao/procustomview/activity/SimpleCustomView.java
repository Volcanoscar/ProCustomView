package me.chenqichao.procustomview.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.view.SimpleCustomView01;
import me.chenqichao.procustomview.view.SimpleCustomView02;
import me.chenqichao.procustomview.view.SimpleCustomView03;

public class SimpleCustomView extends ActionBarActivity implements View.OnClickListener {

    private SimpleCustomView01 scv01;
    private SimpleCustomView02 scv02;
    private SimpleCustomView03 scv03;
    private Button btnDemo01, btnDemo02, btnDemo03;
    private int currentShowViewId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_custiom_view);
        scv01 = (SimpleCustomView01) findViewById(R.id.scv01);
        scv02 = (SimpleCustomView02) findViewById(R.id.scv02);
        scv03 = (SimpleCustomView03) findViewById(R.id.scv03);
        btnDemo01 = (Button) findViewById(R.id.btn_demo1);
        btnDemo02 = (Button) findViewById(R.id.btn_demo2);
        btnDemo03 = (Button) findViewById(R.id.btn_demo3);
        btnDemo01.setOnClickListener(this);
        btnDemo02.setOnClickListener(this);
        btnDemo03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1:
                ToggleShowView(1);
                break;
            case R.id.btn_demo2:
                ToggleShowView(2);
                break;
            case R.id.btn_demo3:
                ToggleShowView(3);
                break;
        }
    }

    private void ToggleShowView(int showViewId) {
        switch (showViewId) {
            case 1:
                if (currentShowViewId == 2) {
                    scv02.stop();
                    scv02.setVisibility(View.GONE);
                } else if (currentShowViewId == 3) {
                    scv03.stop();
                    scv03.setVisibility(View.GONE);
                }
                if (currentShowViewId != 1) {
                    currentShowViewId = 1;
                    scv01.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                if (currentShowViewId == 1) {
                    scv01.setVisibility(View.GONE);
                } else if (currentShowViewId == 3) {
                    scv03.stop();
                    scv03.setVisibility(View.GONE);
                }
                if (currentShowViewId != 2) {
                    currentShowViewId = 2;
                    scv02.setVisibility(View.VISIBLE);
                    scv02.start();
                }
                break;
            case 3:
                if (currentShowViewId == 1) {
                    scv01.setVisibility(View.GONE);
                } else if (currentShowViewId == 2) {
                    scv02.stop();
                    scv02.setVisibility(View.GONE);
                }
                if (currentShowViewId != 3) {
                    currentShowViewId = 3;
                    scv03.setVisibility(View.VISIBLE);
                    scv03.start();
                }
                break;
        }
    }
}
