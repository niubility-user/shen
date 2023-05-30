package com.jd.cashier.app.jdlibcutter.impl.router;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.cashier.app.jdlibcutter.protocol.router.IWebRouter;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes13.dex */
public class WebRouterImpl implements IWebRouter {
    private static final String HTTPS_SCHEME = "https";
    private static final String HTTP_SCHEME = "http";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IRouterAnalyzer
    public boolean isTargetRouter(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        return "http".equalsIgnoreCase(parse.getScheme()) || "https".equalsIgnoreCase(parse.getScheme());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IWebRouter
    public void routerToWeb(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        DeepLinkMHelper.startWebActivity(context, str);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IWebRouter
    public void routerToWebWithoutMore(Context context, String str) {
        if (context != null) {
            URLParamMap uRLParamMap = new URLParamMap();
            uRLParamMap.put(RemoteMessageConst.TO, str);
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            Bundle bundle = new Bundle();
            bundle.putSerializable("urlParamMap", serializableContainer);
            bundle.putString("urlAction", RemoteMessageConst.TO);
            bundle.putBoolean(MBaseKeyNames.IS_SHOW_MORE_BTN, false);
            DeepLinkMHelper.startWebActivity(context, bundle);
        }
    }
}
