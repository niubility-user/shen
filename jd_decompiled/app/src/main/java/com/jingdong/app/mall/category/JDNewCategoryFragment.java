package com.jingdong.app.mall.category;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.navigationbar.c;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.navigationbar.INavigationPage;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;

/* loaded from: classes3.dex */
public class JDNewCategoryFragment extends JDTabFragment {

    /* renamed from: i */
    private static JDNewCategoryFragment f8312i;

    /* renamed from: j */
    private static final String f8313j = JDNewCategoryFragment.class.getSimpleName();

    /* renamed from: g */
    private View f8314g;

    /* renamed from: h */
    private Fragment f8315h;

    /* loaded from: classes3.dex */
    public static class JDNewCategoryTM extends JDTaskModule {

        /* renamed from: g */
        private JDNewCategoryFragment f8316g;

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            JDNewCategoryFragment a = JDNewCategoryFragment.a();
            this.f8316g = a;
            if (a.getArguments() == null) {
                c().putInt("com.360buy:navigationFlag", 1);
                this.f8316g.setArguments(c());
            }
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            j(this.f8316g, 1);
        }
    }

    public static JDNewCategoryFragment a() {
        if (f8312i == null) {
            f8312i = new JDNewCategoryFragment();
        }
        return f8312i;
    }

    public static void b() {
        f8312i = null;
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        if (Log.D) {
            Log.d("navigation-click", f8313j + "   old-->" + i2 + " now-->" + i3);
        }
        Fragment fragment = this.f8315h;
        if (fragment == null || !(fragment instanceof INavigationPage)) {
            return;
        }
        ((INavigationPage) fragment).clickNavigation(i2, i3, str);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 2);
        }
        BaseActivity baseActivity = (BaseActivity) activity;
        super.onAttach(activity);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.isTransStatusbar = true;
        super.onCreate(bundle);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f8314g == null) {
            this.f8314g = ImageUtil.inflate(getActivity().getApplicationContext(), (int) R.layout.category_container_fragment, (ViewGroup) null);
        }
        getChildFragmentManager().beginTransaction().replace(R.id.sh, this.f8315h).commitAllowingStateLoss();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        this.f8315h = AuraFragmentHelper.getInstance().newFragment(getActivity(), "com.jd.lib.category.JDCategoryFragment");
        View inflate = ImageUtil.inflate(getActivity().getApplicationContext(), (int) R.layout.category_container_fragment, (ViewGroup) null);
        this.f8314g = inflate;
        return inflate;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f8314g = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        setIsUseBasePV(false);
        super.onResume();
        if (NavigationBase.getInstance().isNavigationType(3)) {
            c.G().a0(7);
        } else {
            c.G().a0(1);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
