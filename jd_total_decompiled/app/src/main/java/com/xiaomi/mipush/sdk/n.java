package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.o0;
import com.xiaomi.push.e8;
import com.xiaomi.push.k8;
import com.xiaomi.push.l4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class n {
    private static Map<String, o0.a> a = new HashMap();
    private static a b;

    /* loaded from: classes11.dex */
    public static class a {
        public void a(String str, MiPushCommandMessage miPushCommandMessage) {
            throw null;
        }

        public void b(String str, MiPushCommandMessage miPushCommandMessage) {
            throw null;
        }
    }

    static {
        new HashMap();
    }

    public static void a(Context context, e8 e8Var) {
        ArrayList arrayList;
        o0.a aVar;
        String c2 = e8Var.c();
        if (e8Var.a() == 0 && (aVar = a.get(c2)) != null) {
            aVar.e(e8Var.f18588e, e8Var.f18589f);
            o0.c(context).h(c2, aVar);
        }
        if (TextUtils.isEmpty(e8Var.f18588e)) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(e8Var.f18588e);
            arrayList = arrayList2;
        }
        MiPushCommandMessage a2 = q.a(l4.COMMAND_REGISTER.f172a, arrayList, e8Var.f130a, e8Var.d, null, null);
        a aVar2 = b;
        if (aVar2 == null) {
            return;
        }
        aVar2.a(c2, a2);
        throw null;
    }

    public static void b(Context context, k8 k8Var) {
        MiPushCommandMessage a2 = q.a(l4.COMMAND_UNREGISTER.f172a, null, k8Var.a, k8Var.d, null, null);
        String a3 = k8Var.a();
        a aVar = b;
        if (aVar == null) {
            return;
        }
        aVar.b(a3, a2);
        throw null;
    }
}
