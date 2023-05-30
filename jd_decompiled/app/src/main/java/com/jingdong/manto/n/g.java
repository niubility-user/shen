package com.jingdong.manto.n;

import java.util.LinkedHashMap;

/* loaded from: classes16.dex */
public class g {
    int a;
    final LinkedHashMap<String, String> b = new LinkedHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    final LinkedHashMap<String, f> f13876c = new LinkedHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final f a(String str) {
        return this.f13876c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, String str2, f fVar) {
        this.b.put(str, str2);
        this.f13876c.put(str, fVar);
    }
}
