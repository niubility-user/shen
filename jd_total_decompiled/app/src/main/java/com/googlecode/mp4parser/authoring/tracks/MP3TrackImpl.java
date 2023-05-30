package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.SampleImpl;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes12.dex */
public class MP3TrackImpl extends AbstractTrack {
    private static final int ES_OBJECT_TYPE_INDICATION = 107;
    private static final int ES_STREAM_TYPE = 5;
    private static final int MPEG_L3 = 1;
    private static final int MPEG_V1 = 3;
    private static final int SAMPLES_PER_FRAME = 1152;
    long avgBitRate;
    private final DataSource dataSource;
    private long[] durations;
    MP3Header firstHeader;
    long maxBitRate;
    SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    TrackMetaData trackMetaData;
    private static final int[] SAMPLE_RATE = {PcmRecord.DEFAULT_SAMPLE_RATE, 48000, 32000};
    private static final int[] BIT_RATE = {0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class MP3Header {
        int bitRate;
        int bitRateIndex;
        int channelCount;
        int channelMode;
        int layer;
        int mpegVersion;
        int padding;
        int protectionAbsent;
        int sampleFrequencyIndex;
        int sampleRate;

        MP3Header() {
        }

        int getFrameLength() {
            return ((this.bitRate * 144) / this.sampleRate) + this.padding;
        }
    }

    public MP3TrackImpl(DataSource dataSource) throws IOException {
        this(dataSource, "eng");
    }

    private MP3Header readMP3Header(DataSource dataSource) throws IOException {
        MP3Header mP3Header = new MP3Header();
        ByteBuffer allocate = ByteBuffer.allocate(4);
        while (allocate.position() < 4) {
            if (dataSource.read(allocate) == -1) {
                return null;
            }
        }
        if (allocate.get(0) == 84 && allocate.get(1) == 65 && allocate.get(2) == 71) {
            return null;
        }
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer((ByteBuffer) allocate.rewind());
        if (bitReaderBuffer.readBits(11) == 2047) {
            int readBits = bitReaderBuffer.readBits(2);
            mP3Header.mpegVersion = readBits;
            if (readBits == 3) {
                int readBits2 = bitReaderBuffer.readBits(2);
                mP3Header.layer = readBits2;
                if (readBits2 == 1) {
                    mP3Header.protectionAbsent = bitReaderBuffer.readBits(1);
                    int readBits3 = bitReaderBuffer.readBits(4);
                    mP3Header.bitRateIndex = readBits3;
                    int i2 = BIT_RATE[readBits3];
                    mP3Header.bitRate = i2;
                    if (i2 != 0) {
                        int readBits4 = bitReaderBuffer.readBits(2);
                        mP3Header.sampleFrequencyIndex = readBits4;
                        int i3 = SAMPLE_RATE[readBits4];
                        mP3Header.sampleRate = i3;
                        if (i3 != 0) {
                            mP3Header.padding = bitReaderBuffer.readBits(1);
                            bitReaderBuffer.readBits(1);
                            int readBits5 = bitReaderBuffer.readBits(2);
                            mP3Header.channelMode = readBits5;
                            mP3Header.channelCount = readBits5 == 3 ? 1 : 2;
                            return mP3Header;
                        }
                        throw new IOException("Unexpected (reserved) sample rate frequency");
                    }
                    throw new IOException("Unexpected (free/bad) bit rate");
                }
                throw new IOException("Expected Layer III");
            }
            throw new IOException("Expected MPEG Version 1 (ISO/IEC 11172-3)");
        }
        throw new IOException("Expected Start Word 0x7ff");
    }

    private MP3Header readSamples(DataSource dataSource) throws IOException {
        MP3Header mP3Header = null;
        while (true) {
            long position = dataSource.position();
            MP3Header readMP3Header = readMP3Header(dataSource);
            if (readMP3Header == null) {
                return mP3Header;
            }
            if (mP3Header == null) {
                mP3Header = readMP3Header;
            }
            dataSource.position(position);
            ByteBuffer allocate = ByteBuffer.allocate(readMP3Header.getFrameLength());
            dataSource.read(allocate);
            allocate.rewind();
            this.samples.add(new SampleImpl(allocate));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.dataSource.close();
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "soun";
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.durations;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }

    public String toString() {
        return "MP3TrackImpl";
    }

    public MP3TrackImpl(DataSource dataSource, String str) throws IOException {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.dataSource = dataSource;
        this.samples = new LinkedList();
        MP3Header readSamples = readSamples(dataSource);
        this.firstHeader = readSamples;
        double d = readSamples.sampleRate;
        Double.isNaN(d);
        double d2 = d / 1152.0d;
        double size = this.samples.size();
        Double.isNaN(size);
        double d3 = size / d2;
        LinkedList linkedList = new LinkedList();
        Iterator<Sample> it = this.samples.iterator();
        long j2 = 0;
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                Double.isNaN(j2 * 8);
                this.avgBitRate = (int) (r0 / d3);
                this.sampleDescriptionBox = new SampleDescriptionBox();
                AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.TYPE3);
                audioSampleEntry.setChannelCount(this.firstHeader.channelCount);
                audioSampleEntry.setSampleRate(this.firstHeader.sampleRate);
                audioSampleEntry.setDataReferenceIndex(1);
                audioSampleEntry.setSampleSize(16);
                ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
                ESDescriptor eSDescriptor = new ESDescriptor();
                eSDescriptor.setEsId(0);
                SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
                sLConfigDescriptor.setPredefined(2);
                eSDescriptor.setSlConfigDescriptor(sLConfigDescriptor);
                DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
                decoderConfigDescriptor.setObjectTypeIndication(107);
                decoderConfigDescriptor.setStreamType(5);
                decoderConfigDescriptor.setMaxBitRate(this.maxBitRate);
                decoderConfigDescriptor.setAvgBitRate(this.avgBitRate);
                eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
                eSDescriptorBox.setData(eSDescriptor.serialize());
                audioSampleEntry.addBox(eSDescriptorBox);
                this.sampleDescriptionBox.addBox(audioSampleEntry);
                this.trackMetaData.setCreationTime(new Date());
                this.trackMetaData.setModificationTime(new Date());
                this.trackMetaData.setLanguage(str);
                this.trackMetaData.setVolume(1.0f);
                this.trackMetaData.setTimescale(this.firstHeader.sampleRate);
                long[] jArr = new long[this.samples.size()];
                this.durations = jArr;
                Arrays.fill(jArr, 1152L);
                return;
            }
            int size2 = (int) it.next().getSize();
            j2 += size2;
            linkedList.add(Integer.valueOf(size2));
            while (linkedList.size() > d2) {
                linkedList.pop();
            }
            if (linkedList.size() == ((int) d2)) {
                Iterator it2 = linkedList.iterator();
                while (it2.hasNext()) {
                    i2 += ((Integer) it2.next()).intValue();
                }
                double d4 = i2;
                Double.isNaN(d4);
                double size3 = linkedList.size();
                Double.isNaN(size3);
                if (((d4 * 8.0d) / size3) * d2 > this.maxBitRate) {
                    this.maxBitRate = (int) r7;
                }
            }
        }
    }
}
