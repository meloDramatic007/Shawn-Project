package com.saddan.shawn.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.saddan.shawn.R;

import java.util.Date;


public class ScheduleFragment extends Fragment
{
    private  final String TAG =getClass().getSimpleName();
    private SingleDateAndTimePicker picker;

    public ScheduleFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_schedule, container, false);

         picker=view.findViewById(R.id.picker);


         return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: "+picker.getDate());

       // picker.setEnabled(false);
        picker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(String displayed, Date date) {
                Log.d(TAG, "onDateChanged: "+date);
            }
        });

        /*Date d=picker.getDate();
        Date e=picker.getDate();

        long m=d.getTime()-e.getTime();
*/

    }
}
