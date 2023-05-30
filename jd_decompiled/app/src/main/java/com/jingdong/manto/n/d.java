package com.jingdong.manto.n;

import android.content.Intent;
import java.util.LinkedHashMap;

/* loaded from: classes16.dex */
public class d {
    String a;
    Class b;

    /* renamed from: c  reason: collision with root package name */
    Class f13866c;
    final LinkedHashMap<String, String> d = new LinkedHashMap<>();

    /* renamed from: e  reason: collision with root package name */
    final LinkedHashMap<String, f> f13867e = new LinkedHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Class cls, Class cls2) {
        if (cls == null || cls2 == null) {
            return;
        }
        this.a = cls.getName();
        this.b = cls;
        this.f13866c = cls2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final f a(String str) {
        return this.f13867e.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        if (this.f13866c != null) {
            Intent intent = new Intent();
            intent.setClass(com.jingdong.manto.c.a(), this.f13866c);
            com.jingdong.manto.c.a().sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, String str2, f fVar) {
        this.d.put(str, str2);
        this.f13867e.put(str, fVar);
    }
}
