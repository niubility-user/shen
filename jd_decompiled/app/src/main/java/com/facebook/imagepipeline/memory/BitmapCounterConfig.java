package com.facebook.imagepipeline.memory;

/* loaded from: classes.dex */
public class BitmapCounterConfig {
    public static final int DEFAULT_MAX_BITMAP_COUNT = 384;
    private int mMaxBitmapCount;

    /* loaded from: classes.dex */
    public static class Builder {
        private int mMaxBitmapCount;

        private Builder() {
            this.mMaxBitmapCount = 384;
        }

        public BitmapCounterConfig build() {
            return new BitmapCounterConfig(this);
        }

        public int getMaxBitmapCount() {
            return this.mMaxBitmapCount;
        }

        public Builder setMaxBitmapCount(int i2) {
            this.mMaxBitmapCount = i2;
            return this;
        }
    }

    public BitmapCounterConfig(Builder builder) {
        this.mMaxBitmapCount = 384;
        this.mMaxBitmapCount = builder.getMaxBitmapCount();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getMaxBitmapCount() {
        return this.mMaxBitmapCount;
    }

    public void setMaxBitmapCount(int i2) {
        this.mMaxBitmapCount = i2;
    }
}
