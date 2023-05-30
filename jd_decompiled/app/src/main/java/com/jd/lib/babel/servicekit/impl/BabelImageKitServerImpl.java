package com.jd.lib.babel.servicekit.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.babel.servicekit.imagekit.BabelDrawableListener;
import com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes13.dex */
public class BabelImageKitServerImpl implements BabelImageKitServer {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.babel.servicekit.impl.BabelImageKitServerImpl$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private ScalingUtils.ScaleType getActualImageScaleType(SimpleDraweeView simpleDraweeView) {
        switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[simpleDraweeView.getScaleType().ordinal()]) {
            case 1:
                return ScalingUtils.ScaleType.FIT_XY;
            case 2:
                return ScalingUtils.ScaleType.FIT_START;
            case 3:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 4:
                return ScalingUtils.ScaleType.FIT_END;
            case 5:
                return ScalingUtils.ScaleType.CENTER;
            case 6:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 7:
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            default:
                return ScalingUtils.ScaleType.CENTER_CROP;
        }
    }

    @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
    public void displayImage(ImageArr imageArr) {
        if (imageArr == null || imageArr.getImg() == null) {
            return;
        }
        if (imageArr.getImg() instanceof SimpleDraweeView) {
            float[] cornersRadii = imageArr.getCornersRadii();
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageArr.getImg();
            GenericDraweeHierarchyBuilder newInstance = GenericDraweeHierarchyBuilder.newInstance(simpleDraweeView.getResources());
            newInstance.setActualImageScaleType(getActualImageScaleType(simpleDraweeView));
            if (cornersRadii != null && cornersRadii.length == 4) {
                newInstance.setRoundingParams(RoundingParams.fromCornersRadii(cornersRadii[0], cornersRadii[1], cornersRadii[2], cornersRadii[3]));
            }
            simpleDraweeView.setHierarchy(newInstance.build());
        }
        if (imageArr.isUseOption()) {
            JDDisplayImageOptions isScale = new JDDisplayImageOptions().isScale(imageArr.isScale());
            if (!imageArr.isNeedImageOnFail()) {
                isScale.showImageOnFail((Drawable) null);
            }
            if (imageArr.getBitmapConfig() != null) {
                isScale.setBitmapConfig(imageArr.getBitmapConfig());
            }
            JDImageUtils.displayImage(imageArr.getUrl(), imageArr.getImg(), isScale, imageArr.isNeedImageOnFail());
            return;
        }
        JDImageUtils.displayImage(imageArr.getUrl(), imageArr.getImg(), (JDDisplayImageOptions) null, imageArr.isNeedImageOnFail());
    }

    @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
    public ImageView newImageView(Context context, AttributeSet attributeSet) {
        return new SimpleDraweeView(context);
    }

    @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
    public void obtainDrawable(Context context, String str, BabelDrawableListener babelDrawableListener) {
    }
}
