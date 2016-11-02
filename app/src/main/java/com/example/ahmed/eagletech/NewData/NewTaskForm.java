package com.example.ahmed.eagletech.NewData;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmed.eagletech.Instances.Customer;
import com.example.ahmed.eagletech.Instances.Employee;
import com.example.ahmed.eagletech.Instances.Priority;
import com.example.ahmed.eagletech.Instances.Severity;
import com.example.ahmed.eagletech.Instances.Status;
import com.example.ahmed.eagletech.Instances.Types;
import com.example.ahmed.eagletech.R;
import com.example.ahmed.eagletech.Tasks.AddTask;
import com.example.ahmed.eagletech.Tasks.GetCustomersTask;
import com.example.ahmed.eagletech.Tasks.GetEmployeesTask;
import com.example.ahmed.eagletech.Tasks.GetPerioritiesTask;
import com.example.ahmed.eagletech.Tasks.GetSeveritiesTask;
import com.example.ahmed.eagletech.Tasks.GetStatusTask;
import com.example.ahmed.eagletech.Tasks.GetTypesTask;

import java.util.ArrayList;
import java.util.List;

public class NewTaskForm extends AppCompatActivity {


    Button addBtn;
    EditText taskNum;
    EditText taskName;
    EditText taskDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);


        final ArrayList<Priority>priorities;
        final ArrayList<Severity>severities;
        final ArrayList<Status>statusies;
        final ArrayList<Types>types;
        final ArrayList<Employee>employees;
        final ArrayList<Customer>customers;



        GetPerioritiesTask getPerioritiesTask=new GetPerioritiesTask(getApplicationContext());
        GetSeveritiesTask getSeveritiesTask=new GetSeveritiesTask(getApplicationContext());
        GetStatusTask getStatusTask=new GetStatusTask(getApplicationContext());
        GetTypesTask getTypesTask=new GetTypesTask(getApplicationContext());
        GetEmployeesTask getEmployeesTask=new GetEmployeesTask(getApplicationContext());
        GetCustomersTask getCustomersTask=new GetCustomersTask(getApplicationContext());


        try {
            priorities=getPerioritiesTask.execute().get();
            severities=getSeveritiesTask.execute().get();
            statusies=getStatusTask.execute().get();
            types=getTypesTask.execute().get();
            employees=getEmployeesTask.execute().get();
            customers=getCustomersTask.execute().get();


            List<String> list=new ArrayList<String>();

            for (int i=0;i<priorities.size();i++){
                list.add(priorities.get(i).getName());
            }
            final Spinner sp1= (Spinner) findViewById(R.id.spinPeriority);
            ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adp1);

            List<String> list2=new ArrayList<String>();
            for (int i=0;i<severities.size();i++){
                list2.add(severities.get(i).getName());
            }
            final Spinner sp2= (Spinner) findViewById(R.id.spinSeverity);
            ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list2);
            adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp2.setAdapter(adp2);

            List<String> list3=new ArrayList<String>();
            for (int i=0;i<statusies.size();i++){
                list3.add(statusies.get(i).getName());
            }
            final Spinner sp3= (Spinner) findViewById(R.id.spinStatus);
            ArrayAdapter<String> adp3=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list3);
            adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp3.setAdapter(adp3);

            List<String> list4=new ArrayList<String>();
            for (int i=0;i<types.size();i++){
                list4.add(types.get(i).getName());
            }
             final Spinner sp4= (Spinner) findViewById(R.id.spinType);
            ArrayAdapter<String> adp4=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list4);
            adp4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp4.setAdapter(adp4);

            List<String> list5=new ArrayList<String>();
            for (int i=0;i<employees.size();i++){
                list5.add(employees.get(i).getUser().getName());
            }
            final Spinner sp5= (Spinner) findViewById(R.id.spinAssigned);
            ArrayAdapter<String> adp5=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list5);
            adp5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp5.setAdapter(adp5);

            List<String> list6=new ArrayList<String>();
            for (int i=0;i<customers.size();i++){
                list6.add(customers.get(i).getUser().getName());
            }
            final Spinner sp6= (Spinner) findViewById(R.id.spinCustomer);
            ArrayAdapter<String> adp6=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list6);
            adp6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp6.setAdapter(adp6);

            taskNum= (EditText) findViewById(R.id.taskNum);
            taskName= (EditText) findViewById(R.id.taskName);
            taskDesc= (EditText) findViewById(R.id.taskDesc);
            addBtn= (Button) findViewById(R.id.addBtn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(taskNum.getText().toString().equals("")){
                        Toast.makeText(NewTaskForm.this, "Task Num can't be empty", Toast.LENGTH_SHORT).show();
                    }else if(taskName.getText().toString().equals("")){
                        Toast.makeText(NewTaskForm.this, "Task Name can't be empty", Toast.LENGTH_SHORT).show();
                    }if(taskDesc.getText().toString().equals("")){
                        Toast.makeText(NewTaskForm.this, "Task Description can't be empty", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            String tasknum=taskNum.getText().toString();
                            String taskname=taskName.getText().toString();
                            String taskdesc=taskDesc.getText().toString();
                            String tasktypeid=types.get(sp4.getSelectedItemPosition()).getId();
                            String priorityid=priorities.get(sp1.getSelectedItemPosition()).getId();
                            String severityid=severities.get(sp2.getSelectedItemPosition()).getId();
                            String statusid=statusies.get(sp3.getSelectedItemPosition()).getId();
                            String assignedto=employees.get(sp5.getSelectedItemPosition()).getEmploee_id();
                            String customerid=customers.get(sp6.getSelectedItemPosition()).getCustomer_id();
                            AddTask addTask =new AddTask(getApplicationContext());
                            addTask.execute("task",tasknum,taskname,taskdesc,tasktypeid,priorityid,severityid,statusid,assignedto,customerid);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
