package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = JumpUtil.VALUE_DES_H5_GAME)
/* loaded from: classes19.dex */
public class JumpToH5game extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (!(context instanceof BaseActivity)) {
            c(context);
            return;
        }
        String string = bundle.getString("action");
        if (!TextUtils.isEmpty(string)) {
            new URLParamMap().put(RemoteMessageConst.TO, string);
            CommonBridge.forwardWebActivity(context, RemoteMessageConst.TO, string, false, MBaseKeyNames.VALUE_ORIENTATION);
        }
        c(context);
    }
}
