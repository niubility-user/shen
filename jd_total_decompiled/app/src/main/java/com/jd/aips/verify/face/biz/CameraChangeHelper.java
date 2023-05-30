package com.jd.aips.verify.face.biz;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import androidx.annotation.NonNull;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.face.FaceVerifyConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* loaded from: classes12.dex */
public class CameraChangeHelper {
    private int bitsPerPixel = 12;
    private int changedHeight;
    private int changedWidth;

    /* loaded from: classes12.dex */
    public interface CameraPreviewChangeInter {
        void previewChangeCallback(List<Camera.Size> list, int i2, int i3, String str);
    }

    private float getMaxKey(Map<Float, Camera.Size> map) {
        Object[] array = map.keySet().toArray();
        Arrays.sort(array);
        return ((Float) array[array.length - 1]).floatValue();
    }

    private Camera.Size reSelectPreviewSize_New(List<Camera.Size> list, int i2, int i3) {
        Camera.Size select16_9_New = select16_9_New(list, i2, i3);
        return select16_9_New == null ? selectPreviewSize_New(list, i2, i3) : select16_9_New;
    }

    private Camera.Size select16_9_New(List<Camera.Size> list, int i2, int i3) {
        int i4;
        try {
            int size = list.size();
            float f2 = (i2 * 1.0f) / i3;
            HashMap hashMap = new HashMap();
            for (int i5 = size - 1; i5 >= 0; i5--) {
                Camera.Size size2 = list.get(i5);
                if (size2 != null && (i4 = size2.height) != 0) {
                    int i6 = size2.width;
                    if (i6 * i4 != i3 * i2 && Math.max(i4, i6) < 2000 && Math.max(size2.height, size2.width) > 300) {
                        float f3 = (size2.width * 1.0f) / size2.height;
                        if (f3 >= 1.0f && f3 <= 2.3d) {
                            if (!hashMap.containsKey(Float.valueOf(f3))) {
                                hashMap.put(Float.valueOf(f3), size2);
                            } else if (hashMap.get(Float.valueOf(f3)) != null && ((Camera.Size) hashMap.get(Float.valueOf(f3))).width > size2.width) {
                                hashMap.put(Float.valueOf(f3), size2);
                            }
                        }
                    }
                }
            }
            Iterator it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                if (((Float) it.next()).floatValue() == f2) {
                    it.remove();
                }
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.putAll(hashMap);
            Iterator it2 = hashMap.keySet().iterator();
            while (it2.hasNext()) {
                Float f4 = (Float) it2.next();
                if (Math.max(f2, f4.floatValue()) / Math.min(f2, f4.floatValue()) <= 1.3d) {
                    it2.remove();
                }
            }
            if (hashMap.size() != 0) {
                ArrayList arrayList = new ArrayList();
                Iterator it3 = hashMap.entrySet().iterator();
                while (it3.hasNext()) {
                    arrayList.add((Float) ((Map.Entry) it3.next()).getKey());
                }
                return (Camera.Size) hashMap.get((Float) arrayList.get(new Random().nextInt(arrayList.size() - 1 > 1 ? arrayList.size() - 1 : 1)));
            }
            return (Camera.Size) hashMap2.get(Float.valueOf(getMaxKey(hashMap2)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Camera.Size selectCameraSize_New(List<Camera.Size> list, float f2, int i2, int i3, int i4) {
        int size = list.size();
        Camera.Size size2 = null;
        float f3 = 0.0f;
        for (int i5 = 0; i5 < size; i5++) {
            Camera.Size size3 = list.get(i5);
            int i6 = size3.height;
            if (i6 < i2) {
                if (size2 == null && !(i6 == i4 && size3.width == i3)) {
                    if (i6 != 0) {
                        f3 = (size3.width * 1.0f) / i6;
                    }
                    size2 = size3;
                } else if (i6 != 0) {
                    float f4 = (size3.width * 1.0f) / i6;
                    if (Math.abs(f4 - f2) > Math.abs(f3 - f2)) {
                        size2 = size3;
                        f3 = f4;
                    }
                }
            }
        }
        return size2;
    }

    private Camera.Size selectPreviewSize_New(List<Camera.Size> list, int i2, int i3) {
        int i4;
        float f2 = (i2 * 1.0f) / i3;
        int[] iArr = {1000, 2000};
        Camera.Size size = null;
        for (int i5 = 0; i5 < 2; i5++) {
            size = selectCameraSize_New(list, f2, iArr[i5], i2, i3);
            if (size != null && (i4 = size.height) != 0) {
                float f3 = (size.width * 1.0f) / i4;
                float f4 = f3 > f2 ? f3 / f2 : f2 / f3;
                if (f4 >= 1.3d) {
                    String str = "Scale = " + f4;
                    return size;
                }
            }
        }
        return size;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean changeCameraPreviewSize(Camera camera, Camera.PreviewCallback previewCallback, FaceVerifyConfig faceVerifyConfig, int i2, int i3, CameraPreviewChangeInter cameraPreviewChangeInter) throws Exception {
        long j2;
        long j3;
        boolean z;
        this.changedWidth = 0;
        this.changedHeight = 0;
        VerificationSdk.Config config = faceVerifyConfig.verificationSdk.config;
        if (camera == null) {
            return false;
        }
        List<Camera.Size> arrayList = new ArrayList<>();
        long j4 = 0;
        try {
            j2 = System.currentTimeMillis();
            try {
                camera.stopPreview();
                j4 = System.currentTimeMillis();
                Camera.Parameters parameters = camera.getParameters();
                this.bitsPerPixel = ImageFormat.getBitsPerPixel(parameters.getPreviewFormat());
                arrayList = parameters.getSupportedPreviewSizes();
                if (config.perform_get_support_preview_size && arrayList.size() <= config.lock_preview_rule_count) {
                    int i4 = config.lock_camera_preview_width;
                    this.changedWidth = i4;
                    int i5 = config.lock_camera_preview_height;
                    this.changedHeight = i5;
                    parameters.setPreviewSize(i4, i5);
                } else {
                    Camera.Size reSelectPreviewSize_New = reSelectPreviewSize_New(arrayList, i2, i3);
                    if (reSelectPreviewSize_New != null) {
                        int i6 = reSelectPreviewSize_New.width;
                        this.changedWidth = i6;
                        int i7 = reSelectPreviewSize_New.height;
                        this.changedHeight = i7;
                        parameters.setPreviewSize(i6, i7);
                    } else {
                        parameters.setPreviewSize(i2, i3);
                        z = false;
                        camera.setParameters(parameters);
                        camera.setPreviewCallback(previewCallback);
                        camera.startPreview();
                        if (cameraPreviewChangeInter != null) {
                            cameraPreviewChangeInter.previewChangeCallback(arrayList, this.changedWidth, this.changedHeight, String.format(" mResizeStopPreviewFrontTime = %d , mResizeStopPreviewAfterTime = %d ", Long.valueOf(j2), Long.valueOf(j4)));
                        }
                        return z;
                    }
                }
                z = true;
                camera.setParameters(parameters);
                camera.setPreviewCallback(previewCallback);
                camera.startPreview();
                if (cameraPreviewChangeInter != null) {
                }
                return z;
            } catch (Exception e2) {
                e = e2;
                j3 = j4;
                j4 = j2;
                try {
                    this.changedWidth = -1;
                    this.changedHeight = -1;
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    j2 = j4;
                    j4 = j3;
                    if (cameraPreviewChangeInter != null) {
                        cameraPreviewChangeInter.previewChangeCallback(arrayList, this.changedWidth, this.changedHeight, String.format(" mResizeStopPreviewFrontTime = %d , mResizeStopPreviewAfterTime = %d ", Long.valueOf(j2), Long.valueOf(j4)));
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (cameraPreviewChangeInter != null) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            j3 = 0;
        } catch (Throwable th3) {
            th = th3;
            j2 = 0;
        }
    }

    public boolean checkSizeChange(@NonNull byte[] bArr) {
        return bArr.length == ((this.changedWidth * this.changedHeight) * this.bitsPerPixel) / 8;
    }

    public boolean checkSizeResume(@NonNull byte[] bArr, int i2, int i3) {
        return bArr.length == ((i2 * i3) * this.bitsPerPixel) / 8;
    }

    public int getChangedHeight() {
        return this.changedHeight;
    }

    public int getChangedWidth() {
        return this.changedWidth;
    }

    public boolean resumePreviewSize(Camera camera, Camera.PreviewCallback previewCallback, int i2, int i3) {
        if (camera != null) {
            try {
                camera.stopPreview();
                Camera.Parameters parameters = camera.getParameters();
                if (parameters != null) {
                    this.bitsPerPixel = ImageFormat.getBitsPerPixel(parameters.getPreviewFormat());
                    String str = "resetCameraPreviewSize \u91cd\u7f6eCamera\u5206\u8fa8\u7387 width = " + i2 + " , height = " + i3;
                    parameters.setPreviewSize(i2, i3);
                    camera.setParameters(parameters);
                    camera.setPreviewCallback(previewCallback);
                }
                camera.startPreview();
                this.changedWidth = 0;
                this.changedHeight = 0;
                return true;
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.changedWidth = 0;
                this.changedHeight = 0;
                throw th;
            }
        }
        this.changedWidth = 0;
        this.changedHeight = 0;
        return false;
    }
}
