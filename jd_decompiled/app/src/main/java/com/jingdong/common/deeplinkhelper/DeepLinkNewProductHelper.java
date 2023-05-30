package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.newproduct.a;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkNewProductHelper {
    private static final String SWITCH_KEY_CLOSE_NEW_PRODUCT_PAGE = "npCloseNewProductPage";
    private static final String SWITCH_KEY_CLOSE_NEW_PRODUCT_PAGE_PRELOAD = "npCloseNewProductPagePreload";

    public static void startNewProductActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT).toString(), bundle);
    }

    public static void startNewProductCoreActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT_CORE).toString(), bundle);
    }

    public static void startNewProductDropActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT_DROP).toString(), bundle);
    }

    public static void startNewProductHomeTabActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT_HOME_TAB).toString(), bundle);
    }

    public static void startNewProductInfoActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT_INFO).toString(), bundle);
    }

    public static void startNewProductPageActivity(Context context, Bundle bundle) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SWITCH_KEY_CLOSE_NEW_PRODUCT_PAGE, false)) {
            if (bundle != null) {
                bundle.putString("newArrivalsType", "1");
            }
            DeepLinkNewArrivalsHelper.startNewArrivalsActivity(context, bundle);
            return;
        }
        if (!SwitchQueryFetcher.getSwitchBooleanValue(SWITCH_KEY_CLOSE_NEW_PRODUCT_PAGE_PRELOAD, false)) {
            new a().a(bundle);
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEW_PRODUCT_PAGE).toString(), bundle);
    }

    public static void startNewProductRNActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT_RN).toString(), bundle);
    }

    public static void startNewProductTendencyActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEWPRODUCT_TENDENCY).toString(), bundle);
    }

    public static void startReportDetailActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_TRIAL_REPORT_DETAIL).toString(), bundle);
    }

    public static void startReportListActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_TRIAL_REPORT_LIST).toString(), bundle);
    }
}
