package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.collection.ArrayMap;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.jdreactFramework.helper.ElderModeHelper;
import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeLBSListener;
import com.jingdong.common.jdreactFramework.track.RenderDataReportForEmbedded;
import com.jingdong.common.jdreactFramework.utils.JDReactElderModeHelper;
import com.jingdong.common.jdreactFramework.utils.JDReactShowErrorViewUtils;
import com.jingdong.common.jdreactFramework.utils.JDReactUIModeHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.common.jdreactFramework.utils.apm.ApmExceptionReporter;
import com.jingdong.common.jdreactFramework.utils.apm.JDCrashReporter;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.LottieLoadingView;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactExtendHelperCallback implements JDReactHelper.JDReactHelperCallback {
    private static final String LOGIN_TAG = "JDReactLoginTag";

    /* loaded from: classes5.dex */
    public interface LocationCallBack {
        void onSuc(HashMap<String, Object> hashMap);
    }

    public JDReactExtendHelperCallback() {
        ReactInstanceManager.addReactEmbeddedListener(new ReactInstanceManager.ReactEmbeddedListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback.1
            @Override // com.facebook.react.ReactInstanceManager.ReactEmbeddedListener
            public void onLoadFinish(String str) {
                RenderDataReportForEmbedded.getInstance().renderEnd(str);
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactEmbeddedListener
            public void onLoadStart(String str, String str2) {
                RenderDataReportForEmbedded.getInstance().loadStart(str, str2);
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void changeDebug(boolean z) {
        CommonBase.putBooleanToPreference(JDReactConstant.JDREACT_DEVELOP_FLAG, Boolean.valueOf(z));
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void changeUserRNTools(boolean z) {
        CommonBase.putBooleanToPreference(JDReactConstant.JDREACT_DEBUG_TOOL_FLAG, Boolean.valueOf(z));
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getAndroidId() {
        String androidId = BaseInfo.getAndroidId();
        JLog.d("privacy", "androidID: " + androidId);
        return androidId;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getCacheMode(String str) {
        return "DOWNLOAD".equals(str) ? 2 : 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public Activity getCurrentMyActivity() {
        if (BaseFrameUtil.getInstance().getCurrentMyActivity() == null) {
            return null;
        }
        return (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public float getDensity(Context context) {
        float density = BaseInfo.getDensity();
        JLog.d("privacy", "density: " + density);
        return density;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getDeprecatedVersion(String str) {
        try {
            return JDMobileConfig.getInstance().getConfig("JDReact", "JDReactDeprecatedVersion", str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getDisplayMetrics() {
        String displayMetrics = BaseInfo.getDisplayMetrics();
        JLog.d("privacy", "getDisplayMetrics: " + displayMetrics);
        return displayMetrics;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public DisplayMetrics getDisplayMetricsObject(Context context) {
        return BaseInfo.getDisplayMetricsObject();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getEffect(String str) {
        if (!"DOWNLOAD".equals(str) && !JDReactConstant.QUERY_REACTNATIVE.equals(str) && NetConfig.GET_META_DATA_FUNCTION_ID.equals(str)) {
        }
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public ElderModeHelper getElderModeHelper() {
        return new JDReactElderModeHelper();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getIpAddressFromWifiInfo(Context context) {
        String netAddressesForIPv4 = getNetAddressesForIPv4();
        JLog.d("privacy", "WifiInfo: " + netAddressesForIPv4);
        return TextUtils.isEmpty(netAddressesForIPv4) ? "" : netAddressesForIPv4;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public JDReactHttpSetting.AbstractJDReactHttpSetting getJDreactHttpSetting() {
        return new JDReactExtendHttpSetting();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public View getLoadingLottieView() {
        if (!ABTestUtils.isLottieEnable() || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        LottieLoadingView lottieLoadingView = new LottieLoadingView(JdSdk.getInstance().getApplicationContext());
        if (lottieLoadingView.initSuccess()) {
            if (OKLog.D) {
                OKLog.d("LottieLoadingView", "switch is open and LottieLoadingView init success");
            }
            return lottieLoadingView;
        }
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String[] getLocation() {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(JDReactNativeLBSListener.BUSINESSID);
        return new String[]{String.valueOf(JDLocationCache.getInstance().getLocation(jDLocationCacheOption).getLng()), String.valueOf(JDLocationCache.getInstance().getLocation(jDLocationCacheOption).getLat())};
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void getLocationLatLng(String str, String str2, final JDReactHelper.AddressCallBack addressCallBack) {
        new JDReactNativeLBSListener().getLocationMsgNew(str, str2, new LocationCallBack() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback.4
            @Override // com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback.LocationCallBack
            public void onSuc(HashMap<String, Object> hashMap) {
                addressCallBack.suc(hashMap);
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetAddressesForIPv4() {
        List<String> netAddressesForIPv4 = BaseInfo.getNetAddressesForIPv4();
        if (netAddressesForIPv4 != null) {
            JLog.d("privacy", "getNetAddressesForIPv4: " + netAddressesForIPv4.get(0));
            return netAddressesForIPv4.get(0);
        }
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetworkOperator(Context context) {
        String networkOperator = BaseInfo.getNetworkOperator(context);
        JLog.d("privacy", "getNetworkOperator: " + networkOperator);
        return networkOperator;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetworkOperatorName(Context context) {
        String networkOperatorName = BaseInfo.getNetworkOperatorName(context);
        JLog.d("privacy", "getNetworkOperatorName: " + networkOperatorName);
        return networkOperatorName;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetworkType() {
        return BaseInfo.getNetworkType();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public float getScaledDensity(Context context) {
        float scaledDensity = BaseInfo.getScaledDensity();
        JLog.d("privacy", "scaledDensity: " + scaledDensity);
        return scaledDensity;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getScreenHeight() {
        int screenHeight = BaseInfo.getScreenHeight();
        JLog.d("privacy", "height: " + screenHeight);
        return screenHeight;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getScreenWidth() {
        int screenWidth = BaseInfo.getScreenWidth();
        JLog.d("privacy", "width: " + screenWidth);
        return screenWidth;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getSpace(String str) {
        return "DOWNLOAD".equals(str) ? 1 : 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getType(String str) {
        return "DOWNLOAD".equals(str) ? 500 : 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public UIModeHelper getUIModeHelper() {
        return new JDReactUIModeHelper();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getUnableAnimationKey() {
        return BaseActivity.DISPOSEABLE_UNABLE_ANIM;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getVirtualHost(String str) {
        if (JDReactConstant.QUERY_REACTNATIVE.equals(str)) {
            return Configuration.getVirtualHost();
        }
        return NetConfig.GET_META_DATA_FUNCTION_ID.equals(str) ? Configuration.getVirtualHost() : "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isAgreedPrivacy() {
        return BaseInfo.isAgreedPrivacy();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isBeta() {
        if (Configuration.isBeta()) {
            ArrayMap<String, HostConfig.HostModel> hostModelArrayMap = HostConfig.getInstance().getHostModelArrayMap();
            for (String str : hostModelArrayMap.keySet()) {
                String releaseHost = hostModelArrayMap.get(str).getReleaseHost();
                if (!TextUtils.isEmpty(releaseHost) && releaseHost.equalsIgnoreCase("api.m.jd.com")) {
                    return hostModelArrayMap.get(str).isUseBeta();
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isDebug() {
        return CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isDiffUpdate() {
        try {
            return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDReact", "JDReactDiffUpdate", "switch", "0"));
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isPreloadCommon() {
        try {
            return "1".equals(JDMobileConfig.getInstance().getConfig("JDReact", "JDReactInitLoadCommon", "switch", "1"));
        } catch (Exception unused) {
            return true;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isUserRNTools() {
        return CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEBUG_TOOL_FLAG, false);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void jumpWithOpenapp(String str, Context context) {
        if (JumpUtil.isOpenAppScheme(str)) {
            OpenAppJumpController.dispatchJumpRequest(context, new Intent().setData(Uri.parse(str)));
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        context.startActivity(intent);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void jumptoWebPage(Context context, String str, Intent intent) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("openapp.jdmobile://virtual")) {
            Intent intent2 = new Intent();
            intent2.setData(Uri.parse(str));
            OpenAppJumpController.dispatchJumpRequest(context, intent2);
            return;
        }
        Bundle bundle = new Bundle();
        if (intent != null && intent.hasExtra(BaseActivity.DISPOSEABLE_UNABLE_ANIM)) {
            bundle.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, bundle.getBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, false));
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, bundle2.getBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, false));
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle2.putSerializable("urlParamMap", serializableContainer);
        bundle2.putString("urlAction", RemoteMessageConst.TO);
        DeepLinkMHelper.startWebActivity(context, bundle2);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean launchLogin(Context context, final JDReactHelper.JDReactLoginCallback jDReactLoginCallback) {
        if (LoginUserBase.hasLogin()) {
            return false;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback.2
            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                OKLog.i("test", "ILogin.onSuccess, tag:" + str + ", hasLogin:" + LoginUserBase.hasLogin());
                if (JDReactExtendHelperCallback.LOGIN_TAG.equals(str)) {
                    jDReactLoginCallback.onSuccess(str);
                }
            }
        }, LOGIN_TAG);
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postException(Exception exc, ArrayMap arrayMap) {
        ApmExceptionReporter.post(exc, arrayMap);
        JDCrashReporter.post(exc, arrayMap);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postRNMonitorData(HashMap hashMap) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void recycleLoadingLottieView(View view) {
        if (view == null || !(view instanceof LottieLoadingView)) {
            return;
        }
        LottieLoadingView.freeLottieMemory((LottieLoadingView) view);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void requestPermission(Activity activity, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        Bundle generateBundle;
        try {
            String str = hashMap.containsKey("moduleName") ? (String) hashMap.get("moduleName") : ReactConstants.TAG;
            List arrayList = hashMap.containsKey("tittleMsg") ? (List) hashMap.get("tittleMsg") : new ArrayList();
            List arrayList2 = hashMap.containsKey("tipMsg") ? (List) hashMap.get("tipMsg") : new ArrayList();
            List arrayList3 = hashMap.containsKey("permissions") ? (List) hashMap.get("permissions") : new ArrayList();
            try {
                if (arrayList3 != null && arrayList3.size() > 0 && activity != null && !activity.isFinishing()) {
                    if (PermissionHelper.hasPermission(activity, arrayList3)) {
                        if (jDCallback != null) {
                            jDCallback.invoke(new Object[0]);
                        }
                        return;
                    }
                    if (hashMap.containsKey(PermissionHelper.PARAM_USER_INITIATIVE)) {
                        generateBundle = PermissionHelper.generateBundle(str, JDReactExtendHelperCallback.class.getSimpleName(), "requestPermission", ((Boolean) hashMap.get(PermissionHelper.PARAM_USER_INITIATIVE)).booleanValue());
                    } else {
                        generateBundle = PermissionHelper.generateBundle(str, JDReactExtendHelperCallback.class.getSimpleName(), "requestPermission");
                    }
                    PermissionHelper.requestPermission(activity, generateBundle, arrayList3, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallback.3
                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onDenied() {
                            super.onDenied();
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(new Object[0]);
                            }
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onGranted() {
                            super.onGranted();
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(new Object[0]);
                            }
                        }
                    }, arrayList, arrayList2);
                }
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                }
            } catch (Exception unused) {
                if (jDCallback2 != null) {
                    jDCallback2.invoke(new Object[0]);
                }
            }
        } catch (Exception unused2) {
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void sendCommonData(String str, String str2) {
        JDMtaUtils.sendCommonData(JdSdk.getInstance().getApplicationContext(), str, str2, "", "", "", "", "", "", "");
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void sendMsgToJs(Context context, String str, HashMap hashMap) {
        try {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("db", ((Double) hashMap.get("db")).doubleValue());
            createMap.putDouble("time", ((Double) hashMap.get("time")).doubleValue());
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) ((ReactContext) context).getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, createMap);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void setExposureMta(HashMap hashMap, String str) {
        if (hashMap.containsKey("order_id") && hashMap.containsKey("sku_tag")) {
            JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplicationContext(), (String) hashMap.get("page"), (String) hashMap.get("page_id"), (String) hashMap.get("page_param"), (String) hashMap.get("event_id"), str, (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID), (String) hashMap.get("order_id"), (String) hashMap.get("sku_tag"));
        } else {
            JDMtaUtils.sendCommonData(JdSdk.getInstance().getApplicationContext(), (String) hashMap.get("event_id"), str, (String) hashMap.get("event_func"), (String) hashMap.get("page"), (String) hashMap.get("page_param"), (String) hashMap.get("next_class_name"), (String) hashMap.get("next_page_param"), (String) hashMap.get("page_id"), (String) hashMap.get(UnAddressConstants.INTENT_SHOP_ID));
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean showBundleInfo() {
        return isDebug();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void showErrorRetryView(View.OnClickListener onClickListener, ViewGroup viewGroup) {
        JDReactShowErrorViewUtils jDReactShowErrorViewUtils = new JDReactShowErrorViewUtils((Activity) viewGroup.getContext(), (LinearLayout) viewGroup);
        jDReactShowErrorViewUtils.getErrorViewHasRetry(onClickListener);
        jDReactShowErrorViewUtils.setErrorImage(R.drawable.jdreact_y_04);
        jDReactShowErrorViewUtils.setMessageInfo(viewGroup.getResources().getString(R.string.jdreact_net_fail), viewGroup.getResources().getString(R.string.jdreact_net_check), "");
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void showLongToast(String str) {
        Toast.makeText(JdSdk.getInstance().getApplicationContext(), str, 1).show();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void showShortToast(String str) {
        Toast.makeText(JdSdk.getInstance().getApplicationContext(), str, 0).show();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void startActivityInFrameWithNoNavigation(Intent intent, Context context) {
        ((BaseActivity) context).startActivityInFrameWithNoNavigation(intent);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean useDownloadQueue() {
        try {
            return "1".equals(JDMobileConfig.getInstance().getConfig("JDReact", "JDReactDownloadQueue", "switch", "1"));
        } catch (Exception unused) {
            return true;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean useHttp() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postException(String str) {
        ApmExceptionReporter.post(str);
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postException(Throwable th) {
        ApmExceptionReporter.post(th);
    }
}
