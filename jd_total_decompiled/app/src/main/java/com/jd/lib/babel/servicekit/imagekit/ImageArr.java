package com.jd.lib.babel.servicekit.imagekit;

import android.graphics.Bitmap;
import android.widget.ImageView;
import java.util.Arrays;

/* loaded from: classes13.dex */
public class ImageArr {
    private Bitmap.Config bitmapConfig;
    private float[] cornersRadiis;
    private ImageView img;
    private BabelImageLoadingListener loadingListener;
    private boolean roundAsCircle;
    private String url;
    private boolean needImageOnLoading = true;
    private boolean needImageOnFail = true;
    private boolean useOption = false;
    private boolean isScale = false;

    /* loaded from: classes13.dex */
    public static class Builder {
        private Bitmap.Config bitmapConfig;
        private float[] cornersRadiis;
        private ImageView img;
        private boolean isScale;
        private BabelImageLoadingListener loadingListener;
        private boolean needImageOnFail;
        private boolean needImageOnLoading;
        private boolean roundAsCircle;
        private String url;
        private boolean useOption;

        private Builder(ImageView imageView) {
            this.needImageOnLoading = false;
            this.needImageOnFail = false;
            this.roundAsCircle = false;
            this.cornersRadiis = new float[4];
            this.useOption = false;
            this.isScale = false;
            this.img = imageView;
        }

        public static Builder create(ImageView imageView) {
            return new Builder(imageView);
        }

        public ImageArr buid() {
            ImageArr imageArr = new ImageArr(this.img);
            imageArr.url = this.url;
            imageArr.cornersRadiis = this.cornersRadiis;
            imageArr.roundAsCircle = this.roundAsCircle;
            imageArr.needImageOnFail = this.needImageOnFail;
            imageArr.needImageOnLoading = this.needImageOnLoading;
            imageArr.loadingListener = this.loadingListener;
            imageArr.useOption = this.useOption;
            imageArr.isScale = this.isScale;
            imageArr.bitmapConfig = this.bitmapConfig;
            return imageArr;
        }

        public Builder setBitmapConfig(Bitmap.Config config) {
            this.bitmapConfig = config;
            return this;
        }

        public Builder setCornersRadii(float f2) {
            Arrays.fill(this.cornersRadiis, f2);
            return this;
        }

        public Builder setImg(ImageView imageView) {
            this.img = imageView;
            return this;
        }

        public Builder setLoadingListener(BabelImageLoadingListener babelImageLoadingListener) {
            this.loadingListener = babelImageLoadingListener;
            return this;
        }

        public Builder setNeedImageOnFail(boolean z) {
            this.needImageOnFail = z;
            return this;
        }

        public Builder setNeedImageOnLoading(boolean z) {
            this.needImageOnLoading = z;
            return this;
        }

        public Builder setRoundAsCircle(boolean z) {
            this.roundAsCircle = z;
            return this;
        }

        public Builder setScale(boolean z) {
            this.isScale = z;
            return this;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        public Builder setUseOption(boolean z) {
            this.useOption = z;
            return this;
        }

        public static Builder create(ImageWraper imageWraper) {
            return new Builder(imageWraper);
        }

        public Builder setCornersRadii(float f2, float f3, float f4, float f5) {
            float[] fArr = this.cornersRadiis;
            fArr[0] = f2;
            fArr[1] = f3;
            fArr[2] = f4;
            fArr[3] = f5;
            return this;
        }

        private Builder(ImageWraper imageWraper) {
            this.needImageOnLoading = false;
            this.needImageOnFail = false;
            this.roundAsCircle = false;
            this.cornersRadiis = new float[4];
            this.useOption = false;
            this.isScale = false;
            if (imageWraper != null) {
                this.img = imageWraper.getImageView();
                return;
            }
            throw new RuntimeException("ImageWraper not null!");
        }
    }

    public ImageArr(ImageView imageView) {
        this.img = imageView;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public float[] getCornersRadii() {
        return this.cornersRadiis;
    }

    public ImageView getImg() {
        return this.img;
    }

    public BabelImageLoadingListener getLoadingListener() {
        return this.loadingListener;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isNeedImageOnFail() {
        return this.needImageOnFail;
    }

    public boolean isNeedImageOnLoading() {
        return this.needImageOnLoading;
    }

    public boolean isRoundAsCircle() {
        return this.roundAsCircle;
    }

    public boolean isScale() {
        return this.isScale;
    }

    public boolean isUseOption() {
        return this.useOption;
    }
}
