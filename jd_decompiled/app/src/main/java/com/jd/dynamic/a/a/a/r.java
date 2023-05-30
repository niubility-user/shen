package com.jd.dynamic.a.a.a;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.b.a.a.n1;
import com.jd.dynamic.lib.utils.s;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class r implements h {
    static final /* synthetic */ boolean a = true;

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        Object a2;
        if (gVar == null || TextUtils.isEmpty(str)) {
            return "view";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fun", str);
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1899989709:
                    if (str.equals(DYConstants.SIZE_FOR_IMAGE)) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -1846934213:
                    if (str.equals("jointString")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1347771489:
                    if (str.equals("changeItemViewAttributes")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -199516587:
                    if (str.equals(DYConstants.SIZE_FOR_TEXT)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 3480550:
                    if (str.equals("getAttribute")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 865451303:
                    if (str.equals("changeAttributes")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 2141998266:
                    if (str.equals("rebindData")) {
                        c2 = 1;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    if (!a && i.a(0, objArr) == null) {
                        throw new AssertionError();
                    }
                    int intValue = ((Double) i.a(0, objArr)).intValue();
                    Object a3 = i.a(1, objArr);
                    a2 = i.a(2, objArr);
                    jSONObject.put("viewId", intValue);
                    jSONObject.put("layoutId", intValue);
                    jSONObject.put("key", a3);
                    jSONObject.put("value", a2);
                    break;
                case 1:
                    int intValue2 = ((Double) i.a(0, objArr)).intValue();
                    jSONObject.put("layoutId", intValue2);
                    jSONObject.put("viewId", intValue2);
                    jSONObject.put(DeepLinkFillOrderHelper.FROM_KEY, "dynamic_init_data");
                    str = "reBindData";
                    jSONObject.put("fun", str);
                    break;
                case 2:
                    int intValue3 = ((Double) i.a(0, objArr)).intValue();
                    Object a4 = i.a(1, objArr);
                    jSONObject.put("layoutId", intValue3);
                    jSONObject.put("viewId", intValue3);
                    jSONObject.put("key", a4);
                    break;
                case 3:
                    break;
                case 4:
                    Object a5 = i.a(0, objArr);
                    a2 = i.a(1, objArr);
                    jSONObject.put("methodName", a5);
                    jSONObject.put("value", a2);
                    break;
                case 5:
                    Object a6 = i.a(0, objArr);
                    Object a7 = i.a(1, objArr);
                    if (a7 instanceof JSONObject) {
                        ((JSONObject) a7).put("text", a6);
                        jSONObject.put("value", a7);
                        jSONObject.put(DYConstants.DY_IS_JS, true);
                    }
                    jSONObject.put("fun", str);
                    break;
                case 6:
                    Object a8 = i.a(0, objArr);
                    com.jd.dynamic.a.f fVar = i.a(1, objArr) instanceof com.jd.dynamic.a.f ? (com.jd.dynamic.a.f) i.a(1, objArr) : null;
                    if (a8 instanceof JSONObject) {
                        jSONObject.put("fun", str);
                        jSONObject.put("value", a8);
                        DynamicTemplateEngine b = gVar.b();
                        if (b != null && b.getCachePool() != null) {
                            return b(gVar, b).b(b, jSONObject, fVar);
                        }
                    }
                    break;
                default:
                    return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        DynamicTemplateEngine b2 = gVar.b();
        if (b2 == null || b2.getCachePool() == null) {
            return null;
        }
        s.g(jSONObject, null, b2, null);
        return b(gVar, b2).exec(b2, jSONObject);
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "view";
    }

    public n1 b(com.jd.dynamic.a.g gVar, DynamicTemplateEngine dynamicTemplateEngine) {
        CommFunction commFunctionByType = dynamicTemplateEngine.getCachePool().getCommFunctionByType(a());
        if (commFunctionByType == null) {
            commFunctionByType = new n1();
            dynamicTemplateEngine.getCachePool().addCommFunction(a(), commFunctionByType);
        }
        commFunctionByType.mTargetView = gVar.f1712e;
        return (n1) commFunctionByType;
    }
}
