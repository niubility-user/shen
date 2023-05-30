package com.jdjr.risk.identity.verify;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.verify.VerifySession;
import com.jd.aips.verify.tracker.TrackerCallback;

/* loaded from: classes18.dex */
public class IdentityVerifySession extends VerifySession<IdentityVerifyParams, IdentityVerityCallback, IdentityVerifyTracker> {
    public volatile String a;

    public IdentityVerifySession(@NonNull Context context, @NonNull IdentityVerifyParams identityVerifyParams, @NonNull IdentityVerityCallback identityVerityCallback, @Nullable TrackerCallback trackerCallback) {
        super(context, identityVerifyParams, identityVerityCallback, trackerCallback);
        this.a = "";
    }

    @Override // com.jd.aips.verify.VerifySession
    protected IdentityVerifyTracker buildVerifyTracker(@NonNull Context context) {
        return new IdentityVerifyTracker(context, this, this.trackerCallback);
    }
}
