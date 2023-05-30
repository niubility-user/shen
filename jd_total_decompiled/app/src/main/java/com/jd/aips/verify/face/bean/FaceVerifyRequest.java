package com.jd.aips.verify.face.bean;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jd.aips.verify.api.VerifyRequest;
import com.jd.aips.verify.face.FaceVerifyParams;
import com.jd.aips.verify.face.FaceVerifySession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class FaceVerifyRequest extends VerifyRequest {
    public static final String FACE_DETECT_SDK_DOWNGRADED = "faceDetectSdkDowngraded";
    public static final String NOLIGHT = "nolight";
    static final long serialVersionUID = 3731151712039247659L;
    public String antiAttackPolicy4Biz;
    public final String channelFlag;
    public List<String> dazzleColors;
    public List<String> dazzleImages;
    public String exposureImage;
    public String exposureImageCRC;
    public List<String> faceData;
    public final String faceSDK;
    public final String faceSDKVersion;
    public String idCardToken;
    public String imageCRC;
    public final String sdkToken;
    public com.jd.aips.common.bean.DeviceInfo shieldInfo;
    public Map<String, String> verifyStrategy;

    public FaceVerifyRequest(@NonNull Context context, @NonNull FaceVerifySession faceVerifySession, String str, String str2) {
        super(context, faceVerifySession);
        this.channelFlag = "101";
        this.idCardToken = "";
        this.faceData = Collections.emptyList();
        this.imageCRC = "";
        this.exposureImage = "";
        this.exposureImageCRC = "";
        this.dazzleImages = Collections.emptyList();
        this.dazzleColors = Collections.emptyList();
        this.faceSDK = str;
        this.faceSDKVersion = str2;
        this.sdkToken = ((FaceVerifyParams) faceVerifySession.verifyParams).sdkToken;
        this.shieldInfo = this.deviceInfo;
    }
}
