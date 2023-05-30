package com.jingdong.common.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.MobileDeviceUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class MobileLoginUtil {
    private static final String CHINA_UNICOM_APPID = "99166000000000001050";
    private static final String CHINA_UNICOM_APPSECRET = "ed22001054d78268bc1367f5432e34bf";
    private static final String TAG = "WJLogin.MobileLoginUtil";
    private static int timeout = 5000;
    private static String unicomAccessCode;
    private static String unicomSecurityPhone;

    /* loaded from: classes5.dex */
    public static class BackWatcher implements BackForegroundWatcher.BackForegroundListener {
        private BackWatcher() {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
            MobileLoginUtil.preGetMobile(true);
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
        }
    }

    public static boolean canGoToTelecom() {
        return isOpenTelecomLogin() && getSimiState(JdSdk.getInstance().getApplication()) && getDefaultDataSubId(JdSdk.getInstance().getApplication()).intValue() != -1 && isTelecomOperateType(JdSdk.getInstance().getApplication()) && dataEnable(JdSdk.getInstance().getApplication());
    }

    public static boolean canGoToUnicom() {
        return CCFLoginUtil.isOpenUnicom() && getSimiState(JdSdk.getInstance().getApplication()) && getDefaultDataSubId(JdSdk.getInstance().getApplication()).intValue() != -1 && isUnicomOperateType(JdSdk.getInstance().getApplication()) && dataEnable(JdSdk.getInstance().getApplication());
    }

    public static boolean canGoToUnicomWithoutData() {
        return CCFLoginUtil.isOpenUnicom() && getSimiState(JdSdk.getInstance().getApplication()) && getDefaultDataSubId(JdSdk.getInstance().getApplication()).intValue() != -1 && isUnicomOperateType(JdSdk.getInstance().getApplication());
    }

    public static boolean checkPreGetMobile() {
        return (TextUtils.isEmpty(LoginConstans.ONEKEY_LOGIN_OPERATETYPE) || TextUtils.isEmpty(LoginConstans.ONEKEY_LOGIN_PHONENUMBER)) ? false : true;
    }

    public static boolean checkUnicomAccessCode() {
        return (TextUtils.isEmpty(unicomSecurityPhone) || TextUtils.isEmpty(unicomAccessCode)) ? false : true;
    }

    public static boolean dataEnable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    if (Log.D) {
                        Log.d(TAG, "WIFI");
                    }
                    boolean z = context.getPackageManager().checkPermission("android.permission.CHANGE_NETWORK_STATE", context.getPackageName()) == 0;
                    if (Log.D) {
                        Log.d(TAG, "CHANGE_NETWORK_STATE checkPermisson=" + z);
                    }
                    if (z && isMobileEnableReflex(connectivityManager)) {
                        if (Log.D) {
                            Log.d(TAG, "\u6d41\u91cf\u6570\u636e WIFI \u540c\u5f00");
                        }
                        return true;
                    }
                    return false;
                }
                if (type == 0) {
                    if (Log.D) {
                        Log.d(TAG, "\u6d41\u91cf");
                    }
                    NetworkInfo.State state = connectivityManager.getNetworkInfo(0).getState();
                    if (Log.D) {
                        Log.d(TAG, "TYPE_MOBILE State= " + state);
                    }
                    if (NetworkInfo.State.CONNECTED == state) {
                        if (Log.D) {
                            Log.d(TAG, "\u6d41\u91cf enable");
                        }
                        return true;
                    }
                }
                return false;
            }
            if (Log.D) {
                Log.d(TAG, "!networkInfo.isAvailable()");
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static AuthnHelper getAuthnHelperIfOpenMobileLogin() {
        return null;
    }

    @SuppressLint({"NewApi"})
    public static Integer getDefaultDataSubId(Context context) {
        Integer num = -1;
        try {
            if (Build.VERSION.SDK_INT >= 22) {
                SubscriptionManager from = SubscriptionManager.from(context.getApplicationContext());
                Method method = from.getClass().getMethod("getDefaultDataSubId", new Class[0]);
                if (method != null) {
                    num = Integer.valueOf(((Integer) method.invoke(from, new Object[0])).intValue());
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            try {
                try {
                    if (Build.VERSION.SDK_INT >= 22) {
                        SubscriptionManager from2 = SubscriptionManager.from(context.getApplicationContext());
                        Method method2 = from2.getClass().getMethod("getDefaultDataSubscrptionId", new Class[0]);
                        if (method2 != null) {
                            num = Integer.valueOf(((Integer) method2.invoke(from2, new Object[0])).intValue());
                        }
                    }
                } catch (IllegalAccessException unused) {
                    e3.printStackTrace();
                } catch (NoSuchMethodException unused2) {
                    if (Build.VERSION.SDK_INT >= 22) {
                        SubscriptionManager from3 = SubscriptionManager.from(context.getApplicationContext());
                        Method method3 = from3.getClass().getMethod("getDefaultDataPhoneId", new Class[0]);
                        if (method3 != null) {
                            num = Integer.valueOf(((Integer) method3.invoke(from3, new Object[0])).intValue());
                            Log.v("", ((Integer) method3.invoke(from3, new Object[0])).intValue() + "");
                        }
                    }
                } catch (InvocationTargetException unused3) {
                    e3.printStackTrace();
                } catch (Exception unused4) {
                    e3.printStackTrace();
                }
            } catch (IllegalAccessException unused5) {
                e3.printStackTrace();
            } catch (NoSuchMethodException unused6) {
                e3.printStackTrace();
            } catch (InvocationTargetException unused7) {
                e3.printStackTrace();
            } catch (Exception unused8) {
                e3.printStackTrace();
            }
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        if (Log.D) {
            Log.d(TAG, "getDefaultDataSubId id= " + num);
        }
        return num;
    }

    public static boolean getSimiState(Context context) {
        try {
            int simState = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimState();
            if (simState == 0) {
                if (Log.D) {
                    Log.d(TAG, "\u672a\u77e5\u72b6\u6001");
                    return false;
                }
                return false;
            } else if (simState == 1) {
                if (Log.D) {
                    Log.d(TAG, "\u65e0\u5361");
                    return false;
                }
                return false;
            } else if (simState == 2) {
                if (Log.D) {
                    Log.d(TAG, "\u9700\u8981PIN\u89e3\u9501");
                    return false;
                }
                return false;
            } else if (simState == 3) {
                if (Log.D) {
                    Log.d(TAG, "\u9700\u8981PUK\u89e3\u9501");
                    return false;
                }
                return false;
            } else if (simState == 4) {
                if (Log.D) {
                    Log.d(TAG, "\u9700\u8981NetworkPIN\u89e3\u9501");
                    return false;
                }
                return false;
            } else if (simState != 5) {
                return false;
            } else {
                if (Log.D) {
                    Log.d(TAG, "\u826f\u597d");
                }
                return true;
            }
        } catch (Exception unused) {
            if (Log.D) {
                Log.d(TAG, "\u53d6sim\u7684\u72b6\u6001\u5f02\u5e38");
                return false;
            }
            return false;
        }
    }

    public static String getUnicomAPPID() {
        return "99166000000000001050";
    }

    public static String getUnicomAPPSecret() {
        return "ed22001054d78268bc1367f5432e34bf";
    }

    public static String getUnicomAccessCode() {
        return unicomAccessCode;
    }

    public static String getUnicomSecurityPhone() {
        return unicomSecurityPhone;
    }

    public static void initAuthn(Context context) {
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
            registerBackToForeground();
            preGetMobile(false);
        }
    }

    public static boolean isCMOperateType(Context context) {
        String simOperator;
        try {
            simOperator = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperator();
            if (Log.D) {
                Log.e(TAG, " isCMOperateType NetworkOperator = " + simOperator);
            }
        } catch (Exception unused) {
            if (Log.D) {
                Log.e(TAG, "ismi =\u83b7\u53d6\u8fd0\u8425\u5546\u4fe1\u606f\u5f02\u5e38 ");
            }
        }
        if (TextUtils.isEmpty(simOperator)) {
            return false;
        }
        if (simOperator.startsWith("46000") || simOperator.startsWith("46002")) {
            return true;
        }
        return simOperator.startsWith("46007");
    }

    public static boolean isMobileEnableReflex(Context context) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity"), new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isOpenTelecomLogin() {
        boolean isOpenTelecomLoginSwitch = CCFLoginUtil.isOpenTelecomLoginSwitch();
        if (Log.D) {
            Log.d(TAG, "ChinaMobile telecomLoginSwitch " + isOpenTelecomLoginSwitch);
        }
        return isOpenTelecomLoginSwitch;
    }

    public static boolean isTelecomOperateType(Context context) {
        try {
            String simOperator = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperator();
            if (Log.D) {
                Log.e(TAG, "NetworkOperator = " + simOperator);
            }
            if (TextUtils.isEmpty(simOperator)) {
                return false;
            }
            if (simOperator.startsWith("46003") || simOperator.startsWith("46005")) {
                return true;
            }
            return simOperator.startsWith("46011");
        } catch (Exception unused) {
            if (Log.D) {
                Log.e(TAG, "ismi =\u83b7\u53d6\u8fd0\u8425\u5546\u4fe1\u606f\u5f02\u5e38 ");
            }
            return false;
        }
    }

    public static boolean isUnicomOperateType(Context context) {
        String simOperator;
        try {
            simOperator = ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperator();
            if (Log.D) {
                Log.e(TAG, "NetworkOperator = " + simOperator);
            }
        } catch (Exception unused) {
            if (Log.D) {
                Log.e(TAG, "ismi =\u83b7\u53d6\u8fd0\u8425\u5546\u4fe1\u606f\u5f02\u5e38 ");
            }
        }
        if (TextUtils.isEmpty(simOperator)) {
            return false;
        }
        if (simOperator.startsWith("46001") || simOperator.startsWith("46006")) {
            return true;
        }
        return simOperator.startsWith("46009");
    }

    public static void preGetMobile(boolean z) {
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication()) && !LoginUserBase.hasLogin()) {
            if (z && checkPreGetMobile()) {
                return;
            }
            boolean canGoToTelecom = canGoToTelecom();
            boolean z2 = CCFLoginUtil.isOpenChinaMobileLoginSwitch() && "CM".equals(MobileDeviceUtil.getOperateType(JdSdk.getInstance().getApplication()));
            boolean canGoToUnicom = canGoToUnicom();
            if (Log.D) {
                Log.d(TAG, " telecomLoginSwitch=" + canGoToTelecom + "openChinaMobileLoginSwitch=" + z2 + "cuLoginSwitch =" + canGoToUnicom);
            }
            if (z2 || canGoToTelecom || canGoToUnicom) {
                preGetMobile((OnPreGetMobileResponseCallback) null);
            }
        }
    }

    public static void preGetMobileForActivation(OnPreGetMobileResponseCallback onPreGetMobileResponseCallback) {
        if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
            onPreGetMobileResponseCallback.onFail(setResult(1));
        } else if (LoginUserBase.hasLogin()) {
            onPreGetMobileResponseCallback.onFail(setResult(2));
        } else {
            boolean canGoToTelecom = canGoToTelecom();
            boolean z = CCFLoginUtil.isOpenChinaMobileLoginSwitch() && "CM".equals(MobileDeviceUtil.getOperateType(JdSdk.getInstance().getApplication()));
            boolean canGoToUnicom = canGoToUnicom();
            if (!z && !canGoToTelecom && !canGoToUnicom) {
                onPreGetMobileResponseCallback.onFail(setResult(3));
            } else {
                preGetMobile(onPreGetMobileResponseCallback);
            }
        }
    }

    public static void registerBackToForeground() {
        BackForegroundWatcher.getInstance().registerListener(new BackWatcher());
    }

    private static void reportPhoneNumber(Context context, String str) {
        String string = SharedPreferencesUtil.getString("securityPhone", "");
        if (Log.D) {
            Log.d(TAG, "ChinaMobile securityPhone == " + string + "; phoneNum == " + str);
        }
        if (TextUtils.equals(string, str)) {
            return;
        }
        String pin = UserUtil.getWJLoginHelper().getPin();
        String readDeviceUUID = StatisticsReportUtil.readDeviceUUID();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("devicecode", readDeviceUUID);
            jSONObject.put("eventid", "getPhoneNumber");
            jSONObject.put("uid", pin);
            try {
                JDLocation cacheLocation = LoginLocationUtil.getCacheLocation();
                jSONObject.put(JDMtaUtils.LON, cacheLocation.getLng() + "");
                jSONObject.put("lat", cacheLocation.getLat() + "");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            jSONObject.put("phoneNumber", str);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        JMA.report(context, jSONObject);
        SharedPreferencesUtil.putString("securityPhone", str);
    }

    public static JSONObject setResult(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (i2 == 1) {
            jSONObject.put("resultCode", LoginConstans.KEY_PREGETMOBILE_HASNOTACCENTPRIVACY);
            jSONObject.put("msg", LoginConstans.VALUE_PREGETMOBILE_HASNOTACCENTPRIVACY);
        } else if (i2 == 2) {
            jSONObject.put("resultCode", LoginConstans.KEY_PREGETMOBILE_HASLOGIN);
            jSONObject.put("msg", LoginConstans.VALUE_PREGETMOBILE_HASHLOGIN);
        } else if (i2 != 3) {
            if (i2 == 5) {
                jSONObject.put("resultCode", LoginConstans.KEY_PREGETMOBILE_RESULT_NULL);
                jSONObject.put("msg", LoginConstans.VALUE_PREGETMOBILE_RESULT_NULL);
            }
            return jSONObject;
        } else {
            jSONObject.put("resultCode", LoginConstans.KEY_PREGETMOBILE_SWITCHCLOSED);
            jSONObject.put("msg", LoginConstans.VALUE_PREGETMOBILE_SWITCHCLOSED);
        }
        return jSONObject;
    }

    public static boolean isMobileEnableReflex(ConnectivityManager connectivityManager) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static void preGetMobile(final OnPreGetMobileResponseCallback onPreGetMobileResponseCallback) {
        try {
            System.currentTimeMillis();
            UserUtil.getOneKeyLoginHelper().preGetMobile(new OnResponseCallback
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: INVOKE 
                  (wrap: com.wjlogin.onekey.sdk.common.OneKeyLoginHelper : 0x0004: INVOKE  type: STATIC call: com.jingdong.common.utils.UserUtil.getOneKeyLoginHelper():com.wjlogin.onekey.sdk.common.OneKeyLoginHelper A[Catch: Exception -> 0x0011, MD:():com.wjlogin.onekey.sdk.common.OneKeyLoginHelper (m), WRAPPED] (LINE:11))
                  (wrap: com.wjlogin.onekey.sdk.common.listener.OnResponseCallback : 0x000a: CONSTRUCTOR 
                  (r4v0 'onPreGetMobileResponseCallback' com.jingdong.common.login.OnPreGetMobileResponseCallback A[DONT_INLINE])
                  (r0 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[Catch: Exception -> 0x0011, MD:(com.jingdong.common.login.OnPreGetMobileResponseCallback, long):void (m), WRAPPED] call: com.jingdong.common.login.MobileLoginUtil.1.<init>(com.jingdong.common.login.OnPreGetMobileResponseCallback, long):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.wjlogin.onekey.sdk.common.OneKeyLoginHelper.preGetMobile(com.wjlogin.onekey.sdk.common.listener.OnResponseCallback):void A[Catch: Exception -> 0x0011, MD:(com.wjlogin.onekey.sdk.common.listener.OnResponseCallback):void (m), TRY_LEAVE] (LINE:11) in method: com.jingdong.common.login.MobileLoginUtil.preGetMobile(com.jingdong.common.login.OnPreGetMobileResponseCallback):void, file: classes5.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L11
                com.wjlogin.onekey.sdk.common.OneKeyLoginHelper r2 = com.jingdong.common.utils.UserUtil.getOneKeyLoginHelper()     // Catch: java.lang.Exception -> L11
                com.jingdong.common.login.MobileLoginUtil$1 r3 = new com.jingdong.common.login.MobileLoginUtil$1     // Catch: java.lang.Exception -> L11
                r3.<init>()     // Catch: java.lang.Exception -> L11
                r2.preGetMobile(r3)     // Catch: java.lang.Exception -> L11
                goto L15
            L11:
                r4 = move-exception
                r4.printStackTrace()
            L15:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.login.MobileLoginUtil.preGetMobile(com.jingdong.common.login.OnPreGetMobileResponseCallback):void");
        }
    }
