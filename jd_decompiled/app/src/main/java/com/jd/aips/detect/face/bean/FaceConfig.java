package com.jd.aips.detect.face.bean;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class FaceConfig implements Serializable {
    static final long serialVersionUID = 1632936659738532880L;
    public int flagMutipleFace = 0;
    public int flagForceRefine = 0;
    public int flagRotate = 0;
    public float faceBoundCenterX = 0.5f;
    public float faceBoundCenterY = 0.5f;
    public float faceBoundWidth = 1.0f;
    public float faceBoundHeight = 1.0f;
    public int faceMaxArea = 10000;
    public int faceMinArea = 1000;
    public int slffMode = 0;
    public int faceSnapshotTimes = 6;
    public int continueStaticTimes = 3;
    public float angleUp = 15.0f;
    public float angleDown = -15.0f;
    public float angleLeft = 20.0f;
    public float angleRight = -20.0f;
    public float rollLeft = 30.0f;
    public float rollRight = -30.0f;
    public float overlapArea1 = 0.8f;
    public float overlapArea2 = 0.9f;
    public float faceImgScale = 2.0f;
    public int liveMode = 1000;
    public int[] actions = {1003};
    public int liveContinueNum = 3;
    public float thShake = 0.75f;
    public float thNod = 0.7f;
    public float thCameraMove = 20.0f;
    public float livnessBoundScale = 1.5f;
    public int flagAfterSuccess = 0;
    public int imageType = 0;
    public int outputRotate = 1;
    public int flagBlinkDetect = 0;
    public int detectFaceSize = 0;
    public float detectFaceScale = 0.6f;
    public float frameOutOverlap = 0.8f;
    public int flagOccDetect = 0;
    public int occNotifyTimes = 6;
    public float thOccMouth = 0.144f;
    public float thOccEye = 0.203f;
    public float thBrightness = 0.1f;
    public int concatSize = 128;
    public int concatConfuse = 1;
    public int flagLog = 0;
}
