package com.jingdong.sdk.jdupgrade.a;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.jdupgrade.JDMallUpgradeInfoCallBack;
import com.jingdong.sdk.jdupgrade.VersionEntity;
import com.jingdong.sdk.jdupgrade.a.j.i;
import com.jingdong.sdk.jdupgrade.a.j.j;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class f {
    private JDMallUpgradeInfoCallBack a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x0117 A[Catch: all -> 0x0136, TryCatch #0 {all -> 0x0136, blocks: (B:18:0x0056, B:20:0x0098, B:23:0x00c7, B:25:0x00d1, B:30:0x00df, B:32:0x00f3, B:34:0x00fd, B:39:0x010b, B:41:0x0117, B:43:0x0121, B:46:0x012c), top: B:54:0x0056 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            boolean z;
            boolean z2;
            if (i.b()) {
                com.jingdong.sdk.jdupgrade.a.h.f a = com.jingdong.sdk.jdupgrade.a.h.f.a(j.e());
                if (f.this.a == null) {
                    return;
                }
                if (a != null) {
                    VersionEntity versionEntity = new VersionEntity();
                    versionEntity.extreme = a.f15076j;
                    try {
                        versionEntity.state = Integer.valueOf(a.a.a()).intValue();
                    } catch (Throwable unused) {
                        versionEntity.state = 300;
                    }
                    com.jingdong.sdk.jdupgrade.a.h.d dVar = a.f15070c;
                    if (dVar != null && a.f15071e != null && a.d != null) {
                        try {
                            versionEntity.size = Long.valueOf(dVar.d).longValue();
                        } catch (Throwable unused2) {
                        }
                        try {
                            com.jingdong.sdk.jdupgrade.a.h.d dVar2 = a.f15070c;
                            versionEntity.version = dVar2.a;
                            versionEntity.build = dVar2.b;
                            versionEntity.url = dVar2.f15059c;
                            versionEntity.md5 = dVar2.f15062g;
                            versionEntity.fileMd5 = dVar2.f15060e;
                            com.jingdong.sdk.jdupgrade.a.h.c cVar = a.f15071e;
                            versionEntity.installTitle = cVar.f15054e;
                            versionEntity.installText = cVar.f15056g;
                            versionEntity.installConfirm = cVar.f15057h;
                            versionEntity.installCancel = cVar.f15058i;
                            com.jingdong.sdk.jdupgrade.a.h.b bVar = a.d;
                            versionEntity.downloadTitle = bVar.f15054e;
                            versionEntity.downloadText = bVar.f15056g;
                            versionEntity.downloadConfirm = bVar.f15057h;
                            versionEntity.downloadCancel = bVar.f15058i;
                            if (!TextUtils.isEmpty(a.f15074h)) {
                                JSONObject jSONObject = new JSONObject(a.f15074h);
                                versionEntity.packageList = jSONObject.optString("packageList");
                                versionEntity.icon = jSONObject.optString("icon");
                                versionEntity.downloadWlan = jSONObject.optString("downloadWlan");
                                if (!TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("isAutoDownload")) && !TextUtils.equals("TRUE", jSONObject.optString("isAutoDownload")) && !TextUtils.equals("1", jSONObject.optString("isAutoDownload"))) {
                                    z = false;
                                    versionEntity.isAutoDownload = z;
                                    versionEntity.toast = jSONObject.optString(XView2Constants.XVIEW2_ACTION_TOAST);
                                    if (!TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("myJdSettings")) && !TextUtils.equals("TRUE", jSONObject.optString("myJdSettings")) && !TextUtils.equals("1", jSONObject.optString("myJdSettings"))) {
                                        z2 = false;
                                        versionEntity.myJdSettings = z2;
                                        versionEntity.myJdUserSettings = !TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("myJdUserSettings")) || TextUtils.equals("TRUE", jSONObject.optString("myJdUserSettings")) || TextUtils.equals("1", jSONObject.optString("myJdUserSettings"));
                                        versionEntity.requestInterval = jSONObject.optInt("requestInterval");
                                    }
                                    z2 = true;
                                    versionEntity.myJdSettings = z2;
                                    versionEntity.myJdUserSettings = !TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("myJdUserSettings")) || TextUtils.equals("TRUE", jSONObject.optString("myJdUserSettings")) || TextUtils.equals("1", jSONObject.optString("myJdUserSettings"));
                                    versionEntity.requestInterval = jSONObject.optInt("requestInterval");
                                }
                                z = true;
                                versionEntity.isAutoDownload = z;
                                versionEntity.toast = jSONObject.optString(XView2Constants.XVIEW2_ACTION_TOAST);
                                if (!TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("myJdSettings"))) {
                                    z2 = false;
                                    versionEntity.myJdSettings = z2;
                                    versionEntity.myJdUserSettings = !TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("myJdUserSettings")) || TextUtils.equals("TRUE", jSONObject.optString("myJdUserSettings")) || TextUtils.equals("1", jSONObject.optString("myJdUserSettings"));
                                    versionEntity.requestInterval = jSONObject.optInt("requestInterval");
                                }
                                z2 = true;
                                versionEntity.myJdSettings = z2;
                                versionEntity.myJdUserSettings = !TextUtils.equals(DYConstants.DY_TRUE, jSONObject.optString("myJdUserSettings")) || TextUtils.equals("TRUE", jSONObject.optString("myJdUserSettings")) || TextUtils.equals("1", jSONObject.optString("myJdUserSettings"));
                                versionEntity.requestInterval = jSONObject.optInt("requestInterval");
                            }
                        } catch (Throwable unused3) {
                        }
                    }
                    f.this.a.onResult(versionEntity);
                    return;
                }
            } else if (f.this.a == null) {
                return;
            }
            f.this.a.onResult(null);
        }
    }

    public f(JDMallUpgradeInfoCallBack jDMallUpgradeInfoCallBack) {
        this.a = jDMallUpgradeInfoCallBack;
    }

    public void b() {
        j.c().execute(new a());
    }
}
