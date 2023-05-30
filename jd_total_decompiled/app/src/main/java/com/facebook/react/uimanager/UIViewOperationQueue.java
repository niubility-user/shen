package com.facebook.react.uimanager;

import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.react.animation.Animation;
import com.facebook.react.animation.AnimationRegistry;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes12.dex */
public class UIViewOperationQueue {
    public static final int DEFAULT_MIN_TIME_LEFT_IN_FRAME_FOR_NONBATCHED_OPERATION_MS = 8;
    private final AnimationRegistry mAnimationRegistry;
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    private final NativeViewHierarchyManager mNativeViewHierarchyManager;
    private long mNonBatchedExecutionTotalTime;
    private long mProfiledBatchBatchedExecutionTime;
    private long mProfiledBatchCommitStartTime;
    private long mProfiledBatchDispatchViewUpdatesTime;
    private long mProfiledBatchLayoutTime;
    private long mProfiledBatchNonBatchedExecutionTime;
    private long mProfiledBatchRunStartTime;
    private final ReactApplicationContext mReactApplicationContext;
    private long mThreadCpuTime;
    @Nullable
    private NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener;
    private final int[] mMeasureBuffer = new int[4];
    private final Object mDispatchRunnablesLock = new Object();
    private final Object mNonBatchedOperationsLock = new Object();
    private ArrayList<UIOperation> mOperations = new ArrayList<>();
    @GuardedBy("mDispatchRunnablesLock")
    private ArrayList<Runnable> mDispatchUIRunnables = new ArrayList<>();
    @GuardedBy("mNonBatchedOperationsLock")
    private ArrayDeque<UIOperation> mNonBatchedOperations = new ArrayDeque<>();
    private boolean mIsDispatchUIFrameCallbackEnqueued = false;
    private boolean mIsInIllegalUIState = false;
    private boolean mIsProfilingNextBatch = false;

    /* loaded from: classes12.dex */
    public class AddAnimationOperation extends AnimationOperation {
        private final int mReactTag;
        private final Callback mSuccessCallback;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Animation animation = UIViewOperationQueue.this.mAnimationRegistry.getAnimation(this.mAnimationID);
            if (animation != null) {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.startAnimationForNativeView(this.mReactTag, animation, this.mSuccessCallback);
                return;
            }
            throw new IllegalViewOperationException("Animation with id " + this.mAnimationID + " was not found");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private AddAnimationOperation(int i2, int i3, Callback callback) {
            super(i3);
            UIViewOperationQueue.this = r1;
            this.mReactTag = i2;
            this.mSuccessCallback = callback;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class AnimationOperation implements UIOperation {
        protected final int mAnimationID;

        public AnimationOperation(int i2) {
            this.mAnimationID = i2;
        }
    }

    /* loaded from: classes12.dex */
    public final class ChangeJSResponderOperation extends ViewOperation {
        private final boolean mBlockNativeResponder;
        private final boolean mClearResponder;
        private final int mInitialTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChangeJSResponderOperation(int i2, int i3, boolean z, boolean z2) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mInitialTag = i3;
            this.mClearResponder = z;
            this.mBlockNativeResponder = z2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            if (!this.mClearResponder) {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.setJSResponder(this.mTag, this.mInitialTag, this.mBlockNativeResponder);
            } else {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.clearJSResponder();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class ConfigureLayoutAnimationOperation implements UIOperation {
        private final ReadableMap mConfig;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.configureLayoutAnimation(this.mConfig);
        }

        private ConfigureLayoutAnimationOperation(ReadableMap readableMap) {
            UIViewOperationQueue.this = r1;
            this.mConfig = readableMap;
        }
    }

    /* loaded from: classes12.dex */
    public final class CreateViewOperation extends ViewOperation {
        private final String mClassName;
        @Nullable
        private final ReactStylesDiffMap mInitialProps;
        private final ThemedReactContext mThemedContext;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CreateViewOperation(ThemedReactContext themedReactContext, int i2, String str, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mThemedContext = themedReactContext;
            this.mClassName = str;
            this.mInitialProps = reactStylesDiffMap;
            Systrace.startAsyncFlow(0L, "createView", this.mTag);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Systrace.endAsyncFlow(0L, "createView", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.createView(this.mThemedContext, this.mTag, this.mClassName, this.mInitialProps);
        }
    }

    /* loaded from: classes12.dex */
    public final class DismissPopupMenuOperation implements UIOperation {
        private DismissPopupMenuOperation() {
            UIViewOperationQueue.this = r1;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dismissPopupMenu();
        }
    }

    /* loaded from: classes12.dex */
    public final class DispatchCommandOperation extends ViewOperation {
        @Nullable
        private final ReadableArray mArgs;
        private final int mCommand;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DispatchCommandOperation(int i2, int i3, @Nullable ReadableArray readableArray) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mCommand = i3;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
        }
    }

    /* loaded from: classes12.dex */
    public class DispatchUIFrameCallback extends GuardedFrameCallback {
        private static final int FRAME_TIME_MS = 16;
        private final int mMinTimeLeftInFrameForNonBatchedOperationMs;

        private void dispatchPendingNonBatchedOperations(long j2) {
            UIOperation uIOperation;
            while (16 - ((System.nanoTime() - j2) / 1000000) >= this.mMinTimeLeftInFrameForNonBatchedOperationMs) {
                synchronized (UIViewOperationQueue.this.mNonBatchedOperationsLock) {
                    if (UIViewOperationQueue.this.mNonBatchedOperations.isEmpty()) {
                        return;
                    }
                    uIOperation = (UIOperation) UIViewOperationQueue.this.mNonBatchedOperations.pollFirst();
                }
                try {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    uIOperation.execute();
                    UIViewOperationQueue.access$2914(UIViewOperationQueue.this, SystemClock.uptimeMillis() - uptimeMillis);
                } catch (Exception e2) {
                    UIViewOperationQueue.this.mIsInIllegalUIState = true;
                    throw e2;
                }
            }
        }

        @Override // com.facebook.react.uimanager.GuardedFrameCallback
        public void doFrameGuarded(long j2) {
            if (UIViewOperationQueue.this.mIsInIllegalUIState) {
                FLog.w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            Systrace.beginSection(0L, "dispatchNonBatchedUIOperations");
            try {
                dispatchPendingNonBatchedOperations(j2);
                Systrace.endSection(0L);
                UIViewOperationQueue.this.flushPendingBatches();
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this);
            } catch (Throwable th) {
                Systrace.endSection(0L);
                throw th;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private DispatchUIFrameCallback(ReactContext reactContext, int i2) {
            super(reactContext);
            UIViewOperationQueue.this = r1;
            this.mMinTimeLeftInFrameForNonBatchedOperationMs = i2;
        }
    }

    /* loaded from: classes12.dex */
    private final class EmitOnLayoutEventOperation extends ViewOperation {
        private final int mScreenHeight;
        private final int mScreenWidth;
        private final int mScreenX;
        private final int mScreenY;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EmitOnLayoutEventOperation(int i2, int i3, int i4, int i5, int i6) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mScreenX = i3;
            this.mScreenY = i4;
            this.mScreenWidth = i5;
            this.mScreenHeight = i6;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            ((UIManagerModule) UIViewOperationQueue.this.mReactApplicationContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(OnLayoutEvent.obtain(this.mTag, this.mScreenX, this.mScreenY, this.mScreenWidth, this.mScreenHeight));
        }
    }

    /* loaded from: classes12.dex */
    public final class FindTargetForTouchOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;
        private final float mTargetX;
        private final float mTargetY;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                float f2 = UIViewOperationQueue.this.mMeasureBuffer[0];
                float f3 = UIViewOperationQueue.this.mMeasureBuffer[1];
                int findTargetTagForTouch = UIViewOperationQueue.this.mNativeViewHierarchyManager.findTargetTagForTouch(this.mReactTag, this.mTargetX, this.mTargetY);
                try {
                    UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(findTargetTagForTouch, UIViewOperationQueue.this.mMeasureBuffer);
                    this.mCallback.invoke(Integer.valueOf(findTargetTagForTouch), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0] - f2)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1] - f3)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
                } catch (IllegalViewOperationException unused) {
                    this.mCallback.invoke(new Object[0]);
                }
            } catch (IllegalViewOperationException unused2) {
                this.mCallback.invoke(new Object[0]);
            }
        }

        private FindTargetForTouchOperation(int i2, float f2, float f3, Callback callback) {
            UIViewOperationQueue.this = r1;
            this.mReactTag = i2;
            this.mTargetX = f2;
            this.mTargetY = f3;
            this.mCallback = callback;
        }
    }

    /* loaded from: classes12.dex */
    public final class LayoutUpdateFinishedOperation implements UIOperation {
        private final UIImplementation.LayoutUpdateListener mListener;
        private final ReactShadowNode mNode;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mListener.onLayoutUpdated(this.mNode);
        }

        private LayoutUpdateFinishedOperation(ReactShadowNode reactShadowNode, UIImplementation.LayoutUpdateListener layoutUpdateListener) {
            UIViewOperationQueue.this = r1;
            this.mNode = reactShadowNode;
            this.mListener = layoutUpdateListener;
        }
    }

    /* loaded from: classes12.dex */
    public final class ManageChildrenOperation extends ViewOperation {
        @Nullable
        private final int[] mIndicesToRemove;
        @Nullable
        private final int[] mTagsToDelete;
        @Nullable
        private final ViewAtIndex[] mViewsToAdd;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ManageChildrenOperation(int i2, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mIndicesToRemove = iArr;
            this.mViewsToAdd = viewAtIndexArr;
            this.mTagsToDelete = iArr2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.manageChildren(this.mTag, this.mIndicesToRemove, this.mViewsToAdd, this.mTagsToDelete);
        }
    }

    /* loaded from: classes12.dex */
    public final class MeasureInWindowOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                this.mCallback.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }

        private MeasureInWindowOperation(int i2, Callback callback) {
            UIViewOperationQueue.this = r1;
            this.mReactTag = i2;
            this.mCallback = callback;
        }
    }

    /* loaded from: classes12.dex */
    public final class MeasureOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                this.mCallback.invoke(0, 0, Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1])));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }

        private MeasureOperation(int i2, Callback callback) {
            UIViewOperationQueue.this = r1;
            this.mReactTag = i2;
            this.mCallback = callback;
        }
    }

    /* loaded from: classes12.dex */
    public class RegisterAnimationOperation extends AnimationOperation {
        private final Animation mAnimation;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mAnimationRegistry.registerAnimation(this.mAnimation);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private RegisterAnimationOperation(Animation animation) {
            super(animation.getAnimationID());
            UIViewOperationQueue.this = r1;
            this.mAnimation = animation;
        }
    }

    /* loaded from: classes12.dex */
    public final class RemoveAnimationOperation extends AnimationOperation {
        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Animation animation = UIViewOperationQueue.this.mAnimationRegistry.getAnimation(this.mAnimationID);
            if (animation != null) {
                animation.cancel();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private RemoveAnimationOperation(int i2) {
            super(i2);
            UIViewOperationQueue.this = r1;
        }
    }

    /* loaded from: classes12.dex */
    public final class RemoveRootViewOperation extends ViewOperation {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RemoveRootViewOperation(int i2) {
            super(i2);
            UIViewOperationQueue.this = r1;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.removeRootView(this.mTag);
        }
    }

    /* loaded from: classes12.dex */
    public final class SendAccessibilityEvent extends ViewOperation {
        private final int mEventType;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.sendAccessibilityEvent(this.mTag, this.mEventType);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private SendAccessibilityEvent(int i2, int i3) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mEventType = i3;
        }
    }

    /* loaded from: classes12.dex */
    private final class SetChildrenOperation extends ViewOperation {
        private final ReadableArray mChildrenTags;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SetChildrenOperation(int i2, ReadableArray readableArray) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mChildrenTags = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.setChildren(this.mTag, this.mChildrenTags);
        }
    }

    /* loaded from: classes12.dex */
    public class SetLayoutAnimationEnabledOperation implements UIOperation {
        private final boolean mEnabled;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.setLayoutAnimationEnabled(this.mEnabled);
        }

        private SetLayoutAnimationEnabledOperation(boolean z) {
            UIViewOperationQueue.this = r1;
            this.mEnabled = z;
        }
    }

    /* loaded from: classes12.dex */
    public final class ShowPopupMenuOperation extends ViewOperation {
        private final Callback mError;
        private final ReadableArray mItems;
        private final Callback mSuccess;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ShowPopupMenuOperation(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mItems = readableArray;
            this.mError = callback;
            this.mSuccess = callback2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.showPopupMenu(this.mTag, this.mItems, this.mSuccess, this.mError);
        }
    }

    /* loaded from: classes12.dex */
    public class UIBlockOperation implements UIOperation {
        private final UIBlock mBlock;

        public UIBlockOperation(UIBlock uIBlock) {
            UIViewOperationQueue.this = r1;
            this.mBlock = uIBlock;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mBlock.execute(UIViewOperationQueue.this.mNativeViewHierarchyManager);
        }
    }

    /* loaded from: classes12.dex */
    public interface UIOperation {
        void execute();
    }

    /* loaded from: classes12.dex */
    private final class UpdateInstanceHandleOperation extends ViewOperation {
        private final long mInstanceHandle;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateInstanceHandle(this.mTag, this.mInstanceHandle);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private UpdateInstanceHandleOperation(int i2, long j2) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mInstanceHandle = j2;
        }
    }

    /* loaded from: classes12.dex */
    public final class UpdateLayoutOperation extends ViewOperation {
        private final int mHeight;
        private final int mParentTag;
        private final int mWidth;
        private final int mX;
        private final int mY;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UpdateLayoutOperation(int i2, int i3, int i4, int i5, int i6, int i7) {
            super(i3);
            UIViewOperationQueue.this = r1;
            this.mParentTag = i2;
            this.mX = i4;
            this.mY = i5;
            this.mWidth = i6;
            this.mHeight = i7;
            Systrace.startAsyncFlow(0L, "updateLayout", this.mTag);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Systrace.endAsyncFlow(0L, "updateLayout", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateLayout(this.mParentTag, this.mTag, this.mX, this.mY, this.mWidth, this.mHeight);
        }
    }

    /* loaded from: classes12.dex */
    public final class UpdatePropertiesOperation extends ViewOperation {
        private final ReactStylesDiffMap mProps;

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateProperties(this.mTag, this.mProps);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private UpdatePropertiesOperation(int i2, ReactStylesDiffMap reactStylesDiffMap) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mProps = reactStylesDiffMap;
        }
    }

    /* loaded from: classes12.dex */
    public final class UpdateViewExtraData extends ViewOperation {
        private final Object mExtraData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UpdateViewExtraData(int i2, Object obj) {
            super(i2);
            UIViewOperationQueue.this = r1;
            this.mExtraData = obj;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewExtraData(this.mTag, this.mExtraData);
        }
    }

    /* loaded from: classes12.dex */
    public abstract class ViewOperation implements UIOperation {
        public int mTag;

        public ViewOperation(int i2) {
            UIViewOperationQueue.this = r1;
            this.mTag = i2;
        }
    }

    public UIViewOperationQueue(ReactApplicationContext reactApplicationContext, NativeViewHierarchyManager nativeViewHierarchyManager, int i2) {
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
        this.mAnimationRegistry = nativeViewHierarchyManager.getAnimationRegistry();
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext, i2 == -1 ? 8 : i2);
        this.mReactApplicationContext = reactApplicationContext;
    }

    static /* synthetic */ long access$2914(UIViewOperationQueue uIViewOperationQueue, long j2) {
        long j3 = uIViewOperationQueue.mNonBatchedExecutionTotalTime + j2;
        uIViewOperationQueue.mNonBatchedExecutionTotalTime = j3;
        return j3;
    }

    public void flushPendingBatches() {
        if (this.mIsInIllegalUIState) {
            FLog.w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        synchronized (this.mDispatchRunnablesLock) {
            if (this.mDispatchUIRunnables.isEmpty()) {
                return;
            }
            ArrayList<Runnable> arrayList = this.mDispatchUIRunnables;
            this.mDispatchUIRunnables = new ArrayList<>();
            long uptimeMillis = SystemClock.uptimeMillis();
            Iterator<Runnable> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            if (this.mIsProfilingNextBatch) {
                this.mProfiledBatchBatchedExecutionTime = SystemClock.uptimeMillis() - uptimeMillis;
                this.mProfiledBatchNonBatchedExecutionTime = this.mNonBatchedExecutionTotalTime;
                this.mIsProfilingNextBatch = false;
                Systrace.beginAsyncSection(0L, "batchedExecutionTime", 0, 1000000 * uptimeMillis);
                Systrace.endAsyncSection(0L, "batchedExecutionTime", 0);
            }
            this.mNonBatchedExecutionTotalTime = 0L;
        }
    }

    public void addRootView(int i2, SizeMonitoringFrameLayout sizeMonitoringFrameLayout, ThemedReactContext themedReactContext) {
        this.mNativeViewHierarchyManager.addRootView(i2, sizeMonitoringFrameLayout, themedReactContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public void dispatchViewUpdates(final int i2, final long j2, final long j3) {
        long j4;
        final ArrayList<UIOperation> arrayList;
        SystraceMessage.beginSection(0L, "UIViewOperationQueue.dispatchViewUpdates").arg(JshopConst.JSKEY_BATCH_ID, i2).flush();
        try {
            SystemClock.uptimeMillis();
            SystemClock.currentThreadTimeMillis();
            j4 = 0;
            ArrayDeque<UIOperation> arrayDeque = null;
            if (this.mOperations.isEmpty()) {
                arrayList = null;
            } else {
                ArrayList<UIOperation> arrayList2 = this.mOperations;
                this.mOperations = new ArrayList<>();
                arrayList = arrayList2;
            }
            synchronized (this.mNonBatchedOperationsLock) {
                try {
                    if (!this.mNonBatchedOperations.isEmpty()) {
                        arrayDeque = this.mNonBatchedOperations;
                        this.mNonBatchedOperations = new ArrayDeque<>();
                    }
                    final ArrayDeque<UIOperation> arrayDeque2 = arrayDeque;
                } catch (Throwable th) {
                    th = th;
                }
            }
            NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener = this.mViewHierarchyUpdateDebugListener;
            if (notThreadSafeViewHierarchyUpdateDebugListener != null) {
                notThreadSafeViewHierarchyUpdateDebugListener.onViewHierarchyUpdateEnqueued();
            }
            try {
                Runnable runnable = new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x005d: CONSTRUCTOR (r8v0 'runnable' java.lang.Runnable) = 
                      (r18v0 'this' com.facebook.react.uimanager.UIViewOperationQueue A[IMMUTABLE_TYPE, THIS])
                      (r19v0 'i2' int A[DONT_INLINE])
                      (r4v0 'arrayDeque2' java.util.ArrayDeque<com.facebook.react.uimanager.UIViewOperationQueue$UIOperation> A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v1 'arrayList' java.util.ArrayList<com.facebook.react.uimanager.UIViewOperationQueue$UIOperation> A[DONT_INLINE])
                      (r20v0 'j2' long A[DONT_INLINE])
                      (r22v0 'j3' long A[DONT_INLINE])
                      (r10 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r15 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[Catch: all -> 0x0094, DECLARE_VAR, MD:(com.facebook.react.uimanager.UIViewOperationQueue, int, java.util.ArrayDeque, java.util.ArrayList, long, long, long, long):void (m), TRY_ENTER] (LINE:16) call: com.facebook.react.uimanager.UIViewOperationQueue.1.<init>(com.facebook.react.uimanager.UIViewOperationQueue, int, java.util.ArrayDeque, java.util.ArrayList, long, long, long, long):void type: CONSTRUCTOR in method: com.facebook.react.uimanager.UIViewOperationQueue.dispatchViewUpdates(int, long, long):void, file: classes12.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    r14 = r18
                    r0 = r19
                    java.lang.String r1 = "UIViewOperationQueue.dispatchViewUpdates"
                    r12 = 0
                    com.facebook.systrace.SystraceMessage$Builder r1 = com.facebook.systrace.SystraceMessage.beginSection(r12, r1)
                    java.lang.String r2 = "batchId"
                    com.facebook.systrace.SystraceMessage$Builder r1 = r1.arg(r2, r0)
                    r1.flush()
                    long r10 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> La0
                    long r15 = android.os.SystemClock.currentThreadTimeMillis()     // Catch: java.lang.Throwable -> La0
                    java.util.ArrayList<com.facebook.react.uimanager.UIViewOperationQueue$UIOperation> r1 = r14.mOperations     // Catch: java.lang.Throwable -> La0
                    boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> La0
                    r2 = 0
                    if (r1 != 0) goto L31
                    java.util.ArrayList<com.facebook.react.uimanager.UIViewOperationQueue$UIOperation> r1 = r14.mOperations     // Catch: java.lang.Throwable -> La0
                    java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La0
                    r3.<init>()     // Catch: java.lang.Throwable -> La0
                    r14.mOperations = r3     // Catch: java.lang.Throwable -> La0
                    r5 = r1
                    goto L32
                L31:
                    r5 = r2
                L32:
                    java.lang.Object r1 = r14.mNonBatchedOperationsLock     // Catch: java.lang.Throwable -> La0
                    monitor-enter(r1)     // Catch: java.lang.Throwable -> La0
                    java.util.ArrayDeque<com.facebook.react.uimanager.UIViewOperationQueue$UIOperation> r3 = r14.mNonBatchedOperations     // Catch: java.lang.Throwable -> L98
                    boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L98
                    if (r3 != 0) goto L46
                    java.util.ArrayDeque<com.facebook.react.uimanager.UIViewOperationQueue$UIOperation> r2 = r14.mNonBatchedOperations     // Catch: java.lang.Throwable -> L98
                    java.util.ArrayDeque r3 = new java.util.ArrayDeque     // Catch: java.lang.Throwable -> L98
                    r3.<init>()     // Catch: java.lang.Throwable -> L98
                    r14.mNonBatchedOperations = r3     // Catch: java.lang.Throwable -> L98
                L46:
                    r4 = r2
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L98
                    com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener r1 = r14.mViewHierarchyUpdateDebugListener     // Catch: java.lang.Throwable -> La0
                    if (r1 == 0) goto L4f
                    r1.onViewHierarchyUpdateEnqueued()     // Catch: java.lang.Throwable -> La0
                L4f:
                    com.facebook.react.uimanager.UIViewOperationQueue$1 r8 = new com.facebook.react.uimanager.UIViewOperationQueue$1     // Catch: java.lang.Throwable -> La0
                    r1 = r8
                    r2 = r18
                    r3 = r19
                    r6 = r20
                    r17 = r8
                    r8 = r22
                    r12 = r15
                    r1.<init>()     // Catch: java.lang.Throwable -> L94
                    java.lang.String r1 = "acquiring mDispatchRunnablesLock"
                    r2 = 0
                    com.facebook.systrace.SystraceMessage$Builder r1 = com.facebook.systrace.SystraceMessage.beginSection(r2, r1)     // Catch: java.lang.Throwable -> L9c
                    java.lang.String r4 = "batchId"
                    com.facebook.systrace.SystraceMessage$Builder r0 = r1.arg(r4, r0)     // Catch: java.lang.Throwable -> L9c
                    r0.flush()     // Catch: java.lang.Throwable -> L9c
                    java.lang.Object r1 = r14.mDispatchRunnablesLock     // Catch: java.lang.Throwable -> L9c
                    monitor-enter(r1)     // Catch: java.lang.Throwable -> L9c
                    com.facebook.systrace.Systrace.endSection(r2)     // Catch: java.lang.Throwable -> L91
                    java.util.ArrayList<java.lang.Runnable> r0 = r14.mDispatchUIRunnables     // Catch: java.lang.Throwable -> L91
                    r4 = r17
                    r0.add(r4)     // Catch: java.lang.Throwable -> L91
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L91
                    boolean r0 = r14.mIsDispatchUIFrameCallbackEnqueued     // Catch: java.lang.Throwable -> L9c
                    if (r0 != 0) goto L8d
                    com.facebook.react.uimanager.UIViewOperationQueue$2 r0 = new com.facebook.react.uimanager.UIViewOperationQueue$2     // Catch: java.lang.Throwable -> L9c
                    com.facebook.react.bridge.ReactApplicationContext r1 = r14.mReactApplicationContext     // Catch: java.lang.Throwable -> L9c
                    r0.<init>(r1)     // Catch: java.lang.Throwable -> L9c
                    com.facebook.react.bridge.UiThreadUtil.runOnUiThread(r0)     // Catch: java.lang.Throwable -> L9c
                L8d:
                    com.facebook.systrace.Systrace.endSection(r2)
                    return
                L91:
                    r0 = move-exception
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L91
                    throw r0     // Catch: java.lang.Throwable -> L9c
                L94:
                    r0 = move-exception
                    r2 = 0
                    goto La2
                L98:
                    r0 = move-exception
                    r2 = r12
                L9a:
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L9e
                    throw r0     // Catch: java.lang.Throwable -> L9c
                L9c:
                    r0 = move-exception
                    goto La2
                L9e:
                    r0 = move-exception
                    goto L9a
                La0:
                    r0 = move-exception
                    r2 = r12
                La2:
                    com.facebook.systrace.Systrace.endSection(r2)
                    goto La7
                La6:
                    throw r0
                La7:
                    goto La6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIViewOperationQueue.dispatchViewUpdates(int, long, long):void");
            }

            public void enqueueAddAnimation(int i2, int i3, Callback callback) {
                this.mOperations.add(new AddAnimationOperation(i2, i3, callback));
            }

            public void enqueueClearJSResponder() {
                this.mOperations.add(new ChangeJSResponderOperation(0, 0, true, false));
            }

            public void enqueueConfigureLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
                this.mOperations.add(new ConfigureLayoutAnimationOperation(readableMap));
            }

            public void enqueueCreateView(ThemedReactContext themedReactContext, int i2, String str, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
                synchronized (this.mNonBatchedOperationsLock) {
                    this.mNonBatchedOperations.addLast(new CreateViewOperation(themedReactContext, i2, str, reactStylesDiffMap));
                }
            }

            public void enqueueDismissPopupMenu() {
                this.mOperations.add(new DismissPopupMenuOperation());
            }

            public void enqueueDispatchCommand(int i2, int i3, @Nullable ReadableArray readableArray) {
                this.mOperations.add(new DispatchCommandOperation(i2, i3, readableArray));
            }

            public void enqueueFindTargetForTouch(int i2, float f2, float f3, Callback callback) {
                this.mOperations.add(new FindTargetForTouchOperation(i2, f2, f3, callback));
            }

            public void enqueueLayoutUpdateFinished(ReactShadowNode reactShadowNode, UIImplementation.LayoutUpdateListener layoutUpdateListener) {
                this.mOperations.add(new LayoutUpdateFinishedOperation(reactShadowNode, layoutUpdateListener));
            }

            public void enqueueManageChildren(int i2, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
                this.mOperations.add(new ManageChildrenOperation(i2, iArr, viewAtIndexArr, iArr2));
            }

            public void enqueueMeasure(int i2, Callback callback) {
                this.mOperations.add(new MeasureOperation(i2, callback));
            }

            public void enqueueMeasureInWindow(int i2, Callback callback) {
                this.mOperations.add(new MeasureInWindowOperation(i2, callback));
            }

            public void enqueueOnLayoutEvent(int i2, int i3, int i4, int i5, int i6) {
                this.mOperations.add(new EmitOnLayoutEventOperation(i2, i3, i4, i5, i6));
            }

            public void enqueueRegisterAnimation(Animation animation) {
                this.mOperations.add(new RegisterAnimationOperation(animation));
            }

            public void enqueueRemoveAnimation(int i2) {
                this.mOperations.add(new RemoveAnimationOperation(i2));
            }

            public void enqueueRemoveRootView(int i2) {
                this.mOperations.add(new RemoveRootViewOperation(i2));
            }

            public void enqueueSendAccessibilityEvent(int i2, int i3) {
                this.mOperations.add(new SendAccessibilityEvent(i2, i3));
            }

            public void enqueueSetChildren(int i2, ReadableArray readableArray) {
                this.mOperations.add(new SetChildrenOperation(i2, readableArray));
            }

            public void enqueueSetJSResponder(int i2, int i3, boolean z) {
                this.mOperations.add(new ChangeJSResponderOperation(i2, i3, false, z));
            }

            public void enqueueSetLayoutAnimationEnabled(boolean z) {
                this.mOperations.add(new SetLayoutAnimationEnabledOperation(z));
            }

            public void enqueueShowPopupMenu(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
                this.mOperations.add(new ShowPopupMenuOperation(i2, readableArray, callback, callback2));
            }

            public void enqueueUIBlock(UIBlock uIBlock) {
                this.mOperations.add(new UIBlockOperation(uIBlock));
            }

            protected void enqueueUIOperation(UIOperation uIOperation) {
                SoftAssertions.assertNotNull(uIOperation);
                this.mOperations.add(uIOperation);
            }

            public void enqueueUpdateExtraData(int i2, Object obj) {
                this.mOperations.add(new UpdateViewExtraData(i2, obj));
            }

            public void enqueueUpdateInstanceHandle(int i2, long j2) {
                this.mOperations.add(new UpdateInstanceHandleOperation(i2, j2));
            }

            public void enqueueUpdateLayout(int i2, int i3, int i4, int i5, int i6, int i7) {
                this.mOperations.add(new UpdateLayoutOperation(i2, i3, i4, i5, i6, i7));
            }

            public void enqueueUpdateProperties(int i2, String str, ReactStylesDiffMap reactStylesDiffMap) {
                this.mOperations.add(new UpdatePropertiesOperation(i2, reactStylesDiffMap));
            }

            public NativeViewHierarchyManager getNativeViewHierarchyManager() {
                return this.mNativeViewHierarchyManager;
            }

            public Map<String, Long> getProfiledBatchPerfCounters() {
                HashMap hashMap = new HashMap();
                hashMap.put("CommitStartTime", Long.valueOf(this.mProfiledBatchCommitStartTime));
                hashMap.put("LayoutTime", Long.valueOf(this.mProfiledBatchLayoutTime));
                hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mProfiledBatchDispatchViewUpdatesTime));
                hashMap.put("RunStartTime", Long.valueOf(this.mProfiledBatchRunStartTime));
                hashMap.put("BatchedExecutionTime", Long.valueOf(this.mProfiledBatchBatchedExecutionTime));
                hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.mProfiledBatchNonBatchedExecutionTime));
                hashMap.put("NativeModulesThreadCpuTime", Long.valueOf(this.mThreadCpuTime));
                return hashMap;
            }

            public boolean isEmpty() {
                return this.mOperations.isEmpty();
            }

            public void pauseFrameCallback() {
                this.mIsDispatchUIFrameCallbackEnqueued = false;
                ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
                flushPendingBatches();
            }

            public void prependUIBlock(UIBlock uIBlock) {
                this.mOperations.add(0, new UIBlockOperation(uIBlock));
            }

            public void profileNextBatch() {
                this.mIsProfilingNextBatch = true;
                this.mProfiledBatchCommitStartTime = 0L;
            }

            public void resumeFrameCallback() {
                this.mIsDispatchUIFrameCallbackEnqueued = true;
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
            }

            public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
                this.mViewHierarchyUpdateDebugListener = notThreadSafeViewHierarchyUpdateDebugListener;
            }
        }
