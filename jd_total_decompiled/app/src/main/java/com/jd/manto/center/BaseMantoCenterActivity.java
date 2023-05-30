package com.jd.manto.center;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.jingdong.sdk.platform.lib.ui.CompactActivity;

/* loaded from: classes17.dex */
public class BaseMantoCenterActivity extends CompactActivity {

    /* renamed from: g  reason: collision with root package name */
    private a f6248g = new a();

    /* loaded from: classes17.dex */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BaseMantoCenterActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.exit");
        registerReceiver(this.f6248g, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f6248g);
    }
}
