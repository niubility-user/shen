package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMemberCodeHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = JumpUtil.VALUE_DES_MEMBERCODE)
/* loaded from: classes19.dex */
public class JumpToMemberCode extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("subDes");
            if (!TextUtils.isEmpty(string)) {
                DeepLinkMemberCodeHelper.startActivity(context, bundle, string);
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
