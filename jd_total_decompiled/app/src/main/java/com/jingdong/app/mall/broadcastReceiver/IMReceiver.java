package com.jingdong.app.mall.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.app.mall.j.a;
import com.jingdong.app.mall.j.c;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;

/* loaded from: classes19.dex */
public class IMReceiver extends BroadcastReceiver {
    private static final String a = IMReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Log.D) {
            Log.d(a, " onReceive -->> intent " + intent.getAction());
        }
        if (!Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() || CommonBase.getJdSharedPreferences().getBoolean(Configuration.HAS_INIT_TIP, false)) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (action.equals("com.jd.IM_JIMI_SEND_UNREAD_MSG_COUNT")) {
                int intExtra = intent.getIntExtra("unreadMsgCount", 0);
                if (Log.D) {
                    Log.d(a, " onReceive -->> count " + intExtra);
                }
                a.a().c(intExtra);
            } else if (action.equals("com.jd.IM_JIMI_RESPONSE_LATEST_MSG")) {
                if (TextUtils.isEmpty(intent.getStringExtra("customerName")) && TextUtils.isEmpty(intent.getStringExtra("content"))) {
                    return;
                }
                c cVar = new c();
                cVar.content = intent.getStringExtra("content");
                cVar.date = intent.getStringExtra(JDDateTimePickerDialog.SELECT_DATE_MODE);
                cVar.customerName = intent.getStringExtra("customerName");
                cVar.customertype = intent.getStringExtra("customerType");
                cVar.customerHeadIconUrl = intent.getStringExtra("customerHeadIconUrl");
                a.a().b(cVar);
            }
        }
    }
}
