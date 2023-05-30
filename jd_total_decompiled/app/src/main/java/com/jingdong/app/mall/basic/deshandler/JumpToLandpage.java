package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = JumpUtil.VALUE_DES_WEB_LANDPAGE)
/* loaded from: classes19.dex */
public class JumpToLandpage extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("landpageUrl");
        URLParamMap uRLParamMap = new URLParamMap();
        if (!TextUtils.isEmpty(string)) {
            uRLParamMap.put(RemoteMessageConst.TO, string);
        }
        if (context != null) {
            Intent intent = new Intent(context, WebActivity.class);
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            intent.putExtra("urlParamMap", serializableContainer);
            intent.putExtra("urlAction", RemoteMessageConst.TO);
            context.startActivity(intent);
        }
        c(context);
    }
}
