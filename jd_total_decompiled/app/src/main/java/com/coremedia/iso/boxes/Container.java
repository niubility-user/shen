package com.coremedia.iso.boxes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.List;

/* loaded from: classes.dex */
public interface Container {
    List<Box> getBoxes();

    <T extends Box> List<T> getBoxes(Class<T> cls);

    <T extends Box> List<T> getBoxes(Class<T> cls, boolean z);

    ByteBuffer getByteBuffer(long j2, long j3) throws IOException;

    void setBoxes(List<Box> list);

    void writeContainer(WritableByteChannel writableByteChannel) throws IOException;
}
