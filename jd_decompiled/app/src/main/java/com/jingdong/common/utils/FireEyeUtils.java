package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.fireeye.security.FireEyeBaseData;
import com.jd.fireeye.security.FireEyeInit;
import com.jd.fireeye.security.fireeye.DeepLinkFireEyeCallback;
import com.jd.fireeye.security.fireeye.FireEye;
import com.jd.fireeye.security.fireeye.IForegroundCheck;
import com.jd.fireeye.security.fireeye.IMtaUtils;
import com.jingdong.app.mall.global.PasteStateRouterImpl;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.MobileLoginUtil;
import com.jingdong.common.login.OnPreGetMobileResponseCallback;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.permission.ClipBoardPermissionHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.connect.common.Constants;
import java.security.MessageDigest;
import java.util.HashMap;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FireEyeUtils {
    private static final String JMA_SP_FILE = "jma_sp_file";
    public static int STATE = 100;
    public static final int STATE_DELAY = 103;
    public static final int STATE_FAIL = 102;
    public static final int STATE_INIT = 100;
    public static final int STATE_SUCCESS = 101;
    private static final String TAG = "FireEyeUtils";
    public static boolean isFromOpenAPP;
    private static boolean isWaitOaidCallback;
    public static boolean mClipSwitch;
    private static Context mContext;
    public static String appKey = "1b41a7042ce724d9ecaa5a15fe9fab7a";
    public static String spPhoneNumber = appKey + "pn";
    private static Handler handler = new Handler(Looper.getMainLooper());

    static /* synthetic */ boolean access$100() {
        return isSwitchOpen();
    }

    static /* synthetic */ String access$500() {
        return getGisInfo();
    }

    private static boolean getClipBoardSwitch() {
        return PasteStateRouterImpl.getClipPasteStatusValue();
    }

    private static String getGisInfo() {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId("81df1cd53a83445ad64f0c622d2b224a");
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        return location.getLng() + CartConstant.KEY_YB_INFO_LINK + location.getLat();
    }

    private static String getMD5(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void getPhoneNumber(final phoneNumberCallback phonenumbercallback) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.jingdong.common.utils.FireEyeUtils.9
            @Override // java.lang.Runnable
            public void run() {
                int i2 = FireEyeUtils.STATE;
                if (i2 == 101 || i2 == 102) {
                    return;
                }
                FireEyeUtils.STATE = 103;
                if (OKLog.D) {
                    OKLog.d(FireEyeUtils.TAG, "getPhoneNumber \u8d85\u65f6");
                }
                phoneNumberCallback.this.onResult("");
            }
        }, 1000L);
        MobileLoginUtil.preGetMobileForActivation(new OnPreGetMobileResponseCallback() { // from class: com.jingdong.common.utils.FireEyeUtils.10
            @Override // com.jingdong.common.login.OnPreGetMobileResponseCallback
            public void onFail(JSONObject jSONObject) {
                if (FireEyeUtils.STATE == 100) {
                    FireEyeUtils.STATE = 102;
                    if (OKLog.D && jSONObject != null) {
                        OKLog.d(FireEyeUtils.TAG, "getPhoneNumber onFail" + jSONObject.toString());
                    }
                    phoneNumberCallback.this.onResult("");
                }
            }

            @Override // com.jingdong.common.login.OnPreGetMobileResponseCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    String optString = jSONObject.optString("securityPhone");
                    if (OKLog.D) {
                        OKLog.d(FireEyeUtils.TAG, "getPhoneNumber onSuccess" + optString);
                    }
                    if (FireEyeUtils.STATE == 100) {
                        FireEyeUtils.STATE = 101;
                        phoneNumberCallback.this.onResult(optString);
                    }
                }
            }
        });
    }

    private static String h5Switch() {
        return SwitchQueryFetcher.getSwitchStringValue("jdfireH5TimeOut", Constants.DEFAULT_UIN);
    }

    public static void initFireEyeTrack() {
        String property = Configuration.getProperty(Configuration.UNION_ID);
        String property2 = Configuration.getProperty(Configuration.PARTNER);
        String property3 = Configuration.getProperty(Configuration.SUB_UNION_ID);
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        if (OKLog.I) {
            OKLog.i(TAG, "initFireEyeTrack");
        }
        FireEyeInit.init(JdSdk.getInstance().getApplication(), new FireEyeBaseData.TrackBaseDataBuilder().partner(property2).deviceCode(readDeviceUUID).subunionId(property3).unionId(property).appKey(appKey).publicKey(JdSdk.getInstance().getBuildConfigDebug() ? "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcAGh/ed8ITFxUYfBNmzddolj+7SEMNjQ63EOGPTV8rTXpQNGttVEYm6vJI6ADrRkbKNXnY9YerDRQkFQVzGm2ha30cpDcI24yNQ/87brAJ2VgUEltuqqHxwvJMO4HwuhYhheHchSYekphBs6Uz+1IF9XT2K7FMEkeFGGeMVlGzQIDAQAB" : "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBevvGib/8dSPwODgYOGs+v9fD1TWHnGgpdzELpIXsX2eBo0eGFBsWmb9hocDBq6o+sn+v1rqnnUsQr/fMbNXcKCbkhloZ0WVfh8/CXwJJEMq5A8D+rxeSWpcYN3Ykm62CYZfbpcdVoq/pGmFbwKyUbtJ2PsYV0Ax6Y8chMeVxpQIDAQAB").installtionid(StatisticsReportUtil.readInstallationId()).oaId(BaseInfo.getOAID()).mtaSwitch(mtaSwitch()).appSwitch(needSwitch()).h5Switch(h5Switch()).clipSwitch(true).iMtaUtils(new IMtaUtils() { // from class: com.jingdong.common.utils.FireEyeUtils.1
            @Override // com.jd.fireeye.security.fireeye.IMtaUtils
            public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
                if (TextUtils.equals(str, "FireEye_SDK_PasteBoard_Network_Start")) {
                    OpenLinkTimeManager.getInstance().addExtraTiming("FireEye_SDK_PasteBoard_Network_Start", System.currentTimeMillis());
                } else if (TextUtils.equals(str, "FireEye_SDK_PasteBoard_Network_End")) {
                    OpenLinkTimeManager.getInstance().addExtraTiming("FireEye_SDK_PasteBoard_Network_End", System.currentTimeMillis());
                } else {
                    JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, str8, hashMap);
                }
            }
        }).build(), JdSdk.getInstance().getBuildConfigDebug(), JdSdk.getInstance().getBuildConfigDebug());
        FireEyeInit.setForegroundCheck(new IForegroundCheck() { // from class: com.jingdong.common.utils.FireEyeUtils.2
            @Override // com.jd.fireeye.security.fireeye.IForegroundCheck
            public boolean isAppForeground() {
                return BackForegroundWatcher.getInstance().isAppInitStateOrForeground();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.in");
        JdSdk.getInstance().getApplication().registerReceiver(new LoginReceiver(), intentFilter);
    }

    private static boolean isSwitchOpen() {
        String deviceBrand = BaseInfo.getDeviceBrand();
        return SwitchQueryFetcher.getSwitchBooleanValue("oppoReportSwitch", false) && !TextUtils.isEmpty(deviceBrand) && TextUtils.equals("OPPO", deviceBrand.toUpperCase());
    }

    private static boolean mtaSwitch() {
        return !SwitchQueryFetcher.getSwitchBooleanValue("jdfireSwitchMta", false);
    }

    private static boolean needSwitch() {
        return !SwitchQueryFetcher.getSwitchBooleanValue("jdfireNeedSwitch", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportFireEye(final boolean z, final boolean z2) {
        getPhoneNumber(new phoneNumberCallback() { // from class: com.jingdong.common.utils.FireEyeUtils.5
            @Override // com.jingdong.common.utils.phoneNumberCallback
            public void onResult(String str) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("phoneNumber", str);
                    jSONObject.put("devicecode", StatisticsReportUtil.readDeviceUUID());
                    jSONObject.put("gisinfo", FireEyeUtils.access$500());
                    jSONObject.put("isFromOpenApp", z2);
                    jSONObject.put("oaId", BaseInfo.getOAID());
                    jSONObject.put("clipSwitch", z);
                    jSONObject.put("isAgreePrivacy", JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()));
                    boolean unused = FireEyeUtils.isWaitOaidCallback = false;
                    FireEye.reportFireEye(jSONObject, new DeepLinkFireEyeCallback() { // from class: com.jingdong.common.utils.FireEyeUtils.5.1
                        @Override // com.jd.fireeye.security.fireeye.FireEyeCallback
                        public void onFail() {
                        }

                        @Override // com.jd.fireeye.security.fireeye.DeepLinkFireEyeCallback
                        public void onSuccess(JSONObject jSONObject2) {
                            if (z2 || jSONObject2 == null) {
                                return;
                            }
                            String str2 = "openapp is " + jSONObject2.optString("openapp");
                            String optString = jSONObject2.optString("openapp");
                            boolean optBoolean = jSONObject2.optBoolean("openappSwitch", false);
                            if (TextUtils.isEmpty(optString)) {
                                return;
                            }
                            if (FireEyeUtils.mContext != null && optBoolean) {
                                OpenAppJumpController.dispatchJumpRequestInApp(FireEyeUtils.mContext, Uri.parse(optString));
                            } else {
                                new OpenAppJumpBuilder.Builder(Uri.parse(optString)).build().jump(JdSdk.getInstance().getApplication());
                            }
                        }
                    });
                } catch (JSONException e2) {
                    OKLog.e(FireEyeUtils.TAG, e2);
                    String str2 = "exception is" + e2.toString();
                }
            }
        });
    }

    public static void reportFireEyeEvent(String str, boolean z) {
        reportFireEyeEvent(str, "", z);
    }

    public static void reportFireEyeS(Context context, final boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportFireEyeS");
        }
        STATE = 100;
        if (JdSdk.getInstance().getApplicationContext().getSharedPreferences(JMA_SP_FILE, 0).getBoolean(appKey, false)) {
            getPhoneNumber(new phoneNumberCallback() { // from class: com.jingdong.common.utils.FireEyeUtils.3
                @Override // com.jingdong.common.utils.phoneNumberCallback
                public void onResult(String str) {
                    FireEyeUtils.reportPhoneNumberEvent(str, z);
                }
            });
        } else if ((context instanceof Activity) && !z) {
            mContext = context;
            getClipBoardSwitch(context, new ClipBoardCallback() { // from class: com.jingdong.common.utils.FireEyeUtils.4
                @Override // com.jingdong.common.utils.ClipBoardCallback
                public void onResult(boolean z2) {
                    FireEyeUtils.mClipSwitch = z2;
                    if (!TextUtils.isEmpty(BaseInfo.getOAID()) || !FireEyeUtils.access$100()) {
                        FireEyeUtils.reportFireEye(z2, z);
                        return;
                    }
                    boolean unused = FireEyeUtils.isWaitOaidCallback = true;
                    FireEyeUtils.reportOaidDelay(z2, z);
                }
            });
        } else if (TextUtils.isEmpty(BaseInfo.getOAID()) && isSwitchOpen()) {
            isWaitOaidCallback = true;
            reportOaidDelay(mClipSwitch, z);
        } else {
            reportFireEye(mClipSwitch, z);
        }
    }

    public static void reportOaidCallback() {
        if (isWaitOaidCallback && isSwitchOpen()) {
            if (JdSdk.getInstance().getApplicationContext().getSharedPreferences(JMA_SP_FILE, 0).getBoolean(appKey, false)) {
                getPhoneNumber(new phoneNumberCallback() { // from class: com.jingdong.common.utils.FireEyeUtils.6
                    @Override // com.jingdong.common.utils.phoneNumberCallback
                    public void onResult(String str) {
                        FireEyeUtils.reportPhoneNumberEvent(str, false);
                    }
                });
            } else {
                reportFireEye(mClipSwitch, isFromOpenAPP);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportOaidDelay(final boolean z, final boolean z2) {
        if (!TextUtils.isEmpty(BaseInfo.getOAID())) {
            reportFireEye(z, z2);
        } else if (handler == null || !isSwitchOpen()) {
        } else {
            handler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.FireEyeUtils.7
                @Override // java.lang.Runnable
                public void run() {
                    if (FireEyeUtils.access$100()) {
                        FireEyeUtils.reportFireEye(z, z2);
                    }
                }
            }, Final.FIVE_SECOND);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportPhoneNumberEvent(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z2 = false;
        String string = JdSdk.getInstance().getApplicationContext().getSharedPreferences(JMA_SP_FILE, 0).getString(spPhoneNumber, "");
        String md5 = getMD5(str);
        String[] split = string.split(DYConstants.DY_REGEX_COMMA);
        int i2 = 0;
        while (true) {
            if (i2 >= split.length) {
                break;
            } else if (TextUtils.equals(split[i2], md5)) {
                z2 = true;
                break;
            } else {
                i2++;
            }
        }
        if (z2) {
            return;
        }
        reportFireEyeEvent("getPhoneNumber", str, z);
    }

    private static void getClipBoardSwitch(Context context, final ClipBoardCallback clipBoardCallback) {
        ClipBoardPermissionHelper.handleClipBoardPermissionDialog(context, "\u4eac\u53e3\u4ee4", "\u526a\u5207\u677f\u6743\u9650\u7533\u8bf7", "\u4eac\u4e1c\u7533\u8bf7\u83b7\u53d6\u60a8\u7684\u526a\u5207\u677f\u5185\u5bb9\uff0c\u4ee5\u8bfb\u53d6\u4eac\u53e3\u4ee4\uff0c\u62d2\u7edd\u4e0d\u5f71\u54cd\u4f7f\u7528\u5176\u4ed6\u670d\u52a1\u3002", new ClipBoardPermissionHelper.ClipBoardPermissionResultCallBack() { // from class: com.jingdong.common.utils.FireEyeUtils.8
            @Override // com.jingdong.common.permission.ClipBoardPermissionHelper.ClipBoardPermissionResultCallBack
            public void onDenied() {
                if (OKLog.D) {
                    OKLog.d(FireEyeUtils.TAG, "onDenied");
                }
                ClipBoardCallback.this.onResult(false);
                super.onDenied();
            }

            @Override // com.jingdong.common.permission.ClipBoardPermissionHelper.ClipBoardPermissionResultCallBack
            public void onGranted() {
                if (OKLog.D) {
                    OKLog.d(FireEyeUtils.TAG, "onGranted");
                }
                ClipBoardCallback.this.onResult(true);
                super.onGranted();
            }
        });
    }

    public static void reportFireEyeEvent(String str, String str2, boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "reportFireEyeEvent");
        }
        isFromOpenAPP = z;
        JSONObject jSONObject = new JSONObject();
        String str3 = "reportFireEyeEvent ==event=" + str;
        try {
            jSONObject.put("devicecode", StatisticsReportUtil.readDeviceUUID());
            jSONObject.put("isFromOpenApp", z);
            jSONObject.put("oaId", BaseInfo.getOAID());
            jSONObject.put("eventNumber", str);
            jSONObject.put("gisinfo", getGisInfo());
            jSONObject.put("pin", LoginUserBase.getUserPin());
            jSONObject.put("phoneNumber", str2);
            jSONObject.put("isPhoneEvent", !TextUtils.isEmpty(str2));
            jSONObject.put("isAgreePrivacy", JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()));
            FireEye.reportFireEyeEvent(jSONObject);
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
            String str4 = "exception is" + e2.toString();
        }
    }
}
