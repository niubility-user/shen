package com.mp4parser.streaming;

/* loaded from: classes14.dex */
public class StreamingSampleHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <B extends SampleExtension> B getSampleExtension(StreamingSample streamingSample, Class<B> cls) {
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            B b = (B) sampleExtension;
            if (cls.isAssignableFrom(b.getClass())) {
                return b;
            }
        }
        return null;
    }

    static boolean hasSampleExtension(StreamingSample streamingSample, Class<? extends SampleExtension> cls) {
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            if (cls.isAssignableFrom(sampleExtension.getClass())) {
                return true;
            }
        }
        return false;
    }
}
