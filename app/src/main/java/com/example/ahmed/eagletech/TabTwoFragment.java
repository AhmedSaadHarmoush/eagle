package com.example.ahmed.eagletech;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ahmed.eagletech.NewData.NewTaskForm;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabTwoFragment extends Fragment {
Button newTask;

    public TabTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View row =inflater.inflate(R.layout.fragment_tab_two, container, false);
        newTask= (Button) row.findViewById(R.id.newTask);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NewTaskForm.class);
                startActivity(intent);
            }
        });
        return row;
    }

}
