package org.upv.ccupeiro.contadroid.common.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackBarUtils {
    private View parentView;
    private String message;

    public SnackBarUtils(View parentView, String message){
        this.parentView = parentView;
        this.message = message;
    }

    public void showShortSnackBar(){
        Snackbar.make(parentView,message,Snackbar.LENGTH_SHORT);
    }

    public void showLongSnackBar(){
        Snackbar.make(parentView,message,Snackbar.LENGTH_LONG);
    }



}
