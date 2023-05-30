package com.jd.lib.push.broadcastReceiver;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.jd.lib.push.c.f;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.jdpush_new.j.g;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class HuaWeiService extends HmsMessageService {
    private void handleNewToken(String str) {
        g.a("HuaWeiService onNewToken:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f.g();
        com.jd.lib.push.a.b(2, str);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        g.i("bundlePush", "hwservice create ");
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        g.b("HWMessageReceiver", "onPushMsg1");
        try {
            g.h("get Data: " + remoteMessage.getData() + "\n getFrom: " + remoteMessage.getFrom() + "\n getTo: " + remoteMessage.getTo() + "\n getMessageId: " + remoteMessage.getMessageId() + "\n getSendTime: " + remoteMessage.getSentTime() + "\n getDataMap: " + remoteMessage.getDataOfMap() + "\n getMessageType: " + remoteMessage.getMessageType() + "\n getTtl: " + remoteMessage.getTtl() + "\n getToken: " + remoteMessage.getToken());
            String data = remoteMessage.getData();
            if (TextUtils.isEmpty(data)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(data);
            g.a("onPushMsg " + jSONObject.toString());
            PushMessageHandler.parseManufacturerPushMsg(this, jSONObject, 2, 1);
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onMessageSent(String str) {
        super.onMessageSent(str);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onNewToken(String str) {
        handleNewToken(str);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onSendError(String str, Exception exc) {
        super.onSendError(str, exc);
        g.i("bundlePush", "on send error : " + str);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onTokenError(Exception exc) {
        super.onTokenError(exc);
        g.i("bundlePush", "token error : " + exc.getLocalizedMessage());
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onNewToken(String str, Bundle bundle) {
        handleNewToken(str);
    }
}
