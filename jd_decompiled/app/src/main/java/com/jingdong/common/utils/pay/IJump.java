package com.jingdong.common.utils.pay;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.CommonBase;

/* loaded from: classes6.dex */
public interface IJump {
    void doPayFinishForward(String str, CommonBase.BrowserCashierUrlListener browserCashierUrlListener);

    void doPayFinishForward(String str, CommonBase.BrowserNewUrlListener browserNewUrlListener);

    @Deprecated
    void getJumpToken(Activity activity, Bundle bundle, CommonBase.BrowserNewUrlListener browserNewUrlListener);

    @Deprecated
    void jumpToHomeActivity(Activity activity, Bundle bundle);

    void jumpToInterfaceActivity(Activity activity, Bundle bundle);

    void jumpToOrderListActivity(Activity activity, Bundle bundle);

    void jumpToWebActivity(BaseActivity baseActivity, Bundle bundle);

    void reDoJDPay(Activity activity);

    void reDoUnionPay(Activity activity);

    void unionAndWeiXinPay(Activity activity, Bundle bundle, CommonBase.ProgresslListener progresslListener);
}
