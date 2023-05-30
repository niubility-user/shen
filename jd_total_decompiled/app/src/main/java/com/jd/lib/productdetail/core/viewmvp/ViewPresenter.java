package com.jd.lib.productdetail.core.viewmvp;

import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.viewmvp.IViewUI;

/* loaded from: classes15.dex */
public abstract class ViewPresenter<VUI extends IViewUI> {
    protected boolean mIsDestroy = false;
    protected ProductDetailEntity mProduct;
    VUI mUI;

    public ViewPresenter(VUI vui, ProductDetailEntity productDetailEntity) {
        this.mUI = vui;
        this.mProduct = productDetailEntity;
    }

    public VUI getUI() {
        return this.mUI;
    }

    public void handlerEvent(PDApiEvent pDApiEvent) {
    }

    public void handlerEvent(PDViewEvent pDViewEvent) {
    }

    public void onDestroy() {
        this.mIsDestroy = true;
        onPresenterDestroy();
        this.mUI = null;
        this.mProduct = null;
    }

    public abstract void onPresenterDestroy();
}
