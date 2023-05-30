package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.corelib.utils.Log;

@Des(des = "token")
/* loaded from: classes19.dex */
public class JumpToToken extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("tokenKey");
        bundle.getString("payId");
        String string2 = bundle.getString("isBind");
        String string3 = bundle.getString("toMSM");
        String string4 = bundle.getString("action");
        String string5 = bundle.getString("num");
        if (!TextUtils.isEmpty(string)) {
            if (!TextUtils.isEmpty(string2)) {
                CommonUtilEx.getInstance().toClient(bundle.getString("tokenKey"), string2, null);
            } else if (!TextUtils.isEmpty(string3)) {
                CommonUtilEx.getInstance().toClient(bundle.getString("tokenKey"), string3, null);
            } else {
                CommonUtilEx.getInstance().toClient(bundle.getString("tokenKey"), "", null);
            }
        } else if (!TextUtils.isEmpty(string4)) {
            if (LoginConstans.FREGMENT_LOGIN_FLAG.equals(string4)) {
                CommonUtilEx.getInstance().toClient(null, string4, null);
            } else if (NotificationCompat.CATEGORY_CALL.equals(string4)) {
                CommonUtilEx.getInstance().toClient(null, string4, string5);
            }
            if (Log.D) {
                System.out.println("jumpControll \u76d1\u542c\u70b9\u51fb\u53bb\u767b\u9646\u6309\u94ae\u6216\u8005\u6253\u7535\u8bdd+++++action:" + string4);
            }
        }
        c(context);
    }
}
