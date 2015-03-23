package me.chenqichao.procustomview.activity.subactivity3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.fragment.fragment03.BlurMaskFilterFragment;
import me.chenqichao.procustomview.fragment.fragment03.Fragment03_01;

public class CustomViewActivity03Demo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custon_view_activity03_demo);
        int demo = getIntent().getIntExtra("demo", 0);
        initFragment(demo);
    }

    private void initFragment(int demo) {
        switch (demo) {
            case 0:
                Fragment03_01 fragment03_01 = new Fragment03_01();
                Replace(fragment03_01);
                break;
            case 1:
                BlurMaskFilterFragment blurMaskFilterFragment = new BlurMaskFilterFragment();
                Replace(blurMaskFilterFragment);
                break;

        }
    }

    private void Replace(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_demo, fragment);
        transaction.commit();
    }
}
