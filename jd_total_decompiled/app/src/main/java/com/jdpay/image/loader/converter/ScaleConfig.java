package com.jdpay.image.loader.converter;

/* loaded from: classes18.dex */
public class ScaleConfig {
    public static final int MODE_SCALE_WIDTH = 1;
    public final int height;
    public final int maxHeight;
    public final int maxWidth;
    public final int mode;
    public final int width;

    /* loaded from: classes18.dex */
    public static class Builder {
        private int height;
        private int maxHeight;
        private int maxWidth;
        private int mode;
        private int width;

        public ScaleConfig build() {
            return new ScaleConfig(this.width, this.height, this.maxWidth, this.maxHeight, this.mode);
        }

        public Builder setHeight(int i2) {
            this.height = i2;
            return this;
        }

        public Builder setMaxHeight(int i2) {
            this.maxHeight = i2;
            return this;
        }

        public Builder setMaxWidth(int i2) {
            this.maxWidth = i2;
            return this;
        }

        public Builder setMode(int i2) {
            this.mode = i2;
            return this;
        }

        public Builder setWidth(int i2) {
            this.width = i2;
            return this;
        }
    }

    public ScaleConfig(int i2, int i3, int i4, int i5, int i6) {
        this.width = i2;
        this.height = i3;
        this.maxWidth = i4;
        this.maxHeight = i5;
        this.mode = i6;
    }

    public static boolean isValid(ScaleConfig scaleConfig) {
        return scaleConfig != null && scaleConfig.mode == 1 && scaleConfig.width > 0 && scaleConfig.maxHeight > 0;
    }
}
