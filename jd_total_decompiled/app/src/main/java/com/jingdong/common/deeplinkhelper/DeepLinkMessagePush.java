package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.messagecenter.BadgeUtil;
import com.jingdong.common.messagecenter.MessageActivityUtils;
import com.jingdong.common.messagecenter.MessageTabRedCtrl;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkMessagePush {
    private static final String TAG = "DeepLinkMessagePush";

    public static void startMessageLockScreenActivity(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("messagelockscreenactivity").toString(), bundle, i2);
    }

    public static void startPushService(Context context, Bundle bundle) {
        try {
            if (context instanceof Activity) {
                MessageActivityUtils.getInstance().setActivity((Activity) context);
                if (OKLog.D) {
                    OKLog.d(TAG, "context instanceof Activity");
                }
            }
            MessageTabRedCtrl.getInstance().requestMessage();
            BadgeUtil.resetBadgeCount(context);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }
}
