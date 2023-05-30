package com.googlecode.mp4parser.authoring.builder;

import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.util.Mp4Arrays;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class DefaultFragmenterImpl implements Fragmenter {
    private double fragmentLength;

    public DefaultFragmenterImpl(double d) {
        this.fragmentLength = 2.0d;
        this.fragmentLength = d;
    }

    @Override // com.googlecode.mp4parser.authoring.builder.Fragmenter
    public long[] sampleNumbers(Track track) {
        long[] jArr = {1};
        long[] sampleDurations = track.getSampleDurations();
        long[] syncSamples = track.getSyncSamples();
        long timescale = track.getTrackMetaData().getTimescale();
        double d = 0.0d;
        for (int i2 = 0; i2 < sampleDurations.length; i2++) {
            double d2 = sampleDurations[i2];
            double d3 = timescale;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d += d2 / d3;
            if (d >= this.fragmentLength && (syncSamples == null || Arrays.binarySearch(syncSamples, i2 + 1) >= 0)) {
                if (i2 > 0) {
                    jArr = Mp4Arrays.copyOfAndAppend(jArr, i2 + 1);
                }
                d = 0.0d;
            }
        }
        if (d >= this.fragmentLength || jArr.length <= 1) {
            return jArr;
        }
        long[] jArr2 = new long[jArr.length - 1];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length - 1);
        return jArr2;
    }
}
