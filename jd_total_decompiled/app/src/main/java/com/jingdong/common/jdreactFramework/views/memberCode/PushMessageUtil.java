package com.jingdong.common.jdreactFramework.views.memberCode;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class PushMessageUtil {
    private void onMessageEvent20001(Context context, String str) {
        Intent intent = new Intent("code_view_push_msg_action");
        intent.putExtra("msg", str);
        context.sendBroadcast(intent);
    }
}
