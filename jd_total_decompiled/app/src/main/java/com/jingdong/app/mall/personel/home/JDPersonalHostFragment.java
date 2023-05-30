package com.jingdong.app.mall.personel.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.navigationbar.c;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.FloatLayerADUtil;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.NpsTimeUtil;
import com.jingdong.common.utils.PersonalEnterUtils;
import com.jingdong.common.utils.PersonalSwitchManager;
import com.jingdong.common.utils.RefreshParamsUtils;
import com.jingdong.common.utils.personal.JDPersonalPlatformConfigUtils;
import com.jingdong.common.utils.personal.PersonInfoBusinessCallback;
import com.jingdong.common.utils.personal.platform.impl.JDPersonalPlatformHttpProxy;
import com.jingdong.common.utils.pre.PersonalPreLoader;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.lib.compact.CompactBaseFragment;
import com.jingdong.sdk.oklog.OKLog;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class JDPersonalHostFragment extends JDTabFragment {

    /* renamed from: i */
    private static JDPersonalHostFragment f11539i;

    /* renamed from: j */
    private static final String f11540j = JDPersonalHostFragment.class.getSimpleName();

    /* renamed from: g */
    private View f11541g;

    /* renamed from: h */
    private Fragment f11542h;

    /* loaded from: classes4.dex */
    public static class JDPersonalTM extends JDTaskModule {

        /* renamed from: g */
        private JDPersonalHostFragment f11543g;

        /* loaded from: classes4.dex */
        public class a implements PersonalPreLoader.LoadDataObservable<HttpResponse> {

            /* renamed from: com.jingdong.app.mall.personel.home.JDPersonalHostFragment$JDPersonalTM$a$a */
            /* loaded from: classes4.dex */
            class C0372a implements PersonInfoBusinessCallback {
                final /* synthetic */ PersonalPreLoader.DataCallBack a;

                C0372a(a aVar, PersonalPreLoader.DataCallBack dataCallBack) {
                    this.a = dataCallBack;
                }

                @Override // com.jingdong.common.utils.personal.PersonInfoBusinessCallback
                public void onError(Exception exc) {
                    if (OKLog.D) {
                        OKLog.d("PersonalPreLoader", "PersonalPreLoader-net-error");
                    }
                    this.a.onError();
                }

                @Override // com.jingdong.common.utils.personal.PersonInfoBusinessCallback
                public void onSuccess(@NonNull HttpResponse httpResponse) {
                    if (OKLog.D) {
                        OKLog.d("PersonalPreLoader", "PersonalPreLoader-net-success");
                    }
                    this.a.onSuccess(httpResponse);
                }
            }

            a() {
                JDPersonalTM.this = r1;
            }

            @Override // com.jingdong.common.utils.pre.PersonalPreLoader.LoadDataObservable
            public void load(@NotNull PersonalPreLoader.DataCallBack<HttpResponse> dataCallBack) {
                JDPersonalTM.this.p(new C0372a(this, dataCallBack));
            }
        }

        public JDPersonalTM() {
            o();
        }

        private void o() {
            if (LoginUserBase.hasLogin() && JDPersonalHostFragment.f11539i == null && !JDElderModeUtils.isElderMode() && PersonalSwitchManager.getPreLoaderSwitch()) {
                if (OKLog.D) {
                    OKLog.d("PersonalPreLoader", "PersonalPreLoader-net-start");
                }
                PersonalPreLoader.preLoad("personinfoBusiness", new a());
            }
        }

        public void p(PersonInfoBusinessCallback personInfoBusinessCallback) {
            CharSequence charSequence = LoginUserBase.hasLogin() ? "1" : null;
            JDPersonalPlatformHttpProxy jDPersonalPlatformHttpProxy = new JDPersonalPlatformHttpProxy("0");
            HttpSetting httpSetting = jDPersonalPlatformHttpProxy.getHttpSetting();
            if (httpSetting != null) {
                if (!NpsTimeUtil.judgeShowNps(NpsTimeUtil.FUNCTION_FLOAT_NPS)) {
                    httpSetting.putJsonParam("callNPS", "0");
                } else {
                    httpSetting.putJsonParam("callNPS", "1");
                }
                if (!NpsTimeUtil.judgeShowNps(NpsTimeUtil.FUNCTION_FLOAT_CJH)) {
                    httpSetting.putJsonParam("callCJH", "0");
                } else {
                    httpSetting.putJsonParam("callCJH", "1");
                }
                httpSetting.putJsonParam("closeJX", FloatLayerADUtil.getCloseJXSu());
                if (!FloatLayerADUtil.getAdFourTypeCloseSuList().isEmpty()) {
                    httpSetting.putJsonParam("shieldFucIds", FloatLayerADUtil.getAdFourTypeCloseSuList());
                }
                if (!TextUtils.isEmpty(charSequence)) {
                    httpSetting.putJsonParam("refreshEnable", charSequence);
                }
                httpSetting.putJsonParam("headTaskRefresh", RefreshParamsUtils.getCommonRefresh());
                if (LoginUserBase.hasLogin()) {
                    httpSetting.putJsonParam("todayFirst", PersonalEnterUtils.isFirstEnterPersonal());
                }
            }
            JDPersonalPlatformConfigUtils.getPersonInfoBusinessInfo(jDPersonalPlatformHttpProxy, personInfoBusinessCallback);
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            JDPersonalHostFragment c2 = JDPersonalHostFragment.c();
            this.f11543g = c2;
            if (c2.getArguments() == null) {
                c().putInt("com.360buy:navigationFlag", 4);
                this.f11543g.setArguments(c());
            }
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            JDPersonalHostFragment jDPersonalHostFragment = this.f11543g;
            if (jDPersonalHostFragment != null) {
                jDPersonalHostFragment.d(false);
            }
            j(this.f11543g, 4);
        }
    }

    public static JDPersonalHostFragment c() {
        if (f11539i == null) {
            f11539i = new JDPersonalHostFragment();
        }
        return f11539i;
    }

    public boolean d(boolean z) {
        Fragment fragment;
        IMainActivity mainFrameActivity;
        Object obj = null;
        try {
            if (this.f11542h == null && (mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity()) != null && mainFrameActivity.getThisActivity() != null) {
                this.f11542h = AuraFragmentHelper.getInstance().newFragment(mainFrameActivity.getThisActivity(), "com.jd.lib.personal.view.fragment.JDPersonalFragment");
            }
            fragment = this.f11542h;
        } catch (Throwable th) {
            if (Log.D) {
                OKLog.d(f11540j, "handleReflectInvokeLoginMethod get error.", th);
            }
        }
        if (fragment != null) {
            obj = fragment.getClass().getMethod("handleLogin", Boolean.TYPE).invoke(this.f11542h, Boolean.valueOf(z));
            if (OKLog.D) {
                OKLog.d(f11540j, "handleReflectInvokeLoginMethod result:" + obj);
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        }
        throw new IllegalArgumentException("Target Fragment instance must not be null.");
    }

    public static void f() {
        f11539i = null;
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        if (i2 == i3 && i3 == 4) {
            if (d(true)) {
                return;
            }
            try {
                this.f11542h.getClass().getMethod("onTabDoubleClick", new Class[0]).invoke(this.f11542h, new Object[0]);
            } catch (Exception e2) {
                if (Log.D) {
                    OKLog.d(f11540j, "Double click navigation get error.", e2);
                }
            }
        }
        if (Log.D) {
            Log.d("navigation-click", f11540j + "   old-->" + i2 + " now-->" + i3);
        }
    }

    public void e() {
        try {
            Fragment fragment = this.f11542h;
            if (fragment != null) {
                fragment.getClass().getMethod("onNewIntent", new Class[0]).invoke(this.f11542h, new Object[0]);
            }
        } catch (Exception e2) {
            if (Log.D) {
                OKLog.d(f11540j, "onNewIntent get error.", e2);
            }
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
        getChildFragmentManager().beginTransaction().replace(R.id.sh, this.f11542h).commitAllowingStateLoss();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 2);
        }
        this.f11542h = AuraFragmentHelper.getInstance().newFragment(getActivity(), "com.jd.lib.personal.view.fragment.JDPersonalFragment");
        View inflate = ImageUtil.inflate(getActivity().getApplicationContext(), (int) R.layout.myjd_personal_container_fragment, (ViewGroup) null);
        this.f11541g = inflate;
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        Fragment fragment = this.f11542h;
        if (fragment instanceof CompactBaseFragment) {
            boolean onKeyDown = ((CompactBaseFragment) fragment).onKeyDown(i2, keyEvent);
            if (OKLog.D) {
                OKLog.d(f11540j, "JDPersonalHostFragment onKeyDown:" + onKeyDown);
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
        c.G().a0(4);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
