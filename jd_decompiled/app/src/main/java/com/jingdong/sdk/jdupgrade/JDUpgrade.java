package com.jingdong.sdk.jdupgrade;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.jdupgrade.a.a;
import com.jingdong.sdk.jdupgrade.a.c;
import com.jingdong.sdk.jdupgrade.a.i.k;
import com.jingdong.sdk.jdupgrade.a.j.f;
import com.jingdong.sdk.jdupgrade.a.j.g;
import com.jingdong.sdk.jdupgrade.a.j.h;
import java.io.File;

/* loaded from: classes7.dex */
public class JDUpgrade {
    private static k limitedUpgradeChain;
    private static k unLimitedUpgradeChain;

    public static void downloadApk(String str, String str2, boolean z, ApkDownloadCallback apkDownloadCallback) {
        if (c.P()) {
            c.a(str, str2, z, apkDownloadCallback);
            return;
        }
        h.b(XView2Constants.XVIEW2_ACTION_INIT, "downloadApk not init yet, call init first");
        if (apkDownloadCallback != null) {
            apkDownloadCallback.onError();
        }
    }

    public static void hasNewVersion(UpgradeCallback upgradeCallback) {
        if (c.P()) {
            new a(upgradeCallback).a();
            return;
        }
        h.b(XView2Constants.XVIEW2_ACTION_INIT, "hasNewVersion not init yet, call init first");
        if (upgradeCallback != null) {
            upgradeCallback.onChecked(false, "hasNewVersion not init yet, call init first", "");
        }
    }

    public static void init(Application application, UpgradeConfig upgradeConfig, BaseInfoProvider baseInfoProvider) {
        c.a(application, upgradeConfig, baseInfoProvider);
    }

    public static void installApk(String str) {
        if (!c.P()) {
            h.b(XView2Constants.XVIEW2_ACTION_INIT, "installApk not init yet, call init first");
        } else if (TextUtils.isEmpty(str)) {
            h.b("", "installApk path is null");
        } else {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                f.a(str, null);
            } else {
                h.b("", "installApk file not exists");
            }
        }
    }

    public static void limitedCheckAndPop(UpgradeEventListener upgradeEventListener) {
        if (c.P()) {
            if (limitedUpgradeChain == null) {
                limitedUpgradeChain = new k(false);
            }
            limitedUpgradeChain.a(upgradeEventListener);
            return;
        }
        h.b(XView2Constants.XVIEW2_ACTION_INIT, "limitedCheckAndPop not init yet, call init first");
        if (upgradeEventListener != null) {
            upgradeEventListener.onMessage("0", "limitedCheckAndPop not init yet, call init first");
        }
    }

    public static void queryVersionInfo(VersionInfoCallback versionInfoCallback) {
        if (c.P()) {
            c.a(versionInfoCallback);
            return;
        }
        h.b(XView2Constants.XVIEW2_ACTION_INIT, "queryVersionInfo not init yet, call init first");
        if (versionInfoCallback != null) {
            versionInfoCallback.onError();
        }
    }

    public static void requestJDMallUpgradeInfo(JDMallUpgradeInfoCallBack jDMallUpgradeInfoCallBack) {
        c.a(jDMallUpgradeInfoCallBack);
    }

    public static void setAutoDownloadWithWifi(boolean z) {
        c.a(z);
    }

    public static void setCurrentActivity(Activity activity) {
        g.a(activity);
    }

    public static void unlimitedCheckAndPop(UpgradeEventListener upgradeEventListener) {
        if (c.P()) {
            if (unLimitedUpgradeChain == null) {
                unLimitedUpgradeChain = new k(true);
            }
            unLimitedUpgradeChain.a(upgradeEventListener);
            return;
        }
        h.b(XView2Constants.XVIEW2_ACTION_INIT, "unlimitedCheckAndPop not init yet, call init first");
        if (upgradeEventListener != null) {
            upgradeEventListener.onMessage("0", "unlimitedCheckAndPop not init yet, call init first");
        }
    }
}
