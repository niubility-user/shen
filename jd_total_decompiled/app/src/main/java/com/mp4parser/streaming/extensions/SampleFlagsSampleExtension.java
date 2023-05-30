package com.mp4parser.streaming.extensions;

import com.mp4parser.streaming.SampleExtension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class SampleFlagsSampleExtension implements SampleExtension {
    public static Map<Long, SampleFlagsSampleExtension> pool = Collections.synchronizedMap(new HashMap());
    private byte isLeading;
    private int sampleDegradationPriority;
    private byte sampleDependsOn;
    private byte sampleHasRedundancy;
    private byte sampleIsDependedOn;
    private boolean sampleIsNonSyncSample;
    private byte samplePaddingValue;

    public static SampleFlagsSampleExtension create(byte b, byte b2, byte b3, byte b4, byte b5, boolean z, int i2) {
        long j2 = (b2 << 2) + b + (b3 << 4) + (b4 << 6) + (b5 << 8) + (i2 << 11) + ((z ? 1 : 0) << 27);
        SampleFlagsSampleExtension sampleFlagsSampleExtension = pool.get(Long.valueOf(j2));
        if (sampleFlagsSampleExtension == null) {
            SampleFlagsSampleExtension sampleFlagsSampleExtension2 = new SampleFlagsSampleExtension();
            sampleFlagsSampleExtension2.isLeading = b;
            sampleFlagsSampleExtension2.sampleDependsOn = b2;
            sampleFlagsSampleExtension2.sampleIsDependedOn = b3;
            sampleFlagsSampleExtension2.sampleHasRedundancy = b4;
            sampleFlagsSampleExtension2.samplePaddingValue = b5;
            sampleFlagsSampleExtension2.sampleIsNonSyncSample = z;
            sampleFlagsSampleExtension2.sampleDegradationPriority = i2;
            pool.put(Long.valueOf(j2), sampleFlagsSampleExtension2);
            return sampleFlagsSampleExtension2;
        }
        return sampleFlagsSampleExtension;
    }

    public byte getIsLeading() {
        return this.isLeading;
    }

    public int getSampleDegradationPriority() {
        return this.sampleDegradationPriority;
    }

    public byte getSampleDependsOn() {
        return this.sampleDependsOn;
    }

    public byte getSampleHasRedundancy() {
        return this.sampleHasRedundancy;
    }

    public byte getSampleIsDependedOn() {
        return this.sampleIsDependedOn;
    }

    public byte getSamplePaddingValue() {
        return this.samplePaddingValue;
    }

    public boolean isSampleIsNonSyncSample() {
        return this.sampleIsNonSyncSample;
    }

    public boolean isSyncSample() {
        return !this.sampleIsNonSyncSample;
    }

    public void setIsLeading(byte b) {
        this.isLeading = b;
    }

    public void setSampleDegradationPriority(int i2) {
        this.sampleDegradationPriority = i2;
    }

    public void setSampleDependsOn(byte b) {
        this.sampleDependsOn = b;
    }

    public void setSampleHasRedundancy(byte b) {
        this.sampleHasRedundancy = b;
    }

    public void setSampleIsDependedOn(byte b) {
        this.sampleIsDependedOn = b;
    }

    public void setSampleIsNonSyncSample(boolean z) {
        this.sampleIsNonSyncSample = z;
    }

    public void setSamplePaddingValue(byte b) {
        this.samplePaddingValue = b;
    }
}
