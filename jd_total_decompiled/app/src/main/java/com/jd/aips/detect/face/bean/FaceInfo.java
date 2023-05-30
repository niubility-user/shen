package com.jd.aips.detect.face.bean;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class FaceInfo implements Serializable {
    static final long serialVersionUID = -5083542566899742935L;
    public float faceBlur;
    public float faceBrightness;
    public float facePitch;
    public FaceRect faceRect;
    public float faceRoll;
    public float faceYaw;
    public long face_id;
    public int[] landmarks;
    public float occEyeL;
    public float occEyeR;
    public float occMouth;
    public String strEyeProb;
    public String strHeadActionProb;
    public String strMouthProb;
}
