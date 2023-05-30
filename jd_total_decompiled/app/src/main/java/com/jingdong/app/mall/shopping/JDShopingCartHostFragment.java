package com.jingdong.app.mall.shopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.navigationbar.c;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.widget.LoadingHead;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.lib.compact.CompactBaseFragment;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class JDShopingCartHostFragment extends JDTabFragment {

    /* renamed from: i */
    private static JDShopingCartHostFragment f11634i;

    /* renamed from: j */
    private static final String f11635j = JDShopingCartHostFragment.class.getSimpleName();

    /* renamed from: g */
    private View f11636g;

    /* renamed from: h */
    private Fragment f11637h;

    /* loaded from: classes4.dex */
    public static class JDShoppingCartTM extends JDTaskModule {

        /* renamed from: g */
        private JDShopingCartHostFragment f11638g;

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            JDShopingCartHostFragment b = JDShopingCartHostFragment.b();
            this.f11638g = b;
            if (b.getArguments() == null) {
                c().putInt("com.360buy:navigationFlag", 3);
                this.f11638g.setArguments(c());
                return;
            }
            if (Log.D) {
                Log.d(JDShopingCartHostFragment.f11635j, "getArguments == " + this.f11638g.getArguments().get("cartExtParam"));
            }
            this.f11638g.getArguments().putInt("com.360buy:navigationFlag", 3);
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            j(this.f11638g, 3);
        }
    }

    public static JDShopingCartHostFragment b() {
        if (f11634i == null) {
            f11634i = new JDShopingCartHostFragment();
        }
        return f11634i;
    }

    public static void c() {
        f11634i = null;
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        Fragment fragment;
        if (Log.D) {
            Log.d("navigation-click", f11635j + "   old-->" + i2 + " now-->" + i3);
        }
        if (i2 != i3 || (fragment = this.f11637h) == null) {
            return;
        }
        try {
            Method declaredMethod = fragment.getClass().getDeclaredMethod(LoadingHead.LOADING_STATE_PULL_TO_REFRESH, new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.f11637h, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (Log.D) {
            Log.d(f11635j, " onActivityResult ---> requestCode : " + i2);
        }
        Fragment fragment = this.f11637h;
        if (fragment != null) {
            fragment.onActivityResult(i2, i3, intent);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        BaseActivity baseActivity = (BaseActivity) activity;
        if (activity.getTheme() == null) {
            String config = JDMobileConfig.getInstance().getConfig("jdCart", "miuiThemeSwitch", "miuiThemeSwitch");
            if (!TextUtils.isEmpty(config) && config.equals("1")) {
                try {
                    ExceptionReporter.reportExceptionToBugly(new Exception("cart activity.getTheme() == null"));
                    activity.setTheme(R.style.n0);
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
        }
        super.onAttach(activity);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.isTransStatusbar = true;
        super.onCreate(bundle);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getArguments() != null && this.f11637h != null) {
            if (Log.D) {
                Log.d(f11635j, "getArguments == " + getArguments().get("cartExtParam"));
            }
            this.f11637h.setArguments(getArguments());
        }
        getChildFragmentManager().beginTransaction().replace(R.id.sh, this.f11637h).commitAllowingStateLoss();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 2);
        }
        this.f11637h = AuraFragmentHelper.getInstance().newFragment(getActivity(), "com.jd.lib.cart.JDShoppingCartFragment");
        View inflate = ImageUtil.inflate(getActivity().getApplicationContext(), (int) R.layout.cart_container_fragment, (ViewGroup) null);
        this.f11636g = inflate;
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        Fragment fragment = this.f11637h;
        if (fragment != null) {
            boolean onKeyDown = ((CompactBaseFragment) fragment).onKeyDown(i2, keyEvent);
            if (OKLog.D) {
                OKLog.d(f11635j, "JDShopingCartHostFragment onKeyDown:" + onKeyDown);
            }
            return onKeyDown;
        }
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
        c.G().a0(3);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
