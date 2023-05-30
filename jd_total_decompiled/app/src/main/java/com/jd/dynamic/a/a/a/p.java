package com.jd.dynamic.a.a.a;

import android.text.TextUtils;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.b.a.a.l1;
import com.jingdong.common.login.LoginConstans;
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

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0030, code lost:
        com.jd.dynamic.lib.utils.t.g(" user onInvoke  " + r7);
     */
    @Override // com.jd.dynamic.a.a.a.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        JSONObject jSONObject;
        if (gVar == null || TextUtils.isEmpty(str) || gVar.b() == null) {
            return null;
        }
        jSONObject = new JSONObject();
        try {
            jSONObject.put("fun", str);
            char c2 = '\uffff';
            if (str.hashCode() == 103149417 && str.equals(LoginConstans.FREGMENT_LOGIN_FLAG)) {
                c2 = 0;
            }
            return c(gVar, jSONObject, objArr);
        } catch (Exception unused) {
        }
        return b(gVar.b()).exec(gVar.b(), jSONObject);
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
