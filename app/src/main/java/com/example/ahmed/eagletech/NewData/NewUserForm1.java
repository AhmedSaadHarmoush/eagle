package com.example.ahmed.eagletech.NewData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmed.eagletech.Instances.Branch;
import com.example.ahmed.eagletech.Instances.UserType;
import com.example.ahmed.eagletech.R;
import com.example.ahmed.eagletech.Tasks.GetBranchesTask;
import com.example.ahmed.eagletech.Tasks.GetUserTypesTask;

import java.util.ArrayList;
import java.util.List;

public class NewUserForm1 extends AppCompatActivity {
    Button btnAdd;
    EditText name;
    EditText username;
    EditText password;
    EditText repassword;
    EditText mail;
    EditText mobile;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_form1);

        final ArrayList<UserType>userTypes;
        final ArrayList<Branch>branches;

        GetBranchesTask getBranchesTask=new GetBranchesTask(getApplicationContext());
        GetUserTypesTask getUserTypesTask=new GetUserTypesTask(getApplicationContext());

        try{
            userTypes=getUserTypesTask.execute().get();
            branches=getBranchesTask.execute().get();


            List<String> list=new ArrayList<String>();
            for (int i=0;i<userTypes.size();i++){
                list.add(userTypes.get(i).getName());
            }
            final Spinner sp1= (Spinner) findViewById(R.id.spinUserType);
            ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adp1);

            List<String> list2=new ArrayList<String>();
            for (int i=0;i<branches.size();i++){
                list2.add(branches.get(i).getName());
            }
            final Spinner sp2= (Spinner) findViewById(R.id.spinbranch);
            ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,list2);
            adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp2.setAdapter(adp2);

            username= (EditText) findViewById(R.id.usernameReg);
            name= (EditText) findViewById(R.id.nameReg);
            password= (EditText) findViewById(R.id.passwordReg);
            repassword= (EditText) findViewById(R.id.repasswordReg);
            mail= (EditText) findViewById(R.id.mailReg);
            address= (EditText) findViewById(R.id.addressReg);
            mobile= (EditText) findViewById(R.id.mobileReg);
            btnAdd= (Button) findViewById(R.id.addBtn);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String usernameText=username.getText().toString();
                    String nameText=name.getText().toString();
                    String passwordText=password.getText().toString();
                    String repasswordText=repassword.getText().toString();
                    String mailText=mail.getText().toString();
                    String addressText=address.getText().toString();
                    String mobileText=mobile.getText().toString();

                    if(!passwordText.equals(repasswordText)){
                        Toast.makeText(NewUserForm1.this, "Password and re doesn't match", Toast.LENGTH_SHORT).show();
                    }else{
                        String type=userTypes.get(sp1.getSelectedItemPosition()).getId();
                        if(type.equals("2")){
                            String branch=branches.get(sp2.getSelectedItemPosition()).getId();
                            Intent intent=new Intent(NewUserForm1.this,NewUserCustomer.class);
                            intent.putExtra("username",usernameText);
                            intent.putExtra("name",nameText);
                            intent.putExtra("password",passwordText);
                            intent.putExtra("mail",mailText);
                            intent.putExtra("address",addressText);
                            intent.putExtra("mobile",mobileText);
                            intent.putExtra("type",type);
                            intent.putExtra("branch",branch);
                            startActivity(intent);
                        }else{
                            String branch=branches.get(sp2.getSelectedItemPosition()).getId();
                            Intent intent=new Intent(NewUserForm1.this,NewUserEmployee.class);
                            intent.putExtra("username",usernameText);
                            intent.putExtra("name",nameText);
                            intent.putExtra("password",passwordText);
                            intent.putExtra("mail",mailText);
                            intent.putExtra("address",addressText);
                            intent.putExtra("mobile",mobileText);
                            intent.putExtra("type",type);
                            intent.putExtra("branch",branch);
                            startActivity(intent);

                        }
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
