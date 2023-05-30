package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.net.Uri;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import com.jingdong.JdImageToolKit;
import com.jingdong.common.XView2.common.XView2Constants;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
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

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0050, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0061, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0070, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x007f, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x008e, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0090, code lost:
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void initialize(Context context, @Nullable ImagePipelineConfig imagePipelineConfig, @Nullable DraweeConfig draweeConfig, boolean z) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("Fresco#initialize");
        }
        if (sIsInitialized) {
            FLog.w(TAG, "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
        } else {
            sIsInitialized = true;
        }
        NativeCodeSetup.setUseNativeCode(z);
        synchronized (Fresco.class) {
            if (!NativeLoader.isInitialized()) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("Fresco.initialize->SoLoader.init");
                }
                try {
                    try {
                        try {
                            Class.forName("com.facebook.imagepipeline.nativecode.NativeCodeInitializer").getMethod(XView2Constants.XVIEW2_ACTION_INIT, Context.class).invoke(null, context);
                        } catch (IllegalAccessException unused) {
                            NativeLoader.init(new SystemDelegate());
                        } catch (InvocationTargetException unused2) {
                            NativeLoader.init(new SystemDelegate());
                        }
                    } catch (NoSuchMethodException unused3) {
                        NativeLoader.init(new SystemDelegate());
                    }
                } catch (ClassNotFoundException unused4) {
                    NativeLoader.init(new SystemDelegate());
                }
            }
        }
        Context applicationContext = context.getApplicationContext();
        mContext = applicationContext;
        if (imagePipelineConfig == null) {
            ImagePipelineFactory.initialize(applicationContext);
        } else {
            ImagePipelineFactory.initialize(imagePipelineConfig);
        }
        initializeDrawee(applicationContext, draweeConfig);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
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
