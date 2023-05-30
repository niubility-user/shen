package com.jdcn.risk.cpp;

import android.content.Context;
import com.jdjr.risk.util.a.c;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class LoadDoor {
    private static boolean a;

    /* loaded from: classes18.dex */
    private static class b {
        static LoadDoor a = new LoadDoor();
    }

    static {
        try {
            System.loadLibrary("biometric");
            a = true;
        } catch (Throwable th) {
            c.a("LoadDoor", th.getMessage());
            a = false;
        }
    }

    private LoadDoor() {
    }

    private static native String checkAntiFile();

    private static native int checkFingers(String str, String str2);

    private static native String checkSum(Object obj);

    private static native String dec(String str);

    public static LoadDoor e() {
        return b.a;
    }

    private static native String enc(String str);

    private static native String getDecStr(double[] dArr, int i2);

    private static native String getEid(Object obj);

    private static native String getFingerprint(Object obj);

    private static native String getModel(Object obj);

    private static native String getToken(Object obj);

    public JSONObject a() {
        if (a) {
            try {
                return new JSONObject(checkAntiFile());
            } catch (JSONException e2) {
                c.a("LoadDoor", e2.getMessage());
                return null;
            }
        }
        return null;
    }

    public int b(String str, String str2) {
        return checkFingers(str, str2);
    }

    public String c(Context context) {
        return !a ? "" : checkSum(context);
    }

    public String d(Context context) {
        return !a ? "" : getFingerprint(context);
    }

    public String f(Context context) {
        return !a ? "" : getEid(context);
    }

    public String g(Context context) {
        return !a ? "" : getModel(context);
    }

    public String h(Context context) {
        return !a ? "" : getToken(context);
    }
}
