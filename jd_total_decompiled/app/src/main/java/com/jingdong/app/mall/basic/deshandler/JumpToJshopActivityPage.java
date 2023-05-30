package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = JumpUtil.VALUE_DES_JSHOP_ACTIVITY_PAGE)
/* loaded from: classes19.dex */
public class JumpToJshopActivityPage extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String builder = new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JSHOP_TOPICWARE_ACTIVITY).toString();
        bundle.putBoolean("fromTemplate", true);
        bundle.putString("type", "gotoTopicWare");
        DeepLinkDispatch.startActivityDirect(context, builder, bundle);
        c(context);
    }
}
