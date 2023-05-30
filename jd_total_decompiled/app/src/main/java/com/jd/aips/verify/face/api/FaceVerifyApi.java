package com.jd.aips.verify.face.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.aips.common.utils.Base64Utils;
import com.jd.aips.common.utils.MD5Utils;
import com.jd.aips.detect.face.bean.FaceImageData;
import com.jd.aips.verify.BaseVerifyParams;
import com.jd.aips.verify.api.ApiException;
import com.jd.aips.verify.api.ResultDataWrapper;
import com.jd.aips.verify.api.VerifyApi;
import com.jd.aips.verify.face.FaceVerifyConfig;
import com.jd.aips.verify.face.FaceVerifyParams;
import com.jd.aips.verify.face.FaceVerifySession;
import com.jd.aips.verify.face.FaceVerifyTracker;
import com.jd.aips.verify.face.bean.ColorfulImage;
import com.jd.aips.verify.face.bean.FaceVerifyRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class FaceVerifyApi extends VerifyApi {
    public static final String PATH_FACE_VERIFY = "/f/securityAutoIdauth";

    private FaceVerifyApi() {
    }

    public static Bundle requestVerify(@NonNull Context context, @NonNull FaceVerifySession faceVerifySession) {
        byte[] outputData;
        FaceImageData[] detectResults = faceVerifySession.getDetectResults();
        if (detectResults != null && detectResults.length != 0) {
            Bundle bundle = new Bundle();
            FaceVerifyRequest faceVerifyRequest = new FaceVerifyRequest(context, faceVerifySession, "face_sdk", "3.6.5");
            faceVerifyRequest.verifyBusinessType = BaseVerifyParams.VERIFY_BUSINESS_TYPE_SIMPLE;
            faceVerifyRequest.antiAttackPolicy4Biz = "SFF";
            HashMap hashMap = new HashMap(2);
            hashMap.put(FaceVerifyRequest.FACE_DETECT_SDK_DOWNGRADED, Boolean.valueOf(faceVerifySession.isFaceDetectSdkDowngraded()));
            hashMap.put(FaceVerifyRequest.NOLIGHT, Boolean.valueOf(faceVerifySession.isFailedLighting()));
            faceVerifyRequest.extra = hashMap;
            String idCardToken = ((FaceVerifyParams) faceVerifySession.verifyParams).getIdCardToken();
            faceVerifyRequest.verifyStrategy = new HashMap(1);
            if (!TextUtils.isEmpty(idCardToken)) {
                faceVerifyRequest.idCardToken = idCardToken;
                faceVerifyRequest.verifyStrategy.put("IDP", "SFF");
            } else if (TextUtils.isEmpty(idCardToken)) {
                faceVerifyRequest.verifyStrategy.put("MP", "SFF");
            }
            faceVerifyRequest.faceData = new ArrayList();
            if (detectResults.length > 0) {
                byte[] outputData2 = detectResults[0].getOutputData();
                ((FaceVerifyTracker) faceVerifySession.verifyTracker).trackRequestVerify(outputData2.hashCode());
                String encodeBytes = Base64Utils.encodeBytes(outputData2);
                faceVerifyRequest.faceData.add("SFF#" + MD5Utils.md5_32bit(encodeBytes) + ":jdin:1.0:" + encodeBytes);
                if (detectResults[0].securityCode != null && detectResults[0].securityCode.length > 0) {
                    faceVerifyRequest.imageCRC = Base64Utils.encodeBytes(detectResults[0].securityCode);
                } else {
                    faceVerifyRequest.imageCRC = "";
                }
                if (detectResults.length > 1) {
                    String encodeBytes2 = Base64Utils.encodeBytes(detectResults[detectResults.length - 1].getOutputData());
                    faceVerifyRequest.faceData.add("AP#" + MD5Utils.md5_32bit(encodeBytes2) + ":jdin:1.0:" + encodeBytes2);
                }
            }
            if (((FaceVerifyConfig) ((FaceVerifyParams) faceVerifySession.verifyParams).verifyConfig).verificationSdk.config.resolutionImageFlag) {
                byte[] resizeImg = faceVerifySession.getResizeImg();
                if (resizeImg != null && resizeImg.length > 0) {
                    String encodeBytes3 = Base64Utils.encodeBytes(resizeImg);
                    faceVerifyRequest.faceData.add("RES#" + MD5Utils.md5_32bit(encodeBytes3) + ":jdin:1.0:" + encodeBytes3);
                } else {
                    faceVerifyRequest.faceData.add("RES#:jdin:1.0:");
                }
            }
            List<ColorfulImage> colorfulImages = faceVerifySession.getColorfulImages();
            if (colorfulImages != null && !colorfulImages.isEmpty()) {
                int size = colorfulImages.size();
                ArrayList arrayList = new ArrayList(size);
                ArrayList arrayList2 = new ArrayList(size);
                for (ColorfulImage colorfulImage : colorfulImages) {
                    arrayList.add("DAZZLE#" + MD5Utils.md5_32bit(colorfulImage.base64Image) + ":jdin:1.0:" + colorfulImage.base64Image);
                    arrayList2.add(colorfulImage.color);
                }
                faceVerifyRequest.dazzleImages = arrayList;
                faceVerifyRequest.dazzleColors = arrayList2;
            }
            FaceImageData exposureDetectResult = faceVerifySession.getExposureDetectResult();
            if (exposureDetectResult != null && (outputData = exposureDetectResult.getOutputData()) != null) {
                String encodeBytes4 = Base64Utils.encodeBytes(outputData);
                faceVerifyRequest.exposureImage = "EXP#" + MD5Utils.md5_32bit(encodeBytes4) + ":jdin:1.0:" + encodeBytes4;
                byte[] bArr = exposureDetectResult.securityCode;
                if (bArr != null && bArr.length > 0) {
                    faceVerifyRequest.exposureImageCRC = Base64Utils.encodeBytes(bArr);
                } else {
                    faceVerifyRequest.exposureImageCRC = "";
                }
            }
            try {
                bundle.putSerializable("data", (ResultDataWrapper) VerifyApi.toRequest(context, PATH_FACE_VERIFY, faceVerifyRequest, ResultDataWrapper.class));
            } catch (ApiException e2) {
                e2.getMessage();
                bundle.putInt("code", e2.code);
                bundle.putString("msg", e2.getMessage());
            } catch (Exception unused) {
                bundle.putInt("code", 10003);
            }
            return bundle;
        }
        return Bundle.EMPTY;
    }
}
