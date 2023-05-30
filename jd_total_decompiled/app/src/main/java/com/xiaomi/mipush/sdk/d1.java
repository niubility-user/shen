package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.h7;
import com.xiaomi.push.r3;
import com.xiaomi.push.service.b0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d1 extends b0.a {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Context f18371h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(int i2, String str, Context context) {
        super(i2, str);
        this.f18371h = context;
    }

    @Override // com.xiaomi.push.service.b0.a
    protected void b() {
        r3.b(this.f18371h).e(com.xiaomi.push.service.b0.d(this.f18371h).a(h7.AwakeInfoUploadWaySwitch.a(), 0));
    }
}
