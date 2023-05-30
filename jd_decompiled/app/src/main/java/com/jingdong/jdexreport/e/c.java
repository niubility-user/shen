package com.jingdong.jdexreport.e;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class c {
    private Map<String, String> a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private String f12606c;
    private String d;

    public c(Context context, String str, String str2) {
        this.f12606c = "";
        this.d = "";
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        if (applicationContext == null) {
            this.b = context;
        }
        this.f12606c = str;
        this.d = str2;
        this.a = new HashMap();
        this.a = a(a());
    }

    private String a() {
        Context context = this.b;
        if (context == null) {
            return "";
        }
        if (com.jingdong.jdexreport.a.a.e.b(context)) {
            return com.jingdong.jdexreport.a.a.b.b(this.b, this.f12606c);
        }
        return com.jingdong.jdexreport.a.a.b.a(this.b, this.d);
    }

    private synchronized void b() {
        if (this.b == null) {
            return;
        }
        String a = a(this.a);
        if (com.jingdong.jdexreport.a.a.e.b(this.b)) {
            com.jingdong.jdexreport.a.a.b.b(this.b, this.f12606c, a);
        } else {
            com.jingdong.jdexreport.a.a.b.a(this.b, this.d, a);
        }
    }

    public void a(String str, String str2) {
        if (str == null || "".equals(str) || str2 == null || "".equals(str2)) {
            return;
        }
        this.a.put(str, str2);
        b();
    }

    public String b(String str) {
        return (str == null || "".equals(str) || !this.a.containsKey(str)) ? "" : this.a.get(str);
    }

    private static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(com.jingdong.jdexreport.a.a.a.f12574c)) {
            int indexOf = str2.indexOf(com.jingdong.jdexreport.a.a.a.b);
            if (indexOf != -1) {
                hashMap.put(str2.substring(0, indexOf).trim(), str2.substring(indexOf + com.jingdong.jdexreport.a.a.a.b.length()).trim());
            }
        }
        return hashMap;
    }

    private static synchronized String a(Map<String, String> map) {
        String substring;
        synchronized (c.class) {
            String str = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str = str + (entry.getKey() + com.jingdong.jdexreport.a.a.a.b + entry.getValue() + com.jingdong.jdexreport.a.a.a.f12574c);
            }
            substring = str.substring(0, str.lastIndexOf(com.jingdong.jdexreport.a.a.a.f12574c));
        }
        return substring;
    }
}
