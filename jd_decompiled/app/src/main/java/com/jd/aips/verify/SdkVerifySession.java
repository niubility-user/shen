package com.jd.aips.verify;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.verify.SdkVerifyParams;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.config.VerifyConfig;
import com.jd.aips.verify.tracker.BaseVerifyTracker;
import com.jd.aips.verify.tracker.TrackerCallback;

/* loaded from: classes12.dex */
public abstract class SdkVerifySession<C extends VerifyConfig, P extends SdkVerifyParams<C>, T extends BaseVerifyTracker> extends VerifySession<P, VerifyCallback, T> {
    protected volatile int resultCode;
    protected volatile Bundle resultData;
    protected volatile String resultMessage;

    public SdkVerifySession(@NonNull Context context, @NonNull P p, @NonNull VerifyCallback verifyCallback, @Nullable TrackerCallback trackerCallback) {
        super(context, p, verifyCallback, trackerCallback);
        VerificationSdk verificationSdk;
        this.resultCode = 1;
        this.resultMessage = "";
        this.resultData = null;
        C c2 = p.verifyConfig;
        if (c2 == 0 || (verificationSdk = ((VerifyConfig) c2).verificationSdk) == null) {
            return;
        }
        int i2 = verificationSdk.config.sdk_verification_retry_count;
        this.totalCount = i2;
        if (i2 <= 0) {
            this.totalCount = 3;
        }
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public Bundle getResultData() {
        return this.resultData;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    @Override // com.jd.aips.verify.VerifySession
    public String getVerifyId() {
        return this.verifyId;
    }

    public void resetResult() {
        this.verifyId = "";
        this.resultCode = -1;
        this.resultMessage = "";
        this.resultData = null;
    }

    public synchronized void setResult(int i2) {
        setResult(i2, "", "", null);
    }

    public synchronized void setResult(int i2, String str) {
        setResult(i2, str, "", null);
    }

    public synchronized void setResult(int i2, String str, String str2) {
        setResult(i2, str, str2, null);
    }

    public synchronized void setResult(int i2, String str, String str2, Bundle bundle) {
        this.resultCode = i2;
        this.resultMessage = str;
        this.verifyId = str2;
        this.resultData = bundle;
    }
}
