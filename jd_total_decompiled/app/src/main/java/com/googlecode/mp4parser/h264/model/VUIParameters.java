package com.googlecode.mp4parser.h264.model;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;

/* loaded from: classes12.dex */
public class VUIParameters {
    public AspectRatio aspect_ratio;
    public boolean aspect_ratio_info_present_flag;
    public BitstreamRestriction bitstreamRestriction;
    public boolean chroma_loc_info_present_flag;
    public int chroma_sample_loc_type_bottom_field;
    public int chroma_sample_loc_type_top_field;
    public boolean colour_description_present_flag;
    public int colour_primaries;
    public boolean fixed_frame_rate_flag;
    public boolean low_delay_hrd_flag;
    public int matrix_coefficients;
    public HRDParameters nalHRDParams;
    public int num_units_in_tick;
    public boolean overscan_appropriate_flag;
    public boolean overscan_info_present_flag;
    public boolean pic_struct_present_flag;
    public int sar_height;
    public int sar_width;
    public int time_scale;
    public boolean timing_info_present_flag;
    public int transfer_characteristics;
    public HRDParameters vclHRDParams;
    public int video_format;
    public boolean video_full_range_flag;
    public boolean video_signal_type_present_flag;

    /* loaded from: classes12.dex */
    public static class BitstreamRestriction {
        public int log2_max_mv_length_horizontal;
        public int log2_max_mv_length_vertical;
        public int max_bits_per_mb_denom;
        public int max_bytes_per_pic_denom;
        public int max_dec_frame_buffering;
        public boolean motion_vectors_over_pic_boundaries_flag;
        public int num_reorder_frames;

        public String toString() {
            return "BitstreamRestriction{motion_vectors_over_pic_boundaries_flag=" + this.motion_vectors_over_pic_boundaries_flag + ", max_bytes_per_pic_denom=" + this.max_bytes_per_pic_denom + ", max_bits_per_mb_denom=" + this.max_bits_per_mb_denom + ", log2_max_mv_length_horizontal=" + this.log2_max_mv_length_horizontal + ", log2_max_mv_length_vertical=" + this.log2_max_mv_length_vertical + ", num_reorder_frames=" + this.num_reorder_frames + ", max_dec_frame_buffering=" + this.max_dec_frame_buffering + '}';
        }
    }

    public String toString() {
        return "VUIParameters{\naspect_ratio_info_present_flag=" + this.aspect_ratio_info_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", sar_width=" + this.sar_width + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", sar_height=" + this.sar_height + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", overscan_info_present_flag=" + this.overscan_info_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", overscan_appropriate_flag=" + this.overscan_appropriate_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", video_signal_type_present_flag=" + this.video_signal_type_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", video_format=" + this.video_format + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", video_full_range_flag=" + this.video_full_range_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", colour_description_present_flag=" + this.colour_description_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", colour_primaries=" + this.colour_primaries + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", transfer_characteristics=" + this.transfer_characteristics + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", matrix_coefficients=" + this.matrix_coefficients + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", chroma_loc_info_present_flag=" + this.chroma_loc_info_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", chroma_sample_loc_type_top_field=" + this.chroma_sample_loc_type_top_field + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", chroma_sample_loc_type_bottom_field=" + this.chroma_sample_loc_type_bottom_field + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", timing_info_present_flag=" + this.timing_info_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", num_units_in_tick=" + this.num_units_in_tick + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", time_scale=" + this.time_scale + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", fixed_frame_rate_flag=" + this.fixed_frame_rate_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", low_delay_hrd_flag=" + this.low_delay_hrd_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", pic_struct_present_flag=" + this.pic_struct_present_flag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", nalHRDParams=" + this.nalHRDParams + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", vclHRDParams=" + this.vclHRDParams + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", bitstreamRestriction=" + this.bitstreamRestriction + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + ", aspect_ratio=" + this.aspect_ratio + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + '}';
    }
}
