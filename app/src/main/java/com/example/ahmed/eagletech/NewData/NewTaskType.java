package com.example.ahmed.eagletech.NewData;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.eagletech.R;
import com.example.ahmed.eagletech.Tasks.AddTask;

import java.util.concurrent.ExecutionException;

public class NewTaskType extends AppCompatActivity {

    Button addTypeBtn;
    EditText taskTypeName;
    EditText taskTypeDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_type);
        addTypeBtn= (Button) findViewById(R.id.addTypeBtn);
        taskTypeName = (EditText) findViewById(R.id.taskTypeName);
        taskTypeDesc= (EditText) findViewById(R.id.taskTypeDesc);
        addTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeName=taskTypeName.getText().toString();
                String typeDesc=taskTypeDesc.getText().toString();
                if(typeName.equals("")){
                    Toast.makeText(NewTaskType.this, "Type Name can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    AddTask addTask =new AddTask(getApplicationContext());
                    try {
                        String res=addTask.execute("task_type",typeName,typeDesc).get();
                        Log.e("result",res);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
