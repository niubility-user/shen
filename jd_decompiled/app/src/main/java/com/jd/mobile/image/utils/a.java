package com.jd.mobile.image.utils;

import android.graphics.drawable.Animatable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;

/* loaded from: classes17.dex */
public class a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.mobile.image.utils.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class ViewOnClickListenerC0210a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Animatable f6848g;

        ViewOnClickListenerC0210a(Animatable animatable) {
            this.f6848g = animatable;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f6848g.isRunning()) {
                this.f6848g.stop();
            } else {
                this.f6848g.start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            a = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static ScalingUtils.ScaleType a(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return null;
        }
        switch (b.a[scaleType.ordinal()]) {
            case 1:
                return ScalingUtils.ScaleType.CENTER;
            case 2:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 3:
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            case 4:
                return ScalingUtils.ScaleType.FIT_XY;
            case 5:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 6:
                return ScalingUtils.ScaleType.FIT_END;
            case 7:
                return ScalingUtils.ScaleType.FIT_START;
            default:
                return null;
        }
    }

    public static ResizeOptions b(View view) {
        int i2;
        int i3;
        int i4 = 0;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                i4 = layoutParams.width;
                i3 = layoutParams.height;
            } else {
                i3 = 0;
            }
            if (i4 <= 0) {
                i4 = view.getWidth();
            }
            i2 = i3 <= 0 ? view.getHeight() : i3;
        } else {
            i2 = 0;
        }
        if (i4 <= 0) {
            i4 = com.jd.mobile.image.utils.b.d();
        }
        if (i2 <= 0) {
            i2 = com.jd.mobile.image.utils.b.a();
        }
        return new ResizeOptions(i4, i2);
    }

    public static void c(View view, Animatable animatable) {
        if (view == null || animatable == null) {
            return;
        }
        view.setOnClickListener(new ViewOnClickListenerC0210a(animatable));
    }

    public static void d(ImageInfo imageInfo, View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if ((layoutParams.width == -2 || layoutParams.height == -2) && imageInfo != null) {
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                if (height <= 0 || width <= 0) {
                    return;
                }
                if (layoutParams.width == -2 && layoutParams.height == -2) {
                    view.getLayoutParams().width = width;
                    view.getLayoutParams().height = height;
                    view.requestLayout();
                    return;
                }
                boolean z = view instanceof SimpleDraweeView;
                if (!z || ((SimpleDraweeView) view).getAspectRatio() <= 0.0f) {
                    int i2 = layoutParams.width;
                    if (i2 == -2 || layoutParams.height == -2) {
                        if (layoutParams.height > 0) {
                            view.getLayoutParams().width = (layoutParams.height * width) / height;
                        } else if (i2 > 0) {
                            view.getLayoutParams().height = (layoutParams.width * height) / width;
                        } else if (z) {
                            ((SimpleDraweeView) view).setAspectRatio(width / height);
                        } else {
                            view.getLayoutParams().width = width;
                            view.getLayoutParams().height = height;
                        }
                        view.requestLayout();
                    }
                }
            }
        }
    }
}
