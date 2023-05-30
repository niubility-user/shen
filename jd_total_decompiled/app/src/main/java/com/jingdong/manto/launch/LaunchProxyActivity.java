package com.jingdong.manto.launch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.Nullable;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.utils.MantoThreadUtils;

/* loaded from: classes15.dex */
public class LaunchProxyActivity extends Activity {
    private Handler a;
    private HandlerThread b;

    /* renamed from: c */
    private boolean f13245c;
    private boolean d;

    /* loaded from: classes15.dex */
    public class a implements MantoPreLaunchProcess.b {
        final /* synthetic */ g a;

        /* renamed from: com.jingdong.manto.launch.LaunchProxyActivity$a$a */
        /* loaded from: classes15.dex */
        class RunnableC0537a implements Runnable {
            final /* synthetic */ com.jingdong.manto.i.c a;

            RunnableC0537a(com.jingdong.manto.i.c cVar) {
                a.this = r1;
                this.a = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.a(LaunchProxyActivity.this, this.a);
                com.jingdong.manto.preload.c a = com.jingdong.manto.preload.c.a();
                com.jingdong.manto.i.c cVar = this.a;
                a.a(cVar.a, cVar.f13082e);
                LaunchProxyActivity.this.d = !this.a.d();
                LaunchProxyActivity.this.finish();
                LaunchProxyActivity.this.overridePendingTransition(0, 0);
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

            b(MantoPreLaunchProcess.LaunchError launchError) {
                a.this = r1;
                this.a = launchError;
            }

            @Override // java.lang.Runnable
            public void run() {
                g gVar = a.this.a;
                if (gVar == null) {
                    c.a(this.a);
                } else {
                    gVar.d = this.a;
                    gVar.g();
                }
                LaunchProxyActivity.this.finish();
                LaunchProxyActivity.this.overridePendingTransition(0, 0);
            }
        }

        a(g gVar) {
            LaunchProxyActivity.this = r1;
            this.a = gVar;
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void a(com.jingdong.manto.i.c cVar) {
            if (cVar != null) {
                i.f13270e = System.currentTimeMillis() - i.f13269c;
                MantoThreadUtils.runOnUIThreadImmediately(new RunnableC0537a(cVar));
            }
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void onLaunchError(MantoPreLaunchProcess.LaunchError launchError) {
            MantoThreadUtils.runOnUIThread(new b(launchError));
        }
    }

    private void a() {
        if (this.b == null) {
            HandlerThread handlerThread = new HandlerThread("LaunchProxy-Thread");
            this.b = handlerThread;
            handlerThread.start();
            this.a = new Handler(this.b.getLooper());
        }
    }

    public static void a(Context context, LaunchParam launchParam, g gVar) {
        if (context == null) {
            context = com.jingdong.a.g();
        }
        Intent intent = new Intent(context, LaunchProxyActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("launchParcel", launchParam);
        bundle.putParcelable("launchTask", gVar);
        intent.putExtra("bundle", bundle);
        intent.setExtrasClassLoader(LaunchParam.class.getClassLoader());
        context.startActivity(intent);
    }

    private void a(LaunchParam launchParam, g gVar) {
        a();
        this.a.post(new MantoPreLaunchProcess(launchParam, new a(gVar)));
    }

    @Override // android.app.Activity
    public void finish() {
        if (Build.VERSION.SDK_INT < 21 || !this.d) {
            super.finish();
        } else {
            super.finishAndRemoveTask();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        g gVar;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            intent.setExtrasClassLoader(LaunchParam.class.getClassLoader());
            Bundle bundleExtra = intent.getBundleExtra("bundle");
            LaunchParam launchParam = null;
            if (bundleExtra != null) {
                launchParam = (LaunchParam) bundleExtra.getParcelable("launchParcel");
                gVar = (g) bundleExtra.getParcelable("launchTask");
            } else {
                gVar = null;
            }
            if (launchParam != null) {
                a(launchParam, gVar);
                return;
            }
        }
        finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f13245c) {
            finish();
        } else {
            this.f13245c = true;
        }
    }
}
