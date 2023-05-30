package com.jdjr.risk.device.c;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes18.dex */
public class l {
    public static String a(Context context) {
        try {
            File filesDir = context.getApplicationContext().getFilesDir();
            if (filesDir != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(filesDir.getPath());
                String str = File.separator;
                sb.append(str);
                sb.append(".jdd");
                String a = com.jdjr.risk.util.a.b.a(new File(sb.toString() + str + "dfp"));
                return !TextUtils.isEmpty(a) ? a.length() == 116 ? a : "" : "";
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }
}
