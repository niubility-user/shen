package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppUtil;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.sdk.oklog.OKLog;

@Des(des = JumpUtil.VAULE_DES_DM)
/* loaded from: classes19.dex */
public class JumpToDm extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("dmurl");
        String string2 = bundle.getString("shareUrl");
        String string3 = bundle.getString("shareContent");
        String string4 = bundle.getString(MBaseKeyNames.SHARE_TITLE);
        String string5 = bundle.getString("shareAvatar");
        SourceEntity openAppSourceEntity = SourceEntity.getOpenAppSourceEntity(bundle);
        if (OKLog.D) {
            OKLog.d(this.a, " -->> dmUrl : " + string);
        }
        if (openAppSourceEntity != null) {
            string = OpenAppUtil.appendUrlParams(OpenAppUtil.appendUrlParams(string, "resourceType", openAppSourceEntity.getSourceType()), "resourceValue", openAppSourceEntity.getSourceValue());
        }
        if (OKLog.D) {
            OKLog.d(this.a, " -->> dmUrl : " + string);
        }
        if (!TextUtils.isEmpty(string)) {
            if (!TextUtils.isEmpty(string2)) {
                CommonBridge.goToMWithUrlShareInfo(e.b().a(), string, new ShareInfo(string2, string4, string3, string5, ""));
            } else {
                CommonBridge.goToMWithUrl(e.b().a(), string);
            }
        }
        c(context);
    }
}
