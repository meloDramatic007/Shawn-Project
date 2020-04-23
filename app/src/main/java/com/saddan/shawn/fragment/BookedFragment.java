package com.saddan.shawn.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saddan.shawn.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedFragment extends Fragment
{
    private  final String TAG =getClass().getSimpleName() ;
    private long arrived,departure;
    private TextView arrivedtimeTV,departuretimeTV,timeDifferenceTV;
    private long difference;
    private SimpleDateFormat formatter;
    public BookedFragment()
    {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_booked, container, false);

         arrivedtimeTV=view.findViewById(R.id.arrivedtimeTV);
         departuretimeTV=view.findViewById(R.id.departureTimeTV);
         timeDifferenceTV=view.findViewById(R.id.timeDifferenceTV);


        assert getArguments() != null;
        arrived=getArguments().getLong("arrived",0);
        departure=getArguments().getLong("departure",0);
        difference=departure-arrived;

        Log.d(TAG, "onCreateView: "+arrived+"    "+departure);
        Log.d(TAG, "onCreateView: difference:"+difference);
       /* String arrivedDate=getDate(arrived);
        String departureDate=getDate(departure);

        timeElavation.setText("Arrived "+arrivedDate+"-"+departureDate+" Departure");
        timeDifference.setText(getDate(difference));
*/
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*assert getArguments() != null;
        arrived=getArguments().getLong("arrived",0);
        departure=getArguments().getLong("departure",0);
        long difference=departure-arrived;

        Log.d(TAG, "onCreateView: "+arrived+"    "+departure);*/

        arrivedtimeTV.setText("Arrived "+getDate(arrived,1));
        departuretimeTV.setText("Departure "+getDate(departure,1));
        //



          timeDifferenceTV.setText(getTimeDiffrence(difference)+" min");

    }

    private String getTimeDiffrence(long difference){

        return   String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(difference),
                TimeUnit.MILLISECONDS.toMinutes(difference) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)) // The change is in this line
                );
    }

    private String getDate(long milliSeconds,int type)
    {
        // Create a DateFormatter object for displaying date in specified format.
        if(type==1)
        {
             formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US);
        }
        else {
            formatter = new SimpleDateFormat(" HH:MM", Locale.US);
        }

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
