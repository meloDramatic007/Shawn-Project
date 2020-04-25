package com.saddan.shawn.helper;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.saddan.shawn.R;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class DialogHelper extends DialogFragment {
    private final String TAG = getClass().getSimpleName();
    private Dialog dialog;
    private ImageButton dialogClos;
    private TextView arrivedTV, departerTV, timeDiferenceTV, subtotalTV, totalTV, editSlotText, termsConText, promoText;
    private Button payBtn;
    private Context context;
    private String arrived, departure, difference;
    private long diferenceUnit;

    public DialogHelper(Dialog dialog, Context context, String arrived, String departure, String difference, long diferenceUnit) {
        this.dialog = dialog;
        this.context = context;
        this.arrived = arrived;
        this.departure = departure;
        this.difference = difference;
        this.diferenceUnit = diferenceUnit;
    }

    public void initDialog() {
        dialogClos = dialog.findViewById(R.id.clsDialogImageBtn);
        arrivedTV = dialog.findViewById(R.id.arrivedtimeTV);
        departerTV = dialog.findViewById(R.id.departureTimeTV);
        timeDiferenceTV = dialog.findViewById(R.id.timeDifferenceTV);
        subtotalTV = dialog.findViewById(R.id.subTtlCountText);
        totalTV = dialog.findViewById(R.id.ttlCount);
        payBtn = dialog.findViewById(R.id.payBtn);
        editSlotText = dialog.findViewById(R.id.editSlotText);
        termsConText = dialog.findViewById(R.id.termsConText);
        promoText = dialog.findViewById(R.id.promoText);

        arrivedTV.setText(arrived);
        departerTV.setText(departure);
        timeDiferenceTV.setText(difference);
        setBill();
        setListeners();
        dialogClos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().getAttributes().windowAnimations = android.R.anim.fade_in;
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

//        int dialogWidth = 1000; // specify a value here
//        int dialogHeight = 500; // specify a value here
//
//        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        // ... other stuff you want to do in your onStart() method
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width, height);
    }

    private void setListeners() {
        editSlotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        promoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        termsConText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setBill() {
        final double perMintBill = 1.67;
        DecimalFormat df = new DecimalFormat("##.##");
        Log.d(TAG, "setBill: perMint:" + perMintBill);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diferenceUnit);
        Log.d(TAG, "setBill: total:" + perMintBill * minutes);
        subtotalTV.setText(df.format(perMintBill * minutes));
        totalTV.setText(df.format(perMintBill * minutes));
        payBtn.setText("Pay BDT " + df.format(perMintBill * minutes));
    }
}
