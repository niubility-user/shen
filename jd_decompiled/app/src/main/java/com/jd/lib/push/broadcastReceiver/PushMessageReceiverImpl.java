package com.jd.lib.push.broadcastReceiver;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.jdpush_new.j.g;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

/* loaded from: classes16.dex */
public class PushMessageReceiverImpl extends OpenClientPushMessageReceiver {
    private static final String TAG = "PushMessageReceiverImpl";

    @Override // com.vivo.push.sdk.OpenClientPushMessageReceiver, com.vivo.push.sdk.PushMessageCallback
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage uPSNotificationMessage) {
        String skipContent = uPSNotificationMessage.getSkipContent();
        g.a(skipContent);
        try {
            if (TextUtils.isEmpty(skipContent)) {
                return;
            }
            g.b(TAG, "onNotificationMessageClicked " + skipContent);
            ManufacturePushMessageHandler.handleMessage(context, skipContent, 8);
        } catch (Exception e2) {
            g.e(TAG, "\u4f20\u9012\u6570\u636e\u5f02\u5e38 ", e2);
            PushMessageHandler.jumpDefault(context);
        }
    }

    @Override // com.vivo.push.sdk.OpenClientPushMessageReceiver, com.vivo.push.sdk.PushMessageCallback
    public void onReceiveRegId(Context context, String str) {
        g.a("onReceiveRegId regId = " + str);
        com.jd.lib.push.a.b(8, str);
    }
}
