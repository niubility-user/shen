package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTView")
/* loaded from: classes12.dex */
public class ReactViewManager extends ViewGroupManager<ReactViewGroup> {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    private static final int CMD_UPDATA = 3;
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3, 4, 5};

    @ReactProp(name = "enableInterceptTouchEvent")
    public void enableInterceptTouchEvent(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.enableInterceptTouchEvent(z);
    }

    @ReactProp(name = "enableZIndex")
    public void enableZIndex(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.enableZindex(z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("hotspotUpdate", 1, "setPressed", 2, "updateLayout", 3);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RCTView";
    }

    @ReactProp(name = "accessible")
    public void setAccessible(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setFocusable(z);
    }

    @ReactProp(name = "backfaceVisibility")
    public void setBackfaceVisibility(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setBackfaceVisibility(str);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", ViewProps.BORDER_LEFT_COLOR, ViewProps.BORDER_RIGHT_COLOR, ViewProps.BORDER_TOP_COLOR, ViewProps.BORDER_BOTTOM_COLOR, ViewProps.BORDER_START_COLOR, ViewProps.BORDER_END_COLOR})
    public void setBorderColor(ReactViewGroup reactViewGroup, int i2, Integer num) {
        reactViewGroup.setBorderColor(SPACING_TYPES[i2], num == null ? Float.NaN : num.intValue() & ViewCompat.MEASURED_SIZE_MASK, num != null ? num.intValue() >>> 24 : Float.NaN);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS, ViewProps.BORDER_TOP_START_RADIUS, ViewProps.BORDER_TOP_END_RADIUS, ViewProps.BORDER_BOTTOM_START_RADIUS, ViewProps.BORDER_BOTTOM_END_RADIUS})
    public void setBorderRadius(ReactViewGroup reactViewGroup, int i2, float f2) {
        if (!YogaConstants.isUndefined(f2) && f2 < 0.0f) {
            f2 = Float.NaN;
        }
        if (!YogaConstants.isUndefined(f2)) {
            f2 = PixelUtil.toPixelFromDIP(f2);
        }
        if (i2 == 0) {
            reactViewGroup.setBorderRadius(f2);
        } else {
            reactViewGroup.setBorderRadius(f2, i2 - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactViewGroup reactViewGroup, @Nullable String str) {
        reactViewGroup.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH, ViewProps.BORDER_START_WIDTH, ViewProps.BORDER_END_WIDTH})
    public void setBorderWidth(ReactViewGroup reactViewGroup, int i2, float f2) {
        if (!YogaConstants.isUndefined(f2) && f2 < 0.0f) {
            f2 = Float.NaN;
        }
        if (!YogaConstants.isUndefined(f2)) {
            f2 = PixelUtil.toPixelFromDIP(f2);
        }
        reactViewGroup.setBorderWidth(SPACING_TYPES[i2], f2);
    }

    @ReactProp(name = ViewProps.COLLAPSABLE)
    public void setCollapsable(ReactViewGroup reactViewGroup, boolean z) {
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(ReactViewGroup reactViewGroup, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            reactViewGroup.setHitSlopRect(null);
        } else {
            reactViewGroup.setHitSlopRect(new Rect(readableMap.hasKey("left") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("left")) : 0, readableMap.hasKey("top") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("top")) : 0, readableMap.hasKey("right") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("right")) : 0, readableMap.hasKey("bottom") ? (int) PixelUtil.toPixelFromDIP(readableMap.getDouble("bottom")) : 0));
        }
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setNativeBackground(ReactViewGroup reactViewGroup, @Nullable ReadableMap readableMap) {
        reactViewGroup.setTranslucentBackgroundDrawable(readableMap == null ? null : ReactDrawableHelper.createDrawableFromJSDescription(reactViewGroup.getContext(), readableMap));
    }

    @ReactProp(name = "nativeForegroundAndroid")
    @TargetApi(23)
    public void setNativeForeground(ReactViewGroup reactViewGroup, @Nullable ReadableMap readableMap) {
        reactViewGroup.setForeground(readableMap == null ? null : ReactDrawableHelper.createDrawableFromJSDescription(reactViewGroup.getContext(), readableMap));
    }

    @ReactProp(name = ViewProps.NEEDS_OFFSCREEN_ALPHA_COMPOSITING)
    public void setNeedsOffscreenAlphaCompositing(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setNeedsOffscreenAlphaCompositing(z);
    }

    @ReactProp(name = "onInterceptTouchEvent")
    public void setOnInterceptTouchEvent(ReactViewGroup reactViewGroup, int i2) {
        reactViewGroup.setOnInterceptTouchEvent(i2);
    }

    @ReactProp(name = ViewProps.OVERFLOW)
    public void setOverflow(ReactViewGroup reactViewGroup, String str) {
        reactViewGroup.setOverflow(str);
    }

    @ReactProp(name = ViewProps.POINTER_EVENTS)
    public void setPointerEvents(ReactViewGroup reactViewGroup, @Nullable String str) {
        if (str == null) {
            reactViewGroup.setPointerEvents(PointerEvents.AUTO);
        } else {
            reactViewGroup.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", CartConstant.KEY_YB_INFO_LINK)));
        }
    }

    @ReactProp(name = ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS)
    public void setRemoveClippedSubviews(ReactViewGroup reactViewGroup, boolean z) {
        reactViewGroup.setRemoveClippedSubviews(z);
    }

    @ReactProp(name = "hasTVPreferredFocus")
    public void setTVPreferredFocus(ReactViewGroup reactViewGroup, boolean z) {
        if (z) {
            reactViewGroup.setFocusable(true);
            reactViewGroup.setFocusableInTouchMode(true);
            reactViewGroup.requestFocus();
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(ReactViewGroup reactViewGroup, View view, int i2) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            reactViewGroup.addViewWithSubviewClippingEnabled(view, i2);
        } else {
            reactViewGroup.addView(view, i2);
        }
        ViewGroupManager.reorderChildrenByZIndex(reactViewGroup);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactViewGroup(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(ReactViewGroup reactViewGroup, int i2) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            return reactViewGroup.getChildAtWithSubviewClippingEnabled(i2);
        }
        return reactViewGroup.getChildAt(i2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(ReactViewGroup reactViewGroup) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            return reactViewGroup.getAllChildrenCount();
        }
        return reactViewGroup.getChildCount();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactViewGroup reactViewGroup, int i2, @Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            if (readableArray != null && readableArray.size() == 2) {
                if (Build.VERSION.SDK_INT >= 21) {
                    reactViewGroup.drawableHotspotChanged(PixelUtil.toPixelFromDIP(readableArray.getDouble(0)), PixelUtil.toPixelFromDIP(readableArray.getDouble(1)));
                    return;
                }
                return;
            }
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
        } else if (i2 == 2) {
            if (readableArray != null && readableArray.size() == 1) {
                reactViewGroup.setPressed(readableArray.getBoolean(0));
                return;
            }
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
        } else if (i2 != 3) {
        } else {
            ViewParent parent = reactViewGroup.getParent();
            if (parent instanceof ReactRootView) {
                ReactRootView reactRootView = (ReactRootView) parent;
                ViewGroup.LayoutParams layoutParams = reactRootView.getLayoutParams();
                if (readableArray == null || readableArray.size() != 2) {
                    return;
                }
                layoutParams.width = readableArray.getInt(0);
                layoutParams.height = readableArray.getInt(1);
                reactRootView.setLayoutParams(layoutParams);
                ((ViewGroup) parent.getParent()).setLayoutParams(layoutParams);
            }
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeAllViews(ReactViewGroup reactViewGroup) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            reactViewGroup.removeAllViewsWithSubviewClippingEnabled();
        } else {
            reactViewGroup.removeAllViews();
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(ReactViewGroup reactViewGroup, int i2) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            View childAt = getChildAt(reactViewGroup, i2);
            if (childAt.getParent() != null) {
                reactViewGroup.removeView(childAt);
            }
            reactViewGroup.removeViewWithSubviewClippingEnabled(childAt);
            return;
        }
        reactViewGroup.removeViewAt(i2);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    public void setOpacity(ReactViewGroup reactViewGroup, float f2) {
        reactViewGroup.setOpacityIfPossible(f2);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    public void setTransform(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        super.setTransform((ReactViewManager) reactViewGroup, readableArray);
        reactViewGroup.setBackfaceVisibilityDependantOpacity();
    }
}
