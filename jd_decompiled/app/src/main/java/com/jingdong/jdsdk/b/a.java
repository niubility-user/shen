package com.jingdong.jdsdk.b;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.oklog.OKLog;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* loaded from: classes14.dex */
public class a {
    private static LinkedList<String> a = new LinkedList<>();
    private static StringBuffer b = new StringBuffer();

    /* renamed from: c  reason: collision with root package name */
    private static String f12884c = null;

    public static String a() {
        String str;
        if (a.size() >= 2) {
            LinkedList<String> linkedList = a;
            str = linkedList.get(linkedList.size() - 2);
        } else {
            str = null;
        }
        return str == null ? "" : str;
    }

    public static String b() {
        b.append("page info:");
        int size = a.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 < size - 1) {
                b.append(a.poll() + ">>");
            } else {
                b.append(a.poll() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        b.append(f12884c);
        return b.toString();
    }

    public static String c() {
        String str;
        try {
            str = a.getLast();
        } catch (NoSuchElementException unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static void d(String str, String str2) {
        try {
            if (a.size() >= 5) {
                a.poll();
            }
            a.offer(str2);
        } catch (Throwable th) {
            OKLog.e("PageInfoUtils", th);
        }
        b.setLength(0);
        f12884c = str;
    }
}
