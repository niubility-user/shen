package com.jingdong.app.mall.bundle.order_center_isv_core.util;

import android.content.Intent;
import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.hybrid.utils.StartHybridActivityUtils;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class OrderISVStartVirtualOrderDetail {
    public static final int FLAG_FOLLOW_CHARGE = 87;
    public static final int FLAG_GAME_CHARGE = 34;
    public static final int FLAG_MOVIE_ORDER = 43;
    public static final int FLAG_PHONE_CHARGE = 37;
    public static final int FLAG_TRAVEL_EX = 83;
    public static final int FLAG_TRAVEL_IN = 35;
    private static final String TAG = "StartVirtualOrderDetail";

    public static void jumpToActivity(BaseActivity baseActivity, String str, int i2, JSONObjectProxy jSONObjectProxy) {
        Bundle bundle = new Bundle();
        if (i2 == 34) {
            bundle.putBoolean("com.360buy:clearTopFlag", true);
            bundle.putString("orderId", str);
            DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_GAMECHARGEDETAIL_ACTIVITY, bundle);
        } else if (i2 == 35) {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.putExtra("pluginname", "flightDetail");
            intent.putExtra("orderId", str);
            intent.putExtra("type", "1");
            intent.putExtra("pauseBackRefresh", "yes");
            StartHybridActivityUtils.startHybridActivity(baseActivity.getApplicationContext(), intent);
        } else if (i2 == 37) {
            bundle.putBoolean("com.360buy:clearTopFlag", true);
            bundle.putString("orderId", str);
            DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_PHONECHARGEORDERDETAIL_ACTIVITY, bundle);
        } else if (i2 == 43) {
            Intent intent2 = new Intent();
            intent2.putExtra("com.360buy:clearTopFlag", true);
            intent2.putExtra("orderId", str);
            int i3 = 2;
            try {
                i3 = jSONObjectProxy.getInt("type");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            intent2.putExtra("type", i3);
            JDReactAuraHelper.getInstance().launchMovieHomeActivity(baseActivity, intent2);
        } else if (i2 == 83) {
            Intent intent3 = new Intent();
            intent3.addFlags(268435456);
            intent3.putExtra("pluginname", "flightDetail");
            intent3.putExtra("orderId", str);
            intent3.putExtra("type", "2");
            intent3.putExtra("pauseBackRefresh", "yes");
            StartHybridActivityUtils.startHybridActivity(baseActivity.getApplicationContext(), intent3);
        } else if (i2 != 87) {
        } else {
            long pareStr2Long = OrderISVUtil.pareStr2Long(str);
            if (pareStr2Long != -1) {
                bundle.putBoolean("com.360buy:clearTopFlag", true);
                bundle.putLong("orderId", pareStr2Long);
                DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_PHONECHARGEFLOWORDERDETAIL_ACTIVITY, bundle);
            }
        }
    }
}
