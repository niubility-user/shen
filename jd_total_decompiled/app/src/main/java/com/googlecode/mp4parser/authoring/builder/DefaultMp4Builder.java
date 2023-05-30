package com.googlecode.mp4parser.authoring.builder;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.DataEntryUrlBox;
import com.coremedia.iso.boxes.DataInformationBox;
import com.coremedia.iso.boxes.DataReferenceBox;
import com.coremedia.iso.boxes.EditBox;
import com.coremedia.iso.boxes.EditListBox;
import com.coremedia.iso.boxes.FileTypeBox;
import com.coremedia.iso.boxes.HandlerBox;
import com.coremedia.iso.boxes.HintMediaHeaderBox;
import com.coremedia.iso.boxes.MediaBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MediaInformationBox;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.NullMediaHeaderBox;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SampleToChunkBox;
import com.coremedia.iso.boxes.SoundMediaHeaderBox;
import com.coremedia.iso.boxes.StaticChunkOffsetBox;
import com.coremedia.iso.boxes.SubtitleMediaHeaderBox;
import com.coremedia.iso.boxes.SyncSampleBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.coremedia.iso.boxes.VideoMediaHeaderBox;
import com.coremedia.iso.boxes.mdat.MediaDataBox;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.BasicContainer;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.authoring.Edit;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack;
import com.googlecode.mp4parser.boxes.dece.SampleEncryptionBox;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Logger;
import com.googlecode.mp4parser.util.Math;
import com.googlecode.mp4parser.util.Mp4Arrays;
import com.googlecode.mp4parser.util.Path;
import com.jd.dynamic.DYConstants;
import com.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import com.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import com.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes12.dex */
public class DefaultMp4Builder implements Mp4Builder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Logger LOG = Logger.getLogger(DefaultMp4Builder.class);
    private Fragmenter fragmenter;
    Map<Track, StaticChunkOffsetBox> chunkOffsetBoxes = new HashMap();
    Set<SampleAuxiliaryInformationOffsetsBox> sampleAuxiliaryInformationOffsetsBoxes = new HashSet();
    HashMap<Track, List<Sample>> track2Sample = new HashMap<>();
    HashMap<Track, long[]> track2SampleSizes = new HashMap<>();

    public static long gcd(long j2, long j3) {
        return j3 == 0 ? j2 : gcd(j3, j2 % j3);
    }

    private static long sum(int[] iArr) {
        long j2 = 0;
        for (int i2 : iArr) {
            j2 += i2;
        }
        return j2;
    }

    @Override // com.googlecode.mp4parser.authoring.builder.Mp4Builder
    public Container build(Movie movie) {
        Box next;
        if (this.fragmenter == null) {
            this.fragmenter = new BetterFragmenter(2.0d);
        }
        LOG.logDebug("Creating movie " + movie);
        Iterator<Track> it = movie.getTracks().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Track next2 = it.next();
            List<Sample> samples = next2.getSamples();
            putSamples(next2, samples);
            int size = samples.size();
            long[] jArr = new long[size];
            for (int i2 = 0; i2 < size; i2++) {
                jArr[i2] = samples.get(i2).getSize();
            }
            this.track2SampleSizes.put(next2, jArr);
        }
        BasicContainer basicContainer = new BasicContainer();
        basicContainer.addBox(createFileTypeBox(movie));
        HashMap hashMap = new HashMap();
        for (Track track : movie.getTracks()) {
            hashMap.put(track, getChunkSizes(track));
        }
        MovieBox createMovieBox = createMovieBox(movie, hashMap);
        basicContainer.addBox(createMovieBox);
        Iterator it2 = Path.getPaths((Box) createMovieBox, "trak/mdia/minf/stbl/stsz").iterator();
        long j2 = 0;
        while (it2.hasNext()) {
            j2 += sum(((SampleSizeBox) it2.next()).getSampleSizes());
        }
        LOG.logDebug("About to create mdat");
        InterleaveChunkMdat interleaveChunkMdat = new InterleaveChunkMdat(this, movie, hashMap, j2, null);
        basicContainer.addBox(interleaveChunkMdat);
        LOG.logDebug("mdat crated");
        long dataOffset = interleaveChunkMdat.getDataOffset();
        Iterator<StaticChunkOffsetBox> it3 = this.chunkOffsetBoxes.values().iterator();
        while (it3.hasNext()) {
            long[] chunkOffsets = it3.next().getChunkOffsets();
            for (int i3 = 0; i3 < chunkOffsets.length; i3++) {
                chunkOffsets[i3] = chunkOffsets[i3] + dataOffset;
            }
        }
        for (SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox : this.sampleAuxiliaryInformationOffsetsBoxes) {
            long size2 = sampleAuxiliaryInformationOffsetsBox.getSize() + 44;
            Container container = sampleAuxiliaryInformationOffsetsBox;
            while (true) {
                Container parent = container.getParent();
                Iterator<Box> it4 = parent.getBoxes().iterator();
                while (it4.hasNext() && (next = it4.next()) != container) {
                    size2 += next.getSize();
                }
                if (!(parent instanceof Box)) {
                    break;
                }
                container = parent;
            }
            long[] offsets = sampleAuxiliaryInformationOffsetsBox.getOffsets();
            for (int i4 = 0; i4 < offsets.length; i4++) {
                offsets[i4] = offsets[i4] + size2;
            }
            sampleAuxiliaryInformationOffsetsBox.setOffsets(offsets);
        }
        return basicContainer;
    }

    protected void createCencBoxes(CencEncryptedTrack cencEncryptedTrack, SampleTableBox sampleTableBox, int[] iArr) {
        SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = new SampleAuxiliaryInformationSizesBox();
        sampleAuxiliaryInformationSizesBox.setAuxInfoType("cenc");
        sampleAuxiliaryInformationSizesBox.setFlags(1);
        List<CencSampleAuxiliaryDataFormat> sampleEncryptionEntries = cencEncryptedTrack.getSampleEncryptionEntries();
        if (cencEncryptedTrack.hasSubSampleEncryption()) {
            int size = sampleEncryptionEntries.size();
            short[] sArr = new short[size];
            for (int i2 = 0; i2 < size; i2++) {
                sArr[i2] = (short) sampleEncryptionEntries.get(i2).getSize();
            }
            sampleAuxiliaryInformationSizesBox.setSampleInfoSizes(sArr);
        } else {
            sampleAuxiliaryInformationSizesBox.setDefaultSampleInfoSize(8);
            sampleAuxiliaryInformationSizesBox.setSampleCount(cencEncryptedTrack.getSamples().size());
        }
        SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = new SampleAuxiliaryInformationOffsetsBox();
        SampleEncryptionBox sampleEncryptionBox = new SampleEncryptionBox();
        sampleEncryptionBox.setSubSampleEncryption(cencEncryptedTrack.hasSubSampleEncryption());
        sampleEncryptionBox.setEntries(sampleEncryptionEntries);
        long offsetToFirstIV = sampleEncryptionBox.getOffsetToFirstIV();
        long[] jArr = new long[iArr.length];
        int i3 = 0;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            jArr[i4] = offsetToFirstIV;
            int i5 = 0;
            while (i5 < iArr[i4]) {
                offsetToFirstIV += sampleEncryptionEntries.get(i3).getSize();
                i5++;
                i3++;
                sampleEncryptionBox = sampleEncryptionBox;
            }
        }
        sampleAuxiliaryInformationOffsetsBox.setOffsets(jArr);
        sampleTableBox.addBox(sampleAuxiliaryInformationSizesBox);
        sampleTableBox.addBox(sampleAuxiliaryInformationOffsetsBox);
        sampleTableBox.addBox(sampleEncryptionBox);
        this.sampleAuxiliaryInformationOffsetsBoxes.add(sampleAuxiliaryInformationOffsetsBox);
    }

    protected void createCtts(Track track, SampleTableBox sampleTableBox) {
        List<CompositionTimeToSample.Entry> compositionTimeEntries = track.getCompositionTimeEntries();
        if (compositionTimeEntries == null || compositionTimeEntries.isEmpty()) {
            return;
        }
        CompositionTimeToSample compositionTimeToSample = new CompositionTimeToSample();
        compositionTimeToSample.setEntries(compositionTimeEntries);
        sampleTableBox.addBox(compositionTimeToSample);
    }

    protected Box createEdts(Track track, Movie movie) {
        if (track.getEdits() == null || track.getEdits().size() <= 0) {
            return null;
        }
        EditListBox editListBox = new EditListBox();
        editListBox.setVersion(0);
        ArrayList arrayList = new ArrayList();
        for (Edit edit : track.getEdits()) {
            double segmentDuration = edit.getSegmentDuration();
            double timescale = movie.getTimescale();
            Double.isNaN(timescale);
            arrayList.add(new EditListBox.Entry(editListBox, Math.round(segmentDuration * timescale), (edit.getMediaTime() * track.getTrackMetaData().getTimescale()) / edit.getTimeScale(), edit.getMediaRate()));
        }
        editListBox.setEntries(arrayList);
        EditBox editBox = new EditBox();
        editBox.addBox(editListBox);
        return editBox;
    }

    protected FileTypeBox createFileTypeBox(Movie movie) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("mp42");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.TYPE3);
        linkedList.add("isom");
        return new FileTypeBox("iso6", 1L, linkedList);
    }

    protected MovieBox createMovieBox(Movie movie, Map<Track, int[]> map) {
        long duration;
        MovieBox movieBox = new MovieBox();
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setCreationTime(new Date());
        movieHeaderBox.setModificationTime(new Date());
        movieHeaderBox.setMatrix(movie.getMatrix());
        long timescale = getTimescale(movie);
        long j2 = 0;
        for (Track track : movie.getTracks()) {
            if (track.getEdits() != null && !track.getEdits().isEmpty()) {
                double d = 0.0d;
                Iterator<Edit> it = track.getEdits().iterator();
                while (it.hasNext()) {
                    double segmentDuration = (long) it.next().getSegmentDuration();
                    Double.isNaN(segmentDuration);
                    d += segmentDuration;
                }
                double d2 = timescale;
                Double.isNaN(d2);
                duration = (long) (d * d2);
            } else {
                duration = (track.getDuration() * timescale) / track.getTrackMetaData().getTimescale();
            }
            if (duration > j2) {
                j2 = duration;
            }
        }
        movieHeaderBox.setDuration(j2);
        movieHeaderBox.setTimescale(timescale);
        long j3 = 0;
        for (Track track2 : movie.getTracks()) {
            if (j3 < track2.getTrackMetaData().getTrackId()) {
                j3 = track2.getTrackMetaData().getTrackId();
            }
        }
        movieHeaderBox.setNextTrackId(j3 + 1);
        movieBox.addBox(movieHeaderBox);
        Iterator<Track> it2 = movie.getTracks().iterator();
        while (it2.hasNext()) {
            movieBox.addBox(createTrackBox(it2.next(), movie, map));
        }
        Box createUdta = createUdta(movie);
        if (createUdta != null) {
            movieBox.addBox(createUdta);
        }
        return movieBox;
    }

    protected void createSdtp(Track track, SampleTableBox sampleTableBox) {
        if (track.getSampleDependencies() == null || track.getSampleDependencies().isEmpty()) {
            return;
        }
        SampleDependencyTypeBox sampleDependencyTypeBox = new SampleDependencyTypeBox();
        sampleDependencyTypeBox.setEntries(track.getSampleDependencies());
        sampleTableBox.addBox(sampleDependencyTypeBox);
    }

    protected Box createStbl(Track track, Movie movie, Map<Track, int[]> map) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        createStsd(track, sampleTableBox);
        createStts(track, sampleTableBox);
        createCtts(track, sampleTableBox);
        createStss(track, sampleTableBox);
        createSdtp(track, sampleTableBox);
        createStsc(track, map, sampleTableBox);
        createStsz(track, sampleTableBox);
        createStco(track, movie, map, sampleTableBox);
        HashMap hashMap = new HashMap();
        for (Map.Entry<GroupEntry, long[]> entry : track.getSampleGroups().entrySet()) {
            String type = entry.getKey().getType();
            List list = (List) hashMap.get(type);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(type, list);
            }
            list.add(entry.getKey());
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            SampleGroupDescriptionBox sampleGroupDescriptionBox = new SampleGroupDescriptionBox();
            String str = (String) entry2.getKey();
            sampleGroupDescriptionBox.setGroupingType(str);
            sampleGroupDescriptionBox.setGroupEntries((List) entry2.getValue());
            SampleToGroupBox sampleToGroupBox = new SampleToGroupBox();
            sampleToGroupBox.setGroupingType(str);
            SampleToGroupBox.Entry entry3 = null;
            for (int i2 = 0; i2 < track.getSamples().size(); i2++) {
                int i3 = 0;
                for (int i4 = 0; i4 < ((List) entry2.getValue()).size(); i4++) {
                    if (Arrays.binarySearch(track.getSampleGroups().get((GroupEntry) ((List) entry2.getValue()).get(i4)), i2) >= 0) {
                        i3 = i4 + 1;
                    }
                }
                if (entry3 != null && entry3.getGroupDescriptionIndex() == i3) {
                    entry3.setSampleCount(entry3.getSampleCount() + 1);
                } else {
                    SampleToGroupBox.Entry entry4 = new SampleToGroupBox.Entry(1L, i3);
                    sampleToGroupBox.getEntries().add(entry4);
                    entry3 = entry4;
                }
            }
            sampleTableBox.addBox(sampleGroupDescriptionBox);
            sampleTableBox.addBox(sampleToGroupBox);
        }
        if (track instanceof CencEncryptedTrack) {
            createCencBoxes((CencEncryptedTrack) track, sampleTableBox, map.get(track));
        }
        createSubs(track, sampleTableBox);
        LOG.logDebug("done with stbl for track_" + track.getTrackMetaData().getTrackId());
        return sampleTableBox;
    }

    protected void createStco(Track track, Movie movie, Map<Track, int[]> map, SampleTableBox sampleTableBox) {
        char c2;
        int i2;
        if (this.chunkOffsetBoxes.get(track) == null) {
            LOG.logDebug("Calculating chunk offsets for track_" + track.getTrackMetaData().getTrackId());
            ArrayList<Track> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList, new Comparator<Track>() { // from class: com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder.1
                {
                    DefaultMp4Builder.this = this;
                }

                @Override // java.util.Comparator
                public int compare(Track track2, Track track3) {
                    return CastUtils.l2i(track2.getTrackMetaData().getTrackId() - track3.getTrackMetaData().getTrackId());
                }
            });
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            Iterator it = arrayList.iterator();
            while (true) {
                c2 = 0;
                if (!it.hasNext()) {
                    break;
                }
                ArrayList arrayList2 = arrayList;
                Track track2 = (Track) it.next();
                hashMap.put(track2, 0);
                hashMap2.put(track2, 0);
                hashMap3.put(track2, Double.valueOf(0.0d));
                this.chunkOffsetBoxes.put(track2, new StaticChunkOffsetBox());
                arrayList = arrayList2;
            }
            long j2 = 0;
            while (true) {
                Track track3 = null;
                for (Track track4 : arrayList) {
                    ArrayList arrayList3 = arrayList;
                    if ((track3 == null || ((Double) hashMap3.get(track4)).doubleValue() < ((Double) hashMap3.get(track3)).doubleValue()) && ((Integer) hashMap.get(track4)).intValue() < map.get(track4).length) {
                        track3 = track4;
                    }
                    arrayList = arrayList3;
                    c2 = 0;
                }
                if (track3 == null) {
                    break;
                }
                StaticChunkOffsetBox staticChunkOffsetBox = this.chunkOffsetBoxes.get(track3);
                long[] chunkOffsets = staticChunkOffsetBox.getChunkOffsets();
                long[] jArr = new long[1];
                jArr[c2] = j2;
                staticChunkOffsetBox.setChunkOffsets(Mp4Arrays.copyOfAndAppend(chunkOffsets, jArr));
                int intValue = ((Integer) hashMap.get(track3)).intValue();
                int i3 = map.get(track3)[intValue];
                int intValue2 = ((Integer) hashMap2.get(track3)).intValue();
                double doubleValue = ((Double) hashMap3.get(track3)).doubleValue();
                long[] sampleDurations = track3.getSampleDurations();
                int i4 = intValue2;
                while (true) {
                    i2 = intValue2 + i3;
                    if (i4 >= i2) {
                        break;
                    }
                    long j3 = j2 + this.track2SampleSizes.get(track3)[i4];
                    int i5 = intValue;
                    double d = sampleDurations[i4];
                    double timescale = track3.getTrackMetaData().getTimescale();
                    Double.isNaN(d);
                    Double.isNaN(timescale);
                    doubleValue += d / timescale;
                    i4++;
                    intValue = i5;
                    j2 = j3;
                    arrayList = arrayList;
                }
                hashMap.put(track3, Integer.valueOf(intValue + 1));
                hashMap2.put(track3, Integer.valueOf(i2));
                hashMap3.put(track3, Double.valueOf(doubleValue));
                c2 = 0;
            }
        }
        sampleTableBox.addBox(this.chunkOffsetBoxes.get(track));
    }

    protected void createStsc(Track track, Map<Track, int[]> map, SampleTableBox sampleTableBox) {
        int[] iArr = map.get(track);
        SampleToChunkBox sampleToChunkBox = new SampleToChunkBox();
        sampleToChunkBox.setEntries(new LinkedList());
        long j2 = -2147483648L;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (j2 != iArr[i2]) {
                sampleToChunkBox.getEntries().add(new SampleToChunkBox.Entry(i2 + 1, iArr[i2], 1L));
                j2 = iArr[i2];
            }
        }
        sampleTableBox.addBox(sampleToChunkBox);
    }

    protected void createStsd(Track track, SampleTableBox sampleTableBox) {
        sampleTableBox.addBox(track.getSampleDescriptionBox());
    }

    protected void createStss(Track track, SampleTableBox sampleTableBox) {
        long[] syncSamples = track.getSyncSamples();
        if (syncSamples == null || syncSamples.length <= 0) {
            return;
        }
        SyncSampleBox syncSampleBox = new SyncSampleBox();
        syncSampleBox.setSampleNumber(syncSamples);
        sampleTableBox.addBox(syncSampleBox);
    }

    protected void createStsz(Track track, SampleTableBox sampleTableBox) {
        SampleSizeBox sampleSizeBox = new SampleSizeBox();
        sampleSizeBox.setSampleSizes(this.track2SampleSizes.get(track));
        sampleTableBox.addBox(sampleSizeBox);
    }

    protected void createStts(Track track, SampleTableBox sampleTableBox) {
        ArrayList arrayList = new ArrayList();
        TimeToSampleBox.Entry entry = null;
        for (long j2 : track.getSampleDurations()) {
            if (entry != null && entry.getDelta() == j2) {
                entry.setCount(entry.getCount() + 1);
            } else {
                entry = new TimeToSampleBox.Entry(1L, j2);
                arrayList.add(entry);
            }
        }
        TimeToSampleBox timeToSampleBox = new TimeToSampleBox();
        timeToSampleBox.setEntries(arrayList);
        sampleTableBox.addBox(timeToSampleBox);
    }

    protected void createSubs(Track track, SampleTableBox sampleTableBox) {
        if (track.getSubsampleInformationBox() != null) {
            sampleTableBox.addBox(track.getSubsampleInformationBox());
        }
    }

    protected TrackBox createTrackBox(Track track, Movie movie, Map<Track, int[]> map) {
        TrackBox trackBox = new TrackBox();
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.setEnabled(true);
        trackHeaderBox.setInMovie(true);
        trackHeaderBox.setMatrix(track.getTrackMetaData().getMatrix());
        trackHeaderBox.setAlternateGroup(track.getTrackMetaData().getGroup());
        trackHeaderBox.setCreationTime(track.getTrackMetaData().getCreationTime());
        if (track.getEdits() != null && !track.getEdits().isEmpty()) {
            long j2 = 0;
            Iterator<Edit> it = track.getEdits().iterator();
            while (it.hasNext()) {
                j2 += (long) it.next().getSegmentDuration();
            }
            trackHeaderBox.setDuration(j2 * track.getTrackMetaData().getTimescale());
        } else {
            trackHeaderBox.setDuration((track.getDuration() * getTimescale(movie)) / track.getTrackMetaData().getTimescale());
        }
        trackHeaderBox.setHeight(track.getTrackMetaData().getHeight());
        trackHeaderBox.setWidth(track.getTrackMetaData().getWidth());
        trackHeaderBox.setLayer(track.getTrackMetaData().getLayer());
        trackHeaderBox.setModificationTime(new Date());
        trackHeaderBox.setTrackId(track.getTrackMetaData().getTrackId());
        trackHeaderBox.setVolume(track.getTrackMetaData().getVolume());
        trackBox.addBox(trackHeaderBox);
        trackBox.addBox(createEdts(track, movie));
        MediaBox mediaBox = new MediaBox();
        trackBox.addBox(mediaBox);
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(track.getTrackMetaData().getCreationTime());
        mediaHeaderBox.setDuration(track.getDuration());
        mediaHeaderBox.setTimescale(track.getTrackMetaData().getTimescale());
        mediaHeaderBox.setLanguage(track.getTrackMetaData().getLanguage());
        mediaBox.addBox(mediaHeaderBox);
        HandlerBox handlerBox = new HandlerBox();
        mediaBox.addBox(handlerBox);
        handlerBox.setHandlerType(track.getHandler());
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (track.getHandler().equals("vide")) {
            mediaInformationBox.addBox(new VideoMediaHeaderBox());
        } else if (track.getHandler().equals("soun")) {
            mediaInformationBox.addBox(new SoundMediaHeaderBox());
        } else if (track.getHandler().equals("text")) {
            mediaInformationBox.addBox(new NullMediaHeaderBox());
        } else if (track.getHandler().equals("subt")) {
            mediaInformationBox.addBox(new SubtitleMediaHeaderBox());
        } else if (track.getHandler().equals(DYConstants.DY_HINT)) {
            mediaInformationBox.addBox(new HintMediaHeaderBox());
        } else if (track.getHandler().equals("sbtl")) {
            mediaInformationBox.addBox(new NullMediaHeaderBox());
        }
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.addBox(dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.setFlags(1);
        dataReferenceBox.addBox(dataEntryUrlBox);
        mediaInformationBox.addBox(dataInformationBox);
        mediaInformationBox.addBox(createStbl(track, movie, map));
        mediaBox.addBox(mediaInformationBox);
        LOG.logDebug("done with trak for track_" + track.getTrackMetaData().getTrackId());
        return trackBox;
    }

    protected Box createUdta(Movie movie) {
        return null;
    }

    int[] getChunkSizes(Track track) {
        long j2;
        long[] sampleNumbers = this.fragmenter.sampleNumbers(track);
        int[] iArr = new int[sampleNumbers.length];
        int i2 = 0;
        while (i2 < sampleNumbers.length) {
            long j3 = sampleNumbers[i2] - 1;
            int i3 = i2 + 1;
            if (sampleNumbers.length == i3) {
                j2 = track.getSamples().size();
            } else {
                j2 = sampleNumbers[i3] - 1;
            }
            iArr[i2] = CastUtils.l2i(j2 - j3);
            i2 = i3;
        }
        return iArr;
    }

    public long getTimescale(Movie movie) {
        long timescale = movie.getTracks().iterator().next().getTrackMetaData().getTimescale();
        Iterator<Track> it = movie.getTracks().iterator();
        while (it.hasNext()) {
            timescale = Math.lcm(timescale, it.next().getTrackMetaData().getTimescale());
        }
        return timescale;
    }

    protected List<Sample> putSamples(Track track, List<Sample> list) {
        return this.track2Sample.put(track, list);
    }

    public void setFragmenter(Fragmenter fragmenter) {
        this.fragmenter = fragmenter;
    }

    private static long sum(long[] jArr) {
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += j3;
        }
        return j2;
    }

    /* loaded from: classes12.dex */
    public class InterleaveChunkMdat implements Box {
        List<List<Sample>> chunkList;
        long contentSize;
        Container parent;
        List<Track> tracks;

        private InterleaveChunkMdat(Movie movie, Map<Track, int[]> map, long j2) {
            int i2;
            DefaultMp4Builder.this = r17;
            this.chunkList = new ArrayList();
            this.contentSize = j2;
            this.tracks = movie.getTracks();
            ArrayList<Track> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList, new Comparator<Track>() { // from class: com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder.InterleaveChunkMdat.1
                {
                    InterleaveChunkMdat.this = this;
                }

                @Override // java.util.Comparator
                public int compare(Track track, Track track2) {
                    return CastUtils.l2i(track.getTrackMetaData().getTrackId() - track2.getTrackMetaData().getTrackId());
                }
            });
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            for (Track track : arrayList) {
                hashMap.put(track, 0);
                hashMap2.put(track, 0);
                hashMap3.put(track, Double.valueOf(0.0d));
            }
            while (true) {
                Track track2 = null;
                for (Track track3 : arrayList) {
                    if (track2 == null || ((Double) hashMap3.get(track3)).doubleValue() < ((Double) hashMap3.get(track2)).doubleValue()) {
                        if (((Integer) hashMap.get(track3)).intValue() < map.get(track3).length) {
                            track2 = track3;
                        }
                    }
                }
                if (track2 == null) {
                    return;
                }
                int intValue = ((Integer) hashMap.get(track2)).intValue();
                int i3 = map.get(track2)[intValue];
                int intValue2 = ((Integer) hashMap2.get(track2)).intValue();
                double doubleValue = ((Double) hashMap3.get(track2)).doubleValue();
                int i4 = intValue2;
                while (true) {
                    i2 = intValue2 + i3;
                    if (i4 >= i2) {
                        break;
                    }
                    double d = track2.getSampleDurations()[i4];
                    int i5 = intValue;
                    double timescale = track2.getTrackMetaData().getTimescale();
                    Double.isNaN(d);
                    Double.isNaN(timescale);
                    doubleValue += d / timescale;
                    i4++;
                    i3 = i3;
                    intValue = i5;
                }
                this.chunkList.add(track2.getSamples().subList(intValue2, i2));
                hashMap.put(track2, Integer.valueOf(intValue + 1));
                hashMap2.put(track2, Integer.valueOf(i2));
                hashMap3.put(track2, Double.valueOf(doubleValue));
            }
        }

        private boolean isSmallBox(long j2) {
            return j2 + 8 < IjkMediaMeta.AV_CH_WIDE_RIGHT;
        }

        @Override // com.coremedia.iso.boxes.Box
        public void getBox(WritableByteChannel writableByteChannel) throws IOException {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            long size = getSize();
            if (isSmallBox(size)) {
                IsoTypeWriter.writeUInt32(allocate, size);
            } else {
                IsoTypeWriter.writeUInt32(allocate, 1L);
            }
            allocate.put(IsoFile.fourCCtoBytes(MediaDataBox.TYPE));
            if (isSmallBox(size)) {
                allocate.put(new byte[8]);
            } else {
                IsoTypeWriter.writeUInt64(allocate, size);
            }
            allocate.rewind();
            writableByteChannel.write(allocate);
            DefaultMp4Builder.LOG.logDebug("About to write " + this.contentSize);
            Iterator<List<Sample>> it = this.chunkList.iterator();
            long j2 = 0;
            long j3 = 0;
            while (it.hasNext()) {
                for (Sample sample : it.next()) {
                    sample.writeTo(writableByteChannel);
                    j2 += sample.getSize();
                    if (j2 > 1048576) {
                        j2 -= 1048576;
                        j3++;
                        DefaultMp4Builder.LOG.logDebug("Written " + j3 + "MB");
                    }
                }
            }
        }

        public long getDataOffset() {
            Box next;
            long j2 = 16;
            Container container = this;
            while (container instanceof Box) {
                InterleaveChunkMdat interleaveChunkMdat = container;
                Iterator<Box> it = interleaveChunkMdat.getParent().getBoxes().iterator();
                while (it.hasNext() && container != (next = it.next())) {
                    j2 += next.getSize();
                }
                container = interleaveChunkMdat.getParent();
            }
            return j2;
        }

        @Override // com.coremedia.iso.boxes.Box
        public long getOffset() {
            throw new RuntimeException("Doesn't have any meaning for programmatically created boxes");
        }

        @Override // com.coremedia.iso.boxes.Box
        public Container getParent() {
            return this.parent;
        }

        @Override // com.coremedia.iso.boxes.Box
        public long getSize() {
            return this.contentSize + 16;
        }

        @Override // com.coremedia.iso.boxes.Box
        public String getType() {
            return MediaDataBox.TYPE;
        }

        @Override // com.coremedia.iso.boxes.Box
        public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException {
        }

        @Override // com.coremedia.iso.boxes.Box
        public void setParent(Container container) {
            this.parent = container;
        }

        /* synthetic */ InterleaveChunkMdat(DefaultMp4Builder defaultMp4Builder, Movie movie, Map map, long j2, InterleaveChunkMdat interleaveChunkMdat) {
            this(movie, map, j2);
        }
    }
}
