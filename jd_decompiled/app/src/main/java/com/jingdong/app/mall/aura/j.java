package com.jingdong.app.mall.aura;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.aura.DownGradeUtils;
import com.jingdong.aura.sdk.update.AuraUpdate;
import com.jingdong.aura.wrapper.AuraInitializer;
import com.jingdong.common.apkcenter.ApkCenter;
import com.jingdong.common.apkcenter.ApkDownloadController;
import com.jingdong.common.apkcenter.ApkResult;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes19.dex */
public class j {
    private static com.jingdong.app.mall.aura.internal.e a = new com.jingdong.app.mall.aura.internal.e("AuraControl");
    private static com.jingdong.app.mall.aura.internal.b b = new com.jingdong.app.mall.aura.internal.b();

    /* renamed from: c  reason: collision with root package name */
    private static final ThreadFactory f7942c;
    private static final ExecutorService d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements ApkCenter.ApkListener {
        final /* synthetic */ Context a;

        a(Context context) {
            this.a = context;
        }

        @Override // com.jingdong.common.apkcenter.ApkCenter.ApkListener
        public void onDownloadProgressChanged(int i2) {
        }

        @Override // com.jingdong.common.apkcenter.ApkCenter.ApkListener
        public void onFailure(String str) {
        }

        @Override // com.jingdong.common.apkcenter.ApkCenter.ApkListener
        public void onFinish(ApkResult apkResult) {
            j.f(this.a, apkResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes19.dex */
    public static class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private ApkResult f7943g;

        public b(Context context, ApkResult apkResult) {
            this.f7943g = apkResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f7943g.getUpdateListener() != null) {
                this.f7943g.getUpdateListener().onInstallStart();
            }
            if (!ApkDownloadController.getInStance().isCompleted(this.f7943g)) {
                Log.d("AuraUpdate", this.f7943g.id + "not download completed");
                return;
            }
            String bundleNameFromUpdateID = AuraBundleInfos.getBundleNameFromUpdateID(this.f7943g.id);
            ApkResult apkResult = this.f7943g;
            boolean update = AuraInitializer.update(bundleNameFromUpdateID, apkResult.localPath, apkResult.bundleVersionCode, apkResult.md5);
            if (this.f7943g.getUpdateListener() != null) {
                this.f7943g.getUpdateListener().onInstallFinish(update);
            }
            e.q("AuraMaiDianUpdatePlugin", AuraBundleInfos.getBundleNameFromUpdateID(this.f7943g.id) + CartConstant.KEY_YB_INFO_LINK + this.f7943g.bundleVersionCode + CartConstant.KEY_YB_INFO_LINK + update, "UpdateListener.onEnd", "" + PackageInfoUtil.getVersionCode());
        }
    }

    static {
        d dVar = new ThreadFactory() { // from class: com.jingdong.app.mall.aura.d
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return j.b(runnable);
            }
        };
        f7942c = dVar;
        d = Executors.newSingleThreadExecutor(dVar);
        b.a(600000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Thread b(Runnable runnable) {
        return new Thread(runnable, "download_provide_bundle_thread");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c() {
        Log.d("updatePlugins", "in thread:" + Thread.currentThread().getName());
        ApkCenter.downloadAllApks();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(Context context) {
        a aVar = new a(context);
        Iterator<String> it = AuraBundleInfos.getUpdateIDKeySet().iterator();
        while (it.hasNext()) {
            ApkCenter.registerApkListener(it.next(), aVar);
        }
    }

    public static void e() {
        try {
            if (DownGradeUtils.isDownGrade()) {
                Log.i("AuraUpdate", "requestUpdateAllApks x time not request ");
                return;
            }
            Log.i("AuraUpdate", "requestUpdateAllApks  ");
            AuraUpdate.requestUpdateBundles();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(Context context, ApkResult apkResult) {
        String str;
        Log.d("updateAura", "updateAura called. apkResult =  " + apkResult);
        if (apkResult == null || (str = apkResult.hostVersionName) == null || !str.equals(PackageInfoUtil.getVersionName()) || TextUtils.isEmpty(apkResult.localPath)) {
            return;
        }
        a.b(new b(context, apkResult));
    }

    public static void g(Context context) {
        if (ProcessUtil.isMainProcess()) {
            if (DownGradeUtils.isDownGrade()) {
                Log.i("AuraUpdate", "updatePlugins x time not request ");
                return;
            }
            Log.i("AuraUpdate", "updatePlugins  ");
            List<ApkResult> queryApkResults = ApkCenter.queryApkResults();
            if (queryApkResults != null) {
                Iterator<ApkResult> it = queryApkResults.iterator();
                while (it.hasNext()) {
                    f(context, it.next());
                }
            }
            d.execute(new Runnable() { // from class: com.jingdong.app.mall.aura.c
                @Override // java.lang.Runnable
                public final void run() {
                    j.c();
                }
            });
        }
    }
}
