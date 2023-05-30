package com.jd.lib.push.pushIntentService;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.InitApplication;
import com.jingdong.common.messagecenter.PushMessageHandler;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdpush_new.PushIntentService;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdsdk.config.Configuration;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MessageIntentService extends PushIntentService {
    private static final int FROM = 0;
    private static final String TAG = "MessageIntentService";

    @Override // com.jingdong.jdpush_new.PushIntentService
    public void onMessageArrived(Context context, String str) {
        InitApplication.instance(context);
        if (!Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() || CommonBase.getJdSharedPreferences().getBoolean(Configuration.HAS_INIT_TIP, false)) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                g.b(TAG, "push\u6d88\u606f:" + str);
                JSONObject jSONObject = new JSONObject(str);
                g.b(TAG, "onMessageArrived " + jSONObject.toString());
                PushMessageHandler.parseJDChannePushMsg(context, jSONObject, 0);
            } catch (Exception unused) {
                g.d(TAG, "msg\u6570\u636e\u5f02\u5e38:" + str);
            }
        }
    }
}
