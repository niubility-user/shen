package com.jingdong.app.mall.common.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.navigationbar.c;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.widget.LoadingHead;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class JDCommonHostFragment extends JDTabFragment {

    /* renamed from: i */
    private static JDCommonHostFragment f8317i;

    /* renamed from: j */
    private static final String f8318j = JDCommonHostFragment.class.getSimpleName();

    /* renamed from: g */
    private View f8319g;

    /* renamed from: h */
    private Fragment f8320h;

    /* loaded from: classes3.dex */
    public static class JDCommonTM extends JDTaskModule {

        /* renamed from: g */
        private JDCommonHostFragment f8321g;

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            JDCommonHostFragment a = JDCommonHostFragment.a(Integer.valueOf(c().getInt("com.360buy:navigationFlag")));
            this.f8321g = a;
            if (a.getArguments() == null) {
                this.f8321g.setArguments(c());
            }
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            j(this.f8321g, Integer.valueOf(c().getInt("com.360buy:navigationFlag")));
        }
    }

    public static JDCommonHostFragment a(Integer num) {
        if (c.o.containsKey(num)) {
            f8317i = c.o.get(num);
        } else {
            f8317i = new JDCommonHostFragment();
            c.o.put(num, f8317i);
        }
        return f8317i;
    }

    public static void c() {
        f8317i = null;
        c.o.clear();
    }

    public void b(boolean z) {
        Fragment fragment = this.f8320h;
        if (fragment == null) {
            return;
        }
        try {
            Method declaredMethod = fragment.getClass().getDeclaredMethod(LoadingHead.LOADING_STATE_PULL_TO_REFRESH, new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.f8320h, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        if (Log.D) {
            Log.d("navigation-click", f8318j + "   old-->" + i2 + " now-->" + i3);
        }
        if (i2 == i3) {
            b(false);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        FragmentManager childFragmentManager;
        Fragment findFragmentById;
        super.onActivityResult(i2, i3, intent);
        if (getActivity() == null || !isAdded() || (childFragmentManager = getChildFragmentManager()) == null || (findFragmentById = childFragmentManager.findFragmentById(R.id.sh)) == null) {
            return;
        }
        findFragmentById.onActivityResult(i2, i3, intent);
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
        if (this.f8320h != null && getArguments() != null) {
            if (Log.D) {
                Log.d(f8318j, "getArguments = " + getArguments().get("newExtParam"));
            }
            this.f8320h.setArguments(getArguments());
        }
        getChildFragmentManager().beginTransaction().replace(R.id.sh, this.f8320h).commitAllowingStateLoss();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 2);
        }
        String string = getArguments().getString("packageName");
        if (!TextUtils.isEmpty(string)) {
            this.f8320h = AuraFragmentHelper.getInstance().newFragment(getActivity(), string);
            this.f8319g = ImageUtil.inflate(getActivity().getApplicationContext(), (int) R.layout.common_container_fragment, (ViewGroup) null);
        }
        return this.f8319g;
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
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
