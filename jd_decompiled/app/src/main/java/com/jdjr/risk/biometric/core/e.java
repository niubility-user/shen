package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.jdcn.risk.cpp.LoadDoor;
import com.jdjr.risk.device.c.l;
import com.jdjr.risk.util.b.h;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import com.jingdong.common.search.FilterConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes18.dex */
public class e {
    public static ThreadLocal<Bundle> a = new ThreadLocal<Bundle>() { // from class: com.jdjr.risk.biometric.core.e.1
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Bundle initialValue() {
            return new Bundle();
        }
    };
    private String b;

    /* renamed from: f */
    private String f7310f;

    /* renamed from: j */
    private String f7314j;

    /* renamed from: k */
    private String f7315k;

    /* renamed from: l */
    private String f7316l;

    /* renamed from: m */
    private boolean f7317m;

    /* renamed from: c */
    private long f7308c = 0;
    private long d = 0;

    /* renamed from: g */
    private long f7311g = 0;

    /* renamed from: h */
    private long f7312h = 0;

    /* renamed from: n */
    private String f7318n = "UTF-8";

    /* renamed from: e */
    private ReadWriteLock f7309e = new ReentrantReadWriteLock();

    /* renamed from: i */
    private ReadWriteLock f7313i = new ReentrantReadWriteLock();

    public e() {
        this.f7317m = false;
        this.f7317m = BaseInfo.isAgreedPrivacy();
    }

    public static String a() {
        return BaseInfo.isAgreedPrivacy() ? "0" : "1";
    }

    private String a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            byte[] bytes = str.getBytes();
            if (bytes.length > 0) {
                for (int i2 = 0; i2 < bytes.length; i2++) {
                    bytes[i2] = (byte) (bytes[i2] + 1);
                }
                return new String(bytes, "UTF-8");
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    private boolean a(Context context, String str, String str2) {
        return LoadDoor.e().b(str, str2) == 0;
    }

    private void b(Context context, String str, long j2, long j3, String str2, String str3) {
        String str4 = "";
        String str5 = (TextUtils.isEmpty(str) || !str.startsWith("jdd01")) ? "" : str;
        if (!TextUtils.isEmpty(str) && str.startsWith("jdd02")) {
            str5 = h.a(str, str3, this.f7318n);
        }
        if (!TextUtils.isEmpty(str2) && str2.substring(0, 10).endsWith("82")) {
            str4 = h.b(str2, str3, this.f7318n);
        }
        if (!TextUtils.isEmpty(str2) && str2.substring(0, 10).endsWith("81")) {
            str4 = str2;
        }
        if (TextUtils.isEmpty(str5)) {
            return;
        }
        this.f7309e.writeLock().lock();
        try {
            this.b = str5;
            this.f7308c = j2;
            this.d = j3;
            if (!TextUtils.isEmpty(str4)) {
                this.f7314j = str4;
            }
        } catch (Throwable unused) {
        }
        this.f7309e.writeLock().unlock();
        SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
        edit.putString(a("jade"), str);
        edit.putString(a("jadeStamp"), String.valueOf(j2));
        edit.putString(a("jadeVal"), String.valueOf(j3));
        edit.putString(a("whisper"), str3);
        if (!TextUtils.isEmpty(str2)) {
            edit.putString(a("CCOJade"), str2);
        }
        edit.apply();
    }

    private void c(Context context, String str, long j2, long j3, String str2, String str3) {
        String str4 = "";
        String str5 = (TextUtils.isEmpty(str) || !str.startsWith("jdd01")) ? "" : str;
        if (!TextUtils.isEmpty(str) && str.startsWith("jdd02")) {
            str5 = h.a(str, str3, this.f7318n);
        }
        if (!TextUtils.isEmpty(str2) && str2.substring(0, 10).endsWith("82")) {
            str4 = h.b(str2, str3, this.f7318n);
        }
        if (!TextUtils.isEmpty(str2) && str2.substring(0, 10).endsWith("81")) {
            str4 = str2;
        }
        if (TextUtils.isEmpty(str5)) {
            return;
        }
        this.f7313i.writeLock().lock();
        try {
            this.f7310f = str5;
            this.f7311g = j2;
            this.f7312h = j3;
            if (!TextUtils.isEmpty(str4)) {
                this.f7315k = str4;
            }
        } catch (Throwable unused) {
        }
        this.f7313i.writeLock().unlock();
        SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
        edit.putString(a("visJade"), str);
        edit.putString(a("visJadeStamp"), String.valueOf(j2));
        edit.putString(a("visJadeVal"), String.valueOf(j3));
        edit.putString(a("visWhisper"), str3);
        if (!TextUtils.isEmpty(str2)) {
            edit.putString(a("visCCOJade"), str2);
        }
        edit.apply();
    }

    private String h(Context context) {
        String str;
        String string;
        String string2;
        String str2;
        String str3 = "";
        try {
            this.f7309e.readLock().lock();
            str = this.b;
            try {
                this.f7309e.readLock().unlock();
            } catch (Throwable unused) {
                str3 = str;
            }
        } catch (Throwable unused2) {
        }
        if (TextUtils.isEmpty(str)) {
            SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
            if (a2.contains("token")) {
                string = a2.getString("token", "");
                string2 = a2.getString("tokenTime", "0");
                str2 = a2.getString("tokenActTime", "0");
                if (!TextUtils.isEmpty(string)) {
                    SharedPreferences.Editor edit = a2.edit();
                    edit.putString(a("jade"), string);
                    edit.putString(a("jadeStamp"), string2);
                    edit.putString(a("jadeVal"), str2);
                    edit.remove("token");
                    edit.remove("tokenTime");
                    edit.remove("tokenActTime");
                    edit.apply();
                }
            } else {
                string = a2.getString(a("jade"), "");
                string2 = a2.getString(a("jadeStamp"), "0");
                String string3 = a2.getString(a("jadeVal"), "0");
                str3 = a2.getString(a("whisper"), "0");
                str2 = string3;
            }
            str3 = (TextUtils.isEmpty(string) || !string.startsWith("jdd02")) ? str : h.a(string, str3, this.f7318n);
            if (!TextUtils.isEmpty(string) && string.startsWith("jdd01")) {
                str3 = string;
            }
            if (!TextUtils.isEmpty(str3)) {
                this.f7309e.writeLock().lock();
                this.b = str3;
                this.f7308c = Long.parseLong(string2);
                this.d = Long.parseLong(str2);
                this.f7309e.writeLock().unlock();
            }
            return str3;
        }
        return str;
    }

    private String i(Context context) {
        String str;
        String string;
        String string2;
        String str2;
        String str3 = "";
        try {
            this.f7313i.readLock().lock();
            str = this.f7310f;
            try {
                this.f7313i.readLock().unlock();
            } catch (Throwable unused) {
                str3 = str;
            }
        } catch (Throwable unused2) {
        }
        if (TextUtils.isEmpty(str)) {
            SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
            if (a2.contains("guestToken")) {
                string = a2.getString("guestToken", "");
                string2 = a2.getString("guestTokenTime", "0");
                str2 = a2.getString("guestTokenActTime", "0");
                if (!TextUtils.isEmpty(string)) {
                    SharedPreferences.Editor edit = a2.edit();
                    edit.putString(a("visJade"), string);
                    edit.putString(a("visJadeStamp"), string2);
                    edit.putString(a("visJadeVal"), str2);
                    edit.remove("guestToken");
                    edit.remove("guestTokenTime");
                    edit.remove("guestTokenActTime");
                    edit.apply();
                }
            } else {
                string = a2.getString(a("visJade"), "");
                string2 = a2.getString(a("visJadeStamp"), "0");
                String string3 = a2.getString(a("visJadeVal"), "0");
                str3 = a2.getString(a("visWhisper"), "0");
                str2 = string3;
            }
            str3 = (TextUtils.isEmpty(string) || !string.startsWith("jdd02")) ? str : h.a(string, str3, this.f7318n);
            if (!TextUtils.isEmpty(string) && string.startsWith("jdd01")) {
                str3 = string;
            }
            if (!TextUtils.isEmpty(str3)) {
                this.f7313i.writeLock().lock();
                this.f7310f = str3;
                this.f7311g = Long.parseLong(string2);
                this.f7312h = Long.parseLong(str2);
                this.f7313i.writeLock().unlock();
            }
            return str3;
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x009b, code lost:
        if (r6.substring(0, 10).endsWith("81") != false) goto L112;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String j(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "CCOToken"
            java.lang.String r1 = "CCOJade"
            java.lang.String r2 = ""
            java.util.concurrent.locks.ReadWriteLock r3 = r9.f7309e     // Catch: java.lang.Throwable -> Ld8
            java.util.concurrent.locks.Lock r3 = r3.readLock()     // Catch: java.lang.Throwable -> Ld8
            r3.lock()     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r3 = r9.f7314j     // Catch: java.lang.Throwable -> L1b
            java.util.concurrent.locks.ReadWriteLock r4 = r9.f7309e     // Catch: java.lang.Throwable -> L34
            java.util.concurrent.locks.Lock r4 = r4.readLock()     // Catch: java.lang.Throwable -> L34
            r4.unlock()     // Catch: java.lang.Throwable -> L34
            goto L25
        L1b:
            java.util.concurrent.locks.ReadWriteLock r3 = r9.f7309e     // Catch: java.lang.Throwable -> Ld8
            java.util.concurrent.locks.Lock r3 = r3.readLock()     // Catch: java.lang.Throwable -> Ld8
            r3.unlock()     // Catch: java.lang.Throwable -> Ld8
            r3 = r2
        L25:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L34
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != 0) goto L37
            int r4 = r3.length()     // Catch: java.lang.Throwable -> L34
            if (r4 == r5) goto L34
            goto L37
        L34:
            r2 = r3
            goto Ld8
        L37:
            android.content.SharedPreferences r4 = com.jdjr.risk.util.a.d.a(r10)     // Catch: java.lang.Throwable -> L34
            java.lang.String r6 = r9.a(r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r6 = r4.getString(r6, r2)     // Catch: java.lang.Throwable -> L34
            java.lang.String r7 = "whisper"
            java.lang.String r7 = r9.a(r7)     // Catch: java.lang.Throwable -> L34
            java.lang.String r8 = "0"
            java.lang.String r7 = r4.getString(r7, r8)     // Catch: java.lang.Throwable -> L34
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            if (r8 == 0) goto L70
            java.lang.String r6 = r4.getString(r0, r2)     // Catch: java.lang.Throwable -> L34
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            if (r8 != 0) goto L70
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = r9.a(r1)     // Catch: java.lang.Throwable -> L34
            r4.putString(r1, r6)     // Catch: java.lang.Throwable -> L34
            r4.remove(r0)     // Catch: java.lang.Throwable -> L34
            r4.apply()     // Catch: java.lang.Throwable -> L34
        L70:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            r1 = 10
            r4 = 0
            if (r0 != 0) goto L8b
            java.lang.String r0 = r6.substring(r4, r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r8 = "82"
            boolean r0 = r0.endsWith(r8)     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L8b
            java.lang.String r0 = r9.f7318n     // Catch: java.lang.Throwable -> L34
            java.lang.String r3 = com.jdjr.risk.util.b.h.b(r6, r7, r0)     // Catch: java.lang.Throwable -> L34
        L8b:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            if (r0 != 0) goto L9e
            java.lang.String r0 = r6.substring(r4, r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = "81"
            boolean r0 = r0.endsWith(r1)     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L9e
            goto L9f
        L9e:
            r6 = r3
        L9f:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Ld7
            if (r0 != 0) goto Lab
            int r0 = r6.length()     // Catch: java.lang.Throwable -> Ld7
            if (r0 == r5) goto Laf
        Lab:
            java.lang.String r6 = com.jdjr.risk.device.c.l.a(r10)     // Catch: java.lang.Throwable -> Ld7
        Laf:
            boolean r10 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Ld7
            if (r10 != 0) goto Ld8
            int r10 = r6.length()     // Catch: java.lang.Throwable -> Ld7
            if (r10 != r5) goto Ld8
            java.util.concurrent.locks.ReadWriteLock r10 = r9.f7309e     // Catch: java.lang.Throwable -> Ld7
            java.util.concurrent.locks.Lock r10 = r10.writeLock()     // Catch: java.lang.Throwable -> Ld7
            r10.lock()     // Catch: java.lang.Throwable -> Ld7
            r9.f7314j = r6     // Catch: java.lang.Throwable -> Ld0
            java.util.concurrent.locks.ReadWriteLock r10 = r9.f7309e     // Catch: java.lang.Throwable -> Ld7
            java.util.concurrent.locks.Lock r10 = r10.writeLock()     // Catch: java.lang.Throwable -> Ld7
        Lcc:
            r10.unlock()     // Catch: java.lang.Throwable -> Ld7
            goto Ld7
        Ld0:
            java.util.concurrent.locks.ReadWriteLock r10 = r9.f7309e     // Catch: java.lang.Throwable -> Ld7
            java.util.concurrent.locks.Lock r10 = r10.writeLock()     // Catch: java.lang.Throwable -> Ld7
            goto Lcc
        Ld7:
            r2 = r6
        Ld8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.core.e.j(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x009b, code lost:
        if (r6.substring(0, 10).endsWith("81") != false) goto L112;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String k(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "guestCCOToken"
            java.lang.String r1 = "visCCOJade"
            java.lang.String r2 = ""
            java.util.concurrent.locks.ReadWriteLock r3 = r9.f7313i     // Catch: java.lang.Throwable -> Ld8
            java.util.concurrent.locks.Lock r3 = r3.readLock()     // Catch: java.lang.Throwable -> Ld8
            r3.lock()     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r3 = r9.f7315k     // Catch: java.lang.Throwable -> L1b
            java.util.concurrent.locks.ReadWriteLock r4 = r9.f7313i     // Catch: java.lang.Throwable -> L34
            java.util.concurrent.locks.Lock r4 = r4.readLock()     // Catch: java.lang.Throwable -> L34
            r4.unlock()     // Catch: java.lang.Throwable -> L34
            goto L25
        L1b:
            java.util.concurrent.locks.ReadWriteLock r3 = r9.f7313i     // Catch: java.lang.Throwable -> Ld8
            java.util.concurrent.locks.Lock r3 = r3.readLock()     // Catch: java.lang.Throwable -> Ld8
            r3.unlock()     // Catch: java.lang.Throwable -> Ld8
            r3 = r2
        L25:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L34
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != 0) goto L37
            int r4 = r3.length()     // Catch: java.lang.Throwable -> L34
            if (r4 == r5) goto L34
            goto L37
        L34:
            r2 = r3
            goto Ld8
        L37:
            android.content.SharedPreferences r4 = com.jdjr.risk.util.a.d.a(r10)     // Catch: java.lang.Throwable -> L34
            java.lang.String r6 = r9.a(r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r6 = r4.getString(r6, r2)     // Catch: java.lang.Throwable -> L34
            java.lang.String r7 = "visWhisper"
            java.lang.String r7 = r9.a(r7)     // Catch: java.lang.Throwable -> L34
            java.lang.String r8 = "0"
            java.lang.String r7 = r4.getString(r7, r8)     // Catch: java.lang.Throwable -> L34
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            if (r8 == 0) goto L70
            java.lang.String r6 = r4.getString(r0, r2)     // Catch: java.lang.Throwable -> L34
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            if (r8 != 0) goto L70
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = r9.a(r1)     // Catch: java.lang.Throwable -> L34
            r4.putString(r1, r6)     // Catch: java.lang.Throwable -> L34
            r4.remove(r0)     // Catch: java.lang.Throwable -> L34
            r4.apply()     // Catch: java.lang.Throwable -> L34
        L70:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            r1 = 10
            r4 = 0
            if (r0 != 0) goto L8b
            java.lang.String r0 = r6.substring(r4, r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r8 = "82"
            boolean r0 = r0.endsWith(r8)     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L8b
            java.lang.String r0 = r9.f7318n     // Catch: java.lang.Throwable -> L34
            java.lang.String r3 = com.jdjr.risk.util.b.h.b(r6, r7, r0)     // Catch: java.lang.Throwable -> L34
        L8b:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L34
            if (r0 != 0) goto L9e
            java.lang.String r0 = r6.substring(r4, r1)     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = "81"
            boolean r0 = r0.endsWith(r1)     // Catch: java.lang.Throwable -> L34
            if (r0 == 0) goto L9e
            goto L9f
        L9e:
            r6 = r3
        L9f:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Ld7
            if (r0 != 0) goto Lab
            int r0 = r6.length()     // Catch: java.lang.Throwable -> Ld7
            if (r0 == r5) goto Laf
        Lab:
            java.lang.String r6 = com.jdjr.risk.device.c.l.a(r10)     // Catch: java.lang.Throwable -> Ld7
        Laf:
            boolean r10 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Ld7
            if (r10 != 0) goto Ld8
            int r10 = r6.length()     // Catch: java.lang.Throwable -> Ld7
            if (r10 != r5) goto Ld8
            java.util.concurrent.locks.ReadWriteLock r10 = r9.f7313i     // Catch: java.lang.Throwable -> Ld7
            java.util.concurrent.locks.Lock r10 = r10.writeLock()     // Catch: java.lang.Throwable -> Ld7
            r10.lock()     // Catch: java.lang.Throwable -> Ld7
            r9.f7315k = r6     // Catch: java.lang.Throwable -> Ld0
            java.util.concurrent.locks.ReadWriteLock r10 = r9.f7313i     // Catch: java.lang.Throwable -> Ld7
            java.util.concurrent.locks.Lock r10 = r10.writeLock()     // Catch: java.lang.Throwable -> Ld7
        Lcc:
            r10.unlock()     // Catch: java.lang.Throwable -> Ld7
            goto Ld7
        Ld0:
            java.util.concurrent.locks.ReadWriteLock r10 = r9.f7313i     // Catch: java.lang.Throwable -> Ld7
            java.util.concurrent.locks.Lock r10 = r10.writeLock()     // Catch: java.lang.Throwable -> Ld7
            goto Lcc
        Ld7:
            r2 = r6
        Ld8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.core.e.k(android.content.Context):java.lang.String");
    }

    private String l(Context context) {
        try {
            String f2 = LoadDoor.e().f(context);
            if (f2 == null || f2.length() != 148) {
                return "";
            }
            String substring = f2.substring(0, 116);
            String substring2 = f2.substring(116);
            SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
            edit.putString(a("lcJade"), substring);
            edit.putString(a(FilterConstant.FIELD), substring2);
            edit.apply();
            return substring;
        } catch (Throwable unused) {
            return "";
        }
    }

    public String a(Context context) {
        return this.f7316l;
    }

    public void a(Context context, String str, long j2, long j3, String str2, String str3) {
        Bundle bundle;
        if (TextUtils.isEmpty(str) || str.startsWith(HttpInfoConstants.HTTPT_RSULT_PRE) || (bundle = a.get()) == null) {
            return;
        }
        if (bundle.getBoolean("agreedPrivacy") || bundle.getBoolean("tokenExist")) {
            b(context, str, j2, j3, str2, str3);
        } else {
            c(context, str, j2, j3, str2, str3);
        }
    }

    public void a(boolean z) {
        this.f7317m = z;
    }

    public String b(Context context) {
        String c2 = c(context);
        return TextUtils.isEmpty(c2) ? a(context) : c2;
    }

    public boolean b() {
        return this.f7317m;
    }

    public String c(Context context) {
        Bundle bundle = a.get();
        return bundle != null ? (bundle.getBoolean("agreedPrivacy") || bundle.getBoolean("tokenExist")) ? h(context) : i(context) : "";
    }

    public boolean d(Context context) {
        return !TextUtils.isEmpty(h(context));
    }

    public String e(Context context) {
        try {
            if (!a.get().getBoolean("cuid")) {
                return a.get().getBoolean("tokenExist") ? j(context) : "";
            }
            Bundle bundle = a.get();
            if (bundle != null) {
                if (!bundle.getBoolean("agreedPrivacy") && !bundle.getBoolean("tokenExist")) {
                    return k(context);
                }
                return j(context);
            }
            return l.a(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    public String f(Context context) {
        Bundle bundle = a.get();
        return bundle != null ? (bundle.getBoolean("agreedPrivacy") || bundle.getBoolean("tokenExist")) ? j(context) : k(context) : "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x006a, code lost:
        if (a(r9, r5, r6) != false) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String g(android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = "localtoken"
            java.lang.String r1 = "lcJade"
            java.lang.String r2 = "field"
            java.lang.String r3 = ""
            java.lang.String r4 = r8.f7316l     // Catch: java.lang.Throwable -> L72
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L72
            android.content.SharedPreferences r4 = com.jdjr.risk.util.a.d.a(r9)     // Catch: java.lang.Throwable -> L72
            java.lang.String r5 = r8.a(r1)     // Catch: java.lang.Throwable -> L72
            java.lang.String r5 = r4.getString(r5, r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r6 = r8.a(r2)     // Catch: java.lang.Throwable -> L72
            java.lang.String r6 = r4.getString(r6, r3)     // Catch: java.lang.Throwable -> L72
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L72
            if (r7 != 0) goto L30
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L72
            if (r7 == 0) goto L59
        L30:
            java.lang.String r5 = r4.getString(r0, r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r6 = r4.getString(r2, r3)     // Catch: java.lang.Throwable -> L72
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L72
            if (r3 != 0) goto L59
            android.content.SharedPreferences$Editor r3 = r4.edit()     // Catch: java.lang.Throwable -> L72
            java.lang.String r1 = r8.a(r1)     // Catch: java.lang.Throwable -> L72
            r3.putString(r1, r5)     // Catch: java.lang.Throwable -> L72
            java.lang.String r1 = r8.a(r2)     // Catch: java.lang.Throwable -> L72
            r3.putString(r1, r6)     // Catch: java.lang.Throwable -> L72
            r3.remove(r0)     // Catch: java.lang.Throwable -> L72
            r3.remove(r2)     // Catch: java.lang.Throwable -> L72
            r3.apply()     // Catch: java.lang.Throwable -> L72
        L59:
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L72
            if (r0 != 0) goto L6c
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L72
            if (r0 == 0) goto L66
            goto L6c
        L66:
            boolean r0 = r8.a(r9, r5, r6)     // Catch: java.lang.Throwable -> L72
            if (r0 != 0) goto L70
        L6c:
            java.lang.String r5 = r8.l(r9)     // Catch: java.lang.Throwable -> L72
        L70:
            r8.f7316l = r5     // Catch: java.lang.Throwable -> L72
        L72:
            java.lang.String r9 = r8.f7316l
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.core.e.g(android.content.Context):java.lang.String");
    }
}
