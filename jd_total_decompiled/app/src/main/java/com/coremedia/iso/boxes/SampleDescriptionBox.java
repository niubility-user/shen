package com.coremedia.iso.boxes;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.sampleentry.AbstractSampleEntry;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.googlecode.mp4parser.DataSource;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public class SampleDescriptionBox extends AbstractContainerBox implements FullBox {
    public static final String TYPE = "stsd";
    private int flags;
    private int version;

    public SampleDescriptionBox() {
        super(TYPE);
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(getHeader());
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.writeUInt8(allocate, this.version);
        IsoTypeWriter.writeUInt24(allocate, this.flags);
        IsoTypeWriter.writeUInt32(allocate, getBoxes().size());
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        writeContainer(writableByteChannel);
    }

    @Override // com.coremedia.iso.boxes.FullBox
    public int getFlags() {
        return this.flags;
    }

    public AbstractSampleEntry getSampleEntry() {
        Iterator it = getBoxes(AbstractSampleEntry.class).iterator();
        if (it.hasNext()) {
            return (AbstractSampleEntry) it.next();
        }
        return null;
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public long getSize() {
        long containerSize = getContainerSize() + 8;
        return containerSize + ((this.largeBox || 8 + containerSize >= IjkMediaMeta.AV_CH_WIDE_RIGHT) ? 16 : 8);
    }

    @Override // com.coremedia.iso.boxes.FullBox
    public int getVersion() {
        return this.version;
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        dataSource.read(allocate);
        allocate.rewind();
        this.version = IsoTypeReader.readUInt8(allocate);
        this.flags = IsoTypeReader.readUInt24(allocate);
        initContainer(dataSource, j2 - 8, boxParser);
    }

    @Override // com.coremedia.iso.boxes.FullBox
    public void setFlags(int i2) {
        this.flags = i2;
    }

    @Override // com.coremedia.iso.boxes.FullBox
    public void setVersion(int i2) {
        this.version = i2;
    }
}
