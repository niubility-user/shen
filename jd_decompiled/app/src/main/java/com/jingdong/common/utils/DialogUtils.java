package com.jingdong.common.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* loaded from: classes6.dex */
public class DialogUtils {
    public static void showDailog(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(str);
        builder.setTitle("\u6e29\u99a8\u63d0\u793a");
        builder.setPositiveButton("\u786e\u5b9a", onClickListener);
        builder.create().show();
    }
}
