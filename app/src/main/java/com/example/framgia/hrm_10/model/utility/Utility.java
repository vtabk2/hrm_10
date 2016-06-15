package com.example.framgia.hrm_10.model.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by framgia on 17/06/2016.
 */
public class Utility {
    public static void showToast(Context context, CharSequence charSequence) {
        Toast.makeText(context, charSequence, Toast.LENGTH_LONG).show();
    }
}
