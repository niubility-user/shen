package com.jingdong.app.mall.global;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes3.dex */
public class GlobalStatusGetter {
    public static boolean isAppForeground() {
        Context applicationContext;
        Cursor query;
        Bundle extras;
        boolean innerGetAppForegroundStatus = BackForegroundWatcher.getInstance().innerGetAppForegroundStatus();
        return (innerGetAppForegroundStatus || ProcessUtil.isMainProcess() || (applicationContext = JdSdk.getInstance().getApplicationContext()) == null || (query = applicationContext.getContentResolver().query(Uri.parse("content://com.jingdong.app.mall.global.CommonProvider/common"), null, null, null, null)) == null || (extras = query.getExtras()) == null) ? innerGetAppForegroundStatus : extras.getBoolean("back_ground_status", innerGetAppForegroundStatus);
    }
}
