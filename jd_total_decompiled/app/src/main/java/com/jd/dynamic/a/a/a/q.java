package com.jd.dynamic.a.a.a;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.b.a.a.m1;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class q implements h {
    /* JADX WARN: Code restructure failed: missing block: B:335:0x019e, code lost:
        if (r12 != null) goto L336;
     */
    @Override // com.jd.dynamic.a.a.a.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        Object a;
        String str2;
        Object a2;
        Object a3;
        if (gVar == null || TextUtils.isEmpty(str) || gVar.b() == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fun", str);
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1843426658:
                    if (str.equals("hideSoftInput")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1814793473:
                    if (str.equals("getIntentValue")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case -1588806437:
                    if (str.equals("getMbaValue")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1332085432:
                    if (str.equals(XView2Constants.XVIEW2_ACTION_DIALOG)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1263222921:
                    if (str.equals("openApp")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case -1263203643:
                    if (str.equals("openUrl")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case -1010580297:
                    if (str.equals("openH5")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case -691974485:
                    if (str.equals("addCacheData")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 873657:
                    if (str.equals("handData")) {
                        c2 = 14;
                        break;
                    }
                    break;
                case 108704329:
                    if (str.equals("route")) {
                        c2 = 15;
                        break;
                    }
                    break;
                case 110532135:
                    if (str.equals(XView2Constants.XVIEW2_ACTION_TOAST)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 243355426:
                    if (str.equals("getResultIntentValue")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 772299971:
                    if (str.equals("openAppWithParams")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 1151394242:
                    if (str.equals("finishPage")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1561120579:
                    if (str.equals("showSoftInput")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1778729330:
                    if (str.equals("getNetWorkType")) {
                        c2 = '\r';
                        break;
                    }
                    break;
            }
            String str3 = "key";
            switch (c2) {
                case 1:
                    a = i.a(0, objArr);
                    str2 = "layoutId";
                    jSONObject.put(str2, a);
                    break;
                case 2:
                    Object a4 = i.a(0, objArr);
                    a2 = i.a(1, objArr);
                    jSONObject.put("key", a4);
                    str3 = "value";
                    jSONObject.put(str3, a2);
                    break;
                case 3:
                    a = i.a(0, objArr);
                    if (a != null) {
                        str2 = "msg";
                        jSONObject.put(str2, a);
                        break;
                    }
                    break;
                case 4:
                    a2 = i.a(0, objArr);
                    if (a2 != null) {
                        jSONObject.put(str3, a2);
                        break;
                    }
                    break;
                case 5:
                    Object a5 = i.a(0, objArr);
                    Object a6 = i.a(1, objArr);
                    Object a7 = i.a(2, objArr);
                    Object a8 = i.a(3, objArr);
                    a2 = i.a(4, objArr);
                    jSONObject.put("title", a5);
                    jSONObject.put("content", a6);
                    jSONObject.put("confirm", a7);
                    jSONObject.put("cancel", a8);
                    str3 = "success";
                    jSONObject.put(str3, a2);
                    break;
                case 7:
                case '\b':
                    a2 = i.a(0, objArr);
                    jSONObject.put(str3, a2);
                    break;
                case '\t':
                    a3 = i.a(0, objArr);
                    if (a3 != null) {
                        jSONObject.put("url", a3);
                        break;
                    }
                    break;
                case '\n':
                    a3 = i.a(0, objArr);
                    if (a3 != null) {
                        jSONObject.put("url", a3);
                        break;
                    }
                    break;
                case 11:
                    a3 = i.a(0, objArr);
                    break;
                case '\f':
                    Object a9 = i.a(0, objArr);
                    Object a10 = i.a(1, objArr);
                    Object a11 = i.a(2, objArr);
                    a2 = i.a(3, objArr);
                    jSONObject.put("scheme", a9);
                    jSONObject.put("host", a10);
                    jSONObject.put("category", a11);
                    str3 = "des";
                    jSONObject.put(str3, a2);
                    break;
                case 15:
                    Object a12 = i.a(0, objArr);
                    if (a12 instanceof String) {
                        jSONObject.put("url", a12);
                    }
                    Object a13 = i.a(1, objArr);
                    if ((a13 instanceof JSONObject) && (a12 instanceof String)) {
                        StringBuilder sb = new StringBuilder((String) a12);
                        Iterator<String> keys = ((JSONObject) a13).keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            String optString = ((JSONObject) a13).optString(next);
                            if (!TextUtils.isEmpty(optString)) {
                                if (TextUtils.isEmpty(Uri.parse(sb.toString()).getQuery())) {
                                    sb.append("?");
                                    sb.append(next);
                                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                } else {
                                    sb.append(ContainerUtils.FIELD_DELIMITER);
                                    sb.append(next);
                                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                }
                                sb.append(optString);
                            }
                        }
                        jSONObject.put("url", sb.toString());
                    }
                    return b(gVar, gVar.b()).a(gVar.b(), jSONObject, i.a(2, objArr) instanceof com.jd.dynamic.a.f ? (com.jd.dynamic.a.f) i.a(2, objArr) : null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return b(gVar, gVar.b()).exec(gVar.b(), jSONObject);
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "util";
    }

    public m1 b(com.jd.dynamic.a.g gVar, DynamicTemplateEngine dynamicTemplateEngine) {
        CommFunction commFunctionByType = dynamicTemplateEngine.getCachePool().getCommFunctionByType(a());
        if (commFunctionByType == null) {
            commFunctionByType = new m1();
            dynamicTemplateEngine.getCachePool().addCommFunction(a(), commFunctionByType);
        }
        commFunctionByType.mTargetView = gVar.f1712e;
        return (m1) commFunctionByType;
    }
}
