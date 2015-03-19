package me.chenqichao.procustomview.activity.subactivity1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.view.view2.PorterDuffColorFilter1;

public class PorterDuffColorFilterActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnDemo1;
    private PorterDuffColorFilter1 pdcf1;
    private int currentShowViewId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff_color_filter);
        pdcf1 = (PorterDuffColorFilter1) findViewById(R.id.pdcf1);
        btnDemo1 = (Button) findViewById(R.id.btn_demo1);
        btnDemo1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1:
                pdcf1.ShowNextEffect(this);
                break;

        }
    }
}