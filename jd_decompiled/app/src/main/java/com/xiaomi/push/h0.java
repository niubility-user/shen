package com.xiaomi.push;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class h0 {
    public int a;
    public Map<String, String> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    public String f18681c;

    public String a() {
        return this.f18681c;
    }

    public String toString() {
        return String.format("resCode = %1$d, headers = %2$s, response = %3$s", Integer.valueOf(this.a), this.b.toString(), this.f18681c);
    }
}
