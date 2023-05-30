package com.jingdong.jdsdk.utils;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AbsListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class JShopUtils {
    private static final String TAG = "JShopUtils";
    private static JShopUtils shopUtils;
    private JShopListScrollListener mScrollListener;
    public String mUnFollowShopId;
    public String mUnFollowVenderId;

    /* loaded from: classes14.dex */
    public interface JShopFragmentStateListener {
        void onFragmentStateChanged(boolean z);
    }

    /* loaded from: classes14.dex */
    public interface JShopListScrollListener {
        void onScroll(AbsListView absListView, int i2, int i3, int i4);

        void onScrollStateChanged(AbsListView absListView, int i2);
    }

    /* loaded from: classes14.dex */
    public interface JShopStartRequestListener {
        void startRequest();
    }

    public static Fragment getJShopGlobalListFragment(BaseActivity baseActivity) {
        return getJShopGlobalListFragment(baseActivity, null);
    }

    @Nullable
    public static Fragment getJShopListFragment(BaseActivity baseActivity) {
        return getJShopListFragment(baseActivity, null);
    }

    public static JShopUtils getJShopUtilsInstance() {
        if (shopUtils == null) {
            shopUtils = new JShopUtils();
        }
        return shopUtils;
    }

    public static void onJShopFragmentStateChanged(Fragment fragment, boolean z) {
        if (fragment instanceof JShopFragmentStateListener) {
            ((JShopFragmentStateListener) fragment).onFragmentStateChanged(z);
        }
    }

    @Deprecated
    public static void sendJShopListPV(BaseActivity baseActivity, String str, String str2) {
        sendJShopListPV(baseActivity, str, str2, "");
    }

    private static boolean showNewSearchShop(Bundle bundle) {
        if (OKLog.D && bundle != null && TextUtils.equals("\u624b\u673a", bundle.getString("keyword"))) {
            return true;
        }
        return "1".equals(JDMobileConfig.getInstance().getConfig("JDShop", "searchShop", "pageAB", "0"));
    }

    public static void startJShopFragmentRequest(Fragment fragment) {
        if (fragment instanceof JShopStartRequestListener) {
            ((JShopStartRequestListener) fragment).startRequest();
        }
    }

    public JShopListScrollListener getScrollListener() {
        return this.mScrollListener;
    }

    public void setScrollListener(JShopListScrollListener jShopListScrollListener) {
        this.mScrollListener = jShopListScrollListener;
    }

    public static Fragment getJShopGlobalListFragment(BaseActivity baseActivity, Bundle bundle) {
        try {
            return Fragment.instantiate(baseActivity, JshopConst.JSHOP_GLOBAL_SEARCH_LIST_FRGAMENT, bundle);
        } catch (Fragment.InstantiationException e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    @Nullable
    public static Fragment getJShopListFragment(BaseActivity baseActivity, Bundle bundle) {
        Fragment fragment;
        if (showNewSearchShop(bundle)) {
            fragment = AuraFragmentHelper.getInstance().newFragment(baseActivity, "com.jd.lib.searchshop.ShopListFragment");
            if (fragment != null && bundle != null) {
                fragment.setArguments(bundle);
            }
        } else {
            fragment = null;
        }
        if (fragment == null) {
            try {
                return Fragment.instantiate(baseActivity, JshopConst.JSHOP_SEARCH_LIST_FRGAMENT, bundle);
            } catch (Fragment.InstantiationException e2) {
                OKLog.e(TAG, e2);
                return fragment;
            }
        }
        return fragment;
    }

    public static void sendJShopListPV(BaseActivity baseActivity, String str, String str2, String str3) {
        String name = getJShopListFragment(baseActivity).getClass().getName();
        boolean isEmpty = TextUtils.isEmpty(str);
        String str4 = DYConstants.DY_NULL_STR;
        if (isEmpty) {
            str = DYConstants.DY_NULL_STR;
        }
        String stringFromPreference = CommonBase.getStringFromPreference("searchDeviceId", "");
        if (!TextUtils.isEmpty(stringFromPreference)) {
            str4 = stringFromPreference;
        }
        JDMtaUtils.sendPagePv(baseActivity, name, str + "_1_" + str4 + CartConstant.KEY_YB_INFO_LINK + str3, "SearchShop_ResultMain", "");
    }
}
