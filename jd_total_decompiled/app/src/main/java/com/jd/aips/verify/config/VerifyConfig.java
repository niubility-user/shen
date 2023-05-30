package com.jd.aips.verify.config;

import com.jd.aips.verify.api.ResultData;

/* loaded from: classes12.dex */
public class VerifyConfig extends ResultData {
    public static final int FACE_INVISIBLE_SDK = 2;
    public static final int FACE_NON_FULL_SCREEN_FACE_SDK = 3;
    public static final int FACE_VISIBLE_UI_SDK = 1;
    public static final int OCR_AND_FACE_SDK = 3;
    public static final int ONLY_FACE_SDK = 1;
    public static final int ONLY_OCR_SDK = 2;
    public static final int SCENE_CONFIG_ATOM = 2;
    public static final int SCENE_CONFIG_ATOM_HOOK = 3;
    public static final int SCENE_CONFIG_NORMAL = 1;
    static final long serialVersionUID = 9219403658870959449L;
    public String allInOneVersion;
    public VerificationSdk verificationSdk;
}
