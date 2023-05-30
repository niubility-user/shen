package com.jdjr.risk.device.c;

import android.content.Context;
import android.util.Base64;
import com.jdjr.acr.ACRequestManager;

/* loaded from: classes18.dex */
public class z {
    public static String a(Context context, String str, String str2) {
        boolean z = false;
        try {
            Class.forName("com.jdjr.acr.ACRequestManager");
            ACRequestManager.class.getMethod("ACRequest", new Class[0]);
            z = true;
        } catch (Throwable unused) {
        }
        if (z) {
            try {
                byte[] ACRequest = ACRequestManager.newInstance(context, str, str2).ACRequest();
                return (ACRequest == null || ACRequest.length <= 0) ? "" : new String(Base64.encode(ACRequest, 8));
            } catch (Throwable unused2) {
                return "";
            }
        }
        return "";
    }
}
