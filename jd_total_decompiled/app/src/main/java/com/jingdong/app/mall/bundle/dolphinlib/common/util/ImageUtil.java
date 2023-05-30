package com.jingdong.app.mall.bundle.dolphinlib.common.util;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;

/* loaded from: classes19.dex */
public class ImageUtil {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.dolphinlib.common.util.ImageUtil$2  reason: invalid class name */
    /* loaded from: classes19.dex */
    public static /* synthetic */ class AnonymousClass2 {
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
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static final void displayImage(ImageArr imageArr) {
        ImageView img = imageArr.getImg();
        if (img == null || !(img instanceof SimpleDraweeView)) {
            return;
        }
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) img;
        GenericDraweeHierarchyBuilder newInstance = GenericDraweeHierarchyBuilder.newInstance(simpleDraweeView.getResources());
        newInstance.setActualImageScaleType(getActualImageScaleType(simpleDraweeView));
        float[] cornersRadii = imageArr.getCornersRadii();
        if (imageArr.isRoundAsCircle()) {
            newInstance.setRoundingParams(RoundingParams.asCircle());
        } else if (cornersRadii != null && cornersRadii.length == 4) {
            newInstance.setRoundingParams(RoundingParams.fromCornersRadii(cornersRadii[0], cornersRadii[1], cornersRadii[2], cornersRadii[3]));
        }
        simpleDraweeView.setHierarchy(newInstance.build());
        simpleDraweeView.setController(makeController(imageArr, simpleDraweeView));
    }

    private static final ScalingUtils.ScaleType getActualImageScaleType(SimpleDraweeView simpleDraweeView) {
        switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[simpleDraweeView.getScaleType().ordinal()]) {
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
                return ScalingUtils.ScaleType.CENTER_INSIDE;
            default:
                return ScalingUtils.ScaleType.CENTER_CROP;
        }
    }

    public static final void loadImage(String str, ImageWraper imageWraper) {
        ImageArr.Builder create = ImageArr.Builder.create(imageWraper.getImageView());
        create.setRoundAsCircle(imageWraper.isRoundAsCircle());
        create.setCornersRadii(imageWraper.getCornersRadii());
        create.setNeedImageOnLoading(true);
        create.setNeedImageOnLoading(true);
        create.setUrl(str);
        displayImage(create.buid());
    }

    private static final DraweeController makeController(final ImageArr imageArr, final SimpleDraweeView simpleDraweeView) {
        return Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController()).setAutoPlayAnimations(true).setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageArr.getUrl())).setImageDecodeOptions(new ImageDecodeOptionsBuilder().setForceStaticImage(true).build()).build()).setControllerListener(new BaseControllerListener<ImageInfo>() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.util.ImageUtil.1
            @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
            public void onFailure(String str, Throwable th) {
                super.onFailure(str, th);
                if (ImageArr.this.getLoadingListener() != null) {
                    ImageArr.this.getLoadingListener().onLoadingFailed(str, simpleDraweeView);
                }
            }

            public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(str, imageInfo, animatable, null);
            }
        }).build();
    }
}
