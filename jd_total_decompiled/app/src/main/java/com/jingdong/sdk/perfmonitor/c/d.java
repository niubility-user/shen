package com.jingdong.sdk.perfmonitor.c;

import android.content.Context;
import java.util.Set;

/* loaded from: classes12.dex */
public class d extends com.jingdong.sdk.perfmonitor.c.a {

    /* renamed from: g  reason: collision with root package name */
    private a f15409g;

    /* loaded from: classes12.dex */
    public interface a {
        void a(Set<Thread> set);
    }

    public d(Context context, long j2, long j3, a aVar) {
        super(context, j2, j3);
        this.f15409g = aVar;
    }

    @Override // com.jingdong.sdk.perfmonitor.c.a
    void f() {
        a aVar = this.f15409g;
        if (aVar != null) {
            aVar.a(Thread.getAllStackTraces().keySet());
        }
    }
}
