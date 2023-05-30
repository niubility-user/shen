package com.jingdong.sdk.platform.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.floor.FloorManagerProxy;
import com.jingdong.sdk.platform.utils.OnViewHolderHideListener;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class BaseViewHolder<K extends BaseData, V extends BaseEntity, VM extends BaseViewModel, N extends BaseNavigator> extends LifecycleBaseViewHolder<VM, N> implements ViewHolder<K, V> {
    protected String TAG;
    private boolean isAttachToWindow;
    private boolean isValid;
    private ViewGroup mEmptyView;
    protected K mFloorData;
    protected V mFloorEntity;
    protected String mId;
    protected boolean mIsDestroy;
    protected boolean mIsInit;
    protected int mLayoutId;
    private Object mLockObject;
    protected ViewGroup mRootView;
    protected List<Runnable> mRunnablesExcuteOnAttachToWindow;
    private OnViewHolderHideListener mViewHolderHideListener;

    public BaseViewHolder(Context context, K k2, String str, ViewGroup viewGroup) {
        super(context, k2.mManageKey);
        int i2;
        this.TAG = "BaseViewHolder";
        this.mIsDestroy = false;
        this.isAttachToWindow = false;
        this.isValid = false;
        this.mIsInit = false;
        this.mLockObject = new Object();
        this.mContext = context;
        this.mId = str;
        this.mFloorData = k2;
        initData();
        try {
            ViewGroup onCreatedView = onCreatedView(viewGroup);
            this.mRootView = onCreatedView;
            if (onCreatedView == null) {
                onCreatedView();
                if (this.mRootView == null && (i2 = this.mLayoutId) != 0) {
                    this.mRootView = (ViewGroup) PlatformTools.inflate(context, i2, viewGroup, false);
                }
            }
            if (this.mRootView != null) {
                if (isUsedLayoutParams()) {
                    ViewGroup.LayoutParams layoutParams = this.mRootView.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams.width = -1;
                        if (layoutParams.height == -1) {
                            layoutParams.height = -2;
                        }
                        this.mRootView.setLayoutParams(layoutParams);
                    }
                } else {
                    this.mRootView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                }
            }
            initView();
            this.mIsInit = true;
        } catch (Error e2) {
            PlatformTools.catchExceptionAndReportToBugly(e2);
            onInflateFailed(e2);
        } catch (Exception e3) {
            PlatformTools.catchExceptionAndReportToBugly(e3);
            onInflateFailed(e3);
        }
        if (!TextUtils.isEmpty(getActionName())) {
            SyncEventBus.getInstances(this.mFloorData.mManageKey).register(this);
        }
        onViewCreated();
    }

    private void addRunnableToList(Runnable runnable) {
        synchronized (this.mLockObject) {
            if (this.mRunnablesExcuteOnAttachToWindow == null) {
                this.mRunnablesExcuteOnAttachToWindow = new ArrayList(2);
            }
            this.mRunnablesExcuteOnAttachToWindow.add(runnable);
        }
    }

    private void clearRunnables() {
        synchronized (this.mLockObject) {
            List<Runnable> list = this.mRunnablesExcuteOnAttachToWindow;
            if (list != null) {
                list.clear();
            }
            this.mRunnablesExcuteOnAttachToWindow = null;
        }
    }

    private void excuteRunnablesOnAttachToWindow() {
        synchronized (this.mLockObject) {
            List<Runnable> list = this.mRunnablesExcuteOnAttachToWindow;
            if (list != null && !list.isEmpty()) {
                for (Runnable runnable : this.mRunnablesExcuteOnAttachToWindow) {
                    if (runnable != null) {
                        post(runnable, 0L);
                    }
                }
                this.mRunnablesExcuteOnAttachToWindow.clear();
            }
        }
    }

    private synchronized void setAttachToWindow(boolean z) {
        this.isAttachToWindow = z;
    }

    public boolean canSetBg() {
        return true;
    }

    public void disable() {
        this.isValid = false;
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public <T extends View> T findViewById(int i2) {
        return (T) this.mRootView.findViewById(i2);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder, com.jingdong.sdk.aac.base.EventListener
    public String getActionName() {
        return null;
    }

    public int getBackGroundColor() {
        return 0;
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public K getBaseData() {
        return this.mFloorData;
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public V getBaseEntity() {
        return this.mFloorEntity;
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public Object getData(String str) {
        return null;
    }

    protected int getFloorHeigt() {
        return 0;
    }

    public Resources getResources() {
        return this.mContext.getResources();
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public View getView() {
        if (!this.mIsInit) {
            if (this.mEmptyView == null) {
                this.mEmptyView = new LinearLayout(this.mContext);
                this.mEmptyView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            }
            this.mRootView = this.mEmptyView;
        }
        this.mRootView.setClickable(true);
        return this.mRootView;
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public void hideViewHolder() {
        OnViewHolderHideListener onViewHolderHideListener = this.mViewHolderHideListener;
        if (onViewHolderHideListener != null) {
            onViewHolderHideListener.hideViewHolder();
        }
    }

    public void initData() {
    }

    protected abstract void initView();

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public boolean isAttachToWindow() {
        return this.isAttachToWindow;
    }

    public boolean isLine() {
        if (this.mFloorEntity != null) {
            return FloorManagerProxy.getInstances(getBaseData().mMoudleName).isLine(this.mFloorEntity.mId);
        }
        return false;
    }

    public boolean isSameEntity(BaseEntity baseEntity) {
        return baseEntity == this.mFloorEntity;
    }

    protected boolean isUsedLayoutParams() {
        return true;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public boolean isValid() {
        return this.isValid;
    }

    public void onAttachToWindow() {
        setAttachToWindow(true);
        if (PlatformTools.D) {
            PlatformTools.d(this.TAG, "88888888onAttachToWindow floorType = " + this.mId);
        }
        resetHeight(getFloorHeigt());
        excuteRunnablesOnAttachToWindow();
    }

    protected ViewGroup onCreatedView(ViewGroup viewGroup) {
        return null;
    }

    protected abstract void onCreatedView();

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public boolean onDataShowed(V v) {
        return onDataShowed(v, null);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onDestroy() {
        this.mIsDestroy = true;
        if (PlatformTools.D) {
            PlatformTools.d(this.TAG, "onViewDestroy = " + this.mId + " _ ");
        }
        onDestroyView();
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.setTag(null);
        }
        this.mRootView = null;
        this.mFloorData = null;
        this.mFloorEntity = null;
        clearRunnables();
        super.onDestroy();
    }

    protected abstract void onDestroyView();

    public void onDetachFromWindow() {
        setAttachToWindow(false);
    }

    public void onEdgeChange(boolean z) {
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder, com.jingdong.sdk.aac.base.EventListener
    public void onEvent(String str, Object obj) {
    }

    public void onInflateFailed(Throwable th) {
    }

    public void onRecycle() {
        onDestroy();
    }

    public void onShowFailed(Throwable th) {
    }

    public void onViewCreated() {
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public void post(Runnable runnable) {
        SyncEventBus.postToMainThread(runnable);
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public void resetHeight(int i2) {
        ViewGroup.LayoutParams layoutParams;
        if (this.mRootView == null || !isValid() || i2 <= 0 || (layoutParams = this.mRootView.getLayoutParams()) == null || layoutParams.height == i2) {
            return;
        }
        if (PlatformTools.D) {
            PlatformTools.d(this.TAG, "resetHeight to  = " + i2);
        }
        layoutParams.height = i2;
        layoutParams.width = -1;
        this.mRootView.setLayoutParams(layoutParams);
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public void runOnAttachToWindow(Runnable runnable) {
        if (isAttachToWindow()) {
            post(runnable, 0L);
        } else {
            addRunnableToList(runnable);
        }
    }

    protected void setBackGroundColor() {
        if (!canSetBg() || this.mRootView == null || isLine()) {
            return;
        }
        V v = this.mFloorEntity;
        int i2 = 0;
        if (v != null) {
            String str = v.backgroundColor;
            if (!TextUtils.isEmpty(str)) {
                try {
                    i2 = Color.parseColor(str);
                } catch (Exception e2) {
                    PlatformTools.catchException(e2);
                }
            }
        }
        if (i2 == 0) {
            V v2 = this.mFloorEntity;
            if (v2 == null || v2.canUseDefaultBgColor) {
                int backGroundColor = getBackGroundColor();
                if (backGroundColor == 0) {
                    backGroundColor = Color.parseColor(JDDarkUtil.COLOR_FFFFFFF);
                }
                this.mRootView.setBackgroundColor(backGroundColor);
                return;
            }
            return;
        }
        this.mRootView.setBackgroundColor(i2);
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public void setOnViewHolderHideListener(OnViewHolderHideListener onViewHolderHideListener) {
        this.mViewHolderHideListener = onViewHolderHideListener;
    }

    protected void showConfig() {
        setBackGroundColor();
        V v = this.mFloorEntity;
        if (v != null) {
            showConfigData(v);
        }
    }

    protected void showConfigData(V v) {
    }

    public abstract void showData(V v);

    public void showData(V v, Object obj) {
        showData(v);
    }

    public boolean onDataShowed(V v, Object obj) {
        boolean isSameEntity = isSameEntity(v);
        if (PlatformTools.D) {
            PlatformTools.d(this.TAG, "onDataShowed isSameEntity = " + isSameEntity);
        }
        boolean z = true;
        if (v != null && this.mIsInit && !this.mIsDestroy) {
            if (PlatformTools.D) {
                PlatformTools.d(this.TAG, "onDataShowed = " + v.mId);
            }
            this.mFloorEntity = v;
            this.mId = v.mId;
            if (!isSameEntity || !v.isShow) {
                if (PlatformTools.D) {
                    PlatformTools.d(this.TAG, "showData = " + v.mId);
                }
                v.isShow = true;
                try {
                    showConfig();
                    showData(v, obj);
                    try {
                        this.isValid = true;
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        hideViewHolder();
                        onShowFailed(e);
                        PlatformTools.catchExceptionAndReportToBugly(e);
                        return z;
                    }
                } catch (Exception e3) {
                    e = e3;
                    z = false;
                }
            }
        }
        return false;
    }

    @Override // com.jingdong.sdk.platform.base.ViewHolder
    public void post(Runnable runnable, long j2) {
        SyncEventBus.postToMainThread(runnable, j2);
    }
}
