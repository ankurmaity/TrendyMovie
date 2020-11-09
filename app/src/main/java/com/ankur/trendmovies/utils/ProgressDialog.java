package com.ankur.trendmovies.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ankur.trendmovies.R;


/**
 * @author ankur
 */
public class ProgressDialog extends Dialog implements View.OnClickListener {

    private Context mActivity;

    public ProgressDialog(Context a) {
        super(a);
        mActivity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_layout);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
        dismiss();
    }
}