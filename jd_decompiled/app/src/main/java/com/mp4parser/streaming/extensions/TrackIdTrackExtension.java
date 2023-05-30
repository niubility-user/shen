package com.mp4parser.streaming.extensions;

import com.mp4parser.streaming.TrackExtension;

/* loaded from: classes14.dex */
public class TrackIdTrackExtension implements TrackExtension {
    private long trackId;

    public TrackIdTrackExtension(long j2) {
        this.trackId = 1L;
        this.trackId = j2;
    }

    public long getTrackId() {
        return this.trackId;
    }
}
