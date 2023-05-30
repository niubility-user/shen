package com.xiaomi.push;

import android.os.Looper;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.Collection;

/* loaded from: classes11.dex */
public class s9 {

    /* loaded from: classes11.dex */
    public static class a {
        private final StringBuilder a;
        private final String b;

        /* renamed from: c  reason: collision with root package name */
        private final String f19026c;

        public a() {
            this(":", DYConstants.DY_REGEX_COMMA);
        }

        public a(String str, String str2) {
            this.a = new StringBuilder();
            this.b = str;
            this.f19026c = str2;
        }

        public a a(String str, Object obj) {
            if (!TextUtils.isEmpty(str)) {
                if (this.a.length() > 0) {
                    this.a.append(this.f19026c);
                }
                StringBuilder sb = this.a;
                sb.append(str);
                sb.append(this.b);
                sb.append(obj);
            }
            return this;
        }

        public String toString() {
            return this.a.toString();
        }
    }

    public static int a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static long b(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return j2;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j2;
        }
    }

    public static boolean c() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static boolean d(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static int e(String str, int i2) {
        return !TextUtils.isEmpty(str) ? ((str.hashCode() / 10) * 10) + i2 : i2;
    }
}
