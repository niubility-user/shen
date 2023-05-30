package com.jingdong.aura.a.c;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.jd.dynamic.DYConstants;
import com.jingdong.aura.core.reflection.Hack;
import com.jingdong.aura.core.runing.resource.DelegateResourcesUtils;
import com.jingdong.aura.core.util.FakeActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class i extends Instrumentation {
    private Context a;
    private Instrumentation b;
    private static Map<String, Integer> d = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12134c = com.jingdong.aura.core.util.l.c.a("InstrumentationHook");

    public i(Instrumentation instrumentation, Context context) {
        this.a = context;
        this.b = instrumentation;
    }

    private String a(String str, String str2) {
        String str3;
        String str4;
        String str5 = "";
        try {
            com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) com.jingdong.aura.a.b.k.b.b(str);
            if (hVar != null) {
                String str6 = "versioncode:" + hVar.l() + ";";
                j a = g.a(hVar.b());
                if (a == null) {
                    str3 = str6 + "packageLite:null;";
                } else {
                    String str7 = str6 + "packageLite:not null,";
                    if (!a.f12136e.contains(str2) && !a.f12137f.contains(str2)) {
                        str3 = str7 + "not contain;";
                    }
                    str3 = str7 + "contain;";
                }
                String str8 = str3;
                File b = hVar.e().b();
                if (b != null && b.exists()) {
                    str4 = str8 + "archiveFile:" + b.getAbsolutePath() + ";md5:" + com.jingdong.aura.core.util.d.b(b.getAbsolutePath()) + ";";
                } else {
                    str4 = str8 + "archiveFile:null;md5:null;";
                }
                str5 = str4;
                ClassLoader g2 = hVar.g();
                StringBuilder sb = new StringBuilder();
                sb.append(str5);
                sb.append(";classloader:");
                sb.append(g2 == null ? DYConstants.DY_NULL_STR : "not null");
                return sb.toString();
            }
            return "bundleImpl:null";
        } catch (Exception e2) {
            e2.printStackTrace();
            return str5;
        }
    }

    private Activity b(ClassLoader classLoader, String str, Intent intent) {
        Intent isRedirectToLoadingDexPage;
        if (com.jingdong.aura.a.b.c.g()) {
            if (com.jingdong.aura.a.b.c.F().isForeground() && com.jingdong.aura.a.b.c.m() != null) {
                if (intent.getComponent() == null) {
                    intent.setClassName(this.a, str);
                }
                isRedirectToLoadingDexPage = com.jingdong.aura.a.b.c.m().isRedirectToLoadingDexPage(intent);
            }
            isRedirectToLoadingDexPage = null;
        } else {
            if (com.jingdong.aura.a.b.c.m() != null) {
                if (intent.getComponent() == null) {
                    intent.setClassName(this.a, str);
                }
                isRedirectToLoadingDexPage = com.jingdong.aura.a.b.c.m().isRedirectToLoadingDexPage(intent);
            }
            isRedirectToLoadingDexPage = null;
        }
        if (isRedirectToLoadingDexPage == null) {
            return null;
        }
        String className = isRedirectToLoadingDexPage.getComponent().getClassName();
        f12134c.c("redirectToLoadingDexPage to : " + className);
        return this.b.newActivity(classLoader, className, isRedirectToLoadingDexPage);
    }

    @Override // android.app.Instrumentation
    public void addMonitor(Instrumentation.ActivityMonitor activityMonitor) {
        this.b.addMonitor(activityMonitor);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        b(activity);
        if (!l.a.getPackageName().equals(activity.getPackageName())) {
            this.b.callActivityOnCreate(activity, bundle);
            a(activity);
            return;
        }
        DelegateResourcesUtils.updateConfiguration(activity);
        e eVar = new e(activity.getBaseContext(), activity.getClass().getClassLoader());
        Hack.d<ContextThemeWrapper, Context> dVar = com.jingdong.aura.core.reflection.b.z;
        if (dVar != null && dVar.a() != null) {
            com.jingdong.aura.core.reflection.b.z.a((Hack.d<ContextThemeWrapper, Context>) activity, eVar);
        }
        com.jingdong.aura.core.reflection.b.x.a((Hack.d<ContextWrapper, Context>) activity, eVar);
        if (activity.getClass().getClassLoader() instanceof com.jingdong.aura.a.b.j.c) {
            try {
                ((com.jingdong.aura.a.b.j.c) activity.getClass().getClassLoader()).a().n();
                if (com.jingdong.aura.a.b.c.y()) {
                    Intent intent = activity.getIntent();
                    ClassLoader classLoader = activity.getClassLoader();
                    if (classLoader == null) {
                        classLoader = activity.getClass().getClassLoader();
                    }
                    if (intent != null && classLoader != null) {
                        intent.setExtrasClassLoader(classLoader);
                    }
                }
            } catch (l.b.a.b e2) {
                f12134c.a(e2.getMessage() + " Caused by: ", e2.getNestedException());
            }
        }
        try {
            DelegateResourcesUtils.ensureResourcesInjected(activity);
            this.b.callActivityOnCreate(activity, bundle);
            a(activity);
        } catch (Exception e3) {
            if (!e3.toString().contains("android.content.res.Resources") || e3.toString().contains("OutOfMemoryError")) {
                e3.printStackTrace();
            }
            a(activity, bundle, e3);
            throw null;
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        this.b.callActivityOnDestroy(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        this.b.callActivityOnNewIntent(activity, intent);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        this.b.callActivityOnPause(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPostCreate(Activity activity, Bundle bundle) {
        this.b.callActivityOnPostCreate(activity, bundle);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        this.b.callActivityOnRestart(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle bundle) {
        this.b.callActivityOnRestoreInstanceState(activity, bundle);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        this.b.callActivityOnResume(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle) {
        this.b.callActivityOnSaveInstanceState(activity, bundle);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        this.b.callActivityOnStart(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStop(Activity activity) {
        this.b.callActivityOnStop(activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnUserLeaving(Activity activity) {
        this.b.callActivityOnUserLeaving(activity);
    }

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application application) {
        this.b.callApplicationOnCreate(application);
    }

    @Override // android.app.Instrumentation
    public boolean checkMonitorHit(Instrumentation.ActivityMonitor activityMonitor, int i2) {
        return this.b.checkMonitorHit(activityMonitor, i2);
    }

    @Override // android.app.Instrumentation
    public void endPerformanceSnapshot() {
        this.b.endPerformanceSnapshot();
    }

    @Override // android.app.Instrumentation
    public void finish(int i2, Bundle bundle) {
        this.b.finish(i2, bundle);
    }

    @Override // android.app.Instrumentation
    public Bundle getAllocCounts() {
        return this.b.getAllocCounts();
    }

    @Override // android.app.Instrumentation
    public Bundle getBinderCounts() {
        return this.b.getBinderCounts();
    }

    @Override // android.app.Instrumentation
    public ComponentName getComponentName() {
        return this.b.getComponentName();
    }

    @Override // android.app.Instrumentation
    public Context getContext() {
        return this.b.getContext();
    }

    @Override // android.app.Instrumentation
    public Context getTargetContext() {
        return this.b.getTargetContext();
    }

    @Override // android.app.Instrumentation
    @TargetApi(18)
    public UiAutomation getUiAutomation() {
        return this.b.getUiAutomation();
    }

    @Override // android.app.Instrumentation
    public boolean invokeContextMenuAction(Activity activity, int i2, int i3) {
        return this.b.invokeContextMenuAction(activity, i2, i3);
    }

    @Override // android.app.Instrumentation
    public boolean invokeMenuActionSync(Activity activity, int i2, int i3) {
        return this.b.invokeMenuActionSync(activity, i2, i3);
    }

    @Override // android.app.Instrumentation
    public boolean isProfiling() {
        return this.b.isProfiling();
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(Class<?> cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) {
        Hack.d<ContextThemeWrapper, Resources> dVar;
        Activity newActivity = this.b.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
        if (l.a.getPackageName().equals(activityInfo.packageName) && (dVar = com.jingdong.aura.core.reflection.b.A) != null && (newActivity instanceof ContextThemeWrapper)) {
            dVar.a((Hack.d<ContextThemeWrapper, Resources>) newActivity, l.d);
        }
        return newActivity;
    }

    @Override // android.app.Instrumentation
    public Application newApplication(ClassLoader classLoader, String str, Context context) {
        return this.b.newApplication(classLoader, str, context);
    }

    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        this.b.onCreate(bundle);
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        this.b.onDestroy();
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable th) {
        return this.b.onException(obj, th);
    }

    @Override // android.app.Instrumentation
    public void onStart() {
        this.b.onStart();
    }

    @Override // android.app.Instrumentation
    public void removeMonitor(Instrumentation.ActivityMonitor activityMonitor) {
        this.b.removeMonitor(activityMonitor);
    }

    @Override // android.app.Instrumentation
    public void runOnMainSync(Runnable runnable) {
        this.b.runOnMainSync(runnable);
    }

    @Override // android.app.Instrumentation
    public void sendCharacterSync(int i2) {
        this.b.sendCharacterSync(i2);
    }

    @Override // android.app.Instrumentation
    public void sendKeyDownUpSync(int i2) {
        this.b.sendKeyDownUpSync(i2);
    }

    @Override // android.app.Instrumentation
    public void sendKeySync(KeyEvent keyEvent) {
        this.b.sendKeySync(keyEvent);
    }

    @Override // android.app.Instrumentation
    public void sendPointerSync(MotionEvent motionEvent) {
        this.b.sendPointerSync(motionEvent);
    }

    @Override // android.app.Instrumentation
    public void sendStatus(int i2, Bundle bundle) {
        this.b.sendStatus(i2, bundle);
    }

    @Override // android.app.Instrumentation
    public void sendStringSync(String str) {
        this.b.sendStringSync(str);
    }

    @Override // android.app.Instrumentation
    public void sendTrackballEventSync(MotionEvent motionEvent) {
        this.b.sendTrackballEventSync(motionEvent);
    }

    @Override // android.app.Instrumentation
    public void setAutomaticPerformanceSnapshots() {
        this.b.setAutomaticPerformanceSnapshots();
    }

    @Override // android.app.Instrumentation
    public void setInTouchMode(boolean z) {
        this.b.setInTouchMode(z);
    }

    @Override // android.app.Instrumentation
    public void start() {
        this.b.start();
    }

    @Override // android.app.Instrumentation
    public Activity startActivitySync(Intent intent) {
        return this.b.startActivitySync(intent);
    }

    @Override // android.app.Instrumentation
    public void startAllocCounting() {
        this.b.startAllocCounting();
    }

    @Override // android.app.Instrumentation
    public void startPerformanceSnapshot() {
        this.b.startPerformanceSnapshot();
    }

    @Override // android.app.Instrumentation
    public void startProfiling() {
        this.b.startProfiling();
    }

    @Override // android.app.Instrumentation
    public void stopAllocCounting() {
        this.b.stopAllocCounting();
    }

    @Override // android.app.Instrumentation
    public void stopProfiling() {
        this.b.stopProfiling();
    }

    @Override // android.app.Instrumentation
    public void waitForIdle(Runnable runnable) {
        this.b.waitForIdle(runnable);
    }

    @Override // android.app.Instrumentation
    public void waitForIdleSync() {
        this.b.waitForIdleSync();
    }

    @Override // android.app.Instrumentation
    public Activity waitForMonitor(Instrumentation.ActivityMonitor activityMonitor) {
        return this.b.waitForMonitor(activityMonitor);
    }

    @Override // android.app.Instrumentation
    public Activity waitForMonitorWithTimeout(Instrumentation.ActivityMonitor activityMonitor, long j2) {
        return this.b.waitForMonitorWithTimeout(activityMonitor, j2);
    }

    @Override // android.app.Instrumentation
    public Instrumentation.ActivityMonitor addMonitor(IntentFilter intentFilter, Instrumentation.ActivityResult activityResult, boolean z) {
        return this.b.addMonitor(intentFilter, activityResult, z);
    }

    @Override // android.app.Instrumentation
    public Instrumentation.ActivityMonitor addMonitor(String str, Instrumentation.ActivityResult activityResult, boolean z) {
        return this.b.addMonitor(str, activityResult, z);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) {
        Activity a;
        f12134c.e("newActivity called. className = " + str + " intent:" + intent);
        Activity b = b(classLoader, str, intent);
        if (b == null) {
            try {
                b = this.b.newActivity(classLoader, str, intent);
            } catch (Throwable th) {
                f12134c.b("newActivity() catched a throwable", th);
                String b2 = com.jingdong.aura.a.a.a.c().b(str);
                if (b2 != null) {
                    if (d.containsKey(b2)) {
                        Map<String, Integer> map = d;
                        map.put(b2, Integer.valueOf(map.get(b2).intValue() + 1));
                    } else {
                        d.put(b2, 1);
                    }
                    l.b.a.d b3 = com.jingdong.aura.a.b.k.b.b(b2);
                    String a2 = a(b2, str);
                    if (b3 != null) {
                        try {
                            b3.c();
                        } catch (l.b.a.b e2) {
                            f12134c.b("uninstall bundle failed.", e2);
                        }
                    }
                    ArrayList<String> g2 = com.jingdong.aura.a.b.c.g(b2);
                    if (g2 != null && g2.size() > 0 && !TextUtils.isEmpty(com.jingdong.aura.a.b.c.m().getProvidedBundleNotFoundPageName())) {
                        com.jingdong.aura.a.b.e.a(b2, "newActivity failed. try to start provided not found page", "InstrumentaionHook.newActivity_4", th);
                        return a(classLoader, str, intent, b2, g2);
                    }
                    com.jingdong.aura.a.b.e.a(b2, "newActivity failed. error count = " + d.get(b2) + ". " + a2, "InstrumentaionHook.newActivity_2", th);
                    com.jingdong.aura.a.b.k.b.a(b2);
                } else {
                    com.jingdong.aura.a.b.e.a(b2, "newActivity failed.", "InstrumentaionHook.newActivity_3", th);
                }
                com.jingdong.aura.a.b.e.a(b2, "UFOPage", str, "newActivity failed.", "InstrumentationHook.newActivity", th);
                a = a(classLoader, str, intent);
                if (a == null) {
                    com.jingdong.aura.a.b.e.a(b2, "newActivity", str, "newActivity is null", "InstrumentationHook.newActivity", th);
                    throw th;
                }
            }
        }
        a = b;
        if (a != null && (a instanceof ContextThemeWrapper) && (classLoader instanceof f) && com.jingdong.aura.core.reflection.b.A != null) {
            f12134c.e("hook contextThemeWrapper.mResources 1");
            com.jingdong.aura.core.reflection.b.A.a((Hack.d<ContextThemeWrapper, Resources>) a, l.d);
        }
        f12134c.e("newActivity end.");
        return a;
    }

    private void b(Activity activity) {
        if (com.jingdong.aura.a.b.c.w() && com.jingdong.aura.a.b.c.j() != null) {
            com.jingdong.aura.a.b.c.j().beforeCallActivityOnCreate(activity);
        }
    }

    private Activity a(ClassLoader classLoader, String str, Intent intent, String str2, ArrayList<String> arrayList) {
        String providedBundleNotFoundPageName = com.jingdong.aura.a.b.c.m().getProvidedBundleNotFoundPageName();
        if (!TextUtils.isEmpty(providedBundleNotFoundPageName)) {
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(l.a, providedBundleNotFoundPageName));
            Uri data = intent.getData();
            Bundle extras = intent.getExtras();
            if (data != null) {
                intent2.setData(data);
            }
            if (extras != null) {
                intent2.replaceExtras(extras);
            }
            intent2.setExtrasClassLoader(l.b);
            intent2.putExtra("aura_target_classname", str);
            intent2.putExtra("aura_target_bundlename", str2);
            intent2.putStringArrayListExtra("aura_not_prepared_bundlename", arrayList);
            intent2.addFlags(268435456);
            this.a.startActivity(intent2);
            f12134c.c("start provided not found page: " + intent2.getComponent().getClassName());
        }
        Intent intent3 = new Intent();
        intent3.setClass(this.a, FakeActivity.class);
        String className = intent3.getComponent().getClassName();
        f12134c.c("redirectToClassNotFoundPage to : " + className);
        return this.b.newActivity(classLoader, className, intent3);
    }

    private Activity a(ClassLoader classLoader, String str, Intent intent) {
        Intent classNotFoundPage;
        if (com.jingdong.aura.a.b.c.g() && com.jingdong.aura.a.b.c.d()) {
            if (com.jingdong.aura.a.b.c.F().isForeground() && com.jingdong.aura.a.b.c.m() != null) {
                if (intent.getComponent() == null) {
                    intent.setClassName(this.a, str);
                }
                classNotFoundPage = com.jingdong.aura.a.b.c.m().getClassNotFoundPage(intent);
            }
            classNotFoundPage = null;
        } else {
            if (com.jingdong.aura.a.b.c.m() != null) {
                if (intent.getComponent() == null) {
                    intent.setClassName(this.a, str);
                }
                classNotFoundPage = com.jingdong.aura.a.b.c.m().getClassNotFoundPage(intent);
            }
            classNotFoundPage = null;
        }
        if (classNotFoundPage == null) {
            return null;
        }
        String className = classNotFoundPage.getComponent().getClassName();
        f12134c.c("redirectToClassNotFoundPage to : " + className);
        return this.b.newActivity(classLoader, className, classNotFoundPage);
    }

    private void a(Activity activity, Bundle bundle, Exception exc) {
        String str;
        Hack.d<ContextThemeWrapper, Resources> dVar = com.jingdong.aura.core.reflection.b.A;
        if (dVar != null) {
            try {
                str = "(1)Paths in ContextThemeWrapper_mResources:" + DelegateResourcesUtils.getAssetPathFromResources(dVar.a((Hack.d<ContextThemeWrapper, Resources>) activity)) + " paths in runtime:" + DelegateResourcesUtils.getRuntimeAssetHistoryPaths();
            } catch (Exception e2) {
                str = "(2)paths in runtime:" + DelegateResourcesUtils.getRuntimeAssetHistoryPaths() + " getAssetPath fail: " + e2;
            }
            throw new RuntimeException(str, exc);
        }
        throw new RuntimeException("(3)ContextThemeWrapper_mResources is null paths in runtime:" + DelegateResourcesUtils.getRuntimeAssetHistoryPaths(), exc);
    }

    private void a(Activity activity) {
        if (com.jingdong.aura.a.b.c.w() && com.jingdong.aura.a.b.c.j() != null) {
            com.jingdong.aura.a.b.c.j().afterCallActivityOnCreate(activity);
        }
    }
}
