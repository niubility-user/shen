package com.jingdong.app.mall.home.floor.common.i;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.jd.lib.babel.task.common.TaskDataUtil;
import com.jd.lib.babel.task.view.ITask;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.stat.MttLoader;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class i {
    private static int a(Intent intent, String str) {
        if (intent == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        int intExtra = intent.getIntExtra(str, 0);
        intent.removeExtra(str);
        return intExtra;
    }

    public static void b(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            int a = a(intent, MttLoader.ENTRY_ID);
            String s = com.jingdong.app.mall.home.o.a.f.s(intent, "businessId");
            String s2 = com.jingdong.app.mall.home.o.a.f.s(intent, ITask.TASK_PARAM_COMPONENTID);
            String s3 = com.jingdong.app.mall.home.o.a.f.s(intent, ITask.TASK_PARAM_TASKPARAM);
            if (a == 0 && TextUtils.isEmpty(s3) && TextUtils.isEmpty(s2)) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("businessId", s);
            hashMap.put(MttLoader.ENTRY_ID, Integer.valueOf(a));
            hashMap.put(ITask.TASK_PARAM_COMPONENTID, s2);
            hashMap.put(ITask.TASK_PARAM_TASKPARAM, com.jingdong.app.mall.home.r.c.b.c(s3));
            TaskDataUtil.getInstance().writeData(String.valueOf(a), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e("JDHomeTaskUtil", th);
            }
        }
    }
}
