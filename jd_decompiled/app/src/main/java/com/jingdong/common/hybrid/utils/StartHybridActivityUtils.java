package com.jingdong.common.hybrid.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class StartHybridActivityUtils {
    private static final String TAG = "StartHybridActivityUtil";

    public static synchronized void startHybridActivity(Context context, Intent intent) {
        synchronized (StartHybridActivityUtils.class) {
            try {
                OKLog.d("startHybridActivity", "pluginName -- >> " + intent.getStringExtra("pluginname"));
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String string = extras.getString("orderId");
                    String string2 = extras.getString("type");
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        OKLog.d("HybridS", "orderId -- >> " + string);
                        OKLog.d("HybridS", "type -- >> " + string2);
                        String str = "";
                        if ("1".equals(string2)) {
                            str = JumpUtil.VAULE_DES_AIRINORDER;
                        } else if ("2".equals(string2)) {
                            str = JumpUtil.VAULE_DES_AIREXORDER;
                        }
                        String str2 = "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"" + str + "\",\"orderId\":\"" + string + "\",\"orderType\":\"" + string2 + "\"}";
                        Intent intent2 = new Intent();
                        intent2.setFlags(268435456);
                        intent2.setData(Uri.parse(str2));
                        context.startActivity(intent2);
                    }
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }
}
