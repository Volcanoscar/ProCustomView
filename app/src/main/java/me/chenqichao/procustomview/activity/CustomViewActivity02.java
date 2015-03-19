package me.chenqichao.procustomview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.chenqichao.procustomview.R;
import me.chenqichao.procustomview.activity.subactivity1.ColorFilterActivity;
import me.chenqichao.procustomview.activity.subactivity1.PorterDuffColorFilterActivity;
import me.chenqichao.procustomview.activity.subactivity1.PorterDuffXfermodeActivity;

public class CustomViewActivity02 extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView lvDemo;
    private ArrayAdapter<String> adapter = null;
    private String[] title = {"ColorFilterDemo", "PorterDuffColorFilterDemo", "PortDuffXfermodeDemo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_activity02);
        lvDemo = (ListView) findViewById(R.id.lvDemo);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);
        lvDemo.setAdapter(adapter);
        lvDemo.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(CustomViewActivity02.this, ColorFilterActivity.class));
                break;
            case 1:
                startActivity(new Intent(CustomViewActivity02.this, PorterDuffColorFilterActivity.class));
                break;
            case 2:
                startActivity(new Intent(CustomViewActivity02.this, PorterDuffXfermodeActivity.class));
                break;
        }
    }
}
