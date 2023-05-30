package com.jd.aips.tools.linker;

import android.content.Context;
import java.io.File;

/* loaded from: classes12.dex */
public class SafeLinker {

    /* loaded from: classes12.dex */
    public interface LibraryInstaller {
        void installLibrary(Context context, String[] strArr, String str, File file, SafeLinkerInstance safeLinkerInstance);
    }

    /* loaded from: classes12.dex */
    public interface LibraryLoader {
        void loadLibrary(String str);

        void loadPath(String str);

        String mapLibraryName(String str);

        String[] supportedAbis();

        String unmapLibraryName(String str);
    }

    /* loaded from: classes12.dex */
    public interface LoadCallback {
        void failure(Throwable th);

        void success();
    }

    private SafeLinker() {
    }

    public static SafeLinkerInstance force() {
        return new SafeLinkerInstance().force();
    }

    public static void loadLibrary(Context context, String str) {
        loadLibrary(context, str, null, null);
    }

    public static SafeLinkerInstance recursively() {
        return new SafeLinkerInstance().recursively();
    }

    public static void loadLibrary(Context context, String str, String str2) {
        loadLibrary(context, str, str2, null);
    }

    public static void loadLibrary(Context context, String str, LoadCallback loadCallback) {
        loadLibrary(context, str, null, loadCallback);
    }

    public static void loadLibrary(Context context, String str, String str2, LoadCallback loadCallback) {
        new SafeLinkerInstance().loadLibrary(context, str, str2, loadCallback);
    }
}
