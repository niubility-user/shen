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
import com.jingdong.manto.network.mantorequests.v;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.concurrent.ArrayBlockingQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class a {
    private static a d;
    private Handler a;
    private HandlerThread b;

    /* renamed from: c  reason: collision with root package name */
    private ArrayBlockingQueue<PkgDetailEntity> f13988c = new ArrayBlockingQueue<>(64);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.preload.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class HandlerC0653a extends Handler {
        HandlerC0653a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2 && a.this.f13988c.size() > 0) {
                a aVar = a.this;
                aVar.a((PkgDetailEntity) aVar.f13988c.poll());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements h.e {
        final /* synthetic */ PkgDetailEntity a;

        b(PkgDetailEntity pkgDetailEntity) {
            this.a = pkgDetailEntity;
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(long j2, long j3, boolean z) {
        }

        @Override // com.jingdong.manto.launch.h.e
        public void a(MantoPreLaunchProcess.LaunchError launchError) {
            if (a.this.a != null) {
                Message obtainMessage = a.this.a.obtainMessage(2);
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
            if (a.this.a != null) {
                Message obtainMessage = a.this.a.obtainMessage(2);
                obtainMessage.obj = this.a;
                obtainMessage.arg1 = 0;
                obtainMessage.sendToTarget();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c extends IMantoHttpListener {
        c() {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONArray optJSONArray;
            if (jSONObject != null && TextUtils.equals(jSONObject.optString("code", ""), "0") && (optJSONArray = jSONObject.optJSONArray("data")) != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                    pkgDetailEntity.appId = optJSONObject.optString("appId", "");
                    pkgDetailEntity.type = optJSONObject.optString("type", "");
                    pkgDetailEntity.build = optJSONObject.optString(HybridSDK.APP_VERSION_CODE, "");
                    pkgDetailEntity.zipUrl = optJSONObject.optString("zip_url", "");
                    pkgDetailEntity.pkgUrl = optJSONObject.optString("package_url", "");
                    a.this.f13988c.offer(pkgDetailEntity);
                }
                a.this.b();
            }
        }
    }

    private a() {
    }

    public static a a() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(PkgDetailEntity pkgDetailEntity) {
        h hVar = new h(pkgDetailEntity, new com.jingdong.manto.i.c(), false);
        hVar.f13254c = new b(pkgDetailEntity);
        Handler handler = this.a;
        if (handler != null) {
            handler.post(hVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            if (this.f13988c.size() > 0 && this.b == null) {
                HandlerThread handlerThread = new HandlerThread("MantoAppPreDownloadThread", 10);
                this.b = handlerThread;
                handlerThread.start();
                this.a = new HandlerC0653a(this.b.getLooper());
            }
            a(this.f13988c.poll());
        } catch (Throwable unused) {
        }
    }

    public void a(String[] strArr) {
        if (strArr == null || strArr.length == 0 || !MantoProcessUtil.isMainProcess()) {
            return;
        }
        MantoJDHttpHandler.commit(new v(strArr), new c());
    }
}
