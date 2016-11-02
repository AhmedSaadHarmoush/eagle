package com.example.ahmed.eagletech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TaskView extends AppCompatActivity {

    ListView lstTask;
    TextView title;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        String[] data = new String[5];
        data[0]="Task #14";
        data[1]="Task #15";
        data[2]="Task #16";
        data[3]="Task #18";
        data[4]="Task #25";
        lstTask = (ListView) findViewById(R.id.lstTask);
        ArrayAdapter adapter =new ArrayAdapter(getBaseContext(),R.layout.task_item,R.id.title,data);
        lstTask.setAdapter(adapter);
    }
}
