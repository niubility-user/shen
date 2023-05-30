package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes12.dex */
public class CroppedTrack extends AbstractTrack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int fromSample;
    Track origTrack;
    private int toSample;

    public CroppedTrack(Track track, long j2, long j3) {
        super("crop(" + track.getName() + ")");
        this.origTrack = track;
        this.fromSample = (int) j2;
        this.toSample = (int) j3;
    }

    static List<TimeToSampleBox.Entry> getDecodingTimeEntries(List<TimeToSampleBox.Entry> list, long j2, long j3) {
        TimeToSampleBox.Entry next;
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j4 = 0;
        ListIterator<TimeToSampleBox.Entry> listIterator = list.listIterator();
        LinkedList linkedList = new LinkedList();
        while (true) {
            next = listIterator.next();
            if (next.getCount() + j4 > j2) {
                break;
            }
            j4 += next.getCount();
        }
        if (next.getCount() + j4 >= j3) {
            linkedList.add(new TimeToSampleBox.Entry(j3 - j2, next.getDelta()));
            return linkedList;
        }
        linkedList.add(new TimeToSampleBox.Entry((next.getCount() + j4) - j2, next.getDelta()));
        long count = next.getCount();
        while (true) {
            j4 += count;
            if (!listIterator.hasNext()) {
                break;
            }
            next = listIterator.next();
            if (next.getCount() + j4 >= j3) {
                break;
            }
            linkedList.add(next);
            count = next.getCount();
        }
        linkedList.add(new TimeToSampleBox.Entry(j3 - j4, next.getDelta()));
        return linkedList;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.origTrack.close();
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return getCompositionTimeEntries(this.origTrack.getCompositionTimeEntries(), this.fromSample, this.toSample);
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.origTrack.getHandler();
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        if (this.origTrack.getSampleDependencies() == null || this.origTrack.getSampleDependencies().isEmpty()) {
            return null;
        }
        return this.origTrack.getSampleDependencies().subList(this.fromSample, this.toSample);
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.origTrack.getSampleDescriptionBox();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSampleDurations() {
        long[] jArr;
        int i2 = this.toSample - this.fromSample;
        jArr = new long[i2];
        System.arraycopy(this.origTrack.getSampleDurations(), this.fromSample, jArr, 0, i2);
        return jArr;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.origTrack.getSamples().subList(this.fromSample, this.toSample);
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.origTrack.getSubsampleInformationBox();
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSyncSamples() {
        if (this.origTrack.getSyncSamples() != null) {
            long[] syncSamples = this.origTrack.getSyncSamples();
            int length = syncSamples.length;
            int i2 = 0;
            while (i2 < syncSamples.length && syncSamples[i2] < this.fromSample) {
                i2++;
            }
            while (length > 0 && this.toSample < syncSamples[length - 1]) {
                length--;
            }
            int i3 = length - i2;
            long[] jArr = new long[i3];
            System.arraycopy(this.origTrack.getSyncSamples(), i2, jArr, 0, i3);
            for (int i4 = 0; i4 < i3; i4++) {
                jArr[i4] = jArr[i4] - this.fromSample;
            }
            return jArr;
        }
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.origTrack.getTrackMetaData();
    }

    static List<CompositionTimeToSample.Entry> getCompositionTimeEntries(List<CompositionTimeToSample.Entry> list, long j2, long j3) {
        CompositionTimeToSample.Entry next;
        if (list == null || list.isEmpty()) {
            return null;
        }
        long j4 = 0;
        ListIterator<CompositionTimeToSample.Entry> listIterator = list.listIterator();
        ArrayList arrayList = new ArrayList();
        while (true) {
            next = listIterator.next();
            if (next.getCount() + j4 > j2) {
                break;
            }
            j4 += next.getCount();
        }
        if (next.getCount() + j4 >= j3) {
            arrayList.add(new CompositionTimeToSample.Entry((int) (j3 - j2), next.getOffset()));
            return arrayList;
        }
        arrayList.add(new CompositionTimeToSample.Entry((int) ((next.getCount() + j4) - j2), next.getOffset()));
        int count = next.getCount();
        while (true) {
            j4 += count;
            if (!listIterator.hasNext()) {
                break;
            }
            next = listIterator.next();
            if (next.getCount() + j4 >= j3) {
                break;
            }
            arrayList.add(next);
            count = next.getCount();
        }
        arrayList.add(new CompositionTimeToSample.Entry((int) (j3 - j4), next.getOffset()));
        return arrayList;
    }
}
