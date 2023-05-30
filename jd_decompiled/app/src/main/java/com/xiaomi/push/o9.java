package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class o9 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f18917g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f18918h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ String f18919i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ n9 f18920j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o9(n9 n9Var, String str, String str2, String str3) {
        this.f18920j = n9Var;
        this.f18917g = str;
        this.f18918h = str2;
        this.f18919i = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        context = this.f18920j.a;
        SharedPreferences.Editor edit = context.getSharedPreferences(this.f18917g, 4).edit();
        edit.putString(this.f18918h, this.f18919i);
        edit.commit();
    }
}
