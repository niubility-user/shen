package com.mp4parser.streaming;

import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes14.dex */
public abstract class AbstractStreamingTrack implements StreamingTrack {
    protected SampleDescriptionBox stsd;
    protected TrackHeaderBox tkhd;
    protected BlockingQueue<StreamingSample> samples = new ArrayBlockingQueue(1000);
    protected HashMap<Class<? extends TrackExtension>, TrackExtension> trackExtensions = new HashMap<>();

    public AbstractStreamingTrack() {
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        this.tkhd = trackHeaderBox;
        trackHeaderBox.setTrackId(1L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.mp4parser.streaming.StreamingTrack
    public void addTrackExtension(TrackExtension trackExtension) {
        this.trackExtensions.put(trackExtension.getClass(), trackExtension);
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public BlockingQueue<StreamingSample> getSamples() {
        return this.samples;
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public <T extends TrackExtension> T getTrackExtension(Class<T> cls) {
        return (T) this.trackExtensions.get(cls);
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public TrackHeaderBox getTrackHeaderBox() {
        return this.tkhd;
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public boolean hasMoreSamples() {
        return false;
    }

    @Override // com.mp4parser.streaming.StreamingTrack
    public void removeTrackExtension(Class<? extends TrackExtension> cls) {
        this.trackExtensions.remove(cls);
    }
}
