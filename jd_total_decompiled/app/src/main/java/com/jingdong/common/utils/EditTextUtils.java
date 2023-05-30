package com.jingdong.common.utils;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class EditTextUtils {
    public static void setTextViewText(TextView textView, Intent intent, String str) {
        String stringExtra = intent.getStringExtra("title");
        if (stringExtra != null && stringExtra.length() != 0) {
            str = stringExtra;
        }
        textView.setText(str);
    }

    public static void setTextWithSelection(EditText editText, String str) {
        editText.setText(str);
        editText.setSelection(str.length());
    }
}
