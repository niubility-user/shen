package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.boxes.DTSSpecificBox;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes12.dex */
public class DTSTrackImpl extends AbstractTrack {
    private static final int BUFFER = 67108864;
    int bcCoreBitRate;
    int bcCoreChannelMask;
    int bcCoreMaxSampleRate;
    int bitrate;
    int channelCount;
    int channelMask;
    int codecDelayAtMaxFs;
    int coreBitRate;
    int coreChannelMask;
    int coreFramePayloadInBytes;
    int coreMaxSampleRate;
    boolean coreSubStreamPresent;
    private int dataOffset;
    private DataSource dataSource;
    DTSSpecificBox ddts;
    int extAvgBitrate;
    int extFramePayloadInBytes;
    int extPeakBitrate;
    int extSmoothBuffSize;
    boolean extensionSubStreamPresent;
    int frameSize;
    boolean isVBR;
    private String lang;
    int lbrCodingPresent;
    int lsbTrimPercent;
    int maxSampleRate;
    int numExtSubStreams;
    int numFramesTotal;
    int numSamplesOrigAudioAtMaxFs;
    SampleDescriptionBox sampleDescriptionBox;
    private long[] sampleDurations;
    int sampleSize;
    int samplerate;
    private List<Sample> samples;
    int samplesPerFrame;
    int samplesPerFrameAtMaxFs;
    TrackMetaData trackMetaData;
    String type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class LookAhead {
        ByteBuffer buffer;
        long bufferStartPos;
        private final int corePresent;
        long dataEnd;
        DataSource dataSource;
        int inBufferPos = 0;
        long start;

        LookAhead(DataSource dataSource, long j2, long j3, int i2) throws IOException {
            this.dataSource = dataSource;
            this.bufferStartPos = j2;
            this.dataEnd = j3 + j2;
            this.corePresent = i2;
            fillBuffer();
        }

        private void discardByte() {
            this.inBufferPos++;
        }

        private void discardNext4AndMarkStart() {
            long j2 = this.bufferStartPos;
            int i2 = this.inBufferPos;
            this.start = j2 + i2;
            this.inBufferPos = i2 + 4;
        }

        private void discardQWord() {
            this.inBufferPos += 4;
        }

        private void fillBuffer() throws IOException {
            System.err.println("Fill Buffer");
            DataSource dataSource = this.dataSource;
            long j2 = this.bufferStartPos;
            this.buffer = dataSource.map(j2, Math.min(this.dataEnd - j2, 67108864L));
        }

        private ByteBuffer getSample() {
            long j2 = this.start;
            long j3 = this.bufferStartPos;
            if (j2 >= j3) {
                this.buffer.position((int) (j2 - j3));
                ByteBuffer slice = this.buffer.slice();
                slice.limit((int) (this.inBufferPos - (this.start - this.bufferStartPos)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }

        private boolean nextFourEquals(byte b, byte b2, byte b3, byte b4) throws IOException {
            int limit = this.buffer.limit();
            int i2 = this.inBufferPos;
            if (limit - i2 >= 4) {
                return this.buffer.get(i2) == b && this.buffer.get(this.inBufferPos + 1) == b2 && this.buffer.get(this.inBufferPos + 2) == b3 && this.buffer.get(this.inBufferPos + 3) == b4;
            } else if (this.bufferStartPos + i2 + 4 < this.dataSource.size()) {
                return false;
            } else {
                throw new EOFException();
            }
        }

        private boolean nextFourEquals0x64582025() throws IOException {
            return nextFourEquals(ReplyCode.reply0x64, (byte) 88, (byte) 32, ReplyCode.reply0x25);
        }

        private boolean nextFourEquals0x64582025orEof() throws IOException {
            return nextFourEqualsOrEof(ReplyCode.reply0x64, (byte) 88, (byte) 32, ReplyCode.reply0x25);
        }

        private boolean nextFourEquals0x7FFE8001() throws IOException {
            return nextFourEquals(Byte.MAX_VALUE, (byte) -2, Byte.MIN_VALUE, (byte) 1);
        }

        private boolean nextFourEquals0x7FFE8001orEof() throws IOException {
            return nextFourEqualsOrEof(Byte.MAX_VALUE, (byte) -2, Byte.MIN_VALUE, (byte) 1);
        }

        private boolean nextFourEqualsOrEof(byte b, byte b2, byte b3, byte b4) throws IOException {
            int limit = this.buffer.limit();
            int i2 = this.inBufferPos;
            if (limit - i2 >= 4) {
                if ((this.bufferStartPos + i2) % 1048576 == 0) {
                    PrintStream printStream = System.err;
                    StringBuilder sb = new StringBuilder();
                    sb.append(((this.bufferStartPos + this.inBufferPos) / 1024) / 1024);
                    printStream.println(sb.toString());
                }
                return this.buffer.get(this.inBufferPos) == b && this.buffer.get(this.inBufferPos + 1) == b2 && this.buffer.get(this.inBufferPos + 2) == b3 && this.buffer.get(this.inBufferPos + 3) == b4;
            }
            long j2 = this.bufferStartPos;
            long j3 = this.dataEnd;
            if (i2 + j2 + 4 > j3) {
                return j2 + ((long) i2) == j3;
            }
            this.bufferStartPos = this.start;
            this.inBufferPos = 0;
            fillBuffer();
            return nextFourEquals0x7FFE8001();
        }

        public ByteBuffer findNextStart() throws IOException {
            while (true) {
                try {
                    if (this.corePresent == 1) {
                        if (nextFourEquals0x7FFE8001()) {
                            break;
                        }
                        discardByte();
                    } else if (nextFourEquals0x64582025()) {
                        break;
                    } else {
                        discardByte();
                    }
                } catch (EOFException unused) {
                    return null;
                }
                return null;
            }
            discardNext4AndMarkStart();
            while (true) {
                if (this.corePresent == 1) {
                    if (nextFourEquals0x7FFE8001orEof()) {
                        break;
                    }
                    discardQWord();
                } else if (nextFourEquals0x64582025orEof()) {
                    break;
                } else {
                    discardQWord();
                }
                return null;
            }
            return getSample();
        }
    }

    public DTSTrackImpl(DataSource dataSource, String str) throws IOException {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.frameSize = 0;
        this.dataOffset = 0;
        this.ddts = new DTSSpecificBox();
        this.isVBR = false;
        this.coreSubStreamPresent = false;
        this.extensionSubStreamPresent = false;
        this.numExtSubStreams = 0;
        this.coreMaxSampleRate = 0;
        this.coreBitRate = 0;
        this.coreChannelMask = 0;
        this.coreFramePayloadInBytes = 0;
        this.extAvgBitrate = 0;
        this.extPeakBitrate = 0;
        this.extSmoothBuffSize = 0;
        this.extFramePayloadInBytes = 0;
        this.maxSampleRate = 0;
        this.lbrCodingPresent = 0;
        this.numFramesTotal = 0;
        this.samplesPerFrameAtMaxFs = 0;
        this.numSamplesOrigAudioAtMaxFs = 0;
        this.channelMask = 0;
        this.codecDelayAtMaxFs = 0;
        this.bcCoreMaxSampleRate = 0;
        this.bcCoreBitRate = 0;
        this.bcCoreChannelMask = 0;
        this.lsbTrimPercent = 0;
        this.type = "none";
        this.lang = "eng";
        this.lang = str;
        this.dataSource = dataSource;
        parse();
    }

    private List<Sample> generateSamples(DataSource dataSource, int i2, long j2, int i3) throws IOException {
        LookAhead lookAhead = new LookAhead(dataSource, i2, j2, i3);
        ArrayList arrayList = new ArrayList();
        while (true) {
            final ByteBuffer findNextStart = lookAhead.findNextStart();
            if (findNextStart == null) {
                System.err.println("all samples found");
                return arrayList;
            }
            arrayList.add(new Sample() { // from class: com.googlecode.mp4parser.authoring.tracks.DTSTrackImpl.1
                @Override // com.googlecode.mp4parser.authoring.Sample
                public ByteBuffer asByteBuffer() {
                    return findNextStart;
                }

                @Override // com.googlecode.mp4parser.authoring.Sample
                public long getSize() {
                    return findNextStart.rewind().remaining();
                }

                @Override // com.googlecode.mp4parser.authoring.Sample
                public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
                    writableByteChannel.write((ByteBuffer) findNextStart.rewind());
                }
            });
        }
    }

    private int getBitRate(int i2) throws IOException {
        switch (i2) {
            case 0:
                return 32;
            case 1:
                return 56;
            case 2:
                return 64;
            case 3:
                return 96;
            case 4:
                return 112;
            case 5:
                return 128;
            case 6:
                return 192;
            case 7:
                return 224;
            case 8:
                return 256;
            case 9:
                return 320;
            case 10:
                return 384;
            case 11:
                return 448;
            case 12:
                return 512;
            case 13:
                return R2.attr.checked_is_bold;
            case 14:
                return 640;
            case 15:
                return R2.attr.dividerDrawableHorizontal;
            case 16:
                return R2.attr.fragmentStyle;
            case 17:
                return 1024;
            case 18:
                return R2.attr.jdpay_width;
            case 19:
                return R2.attr.lineSpacing;
            case 20:
                return R2.attr.matProg_rimColor;
            case 21:
                return R2.attr.minHeight;
            case 22:
                return R2.attr.minSeparation;
            case 23:
                return R2.attr.number;
            case 24:
                return R2.attr.placeholderImageScaleType;
            case 25:
                return -1;
            default:
                throw new IOException("Unknown bitrate value");
        }
    }

    private int getSampleRate(int i2) throws IOException {
        switch (i2) {
            case 1:
                return 8000;
            case 2:
                return R2.id.rn_redbox_report_label;
            case 3:
                return 32000;
            case 4:
            case 5:
            case 9:
            case 10:
            default:
                throw new IOException("Unknown Sample Rate");
            case 6:
                return R2.drawable.security_key_lower_icon_dark;
            case 7:
                return R2.style.label_04_style;
            case 8:
                return PcmRecord.DEFAULT_SAMPLE_RATE;
            case 11:
                return R2.drawable.wxa_spinner_white_48_outer_holo;
            case 12:
                return R2.styleable.MaterialCalendarItem_android_insetRight;
            case 13:
                return 48000;
        }
    }

    private void parse() throws IOException {
        if (readVariables()) {
            this.sampleDescriptionBox = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry(this.type);
            audioSampleEntry.setChannelCount(this.channelCount);
            audioSampleEntry.setSampleRate(this.samplerate);
            audioSampleEntry.setDataReferenceIndex(1);
            audioSampleEntry.setSampleSize(16);
            audioSampleEntry.addBox(this.ddts);
            this.sampleDescriptionBox.addBox(audioSampleEntry);
            this.trackMetaData.setCreationTime(new Date());
            this.trackMetaData.setModificationTime(new Date());
            this.trackMetaData.setLanguage(this.lang);
            this.trackMetaData.setTimescale(this.samplerate);
            return;
        }
        throw new IOException();
    }

    private boolean parseAuprhdr(int i2, ByteBuffer byteBuffer) {
        int i3;
        byteBuffer.get();
        short s = byteBuffer.getShort();
        this.maxSampleRate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        this.numFramesTotal = byteBuffer.getInt();
        this.samplesPerFrameAtMaxFs = byteBuffer.getShort();
        this.numSamplesOrigAudioAtMaxFs = (byteBuffer.get() << 32) | (byteBuffer.getInt() & 65535);
        this.channelMask = byteBuffer.getShort();
        this.codecDelayAtMaxFs = byteBuffer.getShort();
        if ((s & 3) == 3) {
            this.bcCoreMaxSampleRate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
            this.bcCoreBitRate = byteBuffer.getShort();
            this.bcCoreChannelMask = byteBuffer.getShort();
            i3 = 28;
        } else {
            i3 = 21;
        }
        if ((s & 4) > 0) {
            this.lsbTrimPercent = byteBuffer.get();
            i3++;
        }
        if ((s & 8) > 0) {
            this.lbrCodingPresent = 1;
        }
        while (i3 < i2) {
            byteBuffer.get();
            i3++;
        }
        return true;
    }

    private boolean parseCoressmd(int i2, ByteBuffer byteBuffer) {
        this.coreMaxSampleRate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        this.coreBitRate = byteBuffer.getShort();
        this.coreChannelMask = byteBuffer.getShort();
        this.coreFramePayloadInBytes = byteBuffer.getInt();
        for (int i3 = 11; i3 < i2; i3++) {
            byteBuffer.get();
        }
        return true;
    }

    private void parseDtshdhdr(int i2, ByteBuffer byteBuffer) {
        byteBuffer.getInt();
        byteBuffer.get();
        byteBuffer.getInt();
        byteBuffer.get();
        short s = byteBuffer.getShort();
        byteBuffer.get();
        byte b = byteBuffer.get();
        this.numExtSubStreams = b;
        if ((s & 1) == 1) {
            this.isVBR = true;
        }
        if ((s & 8) == 8) {
            this.coreSubStreamPresent = true;
        }
        if ((s & 16) == 16) {
            this.extensionSubStreamPresent = true;
            this.numExtSubStreams = b + 1;
        } else {
            this.numExtSubStreams = 0;
        }
        for (int i3 = 14; i3 < i2; i3++) {
            byteBuffer.get();
        }
    }

    private boolean parseExtssmd(int i2, ByteBuffer byteBuffer) {
        int i3;
        this.extAvgBitrate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
        if (this.isVBR) {
            this.extPeakBitrate = (byteBuffer.get() << 16) | (byteBuffer.getShort() & 65535);
            this.extSmoothBuffSize = byteBuffer.getShort();
            i3 = 8;
        } else {
            this.extFramePayloadInBytes = byteBuffer.getInt();
            i3 = 7;
        }
        while (i3 < i2) {
            byteBuffer.get();
            i3++;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0130, code lost:
        if (r2 != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0133, code lost:
        if (r1 != true) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0135, code lost:
        if (r10 != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0137, code lost:
        if (r11 != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0139, code lost:
        if (r14 != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x013b, code lost:
        r1 = 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x013f, code lost:
        if (r15 != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0142, code lost:
        if (r2 != true) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0144, code lost:
        if (r1 != true) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0146, code lost:
        if (r10 != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0148, code lost:
        if (r11 != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x014a, code lost:
        if (r14 != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x014c, code lost:
        r1 = 13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0150, code lost:
        if (r15 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0152, code lost:
        if (r2 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0154, code lost:
        if (r1 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0156, code lost:
        if (r10 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0159, code lost:
        if (r11 != true) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x015b, code lost:
        if (r14 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x015d, code lost:
        r1 = 14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0161, code lost:
        r1 = r18;
        r10 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0165, code lost:
        if (r7 != 0) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0167, code lost:
        if (r15 != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0169, code lost:
        if (r2 != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x016b, code lost:
        if (r1 != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x016e, code lost:
        if (r10 != true) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
        r3 = r0.getLong();
        r26.dataOffset = r0.position();
        r1 = -1;
        r2 = false;
        r5 = -1;
        r9 = -1;
        r10 = '\uffff';
        r11 = false;
        r12 = false;
        r13 = 0;
        r14 = false;
        r15 = false;
        r16 = false;
        r17 = 0;
        r18 = false;
        r19 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0170, code lost:
        if (r11 != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0172, code lost:
        if (r14 != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0174, code lost:
        r1 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0178, code lost:
        if (r7 != 6) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x017a, code lost:
        if (r15 != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x017c, code lost:
        if (r2 != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x017e, code lost:
        if (r1 != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0181, code lost:
        if (r10 != true) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0183, code lost:
        if (r11 != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0185, code lost:
        if (r14 != false) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0187, code lost:
        r1 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x018a, code lost:
        if (r7 != 0) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x018c, code lost:
        if (r15 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x018e, code lost:
        if (r2 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0191, code lost:
        if (r1 != true) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0193, code lost:
        if (r10 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0195, code lost:
        if (r11 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:
        if (r2 == false) goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0197, code lost:
        if (r14 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0199, code lost:
        r1 = 11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x019d, code lost:
        if (r7 != 6) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x019f, code lost:
        if (r15 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x01a1, code lost:
        if (r2 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x01a4, code lost:
        if (r1 != true) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x01a6, code lost:
        if (r10 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x01a8, code lost:
        if (r11 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:
        r0 = r26.samplesPerFrame;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x01aa, code lost:
        if (r14 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x01ac, code lost:
        r1 = 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x01af, code lost:
        if (r7 != 0) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x01b1, code lost:
        if (r15 != false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x01b3, code lost:
        if (r2 != false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x01b5, code lost:
        if (r1 != false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x01b7, code lost:
        if (r10 != false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x01ba, code lost:
        if (r11 != true) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x01bc, code lost:
        if (r14 != false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0056, code lost:
        if (r0 == 512) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x01be, code lost:
        r1 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x01c1, code lost:
        if (r7 != 2) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x01c3, code lost:
        if (r15 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x01c5, code lost:
        if (r2 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x01c7, code lost:
        if (r1 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x01c9, code lost:
        if (r10 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x01cc, code lost:
        if (r11 != true) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x01ce, code lost:
        if (r14 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x01d0, code lost:
        r1 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x01d2, code lost:
        r26.ddts.setDTSSamplingFrequency(r26.maxSampleRate);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x01dc, code lost:
        if (r26.isVBR == false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x01de, code lost:
        r26.ddts.setMaxBitRate((r26.coreBitRate + r26.extPeakBitrate) * 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x01ec, code lost:
        r26.ddts.setMaxBitRate((r26.coreBitRate + r26.extAvgBitrate) * 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x01f9, code lost:
        r26.ddts.setAvgBitRate((r26.coreBitRate + r26.extAvgBitrate) * 1000);
        r26.ddts.setPcmSampleDepth(r26.sampleSize);
        r26.ddts.setFrameDuration(r0);
        r26.ddts.setStreamConstruction(r1);
        r0 = r26.coreChannelMask;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x021b, code lost:
        if ((r0 & 8) > 0) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x021e, code lost:
        if ((r0 & 4096) <= 0) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0221, code lost:
        r26.ddts.setCoreLFEPresent(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005a, code lost:
        if (r0 == 1024) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0228, code lost:
        r26.ddts.setCoreLFEPresent(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x022e, code lost:
        r26.ddts.setCoreLayout(r9);
        r26.ddts.setCoreSize(r26.coreFramePayloadInBytes);
        r26.ddts.setStereoDownmix(0);
        r26.ddts.setRepresentationType(4);
        r26.ddts.setChannelLayout(r26.channelMask);
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x024f, code lost:
        if (r26.coreMaxSampleRate <= 0) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0253, code lost:
        if (r26.extAvgBitrate <= 0) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0255, code lost:
        r26.ddts.setMultiAssetFlag(1);
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x025d, code lost:
        r1 = 0;
        r26.ddts.setMultiAssetFlag(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0263, code lost:
        r26.ddts.setLBRDurationMod(r26.lbrCodingPresent);
        r26.ddts.setReservedBoxPresent(r1);
        r26.channelCount = r1;
        r0 = 16;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0274, code lost:
        if (r7 < r0) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0276, code lost:
        r0 = generateSamples(r26.dataSource, r26.dataOffset, r3, r5);
        r26.samples = r0;
        r0 = new long[r0.size()];
        r26.sampleDurations = r0;
        java.util.Arrays.fill(r0, r26.samplesPerFrame);
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0291, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0297, code lost:
        if (((r26.channelMask >> r7) & 1) != 1) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x029b, code lost:
        if (r7 == 0) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x029d, code lost:
        if (r7 == 12) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x02a1, code lost:
        if (r7 == 14) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x02a4, code lost:
        if (r7 == 3) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
        if (r0 == 2048) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x02a7, code lost:
        if (r7 == 4) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x02aa, code lost:
        if (r7 == 7) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x02ae, code lost:
        if (r7 == 8) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x02b0, code lost:
        r26.channelCount += 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x02b6, code lost:
        r26.channelCount++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
        if (r0 == 4096) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x02c0, code lost:
        r7 = r7 + 1;
        r0 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x02c5, code lost:
        r22 = r16;
        r7 = r17;
        r23 = r18;
        r24 = r19;
        r19 = r0.position();
        r8 = r0.getInt();
        r25 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x02dd, code lost:
        if (r8 != 2147385345) goto L374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x02df, code lost:
        if (r5 != 1) goto L226;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x02e1, code lost:
        r17 = r7;
        r16 = r22;
        r18 = r23;
        r19 = r24;
        r1 = -1;
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x02eb, code lost:
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x02ee, code lost:
        r2 = new com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer(r0);
        r5 = r2.readBits(1);
        r8 = r2.readBits(5);
        r7 = r2.readBits(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0300, code lost:
        if (r5 != 1) goto L368;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0304, code lost:
        if (r8 != 31) goto L370;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0062, code lost:
        r0 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0306, code lost:
        if (r7 == 0) goto L232;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x030a, code lost:
        r26.samplesPerFrame = (r2.readBits(7) + 1) * 32;
        r1 = r2.readBits(14);
        r26.frameSize += r1 + 1;
        r9 = r2.readBits(6);
        r26.samplerate = getSampleRate(r2.readBits(4));
        r26.bitrate = getBitRate(r2.readBits(5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x0341, code lost:
        if (r2.readBits(1) == 0) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x0343, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0345, code lost:
        r2.readBits(1);
        r2.readBits(1);
        r2.readBits(1);
        r2.readBits(1);
        r17 = r2.readBits(3);
        r13 = r2.readBits(1);
        r2.readBits(1);
        r2.readBits(2);
        r2.readBits(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x0364, code lost:
        if (r7 != 1) goto L239;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x0366, code lost:
        r2.readBits(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x036b, code lost:
        r2.readBits(1);
        r5 = r2.readBits(4);
        r2.readBits(2);
        r8 = r2.readBits(3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0064, code lost:
        r0 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x037b, code lost:
        if (r8 == 0) goto L255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x037e, code lost:
        if (r8 == 1) goto L255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x0381, code lost:
        if (r8 == 2) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x0384, code lost:
        if (r8 == 3) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0387, code lost:
        if (r8 == 5) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0066, code lost:
        r0 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x038a, code lost:
        if (r8 == 6) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x038c, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x038e, code lost:
        r26.sampleSize = 24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x0393, code lost:
        r26.sampleSize = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x0398, code lost:
        r26.sampleSize = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x039c, code lost:
        r2.readBits(1);
        r2.readBits(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x03a4, code lost:
        if (r5 == 6) goto L262;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x03a7, code lost:
        if (r5 == 7) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0068, code lost:
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x03a9, code lost:
        r2.readBits(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x03ae, code lost:
        r2.readBits(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x03b3, code lost:
        r2.readBits(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x03b7, code lost:
        r0.position((r19 + r1) + 1);
        r16 = r22;
        r18 = r23;
        r19 = r24;
        r2 = r25;
        r1 = -1;
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x03ca, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x03d3, code lost:
        if (r8 != 1683496997) goto L375;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x03d6, code lost:
        if (r5 != (-1)) goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x03d8, code lost:
        r26.samplesPerFrame = r26.samplesPerFrameAtMaxFs;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x03dd, code lost:
        r10 = new com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer(r0);
        r10.readBits(8);
        r10.readBits(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x03f0, code lost:
        if (r10.readBits(1) != 0) goto L274;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x03f2, code lost:
        r2 = 8;
        r8 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x03f7, code lost:
        r2 = 12;
        r8 = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x03fb, code lost:
        r8 = r10.readBits(r8) + 1;
        r0.position(r19 + (r10.readBits(r2) + 1));
        r2 = r0.getInt();
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x0411, code lost:
        if (r2 != 1515870810) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0413, code lost:
        if (r12 != true) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0415, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0417, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006b, code lost:
        if (r0 != r1) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x0419, code lost:
        r20 = r3;
        r10 = r22;
        r4 = r24;
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x0425, code lost:
        if (r2 != 1191201283) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x0429, code lost:
        if (r22 != true) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x042b, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x042d, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x042f, code lost:
        r20 = r3;
        r4 = r24;
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0436, code lost:
        r20 = r3;
        r10 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x043d, code lost:
        if (r2 != 496366178) goto L295;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006d, code lost:
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x0441, code lost:
        if (r23 != true) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x0443, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x0445, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0447, code lost:
        r4 = r24;
        r23 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x0451, code lost:
        if (r2 != 1700671838) goto L302;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x0455, code lost:
        if (r24 != true) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x0457, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006e, code lost:
        if (r9 == 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x0459, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x045b, code lost:
        r23 = r23;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x045f, code lost:
        r23 = r23;
        r4 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x0466, code lost:
        if (r2 != 176167201) goto L308;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x0468, code lost:
        if (r14 != true) goto L306;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x046a, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x046c, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x046e, code lost:
        r14 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x0473, code lost:
        if (r2 != 1101174087) goto L314;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0070, code lost:
        if (r9 == 2) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x0475, code lost:
        if (r11 != true) goto L312;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x0477, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x0479, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x047b, code lost:
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x0480, code lost:
        if (r2 != 45126241) goto L320;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x0482, code lost:
        if (r15 != true) goto L318;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0484, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0486, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x0488, code lost:
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0072, code lost:
        switch(r9) {
            case 4: goto L33;
            case 5: goto L33;
            case 6: goto L33;
            case 7: goto L33;
            case 8: goto L33;
            case 9: goto L33;
            default: goto L32;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x048a, code lost:
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x048c, code lost:
        if (r2 != false) goto L323;
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x048e, code lost:
        r26.frameSize += r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0493, code lost:
        r0.position(r19 + r8);
        r19 = r4;
        r17 = r7;
        r16 = r10;
        r3 = r20;
        r18 = r23;
        r1 = -1;
        r7 = false;
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x04be, code lost:
        throw new java.io.IOException("No DTS_SYNCWORD_* found at " + r0.position());
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0075, code lost:
        r9 = 31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0079, code lost:
        if (r5 != 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007e, code lost:
        if (r11 != true) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0080, code lost:
        if (r15 != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:384:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0082, code lost:
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE11;
        r20 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008b, code lost:
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008d, code lost:
        r20 = 21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0090, code lost:
        if (r14 != true) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0092, code lost:
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE13;
        r20 = 18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009b, code lost:
        if (r15 != true) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009d, code lost:
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE12;
        r1 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a1, code lost:
        if (r1 != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a3, code lost:
        if (r11 != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a5, code lost:
        r20 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00aa, code lost:
        if (r1 != true) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ac, code lost:
        if (r11 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ae, code lost:
        r20 = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b1, code lost:
        if (r1 != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b3, code lost:
        if (r11 != true) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b6, code lost:
        r20 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b8, code lost:
        r26.samplerate = r26.maxSampleRate;
        r26.sampleSize = 24;
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c4, code lost:
        r2 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c7, code lost:
        if (r10 >= 1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00cb, code lost:
        if (r13 <= 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00cd, code lost:
        r7 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00cf, code lost:
        if (r7 == 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d1, code lost:
        if (r7 == 2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d4, code lost:
        if (r7 == 6) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d6, code lost:
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d8, code lost:
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00db, code lost:
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE12;
        r1 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e0, code lost:
        r26.type = "dtsc";
        r1 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e5, code lost:
        r26.type = "dtsc";
        r1 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ea, code lost:
        r26.type = "dtsc";
        r1 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00ef, code lost:
        r7 = r17;
        r26.type = com.coremedia.iso.boxes.sampleentry.AudioSampleEntry.TYPE12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00f3, code lost:
        if (r13 != 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00f5, code lost:
        if (r15 != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f8, code lost:
        if (r2 != true) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00fa, code lost:
        r1 = r18;
        r10 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00fe, code lost:
        if (r1 != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0100, code lost:
        if (r10 != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0102, code lost:
        if (r11 != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0104, code lost:
        if (r14 != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0106, code lost:
        r1 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0109, code lost:
        r1 = r18;
        r10 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x010d, code lost:
        if (r15 != false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x010f, code lost:
        if (r2 != false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0111, code lost:
        if (r1 != false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0113, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0114, code lost:
        if (r10 != true) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0116, code lost:
        if (r11 != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0118, code lost:
        if (r14 != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x011a, code lost:
        r1 = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x011d, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x011e, code lost:
        if (r15 != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0120, code lost:
        if (r2 != r7) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0122, code lost:
        if (r1 != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0124, code lost:
        if (r10 != r7) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0126, code lost:
        if (r11 != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0128, code lost:
        if (r14 != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x012a, code lost:
        r1 = 9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x012e, code lost:
        if (r15 != false) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean readVariables() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.tracks.DTSTrackImpl.readVariables():boolean");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.dataSource.close();
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return "soun";
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.sampleDurations;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return this.samples;
    }

    @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return null;
    }

    @Override // com.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.trackMetaData;
    }

    public DTSTrackImpl(DataSource dataSource) throws IOException {
        super(dataSource.toString());
        this.trackMetaData = new TrackMetaData();
        this.frameSize = 0;
        this.dataOffset = 0;
        this.ddts = new DTSSpecificBox();
        this.isVBR = false;
        this.coreSubStreamPresent = false;
        this.extensionSubStreamPresent = false;
        this.numExtSubStreams = 0;
        this.coreMaxSampleRate = 0;
        this.coreBitRate = 0;
        this.coreChannelMask = 0;
        this.coreFramePayloadInBytes = 0;
        this.extAvgBitrate = 0;
        this.extPeakBitrate = 0;
        this.extSmoothBuffSize = 0;
        this.extFramePayloadInBytes = 0;
        this.maxSampleRate = 0;
        this.lbrCodingPresent = 0;
        this.numFramesTotal = 0;
        this.samplesPerFrameAtMaxFs = 0;
        this.numSamplesOrigAudioAtMaxFs = 0;
        this.channelMask = 0;
        this.codecDelayAtMaxFs = 0;
        this.bcCoreMaxSampleRate = 0;
        this.bcCoreBitRate = 0;
        this.bcCoreChannelMask = 0;
        this.lsbTrimPercent = 0;
        this.type = "none";
        this.lang = "eng";
        this.dataSource = dataSource;
        parse();
    }
}
