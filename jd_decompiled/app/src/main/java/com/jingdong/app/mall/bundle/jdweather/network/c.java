package com.jingdong.app.mall.bundle.jdweather.network;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private d f8200g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(d dVar) {
        this.f8200g = dVar;
    }

    private void a(HttpURLConnection httpURLConnection, boolean z) throws ProtocolException {
        if (this.f8200g == null || httpURLConnection == null) {
            return;
        }
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setInstanceFollowRedirects(z);
        HttpURLConnection.setFollowRedirects(z);
        httpURLConnection.setReadTimeout(this.f8200g.g());
        httpURLConnection.setConnectTimeout(this.f8200g.c());
        if (TextUtils.isEmpty(this.f8200g.d())) {
            return;
        }
        httpURLConnection.addRequestProperty("Cookie", this.f8200g.d());
    }

    private String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append("nmyorderh5.m.jd.com");
        sb.append("/");
        d dVar = this.f8200g;
        if (dVar != null && !TextUtils.isEmpty(dVar.e())) {
            sb.append(this.f8200g.e());
        }
        sb.append("?");
        sb.append(g());
        return sb.toString();
    }

    private String c(String str, String str2, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            try {
                str2 = URLEncoder.encode(str2, "UTF-8");
            } catch (Exception e2) {
                com.jingdong.app.mall.bundle.jdweather.c.a.d(e2);
            }
            if (z) {
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(str2);
        }
        return sb.toString();
    }

    private void d(String str) {
        b f2;
        d dVar = this.f8200g;
        if (dVar == null || (f2 = dVar.f()) == null) {
            return;
        }
        f2.onError(str);
    }

    private void e() {
        b f2;
        d dVar = this.f8200g;
        if (dVar == null || (f2 = dVar.f()) == null) {
            return;
        }
        f2.onReady();
    }

    private void f(String str) {
        b f2;
        d dVar = this.f8200g;
        if (dVar == null || (f2 = dVar.f()) == null) {
            return;
        }
        f2.onSuccess(str);
    }

    private String g() {
        StringBuilder sb = new StringBuilder();
        d dVar = this.f8200g;
        if (dVar != null && dVar.a() != null) {
            Map<String, String> a = this.f8200g.a();
            int i2 = 0;
            for (String str : a.keySet()) {
                sb.append(c(str, a.get(str), i2 != 0));
                i2++;
            }
        }
        return sb.toString();
    }

    private String h() {
        StringBuilder sb = new StringBuilder();
        d dVar = this.f8200g;
        String jSONObject = (dVar == null || dVar.b() == null) ? "" : new JSONObject(this.f8200g.b()).toString();
        HashMap hashMap = new HashMap();
        hashMap.put("body", jSONObject);
        for (String str : hashMap.keySet()) {
            sb.append(c(str, (String) hashMap.get(str), false));
        }
        com.jingdong.app.mall.bundle.jdweather.c.a.a("HttpRequest", "request body\t" + sb.toString());
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01c1 A[Catch: Exception -> 0x01bd, TryCatch #11 {Exception -> 0x01bd, blocks: (B:96:0x01b9, B:100:0x01c1, B:102:0x01c6), top: B:108:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01c6 A[Catch: Exception -> 0x01bd, TRY_LEAVE, TryCatch #11 {Exception -> 0x01bd, blocks: (B:96:0x01b9, B:100:0x01c1, B:102:0x01c6), top: B:108:0x01b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01b9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01a1 A[Catch: Exception -> 0x01a5, TRY_ENTER, TryCatch #15 {Exception -> 0x01a5, blocks: (B:48:0x015d, B:50:0x0165, B:84:0x01a1, B:88:0x01a9, B:90:0x01ae), top: B:112:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a9 A[Catch: Exception -> 0x01a5, TryCatch #15 {Exception -> 0x01a5, blocks: (B:48:0x015d, B:50:0x0165, B:84:0x01a1, B:88:0x01a9, B:90:0x01ae), top: B:112:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ae A[Catch: Exception -> 0x01a5, TRY_LEAVE, TryCatch #15 {Exception -> 0x01a5, blocks: (B:48:0x015d, B:50:0x0165, B:84:0x01a1, B:88:0x01a9, B:90:0x01ae), top: B:112:0x000b }] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 464
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.jdweather.network.c.run():void");
    }
}
