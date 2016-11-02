package com.example.ahmed.eagletech.NewData;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmed.eagletech.Instances.Employee;
import com.example.ahmed.eagletech.Instances.JobRole;
import com.example.ahmed.eagletech.R;
import com.example.ahmed.eagletech.Tasks.AddTask;
import com.example.ahmed.eagletech.Tasks.GetAllEmployeesTask;
import com.example.ahmed.eagletech.Tasks.GetJobRolesTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewUserEmployee extends AppCompatActivity {
    //EditText birth_date;
    Button btnAdd2;

    static String formatedDate;
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_employee);




        final ArrayList<JobRole> jobRoles;
        final ArrayList<Employee>employees;

        GetJobRolesTask getJobRolesTask=new GetJobRolesTask(getApplicationContext());
        GetAllEmployeesTask getAllEmployeesTask =new GetAllEmployeesTask(getApplicationContext());

        try{
            jobRoles=getJobRolesTask.execute().get();
            employees=getAllEmployeesTask.execute().get();

            List<String> list=new ArrayList<String>();
            for (int i=0;i<jobRoles.size();i++){
                list.add(jobRoles.get(i).getJob_role_name());
            }
            final Spinner sp1= (Spinner) findViewById(R.id.spinJobRole);
            ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adp1);

            List<String> list2=new ArrayList<String>();
            for (int i=0;i<employees.size();i++){
                list2.add(employees.get(i).getUser().getName());
            }
            final Spinner sp2= (Spinner) findViewById(R.id.spinManager);
            ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list2);
            adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp2.setAdapter(adp2);


            //birth_date= (EditText) findViewById(R.id.birth_date);
            btnAdd2= (Button) findViewById(R.id.addBtn23);
            final String name;
            final String username;
            final String password;
            final String mail;
            final String mobile;
            final String address;
            final String type;
            final String branch;
            Bundle extras = getIntent().getExtras();

            name = extras.getString("name");
            username= extras.getString("username");
            password= extras.getString("password");
            mail= extras.getString("mail");
            mobile= extras.getString("mobile");
            address= extras.getString("address");
            type= extras.getString("type");
            branch= extras.getString("branch");


            btnAdd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String birth=birth_date.getText().toString();
                    String jobrole=jobRoles.get(sp1.getSelectedItemPosition()).getJob_role_id();
                    String manager=employees.get(sp2.getSelectedItemPosition()).getEmploee_id();
                    AddTask addTask=new AddTask(getApplicationContext());
                    Toast.makeText(NewUserEmployee.this, "making a user", Toast.LENGTH_SHORT).show();
                    String res= null;
                    try {
                        res = addTask.execute("user",username,password,name,mobile,address,mail,branch,type,formatedDate,jobrole,manager).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.e("Error",res);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            year=year-1900;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            formatedDate= sdf.format(new Date( year, month,day));
        }
    }
}
