package com.jd.voice.jdvoicesdk.util;

/* loaded from: classes18.dex */
public class SpeexTool {
    static {
        try {
            System.loadLibrary("jdvoice");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static native synchronized void speekEncode(String str);
}
