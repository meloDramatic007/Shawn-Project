package com.saddan.shawn.helper;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saddan.shawn.R;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class DialogHelper {
    private final String TAG = getClass().getSimpleName();
    private Dialog dialog;
    private ImageButton dialogClos;
    private TextView arrivedTV, departerTV, timeDiferenceTV, subtotalTV, totalTV;
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

        arrivedTV.setText(arrived);
        departerTV.setText(departure);
        timeDiferenceTV.setText(difference);
        setBill();
        dialogClos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
