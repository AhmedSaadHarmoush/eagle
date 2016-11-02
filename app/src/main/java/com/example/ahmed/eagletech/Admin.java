package com.example.ahmed.eagletech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.eagletech.NewData.NewTaskType;
import com.example.ahmed.eagletech.NewData.NewUserForm1;

public class Admin extends Fragment {
    TextView users;
    TextView types;
    public Admin() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowView = inflater.inflate(R.layout.activity_admin, container, false);

        users= (TextView) rowView.findViewById(R.id.users);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewUserForm1.class);
                startActivity(intent);
            }
        });
        types= (TextView) rowView.findViewById(R.id.types);
        types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NewTaskType.class);
                startActivity(intent);
            }
        });
        return rowView;
    }
}
