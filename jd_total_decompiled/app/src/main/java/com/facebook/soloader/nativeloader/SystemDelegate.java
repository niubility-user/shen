package com.facebook.soloader.nativeloader;

/* loaded from: classes12.dex */
public class SystemDelegate implements NativeLoaderDelegate {
    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public String getLibraryPath(String str) {
        return null;
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public int getSoSourcesVersion() {
        return 0;
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public boolean loadLibrary(String str, int i2) {
        System.loadLibrary(str);
        return true;
    }
}
