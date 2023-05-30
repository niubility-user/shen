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
public class JDDongDongMediaModule implements IJDModule {
    private static final String HANDLE_GROUP_PIC = "handleGroupPic";
    private static final String HANDLE_GROUP_VIDEO = "handleGroupVideo";
    private static final String HANDLE_PIC = "handlePic";
    private static final String HANDLE_VIDEO = "handleVideo";
    private static final String TAG = "JDDongDongMediaModule";
    private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(2);
    private static final HashMap<String, Method> METHOD_CACHE = new HashMap<>();

    private Class getClassForName(Context context, String str) throws ClassNotFoundException {
        HashMap<String, Class> hashMap = CLASS_CACHE;
        Class cls = hashMap.get(str);
        if (cls == null && (cls = context.getApplicationContext().getClassLoader().loadClass(str)) != null) {
            hashMap.put(str, cls);
        }
        return cls;
    }

    private Method getMethod(Context context, String str, Class<?>... clsArr) throws NoSuchMethodException, ClassNotFoundException {
        return getMethod(context, "com.jd.lib.icssdk.utils.router.MediaUtils", str, clsArr);
    }

    public void handleGroupPic(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, HANDLE_GROUP_PIC, String.class, String.class).invoke(null, jDJSONObject.optString("pin"), jDJSONObject.optString("mediaInfos"));
            invoke.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke.toString());
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void handleGroupVideo(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, HANDLE_GROUP_VIDEO, Integer.TYPE, String.class, String.class).invoke(null, Integer.valueOf(jDJSONObject.optInt("mediaType")), jDJSONObject.optString("pin"), jDJSONObject.optString("path"));
            invoke.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke.toString());
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void handlePic(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, HANDLE_PIC, String.class, String.class).invoke(null, jDJSONObject.optString("pin"), jDJSONObject.optString("mediaInfos"));
            invoke.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke.toString());
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void handleVideo(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, HANDLE_VIDEO, Integer.TYPE, String.class, String.class).invoke(null, Integer.valueOf(jDJSONObject.optInt("mediaType")), jDJSONObject.optString("pin"), jDJSONObject.optString("path"));
            invoke.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke.toString());
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
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
