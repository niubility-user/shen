package com.jingdong.app.mall.jdvideo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.navigationbar.c;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.widget.LoadingHead;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class JDVideoHostFragment extends JDTabFragment {

    /* renamed from: i */
    private static JDVideoHostFragment f11142i;

    /* renamed from: j */
    private static final String f11143j = JDVideoHostFragment.class.getSimpleName();

    /* renamed from: g */
    private View f11144g;

    /* renamed from: h */
    private Fragment f11145h;

    /* loaded from: classes4.dex */
    public static class JDVideoTM extends JDTaskModule {

        /* renamed from: g */
        private JDVideoHostFragment f11146g;

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            JDVideoHostFragment a = JDVideoHostFragment.a();
            this.f11146g = a;
            if (a.getArguments() == null) {
                c().putInt("com.360buy:navigationFlag", 6);
                this.f11146g.setArguments(c());
            }
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            j(this.f11146g, 6);
        }
    }

    public static JDVideoHostFragment a() {
        if (f11142i == null) {
            f11142i = new JDVideoHostFragment();
        }
        return f11142i;
    }

    public static void c() {
        f11142i = null;
    }

    public void b(boolean z) {
        Fragment fragment = this.f11145h;
        if (fragment == null) {
            return;
        }
        try {
            Method declaredMethod = fragment.getClass().getDeclaredMethod(LoadingHead.LOADING_STATE_PULL_TO_REFRESH, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.f11145h, Boolean.valueOf(z));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        if (Log.D) {
            Log.d("navigation-click", f11143j + "   old-->" + i2 + " now-->" + i3);
        }
        if (i2 == i3) {
            b(false);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (Log.D) {
            Log.d(f11143j, " onActivityResult ---> requestCode : " + i2);
        }
        Fragment fragment = this.f11145h;
        if (fragment != null) {
            fragment.onActivityResult(i2, i3, intent);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
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
        getChildFragmentManager().beginTransaction().replace(R.id.jdvideo_container, this.f11145h).commitAllowingStateLoss();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        this.f11145h = AuraFragmentHelper.getInstance().newFragment(getActivity(), "com.jd.lib.videolife.view.fragment.VideoLifeFragment");
        View inflate = ImageUtil.inflate(getActivity().getApplicationContext(), (int) R.layout.jdvideo_container_fragment, (ViewGroup) null);
        this.f11144g = inflate;
        return inflate;
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
        c.G().a0(6);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
