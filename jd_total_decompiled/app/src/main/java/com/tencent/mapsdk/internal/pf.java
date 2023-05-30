package com.tencent.mapsdk.internal;

import android.content.Context;
import com.tencent.map.tools.Callback;
import com.tencent.mapsdk.internal.ba;

/* loaded from: classes9.dex */
public class pf {

    /* loaded from: classes9.dex */
    public static class a extends ba.c<Boolean> {
        public final /* synthetic */ Callback a;

        public a(Callback callback) {
            this.a = callback;
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(Boolean bool) {
            this.a.callback(bool);
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends ba.i<Boolean> {
        public final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ String f16981c;

        public b(Context context, String str) {
            this.b = context;
            this.f16981c = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            pf.a(this.b, this.f16981c);
            return Boolean.TRUE;
        }
    }

    public static void a(Context context, String str) {
        if (context == null) {
            return;
        }
        String c2 = lc.b(context).c(str);
        kc a2 = kc.a(context);
        a(a2);
        int b2 = b7.b(b7.E(), a2.d(l4.f16791e));
        hc a3 = jc.a(context, str);
        ic.c(a3, c2);
        ic.a(context, a3, c2, j4.a);
        ic.a(context, a3, c2, j4.d);
        ic.a(context, a3, c2, j4.b);
        ic.a(context, a3, c2, j4.f16724c);
        ic.a(context, a3, c2, j4.f16725e);
        ic.a(context, a3, c2, j4.f16726f);
        ic.a(context, a3, c2, j4.f16727g);
        ic.a(context, a3, c2, j4.f16728h);
        if (b2 > 0) {
            a2.b(l4.f16791e, b7.E());
        }
    }

    public static void a(Context context, String str, Callback<Boolean> callback) {
        ba.a((ba.i) new b(context, str)).a((ba.d.b) Boolean.FALSE, (ba.c<ba.d.b>) (callback != null ? new a(callback) : null));
    }

    private static void a(hc hcVar) {
        if (b7.b(hcVar.d(l4.f16791e), "4.0.9.1") < 0) {
            hcVar.a(new String[]{l4.q, l4.s});
        }
    }
}
