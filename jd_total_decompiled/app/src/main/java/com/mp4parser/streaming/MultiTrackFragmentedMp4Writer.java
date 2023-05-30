package com.mp4parser.streaming;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.DataEntryUrlBox;
import com.coremedia.iso.boxes.DataInformationBox;
import com.coremedia.iso.boxes.DataReferenceBox;
import com.coremedia.iso.boxes.FileTypeBox;
import com.coremedia.iso.boxes.HandlerBox;
import com.coremedia.iso.boxes.HintMediaHeaderBox;
import com.coremedia.iso.boxes.MediaBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MediaInformationBox;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.NullMediaHeaderBox;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SampleToChunkBox;
import com.coremedia.iso.boxes.SoundMediaHeaderBox;
import com.coremedia.iso.boxes.StaticChunkOffsetBox;
import com.coremedia.iso.boxes.SubtitleMediaHeaderBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.VideoMediaHeaderBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsBox;
import com.coremedia.iso.boxes.fragment.MovieExtendsHeaderBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentBox;
import com.coremedia.iso.boxes.fragment.MovieFragmentHeaderBox;
import com.coremedia.iso.boxes.fragment.SampleFlags;
import com.coremedia.iso.boxes.fragment.TrackExtendsBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBaseMediaDecodeTimeBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentBox;
import com.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import com.coremedia.iso.boxes.fragment.TrackRunBox;
import com.coremedia.iso.boxes.mdat.MediaDataBox;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.util.Math;
import com.googlecode.mp4parser.util.Mp4Arrays;
import com.jd.dynamic.DYConstants;
import com.mp4parser.streaming.extensions.CencEncryptTrackExtension;
import com.mp4parser.streaming.extensions.CompositionTimeSampleExtension;
import com.mp4parser.streaming.extensions.CompositionTimeTrackExtension;
import com.mp4parser.streaming.extensions.SampleFlagsSampleExtension;
import com.mp4parser.streaming.extensions.SampleFlagsTrackExtension;
import com.mp4parser.streaming.extensions.TrackIdTrackExtension;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes14.dex */
public class MultiTrackFragmentedMp4Writer implements StreamingMp4Writer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    CompositionTimeTrackExtension compositionTimeTrackExtension;
    private final OutputStream outputStream;
    SampleFlagsTrackExtension sampleDependencyTrackExtension;
    StreamingTrack[] source;
    Map<StreamingTrack, List<StreamingSample>> fragmentBuffers = new HashMap();
    private long sequenceNumber = 1;
    private long currentFragmentStartTime = 0;
    private long currentTime = 0;
    Date creationTime = new Date();

    /* loaded from: classes14.dex */
    class ConsumeSamplesCallable implements Callable {
        private StreamingTrack streamingTrack;

        public ConsumeSamplesCallable(StreamingTrack streamingTrack) {
            this.streamingTrack = streamingTrack;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            StreamingSample poll;
            while (true) {
                try {
                    poll = this.streamingTrack.getSamples().poll(100L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (poll != null) {
                    MultiTrackFragmentedMp4Writer.this.consumeSample(this.streamingTrack, poll);
                } else if (!this.streamingTrack.hasMoreSamples()) {
                    return null;
                }
            }
        }
    }

    public MultiTrackFragmentedMp4Writer(StreamingTrack[] streamingTrackArr, OutputStream outputStream) {
        this.source = streamingTrackArr;
        this.outputStream = outputStream;
        HashSet hashSet = new HashSet();
        for (StreamingTrack streamingTrack : streamingTrackArr) {
            if (streamingTrack.getTrackExtension(TrackIdTrackExtension.class) != null && hashSet.contains(Long.valueOf(((TrackIdTrackExtension) streamingTrack.getTrackExtension(TrackIdTrackExtension.class)).getTrackId()))) {
                throw new RuntimeException("There may not be two tracks with the same trackID within one file");
            }
        }
        for (StreamingTrack streamingTrack2 : streamingTrackArr) {
            if (streamingTrack2.getTrackExtension(TrackIdTrackExtension.class) != null) {
                ArrayList arrayList = new ArrayList(hashSet);
                Collections.sort(arrayList);
                streamingTrack2.addTrackExtension(new TrackIdTrackExtension(arrayList.size() > 0 ? ((Long) arrayList.get(arrayList.size() - 1)).longValue() + 1 : 1L));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void consumeSample(StreamingTrack streamingTrack, StreamingSample streamingSample) throws IOException {
        SampleFlagsSampleExtension sampleFlagsSampleExtension = null;
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            if (sampleExtension instanceof SampleFlagsSampleExtension) {
                sampleFlagsSampleExtension = (SampleFlagsSampleExtension) sampleExtension;
            } else if (sampleExtension instanceof CompositionTimeSampleExtension) {
                CompositionTimeSampleExtension compositionTimeSampleExtension = (CompositionTimeSampleExtension) sampleExtension;
            }
        }
        this.currentTime += streamingSample.getDuration();
        this.fragmentBuffers.get(streamingTrack).add(streamingSample);
        long j2 = this.currentTime;
        long j3 = this.currentFragmentStartTime;
        long timescale = streamingTrack.getTimescale();
        Long.signum(timescale);
        if (j2 > j3 + (timescale * 3) && this.fragmentBuffers.size() > 0 && (this.sampleDependencyTrackExtension == null || sampleFlagsSampleExtension == null || sampleFlagsSampleExtension.isSyncSample())) {
            WritableByteChannel newChannel = Channels.newChannel(this.outputStream);
            createMoof(streamingTrack).getBox(newChannel);
            createMdat(streamingTrack).getBox(newChannel);
            this.currentFragmentStartTime = this.currentTime;
            this.fragmentBuffers.clear();
        }
    }

    private Box createMdat(final StreamingTrack streamingTrack) {
        return new WriteOnlyBox(MediaDataBox.TYPE) { // from class: com.mp4parser.streaming.MultiTrackFragmentedMp4Writer.1
            @Override // com.coremedia.iso.boxes.Box
            public void getBox(WritableByteChannel writableByteChannel) throws IOException {
                ArrayList arrayList = new ArrayList();
                Iterator<StreamingSample> it = MultiTrackFragmentedMp4Writer.this.fragmentBuffers.get(streamingTrack).iterator();
                long j2 = 8;
                while (it.hasNext()) {
                    arrayList.add(it.next().getContent());
                    j2 += r4.remaining();
                }
                ByteBuffer allocate = ByteBuffer.allocate(8);
                IsoTypeWriter.writeUInt32(allocate, j2);
                allocate.put(IsoFile.fourCCtoBytes(getType()));
                writableByteChannel.write((ByteBuffer) allocate.rewind());
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    writableByteChannel.write((ByteBuffer) it2.next());
                }
            }

            @Override // com.coremedia.iso.boxes.Box
            public long getSize() {
                long j2 = 8;
                while (MultiTrackFragmentedMp4Writer.this.fragmentBuffers.get(streamingTrack).iterator().hasNext()) {
                    j2 += r0.next().getContent().remaining();
                }
                return j2;
            }
        };
    }

    private void createMfhd(long j2, MovieFragmentBox movieFragmentBox) {
        MovieFragmentHeaderBox movieFragmentHeaderBox = new MovieFragmentHeaderBox();
        movieFragmentHeaderBox.setSequenceNumber(j2);
        movieFragmentBox.addBox(movieFragmentHeaderBox);
    }

    private Box createMoof(StreamingTrack streamingTrack) {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        createMfhd(this.sequenceNumber, movieFragmentBox);
        createTraf(streamingTrack, movieFragmentBox);
        TrackRunBox trackRunBox = movieFragmentBox.getTrackRunBoxes().get(0);
        trackRunBox.setDataOffset(1);
        trackRunBox.setDataOffset((int) (movieFragmentBox.getSize() + 8));
        this.sequenceNumber++;
        return movieFragmentBox;
    }

    private void createTraf(StreamingTrack streamingTrack, MovieFragmentBox movieFragmentBox) {
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.addBox(trackFragmentBox);
        createTfhd(streamingTrack, trackFragmentBox);
        createTfdt(trackFragmentBox);
        createTrun(streamingTrack, trackFragmentBox);
        streamingTrack.getTrackExtension(CencEncryptTrackExtension.class);
    }

    @Override // com.mp4parser.streaming.StreamingMp4Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    protected DataInformationBox createDinf() {
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.addBox(dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.setFlags(1);
        dataReferenceBox.addBox(dataEntryUrlBox);
        return dataInformationBox;
    }

    public Box createFtyp() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("isom");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.TYPE3);
        return new FileTypeBox("isom", 0L, linkedList);
    }

    protected Box createMdhd(StreamingTrack streamingTrack) {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(this.creationTime);
        mediaHeaderBox.setModificationTime(this.creationTime);
        mediaHeaderBox.setDuration(0L);
        mediaHeaderBox.setTimescale(streamingTrack.getTimescale());
        mediaHeaderBox.setLanguage(streamingTrack.getLanguage());
        return mediaHeaderBox;
    }

    protected Box createMdia(StreamingTrack streamingTrack) {
        MediaBox mediaBox = new MediaBox();
        mediaBox.addBox(createMdhd(streamingTrack));
        mediaBox.addBox(createMdiaHdlr(streamingTrack));
        mediaBox.addBox(createMinf(streamingTrack));
        return mediaBox;
    }

    protected Box createMdiaHdlr(StreamingTrack streamingTrack) {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.setHandlerType(streamingTrack.getHandler());
        return handlerBox;
    }

    protected Box createMinf(StreamingTrack streamingTrack) {
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (streamingTrack.getHandler().equals("vide")) {
            mediaInformationBox.addBox(new VideoMediaHeaderBox());
        } else if (streamingTrack.getHandler().equals("soun")) {
            mediaInformationBox.addBox(new SoundMediaHeaderBox());
        } else if (streamingTrack.getHandler().equals("text")) {
            mediaInformationBox.addBox(new NullMediaHeaderBox());
        } else if (streamingTrack.getHandler().equals("subt")) {
            mediaInformationBox.addBox(new SubtitleMediaHeaderBox());
        } else if (streamingTrack.getHandler().equals(DYConstants.DY_HINT)) {
            mediaInformationBox.addBox(new HintMediaHeaderBox());
        } else if (streamingTrack.getHandler().equals("sbtl")) {
            mediaInformationBox.addBox(new NullMediaHeaderBox());
        }
        mediaInformationBox.addBox(createDinf());
        mediaInformationBox.addBox(createStbl(streamingTrack));
        return mediaInformationBox;
    }

    protected Box createMoov() {
        MovieBox movieBox = new MovieBox();
        movieBox.addBox(createMvhd());
        for (StreamingTrack streamingTrack : this.source) {
            movieBox.addBox(createTrak(streamingTrack));
        }
        movieBox.addBox(createMvex());
        return movieBox;
    }

    protected Box createMvex() {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.setVersion(1);
        movieExtendsHeaderBox.setFragmentDuration(0L);
        movieExtendsBox.addBox(movieExtendsHeaderBox);
        for (StreamingTrack streamingTrack : this.source) {
            movieExtendsBox.addBox(createTrex(streamingTrack));
        }
        return movieExtendsBox;
    }

    protected Box createMvhd() {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setVersion(1);
        movieHeaderBox.setCreationTime(this.creationTime);
        movieHeaderBox.setModificationTime(this.creationTime);
        movieHeaderBox.setDuration(0L);
        long[] jArr = new long[0];
        for (StreamingTrack streamingTrack : this.source) {
            Mp4Arrays.copyOfAndAppend(jArr, streamingTrack.getTimescale());
        }
        movieHeaderBox.setTimescale(Math.lcm(jArr));
        movieHeaderBox.setNextTrackId(2L);
        return movieHeaderBox;
    }

    protected Box createStbl(StreamingTrack streamingTrack) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        sampleTableBox.addBox(streamingTrack.getSampleDescriptionBox());
        sampleTableBox.addBox(new TimeToSampleBox());
        sampleTableBox.addBox(new SampleToChunkBox());
        sampleTableBox.addBox(new SampleSizeBox());
        sampleTableBox.addBox(new StaticChunkOffsetBox());
        return sampleTableBox;
    }

    protected void createTfdt(TrackFragmentBox trackFragmentBox) {
        TrackFragmentBaseMediaDecodeTimeBox trackFragmentBaseMediaDecodeTimeBox = new TrackFragmentBaseMediaDecodeTimeBox();
        trackFragmentBaseMediaDecodeTimeBox.setVersion(1);
        trackFragmentBaseMediaDecodeTimeBox.setBaseMediaDecodeTime(this.currentFragmentStartTime);
        trackFragmentBox.addBox(trackFragmentBaseMediaDecodeTimeBox);
    }

    protected void createTfhd(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        trackFragmentHeaderBox.setDefaultSampleFlags(new SampleFlags());
        trackFragmentHeaderBox.setBaseDataOffset(-1L);
        trackFragmentHeaderBox.setTrackId(((TrackIdTrackExtension) streamingTrack.getTrackExtension(TrackIdTrackExtension.class)).getTrackId());
        trackFragmentHeaderBox.setDefaultBaseIsMoof(true);
        trackFragmentBox.addBox(trackFragmentHeaderBox);
    }

    protected Box createTrak(StreamingTrack streamingTrack) {
        TrackBox trackBox = new TrackBox();
        trackBox.addBox(streamingTrack.getTrackHeaderBox());
        trackBox.addBox(streamingTrack.getTrackHeaderBox());
        trackBox.addBox(createMdia(streamingTrack));
        return trackBox;
    }

    protected Box createTrex(StreamingTrack streamingTrack) {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.setTrackId(streamingTrack.getTrackHeaderBox().getTrackId());
        trackExtendsBox.setDefaultSampleDescriptionIndex(1L);
        trackExtendsBox.setDefaultSampleDuration(0L);
        trackExtendsBox.setDefaultSampleSize(0L);
        SampleFlags sampleFlags = new SampleFlags();
        if ("soun".equals(streamingTrack.getHandler()) || "subt".equals(streamingTrack.getHandler())) {
            sampleFlags.setSampleDependsOn(2);
            sampleFlags.setSampleIsDependedOn(2);
        }
        trackExtendsBox.setDefaultSampleFlags(sampleFlags);
        return trackExtendsBox;
    }

    protected void createTrun(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox) {
        TrackRunBox trackRunBox = new TrackRunBox();
        trackRunBox.setVersion(1);
        trackRunBox.setSampleDurationPresent(true);
        trackRunBox.setSampleSizePresent(true);
        ArrayList arrayList = new ArrayList(this.fragmentBuffers.size());
        trackRunBox.setSampleCompositionTimeOffsetPresent(streamingTrack.getTrackExtension(CompositionTimeTrackExtension.class) != null);
        boolean z = streamingTrack.getTrackExtension(SampleFlagsTrackExtension.class) != null;
        trackRunBox.setSampleFlagsPresent(z);
        for (StreamingSample streamingSample : this.fragmentBuffers.get(streamingTrack)) {
            TrackRunBox.Entry entry = new TrackRunBox.Entry();
            entry.setSampleSize(streamingSample.getContent().remaining());
            if (z) {
                SampleFlagsSampleExtension sampleFlagsSampleExtension = (SampleFlagsSampleExtension) StreamingSampleHelper.getSampleExtension(streamingSample, SampleFlagsSampleExtension.class);
                SampleFlags sampleFlags = new SampleFlags();
                sampleFlags.setIsLeading(sampleFlagsSampleExtension.getIsLeading());
                sampleFlags.setSampleIsDependedOn(sampleFlagsSampleExtension.getSampleIsDependedOn());
                sampleFlags.setSampleDependsOn(sampleFlagsSampleExtension.getSampleDependsOn());
                sampleFlags.setSampleHasRedundancy(sampleFlagsSampleExtension.getSampleHasRedundancy());
                sampleFlags.setSampleIsDifferenceSample(sampleFlagsSampleExtension.isSampleIsNonSyncSample());
                sampleFlags.setSamplePaddingValue(sampleFlagsSampleExtension.getSamplePaddingValue());
                sampleFlags.setSampleDegradationPriority(sampleFlagsSampleExtension.getSampleDegradationPriority());
                entry.setSampleFlags(sampleFlags);
            }
            entry.setSampleDuration(streamingSample.getDuration());
            if (trackRunBox.isSampleCompositionTimeOffsetPresent()) {
                entry.setSampleCompositionTimeOffset(((CompositionTimeSampleExtension) StreamingSampleHelper.getSampleExtension(streamingSample, CompositionTimeSampleExtension.class)).getCompositionTimeOffset());
            }
            arrayList.add(entry);
        }
        trackRunBox.setEntries(arrayList);
        trackFragmentBox.addBox(trackRunBox);
    }

    @Override // com.mp4parser.streaming.StreamingMp4Writer
    public void write() throws IOException {
        WritableByteChannel newChannel = Channels.newChannel(this.outputStream);
        createFtyp().getBox(newChannel);
        createMoov().getBox(newChannel);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(this.source.length);
        for (StreamingTrack streamingTrack : this.source) {
            newFixedThreadPool.submit(new ConsumeSamplesCallable(streamingTrack));
        }
    }
}
