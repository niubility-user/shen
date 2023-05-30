package com.huawei.hms.framework.network.grs.g;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.tencent.mapsdk.internal.i2;
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
import org.json.JSONObject;

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
    */
    private void p() {
        if (m()) {
            Logger.i(o, "GRSSDK get httpcode{304} not any changed.");
            c(1);
        } else if (!o()) {
            Logger.i(o, "GRSSDK parse server body all failed.");
            c(2);
        } else {
            try {
                JSONObject jSONObject = new JSONObject(StringUtils.byte2Str(this.b));
                int i2 = -1;
                if (jSONObject.has("isSuccess")) {
                    if (jSONObject.getInt("isSuccess") == 1) {
                        i2 = 1;
                    }
                    i2 = 2;
                } else if (!jSONObject.has("resultCode")) {
                    Logger.e(o, "sth. wrong because server errorcode's key.");
                }
                if (i2 != 1 && jSONObject.has(i2.d)) {
                    i2 = 0;
                }
                c(i2);
                if (i2 == 1 || i2 == 0) {
                    f(jSONObject.has(i2.d) ? jSONObject.getJSONObject(i2.d).toString() : "");
                    e(jSONObject.has("errorList") ? jSONObject.getJSONObject("errorList").toString() : "");
                    return;
                }
                b(jSONObject.has("errorCode") ? jSONObject.getInt("errorCode") : 9001);
                d(jSONObject.has("errorDesc") ? jSONObject.getString("errorDesc") : "");
            } catch (JSONException e2) {
                Logger.w(o, "GrsResponse GrsResponse(String result) JSONException: %s", StringUtils.anonymizeMessage(e2.getMessage()));
                c(2);
            }
        }
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
