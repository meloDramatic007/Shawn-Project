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
import android.widget.Button;
import android.widget.TextView;

import com.saddan.shawn.R;
import com.saddan.shawn.listener.FragmentChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private long arrived, departure;
    private TextView arrivedtimeTV, departuretimeTV, timeDifferenceTV;
    private long difference;
    private Button moreBtn;
    private Button departureBtn;
    private FragmentChangeListener listener;

    public BookedFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked, container, false);

        listener = (FragmentChangeListener) getActivity();
        arrivedtimeTV = view.findViewById(R.id.arrivedtimeTV);
        departuretimeTV = view.findViewById(R.id.departureTimeTV);
        timeDifferenceTV = view.findViewById(R.id.timeDifferenceTV);
        moreBtn = view.findViewById(R.id.moreBtn);
        departureBtn = view.findViewById(R.id.departureBtn);

        assert getArguments() != null;
        arrived = getArguments().getLong("arrived", 0);
        departure = getArguments().getLong("departure", 0);
        difference = departure - arrived;

        Log.d(TAG, "onCreateView: " + arrived + "    " + departure);
        Log.d(TAG, "onCreateView: difference:" + difference);
       /* String arrivedDate=getDate(arrived);
        String departureDate=getDate(departure);

        timeElavation.setText("Arrived "+arrivedDate+"-"+departureDate+" Departure");
        timeDifference.setText(getDate(difference));*/
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrivedtimeTV.setText("Arrived " + getDate(arrived));
        departuretimeTV.setText("Departure " + getDate(departure));
        timeDifferenceTV.setText(getTimeDiffrence(difference) + " min");

        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("m", true);
                bundle.putLong("a", arrived);
                bundle.putLong("d", departure);
                scheduleFragment.setArguments(bundle);
                listener.FragmentChange(scheduleFragment);
            }
        });


    }

    @SuppressLint("DefaultLocale")
    private String getTimeDiffrence(long difference) {

        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(difference),
                TimeUnit.MILLISECONDS.toMinutes(difference) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)) // The change is in this line
        );
    }

    private String getDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }
}
