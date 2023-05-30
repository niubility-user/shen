package com.jd.aips.verify.tracker;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.bean.DeviceInfo;
import com.jd.aips.common.utils.FsGsonUtil;
import com.jd.aips.tracker.UemsTrackManger;
import com.jd.aips.verify.VerifySession;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public abstract class BaseVerifyTracker<V extends VerifySession> implements VerifyTracker {
    protected final Context context;
    private String sdkInfo;
    protected final String sdkName;
    protected final String sdkToken;
    protected final String sdkVersion;
    protected final V session;
    protected final TrackerCallback trackerCallback;

    public BaseVerifyTracker(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull V v, @Nullable TrackerCallback trackerCallback) {
        this.context = context;
        this.sdkName = str;
        this.sdkVersion = str2;
        this.sdkToken = v.verifyParams.sdkToken;
        this.session = v;
        this.trackerCallback = trackerCallback;
        init();
    }

    private void init() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("s_type", "SDK");
            jSONObject.put("s_name", this.sdkName);
            jSONObject.put("s_proj_version", this.sdkVersion);
            jSONObject.put("s_jd_bid", this.session.verifyParams.businessId);
            jSONObject.put("s_jd_pin", this.session.verifyParams.userId);
            jSONObject.put("s_jd_token", this.session.verifyParams.verifyToken);
            jSONObject.put("s_app_id", this.context.getPackageName());
            try {
                jSONObject.put("s_host_version", this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            jSONObject.put("s_jd_biometric_token", this.sdkToken);
            this.sdkInfo = jSONObject.toString();
        } catch (Exception unused2) {
        }
        if (this.sdkInfo == null) {
            this.sdkInfo = "";
        }
    }

    private void tryToCallback(String str, JSONObject jSONObject) {
        if (this.trackerCallback != null) {
            Bundle bundle = new Bundle();
            bundle.putString("kvsJsonObj", jSONObject.toString());
            try {
                this.trackerCallback.onTrack(str, bundle);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject buildTrackData(String str) throws JSONException {
        JSONObject createBaseInfo = createBaseInfo();
        createBaseInfo.put(VerifyTracker.KEY_P_CODE, str);
        return createBaseInfo;
    }

    protected JSONObject createBaseInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(VerifyTracker.KEY_TIMESTAMP, System.currentTimeMillis());
            jSONObject.put("pin", this.session.verifyParams.userId);
            jSONObject.put("businessId", this.session.verifyParams.businessId);
            jSONObject.put("verifyToken", this.session.verifyParams.verifyToken);
            jSONObject.put(VerifyTracker.KEY_ACTUAL_VERIFY_TOKEN, this.session.verifyToken);
            jSONObject.put("token", this.sdkToken);
            DeviceInfo deviceInfo = this.session.verifyParams.getDeviceInfo();
            if (deviceInfo != null) {
                jSONObject.put(VerifyTracker.KEY_DEVICE_INFO, FsGsonUtil.toJson(deviceInfo));
            }
            jSONObject.put(VerifyTracker.KEY_SDK_VERIFY_ID, this.session.id);
            jSONObject.put(VerifyTracker.KEY_TIMES, this.session.count);
            jSONObject.put(VerifyTracker.KEY_APP_VER_NAME, this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    protected abstract String getDefaultPCode();

    public void refreshSdkInfo() {
        this.sdkInfo = null;
        init();
    }

    public void track(@NonNull String str) {
        try {
            track(str, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackComplete(int i2) {
        String str;
        try {
            JSONObject buildTrackData = buildTrackData(getDefaultPCode());
            if (i2 == 0) {
                str = VerifyTracker.EVENT_ALL_PASS;
            } else {
                buildTrackData.put(VerifyTracker.KEY_SDK_CODE, i2);
                str = VerifyTracker.EVENT_ALL_REJECT;
            }
            track(str, buildTrackData);
        } catch (Exception unused) {
        }
    }

    public void trackEnter() {
        try {
            track(VerifyTracker.EVENT_ENTER, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackException(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        try {
            JSONObject createBaseInfo = createBaseInfo();
            createBaseInfo.put(VerifyTracker.KEY_P_CODE, str);
            createBaseInfo.put(VerifyTracker.KEY_ERROR_RESULT_STR, str2);
            createBaseInfo.put(VerifyTracker.KEY_EXCEPTION_MSG, str3);
            track(VerifyTracker.EVENT_NETWORK_WARN, createBaseInfo);
        } catch (Exception unused) {
        }
    }

    public void trackFormatException(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        try {
            JSONObject createBaseInfo = createBaseInfo();
            createBaseInfo.put(VerifyTracker.KEY_VALUE_NAME, str);
            createBaseInfo.put("value", str2);
            createBaseInfo.put("errorMsg", str3);
            track(VerifyTracker.EVENT_FORMAT_EXCEPTION, createBaseInfo);
        } catch (Exception unused) {
        }
    }

    public void trackRequestPermission() {
        try {
            track(VerifyTracker.EVENT_CHECK_CAMERA, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackRequestPermissionFailed() {
        try {
            track(VerifyTracker.EVENT_NO_AUTHORITY, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackRequestPermissionSuccess() {
        try {
            track(VerifyTracker.EVENT_AUTHORITY, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackTryAgain() {
        try {
            track(VerifyTracker.EVENT_TRY_AGAIN, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    public void trackUserCancel() {
        try {
            track(VerifyTracker.EVENT_EXIT, buildTrackData(getDefaultPCode()));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void track(@NonNull String str, @NonNull JSONObject jSONObject) {
        UemsTrackManger.uploadTrack(this.context, str, jSONObject, this.sdkName, this.sdkVersion, this.sdkInfo);
        tryToCallback(str, jSONObject);
    }
}
