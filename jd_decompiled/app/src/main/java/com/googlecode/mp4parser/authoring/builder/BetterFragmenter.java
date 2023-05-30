package com.googlecode.mp4parser.authoring.builder;

import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.util.Mp4Arrays;
import java.util.Arrays;

/* loaded from: classes12.dex */
public class BetterFragmenter implements Fragmenter {
    private double targetDuration;

    public BetterFragmenter(double d) {
        this.targetDuration = d;
    }

    @Override // com.googlecode.mp4parser.authoring.builder.Fragmenter
    public long[] sampleNumbers(Track track) {
        int i2;
        long timescale = track.getTrackMetaData().getTimescale();
        double d = this.targetDuration;
        double d2 = timescale;
        Double.isNaN(d2);
        long j2 = (long) (d * d2);
        long[] jArr = new long[0];
        long[] syncSamples = track.getSyncSamples();
        long[] sampleDurations = track.getSampleDurations();
        long j3 = 2;
        if (syncSamples != null) {
            int length = syncSamples.length;
            long[] jArr2 = new long[length];
            long duration = track.getDuration();
            long j4 = 0;
            long j5 = 0;
            int i3 = 0;
            while (i3 < sampleDurations.length) {
                int binarySearch = Arrays.binarySearch(syncSamples, i3 + 1);
                if (binarySearch >= 0) {
                    jArr2[binarySearch] = j5;
                }
                j5 += sampleDurations[i3];
                i3++;
                j3 = 2;
            }
            int i4 = 0;
            while (true) {
                i2 = length - 1;
                if (i4 >= i2) {
                    break;
                }
                long j6 = jArr2[i4];
                int i5 = i4 + 1;
                long j7 = jArr2[i5];
                if (j4 <= j7 && Math.abs(j6 - j4) < Math.abs(j7 - j4)) {
                    jArr = Mp4Arrays.copyOfAndAppend(jArr, syncSamples[i4]);
                    j4 = jArr2[i4] + j2;
                }
                i4 = i5;
            }
            return duration - jArr2[i2] > j2 / j3 ? Mp4Arrays.copyOfAndAppend(jArr, syncSamples[i2]) : jArr;
        }
        long[] jArr3 = {1};
        double d3 = 0.0d;
        for (int i6 = 1; i6 < sampleDurations.length; i6++) {
            double d4 = sampleDurations[i6];
            Double.isNaN(d4);
            Double.isNaN(d2);
            d3 += d4 / d2;
            if (d3 >= this.targetDuration) {
                if (i6 > 0) {
                    jArr3 = Mp4Arrays.copyOfAndAppend(jArr3, i6 + 1);
                }
                d3 = 0.0d;
            }
        }
        if (d3 < this.targetDuration && jArr3.length > 1) {
            jArr3[jArr3.length - 1] = jArr3[jArr3.length - 2] + (((sampleDurations.length + 1) - jArr3[jArr3.length - 2]) / 2);
        }
        return jArr3;
    }
}
