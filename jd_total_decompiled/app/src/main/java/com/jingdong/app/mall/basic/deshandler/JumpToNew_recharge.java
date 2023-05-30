package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_NEW_RECHARGE)
/* loaded from: classes19.dex */
public class JumpToNew_recharge extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("chargeType");
        if (string == null) {
            string = "";
        }
        string.hashCode();
        char c2 = '\uffff';
        int i2 = 3;
        switch (string.hashCode()) {
            case -1725108154:
                if (string.equals("kMobileChargeView")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1328166378:
                if (string.equals("KGamechargeView")) {
                    c2 = 1;
                    break;
                }
                break;
            case -354505611:
                if (string.equals("kQbChargeView")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1213221010:
                if (string.equals("kFlowChargeView")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            default:
                i2 = 0;
                break;
            case 1:
                break;
            case 2:
                i2 = 2;
                break;
            case 3:
                i2 = 1;
                break;
        }
        DeepLinkChargeHelper.startChargeActivity(context, i2);
        c(context);
    }
}
