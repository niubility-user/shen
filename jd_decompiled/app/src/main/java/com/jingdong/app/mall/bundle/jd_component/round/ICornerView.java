package com.jingdong.app.mall.bundle.jd_component.round;

import android.graphics.Canvas;
import android.graphics.Path;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public interface ICornerView {
    public static final int ROUND = 1;
    public static final int SQUIRCLE = 2;

    /* loaded from: classes2.dex */
    public static class Builder {
        private float bottomLeftRadius;
        private float bottomRightRadius;
        private int height;
        public int mode;
        private float ratio = 1.0f;
        private float topLeftRadius;
        private float topRightRadius;
        private int width;

        public Builder(int i2) {
            this.mode = i2;
        }

        public static Builder getInstance(int i2) {
            return new Builder(i2);
        }

        public ICornerView build() {
            ICornerView drawRoundView;
            int i2 = this.mode;
            if (i2 == 1) {
                drawRoundView = new DrawRoundView(this.topLeftRadius, this.topRightRadius, this.bottomLeftRadius, this.bottomRightRadius);
            } else if (i2 != 2) {
                drawRoundView = new DrawNoneView();
            } else {
                drawRoundView = new DrawSquircleView(this.topLeftRadius, this.topRightRadius, this.bottomLeftRadius, this.bottomRightRadius);
                drawRoundView.setRatio(this.ratio);
            }
            drawRoundView.onSizeChanged(this.width, this.height);
            return drawRoundView;
        }

        public Builder setBottomLeftRadius(float f2) {
            this.bottomLeftRadius = f2;
            return this;
        }

        public Builder setBottomRightRadius(float f2) {
            this.bottomRightRadius = f2;
            return this;
        }

        public Builder setHeight(int i2) {
            this.height = i2;
            return this;
        }

        public Builder setRadius(float f2) {
            return setRadius(f2, f2, f2, f2);
        }

        public Builder setRatio(float f2) {
            this.ratio = f2;
            return this;
        }

        public Builder setTopLeftRadius(float f2) {
            this.topLeftRadius = f2;
            return this;
        }

        public Builder setTopRightRadius(float f2) {
            this.topRightRadius = f2;
            return this;
        }

        public Builder setWidth(int i2) {
            this.width = i2;
            return this;
        }

        public Builder setRadius(float f2, float f3, float f4, float f5) {
            this.topLeftRadius = f2;
            this.topRightRadius = f3;
            this.bottomLeftRadius = f4;
            this.bottomRightRadius = f5;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RoundMode {
    }

    void drawBottomLeft(Canvas canvas);

    void drawBottomRight(Canvas canvas);

    void drawCorner(Canvas canvas);

    void drawTopLeft(Canvas canvas);

    void drawTopRight(Canvas canvas);

    Path getPath();

    Path getShadowPath(float f2, float f3);

    void onSizeChanged(int i2, int i3);

    void setPadding(int i2, int i3);

    void setRadius(float f2);

    void setRadius(float f2, float f3, float f4, float f5);

    void setRatio(float f2);
}
