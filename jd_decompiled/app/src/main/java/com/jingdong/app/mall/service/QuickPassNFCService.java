package com.jingdong.app.mall.service;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.sdk.oklog.OKLog;

@TargetApi(19)
/* loaded from: classes4.dex */
public class QuickPassNFCService extends HostApduService {

    /* renamed from: i  reason: collision with root package name */
    private static final String f11631i = QuickPassNFCService.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private LocalBroadcastManager f11632g;

    /* renamed from: h  reason: collision with root package name */
    private BroadcastReceiver f11633h;

    /* loaded from: classes4.dex */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            QuickPassNFCService.this.sendResponseApdu(intent.getByteArrayExtra("service_data"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements AuraInstallRequest.IOnSuccessListener {
        final /* synthetic */ byte[] a;

        b(byte[] bArr) {
            this.a = bArr;
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
        public void onSuccess() {
            QuickPassNFCService.this.c(this.a);
        }
    }

    private void b(byte[] bArr) {
        if (AuraBundleConfig.getInstance().isBundlePrepared("com.jd.lib.quickpass")) {
            c(bArr);
        } else {
            ((IAuraInstallManager) AuraServiceLoader.get(this, IAuraInstallManager.class)).startInstall(this, new AuraInstallRequest.Builder().setBundleName("com.jd.lib.quickpass").setAuraInstallStyle(AuraInstallRequest.AURA_INSTALL_STYLE2).addOnSuccessListener(new b(bArr)).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(byte[] bArr) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(this, "com.jdpaysdk.payment.quickpass.server.HcePayService"));
            intent.putExtra("service_data", bArr);
            startService(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e("QuickPass", "HcePayService ClassNotFoundException=" + e2.getMessage());
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f11632g = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter("quick-pass-nfc-receiver");
        a aVar = new a();
        this.f11633h = aVar;
        this.f11632g.registerReceiver(aVar, intentFilter);
    }

    @Override // android.nfc.cardemulation.HostApduService
    public void onDeactivated(int i2) {
    }

    @Override // android.app.Service
    public void onDestroy() {
        BroadcastReceiver broadcastReceiver;
        super.onDestroy();
        LocalBroadcastManager localBroadcastManager = this.f11632g;
        if (localBroadcastManager == null || (broadcastReceiver = this.f11633h) == null) {
            return;
        }
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override // android.nfc.cardemulation.HostApduService
    public byte[] processCommandApdu(byte[] bArr, Bundle bundle) {
        if (JDPrivacyHelper.isAcceptPrivacy(this)) {
            b(bArr);
            return null;
        }
        Toast.makeText(this, "\u60a8\u9700\u8981\u540c\u610f\u4eac\u4e1cAPP\u9690\u79c1\u653f\u7b56\uff0c\u624d\u80fd\u5b8c\u6210\u652f\u4ed8", 0).show();
        return null;
    }
}
