package com.huawei.hms.framework.network.grs.g;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes12.dex */
public class d {
    private static final String o = "d";
    private Map<String, List<String>> a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private int f1325c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private long f1326e;

    /* renamed from: f  reason: collision with root package name */
    private long f1327f;

    /* renamed from: g  reason: collision with root package name */
    private String f1328g;

    /* renamed from: h  reason: collision with root package name */
    private int f1329h;

    /* renamed from: i  reason: collision with root package name */
    private int f1330i;

    /* renamed from: j  reason: collision with root package name */
    private String f1331j;

    /* renamed from: k  reason: collision with root package name */
    private long f1332k;

    /* renamed from: l  reason: collision with root package name */
    private String f1333l;

    /* renamed from: m  reason: collision with root package name */
    private Exception f1334m;

    /* renamed from: n  reason: collision with root package name */
    private String f1335n;

    public d(int i2, Map<String, List<String>> map, byte[] bArr, long j2) {
        this.f1325c = 0;
        this.f1329h = 2;
        this.f1330i = 9001;
        this.f1331j = "";
        this.f1332k = 0L;
        this.f1333l = "";
        this.f1325c = i2;
        this.a = map;
        this.b = ByteBuffer.wrap(bArr).array();
        this.d = j2;
        s();
    }

    public d(Exception exc, long j2) {
        this.f1325c = 0;
        this.f1329h = 2;
        this.f1330i = 9001;
        this.f1331j = "";
        this.f1332k = 0L;
        this.f1333l = "";
        this.f1334m = exc;
        this.d = j2;
    }

    private void a(Map<String, String> map) {
        String str;
        String str2;
        if (map.containsKey(HttpHeaders.ETAG)) {
            String str3 = map.get(HttpHeaders.ETAG);
            if (!TextUtils.isEmpty(str3)) {
                Logger.i(o, "success get Etag from server");
                a(str3);
                return;
            }
            str = o;
            str2 = "The Response Heads Etag is Empty";
        } else {
            str = o;
            str2 = "Response Heads has not Etag";
        }
        Logger.i(str, str2);
    }

    private void b(int i2) {
        this.f1330i = i2;
    }

    private void b(Map<String, String> map) {
        long time;
        if (map.containsKey(HttpHeaders.CACHE_CONTROL)) {
            String str = map.get(HttpHeaders.CACHE_CONTROL);
            if (!TextUtils.isEmpty(str) && str.contains("max-age=")) {
                try {
                    time = Long.parseLong(str.substring(str.indexOf("max-age=") + 8));
                } catch (NumberFormatException e2) {
                    e = e2;
                    time = 0;
                }
                try {
                    Logger.v(o, "Cache-Control value{%s}", Long.valueOf(time));
                } catch (NumberFormatException e3) {
                    e = e3;
                    Logger.w(o, "getExpireTime addHeadersToResult NumberFormatException", e);
                    if (time > 0) {
                    }
                    time = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
                    long j2 = time * 1000;
                    Logger.i(o, "convert expireTime{%s}", Long.valueOf(j2));
                    c(String.valueOf(j2 + System.currentTimeMillis()));
                }
            }
            time = 0;
        } else {
            if (map.containsKey(HttpHeaders.EXPIRES)) {
                String str2 = map.get(HttpHeaders.EXPIRES);
                Logger.v(o, "expires is{%s}", str2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ROOT);
                String str3 = map.containsKey(HttpHeaders.DATE) ? map.get(HttpHeaders.DATE) : null;
                try {
                    time = (simpleDateFormat.parse(str2).getTime() - (TextUtils.isEmpty(str3) ? new Date() : simpleDateFormat.parse(str3)).getTime()) / 1000;
                } catch (ParseException e4) {
                    Logger.w(o, "getExpireTime ParseException.", e4);
                }
            } else {
                Logger.i(o, "response headers neither contains Cache-Control nor Expires.");
            }
            time = 0;
        }
        if (time > 0 || time > 2592000) {
            time = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        }
        long j22 = time * 1000;
        Logger.i(o, "convert expireTime{%s}", Long.valueOf(j22));
        c(String.valueOf(j22 + System.currentTimeMillis()));
    }

    private void c(int i2) {
        this.f1329h = i2;
    }

    private void c(long j2) {
        this.f1332k = j2;
    }

    private void c(String str) {
        this.f1331j = str;
    }

    private void c(Map<String, String> map) {
        long j2;
        if (map.containsKey(HttpHeaders.RETRY_AFTER)) {
            String str = map.get(HttpHeaders.RETRY_AFTER);
            if (!TextUtils.isEmpty(str)) {
                try {
                    j2 = Long.parseLong(str);
                } catch (NumberFormatException e2) {
                    Logger.w(o, "getRetryAfter addHeadersToResult NumberFormatException", e2);
                }
                long j3 = j2 * 1000;
                Logger.v(o, "convert retry-afterTime{%s}", Long.valueOf(j3));
                c(j3);
            }
        }
        j2 = 0;
        long j32 = j2 * 1000;
        Logger.v(o, "convert retry-afterTime{%s}", Long.valueOf(j32));
        c(j32);
    }

    private void d(String str) {
    }

    private void e(String str) {
    }

    private void f(String str) {
        this.f1328g = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r9.getInt("resultCode") == 0) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void p() {
        /*
            r11 = this;
            java.lang.String r0 = "errorDesc"
            java.lang.String r1 = "errorList"
            java.lang.String r2 = "errorCode"
            java.lang.String r3 = "resultCode"
            java.lang.String r4 = "isSuccess"
            boolean r5 = r11.m()
            r6 = 1
            if (r5 == 0) goto L1c
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.d.o
            java.lang.String r1 = "GRSSDK get httpcode{304} not any changed."
            com.huawei.hms.framework.common.Logger.i(r0, r1)
            r11.c(r6)
            return
        L1c:
            boolean r5 = r11.o()
            r7 = 2
            if (r5 != 0) goto L2e
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.d.o
            java.lang.String r1 = "GRSSDK parse server body all failed."
            com.huawei.hms.framework.common.Logger.i(r0, r1)
            r11.c(r7)
            return
        L2e:
            r5 = 0
            byte[] r8 = r11.b     // Catch: org.json.JSONException -> Lb7
            java.lang.String r8 = com.huawei.hms.framework.common.StringUtils.byte2Str(r8)     // Catch: org.json.JSONException -> Lb7
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lb7
            r9.<init>(r8)     // Catch: org.json.JSONException -> Lb7
            r8 = -1
            boolean r10 = r9.has(r4)     // Catch: org.json.JSONException -> Lb7
            if (r10 == 0) goto L48
            int r3 = r9.getInt(r4)     // Catch: org.json.JSONException -> Lb7
            if (r3 != r6) goto L56
            goto L54
        L48:
            boolean r4 = r9.has(r3)     // Catch: org.json.JSONException -> Lb7
            if (r4 == 0) goto L58
            int r3 = r9.getInt(r3)     // Catch: org.json.JSONException -> Lb7
            if (r3 != 0) goto L56
        L54:
            r8 = 1
            goto L5f
        L56:
            r8 = 2
            goto L5f
        L58:
            java.lang.String r3 = com.huawei.hms.framework.network.grs.g.d.o     // Catch: org.json.JSONException -> Lb7
            java.lang.String r4 = "sth. wrong because server errorcode's key."
            com.huawei.hms.framework.common.Logger.e(r3, r4)     // Catch: org.json.JSONException -> Lb7
        L5f:
            java.lang.String r3 = "services"
            if (r8 == r6) goto L6a
            boolean r4 = r9.has(r3)     // Catch: org.json.JSONException -> Lb7
            if (r4 == 0) goto L6a
            r8 = 0
        L6a:
            r11.c(r8)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r4 = ""
            if (r8 == r6) goto L92
            if (r8 != 0) goto L74
            goto L92
        L74:
            boolean r1 = r9.has(r2)     // Catch: org.json.JSONException -> Lb7
            if (r1 == 0) goto L7f
            int r1 = r9.getInt(r2)     // Catch: org.json.JSONException -> Lb7
            goto L81
        L7f:
            r1 = 9001(0x2329, float:1.2613E-41)
        L81:
            r11.b(r1)     // Catch: org.json.JSONException -> Lb7
            boolean r1 = r9.has(r0)     // Catch: org.json.JSONException -> Lb7
            if (r1 == 0) goto L8e
            java.lang.String r4 = r9.getString(r0)     // Catch: org.json.JSONException -> Lb7
        L8e:
            r11.d(r4)     // Catch: org.json.JSONException -> Lb7
            goto Lce
        L92:
            boolean r0 = r9.has(r3)     // Catch: org.json.JSONException -> Lb7
            if (r0 == 0) goto La1
            org.json.JSONObject r0 = r9.getJSONObject(r3)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r0 = r0.toString()     // Catch: org.json.JSONException -> Lb7
            goto La2
        La1:
            r0 = r4
        La2:
            r11.f(r0)     // Catch: org.json.JSONException -> Lb7
            boolean r0 = r9.has(r1)     // Catch: org.json.JSONException -> Lb7
            if (r0 == 0) goto Lb3
            org.json.JSONObject r0 = r9.getJSONObject(r1)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r4 = r0.toString()     // Catch: org.json.JSONException -> Lb7
        Lb3:
            r11.e(r4)     // Catch: org.json.JSONException -> Lb7
            goto Lce
        Lb7:
            r0 = move-exception
            java.lang.String r1 = com.huawei.hms.framework.network.grs.g.d.o
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r0)
            r2[r5] = r0
            java.lang.String r0 = "GrsResponse GrsResponse(String result) JSONException: %s"
            com.huawei.hms.framework.common.Logger.w(r1, r0, r2)
            r11.c(r7)
        Lce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.g.d.p():void");
    }

    private void q() {
        if (o() || n() || m()) {
            Map<String, String> r = r();
            if (r.size() <= 0) {
                Logger.w(o, "parseHeader {headers.size() <= 0}");
                return;
            }
            try {
                if (o() || m()) {
                    b(r);
                    a(r);
                }
                if (n()) {
                    c(r);
                }
            } catch (JSONException e2) {
                Logger.w(o, "parseHeader catch JSONException: %s", StringUtils.anonymizeMessage(e2.getMessage()));
            }
        }
    }

    private Map<String, String> r() {
        HashMap hashMap = new HashMap(16);
        Map<String, List<String>> map = this.a;
        if (map == null || map.size() <= 0) {
            Logger.v(o, "parseRespHeaders {respHeaders == null} or {respHeaders.size() <= 0}");
            return hashMap;
        }
        for (Map.Entry<String, List<String>> entry : this.a.entrySet()) {
            String key = entry.getKey();
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                hashMap.put(key, it.next());
            }
        }
        return hashMap;
    }

    private void s() {
        q();
        p();
    }

    public String a() {
        return this.f1331j;
    }

    public void a(int i2) {
    }

    public void a(long j2) {
        this.f1327f = j2;
    }

    public void a(String str) {
        this.f1333l = str;
    }

    public int b() {
        return this.f1325c;
    }

    public void b(long j2) {
        this.f1326e = j2;
    }

    public void b(String str) {
        this.f1335n = str;
    }

    public int c() {
        return this.f1330i;
    }

    public Exception d() {
        return this.f1334m;
    }

    public String e() {
        return this.f1333l;
    }

    public int f() {
        return this.f1329h;
    }

    public long g() {
        return this.f1327f;
    }

    public long h() {
        return this.f1326e;
    }

    public long i() {
        return this.d;
    }

    public String j() {
        return this.f1328g;
    }

    public long k() {
        return this.f1332k;
    }

    public String l() {
        return this.f1335n;
    }

    public boolean m() {
        return this.f1325c == 304;
    }

    public boolean n() {
        return this.f1325c == 503;
    }

    public boolean o() {
        return this.f1325c == 200;
    }
}
