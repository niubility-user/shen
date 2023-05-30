package com.jd.aips.detect.face;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jd.aips.detect.face.bean.FaceConfig;
import com.jd.aips.detect.face.bean.FaceDataInfo;
import com.jd.aips.detect.face.bean.FaceImageData;
import com.jd.aips.detect.face.bean.FaceInfo;
import com.jd.aips.detect.face.bean.FrameInfo;
import com.jd.aips.tools.linker.SafeLinker;
import java.io.UnsupportedEncodingException;

/* loaded from: classes12.dex */
public class FaceDetector {
    public static final String FACE_SDK_NAME = "face_sdk";
    public static final String FACE_SDK_VERSION = "3.6.5";
    private static final String LIBRARY_NAME = "jdface-jni";
    private static final String MODEL_DIR = "caffe";
    private static volatile FaceDetector instance;
    volatile DetectCallback detectCallback = null;
    volatile boolean soLoaded = false;
    volatile boolean inited = false;

    /* loaded from: classes12.dex */
    public interface DetectCallback {
        void onDetectCallback(int i2, FaceImageData[] faceImageDataArr, int i3, FaceDataInfo[] faceDataInfoArr);

        void onDetectError(int i2, String str);
    }

    private FaceDetector() {
    }

    public static FaceDetector getInstance() {
        if (instance == null) {
            synchronized (FaceDetector.class) {
                if (instance == null) {
                    instance = new FaceDetector();
                }
            }
        }
        return instance;
    }

    private boolean loadLibrary(Context context) {
        boolean z;
        try {
            System.loadLibrary(LIBRARY_NAME);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            return z;
        }
        try {
            SafeLinker.recursively().loadLibrary(context, LIBRARY_NAME);
            return true;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public static native FaceInfo[] nativeDetect(byte[] bArr, int i2, int i3);

    public static native void nativeDetectResume(byte[] bArr, int i2);

    public static native FrameInfo nativeGetFrameInfo();

    public static native String nativeGetSdkInfo();

    public static native boolean nativeInit(String str);

    public static native void nativeRelease();

    public static native void nativeSetConcatControl(int i2);

    public static native void nativeSetFaceConfig(FaceConfig faceConfig);

    public static native void nativeSetImgSavePath(String str);

    static void onDetectCallBack(int i2, FaceImageData[] faceImageDataArr, int i3, FaceDataInfo[] faceDataInfoArr) {
        DetectCallback detectCallback = getInstance().detectCallback;
        if (detectCallback != null) {
            detectCallback.onDetectCallback(i2, faceImageDataArr, i3, faceDataInfoArr);
        }
    }

    private boolean validate() {
        if (!this.inited) {
            onError(2003, "failed to init detect sdk!");
        }
        return this.inited;
    }

    public synchronized FaceInfo[] detect(byte[] bArr, int i2, int i3) {
        if (validate()) {
            return nativeDetect(bArr, i2, i3);
        }
        return null;
    }

    public synchronized FrameInfo getFrameInfo() {
        if (validate()) {
            return nativeGetFrameInfo();
        }
        return null;
    }

    public synchronized boolean init(@NonNull Context context) {
        if (!this.soLoaded) {
            this.soLoaded = loadLibrary(context);
        }
        if (!this.soLoaded) {
            this.inited = false;
            onError(2004, "failed to load so library!");
            return false;
        }
        if (!this.inited) {
            this.inited = nativeInit(context.getDir(MODEL_DIR, 0).getAbsolutePath());
            if (!this.inited) {
                onError(2003, "failed to init detect sdk!");
            }
        }
        return this.inited;
    }

    public synchronized void onError(int i2, String str) {
        if (this.detectCallback != null) {
            this.detectCallback.onDetectError(i2, str);
        }
    }

    public synchronized void release() {
        this.detectCallback = null;
        if (validate()) {
            this.inited = false;
            nativeRelease();
        }
    }

    public synchronized void resume(@NonNull FaceConfig faceConfig, @NonNull String str) {
        byte[] bArr;
        if (validate()) {
            nativeSetFaceConfig(faceConfig);
            int length = str.length();
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                bArr = new byte[0];
                length = 0;
            }
            nativeDetectResume(bArr, length);
        }
    }

    public synchronized void setConcatControl(int i2) {
        if (validate()) {
            nativeSetConcatControl(i2);
        }
    }

    public synchronized void setDetectCallback(@NonNull DetectCallback detectCallback) {
        this.detectCallback = detectCallback;
    }
}
