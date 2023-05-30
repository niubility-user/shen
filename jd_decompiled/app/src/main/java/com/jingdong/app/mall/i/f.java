package com.jingdong.app.mall.i;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.jingdong.app.mall.i.f;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class f {

    /* renamed from: e  reason: collision with root package name */
    private static final String f11110e = "f";
    private final String a;
    private volatile AtomicInteger b;

    /* renamed from: c  reason: collision with root package name */
    private Runnable f11111c;
    Gson d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(long j2, Exception exc) {
            if (f.this.b.get() < 3) {
                f.this.b.incrementAndGet();
                ThreadManager.getMainHandler().postDelayed(new Runnable() { // from class: com.jingdong.app.mall.i.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThreadManager.heavy().post(new f(null).f11111c);
                    }
                }, 60000L);
            }
            String valueOf = String.valueOf(System.currentTimeMillis() - j2);
            f fVar = f.this;
            fVar.l(CartConstant.KEY_CART_TEXTINFO_FINISH, fVar.h(f.this.b.get() + "", valueOf, "1"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ Boolean f(Context context) throws Exception {
            return Boolean.valueOf(f.this.j(context));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(final Context context) {
            ThreadManager.light().post(new Callable() { // from class: com.jingdong.app.mall.i.b
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return f.a.this.f(context);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(long j2, final Context context) {
            String str;
            String valueOf = String.valueOf(System.currentTimeMillis() - j2);
            if (f.this.k()) {
                str = f.this.j(context) ? "0" : "3";
            } else {
                ThreadManager.getMainHandler().postDelayed(new Runnable() { // from class: com.jingdong.app.mall.i.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        f.a.this.h(context);
                    }
                }, Final.FIVE_SECOND);
                str = "2";
            }
            f fVar = f.this;
            fVar.l(CartConstant.KEY_CART_TEXTINFO_FINISH, fVar.h(f.this.b.get() + "", valueOf, str));
        }

        @Override // java.lang.Runnable
        public void run() {
            final long currentTimeMillis = System.currentTimeMillis();
            final Context applicationContext = JdSdk.getInstance().getApplicationContext();
            ((IAuraInstallManager) AuraServiceLoader.get(applicationContext, IAuraInstallManager.class)).startInstall(applicationContext, new AuraInstallRequest.Builder().setBundleName(f.this.a).setDownloadType(1).addOnFailerListener(new AuraInstallRequest.IOnFailerListener() { // from class: com.jingdong.app.mall.i.e
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
                public final void onFailure(Exception exc) {
                    f.a.this.d(currentTimeMillis, exc);
                }
            }).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.app.mall.i.a
                @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                public final void onSuccess() {
                    f.a.this.j(currentTimeMillis, applicationContext);
                }
            }).build());
            HashMap hashMap = new HashMap();
            hashMap.put("pkg_type", Integer.valueOf(JdSdk.getInstance().getPkgType()));
            f fVar = f.this;
            fVar.l("start", fVar.d.toJson(hashMap));
        }
    }

    /* synthetic */ f(a aVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("retry", str);
        hashMap.put("duration", str2);
        hashMap.put("code", str3);
        return this.d.toJson(hashMap);
    }

    public static void i() {
        ThreadManager.heavy().post(new f().f11111c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j(Context context) {
        try {
            JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.edge.init.EdgeInit").getDeclaredMethod(XView2Constants.XVIEW2_ACTION_INIT, Application.class).invoke(null, JdSdk.getInstance().getApplication());
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return AuraBundleConfig.getInstance().isBundlePrepared(this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(String str, String str2) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "AIModel_initiator_" + str, str2, "AIModel_initiator", "edgeComputing", null, null, null);
        if (OKLog.D) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("eventId", str);
            hashMap.put("eventParam", str2);
            hashMap.put("pageId", "AIModel_initiator");
            OKLog.d(f11110e, this.d.toJson(hashMap));
        }
    }

    private f() {
        this.b = new AtomicInteger(0);
        this.f11111c = new a();
        this.d = new Gson();
        this.a = AuraBundleInfos.getBundleNameFromBundleId(126);
    }
}
