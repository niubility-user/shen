package com.jingdong.common.jdreactFramework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import com.facebook.react.ReactInstanceManager;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.helper.UIModeHelper;
import com.jingdong.common.jdreactFramework.track.RenderDataReportForEmbedded;
import java.util.HashMap;

/* loaded from: classes5.dex */
public abstract class AbsJDReactExtendHelperCallback implements JDReactHelper.JDReactHelperCallback {
    public AbsJDReactExtendHelperCallback() {
        ReactInstanceManager.addReactEmbeddedListener(new ReactInstanceManager.ReactEmbeddedListener() { // from class: com.jingdong.common.jdreactFramework.AbsJDReactExtendHelperCallback.1
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
    public int getCacheMode(String str) {
        return 0;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public Activity getCurrentMyActivity() {
        return null;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public int getEffect(String str) {
        return 0;
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
    public boolean isBeta() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public boolean isDebug() {
        return true;
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
    public void postRNMonitorData(HashMap hashMap) {
    }

    @Override // com.jingdong.common.jdreactFramework.JDReactHelper.JDReactHelperCallback
    public void recycleLoadingLottieView(View view) {
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
