package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.facebook.soloader.nativeloader.NativeLoader;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* loaded from: classes12.dex */
public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_DISABLE_BACKUP_SOSOURCE = 8;
    public static final int SOLOADER_DONT_TREAT_AS_SYSTEMAPP = 32;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    public static final int SOLOADER_SKIP_MERGED_JNI_ONLOAD = 16;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    private static final String SO_STORE_NAME_SPLIT = "lib-";
    static final boolean SYSTRACE_LIBRARY_LOADING;
    static final String TAG = "SoLoader";
    private static boolean isSystemApp;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static ApplicationSoSource sApplicationSoSource;
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static UnpackingSoSource[] sBackupSoSources;
    @GuardedBy("sSoSourcesLock")
    private static int sFlags;
    @Nullable
    static SoFileLoader sSoFileLoader;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    @GuardedBy("sSoSourcesLock")
    @Nullable
    private static SoSource[] sSoSources = null;
    @GuardedBy("sSoSourcesLock")
    private static volatile int sSoSourcesVersion = 0;
    @GuardedBy("SoLoader.class")
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();
    @GuardedBy("SoLoader.class")
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;

    @DoNotOptimize
    @TargetApi(14)
    /* loaded from: classes12.dex */
    public static class Api14Utils {
        private Api14Utils() {
        }

        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader != null && !(classLoader instanceof BaseDexClassLoader)) {
                throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
            }
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e2) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e2);
            }
        }
    }

    @NotThreadSafe
    /* loaded from: classes12.dex */
    static class TestOnlyUtils {
        TestOnlyUtils() {
        }

        static void resetStatus() {
            synchronized (SoLoader.class) {
                SoLoader.sLoadedLibraries.clear();
                SoLoader.sLoadingLibraries.clear();
                SoLoader.sSoFileLoader = null;
            }
            setSoSources(null);
        }

        static void setSoFileLoader(SoFileLoader soFileLoader) {
            SoLoader.sSoFileLoader = soFileLoader;
        }

        static void setSoSources(SoSource[] soSourceArr) {
            SoLoader.sSoSourcesLock.writeLock().lock();
            try {
                SoSource[] unused = SoLoader.sSoSources = soSourceArr;
                SoLoader.access$208();
            } finally {
                SoLoader.sSoSourcesLock.writeLock().unlock();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    static {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                z = true;
            }
        } catch (NoClassDefFoundError | UnsatisfiedLinkError unused) {
        }
        SYSTRACE_LIBRARY_LOADING = z;
    }

    static /* synthetic */ int access$208() {
        int i2 = sSoSourcesVersion;
        sSoSourcesVersion = i2 + 1;
        return i2;
    }

    public static boolean areSoSourcesAbisSupported() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                String[] supportedAbis = SysUtil.getSupportedAbis();
                for (SoSource soSource : sSoSources) {
                    for (String str : soSource.getSoSourceAbis()) {
                        boolean z = false;
                        for (int i2 = 0; i2 < supportedAbis.length && !z; i2++) {
                            z = str.equals(supportedAbis[i2]);
                        }
                        if (!z) {
                            String str2 = "abi not supported: " + str;
                            reentrantReadWriteLock = sSoSourcesLock;
                        }
                    }
                }
                sSoSourcesLock.readLock().unlock();
                return true;
            }
            reentrantReadWriteLock.readLock().unlock();
            return false;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new RuntimeException("SoLoader.init() not yet called");
        }
    }

    private static boolean checkIfSystemApp(Context context, int i2) {
        return ((i2 & 32) != 0 || context == null || (context.getApplicationInfo().flags & 129) == 0) ? false : true;
    }

    public static void deinitForTest() {
        TestOnlyUtils.setSoSources(null);
    }

    private static void doLoadLibraryBySoName(String str, int i2, @Nullable StrictMode.ThreadPolicy threadPolicy) throws UnsatisfiedLinkError {
        boolean z;
        int i3;
        ReentrantReadWriteLock reentrantReadWriteLock;
        ReentrantReadWriteLock reentrantReadWriteLock2 = sSoSourcesLock;
        reentrantReadWriteLock2.readLock().lock();
        try {
            if (sSoSources != null) {
                reentrantReadWriteLock2.readLock().unlock();
                int i4 = 0;
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    z = false;
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str, "]");
                }
                try {
                    reentrantReadWriteLock2.readLock().lock();
                    i3 = 0;
                    int i5 = 0;
                    while (i3 == 0) {
                        SoSource[] soSourceArr = sSoSources;
                        if (i5 < soSourceArr.length) {
                            i3 = soSourceArr[i5].loadLibrary(str, i2, threadPolicy);
                            if (i3 != 3 || sBackupSoSources == null) {
                                i5++;
                            } else {
                                String str2 = "Trying backup SoSource for " + str;
                                for (UnpackingSoSource unpackingSoSource : sBackupSoSources) {
                                    unpackingSoSource.prepare(str);
                                    int loadLibrary = unpackingSoSource.loadLibrary(str, i2, threadPolicy);
                                    if (loadLibrary == 1) {
                                        i3 = loadLibrary;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy);
                    }
                    if (i3 == 0 || i3 == 3) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("couldn't find DSO to load: ");
                        sb.append(str);
                        reentrantReadWriteLock.readLock().lock();
                        while (i4 < sSoSources.length) {
                            sb.append("\n\tSoSource ");
                            sb.append(i4);
                            sb.append(": ");
                            sb.append(sSoSources[i4].toString());
                            i4++;
                        }
                        ApplicationSoSource applicationSoSource = sApplicationSoSource;
                        if (applicationSoSource != null) {
                            File nativeLibDirFromContext = ApplicationSoSource.getNativeLibDirFromContext(applicationSoSource.getUpdatedContext());
                            sb.append("\n\tNative lib dir: ");
                            sb.append(nativeLibDirFromContext.getAbsolutePath());
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                        sSoSourcesLock.readLock().unlock();
                        sb.append(" result: ");
                        sb.append(i3);
                        throw new UnsatisfiedLinkError(sb.toString());
                    }
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    i4 = i3;
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy);
                    }
                    if (i4 == 0 || i4 == 3) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("couldn't find DSO to load: ");
                        sb2.append(str);
                        String message = th.getMessage();
                        if (message == null) {
                            message = th.toString();
                        }
                        sb2.append(" caused by: ");
                        sb2.append(message);
                        th.printStackTrace();
                        sb2.append(" result: ");
                        sb2.append(i4);
                        throw new UnsatisfiedLinkError(sb2.toString());
                    }
                    return;
                }
            }
            String str3 = "Could not load: " + str + " because no SO source exists";
            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String[] getLibraryDependencies(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String[] strArr = null;
            if (sSoSources != null) {
                int i2 = 0;
                while (strArr == null) {
                    SoSource[] soSourceArr = sSoSources;
                    if (i2 >= soSourceArr.length) {
                        break;
                    }
                    strArr = soSourceArr[i2].getLibraryDependencies(str);
                    i2++;
                }
            }
            return strArr;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String getLibraryPath(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String str2 = null;
            if (sSoSources != null) {
                int i2 = 0;
                while (str2 == null) {
                    SoSource[] soSourceArr = sSoSources;
                    if (i2 >= soSourceArr.length) {
                        break;
                    }
                    str2 = soSourceArr[i2].getLibraryPath(str);
                    i2++;
                }
            }
            return str2;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23 && i2 <= 27) {
            try {
                Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException | SecurityException unused) {
            }
        }
        return null;
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion;
    }

    public static void init(Context context, int i2) throws IOException {
        init(context, i2, null);
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
                return;
            }
            Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
            final boolean z = nativeLoadRuntimeMethod != null;
            final String classLoaderLdLoadLibrary = z ? Api14Utils.getClassLoaderLdLoadLibrary() : null;
            makeNonZipPath(classLoaderLdLoadLibrary);
            sSoFileLoader = new SoFileLoader
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: SPUT 
                  (wrap: com.facebook.soloader.SoFileLoader : 0x0028: CONSTRUCTOR 
                  (r2v1 'z' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r3v0 'classLoaderLdLoadLibrary' java.lang.String A[DONT_INLINE])
                  (r4 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r5 I:java.lang.Runtime A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r6v0 'nativeLoadRuntimeMethod' java.lang.reflect.Method A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[Catch: all -> 0x002f, MD:(boolean, java.lang.String, java.lang.String, java.lang.Runtime, java.lang.reflect.Method):void (m), WRAPPED] (LINE:7) call: com.facebook.soloader.SoLoader.1.<init>(boolean, java.lang.String, java.lang.String, java.lang.Runtime, java.lang.reflect.Method):void type: CONSTRUCTOR)
                 A[Catch: all -> 0x002f, TRY_LEAVE] (LINE:7) com.facebook.soloader.SoLoader.sSoFileLoader com.facebook.soloader.SoFileLoader in method: com.facebook.soloader.SoLoader.initSoLoader(com.facebook.soloader.SoFileLoader):void, file: classes12.dex
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
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:240)
                	at jadx.core.dex.regions.SynchronizedRegion.generate(SynchronizedRegion.java:44)
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
                java.lang.Class<com.facebook.soloader.SoLoader> r0 = com.facebook.soloader.SoLoader.class
                monitor-enter(r0)
                if (r7 == 0) goto L9
                com.facebook.soloader.SoLoader.sSoFileLoader = r7     // Catch: java.lang.Throwable -> L2f
                monitor-exit(r0)
                return
            L9:
                java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L2f
                java.lang.reflect.Method r6 = getNativeLoadRuntimeMethod()     // Catch: java.lang.Throwable -> L2f
                if (r6 == 0) goto L16
                r7 = 1
                r2 = 1
                goto L18
            L16:
                r7 = 0
                r2 = 0
            L18:
                if (r2 == 0) goto L1f
                java.lang.String r7 = com.facebook.soloader.SoLoader.Api14Utils.getClassLoaderLdLoadLibrary()     // Catch: java.lang.Throwable -> L2f
                goto L20
            L1f:
                r7 = 0
            L20:
                r3 = r7
                java.lang.String r4 = makeNonZipPath(r3)     // Catch: java.lang.Throwable -> L2f
                com.facebook.soloader.SoLoader$1 r7 = new com.facebook.soloader.SoLoader$1     // Catch: java.lang.Throwable -> L2f
                r1 = r7
                r1.<init>()     // Catch: java.lang.Throwable -> L2f
                com.facebook.soloader.SoLoader.sSoFileLoader = r7     // Catch: java.lang.Throwable -> L2f
                monitor-exit(r0)
                return
            L2f:
                r7 = move-exception
                monitor-exit(r0)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.initSoLoader(com.facebook.soloader.SoFileLoader):void");
        }

        private static void initSoSources(Context context, int i2, @Nullable SoFileLoader soFileLoader) throws IOException {
            int i3;
            sSoSourcesLock.writeLock().lock();
            try {
                if (sSoSources == null) {
                    sFlags = i2;
                    ArrayList arrayList = new ArrayList();
                    String str = System.getenv("LD_LIBRARY_PATH");
                    if (str == null) {
                        str = SysUtil.is64Bit() ? "/vendor/lib64:/system/lib64" : "/vendor/lib:/system/lib";
                    }
                    for (String str2 : str.split(":")) {
                        String str3 = "adding system library source: " + str2;
                        arrayList.add(new DirectorySoSource(new File(str2), 2));
                    }
                    if (context != null) {
                        if ((i2 & 1) != 0) {
                            sBackupSoSources = null;
                            arrayList.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                        } else {
                            if (isSystemApp) {
                                i3 = 0;
                            } else {
                                sApplicationSoSource = new ApplicationSoSource(context, Build.VERSION.SDK_INT <= 17 ? 1 : 0);
                                String str4 = "adding application source: " + sApplicationSoSource.toString();
                                arrayList.add(0, sApplicationSoSource);
                                i3 = 1;
                            }
                            if ((sFlags & 8) != 0) {
                                sBackupSoSources = null;
                            } else {
                                File file = new File(context.getApplicationInfo().sourceDir);
                                ArrayList arrayList2 = new ArrayList();
                                ApkSoSource apkSoSource = new ApkSoSource(context, file, SO_STORE_NAME_MAIN, i3);
                                arrayList2.add(apkSoSource);
                                String str5 = "adding backup source from : " + apkSoSource.toString();
                                if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
                                    String[] strArr = context.getApplicationInfo().splitSourceDirs;
                                    int length = strArr.length;
                                    int i4 = 0;
                                    int i5 = 0;
                                    while (i4 < length) {
                                        File file2 = new File(strArr[i4]);
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(SO_STORE_NAME_SPLIT);
                                        sb.append(i5);
                                        ApkSoSource apkSoSource2 = new ApkSoSource(context, file2, sb.toString(), i3);
                                        String str6 = "adding backup source: " + apkSoSource2.toString();
                                        arrayList2.add(apkSoSource2);
                                        i4++;
                                        i5++;
                                    }
                                }
                                sBackupSoSources = (UnpackingSoSource[]) arrayList2.toArray(new UnpackingSoSource[arrayList2.size()]);
                                arrayList.addAll(0, arrayList2);
                            }
                        }
                    }
                    SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                    int makePrepareFlags = makePrepareFlags();
                    int length2 = soSourceArr.length;
                    while (true) {
                        int i6 = length2 - 1;
                        if (length2 <= 0) {
                            break;
                        }
                        String str7 = "Preparing SO source: " + soSourceArr[i6];
                        soSourceArr[i6].prepare(makePrepareFlags);
                        length2 = i6;
                    }
                    sSoSources = soSourceArr;
                    sSoSourcesVersion++;
                    String str8 = "init finish: " + sSoSources.length + " SO sources prepared";
                }
            } finally {
                sSoSourcesLock.writeLock().unlock();
            }
        }

        public static boolean isInitialized() {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.readLock().lock();
            try {
                boolean z = sSoSources != null;
                reentrantReadWriteLock.readLock().unlock();
                return z;
            } catch (Throwable th) {
                sSoSourcesLock.readLock().unlock();
                throw th;
            }
        }

        public static boolean loadLibrary(String str) {
            return loadLibrary(str, 0);
        }

        public static void loadLibraryBySoName(String str, int i2, StrictMode.ThreadPolicy threadPolicy) {
            loadLibraryBySoNameImpl(str, null, null, i2, threadPolicy);
        }

        private static boolean loadLibraryBySoNameImpl(String str, @Nullable String str2, @Nullable String str3, int i2, @Nullable StrictMode.ThreadPolicy threadPolicy) {
            boolean z;
            Object obj;
            boolean z2 = false;
            if (TextUtils.isEmpty(str2) || !sLoadedAndMergedLibraries.contains(str2)) {
                synchronized (SoLoader.class) {
                    HashSet<String> hashSet = sLoadedLibraries;
                    if (!hashSet.contains(str)) {
                        z = false;
                    } else if (str3 == null) {
                        return false;
                    } else {
                        z = true;
                    }
                    Map<String, Object> map = sLoadingLibraries;
                    if (map.containsKey(str)) {
                        obj = map.get(str);
                    } else {
                        Object obj2 = new Object();
                        map.put(str, obj2);
                        obj = obj2;
                    }
                    synchronized (obj) {
                        if (!z) {
                            synchronized (SoLoader.class) {
                                if (hashSet.contains(str)) {
                                    if (str3 == null) {
                                        return false;
                                    }
                                    z = true;
                                }
                                if (!z) {
                                    try {
                                        String str4 = "About to load: " + str;
                                        doLoadLibraryBySoName(str, i2, threadPolicy);
                                        synchronized (SoLoader.class) {
                                            String str5 = "Loaded: " + str;
                                            hashSet.add(str);
                                        }
                                    } catch (UnsatisfiedLinkError e2) {
                                        String message = e2.getMessage();
                                        if (message != null && message.contains("unexpected e_machine:")) {
                                            throw new WrongAbiError(e2, message.substring(message.lastIndexOf("unexpected e_machine:")));
                                        }
                                        throw e2;
                                    }
                                }
                            }
                        }
                        if ((i2 & 16) == 0) {
                            if (!TextUtils.isEmpty(str2) && sLoadedAndMergedLibraries.contains(str2)) {
                                z2 = true;
                            }
                            if (str3 != null && !z2) {
                                boolean z3 = SYSTRACE_LIBRARY_LOADING;
                                if (z3) {
                                    Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", str2, "]");
                                }
                                try {
                                    String str6 = "About to merge: " + str2 + " / " + str;
                                    MergedSoMapping.invokeJniOnload(str2);
                                    sLoadedAndMergedLibraries.add(str2);
                                    if (z3) {
                                        Api18TraceUtils.endSection();
                                    }
                                } catch (UnsatisfiedLinkError e3) {
                                    throw new RuntimeException("Failed to call JNI_OnLoad from '" + str2 + "', which has been merged into '" + str + "'.  See comment for details.", e3);
                                }
                            }
                        }
                        return !z;
                    }
                }
            }
            return false;
        }

        public static String makeLdLibraryPath() {
            sSoSourcesLock.readLock().lock();
            try {
                assertInitialized();
                ArrayList arrayList = new ArrayList();
                SoSource[] soSourceArr = sSoSources;
                if (soSourceArr != null) {
                    for (SoSource soSource : soSourceArr) {
                        soSource.addToLdLibraryPath(arrayList);
                    }
                }
                String join = TextUtils.join(":", arrayList);
                String str = "makeLdLibraryPath final path: " + join;
                return join;
            } finally {
                sSoSourcesLock.readLock().unlock();
            }
        }

        @Nullable
        public static String makeNonZipPath(String str) {
            if (str == null) {
                return null;
            }
            String[] split = str.split(":");
            ArrayList arrayList = new ArrayList(split.length);
            for (String str2 : split) {
                if (!str2.contains("!")) {
                    arrayList.add(str2);
                }
            }
            return TextUtils.join(":", arrayList);
        }

        private static int makePrepareFlags() {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                int i2 = (sFlags & 2) != 0 ? 1 : 0;
                reentrantReadWriteLock.writeLock().unlock();
                return i2;
            } catch (Throwable th) {
                sSoSourcesLock.writeLock().unlock();
                throw th;
            }
        }

        public static void prependSoSource(SoSource soSource) throws IOException {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                String str = "Prepending to SO sources: " + soSource;
                assertInitialized();
                soSource.prepare(makePrepareFlags());
                SoSource[] soSourceArr = sSoSources;
                SoSource[] soSourceArr2 = new SoSource[soSourceArr.length + 1];
                soSourceArr2[0] = soSource;
                System.arraycopy(soSourceArr, 0, soSourceArr2, 1, soSourceArr.length);
                sSoSources = soSourceArr2;
                sSoSourcesVersion++;
                String str2 = "Prepended to SO sources: " + soSource;
                reentrantReadWriteLock.writeLock().unlock();
            } catch (Throwable th) {
                sSoSourcesLock.writeLock().unlock();
                throw th;
            }
        }

        public static void setInTestMode() {
            TestOnlyUtils.setSoSources(new SoSource[]{new NoopSoSource()});
        }

        public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper systemLoadLibraryWrapper) {
            sSystemLoadLibraryWrapper = systemLoadLibraryWrapper;
        }

        public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
            assertInitialized();
            try {
                return unpackLibraryBySoName(System.mapLibraryName(str));
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        static File unpackLibraryBySoName(String str) throws IOException {
            sSoSourcesLock.readLock().lock();
            try {
                for (SoSource soSource : sSoSources) {
                    File unpackLibrary = soSource.unpackLibrary(str);
                    if (unpackLibrary != null) {
                        return unpackLibrary;
                    }
                }
                sSoSourcesLock.readLock().unlock();
                throw new FileNotFoundException(str);
            } finally {
                sSoSourcesLock.readLock().unlock();
            }
        }

        public static void init(Context context, int i2, @Nullable SoFileLoader soFileLoader) throws IOException {
            StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            try {
                isSystemApp = checkIfSystemApp(context, i2);
                initSoLoader(soFileLoader);
                initSoSources(context, i2, soFileLoader);
                if (!NativeLoader.isInitialized()) {
                    NativeLoader.init(new NativeLoaderToSoLoaderDelegate());
                }
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
            }
        }

        public static boolean loadLibrary(String str, int i2) throws UnsatisfiedLinkError {
            SystemLoadLibraryWrapper systemLoadLibraryWrapper;
            boolean z;
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.readLock().lock();
            try {
                if (sSoSources == null) {
                    if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                        assertInitialized();
                    } else {
                        synchronized (SoLoader.class) {
                            z = !sLoadedLibraries.contains(str);
                            if (z) {
                                SystemLoadLibraryWrapper systemLoadLibraryWrapper2 = sSystemLoadLibraryWrapper;
                                if (systemLoadLibraryWrapper2 != null) {
                                    systemLoadLibraryWrapper2.loadLibrary(str);
                                } else {
                                    System.loadLibrary(str);
                                }
                            }
                        }
                        reentrantReadWriteLock.readLock().unlock();
                        return z;
                    }
                }
                reentrantReadWriteLock.readLock().unlock();
                if (isSystemApp && (systemLoadLibraryWrapper = sSystemLoadLibraryWrapper) != null) {
                    systemLoadLibraryWrapper.loadLibrary(str);
                    return true;
                }
                String mapLibName = MergedSoMapping.mapLibName(str);
                return loadLibraryBySoName(System.mapLibraryName(mapLibName != null ? mapLibName : str), str, mapLibName, i2, null);
            } catch (Throwable th) {
                sSoSourcesLock.readLock().unlock();
                throw th;
            }
        }

        private static boolean loadLibraryBySoName(String str, @Nullable String str2, @Nullable String str3, int i2, @Nullable StrictMode.ThreadPolicy threadPolicy) {
            boolean z;
            boolean z2 = false;
            do {
                try {
                    z2 = loadLibraryBySoNameImpl(str, str2, str3, i2, threadPolicy);
                    z = false;
                } catch (UnsatisfiedLinkError e2) {
                    int i3 = sSoSourcesVersion;
                    sSoSourcesLock.writeLock().lock();
                    try {
                        try {
                            z = true;
                            if (sApplicationSoSource == null || !sApplicationSoSource.checkAndMaybeUpdate()) {
                                z = false;
                            } else {
                                String str4 = "sApplicationSoSource updated during load: " + str + ", attempting load again.";
                                sSoSourcesVersion++;
                            }
                            sSoSourcesLock.writeLock().unlock();
                            if (sSoSourcesVersion == i3) {
                                throw e2;
                            }
                        } catch (IOException e3) {
                            throw new RuntimeException(e3);
                        }
                    } catch (Throwable th) {
                        sSoSourcesLock.writeLock().unlock();
                        throw th;
                    }
                }
            } while (z);
            return z2;
        }

        public static void init(Context context, boolean z) {
            try {
                init(context, z ? 1 : 0);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }
