package me.chenqichao.procustomview.activity.subactivity1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.view.view2.PorterDuffXfermodeView;

public class PorterDuffXfermodeActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnDemo1;
    private PorterDuffXfermodeView pdxv;
    private int currentShowViewId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff_xfermode);
        pdxv = (PorterDuffXfermodeView) findViewById(R.id.pdxv);
        btnDemo1 = (Button) findViewById(R.id.btn_demo1);
        btnDemo1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1:
                pdxv.ShowNextEffect();
                break;

        }
    }
}