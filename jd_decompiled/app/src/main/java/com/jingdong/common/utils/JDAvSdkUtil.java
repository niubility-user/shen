package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDAvSdkUtil {
    private static final String TAG = "JDAvSdkUtil";

    public static void QueryRegistStatus(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            JDRtcUtils.QueryRegistStatus(jSONObject.optString("UserPin"), jSONObject.optString("Appid"), jSONObject.optString("instanceId"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void answer(IRouterParams iRouterParams) {
        try {
            JDRtcUtils.answer(new JSONObject(iRouterParams.getRouterParam()).optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void handsFree(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            JDRtcUtils.handsFree(jSONObject.optBoolean("isHandsFree"), jSONObject.optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void hangup(IRouterParams iRouterParams) {
        try {
            JDRtcUtils.hangup(new JSONObject(iRouterParams.getRouterParam()).optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void initVideoInstance(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("instanceId");
            String optString2 = jSONObject.optString("user");
            String optString3 = jSONObject.optString("appid");
            JDRtcUtils.register(jSONObject.has("isOpenCamera") ? jSONObject.optBoolean("isOpenCamera") : false, jSONObject.has("ServerAddr") ? jSONObject.optString("ServerAddr") : "", jSONObject.has("ServerPort") ? jSONObject.optString("ServerPort") : "", optString2, jSONObject.has("token") ? jSONObject.optString("token") : "", optString3, optString, jSONObject.has("clientType") ? jSONObject.optString("clientType") : "", jSONObject.has(HybridSDK.APP_VERSION) ? jSONObject.optString(HybridSDK.APP_VERSION) : "", jSONObject.has("usePushVideoView") ? jSONObject.optString("usePushVideoView") : "", iRouterParams);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void mute(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            JDRtcUtils.mute(jSONObject.optBoolean("isMute"), jSONObject.optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void onKicked(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("instanceId");
            if (TextUtils.isEmpty(optString)) {
                optString = jSONObject.optString("instname");
            }
            JDRtcUtils.registerKick(optString, iRouterParams);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void onPushInvite(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            JDRtcUtils.onPushInviteByInstance(jSONObject.optString("pushInfo"), jSONObject.optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void reconnectSignal(IRouterParams iRouterParams) {
        try {
            JDRtcUtils.reconnectSignalByInstance(new JSONObject(iRouterParams.getRouterParam()).optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void register(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            JDRtcUtils.register(true, jSONObject.has("ServerAddr") ? jSONObject.optString("ServerAddr") : "", jSONObject.has("ServerPort") ? jSONObject.optString("ServerPort") : "", jSONObject.optString("pin"), jSONObject.optString("aid"), jSONObject.optString("appid"), jSONObject.optString("instanceId"), jSONObject.optString("clientType"), jSONObject.optString(HybridSDK.APP_VERSION), jSONObject.has("usePushVideoView") ? jSONObject.optString("usePushVideoView") : "", iRouterParams);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void showRtcToast(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            JDRtcUtils.showRtcToast(jSONObject.optString("toastmsg"), jSONObject.optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void startVideoCall(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("user");
            String optString2 = jSONObject.optString("appid");
            String optString3 = jSONObject.has("selfNickName") ? jSONObject.optString("selfNickName") : "";
            String optString4 = jSONObject.has("selfNickSubName") ? jSONObject.optString("selfNickSubName") : "";
            String optString5 = jSONObject.has("selfheadPicUrl") ? jSONObject.optString("selfheadPicUrl") : "";
            String optString6 = jSONObject.has("token") ? jSONObject.optString("token") : "";
            String optString7 = jSONObject.has("clientType") ? jSONObject.optString("clientType") : "";
            String optString8 = jSONObject.has(HybridSDK.APP_VERSION) ? jSONObject.optString(HybridSDK.APP_VERSION) : "";
            String optString9 = jSONObject.has("usePushVideoView") ? jSONObject.optString("usePushVideoView") : "";
            String optString10 = jSONObject.has("ServerAddr") ? jSONObject.optString("ServerAddr") : "";
            String optString11 = jSONObject.has("ServerPort") ? jSONObject.optString("ServerPort") : "";
            String optString12 = jSONObject.has("ticketId") ? jSONObject.optString("ticketId") : "";
            String optString13 = jSONObject.has("userName") ? jSONObject.optString("userName") : "";
            String optString14 = jSONObject.has("userSubName") ? jSONObject.optString("userSubName") : "";
            String optString15 = jSONObject.has("Avatar") ? jSONObject.optString("Avatar") : "";
            String optString16 = jSONObject.optString("instanceId");
            String optString17 = jSONObject.optString("UserPin");
            String optString18 = jSONObject.optString("UserAppid");
            boolean optBoolean = jSONObject.optBoolean("isOpenCamera");
            boolean optBoolean2 = jSONObject.optBoolean("isVideoCall");
            String optString19 = jSONObject.optString("userData");
            JDRtcUtils.register(optBoolean, optString10, optString11, optString, optString6, optString2, optString16, optString7, optString8, optString9, iRouterParams);
            JDRtcUtils.callByInstance(optString3, optString4, optString5, optString12, optString17, optString18, optString13, optString14, optString15, optBoolean2, optBoolean, optString19, optString16);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void unregister(IRouterParams iRouterParams) {
        try {
            JDRtcUtils.unregisterByInstance(new JSONObject(iRouterParams.getRouterParam()).optString("instanceId"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* loaded from: classes6.dex */
    public static class JDRtcUtils {
        public static final String KEY_INIT_VIDEO = "initVideo";
        public static final String NEED_REGISTER_VALUE = "1";
        private static final String TAG = "JDRtcUtils";
        private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(2);
        private static boolean sRegistered = false;

        public static boolean QueryRegistStatus(String str, String str2, String str3) {
            try {
                Method rtcMethod = getRtcMethod("QueryRegistStatus", String.class, String.class, String.class);
                if (rtcMethod != null) {
                    Object invoke = rtcMethod.invoke(null, str, str2, str3);
                    if (invoke instanceof Boolean) {
                        return ((Boolean) invoke).booleanValue();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return false;
        }

        private static boolean allowRegister() {
            return true;
        }

        public static void answer(String str) {
            try {
                Method rtcMethod = getRtcMethod("answerByInstance", String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void callByInstance(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, boolean z2, String str10, String str11) {
            try {
                Class cls = Boolean.TYPE;
                Method rtcMethod = getRtcMethod("callByInstance", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, cls, cls, String.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str, str2, str3, str4, str5, str6, str7, str8, str9, Boolean.valueOf(z), Boolean.valueOf(z2), str10, str11);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public static Class getClassForName(String str) throws ClassNotFoundException {
            HashMap<String, Class> hashMap = CLASS_CACHE;
            Class<?> cls = hashMap.get(str);
            if (cls == null && (cls = JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass(str)) != null) {
                hashMap.put(str, cls);
            }
            return cls;
        }

        private static int getConnectionStateByInstance(String str) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
            Method rtcMethod = getRtcMethod("getConnectionStateByInstance", String.class);
            if (rtcMethod != null) {
                Object invoke = rtcMethod.invoke(null, str);
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
                return -1;
            }
            return -1;
        }

        public static Method getMethod(String str, String str2, Class<?>... clsArr) throws ClassNotFoundException, NoSuchMethodException {
            Class classForName = getClassForName(str);
            if (classForName != null) {
                return classForName.getMethod(str2, clsArr);
            }
            return null;
        }

        private static Method getRtcMethod(String str, Class<?>... clsArr) throws NoSuchMethodException, ClassNotFoundException {
            return getMethod("com.jd.lib.avsdk.JDRtcSdk", str, clsArr);
        }

        public static void handsFree(boolean z, String str) {
            try {
                Method rtcMethod = getRtcMethod("handsFreeByInstance", Boolean.TYPE, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, Boolean.valueOf(z), str);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void hangup(String str) {
            try {
                Method rtcMethod = getRtcMethod("hangupByInstance", String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void hasInviting(String str, String str2) {
            Method rtcMethod;
            if (isBundleAvsdkNotPrepared()) {
                return;
            }
            try {
                if (getConnectionStateByInstance(str2) != 10 || (rtcMethod = getRtcMethod("handleInvitedInfoByInstance", String.class, String.class)) == null) {
                    return;
                }
                rtcMethod.invoke(null, str, str2);
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void initInstance(String str, String str2, String str3) {
            if (isBundleAvsdkNotPrepared()) {
                return;
            }
            try {
                Method rtcMethod = getRtcMethod("initInstance", String.class, String.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str, str2, str3);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        private static boolean isBundleAvsdkNotPrepared() {
            return !AuraBundleConfig.getInstance().isBundlePrepared(AuraBundleInfos.getBundleNameFromBundleId(85));
        }

        public static boolean isCalling(String str) {
            if (isBundleAvsdkNotPrepared()) {
                return false;
            }
            try {
                return getConnectionStateByInstance(str) == 1;
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
                return false;
            }
        }

        public static void mute(boolean z, String str) {
            try {
                Method rtcMethod = getRtcMethod("muteByInstance", Boolean.TYPE, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, Boolean.valueOf(z), str);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void onPushInviteByInstance(String str, String str2) {
            if (isBundleAvsdkNotPrepared()) {
                return;
            }
            try {
                Method rtcMethod = getRtcMethod("onPushInviteByInstance", String.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str, str2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public static void reconnectSignalByInstance(String str) {
            if (isBundleAvsdkNotPrepared() || !allowRegister()) {
                return;
            }
            try {
                Method rtcMethod = getRtcMethod("reconnectSignalByInstance", String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        private static void register(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final IRouterParams iRouterParams) {
            if (isBundleAvsdkNotPrepared()) {
                ((IAuraInstallManager) AuraServiceLoader.get(JdSdk.getInstance().getApplicationContext(), IAuraInstallManager.class)).startInstall(JdSdk.getInstance().getApplicationContext(), new AuraInstallRequest.Builder().addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.utils.JDAvSdkUtil.JDRtcUtils.1
                    @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                    public void onSuccess() {
                        OKLog.i(JDRtcUtils.TAG, "Install avsdk success");
                        JDRtcUtils.initInstance(str4, str5, str6);
                        JDRtcUtils.registerInternal(str, str2, str3, str4, str7, iRouterParams);
                    }
                }).setBundleName(AuraBundleInfos.getBundleNameFromBundleId(85)).setDownloadType(1).build());
                return;
            }
            initInstance(str4, str5, str6);
            registerInternal(str, str2, str3, str4, str7, iRouterParams);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void registerInternal(String str, String str2, String str3, String str4, String str5, IRouterParams iRouterParams) {
            if (sRegistered) {
                return;
            }
            if (iRouterParams != null) {
                try {
                    setUseSdkUi(str4, iRouterParams);
                    setCallBackToRoute(iRouterParams, str4);
                } catch (Exception e2) {
                    OKLog.e(TAG, e2.getMessage());
                    return;
                }
            }
            Method rtcMethod = getRtcMethod("registerByInstance", String.class, String.class, String.class, String.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, str, str3, str2, str4, str5);
                OKLog.d(TAG, "Register success");
            }
        }

        public static void registerKick(String str, IRouterParams iRouterParams) {
            if (isBundleAvsdkNotPrepared()) {
                return;
            }
            try {
                Method rtcMethod = getRtcMethod("registerKickByInstance", String.class, IRouterParams.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str, iRouterParams);
                    OKLog.d(TAG, "registerKickByInstance success");
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void setCallBackToRoute(final IRouterParams iRouterParams, String str) {
            try {
                Observer observer = new Observer() { // from class: com.jingdong.common.utils.JDAvSdkUtil.JDRtcUtils.3
                    @Override // java.util.Observer
                    public void update(Observable observable, Object obj) {
                        IRouterParams.this.onCallBack(obj);
                    }
                };
                Method rtcMethod = getRtcMethod("setIRouterCallBack", Observer.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, observer, str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public static void setUseSdkUi(String str, IRouterParams iRouterParams) {
            try {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                if (jSONObject.has("useSdkUi")) {
                    boolean optBoolean = jSONObject.optBoolean("useSdkUi");
                    Method rtcMethod = getRtcMethod("setUseSdkUiByInstance", String.class, Boolean.TYPE);
                    if (rtcMethod != null) {
                        rtcMethod.invoke(null, str, Boolean.valueOf(optBoolean));
                    }
                } else {
                    Method rtcMethod2 = getRtcMethod("setUseSdkUiByInstance", String.class, Boolean.TYPE);
                    if (rtcMethod2 != null) {
                        rtcMethod2.invoke(null, str, Boolean.TRUE);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public static void showRtcToast(String str, String str2) {
            try {
                Method rtcMethod = getRtcMethod("showRtcToastByInstance", String.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str, str2);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void unregisterByInstance(String str) {
            if (isBundleAvsdkNotPrepared()) {
                return;
            }
            try {
                Method rtcMethod = getRtcMethod("unRegisterByInstance", String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, str);
                    OKLog.d(TAG, "Unregister success");
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }

        public static void initInstance(boolean z, String str, String str2, String str3, String str4, String str5) {
            if (isBundleAvsdkNotPrepared()) {
                return;
            }
            try {
                Method rtcMethod = getRtcMethod("initInstance", Boolean.TYPE, String.class, String.class, String.class, String.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, Boolean.valueOf(z), str, str2, str3, str4, str5);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public static void setCallBackToRoute(Observer observer, String str) {
            try {
                Method rtcMethod = getRtcMethod("setIRouterCallBack", Observer.class, String.class);
                if (rtcMethod != null) {
                    rtcMethod.invoke(null, observer, str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void register(final boolean z, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final IRouterParams iRouterParams) {
            if (isBundleAvsdkNotPrepared()) {
                ((IAuraInstallManager) AuraServiceLoader.get(JdSdk.getInstance().getApplicationContext(), IAuraInstallManager.class)).startInstall(JdSdk.getInstance().getApplicationContext(), new AuraInstallRequest.Builder().addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.utils.JDAvSdkUtil.JDRtcUtils.2
                    @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                    public void onSuccess() {
                        OKLog.i(JDRtcUtils.TAG, "Install avsdk success");
                        JDRtcUtils.initInstance(z, str, str2, str6, str7, str8);
                        JDRtcUtils.registerInternal(str3, str4, str5, str6, str9, iRouterParams);
                    }
                }).setBundleName(AuraBundleInfos.getBundleNameFromBundleId(85)).setDownloadType(1).build());
                return;
            }
            initInstance(z, str, str2, str6, str7, str8);
            registerInternal(str3, str4, str5, str6, str9, iRouterParams);
        }
    }
}
