package com.jd.dynamic.lib.utils;

import android.text.TextUtils;
import android.view.View;
import androidx.core.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.j.a;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.ViewNode;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class s {
    public static Object a(String str, DynamicTemplateEngine dynamicTemplateEngine, Object obj, View view) {
        com.jd.dynamic.lib.expv2.h hVar = new com.jd.dynamic.lib.expv2.h();
        hVar.a(dynamicTemplateEngine, obj, view);
        return hVar.a(str);
    }

    public static Object b(String str, Object obj, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        Exception exc;
        String str2;
        String j2;
        String O;
        int i2;
        t.g("execEvent: eventId:" + str);
        ArrayList arrayList = new ArrayList();
        if (dynamicTemplateEngine != null) {
            String param = dynamicTemplateEngine.getCachePool().getParam(str);
            if (TextUtils.isEmpty(param)) {
                str2 = "FunctionDispatcher dispatcherFunction jsonStr is empty, eventId: " + str;
                j2 = m.j(dynamicTemplateEngine);
                O = m.O(dynamicTemplateEngine);
                i2 = R2.attr.materialAlertDialogTitleTextStyle;
                exc = new Exception();
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(param);
                    if (jSONObject.has("funType") && jSONObject.has("funBody")) {
                        String string = jSONObject.getString("funType");
                        String string2 = jSONObject.getString("funBody");
                        if (TextUtils.isEmpty(string2)) {
                            return null;
                        }
                        if (TextUtils.equals(string, "javascript")) {
                            if (dynamicTemplateEngine.getJscDynContext() == null) {
                                dynamicTemplateEngine.initJSEnv();
                            }
                            if (dynamicTemplateEngine.getJscDynContext() != null) {
                                dynamicTemplateEngine.getJscDynContext().d = obj;
                                dynamicTemplateEngine.getJscDynContext().f1712e = view;
                                return dynamicTemplateEngine.getJscDynContext().e(string2, str, view);
                            }
                            return null;
                        }
                        param = string2;
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException("action", "FunctionDispatcher dispatcherFunction error, eventId: " + str, m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), R2.attr.materialAlertDialogTitlePanelStyle, e2, m.q(dynamicTemplateEngine.getZipVersion(), null));
                }
                try {
                    Object nextValue = new JSONTokener(param).nextValue();
                    if (nextValue instanceof JSONObject) {
                        g((JSONObject) nextValue, obj, dynamicTemplateEngine, view);
                        return c((JSONObject) nextValue, dynamicTemplateEngine, view);
                    } else if (nextValue instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) nextValue;
                        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                            JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                            g(optJSONObject, obj, dynamicTemplateEngine, view);
                            arrayList.add(c(optJSONObject, dynamicTemplateEngine, view));
                        }
                    }
                } catch (Exception e3) {
                    exc = e3;
                    str2 = "FunctionDispatcher dispatcherFunction error, eventId: " + str;
                    j2 = m.j(dynamicTemplateEngine);
                    O = m.O(dynamicTemplateEngine);
                    i2 = R2.attr.materialAlertDialogTitlePanelStyle;
                }
            }
            DynamicSdk.handException("action", str2, j2, O, i2, exc, m.q(dynamicTemplateEngine.getZipVersion(), null));
        } else {
            DynamicSdk.handException("action", "FunctionDispatcher dispatcherFunction without engine, eventId: " + str, (String) null, (String) null, (int) R2.attr.materialButtonOutlinedStyle, new Exception());
        }
        return arrayList;
    }

    private static Object c(JSONObject jSONObject, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        if (jSONObject.has("class")) {
            if (dynamicTemplateEngine != null) {
                Pair<String, JSONObject> a = com.jd.dynamic.b.b.a.a.a(jSONObject);
                t.g("execEvent: class:" + a.first + "  jsonObject:" + a.second);
                CommFunction commFunctionByType = dynamicTemplateEngine.getCachePool().getCommFunctionByType(a.first);
                if (commFunctionByType == null) {
                    IFunctionFactory customFactory = dynamicTemplateEngine.getCustomFactory();
                    if (customFactory != null) {
                        commFunctionByType = customFactory.createCommFunction(a.first);
                    }
                    if (commFunctionByType == null) {
                        commFunctionByType = dynamicTemplateEngine.getDefaultFactory().createCommFunction(a.first);
                        dynamicTemplateEngine.getCachePool().addCommFunction(a.first, commFunctionByType);
                    }
                }
                if (commFunctionByType == null) {
                    DynamicSdk.handException("action", "FunctionDispatcher genAndExecFunction function is null", m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), R2.attr.materialButtonStyle, null, m.q(dynamicTemplateEngine.getZipVersion(), null));
                } else if (commFunctionByType.getConditionValue(jSONObject)) {
                    commFunctionByType.mTargetView = view;
                    return commFunctionByType.execInner(dynamicTemplateEngine, a.second);
                }
            } else {
                DynamicSdk.handException("action", "FunctionDispatcher genAndExecFunction without engine", (String) null, (String) null, (int) R2.attr.materialButtonOutlinedStyle, (Exception) null);
            }
        }
        return null;
    }

    public static String d(String str) {
        int length;
        return (!TextUtils.isEmpty(str) && (length = str.length()) > 5) ? str.substring(4, length - 1) : "";
    }

    public static void e(final DynamicTemplateEngine dynamicTemplateEngine, ViewNode viewNode) {
        Observable.from(viewNode.getChilds()).forEach(new Action1() { // from class: com.jd.dynamic.lib.utils.f
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                s.j(dynamicTemplateEngine, (ViewNode) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.utils.g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                s.f(dynamicTemplateEngine, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void f(DynamicTemplateEngine dynamicTemplateEngine, Throwable th) {
        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "FunctionDispatcher parseEvent catch Exception", m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), R2.attr.liteMode, new Exception(th), dynamicTemplateEngine == null ? null : m.q(dynamicTemplateEngine.getZipVersion(), null));
    }

    public static void g(JSONObject jSONObject, Object obj, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        JSONArray names;
        if (jSONObject == null || (names = jSONObject.names()) == null) {
            return;
        }
        for (int i2 = 0; i2 < names.length(); i2++) {
            String optString = names.optString(i2);
            Object opt = jSONObject.opt(optString);
            if (opt instanceof JSONObject) {
                g((JSONObject) opt, obj, dynamicTemplateEngine, view);
            } else if (opt instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) opt;
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    g(jSONArray.optJSONObject(i3), obj, dynamicTemplateEngine, view);
                }
            } else if (DynamicUtils.isElOrKnownSymbol(optString)) {
                String obj2 = h(optString, dynamicTemplateEngine, obj, view).toString();
                String valueOf = String.valueOf(opt);
                if (DynamicUtils.isElOrKnownSymbol(valueOf)) {
                    Object h2 = h(valueOf, dynamicTemplateEngine, obj, view);
                    try {
                        jSONObject.remove(optString);
                        jSONObject.put(obj2, h2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    jSONObject.remove(optString);
                    jSONObject.put(obj2, valueOf);
                }
            } else {
                String valueOf2 = String.valueOf(opt);
                if (DynamicUtils.isElOrKnownSymbol(valueOf2)) {
                    jSONObject.put(optString, h(valueOf2, dynamicTemplateEngine, obj, view));
                }
            }
        }
    }

    private static Object h(String str, DynamicTemplateEngine dynamicTemplateEngine, Object obj, View view) {
        if (str.startsWith("fun{")) {
            return str;
        }
        if (com.jd.dynamic.b.a.b.o().q(dynamicTemplateEngine.getSystemCode(), dynamicTemplateEngine.getBizField())) {
            a.e eVar = a.e.f1789l;
            com.jd.dynamic.lib.expv2.c.i h2 = eVar.h(str);
            if (h2 == null) {
                h2 = eVar.g(str);
            }
            if (h2 != null) {
                h2.v();
                return h2.c(str, obj, dynamicTemplateEngine, view);
            }
            com.jd.dynamic.lib.expv2.c.i e2 = new com.jd.dynamic.lib.expv2.h().e(str);
            if (e2 == null) {
                return null;
            }
            return e2.c(str, obj, dynamicTemplateEngine, view);
        }
        return a(str, dynamicTemplateEngine, obj, view);
    }

    public static List<String> i(String str) {
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            if (str2.startsWith("fun{")) {
                String d = d(str2);
                if (!TextUtils.isEmpty(d)) {
                    arrayList.add(d);
                }
            }
        }
        return arrayList;
    }

    public static /* synthetic */ void j(DynamicTemplateEngine dynamicTemplateEngine, ViewNode viewNode) {
        String str = viewNode.getAttributes().get("id");
        String str2 = viewNode.getAttributes().get("type");
        String str3 = viewNode.getAttributes().get("__private_text__");
        if (TextUtils.isEmpty(str3)) {
            str3 = viewNode.textContent;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.equals(str2, "javascript")) {
                jSONObject.put("funBody", "container.__events." + str + "=function(targetView){" + str3 + "}");
                jSONObject.put("funType", "javascript");
                str3 = jSONObject.toString();
                dynamicTemplateEngine.loadJsSo();
            } else {
                jSONObject.put("funBody", str3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        dynamicTemplateEngine.getCachePool().putParam(str, str3);
    }
}
