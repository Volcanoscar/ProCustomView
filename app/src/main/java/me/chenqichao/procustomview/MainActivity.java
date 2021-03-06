package me.chenqichao.procustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.chenqichao.procustomview.activity.CustomViewActivity01;
import me.chenqichao.procustomview.activity.CustomViewActivity02;
import me.chenqichao.procustomview.activity.CustomViewActivity03;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private String[] activities = {
            "自定义控件其实很简单1/12",
            "自定义控件其实很简单1/6",
            "自定义控件其实很简单1/4"};
    private ArrayAdapter<String> adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, CustomViewActivity01.class));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, CustomViewActivity02.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, CustomViewActivity03.class));
                break;
        }
    }
}
