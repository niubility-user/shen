package com.jingdong.app.mall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.jdsdk.constant.PDConstant;

/* loaded from: classes4.dex */
public class s {
    public static void a(Activity activity, Intent intent, boolean z) {
        if (activity == null || intent == null) {
            return;
        }
        if (activity.getParent() != null && (activity instanceof BaseActivity)) {
            if (z) {
                intent.putExtra("com.360buy:navigationDisplayFlag", 0);
            } else {
                intent.putExtra("com.360buy:navigationDisplayFlag", -1);
            }
            ((BaseActivity) activity).startActivityInFrame(intent);
            return;
        }
        activity.startActivity(intent);
    }

    public static void b(Activity activity, String str, SourceEntity sourceEntity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("keyword", str);
        intent.putExtra("source", sourceEntity);
        DeepLinkJShopHomeHelper.gotoJshopSearchShopList(activity, intent.getExtras());
    }

    public static void c(Activity activity) {
        if (activity == null) {
        }
    }

    public static void d(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        CommonUtilEx.getInstance().backToHomePage(baseActivity);
    }

    public static void e(Activity activity, Bundle bundle) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("com.360buy:clearTopFlag", true);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        JDReactAuraHelper.getInstance().launchMovieHomeActivity(activity, intent);
    }

    public static void f(BaseActivity baseActivity, String str) {
        if (baseActivity == null) {
            return;
        }
        if ("tap_flow".equals(str)) {
            DeepLinkChargeHelper.startFlowChargeActivity(baseActivity);
        } else if ("tap_qq".equals(str)) {
            DeepLinkChargeHelper.startQQChargeActivity(baseActivity);
        } else if ("tap_game".equals(str)) {
            DeepLinkChargeHelper.startGameChargeActivity(baseActivity);
        } else if ("tap_life".equals(str)) {
            DeepLinkChargeHelper.startLifeChargeActivity(baseActivity);
        } else {
            DeepLinkChargeHelper.startPhoneChargeActivity(baseActivity);
        }
    }

    public static void g(Context context, Bundle bundle) {
        h(context, bundle, null);
    }

    public static void h(Context context, Bundle bundle, SourceEntity sourceEntity) {
        if (context == null || bundle == null) {
            return;
        }
        if (sourceEntity != null) {
            bundle.putSerializable("source", sourceEntity);
        }
        if (!TextUtils.isEmpty(bundle.getString("clickUrl"))) {
            bundle.putString("targetUrl", bundle.getString("clickUrl"));
        }
        DeeplinkProductDetailHelper.startProductDetail(context, bundle);
    }

    public static void i(Context context, Long l2, String str, SourceEntity sourceEntity) {
        if (context == null || l2 == null) {
            return;
        }
        g(context, DeeplinkProductDetailHelper.BundleBuilder.from(l2.longValue()).imageTitlePrice(null, str, null).sourceEntity(sourceEntity).build());
    }

    public static void j(Activity activity, Bundle bundle, int i2, SourceEntity sourceEntity) {
        if (activity == null || bundle == null) {
            return;
        }
        bundle.putBoolean(PDConstant.EXTRA_FROM_CAR, true);
        if (sourceEntity != null) {
            bundle.putSerializable("source", sourceEntity);
        }
        DeeplinkProductDetailHelper.startProductDetailForResult(activity, bundle, i2);
    }

    public static void k(Activity activity, Long l2, String str, int i2, SourceEntity sourceEntity) {
        if (activity == null || l2 == null) {
            return;
        }
        j(activity, DeeplinkProductDetailHelper.BundleBuilder.from(l2.longValue()).imageTitlePrice(null, str, null).sourceEntity(sourceEntity).build(), i2, null);
    }

    public static void l(Activity activity, Bundle bundle) {
        DeepLinkProductListHelper.startProductListActivity(activity, bundle);
    }

    public static void m(Activity activity, Intent intent) {
        a(activity, intent, false);
    }

    public static void n(BaseActivity baseActivity, Bundle bundle) {
        if (baseActivity == null) {
            return;
        }
        DeepLinkCartHelper.startCartMain(baseActivity, bundle);
    }
}
