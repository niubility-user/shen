package com.vivo.push.d;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* loaded from: classes11.dex */
final class ab implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ List b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ List f18258c;
    final /* synthetic */ String d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ aa f18259e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ab(aa aaVar, int i2, List list, List list2, String str) {
        this.f18259e = aaVar;
        this.a = i2;
        this.b = list;
        this.f18258c = list2;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        aa aaVar = this.f18259e;
        PushMessageCallback pushMessageCallback = ((z) aaVar).b;
        context = ((com.vivo.push.l) aaVar).a;
        pushMessageCallback.onSetTags(context, this.a, this.b, this.f18258c, this.d);
    }
}
