package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

/* loaded from: classes.dex */
public final class ThumbnailSizeChecker {
    public static final float ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO = 1.3333334f;
    private static final int ROTATED_90_DEGREES_CLOCKWISE = 90;
    private static final int ROTATED_90_DEGREES_COUNTER_CLOCKWISE = 270;

    public static int getAcceptableSize(int i2) {
        return (int) (i2 * 1.3333334f);
    }

    public static boolean isImageBigEnough(int i2, int i3, ResizeOptions resizeOptions) {
        int acceptableSize = getAcceptableSize(i2);
        return resizeOptions == null ? ((float) acceptableSize) >= 2048.0f && getAcceptableSize(i3) >= 2048 : acceptableSize >= resizeOptions.width && getAcceptableSize(i3) >= resizeOptions.height;
    }

    public static boolean isImageBigEnough(EncodedImage encodedImage, ResizeOptions resizeOptions) {
        int height;
        int width;
        if (encodedImage == null) {
            return false;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 90 || rotationAngle == 270) {
            height = encodedImage.getHeight();
            width = encodedImage.getWidth();
        } else {
            height = encodedImage.getWidth();
            width = encodedImage.getHeight();
        }
        return isImageBigEnough(height, width, resizeOptions);
    }
}
