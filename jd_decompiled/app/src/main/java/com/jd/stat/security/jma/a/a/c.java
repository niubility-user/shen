package com.jd.stat.security.jma.a.a;

import java.util.ArrayList;

/* loaded from: classes18.dex */
public class c {
    private String a = "";
    private ArrayList<String> b = new ArrayList<>();

    public void a(String str) {
        if (str != null) {
            this.a = str;
        }
    }

    public void b(String str) {
        if (str != null) {
            this.b.add(str);
        }
    }

    public String a() {
        return this.a;
    }

    public ArrayList<String> b() {
        return this.b;
    }
}
