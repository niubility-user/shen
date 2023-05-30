package com.jingdong.aura.wrapper;

import android.app.Application;
import com.jd.dynamic.DYConstants;
import com.jingdong.aura.a.b.h;
import com.jingdong.aura.a.c.j;
import com.jingdong.aura.wrapper.d.b;
import com.jingdong.common.utils.LangUtils;
import java.util.Properties;
import l.b.a.d;

/* loaded from: classes4.dex */
public class AuraInitializer {
    private static com.jingdong.aura.core.util.l.b log = com.jingdong.aura.core.util.l.c.a("AuraInitializer");
    private Application mApplication;
    private long mInitStartTime;
    private boolean mIsUpdate;
    private String mPackageName;
    private Properties mProperties = new Properties();
    private boolean mTryInstall = false;

    /* loaded from: classes4.dex */
    public class a extends b.d {
        final /* synthetic */ com.jingdong.aura.wrapper.d.a b;

        /* renamed from: c */
        final /* synthetic */ com.jingdong.aura.wrapper.d.c f12260c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(AuraInitializer auraInitializer, String str, com.jingdong.aura.wrapper.d.a aVar, com.jingdong.aura.wrapper.d.c cVar) {
            super(str);
            this.b = aVar;
            this.f12260c = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.a(true, false);
            this.f12260c.a(true, true);
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        boolean a(String str);
    }

    public AuraInitializer(Application application, String str, boolean z) {
        this.mApplication = application;
        this.mPackageName = str;
        this.mIsUpdate = c.c(application);
    }

    private void installBundles() {
        if (this.mIsUpdate) {
            com.jingdong.aura.wrapper.d.a a2 = com.jingdong.aura.wrapper.d.a.a();
            com.jingdong.aura.wrapper.d.c b2 = com.jingdong.aura.wrapper.d.c.b();
            a2.a(this.mApplication);
            b2.a(this.mApplication);
            com.jingdong.aura.wrapper.d.b.b(new a(this, "AuraInstallerTask", a2, b2));
            return;
        }
        c.d(this.mApplication);
        c.a(this.mApplication);
        c.e(this.mApplication);
    }

    public static void loadBundle(String str) {
        com.jingdong.aura.a.b.a.b().c(str);
        d b2 = com.jingdong.aura.a.b.k.b.b(str);
        if (b2 != null) {
            h hVar = (h) b2;
            if (hVar != null) {
                hVar.m();
            }
            try {
                b2.a();
            } catch (l.b.a.b e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean update(String str, String str2, int i2, String str3) {
        log.a("update: bundleName:" + str + " fullPath:" + str2 + " versionCode:" + i2 + " md5:" + str3);
        boolean a2 = com.jingdong.aura.a.b.a.b().a(str, str2, i2, str3);
        if (a2) {
            com.jingdong.aura.a.a.a.c().a(j.a(str2, str3));
            com.jingdong.aura.a.a.c.b();
        }
        return a2;
    }

    public void init() {
        this.mInitStartTime = System.currentTimeMillis();
        try {
            com.jingdong.aura.a.b.a.b().a(this.mApplication);
            log.b("Aura framework inited end " + this.mPackageName + LangUtils.SINGLE_SPACE + (System.currentTimeMillis() - this.mInitStartTime) + " ms");
            com.jingdong.aura.wrapper.a.a(this.mApplication);
        } catch (Throwable th) {
            throw new RuntimeException(" initialization fail" + th.getMessage());
        }
    }

    public void preInstallBundles() {
        installBundles();
    }

    public void setBundleSecurityVerifier(b bVar) {
        com.jingdong.aura.a.b.a.b().a(bVar);
    }

    public void startUp(String str) {
        if (str != null) {
            try {
                if (com.jingdong.aura.a.b.c.w()) {
                    this.mProperties.put("com.jingdong.aura.AppDirectory.debug", str);
                }
            } catch (Throwable th) {
                throw new RuntimeException("Could not set Globals !!!", th);
            }
        }
        this.mProperties.put("com.jingdong.aura.AppDirectory", com.jingdong.aura.core.util.d.a().getParent());
        if (c.a()) {
            com.jingdong.aura.a.b.a.b().a(new com.jingdong.aura.wrapper.b());
        }
        if (this.mIsUpdate) {
            log.a("aura is first start:" + this.mIsUpdate);
            this.mProperties.put("osgi.init", DYConstants.DY_TRUE);
        }
        com.jingdong.aura.a.a.b.a(this.mApplication, this.mIsUpdate);
        log.b("Aura framework prepare starting in process " + this.mPackageName + LangUtils.SINGLE_SPACE + (System.currentTimeMillis() - this.mInitStartTime) + " ms");
        com.jingdong.aura.a.b.a.b().a(this.mApplication, this.mProperties);
        log.b("Aura framework end startUp in process " + this.mPackageName + LangUtils.SINGLE_SPACE + (System.currentTimeMillis() - this.mInitStartTime) + " ms");
        com.jingdong.aura.a.a.c.b();
    }
}
