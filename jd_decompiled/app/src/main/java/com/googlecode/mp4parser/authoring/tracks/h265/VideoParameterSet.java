package com.googlecode.mp4parser.authoring.tracks.h265;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import com.googlecode.mp4parser.util.ByteBufferByteChannel;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

/* loaded from: classes12.dex */
public class VideoParameterSet {
    ByteBuffer vps;
    int vps_parameter_set_id;

    public VideoParameterSet(ByteBuffer byteBuffer) throws IOException {
        this.vps = byteBuffer;
        CAVLCReader cAVLCReader = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel((ByteBuffer) byteBuffer.position(0))));
        this.vps_parameter_set_id = cAVLCReader.readU(4, "vps_parameter_set_id");
        cAVLCReader.readU(2, "vps_reserved_three_2bits");
        cAVLCReader.readU(6, "vps_max_layers_minus1");
        int readU = cAVLCReader.readU(3, "vps_max_sub_layers_minus1");
        cAVLCReader.readBool("vps_temporal_id_nesting_flag");
        cAVLCReader.readU(16, "vps_reserved_0xffff_16bits");
        profile_tier_level(readU, cAVLCReader);
        boolean readBool = cAVLCReader.readBool("vps_sub_layer_ordering_info_present_flag");
        int[] iArr = new int[readBool ? 1 : readU + 1];
        int[] iArr2 = new int[readBool ? 1 : readU + 1];
        int[] iArr3 = new int[readBool ? 1 : readU + 1];
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
        throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.tracks.h265.VideoParameterSet.hrd_parameters(boolean, int, com.googlecode.mp4parser.h264.read.CAVLCReader):void");
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

    public ByteBuffer toByteBuffer() {
        return this.vps;
    }
}
