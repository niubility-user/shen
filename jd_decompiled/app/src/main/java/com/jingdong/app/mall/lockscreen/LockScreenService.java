package com.jingdong.app.mall.lockscreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeMyJDModule;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class LockScreenService extends Service {

    /* renamed from: g  reason: collision with root package name */
    private String f11160g = "";

    /* renamed from: h  reason: collision with root package name */
    private String f11161h = "";

    /* renamed from: i  reason: collision with root package name */
    private BroadcastReceiver f11162i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends BroadcastReceiver {

        /* renamed from: com.jingdong.app.mall.lockscreen.LockScreenService$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0342a implements Runnable {
            RunnableC0342a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Log.D) {
                    Log.e("LockScreen", "activity-->open2");
                }
                Intent intent = new Intent(LockScreenService.this, LockScreenActivity.class);
                intent.putExtra("imageUrl", LockScreenService.this.f11161h);
                intent.addFlags(268435456);
                LockScreenService.this.startActivity(intent);
            }
        }

        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                if (context != null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(JDReactNativeMyJDModule.SCREEN_LOCK, 0);
                    LockScreenService.this.f11160g = sharedPreferences.getString(JDReactNativeMyJDModule.SCREEN_LOCK_KEY, "0");
                    LockScreenService.this.f11161h = sharedPreferences.getString(JDReactNativeMyJDModule.SCREEN_LOCK_PIC_KEY, "");
                }
                if ("1".equals(JDMobileConfig.getInstance().getConfig("SKIN", "lockScreen", "lockScreen"))) {
                    if (Log.D) {
                        Log.e("LockScreen", "activity-->open--" + LockScreenService.this.f11160g);
                        Log.e("LockScreen", "activity-->open--" + LockScreenService.this.f11161h);
                    }
                    if (!"1".equals(LockScreenService.this.f11160g) || LockScreenService.this.f11161h == null || "".equals(LockScreenService.this.f11161h) || !b.e(LockScreenService.this)) {
                        return;
                    }
                    if (Log.D) {
                        Log.e("LockScreen", "activity-->open1");
                    }
                    new Handler().postDelayed(new RunnableC0342a(), 100L);
                }
            }
        }
    }

    private void e() {
        try {
            this.f11162i = new a();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            registerReceiver(this.f11162i, intentFilter);
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "activity-->open1" + e2.toString());
            }
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        e();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.f11162i;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
