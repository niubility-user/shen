package com.jd.aips.detect.face.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface DetectCallbackType {
    public static final int TYPE_ACTION_CHANGE = 1004;
    public static final int TYPE_ACTION_DISCONTINUE = 1015;
    public static final int TYPE_CONCAT_DONE = 1099;
    public static final int TYPE_CONCAT_FAIL = 1098;
    public static final int TYPE_DID_FIND_FACE = 1003;
    public static final int TYPE_EYE_CLOSE = 1007;
    public static final int TYPE_FACE_LOST = 1002;
    public static final int TYPE_FACE_OCCLUTION_EYE = 1013;
    public static final int TYPE_FACE_OCCLUTION_MOUTH = 1012;
    public static final int TYPE_FACE_POSE_RIGHT = 1011;
    public static final int TYPE_FACE_TOO_DARK = 1014;
    public static final int TYPE_FACE_TOO_FAR = 1009;
    public static final int TYPE_FACE_TOO_NEAR = 1010;
    public static final int TYPE_FRAME_OUT = 1005;
    public static final int TYPE_HEAD_POSE_WRONG = 1008;
    public static final int TYPE_MOTION_BLUR = 1006;
    public static final int TYPE_PREPARE_SUCCESS = 1000;
    public static final int TYPE_SUCCESS = 1001;
}
