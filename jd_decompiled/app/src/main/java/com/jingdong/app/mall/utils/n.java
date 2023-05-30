package com.jingdong.app.mall.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.utils.JumpMessageActivityUtil;

/* loaded from: classes4.dex */
public class n {
    public static void a(Context context) {
        JumpMessageActivityUtil.jumpToMessageCenter(context);
    }

    public static void b(Context context, Bundle bundle) {
        String string = bundle.getString("msgSec");
        if (TextUtils.isEmpty(string)) {
            a(context);
            return;
        }
        try {
            JDJSONObject parseObject = JDJSON.parseObject(string);
            String optString = parseObject.optString("accountName");
            String optString2 = parseObject.optString("accountType");
            String optString3 = parseObject.optString("bubbleCount");
            Bundle bundle2 = new Bundle();
            bundle2.putString("containerName", optString);
            bundle2.putString(NotificationMessageSummary.CONTAINER_ID, optString2);
            bundle2.putString("bubbleCount", optString3);
            JumpMessageActivityUtil.jumpToSecond(context, bundle2);
        } catch (Exception unused) {
            a(context);
        }
    }
}
