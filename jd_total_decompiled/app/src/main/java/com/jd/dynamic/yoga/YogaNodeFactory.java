package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public abstract class YogaNodeFactory {
    public static YogaNode create() {
        return new YogaNodeJNIFinalizer();
    }

    public static YogaNode create(YogaConfig yogaConfig) {
        return new YogaNodeJNIFinalizer(yogaConfig);
    }
}
