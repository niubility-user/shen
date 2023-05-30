package com.jd.aips.verify.face;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.utils.Base64Utils;
import com.jd.aips.detect.face.bean.FaceImageData;
import com.jd.aips.detect.face.bean.FaceInfo;
import com.jd.aips.verify.SdkVerifyEngine;
import com.jd.aips.verify.VerifyCallback;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.activity.FaceVerifyDialogActivity;
import com.jd.aips.verify.face.activity.FaceVerifyNormalActivity;
import com.jd.aips.verify.tracker.TrackerCallback;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class FaceVerifyEngine extends SdkVerifyEngine<FaceVerifyParams, FaceVerifySession> {
    public static String SDK_NAME = "verification_sdk";
    public static String SDK_VERSION = "3.1.00";
    private static volatile FaceVerifyEngine instance;

    private Bundle buildData(int i2) {
        byte[] outputData;
        Bundle bundle = new Bundle();
        FaceImageData[] detectResults = ((FaceVerifySession) this.session).getDetectResults();
        if (i2 == 0 && detectResults != null && detectResults.length > 0 && (outputData = detectResults[0].getOutputData()) != null) {
            String encodeBytes = Base64Utils.encodeBytes(outputData);
            bundle.putString("faceImgBase64", encodeBytes);
            if (!TextUtils.isEmpty(encodeBytes)) {
                ArrayList<String> arrayList = new ArrayList<>(detectResults.length);
                arrayList.add(encodeBytes);
                for (int i3 = 1; i3 < detectResults.length; i3++) {
                    byte[] outputData2 = detectResults[i3].getOutputData();
                    if (outputData2 != null) {
                        arrayList.add(Base64Utils.encodeBytes(outputData2));
                    }
                }
                bundle.putStringArrayList("faceImgList", arrayList);
                FaceInfo faceInfoOfResult = ((FaceVerifySession) this.session).getFaceInfoOfResult();
                if (faceInfoOfResult != null) {
                    bundle.putSerializable("faceFrame", faceInfoOfResult.faceRect);
                }
            }
        }
        if (!bundle.containsKey("faceImgBase64")) {
            bundle.putString("faceImgBase64", "");
        }
        if (!bundle.containsKey("faceImgList")) {
            bundle.putStringArrayList("faceImgList", new ArrayList<>(0));
        }
        if (!bundle.containsKey("faceFrame")) {
            bundle.putSerializable("faceFrame", new HashMap(0));
        }
        return bundle;
    }

    public static FaceVerifyEngine getInstance() {
        if (instance == null) {
            synchronized (FaceVerifyEngine.class) {
                if (instance == null) {
                    instance = new FaceVerifyEngine();
                }
            }
        }
        return instance;
    }

    @Override // com.jd.aips.verify.SdkVerifyEngine
    public synchronized void callbackFinishSDK(int i2, String str) {
        if (this.session != 0 && ((FaceVerifySession) this.session).verifyParams != 0) {
            int i3 = ((FaceVerifyConfig) ((FaceVerifyParams) ((FaceVerifySession) this.session).verifyParams).verifyConfig).verificationSdk.config.scene_config;
            callbackFinishSDK(i2, str, (i3 == 2 || i3 == 3) ? buildData(i2) : null);
        }
    }

    @Override // com.jd.aips.verify.SdkVerifyEngine
    protected String getLogTagSuffix() {
        return "FACE";
    }

    @Override // com.jd.aips.verify.SdkVerifyEngine
    protected String getSdkName() {
        return SDK_NAME;
    }

    @Override // com.jd.aips.verify.SdkVerifyEngine
    protected String getSdkVersion() {
        return SDK_VERSION;
    }

    @Override // com.jd.aips.verify.SdkVerifyEngine
    protected void toLaunch() {
        VerificationSdk.Config config = ((FaceVerifyConfig) ((FaceVerifyParams) ((FaceVerifySession) this.session).verifyParams).verifyConfig).verificationSdk.config;
        int i2 = config.authentication_mode;
        if (i2 == 1) {
            Intent intent = new Intent(this.applicationContext, FaceVerifyNormalActivity.class);
            intent.setFlags(268435456);
            this.applicationContext.startActivity(intent);
        } else if (i2 == 3) {
            Intent intent2 = new Intent(this.applicationContext, FaceVerifyDialogActivity.class);
            intent2.setFlags(268435456);
            this.applicationContext.startActivity(intent2);
        } else {
            ((FaceVerifyTracker) ((FaceVerifySession) this.session).verifyTracker).trackSceneError();
            callbackFinishSDK(2, String.format("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aauthentication_mode = %s", Integer.valueOf(config.authentication_mode)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.SdkVerifyEngine
    public FaceVerifyParams buildVerifyParams(@NonNull Bundle bundle) {
        return new FaceVerifyParams(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.SdkVerifyEngine
    public FaceVerifySession buildVerifySession(@NonNull Context context, @NonNull FaceVerifyParams faceVerifyParams, @NonNull VerifyCallback verifyCallback, @Nullable TrackerCallback trackerCallback) {
        return new FaceVerifySession(context, faceVerifyParams, verifyCallback, trackerCallback);
    }
}
