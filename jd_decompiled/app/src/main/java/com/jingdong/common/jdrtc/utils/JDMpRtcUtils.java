package com.jingdong.common.jdrtc.utils;

import android.content.Context;
import android.view.SurfaceView;
import com.jd.framework.json.JDJSON;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.jdrtc.bean.RtcMpUserInfo;
import com.jingdong.common.jdrtc.event.RtcMpInfoInterface;
import com.jingdong.common.jdrtc.event.RtcMpStateInterface;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Observer;

/* loaded from: classes5.dex */
public class JDMpRtcUtils {
    private static final String TAG = "JDMpRtcUtils";
    private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(2);
    private static boolean sRegistered = false;

    /* loaded from: classes5.dex */
    public interface IJdmpRtcDownload {
        void fail();

        void success();
    }

    public static Class getClassForName(Context context, String str) throws ClassNotFoundException {
        HashMap<String, Class> hashMap = CLASS_CACHE;
        Class cls = hashMap.get(str);
        if (cls == null && (cls = context.getApplicationContext().getClassLoader().loadClass(str)) != null) {
            hashMap.put(str, cls);
        }
        return cls;
    }

    public static Method getMethod(Context context, String str, String str2, Class<?>... clsArr) throws ClassNotFoundException, NoSuchMethodException {
        Class classForName = getClassForName(context, str);
        if (classForName != null) {
            return classForName.getMethod(str2, clsArr);
        }
        return null;
    }

    public static boolean getMpRegisterStatus(Context context, String str, String str2, String str3, String str4) {
        try {
            Method rtcMethod = getRtcMethod(context, "QueryMpRegistStatus", String.class, String.class, String.class, String.class);
            if (rtcMethod != null) {
                boolean booleanValue = ((Boolean) rtcMethod.invoke(null, str2, str3, str4, str)).booleanValue();
                String str5 = "getMpRegisterStatus----isRegist = " + booleanValue;
                return booleanValue;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static Method getRtcMethod(Context context, String str, Class<?>... clsArr) throws NoSuchMethodException, ClassNotFoundException {
        return getMethod(context, "com.jd.lib.avsdk.JDRtcSdk", str, clsArr);
    }

    public static SurfaceView getRtcMpSurfaceByPin(Context context, String str, String str2) {
        try {
            Method rtcMethod = getRtcMethod(context, "getRtcMpSurfaceByPin", Context.class, String.class, String.class);
            if (rtcMethod != null) {
                return (SurfaceView) rtcMethod.invoke(null, context, str, str2);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static SurfaceView getRtcSelfMpSurface(Context context, String str) {
        try {
            Method rtcMethod = getRtcMethod(context, "getRtcSelfMpSurface", Context.class, String.class);
            if (rtcMethod != null) {
                return (SurfaceView) rtcMethod.invoke(null, context, str);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static SurfaceView getRtcThatMpSurface(Context context, String str) {
        try {
            Method rtcMethod = getRtcMethod(context, "getRtcThatMpSurface", Context.class, String.class);
            if (rtcMethod != null) {
                return (SurfaceView) rtcMethod.invoke(null, context, str);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static void initMpInstance(Context context, String str, String str2, String str3, String str4, String str5) {
        try {
            Method rtcMethod = getRtcMethod(context, "initMpInstance", Context.class, String.class, String.class, String.class, String.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, str2, str3, str4, str5);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void installRtcBundle(final IJdmpRtcDownload iJdmpRtcDownload) {
        if (isBundleAvsdkNotPrepared()) {
            ((IAuraInstallManager) AuraServiceLoader.get(JdSdk.getInstance().getApplicationContext(), IAuraInstallManager.class)).startInstall(JdSdk.getInstance().getApplicationContext(), new AuraInstallRequest.Builder().addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.jdrtc.utils.JDMpRtcUtils.2
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                public void onSuccess() {
                    OKLog.i(JDMpRtcUtils.TAG, "Install avsdk success mp");
                    IJdmpRtcDownload.this.success();
                }
            }).addOnFailerListener(new AuraInstallRequest.IOnFailerListener() { // from class: com.jingdong.common.jdrtc.utils.JDMpRtcUtils.1
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
                public void onFailure(Exception exc) {
                    OKLog.i(JDMpRtcUtils.TAG, "Install avsdk failed mp");
                    IJdmpRtcDownload.this.fail();
                }
            }).setBundleName(AuraBundleInfos.getBundleNameFromBundleId(85)).setDownloadType(1).build());
            return;
        }
        iJdmpRtcDownload.success();
    }

    private static boolean isBundleAvsdkNotPrepared() {
        return !AuraBundleConfig.getInstance().isBundlePrepared(AuraBundleInfos.getBundleNameFromBundleId(85));
    }

    public static void registerMpByInstance(Context context, String str, String str2, String str3, String str4, String str5) {
        if (sRegistered) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "registerMpByInstance", String.class, String.class, String.class, String.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, str, str2, str3, str4, str5);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void releaseRtcAllMpSurface(Context context, String str) {
        try {
            Method rtcMethod = getRtcMethod(context, "releaseRtcAllMpSurface", Context.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str);
            }
        } catch (Exception unused) {
        }
    }

    public static void releaseRtcMpSurfaceByPin(Context context, String str, String str2) {
        try {
            Method rtcMethod = getRtcMethod(context, "releaseRtcMpSurfaceByPin", Context.class, String.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, str2);
            }
        } catch (Exception unused) {
        }
    }

    public static void rtcHandFreeOpen(Context context, String str, boolean z) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "rtcHandFreeOpen", Context.class, String.class, Boolean.TYPE);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, Boolean.valueOf(z));
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void rtcMpAcceptInvite(Context context, String str) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "rtcMpAcceptInvite", Context.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void rtcMpCall(Context context, String str, String str2, String str3, RtcMpUserInfo rtcMpUserInfo, boolean z, String str4) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            String jSONString = JDJSON.toJSONString(rtcMpUserInfo);
            String str5 = "rtcMpCall start ---- thatJsonString" + jSONString + " selfName = " + str2 + " selfAvatar = " + str3 + " isVideoType = " + z + "userData = " + str4;
            Method rtcMethod = getRtcMethod(context, "rtcMpCall", Context.class, String.class, String.class, String.class, String.class, Boolean.TYPE, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, str2, str3, jSONString, Boolean.valueOf(z), str4);
            }
        } catch (Exception e2) {
            String str6 = "rtcMpCall----error = " + e2.toString();
        }
    }

    public static void rtcMpHangUp(Context context, String str) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "rtcMpHangUp", Context.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void rtcMpSpeakerOpen(Context context, String str, boolean z) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "rtcMpSpeakerOpen", Context.class, String.class, Boolean.TYPE);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, Boolean.valueOf(z));
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void rtcMpToggleCamera(Context context, String str) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "rtcMpToggleCamera", Context.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void rtcMpVideoOpen(Context context, String str, boolean z) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "rtcMpVideoOpen", Context.class, String.class, Boolean.TYPE);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, Boolean.valueOf(z));
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void setRtcMpIRouterCallback(Context context, String str, Observer observer) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "setRtcMpIRouterCallBack", Observer.class, String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, observer, str);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void setRtcMpInfoInterface(Context context, String str, RtcMpInfoInterface rtcMpInfoInterface) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "setRtcMpInfoInterface", Context.class, String.class, RtcMpInfoInterface.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, rtcMpInfoInterface);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void setRtcMpStateInterface(Context context, String str, RtcMpStateInterface rtcMpStateInterface) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "setRtcMpStateInterface", Context.class, String.class, RtcMpStateInterface.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, rtcMpStateInterface);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void unRegisterMpByInstance(Context context, String str) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            Method rtcMethod = getRtcMethod(context, "unRegisterMpByInstance", String.class);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, str);
                sRegistered = false;
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public static void updateMpSurface(Context context, String str) {
    }

    public static boolean registerMpByInstance(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Observer observer) {
        try {
            if (isBundleAvsdkNotPrepared()) {
                return false;
            }
            initMpInstance(context, str, str2, str6, str7, str8);
            setRtcMpIRouterCallback(context, str6, observer);
            registerMpByInstance(context, str3, str4, str5, "token", str6);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void rtcMpCall(Context context, String str, String str2, String str3, RtcMpUserInfo rtcMpUserInfo, boolean z, String str4, String str5, boolean z2) {
        if (isBundleAvsdkNotPrepared()) {
            return;
        }
        try {
            String jSONString = JDJSON.toJSONString(rtcMpUserInfo);
            String str6 = "rtcMpCall start ---- thatJsonString" + jSONString + " selfName = " + str2 + " selfAvatar = " + str3 + " isVideoType = " + z + "userData = " + str4 + "sessionId = " + str5 + "isOpenCamera = " + z2;
            Class cls = Boolean.TYPE;
            Method rtcMethod = getRtcMethod(context, "rtcMpCall", Context.class, String.class, String.class, String.class, String.class, cls, String.class, String.class, cls);
            if (rtcMethod != null) {
                rtcMethod.invoke(null, context, str, str2, str3, jSONString, Boolean.valueOf(z), str4, str5, Boolean.valueOf(z2));
            }
        } catch (Exception e2) {
            String str7 = "rtcMpCall----error = " + e2.toString();
        }
    }
}
