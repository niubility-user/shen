package com.jingdong.common.utils.pay;

import android.app.Activity;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.CommonBase;

/* loaded from: classes6.dex */
public interface IPay {
    void doPay(Activity activity, JDJSONObject jDJSONObject, PayCallbackListener payCallbackListener);

    void doPayFinishForward(String str, CommonBase.BrowserCashierUrlListener browserCashierUrlListener);

    void doPayFinishForward(String str, CommonBase.BrowserNewUrlListener browserNewUrlListener);

    void doPayWithWebURL(Activity activity, String str, String str2);

    void showSuccessPage(Activity activity, Bundle bundle, String str, String str2);
}
