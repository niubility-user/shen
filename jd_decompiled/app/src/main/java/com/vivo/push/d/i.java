package com.vivo.push.d;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* loaded from: classes11.dex */
final class i implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ List b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ List f18263c;
    final /* synthetic */ String d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ h f18264e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(h hVar, int i2, List list, List list2, String str) {
        this.f18264e = hVar;
        this.a = i2;
        this.b = list;
        this.f18263c = list2;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        h hVar = this.f18264e;
        PushMessageCallback pushMessageCallback = ((z) hVar).b;
        context = ((com.vivo.push.l) hVar).a;
        pushMessageCallback.onDelTags(context, this.a, this.b, this.f18263c, this.d);
    }
}
