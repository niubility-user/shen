package com.jd.phc;

import com.jd.phc.e;

/* loaded from: classes.dex */
public class h {
    public static final String a;
    public static final String b;

    static {
        if (a.a) {
            a = "http://phc.jd.com/v1";
        } else {
            a = e.d != e.c.Official ? "http://p-phc.jd.com/v1" : "http://phc.jd.com/v1";
        }
        b = a + "/request_dsecret";
    }
}
