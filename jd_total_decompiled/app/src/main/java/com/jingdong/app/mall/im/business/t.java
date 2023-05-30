package com.jingdong.app.mall.im.business;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.DeepLinkHelper;
import com.jingdong.service.impl.IMOpenApp;

/* loaded from: classes4.dex */
public class t extends IMOpenApp {
    private static final String a = "t";

    @Override // com.jingdong.service.impl.IMOpenApp, com.jingdong.service.service.OpenAppService
    public void gotoHomePage(Context context) {
        startOpenApp(context, "openjd://virtual?params={\"category\":\"jump\",\"des\":\"HomePage\"}");
    }

    @Override // com.jingdong.service.impl.IMOpenApp, com.jingdong.service.service.OpenAppService
    public void gotoOrderPage(Context context, String str) {
        OKLog.d("bundleicssdkservice", a + "---gotoOrderPage orderId:" + str);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("orderId", str);
            bundle.putBoolean("isNew", true);
            bundle.putString("from", "DongdongChat_Main");
            DeepLinkHelper.startActivityDirect(context, "neworderdetailactivity", bundle);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.service.impl.IMOpenApp, com.jingdong.service.service.OpenAppService
    public void gotoProductDetailPage(Context context, String str, String str2) {
        OKLog.d("bundleicssdkservice", a + "---gotoProductDetailPage pid:" + str + " param: " + str2);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            new OpenAppJumpBuilder.Builder().des("productDetail").map("skuId", str).map(JshopConst.JSHOP_M_PARAM, "{\"dongdong\":\"" + str2 + "\"}").build().jump(context);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.service.impl.IMOpenApp, com.jingdong.service.service.OpenAppService
    public void gotoUserInfoPage(Context context) {
        OKLog.d("bundleicssdkservice", a + "---gotoUserInfoPage");
        new OpenAppJumpBuilder.Builder(Uri.parse("openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"userinfoeditpage\",\"sourceType\":\"m_customer\"}")).build().jump(context);
    }

    @Override // com.jingdong.service.impl.IMOpenApp, com.jingdong.service.service.OpenAppService
    public void startOpenApp(Context context, String str) {
        OKLog.d("bundleicssdkservice", a + "---startOpenApp params:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.toLowerCase().startsWith("openapp")) {
            try {
                OpenAppJumpController.dispatchJumpRequestInApp(context, Uri.parse(str));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (context == null) {
        } else {
            try {
                Bundle bundle = new Bundle();
                URLParamMap uRLParamMap = new URLParamMap();
                uRLParamMap.put(RemoteMessageConst.TO, str);
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                bundle.putSerializable("urlParamMap", serializableContainer);
                DeepLinkCommonHelper.startWebActivity(context, bundle, true);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.service.impl.IMOpenApp, com.jingdong.service.service.OpenAppService
    public void gotoProductDetailPage(Context context, String str) {
        OKLog.d("bundleicssdkservice", a + "---gotoProductDetailPage pid:" + str);
        Bundle bundle = new Bundle();
        bundle.putString("skuId", str);
        JumpUtil.execJumpByDes("productDetail", context, bundle);
    }
}
