package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.SampleDependencyTypeBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class AACTrackImpl extends AbstractTrack {
    static Map<Integer, String> audioObjectTypes;
    public static Map<Integer, Integer> samplingFrequencyIndexMap;
    long avgBitRate;
    int bufferSizeDB;
    private DataSource dataSource;
    long[] decTimes;
    AdtsHeader firstHeader;
    private String lang;
    long maxBitRate;
    SampleDescriptionBox sampleDescriptionBox;
    private List<Sample> samples;
    TrackMetaData trackMetaData;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class AdtsHeader {
        int bufferFullness;
        int channelconfig;
        int copyrightStart;
        int copyrightedStream;
        int frameLength;
        int home;
        int layer;
        int mpegVersion;
        int numAacFramesPerAdtsFrame;
        int original;
        int profile;
        int protectionAbsent;
        int sampleFrequencyIndex;
        int sampleRate;

        AdtsHeader() {
        }

        int getSize() {
            return (this.protectionAbsent == 0 ? 2 : 0) + 7;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        audioObjectTypes = hashMap;
        hashMap.put(1, "AAC Main");
        audioObjectTypes.put(2, "AAC LC (Low Complexity)");
        audioObjectTypes.put(3, "AAC SSR (Scalable Sample Rate)");
        audioObjectTypes.put(4, "AAC LTP (Long Term Prediction)");
        audioObjectTypes.put(5, "SBR (Spectral Band Replication)");
        audioObjectTypes.put(6, "AAC Scalable");
        audioObjectTypes.put(7, "TwinVQ");
        audioObjectTypes.put(8, "CELP (Code Excited Linear Prediction)");
        audioObjectTypes.put(9, "HXVC (Harmonic Vector eXcitation Coding)");
        audioObjectTypes.put(10, "Reserved");
        audioObjectTypes.put(11, "Reserved");
        audioObjectTypes.put(12, "TTSI (Text-To-Speech Interface)");
        audioObjectTypes.put(13, "Main Synthesis");
        audioObjectTypes.put(14, "Wavetable Synthesis");
        audioObjectTypes.put(15, "General MIDI");
        audioObjectTypes.put(16, "Algorithmic Synthesis and Audio Effects");
        audioObjectTypes.put(17, "ER (Error Resilient) AAC LC");
        audioObjectTypes.put(18, "Reserved");
        audioObjectTypes.put(19, "ER AAC LTP");
        audioObjectTypes.put(20, "ER AAC Scalable");
        audioObjectTypes.put(21, "ER TwinVQ");
        audioObjectTypes.put(22, "ER BSAC (Bit-Sliced Arithmetic Coding)");
        audioObjectTypes.put(23, "ER AAC LD (Low Delay)");
        audioObjectTypes.put(24, "ER CELP");
        audioObjectTypes.put(25, "ER HVXC");
        audioObjectTypes.put(26, "ER HILN (Harmonic and Individual Lines plus Noise)");
        audioObjectTypes.put(27, "ER Parametric");
        audioObjectTypes.put(28, "SSC (SinuSoidal Coding)");
        audioObjectTypes.put(29, "PS (Parametric Stereo)");
        audioObjectTypes.put(30, "MPEG Surround");
        audioObjectTypes.put(31, "(Escape value)");
        audioObjectTypes.put(32, "Layer-1");
        audioObjectTypes.put(33, "Layer-2");
        audioObjectTypes.put(34, "Layer-3");
        audioObjectTypes.put(35, "DST (Direct Stream Transfer)");
        audioObjectTypes.put(36, "ALS (Audio Lossless)");
        audioObjectTypes.put(37, "SLS (Scalable LosslesS)");
        audioObjectTypes.put(38, "SLS non-core");
        audioObjectTypes.put(39, "ER AAC ELD (Enhanced Low Delay)");
        audioObjectTypes.put(40, "SMR (Symbolic Music Representation) Simple");
        audioObjectTypes.put(41, "SMR Main");
        audioObjectTypes.put(42, "USAC (Unified Speech and Audio Coding) (no SBR)");
        audioObjectTypes.put(43, "SAOC (Spatial Audio Object Coding)");
        audioObjectTypes.put(44, "LD MPEG Surround");
        audioObjectTypes.put(45, "USAC");
        HashMap hashMap2 = new HashMap();
        samplingFrequencyIndexMap = hashMap2;
        hashMap2.put(96000, 0);
        samplingFrequencyIndexMap.put(88200, 1);
        samplingFrequencyIndexMap.put(64000, 2);
        samplingFrequencyIndexMap.put(48000, 3);
        samplingFrequencyIndexMap.put(Integer.valueOf((int) PcmRecord.DEFAULT_SAMPLE_RATE), 4);
        samplingFrequencyIndexMap.put(32000, 5);
        samplingFrequencyIndexMap.put(Integer.valueOf((int) R2.styleable.MaterialCalendarItem_android_insetRight), 6);
        samplingFrequencyIndexMap.put(Integer.valueOf((int) R2.style.label_04_style), 7);
        samplingFrequencyIndexMap.put(Integer.valueOf((int) R2.id.rn_redbox_report_label), 8);
        samplingFrequencyIndexMap.put(Integer.valueOf((int) R2.drawable.wxa_spinner_white_48_outer_holo), 9);
        samplingFrequencyIndexMap.put(Integer.valueOf((int) R2.drawable.security_key_lower_icon_dark), 10);
        samplingFrequencyIndexMap.put(8000, 11);
        samplingFrequencyIndexMap.put(0, 96000);
        samplingFrequencyIndexMap.put(1, 88200);
        samplingFrequencyIndexMap.put(2, 64000);
        samplingFrequencyIndexMap.put(3, 48000);
        samplingFrequencyIndexMap.put(4, Integer.valueOf((int) PcmRecord.DEFAULT_SAMPLE_RATE));
        samplingFrequencyIndexMap.put(5, 32000);
        samplingFrequencyIndexMap.put(6, Integer.valueOf((int) R2.styleable.MaterialCalendarItem_android_insetRight));
        samplingFrequencyIndexMap.put(7, Integer.valueOf((int) R2.style.label_04_style));
        samplingFrequencyIndexMap.put(8, Integer.valueOf((int) R2.id.rn_redbox_report_label));
        samplingFrequencyIndexMap.put(9, Integer.valueOf((int) R2.drawable.wxa_spinner_white_48_outer_holo));
        samplingFrequencyIndexMap.put(10, Integer.valueOf((int) R2.drawable.security_key_lower_icon_dark));
        samplingFrequencyIndexMap.put(11, 8000);
    }

    public AACTrackImpl(DataSource dataSource) throws IOException {
        this(dataSource, "eng");
    }

    private AdtsHeader readADTSHeader(DataSource dataSource) throws IOException {
        AdtsHeader adtsHeader = new AdtsHeader();
        ByteBuffer allocate = ByteBuffer.allocate(7);
        while (allocate.position() < 7) {
            if (dataSource.read(allocate) == -1) {
                return null;
            }
        }
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer((ByteBuffer) allocate.rewind());
        if (bitReaderBuffer.readBits(12) == 4095) {
            adtsHeader.mpegVersion = bitReaderBuffer.readBits(1);
            adtsHeader.layer = bitReaderBuffer.readBits(2);
            adtsHeader.protectionAbsent = bitReaderBuffer.readBits(1);
            adtsHeader.profile = bitReaderBuffer.readBits(2) + 1;
            int readBits = bitReaderBuffer.readBits(4);
            adtsHeader.sampleFrequencyIndex = readBits;
            adtsHeader.sampleRate = samplingFrequencyIndexMap.get(Integer.valueOf(readBits)).intValue();
            bitReaderBuffer.readBits(1);
            adtsHeader.channelconfig = bitReaderBuffer.readBits(3);
            adtsHeader.original = bitReaderBuffer.readBits(1);
            adtsHeader.home = bitReaderBuffer.readBits(1);
            adtsHeader.copyrightedStream = bitReaderBuffer.readBits(1);
            adtsHeader.copyrightStart = bitReaderBuffer.readBits(1);
            adtsHeader.frameLength = bitReaderBuffer.readBits(13);
            adtsHeader.bufferFullness = bitReaderBuffer.readBits(11);
            int readBits2 = bitReaderBuffer.readBits(2) + 1;
            adtsHeader.numAacFramesPerAdtsFrame = readBits2;
            if (readBits2 == 1) {
                if (adtsHeader.protectionAbsent == 0) {
                    dataSource.read(ByteBuffer.allocate(2));
                }
                return adtsHeader;
            }
            throw new IOException("This muxer can only work with 1 AAC frame per ADTS frame");
        }
        throw new IOException("Expected Start Word 0xfff");
    }

    private AdtsHeader readSamples(DataSource dataSource) throws IOException {
        AdtsHeader adtsHeader = null;
        while (true) {
            AdtsHeader readADTSHeader = readADTSHeader(dataSource);
            if (readADTSHeader == null) {
                return adtsHeader;
            }
            if (adtsHeader == null) {
                adtsHeader = readADTSHeader;
            }
            dataSource.position();
            final long size = readADTSHeader.frameLength - readADTSHeader.getSize();
            this.samples.add(new Sample
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0020: INVOKE 
                  (wrap: java.util.List<com.googlecode.mp4parser.authoring.Sample> : 0x0017: IGET (r10v0 'this' com.googlecode.mp4parser.authoring.tracks.AACTrackImpl A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:4) com.googlecode.mp4parser.authoring.tracks.AACTrackImpl.samples java.util.List)
                  (wrap: com.googlecode.mp4parser.authoring.Sample : 0x001d: CONSTRUCTOR 
                  (r10v0 'this' com.googlecode.mp4parser.authoring.tracks.AACTrackImpl A[IMMUTABLE_TYPE, THIS])
                  (r4 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r6v0 'size' long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.googlecode.mp4parser.authoring.tracks.AACTrackImpl, long, long):void (m), WRAPPED] call: com.googlecode.mp4parser.authoring.tracks.AACTrackImpl.1.<init>(com.googlecode.mp4parser.authoring.tracks.AACTrackImpl, long, long):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.List.add(java.lang.Object):boolean A[MD:(E):boolean (c)] (LINE:4) in method: com.googlecode.mp4parser.authoring.tracks.AACTrackImpl.readSamples(com.googlecode.mp4parser.DataSource):com.googlecode.mp4parser.authoring.tracks.AACTrackImpl$AdtsHeader, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:175)
                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                r0 = 0
            L1:
                com.googlecode.mp4parser.authoring.tracks.AACTrackImpl$AdtsHeader r1 = r10.readADTSHeader(r11)
                if (r1 != 0) goto L8
                return r0
            L8:
                if (r0 != 0) goto Lb
                r0 = r1
            Lb:
                long r4 = r11.position()
                int r2 = r1.frameLength
                int r3 = r1.getSize()
                int r2 = r2 - r3
                long r6 = (long) r2
                java.util.List<com.googlecode.mp4parser.authoring.Sample> r8 = r10.samples
                com.googlecode.mp4parser.authoring.tracks.AACTrackImpl$1 r9 = new com.googlecode.mp4parser.authoring.tracks.AACTrackImpl$1
                r2 = r9
                r3 = r10
                r2.<init>()
                r8.add(r9)
                long r2 = r11.position()
                int r4 = r1.frameLength
                long r4 = (long) r4
                long r2 = r2 + r4
                int r1 = r1.getSize()
                long r4 = (long) r1
                long r2 = r2 - r4
                r11.position(r2)
                goto L1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.tracks.AACTrackImpl.readSamples(com.googlecode.mp4parser.DataSource):com.googlecode.mp4parser.authoring.tracks.AACTrackImpl$AdtsHeader");
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
            return this.decTimes;
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public List<Sample> getSamples() {
            return this.samples;
        }

        @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
        public SubSampleInformationBox getSubsampleInformationBox() {
            return null;
        }

        @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
        public long[] getSyncSamples() {
            return null;
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public TrackMetaData getTrackMetaData() {
            return this.trackMetaData;
        }

        public String toString() {
            return "AACTrackImpl{sampleRate=" + this.firstHeader.sampleRate + ", channelconfig=" + this.firstHeader.channelconfig + '}';
        }

        public AACTrackImpl(DataSource dataSource, String str) throws IOException {
            super(dataSource.toString());
            this.trackMetaData = new TrackMetaData();
            this.lang = "eng";
            this.lang = str;
            this.dataSource = dataSource;
            this.samples = new ArrayList();
            AdtsHeader readSamples = readSamples(dataSource);
            this.firstHeader = readSamples;
            double d = readSamples.sampleRate;
            Double.isNaN(d);
            double d2 = d / 1024.0d;
            double size = this.samples.size();
            Double.isNaN(size);
            double d3 = size / d2;
            LinkedList linkedList = new LinkedList();
            Iterator<Sample> it = this.samples.iterator();
            long j2 = 0;
            while (true) {
                int i2 = 0;
                if (!it.hasNext()) {
                    break;
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
            Double.isNaN(j2 * 8);
            this.avgBitRate = (int) (r0 / d3);
            this.bufferSizeDB = R2.attr.placeholderImageScaleType;
            this.sampleDescriptionBox = new SampleDescriptionBox();
            AudioSampleEntry audioSampleEntry = new AudioSampleEntry(AudioSampleEntry.TYPE3);
            int i3 = this.firstHeader.channelconfig;
            if (i3 == 7) {
                audioSampleEntry.setChannelCount(8);
            } else {
                audioSampleEntry.setChannelCount(i3);
            }
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
            decoderConfigDescriptor.setObjectTypeIndication(64);
            decoderConfigDescriptor.setStreamType(5);
            decoderConfigDescriptor.setBufferSizeDB(this.bufferSizeDB);
            decoderConfigDescriptor.setMaxBitRate(this.maxBitRate);
            decoderConfigDescriptor.setAvgBitRate(this.avgBitRate);
            AudioSpecificConfig audioSpecificConfig = new AudioSpecificConfig();
            audioSpecificConfig.setOriginalAudioObjectType(2);
            audioSpecificConfig.setSamplingFrequencyIndex(this.firstHeader.sampleFrequencyIndex);
            audioSpecificConfig.setChannelConfiguration(this.firstHeader.channelconfig);
            decoderConfigDescriptor.setAudioSpecificInfo(audioSpecificConfig);
            eSDescriptor.setDecoderConfigDescriptor(decoderConfigDescriptor);
            eSDescriptorBox.setEsDescriptor(eSDescriptor);
            audioSampleEntry.addBox(eSDescriptorBox);
            this.sampleDescriptionBox.addBox(audioSampleEntry);
            this.trackMetaData.setCreationTime(new Date());
            this.trackMetaData.setModificationTime(new Date());
            this.trackMetaData.setLanguage(str);
            this.trackMetaData.setVolume(1.0f);
            this.trackMetaData.setTimescale(this.firstHeader.sampleRate);
            long[] jArr = new long[this.samples.size()];
            this.decTimes = jArr;
            Arrays.fill(jArr, 1024L);
        }
    }
