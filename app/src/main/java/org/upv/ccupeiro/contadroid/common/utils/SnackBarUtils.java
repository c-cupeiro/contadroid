package org.upv.ccupeiro.contadroid.common.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackBarUtils {

    public static void showShortSnackBar(View parentView, String message){
        Snackbar.make(parentView,message,Snackbar.LENGTH_SHORT);
    }

    public static void showLongSnackBar(View parentView, String message){
        Snackbar.make(parentView,message,Snackbar.LENGTH_LONG);
    }



}
