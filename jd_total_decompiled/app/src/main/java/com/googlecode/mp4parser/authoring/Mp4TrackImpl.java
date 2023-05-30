package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.EditListBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.SampleFlags;
import com.coremedia.iso.boxes.fragment.TrackExtendsBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.coremedia.iso.boxes.mdat.SampleList;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.BasicContainer;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Mp4Arrays;
import com.googlecode.mp4parser.util.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class Mp4TrackImpl extends AbstractTrack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private List<CompositionTimeToSample.Entry> compositionTimeEntries;
    private long[] decodingTimes;
    IsoFile[] fragments;
    private String handler;
    private List<SampleDependencyTypeBox.Entry> sampleDependencies;
    private SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    private SubSampleInformationBox subSampleInformationBox;
    private long[] syncSamples;
    TrackBox trackBox;
    private TrackMetaData trackMetaData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Mp4TrackImpl(String str, TrackBox trackBox, IsoFile... isoFileArr) {
        super(str);
        TrackRunBox.Entry entry;
        String str2;
        ArrayList arrayList;
        SampleFlags defaultSampleFlags;
        int i2;
        Mp4TrackImpl mp4TrackImpl = this;
        mp4TrackImpl.syncSamples = null;
        mp4TrackImpl.trackMetaData = new TrackMetaData();
        mp4TrackImpl.subSampleInformationBox = null;
        mp4TrackImpl.trackBox = trackBox;
        long trackId = trackBox.getTrackHeaderBox().getTrackId();
        mp4TrackImpl.samples = new SampleList(trackBox, isoFileArr);
        SampleTableBox sampleTableBox = trackBox.getMediaBox().getMediaInformationBox().getSampleTableBox();
        mp4TrackImpl.handler = trackBox.getMediaBox().getHandlerBox().getHandlerType();
        ArrayList arrayList2 = new ArrayList();
        mp4TrackImpl.compositionTimeEntries = new ArrayList();
        mp4TrackImpl.sampleDependencies = new ArrayList();
        arrayList2.addAll(sampleTableBox.getTimeToSampleBox().getEntries());
        if (sampleTableBox.getCompositionTimeToSample() != null) {
            mp4TrackImpl.compositionTimeEntries.addAll(sampleTableBox.getCompositionTimeToSample().getEntries());
        }
        if (sampleTableBox.getSampleDependencyTypeBox() != null) {
            mp4TrackImpl.sampleDependencies.addAll(sampleTableBox.getSampleDependencyTypeBox().getEntries());
        }
        if (sampleTableBox.getSyncSampleBox() != null) {
            mp4TrackImpl.syncSamples = sampleTableBox.getSyncSampleBox().getSampleNumber();
        }
        String str3 = SubSampleInformationBox.TYPE;
        mp4TrackImpl.subSampleInformationBox = (SubSampleInformationBox) Path.getPath((AbstractContainerBox) sampleTableBox, SubSampleInformationBox.TYPE);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(((Box) trackBox.getParent()).getParent().getBoxes(MovieFragmentBox.class));
        int length = isoFileArr.length;
        int i3 = 0;
        while (i3 < length) {
            ArrayList arrayList4 = arrayList3;
            arrayList4.addAll(isoFileArr[i3].getBoxes(MovieFragmentBox.class));
            i3++;
            mp4TrackImpl = this;
            arrayList3 = arrayList4;
            trackId = trackId;
        }
        mp4TrackImpl.sampleDescriptionBox = sampleTableBox.getSampleDescriptionBox();
        List boxes = trackBox.getParent().getBoxes(MovieExtendsBox.class);
        if (boxes.size() > 0) {
            Iterator it = boxes.iterator();
            while (it.hasNext()) {
                for (TrackExtendsBox trackExtendsBox : ((MovieExtendsBox) it.next()).getBoxes(TrackExtendsBox.class)) {
                    if (trackExtendsBox.getTrackId() == trackId) {
                        if (Path.getPaths(((Box) trackBox.getParent()).getParent(), "/moof/traf/subs").size() > 0) {
                            mp4TrackImpl.subSampleInformationBox = new SubSampleInformationBox();
                        }
                        Iterator it2 = arrayList3.iterator();
                        long j2 = 1;
                        long j3 = 1;
                        while (it2.hasNext()) {
                            long j4 = j3;
                            for (TrackFragmentBox trackFragmentBox : ((MovieFragmentBox) it2.next()).getBoxes(TrackFragmentBox.class)) {
                                if (trackFragmentBox.getTrackFragmentHeaderBox().getTrackId() == trackId) {
                                    long j5 = trackId;
                                    mp4TrackImpl.sampleGroups = getSampleGroups(sampleTableBox.getBoxes(SampleGroupDescriptionBox.class), Path.getPaths((Container) trackFragmentBox, SampleGroupDescriptionBox.TYPE), Path.getPaths((Container) trackFragmentBox, SampleToGroupBox.TYPE), mp4TrackImpl.sampleGroups, j4 - j2);
                                    SubSampleInformationBox subSampleInformationBox = (SubSampleInformationBox) Path.getPath((AbstractContainerBox) trackFragmentBox, str3);
                                    if (subSampleInformationBox != null) {
                                        long j6 = (j4 - 0) - 1;
                                        for (SubSampleInformationBox.SubSampleEntry subSampleEntry : subSampleInformationBox.getEntries()) {
                                            SubSampleInformationBox.SubSampleEntry subSampleEntry2 = new SubSampleInformationBox.SubSampleEntry();
                                            subSampleEntry2.getSubsampleEntries().addAll(subSampleEntry.getSubsampleEntries());
                                            if (j6 != 0) {
                                                subSampleEntry2.setSampleDelta(j6 + subSampleEntry.getSampleDelta());
                                                j6 = 0;
                                            } else {
                                                subSampleEntry2.setSampleDelta(subSampleEntry.getSampleDelta());
                                            }
                                            mp4TrackImpl.subSampleInformationBox.getEntries().add(subSampleEntry2);
                                        }
                                    }
                                    for (TrackRunBox trackRunBox : trackFragmentBox.getBoxes(TrackRunBox.class)) {
                                        TrackFragmentHeaderBox trackFragmentHeaderBox = ((TrackFragmentBox) trackRunBox.getParent()).getTrackFragmentHeaderBox();
                                        int i4 = 1;
                                        boolean z = true;
                                        for (TrackRunBox.Entry entry2 : trackRunBox.getEntries()) {
                                            if (trackRunBox.isSampleDurationPresent()) {
                                                if (arrayList2.size() != 0 && ((TimeToSampleBox.Entry) arrayList2.get(arrayList2.size() - i4)).getDelta() == entry2.getSampleDuration()) {
                                                    TimeToSampleBox.Entry entry3 = (TimeToSampleBox.Entry) arrayList2.get(arrayList2.size() - i4);
                                                    entry = entry2;
                                                    str2 = str3;
                                                    arrayList = arrayList3;
                                                    entry3.setCount(entry3.getCount() + 1);
                                                } else {
                                                    entry = entry2;
                                                    str2 = str3;
                                                    arrayList = arrayList3;
                                                    arrayList2.add(new TimeToSampleBox.Entry(1L, entry.getSampleDuration()));
                                                }
                                            } else {
                                                entry = entry2;
                                                str2 = str3;
                                                arrayList = arrayList3;
                                                if (trackFragmentHeaderBox.hasDefaultSampleDuration()) {
                                                    arrayList2.add(new TimeToSampleBox.Entry(1L, trackFragmentHeaderBox.getDefaultSampleDuration()));
                                                } else {
                                                    arrayList2.add(new TimeToSampleBox.Entry(1L, trackExtendsBox.getDefaultSampleDuration()));
                                                }
                                            }
                                            if (trackRunBox.isSampleCompositionTimeOffsetPresent()) {
                                                if (mp4TrackImpl.compositionTimeEntries.size() != 0) {
                                                    List<CompositionTimeToSample.Entry> list = mp4TrackImpl.compositionTimeEntries;
                                                    i2 = 1;
                                                    if (list.get(list.size() - 1).getOffset() == entry.getSampleCompositionTimeOffset()) {
                                                        List<CompositionTimeToSample.Entry> list2 = mp4TrackImpl.compositionTimeEntries;
                                                        CompositionTimeToSample.Entry entry4 = list2.get(list2.size() - 1);
                                                        entry4.setCount(entry4.getCount() + 1);
                                                    }
                                                } else {
                                                    i2 = 1;
                                                }
                                                mp4TrackImpl.compositionTimeEntries.add(new CompositionTimeToSample.Entry(i2, CastUtils.l2i(entry.getSampleCompositionTimeOffset())));
                                            }
                                            if (trackRunBox.isSampleFlagsPresent()) {
                                                defaultSampleFlags = entry.getSampleFlags();
                                            } else if (z && trackRunBox.isFirstSampleFlagsPresent()) {
                                                defaultSampleFlags = trackRunBox.getFirstSampleFlags();
                                            } else if (trackFragmentHeaderBox.hasDefaultSampleFlags()) {
                                                defaultSampleFlags = trackFragmentHeaderBox.getDefaultSampleFlags();
                                            } else {
                                                defaultSampleFlags = trackExtendsBox.getDefaultSampleFlags();
                                            }
                                            if (defaultSampleFlags == null || defaultSampleFlags.isSampleIsDifferenceSample()) {
                                                i4 = 1;
                                            } else {
                                                i4 = 1;
                                                mp4TrackImpl.syncSamples = Mp4Arrays.copyOfAndAppend(mp4TrackImpl.syncSamples, j4);
                                            }
                                            j4++;
                                            str3 = str2;
                                            arrayList3 = arrayList;
                                            z = false;
                                        }
                                    }
                                    trackId = j5;
                                    j2 = 1;
                                }
                            }
                            j3 = j4;
                        }
                    }
                }
            }
        } else {
            mp4TrackImpl.sampleGroups = getSampleGroups(sampleTableBox.getBoxes(SampleGroupDescriptionBox.class), null, sampleTableBox.getBoxes(SampleToGroupBox.class), mp4TrackImpl.sampleGroups, 0L);
        }
        mp4TrackImpl.decodingTimes = TimeToSampleBox.blowupTimeToSamples(arrayList2);
        MediaHeaderBox mediaHeaderBox = trackBox.getMediaBox().getMediaHeaderBox();
        TrackHeaderBox trackHeaderBox = trackBox.getTrackHeaderBox();
        mp4TrackImpl.trackMetaData.setTrackId(trackHeaderBox.getTrackId());
        mp4TrackImpl.trackMetaData.setCreationTime(mediaHeaderBox.getCreationTime());
        mp4TrackImpl.trackMetaData.setLanguage(mediaHeaderBox.getLanguage());
        mp4TrackImpl.trackMetaData.setModificationTime(mediaHeaderBox.getModificationTime());
        mp4TrackImpl.trackMetaData.setTimescale(mediaHeaderBox.getTimescale());
        mp4TrackImpl.trackMetaData.setHeight(trackHeaderBox.getHeight());
        mp4TrackImpl.trackMetaData.setWidth(trackHeaderBox.getWidth());
        mp4TrackImpl.trackMetaData.setLayer(trackHeaderBox.getLayer());
        mp4TrackImpl.trackMetaData.setMatrix(trackHeaderBox.getMatrix());
        mp4TrackImpl.trackMetaData.setVolume(trackHeaderBox.getVolume());
        EditListBox editListBox = (EditListBox) Path.getPath((AbstractContainerBox) trackBox, "edts/elst");
        MovieHeaderBox movieHeaderBox = (MovieHeaderBox) Path.getPath((AbstractContainerBox) trackBox, "../mvhd");
        if (editListBox != null) {
            for (Iterator<EditListBox.Entry> it3 = editListBox.getEntries().iterator(); it3.hasNext(); it3 = it3) {
                EditListBox.Entry next = it3.next();
                List<Edit> list3 = mp4TrackImpl.edits;
                long mediaTime = next.getMediaTime();
                long timescale = mediaHeaderBox.getTimescale();
                double mediaRate = next.getMediaRate();
                MediaHeaderBox mediaHeaderBox2 = mediaHeaderBox;
                double segmentDuration = next.getSegmentDuration();
                double timescale2 = movieHeaderBox.getTimescale();
                Double.isNaN(segmentDuration);
                Double.isNaN(timescale2);
                list3.add(new Edit(mediaTime, timescale, mediaRate, segmentDuration / timescale2));
                mp4TrackImpl = this;
                mediaHeaderBox = mediaHeaderBox2;
            }
        }
    }

    private Map<GroupEntry, long[]> getSampleGroups(List<SampleGroupDescriptionBox> list, List<SampleGroupDescriptionBox> list2, List<SampleToGroupBox> list3, Map<GroupEntry, long[]> map, long j2) {
        for (SampleToGroupBox sampleToGroupBox : list3) {
            int i2 = 0;
            for (SampleToGroupBox.Entry entry : sampleToGroupBox.getEntries()) {
                if (entry.getGroupDescriptionIndex() > 0) {
                    GroupEntry groupEntry = null;
                    if (entry.getGroupDescriptionIndex() > 65535) {
                        for (SampleGroupDescriptionBox sampleGroupDescriptionBox : list2) {
                            if (sampleGroupDescriptionBox.getGroupingType().equals(sampleToGroupBox.getGroupingType())) {
                                groupEntry = sampleGroupDescriptionBox.getGroupEntries().get((entry.getGroupDescriptionIndex() - 1) & 65535);
                            }
                        }
                    } else {
                        for (SampleGroupDescriptionBox sampleGroupDescriptionBox2 : list) {
                            if (sampleGroupDescriptionBox2.getGroupingType().equals(sampleToGroupBox.getGroupingType())) {
                                groupEntry = sampleGroupDescriptionBox2.getGroupEntries().get(entry.getGroupDescriptionIndex() - 1);
                            }
                        }
                    }
                    GroupEntry groupEntry2 = groupEntry;
                    long[] jArr = map.get(groupEntry2);
                    if (jArr == null) {
                        jArr = new long[0];
                    }
                    long[] jArr2 = jArr;
                    long[] jArr3 = new long[CastUtils.l2i(entry.getSampleCount()) + jArr2.length];
                    System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
                    int i3 = 0;
                    while (true) {
                        long j3 = i3;
                        if (j3 >= entry.getSampleCount()) {
                            break;
                        }
                        jArr3[jArr2.length + i3] = j2 + i2 + j3;
                        i3++;
                    }
                    map.put(groupEntry2, jArr3);
                }
                i2 = (int) (i2 + entry.getSampleCount());
            }
        }
        return map;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Container parent = this.trackBox.getParent();
        if (parent instanceof BasicContainer) {
            ((BasicContainer) parent).close();
        }
        IsoFile[] isoFileArr = this.fragments;
        if (isoFileArr != null) {
            for (IsoFile isoFile : isoFileArr) {
                isoFile.close();
            }
        }
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.compositionTimeEntries;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.handler;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.sampleDependencies;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public synchronized long[] getSampleDurations() {
        return this.decodingTimes;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.subSampleInformationBox;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        long[] jArr = this.syncSamples;
        if (jArr == null || jArr.length == this.samples.size()) {
            return null;
        }
        return this.syncSamples;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }
}
