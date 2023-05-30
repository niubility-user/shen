package com.jd.lib.productdetail.core.viewmvp;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.floor.PDFloorData;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.viewmvp.ViewPresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import de.greenrobot.event.EventBus;

/* loaded from: classes15.dex */
public abstract class BaseViewHolder<VP extends ViewPresenter> implements IViewUI {
    protected static final String TAG = "BaseViewHolder";
    protected String CLASS_NAME = "com.jd.lib.productdetail.ProductDetailActivity";
    protected Context mContext;
    protected PDFloorData mFloorData;
    protected boolean mIsDestroy;
    protected View mRootView;
    protected VP mViewPresenter;
    protected BaseActivity myActivity;
    protected ProductDetailEntity product;

    public BaseViewHolder(BaseActivity baseActivity, View view, PDFloorData pDFloorData) {
        this.myActivity = baseActivity;
        this.mContext = baseActivity.getBaseContext();
        this.mRootView = view;
        this.mFloorData = pDFloorData;
        this.product = pDFloorData.mProduct;
    }

    public abstract VP createPresenter();

    public View findViewById(int i2) {
        return this.mRootView.findViewById(i2);
    }

    @Override // com.jd.lib.productdetail.core.viewmvp.IViewUI
    public Context getContext() {
        return this.myActivity;
    }

    public EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    @Override // com.jd.lib.productdetail.core.viewmvp.IViewUI
    public VP getPresenter() {
        if (this.mViewPresenter == null) {
            this.mViewPresenter = createPresenter();
        }
        VP vp = this.mViewPresenter;
        if (vp != null) {
            return vp;
        }
        throw new NullPointerException("getPresenter can not return null");
    }

    public Resources getResources() {
        return this.mContext.getResources();
    }

    public String getString(int i2) {
        return getResources().getString(i2);
    }

    public View getView() {
        return this.mRootView;
    }

    public void onDestroy() {
        if (Log.D) {
            Log.d(TAG, "onViewDestroy = ");
        }
        this.mIsDestroy = true;
        onViewDestroy();
        VP vp = this.mViewPresenter;
        if (vp != null) {
            vp.onDestroy();
        }
        this.mViewPresenter = null;
        this.mRootView = null;
        this.product = null;
        this.mContext = null;
    }

    protected abstract void onViewDestroy();

    public void post(Runnable runnable) {
        BaseActivity baseActivity = this.myActivity;
        if (baseActivity != null) {
            baseActivity.post(runnable);
        }
    }

    public void post(Runnable runnable, int i2) {
        BaseActivity baseActivity = this.myActivity;
        if (baseActivity != null) {
            baseActivity.post(runnable, i2);
        }
    }
}
