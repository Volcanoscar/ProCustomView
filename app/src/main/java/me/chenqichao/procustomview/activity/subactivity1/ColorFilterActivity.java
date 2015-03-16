package me.chenqichao.procustomview.activity.subactivity1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.view.view2.ColorFilterView1;
import me.chenqichao.procustomview.view.view2.ColorFilterView2;

public class ColorFilterActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnDemo1, btnDemo2, btnNext;
    private ColorFilterView1 cfv1;
    private ColorFilterView2 cfv2;
    private int currentShowViewId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_filter);
        cfv1 = (ColorFilterView1) findViewById(R.id.cfv1);
        cfv2 = (ColorFilterView2) findViewById(R.id.cfv2);
        btnDemo1 = (Button) findViewById(R.id.btn_demo1);
        btnDemo2 = (Button) findViewById(R.id.btn_demo2);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnDemo1.setOnClickListener(this);
        btnDemo2.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void ToggleShowView(int showViewId) {
        switch (showViewId) {
            case 1:
                if (currentShowViewId == 2) {
                    currentShowViewId = 1;
                    btnNext.setVisibility(View.INVISIBLE);
                    cfv2.setVisibility(View.GONE);
                    cfv1.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                if (currentShowViewId == 1) {
                    currentShowViewId = 2;
                    btnNext.setVisibility(View.VISIBLE);
                    cfv1.setVisibility(View.GONE);
                    cfv2.setVisibility(View.VISIBLE);
                }
                break;
        }
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
            case R.id.btn_next:
                cfv2.ShowNextEffect();
                break;

        }
    }
}
