package com.facebook.jni;

import com.facebook.jni.DestructorThread;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
/* loaded from: classes.dex */
public class HybridData {
    @DoNotStrip
    private Destructor mDestructor = new Destructor(this);

    /* loaded from: classes.dex */
    public static class Destructor extends DestructorThread.Destructor {
        @DoNotStrip
        private long mNativePointer;

        Destructor(Object obj) {
            super(obj);
        }

        static native void deleteNative(long j2);

        @Override // com.facebook.jni.DestructorThread.Destructor
        void destruct() {
            deleteNative(this.mNativePointer);
            this.mNativePointer = 0L;
        }
    }

    static {
        SoLoader.loadLibrary("fb");
    }

    public boolean isValid() {
        return this.mDestructor.mNativePointer != 0;
    }

    public synchronized void resetNative() {
        this.mDestructor.destruct();
    }
}
