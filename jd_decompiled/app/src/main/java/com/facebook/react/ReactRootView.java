package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.common.MeasureSpecProvider;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ReactRootView extends SizeMonitoringFrameLayout implements RootView, MeasureSpecProvider {
    private boolean isCanReport;
    private final ReactAndroidHWInputDeviceHelper mAndroidHWInputDeviceHelper;
    @Nullable
    private Bundle mAppProperties;
    @Nullable
    private CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    private int mHeightMeasureSpec;
    @Nullable
    private String mInitialUITemplate;
    private boolean mIsAttachedToInstance;
    @Nullable
    private String mJSModuleName;
    @Nullable
    private JSTouchDispatcher mJSTouchDispatcher;
    private MtaCallback mMtaCallback;
    @Nullable
    private ReactInstanceManager mReactInstanceManager;
    @Nullable
    private ReactRootViewEventListener mRootViewEventListener;
    private int mRootViewTag;
    private boolean mShouldLogContentAppeared;
    private int mUIManagerType;
    private boolean mWasMeasured;
    private int mWidthMeasureSpec;
    private WindowChangeListener mWindowChangeListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CustomGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final int mMinKeyboardHeightDetected;
        private final Rect mVisibleViewArea;
        private int mKeyboardHeight = 0;
        private int mDeviceRotation = 0;
        private DisplayMetrics mWindowMetrics = new DisplayMetrics();
        private DisplayMetrics mScreenMetrics = new DisplayMetrics();

        CustomGlobalLayoutListener() {
            if (ReactRootView.this.getContext() instanceof Activity) {
                DisplayMetricsHolder.initDisplayMetricsIfNotInitializedForActivity((Activity) ReactRootView.this.getContext());
            } else {
                DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(ReactRootView.this.getContext().getApplicationContext());
            }
            this.mVisibleViewArea = new Rect();
            this.mMinKeyboardHeightDetected = (int) PixelUtil.toPixelFromDIP(60.0f);
        }

        private boolean areMetricsEqual(DisplayMetrics displayMetrics, DisplayMetrics displayMetrics2) {
            if (Build.VERSION.SDK_INT >= 17) {
                return displayMetrics.equals(displayMetrics2);
            }
            return displayMetrics.widthPixels == displayMetrics2.widthPixels && displayMetrics.heightPixels == displayMetrics2.heightPixels && displayMetrics.density == displayMetrics2.density && displayMetrics.densityDpi == displayMetrics2.densityDpi && displayMetrics.scaledDensity == displayMetrics2.scaledDensity && displayMetrics.xdpi == displayMetrics2.xdpi && displayMetrics.ydpi == displayMetrics2.ydpi;
        }

        private void checkForDeviceDimensionsChanges() {
            DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
            int i2 = windowDisplayMetrics.heightPixels;
            String str = "checkForDeviceDimensionsChanges: last width=" + windowDisplayMetrics.widthPixels + ":: height=" + i2;
            if (ReactRootView.this.getContext() instanceof Activity) {
                DisplayMetricsHolder.initDisplayMetricsForActivity((Activity) ReactRootView.this.getContext());
            } else {
                DisplayMetricsHolder.initDisplayMetrics(ReactRootView.this.getContext());
            }
            if (areMetricsEqual(this.mWindowMetrics, DisplayMetricsHolder.getWindowDisplayMetrics()) && areMetricsEqual(this.mScreenMetrics, DisplayMetricsHolder.getScreenDisplayMetrics())) {
                return;
            }
            String str2 = "!areMetricsEqual: mWindowMetrics=" + this.mWindowMetrics;
            String str3 = "!areMetricsEqual: mScreenMetrics=" + this.mScreenMetrics;
            this.mWindowMetrics.setTo(DisplayMetricsHolder.getWindowDisplayMetrics());
            this.mScreenMetrics.setTo(DisplayMetricsHolder.getScreenDisplayMetrics());
            emitUpdateDimensionsEvent();
        }

        private void checkForDeviceOrientationChanges() {
            int rotation = ((WindowManager) ReactRootView.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.mDeviceRotation == rotation) {
                return;
            }
            this.mDeviceRotation = rotation;
            emitOrientationChanged(rotation);
        }

        private void checkForKeyboardEvents() {
            ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
            int i2 = DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - this.mVisibleViewArea.bottom;
            int i3 = this.mKeyboardHeight;
            if (i3 != i2 && i2 > this.mMinKeyboardHeightDetected) {
                this.mKeyboardHeight = i2;
                WritableMap createMap = Arguments.createMap();
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putDouble("screenY", PixelUtil.toDIPFromPixel(this.mVisibleViewArea.bottom));
                createMap2.putDouble("screenX", PixelUtil.toDIPFromPixel(this.mVisibleViewArea.left));
                createMap2.putDouble("width", PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()));
                createMap2.putDouble("height", PixelUtil.toDIPFromPixel(this.mKeyboardHeight));
                createMap.putMap("endCoordinates", createMap2);
                ReactRootView.this.sendEvent("keyboardDidShow", createMap);
            } else if (i3 == 0 || i2 > this.mMinKeyboardHeightDetected) {
            } else {
                this.mKeyboardHeight = 0;
                ReactRootView.this.sendEvent("keyboardDidHide", null);
            }
        }

        private void emitOrientationChanged(int i2) {
            double d;
            String str;
            double d2;
            boolean z = true;
            if (i2 != 0) {
                if (i2 == 1) {
                    d2 = -90.0d;
                    str = "landscape-primary";
                } else if (i2 == 2) {
                    d = 180.0d;
                    str = "portrait-secondary";
                } else if (i2 != 3) {
                    return;
                } else {
                    d2 = 90.0d;
                    str = "landscape-secondary";
                }
                WritableMap createMap = Arguments.createMap();
                createMap.putString("name", str);
                createMap.putDouble("rotationDegrees", d2);
                createMap.putBoolean("isLandscape", z);
                ReactRootView.this.sendEvent("namedOrientationDidChange", createMap);
            }
            d = 0.0d;
            str = "portrait-primary";
            d2 = d;
            z = false;
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putString("name", str);
            createMap2.putDouble("rotationDegrees", d2);
            createMap2.putBoolean("isLandscape", z);
            ReactRootView.this.sendEvent("namedOrientationDidChange", createMap2);
        }

        private void emitUpdateDimensionsEvent() {
            if (ReactRootView.this.mReactInstanceManager == null) {
                return;
            }
            ((DeviceInfoModule) ReactRootView.this.mReactInstanceManager.getCurrentReactContext().getNativeModule(DeviceInfoModule.class)).emitUpdateDimensionsEvent();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ReactRootView.this.mReactInstanceManager == null || !ReactRootView.this.mIsAttachedToInstance || ReactRootView.this.mReactInstanceManager.getCurrentReactContext() == null) {
                return;
            }
            checkForKeyboardEvents();
            checkForDeviceOrientationChanges();
            checkForDeviceDimensionsChanges();
        }
    }

    /* loaded from: classes.dex */
    public interface MtaCallback {
        void setExposureMta(ReadableMap readableMap, String str);
    }

    /* loaded from: classes.dex */
    public interface ReactRootViewEventListener {
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }

    /* loaded from: classes.dex */
    public interface WindowChangeListener {
        void onWindowChange(int i2, int i3);
    }

    public ReactRootView(Context context) {
        super(context);
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mUIManagerType = 1;
        this.isCanReport = false;
        init();
    }

    private void attachToReactInstanceManager() {
        Systrace.beginSection(0L, "attachToReactInstanceManager");
        try {
            if (this.mIsAttachedToInstance) {
                return;
            }
            this.mIsAttachedToInstance = true;
            ((ReactInstanceManager) Assertions.assertNotNull(this.mReactInstanceManager)).attachRootView(this);
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        } finally {
            Systrace.endSection(0L);
        }
    }

    private void dispatchJSTouchEvent(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            if (this.mJSTouchDispatcher == null) {
                FLog.w(ReactConstants.TAG, "Unable to dispatch touch to JS before the dispatcher is available");
                return;
            }
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
            return;
        }
        FLog.w(ReactConstants.TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
    }

    private void enableLayoutCalculation() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null) {
            FLog.w(ReactConstants.TAG, "Unable to enable layout calculation for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
        if (currentReactContext != null) {
            ((UIManagerModule) currentReactContext.getCatalystInstance().getNativeModule(UIManagerModule.class)).getUIImplementation().enableLayoutCalculationForRootNode(getRootViewTag());
        }
    }

    private CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new CustomGlobalLayoutListener();
        }
        return this.mCustomGlobalLayoutListener;
    }

    private void init() {
        setClipChildren(false);
    }

    private void removeOnGlobalLayoutListener() {
        getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
    }

    private void updateRootLayoutSpecs(int i2, int i3) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null) {
            FLog.w(ReactConstants.TAG, "Unable to update root layout specs for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
        if (currentReactContext != null) {
            UIManagerHelper.getUIManager(currentReactContext, getUIManagerType()).updateRootLayoutSpecs(getRootViewTag(), i2, i3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
            if (this.isCanReport) {
                ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
                if (reactInstanceManager != null && this.mJSModuleName != null) {
                    reactInstanceManager.onLoadingFinished(String.valueOf(hashCode()) + this.mJSModuleName.hashCode());
                }
                this.isCanReport = false;
            }
        } catch (StackOverflowError e2) {
            handleException(e2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            this.mAndroidHWInputDeviceHelper.handleKeyEvent(keyEvent);
            return super.dispatchKeyEvent(keyEvent);
        }
        FLog.w(ReactConstants.TAG, "Unable to handle key event as the catalyst instance has not been attached");
        return super.dispatchKeyEvent(keyEvent);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        Assertions.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    @Nullable
    public Bundle getAppProperties() {
        return this.mAppProperties;
    }

    @Override // com.facebook.react.uimanager.common.MeasureSpecProvider
    public int getHeightMeasureSpec() {
        if (!this.mWasMeasured && getLayoutParams() != null && getLayoutParams().height > 0) {
            return View.MeasureSpec.makeMeasureSpec(getLayoutParams().height, 1073741824);
        }
        return this.mHeightMeasureSpec;
    }

    @Nullable
    public String getInitialUITemplate() {
        return this.mInitialUITemplate;
    }

    String getJSModuleName() {
        return (String) Assertions.assertNotNull(this.mJSModuleName);
    }

    @Nullable
    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactInstanceManager;
    }

    public int getRootViewTag() {
        return this.mRootViewTag;
    }

    public int getUIManagerType() {
        return this.mUIManagerType;
    }

    @Override // com.facebook.react.uimanager.common.MeasureSpecProvider
    public int getWidthMeasureSpec() {
        if (!this.mWasMeasured && getLayoutParams() != null && getLayoutParams().width > 0) {
            return View.MeasureSpec.makeMeasureSpec(getLayoutParams().width, 1073741824);
        }
        return this.mWidthMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.RootView
    public void handleException(Throwable th) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && reactInstanceManager.getCurrentReactContext() != null) {
            this.mReactInstanceManager.getCurrentReactContext().handleException(new IllegalViewOperationException(th.getMessage(), this, th));
            return;
        }
        throw new RuntimeException(th);
    }

    public void onAttachedToReactInstance() {
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        ReactRootViewEventListener reactRootViewEventListener = this.mRootViewEventListener;
        if (reactRootViewEventListener != null) {
            reactRootViewEventListener.onAttachedToReactInstance(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            removeOnGlobalLayoutListener();
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    @Override // com.facebook.react.uimanager.RootView
    public void onChildStartedNativeGesture(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            if (this.mJSTouchDispatcher == null) {
                FLog.w(ReactConstants.TAG, "Unable to dispatch touch to JS before the dispatcher is available");
                return;
            }
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
            return;
        }
        FLog.w(ReactConstants.TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            removeOnGlobalLayoutListener();
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            this.mAndroidHWInputDeviceHelper.clearFocus();
            super.onFocusChanged(z, i2, rect);
            return;
        }
        FLog.w(ReactConstants.TAG, "Unable to handle focus changed event as the catalyst instance has not been attached");
        super.onFocusChanged(z, i2, rect);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        dispatchJSTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0057 A[Catch: all -> 0x0098, LOOP:0: B:18:0x0051->B:20:0x0057, LOOP_END, TryCatch #0 {all -> 0x0098, blocks: (B:3:0x0007, B:7:0x0017, B:12:0x0042, B:16:0x004b, B:21:0x0075, B:23:0x007f, B:25:0x0083, B:26:0x008a, B:18:0x0051, B:20:0x0057, B:9:0x001e, B:11:0x0024), top: B:33:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007f A[Catch: all -> 0x0098, TryCatch #0 {all -> 0x0098, blocks: (B:3:0x0007, B:7:0x0017, B:12:0x0042, B:16:0x004b, B:21:0x0075, B:23:0x007f, B:25:0x0083, B:26:0x008a, B:18:0x0051, B:20:0x0057, B:9:0x001e, B:11:0x0024), top: B:33:0x0007 }] */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            java.lang.String r2 = "ReactRootView.onMeasure"
            com.facebook.systrace.Systrace.beginSection(r0, r2)
            r8.mWidthMeasureSpec = r9     // Catch: java.lang.Throwable -> L98
            r8.mHeightMeasureSpec = r10     // Catch: java.lang.Throwable -> L98
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch: java.lang.Throwable -> L98
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 0
            if (r2 == r3) goto L1c
            if (r2 != 0) goto L17
            goto L1c
        L17:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch: java.lang.Throwable -> L98
            goto L42
        L1c:
            r9 = 0
            r2 = 0
        L1e:
            int r5 = r8.getChildCount()     // Catch: java.lang.Throwable -> L98
            if (r2 >= r5) goto L42
            android.view.View r5 = r8.getChildAt(r2)     // Catch: java.lang.Throwable -> L98
            int r6 = r5.getLeft()     // Catch: java.lang.Throwable -> L98
            int r7 = r5.getMeasuredWidth()     // Catch: java.lang.Throwable -> L98
            int r6 = r6 + r7
            int r7 = r5.getPaddingLeft()     // Catch: java.lang.Throwable -> L98
            int r6 = r6 + r7
            int r5 = r5.getPaddingRight()     // Catch: java.lang.Throwable -> L98
            int r6 = r6 + r5
            int r9 = java.lang.Math.max(r9, r6)     // Catch: java.lang.Throwable -> L98
            int r2 = r2 + 1
            goto L1e
        L42:
            int r2 = android.view.View.MeasureSpec.getMode(r10)     // Catch: java.lang.Throwable -> L98
            if (r2 == r3) goto L50
            if (r2 != 0) goto L4b
            goto L50
        L4b:
            int r10 = android.view.View.MeasureSpec.getSize(r10)     // Catch: java.lang.Throwable -> L98
            goto L75
        L50:
            r10 = 0
        L51:
            int r2 = r8.getChildCount()     // Catch: java.lang.Throwable -> L98
            if (r4 >= r2) goto L75
            android.view.View r2 = r8.getChildAt(r4)     // Catch: java.lang.Throwable -> L98
            int r3 = r2.getTop()     // Catch: java.lang.Throwable -> L98
            int r5 = r2.getMeasuredHeight()     // Catch: java.lang.Throwable -> L98
            int r3 = r3 + r5
            int r5 = r2.getPaddingTop()     // Catch: java.lang.Throwable -> L98
            int r3 = r3 + r5
            int r2 = r2.getPaddingBottom()     // Catch: java.lang.Throwable -> L98
            int r3 = r3 + r2
            int r10 = java.lang.Math.max(r10, r3)     // Catch: java.lang.Throwable -> L98
            int r4 = r4 + 1
            goto L51
        L75:
            r8.setMeasuredDimension(r9, r10)     // Catch: java.lang.Throwable -> L98
            r9 = 1
            r8.mWasMeasured = r9     // Catch: java.lang.Throwable -> L98
            com.facebook.react.ReactInstanceManager r9 = r8.mReactInstanceManager     // Catch: java.lang.Throwable -> L98
            if (r9 == 0) goto L8a
            boolean r9 = r8.mIsAttachedToInstance     // Catch: java.lang.Throwable -> L98
            if (r9 != 0) goto L8a
            r8.attachToReactInstanceManager()     // Catch: java.lang.Throwable -> L98
            r8.enableLayoutCalculation()     // Catch: java.lang.Throwable -> L98
            goto L94
        L8a:
            r8.enableLayoutCalculation()     // Catch: java.lang.Throwable -> L98
            int r9 = r8.mWidthMeasureSpec     // Catch: java.lang.Throwable -> L98
            int r10 = r8.mHeightMeasureSpec     // Catch: java.lang.Throwable -> L98
            r8.updateRootLayoutSpecs(r9, r10)     // Catch: java.lang.Throwable -> L98
        L94:
            com.facebook.systrace.Systrace.endSection(r0)
            return
        L98:
            r9 = move-exception
            com.facebook.systrace.Systrace.endSection(r0)
            goto L9e
        L9d:
            throw r9
        L9e:
            goto L9d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactRootView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        dispatchJSTouchEvent(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.mShouldLogContentAppeared) {
            this.mShouldLogContentAppeared = false;
            String str = this.mJSModuleName;
            if (str != null) {
                ReactMarker.logMarker(ReactMarkerConstants.CONTENT_APPEARED, str, this.mRootViewTag);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
            this.mAndroidHWInputDeviceHelper.onFocusChanged(view2);
            super.requestChildFocus(view, view2);
            return;
        }
        FLog.w(ReactConstants.TAG, "Unable to handle child focus changed event as the catalyst instance has not been attached");
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void runApplication() {
        Systrace.beginSection(0L, "ReactRootView.runApplication");
        try {
            ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
            if (reactInstanceManager != null && this.mIsAttachedToInstance) {
                ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                if (currentReactContext == null) {
                    return;
                }
                this.isCanReport = true;
                CatalystInstance catalystInstance = currentReactContext.getCatalystInstance();
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble("rootTag", getRootViewTag());
                Bundle appProperties = getAppProperties();
                if (appProperties != null) {
                    WritableMap fromBundle = Arguments.fromBundle(appProperties);
                    if (!fromBundle.hasKey("rootViewTag")) {
                        fromBundle.putString("rootViewTag", "" + getJSModuleName() + getRootViewTag());
                    }
                    try {
                        writableNativeMap.putMap("initialProps", fromBundle);
                    } catch (Exception unused) {
                    }
                }
                if (getUIManagerType() == 2) {
                    writableNativeMap.putBoolean("fabric", true);
                }
                this.mShouldLogContentAppeared = true;
                ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication(getJSModuleName(), writableNativeMap);
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactInstanceManager.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    public void setAppProperties(@Nullable Bundle bundle) {
        UiThreadUtil.assertOnUiThread();
        this.mAppProperties = bundle;
        if (getRootViewTag() != 0) {
            runApplication();
        }
    }

    public void setEventListener(ReactRootViewEventListener reactRootViewEventListener) {
        this.mRootViewEventListener = reactRootViewEventListener;
    }

    public void setExposureMta() {
        Map map;
        JDReactData.newInstance();
        if (JDReactData.getData() != null) {
            JDReactData.newInstance();
            if (JDReactData.getData().isEmpty() || this.mMtaCallback == null || this.mJSModuleName == null) {
                return;
            }
            String str = "" + getJSModuleName() + getRootViewTag();
            ArrayList<Map> arrayList = new ArrayList();
            int i2 = 0;
            while (true) {
                JDReactData.newInstance();
                if (i2 >= JDReactData.getData().size()) {
                    break;
                }
                JDReactData.newInstance();
                ReadableMap readableMap = JDReactData.getData().get(i2);
                if (readableMap.hasKey("rootView") && readableMap.hasKey("event_id") && readableMap.hasKey("event_param") && readableMap.hasKey("separator") && str.equals(readableMap.getString("rootView"))) {
                    String string = readableMap.getString("event_id");
                    boolean z = false;
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        String str2 = (String) ((Map) arrayList.get(i3)).get("event_id");
                        if (string != null && string.equals(str2) && (map = (Map) arrayList.get(i3)) != null && map.get("event_param") != null && readableMap.getString("separator") != null && readableMap.getString("event_param") != null) {
                            map.put("event_param", ((String) map.get("event_param")) + readableMap.getString("separator") + readableMap.getString("event_param"));
                            z = true;
                        }
                    }
                    if (!z && readableMap.getString("event_id") != null && readableMap.getString("event_param") != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("event_id", readableMap.getString("event_id"));
                        hashMap.put("event_param", readableMap.getString("event_param"));
                        hashMap.put("index", "" + i2);
                        arrayList.add(hashMap);
                    }
                }
                i2++;
            }
            JDReactData.newInstance();
            if (JDReactData.getData() != null) {
                for (Map map2 : arrayList) {
                    MtaCallback mtaCallback = this.mMtaCallback;
                    JDReactData.newInstance();
                    mtaCallback.setExposureMta(JDReactData.getData().get(Integer.parseInt((String) map2.get("index"))), (String) map2.get("event_param"));
                }
            }
            List synchronizedList = Collections.synchronizedList(new ArrayList());
            JDReactData.newInstance();
            synchronizedList.addAll(JDReactData.getData());
            Iterator it = synchronizedList.iterator();
            while (it.hasNext()) {
                ReadableMap readableMap2 = (ReadableMap) it.next();
                if (readableMap2.hasKey("rootView") && str.equals(readableMap2.getString("rootView"))) {
                    it.remove();
                }
            }
            JDReactData.newInstance();
            JDReactData.getData().clear();
            if (synchronizedList != null && !synchronizedList.isEmpty()) {
                JDReactData.newInstance();
                JDReactData.getData().addAll(synchronizedList);
            }
            JDReactData.newInstance();
            if (JDReactData.getData() != null) {
                JDReactData.newInstance();
                if (JDReactData.getData().isEmpty()) {
                    JDReactData.newInstance();
                    JDReactData.cleanAll();
                }
            }
            arrayList.clear();
        }
    }

    public void setIsFabric(boolean z) {
        this.mUIManagerType = z ? 2 : 1;
    }

    public void setMtaCallback(MtaCallback mtaCallback) {
        this.mMtaCallback = mtaCallback;
    }

    public void setRootViewTag(int i2) {
        this.mRootViewTag = i2;
    }

    public void setWindowChangeListener(WindowChangeListener windowChangeListener) {
        this.mWindowChangeListener = windowChangeListener;
    }

    @VisibleForTesting
    void simulateAttachForTesting() {
        this.mIsAttachedToInstance = true;
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str) {
        startReactApplication(reactInstanceManager, str, null);
    }

    public void unmountReactApplication() {
        setExposureMta();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && this.mIsAttachedToInstance) {
            reactInstanceManager.detachRootView(this);
            this.mReactInstanceManager = null;
            this.mIsAttachedToInstance = false;
        }
        this.mShouldLogContentAppeared = false;
    }

    public void updateScreenSize() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || reactInstanceManager.getCurrentReactContext() == null) {
            return;
        }
        DisplayMetricsHolder.setScreenDisplayMetrics(null);
        if (getContext() instanceof Activity) {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitializedForActivity((Activity) getContext());
        } else {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(getContext());
        }
        ((DeviceInfoModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(DeviceInfoModule.class)).emitUpdateDimensionsEvent();
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle) {
        startReactApplication(reactInstanceManager, str, bundle, null);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle, @Nullable String str2) {
        Systrace.beginSection(0L, "startReactApplication");
        try {
            UiThreadUtil.assertOnUiThread();
            Assertions.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
            this.mReactInstanceManager = reactInstanceManager;
            if (str != null) {
                reactInstanceManager.startReactApplication(String.valueOf(hashCode()) + str.hashCode(), str);
            }
            this.mJSModuleName = str;
            this.mAppProperties = bundle;
            this.mInitialUITemplate = str2;
            if (!this.mReactInstanceManager.hasStartedCreatingInitialContext()) {
                this.mReactInstanceManager.createReactContextInBackground();
            }
            attachToReactInstanceManager();
        } finally {
            Systrace.endSection(0L);
        }
    }

    public ReactRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mUIManagerType = 1;
        this.isCanReport = false;
        init();
    }

    public ReactRootView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mUIManagerType = 1;
        this.isCanReport = false;
        init();
    }
}
