package com.example.ahmed.eagletech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.eagletech.Tasks.LoginTask;

public class Login extends AppCompatActivity {

    EditText txtUser;
    EditText txtPass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        txtPass= (EditText) findViewById(R.id.pass);
        txtUser= (EditText) findViewById(R.id.user);
        btn= (Button) findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    Intent i=new Intent(Login.this, MainActivity.class);
//                    startActivity(i);
//
//
                Log.e("LOGIN CLASS ", "I'm here ");
                String username =txtUser.getText().toString();
                String password =txtPass.getText().toString();
                LoginTask loginTask=new LoginTask(getApplicationContext());
                try {
                    String result =loginTask.execute(username,password).get();
                    if(result.equals("undefinedUser")){
                    Toast.makeText(getApplicationContext(), "undefined username or password", Toast.LENGTH_SHORT).show();
                }else if(result.equals("successful")){
                        Intent intent =new Intent(getApplication(),Navigation.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
