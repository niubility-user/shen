package com.vivo.push.d;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* loaded from: classes11.dex */
final class ac implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ List b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ List f18260c;
    final /* synthetic */ String d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ aa f18261e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ac(aa aaVar, int i2, List list, List list2, String str) {
        this.f18261e = aaVar;
        this.a = i2;
        this.b = list;
        this.f18260c = list2;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        aa aaVar = this.f18261e;
        PushMessageCallback pushMessageCallback = ((z) aaVar).b;
        context = ((com.vivo.push.l) aaVar).a;
        pushMessageCallback.onSetAlias(context, this.a, this.b, this.f18260c, this.d);
    }
}
