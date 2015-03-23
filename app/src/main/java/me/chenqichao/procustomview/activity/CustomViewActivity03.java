package me.chenqichao.procustomview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.activity.subactivity3.CustomViewActivity03Demo;


public class CustomViewActivity03 extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView lvDemo;
    private ArrayAdapter<String> adapter = null;
    private String[] title = {"FontMetricsDemo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_activity03);
        lvDemo = (ListView) findViewById(R.id.lvDemo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);
        lvDemo.setAdapter(adapter);
        lvDemo.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent = new Intent(CustomViewActivity03.this, CustomViewActivity03Demo.class);
                intent.putExtra("demo", 0);
                startActivity(intent);
                break;
        }
    }
}