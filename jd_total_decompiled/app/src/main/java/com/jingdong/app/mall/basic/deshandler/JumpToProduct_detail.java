package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;

@Des(des = "productDetail,skudetail")
/* loaded from: classes19.dex */
public class JumpToProduct_detail extends a {
    private String b = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"JDFlutterView\",\"dragBackEnable\":\"0\",\"flutterRouterName\":\"JDFlutterMiniProduct\",\"isFromFlutterVCEnable\":\"1\",\"isShowNativeNavBar\":\"0\",\"param\":{\"sourceExt\":50,\"seedSource\":\"0\",\"skus\":[\"%1$s\"],\"last_click_id\":\"\",\"rec_broker\":\"\"}}";

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String str = "";
        String string = bundle.getString("skuId");
        String config = JDMobileConfig.getInstance().getConfig("JDProductdetail", "downgradeToProduct", "DowngradeToProductPage");
        if (SwitchQueryFetcher.isXTime()) {
            config = "0";
        }
        try {
            str = bundle.getString("jumpType", "").replaceAll(" +", "");
        } catch (Exception e2) {
            if (Log.E) {
                Log.d(this.a, "forwardProductDetail ", e2);
            }
        }
        if (Log.D) {
            Log.d(this.a, "forwardProductDetail skuId : " + string);
            Log.d(this.a, "forwardProductDetail landPageId : " + bundle.getString("landPageId"));
            Log.d(this.a, "bundle.jumpType ==" + str);
            Log.d(this.a, "\u964d\u7ea7\u5f00\u5173\uff1aqueryParamKey ==" + config);
        }
        if (!TextUtils.isEmpty(string) && (context instanceof Activity)) {
            try {
                if (TextUtils.equals(config, "1")) {
                    if (TextUtils.equals(str, "mini")) {
                        String format = String.format(this.b, string);
                        this.b = format;
                        new OpenAppJumpBuilder.Builder(Uri.parse(format)).build().jump(context);
                    } else {
                        DeeplinkProductDetailHelper.startProductDetailFromOpenApp(context, bundle);
                    }
                } else {
                    DeeplinkProductDetailHelper.startProductDetailFromOpenApp(context, bundle);
                }
            } catch (Exception e3) {
                if (Log.E) {
                    Log.d(this.a, "forwardProductDetail ", e3);
                }
            }
            c(context);
            return;
        }
        c(context);
    }
}
