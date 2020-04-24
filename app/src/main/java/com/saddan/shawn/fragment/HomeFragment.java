package com.saddan.shawn.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saddan.shawn.R;
import com.saddan.shawn.helper.DialogHelper;
import com.saddan.shawn.listener.FragmentChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    private Button setbtn;
    private FragmentChangeListener listener;

    private  final String TAG =getClass().getSimpleName() ;
    private long arrived,departure;
    private TextView arrivedtimeTV,departuretimeTV,timeDifferenceTV,countDownTV;
    private long difference;
    private Button moreBtn;
    private LinearLayout bookedLayout;
    //private boolean fromScheduleFragment=false;
    private Button departureBtn;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_home, container, false);


        arrivedtimeTV=view.findViewById(R.id.arrivedtimeTV);
        departuretimeTV=view.findViewById(R.id.departureTimeTV);
        timeDifferenceTV=view.findViewById(R.id.timeDifferenceTV);
        countDownTV=view.findViewById(R.id.countDownTV);
        moreBtn=view.findViewById(R.id.moreBtn);
        bookedLayout=view.findViewById(R.id.bookedLayout);
        departureBtn=view.findViewById(R.id.departureBtn);

        assert getArguments() != null;
        if(getArguments().getBoolean("s"))
        {
            bookedLayout.setVisibility(View.VISIBLE);
            arrived=getArguments().getLong("arrived",0);
            departure=getArguments().getLong("departure",0);
            difference=departure-arrived;

            setTimer(difference);

            Log.d(TAG, "onCreateView: "+arrived+"    "+departure);
            Log.d(TAG, "onCreateView: difference:"+difference);

        }


         setbtn=view.findViewById(R.id.setBtn);
         listener= (FragmentChangeListener) getActivity();



         return view;
    }

    private void setTimer(long difference)
    {
        new CountDownTimer(difference, 1000) {

            public void onTick(long millisUntilFinished) {
                countDownTV.setText(getTimeDiffrence(millisUntilFinished));
            }

            public void onFinish() {
                countDownTV.setText("done!");
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle bundle=new Bundle();
                bundle.putBoolean("m",false);
                ScheduleFragment scheduleFragment=new ScheduleFragment();
                scheduleFragment.setArguments(bundle);
                listener.FragmentChange(scheduleFragment);
            }
        });

        arrivedtimeTV.setText("Arrived "+getDate(arrived));
        departuretimeTV.setText("Departure "+getDate(departure));
        timeDifferenceTV.setText(getTimeDiffrence(difference)+" min");

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ScheduleFragment scheduleFragment=new ScheduleFragment();
                Bundle bundle=new Bundle();
                bundle.putBoolean("m",true);
                bundle.putLong("a",arrived);
                bundle.putLong("d",departure);
                scheduleFragment.setArguments(bundle);
                listener.FragmentChange(scheduleFragment);
            }
        });


        departureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dialog dialog=new Dialog(requireActivity());
                dialog.setContentView(R.layout.voucher_dialog);
                DialogHelper dialogHelper=new DialogHelper(dialog,requireActivity(),getDate(arrived),getDate(departure),getTimeDiffrence(difference),difference);
                dialogHelper.initDialog();
                dialog.show();
            }
        });

    }

    @SuppressLint("DefaultLocale")
    private String getTimeDiffrence(long difference){

        return   String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(difference),
                TimeUnit.MILLISECONDS.toMinutes(difference) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)) // The change is in this line
        );
    }

    private String getDate(long milliSeconds)
    {
        // Create a DateFormatter object for displaying date in specified format.

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
