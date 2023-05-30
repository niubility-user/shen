package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDDongDongSmileModule implements IJDModule {
    private static final String CHECK_SMILE = "checkSmile";
    private static final String DELETE_SMILEYS = "deleteSmileys";
    private static final String FIND_SMILEYS = "findSmileys";
    private static final String FIND_SMILEYS_WITH_FLAG = "findSmileysWithFlag";
    private static final String GET_EMOJI_URL = "getEmojiUrl";
    private static final String GET_PIN = "getPin";
    private static final String IS_LIGHT_MODE = "isLightMode";
    private static final String IS_SMILE = "isSmile";
    private static final String PARSE_EMOTION = "parseEmotion";
    private static final String PUT_DOWNLOAD_MARK = "putDownloadMark";
    private static final String SAVE_SMILEYS = "saveSmileys";
    private static final String TAG = "JDDongDongSmileModule";
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
        return getMethod(context, "com.jd.lib.icssdk.SmileRouterUtils", str, clsArr);
    }

    public void checkSmile(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, CHECK_SMILE, Context.class, Boolean.TYPE).invoke(null, context, Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void deleteSmileys(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, DELETE_SMILEYS, Context.class, Boolean.TYPE).invoke(null, context, Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void findSmileys(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, FIND_SMILEYS, Context.class, Boolean.TYPE).invoke(null, context, Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void findSmileysWithFlag(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, FIND_SMILEYS_WITH_FLAG, Context.class, Boolean.TYPE).invoke(null, context, Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void getEmojiUrl(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, GET_EMOJI_URL, new Class[0]).invoke(null, new Object[0]);
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void getPin(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, GET_PIN, Boolean.TYPE).invoke(null, Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void isLightMode(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, IS_LIGHT_MODE, Context.class).invoke(null, context);
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void isSmile(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, IS_SMILE, String.class).invoke(null, jDJSONObject.optString("content"));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void parseEmotion(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, PARSE_EMOTION, Context.class, String.class, Integer.TYPE, Boolean.TYPE).invoke(null, context, jDJSONObject.optString("content"), Integer.valueOf(jDJSONObject.optInt(ViewProps.FONT_SIZE)), Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            String str = "JDDongDongSmileModule parseEmotion>>>" + e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void putDownloadMark(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, PUT_DOWNLOAD_MARK, Boolean.TYPE).invoke(null, Boolean.valueOf(jDJSONObject.optBoolean(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD)));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void saveSmileys(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, SAVE_SMILEYS, Context.class, String.class, Boolean.TYPE).invoke(null, context, jDJSONObject.optString("smileBean"), Boolean.valueOf(jDJSONObject.optBoolean("visitor")));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
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
