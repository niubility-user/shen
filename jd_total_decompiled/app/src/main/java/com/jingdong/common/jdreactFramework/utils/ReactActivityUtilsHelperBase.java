package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginLocalDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes5.dex */
public class ReactActivityUtilsHelperBase {
    private static final String TAG = "ReactActivityUtilsHelperBase";
    private static String mCommonActivityName = "com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity";
    private static String mPackageName = "com.jingdong.app.mall";

    public static String getCommonActivityName() {
        return mCommonActivityName;
    }

    public static String getPackageName() {
        return mPackageName;
    }

    private static boolean intentNeedLogin(Intent intent) {
        Bundle extras;
        if (intent == null || !intent.hasExtra(VKEventUtil.JUMP_NEEDLOGIN) || (extras = intent.getExtras()) == null) {
            return false;
        }
        Object obj = extras.get(VKEventUtil.JUMP_NEEDLOGIN);
        if (obj instanceof String) {
            return DYConstants.DY_TRUE.equals(obj) || "1".equals(obj);
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void performStartReactActivity(Context context, Class<?> cls, Intent intent, String str, String str2, boolean z) {
        String stringExtra = intent.getStringExtra("pluginName");
        if (TextUtils.isEmpty(stringExtra)) {
            TrackUtil.trackLoadFail(9, stringExtra);
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            TrackUtil.trackLoadFail(11, stringExtra);
            e2.printStackTrace();
        }
    }

    public static void setCommonActivityName(String str) {
        mCommonActivityName = str;
    }

    public static void setPackageName(String str) {
        mPackageName = str;
    }

    public static synchronized void startClassPluginActivity(Context context, Class<?> cls, Intent intent, String str) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            JDReactHelper.newInstance().getTrackListener();
            try {
                try {
                    intent.putExtra("pluginName", str);
                    if (!ReactModuleAvailabilityUtils.getModuleAvailability(str)) {
                        whenUnavailable(context, cls, intent, str);
                        TrackUtil.trackLoadFail(8, str);
                    } else if (TextUtils.isEmpty(str)) {
                        whenUnavailable(context, cls, intent, str);
                        TrackUtil.trackLoadFail(9, str);
                    } else {
                        String str2 = ReactSharedPreferenceUtils.getDownLoadingStatus(str).status;
                        String string = intent.getExtras().getString("downloadpath", "");
                        String str3 = TAG;
                        JLog.d(str3, "The plugin download path is " + string);
                        Uri parse = Uri.parse(string);
                        if (!TextUtils.isEmpty(string) && TextUtils.equals(parse.getHost(), "storage.jd.com")) {
                            JLog.d(str3, "start to load the page");
                            File file = new File(JDReactConstant.ReactDownloadPath.getAbsolutePath() + File.separator + str);
                            if (file.exists()) {
                                ReactNativeUpdate.delete(file);
                                ReactSharedPreferenceUtils.reverseCurBakSP(true, str);
                                ReactSharedPreferenceUtils.reverseCurBakSP(false, str);
                            }
                            PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
                            PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                            pluginUpdateInfo.pluginUpdateName = str;
                            pluginUpdateInfo.pluginUpdateVersion = "0.0";
                            pluginUpdateInfo.pluginDownloadUrl = string;
                            pluginUpdateInfo.realDownloadUrl = string;
                            pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                            pluginUpdateInfo.downType = 0;
                            pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
                            ReactNativeUpdateManager.getInstance().addForceDownloadTask(pluginDownloadInfo);
                            intent.putExtra("force_download_mode", true);
                        }
                        startReactActivity(context, cls, intent, str, str2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Exception unused) {
                whenUnavailable(context, cls, intent, str);
                TrackUtil.trackLoadFail(11, str);
            }
        }
    }

    public static synchronized void startJDReactActivity(Context context, Intent intent, Bundle bundle) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            intent.putExtras(bundle);
            startClassPluginActivity(context, null, intent, bundle.getString(JDReactConstant.IntentConstant.MODULE_NAME));
        }
    }

    public static void startJDReactActivityForDebug(Activity activity, String str, String str2, String str3) {
        ComponentName componentName = new ComponentName(getPackageName(), getCommonActivityName());
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra(JDReactConstant.IntentConstant.MODULE_NAME, str);
        intent.putExtra("appname", str);
        intent.putExtra("pluginName", str);
        intent.putExtra("param", str2);
        intent.putExtra("destBundlePath", str3);
        intent.putExtra("localDebug", TextUtils.isEmpty(str3));
        activity.startActivity(intent);
    }

    public static synchronized void startJDReactCommonActivity(Context context, Bundle bundle) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(getPackageName(), getCommonActivityName()));
            intent.putExtras(bundle);
            startClassPluginActivity(context, null, intent, bundle.getString(JDReactConstant.IntentConstant.MODULE_NAME));
        }
    }

    public static synchronized void startJDReactCommonModule(Context context, String str) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(str)) {
                str = JumpUtils.getJumpProtocalHeader() + "://virtual?params={\"category\":\"jump\",\"des\":\"" + JumpUtils.getJumpDes() + "\",\"modulename\":\"JDReactTest1Android\",\"appname\":\"MovieHomePage\",\"title\":\"bbbbbbbbbb\",\"param\":{\"sourceValue\":\"download\"}}";
            }
            intent.setData(Uri.parse(str));
            JDReactHelper.newInstance().jumpWithOpenapp(str, context);
        }
    }

    public static synchronized void startJDReactCommonPage(Context context, String str, Bundle bundle) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JLog.e(TAG, "test log " + str);
            Intent intent = new Intent();
            String unableAnimationKey = JDReactHelper.newInstance().getUnableAnimationKey();
            int i2 = 0;
            if (bundle != null && unableAnimationKey != null) {
                intent.putExtra(unableAnimationKey, bundle.getBoolean(unableAnimationKey, false));
            }
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("jdreactkey");
            String queryParameter2 = parse.getQueryParameter("jdreactapp");
            String queryParameter3 = parse.getQueryParameter("jdreactparam");
            if (TextUtils.equals("storage.jd.com", parse.getHost())) {
                intent.setComponent(new ComponentName(getPackageName(), getCommonActivityName()));
                if (queryParameter != null) {
                    str = str.replaceAll("jdreactkey=" + queryParameter, "");
                }
                if (queryParameter2 != null) {
                    str = str.replaceAll("app_name=" + queryParameter2, "");
                }
                intent.putExtra("downloadpath", str);
                PluginLocalDownloadInfo downLoadingStatus = ReactSharedPreferenceUtils.getDownLoadingStatus(queryParameter);
                if (TextUtils.isEmpty(queryParameter2)) {
                    queryParameter2 = queryParameter;
                }
                intent.putExtra(JDReactConstant.IntentConstant.MODULE_NAME, queryParameter);
                intent.putExtra("appname", queryParameter2);
                intent.putExtra("ishidden", true);
                intent.putExtra("pluginName", queryParameter);
                if (!TextUtils.isEmpty(str)) {
                    File file = new File(JDReactConstant.ReactDownloadPath.getAbsolutePath() + File.separator + queryParameter);
                    if (file.exists()) {
                        ReactNativeUpdate.delete(file);
                        ReactSharedPreferenceUtils.reverseCurBakSP(true, queryParameter);
                        ReactSharedPreferenceUtils.reverseCurBakSP(false, queryParameter);
                    }
                    PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
                    PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                    pluginUpdateInfo.pluginUpdateName = queryParameter;
                    pluginUpdateInfo.pluginUpdateVersion = "0.0";
                    pluginUpdateInfo.pluginDownloadUrl = str;
                    pluginUpdateInfo.realDownloadUrl = str;
                    pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                    pluginUpdateInfo.downType = 0;
                    pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
                    ReactNativeUpdateManager.getInstance().addForceDownloadTask(pluginDownloadInfo);
                    intent.putExtra("force_download_mode", true);
                    startReactActivity(context, null, intent, queryParameter, downLoadingStatus.status);
                }
                return;
            }
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            if (TextUtils.isEmpty(queryParameter)) {
                JDReactHelper.newInstance().jumptoWebPage(context, str.replaceAll("jdreactkey", ""), intent);
                return;
            }
            if (TextUtils.isEmpty(queryParameter2)) {
                queryParameter2 = queryParameter;
            }
            if (TextUtils.isEmpty(queryParameter3)) {
                String str2 = "{";
                for (String str3 : parse.getQueryParameterNames()) {
                    if (!TextUtils.isEmpty(str3)) {
                        String substring = str3.startsWith("rn_") ? str3.substring(3, str3.length()) : str3;
                        if (!TextUtils.isEmpty(str3) && !str3.equals("jdreactkey") && !str3.equals("jdreactapp")) {
                            if (i2 != 0) {
                                str2 = str2 + DYConstants.DY_REGEX_COMMA;
                            }
                            i2++;
                            str2 = (parse.getQueryParameter(str3) != null && parse.getQueryParameter(str3).contains("[") && parse.getQueryParameter(str3).contains("]")) ? str2 + "\"" + substring + "\":" + parse.getQueryParameter(str3) : str2 + "\"" + substring + "\":\"" + parse.getQueryParameter(str3) + "\"";
                        }
                    }
                }
                queryParameter3 = str2 + "}";
            }
            String str4 = JumpUtils.getJumpProtocalHeader() + "://virtual?params={\"category\":\"jump\",\"des\":\"" + JumpUtils.getJumpDes() + "\",\"modulename\":\"" + queryParameter + "\",\"appname\":\"" + queryParameter2 + "\",\"ishidden\":true,\"param\":" + queryParameter3 + ",\"h5params\":" + queryParameter3 + "}";
            intent.setData(Uri.parse(str4));
            JDReactHelper.newInstance().jumpWithOpenapp(str4, context);
        }
    }

    public static synchronized void startJDReactNativeFaxianActivity(Context context, String str) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            if (TextUtils.isEmpty(str)) {
                JDReactHelper.newInstance().showShortToast("\u4e1a\u52a1\u4e0d\u5b58\u5728");
                return;
            }
            Intent intent = new Intent();
            String str2 = JumpUtils.getJumpProtocalHeader() + "://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"" + JDReactConstant.AVAILABILITY_FAXIAN + "\",\"appname\":\"" + JDReactConstant.AVAILABILITY_FAXIAN + "\",\"ishidden\":true,\"param\":{" + str + "}}";
            intent.setData(Uri.parse(str2));
            JDReactHelper.newInstance().jumpWithOpenapp(str2, context);
        }
    }

    private static void startReactActivity(Context context, Class<?> cls, Intent intent, String str, String str2) {
        startReactActivity(context, cls, intent, str, str2, false);
    }

    public static synchronized void whenUnavailable(Context context, Class<?> cls, Intent intent, String str) {
        synchronized (ReactActivityUtilsHelperBase.class) {
            if (JDReactConstant.AVAILABILITY_MOVIE.equals(str)) {
                intent.putExtra(Constant.KEY_PROMOTION_AVAILABLE, false);
                JDReactHelper.newInstance().startActivityInFrameWithNoNavigation(intent, context);
                return;
            }
            String moduleBackupUrl = ReactModuleAvailabilityUtils.getModuleBackupUrl(str, intent == null ? "" : intent.getStringExtra(JDReactConstant.IntentConstant.BACKUP_URL));
            if (!TextUtils.isEmpty(moduleBackupUrl)) {
                if (JDReactConstant.AVAILABILITY_FLIGHTORDERDETAIL.equals(str)) {
                    String stringExtra = intent.getStringExtra("orderId");
                    String stringExtra2 = intent.getStringExtra("type");
                    if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                        moduleBackupUrl = moduleBackupUrl + "?orderId=" + stringExtra + "&businessType=" + stringExtra2;
                    } else {
                        JDReactHelper.newInstance().showLongToast("\u4e1a\u52a1\u6682\u65f6\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
                        return;
                    }
                }
                if (JDReactConstant.AVAILABILITY_PAYSUCCESS.equals(str)) {
                    String stringExtra3 = intent.getStringExtra("orderId");
                    String stringExtra4 = intent.getStringExtra("orderPrice");
                    if (!TextUtils.isEmpty(stringExtra3) && !TextUtils.isEmpty(stringExtra4)) {
                        moduleBackupUrl = "https://newcz.m.jd.com/payback.html?orderId=" + stringExtra3 + "&orderPrice=" + stringExtra4;
                    } else {
                        JDReactHelper.newInstance().showLongToast("\u4e1a\u52a1\u6682\u65f6\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
                        return;
                    }
                }
                JDReactHelper.newInstance().jumptoWebPage(context, ReactModuleAvailabilityUtils.appendParamsToUrl(moduleBackupUrl, intent == null ? null : intent.getStringExtra("h5params")), intent);
            } else {
                JDReactHelper.newInstance().showLongToast("\u4e1a\u52a1\u6682\u65f6\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            }
        }
    }

    private static void startReactActivity(final Context context, final Class<?> cls, final Intent intent, final String str, final String str2, final boolean z) {
        if (TextUtils.isEmpty(intent.getStringExtra("pluginName"))) {
            return;
        }
        if (intentNeedLogin(intent) && JDReactHelper.newInstance().launchLogin(context, new JDReactHelper.JDReactLoginCallback() { // from class: com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase.1
            @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactLoginCallback
            public void onSuccess(String str3) {
                ReactActivityUtilsHelperBase.performStartReactActivity(context, cls, intent, str, str2, z);
            }
        })) {
            return;
        }
        performStartReactActivity(context, cls, intent, str, str2, z);
    }
}
