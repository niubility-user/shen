package com.jingdong.sdk.platform.floor.viewmvp;

import com.jingdong.sdk.platform.floor.viewmvp.IViewUI;

/* loaded from: classes10.dex */
public abstract class ViewPresenter<VUI extends IViewUI> {
    protected boolean mIsDestroy = false;
    VUI mUI;

    public ViewPresenter(VUI vui) {
        this.mUI = vui;
    }

    public VUI getUI() {
        return this.mUI;
    }

    public void onDestroy() {
        this.mIsDestroy = true;
        onPresenterDestroy();
        this.mUI = null;
    }

    public abstract void onPresenterDestroy();
}
