package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.facebook.react.R;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.AccessibilityDelegateUtil;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import com.jd.dynamic.DYConstants;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C> {
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_HINT = "accessibilityHint";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_ACCESSIBILITY_ROLE = "accessibilityRole";
    private static final String PROP_ACCESSIBILITY_STATES = "accessibilityStates";
    private static final String PROP_BACKGROUND_COLOR = "backgroundColor";
    private static final String PROP_ELEVATION = "elevation";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    public static final String PROP_NATIVE_ID = "nativeID";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_ROTATION = "rotation";
    private static final String PROP_SCALE_X = "scaleX";
    private static final String PROP_SCALE_Y = "scaleY";
    public static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private static final String PROP_TRANSLATE_X = "translateX";
    private static final String PROP_TRANSLATE_Y = "translateY";
    private static final String PROP_Z_INDEX = "zIndex";
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = (float) Math.sqrt(5.0d);
    private static MatrixMathHelper.MatrixDecompositionContext sMatrixDecompositionContext = new MatrixMathHelper.MatrixDecompositionContext();
    private static double[] sTransformDecompositionArray = new double[16];

    private static void resetTransformProperty(@Nonnull View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
        view.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    private static void setTransformProperty(@Nonnull View view, ReadableArray readableArray) {
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        MatrixMathHelper.decomposeMatrix(sTransformDecompositionArray, sMatrixDecompositionContext);
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[1]));
        view.setRotation((float) sMatrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float) sMatrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float) sMatrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float) sMatrixDecompositionContext.scale[0]);
        view.setScaleY((float) sMatrixDecompositionContext.scale[1]);
        double[] dArr = sMatrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f2 = (float) dArr[2];
            if (f2 == 0.0f) {
                f2 = 7.8125E-4f;
            }
            float f3 = (-1.0f) / f2;
            float f4 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            view.setCameraDistance(f4 * f4 * f3 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    private void updateViewAccessibility(@Nonnull T t) {
        AccessibilityDelegateUtil.setDelegate(t);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(@Nonnull T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
    }

    @ReactProp(name = PROP_ACCESSIBILITY_COMPONENT_TYPE)
    public void setAccessibilityComponentType(@Nonnull T t, String str) {
        AccessibilityHelper.updateAccessibilityComponentType(t, str);
    }

    @ReactProp(name = PROP_ACCESSIBILITY_HINT)
    public void setAccessibilityHint(@Nonnull T t, String str) {
        t.setTag(R.id.accessibility_hint, str);
    }

    @ReactProp(name = PROP_ACCESSIBILITY_LABEL)
    public void setAccessibilityLabel(@Nonnull T t, String str) {
        t.setContentDescription(str);
    }

    @ReactProp(name = PROP_ACCESSIBILITY_LIVE_REGION)
    public void setAccessibilityLiveRegion(@Nonnull T t, @Nullable String str) {
        if (str != null && !str.equals("none")) {
            if (str.equals("polite")) {
                ViewCompat.setAccessibilityLiveRegion(t, 1);
                return;
            } else if (str.equals("assertive")) {
                ViewCompat.setAccessibilityLiveRegion(t, 2);
                return;
            } else {
                return;
            }
        }
        ViewCompat.setAccessibilityLiveRegion(t, 0);
    }

    @ReactProp(name = PROP_ACCESSIBILITY_ROLE)
    public void setAccessibilityRole(@Nonnull T t, @Nullable String str) {
        if (str == null) {
            return;
        }
        t.setTag(R.id.accessibility_role, AccessibilityDelegateUtil.AccessibilityRole.fromValue(str));
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(@Nonnull T t, int i2) {
        t.setBackgroundColor(i2);
    }

    @ReactProp(name = PROP_ELEVATION)
    public void setElevation(@Nonnull T t, float f2) {
        ViewCompat.setElevation(t, PixelUtil.toPixelFromDIP(f2));
    }

    @ReactProp(name = PROP_IMPORTANT_FOR_ACCESSIBILITY)
    public void setImportantForAccessibility(@Nonnull T t, @Nullable String str) {
        if (str != null && !str.equals("auto")) {
            if (str.equals("yes")) {
                ViewCompat.setImportantForAccessibility(t, 1);
                return;
            } else if (str.equals("no")) {
                ViewCompat.setImportantForAccessibility(t, 2);
                return;
            } else if (str.equals("no-hide-descendants")) {
                ViewCompat.setImportantForAccessibility(t, 4);
                return;
            } else {
                return;
            }
        }
        ViewCompat.setImportantForAccessibility(t, 0);
    }

    @ReactProp(name = PROP_NATIVE_ID)
    public void setNativeId(@Nonnull T t, String str) {
        t.setTag(R.id.view_tag_native_id, str);
        ReactFindViewUtil.notifyViewRendered(t);
    }

    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(@Nonnull T t, float f2) {
        t.setAlpha(f2);
    }

    @ReactProp(name = PROP_RENDER_TO_HARDWARE_TEXTURE)
    public void setRenderToHardwareTexture(@Nonnull T t, boolean z) {
        t.setLayerType(z ? 2 : 0, null);
    }

    @ReactProp(name = PROP_ROTATION)
    @Deprecated
    public void setRotation(@Nonnull T t, float f2) {
        t.setRotation(f2);
    }

    @ReactProp(defaultFloat = 1.0f, name = PROP_SCALE_X)
    @Deprecated
    public void setScaleX(@Nonnull T t, float f2) {
        t.setScaleX(f2);
    }

    @ReactProp(defaultFloat = 1.0f, name = PROP_SCALE_Y)
    @Deprecated
    public void setScaleY(@Nonnull T t, float f2) {
        t.setScaleY(f2);
    }

    @ReactProp(name = PROP_TEST_ID)
    public void setTestId(@Nonnull T t, String str) {
        t.setTag(R.id.react_test_id, str);
        t.setTag(str);
    }

    @ReactProp(name = PROP_TRANSFORM)
    public void setTransform(@Nonnull T t, @Nullable ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
        } else {
            setTransformProperty(t, readableArray);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = PROP_TRANSLATE_X)
    @Deprecated
    public void setTranslateX(@Nonnull T t, float f2) {
        t.setTranslationX(PixelUtil.toPixelFromDIP(f2));
    }

    @ReactProp(defaultFloat = 0.0f, name = PROP_TRANSLATE_Y)
    @Deprecated
    public void setTranslateY(@Nonnull T t, float f2) {
        t.setTranslationY(PixelUtil.toPixelFromDIP(f2));
    }

    @ReactProp(name = PROP_ACCESSIBILITY_STATES)
    public void setViewStates(@Nonnull T t, @Nullable ReadableArray readableArray) {
        t.setSelected(false);
        t.setEnabled(true);
        if (readableArray == null) {
            return;
        }
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            String string = readableArray.getString(i2);
            if (string.equals(DYConstants.DY_SELECTED)) {
                t.setSelected(true);
            } else if (string.equals("disabled")) {
                t.setEnabled(false);
            }
        }
    }

    @ReactProp(name = PROP_Z_INDEX)
    public void setZIndex(@Nonnull T t, float f2) {
        ViewGroupManager.setViewZIndex(t, Math.round(f2));
        ViewParent parent = t.getParent();
        if (parent == null || !(parent instanceof ReactZIndexedViewGroup)) {
            return;
        }
        ((ReactZIndexedViewGroup) parent).updateDrawingOrder();
    }
}
