package com.jd.sec;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.biometric.core.BiometricManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class LogoManager {
    private static LogoManager a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f6865c = false;
    private volatile AtomicBoolean d = new AtomicBoolean(false);

    /* loaded from: classes17.dex */
    public interface IEnv {
        String UserAgent();

        String env();
    }

    /* loaded from: classes17.dex */
    public interface OnNodeCallback {
        void onLogin(String str);

        void onOrder(String str);
    }

    /* loaded from: classes17.dex */
    public enum ServerLocation {
        CHA(1),
        IDA(2),
        TH(3),
        MCA(4);
        
        private int locationValue;

        ServerLocation(int i2) {
            this.locationValue = i2;
        }

        public int getLocationValue() {
            return this.locationValue;
        }
    }

    /* loaded from: classes17.dex */
    class a implements OnNodeCallback {
        a() {
        }

        @Override // com.jd.sec.LogoManager.OnNodeCallback
        public void onLogin(String str) {
            if (LogoManager.this.b != null) {
                if (TextUtils.isEmpty(str)) {
                    BiometricManager.getInstance().startBiometric(LogoManager.this.b, com.jd.sec.a.c(), com.jd.sec.a.b());
                } else {
                    BiometricManager.getInstance().startBiometric(LogoManager.this.b, com.jd.sec.a.c(), str);
                }
            }
        }

        @Override // com.jd.sec.LogoManager.OnNodeCallback
        public void onOrder(String str) {
            if (LogoManager.this.b != null) {
                BiometricManager.getInstance().startBiometric(LogoManager.this.b, com.jd.sec.a.c(), com.jd.sec.a.b());
            }
        }
    }

    private LogoManager(Context context) {
        this.b = context instanceof Application ? context : context.getApplicationContext();
    }

    public static LogoManager getInstance(Context context) {
        if (a == null) {
            synchronized (LogoManager.class) {
                if (a == null) {
                    a = new LogoManager(context);
                }
            }
        }
        return a;
    }

    public String getLocalLogo() {
        return this.b != null ? BiometricManager.getInstance().a().a(this.b) : "";
    }

    public String getLogo() {
        if (this.b != null) {
            String a2 = BiometricManager.getInstance().a(this.b);
            if (TextUtils.isEmpty(a2)) {
                if (!this.d.compareAndSet(false, true)) {
                    BiometricManager.getInstance().a().a(this.b);
                    return a2;
                }
                String cacheTokenByBizId = BiometricManager.getInstance().getCacheTokenByBizId(this.b, com.jd.sec.a.c(), com.jd.sec.a.b());
                this.d.set(false);
                return cacheTokenByBizId;
            }
            return a2;
        }
        return "";
    }

    public OnNodeCallback getNoteCallback() {
        return new a();
    }

    public void init() {
        init(ServerLocation.CHA);
    }

    public void init(ServerLocation serverLocation) {
        if (this.b == null || this.f6865c || !this.d.compareAndSet(false, true) || this.f6865c) {
            return;
        }
        BiometricManager.getInstance().startBiometric(this.b, com.jd.sec.a.c(), com.jd.sec.a.b());
        this.f6865c = true;
        this.d.set(false);
    }

    public void init(ServerLocation serverLocation, InitParams initParams) {
        com.jd.sec.a.a(initParams);
        init(serverLocation);
    }

    public void initInBackground(ServerLocation serverLocation) {
        init(serverLocation);
    }

    public void initInBackground(ServerLocation serverLocation, InitParams initParams) {
        com.jd.sec.a.a(initParams);
        initInBackground(serverLocation);
    }

    public void setDebugMode(boolean z) {
    }

    public void setDebugMode(boolean z, boolean z2) {
    }
}
