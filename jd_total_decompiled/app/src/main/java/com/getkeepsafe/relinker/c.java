package com.getkeepsafe.relinker;

import android.content.Context;
import java.io.File;

/* loaded from: classes12.dex */
public class c {

    /* loaded from: classes12.dex */
    public interface a {
        void a(Context context, String[] strArr, String str, File file, com.getkeepsafe.relinker.d dVar);
    }

    /* loaded from: classes12.dex */
    public interface b {
        void loadLibrary(String str);

        void loadPath(String str);

        String mapLibraryName(String str);

        String[] supportedAbis();

        String unmapLibraryName(String str);
    }

    /* renamed from: com.getkeepsafe.relinker.c$c  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0027c {
        void failure(Throwable th);

        void success();
    }

    /* loaded from: classes12.dex */
    public interface d {
        void log(String str);
    }

    public static void a(Context context, String str) {
        b(context, str, null, null);
    }

    public static void b(Context context, String str, String str2, InterfaceC0027c interfaceC0027c) {
        new com.getkeepsafe.relinker.d().f(context, str, str2, interfaceC0027c);
    }
}
