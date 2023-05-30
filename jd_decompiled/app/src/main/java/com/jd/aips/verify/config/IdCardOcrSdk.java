package com.jd.aips.verify.config;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class IdCardOcrSdk implements Serializable {
    static final long serialVersionUID = -1855778967955716500L;
    public Config config;
    public String version;

    /* loaded from: classes12.dex */
    public class Config implements Serializable {
        static final long serialVersionUID = -6536501627071263775L;
        public float idcard_thIDCard = 0.9f;
        public float idcard_thIncomplete = 0.9f;
        public float idcard_thReflection = 2.0f;
        public float idcard_thHackReflection = 2.0f;
        public float idcard_thBlur = 0.3f;
        public int idcard_frameNum = 10;
        public int idcard_hackFrameNum = 20;
        public float idcard_thTiltAngle = 35.0f;
        public float idcard_thRotateAngle = 8.0f;
        public float idcard_thOcc = 0.9f;
        public float idcard_thDistance = 0.5f;

        public Config() {
        }
    }
}
