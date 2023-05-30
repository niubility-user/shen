package com.jd.aips.verify.face.api;

import android.content.Context;
import android.text.TextUtils;
import com.jd.aips.common.utils.AksUtils;
import com.jd.aips.common.utils.FsGsonUtil;
import com.jd.aips.common.utils.JDCNImageUtils;
import com.jd.aips.verify.api.ApiHelper;
import com.jd.aips.verify.face.FaceVerifyParams;
import com.jd.aips.verify.face.FaceVerifySession;
import com.jd.aips.verify.face.bean.DeviceInfo;
import com.jd.aips.verify.face.bean.Flow;
import com.jd.aips.verify.face.bean.UploadVerifyRecord;
import com.jingdong.common.entity.JDQuickPassParam;
import com.jingdong.sdk.platform.business.personal.R2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes12.dex */
public class UploadVerifyRecordApi {
    public static final String PATH_UPLOAD_VERIFY_RECORD = "/record/uploadVerifyRecord";

    public static void uploadFaceActionRecord(Context context, String str, int i2, FaceVerifySession faceVerifySession) {
        UploadVerifyRecord uploadVerifyRecord = new UploadVerifyRecord();
        uploadVerifyRecord.setBusinessId(((FaceVerifyParams) faceVerifySession.verifyParams).getBusinessId());
        uploadVerifyRecord.setDeviceId("M101101011811260156");
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceSN = "M101101011811260156";
        uploadVerifyRecord.setDeviceInfo(deviceInfo);
        Flow flow = new Flow();
        flow.setVerifyId(faceVerifySession.id);
        flow.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        flow.setAlgChannel(JDQuickPassParam.JD_PAY_CODE_SOURCE_JDJR);
        flow.setImgBase64(str);
        flow.setNirImgBase64("");
        flow.setVerifyResult("VARIFY_PASS");
        flow.setVerifyScore(-1.0d);
        flow.setUserId(((FaceVerifyParams) faceVerifySession.verifyParams).getUserId());
        flow.setModelVersion("3.4.0");
        flow.setLiveScore(0.0d);
        flow.setHackScore(0.0d);
        flow.setGammaScore(0.0d);
        flow.setPhiScore(0.0d);
        flow.setThetaScore(0.0d);
        flow.setBlurScore(0.0d);
        flow.setDistance(0.0d);
        flow.setDistance(0.0d);
        flow.setDecisionCode("0");
        flow.setEncryptionType("AKS");
        flow.setOrderId(((FaceVerifyParams) faceVerifySession.verifyParams).getVerifyToken());
        flow.setExt("" + i2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(flow);
        uploadVerifyRecord.setFlowList(arrayList);
        uploadVerifyRecord.setSdkToken(((FaceVerifyParams) faceVerifySession.verifyParams).sdkToken);
        try {
            ApiHelper.toPost(String.format("%s%s", ApiHelper.getGatewayUrl(), PATH_UPLOAD_VERIFY_RECORD), FsGsonUtil.toJson(uploadVerifyRecord));
        } catch (Throwable unused) {
        }
    }

    public static void uploadThumbnailFlowRecord(Context context, FaceVerifySession faceVerifySession, int i2, byte[] bArr, int i3, int i4, int i5, String str) {
        byte[] yuv2JpegRotaingWithoutMirror;
        if (bArr != null && Math.abs(bArr.length - (((i3 * i4) * 3) / 2)) <= 2) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    yuv2JpegRotaingWithoutMirror = JDCNImageUtils.yuv2JpegRotaingWithoutMirror(bArr, i3, i4, 80, 512, i4, i5);
                    String str2 = "\u70ab\u5f69\u56fe\u5927\u5c0f = " + yuv2JpegRotaingWithoutMirror.length;
                } else {
                    yuv2JpegRotaingWithoutMirror = JDCNImageUtils.yuv2JpegRotaingWithoutMirror(bArr, i3, i4, 70, R2.anim.manto_translate_dialog_in, 80, i5);
                }
                if (yuv2JpegRotaingWithoutMirror == null) {
                    return;
                }
                String encryptAndBase64 = AksUtils.encryptAndBase64(context, yuv2JpegRotaingWithoutMirror);
                UploadVerifyRecord uploadVerifyRecord = new UploadVerifyRecord();
                uploadVerifyRecord.setSdkToken(((FaceVerifyParams) faceVerifySession.verifyParams).sdkToken);
                if (!TextUtils.isEmpty(str)) {
                    uploadVerifyRecord.setBusinessId(((FaceVerifyParams) faceVerifySession.verifyParams).getBusinessId() + "_color");
                } else {
                    uploadVerifyRecord.setBusinessId(((FaceVerifyParams) faceVerifySession.verifyParams).getBusinessId());
                }
                uploadVerifyRecord.setDeviceId("M101101011811260156");
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.deviceSN = "M101101011811260156";
                uploadVerifyRecord.setDeviceInfo(deviceInfo);
                Flow flow = new Flow();
                flow.setVerifyId(faceVerifySession.id);
                flow.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                flow.setAlgChannel(JDQuickPassParam.JD_PAY_CODE_SOURCE_JDJR);
                flow.setImgBase64(encryptAndBase64);
                flow.setNirImgBase64("");
                flow.setVerifyResult("VARIFY_PASS");
                flow.setVerifyScore(-1.0d);
                flow.setUserId(((FaceVerifyParams) faceVerifySession.verifyParams).getUserId());
                flow.setModelVersion("3.4.0");
                flow.setLiveScore(0.0d);
                flow.setHackScore(0.0d);
                flow.setGammaScore(0.0d);
                flow.setPhiScore(0.0d);
                flow.setThetaScore(0.0d);
                flow.setBlurScore(0.0d);
                flow.setDistance(0.0d);
                flow.setDistance(0.0d);
                flow.setDecisionCode("0");
                flow.setEncryptionType("AKS");
                flow.setOrderId(((FaceVerifyParams) faceVerifySession.verifyParams).getVerifyToken());
                if (TextUtils.isEmpty(str)) {
                    flow.setExt("" + i2);
                } else {
                    flow.setExt("{\"type\":" + str + "}");
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(flow);
                uploadVerifyRecord.setFlowList(arrayList);
                ApiHelper.toPost(String.format("%s%s", ApiHelper.getGatewayUrl(), PATH_UPLOAD_VERIFY_RECORD), FsGsonUtil.toJson(uploadVerifyRecord));
            } catch (Throwable unused) {
            }
        }
    }
}
