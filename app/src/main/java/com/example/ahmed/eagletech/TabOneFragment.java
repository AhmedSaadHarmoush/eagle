package com.example.ahmed.eagletech;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabOneFragment extends Fragment {


    public TabOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowView = inflater.inflate(R.layout.fragment_tab_one, container, false);
        TextView pending = (TextView) rowView.findViewById(R.id.pending);
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TaskView.class);
                startActivity(intent);
            }
        });
        return rowView;
    }

}
