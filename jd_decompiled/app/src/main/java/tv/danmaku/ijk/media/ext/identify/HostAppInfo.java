package tv.danmaku.ijk.media.ext.identify;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class HostAppInfo implements Serializable {
    private String appName;
    private ActivityInfoCallback callback;
    private String versionCode;
    private String versionName;

    /* loaded from: classes11.dex */
    public interface ActivityInfoCallback {
        String getCurActivityClsName();

        boolean isMainProcess();
    }

    public HostAppInfo(String str, String str2, String str3, ActivityInfoCallback activityInfoCallback) {
        this.appName = str;
        this.versionName = str2;
        this.versionCode = str3;
        this.callback = activityInfoCallback;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getCurClsName() {
        ActivityInfoCallback activityInfoCallback = this.callback;
        return activityInfoCallback == null ? "" : activityInfoCallback.getCurActivityClsName();
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public boolean isMainProcess() {
        ActivityInfoCallback activityInfoCallback = this.callback;
        if (activityInfoCallback == null) {
            return false;
        }
        return activityInfoCallback.isMainProcess();
    }
}
