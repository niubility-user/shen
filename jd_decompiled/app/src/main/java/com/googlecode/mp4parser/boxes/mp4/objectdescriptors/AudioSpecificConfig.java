package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoTypeWriter;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.PcmRecord;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Descriptor(objectTypeIndication = 64, tags = {5})
/* loaded from: classes12.dex */
public class AudioSpecificConfig extends BaseDescriptor {
    public boolean aacScalefactorDataResilienceFlag;
    public boolean aacSectionDataResilienceFlag;
    public boolean aacSpectralDataResilienceFlag;
    public int audioObjectType;
    public int channelConfiguration;
    byte[] configBytes;
    public int coreCoderDelay;
    public int dependsOnCoreCoder;
    public int directMapping;
    public ELDSpecificConfig eldSpecificConfig;
    public int epConfig;
    public int erHvxcExtensionFlag;
    public int extensionAudioObjectType;
    public int extensionChannelConfiguration;
    public int extensionFlag;
    public int extensionFlag3;
    public int extensionSamplingFrequency;
    public int fillBits;
    public int frameLengthFlag;
    public boolean gaSpecificConfig;
    public int hilnContMode;
    public int hilnEnhaLayer;
    public int hilnEnhaQuantMode;
    public int hilnFrameLength;
    public int hilnMaxNumLine;
    public int hilnQuantMode;
    public int hilnSampleRateCode;
    public int hvxcRateMode;
    public int hvxcVarMode;
    public int isBaseLayer;
    public int layerNr;
    public int layer_length;
    public int numOfSubFrame;
    public int origExtensionAudioObjectType;
    public int originalAudioObjectType;
    public int paraExtensionFlag;
    public int paraMode;
    public boolean parametricSpecificConfig;
    public boolean psPresentFlag;
    public int sacPayloadEmbedding;
    public int samplingFrequency;
    public int samplingFrequencyIndex;
    public boolean sbrPresentFlag;
    public int var_ScalableFlag;
    public static Map<Integer, Integer> samplingFrequencyIndexMap = new HashMap();
    public static Map<Integer, String> audioObjectTypeMap = new HashMap();
    public int extensionSamplingFrequencyIndex = -1;
    public int syncExtensionType = -1;
    public int innerSyncExtensionType = -1;
    public int outerSyncExtensionType = -1;
    boolean parsed = false;

    /* loaded from: classes12.dex */
    public class ELDSpecificConfig {
        private static final int ELDEXT_TERM = 0;
        public boolean aacScalefactorDataResilienceFlag;
        public boolean aacSectionDataResilienceFlag;
        public boolean aacSpectralDataResilienceFlag;
        public boolean frameLengthFlag;
        public boolean ldSbrCrcFlag;
        public boolean ldSbrPresentFlag;
        public boolean ldSbrSamplingRate;

        public ELDSpecificConfig(int i2, BitReaderBuffer bitReaderBuffer) {
            int i3;
            this.frameLengthFlag = bitReaderBuffer.readBool();
            this.aacSectionDataResilienceFlag = bitReaderBuffer.readBool();
            this.aacScalefactorDataResilienceFlag = bitReaderBuffer.readBool();
            this.aacSpectralDataResilienceFlag = bitReaderBuffer.readBool();
            boolean readBool = bitReaderBuffer.readBool();
            this.ldSbrPresentFlag = readBool;
            if (readBool) {
                this.ldSbrSamplingRate = bitReaderBuffer.readBool();
                this.ldSbrCrcFlag = bitReaderBuffer.readBool();
                ld_sbr_header(i2, bitReaderBuffer);
            }
            while (bitReaderBuffer.readBits(4) != 0) {
                int readBits = bitReaderBuffer.readBits(4);
                if (readBits == 15) {
                    i3 = bitReaderBuffer.readBits(8);
                    readBits += i3;
                } else {
                    i3 = 0;
                }
                if (i3 == 255) {
                    readBits += bitReaderBuffer.readBits(16);
                }
                for (int i4 = 0; i4 < readBits; i4++) {
                    bitReaderBuffer.readBits(8);
                }
            }
        }

        public void ld_sbr_header(int i2, BitReaderBuffer bitReaderBuffer) {
            int i3;
            switch (i2) {
                case 1:
                case 2:
                    i3 = 1;
                    break;
                case 3:
                    i3 = 2;
                    break;
                case 4:
                case 5:
                case 6:
                    i3 = 3;
                    break;
                case 7:
                    i3 = 4;
                    break;
                default:
                    i3 = 0;
                    break;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                new sbr_header(bitReaderBuffer);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class sbr_header {
        public boolean bs_alter_scale;
        public boolean bs_amp_res;
        public int bs_freq_scale;
        public boolean bs_header_extra_1;
        public boolean bs_header_extra_2;
        public boolean bs_interpol_freq;
        public int bs_limiter_bands;
        public int bs_limiter_gains;
        public int bs_noise_bands;
        public int bs_reserved;
        public boolean bs_smoothing_mode;
        public int bs_start_freq;
        public int bs_stop_freq;
        public int bs_xover_band;

        public sbr_header(BitReaderBuffer bitReaderBuffer) {
            this.bs_amp_res = bitReaderBuffer.readBool();
            this.bs_start_freq = bitReaderBuffer.readBits(4);
            this.bs_stop_freq = bitReaderBuffer.readBits(4);
            this.bs_xover_band = bitReaderBuffer.readBits(3);
            this.bs_reserved = bitReaderBuffer.readBits(2);
            this.bs_header_extra_1 = bitReaderBuffer.readBool();
            this.bs_header_extra_2 = bitReaderBuffer.readBool();
            if (this.bs_header_extra_1) {
                this.bs_freq_scale = bitReaderBuffer.readBits(2);
                this.bs_alter_scale = bitReaderBuffer.readBool();
                this.bs_noise_bands = bitReaderBuffer.readBits(2);
            }
            if (this.bs_header_extra_2) {
                this.bs_limiter_bands = bitReaderBuffer.readBits(2);
                this.bs_limiter_gains = bitReaderBuffer.readBits(2);
                this.bs_interpol_freq = bitReaderBuffer.readBool();
            }
            this.bs_smoothing_mode = bitReaderBuffer.readBool();
        }
    }

    static {
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
        audioObjectTypeMap.put(1, "AAC main");
        audioObjectTypeMap.put(2, "AAC LC");
        audioObjectTypeMap.put(3, "AAC SSR");
        audioObjectTypeMap.put(4, "AAC LTP");
        audioObjectTypeMap.put(5, "SBR");
        audioObjectTypeMap.put(6, "AAC Scalable");
        audioObjectTypeMap.put(7, "TwinVQ");
        audioObjectTypeMap.put(8, "CELP");
        audioObjectTypeMap.put(9, "HVXC");
        audioObjectTypeMap.put(10, "(reserved)");
        audioObjectTypeMap.put(11, "(reserved)");
        audioObjectTypeMap.put(12, "TTSI");
        audioObjectTypeMap.put(13, "Main synthetic");
        audioObjectTypeMap.put(14, "Wavetable synthesis");
        audioObjectTypeMap.put(15, "General MIDI");
        audioObjectTypeMap.put(16, "Algorithmic Synthesis and Audio FX");
        audioObjectTypeMap.put(17, "ER AAC LC");
        audioObjectTypeMap.put(18, "(reserved)");
        audioObjectTypeMap.put(19, "ER AAC LTP");
        audioObjectTypeMap.put(20, "ER AAC Scalable");
        audioObjectTypeMap.put(21, "ER TwinVQ");
        audioObjectTypeMap.put(22, "ER BSAC");
        audioObjectTypeMap.put(23, "ER AAC LD");
        audioObjectTypeMap.put(24, "ER CELP");
        audioObjectTypeMap.put(25, "ER HVXC");
        audioObjectTypeMap.put(26, "ER HILN");
        audioObjectTypeMap.put(27, "ER Parametric");
        audioObjectTypeMap.put(28, "SSC");
        audioObjectTypeMap.put(29, "PS");
        audioObjectTypeMap.put(30, "MPEG Surround");
        audioObjectTypeMap.put(31, "(escape)");
        audioObjectTypeMap.put(32, "Layer-1");
        audioObjectTypeMap.put(33, "Layer-2");
        audioObjectTypeMap.put(34, "Layer-3");
        audioObjectTypeMap.put(35, "DST");
        audioObjectTypeMap.put(36, "ALS");
        audioObjectTypeMap.put(37, "SLS");
        audioObjectTypeMap.put(38, "SLS non-core");
        audioObjectTypeMap.put(39, "ER AAC ELD");
        audioObjectTypeMap.put(40, "SMR Simple");
        audioObjectTypeMap.put(41, "SMR Main");
    }

    public AudioSpecificConfig() {
        this.tag = 5;
    }

    private int gaSpecificConfigSize() {
        int i2 = (this.dependsOnCoreCoder == 1 ? 16 : 2) + 1;
        if (this.channelConfiguration != 0) {
            int i3 = this.audioObjectType;
            if (i3 == 6 || i3 == 20) {
                i2 += 3;
            }
            if (this.extensionFlag == 1) {
                if (i3 == 22) {
                    i2 = i2 + 5 + 11;
                }
                if (i3 == 17 || i3 == 19 || i3 == 20 || i3 == 23) {
                    i2 = i2 + 1 + 1 + 1;
                }
                i2++;
                if (this.extensionFlag3 == 1) {
                    throw new RuntimeException("Not implemented");
                }
            }
            return i2;
        }
        throw new UnsupportedOperationException("can't parse program_config_element yet");
    }

    private int getAudioObjectType(BitReaderBuffer bitReaderBuffer) throws IOException {
        int readBits = bitReaderBuffer.readBits(5);
        return readBits == 31 ? bitReaderBuffer.readBits(6) + 32 : readBits;
    }

    private void parseErHvxcConfig(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.hvxcVarMode = bitReaderBuffer.readBits(1);
        this.hvxcRateMode = bitReaderBuffer.readBits(2);
        int readBits = bitReaderBuffer.readBits(1);
        this.erHvxcExtensionFlag = readBits;
        if (readBits == 1) {
            this.var_ScalableFlag = bitReaderBuffer.readBits(1);
        }
    }

    private void parseGaSpecificConfig(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.frameLengthFlag = bitReaderBuffer.readBits(1);
        int readBits = bitReaderBuffer.readBits(1);
        this.dependsOnCoreCoder = readBits;
        if (readBits == 1) {
            this.coreCoderDelay = bitReaderBuffer.readBits(14);
        }
        this.extensionFlag = bitReaderBuffer.readBits(1);
        if (i3 != 0) {
            if (i4 == 6 || i4 == 20) {
                this.layerNr = bitReaderBuffer.readBits(3);
            }
            if (this.extensionFlag == 1) {
                if (i4 == 22) {
                    this.numOfSubFrame = bitReaderBuffer.readBits(5);
                    this.layer_length = bitReaderBuffer.readBits(11);
                }
                if (i4 == 17 || i4 == 19 || i4 == 20 || i4 == 23) {
                    this.aacSectionDataResilienceFlag = bitReaderBuffer.readBool();
                    this.aacScalefactorDataResilienceFlag = bitReaderBuffer.readBool();
                    this.aacSpectralDataResilienceFlag = bitReaderBuffer.readBool();
                }
                int readBits2 = bitReaderBuffer.readBits(1);
                this.extensionFlag3 = readBits2;
                if (readBits2 == 1) {
                    throw new RuntimeException("not yet implemented");
                }
            }
            this.gaSpecificConfig = true;
            return;
        }
        throw new UnsupportedOperationException("can't parse program_config_element yet");
    }

    private void parseHilnConfig(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.hilnQuantMode = bitReaderBuffer.readBits(1);
        this.hilnMaxNumLine = bitReaderBuffer.readBits(8);
        this.hilnSampleRateCode = bitReaderBuffer.readBits(4);
        this.hilnFrameLength = bitReaderBuffer.readBits(12);
        this.hilnContMode = bitReaderBuffer.readBits(2);
    }

    private void parseHilnEnexConfig(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        int readBits = bitReaderBuffer.readBits(1);
        this.hilnEnhaLayer = readBits;
        if (readBits == 1) {
            this.hilnEnhaQuantMode = bitReaderBuffer.readBits(2);
        }
    }

    private void parseParaConfig(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        int readBits = bitReaderBuffer.readBits(2);
        this.paraMode = readBits;
        if (readBits != 1) {
            parseErHvxcConfig(i2, i3, i4, bitReaderBuffer);
        }
        if (this.paraMode != 0) {
            parseHilnConfig(i2, i3, i4, bitReaderBuffer);
        }
        this.paraExtensionFlag = bitReaderBuffer.readBits(1);
        this.parametricSpecificConfig = true;
    }

    private void parseParametricSpecificConfig(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        int readBits = bitReaderBuffer.readBits(1);
        this.isBaseLayer = readBits;
        if (readBits == 1) {
            parseParaConfig(i2, i3, i4, bitReaderBuffer);
        } else {
            parseHilnEnexConfig(i2, i3, i4, bitReaderBuffer);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.nio.ByteBuffer serializeConfigBytes() {
        /*
            Method dump skipped, instructions count: 500
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig.serializeConfigBytes():java.nio.ByteBuffer");
    }

    private void writeAudioObjectType(int i2, BitWriterBuffer bitWriterBuffer) {
        if (i2 >= 32) {
            bitWriterBuffer.writeBits(31, 5);
            bitWriterBuffer.writeBits(i2 - 32, 6);
            return;
        }
        bitWriterBuffer.writeBits(i2, 5);
    }

    private void writeGaSpecificConfig(BitWriterBuffer bitWriterBuffer) {
        bitWriterBuffer.writeBits(this.frameLengthFlag, 1);
        bitWriterBuffer.writeBits(this.dependsOnCoreCoder, 1);
        if (this.dependsOnCoreCoder == 1) {
            bitWriterBuffer.writeBits(this.coreCoderDelay, 14);
        }
        bitWriterBuffer.writeBits(this.extensionFlag, 1);
        if (this.channelConfiguration != 0) {
            int i2 = this.audioObjectType;
            if (i2 == 6 || i2 == 20) {
                bitWriterBuffer.writeBits(this.layerNr, 3);
            }
            if (this.extensionFlag == 1) {
                if (this.audioObjectType == 22) {
                    bitWriterBuffer.writeBits(this.numOfSubFrame, 5);
                    bitWriterBuffer.writeBits(this.layer_length, 11);
                }
                int i3 = this.audioObjectType;
                if (i3 == 17 || i3 == 19 || i3 == 20 || i3 == 23) {
                    bitWriterBuffer.writeBool(this.aacSectionDataResilienceFlag);
                    bitWriterBuffer.writeBool(this.aacScalefactorDataResilienceFlag);
                    bitWriterBuffer.writeBool(this.aacSpectralDataResilienceFlag);
                }
                bitWriterBuffer.writeBits(this.extensionFlag3, 1);
                if (this.extensionFlag3 == 1) {
                    throw new RuntimeException("not yet implemented");
                }
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("can't parse program_config_element yet");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioSpecificConfig audioSpecificConfig = (AudioSpecificConfig) obj;
        return this.aacScalefactorDataResilienceFlag == audioSpecificConfig.aacScalefactorDataResilienceFlag && this.aacSectionDataResilienceFlag == audioSpecificConfig.aacSectionDataResilienceFlag && this.aacSpectralDataResilienceFlag == audioSpecificConfig.aacSpectralDataResilienceFlag && this.audioObjectType == audioSpecificConfig.audioObjectType && this.channelConfiguration == audioSpecificConfig.channelConfiguration && this.coreCoderDelay == audioSpecificConfig.coreCoderDelay && this.dependsOnCoreCoder == audioSpecificConfig.dependsOnCoreCoder && this.directMapping == audioSpecificConfig.directMapping && this.epConfig == audioSpecificConfig.epConfig && this.erHvxcExtensionFlag == audioSpecificConfig.erHvxcExtensionFlag && this.extensionAudioObjectType == audioSpecificConfig.extensionAudioObjectType && this.extensionChannelConfiguration == audioSpecificConfig.extensionChannelConfiguration && this.extensionFlag == audioSpecificConfig.extensionFlag && this.extensionFlag3 == audioSpecificConfig.extensionFlag3 && this.extensionSamplingFrequency == audioSpecificConfig.extensionSamplingFrequency && this.extensionSamplingFrequencyIndex == audioSpecificConfig.extensionSamplingFrequencyIndex && this.fillBits == audioSpecificConfig.fillBits && this.frameLengthFlag == audioSpecificConfig.frameLengthFlag && this.gaSpecificConfig == audioSpecificConfig.gaSpecificConfig && this.hilnContMode == audioSpecificConfig.hilnContMode && this.hilnEnhaLayer == audioSpecificConfig.hilnEnhaLayer && this.hilnEnhaQuantMode == audioSpecificConfig.hilnEnhaQuantMode && this.hilnFrameLength == audioSpecificConfig.hilnFrameLength && this.hilnMaxNumLine == audioSpecificConfig.hilnMaxNumLine && this.hilnQuantMode == audioSpecificConfig.hilnQuantMode && this.hilnSampleRateCode == audioSpecificConfig.hilnSampleRateCode && this.hvxcRateMode == audioSpecificConfig.hvxcRateMode && this.hvxcVarMode == audioSpecificConfig.hvxcVarMode && this.isBaseLayer == audioSpecificConfig.isBaseLayer && this.layerNr == audioSpecificConfig.layerNr && this.layer_length == audioSpecificConfig.layer_length && this.numOfSubFrame == audioSpecificConfig.numOfSubFrame && this.paraExtensionFlag == audioSpecificConfig.paraExtensionFlag && this.paraMode == audioSpecificConfig.paraMode && this.parametricSpecificConfig == audioSpecificConfig.parametricSpecificConfig && this.psPresentFlag == audioSpecificConfig.psPresentFlag && this.sacPayloadEmbedding == audioSpecificConfig.sacPayloadEmbedding && this.samplingFrequency == audioSpecificConfig.samplingFrequency && this.samplingFrequencyIndex == audioSpecificConfig.samplingFrequencyIndex && this.sbrPresentFlag == audioSpecificConfig.sbrPresentFlag && this.syncExtensionType == audioSpecificConfig.syncExtensionType && this.var_ScalableFlag == audioSpecificConfig.var_ScalableFlag && Arrays.equals(this.configBytes, audioSpecificConfig.configBytes);
    }

    public int getChannelConfiguration() {
        return this.channelConfiguration;
    }

    public byte[] getConfigBytes() {
        return serializeConfigBytes().array();
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        int i2 = (this.originalAudioObjectType > 30 ? 11 : 5) + 4;
        if (this.samplingFrequencyIndex == 15) {
            i2 += 24;
        }
        int i3 = i2 + 4;
        int i4 = this.audioObjectType;
        if (i4 == 5 || i4 == 29) {
            i3 += 4;
            if (this.extensionSamplingFrequencyIndex == 15) {
                i3 += 24;
            }
        }
        if (i4 == 22) {
            i3 += 4;
        }
        if (this.gaSpecificConfig) {
            i3 += gaSpecificConfigSize();
        }
        int i5 = this.outerSyncExtensionType;
        if (i5 >= 0) {
            i3 += 11;
            if (i5 == 695) {
                i3 += 5;
                int i6 = this.extensionAudioObjectType;
                if (i6 > 30) {
                    i3 += 6;
                }
                if (i6 == 5) {
                    i3++;
                    if (this.sbrPresentFlag) {
                        i3 += 4;
                        if (this.extensionSamplingFrequencyIndex == 15) {
                            i3 += 24;
                        }
                        int i7 = this.innerSyncExtensionType;
                        if (i7 >= 0) {
                            i3 += 11;
                            if (i7 == 1352) {
                                i3++;
                            }
                        }
                    }
                }
                if (i6 == 22) {
                    int i8 = i3 + 1;
                    if (this.sbrPresentFlag) {
                        i8 += 4;
                        if (this.extensionSamplingFrequencyIndex == 15) {
                            i8 += 24;
                        }
                    }
                    i3 = i8 + 4;
                }
            }
        }
        double d = i3;
        Double.isNaN(d);
        return (int) Math.ceil(d / 8.0d);
    }

    public int getExtensionAudioObjectType() {
        return this.extensionAudioObjectType;
    }

    public int getExtensionSamplingFrequency() {
        int i2 = this.extensionSamplingFrequencyIndex;
        return i2 == 15 ? this.extensionSamplingFrequency : samplingFrequencyIndexMap.get(Integer.valueOf(i2)).intValue();
    }

    public int getSamplingFrequency() {
        int i2 = this.samplingFrequencyIndex;
        return i2 == 15 ? this.samplingFrequency : samplingFrequencyIndexMap.get(Integer.valueOf(i2)).intValue();
    }

    public int hashCode() {
        byte[] bArr = this.configBytes;
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((bArr != null ? Arrays.hashCode(bArr) : 0) * 31) + this.audioObjectType) * 31) + this.samplingFrequencyIndex) * 31) + this.samplingFrequency) * 31) + this.channelConfiguration) * 31) + this.extensionAudioObjectType) * 31) + (this.sbrPresentFlag ? 1 : 0)) * 31) + (this.psPresentFlag ? 1 : 0)) * 31) + this.extensionSamplingFrequencyIndex) * 31) + this.extensionSamplingFrequency) * 31) + this.extensionChannelConfiguration) * 31) + this.sacPayloadEmbedding) * 31) + this.fillBits) * 31) + this.epConfig) * 31) + this.directMapping) * 31) + this.syncExtensionType) * 31) + this.frameLengthFlag) * 31) + this.dependsOnCoreCoder) * 31) + this.coreCoderDelay) * 31) + this.extensionFlag) * 31) + this.layerNr) * 31) + this.numOfSubFrame) * 31) + this.layer_length) * 31) + (this.aacSectionDataResilienceFlag ? 1 : 0)) * 31) + (this.aacScalefactorDataResilienceFlag ? 1 : 0)) * 31) + (this.aacSpectralDataResilienceFlag ? 1 : 0)) * 31) + this.extensionFlag3) * 31) + (this.gaSpecificConfig ? 1 : 0)) * 31) + this.isBaseLayer) * 31) + this.paraMode) * 31) + this.paraExtensionFlag) * 31) + this.hvxcVarMode) * 31) + this.hvxcRateMode) * 31) + this.erHvxcExtensionFlag) * 31) + this.var_ScalableFlag) * 31) + this.hilnQuantMode) * 31) + this.hilnMaxNumLine) * 31) + this.hilnSampleRateCode) * 31) + this.hilnFrameLength) * 31) + this.hilnContMode) * 31) + this.hilnEnhaLayer) * 31) + this.hilnEnhaQuantMode) * 31) + (this.parametricSpecificConfig ? 1 : 0);
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        this.parsed = true;
        ByteBuffer slice = byteBuffer.slice();
        slice.limit(this.sizeOfInstance);
        byteBuffer.position(byteBuffer.position() + this.sizeOfInstance);
        byte[] bArr = new byte[this.sizeOfInstance];
        this.configBytes = bArr;
        slice.get(bArr);
        slice.rewind();
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(slice);
        int audioObjectType = getAudioObjectType(bitReaderBuffer);
        this.audioObjectType = audioObjectType;
        this.originalAudioObjectType = audioObjectType;
        int readBits = bitReaderBuffer.readBits(4);
        this.samplingFrequencyIndex = readBits;
        if (readBits == 15) {
            this.samplingFrequency = bitReaderBuffer.readBits(24);
        }
        this.channelConfiguration = bitReaderBuffer.readBits(4);
        int i2 = this.audioObjectType;
        if (i2 != 5 && i2 != 29) {
            this.extensionAudioObjectType = 0;
        } else {
            this.extensionAudioObjectType = 5;
            this.sbrPresentFlag = true;
            if (i2 == 29) {
                this.psPresentFlag = true;
            }
            int readBits2 = bitReaderBuffer.readBits(4);
            this.extensionSamplingFrequencyIndex = readBits2;
            if (readBits2 == 15) {
                this.extensionSamplingFrequency = bitReaderBuffer.readBits(24);
            }
            int audioObjectType2 = getAudioObjectType(bitReaderBuffer);
            this.audioObjectType = audioObjectType2;
            if (audioObjectType2 == 22) {
                this.extensionChannelConfiguration = bitReaderBuffer.readBits(4);
            }
        }
        int i3 = this.audioObjectType;
        switch (i3) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                parseGaSpecificConfig(this.samplingFrequencyIndex, this.channelConfiguration, i3, bitReaderBuffer);
                break;
            case 8:
                throw new UnsupportedOperationException("can't parse CelpSpecificConfig yet");
            case 9:
                throw new UnsupportedOperationException("can't parse HvxcSpecificConfig yet");
            case 12:
                throw new UnsupportedOperationException("can't parse TTSSpecificConfig yet");
            case 13:
            case 14:
            case 15:
            case 16:
                throw new UnsupportedOperationException("can't parse StructuredAudioSpecificConfig yet");
            case 24:
                throw new UnsupportedOperationException("can't parse ErrorResilientCelpSpecificConfig yet");
            case 25:
                throw new UnsupportedOperationException("can't parse ErrorResilientHvxcSpecificConfig yet");
            case 26:
            case 27:
                parseParametricSpecificConfig(this.samplingFrequencyIndex, this.channelConfiguration, i3, bitReaderBuffer);
                break;
            case 28:
                throw new UnsupportedOperationException("can't parse SSCSpecificConfig yet");
            case 30:
                this.sacPayloadEmbedding = bitReaderBuffer.readBits(1);
                throw new UnsupportedOperationException("can't parse SpatialSpecificConfig yet");
            case 32:
            case 33:
            case 34:
                throw new UnsupportedOperationException("can't parse MPEG_1_2_SpecificConfig yet");
            case 35:
                throw new UnsupportedOperationException("can't parse DSTSpecificConfig yet");
            case 36:
                this.fillBits = bitReaderBuffer.readBits(5);
                throw new UnsupportedOperationException("can't parse ALSSpecificConfig yet");
            case 37:
            case 38:
                throw new UnsupportedOperationException("can't parse SLSSpecificConfig yet");
            case 39:
                this.eldSpecificConfig = new ELDSpecificConfig(this.channelConfiguration, bitReaderBuffer);
                break;
            case 40:
            case 41:
                throw new UnsupportedOperationException("can't parse SymbolicMusicSpecificConfig yet");
        }
        int i4 = this.audioObjectType;
        if (i4 != 17 && i4 != 39) {
            switch (i4) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    break;
                default:
                    if (this.extensionAudioObjectType != 5 || bitReaderBuffer.remainingBits() < 16) {
                        return;
                    }
                    int readBits3 = bitReaderBuffer.readBits(11);
                    this.syncExtensionType = readBits3;
                    this.outerSyncExtensionType = readBits3;
                    if (readBits3 == 695) {
                        int audioObjectType3 = getAudioObjectType(bitReaderBuffer);
                        this.extensionAudioObjectType = audioObjectType3;
                        if (audioObjectType3 == 5) {
                            boolean readBool = bitReaderBuffer.readBool();
                            this.sbrPresentFlag = readBool;
                            if (readBool) {
                                int readBits4 = bitReaderBuffer.readBits(4);
                                this.extensionSamplingFrequencyIndex = readBits4;
                                if (readBits4 == 15) {
                                    this.extensionSamplingFrequency = bitReaderBuffer.readBits(24);
                                }
                                if (bitReaderBuffer.remainingBits() >= 12) {
                                    int readBits5 = bitReaderBuffer.readBits(11);
                                    this.syncExtensionType = readBits5;
                                    this.innerSyncExtensionType = readBits5;
                                    if (readBits5 == 1352) {
                                        this.psPresentFlag = bitReaderBuffer.readBool();
                                    }
                                }
                            }
                        }
                        if (this.extensionAudioObjectType == 22) {
                            boolean readBool2 = bitReaderBuffer.readBool();
                            this.sbrPresentFlag = readBool2;
                            if (readBool2) {
                                int readBits6 = bitReaderBuffer.readBits(4);
                                this.extensionSamplingFrequencyIndex = readBits6;
                                if (readBits6 == 15) {
                                    this.extensionSamplingFrequency = bitReaderBuffer.readBits(24);
                                }
                            }
                            this.extensionChannelConfiguration = bitReaderBuffer.readBits(4);
                            return;
                        }
                        return;
                    }
                    return;
            }
        }
        int readBits7 = bitReaderBuffer.readBits(2);
        this.epConfig = readBits7;
        if (readBits7 != 2 && readBits7 != 3) {
            if (readBits7 == 3) {
                int readBits8 = bitReaderBuffer.readBits(1);
                this.directMapping = readBits8;
                if (readBits8 == 0) {
                    throw new RuntimeException("not implemented");
                }
            }
            if (this.extensionAudioObjectType != 5) {
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("can't parse ErrorProtectionSpecificConfig yet");
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(getSize());
        IsoTypeWriter.writeUInt8(allocate, this.tag);
        writeSize(allocate, getContentSize());
        allocate.put(serializeConfigBytes());
        return (ByteBuffer) allocate.rewind();
    }

    public void setAudioObjectType(int i2) {
        this.audioObjectType = i2;
    }

    public void setChannelConfiguration(int i2) {
        this.channelConfiguration = i2;
    }

    public void setOriginalAudioObjectType(int i2) {
        this.originalAudioObjectType = i2;
    }

    public void setSamplingFrequency(int i2) {
        this.samplingFrequency = i2;
    }

    public void setSamplingFrequencyIndex(int i2) {
        this.samplingFrequencyIndex = i2;
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AudioSpecificConfig");
        sb.append("{configBytes=");
        sb.append(Hex.encodeHex(this.configBytes));
        sb.append(", audioObjectType=");
        sb.append(this.audioObjectType);
        sb.append(" (");
        sb.append(audioObjectTypeMap.get(Integer.valueOf(this.audioObjectType)));
        sb.append(")");
        sb.append(", samplingFrequencyIndex=");
        sb.append(this.samplingFrequencyIndex);
        sb.append(" (");
        sb.append(samplingFrequencyIndexMap.get(Integer.valueOf(this.samplingFrequencyIndex)));
        sb.append(")");
        sb.append(", samplingFrequency=");
        sb.append(this.samplingFrequency);
        sb.append(", channelConfiguration=");
        sb.append(this.channelConfiguration);
        if (this.extensionAudioObjectType > 0) {
            sb.append(", extensionAudioObjectType=");
            sb.append(this.extensionAudioObjectType);
            sb.append(" (");
            sb.append(audioObjectTypeMap.get(Integer.valueOf(this.extensionAudioObjectType)));
            sb.append(")");
            sb.append(", sbrPresentFlag=");
            sb.append(this.sbrPresentFlag);
            sb.append(", psPresentFlag=");
            sb.append(this.psPresentFlag);
            sb.append(", extensionSamplingFrequencyIndex=");
            sb.append(this.extensionSamplingFrequencyIndex);
            sb.append(" (");
            sb.append(samplingFrequencyIndexMap.get(Integer.valueOf(this.extensionSamplingFrequencyIndex)));
            sb.append(")");
            sb.append(", extensionSamplingFrequency=");
            sb.append(this.extensionSamplingFrequency);
            sb.append(", extensionChannelConfiguration=");
            sb.append(this.extensionChannelConfiguration);
        }
        sb.append(", syncExtensionType=");
        sb.append(this.syncExtensionType);
        if (this.gaSpecificConfig) {
            sb.append(", frameLengthFlag=");
            sb.append(this.frameLengthFlag);
            sb.append(", dependsOnCoreCoder=");
            sb.append(this.dependsOnCoreCoder);
            sb.append(", coreCoderDelay=");
            sb.append(this.coreCoderDelay);
            sb.append(", extensionFlag=");
            sb.append(this.extensionFlag);
            sb.append(", layerNr=");
            sb.append(this.layerNr);
            sb.append(", numOfSubFrame=");
            sb.append(this.numOfSubFrame);
            sb.append(", layer_length=");
            sb.append(this.layer_length);
            sb.append(", aacSectionDataResilienceFlag=");
            sb.append(this.aacSectionDataResilienceFlag);
            sb.append(", aacScalefactorDataResilienceFlag=");
            sb.append(this.aacScalefactorDataResilienceFlag);
            sb.append(", aacSpectralDataResilienceFlag=");
            sb.append(this.aacSpectralDataResilienceFlag);
            sb.append(", extensionFlag3=");
            sb.append(this.extensionFlag3);
        }
        if (this.parametricSpecificConfig) {
            sb.append(", isBaseLayer=");
            sb.append(this.isBaseLayer);
            sb.append(", paraMode=");
            sb.append(this.paraMode);
            sb.append(", paraExtensionFlag=");
            sb.append(this.paraExtensionFlag);
            sb.append(", hvxcVarMode=");
            sb.append(this.hvxcVarMode);
            sb.append(", hvxcRateMode=");
            sb.append(this.hvxcRateMode);
            sb.append(", erHvxcExtensionFlag=");
            sb.append(this.erHvxcExtensionFlag);
            sb.append(", var_ScalableFlag=");
            sb.append(this.var_ScalableFlag);
            sb.append(", hilnQuantMode=");
            sb.append(this.hilnQuantMode);
            sb.append(", hilnMaxNumLine=");
            sb.append(this.hilnMaxNumLine);
            sb.append(", hilnSampleRateCode=");
            sb.append(this.hilnSampleRateCode);
            sb.append(", hilnFrameLength=");
            sb.append(this.hilnFrameLength);
            sb.append(", hilnContMode=");
            sb.append(this.hilnContMode);
            sb.append(", hilnEnhaLayer=");
            sb.append(this.hilnEnhaLayer);
            sb.append(", hilnEnhaQuantMode=");
            sb.append(this.hilnEnhaQuantMode);
        }
        sb.append('}');
        return sb.toString();
    }

    public int getAudioObjectType() {
        return this.audioObjectType;
    }
}
