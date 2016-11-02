package com.example.ahmed.eagletech;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.eagletech.Tasks.UserDataTask;
import com.example.ahmed.eagletech.Instances.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView name;
    TextView job;
    TextView type;
    TextView branch;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowView = inflater.inflate(R.layout.activity_profile, container, false);
        UserDataTask userDataTask = new UserDataTask(getActivity());
        try {
            User user=userDataTask.execute().get();
            name= (TextView) rowView.findViewById(R.id.name);
            job= (TextView) rowView.findViewById(R.id.job);
            type= (TextView) rowView.findViewById(R.id.type);
            branch= (TextView) rowView.findViewById(R.id.branch);

            name.setText(user.getName());
            job.setText("Job Role : "+user.getJob().getName());
            branch.setText("Branch :"+user.getBranch().getName());
            if(user.getType().equals("1")){
                type.setText("Admin");
            }else if(user.getType().equals("2")){
                type.setText("Branch Manager");
            }else{
                type.setText("User");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowView;
    }

}
