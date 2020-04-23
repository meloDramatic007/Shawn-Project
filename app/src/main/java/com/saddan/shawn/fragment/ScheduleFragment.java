package com.saddan.shawn.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.saddan.shawn.R;
import com.saddan.shawn.listener.FragmentChangeListener;

import java.util.Date;


public class ScheduleFragment extends Fragment
{
    private  final String TAG =getClass().getSimpleName();
    private Button setBtn,cancelBtn;
    private SingleDateAndTimePicker arrivedPicker,departurePicker;
    private Date arrivedDate,departerDate;
    private LinearLayout arriveDisableLayout,departureDisableLayout;
    private boolean setArrivedDate=false;
    private FragmentChangeListener listener;
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

         arrivedPicker=view.findViewById(R.id.arrivedPicker);
         departurePicker=view.findViewById(R.id.departurePicker);
         setBtn=view.findViewById(R.id.saveBtn);
         cancelBtn=view.findViewById(R.id.cancelBtn);
         arriveDisableLayout =view.findViewById(R.id.arrivedLayout);
         departureDisableLayout=view.findViewById(R.id.departureDisable);

         listener= (FragmentChangeListener) getActivity();

         return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: ");
        departurePicker.setEnabled(false);
        departureDisableLayout.setBackgroundColor(getResources().getColor(R.color.disableColor));

        arrivedDate=arrivedPicker.getDate();
        Log.d(TAG, "arrived date before scrolling "+arrivedDate);
        departerDate=departurePicker.getDate();
        Log.d(TAG, "departure date before scrolling "+departerDate);

        arrivedPicker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(String displayed, Date date)
            {
                arrivedDate=date;
                Log.d(TAG, "onDateChanged: "+date);
                Log.d(TAG, "onDateChanged: arrivedDate"+arrivedDate);
            }
        });

        departurePicker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(String displayed, Date date)
            {
                departerDate=date;
                Log.d(TAG, "onDateChanged: "+date);
                Log.d(TAG, "onDateChanged: departureDate"+departerDate);
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!setArrivedDate)
                {
                    arrivedPicker.setEnabled(false);
                    arriveDisableLayout.setBackgroundColor(getResources().getColor(R.color.disableColor));

                    departurePicker.setEnabled(true);
                    departureDisableLayout.setBackgroundColor(getResources().getColor(R.color.enableColor));

                    setArrivedDate=true;
                }else {

                      Bundle bundle=new Bundle();
                      Log.d(TAG, "onClick: "+arrivedDate.getTime());
                      Log.d(TAG, "onClick: "+departerDate.getTime());
                      bundle.putLong("arrived",arrivedDate.getTime());
                      bundle.putLong("departure",departerDate.getTime());
                      BookedFragment bookedFragment=new BookedFragment();
                      bookedFragment.setArguments(bundle);
                      listener.FragmentChange(bookedFragment);

                }


            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                 if(setArrivedDate)
                 {
                     arrivedPicker.setEnabled(true);
                     arriveDisableLayout.setBackgroundColor(getResources().getColor(R.color.enableColor));
                     setArrivedDate=false;
                 }else {

                 }
            }
        });

    }

    @Override
    public void onStart() 
    {
        setArrivedDate=false;

        super.onStart();
        Log.d(TAG, "onStart: ");
    }
}
