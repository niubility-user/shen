package com.facebook.react.views.image;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaConstants;
import com.jd.dynamic.DYConstants;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = ReactImageManager.REACT_CLASS)
/* loaded from: classes12.dex */
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    public static final int COMMAND_START = 1;
    public static final int COMMAND_STOP = 2;
    public static final String REACT_CLASS = "RCTImageView";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    @Nullable
    private GlobalImageLoadListener mGlobalImageLoadListener;

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this(abstractDraweeControllerBuilder, null, obj);
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("start", 1, "stop", 2);
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ImageLoadEvent.eventNameForType(4), MapBuilder.of("registrationName", "onLoadStart")).put(ImageLoadEvent.eventNameForType(2), MapBuilder.of("registrationName", "onLoad")).put(ImageLoadEvent.eventNameForType(1), MapBuilder.of("registrationName", "onError")).put(ImageLoadEvent.eventNameForType(3), MapBuilder.of("registrationName", "onLoadEnd")).put(ImageLoadEvent.eventNameForType(6), MapBuilder.of("registrationName", "onAnimationStart")).put(ImageLoadEvent.eventNameForType(7), MapBuilder.of("registrationName", "onAnimationEnd")).put(ImageLoadEvent.eventNameForType(8), MapBuilder.of("registrationName", "onAnimationRepeat")).put(ImageLoadEvent.eventNameForType(9), MapBuilder.of("registrationName", "onAnimationGetDuration")).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "bitmapConfig")
    public void setBitmapConfig(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setBitmapConfig(str);
    }

    @ReactProp(name = "blurRadius")
    public void setBlurRadius(ReactImageView reactImageView, float f2) {
        reactImageView.setBlurRadius(f2);
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(ReactImageView reactImageView, @Nullable Integer num) {
        if (num == null) {
            reactImageView.setBorderColor(0);
        } else {
            reactImageView.setBorderColor(num.intValue());
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public void setBorderRadius(ReactImageView reactImageView, int i2, float f2) {
        if (!YogaConstants.isUndefined(f2)) {
            f2 = PixelUtil.toPixelFromDIP(f2);
        }
        if (i2 == 0) {
            reactImageView.setBorderRadius(f2);
        } else {
            reactImageView.setBorderRadius(f2, i2 - 1);
        }
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(ReactImageView reactImageView, float f2) {
        reactImageView.setBorderWidth(f2);
    }

    @ReactProp(name = "defaultSrc")
    public void setDefaultSource(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setDefaultSource(str);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(ReactImageView reactImageView, int i2) {
        reactImageView.setFadeDuration(i2);
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReactImageView reactImageView, ReadableMap readableMap) {
        reactImageView.setHeaders(readableMap);
    }

    @ReactProp(name = "imageGrey")
    public void setImageGrey(ReactImageView reactImageView, float f2) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(f2);
        reactImageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactImageView reactImageView, boolean z) {
        reactImageView.setShouldNotifyLoadEvents(z);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setLoadingIndicatorSource(str);
    }

    @ReactProp(name = "loopCount")
    public void setLoopCount(ReactImageView reactImageView, int i2) {
        reactImageView.setLoopCount(i2);
    }

    @ReactProp(customType = "Color", name = "overlayColor")
    public void setOverlayColor(ReactImageView reactImageView, @Nullable Integer num) {
        if (num == null) {
            reactImageView.setOverlayColor(0);
        } else {
            reactImageView.setOverlayColor(num.intValue());
        }
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(ReactImageView reactImageView, @Nullable ReadableMap readableMap) {
        reactImageView.setPlaceholder(readableMap);
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactImageView reactImageView, boolean z) {
        reactImageView.setProgressiveRenderingEnabled(z);
    }

    @ReactProp(name = ViewProps.RESIZE_METHOD)
    public void setResizeMethod(ReactImageView reactImageView, @Nullable String str) {
        if (str != null && !"auto".equals(str)) {
            if ("resize".equals(str)) {
                reactImageView.setResizeMethod(ImageResizeMethod.RESIZE);
                return;
            } else if ("scale".equals(str)) {
                reactImageView.setResizeMethod(ImageResizeMethod.SCALE);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid resize method: '" + str + "'");
            }
        }
        reactImageView.setResizeMethod(ImageResizeMethod.AUTO);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(ReactImageView reactImageView, @Nullable String str) {
        reactImageView.setScaleType(ImageResizeMode.toScaleType(str));
        reactImageView.setTileMode(ImageResizeMode.toTileMode(str));
    }

    @ReactProp(name = "src")
    public void setSource(ReactImageView reactImageView, @Nullable ReadableArray readableArray) {
        reactImageView.setSource(readableArray);
    }

    @ReactProp(customType = "Color", name = DYConstants.DY_IMAGE_TINT_COLOR)
    public void setTintColor(ReactImageView reactImageView, @Nullable Integer num) {
        if (num == null) {
            reactImageView.clearColorFilter();
        } else {
            reactImageView.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContext = obj;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactImageView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactImageView(themedReactContext, getDraweeControllerBuilder(), this.mGlobalImageLoadListener, getCallerContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactImageView reactImageView) {
        super.onAfterUpdateTransaction((ReactImageManager) reactImageView);
        reactImageView.maybeUpdateView();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactImageView reactImageView, int i2, @Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            reactImageView.start();
        } else if (i2 != 2) {
        } else {
            reactImageView.stop();
        }
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
    }
}
