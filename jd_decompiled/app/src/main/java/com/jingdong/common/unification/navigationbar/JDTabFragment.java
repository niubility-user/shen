package com.jingdong.common.unification.navigationbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.cleanmvp.presenter.BaseNavigator;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.cleanmvp.ui.MvpBaseFragment;
import com.jingdong.common.R;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public abstract class JDTabFragment<P extends BasePresenter, N extends BaseNavigator> extends MvpBaseFragment implements INavigationPage {
    private static final String TAG = "JDTabFragment";
    public boolean isNavigationTab = false;
    public boolean isTransStatusbar = false;

    private void setStatusBarTint() {
        ViewGroup viewGroup = (ViewGroup) this.rootView;
        int paddingTop = viewGroup.getPaddingTop();
        int statusBarHeight = UnStatusBarTintUtil.getStatusBarHeight((Activity) this.thisActivity);
        if (this.thisActivity.isStatusBarTintEnable()) {
            if (this.isTransStatusbar) {
                UnStatusBarTintUtil.setBackgroundColor(getActivity(), 0);
                UnStatusBarTintUtil.setStatusBarDarkMode(this.thisActivity);
                if (paddingTop >= statusBarHeight) {
                    viewGroup.setPadding(viewGroup.getPaddingLeft(), paddingTop - statusBarHeight, viewGroup.getPaddingRight(), viewGroup.getPaddingBottom());
                    return;
                }
                return;
            }
            if (UnStatusBarTintUtil.setStatusBarLightMode(this.thisActivity)) {
                UnStatusBarTintUtil.setBackgroundResource(this.thisActivity, R.color.status_bar_bg_light);
            } else {
                UnStatusBarTintUtil.setBackgroundResource(this.thisActivity, R.color.status_bar_bg);
            }
            if (paddingTop < UnStatusBarTintUtil.getStatusBarHeight((Activity) this.thisActivity)) {
                viewGroup.setPadding(viewGroup.getPaddingLeft(), paddingTop + statusBarHeight, viewGroup.getPaddingRight(), viewGroup.getPaddingBottom());
            }
        }
    }

    public abstract void clickNavigation(int i2, int i3, String str);

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment
    @Nullable
    protected BasePresenter createPresenter() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment
    public P getPresenter() {
        return (P) super.getPresenter();
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void hideProgress() {
    }

    public boolean isJumpFromNavigation() {
        return NavigationBase.getInstance().isJumpFromClick(NavigationBase.getInstance().mCurrentIndex);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.needRemoveviewOnStop = false;
        if (OKLog.D) {
            OKLog.d("JDNavigationFragment", "JDTabFragment-onCreate-" + NavigationBase.getInstance().mCurrentIndex);
        }
        if (this.isNavigationTab) {
            clickNavigation(NavigationBase.getInstance().oldPage, NavigationBase.getInstance().mCurrentIndex, NavigationBase.getInstance().getmUrl(NavigationBase.getInstance().mCurrentIndex));
            NavigationBase.getInstance().oldPage = NavigationBase.getInstance().mCurrentIndex;
            try {
                setStatusBarTint();
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public abstract View onCreateViews(LayoutInflater layoutInflater, Bundle bundle);

    @Override // com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public abstract boolean onKeyDown(int i2, KeyEvent keyEvent);

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment
    public void setPresenter(@NonNull BasePresenter basePresenter) {
        super.setPresenter(basePresenter);
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showProgress() {
    }
}
