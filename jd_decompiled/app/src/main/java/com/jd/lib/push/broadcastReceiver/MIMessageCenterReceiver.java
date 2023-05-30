package com.jd.lib.push.broadcastReceiver;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.mta.b;
import com.jingdong.sdk.platform.business.personal.R2;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MIMessageCenterReceiver extends PushMessageReceiver {
    private String mRegId;

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        List<String> commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : commandArguments.get(0);
        if ("register".equals(command)) {
            b.b().l(1100);
            if (miPushCommandMessage.getResultCode() == 0) {
                g.j("onCommandResult is called. " + str);
                b.b().l(R2.attr.layout_constraintCircleAngle);
                this.mRegId = str;
                com.jd.lib.push.a.b(1, str);
                return;
            }
            b.b().h(1, (int) miPushCommandMessage.getResultCode());
        }
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        g.j("onNotificationMessageArrived " + miPushMessage.toString());
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        try {
            if (TextUtils.isEmpty(miPushMessage.getContent())) {
                return;
            }
            g.a("onNotificationMessageClicked " + miPushMessage.getContent());
            ManufacturePushMessageHandler.handleMessage(context, miPushMessage.getContent(), 1);
        } catch (Exception e2) {
            g.f("\u4f20\u9012\u6570\u636e\u5f02\u5e38 ", e2);
            PushMessageHandler.jumpDefault(context);
        }
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        g.j("onReceivePassThroughMessage is called. " + miPushMessage.toString());
        try {
            if (TextUtils.isEmpty(miPushMessage.toString())) {
                return;
            }
            JSONObject jSONObject = new JSONObject(miPushMessage.toString());
            g.a("onReceivePassThroughMessage " + jSONObject.toString());
            PushMessageHandler.parseManufacturerPushMsg(context, jSONObject, 1, 1);
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        g.j("onReceiveRegisterResult is called. " + miPushCommandMessage.toString());
        String command = miPushCommandMessage.getCommand();
        List<String> commandArguments = miPushCommandMessage.getCommandArguments();
        String str = (commandArguments == null || commandArguments.size() <= 0) ? null : commandArguments.get(0);
        if ("register".equals(command) && miPushCommandMessage.getResultCode() == 0) {
            this.mRegId = str;
        }
    }
}
