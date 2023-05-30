package com.jingdong.app.mall.network;

import android.content.Context;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.network.cronet.RuntimeMobileConfig;
import com.jingdong.common.network.cronet.TaskController;
import com.jingdong.common.network.quicpro.QuicProEngine;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: com.jingdong.app.mall.network.a$a */
    /* loaded from: classes4.dex */
    public class C0363a implements AuraInstallRequest.IOnSuccessListener {
        C0363a() {
            a.this = r1;
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
        public void onSuccess() {
            try {
                if (a.this.c()) {
                    if (QuicProEngine.getInstance() != null) {
                        QuicProEngine.getInstance().setLogLevel(0);
                    }
                    OKLog.d("JdCronet", "\u63d2\u4ef6\u5c31\u7eea\uff0c\u5f00\u59cb\u8fd0\u884c\u6d4b\u901f\u4efb\u52a1");
                    TaskController.getInstance().runTasks();
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements AuraInstallRequest.IOnFailerListener {
        b(a aVar) {
        }

        @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
        public void onFailure(Exception exc) {
            OKLog.d("JdCronet", "\u63d2\u4ef6\u4e0b\u8f7d\u5931\u8d25 " + exc);
        }
    }

    /* loaded from: classes4.dex */
    public static class c {
        public static a a = new a(null);
    }

    /* synthetic */ a(C0363a c0363a) {
        this();
    }

    public static a a() {
        return c.a;
    }

    public void b(Context context) {
        if (!RuntimeMobileConfig.isEnable()) {
            if (OKLog.D) {
                OKLog.d("JdCronet", "\u7ebf\u4e0a\u5f00\u5173\u672a\u6253\u5f00\u505c\u6b62\u52a0\u8f7dcronet\u540e\u88c5\u63d2\u4ef6");
                return;
            }
            return;
        }
        try {
            ((IAuraInstallManager) AuraServiceLoader.get(context, IAuraInstallManager.class)).startInstall(context, new AuraInstallRequest.Builder().setBundleName(AuraBundleInfos.getBundleNameFromBundleId(107)).setDownloadType(2).addOnFailerListener(new b(this)).addOnSuccessListener(new C0363a()).build());
            OKLog.d("JdCronet", "\u5f00\u59cb\u9759\u9ed8\u52a0\u8f7dCronet\u63d2\u4ef6");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean c() {
        return AuraConfig.isBundlePrepered(AuraBundleInfos.getBundleNameFromBundleId(107));
    }

    private a() {
    }
}
