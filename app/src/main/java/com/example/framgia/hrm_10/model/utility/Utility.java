package com.example.framgia.hrm_10.model.utility;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by framgia on 17/06/2016.
 */
public class Utility {
    private static final int DAY = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;

    public static void showToast(Context context, CharSequence charSequence) {
        Toast.makeText(context, charSequence, Toast.LENGTH_LONG).show();
    }

    public static boolean checkBefore(CharSequence sequence1, CharSequence sequence2) {
        String dateStr1 = sequence1.toString();
        String dateStr2 = sequence2.toString();
        if (TextUtils.equals(dateStr1, dateStr2)) {
            return true;
        }
        String[] arr1 = dateStr1.split("/");
        String[] arr2 = dateStr2.split("/");
        Date date1 = new Date(Integer.parseInt(arr1[YEAR]), Integer.parseInt(arr1[MONTH]), Integer.parseInt(arr1[DAY]));
        Date date2 = new Date(Integer.parseInt(arr2[YEAR]), Integer.parseInt(arr2[MONTH]), Integer.parseInt(arr2[DAY]));
        return date1.before(date2);
    }
}
