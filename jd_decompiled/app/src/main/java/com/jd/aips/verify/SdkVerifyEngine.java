package com.jd.aips.verify;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.bean.DeviceInfo;
import com.jd.aips.common.utils.BiometricTokenUtil;
import com.jd.aips.common.utils.DeviceInfoUtil;
import com.jd.aips.common.utils.SecurityChannelUtils;
import com.jd.aips.tools.log.Logger;
import com.jd.aips.verify.SdkVerifyParams;
import com.jd.aips.verify.SdkVerifySession;
import com.jd.aips.verify.tracker.TrackerCallback;

/* loaded from: classes12.dex */
public abstract class SdkVerifyEngine<P extends SdkVerifyParams, S extends SdkVerifySession> implements VerifyEngine {
    protected volatile Context applicationContext;
    protected volatile DeviceInfo deviceInfo;
    protected volatile S session;

    protected abstract P buildVerifyParams(@NonNull Bundle bundle);

    protected abstract S buildVerifySession(@NonNull Context context, @NonNull P p, @NonNull VerifyCallback verifyCallback, @Nullable TrackerCallback trackerCallback);

    public synchronized void callbackFinishSDK(int i2, String str) {
        callbackFinishSDK(i2, str, null);
    }

    protected synchronized void doCallback(@NonNull VerifyCallback verifyCallback, int i2, String str) {
        doCallback(verifyCallback, i2, str, null, null);
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

    protected abstract String getLogTagSuffix();

    protected abstract String getSdkName();

    protected abstract String getSdkVersion();

    public S getSession() {
        return this.session;
    }

    public synchronized void launch(@NonNull Context context, @Nullable Bundle bundle, @NonNull VerifyCallback verifyCallback) {
        launch(context, bundle, verifyCallback, null);
    }

    public synchronized void release() {
        if (this.applicationContext != null) {
            this.applicationContext = null;
        }
        if (this.session != null) {
            this.session.destroy();
            this.session = null;
        }
    }

    protected abstract void toLaunch();

    public synchronized void callbackFinishSDK(int i2, String str, Bundle bundle) {
        callbackFinishSDK(i2, str, null, bundle);
    }

    protected synchronized void doCallback(@NonNull VerifyCallback verifyCallback, int i2, String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str2) && this.session != null && this.session.verifyParams != 0) {
            str2 = this.session.verifyParams.verifyToken;
        }
        if (bundle == null) {
            if (i2 == 0) {
                bundle = new Bundle();
            } else {
                bundle = Bundle.EMPTY;
            }
        }
        if (i2 == 0) {
            bundle.putInt("retry_count", this.session.count);
        }
        try {
            verifyCallback.onResult(i2, str, str2, bundle);
        } catch (Throwable unused) {
        }
        Logger.removeSuffix(getLogTagSuffix());
        release();
    }

    public synchronized void launch(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback, @Nullable TrackerCallback trackerCallback) {
        Logger.addSuffix(getLogTagSuffix());
        if (context == null) {
            throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1acontext \u4e3a\u7a7a\uff01");
        }
        if (verifyCallback != null) {
            if (this.session != null) {
                doCallback(verifyCallback, 1, "\u91cd\u590d\u8c03\u7528\uff01");
                return;
            } else if (bundle == null) {
                doCallback(verifyCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aparam bundle \u4e3a\u7a7a\uff01");
                return;
            } else {
                this.applicationContext = context.getApplicationContext();
                try {
                    if (this.deviceInfo == null) {
                        this.deviceInfo = DeviceInfoUtil.buildDeviceInfo(this.applicationContext);
                    }
                    P buildVerifyParams = buildVerifyParams(bundle);
                    if (!buildVerifyParams.validateParams()) {
                        doCallback(verifyCallback, 5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff01");
                        return;
                    }
                    if (!buildVerifyParams.securityChannelReady) {
                        SecurityChannelUtils.startSecurityChannel(this.applicationContext);
                    }
                    if (TextUtils.isEmpty(buildVerifyParams.sdkToken)) {
                        buildVerifyParams.sdkToken = BiometricTokenUtil.buildBiometricToken(context, buildVerifyParams.userId);
                    }
                    buildVerifyParams.verifySdkName = getSdkName();
                    buildVerifyParams.verifySdkVersion = getSdkVersion();
                    buildVerifyParams.setDeviceInfo(this.deviceInfo);
                    this.session = buildVerifySession(this.applicationContext, buildVerifyParams, verifyCallback, trackerCallback);
                    toLaunch();
                    return;
                } catch (Exception e2) {
                    doCallback(verifyCallback, 5, "Sdk \u542f\u52a8\u5931\u8d25\uff1a" + e2.getMessage());
                    return;
                }
            }
        }
        throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1averifyCallback \u4e3a\u7a7a\uff01");
    }

    public synchronized void callbackFinishSDK(int i2, String str, String str2, Bundle bundle) {
        if (this.session != null) {
            this.session.verifyTracker.trackComplete(i2);
            doCallback((VerifyCallback) this.session.verifyCallback, i2, str, str2, bundle);
        }
    }
}
