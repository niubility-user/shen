package com.jingdong.common.jdreactFramework;

import android.app.Application;
import android.content.Context;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.utils.JumpUtils;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jdreactFramework.utils.ReactModuleAvailabilityUtils;

/* loaded from: classes5.dex */
public class AresSDK {
    public void checkUpdate() {
        ReactNativeUpdate.getInstance().checkUpdate();
    }

    public void cleanCacheWhenEngineUpdate() {
        ReactNativeUpdate.getInstance().reactUnzipSo();
    }

    public String getJumpDes() {
        return JumpUtils.getJumpDes();
    }

    public String getJumpProtocalHeader() {
        return JumpUtils.getJumpProtocalHeader();
    }

    public boolean getModuleAvailability(String str) {
        return ReactModuleAvailabilityUtils.getModuleAvailability(str);
    }

    public PluginVersion getPluginDir(Context context, String str) {
        return ReactNativeFileManager.getPluginDir(context, str);
    }

    public void init(Application application, boolean z) {
        JDReactHelper.newInstance().init(application, z);
    }

    public boolean isJumpProtocalHeader(String str) {
        return JumpUtils.isJumpProtocalHeader(str);
    }

    public void saveModuleAvailability(String str, boolean z) {
        ReactModuleAvailabilityUtils.saveModuleAvailability(str, z);
    }

    public void setCommonActivityName(String str) {
        ReactActivityUtilsHelperBase.setCommonActivityName(str);
    }

    public void setJDReactAuraHelper(JDReactAuraHelper.CommonInvokeInterface commonInvokeInterface, Context context, Application application, JDReactHelper.JDReactHelperCallback jDReactHelperCallback) {
        JDReactAuraHelper.getInstance().setCommonInvokeInterface(commonInvokeInterface, context, application, jDReactHelperCallback);
    }

    public void setJDReactHelperCallback(JDReactHelper.JDReactHelperCallback jDReactHelperCallback) {
        JDReactHelper.newInstance().setJDReactHelperCallback(jDReactHelperCallback);
    }

    public void setJumpDes(String str) {
        JumpUtils.setJumpDes(str);
    }

    public void setJumpProtocalHeader(String str) {
        JumpUtils.setJumpDes(str);
    }

    public void setPackageName(String str) {
        ReactActivityUtilsHelperBase.setPackageName(str);
    }
}
