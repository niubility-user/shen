package com.tencent.open.log;

import android.text.TextUtils;
import com.tencent.open.log.d;
import com.tencent.open.utils.m;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes9.dex */
public class b {
    private static SimpleDateFormat a = d.C0811d.a("yy.MM.dd.HH");

    /* renamed from: g */
    private File f17682g;
    private String b = "Tracer.File";

    /* renamed from: c */
    private int f17679c = Integer.MAX_VALUE;
    private int d = Integer.MAX_VALUE;

    /* renamed from: e */
    private int f17680e = 4096;

    /* renamed from: f */
    private long f17681f = 10000;

    /* renamed from: h */
    private int f17683h = 10;

    /* renamed from: i */
    private String f17684i = ".log";

    /* renamed from: j */
    private long f17685j = Long.MAX_VALUE;

    public b(File file, int i2, int i3, int i4, String str, long j2, int i5, String str2, long j3) {
        a(file);
        b(i2);
        a(i3);
        c(i4);
        a(str);
        b(j2);
        d(i5);
        b(str2);
        c(j3);
    }

    private String c(String str) {
        return "com.tencent.mobileqq_connectSdk." + str + ".log";
    }

    private File d(long j2) {
        String c2 = c(a(j2));
        String b = m.b();
        if (!TextUtils.isEmpty(b) || b != null) {
            try {
                File file = new File(b, c.o);
                if (!file.exists()) {
                    file.mkdirs();
                }
                return new File(file, c2);
            } catch (Exception e2) {
                SLog.e(SLog.TAG, "getWorkFile,get app specific file exception:", e2);
            }
        }
        return null;
    }

    public File a() {
        return d(System.currentTimeMillis());
    }

    public String b() {
        return this.b;
    }

    public static String a(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        return new SimpleDateFormat("yy.MM.dd.HH").format(calendar.getTime());
    }

    public void b(int i2) {
        this.d = i2;
    }

    public int c() {
        return this.f17680e;
    }

    public void b(long j2) {
        this.f17681f = j2;
    }

    public void c(int i2) {
        this.f17680e = i2;
    }

    public void b(String str) {
        this.f17684i = str;
    }

    public void c(long j2) {
        this.f17685j = j2;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(int i2) {
        this.f17679c = i2;
    }

    public void a(File file) {
        this.f17682g = file;
    }

    public int d() {
        return this.f17683h;
    }

    public void d(int i2) {
        this.f17683h = i2;
    }
}
