package com.mp4parser.streaming.extensions;

import com.mp4parser.streaming.SampleExtension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class CompositionTimeSampleExtension implements SampleExtension {
    public static Map<Integer, CompositionTimeSampleExtension> pool = Collections.synchronizedMap(new HashMap());
    private int ctts;

    public static CompositionTimeSampleExtension create(int i2) {
        CompositionTimeSampleExtension compositionTimeSampleExtension = pool.get(Integer.valueOf(i2));
        if (compositionTimeSampleExtension == null) {
            CompositionTimeSampleExtension compositionTimeSampleExtension2 = new CompositionTimeSampleExtension();
            compositionTimeSampleExtension2.ctts = i2;
            pool.put(Integer.valueOf(i2), compositionTimeSampleExtension2);
            return compositionTimeSampleExtension2;
        }
        return compositionTimeSampleExtension;
    }

    public int getCompositionTimeOffset() {
        return this.ctts;
    }
}
