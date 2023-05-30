package com.jd.lib.push.broadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.mta.b;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MZMessageCenterReceiver extends MzPushMessageReceiver {
    private static final String TAG = "MZMessageCenterReceiver";

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onMessage(Context context, String str) {
        g.i(TAG, "onMessage " + str);
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            g.b(TAG, "onMessage " + jSONObject.toString());
            PushMessageHandler.parseManufacturerPushMsg(context, jSONObject, 3, 1);
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {
        g.i(TAG, "NEWonNotificationClicked ");
        try {
            String selfDefineContentString = mzPushMessage.getSelfDefineContentString();
            if (!TextUtils.isEmpty(selfDefineContentString)) {
                g.b(TAG, "onNotificationClicked " + selfDefineContentString);
                ManufacturePushMessageHandler.handleMessage(context, selfDefineContentString, 3);
            }
        } catch (Exception e2) {
            g.e(TAG, "\u4f20\u9012\u6570\u636e\u5f02\u5e38 ", e2);
            PushMessageHandler.jumpDefault(context);
        }
        super.onNotificationClicked(context, mzPushMessage);
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onNotifyMessageArrived(Context context, String str) {
        g.i(TAG, "onNotifyMessageArrived " + str);
        super.onNotifyMessageArrived(context, str);
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onRegister(Context context, String str) {
        g.i(TAG, "onRegister pushID " + str);
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        g.k(TAG, "pushId " + registerStatus.getPushId() + LangUtils.SINGLE_SPACE + context.getPackageName());
        try {
            if (!TextUtils.equals(BasicPushStatus.SUCCESS_CODE, registerStatus.getCode())) {
                b.b().h(3, Integer.valueOf(registerStatus.getCode()).intValue());
            }
        } catch (Exception unused) {
        }
        b.b().l(R2.color.button_r_c_font_color_dark);
        com.jd.lib.push.a.b(3, registerStatus.getPushId());
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onUnRegister(Context context, boolean z) {
        g.i(TAG, "onUnRegister " + z);
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        g.i(TAG, "onUnRegisterStatus " + unRegisterStatus + LangUtils.SINGLE_SPACE + context.getPackageName());
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        super.onUpdateNotificationBuilder(pushNotificationBuilder);
    }

    @Override // com.meizu.cloud.pushsdk.MzPushMessageReceiver
    public void onMessage(Context context, Intent intent) {
        g.i(TAG, "onMessage fly3");
        super.onMessage(context, intent);
    }
}
