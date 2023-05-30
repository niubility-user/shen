package com.jd.stat.common.relinker;

import android.content.Context;
import java.io.File;

/* loaded from: classes18.dex */
public class b {

    /* loaded from: classes18.dex */
    public interface a {
        void a(Context context, String[] strArr, String str, File file, com.jd.stat.common.relinker.c cVar);
    }

    /* renamed from: com.jd.stat.common.relinker.b$b  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    public interface InterfaceC0220b {
        void a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        String d(String str);
    }

    /* loaded from: classes18.dex */
    public interface c {
        void a();

        void a(Throwable th);
    }

    /* loaded from: classes18.dex */
    public interface d {
        void a(String str);
    }

    public static void a(Context context, String str) {
        a(context, str, null, null);
    }

    public static void a(Context context, String str, String str2, c cVar) {
        new com.jd.stat.common.relinker.c().a(context, str, str2, cVar);
    }
}
