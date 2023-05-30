package com.jd.aips.verify.face.biz;

import androidx.annotation.NonNull;
import com.jd.aips.detect.face.bean.FaceConfig;
import com.jd.aips.verify.config.FaceSdk;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.FaceVerifyConfig;
import java.util.List;

/* loaded from: classes12.dex */
public class FaceConfigHelper {
    public static FaceConfig buildFaceConfig(@NonNull FaceVerifyConfig faceVerifyConfig, int i2, int i3, boolean z) {
        FaceConfig faceConfig = new FaceConfig();
        if (i2 == 0) {
            faceConfig.flagRotate = 2;
        } else if (i2 == 90) {
            faceConfig.flagRotate = 1;
        } else if (i2 != 180) {
            faceConfig.flagRotate = 0;
        } else {
            faceConfig.flagRotate = 3;
        }
        faceConfig.outputRotate = 1;
        FaceSdk.Config config = faceVerifyConfig.faceSdk.config;
        VerificationSdk.Config config2 = faceVerifyConfig.verificationSdk.config;
        if (config2.expand_face_detect_range) {
            faceConfig.faceBoundWidth = 1.3f;
            faceConfig.faceBoundHeight = 1.3f;
        }
        faceConfig.slffMode = config.face_slffMode;
        faceConfig.faceSnapshotTimes = config.face_faceSnapshotTimes;
        faceConfig.continueStaticTimes = config.face_continueStaticTimes;
        faceConfig.overlapArea1 = config.face_overlapArea1;
        faceConfig.overlapArea2 = config.face_overlapArea2;
        faceConfig.angleUp = config.face_angleUp;
        faceConfig.angleDown = config.face_angleDown;
        faceConfig.angleLeft = config.face_angleLeft;
        faceConfig.angleRight = config.face_angleRight;
        faceConfig.rollLeft = config.face_rollLeft;
        faceConfig.rollRight = config.face_rollRight;
        faceConfig.faceImgScale = config.face_faceImgScale;
        faceConfig.frameOutOverlap = config.face_frameOutOverlap;
        faceConfig.flagOccDetect = config.face_flagOccDetect;
        faceConfig.occNotifyTimes = config.face_occNotifyTimes;
        float f2 = config.face_thOccMouth;
        if (f2 != 0.0f) {
            faceConfig.thOccMouth = f2;
        }
        if (config.face_thOccEye != 0.0f) {
            faceConfig.thOccEye = f2;
        }
        float f3 = config.face_thOccMouth_3_4;
        if (f3 != 0.0f) {
            faceConfig.thOccMouth = f3;
        }
        float f4 = config.face_thOccEye_3_4;
        if (f4 != 0.0f) {
            faceConfig.thOccEye = f4;
        }
        float f5 = config.face_thOccMouth_3_6_4;
        if (f5 != 0.0f) {
            faceConfig.thOccMouth = f5;
        }
        float f6 = config.face_thOccEye_3_6_4;
        if (f6 != 0.0f) {
            faceConfig.thOccEye = f6;
        }
        faceConfig.thBrightness = config.face_thBrightness;
        faceConfig.livnessBoundScale = config.face_livnessBoundScale;
        faceConfig.thShake = config.face_thShake;
        faceConfig.thNod = config.face_thNod;
        faceConfig.thCameraMove = config.face_thCameraMove;
        faceConfig.flagAfterSuccess = config2.facedazzle_flag ? 1 : 0;
        int[] faceDetectActions = getFaceDetectActions(config, i3);
        if (faceDetectActions != null && faceVerifyConfig.verificationSdk.config.sdk_face_identify_strategy == 2) {
            faceConfig.liveMode = 1002;
        } else {
            faceConfig.liveMode = 1000;
        }
        faceConfig.actions = faceDetectActions;
        faceConfig.concatSize = config.face_exposure_img_short_length;
        faceConfig.concatConfuse = faceVerifyConfig.verificationSdk.config.face_img_concat_confuse_flag ? 1 : 0;
        faceConfig.faceMinArea = config.face_faceMinArea;
        if (z) {
            faceConfig.flagLog = 1;
        }
        return faceConfig;
    }

    private static int[] getFaceDetectActions(FaceSdk.Config config, int i2) {
        int[] iArr = null;
        try {
            List<String> list = config.face_action_rules.get(i2).face_actions;
            if (list != null && list.size() > 0) {
                iArr = new int[list.size()];
                for (int i3 = 0; i3 < list.size(); i3++) {
                    iArr[i3] = Integer.parseInt(list.get(i3));
                }
            }
        } catch (Exception unused) {
        }
        return iArr;
    }
}
