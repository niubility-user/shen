package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import com.facebook.react.ReactInstanceManager;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.JDReactHttpSetting;
import com.jingdong.common.jdreactFramework.helper.ElderModeHelper;
import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.jdreactFramework.track.RenderDataReportForEmbedded;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactExtendHelperCallbackDemo implements JDReactHelper.JDReactHelperCallback {
    public JDReactExtendHelperCallbackDemo() {
        ReactInstanceManager.addReactEmbeddedListener(new ReactInstanceManager.ReactEmbeddedListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactExtendHelperCallbackDemo.1
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
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void changeUserRNTools(boolean z) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getAndroidId() {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getCacheMode(String str) {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public Activity getCurrentMyActivity() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public float getDensity(Context context) {
        return 0.0f;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getDeprecatedVersion(String str) {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getDisplayMetrics() {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public DisplayMetrics getDisplayMetricsObject(Context context) {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getEffect(String str) {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public ElderModeHelper getElderModeHelper() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getIpAddressFromWifiInfo(Context context) {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public JDReactHttpSetting.AbstractJDReactHttpSetting getJDreactHttpSetting() {
        return new JDReactExtendHttpSettingDemo();
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public View getLoadingLottieView() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String[] getLocation() {
        return new String[2];
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void getLocationLatLng(String str, String str2, JDReactHelper.AddressCallBack addressCallBack) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetAddressesForIPv4() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetworkOperator(Context context) {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetworkOperatorName(Context context) {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getNetworkType() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public float getScaledDensity(Context context) {
        return 0.0f;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getScreenHeight() {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getScreenWidth() {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getSpace(String str) {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getType(String str) {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public UIModeHelper getUIModeHelper() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getUnableAnimationKey() {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public String getVirtualHost(String str) {
        return "";
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isAgreedPrivacy() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isBeta() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isDebug() {
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isDiffUpdate() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isPreloadCommon() {
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isUserRNTools() {
        return true;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void jumpWithOpenapp(String str, Context context) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void jumptoWebPage(Context context, String str, Intent intent) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean launchLogin(Context context, JDReactHelper.JDReactLoginCallback jDReactLoginCallback) {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postException(Exception exc, ArrayMap arrayMap) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postException(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postException(Throwable th) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void postRNMonitorData(HashMap hashMap) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void recycleLoadingLottieView(View view) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void requestPermission(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void sendCommonData(String str, String str2) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void sendMsgToJs(Context context, String str, HashMap hashMap) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void setExposureMta(HashMap hashMap, String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean showBundleInfo() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void showErrorRetryView(View.OnClickListener onClickListener, ViewGroup viewGroup) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void showLongToast(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void showShortToast(String str) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void startActivityInFrameWithNoNavigation(Intent intent, Context context) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean useDownloadQueue() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean useHttp() {
        return false;
    }
}
