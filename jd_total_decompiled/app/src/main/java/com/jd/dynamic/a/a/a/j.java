package com.jd.dynamic.a.a.a;

import android.text.TextUtils;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.b.a.a.j1;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class j implements h {
    /* JADX WARN: Removed duplicated region for block: B:123:0x0036 A[Catch: Exception -> 0x0053, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0053, blocks: (B:109:0x000b, B:120:0x002b, B:123:0x0036, B:128:0x003e, B:130:0x0042, B:117:0x0020), top: B:152:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x003e A[Catch: Exception -> 0x0053, TRY_ENTER, TryCatch #2 {Exception -> 0x0053, blocks: (B:109:0x000b, B:120:0x002b, B:123:0x0036, B:128:0x003e, B:130:0x0042, B:117:0x0020), top: B:152:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0072  */
    @Override // com.jd.dynamic.a.a.a.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        JSONObject jSONObject;
        CommFunction commFunctionByType;
        int hashCode;
        String str2;
        Object a;
        if (gVar != null && !TextUtils.isEmpty(str)) {
            try {
                hashCode = str.hashCode();
            } catch (Exception e2) {
                e = e2;
                jSONObject = null;
            }
            if (hashCode == -1926005497) {
                str2 = "exposure";
            } else if (hashCode == 3590) {
                str2 = "pv";
            } else if (hashCode != 94750088) {
                a = i.a(0, objArr);
                if (!(a instanceof JSONObject)) {
                    jSONObject = (JSONObject) a;
                    try {
                        jSONObject.put("fun", str);
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        if (jSONObject != null) {
                        }
                        return null;
                    }
                } else if (a instanceof String) {
                    JSONObject jSONObject2 = new JSONObject((String) a);
                    try {
                        jSONObject2.put("fun", str);
                        jSONObject = jSONObject2;
                    } catch (Exception e4) {
                        e = e4;
                        jSONObject = jSONObject2;
                        e.printStackTrace();
                        if (jSONObject != null) {
                        }
                        return null;
                    }
                } else {
                    jSONObject = null;
                }
                if (jSONObject != null && gVar.b() != null) {
                    commFunctionByType = gVar.b().getCachePool().getCommFunctionByType(a());
                    if (commFunctionByType == null) {
                        commFunctionByType = new j1();
                        gVar.b().getCachePool().addCommFunction(a(), commFunctionByType);
                    }
                    commFunctionByType.mTargetView = gVar.f1712e;
                    return commFunctionByType.exec(gVar.b(), jSONObject);
                }
            } else {
                str2 = "click";
            }
            str.equals(str2);
            a = i.a(0, objArr);
            if (!(a instanceof JSONObject)) {
            }
            if (jSONObject != null) {
                commFunctionByType = gVar.b().getCachePool().getCommFunctionByType(a());
                if (commFunctionByType == null) {
                }
                commFunctionByType.mTargetView = gVar.f1712e;
                return commFunctionByType.exec(gVar.b(), jSONObject);
            }
        }
        return null;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return IExceptionHandler.DynamicExceptionData.TYPE_MTA;
    }
}
