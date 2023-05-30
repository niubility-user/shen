package com.jingdong.common.controller;

import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.OrderQueueHttpSetting;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;

/* loaded from: classes5.dex */
public class InterNationalAuthController {
    public static void isInterNationalAuth(IMyActivity iMyActivity, HttpGroup.OnAllListener onAllListener) {
        if (iMyActivity == null || onAllListener == null) {
            return;
        }
        OrderQueueHttpSetting orderQueueHttpSetting = new OrderQueueHttpSetting();
        orderQueueHttpSetting.setFunctionId("getInternationalAuthInfo");
        orderQueueHttpSetting.setPost(true);
        orderQueueHttpSetting.setListener(onAllListener);
        orderQueueHttpSetting.setNotifyUser(true);
        orderQueueHttpSetting.setEffect(1);
        orderQueueHttpSetting.setUseFastJsonParser(true);
        iMyActivity.getHttpGroupaAsynPool().add(orderQueueHttpSetting);
    }
}
