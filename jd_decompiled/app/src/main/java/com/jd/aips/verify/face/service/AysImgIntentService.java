package com.jd.aips.verify.face.service;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.jd.aips.common.utils.AksUtils;
import com.jd.aips.common.utils.JDCNImageUtils;
import com.jd.aips.detect.face.bean.FaceImageData;
import com.jd.aips.verify.face.FaceVerifyEngine;
import com.jd.aips.verify.face.FaceVerifySession;
import com.jd.aips.verify.face.api.UploadVerifyRecordApi;

/* loaded from: classes12.dex */
public class AysImgIntentService extends IntentService {
    public AysImgIntentService() {
        super("AysImgIntentService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(@Nullable Intent intent) {
        FaceImageData[] detectResults;
        if (intent == null) {
            return;
        }
        try {
            FaceVerifySession session = FaceVerifyEngine.getInstance().getSession();
            if (session == null || (detectResults = session.getDetectResults()) == null) {
                return;
            }
            if (detectResults.length > 1) {
                for (int i2 = 1; i2 < detectResults.length; i2++) {
                    UploadVerifyRecordApi.uploadFaceActionRecord(this, AksUtils.encryptAndBase64(this, JDCNImageUtils.jpegRotaing(detectResults[i2].getOutputData(), detectResults[i2].width, detectResults[i2].height, 80, 640, 10000, 0)), i2, session);
                }
            }
        } catch (Exception unused) {
        }
    }
}
