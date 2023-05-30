package com.googlecode.mp4parser.authoring.tracks.h265;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/* loaded from: classes12.dex */
public class SequenceParameterSetRbsp {
    public SequenceParameterSetRbsp(InputStream inputStream) throws IOException {
        CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
        cAVLCReader.readNBit(4, "sps_video_parameter_set_id");
        int readNBit = (int) cAVLCReader.readNBit(3, "sps_max_sub_layers_minus1");
        cAVLCReader.readBool("sps_temporal_id_nesting_flag");
        profile_tier_level(readNBit, cAVLCReader);
        cAVLCReader.readUE("sps_seq_parameter_set_id");
        if (cAVLCReader.readUE("chroma_format_idc") == 3) {
            cAVLCReader.read1Bit();
            cAVLCReader.readUE("pic_width_in_luma_samples");
            cAVLCReader.readUE("pic_width_in_luma_samples");
            if (cAVLCReader.readBool("conformance_window_flag")) {
                cAVLCReader.readUE("conf_win_left_offset");
                cAVLCReader.readUE("conf_win_right_offset");
                cAVLCReader.readUE("conf_win_top_offset");
                cAVLCReader.readUE("conf_win_bottom_offset");
            }
        }
        cAVLCReader.readUE("bit_depth_luma_minus8");
        cAVLCReader.readUE("bit_depth_chroma_minus8");
        cAVLCReader.readUE("log2_max_pic_order_cnt_lsb_minus4");
        boolean readBool = cAVLCReader.readBool("sps_sub_layer_ordering_info_present_flag");
        int i2 = (readNBit - (readBool ? 0 : readNBit)) + 1;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        for (int i3 = readBool ? 0 : readNBit; i3 <= readNBit; i3++) {
            iArr[i3] = cAVLCReader.readUE("sps_max_dec_pic_buffering_minus1[" + i3 + "]");
            iArr2[i3] = cAVLCReader.readUE("sps_max_num_reorder_pics[" + i3 + "]");
            iArr3[i3] = cAVLCReader.readUE("sps_max_latency_increase_plus1[" + i3 + "]");
        }
        cAVLCReader.readUE("log2_min_luma_coding_block_size_minus3");
        cAVLCReader.readUE("log2_diff_max_min_luma_coding_block_size");
        cAVLCReader.readUE("log2_min_transform_block_size_minus2");
        cAVLCReader.readUE("log2_diff_max_min_transform_block_size");
        cAVLCReader.readUE("max_transform_hierarchy_depth_inter");
        cAVLCReader.readUE("max_transform_hierarchy_depth_intra");
        if (cAVLCReader.readBool("scaling_list_enabled_flag") && cAVLCReader.readBool("sps_scaling_list_data_present_flag")) {
            scaling_list_data(cAVLCReader);
        }
        cAVLCReader.readBool("amp_enabled_flag");
        cAVLCReader.readBool("sample_adaptive_offset_enabled_flag");
        if (cAVLCReader.readBool("pcm_enabled_flag")) {
            cAVLCReader.readNBit(4, "pcm_sample_bit_depth_luma_minus1");
            cAVLCReader.readNBit(4, "pcm_sample_bit_depth_chroma_minus1");
            cAVLCReader.readUE("log2_min_pcm_luma_coding_block_size_minus3");
        }
    }

    private void profile_tier_level(int i2, CAVLCReader cAVLCReader) throws IOException {
        long[] jArr;
        boolean[] zArr;
        int i3 = i2;
        int i4 = 2;
        cAVLCReader.readU(2, "general_profile_space");
        cAVLCReader.readBool("general_tier_flag");
        cAVLCReader.readU(5, "general_profile_idc");
        int i5 = 32;
        boolean[] zArr2 = new boolean[32];
        char c2 = 0;
        int i6 = 0;
        while (i6 < i5) {
            zArr2[i6] = cAVLCReader.readBool();
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
        cAVLCReader.readNBit(44, "general_reserved_zero_44bits");
        cAVLCReader.readByte();
        boolean[] zArr3 = new boolean[i3];
        boolean[] zArr4 = new boolean[i3];
        int i7 = 0;
        while (i7 < i3) {
            zArr3[i7] = cAVLCReader.readBool("sub_layer_profile_present_flag[" + i7 + "]");
            zArr4[i7] = cAVLCReader.readBool("sub_layer_level_present_flag[" + i7 + "]");
            i7++;
            i3 = i2;
            i4 = 2;
            i5 = 32;
            c2 = 0;
        }
        if (i3 > 0) {
            int[] iArr = new int[8];
            for (int i8 = i3; i8 < 8; i8++) {
                iArr[i8] = cAVLCReader.readU(i4, "reserved_zero_2bits[" + i8 + "]");
            }
        }
        int[] iArr2 = new int[i3];
        boolean[] zArr5 = new boolean[i3];
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i4];
        iArr4[1] = i5;
        iArr4[c2] = i3;
        boolean[][] zArr6 = (boolean[][]) Array.newInstance(boolean.class, iArr4);
        boolean[] zArr7 = new boolean[i3];
        boolean[] zArr8 = new boolean[i3];
        boolean[] zArr9 = new boolean[i3];
        boolean[] zArr10 = new boolean[i3];
        long[] jArr2 = new long[i3];
        int[] iArr5 = new int[i3];
        int i9 = 0;
        while (i9 < i3) {
            if (zArr3[i9]) {
                zArr = zArr3;
                iArr2[i9] = cAVLCReader.readU(2, "sub_layer_profile_space[" + i9 + "]");
                zArr5[i9] = cAVLCReader.readBool("sub_layer_tier_flag[" + i9 + "]");
                iArr3[i9] = cAVLCReader.readU(5, "sub_layer_profile_idc[" + i9 + "]");
                int i10 = 0;
                while (i10 < 32) {
                    zArr6[i9][i10] = cAVLCReader.readBool("sub_layer_profile_compatibility_flag[" + i9 + "][" + i10 + "]");
                    i10++;
                    jArr2 = jArr2;
                }
                zArr7[i9] = cAVLCReader.readBool("sub_layer_progressive_source_flag[" + i9 + "]");
                zArr8[i9] = cAVLCReader.readBool("sub_layer_interlaced_source_flag[" + i9 + "]");
                zArr9[i9] = cAVLCReader.readBool("sub_layer_non_packed_constraint_flag[" + i9 + "]");
                zArr10[i9] = cAVLCReader.readBool("sub_layer_frame_only_constraint_flag[" + i9 + "]");
                jArr2[i9] = cAVLCReader.readNBit(44);
                jArr = jArr2;
            } else {
                jArr = jArr2;
                zArr = zArr3;
            }
            if (zArr4[i9]) {
                iArr5[i9] = cAVLCReader.readU(8, "sub_layer_level_idc[" + i9 + "]");
            }
            i9++;
            i3 = i2;
            zArr3 = zArr;
            jArr2 = jArr;
        }
    }

    private void scaling_list_data(CAVLCReader cAVLCReader) throws IOException {
        int i2 = 4;
        boolean[][] zArr = new boolean[4];
        int[][] iArr = new int[4];
        int[][] iArr2 = new int[2];
        int[][][] iArr3 = new int[4][];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = 0;
            while (true) {
                if (i4 >= (i3 == 3 ? 2 : 6)) {
                    break;
                }
                zArr[i3] = new boolean[i3 == 3 ? 2 : 6];
                iArr[i3] = new int[i3 == 3 ? 2 : 6];
                iArr3[i3] = new int[i3 == 3 ? 2 : 6];
                zArr[i3][i4] = cAVLCReader.readBool();
                if (!zArr[i3][i4]) {
                    iArr[i3][i4] = cAVLCReader.readUE("scaling_list_pred_matrix_id_delta[" + i3 + "][" + i4 + "]");
                } else {
                    int min = Math.min(64, 1 << ((i3 << 1) + i2));
                    int i5 = 8;
                    if (i3 > 1) {
                        int i6 = i3 - 2;
                        iArr2[i6][i4] = cAVLCReader.readSE("scaling_list_dc_coef_minus8[" + i3 + "- 2][" + i4 + "]");
                        i5 = 8 + iArr2[i6][i4];
                    }
                    iArr3[i3][i4] = new int[min];
                    for (int i7 = 0; i7 < min; i7++) {
                        i5 = ((i5 + cAVLCReader.readSE("scaling_list_delta_coef ")) + 256) % 256;
                        iArr3[i3][i4][i7] = i5;
                    }
                }
                i4++;
                i2 = 4;
            }
            i3++;
        }
    }
}
