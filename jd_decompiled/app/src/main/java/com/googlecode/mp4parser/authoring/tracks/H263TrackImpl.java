package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.MultiFileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.SampleImpl;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.tracks.AbstractH26XTrack;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderSpecificInfo;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Mp4Arrays;
import com.googlecode.mp4parser.util.Path;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: classes12.dex */
public class H263TrackImpl extends AbstractH26XTrack {
    private static Logger LOG = Logger.getLogger(ESDescriptor.class.getName());
    int BINARY;
    int BINARY_ONLY;
    int GRAYSCALE;
    int RECTANGULAR;
    boolean esdsComplete;
    List<ByteBuffer> esdsStuff;
    int fixed_vop_time_increment;
    List<Sample> samples;
    SampleDescriptionBox stsd;
    int vop_time_increment_resolution;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H263TrackImpl(DataSource dataSource) throws IOException {
        super(dataSource, false);
        char c2 = 0;
        this.RECTANGULAR = 0;
        this.BINARY = 1;
        int i2 = 2;
        this.BINARY_ONLY = 2;
        this.GRAYSCALE = 3;
        this.samples = new ArrayList();
        this.esdsStuff = new ArrayList();
        this.esdsComplete = false;
        this.fixed_vop_time_increment = -1;
        this.vop_time_increment_resolution = 0;
        AbstractH26XTrack.LookAhead lookAhead = new AbstractH26XTrack.LookAhead(dataSource);
        List<? extends ByteBuffer> arrayList = new ArrayList<>();
        VisualSampleEntry visualSampleEntry = new VisualSampleEntry(VisualSampleEntry.TYPE1);
        SampleDescriptionBox sampleDescriptionBox = new SampleDescriptionBox();
        this.stsd = sampleDescriptionBox;
        sampleDescriptionBox.addBox(visualSampleEntry);
        long j2 = 0;
        int i3 = 0;
        long j3 = -1;
        while (true) {
            ByteBuffer findNextNal = findNextNal(lookAhead);
            if (findNextNal == null) {
                long[] jArr = this.decodingTimes;
                long[] jArr2 = new long[1];
                jArr2[c2] = jArr[jArr.length - 1];
                this.decodingTimes = Mp4Arrays.copyOfAndAppend(jArr, jArr2);
                ESDescriptor eSDescriptor = new ESDescriptor();
                eSDescriptor.setEsId(1);
                DecoderConfigDescriptor decoderConfigDescriptor = new DecoderConfigDescriptor();
                decoderConfigDescriptor.setObjectTypeIndication(32);
                decoderConfigDescriptor.setStreamType(4);
                DecoderSpecificInfo decoderSpecificInfo = new DecoderSpecificInfo();
                Sample createSampleObject = createSampleObject(this.esdsStuff);
                byte[] bArr = new byte[CastUtils.l2i(createSampleObject.getSize())];
                createSampleObject.asByteBuffer().get(bArr);
                decoderSpecificInfo.setData(bArr);
                decoderConfigDescriptor.setDecoderSpecificInfo(decoderSpecificInfo);
                eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
                SLConfigDescriptor sLConfigDescriptor = new SLConfigDescriptor();
                sLConfigDescriptor.setPredefined(i2);
                eSDescriptor.setSlConfigDescriptor(sLConfigDescriptor);
                ESDescriptorBox eSDescriptorBox = new ESDescriptorBox();
                eSDescriptorBox.setEsDescriptor(eSDescriptor);
                visualSampleEntry.addBox(eSDescriptorBox);
                this.trackMetaData.setTimescale(this.vop_time_increment_resolution);
                return;
            }
            ByteBuffer duplicate = findNextNal.duplicate();
            int readUInt8 = IsoTypeReader.readUInt8(findNextNal);
            if (readUInt8 == 176 || readUInt8 == 181 || readUInt8 == 0 || readUInt8 == 32 || readUInt8 == 178) {
                if (!this.esdsComplete) {
                    this.esdsStuff.add(duplicate);
                    if (readUInt8 == 32) {
                        parse0x20Unit(findNextNal, i3, visualSampleEntry);
                    } else if (readUInt8 == 181) {
                        i3 = parse0x05Unit(findNextNal);
                    }
                }
            } else if (readUInt8 == 179) {
                this.esdsComplete = true;
                int readBits = new BitReaderBuffer(findNextNal).readBits(18);
                j2 = (readBits & 63) + (((readBits >>> 7) & 63) * 60) + (((readBits >>> 13) & 31) * 60 * 60);
                this.stss.add(Integer.valueOf(this.samples.size() + 1));
                arrayList.add(duplicate);
            } else if (readUInt8 == 182) {
                BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(findNextNal);
                bitReaderBuffer.readBits(2);
                while (bitReaderBuffer.readBool()) {
                    j2++;
                }
                bitReaderBuffer.readBool();
                int i4 = 0;
                while (this.vop_time_increment_resolution >= (1 << i4)) {
                    i4++;
                }
                int readBits2 = bitReaderBuffer.readBits(i4);
                long j4 = j2;
                long j5 = (this.vop_time_increment_resolution * j2) + (readBits2 % r7);
                if (j3 != -1) {
                    this.decodingTimes = Mp4Arrays.copyOfAndAppend(this.decodingTimes, j5 - j3);
                }
                PrintStream printStream = System.err;
                StringBuilder sb = new StringBuilder("Frame increment: ");
                sb.append(j5 - j3);
                sb.append(" vop time increment: ");
                sb.append(readBits2);
                sb.append(" last_sync_point: ");
                j2 = j4;
                sb.append(j2);
                sb.append(" time_code: ");
                sb.append(j5);
                printStream.println(sb.toString());
                arrayList.add(duplicate);
                this.samples.add(createSampleObject(arrayList));
                arrayList.clear();
                j3 = j5;
            } else {
                throw new RuntimeException("Got start code I don't know. Ask Sebastian via mp4parser mailing list what to do");
            }
            c2 = 0;
            i2 = 2;
        }
    }

    public static void main(String[] strArr) throws IOException {
        FileDataSourceImpl fileDataSourceImpl = new FileDataSourceImpl("C:\\content\\bbb.h263");
        Movie movie = new Movie();
        movie.addTrack(new H263TrackImpl(fileDataSourceImpl));
        new DefaultMp4Builder().build(movie).writeContainer(Channels.newChannel(new FileOutputStream("output.mp4")));
    }

    public static void main1(String[] strArr) throws IOException {
        File[] listFiles = new File("C:\\dev\\mp4parser\\frames").listFiles();
        Arrays.sort(listFiles);
        Movie movie = new Movie();
        movie.addTrack(new H263TrackImpl(new MultiFileDataSourceImpl(listFiles)));
        new DefaultMp4Builder().build(movie).writeContainer(Channels.newChannel(new FileOutputStream("output.mp4")));
    }

    public static void main2(String[] strArr) throws IOException {
        ESDescriptorBox eSDescriptorBox = (ESDescriptorBox) Path.getPath(new IsoFile("C:\\content\\bbb.mp4"), "/moov[0]/trak[0]/mdia[0]/minf[0]/stbl[0]/stsd[0]/mp4v[0]/esds[0]");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        eSDescriptorBox.getBox(Channels.newChannel(byteArrayOutputStream));
        System.err.println(Hex.encodeHex(byteArrayOutputStream.toByteArray()));
        System.err.println(eSDescriptorBox.getEsDescriptor());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        eSDescriptorBox.getBox(Channels.newChannel(byteArrayOutputStream2));
        System.err.println(Hex.encodeHex(byteArrayOutputStream2.toByteArray()));
    }

    private int parse0x05Unit(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        if (bitReaderBuffer.readBool()) {
            int readBits = bitReaderBuffer.readBits(4);
            bitReaderBuffer.readBits(3);
            return readBits;
        }
        return 0;
    }

    private void parse0x20Unit(ByteBuffer byteBuffer, int i2, VisualSampleEntry visualSampleEntry) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        bitReaderBuffer.readBool();
        bitReaderBuffer.readBits(8);
        if (bitReaderBuffer.readBool()) {
            i2 = bitReaderBuffer.readBits(4);
            bitReaderBuffer.readBits(3);
        }
        if (bitReaderBuffer.readBits(4) == 15) {
            bitReaderBuffer.readBits(8);
            bitReaderBuffer.readBits(8);
        }
        if (bitReaderBuffer.readBool()) {
            bitReaderBuffer.readBits(2);
            bitReaderBuffer.readBool();
            if (bitReaderBuffer.readBool()) {
                throw new RuntimeException("Implemented when needed");
            }
        }
        int readBits = bitReaderBuffer.readBits(2);
        if (readBits == this.GRAYSCALE && i2 != 1) {
            bitReaderBuffer.readBits(4);
        }
        bitReaderBuffer.readBool();
        this.vop_time_increment_resolution = bitReaderBuffer.readBits(16);
        bitReaderBuffer.readBool();
        if (bitReaderBuffer.readBool()) {
            LOG.info("Fixed Frame Rate");
            int i3 = 0;
            while (this.vop_time_increment_resolution >= (1 << i3)) {
                i3++;
            }
            this.fixed_vop_time_increment = bitReaderBuffer.readBits(i3);
        }
        if (readBits != this.BINARY_ONLY) {
            if (readBits == this.RECTANGULAR) {
                bitReaderBuffer.readBool();
                visualSampleEntry.setWidth(bitReaderBuffer.readBits(13));
                bitReaderBuffer.readBool();
                visualSampleEntry.setHeight(bitReaderBuffer.readBits(13));
                bitReaderBuffer.readBool();
                return;
            }
            return;
        }
        throw new RuntimeException("Please implmenet me");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.googlecode.mp4parser.authoring.tracks.AbstractH26XTrack
    public Sample createSampleObject(List<? extends ByteBuffer> list) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[]{0, 0, 1});
        ByteBuffer[] byteBufferArr = new ByteBuffer[list.size() * 2];
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = i2 * 2;
            byteBufferArr[i3] = wrap;
            byteBufferArr[i3 + 1] = list.get(i2);
        }
        return new SampleImpl(byteBufferArr);
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "vide";
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.stsd;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }
}
