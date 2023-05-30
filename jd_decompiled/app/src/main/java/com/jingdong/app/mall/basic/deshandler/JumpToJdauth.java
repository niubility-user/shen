package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.vivo.push.PushClientConstants;

@Des(des = JumpUtil.VALUE_DES_JDAUTH)
/* loaded from: classes19.dex */
public class JumpToJdauth extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        bundle.getString("appId");
        String string = bundle.getString(PairKey.APP_KEY);
        String string2 = bundle.getString(PushClientConstants.TAG_PKG_NAME);
        String string3 = bundle.getString("actName");
        String string4 = bundle.getString("redirect_url");
        String format = String.format(CommonBase.getJdSharedPreferences().getString("JDOAuthURL", "https://kploauth.jd.com/oauth/m/authorize?response_type=code") + "&client_id=%s&redirect_uri=%s&state=%s&device_type=%s", string, string4, StatisticsReportUtil.readDeviceUUID(), "android");
        URLParamMap uRLParamMap = new URLParamMap();
        if (!TextUtils.isEmpty(format)) {
            uRLParamMap.put(RemoteMessageConst.TO, format);
        }
        if (context != null) {
            Intent intent = new Intent(context, WebActivity.class);
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            intent.putExtra("redirect_url", string4);
            intent.putExtra("packageName", string2);
            intent.putExtra(Constants.JLOG_ACTIVITYNAME_PARAM_KEY, string3);
            intent.putExtra(MBaseKeyNames.IS_FROM_AUTHSDK, true);
            intent.putExtra("urlParamMap", serializableContainer);
            intent.putExtra("urlAction", RemoteMessageConst.TO);
            context.startActivity(intent);
        }
        c(context);
    }
}
