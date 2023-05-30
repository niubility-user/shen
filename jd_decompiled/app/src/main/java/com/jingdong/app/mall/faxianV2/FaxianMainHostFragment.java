package com.jingdong.app.mall.faxianV2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.hwsupersdk.sdk.utils.JDImproveSDKUtils;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.app.mall.faxianV2.a;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.builderimpl.DefaultRouterBuilder;
import com.jingdong.common.unification.router.module.JDNavigationModule;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.widget.custom.discovery.RedPointManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;
import com.unionpay.tsmservice.mi.data.Constant;

/* loaded from: classes3.dex */
public class FaxianMainHostFragment extends JDTabFragment {

    /* renamed from: j */
    private static final String f8417j = FaxianMainHostFragment.class.getSimpleName();

    /* renamed from: k */
    private static boolean f8418k;

    /* renamed from: l */
    public static String f8419l;

    /* renamed from: m */
    public static String f8420m;

    /* renamed from: n */
    public static String f8421n;
    private static FaxianMainHostFragment o;
    private static a.b p;

    /* renamed from: g */
    private View f8422g;

    /* renamed from: h */
    private Fragment f8423h;

    /* renamed from: i */
    private OnElderModeChangeListener f8424i;

    /* loaded from: classes3.dex */
    public static class DiscoveryMainFragmentTM extends JDTaskModule {

        /* renamed from: g */
        private Fragment f8425g;

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            FaxianMainHostFragment j2 = FaxianMainHostFragment.j();
            this.f8425g = j2;
            if (j2.getArguments() == null) {
                c().putInt("com.360buy:navigationFlag", 2);
                this.f8425g.setArguments(c());
            }
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            j(this.f8425g, 2);
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void k(Bundle bundle) {
            super.k(bundle);
            n(bundle);
        }

        public void n(Bundle bundle) {
            if (bundle != null) {
                FaxianMainHostFragment.f8419l = bundle.getString("sourceId");
                FaxianMainHostFragment.f8420m = bundle.getString("sourceType");
                FaxianMainHostFragment.f8421n = bundle.getString("findExtParam");
            }
        }
    }

    /* loaded from: classes3.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f8426g;

        a(boolean z) {
            this.f8426g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f8426g) {
                CommonBase.putIntToPreference(RedPointManager.SHARED_FAXIAN_REDPOINTFLAG, 1);
                ((DefaultRouterBuilder) ((DefaultRouterBuilder) JDRouter.to(JDNavigationModule.TAG, "showNavigationRedPoint").putString("functionId", "find")).putBoolean("isShowRedPoint", true)).jump(JdSdk.getInstance().getApplicationContext());
                return;
            }
            CommonBase.putIntToPreference(RedPointManager.SHARED_FAXIAN_REDPOINTFLAG, 0);
            ((DefaultRouterBuilder) ((DefaultRouterBuilder) JDRouter.to(JDNavigationModule.TAG, "showNavigationRedPoint").putString("functionId", "find")).putBoolean("isShowRedPoint", false)).jump(JdSdk.getInstance().getApplicationContext());
        }
    }

    /* loaded from: classes3.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f8427g;

        b(String str) {
            this.f8427g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((DefaultRouterBuilder) ((DefaultRouterBuilder) JDRouter.to(JDNavigationModule.TAG, "showNavigationLabel").putString("functionId", "find")).putString(Constant.KEY_PROMOTION_LABEL, this.f8427g)).jump(JdSdk.getInstance().getApplicationContext());
        }
    }

    /* loaded from: classes3.dex */
    public class c implements OnElderModeChangeListener {
        c(FaxianMainHostFragment faxianMainHostFragment) {
        }

        @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
        public void onElderModeChanged(int i2) {
            FaxianMainHostFragment.l(FaxianMainHostFragment.f8418k);
        }
    }

    /* loaded from: classes3.dex */
    class d implements a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.faxianV2.a.b
        public void validateRedPoint(int i2, String str) {
            if (TextUtils.isEmpty(str)) {
                com.jingdong.app.mall.faxianV2.a.a = false;
            }
            if (!TextUtils.isEmpty(str)) {
                FaxianMainHostFragment.m(str);
                com.jingdong.app.mall.faxianV2.a.a = true;
                return;
            }
            if (i2 == 1) {
                FaxianMainHostFragment.l(true);
            }
            if (i2 == 0) {
                FaxianMainHostFragment.m("");
            }
        }
    }

    static {
        d dVar = new d();
        p = dVar;
        com.jingdong.app.mall.faxianV2.a.f(dVar);
    }

    public static void e() {
        com.jingdong.app.mall.faxianV2.a.d(false);
    }

    public static void f() {
        if (NavigationBase.getInstance().mCurrentIndex == 2) {
            return;
        }
        com.jingdong.app.mall.faxianV2.a.d(false);
    }

    private void g() {
        l(false);
        m("");
        if (com.jingdong.app.mall.faxianV2.a.a) {
            com.jingdong.app.mall.faxianV2.a.d(true);
        }
        SharedPreferencesUtil.putString("NavigationBar_Discover_RedPoint_2", "0");
    }

    public static JDTaskModule h(Bundle bundle) {
        DiscoveryMainFragmentTM discoveryMainFragmentTM = new DiscoveryMainFragmentTM();
        discoveryMainFragmentTM.k(bundle);
        return discoveryMainFragmentTM;
    }

    public static synchronized FaxianMainHostFragment j() {
        FaxianMainHostFragment faxianMainHostFragment;
        synchronized (FaxianMainHostFragment.class) {
            if (o == null) {
                o = new FaxianMainHostFragment();
            }
            faxianMainHostFragment = o;
        }
        return faxianMainHostFragment;
    }

    public static void k() {
        o = null;
    }

    public static void l(boolean z) {
        if (JDElderModeUtils.isElderMode()) {
            o(false);
            f8418k = false;
            return;
        }
        o(z);
        f8418k = z;
    }

    public static void m(String str) {
        if (JDElderModeUtils.isElderMode()) {
            return;
        }
        q(str);
    }

    private void n(boolean z) {
        if (z) {
            if (this.f8424i == null) {
                this.f8424i = new c(this);
            }
            JDElderModeUtils.addElderModeChangeListener(this.f8424i);
            return;
        }
        OnElderModeChangeListener onElderModeChangeListener = this.f8424i;
        if (onElderModeChangeListener != null) {
            JDElderModeUtils.removeElderModeChangeListener(onElderModeChangeListener);
        }
    }

    public static void o(boolean z) {
        if (MainFrameActivity.getFaxianShowNew() == null || BaseFrameUtil.getInstance().getMainFrameActivity() == null || BaseFrameUtil.getInstance().getMainFrameActivity().getHandler() == null) {
            return;
        }
        BaseFrameUtil.getInstance().getMainFrameActivity().getHandler().post(new a(z));
    }

    public static void q(String str) {
        if (BaseFrameUtil.getInstance().getMainFrameActivity() == null || BaseFrameUtil.getInstance().getMainFrameActivity().getHandler() == null) {
            return;
        }
        BaseFrameUtil.getInstance().getMainFrameActivity().getHandler().post(new b(str));
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        String[] split = SharedPreferencesUtil.getString("NavigationBar_Discover_RedPoint_2", "0").split(ContainerUtils.FIELD_DELIMITER);
        String str2 = split[0];
        jDJSONObject.put("redpoint", (Object) str2);
        if (split.length > 1) {
            jDJSONObject.put("markId", (Object) split[1]);
        }
        if (i2 == i3) {
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "NavigationBar_Discover", String.format("1_%d", 0), "", "", "\u5e95\u90e8\u5bfc\u822a", "", "", jDJSONObject.toJSONString(), null);
        } else if (isJumpFromNavigation()) {
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), "NavigationBar_Discover", String.format("0_%s", str2), "", "", "\u5e95\u90e8\u5bfc\u822a", "", "", jDJSONObject.toJSONString(), null);
        }
        if (this.f8423h instanceof JDTabFragment) {
            if (Log.D) {
                Log.d("Arthur: ", "do clickNavigation");
            }
            com.jingdong.app.mall.navigationbar.d.a().b(2, false);
            ((JDTabFragment) this.f8423h).clickNavigation(i2, i3, str);
        } else if (Log.D) {
            Log.d("Arthur: ", "It seems discoveryFragment is not inited ...");
        }
    }

    public void d(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString("sourceId", f8419l);
        arguments.putString("sourceType", f8420m);
        arguments.putString("findExtParam", f8421n);
        if (Log.D) {
            Log.d(f8417j, "sourceId : " + f8419l + " sourceType : " + f8420m + " findExtParam : " + f8421n);
        }
        fragment.setArguments(arguments);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        Fragment fragment = this.f8423h;
        if (fragment != null) {
            fragment.onActivityResult(i2, i3, intent);
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        this.thisActivity = (BaseActivity) activity;
        super.onAttach(activity);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.isTransStatusbar = true;
        super.onCreate(bundle);
        n(true);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            d(this.f8423h);
            getChildFragmentManager().beginTransaction().replace(R.id.sh, this.f8423h).commitAllowingStateLoss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        if ("1".equals(CommonBase.getJdSharedPreferences().getString("hwImproveEnable", "0"))) {
            JDImproveSDKUtils.setVIPSceneStatus(2, 2);
        }
        if (this.f8422g == null) {
            try {
                this.f8423h = AuraFragmentHelper.getInstance().newFragment(getActivity(), "com.jd.lib.Discovery.view.fragment.FaxianMainFeedsFragment");
                this.f8422g = ImageUtil.inflate(this.thisActivity.getApplicationContext(), (int) R.layout.cart_container_fragment, (ViewGroup) null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this.f8422g;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        SharedPreferencesUtil.putString("NavigationBar_Discover_RedPoint_2", "0");
        n(false);
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
        g();
        SharedPreferencesUtil.putString("faxian_kuaibao_source", getPageParam());
        com.jingdong.app.mall.navigationbar.c.G().a0(2);
        f8418k = false;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }
}
