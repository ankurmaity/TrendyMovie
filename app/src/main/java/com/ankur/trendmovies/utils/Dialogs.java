package com.ankur.trendmovies.utils;

import android.app.Dialog;
import android.content.Context;

/**
 * @author ankur
 */
public class Dialogs {

    public static Dialog showProgressDialog(Context activity) {

       ProgressDialog dialog = new ProgressDialog(activity);
        if (!dialog.isShowing()) {
            dialog.show();
        }
        return dialog;
    }

    public static void hideProgressDialog(Dialog dialog) {

        if (Utilities.has(dialog) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
