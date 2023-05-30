package com.vivo.push.d;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
final class v implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ Map b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ u f18268c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(u uVar, Context context, Map map) {
        this.f18268c = uVar;
        this.a = context;
        this.b = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String packageName = this.a.getPackageName();
        try {
            List runningTasksWithAOP = BaseInfo.getRunningTasksWithAOP((ActivityManager) this.a.getSystemService("activity"), 100);
            if (runningTasksWithAOP != null) {
                Iterator it = runningTasksWithAOP.iterator();
                while (it.hasNext()) {
                    ComponentName componentName = ((ActivityManager.RunningTaskInfo) it.next()).topActivity;
                    if (componentName.getPackageName().equals(packageName)) {
                        com.vivo.push.util.p.d("OnNotificationClickTask", "topClassName=" + componentName.getClassName());
                        Intent intent = new Intent();
                        intent.setComponent(componentName);
                        intent.setFlags(335544320);
                        u.b(intent, this.b);
                        this.a.startActivity(intent);
                        return;
                    }
                }
            }
        } catch (Exception e2) {
            com.vivo.push.util.p.a("OnNotificationClickTask", "start recentIntent is error", e2);
        }
        Intent launchIntentForPackage = this.a.getPackageManager().getLaunchIntentForPackage(this.a.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setFlags(268435456);
            u.b(launchIntentForPackage, this.b);
            this.a.startActivity(launchIntentForPackage);
            return;
        }
        com.vivo.push.util.p.a("OnNotificationClickTask", "LaunchIntent is null");
    }
}
