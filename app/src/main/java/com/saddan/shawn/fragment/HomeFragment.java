package com.saddan.shawn.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saddan.shawn.R;
import com.saddan.shawn.listener.FragmentChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    private Button setbtn;
    private FragmentChangeListener listener;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_home, container, false);

         setbtn=view.findViewById(R.id.setBtn);
         listener= (FragmentChangeListener) getActivity();

         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.FragmentChange(new ScheduleFragment());
            }
        });
    }
}
