package com.jd.aips.verify.face.biz;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import androidx.annotation.NonNull;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean changeCameraPreviewSize(android.hardware.Camera r18, android.hardware.Camera.PreviewCallback r19, com.jd.aips.verify.face.FaceVerifyConfig r20, int r21, int r22, com.jd.aips.verify.face.biz.CameraChangeHelper.CameraPreviewChangeInter r23) throws java.lang.Exception {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r21
            r3 = r22
            r4 = r23
            java.lang.String r5 = " mResizeStopPreviewFrontTime = %d , mResizeStopPreviewAfterTime = %d "
            r6 = 0
            r1.changedWidth = r6
            r1.changedHeight = r6
            r7 = r20
            com.jd.aips.verify.config.VerificationSdk r7 = r7.verificationSdk
            com.jd.aips.verify.config.VerificationSdk$Config r7 = r7.config
            if (r0 == 0) goto Lc7
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r9 = 2
            r10 = 0
            long r13 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r18.stopPreview()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            long r10 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            android.hardware.Camera$Parameters r15 = r18.getParameters()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            int r16 = r15.getPreviewFormat()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            int r12 = android.graphics.ImageFormat.getBitsPerPixel(r16)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r1.bitsPerPixel = r12     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            java.util.List r8 = r15.getSupportedPreviewSizes()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            boolean r12 = r7.perform_get_support_preview_size     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            if (r12 == 0) goto L56
            int r12 = r8.size()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            int r6 = r7.lock_preview_rule_count     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            if (r12 > r6) goto L56
            int r2 = r7.lock_camera_preview_width     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r1.changedWidth = r2     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            int r3 = r7.lock_camera_preview_height     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r1.changedHeight = r3     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r15.setPreviewSize(r2, r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            goto L67
        L56:
            android.hardware.Camera$Size r6 = r1.reSelectPreviewSize_New(r8, r2, r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            if (r6 == 0) goto L69
            int r2 = r6.width     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r1.changedWidth = r2     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            int r3 = r6.height     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r1.changedHeight = r3     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r15.setPreviewSize(r2, r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
        L67:
            r2 = 1
            goto L6d
        L69:
            r15.setPreviewSize(r2, r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r2 = 0
        L6d:
            r0.setParameters(r15)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r18.setPreviewCallback(r19)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            r18.startPreview()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L97
            if (r4 == 0) goto L93
            java.lang.Object[] r0 = new java.lang.Object[r9]
            java.lang.Long r3 = java.lang.Long.valueOf(r13)
            r6 = 0
            r0[r6] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r10)
            r6 = 1
            r0[r6] = r3
            java.lang.String r0 = java.lang.String.format(r5, r0)
            int r3 = r1.changedWidth
            int r5 = r1.changedHeight
            r4.previewChangeCallback(r8, r3, r5, r0)
        L93:
            r6 = r2
            goto Lc7
        L95:
            r0 = move-exception
            goto La9
        L97:
            r0 = move-exception
            r2 = r10
            r10 = r13
            goto La0
        L9b:
            r0 = move-exception
            r13 = r10
            goto La9
        L9e:
            r0 = move-exception
            r2 = r10
        La0:
            r6 = -1
            r1.changedWidth = r6     // Catch: java.lang.Throwable -> La6
            r1.changedHeight = r6     // Catch: java.lang.Throwable -> La6
            throw r0     // Catch: java.lang.Throwable -> La6
        La6:
            r0 = move-exception
            r13 = r10
            r10 = r2
        La9:
            if (r4 == 0) goto Lc6
            java.lang.Object[] r2 = new java.lang.Object[r9]
            java.lang.Long r3 = java.lang.Long.valueOf(r13)
            r6 = 0
            r2[r6] = r3
            java.lang.Long r3 = java.lang.Long.valueOf(r10)
            r6 = 1
            r2[r6] = r3
            java.lang.String r2 = java.lang.String.format(r5, r2)
            int r3 = r1.changedWidth
            int r5 = r1.changedHeight
            r4.previewChangeCallback(r8, r3, r5, r2)
        Lc6:
            throw r0
        Lc7:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.verify.face.biz.CameraChangeHelper.changeCameraPreviewSize(android.hardware.Camera, android.hardware.Camera$PreviewCallback, com.jd.aips.verify.face.FaceVerifyConfig, int, int, com.jd.aips.verify.face.biz.CameraChangeHelper$CameraPreviewChangeInter):boolean");
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
