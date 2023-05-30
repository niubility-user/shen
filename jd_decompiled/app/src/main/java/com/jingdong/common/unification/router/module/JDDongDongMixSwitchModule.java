package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDDongDongMixSwitchModule implements IJDModule {
    private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(2);
    private static final HashMap<String, Method> METHOD_CACHE = new HashMap<>();
    private static final String SET_MIXSESSION_SWITCH = "setMixSessionSwitch";
    private static final String TAG = "JDDongDongMixSwitchModule";

    private Class getClassForName(Context context, String str) throws ClassNotFoundException {
        HashMap<String, Class> hashMap = CLASS_CACHE;
        Class cls = hashMap.get(str);
        if (cls == null && (cls = context.getApplicationContext().getClassLoader().loadClass(str)) != null) {
            hashMap.put(str, cls);
        }
        return cls;
    }

    private Method getMethod(Context context, String str, Class<?>... clsArr) throws NoSuchMethodException, ClassNotFoundException {
        return getMethod(context, "jd.cdyjy.jimcore.ics.utils.MixCacheUtils", str, clsArr);
    }

    public void refreshMixSwitch(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            try {
                Object invoke = getMethod(context, SET_MIXSESSION_SWITCH, String.class).invoke(null, jDJSONObject.optString("pin"));
                invoke.toString();
                CallBackListener callBackListener = routerEntry.callBackListener;
                if (callBackListener == null || !(callBackListener instanceof CallBackWithReturnListener)) {
                    return;
                }
                ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke.toString());
                return;
            } catch (Exception e2) {
                e2.toString();
                CallBackListener callBackListener2 = routerEntry.callBackListener;
                if (callBackListener2 != null) {
                    JDRouterUtil.callBackError(callBackListener2, 703);
                    return;
                }
                return;
            }
        }
        CallBackListener callBackListener3 = routerEntry.callBackListener;
        if (callBackListener3 != null) {
            JDRouterUtil.callBackError(callBackListener3, 703);
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    private Method getMethod(Context context, String str, String str2, Class<?>... clsArr) throws ClassNotFoundException, NoSuchMethodException {
        Class classForName;
        HashMap<String, Method> hashMap = METHOD_CACHE;
        Method method = hashMap.get(str2);
        if (method != null || (classForName = getClassForName(context, str)) == null) {
            return method;
        }
        Method method2 = classForName.getMethod(str2, clsArr);
        hashMap.put(str2, method2);
        return method2;
    }
}
