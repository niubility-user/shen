package com.jd.aips.verify.config;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes12.dex */
public class VerificationSdk implements Serializable {
    static final long serialVersionUID = -6725984349558358234L;
    public Config config;
    public String version;

    /* loaded from: classes12.dex */
    public class Config implements Serializable {
        static final long serialVersionUID = 7092346902553100368L;
        public String detect_text;
        public List<String> face_exposure_rule;
        public int lock_camera_preview_height;
        public int lock_camera_preview_width;
        public boolean need_lock_camera_preview_size;
        public String new_guide_link;
        public boolean openmp_flag;
        public boolean perform_get_support_preview_size;
        public String privacy_agreemen_background_colour;
        public String privacy_agreemen_name;
        public String privacy_agreemen_statement;
        public String privacy_agreemen_text;
        public String privacy_agreemen_url;
        public int sdk_face_detection_degradation_time;
        public boolean sdk_idcard_detection_confirm_editor;
        public int sdk_verification_strategy;
        public int sdk_verification_retry_count = 3;
        public int sdk_face_identify_strategy = 0;
        public int sdk_face_detection_timeout = 10;
        public boolean sdk_verification_report_enable = false;
        public int sdk_verification_report_thumbnail_interval = 3;
        public int sdk_idcard_detection_timeout = 30;
        public int sdk_idcard_detection_degradation_time = 5;
        public boolean sdk_idcard_report_enable = false;
        public int sdk_idcard_report_thumbnail_interval = 3;
        public String idcard_privacy_statement = "";
        public int sdk_idcard_strategy = 2;
        public String jump_text = "";
        public boolean jump_other_verification_flag = false;
        public String jump_colour = "";
        public boolean facedazzle_flag = false;
        public boolean resolutionImageFlag = false;
        public int hook_time = 2;
        public boolean imageCrcFlag = false;
        public boolean faqFlag = false;
        public String faqUrl = "";
        public String faqText = "";
        public int scene_config = 1;
        public int authentication_mode = 1;
        public boolean expand_face_detect_range = false;
        public int lock_preview_rule_count = 1;
        public int nosense_timeout = 3;
        public boolean delay_frame_flag = false;
        public int delay_frame_size = 5;
        public boolean reflect_flag = false;
        public boolean face_exposure_flag = false;
        public int face_exposure_time = 2000;
        public int face_exposure_time_interval = 1000;
        public String scene_name = "";
        public String button_colour = "#EF4034";
        public boolean detect_area_flag = false;
        public boolean privacy_authorization_flag = false;
        public boolean privacy_agreemen_show_flag = false;
        public boolean sdk_idcard_nfc_flag = false;
        public boolean face_img_concat_confuse_flag = false;

        public Config() {
        }
    }
}
