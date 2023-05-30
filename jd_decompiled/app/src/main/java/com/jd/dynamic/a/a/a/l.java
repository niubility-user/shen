package com.jd.dynamic.a.a.a;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class l implements h {
    private void b(Object obj, JSONObject jSONObject) {
        try {
            if (obj instanceof JSONObject) {
                if (((JSONObject) obj).has("body")) {
                    jSONObject.put("body", ((JSONObject) obj).opt("body"));
                }
                if (((JSONObject) obj).has("header")) {
                    jSONObject.put("header", ((JSONObject) obj).opt("header"));
                }
                if (((JSONObject) obj).has("method")) {
                    jSONObject.put("method", ((JSONObject) obj).opt("method"));
                }
                if (((JSONObject) obj).has("server")) {
                    jSONObject.put("server", ((JSONObject) obj).opt("server"));
                }
                if (((JSONObject) obj).has("businessType")) {
                    jSONObject.put("businessType", ((JSONObject) obj).opt("businessType"));
                }
                if (((JSONObject) obj).has(DYConstants.DY_REQUEST_SHOWLADING)) {
                    jSONObject.put(DYConstants.DY_REQUEST_SHOWLADING, ((JSONObject) obj).opt(DYConstants.DY_REQUEST_SHOWLADING));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        if (objArr != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    char c2 = '\uffff';
                    int hashCode = str.hashCode();
                    if (hashCode != 3526536) {
                        if (hashCode == 1803263872 && str.equals("sendFunction")) {
                            c2 = 1;
                        }
                    } else if (str.equals("send")) {
                        c2 = 0;
                    }
                    if (c2 == 0 || c2 == 1) {
                        JSONObject jSONObject = new JSONObject();
                        Object a = i.a(0, objArr);
                        if (a != null) {
                            jSONObject.put("send".equals(str) ? "url" : "functionId", a);
                        }
                        b(i.a(1, objArr), jSONObject);
                        com.jd.dynamic.a.f fVar = i.a(2, objArr) instanceof com.jd.dynamic.a.f ? (com.jd.dynamic.a.f) objArr[2] : null;
                        com.jd.dynamic.a.f fVar2 = i.a(3, objArr) instanceof com.jd.dynamic.a.f ? (com.jd.dynamic.a.f) objArr[3] : null;
                        DynamicTemplateEngine b = gVar.b();
                        if (b != null && b.getCachePool() != null) {
                            CommFunction commFunctionByType = b.getCachePool().getCommFunctionByType(a());
                            if (commFunctionByType == null) {
                                commFunctionByType = new com.jd.dynamic.lib.b.a.a.g();
                                b.getCachePool().addCommFunction(a(), commFunctionByType);
                            }
                            commFunctionByType.mTargetView = gVar.f1712e;
                            if (commFunctionByType instanceof com.jd.dynamic.lib.b.a.a.g) {
                                com.jd.dynamic.lib.b.a.a.g gVar2 = (com.jd.dynamic.lib.b.a.a.g) commFunctionByType;
                                return "send".equals(str) ? gVar2.e(b, jSONObject, fVar, fVar2) : gVar2.a(b, jSONObject, fVar, fVar2);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "request";
    }
}
