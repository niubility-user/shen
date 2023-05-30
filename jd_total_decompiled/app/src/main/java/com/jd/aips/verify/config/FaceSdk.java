package com.jd.aips.verify.config;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes12.dex */
public class FaceSdk implements Serializable {
    static final long serialVersionUID = -8571117948415359740L;
    public Config config;
    public String version;

    /* loaded from: classes12.dex */
    public class Config implements Serializable {
        static final long serialVersionUID = -6656918195239227360L;
        public List<FaceActionRule> face_action_rules;
        public int face_slffMode = 0;
        public int face_faceSnapshotTimes = 6;
        public int face_continueStaticTimes = 3;
        public float face_angleUp = 12.0f;
        public float face_angleDown = -12.0f;
        public float face_angleLeft = 12.0f;
        public float face_angleRight = -12.0f;
        public float face_rollLeft = 20.0f;
        public float face_rollRight = -20.0f;
        public float face_overlapArea1 = 0.8f;
        public float face_overlapArea2 = 0.9f;
        public float face_faceImgScale = 2.0f;
        public float face_frameOutOverlap = 0.8f;
        public int face_flagOccDetect = 0;
        public int face_occNotifyTimes = 6;
        public float face_thOccMouth = 0.7f;
        public float face_thOccEye = 0.9f;
        public float face_thOccMouth_3_4 = 0.7f;
        public float face_thOccEye_3_4 = 0.9f;
        public float face_thOccMouth_3_6_4 = 0.144f;
        public float face_thOccEye_3_6_4 = 0.203f;
        public float face_thBrightness = 0.1f;
        public float face_thNod = 0.75f;
        public float face_thShake = 0.7f;
        public float face_thCameraMove = 8.0f;
        public float face_livnessBoundScale = 1.5f;
        public String face_action_timeout_1004 = "10";
        public String face_action_timeout_1005 = "10";
        public String face_action_timeout_1002 = "10";
        public String face_action_timeout_1003 = "10";
        public String face_action_timeout_1000 = "10";
        public int face_exposure_img_short_length = 128;
        public int face_faceMinArea = 1000;

        /* loaded from: classes12.dex */
        public class FaceActionRule implements Serializable {
            static final long serialVersionUID = 1986792390400064483L;
            public List<String> face_actions;

            public FaceActionRule() {
            }
        }

        public Config() {
        }
    }
}
