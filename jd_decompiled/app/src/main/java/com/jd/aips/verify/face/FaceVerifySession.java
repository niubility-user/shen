package com.jd.aips.verify.face;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.utils.JDCNImageUtils;
import com.jd.aips.detect.face.bean.FaceDataInfo;
import com.jd.aips.detect.face.bean.FaceImageData;
import com.jd.aips.detect.face.bean.FaceInfo;
import com.jd.aips.detect.face.bean.FrameInfo;
import com.jd.aips.verify.SdkVerifySession;
import com.jd.aips.verify.VerifyCallback;
import com.jd.aips.verify.face.bean.ColorfulImage;
import com.jd.aips.verify.tracker.TrackerCallback;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes12.dex */
public class FaceVerifySession extends SdkVerifySession<FaceVerifyConfig, FaceVerifyParams, FaceVerifyTracker> {
    private volatile List<ColorfulImage> colorfulImages;
    private volatile FaceImageData[] detectResults;
    private volatile FaceImageData exposureDetectResult;
    private volatile boolean faceDetectSdkDowngraded;
    private volatile FaceInfo faceInfoOfResult;
    private volatile List<FaceInfo> faceInfos;
    private volatile boolean failedLighting;
    private volatile FrameInfo frameInfo;
    private volatile byte[] resizeImg;

    public FaceVerifySession(@NonNull Context context, @NonNull FaceVerifyParams faceVerifyParams, @NonNull VerifyCallback verifyCallback, @Nullable TrackerCallback trackerCallback) {
        super(context, faceVerifyParams, verifyCallback, trackerCallback);
        this.faceDetectSdkDowngraded = false;
    }

    public synchronized void cacheColorfulImage(ColorfulImage colorfulImage) {
        if (this.colorfulImages == null) {
            this.colorfulImages = new CopyOnWriteArrayList();
        }
        this.colorfulImages.add(colorfulImage);
    }

    public synchronized void cacheDetectResult(@Nullable FaceImageData[] faceImageDataArr, @Nullable FaceDataInfo[] faceDataInfoArr) {
        this.detectResults = faceImageDataArr;
        if (faceDataInfoArr != null && faceDataInfoArr.length > 0) {
            this.faceInfoOfResult = faceDataInfoArr[0].all_face_info;
        } else {
            this.faceInfoOfResult = null;
        }
    }

    public synchronized void cacheExposureDetectResult(FaceImageData faceImageData) {
        this.exposureDetectResult = faceImageData;
    }

    public synchronized void cacheFaceInfos(FaceInfo[] faceInfoArr) {
        if (this.faceInfos == null) {
            this.faceInfos = new CopyOnWriteArrayList();
        }
        if (faceInfoArr != null && faceInfoArr.length > 0) {
            this.faceInfos.add(faceInfoArr[0]);
        }
    }

    public synchronized void cacheFrameInfo(FrameInfo frameInfo) {
        this.frameInfo = frameInfo;
    }

    public synchronized boolean cacheResizeImg(byte[] bArr, int i2, int i3, int i4) {
        this.resizeImg = JDCNImageUtils.yuv2JpegRotaingWithoutMirror(bArr, i2, i3, 80, i2, i3, i4);
        return this.resizeImg != null;
    }

    @Override // com.jd.aips.verify.VerifySession
    public void destroy() {
        resetAllCaches();
        super.destroy();
    }

    public List<ColorfulImage> getColorfulImages() {
        return this.colorfulImages;
    }

    public FaceImageData[] getDetectResults() {
        return this.detectResults;
    }

    public FaceImageData getExposureDetectResult() {
        return this.exposureDetectResult;
    }

    public FaceInfo getFaceInfoOfResult() {
        return this.faceInfoOfResult;
    }

    public List<FaceInfo> getFaceInfos() {
        return this.faceInfos;
    }

    public FrameInfo getFrameInfo() {
        return this.frameInfo;
    }

    public byte[] getResizeImg() {
        return this.resizeImg;
    }

    public boolean isFaceDetectSdkDowngraded() {
        return this.faceDetectSdkDowngraded;
    }

    public boolean isFailedLighting() {
        return this.failedLighting;
    }

    public synchronized void resetAllCaches() {
        resetDetectCaches();
        this.faceDetectSdkDowngraded = false;
        this.failedLighting = false;
    }

    public synchronized void resetDetectCaches() {
        this.faceInfos = null;
        this.faceInfoOfResult = null;
        this.frameInfo = null;
        this.detectResults = null;
        this.exposureDetectResult = null;
        this.resizeImg = null;
        this.colorfulImages = null;
    }

    public synchronized void resetFaceInfosCache() {
        this.faceInfos = null;
    }

    public void setFaceDetectSdkDowngraded(boolean z) {
        this.faceDetectSdkDowngraded = z;
    }

    public void setFailedLighting(boolean z) {
        this.failedLighting = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.verify.VerifySession
    public FaceVerifyTracker buildVerifyTracker(@NonNull Context context) {
        return new FaceVerifyTracker(context, this, this.trackerCallback);
    }
}
