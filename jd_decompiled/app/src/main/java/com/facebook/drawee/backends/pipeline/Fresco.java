package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.net.Uri;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.jingdong.JdImageToolKit;
import java.io.File;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class Fresco {
    private static final Class<?> TAG = Fresco.class;
    private static Context mContext;
    private static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier;
    private static volatile boolean sIsInitialized;

    /* loaded from: classes.dex */
    public static class JDImageNetworkException extends Throwable {
        public String dnsIp;
        public boolean isDomainRequest;
        public String requestUrl;

        public JDImageNetworkException() {
        }

        public JDImageNetworkException(Throwable th, boolean z, String str, String str2) {
            super(th);
            this.isDomainRequest = z;
            this.requestUrl = str;
            this.dnsIp = str2;
        }
    }

    private Fresco() {
    }

    @Deprecated
    public static void clearDiskCache() {
        getImagePipelineFactory().getMainFileCache().clearAll();
    }

    @Deprecated
    public static void clearMemoryCache() {
        getImagePipeline().clearMemoryCaches();
    }

    @Deprecated
    public static void destory() {
        clearMemoryCache();
    }

    @Deprecated
    public static Context getContext() {
        return mContext;
    }

    @Deprecated
    public static File getDiskCacheDir() {
        return JdImageToolKit.getMainDiskCacheRootDir();
    }

    public static PipelineDraweeControllerBuilderSupplier getDraweeControllerBuilderSupplier() {
        return sDraweeControllerBuilderSupplier;
    }

    public static ImagePipeline getImagePipeline() {
        return getImagePipelineFactory().getImagePipeline();
    }

    public static ImagePipelineFactory getImagePipelineFactory() {
        return ImagePipelineFactory.getInstance();
    }

    public static boolean hasBeenInitialized() {
        return sIsInitialized;
    }

    public static void initialize(Context context) {
        initialize(context, (ImagePipelineConfig) null, (DraweeConfig) null);
    }

    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig) {
        initialize(context, imagePipelineConfig, (DraweeConfig) null);
    }

    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig, @Nullable DraweeConfig draweeConfig) {
        initialize(context, imagePipelineConfig, draweeConfig, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0090, code lost:
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0050, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0061, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0070, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x007f, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x008e, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L105;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void initialize(android.content.Context r6, @javax.annotation.Nullable com.facebook.imagepipeline.core.ImagePipelineConfig r7, @javax.annotation.Nullable com.facebook.drawee.backends.pipeline.DraweeConfig r8, boolean r9) {
        /*
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto Lb
            java.lang.String r0 = "Fresco#initialize"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        Lb:
            boolean r0 = com.facebook.drawee.backends.pipeline.Fresco.sIsInitialized
            r1 = 1
            if (r0 == 0) goto L18
            java.lang.Class<?> r0 = com.facebook.drawee.backends.pipeline.Fresco.TAG
            java.lang.String r2 = "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!"
            com.facebook.common.logging.FLog.w(r0, r2)
            goto L1a
        L18:
            com.facebook.drawee.backends.pipeline.Fresco.sIsInitialized = r1
        L1a:
            com.facebook.imagepipeline.core.NativeCodeSetup.setUseNativeCode(r9)
            java.lang.Class<com.facebook.drawee.backends.pipeline.Fresco> r9 = com.facebook.drawee.backends.pipeline.Fresco.class
            monitor-enter(r9)
            boolean r0 = com.facebook.soloader.nativeloader.NativeLoader.isInitialized()     // Catch: java.lang.Throwable -> Lbb
            if (r0 != 0) goto L9e
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L31
            java.lang.String r0 = "Fresco.initialize->SoLoader.init"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)     // Catch: java.lang.Throwable -> Lbb
        L31:
            java.lang.String r0 = "com.facebook.imagepipeline.nativecode.NativeCodeInitializer"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            java.lang.String r2 = "init"
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            r2 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            r1[r5] = r6     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            r0.invoke(r2, r1)     // Catch: java.lang.Throwable -> L53 java.lang.NoSuchMethodException -> L55 java.lang.reflect.InvocationTargetException -> L64 java.lang.IllegalAccessException -> L73 java.lang.ClassNotFoundException -> L82
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L9e
            goto L90
        L53:
            r6 = move-exception
            goto L94
        L55:
            com.facebook.soloader.nativeloader.SystemDelegate r0 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            com.facebook.soloader.nativeloader.NativeLoader.init(r0)     // Catch: java.lang.Throwable -> L53
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L9e
            goto L90
        L64:
            com.facebook.soloader.nativeloader.SystemDelegate r0 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            com.facebook.soloader.nativeloader.NativeLoader.init(r0)     // Catch: java.lang.Throwable -> L53
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L9e
            goto L90
        L73:
            com.facebook.soloader.nativeloader.SystemDelegate r0 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            com.facebook.soloader.nativeloader.NativeLoader.init(r0)     // Catch: java.lang.Throwable -> L53
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L9e
            goto L90
        L82:
            com.facebook.soloader.nativeloader.SystemDelegate r0 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            com.facebook.soloader.nativeloader.NativeLoader.init(r0)     // Catch: java.lang.Throwable -> L53
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r0 == 0) goto L9e
        L90:
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()     // Catch: java.lang.Throwable -> Lbb
            goto L9e
        L94:
            boolean r7 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch: java.lang.Throwable -> Lbb
            if (r7 == 0) goto L9d
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()     // Catch: java.lang.Throwable -> Lbb
        L9d:
            throw r6     // Catch: java.lang.Throwable -> Lbb
        L9e:
            monitor-exit(r9)     // Catch: java.lang.Throwable -> Lbb
            android.content.Context r6 = r6.getApplicationContext()
            com.facebook.drawee.backends.pipeline.Fresco.mContext = r6
            if (r7 != 0) goto Lab
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize(r6)
            goto Lae
        Lab:
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize(r7)
        Lae:
            initializeDrawee(r6, r8)
            boolean r6 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r6 == 0) goto Lba
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        Lba:
            return
        Lbb:
            r6 = move-exception
            monitor-exit(r9)     // Catch: java.lang.Throwable -> Lbb
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.Fresco.initialize(android.content.Context, com.facebook.imagepipeline.core.ImagePipelineConfig, com.facebook.drawee.backends.pipeline.DraweeConfig, boolean):void");
    }

    @Deprecated
    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig, boolean z) {
        if (hasBeenInitialized()) {
            return;
        }
        initialize(context, imagePipelineConfig);
    }

    @Deprecated
    public static void initialize(Context context, boolean z) {
        if (hasBeenInitialized()) {
            return;
        }
        initialize(context);
    }

    private static void initializeDrawee(Context context, @Nullable DraweeConfig draweeConfig) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("Fresco.initializeDrawee");
        }
        PipelineDraweeControllerBuilderSupplier pipelineDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(context, draweeConfig);
        sDraweeControllerBuilderSupplier = pipelineDraweeControllerBuilderSupplier;
        SimpleDraweeView.initialize(pipelineDraweeControllerBuilderSupplier);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public static PipelineDraweeControllerBuilder newDraweeControllerBuilder() {
        return sDraweeControllerBuilderSupplier.get();
    }

    @Deprecated
    public static void removeDiskCache(Uri uri) {
        getImagePipelineFactory().getMainFileCache().remove(new SimpleCacheKey(uri.toString()));
    }

    @Deprecated
    public static void removeMemoryCache(Uri uri) {
        getImagePipeline().evictFromMemoryCache(uri);
    }

    public static void shutDown() {
        sDraweeControllerBuilderSupplier = null;
        SimpleDraweeView.shutDown();
        ImagePipelineFactory.shutDown();
    }
}
