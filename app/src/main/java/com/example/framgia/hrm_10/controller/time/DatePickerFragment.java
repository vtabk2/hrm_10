package com.example.framgia.hrm_10.controller.time;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by framgia on 02/06/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private static final int DAY = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    private TextView mTextViewBirthday;

    public DatePickerFragment setTextViewBirthday(TextView textViewBirthday) {
        this.mTextViewBirthday = textViewBirthday;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String tmp = mTextViewBirthday.getText().toString();
        String[] arr = tmp.split("/");
        int year = Integer.parseInt(arr[YEAR]);
        int month = Integer.parseInt(arr[MONTH]);
        int day = Integer.parseInt(arr[DAY]);
        return new DatePickerDialog(getActivity(), this, year, month - 1, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        month = month + 1;
        mTextViewBirthday.setText("" + String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year);
    }
}
