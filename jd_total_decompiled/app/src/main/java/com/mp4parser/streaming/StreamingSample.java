package com.mp4parser.streaming;

import java.nio.ByteBuffer;

/* loaded from: classes14.dex */
public interface StreamingSample {
    ByteBuffer getContent();

    long getDuration();

    SampleExtension[] getExtensions();
}
