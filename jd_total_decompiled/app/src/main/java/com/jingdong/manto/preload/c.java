package com.jingdong.manto.preload;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.h;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.u;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoSharedPrefrenceUtil;
import java.util.concurrent.ArrayBlockingQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class c {

    /* renamed from: f */
    private static c f13989f;
    private Handler a;
    private HandlerThread b;

    /* renamed from: c */
    private boolean f13990c = false;
    private ArrayBlockingQueue<PkgDetailEntity> d = new ArrayBlockingQueue<>(64);

    /* renamed from: e */
    public long f13991e;

    /* loaded from: classes16.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Looper looper) {
            super(looper);
            c.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 2) {
                return;
            }
            if (c.this.d.size() <= 0) {
                c.this.c();
                return;
            }
            c cVar = c.this;
            cVar.a((PkgDetailEntity) cVar.d.poll());
        }
    }

    /* loaded from: classes16.dex */
    public class b implements h.e {
        final /* synthetic */ PkgDetailEntity a;

        b(PkgDetailEntity pkgDetailEntity) {
            c.this = r1;
            this.a = pkgDetailEntity;
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(long j2, long j3, boolean z) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            if (c.this.a != null) {
                Message obtainMessage = c.this.a.obtainMessage(2);
                obtainMessage.obj = this.a;
                obtainMessage.arg1 = -1;
                obtainMessage.sendToTarget();
            }
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(PkgDetailEntity pkgDetailEntity) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(boolean z) {
            if (c.this.a != null) {
                Message obtainMessage = c.this.a.obtainMessage(2);
                obtainMessage.obj = this.a;
                obtainMessage.arg1 = 0;
                obtainMessage.sendToTarget();
            }
        }
    }

    /* renamed from: com.jingdong.manto.preload.c$c */
    /* loaded from: classes16.dex */
    public class C0655c extends IMantoHttpListener {
        C0655c() {
            c.this = r1;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            c.this.c();
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONArray optJSONArray;
            if (jSONObject == null || !TextUtils.equals(jSONObject.optString("code", ""), "0") || (optJSONArray = jSONObject.optJSONArray("data")) == null) {
                return;
            }
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                pkgDetailEntity.appId = optJSONObject.optString("appId", "");
                pkgDetailEntity.type = optJSONObject.optString("type", "");
                pkgDetailEntity.build = optJSONObject.optString(HybridSDK.APP_VERSION_CODE, "");
                pkgDetailEntity.zipUrl = optJSONObject.optString("zip_url", "");
                pkgDetailEntity.pkgUrl = optJSONObject.optString("package_url", "");
                c.this.d.offer(pkgDetailEntity);
            }
            c.this.b();
        }
    }

    private c() {
    }

    public static c a() {
        if (f13989f == null) {
            synchronized (c.class) {
                if (f13989f == null) {
                    f13989f = new c();
                }
            }
        }
        return f13989f;
    }

    public void a(PkgDetailEntity pkgDetailEntity) {
        h hVar = new h(pkgDetailEntity, new com.jingdong.manto.i.c(), false);
        hVar.f13254c = new b(pkgDetailEntity);
        this.a.post(hVar);
    }

    public void b() {
        try {
            if (this.d.size() <= 0 || this.f13990c) {
                return;
            }
            HandlerThread handlerThread = new HandlerThread("MantoPreDownloadThread", 10);
            this.b = handlerThread;
            handlerThread.start();
            this.a = new a(this.b.getLooper());
            a(this.d.poll());
            this.f13990c = true;
        } catch (Throwable unused) {
        }
    }

    public void c() {
        this.d.clear();
        HandlerThread handlerThread = this.b;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        this.f13990c = false;
    }

    public void a(long j2) {
        this.f13991e = j2;
    }

    public void a(String str, String str2) {
        PkgDetailEntity c2;
        if (!this.f13990c || this.d.size() == 0 || (c2 = com.jingdong.manto.b.k().c(str, str2)) == null) {
            return;
        }
        this.d.remove(c2);
    }

    public void d() {
        if (MantoProcessUtil.isMainProcess()) {
            long j2 = this.f13991e;
            if (System.currentTimeMillis() - MantoSharedPrefrenceUtil.getPrefLong(com.jingdong.a.g(), "manto_pre_download", 0L) <= (j2 > 0 ? j2 * 60 * 1000 : 28800000L)) {
                return;
            }
            MantoJDHttpHandler.commit(new u(), new C0655c());
            MantoSharedPrefrenceUtil.setAndApplyLong(com.jingdong.a.g(), "manto_pre_download", System.currentTimeMillis());
        }
    }
}
