package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.CastUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public final class AudioSampleEntry extends AbstractSampleEntry {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TYPE1 = "samr";
    public static final String TYPE10 = "mlpa";
    public static final String TYPE11 = "dtsl";
    public static final String TYPE12 = "dtsh";
    public static final String TYPE13 = "dtse";
    public static final String TYPE2 = "sawb";
    public static final String TYPE3 = "mp4a";
    public static final String TYPE4 = "drms";
    public static final String TYPE5 = "alac";
    public static final String TYPE7 = "owma";
    public static final String TYPE8 = "ac-3";
    public static final String TYPE9 = "ec-3";
    public static final String TYPE_ENCRYPTED = "enca";
    private long bytesPerFrame;
    private long bytesPerPacket;
    private long bytesPerSample;
    private int channelCount;
    private int compressionId;
    private int packetSize;
    private int reserved1;
    private long reserved2;
    private long sampleRate;
    private int sampleSize;
    private long samplesPerPacket;
    private int soundVersion;
    private byte[] soundVersion2Data;

    public AudioSampleEntry(String str) {
        super(str);
    }

    @Override // com.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(getHeader());
        int i2 = this.soundVersion;
        ByteBuffer allocate = ByteBuffer.allocate((i2 == 1 ? 16 : 0) + 28 + (i2 == 2 ? 36 : 0));
        allocate.position(6);
        IsoTypeWriter.writeUInt16(allocate, this.dataReferenceIndex);
        IsoTypeWriter.writeUInt16(allocate, this.soundVersion);
        IsoTypeWriter.writeUInt16(allocate, this.reserved1);
        IsoTypeWriter.writeUInt32(allocate, this.reserved2);
        IsoTypeWriter.writeUInt16(allocate, this.channelCount);
        IsoTypeWriter.writeUInt16(allocate, this.sampleSize);
        IsoTypeWriter.writeUInt16(allocate, this.compressionId);
        IsoTypeWriter.writeUInt16(allocate, this.packetSize);
        if (this.type.equals(TYPE10)) {
            IsoTypeWriter.writeUInt32(allocate, getSampleRate());
        } else {
            IsoTypeWriter.writeUInt32(allocate, getSampleRate() << 16);
        }
        if (this.soundVersion == 1) {
            IsoTypeWriter.writeUInt32(allocate, this.samplesPerPacket);
            IsoTypeWriter.writeUInt32(allocate, this.bytesPerPacket);
            IsoTypeWriter.writeUInt32(allocate, this.bytesPerFrame);
            IsoTypeWriter.writeUInt32(allocate, this.bytesPerSample);
        }
        if (this.soundVersion == 2) {
            IsoTypeWriter.writeUInt32(allocate, this.samplesPerPacket);
            IsoTypeWriter.writeUInt32(allocate, this.bytesPerPacket);
            IsoTypeWriter.writeUInt32(allocate, this.bytesPerFrame);
            IsoTypeWriter.writeUInt32(allocate, this.bytesPerSample);
            allocate.put(this.soundVersion2Data);
        }
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        writeContainer(writableByteChannel);
    }

    public long getBytesPerFrame() {
        return this.bytesPerFrame;
    }

    public long getBytesPerPacket() {
        return this.bytesPerPacket;
    }

    public long getBytesPerSample() {
        return this.bytesPerSample;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getCompressionId() {
        return this.compressionId;
    }

    public int getPacketSize() {
        return this.packetSize;
    }

    public int getReserved1() {
        return this.reserved1;
    }

    public long getReserved2() {
        return this.reserved2;
    }

    public long getSampleRate() {
        return this.sampleRate;
    }

    public int getSampleSize() {
        return this.sampleSize;
    }

    public long getSamplesPerPacket() {
        return this.samplesPerPacket;
    }

    @Override // com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public long getSize() {
        int i2 = this.soundVersion;
        int i3 = 16;
        long containerSize = (i2 == 1 ? 16 : 0) + 28 + (i2 == 2 ? 36 : 0) + getContainerSize();
        if (!this.largeBox && 8 + containerSize < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
            i3 = 8;
        }
        return containerSize + i3;
    }

    public int getSoundVersion() {
        return this.soundVersion;
    }

    public byte[] getSoundVersion2Data() {
        return this.soundVersion2Data;
    }

    @Override // com.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, com.googlecode.mp4parser.AbstractContainerBox, com.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j2, BoxParser boxParser) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(28);
        dataSource.read(allocate);
        allocate.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(allocate);
        this.soundVersion = IsoTypeReader.readUInt16(allocate);
        this.reserved1 = IsoTypeReader.readUInt16(allocate);
        this.reserved2 = IsoTypeReader.readUInt32(allocate);
        this.channelCount = IsoTypeReader.readUInt16(allocate);
        this.sampleSize = IsoTypeReader.readUInt16(allocate);
        this.compressionId = IsoTypeReader.readUInt16(allocate);
        this.packetSize = IsoTypeReader.readUInt16(allocate);
        this.sampleRate = IsoTypeReader.readUInt32(allocate);
        if (!this.type.equals(TYPE10)) {
            this.sampleRate >>>= 16;
        }
        if (this.soundVersion == 1) {
            ByteBuffer allocate2 = ByteBuffer.allocate(16);
            dataSource.read(allocate2);
            allocate2.rewind();
            this.samplesPerPacket = IsoTypeReader.readUInt32(allocate2);
            this.bytesPerPacket = IsoTypeReader.readUInt32(allocate2);
            this.bytesPerFrame = IsoTypeReader.readUInt32(allocate2);
            this.bytesPerSample = IsoTypeReader.readUInt32(allocate2);
        }
        if (this.soundVersion == 2) {
            ByteBuffer allocate3 = ByteBuffer.allocate(36);
            dataSource.read(allocate3);
            allocate3.rewind();
            this.samplesPerPacket = IsoTypeReader.readUInt32(allocate3);
            this.bytesPerPacket = IsoTypeReader.readUInt32(allocate3);
            this.bytesPerFrame = IsoTypeReader.readUInt32(allocate3);
            this.bytesPerSample = IsoTypeReader.readUInt32(allocate3);
            byte[] bArr = new byte[20];
            this.soundVersion2Data = bArr;
            allocate3.get(bArr);
        }
        if (TYPE7.equals(this.type)) {
            System.err.println(TYPE7);
            long j3 = j2 - 28;
            int i2 = this.soundVersion;
            final long j4 = (j3 - (i2 != 1 ? 0 : 16)) - (i2 != 2 ? 0 : 36);
            final ByteBuffer allocate4 = ByteBuffer.allocate(CastUtils.l2i(j4));
            dataSource.read(allocate4);
            addBox(new Box() { // from class: com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.1
                @Override // com.coremedia.iso.boxes.Box
                public void getBox(WritableByteChannel writableByteChannel) throws IOException {
                    allocate4.rewind();
                    writableByteChannel.write(allocate4);
                }

                @Override // com.coremedia.iso.boxes.Box
                public long getOffset() {
                    return 0L;
                }

                @Override // com.coremedia.iso.boxes.Box
                public Container getParent() {
                    return AudioSampleEntry.this;
                }

                @Override // com.coremedia.iso.boxes.Box
                public long getSize() {
                    return j4;
                }

                @Override // com.coremedia.iso.boxes.Box
                public String getType() {
                    return "----";
                }

                @Override // com.coremedia.iso.boxes.Box
                public void parse(DataSource dataSource2, ByteBuffer byteBuffer2, long j5, BoxParser boxParser2) throws IOException {
                    throw new RuntimeException("NotImplemented");
                }

                @Override // com.coremedia.iso.boxes.Box
                public void setParent(Container container) {
                    if (!AudioSampleEntry.$assertionsDisabled && container != AudioSampleEntry.this) {
                        throw new AssertionError("you cannot diswown this special box");
                    }
                }
            });
            return;
        }
        long j5 = j2 - 28;
        int i3 = this.soundVersion;
        initContainer(dataSource, (j5 - (i3 != 1 ? 0 : 16)) - (i3 != 2 ? 0 : 36), boxParser);
    }

    public void setBytesPerFrame(long j2) {
        this.bytesPerFrame = j2;
    }

    public void setBytesPerPacket(long j2) {
        this.bytesPerPacket = j2;
    }

    public void setBytesPerSample(long j2) {
        this.bytesPerSample = j2;
    }

    public void setChannelCount(int i2) {
        this.channelCount = i2;
    }

    public void setCompressionId(int i2) {
        this.compressionId = i2;
    }

    public void setPacketSize(int i2) {
        this.packetSize = i2;
    }

    public void setReserved1(int i2) {
        this.reserved1 = i2;
    }

    public void setReserved2(long j2) {
        this.reserved2 = j2;
    }

    public void setSampleRate(long j2) {
        this.sampleRate = j2;
    }

    public void setSampleSize(int i2) {
        this.sampleSize = i2;
    }

    public void setSamplesPerPacket(long j2) {
        this.samplesPerPacket = j2;
    }

    public void setSoundVersion(int i2) {
        this.soundVersion = i2;
    }

    public void setSoundVersion2Data(byte[] bArr) {
        this.soundVersion2Data = bArr;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // com.googlecode.mp4parser.BasicContainer
    public String toString() {
        return "AudioSampleEntry{bytesPerSample=" + this.bytesPerSample + ", bytesPerFrame=" + this.bytesPerFrame + ", bytesPerPacket=" + this.bytesPerPacket + ", samplesPerPacket=" + this.samplesPerPacket + ", packetSize=" + this.packetSize + ", compressionId=" + this.compressionId + ", soundVersion=" + this.soundVersion + ", sampleRate=" + this.sampleRate + ", sampleSize=" + this.sampleSize + ", channelCount=" + this.channelCount + ", boxes=" + getBoxes() + '}';
    }
}
