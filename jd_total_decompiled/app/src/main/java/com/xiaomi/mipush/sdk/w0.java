package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class w0 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f18431g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Context f18432h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ r0 f18433i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w0(String str, Context context, r0 r0Var) {
        this.f18431g = str;
        this.f18432h = context;
        this.f18433i = r0Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        if (TextUtils.isEmpty(this.f18431g)) {
            return;
        }
        String[] split = this.f18431g.split("~");
        int length = split.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str = "";
                break;
            }
            String str2 = split[i2];
            if (!TextUtils.isEmpty(str2) && str2.startsWith("token:")) {
                str = str2.substring(str2.indexOf(":") + 1);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str)) {
            g.j.a.a.a.c.o("ASSEMBLE_PUSH : receive incorrect token");
            return;
        }
        g.j.a.a.a.c.o("ASSEMBLE_PUSH : receive correct token");
        v0.p(this.f18432h, this.f18433i, str);
        v0.f(this.f18432h);
    }
}
