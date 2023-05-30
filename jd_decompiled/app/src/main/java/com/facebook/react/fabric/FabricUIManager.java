package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.fabric.jsi.Binding;
import com.facebook.react.fabric.jsi.EventBeatManager;
import com.facebook.react.fabric.jsi.EventEmitterWrapper;
import com.facebook.react.fabric.jsi.FabricSoLoader;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.mountitems.BatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.CreateMountItem;
import com.facebook.react.fabric.mounting.mountitems.DeleteMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.InsertMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem;
import com.facebook.react.fabric.mounting.mountitems.RemoveMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateEventEmitterMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateLayoutMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateLocalDataMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePropsMountItem;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ReactRootViewTagGenerator;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.common.MeasureSpecProvider;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.systrace.Systrace;
import com.facebook.yoga.YogaMeasureMode;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes12.dex */
public class FabricUIManager implements UIManager, LifecycleEventListener {
    private static final String TAG = "FabricUIManager";
    private static final Map<String, String> sComponentNames;
    private Binding mBinding;
    @ThreadConfined(ThreadConfined.UI)
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    private final EventBeatManager mEventBeatManager;
    private final EventDispatcher mEventDispatcher;
    private final MountingManager mMountingManager;
    private final ReactApplicationContext mReactApplicationContext;
    private final ConcurrentHashMap<Integer, ThemedReactContext> mReactContextForRootTag = new ConcurrentHashMap<>();
    private final Object mMountItemsLock = new Object();
    private final Object mPreMountItemsLock = new Object();
    @GuardedBy("mMountItemsLock")
    private List<MountItem> mMountItems = new ArrayList();
    @GuardedBy("mPreMountItemsLock")
    private List<MountItem> mPreMountItems = new ArrayList();
    @ThreadConfined(ThreadConfined.UI)
    private boolean mIsMountingEnabled = true;
    private long mRunStartTime = 0;
    private long mBatchedExecutionTime = 0;
    private long mNonBatchedExecutionTime = 0;
    private long mDispatchViewUpdatesTime = 0;
    private long mCommitStartTime = 0;
    private long mLayoutTime = 0;
    private long mFinishTransactionTime = 0;

    /* loaded from: classes12.dex */
    private class DispatchUIFrameCallback extends GuardedFrameCallback {
        @Override // com.facebook.react.fabric.GuardedFrameCallback
        public void doFrameGuarded(long j2) {
            try {
                if (FabricUIManager.this.mIsMountingEnabled) {
                    try {
                        FabricUIManager.this.flushMountItems();
                        return;
                    } catch (Exception e2) {
                        FLog.i(ReactConstants.TAG, "Exception thrown when executing UIFrameGuarded", (Throwable) e2);
                        FabricUIManager.this.mIsMountingEnabled = false;
                        throw e2;
                    }
                }
                FLog.w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
            } finally {
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, FabricUIManager.this.mDispatchUIFrameCallback);
            }
        }

        private DispatchUIFrameCallback(ReactContext reactContext) {
            super(reactContext);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        sComponentNames = hashMap;
        FabricSoLoader.staticInit();
        hashMap.put(TemplateViewBase.ELEM_TYPE_VIEW, "RCTView");
        hashMap.put(TemplateViewBase.ELEM_TYPE_IMAGE, ReactImageManager.REACT_CLASS);
        hashMap.put("ScrollView", ReactScrollViewManager.REACT_CLASS);
        hashMap.put("ReactPerformanceLoggerFlag", "ReactPerformanceLoggerFlag");
        hashMap.put("Paragraph", ReactTextViewManager.REACT_CLASS);
        hashMap.put(TemplateViewBase.ELEM_TYPE_TEXT, "RCText");
        hashMap.put("RawText", ReactRawTextManager.REACT_CLASS);
        hashMap.put("ActivityIndicatorView", ReactProgressBarViewManager.REACT_CLASS);
        hashMap.put("ShimmeringView", "RKShimmeringView");
        hashMap.put("TemplateView", "RCTTemplateView");
    }

    public FabricUIManager(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, EventBeatManager eventBeatManager) {
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        this.mMountingManager = new MountingManager(viewManagerRegistry);
        this.mEventDispatcher = eventDispatcher;
        this.mEventBeatManager = eventBeatManager;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @DoNotStrip
    private MountItem createBatchMountItem(MountItem[] mountItemArr, int i2) {
        return new BatchMountItem(mountItemArr, i2);
    }

    @DoNotStrip
    private MountItem createMountItem(String str, int i2, int i3, boolean z) {
        String str2 = sComponentNames.get(str);
        if (str2 != null) {
            ThemedReactContext themedReactContext = this.mReactContextForRootTag.get(Integer.valueOf(i2));
            if (themedReactContext != null) {
                return new CreateMountItem(themedReactContext, str2, i3, z);
            }
            throw new IllegalArgumentException("Unable to find ReactContext for root: " + i2);
        }
        throw new IllegalArgumentException("Unable to find component with name " + str);
    }

    @DoNotStrip
    private MountItem deleteMountItem(int i2) {
        return new DeleteMountItem(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void flushMountItems() {
        List<MountItem> list;
        List<MountItem> list2;
        if (!this.mIsMountingEnabled) {
            FLog.w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        try {
            synchronized (this.mPreMountItemsLock) {
                list = this.mPreMountItems;
                this.mPreMountItems = new ArrayList();
            }
            this.mRunStartTime = SystemClock.uptimeMillis();
            synchronized (this.mMountItemsLock) {
                list2 = this.mMountItems;
                this.mMountItems = new ArrayList();
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            Systrace.beginSection(0L, "FabricUIManager::premountViews (" + list.size() + " batches)");
            Iterator<MountItem> it = list.iterator();
            while (it.hasNext()) {
                it.next().execute(this.mMountingManager);
            }
            this.mNonBatchedExecutionTime = SystemClock.uptimeMillis() - uptimeMillis;
            Systrace.endSection(0L);
            Systrace.beginSection(0L, "FabricUIManager::mountViews (" + list2.size() + " batches)");
            long uptimeMillis2 = SystemClock.uptimeMillis();
            Iterator<MountItem> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().execute(this.mMountingManager);
            }
            this.mBatchedExecutionTime = SystemClock.uptimeMillis() - uptimeMillis2;
            Systrace.endSection(0L);
        } catch (Exception e2) {
            FLog.e(ReactConstants.TAG, "Exception thrown when executing UIFrameGuarded", e2);
            this.mIsMountingEnabled = false;
            throw e2;
        }
    }

    @DoNotStrip
    private MountItem insertMountItem(int i2, int i3, int i4) {
        return new InsertMountItem(i2, i3, i4);
    }

    @DoNotStrip
    private long measure(String str, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, int i2, int i3, int i4, int i5) {
        MountingManager mountingManager = this.mMountingManager;
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        float f2 = i2;
        float f3 = i3;
        float yogaSize = LayoutMetricsConversions.getYogaSize(f2, f3);
        YogaMeasureMode yogaMeasureMode = LayoutMetricsConversions.getYogaMeasureMode(f2, f3);
        float f4 = i4;
        float f5 = i5;
        return mountingManager.measure(reactApplicationContext, str, readableNativeMap, readableNativeMap2, yogaSize, yogaMeasureMode, LayoutMetricsConversions.getYogaSize(f4, f5), LayoutMetricsConversions.getYogaMeasureMode(f4, f5));
    }

    @DoNotStrip
    private void preallocateView(int i2, String str) {
        if (UiThreadUtil.isOnUiThread()) {
            return;
        }
        synchronized (this.mPreMountItemsLock) {
            String str2 = sComponentNames.get(str);
            Assertions.assertNotNull(str2);
            this.mPreMountItems.add(new PreAllocateViewMountItem((ThemedReactContext) Assertions.assertNotNull(this.mReactContextForRootTag.get(Integer.valueOf(i2))), i2, str2));
        }
    }

    @DoNotStrip
    private MountItem removeMountItem(int i2, int i3, int i4) {
        return new RemoveMountItem(i2, i3, i4);
    }

    @DoNotStrip
    private void scheduleMountItems(MountItem mountItem, long j2, long j3, long j4) {
        this.mCommitStartTime = j2;
        this.mLayoutTime = j3;
        this.mFinishTransactionTime = SystemClock.uptimeMillis() - j4;
        this.mDispatchViewUpdatesTime = SystemClock.uptimeMillis();
        synchronized (this.mMountItemsLock) {
            this.mMountItems.add(mountItem);
        }
        if (UiThreadUtil.isOnUiThread()) {
            flushMountItems();
        }
    }

    @DoNotStrip
    private MountItem updateEventEmitterMountItem(int i2, Object obj) {
        return new UpdateEventEmitterMountItem(i2, (EventEmitterWrapper) obj);
    }

    @DoNotStrip
    private MountItem updateLayoutMountItem(int i2, int i3, int i4, int i5, int i6) {
        return new UpdateLayoutMountItem(i2, i3, i4, i5, i6);
    }

    @DoNotStrip
    private MountItem updateLocalDataMountItem(int i2, ReadableNativeMap readableNativeMap) {
        return new UpdateLocalDataMountItem(i2, readableNativeMap);
    }

    @DoNotStrip
    private MountItem updatePropsMountItem(int i2, ReadableNativeMap readableNativeMap) {
        return new UpdatePropsMountItem(i2, readableNativeMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.bridge.UIManager
    public <T extends SizeMonitoringFrameLayout & MeasureSpecProvider> int addRootView(T t, WritableMap writableMap, @Nullable String str) {
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        ThemedReactContext themedReactContext = new ThemedReactContext(this.mReactApplicationContext, t.getContext());
        this.mMountingManager.addRootView(nextRootViewTag, t);
        this.mReactContextForRootTag.put(Integer.valueOf(nextRootViewTag), themedReactContext);
        this.mBinding.startSurface(nextRootViewTag, (NativeMap) writableMap);
        T t2 = t;
        updateRootLayoutSpecs(nextRootViewTag, t2.getWidthMeasureSpec(), t2.getHeightMeasureSpec());
        if (str != null) {
            this.mBinding.renderTemplateToSurface(nextRootViewTag, str);
        }
        return nextRootViewTag;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void clearJSResponder() {
    }

    @Override // com.facebook.react.bridge.UIManager
    public void dispatchCommand(int i2, int i3, ReadableArray readableArray) {
        synchronized (this.mMountItemsLock) {
            this.mMountItems.add(new DispatchCommandMountItem(i2, i3, readableArray));
        }
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mCommitStartTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mRunStartTime));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.mBatchedExecutionTime));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.mNonBatchedExecutionTime));
        hashMap.put("FinishFabricTransactionTime", Long.valueOf(this.mFinishTransactionTime));
        return hashMap;
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void initialize() {
        this.mEventDispatcher.registerEventEmitter(2, new FabricEventEmitter(this));
        this.mEventDispatcher.addBatchEventDispatchedListener(this.mEventBeatManager);
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void onCatalystInstanceDestroy() {
        this.mEventDispatcher.removeBatchEventDispatchedListener(this.mEventBeatManager);
        this.mEventDispatcher.unregisterEventEmitter(2);
        this.mBinding.unregister();
        ViewManagerPropertyUpdater.clear();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    @DoNotStrip
    public void onRequestEventBeat() {
        this.mEventDispatcher.dispatchAllEvents();
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
    }

    public void receiveEvent(int i2, String str, @Nullable WritableMap writableMap) {
        EventEmitterWrapper eventEmitter = this.mMountingManager.getEventEmitter(i2);
        if (eventEmitter == null) {
            FLog.d(TAG, "Unable to invoke event: " + str + " for reactTag: " + i2);
            return;
        }
        eventEmitter.invoke(str, writableMap);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void removeRootView(int i2) {
        this.mMountingManager.removeRootView(i2);
        this.mReactContextForRootTag.remove(Integer.valueOf(i2));
    }

    public void setBinding(Binding binding) {
        this.mBinding = binding;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void setJSResponder(int i2, boolean z) {
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(final int i2, final int i3, final int i4) {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        reactApplicationContext.runOnJSQueueThread(new GuardedRunnable(reactApplicationContext) { // from class: com.facebook.react.fabric.FabricUIManager.1
            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                FabricUIManager.this.mBinding.setConstraints(i2, LayoutMetricsConversions.getMinSize(i3), LayoutMetricsConversions.getMaxSize(i3), LayoutMetricsConversions.getMinSize(i4), LayoutMetricsConversions.getMaxSize(i4));
            }
        });
    }
}
