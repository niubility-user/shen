package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.googlecode.mp4parser.authoring.Edit;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* loaded from: classes12.dex */
public class ChangeTimeScaleTrack implements Track {
    private static final Logger LOG = Logger.getLogger(ChangeTimeScaleTrack.class.getName());
    List<CompositionTimeToSample.Entry> ctts;
    long[] decodingTimes;
    Track source;
    long timeScale;

    public ChangeTimeScaleTrack(Track track, long j2, long[] jArr) {
        this.source = track;
        this.timeScale = j2;
        double d = j2;
        double timescale = track.getTrackMetaData().getTimescale();
        Double.isNaN(d);
        Double.isNaN(timescale);
        double d2 = d / timescale;
        this.ctts = adjustCtts(track.getCompositionTimeEntries(), d2);
        this.decodingTimes = adjustTts(track.getSampleDurations(), d2, jArr, getTimes(track, jArr, j2));
    }

    static List<CompositionTimeToSample.Entry> adjustCtts(List<CompositionTimeToSample.Entry> list, double d) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            for (CompositionTimeToSample.Entry entry : list) {
                int count = entry.getCount();
                double offset = entry.getOffset();
                Double.isNaN(offset);
                arrayList.add(new CompositionTimeToSample.Entry(count, (int) Math.round(offset * d)));
            }
            return arrayList;
        }
        return null;
    }

    static long[] adjustTts(long[] jArr, double d, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[jArr.length];
        long j2 = 0;
        int i2 = 1;
        while (i2 <= jArr.length) {
            int i3 = i2 - 1;
            double d2 = jArr[i3];
            Double.isNaN(d2);
            long round = Math.round(d2 * d);
            int i4 = i2 + 1;
            int binarySearch = Arrays.binarySearch(jArr2, i4);
            if (binarySearch >= 0 && jArr3[binarySearch] != j2) {
                long j3 = jArr3[binarySearch] - (j2 + round);
                LOG.finest(String.format("Sample %d %d / %d - correct by %d", Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(jArr3[binarySearch]), Long.valueOf(j3)));
                round += j3;
            }
            j2 += round;
            jArr4[i3] = round;
            i2 = i4;
        }
        return jArr4;
    }

    private static long[] getTimes(Track track, long[] jArr, long j2) {
        long[] jArr2 = new long[jArr.length];
        long j3 = 0;
        int i2 = 0;
        int i3 = 1;
        while (true) {
            long j4 = i3;
            if (j4 > jArr[jArr.length - 1]) {
                return jArr2;
            }
            if (j4 == jArr[i2]) {
                jArr2[i2] = (j3 * j2) / track.getTrackMetaData().getTimescale();
                i2++;
            }
            j3 += track.getSampleDurations()[i3 - 1];
            i3++;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.source.close();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.ctts;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        long j2 = 0;
        for (long j3 : this.decodingTimes) {
            j2 += j3;
        }
        return j2;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return this.source.getEdits();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.source.getHandler();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "timeScale(" + this.source.getName() + ")";
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.source.getSampleDependencies();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.source.getSampleDescriptionBox();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.decodingTimes;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.source.getSampleGroups();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.source.getSamples();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.source.getSubsampleInformationBox();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.source.getSyncSamples();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        TrackMetaData trackMetaData = (TrackMetaData) this.source.getTrackMetaData().clone();
        trackMetaData.setTimescale(this.timeScale);
        return trackMetaData;
    }

    public String toString() {
        return "ChangeTimeScaleTrack{source=" + this.source + '}';
    }
}
