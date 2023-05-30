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
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes14.dex */
public class SingleTrackFragmentedMp4Writer implements StreamingMp4Writer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    CompositionTimeTrackExtension compositionTimeTrackExtension;
    private final OutputStream outputStream;
    SampleFlagsTrackExtension sampleDependencyTrackExtension;
    private long sequenceNumber;
    StreamingTrack source;
    List<StreamingSample> fragment = new ArrayList();
    private long currentFragmentStartTime = 0;
    private long currentTime = 0;
    Date creationTime = new Date();

    public SingleTrackFragmentedMp4Writer(StreamingTrack streamingTrack, OutputStream outputStream) {
        this.source = streamingTrack;
        this.outputStream = outputStream;
        this.compositionTimeTrackExtension = (CompositionTimeTrackExtension) streamingTrack.getTrackExtension(CompositionTimeTrackExtension.class);
        this.sampleDependencyTrackExtension = (SampleFlagsTrackExtension) streamingTrack.getTrackExtension(SampleFlagsTrackExtension.class);
    }

    private void consumeSample(StreamingSample streamingSample, WritableByteChannel writableByteChannel) throws IOException {
        SampleFlagsSampleExtension sampleFlagsSampleExtension = null;
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            if (sampleExtension instanceof SampleFlagsSampleExtension) {
                sampleFlagsSampleExtension = (SampleFlagsSampleExtension) sampleExtension;
            } else if (sampleExtension instanceof CompositionTimeSampleExtension) {
                CompositionTimeSampleExtension compositionTimeSampleExtension = (CompositionTimeSampleExtension) sampleExtension;
            }
        }
        this.currentTime += streamingSample.getDuration();
        this.fragment.add(streamingSample);
        if (this.currentTime <= this.currentFragmentStartTime + (this.source.getTimescale() * 3) || this.fragment.size() <= 0) {
            return;
        }
        if (this.sampleDependencyTrackExtension == null || sampleFlagsSampleExtension == null || sampleFlagsSampleExtension.isSyncSample()) {
            createMoof().getBox(writableByteChannel);
            createMdat().getBox(writableByteChannel);
            this.currentFragmentStartTime = this.currentTime;
            this.fragment.clear();
        }
    }

    private Box createMdat() {
        return new WriteOnlyBox(MediaDataBox.TYPE) { // from class: com.mp4parser.streaming.SingleTrackFragmentedMp4Writer.1
            @Override // com.coremedia.iso.boxes.Box
            public void getBox(WritableByteChannel writableByteChannel) throws IOException {
                ArrayList arrayList = new ArrayList();
                Iterator<StreamingSample> it = SingleTrackFragmentedMp4Writer.this.fragment.iterator();
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
                while (SingleTrackFragmentedMp4Writer.this.fragment.iterator().hasNext()) {
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

    private Box createMoof() {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        createMfhd(this.sequenceNumber, movieFragmentBox);
        createTraf(this.sequenceNumber, movieFragmentBox);
        TrackRunBox trackRunBox = movieFragmentBox.getTrackRunBoxes().get(0);
        trackRunBox.setDataOffset(1);
        trackRunBox.setDataOffset((int) (movieFragmentBox.getSize() + 8));
        return movieFragmentBox;
    }

    private void createTraf(long j2, MovieFragmentBox movieFragmentBox) {
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.addBox(trackFragmentBox);
        createTfhd(trackFragmentBox);
        createTfdt(trackFragmentBox);
        createTrun(trackFragmentBox);
        this.source.getTrackExtension(CencEncryptTrackExtension.class);
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

    protected Box createMdhd() {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(this.creationTime);
        mediaHeaderBox.setModificationTime(this.creationTime);
        mediaHeaderBox.setDuration(0L);
        mediaHeaderBox.setTimescale(this.source.getTimescale());
        mediaHeaderBox.setLanguage(this.source.getLanguage());
        return mediaHeaderBox;
    }

    protected Box createMdia() {
        MediaBox mediaBox = new MediaBox();
        mediaBox.addBox(createMdhd());
        mediaBox.addBox(createMdiaHdlr());
        mediaBox.addBox(createMinf());
        return mediaBox;
    }

    protected Box createMdiaHdlr() {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.setHandlerType(this.source.getHandler());
        return handlerBox;
    }

    protected Box createMinf() {
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (this.source.getHandler().equals("vide")) {
            mediaInformationBox.addBox(new VideoMediaHeaderBox());
        } else if (this.source.getHandler().equals("soun")) {
            mediaInformationBox.addBox(new SoundMediaHeaderBox());
        } else if (this.source.getHandler().equals("text")) {
            mediaInformationBox.addBox(new NullMediaHeaderBox());
        } else if (this.source.getHandler().equals("subt")) {
            mediaInformationBox.addBox(new SubtitleMediaHeaderBox());
        } else if (this.source.getHandler().equals(DYConstants.DY_HINT)) {
            mediaInformationBox.addBox(new HintMediaHeaderBox());
        } else if (this.source.getHandler().equals("sbtl")) {
            mediaInformationBox.addBox(new NullMediaHeaderBox());
        }
        mediaInformationBox.addBox(createDinf());
        mediaInformationBox.addBox(createStbl());
        return mediaInformationBox;
    }

    protected Box createMoov() {
        MovieBox movieBox = new MovieBox();
        movieBox.addBox(createMvhd());
        movieBox.addBox(createTrak());
        movieBox.addBox(createMvex());
        return movieBox;
    }

    protected Box createMvex() {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.setVersion(1);
        movieExtendsHeaderBox.setFragmentDuration(0L);
        movieExtendsBox.addBox(movieExtendsHeaderBox);
        movieExtendsBox.addBox(createTrex());
        return movieExtendsBox;
    }

    protected Box createMvhd() {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setVersion(1);
        movieHeaderBox.setCreationTime(this.creationTime);
        movieHeaderBox.setModificationTime(this.creationTime);
        movieHeaderBox.setDuration(0L);
        movieHeaderBox.setTimescale(this.source.getTimescale());
        movieHeaderBox.setNextTrackId(2L);
        return movieHeaderBox;
    }

    protected Box createStbl() {
        SampleTableBox sampleTableBox = new SampleTableBox();
        sampleTableBox.addBox(this.source.getSampleDescriptionBox());
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

    protected void createTfhd(TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        trackFragmentHeaderBox.setDefaultSampleFlags(new SampleFlags());
        trackFragmentHeaderBox.setBaseDataOffset(-1L);
        TrackIdTrackExtension trackIdTrackExtension = (TrackIdTrackExtension) this.source.getTrackExtension(TrackIdTrackExtension.class);
        if (trackIdTrackExtension != null) {
            trackFragmentHeaderBox.setTrackId(trackIdTrackExtension.getTrackId());
        } else {
            trackFragmentHeaderBox.setTrackId(1L);
        }
        trackFragmentHeaderBox.setDefaultBaseIsMoof(true);
        trackFragmentBox.addBox(trackFragmentHeaderBox);
    }

    protected Box createTrak() {
        TrackBox trackBox = new TrackBox();
        trackBox.addBox(this.source.getTrackHeaderBox());
        trackBox.addBox(createMdia());
        return trackBox;
    }

    protected Box createTrex() {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.setTrackId(this.source.getTrackHeaderBox().getTrackId());
        trackExtendsBox.setDefaultSampleDescriptionIndex(1L);
        trackExtendsBox.setDefaultSampleDuration(0L);
        trackExtendsBox.setDefaultSampleSize(0L);
        SampleFlags sampleFlags = new SampleFlags();
        if ("soun".equals(this.source.getHandler()) || "subt".equals(this.source.getHandler())) {
            sampleFlags.setSampleDependsOn(2);
            sampleFlags.setSampleIsDependedOn(2);
        }
        trackExtendsBox.setDefaultSampleFlags(sampleFlags);
        return trackExtendsBox;
    }

    protected void createTrun(TrackFragmentBox trackFragmentBox) {
        TrackRunBox trackRunBox = new TrackRunBox();
        trackRunBox.setVersion(1);
        trackRunBox.setSampleDurationPresent(true);
        trackRunBox.setSampleSizePresent(true);
        ArrayList arrayList = new ArrayList(this.fragment.size());
        trackRunBox.setSampleCompositionTimeOffsetPresent(this.source.getTrackExtension(CompositionTimeTrackExtension.class) != null);
        boolean z = this.source.getTrackExtension(SampleFlagsTrackExtension.class) != null;
        trackRunBox.setSampleFlagsPresent(z);
        for (StreamingSample streamingSample : this.fragment) {
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
        StreamingSample poll;
        WritableByteChannel newChannel = Channels.newChannel(this.outputStream);
        createFtyp().getBox(newChannel);
        createMoov().getBox(newChannel);
        while (true) {
            try {
                poll = this.source.getSamples().poll(100L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (poll != null) {
                consumeSample(poll, newChannel);
            } else if (!this.source.hasMoreSamples()) {
                return;
            }
        }
    }
}
