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

public class NewUserCustomer extends AppCompatActivity {
    EditText city;
    EditText resname;
    Button btnAdd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_customer);
        city= (EditText) findViewById(R.id.cityReg);
        resname= (EditText) findViewById(R.id.resnameReg);
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
            Log.e("error",name+username+mail);
            btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityText=city.getText().toString();
                String resnameText=resname.getText().toString();
                try{
                    AddTask addTask=new AddTask(getApplicationContext());
                    Toast.makeText(NewUserCustomer.this, "making a user", Toast.LENGTH_SHORT).show();
                    String res=addTask.execute("user",username,password,name,mobile,address,mail,branch,type,cityText,resnameText).get();
                    Log.e("Error",res);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
