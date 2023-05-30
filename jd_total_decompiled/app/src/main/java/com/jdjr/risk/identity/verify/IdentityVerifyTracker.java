package com.jdjr.risk.identity.verify;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.verify.tracker.BaseVerifyTracker;
import com.jd.aips.verify.tracker.TrackerCallback;
import com.jd.aips.verify.tracker.VerifyTracker;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class IdentityVerifyTracker extends BaseVerifyTracker<IdentityVerifySession> {
    public IdentityVerifyTracker(@NonNull Context context, @NonNull IdentityVerifySession identityVerifySession, @Nullable TrackerCallback trackerCallback) {
        super(context, IdentityVerityEngine.SDK_NAME, "3.1.00", identityVerifySession, trackerCallback);
    }

    public void a() {
        try {
            track(VerifyTracker.EVENT_FACE_BEGIN, buildTrackData(VerifyTracker.P_CODE_VERIFY));
        } catch (Exception unused) {
        }
    }

    public void b() {
        try {
            track(VerifyTracker.EVENT_EXIT, buildTrackData(VerifyTracker.P_CODE_VERIFY));
        } catch (Exception unused) {
        }
    }

    public void c() {
        try {
            track(VerifyTracker.EVENT_ENTER_AUTHORITY_PAGE, buildTrackData(VerifyTracker.P_CODE_VERIFY));
        } catch (Exception unused) {
        }
    }

    public void d() {
        try {
            track("request", buildTrackData(VerifyTracker.P_CODE_APPLICATE));
        } catch (Exception unused) {
        }
    }

    public void e() {
        try {
            track(VerifyTracker.EVENT_RISK, buildTrackData(VerifyTracker.P_CODE_VERIFY));
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.aips.verify.tracker.BaseVerifyTracker
    protected String getDefaultPCode() {
        return VerifyTracker.P_CODE_VERIFY;
    }

    @Override // com.jd.aips.verify.tracker.BaseVerifyTracker
    public void trackEnter() {
        try {
            track(VerifyTracker.EVENT_ENTER, buildTrackData("1"));
        } catch (Exception unused) {
        }
    }

    public void a(String str) {
        try {
            JSONObject buildTrackData = buildTrackData(VerifyTracker.P_CODE_APPLICATE);
            buildTrackData.put(VerifyTracker.KEY_CONFIG_VER, str);
            track(VerifyTracker.EVENT_PASS, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void a(int i2) {
        try {
            JSONObject buildTrackData = buildTrackData(VerifyTracker.P_CODE_APPLICATE);
            buildTrackData.put(VerifyTracker.KEY_CODE, i2);
            track(VerifyTracker.EVENT_REJECT, buildTrackData);
        } catch (Exception unused) {
        }
    }
}
