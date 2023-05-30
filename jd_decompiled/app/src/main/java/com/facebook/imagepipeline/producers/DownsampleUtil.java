package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.DownSample;
import com.facebook.imagepipeline.request.ImageRequest;

/* loaded from: classes.dex */
public class DownsampleUtil {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    private static final float INTERVAL_ROUNDING = 0.33333334f;

    /* renamed from: com.facebook.imagepipeline.producers.DownsampleUtil$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$request$DownSample$ViewScaleType;

        static {
            int[] iArr = new int[DownSample.ViewScaleType.values().length];
            $SwitchMap$com$facebook$imagepipeline$request$DownSample$ViewScaleType = iArr;
            try {
                iArr[DownSample.ViewScaleType.FIT_INSIDE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$request$DownSample$ViewScaleType[DownSample.ViewScaleType.CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private DownsampleUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int computeImageSampleSize(int r6, int r7, com.facebook.imagepipeline.common.ResizeOptions r8, com.facebook.imagepipeline.request.DownSample.ViewScaleType r9) {
        /*
            int r0 = r8.width
            int r8 = r8.height
            r1 = 1
            if (r9 == 0) goto L30
            int[] r2 = com.facebook.imagepipeline.producers.DownsampleUtil.AnonymousClass1.$SwitchMap$com$facebook$imagepipeline$request$DownSample$ViewScaleType
            int r9 = r9.ordinal()
            r9 = r2[r9]
            r2 = 2
            if (r9 == r1) goto L21
            if (r9 == r2) goto L15
            goto L30
        L15:
            r4 = 1
            r9 = r6
            r3 = r7
        L18:
            int r9 = r9 / r2
            if (r9 < r0) goto L31
            int r3 = r3 / r2
            if (r3 < r8) goto L31
            int r4 = r4 * 2
            goto L18
        L21:
            r4 = 1
            r9 = r6
            r3 = r7
        L24:
            int r9 = r9 / r2
            if (r9 >= r0) goto L2b
            int r5 = r3 / 2
            if (r5 < r8) goto L31
        L2b:
            int r3 = r3 / 2
            int r4 = r4 * 2
            goto L24
        L30:
            r4 = 1
        L31:
            if (r4 >= r1) goto L34
            goto L35
        L34:
            r1 = r4
        L35:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "srcWidth:"
            r9.append(r2)
            r9.append(r6)
            java.lang.String r6 = ",srcHeight:"
            r9.append(r6)
            r9.append(r7)
            java.lang.String r6 = ",targetWidth:"
            r9.append(r6)
            r9.append(r0)
            java.lang.String r6 = ",targetHeight:"
            r9.append(r6)
            r9.append(r8)
            java.lang.String r6 = ",scale:"
            r9.append(r6)
            r9.append(r1)
            java.lang.String r6 = r9.toString()
            java.lang.String r7 = "DownSampleUtil"
            com.facebook.common.logging.FLog.d(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DownsampleUtil.computeImageSampleSize(int, int, com.facebook.imagepipeline.common.ResizeOptions, com.facebook.imagepipeline.request.DownSample$ViewScaleType):int");
    }

    @VisibleForTesting
    static float determineDownsampleRatio(ImageRequest imageRequest, EncodedImage encodedImage) {
        Preconditions.checkArgument(EncodedImage.isMetaDataAvailable(encodedImage));
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        }
        int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        boolean z = rotationAngle == 90 || rotationAngle == 270;
        int height = z ? encodedImage.getHeight() : encodedImage.getWidth();
        int width = z ? encodedImage.getWidth() : encodedImage.getHeight();
        float f2 = resizeOptions.width / height;
        float f3 = resizeOptions.height / width;
        float max = Math.max(f2, f3);
        FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(height), Integer.valueOf(width), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(max), imageRequest.getSourceUri().toString());
        return max;
    }

    public static int determineSampleSize(ImageRequest imageRequest, EncodedImage encodedImage) {
        if (EncodedImage.isMetaDataAvailable(encodedImage)) {
            float determineDownsampleRatio = determineDownsampleRatio(imageRequest, encodedImage);
            int ratioToSampleSizeJPEG = encodedImage.getImageFormat() == DefaultImageFormats.JPEG ? ratioToSampleSizeJPEG(determineDownsampleRatio) : ratioToSampleSize(determineDownsampleRatio);
            int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
            ResizeOptions resizeOptions = imageRequest.getResizeOptions();
            float f2 = resizeOptions != null ? resizeOptions.maxBitmapSize : 2048.0f;
            while (max / ratioToSampleSizeJPEG > f2) {
                ratioToSampleSizeJPEG = encodedImage.getImageFormat() == DefaultImageFormats.JPEG ? ratioToSampleSizeJPEG * 2 : ratioToSampleSizeJPEG + 1;
            }
            return ratioToSampleSizeJPEG;
        }
        return 1;
    }

    private static int getRotationAngle(ImageRequest imageRequest, EncodedImage encodedImage) {
        if (imageRequest.getRotationOptions().useImageMetadata()) {
            int rotationAngle = encodedImage.getRotationAngle();
            Preconditions.checkArgument(rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270);
            return rotationAngle;
        }
        return 0;
    }

    @VisibleForTesting
    static int ratioToSampleSize(float f2) {
        if (f2 > 0.6666667f) {
            return 1;
        }
        int i2 = 2;
        while (true) {
            double d = i2;
            double pow = Math.pow(d, 2.0d);
            Double.isNaN(d);
            Double.isNaN(d);
            if ((1.0d / d) + ((1.0d / (pow - d)) * 0.3333333432674408d) <= f2) {
                return i2 - 1;
            }
            i2++;
        }
    }

    @VisibleForTesting
    static int ratioToSampleSizeJPEG(float f2) {
        if (f2 > 0.6666667f) {
            return 1;
        }
        int i2 = 2;
        while (true) {
            int i3 = i2 * 2;
            double d = i3;
            Double.isNaN(d);
            double d2 = 1.0d / d;
            if (d2 + (0.3333333432674408d * d2) <= f2) {
                return i2;
            }
            i2 = i3;
        }
    }

    @VisibleForTesting
    static int roundToPowerOfTwo(int i2) {
        int i3 = 1;
        while (i3 < i2) {
            i3 *= 2;
        }
        return i3;
    }
}
