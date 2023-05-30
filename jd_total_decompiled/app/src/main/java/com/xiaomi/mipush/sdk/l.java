package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.c4;
import com.xiaomi.push.service.b0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class l extends b0.a {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Context f18391h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(int i2, String str, Context context) {
        super(i2, str);
        this.f18391h = context;
    }

    @Override // com.xiaomi.push.service.b0.a
    protected void b() {
        c4.k(this.f18391h);
    }
}
