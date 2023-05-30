package jd.wjlogin_sdk.relinker;

import android.content.Context;
import java.io.File;

/* loaded from: classes.dex */
public class b {

    /* loaded from: classes.dex */
    public interface a {
        void a(Context context, String[] strArr, String str, File file, jd.wjlogin_sdk.relinker.c cVar);
    }

    /* renamed from: jd.wjlogin_sdk.relinker.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0850b {
        void a(String str);

        String[] a();

        String b(String str);

        void c(String str);

        String d(String str);
    }

    /* loaded from: classes.dex */
    public interface c {
        void a();

        void a(Throwable th);
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(String str);
    }

    private b() {
    }

    public static void a(Context context, String str) {
        a(context, str, null, null);
    }

    public static jd.wjlogin_sdk.relinker.c b() {
        return new jd.wjlogin_sdk.relinker.c().b();
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void a(Context context, String str, c cVar) {
        a(context, str, null, cVar);
    }

    public static void a(Context context, String str, String str2, c cVar) {
        new jd.wjlogin_sdk.relinker.c().a(context, str, str2, cVar);
    }

    public static jd.wjlogin_sdk.relinker.c a() {
        return new jd.wjlogin_sdk.relinker.c().a();
    }

    public static jd.wjlogin_sdk.relinker.c a(d dVar) {
        return new jd.wjlogin_sdk.relinker.c().a(dVar);
    }
}
