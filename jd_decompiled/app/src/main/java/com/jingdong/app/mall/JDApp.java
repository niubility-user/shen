package com.jingdong.app.mall;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.multidex.MultiDex;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.utils.q;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.jdsdk.JdSdk;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class JDApp extends MyApplication {
    private static String PHASE_ATTACH = "attachBaseContext";
    private static String PHASE_ONCREATE = "onCreate";
    private static String PHASE_REINIT = "reInit";
    private static JDApp instance;
    Method mtdConfigurationChanged;
    Method mtdCreated;
    Method mtdInitStatus;
    Method mtdLazyInit;
    Method mtdReInit;
    private Object processInit;
    private boolean successInit = true;
    private StringBuilder errorMsg = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f7585g;

        a(JDApp jDApp, Context context) {
            this.f7585g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            MultiDex.install(this.f7585g);
        }
    }

    static {
        loadLib();
    }

    public static JDApp getInstance() {
        return instance;
    }

    private void initAppLike(Context context) {
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.JDAppLikeInitImpl", false, context.getClassLoader());
            this.processInit = cls.newInstance();
            Method declaredMethod = cls.getDeclaredMethod(XView2Constants.XVIEW2_ACTION_INIT, Application.class);
            Method declaredMethod2 = cls.getDeclaredMethod("onBaseContextAttached", Context.class);
            this.mtdCreated = cls.getDeclaredMethod("onCreate", new Class[0]);
            this.mtdConfigurationChanged = cls.getDeclaredMethod("onConfigurationChanged", Configuration.class);
            this.mtdLazyInit = cls.getDeclaredMethod("isLazyInit", new Class[0]);
            this.mtdInitStatus = cls.getDeclaredMethod("initStatus", new Class[0]);
            this.mtdReInit = cls.getDeclaredMethod("reInit", new Class[0]);
            Object obj = this.processInit;
            if (obj != null) {
                declaredMethod.invoke(obj, this);
                declaredMethod2.invoke(this.processInit, context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            recordInitError(PHASE_ATTACH, th);
        }
    }

    static void loadLib() {
        System.loadLibrary("JDMobileSec");
    }

    private void loadMultiDex(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            MultiDex.install(context);
            return;
        }
        q.c().b(10);
        q.c().d(new a(this, context));
        q.c().f();
        q.c().e();
    }

    private void recordInitError(String str, Throwable th) {
        int i2 = 0;
        this.successInit = false;
        if (TextUtils.isEmpty(str) || th == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (th.getCause() != null) {
            try {
                th = th.getCause();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i3 = 30;
        if (stackTrace != null) {
            int length = stackTrace.length;
            while (i2 < length) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                int i4 = i3 - 1;
                if (i3 <= 0) {
                    break;
                }
                sb.append(stackTraceElement.toString());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                i2++;
                i3 = i4;
            }
        }
        StringBuilder sb2 = this.errorMsg;
        sb2.append("phase:");
        sb2.append(str);
        sb2.append("  stack::");
        sb2.append((CharSequence) sb);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        c.a = SystemClock.elapsedRealtime();
        instance = this;
        OpenLinkTimeManager.getInstance().start();
        try {
            c.c(com.doupo.linker.a.c(this));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            q.c().start();
        } catch (IllegalThreadStateException e2) {
            e2.printStackTrace();
        }
        loadMultiDex(context);
        initAppLike(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        return applicationContext != null ? applicationContext : this;
    }

    public String getInitErrorMsg() {
        StringBuilder sb = this.errorMsg;
        return sb != null ? sb.toString() : "";
    }

    public boolean initStatus() {
        Method method;
        Object obj = this.processInit;
        if (obj != null && (method = this.mtdInitStatus) != null) {
            try {
                return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public boolean isLazyInit() {
        Method method;
        Object obj = this.processInit;
        if (obj == null || (method = this.mtdLazyInit) == null) {
            return true;
        }
        try {
            return ((Boolean) method.invoke(obj, new Object[0])).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public boolean isSuccessInit() {
        return this.successInit;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        Method method;
        super.onConfigurationChanged(configuration);
        Object obj = this.processInit;
        if (obj == null || (method = this.mtdConfigurationChanged) == null) {
            return;
        }
        try {
            method.invoke(obj, configuration);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        Method method;
        super.onCreate();
        Object obj = this.processInit;
        if (obj != null && (method = this.mtdCreated) != null) {
            try {
                method.invoke(obj, new Object[0]);
            } catch (Throwable th) {
                th.printStackTrace();
                recordInitError(PHASE_ONCREATE, th);
            }
        }
        JdSdk.getInstance().setAppInitialized();
    }

    public void reInit() {
        Method method;
        Object obj = this.processInit;
        if (obj == null || (method = this.mtdReInit) == null) {
            return;
        }
        try {
            method.invoke(obj, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
            recordInitError(PHASE_REINIT, th);
        }
    }
}
