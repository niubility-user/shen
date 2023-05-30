package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.tencent.mapsdk.internal.i2;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes12.dex */
public class g extends a implements Callable<d> {

    /* renamed from: i  reason: collision with root package name */
    private static final String f1338i = "g";

    public g(String str, int i2, c cVar, Context context, String str2, GrsBaseInfo grsBaseInfo, com.huawei.hms.framework.network.grs.e.c cVar2) {
        super(str, i2, cVar, context, str2, grsBaseInfo, cVar2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0132: MOVE (r6 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]), block:B:63:0x0132 */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0125  */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public d call() {
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnection2;
        long j2;
        long currentTimeMillis;
        long elapsedRealtime;
        String str = f1338i;
        Logger.i(str, "Post call execute");
        long j3 = 0;
        HttpsURLConnection httpsURLConnection3 = null;
        InputStream inputStream = null;
        byte[] bArr = null;
        try {
            try {
                try {
                    elapsedRealtime = SystemClock.elapsedRealtime();
                    try {
                        j3 = System.currentTimeMillis();
                        httpsURLConnection2 = com.huawei.hms.framework.network.grs.h.f.a.a(c(), a(), e());
                        try {
                        } catch (IOException e2) {
                            e = e2;
                            long j4 = j3;
                            j3 = elapsedRealtime;
                            j2 = j4;
                            long elapsedRealtime2 = SystemClock.elapsedRealtime();
                            currentTimeMillis = System.currentTimeMillis();
                            Logger.w(f1338i, "RequestCallableV2 run task catch IOException", e);
                            this.a = new d(e, elapsedRealtime2 - j3);
                            if (httpsURLConnection2 != null) {
                                try {
                                    httpsURLConnection2.disconnect();
                                } catch (RuntimeException unused) {
                                    j3 = j2;
                                    Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                                    this.a.b(c());
                                    this.a.a(d());
                                    this.a.b(j3);
                                    this.a.a(currentTimeMillis);
                                    if (b() != null) {
                                    }
                                    return this.a;
                                } catch (Throwable unused2) {
                                    j3 = j2;
                                    Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                                    this.a.b(c());
                                    this.a.a(d());
                                    this.a.b(j3);
                                    this.a.a(currentTimeMillis);
                                    if (b() != null) {
                                    }
                                    return this.a;
                                }
                            }
                            j3 = j2;
                            this.a.b(c());
                            this.a.a(d());
                            this.a.b(j3);
                            this.a.a(currentTimeMillis);
                            if (b() != null) {
                            }
                            return this.a;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        httpsURLConnection2 = null;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (httpsURLConnection3 != null) {
                        try {
                            httpsURLConnection3.disconnect();
                        } catch (RuntimeException unused3) {
                            Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                        } catch (Throwable unused4) {
                            Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                httpsURLConnection2 = null;
                j2 = 0;
            }
            if (httpsURLConnection2 == null) {
                Logger.w(str, "create HttpsURLConnection instance by url return null.");
                if (httpsURLConnection2 != null) {
                    try {
                        httpsURLConnection2.disconnect();
                    } catch (RuntimeException unused5) {
                        Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                    } catch (Throwable unused6) {
                        Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                    }
                }
                return null;
            }
            httpsURLConnection2.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            httpsURLConnection2.setRequestMethod("POST");
            httpsURLConnection2.setDoOutput(true);
            httpsURLConnection2.setDoInput(true);
            String a = b() != null ? b().a() : "";
            if (TextUtils.isEmpty(a)) {
                a = ContainerUtils.FIELD_DELIMITER;
            }
            httpsURLConnection2.setRequestProperty(HttpHeaders.IF_NONE_MATCH, a);
            httpsURLConnection2.connect();
            com.huawei.hms.framework.network.grs.h.f.a.a(httpsURLConnection2, f().a(i2.d, ""));
            int responseCode = httpsURLConnection2.getResponseCode();
            if (responseCode == 200) {
                try {
                    inputStream = httpsURLConnection2.getInputStream();
                    byte[] byteArray = IoUtils.toByteArray(inputStream);
                    IoUtils.closeSecure(inputStream);
                    bArr = byteArray;
                } catch (Throwable th2) {
                    IoUtils.closeSecure(inputStream);
                    throw th2;
                }
            }
            Map headerFields = httpsURLConnection2.getHeaderFields();
            httpsURLConnection2.disconnect();
            long elapsedRealtime3 = SystemClock.elapsedRealtime();
            currentTimeMillis = System.currentTimeMillis();
            this.a = new d(responseCode, headerFields, bArr == null ? new byte[0] : bArr, elapsedRealtime3 - elapsedRealtime);
            if (httpsURLConnection2 != null) {
                try {
                    httpsURLConnection2.disconnect();
                } catch (RuntimeException unused7) {
                    Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch RuntimeException");
                    this.a.b(c());
                    this.a.a(d());
                    this.a.b(j3);
                    this.a.a(currentTimeMillis);
                    if (b() != null) {
                    }
                    return this.a;
                } catch (Throwable unused8) {
                    Logger.w(f1338i, "RequestCallableV2 disconnect HttpsURLConnection catch Throwable");
                    this.a.b(c());
                    this.a.a(d());
                    this.a.b(j3);
                    this.a.a(currentTimeMillis);
                    if (b() != null) {
                    }
                    return this.a;
                }
            }
            this.a.b(c());
            this.a.a(d());
            this.a.b(j3);
            this.a.a(currentTimeMillis);
            if (b() != null) {
                b().a(this.a);
            }
            return this.a;
        } catch (Throwable th3) {
            th = th3;
            httpsURLConnection3 = httpsURLConnection;
        }
    }
}
