package com.jingdong.app.mall.log;

import com.alibaba.fastjson.util.TypeUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.oklog.OKLogConfig;
import com.jingdong.sdk.talos.b;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes.dex */
public class e {

    /* renamed from: f */
    private static e f11189f;
    private StategyEntity a;

    /* renamed from: c */
    private f f11190c;
    private boolean d;

    /* renamed from: e */
    private volatile boolean f11191e = false;
    private OKLogConfig b = new OKLogConfig().setDebug(h()).setLogWrapperClassFullNames(new String[]{Log.class.getName()}).diskRecord(h(), JdSdk.getInstance().getApplication());

    /* loaded from: classes4.dex */
    public class a implements b.c {
        a() {
            e.this = r1;
        }

        @Override // com.jingdong.sdk.talos.b.c
        public boolean a() {
            return e.this.d;
        }
    }

    private e() {
        m();
    }

    public static synchronized e b() {
        e eVar;
        synchronized (e.class) {
            if (f11189f == null) {
                f11189f = new e();
            }
            eVar = f11189f;
        }
        return eVar;
    }

    private static int c(String str, int i2) {
        try {
            String config = JDMobileConfig.getInstance().getConfig("JDOKLog", "switch", str, i2 + "");
            if (h()) {
                String str2 = "JDOKLog-switch   " + str + "\uff1a " + config;
            }
            return TypeUtils.castToInt(config).intValue();
        } catch (Throwable th) {
            if (h()) {
                String str3 = "JDOKLog-switch   " + th;
            }
            return i2;
        }
    }

    private int e() {
        return c("interval", 5);
    }

    private static boolean h() {
        return false;
    }

    private boolean i() {
        return c("enable", 0) == 1;
    }

    private void m() {
        this.a = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "2", "1");
        if (!h() || this.a == null) {
            return;
        }
        String str = "requestStrategy: rt:" + this.a.rt + ", ret:" + this.a.ret + ", param:" + this.a.param;
    }

    public f d() {
        return this.f11190c;
    }

    public void f() {
        StategyEntity stategyEntity = this.a;
        if (stategyEntity != null && "1".equals(stategyEntity.ret)) {
            f fVar = new f(this.a.param);
            this.f11190c = fVar;
            this.b.setLogReporter(fVar);
        }
        this.b.start();
        Log.init();
    }

    public void g() {
        try {
            if (this.f11191e) {
                return;
            }
            this.f11191e = true;
            if (ProcessUtil.isMainProcess() && JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication()) && i()) {
                h();
                b.C0746b c0746b = new b.C0746b(JdSdk.getInstance().getApplication());
                c0746b.c("fba8ae5a5078417d90ae1355af234d4f");
                c0746b.d(DeviceInfoHelper.getAid());
                c0746b.j(new b.d() { // from class: com.jingdong.app.mall.log.b
                    @Override // com.jingdong.sdk.talos.b.d
                    public final String getUserId() {
                        String pin;
                        pin = UserUtil.getWJLoginHelper().getPin();
                        return pin;
                    }
                });
                c0746b.g(h());
                c0746b.i(Boolean.FALSE);
                c0746b.f(e());
                c0746b.h(Configuration.getProperty(Configuration.PARTNER, ""));
                c0746b.e(new a());
                com.jingdong.sdk.talos.a.l(c0746b.b());
                com.jingdong.sdk.talos.a.d("LogX", "-----------------------------------------APP\u542f\u52a8----------------------------------------------");
                JDMobileConfig.getInstance().registerListener(new JDMoblieConfigListener() { // from class: com.jingdong.app.mall.log.a
                    {
                        e.this = this;
                    }

                    @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
                    public final void onConfigUpdate() {
                        e.this.l();
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: n */
    public void l() {
        this.d = c("oklog2logx", 0) == 1;
    }
}
