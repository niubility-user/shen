package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes.dex */
public interface ICommon {
    void backToHomePage(Context context);

    boolean checkSDKForPay();

    void doPay(Activity activity, String str);

    void forwardWebActivity(IMyActivity iMyActivity, String str);

    void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap);

    void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap, boolean z);

    void forwardWebActivityForAction(Context context, String str, URLParamMap uRLParamMap);

    void goToShoppingCartPage(IMyActivity iMyActivity, boolean z);

    void goToShoppingCartPageSingle(IMyActivity iMyActivity);

    void startActivityInFrame(Context context, Intent intent);

    void toClient(String str, String str2, String str3);
}
