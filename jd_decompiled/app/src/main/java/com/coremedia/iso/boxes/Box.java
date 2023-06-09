package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.googlecode.mp4parser.DataSource;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes.dex */
public interface Box {
    void getBox(WritableByteChannel writableByteChannel) throws IOException;

    long getOffset();

    Container getParent();

    long getSize();

    String getType();

    void parse(DataSource dataSource, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException;

    void setParent(Container container);
}
