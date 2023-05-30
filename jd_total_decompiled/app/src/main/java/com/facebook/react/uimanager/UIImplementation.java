package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.common.MeasureSpecProvider;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.yoga.YogaDirection;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class UIImplementation {
    protected final EventDispatcher mEventDispatcher;
    private long mLastCalculateLayoutTime;
    @Nullable
    protected LayoutUpdateListener mLayoutUpdateListener;
    private final int[] mMeasureBuffer;
    private final Set<Integer> mMeasuredRootNodes;
    private final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    private final UIViewOperationQueue mOperationsQueue;
    protected final ReactApplicationContext mReactContext;
    protected final ShadowNodeRegistry mShadowNodeRegistry;
    private final ViewManagerRegistry mViewManagers;
    protected Object uiImplementationThreadLock;

    /* loaded from: classes12.dex */
    public interface LayoutUpdateListener {
        void onLayoutUpdated(ReactShadowNode reactShadowNode);
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, UIManagerModule.ViewManagerResolver viewManagerResolver, EventDispatcher eventDispatcher, int i2) {
        this(reactApplicationContext, new ViewManagerRegistry(viewManagerResolver), eventDispatcher, i2);
    }

    private void assertNodeDoesNotNeedCustomLayoutForChildren(ReactShadowNode reactShadowNode) {
        ViewManager viewManager = (ViewManager) Assertions.assertNotNull(this.mViewManagers.get(reactShadowNode.getViewClass()));
        if (viewManager instanceof ViewGroupManager) {
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            if (viewGroupManager == null || !viewGroupManager.needsCustomLayoutForChildren()) {
                return;
            }
            throw new IllegalViewOperationException("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + reactShadowNode.getViewClass() + "). Use measure instead.");
        }
        throw new IllegalViewOperationException("Trying to use view " + reactShadowNode.getViewClass() + " as a parent, but its Manager doesn't extends ViewGroupManager");
    }

    private boolean assertViewExists(int i2, String str) {
        if (this.mShadowNodeRegistry.getNode(i2) == null) {
            FLog.e("assertViewExists", "Unable to execute operation " + str + " on view with tag: " + i2 + ", since the view does not exists");
            return false;
        }
        return true;
    }

    private void dispatchViewUpdatesIfNeeded() {
        if (this.mOperationsQueue.isEmpty()) {
            dispatchViewUpdates(-1);
        }
    }

    private void measureLayoutRelativeToVerifiedAncestor(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int[] iArr) {
        int i2;
        int i3;
        if (reactShadowNode != reactShadowNode2) {
            i2 = Math.round(reactShadowNode.getLayoutX());
            i3 = Math.round(reactShadowNode.getLayoutY());
            for (ReactShadowNode parent = reactShadowNode.getParent(); parent != reactShadowNode2; parent = parent.getParent()) {
                Assertions.assertNotNull(parent);
                assertNodeDoesNotNeedCustomLayoutForChildren(parent);
                i2 += Math.round(parent.getLayoutX());
                i3 += Math.round(parent.getLayoutY());
            }
            assertNodeDoesNotNeedCustomLayoutForChildren(reactShadowNode2);
        } else {
            i2 = 0;
            i3 = 0;
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = reactShadowNode.getScreenWidth();
        iArr[3] = reactShadowNode.getScreenHeight();
    }

    private void notifyOnBeforeLayoutRecursive(ReactShadowNode reactShadowNode) {
        if (reactShadowNode == null || !reactShadowNode.hasUpdates()) {
            return;
        }
        for (int i2 = 0; i2 < reactShadowNode.getChildCount(); i2++) {
            notifyOnBeforeLayoutRecursive(reactShadowNode.getChildAt(i2));
        }
        reactShadowNode.onBeforeLayout();
    }

    private void removeShadowNodeRecursive(ReactShadowNode reactShadowNode) {
        NativeViewHierarchyOptimizer.handleRemoveNode(reactShadowNode);
        this.mShadowNodeRegistry.removeNode(reactShadowNode.getReactTag());
        this.mMeasuredRootNodes.remove(Integer.valueOf(reactShadowNode.getReactTag()));
        for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
            removeShadowNodeRecursive(reactShadowNode.getChildAt(childCount));
        }
        reactShadowNode.removeAndDisposeAllChildren();
    }

    public void addAnimation(int i2, int i3, Callback callback) {
        if (assertViewExists(i2, "addAnimation")) {
            this.mOperationsQueue.enqueueAddAnimation(i2, i3, callback);
        }
    }

    public void addUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.enqueueUIBlock(uIBlock);
    }

    protected void applyUpdatesRecursive(ReactShadowNode reactShadowNode, float f2, float f3) {
        if (reactShadowNode == null || !reactShadowNode.hasUpdates()) {
            return;
        }
        if (!reactShadowNode.isVirtualAnchor()) {
            for (int i2 = 0; i2 < reactShadowNode.getChildCount(); i2++) {
                applyUpdatesRecursive(reactShadowNode.getChildAt(i2), reactShadowNode.getLayoutX() + f2, reactShadowNode.getLayoutY() + f3);
            }
        }
        int reactTag = reactShadowNode.getReactTag();
        if (!this.mShadowNodeRegistry.isRootNode(reactTag) && reactShadowNode.dispatchUpdates(f2, f3, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer) && reactShadowNode.shouldNotifyOnLayout()) {
            this.mEventDispatcher.dispatchEvent(OnLayoutEvent.obtain(reactTag, reactShadowNode.getScreenX(), reactShadowNode.getScreenY(), reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight()));
        }
        reactShadowNode.markUpdateSeen();
    }

    protected void calculateRootLayout(ReactShadowNode reactShadowNode) {
        if (reactShadowNode == null || !reactShadowNode.hasUpdates()) {
            return;
        }
        SystraceMessage.beginSection(0L, "cssRoot.calculateLayout").arg("rootTag", reactShadowNode.getReactTag()).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            reactShadowNode.calculateLayout();
        } finally {
            Systrace.endSection(0L);
            this.mLastCalculateLayoutTime = SystemClock.uptimeMillis() - uptimeMillis;
        }
    }

    public void clearJSResponder() {
        this.mOperationsQueue.enqueueClearJSResponder();
    }

    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.mOperationsQueue.enqueueConfigureLayoutAnimation(readableMap, callback, callback2);
    }

    protected ReactShadowNode createRootShadowNode() {
        ReactShadowNodeImpl reactShadowNodeImpl = new ReactShadowNodeImpl();
        if (I18nUtil.getInstance().isRTL(this.mReactContext)) {
            reactShadowNodeImpl.setLayoutDirection(YogaDirection.RTL);
        }
        reactShadowNodeImpl.setViewClassName("Root");
        return reactShadowNodeImpl;
    }

    protected ReactShadowNode createShadowNode(String str) {
        return this.mViewManagers.get(str).createShadowNodeInstance(this.mReactContext);
    }

    public void createView(int i2, String str, int i3, ReadableMap readableMap) {
        synchronized (this.uiImplementationThreadLock) {
            ReactShadowNode createShadowNode = createShadowNode(str);
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i3);
            if (node == null) {
                return;
            }
            createShadowNode.setReactTag(i2);
            createShadowNode.setViewClassName(str);
            createShadowNode.setRootTag(node.getReactTag());
            createShadowNode.setThemedContext(node.getThemedContext());
            this.mShadowNodeRegistry.addNode(createShadowNode);
            ReactStylesDiffMap reactStylesDiffMap = null;
            if (readableMap != null) {
                reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                createShadowNode.updateProperties(reactStylesDiffMap);
            }
            handleCreateView(createShadowNode, i3, reactStylesDiffMap);
        }
    }

    public void dismissPopupMenu() {
        this.mOperationsQueue.enqueueDismissPopupMenu();
    }

    public void dispatchViewManagerCommand(int i2, int i3, @Nullable ReadableArray readableArray) {
        if (assertViewExists(i2, "dispatchViewManagerCommand")) {
            this.mOperationsQueue.enqueueDispatchCommand(i2, i3, readableArray);
        }
    }

    public void dispatchViewUpdates(int i2) {
        SystraceMessage.beginSection(0L, "UIImplementation.dispatchViewUpdates").arg(JshopConst.JSKEY_BATCH_ID, i2).flush();
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            updateViewHierarchy();
            this.mNativeViewHierarchyOptimizer.onBatchComplete();
            this.mOperationsQueue.dispatchViewUpdates(i2, uptimeMillis, this.mLastCalculateLayoutTime);
        } finally {
            Systrace.endSection(0L);
        }
    }

    public void enableLayoutCalculationForRootNode(int i2) {
        this.mMeasuredRootNodes.add(Integer.valueOf(i2));
    }

    public void findSubviewIn(int i2, float f2, float f3, Callback callback) {
        this.mOperationsQueue.enqueueFindTargetForTouch(i2, f2, f3, callback);
    }

    public Map<String, Long> getProfiledBatchPerfCounters() {
        return this.mOperationsQueue.getProfiledBatchPerfCounters();
    }

    public UIViewOperationQueue getUIViewOperationQueue() {
        return this.mOperationsQueue;
    }

    protected void handleCreateView(ReactShadowNode reactShadowNode, int i2, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        if (reactShadowNode.isVirtual()) {
            return;
        }
        this.mNativeViewHierarchyOptimizer.handleCreateView(reactShadowNode, reactShadowNode.getThemedContext(), reactStylesDiffMap);
    }

    protected void handleUpdateView(ReactShadowNode reactShadowNode, String str, ReactStylesDiffMap reactStylesDiffMap) {
        if (reactShadowNode.isVirtual()) {
            return;
        }
        this.mNativeViewHierarchyOptimizer.handleUpdateView(reactShadowNode, str, reactStylesDiffMap);
    }

    /* JADX WARN: Code restructure failed: missing block: B:205:0x0048, code lost:
        if (r25 == null) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x004e, code lost:
        if (r11 != r25.size()) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0058, code lost:
        throw new com.facebook.react.uimanager.IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void manageChildren(int i2, @Nullable ReadableArray readableArray, @Nullable ReadableArray readableArray2, @Nullable ReadableArray readableArray3, @Nullable ReadableArray readableArray4, @Nullable ReadableArray readableArray5) {
        ReadableArray readableArray6 = readableArray;
        synchronized (this.uiImplementationThreadLock) {
            try {
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
                if (node == null) {
                    return;
                }
                int size = readableArray6 == null ? 0 : readableArray.size();
                int size2 = readableArray3 == null ? 0 : readableArray3.size();
                int size3 = readableArray5 == null ? 0 : readableArray5.size();
                if (size != 0 && (readableArray2 == null || size != readableArray2.size())) {
                    throw new IllegalViewOperationException("Size of moveFrom != size of moveTo!");
                }
                int i3 = size + size2;
                ViewAtIndex[] viewAtIndexArr = new ViewAtIndex[i3];
                int i4 = size + size3;
                int[] iArr = new int[i4];
                try {
                    int[] iArr2 = new int[i4];
                    int i5 = i3;
                    int[] iArr3 = new int[size3];
                    if (size > 0) {
                        Assertions.assertNotNull(readableArray);
                        Assertions.assertNotNull(readableArray2);
                        int i6 = 0;
                        while (i6 < size) {
                            int i7 = i4;
                            int i8 = readableArray6.getInt(i6);
                            int reactTag = node.getChildAt(i8).getReactTag();
                            viewAtIndexArr[i6] = new ViewAtIndex(reactTag, readableArray2.getInt(i6));
                            iArr[i6] = i8;
                            iArr2[i6] = reactTag;
                            i6++;
                            readableArray6 = readableArray;
                            i4 = i7;
                            iArr3 = iArr3;
                            node = node;
                        }
                    }
                    ReactShadowNode reactShadowNode = node;
                    int[] iArr4 = iArr3;
                    int i9 = i4;
                    if (size2 > 0) {
                        Assertions.assertNotNull(readableArray3);
                        Assertions.assertNotNull(readableArray4);
                        for (int i10 = 0; i10 < size2; i10++) {
                            viewAtIndexArr[size + i10] = new ViewAtIndex(readableArray3.getInt(i10), readableArray4.getInt(i10));
                        }
                    }
                    if (size3 > 0) {
                        Assertions.assertNotNull(readableArray5);
                        int i11 = 0;
                        while (i11 < size3) {
                            int i12 = readableArray5.getInt(i11);
                            ReactShadowNode reactShadowNode2 = reactShadowNode;
                            int reactTag2 = reactShadowNode2.getChildAt(i12).getReactTag();
                            int i13 = size + i11;
                            iArr[i13] = i12;
                            iArr2[i13] = reactTag2;
                            iArr4[i11] = reactTag2;
                            i11++;
                            reactShadowNode = reactShadowNode2;
                        }
                    }
                    ReactShadowNode reactShadowNode3 = reactShadowNode;
                    Arrays.sort(viewAtIndexArr, ViewAtIndex.COMPARATOR);
                    Arrays.sort(iArr);
                    int i14 = -1;
                    for (int i15 = i9 - 1; i15 >= 0; i15--) {
                        if (iArr[i15] != i14) {
                            reactShadowNode3.removeChildAt(iArr[i15]);
                            i14 = iArr[i15];
                        } else {
                            throw new IllegalViewOperationException("Repeated indices in Removal list for view tag: " + i2);
                        }
                    }
                    int i16 = 0;
                    while (true) {
                        int i17 = i5;
                        if (i16 < i17) {
                            ViewAtIndex viewAtIndex = viewAtIndexArr[i16];
                            int[] iArr5 = iArr2;
                            ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(viewAtIndex.mTag);
                            if (node2 != null) {
                                reactShadowNode3.addChildAt(node2, viewAtIndex.mIndex);
                                i16++;
                                iArr2 = iArr5;
                                i5 = i17;
                            } else {
                                throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag);
                            }
                        } else {
                            int[] iArr6 = iArr2;
                            if (!reactShadowNode3.isVirtual() && !reactShadowNode3.isVirtualAnchor()) {
                                this.mNativeViewHierarchyOptimizer.handleManageChildren(reactShadowNode3, iArr, iArr6, viewAtIndexArr, iArr4);
                            }
                            for (int i18 = 0; i18 < size3; i18++) {
                                removeShadowNode(this.mShadowNodeRegistry.getNode(iArr4[i18]));
                            }
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public void measure(int i2, Callback callback) {
        this.mOperationsQueue.enqueueMeasure(i2, callback);
    }

    public void measureInWindow(int i2, Callback callback) {
        this.mOperationsQueue.enqueueMeasureInWindow(i2, callback);
    }

    public void measureLayout(int i2, int i3, Callback callback, Callback callback2) {
        try {
            measureLayout(i2, i3, this.mMeasureBuffer);
            callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3])));
        } catch (IllegalViewOperationException e2) {
            callback.invoke(e2.getMessage());
        }
    }

    public void measureLayoutRelativeToParent(int i2, Callback callback, Callback callback2) {
        try {
            measureLayoutRelativeToParent(i2, this.mMeasureBuffer);
            callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(this.mMeasureBuffer[3])));
        } catch (IllegalViewOperationException e2) {
            callback.invoke(e2.getMessage());
        }
    }

    public void onHostDestroy() {
    }

    public void onHostPause() {
        this.mOperationsQueue.pauseFrameCallback();
    }

    public void onHostResume() {
        this.mOperationsQueue.resumeFrameCallback();
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mOperationsQueue.prependUIBlock(uIBlock);
    }

    public void profileNextBatch() {
        this.mOperationsQueue.profileNextBatch();
    }

    public void registerAnimation(Animation animation) {
        this.mOperationsQueue.enqueueRegisterAnimation(animation);
    }

    public <T extends SizeMonitoringFrameLayout & MeasureSpecProvider> void registerRootView(T t, int i2, ThemedReactContext themedReactContext) {
        synchronized (this.uiImplementationThreadLock) {
            final ReactShadowNode createRootShadowNode = createRootShadowNode();
            createRootShadowNode.setReactTag(i2);
            createRootShadowNode.setThemedContext(themedReactContext);
            updateRootView(createRootShadowNode, t.getWidthMeasureSpec(), t.getHeightMeasureSpec());
            themedReactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.UIImplementation.1
                {
                    UIImplementation.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    UIImplementation.this.mShadowNodeRegistry.addRootNode(createRootShadowNode);
                }
            });
            this.mOperationsQueue.addRootView(i2, t, themedReactContext);
        }
    }

    public void removeAnimation(int i2, int i3) {
        if (assertViewExists(i2, "removeAnimation")) {
            this.mOperationsQueue.enqueueRemoveAnimation(i3);
        }
    }

    public void removeLayoutUpdateListener() {
        this.mLayoutUpdateListener = null;
    }

    public void removeRootShadowNode(int i2) {
        synchronized (this.uiImplementationThreadLock) {
            this.mShadowNodeRegistry.removeRootNode(i2);
        }
    }

    public void removeRootView(int i2) {
        removeRootShadowNode(i2);
        this.mOperationsQueue.enqueueRemoveRootView(i2);
    }

    protected final void removeShadowNode(ReactShadowNode reactShadowNode) {
        removeShadowNodeRecursive(reactShadowNode);
        reactShadowNode.dispose();
    }

    public void removeSubviewsFromContainerWithID(int i2) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        if (node != null) {
            WritableArray createArray = Arguments.createArray();
            for (int i3 = 0; i3 < node.getChildCount(); i3++) {
                createArray.pushInt(i3);
            }
            manageChildren(i2, null, null, null, null, createArray);
            return;
        }
        throw new IllegalViewOperationException("Trying to remove subviews of an unknown view tag: " + i2);
    }

    public void replaceExistingNonRootView(int i2, int i3) {
        if (!this.mShadowNodeRegistry.isRootNode(i2) && !this.mShadowNodeRegistry.isRootNode(i3)) {
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
            if (node != null) {
                ReactShadowNode parent = node.getParent();
                if (parent != null) {
                    int indexOf = parent.indexOf(node);
                    if (indexOf >= 0) {
                        WritableArray createArray = Arguments.createArray();
                        createArray.pushInt(i3);
                        WritableArray createArray2 = Arguments.createArray();
                        createArray2.pushInt(indexOf);
                        WritableArray createArray3 = Arguments.createArray();
                        createArray3.pushInt(indexOf);
                        manageChildren(parent.getReactTag(), null, null, createArray, createArray2, createArray3);
                        return;
                    }
                    throw new IllegalStateException("Didn't find child tag in parent");
                }
                throw new IllegalViewOperationException("Node is not attached to a parent: " + i2);
            }
            throw new IllegalViewOperationException("Trying to replace unknown view tag: " + i2);
        }
        throw new IllegalViewOperationException("Trying to add or replace a root tag!");
    }

    public int resolveRootTagFromReactTag(int i2) {
        if (this.mShadowNodeRegistry.isRootNode(i2)) {
            return i2;
        }
        ReactShadowNode resolveShadowNode = resolveShadowNode(i2);
        if (resolveShadowNode != null) {
            return resolveShadowNode.getRootTag();
        }
        FLog.w(ReactConstants.TAG, "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + i2);
        return 0;
    }

    public final ReactShadowNode resolveShadowNode(int i2) {
        return this.mShadowNodeRegistry.getNode(i2);
    }

    public final ViewManager resolveViewManager(String str) {
        return this.mViewManagers.get(str);
    }

    public void sendAccessibilityEvent(int i2, int i3) {
        this.mOperationsQueue.enqueueSendAccessibilityEvent(i2, i3);
    }

    public void setChildren(int i2, ReadableArray readableArray) {
        synchronized (this.uiImplementationThreadLock) {
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
            if (node == null) {
                return;
            }
            for (int i3 = 0; i3 < readableArray.size(); i3++) {
                ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(readableArray.getInt(i3));
                if (node2 != null) {
                    node.addChildAt(node2, i3);
                }
            }
            if (!node.isVirtual() && !node.isVirtualAnchor()) {
                this.mNativeViewHierarchyOptimizer.handleSetChildren(node, readableArray);
            }
        }
    }

    public void setJSResponder(int i2, boolean z) {
        ReactShadowNode node;
        if (!assertViewExists(i2, "setJSResponder") || (node = this.mShadowNodeRegistry.getNode(i2)) == null) {
            return;
        }
        while (true) {
            if (!node.isVirtual() && !node.isLayoutOnly()) {
                this.mOperationsQueue.enqueueSetJSResponder(node.getReactTag(), i2, z);
                return;
            }
            node = node.getParent();
        }
    }

    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mOperationsQueue.enqueueSetLayoutAnimationEnabled(z);
    }

    public void setLayoutUpdateListener(LayoutUpdateListener layoutUpdateListener) {
        this.mLayoutUpdateListener = layoutUpdateListener;
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mOperationsQueue.setViewHierarchyUpdateDebugListener(notThreadSafeViewHierarchyUpdateDebugListener);
    }

    public void setViewLocalData(int i2, Object obj) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Attempt to set local data for view with unknown tag: " + i2);
            return;
        }
        node.setLocalData(obj);
        dispatchViewUpdatesIfNeeded();
    }

    public void showPopupMenu(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
        if (assertViewExists(i2, "showPopupMenu")) {
            this.mOperationsQueue.enqueueShowPopupMenu(i2, readableArray, callback, callback2);
        }
    }

    public void synchronouslyUpdateViewOnUIThread(int i2, ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        this.mOperationsQueue.getNativeViewHierarchyManager().updateProperties(i2, reactStylesDiffMap);
    }

    public void updateNodeSize(int i2, int i3, int i4) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update size of non-existent tag: " + i2);
            return;
        }
        node.setStyleWidth(i3);
        node.setStyleHeight(i4);
        dispatchViewUpdatesIfNeeded();
    }

    public void updateRootView(int i2, int i3, int i4) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        if (node == null) {
            FLog.w(ReactConstants.TAG, "Tried to update non-existent root tag: " + i2);
            return;
        }
        updateRootView(node, i3, i4);
    }

    public void updateView(int i2, String str, ReadableMap readableMap) {
        if (this.mViewManagers.get(str) != null) {
            ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
            if (node == null) {
                throw new IllegalViewOperationException("Trying to update non-existent view with tag " + i2);
            } else if (readableMap != null) {
                ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                node.updateProperties(reactStylesDiffMap);
                handleUpdateView(node, str, reactStylesDiffMap);
                return;
            } else {
                return;
            }
        }
        throw new IllegalViewOperationException("Got unknown view type: " + str);
    }

    protected void updateViewHierarchy() {
        Systrace.beginSection(0L, "UIImplementation.updateViewHierarchy");
        for (int i2 = 0; i2 < this.mShadowNodeRegistry.getRootNodeCount(); i2++) {
            try {
                int rootTag = this.mShadowNodeRegistry.getRootTag(i2);
                ReactShadowNode node = this.mShadowNodeRegistry.getNode(rootTag);
                if (this.mMeasuredRootNodes.contains(Integer.valueOf(rootTag))) {
                    SystraceMessage.beginSection(0L, "UIImplementation.notifyOnBeforeLayoutRecursive").arg("rootTag", node.getReactTag()).flush();
                    notifyOnBeforeLayoutRecursive(node);
                    Systrace.endSection(0L);
                    calculateRootLayout(node);
                    SystraceMessage.beginSection(0L, "UIImplementation.applyUpdatesRecursive").arg("rootTag", node.getReactTag()).flush();
                    applyUpdatesRecursive(node, 0.0f, 0.0f);
                    Systrace.endSection(0L);
                    LayoutUpdateListener layoutUpdateListener = this.mLayoutUpdateListener;
                    if (layoutUpdateListener != null) {
                        this.mOperationsQueue.enqueueLayoutUpdateFinished(node, layoutUpdateListener);
                    }
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    }

    public void viewIsDescendantOf(int i2, int i3, Callback callback) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(i3);
        if (node == null || node2 == null) {
            callback.invoke(Boolean.FALSE);
        } else {
            callback.invoke(Boolean.valueOf(node.isDescendantOf(node2)));
        }
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, List<ViewManager> list, EventDispatcher eventDispatcher, int i2) {
        this(reactApplicationContext, new ViewManagerRegistry(list), eventDispatcher, i2);
    }

    public UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int i2) {
        this(reactApplicationContext, viewManagerRegistry, new UIViewOperationQueue(reactApplicationContext, new NativeViewHierarchyManager(viewManagerRegistry, reactApplicationContext), i2), eventDispatcher);
    }

    protected UIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, UIViewOperationQueue uIViewOperationQueue, EventDispatcher eventDispatcher) {
        this.uiImplementationThreadLock = new Object();
        ShadowNodeRegistry shadowNodeRegistry = new ShadowNodeRegistry();
        this.mShadowNodeRegistry = shadowNodeRegistry;
        this.mMeasuredRootNodes = new HashSet();
        this.mMeasureBuffer = new int[4];
        this.mLastCalculateLayoutTime = 0L;
        this.mReactContext = reactApplicationContext;
        this.mViewManagers = viewManagerRegistry;
        this.mOperationsQueue = uIViewOperationQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(uIViewOperationQueue, shadowNodeRegistry);
        this.mEventDispatcher = eventDispatcher;
    }

    public void updateRootView(ReactShadowNode reactShadowNode, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            reactShadowNode.setStyleMaxWidth(size);
        } else if (mode == 0) {
            reactShadowNode.setStyleWidthAuto();
        } else if (mode == 1073741824) {
            reactShadowNode.setStyleWidth(size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            reactShadowNode.setStyleMaxHeight(size2);
        } else if (mode2 == 0) {
            reactShadowNode.setStyleHeightAuto();
        } else if (mode2 != 1073741824) {
        } else {
            reactShadowNode.setStyleHeight(size2);
        }
    }

    private void measureLayout(int i2, int i3, int[] iArr) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        ReactShadowNode node2 = this.mShadowNodeRegistry.getNode(i3);
        if (node != null && node2 != null) {
            if (node != node2) {
                for (ReactShadowNode parent = node.getParent(); parent != node2; parent = parent.getParent()) {
                    if (parent == null) {
                        throw new IllegalViewOperationException("Tag " + i3 + " is not an ancestor of tag " + i2);
                    }
                }
            }
            measureLayoutRelativeToVerifiedAncestor(node, node2, iArr);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Tag ");
        if (node != null) {
            i2 = i3;
        }
        sb.append(i2);
        sb.append(" does not exist");
        throw new IllegalViewOperationException(sb.toString());
    }

    private void measureLayoutRelativeToParent(int i2, int[] iArr) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(i2);
        if (node != null) {
            ReactShadowNode parent = node.getParent();
            if (parent != null) {
                measureLayoutRelativeToVerifiedAncestor(node, parent, iArr);
                return;
            }
            throw new IllegalViewOperationException("View with tag " + i2 + " doesn't have a parent!");
        }
        throw new IllegalViewOperationException("No native view for tag " + i2 + " exists!");
    }
}
