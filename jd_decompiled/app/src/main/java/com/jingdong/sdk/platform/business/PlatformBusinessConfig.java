package com.jingdong.sdk.platform.business;

import android.content.Context;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.config.PlatformConfig;
import com.jingdong.sdk.platform.config.PlatformLog;
import com.jingdong.sdk.platform.config.PlatformPlugin;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class PlatformBusinessConfig {
    public static PlatformConfig.PlatformConfigBuilder getPlatformBuilder(boolean z, final Context context) {
        return PlatformConfig.PlatformConfigBuilder.create(z).platformLog(new PlatformLog() { // from class: com.jingdong.sdk.platform.business.PlatformBusinessConfig.2
            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void d(String str, String str2) {
                OKLog.d(str, str2);
            }

            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void e(String str, String str2) {
                OKLog.e(str, str2);
            }

            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void i(String str, String str2) {
                OKLog.i(str, str2);
            }

            @Override // com.jingdong.sdk.platform.config.PlatformLog
            public void reportException(Throwable th) {
                ExceptionReporter.reportExceptionToBugly(th);
            }
        }).platformList(new PlatformPlugin() { // from class: com.jingdong.sdk.platform.business.PlatformBusinessConfig.1
            @Override // com.jingdong.sdk.platform.config.PlatformPlugin
            public List<String> getInitList() {
                return PlatformBusinessConfig.getPluginList(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getPluginList(Context context) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("com.jingdong.sdk.platform.business.Init");
        arrayList.add("com.jingdong.sdk.platform.business.personal.Init");
        arrayList.add("com.jingdong.sdk.platform.business.puppet.Init");
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0077 A[Catch: IOException -> 0x0073, TRY_LEAVE, TryCatch #4 {IOException -> 0x0073, blocks: (B:48:0x006f, B:52:0x0077), top: B:64:0x006f }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.json.JSONArray readInitJson(android.content.Context r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.String r2 = "platform_inits.json"
            java.io.InputStream r4 = r4.open(r2)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            if (r4 != 0) goto L1d
            if (r4 == 0) goto L1c
            r4.close()     // Catch: java.io.IOException -> L18
            goto L1c
        L18:
            r4 = move-exception
            r4.printStackTrace()
        L1c:
            return r1
        L1d:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L3e
        L27:
            java.lang.String r3 = r2.readLine()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L6b
            if (r3 == 0) goto L31
            r0.append(r3)     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L6b
            goto L27
        L31:
            r2.close()     // Catch: java.io.IOException -> L50
            if (r4 == 0) goto L5b
            r4.close()     // Catch: java.io.IOException -> L50
            goto L5b
        L3a:
            r3 = move-exception
            goto L47
        L3c:
            r0 = move-exception
            goto L6d
        L3e:
            r3 = move-exception
            r2 = r1
            goto L47
        L41:
            r0 = move-exception
            r4 = r1
            goto L6d
        L44:
            r3 = move-exception
            r4 = r1
            r2 = r4
        L47:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L6b
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.io.IOException -> L50
            goto L52
        L50:
            r4 = move-exception
            goto L58
        L52:
            if (r4 == 0) goto L5b
            r4.close()     // Catch: java.io.IOException -> L50
            goto L5b
        L58:
            r4.printStackTrace()
        L5b:
            java.lang.String r4 = r0.toString()
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch: org.json.JSONException -> L66
            r0.<init>(r4)     // Catch: org.json.JSONException -> L66
            r1 = r0
            goto L6a
        L66:
            r4 = move-exception
            r4.printStackTrace()
        L6a:
            return r1
        L6b:
            r0 = move-exception
            r1 = r2
        L6d:
            if (r1 == 0) goto L75
            r1.close()     // Catch: java.io.IOException -> L73
            goto L75
        L73:
            r4 = move-exception
            goto L7b
        L75:
            if (r4 == 0) goto L7e
            r4.close()     // Catch: java.io.IOException -> L73
            goto L7e
        L7b:
            r4.printStackTrace()
        L7e:
            goto L80
        L7f:
            throw r0
        L80:
            goto L7f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.platform.business.PlatformBusinessConfig.readInitJson(android.content.Context):org.json.JSONArray");
    }
}
