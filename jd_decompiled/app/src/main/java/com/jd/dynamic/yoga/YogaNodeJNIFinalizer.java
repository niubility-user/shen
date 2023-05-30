package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public class YogaNodeJNIFinalizer extends YogaNodeJNIBase {
    public YogaNodeJNIFinalizer() {
    }

    public YogaNodeJNIFinalizer(YogaConfig yogaConfig) {
        super(yogaConfig);
    }

    protected void finalize() {
        try {
            freeNatives();
        } finally {
            super.finalize();
        }
    }

    public void freeNatives() {
        long j2 = this.mNativePointer;
        if (j2 != 0) {
            this.mNativePointer = 0L;
            YogaNative.jni_YGNodeFreeJNI(j2);
        }
    }
}
