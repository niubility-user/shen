package com.jingdong.manto.pkg.b;

import com.jingdong.manto.pkg.b.h;
import com.jingdong.manto.pkg.b.i;
import com.jingdong.manto.utils.t;
import com.jingdong.sdk.jweb.JWebResourceResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class f {
    private static i a = new i.a(com.jingdong.manto.c.a());
    private static final Collection<String> b = Collections.unmodifiableCollection(Arrays.asList("NAPageFrame.html", "NABridge.js", "NAService.js", "NAWebview.js"));

    /* renamed from: c  reason: collision with root package name */
    private static final ConcurrentHashMap<String, String> f13982c = new ConcurrentHashMap<>();

    private static String a(String str) {
        return "mantoLib/" + str;
    }

    public static JWebResourceResponse b(String str) {
        Object a2;
        h hVar = h.d.a.get(JWebResourceResponse.class);
        String a3 = a(str);
        if (b.contains(str)) {
            ConcurrentHashMap<String, String> concurrentHashMap = f13982c;
            String str2 = concurrentHashMap.get(str);
            if (str2 == null) {
                InputStream d = c.d(str);
                if (d == null) {
                    d = a.a(a3);
                }
                str2 = t.b(d);
                concurrentHashMap.put(str, str2);
            }
            a2 = hVar.a(str, new ByteArrayInputStream(str2.getBytes()));
        } else {
            InputStream d2 = c.d(str);
            if (d2 == null) {
                d2 = a.a(a3);
            }
            a2 = hVar.a(str, d2);
        }
        return (JWebResourceResponse) a2;
    }

    public static String c(String str) {
        String a2 = a(str);
        if (!b.contains(str)) {
            if (a != null) {
                InputStream d = c.d(str);
                if (d == null) {
                    d = a.a(a2);
                }
                return t.b(d);
            }
            return "";
        }
        ConcurrentHashMap<String, String> concurrentHashMap = f13982c;
        String str2 = concurrentHashMap.get(str);
        if (str2 != null || a == null) {
            return str2;
        }
        InputStream d2 = c.d(str);
        if (d2 == null) {
            d2 = a.a(a2);
        }
        String b2 = t.b(d2);
        concurrentHashMap.put(str, b2);
        return b2;
    }
}
