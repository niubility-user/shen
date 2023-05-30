package com.googlecode.mp4parser.authoring.tracks.h265;

import com.coremedia.iso.IsoTypeReader;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.SampleImpl;
import com.googlecode.mp4parser.h264.read.CAVLCReader;
import com.googlecode.mp4parser.util.ByteBufferByteChannel;
import com.mp4parser.iso14496.part15.HevcDecoderConfigurationRecord;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class H265TrackImplOld {
    public static final int AUD_NUT = 35;
    private static final int BLA_N_LP = 18;
    private static final int BLA_W_LP = 16;
    private static final int BLA_W_RADL = 17;
    private static final long BUFFER = 1048576;
    private static final int CRA_NUT = 21;
    private static final int IDR_N_LP = 20;
    private static final int IDR_W_RADL = 19;
    public static final int PPS_NUT = 34;
    public static final int PREFIX_SEI_NUT = 39;
    private static final int RADL_N = 6;
    private static final int RADL_R = 7;
    private static final int RASL_N = 8;
    private static final int RASL_R = 9;
    public static final int RSV_NVCL41 = 41;
    public static final int RSV_NVCL42 = 42;
    public static final int RSV_NVCL43 = 43;
    public static final int RSV_NVCL44 = 44;
    public static final int SPS_NUT = 33;
    private static final int STSA_N = 4;
    private static final int STSA_R = 5;
    private static final int TRAIL_N = 0;
    private static final int TRAIL_R = 1;
    private static final int TSA_N = 2;
    private static final int TSA_R = 3;
    public static final int UNSPEC48 = 48;
    public static final int UNSPEC49 = 49;
    public static final int UNSPEC50 = 50;
    public static final int UNSPEC51 = 51;
    public static final int UNSPEC52 = 52;
    public static final int UNSPEC53 = 53;
    public static final int UNSPEC54 = 54;
    public static final int UNSPEC55 = 55;
    public static final int VPS_NUT = 32;
    LinkedHashMap<Long, ByteBuffer> videoParamterSets = new LinkedHashMap<>();
    LinkedHashMap<Long, ByteBuffer> sequenceParamterSets = new LinkedHashMap<>();
    LinkedHashMap<Long, ByteBuffer> pictureParamterSets = new LinkedHashMap<>();
    List<Long> syncSamples = new ArrayList();
    List<Sample> samples = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class LookAhead {
        ByteBuffer buffer;
        DataSource dataSource;
        long start;
        long bufferStartPos = 0;
        int inBufferPos = 0;

        LookAhead(DataSource dataSource) throws IOException {
            this.dataSource = dataSource;
            fillBuffer();
        }

        void discardByte() {
            this.inBufferPos++;
        }

        void discardNext3AndMarkStart() {
            int i2 = this.inBufferPos + 3;
            this.inBufferPos = i2;
            this.start = this.bufferStartPos + i2;
        }

        public void fillBuffer() throws IOException {
            DataSource dataSource = this.dataSource;
            this.buffer = dataSource.map(this.bufferStartPos, Math.min(dataSource.size() - this.bufferStartPos, (long) H265TrackImplOld.BUFFER));
        }

        public ByteBuffer getNal() {
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

        boolean nextThreeEquals000or001orEof() throws IOException {
            int limit = this.buffer.limit();
            int i2 = this.inBufferPos;
            if (limit - i2 >= 3) {
                return this.buffer.get(i2) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && (this.buffer.get(this.inBufferPos + 2) == 0 || this.buffer.get(this.inBufferPos + 2) == 1);
            } else if (this.bufferStartPos + i2 + 3 > this.dataSource.size()) {
                return this.bufferStartPos + ((long) this.inBufferPos) == this.dataSource.size();
            } else {
                this.bufferStartPos = this.start;
                this.inBufferPos = 0;
                fillBuffer();
                return nextThreeEquals000or001orEof();
            }
        }

        boolean nextThreeEquals001() throws IOException {
            int limit = this.buffer.limit();
            int i2 = this.inBufferPos;
            if (limit - i2 >= 3) {
                return this.buffer.get(i2) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && this.buffer.get(this.inBufferPos + 2) == 1;
            } else if (this.bufferStartPos + i2 == this.dataSource.size()) {
                throw new EOFException();
            } else {
                throw new RuntimeException("buffer repositioning require");
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class NalUnitHeader {
        int forbiddenZeroFlag;
        int nalUnitType;
        int nuhLayerId;
        int nuhTemporalIdPlusOne;
    }

    /* loaded from: classes12.dex */
    public enum PARSE_STATE {
        AUD_SEI_SLICE,
        SEI_SLICE,
        SLICE_OES_EOB;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static PARSE_STATE[] valuesCustom() {
            PARSE_STATE[] valuesCustom = values();
            int length = valuesCustom.length;
            PARSE_STATE[] parse_stateArr = new PARSE_STATE[length];
            System.arraycopy(valuesCustom, 0, parse_stateArr, 0, length);
            return parse_stateArr;
        }
    }

    public H265TrackImplOld(DataSource dataSource) throws IOException {
        LookAhead lookAhead = new LookAhead(dataSource);
        ArrayList arrayList = new ArrayList();
        long j2 = 1;
        long j3 = 1;
        int i2 = 0;
        while (true) {
            ByteBuffer findNextNal = findNextNal(lookAhead);
            if (findNextNal == null) {
                System.err.println("");
                HevcDecoderConfigurationRecord hevcDecoderConfigurationRecord = new HevcDecoderConfigurationRecord();
                hevcDecoderConfigurationRecord.setArrays(getArrays());
                hevcDecoderConfigurationRecord.setAvgFrameRate(0);
                return;
            }
            NalUnitHeader nalUnitHeader = getNalUnitHeader(findNextNal);
            switch (nalUnitHeader.nalUnitType) {
                case 32:
                    this.videoParamterSets.put(Long.valueOf(j3), findNextNal);
                    break;
                case 33:
                    this.sequenceParamterSets.put(Long.valueOf(j3), findNextNal);
                    break;
                case 34:
                    this.pictureParamterSets.put(Long.valueOf(j3), findNextNal);
                    break;
            }
            int i3 = nalUnitHeader.nalUnitType;
            i2 = i3 < 32 ? i3 : i2;
            if (isFirstOfAU(i3, findNextNal, arrayList) && !arrayList.isEmpty()) {
                System.err.println("##########################");
                for (ByteBuffer byteBuffer : arrayList) {
                    NalUnitHeader nalUnitHeader2 = getNalUnitHeader(byteBuffer);
                    System.err.println(String.format("type: %3d - layer: %3d - tempId: %3d - size: %3d", Integer.valueOf(nalUnitHeader2.nalUnitType), Integer.valueOf(nalUnitHeader2.nuhLayerId), Integer.valueOf(nalUnitHeader2.nuhTemporalIdPlusOne), Integer.valueOf(byteBuffer.limit())));
                    j2 = 1;
                }
                System.err.println("                          ##########################");
                this.samples.add(createSample(arrayList));
                arrayList.clear();
                j3 += j2;
            }
            arrayList.add(findNextNal);
            if (i2 >= 16 && i2 <= 21) {
                this.syncSamples.add(Long.valueOf(j3));
            }
            j2 = 1;
        }
    }

    private ByteBuffer findNextNal(LookAhead lookAhead) throws IOException {
        while (!lookAhead.nextThreeEquals001()) {
            try {
                lookAhead.discardByte();
            } catch (EOFException unused) {
                return null;
            }
        }
        lookAhead.discardNext3AndMarkStart();
        while (!lookAhead.nextThreeEquals000or001orEof()) {
            lookAhead.discardByte();
        }
        return lookAhead.getNal();
    }

    private List<HevcDecoderConfigurationRecord.Array> getArrays() {
        HevcDecoderConfigurationRecord.Array array = new HevcDecoderConfigurationRecord.Array();
        array.array_completeness = true;
        array.nal_unit_type = 32;
        array.nalUnits = new ArrayList();
        for (ByteBuffer byteBuffer : this.videoParamterSets.values()) {
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.position(0);
            byteBuffer.get(bArr);
            array.nalUnits.add(bArr);
        }
        HevcDecoderConfigurationRecord.Array array2 = new HevcDecoderConfigurationRecord.Array();
        array2.array_completeness = true;
        array2.nal_unit_type = 33;
        array2.nalUnits = new ArrayList();
        for (ByteBuffer byteBuffer2 : this.sequenceParamterSets.values()) {
            byte[] bArr2 = new byte[byteBuffer2.limit()];
            byteBuffer2.position(0);
            byteBuffer2.get(bArr2);
            array2.nalUnits.add(bArr2);
        }
        HevcDecoderConfigurationRecord.Array array3 = new HevcDecoderConfigurationRecord.Array();
        array3.array_completeness = true;
        array3.nal_unit_type = 33;
        array3.nalUnits = new ArrayList();
        for (ByteBuffer byteBuffer3 : this.pictureParamterSets.values()) {
            byte[] bArr3 = new byte[byteBuffer3.limit()];
            byteBuffer3.position(0);
            byteBuffer3.get(bArr3);
            array3.nalUnits.add(bArr3);
        }
        return Arrays.asList(array, array2, array3);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void hrd_parameters(boolean r12, int r13, com.googlecode.mp4parser.h264.read.CAVLCReader r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.tracks.h265.H265TrackImplOld.hrd_parameters(boolean, int, com.googlecode.mp4parser.h264.read.CAVLCReader):void");
    }

    public static void main(String[] strArr) throws IOException {
        new H265TrackImplOld(new FileDataSourceImpl("c:\\content\\test-UHD-HEVC_01_FMV_Med_track1.hvc"));
    }

    protected Sample createSample(List<ByteBuffer> list) {
        byte[] bArr = new byte[list.size() * 4];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Iterator<ByteBuffer> it = list.iterator();
        while (it.hasNext()) {
            wrap.putInt(it.next().remaining());
        }
        ByteBuffer[] byteBufferArr = new ByteBuffer[list.size() * 2];
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = i2 * 2;
            byteBufferArr[i3] = ByteBuffer.wrap(bArr, i2 * 4, 4);
            byteBufferArr[i3 + 1] = list.get(i2);
        }
        return new SampleImpl(byteBufferArr);
    }

    public int getFrameRate(ByteBuffer byteBuffer) throws IOException {
        CAVLCReader cAVLCReader = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel((ByteBuffer) byteBuffer.position(0))));
        cAVLCReader.readU(4, "vps_parameter_set_id");
        cAVLCReader.readU(2, "vps_reserved_three_2bits");
        cAVLCReader.readU(6, "vps_max_layers_minus1");
        int readU = cAVLCReader.readU(3, "vps_max_sub_layers_minus1");
        cAVLCReader.readBool("vps_temporal_id_nesting_flag");
        cAVLCReader.readU(16, "vps_reserved_0xffff_16bits");
        profile_tier_level(readU, cAVLCReader);
        boolean readBool = cAVLCReader.readBool("vps_sub_layer_ordering_info_present_flag");
        int[] iArr = new int[readBool ? 0 : readU];
        int[] iArr2 = new int[readBool ? 0 : readU];
        int[] iArr3 = new int[readBool ? 0 : readU];
        for (int i2 = readBool ? 0 : readU; i2 <= readU; i2++) {
            iArr[i2] = cAVLCReader.readUE("vps_max_dec_pic_buffering_minus1[" + i2 + "]");
            iArr2[i2] = cAVLCReader.readUE("vps_max_dec_pic_buffering_minus1[" + i2 + "]");
            iArr3[i2] = cAVLCReader.readUE("vps_max_dec_pic_buffering_minus1[" + i2 + "]");
        }
        int readU2 = cAVLCReader.readU(6, "vps_max_layer_id");
        int readUE = cAVLCReader.readUE("vps_num_layer_sets_minus1");
        boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, readUE, readU2);
        for (int i3 = 1; i3 <= readUE; i3++) {
            for (int i4 = 0; i4 <= readU2; i4++) {
                zArr[i3][i4] = cAVLCReader.readBool("layer_id_included_flag[" + i3 + "][" + i4 + "]");
            }
        }
        if (cAVLCReader.readBool("vps_timing_info_present_flag")) {
            cAVLCReader.readU(32, "vps_num_units_in_tick");
            cAVLCReader.readU(32, "vps_time_scale");
            if (cAVLCReader.readBool("vps_poc_proportional_to_timing_flag")) {
                cAVLCReader.readUE("vps_num_ticks_poc_diff_one_minus1");
            }
            int readUE2 = cAVLCReader.readUE("vps_num_hrd_parameters");
            int[] iArr4 = new int[readUE2];
            boolean[] zArr2 = new boolean[readUE2];
            for (int i5 = 0; i5 < readUE2; i5++) {
                iArr4[i5] = cAVLCReader.readUE("hrd_layer_set_idx[" + i5 + "]");
                if (i5 > 0) {
                    zArr2[i5] = cAVLCReader.readBool("cprms_present_flag[" + i5 + "]");
                } else {
                    zArr2[0] = true;
                }
                hrd_parameters(zArr2[i5], readU, cAVLCReader);
            }
        }
        if (cAVLCReader.readBool("vps_extension_flag")) {
            while (cAVLCReader.moreRBSPData()) {
                cAVLCReader.readBool("vps_extension_data_flag");
            }
        }
        cAVLCReader.readTrailingBits();
        return 0;
    }

    public NalUnitHeader getNalUnitHeader(ByteBuffer byteBuffer) {
        byteBuffer.position(0);
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        NalUnitHeader nalUnitHeader = new NalUnitHeader();
        nalUnitHeader.forbiddenZeroFlag = (32768 & readUInt16) >> 15;
        nalUnitHeader.nalUnitType = (readUInt16 & 32256) >> 9;
        nalUnitHeader.nuhLayerId = (readUInt16 & 504) >> 3;
        nalUnitHeader.nuhTemporalIdPlusOne = readUInt16 & 7;
        return nalUnitHeader;
    }

    boolean isFirstOfAU(int i2, ByteBuffer byteBuffer, List<ByteBuffer> list) {
        if (list.isEmpty()) {
            return true;
        }
        boolean z = getNalUnitHeader(list.get(list.size() - 1)).nalUnitType <= 31;
        switch (i2) {
            case 32:
            case 33:
            case 34:
            case 35:
            case 39:
            case 41:
            case 42:
            case 43:
            case 44:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                if (z) {
                    return true;
                }
                break;
        }
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                break;
            default:
                switch (i2) {
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                        break;
                    default:
                        return false;
                }
        }
        byteBuffer.position(0);
        byteBuffer.get(new byte[50]);
        byteBuffer.position(2);
        return z && (IsoTypeReader.readUInt8(byteBuffer) & 128) > 0;
    }

    public void profile_tier_level(int i2, CAVLCReader cAVLCReader) throws IOException {
        int i3 = i2;
        int i4 = 2;
        cAVLCReader.readU(2, "general_profile_space ");
        cAVLCReader.readBool("general_tier_flag");
        cAVLCReader.readU(5, "general_profile_idc");
        int i5 = 32;
        boolean[] zArr = new boolean[32];
        char c2 = 0;
        int i6 = 0;
        while (i6 < i5) {
            zArr[i6] = cAVLCReader.readBool("general_profile_compatibility_flag[" + i6 + "]");
            i6++;
            i3 = i2;
            i4 = 2;
            i5 = 32;
            c2 = 0;
        }
        cAVLCReader.readBool("general_progressive_source_flag");
        cAVLCReader.readBool("general_interlaced_source_flag");
        cAVLCReader.readBool("general_non_packed_constraint_flag");
        cAVLCReader.readBool("general_frame_only_constraint_flag");
        cAVLCReader.readU(44, "general_reserved_zero_44bits");
        int i7 = 8;
        cAVLCReader.readU(8, "general_level_idc");
        boolean[] zArr2 = new boolean[i3];
        boolean[] zArr3 = new boolean[i3];
        int i8 = 0;
        while (i8 < i3) {
            zArr2[i8] = cAVLCReader.readBool("sub_layer_profile_present_flag[" + i8 + "]");
            zArr3[i8] = cAVLCReader.readBool("sub_layer_level_present_flag[" + i8 + "]");
            i8++;
            i3 = i2;
            i4 = 2;
            i5 = 32;
            c2 = 0;
            i7 = 8;
        }
        if (i3 > 0) {
            for (int i9 = i3; i9 < i7; i9++) {
                cAVLCReader.readU(i4, "reserved_zero_2bits");
            }
        }
        int[] iArr = new int[i3];
        boolean[] zArr4 = new boolean[i3];
        int[] iArr2 = new int[i3];
        int[] iArr3 = new int[i4];
        iArr3[1] = i5;
        iArr3[c2] = i3;
        boolean[][] zArr5 = (boolean[][]) Array.newInstance(boolean.class, iArr3);
        boolean[] zArr6 = new boolean[i3];
        boolean[] zArr7 = new boolean[i3];
        boolean[] zArr8 = new boolean[i3];
        boolean[] zArr9 = new boolean[i3];
        int[] iArr4 = new int[i3];
        int i10 = 0;
        while (i10 < i3) {
            if (zArr2[i10]) {
                iArr[i10] = cAVLCReader.readU(2, "sub_layer_profile_space[" + i10 + "]");
                zArr4[i10] = cAVLCReader.readBool("sub_layer_tier_flag[" + i10 + "]");
                iArr2[i10] = cAVLCReader.readU(5, "sub_layer_profile_idc[" + i10 + "]");
                int i11 = 0;
                while (i11 < 32) {
                    zArr5[i10][i11] = cAVLCReader.readBool("sub_layer_profile_compatibility_flag[" + i10 + "][" + i11 + "]");
                    i11++;
                    zArr6 = zArr6;
                }
                zArr6[i10] = cAVLCReader.readBool("sub_layer_progressive_source_flag[" + i10 + "]");
                zArr7[i10] = cAVLCReader.readBool("sub_layer_interlaced_source_flag[" + i10 + "]");
                zArr8[i10] = cAVLCReader.readBool("sub_layer_non_packed_constraint_flag[" + i10 + "]");
                zArr9[i10] = cAVLCReader.readBool("sub_layer_frame_only_constraint_flag[" + i10 + "]");
                cAVLCReader.readNBit(44, "reserved");
            }
            boolean[] zArr10 = zArr6;
            if (zArr3[i10]) {
                iArr4[i10] = cAVLCReader.readU(8, "sub_layer_level_idc");
            }
            i10++;
            i3 = i2;
            zArr6 = zArr10;
        }
    }

    void sub_layer_hrd_parameters(int i2, int i3, boolean z, CAVLCReader cAVLCReader) throws IOException {
        int[] iArr = new int[i3];
        int[] iArr2 = new int[i3];
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        boolean[] zArr = new boolean[i3];
        for (int i4 = 0; i4 <= i3; i4++) {
            iArr[i4] = cAVLCReader.readUE("bit_rate_value_minus1[" + i4 + "]");
            iArr2[i4] = cAVLCReader.readUE("cpb_size_value_minus1[" + i4 + "]");
            if (z) {
                iArr3[i4] = cAVLCReader.readUE("cpb_size_du_value_minus1[" + i4 + "]");
                iArr4[i4] = cAVLCReader.readUE("bit_rate_du_value_minus1[" + i4 + "]");
            }
            zArr[i4] = cAVLCReader.readBool("cbr_flag[" + i4 + "]");
        }
    }
}
