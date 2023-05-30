package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.facebook.react.uimanager.ViewProps;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDDongDongModule implements IJDModule {
    private static final String BACKGROUND_SWITCH = "backgroundSwitch";
    private static final String CANCEL_WIDNDOW = "cancelIcsPopWindow";
    private static final String CLEAR_ALL_UNREAD = "clearAllUnread";
    private static final String DELETE_ALL_RECENT = "deleteAllRecent";
    private static final String DEL_RECENT_ITEM = "deleteRecentItem";
    private static final String FOREGROUND_SWITCH = "foregroundSwitch";
    private static final String GET_PRODUCT_MSGS = "getProductMsgs";
    private static final String GET_RECENT_LIST = "getRecentList";
    private static final String GET_UNREAD_COUNT = "getUnreadCount";
    private static final String INIT_DATA = "initData";
    private static final String OPEN_CHAT = "openChat";
    private static final String OPEN_MIX_CHAT = "openMixChat";
    private static final String PARSE_EMOTION = "parseEmotion";
    private static final String PARSE_TEXT = "parseText";
    private static final String TAG = "JDDongDongModule";
    private static final String TOP_SESSION = "topSession";
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
        return getMethod(context, "com.jd.lib.icssdk.MessagerCenterUtils", str, clsArr);
    }

    public void backgroundSwitch(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, BACKGROUND_SWITCH, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
        } catch (Exception e2) {
            String str = "JDDongDongModule backgroundSwitch>>>" + e2.toString();
        }
    }

    public void cancelIcsPopWindow(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, CANCEL_WIDNDOW, Context.class).invoke(null, context);
        } catch (Exception e2) {
            String str = "JDDongDongModule cancelIcsPopWindow>>>" + e2.toString();
        }
    }

    public void clearAllUnread(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, CLEAR_ALL_UNREAD, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
            if (invoke != null && routerEntry.callBackListener != null) {
                if (((Boolean) invoke).booleanValue()) {
                    routerEntry.callBackListener.onComplete();
                } else {
                    JDRouterUtil.callBackError(routerEntry.callBackListener, 703);
                }
            }
        } catch (Exception e2) {
            String str = "JDDongDongModule clearAllUnread>>>" + e2.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 != null) {
                JDRouterUtil.callBackError(callBackListener2, 703);
            }
        }
    }

    public void deleteAllRecent(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, DELETE_ALL_RECENT, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
            if (invoke != null && routerEntry.callBackListener != null) {
                if (((Boolean) invoke).booleanValue()) {
                    routerEntry.callBackListener.onComplete();
                } else {
                    JDRouterUtil.callBackError(routerEntry.callBackListener, 703);
                }
            }
        } catch (Exception e2) {
            String str = "JDDongDongModule deleteAllRecent>>>" + e2.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 != null) {
                JDRouterUtil.callBackError(callBackListener2, 703);
            }
        }
    }

    public void deleteRecentItem(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, DEL_RECENT_ITEM, Context.class, String.class, String.class).invoke(null, context, jDJSONObject.optString("pin"), jDJSONObject.optString("recentContactEntityJsonStr"));
            if (invoke != null && routerEntry.callBackListener != null) {
                if (((Boolean) invoke).booleanValue()) {
                    routerEntry.callBackListener.onComplete();
                } else {
                    JDRouterUtil.callBackError(routerEntry.callBackListener, 703);
                }
            }
        } catch (Exception e2) {
            String str = "JDDongDongModule deleteRecentItem>>>" + e2.toString();
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 != null) {
                JDRouterUtil.callBackError(callBackListener2, 703);
            }
        }
    }

    public void foregroundSwitch(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, FOREGROUND_SWITCH, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
        } catch (Exception e2) {
            String str = "JDDongDongModule foregroundSwitch>>>" + e2.toString();
        }
    }

    public void getProductMsg(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            try {
                Object invoke = getMethod(context, GET_PRODUCT_MSGS, Context.class, String.class, String.class, String.class, String.class, Long.TYPE, Integer.TYPE).invoke(null, context, jDJSONObject.optString("fpin"), jDJSONObject.optString("fapp"), jDJSONObject.optString("tpin"), jDJSONObject.optString("tapp"), Long.valueOf(jDJSONObject.optLong(VerifyTracker.KEY_TIMESTAMP)), Integer.valueOf(jDJSONObject.optInt("count")));
                invoke.toString();
                CallBackListener callBackListener2 = routerEntry.callBackListener;
                if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                    return;
                }
                ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke.toString());
            } catch (Exception e2) {
                e = e2;
                e.toString();
                CallBackListener callBackListener3 = routerEntry.callBackListener;
                if (callBackListener3 != null) {
                    JDRouterUtil.callBackError(callBackListener3, 703);
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public void getRecentList(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, GET_RECENT_LIST, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
        } catch (Exception e2) {
            String str = "JDDongDongModule getRecentList>>>" + e2.toString();
        }
    }

    public void getUnreadCount(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, GET_UNREAD_COUNT, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            String str = "JDDongDongModule getUnreadCount>>>" + e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void initData(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, INIT_DATA, Context.class, String.class).invoke(null, context, jDJSONObject.optString("pin"));
        } catch (Exception e2) {
            String str = "JDDongDongModule initData>>>" + e2.toString();
        }
    }

    public void openChat(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, OPEN_CHAT, Context.class, String.class, String.class).invoke(null, context, jDJSONObject.optString("pin"), jDJSONObject.optString("recentContactEntityJsonStr"));
        } catch (Exception e2) {
            String str = "JDDongDongModule openChat>>>" + e2.toString();
        }
    }

    public void openMixChat(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        try {
            getMethod(context, OPEN_MIX_CHAT, Context.class, String.class, String.class).invoke(null, context, jDJSONObject.optString("pin"), jDJSONObject.optString(NotificationMessageSummary.TRANSMISSION));
        } catch (Exception e2) {
            String str = "JDDongDongModule openMixChat>>>" + e2.toString();
        }
    }

    public void parseEmotion(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, PARSE_EMOTION, Context.class, String.class, Integer.TYPE).invoke(null, context, jDJSONObject.optString("content"), Integer.valueOf(jDJSONObject.optInt(ViewProps.FONT_SIZE)));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            String str = "JDDongDongModule parseEmotion>>>" + e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    public void parseText(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            Object invoke = getMethod(context, PARSE_TEXT, Context.class, String.class).invoke(null, jDJSONObject.optString("lastMsg"));
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                return;
            }
            ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
        } catch (Exception e2) {
            String str = "JDDongDongModule parseText>>>" + e2.toString();
            CallBackListener callBackListener3 = routerEntry.callBackListener;
            if (callBackListener3 != null) {
                JDRouterUtil.callBackError(callBackListener3, 703);
            }
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    public void showChat(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("action", jDJSONObject.toString());
                DeeplinkDongDongHelper.getInstance().startDongDong(context, bundle);
                CallBackListener callBackListener = routerEntry.callBackListener;
                if (callBackListener != null) {
                    callBackListener.onComplete();
                    return;
                }
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
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

    public void topSession(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        try {
            try {
                getMethod(context, TOP_SESSION, String.class, String.class, Integer.class, Integer.class, RouterEntry.class).invoke(null, jDJSONObject.optString("pin"), jDJSONObject.optString("sessionId"), Integer.valueOf(jDJSONObject.optInt("type")), Integer.valueOf(jDJSONObject.optInt("top")), routerEntry);
            } catch (Exception e2) {
                e = e2;
                String str = "JDDongDongModule topSession>>>" + e.toString();
                CallBackListener callBackListener2 = routerEntry.callBackListener;
                if (callBackListener2 != null) {
                    JDRouterUtil.callBackError(callBackListener2, 703);
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
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
