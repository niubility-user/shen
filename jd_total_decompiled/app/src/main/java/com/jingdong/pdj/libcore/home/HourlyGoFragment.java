package com.jingdong.pdj.libcore.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.deeplinkhelper.DeepLinkHourlyGoHelper;
import com.jingdong.common.unification.navigationbar.JDTabFragment;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.pdj.libcore.R;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HourlyGoFragment extends JDTabFragment {
    public static final String HOURLY_FRAGMENT_NAME = "com.jd.lib.hourlygo.home.fragment.HourlyGoHomeFragment";
    protected static final String TAG = "HourlyGoFragment";
    public static JSONObject outLinkJsonParam;
    private BaseFragment hourlyGoHomeFragment;
    public static final String HOURLY_GO_IS_FIRST = "hourly_go_is_first";
    public static boolean HOURLY_GO_IS_ENTER_IN = CommonBase.getBooleanFromPreference(HOURLY_GO_IS_FIRST, Boolean.FALSE).booleanValue();

    public static boolean isFragmentAvailable() {
        if (AuraFragmentHelper.getInstance().isFragmentAvailable(AuraBundleInfos.getBundleNameFromBundleId(114), HOURLY_FRAGMENT_NAME)) {
            return true;
        }
        DeepLinkHourlyGoHelper.startHourlyGoDetailPage(JdSdk.getInstance().getApplicationContext(), new Bundle());
        return false;
    }

    public static void setOuterLinkParams(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        OKLog.e(TAG, "setOuterLinkParams=" + jSONObject.toString());
        outLinkJsonParam = jSONObject;
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            if (this.hourlyGoHomeFragment != null) {
                getChildFragmentManager().beginTransaction().replace(R.id.lib_hourly_core_container, this.hourlyGoHomeFragment).commitAllowingStateLoss();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(TAG, "onCreateView" + e2.toString());
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        try {
            Fragment newFragment = AuraFragmentHelper.getInstance().newFragment(getActivity(), HOURLY_FRAGMENT_NAME);
            if (newFragment instanceof BaseFragment) {
                this.hourlyGoHomeFragment = (BaseFragment) newFragment;
            }
            this.rootView = ImageUtil.inflate(getActivity().getApplicationContext(), R.layout.hourly_go_container_fragment, (ViewGroup) null);
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(TAG, "onCreateViews" + e2.toString());
        }
        return this.rootView;
    }

    @Override // com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.sdk.lib.compact.CompactBaseFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        BaseFragment baseFragment = this.hourlyGoHomeFragment;
        if (baseFragment != null) {
            return baseFragment.onKeyDown(i2, keyEvent);
        }
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        setIsUseBasePV(false);
        super.onResume();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        BaseFragment baseFragment = this.hourlyGoHomeFragment;
        if (baseFragment != null) {
            baseFragment.setUserVisibleHint(z);
        }
    }
}
