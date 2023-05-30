package com.jingdong.jdpush_new.mta;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdpush_new.j.c;
import com.jingdong.jdpush_new.j.f;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.i;
import com.jingdong.jdpush_new.j.l;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.jingdong.sdk.threadpool.common.Disposable;

/* loaded from: classes12.dex */
public class b {

    /* renamed from: c */
    private static b f12867c = null;
    public static final String d = "b";
    public PushMtaInfo a = new PushMtaInfo();
    private Disposable b;

    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f12868g;

        a(Context context) {
            b.this = r1;
            this.f12868g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.d(b.d, "\u8fbe\u5230\u6700\u5927\u7b49\u5f85\u65f6\u95f4\uff0c\u51c6\u5907\u4e0a\u62a5push\u521d\u59cb\u5316\u57cb\u70b9");
            PushMtaInfo pushMtaInfo = b.this.a;
            pushMtaInfo.isTimeout = 1;
            pushMtaInfo.costTime = System.currentTimeMillis() - b.this.a.t;
            if ("none".equals(BaseInfo.getNetworkType())) {
                PushMtaInfo pushMtaInfo2 = b.this.a;
                if (pushMtaInfo2.dtSrc == 0) {
                    pushMtaInfo2.longConnException = "\u65e0\u7f51\u7edc";
                } else {
                    pushMtaInfo2.romException = "\u65e0\u7f51\u7edc";
                }
            }
            b.this.f(this.f12868g);
        }
    }

    private b() {
    }

    public static b b() {
        if (f12867c == null) {
            synchronized (b.class) {
                if (f12867c == null) {
                    f12867c = new b();
                }
            }
        }
        return f12867c;
    }

    public void f(Context context) {
        try {
            this.a.uuid = com.jingdong.jdpush_new.a.b();
            boolean a2 = f.a(context);
            PushMtaInfo pushMtaInfo = this.a;
            pushMtaInfo.openPush = a2 ? 1 : 0;
            String obj = JDJSON.toJSON(pushMtaInfo).toString();
            JDMtaUtils.sendExposureDataWithExt(context, "push_sdk_initial", "", "", "", "", obj, null);
            g.i(d, "\u4e0a\u62a5push\u521d\u59cb\u5316\u57cb\u70b9\u6210\u529f\uff1a" + obj);
        } catch (Throwable th) {
            g.f(d, th);
        }
    }

    public PushMtaInfo c() {
        return b().a;
    }

    public void d(Context context, boolean z) {
        try {
            if (z) {
                this.a.t = System.currentTimeMillis();
                PushMtaInfo pushMtaInfo = this.a;
                pushMtaInfo.isMainProc = 1;
                pushMtaInfo.appVer = BaseInfo.getAppVersionName();
                this.a.openPush = f.a(context) ? 1 : 0;
                this.a.manufacture = BaseInfo.getDeviceManufacture();
                this.a.model = BaseInfo.getDeviceModel();
                PushMtaInfo pushMtaInfo2 = this.a;
                pushMtaInfo2.osVer = Build.VERSION.SDK_INT;
                pushMtaInfo2.kernelVer = c.j();
                this.a.romVer = l.g();
                this.a.dtSrc = l.a();
                this.a.romSdkVer = c.n();
                this.a.longConnVer = c.m();
                j(context);
            } else {
                e(context);
                PushMtaInfo pushMtaInfo3 = this.a;
                pushMtaInfo3.isMainProc = 0;
                pushMtaInfo3.dtSrc = 0;
            }
            k(context);
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public void e(Context context) {
        try {
            this.a = (PushMtaInfo) JDJSON.parseObject(i.e(context).getString("mta_info", "{}"), PushMtaInfo.class);
        } catch (Throwable th) {
            g.e(d, "readFromSP error", th);
        }
    }

    public void g(Context context) {
        Disposable disposable = this.b;
        if (disposable != null) {
            disposable.dispose();
            this.b = null;
        }
        f(context);
    }

    public void h(int i2, int i3) {
        b().l((i2 * 1000) + 90);
        b().c().romReturnCode = i3;
        b().g(JdSdk.getInstance().getApplication());
    }

    public void i(int i2, Throwable th) {
        b().l((i2 * 1000) + 90);
        if (i2 == 0) {
            b().c().longConnException = Log.getStackTraceString(th);
        } else {
            b().c().romException = Log.getStackTraceString(th);
        }
        b().g(JdSdk.getInstance().getApplication());
    }

    public void j(Context context) {
        PushMtaInfo pushMtaInfo = this.a;
        pushMtaInfo.uuid = "";
        i.e(context).edit().putString("mta_info", ((JDJSONObject) JDJSON.toJSON(pushMtaInfo)).toString()).commit();
    }

    public void k(Context context) {
        this.b = ThreadManager.light().postDelay(new a(context), 10000L);
    }

    public void l(int i2) {
        PushMtaInfo pushMtaInfo = this.a;
        pushMtaInfo.step = i2;
        pushMtaInfo.costTime = System.currentTimeMillis() - this.a.t;
    }
}
