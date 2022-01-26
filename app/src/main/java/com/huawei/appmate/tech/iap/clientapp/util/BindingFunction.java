package com.huawei.appmate.tech.iap.clientapp.util;

import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingFunction {

    @BindingAdapter("bindServerDate")
    public static void bindServerDate(TextView textView, String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            textView.setText(sdf.format(Long.parseLong(dateString)));
        } catch (Exception ex) {
            textView.setText(dateString);
        }
    }

}
