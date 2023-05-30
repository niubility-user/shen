package com.jingdong.common.messagecenter;

import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.jingdong.common.entity.HWModel;
import com.jingdong.common.entity.JDModel;
import com.jingdong.common.entity.MIModel;
import com.jingdong.common.entity.MZModel;
import com.jingdong.common.entity.OPPOModel;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MessageCommonUtils {
    public static final String LOCAL_NOTIFICATION_COUNT = "local_notification_count";
    public static final String MESSAGE_SOUND_SWITCH = "message_sound_switch";
    private static final String PUSH_CONFIG = "pushConfig";
    private static final String TAG = "MessageCommonUtils";
    public static JDPushApi jdPushApi;
    private static long lastClickTime;
    private static LoginBaseStateChange mloginBaseStateChange;

    /* loaded from: classes5.dex */
    public class DeviceModle {
        public static final int DEVICE_EMUI = 2;
        public static final int DEVICE_FLYME = 3;
        public static final int DEVICE_GT = 5;
        public static final int DEVICE_HONOR = 12;
        public static final int DEVICE_MIUI = 1;
        public static final int DEVICE_OPPO = 6;
        public static final int DEVICE_ORTHER = 0;
        public static final int DEVICE_VIVO = 8;
        public static final int DEVICE_XG = 4;

        public DeviceModle() {
            MessageCommonUtils.this = r1;
        }
    }

    /* loaded from: classes5.dex */
    public interface LoginBaseStateChange {
        void loginUserIn();

        void loginUserOut();
    }

    public static void Bind() {
        LoginBaseStateChange loginBaseStateChange = mloginBaseStateChange;
        if (loginBaseStateChange != null) {
            loginBaseStateChange.loginUserIn();
        }
        try {
            JDPushApi jDPushApi = jdPushApi;
            if (jDPushApi != null) {
                jDPushApi.bindPin(UserUtil.getWJLoginHelper().getPin());
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void clearBadge(Context context) throws Exception {
        JDPushApi jDPushApi = jdPushApi;
        if (jDPushApi != null) {
            jDPushApi.clearBadge(context);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "clearBadge");
        }
    }

    public static void clearPushNotices() {
        try {
            JDPushApi jDPushApi = jdPushApi;
            if (jDPushApi != null) {
                jDPushApi.clearPushNotices();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int countUnreadMsg(Context context, String str) {
        int i2 = 0;
        try {
            i2 = ((Integer) JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass("com.jd.lib.icssdk.MessagerCenterUtils").getMethod("count_unread_msg", Context.class, String.class).invoke(null, context, str)).intValue();
            OKLog.d("count_unread_msg", String.valueOf(i2));
            return i2;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return i2;
        }
    }

    public static String countUnreadMsginfo(Context context, String str) {
        String str2;
        String str3 = null;
        try {
            str2 = (String) JdSdk.getInstance().getApplicationContext().getClassLoader().loadClass("com.jd.lib.icssdk.MessagerCenterUtils").getMethod("count_unread_msg", Context.class, String.class).invoke(null, context, str);
        } catch (Error e2) {
            e = e2;
        } catch (Exception e3) {
            e = e3;
        }
        try {
            OKLog.d("count_unread_msg", String.valueOf(str2));
            return str2;
        } catch (Error e4) {
            e = e4;
            str3 = str2;
            OKLog.e(TAG, e);
            return str3;
        } catch (Exception e5) {
            e = e5;
            str3 = str2;
            OKLog.e(TAG, e);
            return str3;
        }
    }

    public static int getDeviceChannel() {
        JDPushApi jDPushApi = jdPushApi;
        if (jDPushApi == null) {
            return -1;
        }
        return jDPushApi.getChannelId();
    }

    public static String getDeviceToken(Context context) {
        return makeDeviceToken(context);
    }

    public static String getMIRegId(Context context, int i2) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("MIRegId" + i2, "");
    }

    public static boolean getMessageSoundSwitch() {
        return CommonBase.getJdSharedPreferences().getBoolean(MESSAGE_SOUND_SWITCH, true);
    }

    public static String getPushConfig() {
        return CommonBase.getStringFromPreference(PUSH_CONFIG, "");
    }

    public static synchronized boolean isFastClick() {
        synchronized (MessageCommonUtils.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastClickTime < 500) {
                lastClickTime = currentTimeMillis;
                return true;
            }
            lastClickTime = currentTimeMillis;
            return false;
        }
    }

    public static boolean isStartJDService() {
        if (!TextUtils.isEmpty(getPushConfig())) {
            try {
                JSONObject jSONObject = new JSONObject(getPushConfig());
                return RomUtil.getDevice() == 1 ? new MIModel(new JSONObjectProxy(jSONObject.optJSONObject(MIModel.MI_MODEL))).isJdPush() : RomUtil.getDevice() == 2 ? new HWModel(new JSONObjectProxy(jSONObject.optJSONObject(HWModel.HW_MODEL))).isJdPush() : RomUtil.getDevice() == 3 ? new MZModel(new JSONObjectProxy(jSONObject.optJSONObject(MZModel.MZ_MODEL))).isJdPush() : RomUtil.getDevice() == 6 ? new OPPOModel(new JSONObjectProxy(jSONObject.optJSONObject(OPPOModel.OPPO_MODEL))).isJdPush() : new JDModel(new JSONObjectProxy(jSONObject.optJSONObject(JDModel.JD_MODEL))).isJdPush();
            } catch (Exception unused) {
            }
        }
        return true;
    }

    public static String makeDeviceToken(Context context) {
        return ("JD2" + (BaseInfo.getBoard().length() % 10) + (BaseInfo.getDeviceBrand().length() % 10) + (BaseInfo.getDeviceName().length() % 10) + (BaseInfo.getOSName().length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (BaseInfo.getDeviceManufacture().length() % 10) + (BaseInfo.getDeviceModel().length() % 10) + (BaseInfo.getDeviceProductName().length() % 10) + (BaseInfo.getOSVersionTags().length() % 10) + (BaseInfo.getOSVersionType().length() % 10) + (Build.USER.length() % 10)) + BaseInfo.getAndroidId();
    }

    public static void putMessageSoundSwitch(boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean(MESSAGE_SOUND_SWITCH, z).commit();
    }

    public static void recordOpenPushInfo(Context context, int i2, String str) {
    }

    public static void recordOpenPushInfo(Context context, int i2, String str, String str2, int i3, String str3, String str4) {
        try {
            JDPushApi jDPushApi = jdPushApi;
            if (jDPushApi != null) {
                jDPushApi.recordOpenPushInfo(context, i2, str, str2, i3, str3, str4);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static void registId(String str) {
        try {
            JDPushApi jDPushApi = jdPushApi;
            if (jDPushApi != null) {
                jDPushApi.checkRegId(str);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static synchronized void registPersonalMessageObserver(LoginBaseStateChange loginBaseStateChange) {
        synchronized (MessageCommonUtils.class) {
            if (loginBaseStateChange != null) {
                mloginBaseStateChange = loginBaseStateChange;
            }
        }
    }

    public static void registeredBusiness(int i2, Class<?> cls) {
        try {
            JDPushApi jDPushApi = jdPushApi;
            if (jDPushApi != null) {
                jDPushApi.registeredBusiness(i2, cls);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setBadgeNum(Context context, int i2) throws Exception {
        JDPushApi jDPushApi = jdPushApi;
        if (jDPushApi != null) {
            jDPushApi.setBadgeNum(context, i2);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "setBadgeNum:" + i2);
        }
    }

    public static void switchPushService(boolean z) {
    }

    public static void unBind() {
        SharedPreferencesUtil.putString("messageFirstCache", "");
        MessageTabRedCtrl.getInstance().showMsgRedPoint(0, 0);
        LoginBaseStateChange loginBaseStateChange = mloginBaseStateChange;
        if (loginBaseStateChange != null) {
            loginBaseStateChange.loginUserOut();
        }
        String pin = UserUtil.getWJLoginHelper().getPin();
        try {
            JDPushApi jDPushApi = jdPushApi;
            if (jDPushApi != null) {
                jDPushApi.unBindPin(pin);
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }
}
