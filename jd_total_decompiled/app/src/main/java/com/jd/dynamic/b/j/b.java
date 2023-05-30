package com.jd.dynamic.b.j;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.g.e;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class b {

    /* loaded from: classes13.dex */
    public static final class a extends c {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f1790j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f1791k;

        a(String str, String str2) {
            this.f1790j = str;
            this.f1791k = str2;
        }

        @Override // com.jd.dynamic.b.g.c
        public void execute() {
            new com.jd.dynamic.b.j.a().h(this.f1791k, this.f1790j);
            DYConstants.DYLog("xpj22 execute sys and biz " + this.f1791k + ' ' + this.f1790j);
        }
    }

    public final void a(@NotNull String str, @NotNull List<String> list) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            e a2 = e.a();
            Intrinsics.checkExpressionValueIsNotNull(a2, "DYThreadManager.getInstance()");
            a2.c().b(new a((String) it.next(), str));
        }
    }
}
