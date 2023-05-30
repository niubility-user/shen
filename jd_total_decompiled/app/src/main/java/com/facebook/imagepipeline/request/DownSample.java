package com.facebook.imagepipeline.request;

import android.widget.ImageView;
import com.facebook.imagepipeline.common.ResizeOptions;

/* loaded from: classes.dex */
public class DownSample {
    private boolean downSamepleEnable;
    private int inSamepleSise;
    private ResizeOptions resizeOptions;
    private String url;
    private ViewScaleType viewScaleType;

    /* renamed from: com.facebook.imagepipeline.request.DownSample$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum ViewScaleType {
        FIT_INSIDE,
        CROP;

        public static ViewScaleType fromImageView(ImageView imageView) {
            int i2 = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()];
            return (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) ? FIT_INSIDE : CROP;
        }
    }

    public DownSample() {
    }

    public DownSample(boolean z, int i2, ResizeOptions resizeOptions, ViewScaleType viewScaleType) {
        this.downSamepleEnable = z;
        this.inSamepleSise = i2;
        this.resizeOptions = resizeOptions;
        this.viewScaleType = viewScaleType;
    }

    public int getInSamepleSise() {
        return this.inSamepleSise;
    }

    public ResizeOptions getResizeOptions() {
        return this.resizeOptions;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isDownSamepleEnable() {
        return this.downSamepleEnable;
    }

    public void setDownSamepleEnable(boolean z) {
        this.downSamepleEnable = z;
    }

    public void setInSamepleSise(int i2) {
        this.inSamepleSise = i2;
    }

    public void setResizeOptions(ResizeOptions resizeOptions) {
        this.resizeOptions = resizeOptions;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
