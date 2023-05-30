package com.jd.cashier.app.jdlibcutter.impl.ui.loading;

import android.view.View;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.protocol.ui.loading.ILoading;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.ui.LottieLoadingView;

/* loaded from: classes13.dex */
public class LoadingImpl implements ILoading {
    private View mLoadingView;

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.loading.ILoading
    public void hideLoading(ViewGroup viewGroup) {
        if (viewGroup != null) {
            try {
                viewGroup.removeAllViews();
                this.mLoadingView = null;
                viewGroup.setVisibility(8);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.loading.ILoading
    public void onDestroyLoading(ViewGroup viewGroup) {
        try {
            View view = this.mLoadingView;
            if (view instanceof LottieLoadingView) {
                ((LottieLoadingView) view).freeResource();
            }
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            this.mLoadingView = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.loading.ILoading
    public void showLoading(ViewGroup viewGroup) {
        if (viewGroup != null) {
            try {
                if (viewGroup.getVisibility() == 8) {
                    viewGroup.removeAllViews();
                    viewGroup.setVisibility(0);
                    View lottieLoadingView = BaseApplication.getLottieLoadingView();
                    this.mLoadingView = lottieLoadingView;
                    viewGroup.addView(lottieLoadingView);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
