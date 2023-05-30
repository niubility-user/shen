package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.b.a.a.l1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class p implements h {
    private Object c(com.jd.dynamic.a.g gVar, JSONObject jSONObject, Object... objArr) {
        Object a = (objArr == null || objArr.length <= 0) ? null : i.a(0, objArr);
        com.jd.dynamic.a.f fVar = (objArr == null || objArr.length <= 1 || !(objArr[1] instanceof com.jd.dynamic.a.f)) ? null : (com.jd.dynamic.a.f) objArr[1];
        if (a != null) {
            try {
                jSONObject.put("params", a);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        DynamicTemplateEngine b = gVar.b();
        if (b != null && b.getCachePool() != null) {
            CommFunction commFunctionByType = b.getCachePool().getCommFunctionByType(a());
            if (commFunctionByType == null) {
                commFunctionByType = new l1();
                b.getCachePool().addCommFunction(a(), commFunctionByType);
            }
            commFunctionByType.mTargetView = gVar.f1712e;
            if (commFunctionByType instanceof l1) {
                return ((l1) commFunctionByType).b(b.getActivity(), jSONObject, fVar);
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0030, code lost:
        com.jd.dynamic.lib.utils.t.g(" user onInvoke  " + r7);
     */
    @Override // com.jd.dynamic.a.a.a.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(com.jd.dynamic.a.g r6, java.lang.String r7, java.lang.Object... r8) {
        /*
            r5 = this;
            if (r6 == 0) goto L60
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto L60
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r6.b()
            if (r0 != 0) goto Lf
            goto L60
        Lf:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "fun"
            r0.put(r1, r7)     // Catch: java.lang.Exception -> L4f
            r1 = -1
            int r2 = r7.hashCode()     // Catch: java.lang.Exception -> L4f
            r3 = 103149417(0x625ef69, float:3.1208942E-35)
            r4 = 0
            if (r2 == r3) goto L25
            goto L2e
        L25:
            java.lang.String r2 = "login"
            boolean r2 = r7.equals(r2)     // Catch: java.lang.Exception -> L4f
            if (r2 == 0) goto L2e
            r1 = 0
        L2e:
            if (r1 == 0) goto L4a
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Exception -> L4f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L4f
            r1.<init>()     // Catch: java.lang.Exception -> L4f
            java.lang.String r2 = " user onInvoke  "
            r1.append(r2)     // Catch: java.lang.Exception -> L4f
            r1.append(r7)     // Catch: java.lang.Exception -> L4f
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Exception -> L4f
            r8[r4] = r7     // Catch: java.lang.Exception -> L4f
            com.jd.dynamic.lib.utils.t.g(r8)     // Catch: java.lang.Exception -> L4f
            goto L4f
        L4a:
            java.lang.Object r6 = r5.c(r6, r0, r8)     // Catch: java.lang.Exception -> L4f
            return r6
        L4f:
            com.jd.dynamic.base.DynamicTemplateEngine r7 = r6.b()
            com.jd.dynamic.lib.b.a.a.l1 r7 = r5.b(r7)
            com.jd.dynamic.base.DynamicTemplateEngine r6 = r6.b()
            java.lang.Object r6 = r7.exec(r6, r0)
            return r6
        L60:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.a.a.a.p.a(com.jd.dynamic.a.g, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "user";
    }

    public l1 b(DynamicTemplateEngine dynamicTemplateEngine) {
        CommFunction commFunctionByType = dynamicTemplateEngine.getCachePool().getCommFunctionByType(a());
        if (commFunctionByType == null) {
            commFunctionByType = new l1();
            dynamicTemplateEngine.getCachePool().addCommFunction(a(), commFunctionByType);
        }
        return (l1) commFunctionByType;
    }
}
