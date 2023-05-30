package com.jingdong.app.mall.update;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.jingdong.app.mall.update.view.InstallApkActivity;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class InstallApkService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (Log.D) {
            Log.d("InstallApkService", "startId -->> " + i3);
        }
        if (ApplicationUpgradeHelper.apkFileIsExists(CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_VERSION, ""))) {
            Intent intent2 = new Intent(this, InstallApkActivity.class);
            intent2.addFlags(268435456);
            startActivity(intent2);
        }
        stopSelf();
        return super.onStartCommand(intent, i2, i3);
    }
}
