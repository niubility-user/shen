package com.facebook.animated.giflite;

import android.graphics.Movie;
import com.facebook.animated.giflite.decoder.GifMetadataDecoder;
import com.facebook.animated.giflite.draw.MovieAnimatedImage;
import com.facebook.animated.giflite.draw.MovieDrawer;
import com.facebook.animated.giflite.draw.MovieFrame;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class GifDecoder implements ImageDecoder {
    private static AnimatedDrawableFrameInfo.DisposalMethod translateFrameDisposal(int i2) {
        return i2 != 2 ? i2 != 3 ? AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT : AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_PREVIOUS : AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND;
    }

    @Override // com.facebook.imagepipeline.decoder.ImageDecoder
    public CloseableImage decode(EncodedImage encodedImage, int i2, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        InputStream inputStream = encodedImage.getInputStream();
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GifMetadataDecoder create = GifMetadataDecoder.create(inputStream, byteArrayOutputStream);
                if (byteArrayOutputStream.size() > 0) {
                    inputStream.close();
                    inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                }
                inputStream.reset();
                Movie decodeStream = Movie.decodeStream(inputStream);
                MovieDrawer movieDrawer = new MovieDrawer(decodeStream);
                int frameCount = create.getFrameCount();
                MovieFrame[] movieFrameArr = new MovieFrame[frameCount];
                int i3 = 0;
                int i4 = 0;
                while (i4 < frameCount) {
                    int frameDurationMs = create.getFrameDurationMs(i4);
                    int i5 = i3 + frameDurationMs;
                    movieFrameArr[i4] = new MovieFrame(movieDrawer, i5, frameDurationMs, decodeStream.width(), decodeStream.height(), translateFrameDisposal(create.getFrameDisposal(i4)));
                    i4++;
                    i3 = i5;
                }
                CloseableAnimatedImage closeableAnimatedImage = new CloseableAnimatedImage(AnimatedImageResult.forAnimatedImage(new MovieAnimatedImage(movieFrameArr, encodedImage.getSize(), decodeStream.duration(), create.getLoopCount())), false);
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                return closeableAnimatedImage;
            } catch (IOException e2) {
                throw new RuntimeException("Error while decoding gif", e2);
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }
}
