package com.facebook.soloader;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public abstract class NativeLibrary {
    private static final String TAG = "com.facebook.soloader.NativeLibrary";
    @Nullable
    private List<String> mLibraryNames;
    private final Object mLock = new Object();
    private Boolean mLoadLibraries = Boolean.TRUE;
    private boolean mLibrariesLoaded = false;
    @Nullable
    private volatile UnsatisfiedLinkError mLinkError = null;

    protected NativeLibrary(List<String> list) {
        this.mLibraryNames = list;
    }

    public void ensureLoaded() throws UnsatisfiedLinkError {
        if (!loadLibraries()) {
            throw this.mLinkError;
        }
    }

    @Nullable
    public UnsatisfiedLinkError getError() {
        return this.mLinkError;
    }

    protected void initialNativeCheck() throws UnsatisfiedLinkError {
    }

    @Nullable
    public boolean loadLibraries() {
        synchronized (this.mLock) {
            if (!this.mLoadLibraries.booleanValue()) {
                return this.mLibrariesLoaded;
            }
            try {
                List<String> list = this.mLibraryNames;
                if (list != null) {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        SoLoader.loadLibrary(it.next());
                    }
                }
                initialNativeCheck();
                this.mLibrariesLoaded = true;
                this.mLibraryNames = null;
            } catch (UnsatisfiedLinkError e2) {
                this.mLinkError = e2;
                this.mLibrariesLoaded = false;
            }
            this.mLoadLibraries = Boolean.FALSE;
            return this.mLibrariesLoaded;
        }
    }
}
