package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommentHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = "comment")
/* loaded from: classes19.dex */
public class JumpToComment extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("subDes");
            if (!TextUtils.isEmpty(string)) {
                DeepLinkCommentHelper.startCommentPage(context, bundle, string);
            } else {
                URLParamMap uRLParamMap = new URLParamMap();
                uRLParamMap.put(RemoteMessageConst.TO, "https://vs.m.jd.com/commentAppdau/index.html");
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                bundle.putSerializable("urlParamMap", serializableContainer);
                bundle.putString("urlAction", RemoteMessageConst.TO);
                DeepLinkCommonHelper.startWebActivity(context, bundle, true);
            }
        }
        c(context);
    }
}
