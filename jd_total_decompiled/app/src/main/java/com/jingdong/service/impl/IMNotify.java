package com.jingdong.service.impl;

import android.app.Activity;
import android.content.Context;
import com.jingdong.service.BaseService;
import com.jingdong.service.service.NotifyService;
import java.util.List;

/* loaded from: classes10.dex */
public class IMNotify extends BaseService implements NotifyService {
    private static final String TAG = "IMNotify";

    public boolean checkMessagePopShow() {
        return false;
    }

    public void dismissNotifyActivity(Activity activity) {
    }

    public int getAnimationStyle() {
        return 0;
    }

    public String getAppPackageName() {
        return "";
    }

    public Activity getCurrentActivity() {
        return null;
    }

    public boolean getDDStationWindowKey() {
        return false;
    }

    public boolean getMSGSOUND() {
        return false;
    }

    public boolean getMsgShakeSwitch() {
        return false;
    }

    public String getNotifyClassName() {
        return "";
    }

    public List<String> getPopWindowList() {
        return null;
    }

    public boolean isAppForeground(Context context) {
        return false;
    }

    public int notifyLargeIcon() {
        return 0;
    }

    public int notifySmallIcon() {
        return 0;
    }

    public int soundResId() {
        return 0;
    }
}
