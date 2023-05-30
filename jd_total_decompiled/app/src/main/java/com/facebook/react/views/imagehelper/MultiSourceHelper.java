package com.facebook.react.views.imagehelper;

import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class MultiSourceHelper {

    /* loaded from: classes12.dex */
    public static class MultiSourceResult {
        @Nullable
        private final ImageSource bestResult;
        @Nullable
        private final ImageSource bestResultInCache;

        @Nullable
        public ImageSource getBestResult() {
            return this.bestResult;
        }

        @Nullable
        public ImageSource getBestResultInCache() {
            return this.bestResultInCache;
        }

        private MultiSourceResult(@Nullable ImageSource imageSource, @Nullable ImageSource imageSource2) {
            this.bestResult = imageSource;
            this.bestResultInCache = imageSource2;
        }
    }

    public static MultiSourceResult getBestSourceForSize(int i2, int i3, List<ImageSource> list) {
        return getBestSourceForSize(i2, i3, list, 1.0d);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.facebook.react.views.imagehelper.MultiSourceHelper$1, com.facebook.react.views.imagehelper.ImageSource] */
    public static MultiSourceResult getBestSourceForSize(int i2, int i3, List<ImageSource> list, double d) {
        ?? r1 = 0;
        if (list.isEmpty()) {
            return new MultiSourceResult(r1, r1);
        }
        if (list.size() == 1) {
            return new MultiSourceResult(list.get(0), r1);
        }
        if (i2 > 0 && i3 > 0) {
            ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
            double d2 = i2 * i3;
            Double.isNaN(d2);
            double d3 = d2 * d;
            double d4 = Double.MAX_VALUE;
            double d5 = Double.MAX_VALUE;
            ImageSource imageSource = null;
            ImageSource imageSource2 = null;
            for (ImageSource imageSource3 : list) {
                double abs = Math.abs(1.0d - (imageSource3.getSize() / d3));
                if (abs < d4) {
                    imageSource2 = imageSource3;
                    d4 = abs;
                }
                if (abs < d5 && imagePipeline.isInBitmapMemoryCache(imageSource3.getUri())) {
                    imageSource = imageSource3;
                    d5 = abs;
                }
            }
            if (imageSource != null && imageSource2 != null && imageSource.getSource().equals(imageSource2.getSource())) {
                imageSource = null;
            }
            return new MultiSourceResult(imageSource2, imageSource);
        }
        return new MultiSourceResult(r1, r1);
    }
}
