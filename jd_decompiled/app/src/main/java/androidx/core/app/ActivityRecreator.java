package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
final class ActivityRecreator {
    private static final String LOG_TAG = "ActivityRecreator";
    protected static final Class<?> activityThreadClass;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    protected static final Field mainThreadField;
    protected static final Method performStopActivity2ParamsMethod;
    protected static final Method performStopActivity3ParamsMethod;
    protected static final Method requestRelaunchActivityMethod;
    protected static final Field tokenField;

    /* loaded from: classes.dex */
    private static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
        Object currentlyRecreatingToken;
        private Activity mActivity;
        private boolean mStarted = false;
        private boolean mDestroyed = false;
        private boolean mStopQueued = false;

        LifecycleCheckCallbacks(@NonNull Activity activity) {
            this.mActivity = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.mActivity == activity) {
                this.mActivity = null;
                this.mDestroyed = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.mDestroyed || this.mStopQueued || this.mStarted || !ActivityRecreator.queueOnStopIfNecessary(this.currentlyRecreatingToken, activity)) {
                return;
            }
            this.mStopQueued = true;
            this.currentlyRecreatingToken = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.mActivity == activity) {
                this.mStarted = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> activityThreadClass2 = getActivityThreadClass();
        activityThreadClass = activityThreadClass2;
        mainThreadField = getMainThreadField();
        tokenField = getTokenField();
        performStopActivity3ParamsMethod = getPerformStopActivity3Params(activityThreadClass2);
        performStopActivity2ParamsMethod = getPerformStopActivity2Params(activityThreadClass2);
        requestRelaunchActivityMethod = getRequestRelaunchActivityMethod(activityThreadClass2);
    }

    private ActivityRecreator() {
    }

    private static Class<?> getActivityThreadClass() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Field getMainThreadField() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method getPerformStopActivity2Params(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method getPerformStopActivity3Params(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method getRequestRelaunchActivityMethod(Class<?> cls) {
        if (needsRelaunchCall() && cls != null) {
            try {
                Class<?> cls2 = Boolean.TYPE;
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, cls2, Configuration.class, Configuration.class, cls2, cls2);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static Field getTokenField() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean needsRelaunchCall() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 == 26 || i2 == 27;
    }

    protected static boolean queueOnStopIfNecessary(Object obj, Activity activity) {
        try {
            final Object obj2 = tokenField.get(activity);
            if (obj2 != obj) {
                return false;
            }
            mainThreadField.get(activity);
            mainHandler.postAtFrontOfQueue(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
                  (wrap: android.os.Handler : 0x0010: SGET  A[Catch: all -> 0x001c, WRAPPED] (LINE:3) androidx.core.app.ActivityRecreator.mainHandler android.os.Handler)
                  (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR (r3 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r1v1 'obj2' java.lang.Object A[DONT_INLINE]) A[Catch: all -> 0x001c, MD:(java.lang.Object, java.lang.Object):void (m), WRAPPED] call: androidx.core.app.ActivityRecreator.3.<init>(java.lang.Object, java.lang.Object):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.os.Handler.postAtFrontOfQueue(java.lang.Runnable):boolean A[Catch: all -> 0x001c, MD:(java.lang.Runnable):boolean (c), TRY_LEAVE] (LINE:3) in method: androidx.core.app.ActivityRecreator.queueOnStopIfNecessary(java.lang.Object, android.app.Activity):boolean, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                r0 = 0
                java.lang.reflect.Field r1 = androidx.core.app.ActivityRecreator.tokenField     // Catch: java.lang.Throwable -> L1c
                java.lang.Object r1 = r1.get(r4)     // Catch: java.lang.Throwable -> L1c
                if (r1 == r3) goto La
                return r0
            La:
                java.lang.reflect.Field r3 = androidx.core.app.ActivityRecreator.mainThreadField     // Catch: java.lang.Throwable -> L1c
                java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L1c
                android.os.Handler r4 = androidx.core.app.ActivityRecreator.mainHandler     // Catch: java.lang.Throwable -> L1c
                androidx.core.app.ActivityRecreator$3 r2 = new androidx.core.app.ActivityRecreator$3     // Catch: java.lang.Throwable -> L1c
                r2.<init>()     // Catch: java.lang.Throwable -> L1c
                r4.postAtFrontOfQueue(r2)     // Catch: java.lang.Throwable -> L1c
                r3 = 1
                return r3
            L1c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ActivityRecreator.queueOnStopIfNecessary(java.lang.Object, android.app.Activity):boolean");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean recreate(@NonNull Activity activity) {
            Object obj;
            if (Build.VERSION.SDK_INT >= 28) {
                activity.recreate();
                return true;
            } else if (needsRelaunchCall() && requestRelaunchActivityMethod == null) {
                return false;
            } else {
                if (performStopActivity2ParamsMethod == null && performStopActivity3ParamsMethod == null) {
                    return false;
                }
                try {
                    final Object obj2 = tokenField.get(activity);
                    if (obj2 == null || (obj = mainThreadField.get(activity)) == null) {
                        return false;
                    }
                    final Application application = activity.getApplication();
                    final LifecycleCheckCallbacks lifecycleCheckCallbacks = new LifecycleCheckCallbacks(activity);
                    application.registerActivityLifecycleCallbacks(lifecycleCheckCallbacks);
                    Handler handler = mainHandler;
                    handler.post(new Runnable() { // from class: androidx.core.app.ActivityRecreator.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LifecycleCheckCallbacks.this.currentlyRecreatingToken = obj2;
                        }
                    });
                    if (needsRelaunchCall()) {
                        Method method = requestRelaunchActivityMethod;
                        Boolean bool = Boolean.FALSE;
                        method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                    } else {
                        activity.recreate();
                    }
                    handler.post(new Runnable() { // from class: androidx.core.app.ActivityRecreator.2
                        @Override // java.lang.Runnable
                        public void run() {
                            application.unregisterActivityLifecycleCallbacks(lifecycleCheckCallbacks);
                        }
                    });
                    return true;
                } catch (Throwable unused) {
                    return false;
                }
            }
        }
    }
