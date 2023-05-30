package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga2.YogaConstants;
import com.jd.dynamic.DYConstants;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes13.dex */
public class JDCustomImageViewManager extends SimpleViewManager<SimpleDraweeView> {

    /* loaded from: classes13.dex */
    public interface BitMapCallback {
        void onResult(Drawable drawable);
    }

    /* loaded from: classes13.dex */
    public static class BlurPostprocessor extends BasePostprocessor {
        int radius;

        public BlurPostprocessor(int i2) {
            this.radius = 10;
            this.radius = i2;
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
        public String getName() {
            return "blurPostprocessor";
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor
        public void process(Bitmap bitmap) {
            int i2;
            int i3;
            BlurPostprocessor blurPostprocessor = this;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i4 = width * height;
            int[] iArr = new int[i4];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i5 = width - 1;
            int i6 = height - 1;
            int i7 = blurPostprocessor.radius;
            int i8 = i7 + i7 + 1;
            int[] iArr2 = new int[i4];
            int[] iArr3 = new int[i4];
            int[] iArr4 = new int[i4];
            int[] iArr5 = new int[Math.max(width, height)];
            int i9 = (i8 + 1) >> 1;
            int i10 = i9 * i9;
            int i11 = i10 * 256;
            int[] iArr6 = new int[i11];
            for (int i12 = 0; i12 < i11; i12++) {
                iArr6[i12] = i12 / i10;
            }
            int[][] iArr7 = (int[][]) Array.newInstance(int.class, i8, 3);
            int i13 = blurPostprocessor.radius + 1;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            while (i14 < height) {
                int i17 = -blurPostprocessor.radius;
                int i18 = height;
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                int i22 = 0;
                int i23 = 0;
                int i24 = 0;
                int i25 = 0;
                int i26 = 0;
                int i27 = 0;
                while (true) {
                    i3 = blurPostprocessor.radius;
                    if (i17 > i3) {
                        break;
                    }
                    int i28 = i6;
                    int i29 = iArr[i15 + Math.min(i5, Math.max(i17, 0))];
                    int[] iArr8 = iArr7[blurPostprocessor.radius + i17];
                    iArr8[0] = (i29 & 16711680) >> 16;
                    iArr8[1] = (i29 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    iArr8[2] = i29 & 255;
                    int abs = i13 - Math.abs(i17);
                    i19 += iArr8[0] * abs;
                    i20 += iArr8[1] * abs;
                    i21 += iArr8[2] * abs;
                    if (i17 > 0) {
                        i25 += iArr8[0];
                        i26 += iArr8[1];
                        i27 += iArr8[2];
                    } else {
                        i22 += iArr8[0];
                        i23 += iArr8[1];
                        i24 += iArr8[2];
                    }
                    i17++;
                    i6 = i28;
                }
                int i30 = i6;
                for (int i31 = 0; i31 < width; i31++) {
                    iArr2[i15] = iArr6[i19];
                    iArr3[i15] = iArr6[i20];
                    iArr4[i15] = iArr6[i21];
                    int i32 = i19 - i22;
                    int i33 = i20 - i23;
                    int i34 = i21 - i24;
                    int i35 = blurPostprocessor.radius;
                    int[] iArr9 = iArr7[((i3 - i35) + i8) % i8];
                    int i36 = i22 - iArr9[0];
                    int i37 = i23 - iArr9[1];
                    int i38 = i24 - iArr9[2];
                    if (i14 == 0) {
                        iArr5[i31] = Math.min(i35 + i31 + 1, i5);
                    }
                    int i39 = iArr[i16 + iArr5[i31]];
                    iArr9[0] = (i39 & 16711680) >> 16;
                    iArr9[1] = (i39 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    iArr9[2] = i39 & 255;
                    int i40 = i25 + iArr9[0];
                    int i41 = i26 + iArr9[1];
                    int i42 = i27 + iArr9[2];
                    i19 = i32 + i40;
                    i20 = i33 + i41;
                    i21 = i34 + i42;
                    i3 = (i3 + 1) % i8;
                    int[] iArr10 = iArr7[i3 % i8];
                    i22 = i36 + iArr10[0];
                    i23 = i37 + iArr10[1];
                    i24 = i38 + iArr10[2];
                    i25 = i40 - iArr10[0];
                    i26 = i41 - iArr10[1];
                    i27 = i42 - iArr10[2];
                    i15++;
                }
                i16 += width;
                i14++;
                height = i18;
                i6 = i30;
            }
            int i43 = i6;
            int i44 = height;
            int i45 = 0;
            while (i45 < width) {
                int i46 = blurPostprocessor.radius;
                int i47 = (-i46) * width;
                int i48 = -i46;
                int[] iArr11 = iArr5;
                int i49 = 0;
                int i50 = 0;
                int i51 = 0;
                int i52 = 0;
                int i53 = 0;
                int i54 = 0;
                int i55 = 0;
                int i56 = 0;
                int i57 = 0;
                while (true) {
                    i2 = blurPostprocessor.radius;
                    if (i48 > i2) {
                        break;
                    }
                    int max = Math.max(0, i47) + i45;
                    int[] iArr12 = iArr7[blurPostprocessor.radius + i48];
                    iArr12[0] = iArr2[max];
                    iArr12[1] = iArr3[max];
                    iArr12[2] = iArr4[max];
                    int abs2 = i13 - Math.abs(i48);
                    i49 += iArr2[max] * abs2;
                    i50 += iArr3[max] * abs2;
                    i51 += iArr4[max] * abs2;
                    if (i48 > 0) {
                        i55 += iArr12[0];
                        i56 += iArr12[1];
                        i57 += iArr12[2];
                    } else {
                        i52 += iArr12[0];
                        i53 += iArr12[1];
                        i54 += iArr12[2];
                    }
                    int i58 = i43;
                    if (i48 < i58) {
                        i47 += width;
                    }
                    i48++;
                    i43 = i58;
                }
                int i59 = i2;
                int i60 = i43;
                int i61 = i45;
                int i62 = i44;
                int i63 = 0;
                while (i63 < i62) {
                    iArr[i61] = (iArr[i61] & (-16777216)) | (iArr6[i49] << 16) | (iArr6[i50] << 8) | iArr6[i51];
                    int i64 = i49 - i52;
                    int i65 = i50 - i53;
                    int i66 = i51 - i54;
                    int i67 = i62;
                    int[] iArr13 = iArr7[((i59 - blurPostprocessor.radius) + i8) % i8];
                    int i68 = i52 - iArr13[0];
                    int i69 = i53 - iArr13[1];
                    int i70 = i54 - iArr13[2];
                    if (i45 == 0) {
                        iArr11[i63] = Math.min(i63 + i13, i60) * width;
                    }
                    int i71 = iArr11[i63] + i45;
                    iArr13[0] = iArr2[i71];
                    iArr13[1] = iArr3[i71];
                    iArr13[2] = iArr4[i71];
                    int i72 = i55 + iArr13[0];
                    int i73 = i56 + iArr13[1];
                    int i74 = i57 + iArr13[2];
                    i49 = i64 + i72;
                    i50 = i65 + i73;
                    i51 = i66 + i74;
                    i59 = (i59 + 1) % i8;
                    int[] iArr14 = iArr7[i59];
                    i52 = i68 + iArr14[0];
                    i53 = i69 + iArr14[1];
                    i54 = i70 + iArr14[2];
                    i55 = i72 - iArr14[0];
                    i56 = i73 - iArr14[1];
                    i57 = i74 - iArr14[2];
                    i61 += width;
                    i63++;
                    blurPostprocessor = this;
                    i62 = i67;
                }
                i44 = i62;
                i45++;
                blurPostprocessor = this;
                i43 = i60;
                iArr5 = iArr11;
            }
            bitmap.setPixels(iArr, 0, width, 0, 0, width, i44);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RTCJDCustomImageViewManager";
    }

    @ReactPropGroup(defaultFloat = YogaConstants.UNDEFINED, names = {"borderRadius", ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public void setBorderRadius(JDReactSimpleDraweeView jDReactSimpleDraweeView, int i2, float f2) {
        if (!com.facebook.yoga.YogaConstants.isUndefined(f2)) {
            f2 = PixelUtil.toPixelFromDIP(f2);
        }
        if (i2 == 0) {
            jDReactSimpleDraweeView.setBorderRadius(f2);
        } else {
            jDReactSimpleDraweeView.setBorderRadius(f2, i2 - 1);
        }
    }

    @ReactProp(name = "source")
    public void setSource(JDReactSimpleDraweeView jDReactSimpleDraweeView, @Nullable ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey("url")) {
            return;
        }
        String string = readableMap.getString("url");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        jDReactSimpleDraweeView.setImageResource(string, readableMap.hasKey("blurRadius") ? readableMap.getInt("blurRadius") : 0, readableMap.hasKey(ViewProps.RESIZE_MODE) ? readableMap.getString(ViewProps.RESIZE_MODE) : "fit_xy");
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public SimpleDraweeView createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactSimpleDraweeView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(SimpleDraweeView simpleDraweeView) {
        super.onAfterUpdateTransaction((JDCustomImageViewManager) simpleDraweeView);
        if (simpleDraweeView instanceof JDReactSimpleDraweeView) {
            ((JDReactSimpleDraweeView) simpleDraweeView).maybeUpdate();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(SimpleDraweeView simpleDraweeView) {
        super.onDropViewInstance((JDCustomImageViewManager) simpleDraweeView);
        JDReactSimpleDraweeView jDReactSimpleDraweeView = (JDReactSimpleDraweeView) simpleDraweeView;
        ((ThemedReactContext) simpleDraweeView.getContext()).removeLifecycleEventListener(jDReactSimpleDraweeView);
        jDReactSimpleDraweeView.cleanupAndRemoveListener();
    }

    /* loaded from: classes13.dex */
    public static class JDReactSimpleDraweeView extends SimpleDraweeView implements LifecycleEventListener {
        private BitMapCallback callback;
        private AbstractDraweeController controller;
        private ControllerListener controllerListener;
        private Drawable drawable;
        private ImageInfo imageInfo;
        public boolean left;
        public Bitmap mBitmapCached;
        private float mBorderRadius;
        private float[] mBorderRadiusArray;
        private boolean mIsDirty;
        private boolean success;

        public JDReactSimpleDraweeView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.success = false;
            this.left = true;
            this.mBorderRadius = 1.0E21f;
            themedReactContext.addLifecycleEventListener(this);
            setHierarchy(GenericDraweeHierarchyBuilder.newInstance(getResources()).setFadeDuration(300).setPlaceholderImage(R.drawable.jdreact_home_icon_default).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY).build());
        }

        public void cleanupAndRemoveListener() {
            ControllerListener controllerListener;
            AbstractDraweeController abstractDraweeController = this.controller;
            if (abstractDraweeController == null || (controllerListener = this.controllerListener) == null) {
                return;
            }
            abstractDraweeController.removeControllerListener(controllerListener);
        }

        public Drawable getDrawable(BitMapCallback bitMapCallback) {
            Drawable drawable = this.drawable;
            if (drawable != null) {
                return drawable;
            }
            this.callback = bitMapCallback;
            return null;
        }

        public void maybeUpdate() {
            GenericDraweeHierarchy hierarchy;
            if (this.mIsDirty && (hierarchy = getHierarchy()) != null) {
                RoundingParams roundingParams = hierarchy.getRoundingParams();
                if (roundingParams == null) {
                    roundingParams = new RoundingParams();
                }
                float f2 = !com.facebook.yoga.YogaConstants.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
                float[] fArr = this.mBorderRadiusArray;
                float f3 = (fArr == null || com.facebook.yoga.YogaConstants.isUndefined(fArr[0])) ? f2 : this.mBorderRadiusArray[0];
                float[] fArr2 = this.mBorderRadiusArray;
                float f4 = (fArr2 == null || com.facebook.yoga.YogaConstants.isUndefined(fArr2[1])) ? f2 : this.mBorderRadiusArray[1];
                float[] fArr3 = this.mBorderRadiusArray;
                float f5 = (fArr3 == null || com.facebook.yoga.YogaConstants.isUndefined(fArr3[2])) ? f2 : this.mBorderRadiusArray[2];
                float[] fArr4 = this.mBorderRadiusArray;
                if (fArr4 != null && !com.facebook.yoga.YogaConstants.isUndefined(fArr4[3])) {
                    f2 = this.mBorderRadiusArray[3];
                }
                roundingParams.setCornersRadii(f3, f4, f5, f2);
                hierarchy.setRoundingParams(roundingParams);
            }
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
        }

        public void setBorderRadius(float f2) {
            if (FloatUtil.floatsEqual(this.mBorderRadius, f2)) {
                return;
            }
            this.mBorderRadius = f2;
            this.mIsDirty = true;
        }

        public void setImageResource(String str, int i2, String str2) {
            ImageRequest build;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            BaseControllerListener<ImageInfo> baseControllerListener = new BaseControllerListener<ImageInfo>() { // from class: com.jingdong.jdreact.plugin.banner.JDCustomImageViewManager.JDReactSimpleDraweeView.1
                {
                    JDReactSimpleDraweeView.this = this;
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onFailure(String str3, Throwable th) {
                    super.onFailure(str3, th);
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onIntermediateImageFailed(String str3, Throwable th) {
                    super.onIntermediateImageFailed(str3, th);
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onFinalImageSet(String str3, @javax.annotation.Nullable ImageInfo imageInfo, @javax.annotation.Nullable Animatable animatable, Drawable drawable) {
                    super.onFinalImageSet(str3, (String) imageInfo, animatable, drawable);
                    JDReactSimpleDraweeView.this.setStaus(true, drawable, imageInfo);
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onIntermediateImageSet(String str3, @javax.annotation.Nullable ImageInfo imageInfo) {
                    super.onIntermediateImageSet(str3, (String) imageInfo);
                }
            };
            Uri parse = Uri.parse(str);
            if (i2 != 0) {
                build = ImageRequestBuilder.newBuilderWithSource(parse).setPostprocessor(new BlurPostprocessor(i2)).build();
            } else {
                build = ImageRequestBuilder.newBuilderWithSource(parse).build();
            }
            ScalingUtils.ScaleType scaleType = ScalingUtils.ScaleType.FIT_XY;
            if (!TextUtils.isEmpty(str2)) {
                if (str2.equals("center_crop")) {
                    scaleType = ScalingUtils.ScaleType.CENTER_CROP;
                } else if (!str2.equals("fit_xy")) {
                    if (str2.equals(DYConstants.DY_CENTER)) {
                        scaleType = ScalingUtils.ScaleType.CENTER;
                    } else if (str2.equals("center_inside")) {
                        scaleType = ScalingUtils.ScaleType.CENTER_INSIDE;
                    } else if (str2.equals("fit_center")) {
                        scaleType = ScalingUtils.ScaleType.FIT_CENTER;
                    } else if (str2.equals("fit_end")) {
                        scaleType = ScalingUtils.ScaleType.FIT_END;
                    } else if (str2.equals("fit_start")) {
                        scaleType = ScalingUtils.ScaleType.FIT_START;
                    } else if (str2.equals("focus_crop")) {
                        scaleType = ScalingUtils.ScaleType.FOCUS_CROP;
                    }
                }
            }
            ((GenericDraweeHierarchy) getHierarchy()).setActualImageScaleType(scaleType);
            try {
                AbstractDraweeController build2 = Fresco.newDraweeControllerBuilder().setOldController(getController()).setImageRequest(build).setControllerListener(baseControllerListener).build();
                this.controller = build2;
                setController(build2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void setStaus(boolean z, Drawable drawable, ImageInfo imageInfo) {
            this.success = z;
            this.drawable = drawable;
            this.imageInfo = imageInfo;
            BitMapCallback bitMapCallback = this.callback;
            if (bitMapCallback != null) {
                bitMapCallback.onResult(drawable);
            }
        }

        public void setBorderRadius(float f2, int i2) {
            if (i2 < 0 || i2 > 3) {
                return;
            }
            if (this.mBorderRadiusArray == null) {
                float[] fArr = new float[4];
                this.mBorderRadiusArray = fArr;
                Arrays.fill(fArr, 1.0E21f);
            }
            if (FloatUtil.floatsEqual(this.mBorderRadiusArray[i2], f2)) {
                return;
            }
            this.mBorderRadiusArray[i2] = f2;
            this.mIsDirty = true;
        }

        public JDReactSimpleDraweeView(Context context) {
            super(context);
            this.success = false;
            this.left = true;
            this.mBorderRadius = 1.0E21f;
        }
    }
}
