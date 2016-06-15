package com.example.framgia.hrm_10.model.listenner;

import android.view.View;

/**
 * Created by framgia on 13/06/2016.
 */
public interface OnClickItemListener {
    void onClickItem(View view, int position);

    void onLongClickItem(View view, int position);
}
