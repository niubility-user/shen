package com.jingdong.app.mall.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes4.dex */
public class ForwardActionService extends Service {
    public void a() {
        stopSelf();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i2) {
        super.onStart(intent, i2);
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra(NotificationMessageSummary.LAND_PAGE_URL);
        if (Log.D) {
            Log.d("TEST", " ForwardActionService -->> onStart  action : " + stringExtra);
        }
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        new URLParamMap().put(RemoteMessageConst.TO, stringExtra);
        Bundle bundle = new Bundle();
        bundle.putString("action", RemoteMessageConst.TO);
        bundle.putString("url", stringExtra);
        JumpUtil.execJumpByDes("m", this, bundle);
        a();
    }
}
