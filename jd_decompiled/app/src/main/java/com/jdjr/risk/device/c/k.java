package com.jdjr.risk.device.c;

import android.content.Context;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes18.dex */
public class k {
    private static volatile k d;
    private String a = "";
    private ReadWriteLock b = new ReentrantReadWriteLock();

    /* renamed from: c */
    private ReentrantLock f7353c = new ReentrantLock();

    private k() {
    }

    public static k a() {
        if (d == null) {
            synchronized (k.class) {
                if (d == null) {
                    d = new k();
                }
            }
        }
        return d;
    }

    private static String a(String str) {
        try {
            String a = j.a("stat " + str, true);
            if (TextUtils.isEmpty(a)) {
                return "";
            }
            String[] split = a.split(System.getProperty("line.separator"));
            return split.length > 4 ? split[4] : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02X", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x002c, code lost:
        if (r5.f7353c.isHeldByCurrentThread() != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x002e, code lost:
        r5.f7353c.unlock();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x003b, code lost:
        if (r5.f7353c.isHeldByCurrentThread() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x003e, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            java.util.concurrent.locks.ReentrantLock r1 = r5.f7353c     // Catch: java.lang.Throwable -> L34
            r2 = 200(0xc8, double:9.9E-322)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L34
            boolean r1 = r1.tryLock(r2, r4)     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L26
            java.lang.String r0 = r5.d(r6)     // Catch: java.lang.Throwable -> L34
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L26
            java.lang.String r0 = r5.e(r6)     // Catch: java.lang.Throwable -> L34
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L26
            java.lang.String r0 = r5.f(r6)     // Catch: java.lang.Throwable -> L34
        L26:
            java.util.concurrent.locks.ReentrantLock r6 = r5.f7353c
            boolean r6 = r6.isHeldByCurrentThread()
            if (r6 == 0) goto L3e
        L2e:
            java.util.concurrent.locks.ReentrantLock r6 = r5.f7353c
            r6.unlock()
            goto L3e
        L34:
            java.util.concurrent.locks.ReentrantLock r6 = r5.f7353c
            boolean r6 = r6.isHeldByCurrentThread()
            if (r6 == 0) goto L3e
            goto L2e
        L3e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.c.k.b(android.content.Context):java.lang.String");
    }

    private static byte[] b(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return messageDigest.digest();
        } catch (Exception unused) {
            return null;
        }
    }

    private String c(Context context) {
        try {
            return com.jdjr.risk.util.a.d.a(context).getString("CommonID", "");
        } catch (Throwable unused) {
            return "";
        }
    }

    private String d(Context context) {
        String sb;
        try {
            String h2 = r.h(context);
            if (TextUtils.isEmpty(h2)) {
                return "";
            }
            String a = a("/sdcard/Download");
            if (TextUtils.isEmpty(a)) {
                sb = "10" + a(b(h2));
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("11");
                sb2.append(a(b(h2 + a)));
                sb = sb2.toString();
            }
            return sb;
        } catch (Throwable unused) {
            return "";
        }
    }

    private String e(Context context) {
        try {
            String a = a("/sdcard/Download");
            if (TextUtils.isEmpty(a)) {
                return "";
            }
            String a2 = a("/data/media");
            if (TextUtils.isEmpty(a)) {
                return "";
            }
            return "01" + a(b(a + a2));
        } catch (Throwable unused) {
            return "";
        }
    }

    private String f(Context context) {
        try {
            String uuid = UUID.randomUUID().toString();
            if (TextUtils.isEmpty(uuid)) {
                return "";
            }
            return "00" + a(b(uuid));
        } catch (Throwable unused) {
            return "";
        }
    }

    public String a(Context context) {
        String str;
        this.b.readLock().lock();
        try {
            str = this.a;
            this.b.readLock().unlock();
        } catch (Throwable unused) {
            this.b.readLock().unlock();
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = c(context);
            if (TextUtils.isEmpty(str)) {
                str = b(context);
                com.jdjr.risk.util.a.d.a(context).edit().putString("CommonID", str).apply();
            }
            this.b.writeLock().lock();
            try {
                this.a = str;
            } catch (Throwable unused2) {
            }
            this.b.writeLock().unlock();
        }
        return str;
    }
}
