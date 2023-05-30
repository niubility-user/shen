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
    */
    public static int computeImageSampleSize(int i2, int i3, ResizeOptions resizeOptions, DownSample.ViewScaleType viewScaleType) {
        int i4;
        int i5 = resizeOptions.width;
        int i6 = resizeOptions.height;
        if (viewScaleType != null) {
            int i7 = AnonymousClass1.$SwitchMap$com$facebook$imagepipeline$request$DownSample$ViewScaleType[viewScaleType.ordinal()];
            if (i7 == 1) {
                i4 = 1;
                int i8 = i2;
                int i9 = i3;
                while (true) {
                    i8 /= 2;
                    if (i8 < i5 && i9 / 2 < i6) {
                        break;
                    }
                    i9 /= 2;
                    i4 *= 2;
                }
            } else if (i7 == 2) {
                i4 = 1;
                int i10 = i2;
                int i11 = i3;
                while (true) {
                    i10 /= 2;
                    if (i10 < i5 || (i11 = i11 / 2) < i6) {
                        break;
                    }
                    i4 *= 2;
                }
            }
            int i12 = i4 >= 1 ? i4 : 1;
            FLog.d("DownSampleUtil", "srcWidth:" + i2 + ",srcHeight:" + i3 + ",targetWidth:" + i5 + ",targetHeight:" + i6 + ",scale:" + i12);
            return i12;
        }
        i4 = 1;
        if (i4 >= 1) {
        }
        FLog.d("DownSampleUtil", "srcWidth:" + i2 + ",srcHeight:" + i3 + ",targetWidth:" + i5 + ",targetHeight:" + i6 + ",scale:" + i12);
        return i12;
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
