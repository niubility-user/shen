package com.jingdong.sdk.jdupgrade.a;

import android.text.TextUtils;
import com.jingdong.sdk.jdupgrade.VersionInfo;
import com.jingdong.sdk.jdupgrade.VersionInfoCallback;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.i;
import com.jingdong.sdk.jdupgrade.a.j.j;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class g {
    private VersionInfoCallback a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            if (i.b()) {
                com.jingdong.sdk.jdupgrade.a.h.f a = com.jingdong.sdk.jdupgrade.a.h.f.a(j.e());
                if (a != null) {
                    VersionInfo versionInfo = new VersionInfo();
                    try {
                        versionInfo.state = Integer.parseInt(a.a.a());
                    } catch (Throwable th) {
                        th.printStackTrace();
                        versionInfo.state = 300;
                    }
                    com.jingdong.sdk.jdupgrade.a.h.d dVar = a.f15070c;
                    if (dVar != null && a.f15071e != null && a.d != null) {
                        try {
                            versionInfo.size = Long.parseLong(dVar.d);
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                        try {
                            com.jingdong.sdk.jdupgrade.a.h.d dVar2 = a.f15070c;
                            versionInfo.version = dVar2.a;
                            versionInfo.build = dVar2.b;
                            versionInfo.url = dVar2.f15059c;
                            versionInfo.urlMd5 = dVar2.f15062g;
                            versionInfo.fileMd5 = dVar2.f15060e;
                            com.jingdong.sdk.jdupgrade.a.h.c cVar = a.f15071e;
                            versionInfo.installTitle = cVar.f15054e;
                            versionInfo.installText = cVar.f15056g;
                            versionInfo.installConfirm = cVar.f15057h;
                            versionInfo.installCancel = cVar.f15058i;
                            com.jingdong.sdk.jdupgrade.a.h.b bVar = a.d;
                            versionInfo.downloadTitle = bVar.f15054e;
                            versionInfo.downloadText = bVar.f15056g;
                            versionInfo.downloadConfirm = bVar.f15057h;
                            versionInfo.downloadCancel = bVar.f15058i;
                            if (!TextUtils.isEmpty(a.f15074h)) {
                                versionInfo.customizeFields = new JSONObject(a.f15074h);
                            }
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    g.this.a.onSuccess(versionInfo);
                    return;
                }
                str = "upgrade info null";
            } else {
                str = "network not available";
            }
            h.b("NetworkHelper", str);
            g.this.a.onError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(VersionInfoCallback versionInfoCallback) {
        this.a = versionInfoCallback;
    }

    public void b() {
        VersionInfoCallback versionInfoCallback = this.a;
        if (versionInfoCallback == null) {
            return;
        }
        versionInfoCallback.onStart();
        j.c().execute(new a());
    }
}
