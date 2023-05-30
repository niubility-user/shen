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

    /* JADX WARN: Code restructure failed: missing block: B:150:0x009b, code lost:
        if (r6.substring(0, 10).endsWith("81") != false) goto L173;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String j(Context context) {
        try {
            this.f7309e.readLock().lock();
            String str = this.f7314j;
            try {
                this.f7309e.readLock().unlock();
            } catch (Throwable unused) {
            }
            if (!TextUtils.isEmpty(str)) {
                if (str.length() != 116) {
                }
                return str;
            }
            SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
            String string = a2.getString(a("CCOJade"), "");
            String string2 = a2.getString(a("whisper"), "0");
            if (TextUtils.isEmpty(string)) {
                string = a2.getString("CCOToken", "");
                if (!TextUtils.isEmpty(string)) {
                    SharedPreferences.Editor edit = a2.edit();
                    edit.putString(a("CCOJade"), string);
                    edit.remove("CCOToken");
                    edit.apply();
                }
            }
            if (!TextUtils.isEmpty(string) && string.substring(0, 10).endsWith("82")) {
                str = h.b(string, string2, this.f7318n);
            }
            if (!TextUtils.isEmpty(string)) {
            }
            string = str;
            try {
                if (TextUtils.isEmpty(string) || string.length() != 116) {
                    string = l.a(context);
                }
            } catch (Throwable unused2) {
            }
            if (TextUtils.isEmpty(string) || string.length() != 116) {
                return "";
            }
            this.f7309e.writeLock().lock();
            this.f7314j = string;
            this.f7309e.writeLock().unlock();
            return string;
        } catch (Throwable unused3) {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:150:0x009b, code lost:
        if (r6.substring(0, 10).endsWith("81") != false) goto L173;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String k(Context context) {
        try {
            this.f7313i.readLock().lock();
            String str = this.f7315k;
            try {
                this.f7313i.readLock().unlock();
            } catch (Throwable unused) {
            }
            if (!TextUtils.isEmpty(str)) {
                if (str.length() != 116) {
                }
                return str;
            }
            SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
            String string = a2.getString(a("visCCOJade"), "");
            String string2 = a2.getString(a("visWhisper"), "0");
            if (TextUtils.isEmpty(string)) {
                string = a2.getString("guestCCOToken", "");
                if (!TextUtils.isEmpty(string)) {
                    SharedPreferences.Editor edit = a2.edit();
                    edit.putString(a("visCCOJade"), string);
                    edit.remove("guestCCOToken");
                    edit.apply();
                }
            }
            if (!TextUtils.isEmpty(string) && string.substring(0, 10).endsWith("82")) {
                str = h.b(string, string2, this.f7318n);
            }
            if (!TextUtils.isEmpty(string)) {
            }
            string = str;
            try {
                if (TextUtils.isEmpty(string) || string.length() != 116) {
                    string = l.a(context);
                }
            } catch (Throwable unused2) {
            }
            if (TextUtils.isEmpty(string) || string.length() != 116) {
                return "";
            }
            this.f7313i.writeLock().lock();
            this.f7315k = string;
            this.f7313i.writeLock().unlock();
            return string;
        } catch (Throwable unused3) {
            return "";
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:70:0x006a, code lost:
        if (a(r9, r5, r6) != false) goto L72;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String g(Context context) {
        try {
            if (TextUtils.isEmpty(this.f7316l)) {
                SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
                String string = a2.getString(a("lcJade"), "");
                String string2 = a2.getString(a(FilterConstant.FIELD), "");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    string = a2.getString("localtoken", "");
                    string2 = a2.getString(FilterConstant.FIELD, "");
                    if (!TextUtils.isEmpty(string)) {
                        SharedPreferences.Editor edit = a2.edit();
                        edit.putString(a("lcJade"), string);
                        edit.putString(a(FilterConstant.FIELD), string2);
                        edit.remove("localtoken");
                        edit.remove(FilterConstant.FIELD);
                        edit.apply();
                    }
                }
                if (!TextUtils.isEmpty(string)) {
                    if (TextUtils.isEmpty(string2)) {
                    }
                }
                string = l(context);
                this.f7316l = string;
            }
        } catch (Throwable unused) {
        }
        return this.f7316l;
    }
}
