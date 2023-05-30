package com.jingdong.app.mall.bundle.styleinfoview.base;

import android.view.View;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.widget.JDFlipPageLayout;

/* loaded from: classes3.dex */
public abstract class ProductBasePage implements JDFlipPageLayout.IFlipPage {
    protected static String TAG = "ProductDetailActivity";
    protected BaseActivity mActivity;
    protected View mRootView;

    public ProductBasePage(BaseActivity baseActivity, View view) {
        this.mActivity = baseActivity;
        this.mRootView = view;
        initView();
    }

    public void destroy() {
    }

    @Override // com.jingdong.common.widget.JDFlipPageLayout.IFlipPage
    public View getRootView() {
        return this.mRootView;
    }

    protected void initView() {
    }

    @Override // com.jingdong.common.widget.JDFlipPageLayout.IFlipPage
    public boolean isFlipToBottom() {
        return false;
    }

    @Override // com.jingdong.common.widget.JDFlipPageLayout.IFlipPage
    public boolean isFlipToTop() {
        return false;
    }

    public void onRefresh() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }
}
