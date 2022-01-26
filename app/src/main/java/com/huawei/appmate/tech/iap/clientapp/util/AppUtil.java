package com.huawei.appmate.tech.iap.clientapp.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class AppUtil {

    public static void copyToClipboard(Context context, CharSequence text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", text);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(context, "Successfully copied", Toast.LENGTH_SHORT).show();
    }

}
