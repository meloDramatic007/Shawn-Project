package com.saddan.shawn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.saddan.shawn.fragment.HomeFragment;
import com.saddan.shawn.listener.FragmentChangeListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private  final String TAG =getClass().getSimpleName() ;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.add(R.id.container,new HomeFragment(),null);
        ft.commit();

        /*Calendar cal = Calendar.getInstance();
        int current = cal.get(Calendar.YEAR);
        ArrayList<String> current_year = getDatesOfYear(current);
        Log.d(TAG, "onCreate: "+current_year.size());

        for (String d:current_year)
        {
            Log.d(TAG, "onCreate: "+d);
        }*/

    }

    private ArrayList<String> getDatesOfYear(int year)
    {
        ArrayList<String> dates = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.set(year,0,1);
        while(cal.get(Calendar.YEAR)==year){
            DateFormat df = new SimpleDateFormat("E, dd MMM");
            String date = df.format(cal.getTime());
            dates.add(date);
            System.out.println(date);
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

    @Override
    public void FragmentChange(Fragment fragment)
    {
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
