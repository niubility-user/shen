package com.jingdong.manto.launch;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.card.CardPreviewActivity;
import com.jingdong.manto.card.MantoLightActivity;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.ui.MantoDebugNativeActivity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.m;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i {
    private static Handler a;
    private static HandlerThread b;

    /* renamed from: c  reason: collision with root package name */
    public static long f13269c;
    private static WeakReference<Context> d;

    /* renamed from: e  reason: collision with root package name */
    public static long f13270e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ LaunchParam a;
        final /* synthetic */ Context b;

        a(LaunchParam launchParam, Context context) {
            this.a = launchParam;
            this.b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject = new JSONObject();
            String str = (TextUtils.isEmpty(this.a.debugType) || TextUtils.equals(DYConstants.DY_NULL_STR, this.a.debugType)) ? "1" : this.a.debugType;
            try {
                jSONObject.put("vapp_type", str);
                String str2 = "0";
                jSONObject.put(BaseEvent.SCENE, MantoStringUtils.isEmpty(this.a.scene) ? "0" : this.a.scene);
                PkgDetailEntity c2 = com.jingdong.manto.b.k().c(this.a.appId, str);
                if (c2 != null) {
                    str2 = c2.build;
                }
                jSONObject.put("originalBuildId", str2);
                jSONObject.put("coldFlag", com.jingdong.manto.n.c.a(this.a.appId) && c2 != null && c2.isSwitchOpen(8) && TextUtils.equals("1", str) ? "2" : "1");
            } catch (Throwable th) {
                MantoLog.e(DYConstants.DY_TRACK, th);
            }
            Context context = this.b;
            LaunchParam launchParam = this.a;
            MantoTrack.sendCommonDataWithExt(context, "\u6253\u5f00", "applets_open", launchParam.appId, "", launchParam.sourcePath, jSONObject.toString(), "", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements MantoPreLaunchProcess.b {
        final /* synthetic */ g a;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ com.jingdong.manto.i.c a;

            a(b bVar, com.jingdong.manto.i.c cVar) {
                this.a = cVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.a((Context) i.d.get(), this.a);
                com.jingdong.manto.preload.c a = com.jingdong.manto.preload.c.a();
                com.jingdong.manto.i.c cVar = this.a;
                a.a(cVar.a, cVar.f13082e);
            }
        }

        /* renamed from: com.jingdong.manto.launch.i$b$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0541b implements Runnable {
            final /* synthetic */ MantoPreLaunchProcess.LaunchError a;

            RunnableC0541b(MantoPreLaunchProcess.LaunchError launchError) {
                this.a = launchError;
            }

            @Override // java.lang.Runnable
            public void run() {
                g gVar = b.this.a;
                if (gVar == null) {
                    i.b(this.a);
                    return;
                }
                gVar.d = this.a;
                gVar.g();
            }
        }

        b(g gVar) {
            this.a = gVar;
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void a(com.jingdong.manto.i.c cVar) {
            if (cVar != null) {
                i.f13270e = System.currentTimeMillis() - i.f13269c;
                MantoThreadUtils.runOnUIThreadImmediately(new a(this, cVar));
            }
        }

        @Override // com.jingdong.manto.launch.MantoPreLaunchProcess.b
        public void onLaunchError(MantoPreLaunchProcess.LaunchError launchError) {
            MantoThreadUtils.runOnUIThread(new RunnableC0541b(launchError));
        }
    }

    public static void a(Context context, LaunchParam launchParam) {
        a(context, launchParam, (g) null);
    }

    public static void a(Context context, LaunchParam launchParam, g gVar) {
        if (Math.abs(System.currentTimeMillis() - f13269c) < 200) {
            return;
        }
        d = new WeakReference<>(context);
        f13269c = System.currentTimeMillis();
        if (TextUtils.equals("1", m.a("useLaunchProxy", launchParam.launchProxy))) {
            LaunchProxyActivity.a(context, launchParam, gVar);
        } else {
            a(launchParam, gVar);
        }
        System.currentTimeMillis();
        com.jingdong.manto.b.d().networkIO().execute(new a(launchParam, context));
    }

    public static void a(LaunchParam launchParam) {
        a(launchParam, MantoProcessUtil.getContext());
    }

    public static void a(LaunchParam launchParam, Context context) {
        Intent intent;
        if (!TextUtils.isEmpty(launchParam.appId) && Math.abs(System.currentTimeMillis() - f13269c) >= 200) {
            if (launchParam.isCard) {
                if (context == null) {
                    context = com.jingdong.a.g();
                }
                intent = new Intent(context, CardPreviewActivity.class);
            } else if (TextUtils.equals(launchParam.mpMode, "1") || TextUtils.equals(launchParam.mpMode, "2") || TextUtils.equals(launchParam.mpMode, "3")) {
                if (context == null) {
                    context = com.jingdong.a.g();
                }
                com.jingdong.manto.r.d.d = System.currentTimeMillis();
                intent = new Intent(context, MantoLightActivity.class);
            } else if (!TextUtils.equals(launchParam.debugType, LaunchParam.IDE_NATIVE_CMD)) {
                if (MantoProcessUtil.isMainProcess()) {
                    a(context, launchParam);
                    return;
                } else {
                    new g(launchParam).e();
                    return;
                }
            } else {
                if (context == null) {
                    context = com.jingdong.a.g();
                }
                intent = new Intent(context, MantoDebugNativeActivity.class);
            }
            intent.addFlags(268435456);
            intent.putExtra(ConstantsAPI.Token.WX_LAUNCH_PARAM_KEY, launchParam);
            context.startActivity(intent);
        }
    }

    private static void a(LaunchParam launchParam, g gVar) {
        b();
        a.post(new MantoPreLaunchProcess(launchParam, new b(gVar)));
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a");
        }
        LaunchParam launchParam = new LaunchParam();
        launchParam.debugType = "14";
        launchParam.sourcePath = str;
        launchParam.appId = str2;
        a(launchParam);
    }

    public static void a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a");
        }
        LaunchParam launchParam = new LaunchParam();
        launchParam.debugType = "14";
        launchParam.sourcePath = str;
        launchParam.appId = str2;
        launchParam.sourceSubPkgJson = str3;
        a(launchParam);
    }

    public static void a(JSONObject jSONObject, Context context) {
        context.getSharedPreferences("mini-dev-mode", 0).edit().putString("key", jSONObject.toString()).commit();
        LaunchParam launchParam = new LaunchParam();
        launchParam.sourcePath = null;
        launchParam.appId = jSONObject.optString("appId", "demoAppId");
        launchParam.launchPath = null;
        launchParam.debugType = "13";
        launchParam.version = 0;
        launchParam.launchReferrer = null;
        a(launchParam, context);
    }

    private static void b() {
        if (b == null) {
            HandlerThread handlerThread = new HandlerThread("MantoLaunch-Thread");
            b = handlerThread;
            handlerThread.start();
            a = new Handler(b.getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(MantoPreLaunchProcess.LaunchError launchError) {
        c.a(launchError);
    }
}
