package com.facebook.react.fabric.mounting;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.fabric.jsi.EventEmitterWrapper;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.yoga.YogaMeasureMode;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public class MountingManager {
    private final RootViewManager mRootViewManager = new RootViewManager();
    private final ConcurrentHashMap<Integer, ViewState> mTagToViewState = new ConcurrentHashMap<>();
    private final ViewManagerRegistry mViewManagerRegistry;
    private final ContextBasedViewPool mViewPool;

    public MountingManager(ViewManagerRegistry viewManagerRegistry) {
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mViewPool = new ContextBasedViewPool(viewManagerRegistry);
    }

    @UiThread
    private void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        int id = view.getId();
        ViewState viewState = getViewState(id);
        ViewManager viewManager = viewState.mViewManager;
        if (!viewState.mIsRoot && viewManager != null) {
            viewManager.onDropViewInstance(view);
        }
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(viewState);
            for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                if (this.mTagToViewState.get(Integer.valueOf(childAt.getId())) != null) {
                    dropView(childAt);
                }
                viewGroupManager.removeViewAt(viewGroup, childCount);
            }
        }
        this.mTagToViewState.remove(Integer.valueOf(id));
        this.mViewPool.returnToPool((ThemedReactContext) view.getContext(), ((ViewManager) Assertions.assertNotNull(viewManager)).getName(), view);
    }

    private static ViewGroupManager<ViewGroup> getViewGroupManager(ViewState viewState) {
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager != null) {
            return (ViewGroupManager) viewManager;
        }
        throw new IllegalStateException("Unable to find ViewManager");
    }

    private ViewState getViewState(int i2) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i2));
        if (viewState != null) {
            return viewState;
        }
        throw new IllegalStateException("Unable to find viewState for tag " + i2);
    }

    @UiThread
    public void addRootView(int i2, SizeMonitoringFrameLayout sizeMonitoringFrameLayout) {
        if (sizeMonitoringFrameLayout.getId() == -1) {
            this.mTagToViewState.put(Integer.valueOf(i2), new ViewState(i2, sizeMonitoringFrameLayout, this.mRootViewManager, true));
            sizeMonitoringFrameLayout.setId(i2);
            return;
        }
        throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
    }

    @UiThread
    public void addViewAt(int i2, int i3, int i4) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        ViewGroup viewGroup = (ViewGroup) viewState.mView;
        View view = getViewState(i3).mView;
        if (view != null) {
            getViewGroupManager(viewState).addView(viewGroup, view, i4);
            return;
        }
        throw new IllegalStateException("Unable to find view for tag " + i3);
    }

    @UiThread
    public void createView(ThemedReactContext themedReactContext, String str, int i2, boolean z) {
        View view;
        ViewManager viewManager;
        UiThreadUtil.assertOnUiThread();
        if (z) {
            view = null;
            viewManager = null;
        } else {
            viewManager = this.mViewManagerRegistry.get(str);
            view = this.mViewPool.getOrCreateView(str, themedReactContext);
            view.setId(i2);
        }
        this.mTagToViewState.put(Integer.valueOf(i2), new ViewState(i2, view, viewManager));
    }

    @UiThread
    public void deleteView(int i2) {
        UiThreadUtil.assertOnUiThread();
        View view = getViewState(i2).mView;
        if (view != null) {
            dropView(view);
        } else {
            this.mTagToViewState.remove(Integer.valueOf(i2));
        }
    }

    @Nullable
    @AnyThread
    public EventEmitterWrapper getEventEmitter(int i2) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i2));
        if (viewState == null) {
            return null;
        }
        return viewState.mEventEmitter;
    }

    @AnyThread
    public long measure(ReactContext reactContext, String str, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
        return this.mViewManagerRegistry.get(str).measure(reactContext, readableNativeMap, readableNativeMap2, f2, yogaMeasureMode, f3, yogaMeasureMode2);
    }

    @UiThread
    public void preallocateView(ThemedReactContext themedReactContext, String str) {
        this.mViewPool.createView(themedReactContext, str);
    }

    public void receiveCommand(int i2, int i3, @Nullable ReadableArray readableArray) {
        ViewState viewState = getViewState(i2);
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager != null) {
            View view = viewState.mView;
            if (view != null) {
                viewManager.receiveCommand(view, i3, readableArray);
                return;
            }
            throw new IllegalStateException("Unable to find viewState view for tag " + i2);
        }
        throw new IllegalStateException("Unable to find viewState manager for tag " + i2);
    }

    @UiThread
    public void removeRootView(int i2) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i2));
        if (viewState == null || !viewState.mIsRoot) {
            SoftAssertions.assertUnreachable("View with tag " + i2 + " is not registered as a root view");
        }
        View view = viewState.mView;
        if (view != null) {
            dropView(view);
        }
    }

    @UiThread
    public void removeViewAt(int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        ViewGroup viewGroup = (ViewGroup) viewState.mView;
        if (viewGroup != null) {
            getViewGroupManager(viewState).removeViewAt(viewGroup, i3);
            return;
        }
        throw new IllegalStateException("Unable to find view for tag " + i2);
    }

    @UiThread
    public void updateEventEmitter(int i2, EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        getViewState(i2).mEventEmitter = eventEmitterWrapper;
    }

    @UiThread
    public void updateLayout(int i2, int i3, int i4, int i5, int i6) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
            ViewParent parent = view.getParent();
            if (parent instanceof RootView) {
                parent.requestLayout();
            }
            view.layout(i3, i4, i5 + i3, i6 + i4);
            return;
        }
        throw new IllegalStateException("Unable to find View for tag: " + i2);
    }

    @UiThread
    public void updateLocalData(int i2, ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        if (viewState.mCurrentProps != null) {
            if (viewState.mCurrentLocalData != null && readableMap.hasKey("hash") && viewState.mCurrentLocalData.getDouble("hash") == readableMap.getDouble("hash") && viewState.mCurrentLocalData.toString().equals(readableMap.toString())) {
                return;
            }
            viewState.mCurrentLocalData = readableMap;
            ViewManager viewManager = viewState.mViewManager;
            if (viewManager != null) {
                Object updateLocalData = viewManager.updateLocalData(viewState.mView, viewState.mCurrentProps, new ReactStylesDiffMap(readableMap));
                if (updateLocalData != null) {
                    viewManager.updateExtraData(viewState.mView, updateLocalData);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Unable to find ViewManager for tag: " + i2);
        }
        throw new IllegalStateException("Can not update local data to view without props: " + i2);
    }

    @UiThread
    public void updateProps(int i2, ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState viewState = getViewState(i2);
        viewState.mCurrentProps = new ReactStylesDiffMap(readableMap);
        View view = viewState.mView;
        if (view != null) {
            ((ViewManager) Assertions.assertNotNull(viewState.mViewManager)).updateProperties(view, viewState.mCurrentProps);
            return;
        }
        throw new IllegalStateException("Unable to find view for tag " + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ViewState {
        public ReadableMap mCurrentLocalData;
        public ReactStylesDiffMap mCurrentProps;
        public EventEmitterWrapper mEventEmitter;
        final boolean mIsRoot;
        final int mReactTag;
        @Nullable
        final View mView;
        @Nullable
        final ViewManager mViewManager;

        private ViewState(int i2, @Nullable View view, @Nullable ViewManager viewManager) {
            this(i2, view, viewManager, false);
        }

        private ViewState(int i2, @Nullable View view, ViewManager viewManager, boolean z) {
            this.mReactTag = i2;
            this.mView = view;
            this.mIsRoot = z;
            this.mViewManager = viewManager;
        }
    }
}
