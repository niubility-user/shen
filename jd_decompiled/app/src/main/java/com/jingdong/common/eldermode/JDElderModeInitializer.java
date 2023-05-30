package com.jingdong.common.eldermode;

import android.content.Context;
import android.content.SharedPreferences;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.sdk.eldermode.helper.IElderModeExceptionHandler;
import com.jingdong.sdk.eldermode.helper.IElderModeLogger;
import com.jingdong.sdk.eldermode.helper.impl.JDElderModePreferencesCacheImpl;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes.dex */
public class JDElderModeInitializer {
    private static final String MODULE_NAME = "JDElderMode";

    public static void initialize(Context context) {
        try {
            JDElderModeUtils.initialize(JDElderModeUtils.newBuilder(context).debug(JdSdk.getInstance().getBuildConfigDebug()).cache(new JDElderModePreferencesCacheImpl(context) { // from class: com.jingdong.common.eldermode.JDElderModeInitializer.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.jingdong.sdk.eldermode.helper.impl.JDElderModePreferencesCacheImpl
                @NotNull
                public SharedPreferences createSharedPreferences(@NotNull Context context2, @NotNull String str, int i2) {
                    return new JDSharedPreferences(context2, str, i2);
                }
            }).dialog(new JDElderModeDialogImpl()).toast(new JDElderModeToastImpl()).logger(new IElderModeLogger() { // from class: com.jingdong.common.eldermode.JDElderModeInitializer.2
                @Override // com.jingdong.sdk.eldermode.helper.IElderModeLogger
                public void log(@Nullable String str) {
                    if (str == null || !OKLog.D) {
                        return;
                    }
                    OKLog.d(JDElderModeInitializer.MODULE_NAME, str);
                }
            }).exceptionHandler(new IElderModeExceptionHandler() { // from class: com.jingdong.common.eldermode.JDElderModeInitializer.1
                @Override // com.jingdong.sdk.eldermode.helper.IElderModeExceptionHandler
                public void handleException(@Nullable Throwable th) {
                    if (th != null) {
                        if (OKLog.D) {
                            OKLog.d(JDElderModeInitializer.MODULE_NAME, th);
                        }
                        JdCrashReport.postCaughtException(th, JDElderModeInitializer.MODULE_NAME);
                    }
                }
            }).networkController(new JDElderModeNetworkControllerImpl()).mtaSender(new JDElderModeMtaSenderImpl()).darkModeConfig(new JDElderModeDarkModeConfigImpl()).textSizeConfig(new JDElderModeTextSizeConfigImpl()).overseasConfig(new JDElderModeOverseasConfigImpl()).userInfoConfig(new JDElderModeUserInfoConfigImpl()).build());
        } catch (Throwable unused) {
        }
    }
}
