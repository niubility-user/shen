package com.vivo.push.d;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.sdk.PushMessageCallback;

/* loaded from: classes11.dex */
final class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ com.vivo.push.b.i b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ d f18262c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar, String str, com.vivo.push.b.i iVar) {
        this.f18262c = dVar;
        this.a = str;
        this.b = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        Context context2;
        if (!TextUtils.isEmpty(this.a)) {
            d dVar = this.f18262c;
            PushMessageCallback pushMessageCallback = ((z) dVar).b;
            context2 = ((com.vivo.push.l) dVar).a;
            pushMessageCallback.onReceiveRegId(context2, this.a);
        }
        d dVar2 = this.f18262c;
        PushMessageCallback pushMessageCallback2 = ((z) dVar2).b;
        context = ((com.vivo.push.l) dVar2).a;
        pushMessageCallback2.onBind(context, this.b.h(), this.b.d());
    }
}
