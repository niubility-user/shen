package com.jingdong;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.jd.mobile.image.config.DefaultParameterFactory;
import com.jd.mobile.image.config.IAVIFWhitePageEnable;
import com.jd.mobile.image.config.ICDNDomainResolver;
import com.jd.mobile.image.config.IExceptionReportHandler;
import com.jd.mobile.image.config.IGIFWhitePageEnable;
import com.jd.mobile.image.config.IHttpDnsDependency;
import com.jd.mobile.image.config.IImageController;
import com.jd.mobile.image.config.IImagePerformanceReporter;
import com.jd.mobile.image.config.INetStatReporter;
import com.jd.mobile.image.config.INetworkParameter;
import com.jd.mobile.image.config.IOtherDependency;
import com.jingdong.common.network.CDNRouteSelector;
import com.jingdong.common.network.ConnectivityChangeObserver;
import java.io.File;

/* loaded from: classes18.dex */
public class JdImageToolKit {
    public static final String TAG = "JdImageToolKit";
    @SuppressLint({"StaticFieldLeak"})
    private static ImageConfigEngine sConfigEngine;

    /* loaded from: classes18.dex */
    public static class ImageConfigEngine {
        private IAVIFWhitePageEnable avifWhitePageEnable;
        private final Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier;
        private ICDNDomainResolver cdnDomainsResolver;
        private CDNRouteSelector cdnRouteSelector;
        private ConnectivityChangeObserver connectivityChangeObserver;
        private final Context context;
        private IGIFWhitePageEnable gifWhitePageEnable;
        private IHttpDnsDependency httpDnsDependency;
        private IImageController imageControllerImpl;
        private final int loggingLevel;
        private DiskCacheConfig mainDiskCacheConfig;
        private final INetStatReporter netStatReporter;
        private INetworkParameter networkParamSupplier;
        private final IOtherDependency otherDependencyImpl;
        private IImagePerformanceReporter performanceReporter;
        private IExceptionReportHandler reportHandlerImpl;

        /* loaded from: classes18.dex */
        public static class Builder {
            private IAVIFWhitePageEnable avifWhitePageEnable;
            private Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier;
            private ICDNDomainResolver cdnDomainsResolver;
            private final Context context;
            private IGIFWhitePageEnable gifWhitePageEnable;
            private IHttpDnsDependency httpDnsDependency;
            private IImageController imageControllerImpl;
            private int loggingLevel;
            private DiskCacheConfig mainDiskCacheConfig;
            private INetStatReporter netStatReporter;
            private INetworkParameter networkParamSupplier;
            private IOtherDependency otherDependencyImpl;
            private IImagePerformanceReporter performanceReporter;
            private IExceptionReportHandler reportHandlerImpl;

            private Builder(Context context) {
                this.loggingLevel = 5;
                this.context = context;
            }

            public static Builder newBuilder(Context context) {
                return new Builder(context);
            }

            public ImageConfigEngine build() {
                return new ImageConfigEngine(this);
            }

            public Builder setAvifWhitePageEnable(IAVIFWhitePageEnable iAVIFWhitePageEnable) {
                this.avifWhitePageEnable = iAVIFWhitePageEnable;
                return this;
            }

            public Builder setBitmapMemoryCacheParamSupplier(Supplier<MemoryCacheParams> supplier) {
                this.bitmapMemoryCacheParamsSupplier = supplier;
                return this;
            }

            public Builder setCDNDomainResolver(ICDNDomainResolver iCDNDomainResolver) {
                this.cdnDomainsResolver = iCDNDomainResolver;
                return this;
            }

            public Builder setGifWhitePageEnable(IGIFWhitePageEnable iGIFWhitePageEnable) {
                this.gifWhitePageEnable = iGIFWhitePageEnable;
                return this;
            }

            public Builder setHttpDnsDependency(IHttpDnsDependency iHttpDnsDependency) {
                this.httpDnsDependency = iHttpDnsDependency;
                return this;
            }

            public Builder setImageControllerImpl(IImageController iImageController) {
                this.imageControllerImpl = iImageController;
                return this;
            }

            public Builder setLoggingLevel(int i2) {
                this.loggingLevel = i2;
                return this;
            }

            public Builder setMainDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
                this.mainDiskCacheConfig = diskCacheConfig;
                return this;
            }

            public Builder setNetStatReporter(INetStatReporter iNetStatReporter) {
                this.netStatReporter = iNetStatReporter;
                return this;
            }

            public Builder setNetworkParameterImpl(INetworkParameter iNetworkParameter) {
                this.networkParamSupplier = iNetworkParameter;
                return this;
            }

            public Builder setOtherDependencyImpl(IOtherDependency iOtherDependency) {
                this.otherDependencyImpl = iOtherDependency;
                return this;
            }

            public Builder setPerformanceReporter(IImagePerformanceReporter iImagePerformanceReporter) {
                this.performanceReporter = iImagePerformanceReporter;
                return this;
            }

            public Builder setReportHandlerImpl(IExceptionReportHandler iExceptionReportHandler) {
                this.reportHandlerImpl = iExceptionReportHandler;
                return this;
            }
        }

        private ImageConfigEngine(Builder builder) {
            this.context = builder.context;
            this.imageControllerImpl = builder.imageControllerImpl;
            this.networkParamSupplier = builder.networkParamSupplier;
            this.reportHandlerImpl = builder.reportHandlerImpl;
            this.otherDependencyImpl = builder.otherDependencyImpl;
            this.netStatReporter = builder.netStatReporter;
            this.httpDnsDependency = builder.httpDnsDependency;
            this.performanceReporter = builder.performanceReporter;
            this.bitmapMemoryCacheParamsSupplier = builder.bitmapMemoryCacheParamsSupplier;
            this.mainDiskCacheConfig = builder.mainDiskCacheConfig;
            this.loggingLevel = builder.loggingLevel;
            this.cdnDomainsResolver = builder.cdnDomainsResolver;
            this.avifWhitePageEnable = builder.avifWhitePageEnable;
            this.gifWhitePageEnable = builder.gifWhitePageEnable;
        }

        public Context getApplicationContext() {
            return this.context;
        }

        public IAVIFWhitePageEnable getAvifWhitePageEnable() {
            if (this.avifWhitePageEnable == null) {
                this.avifWhitePageEnable = DefaultParameterFactory.getInstance().getAvifWhitePageEnable();
            }
            return this.avifWhitePageEnable;
        }

        public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
            return this.bitmapMemoryCacheParamsSupplier;
        }

        public ICDNDomainResolver getCDNDomainsResolver() {
            if (this.cdnDomainsResolver == null) {
                this.cdnDomainsResolver = DefaultParameterFactory.getInstance().getCDNDomainResolver();
            }
            return this.cdnDomainsResolver;
        }

        public CDNRouteSelector getCdnRouteSelector() {
            return this.cdnRouteSelector;
        }

        public ConnectivityChangeObserver getConnectivityChangeObserver() {
            return this.connectivityChangeObserver;
        }

        public IExceptionReportHandler getExceptionReportHandlerImpl() {
            if (this.reportHandlerImpl == null) {
                this.reportHandlerImpl = DefaultParameterFactory.getInstance().getReportHandlerImpl();
            }
            return this.reportHandlerImpl;
        }

        public IGIFWhitePageEnable getGIFWhitePageEnable() {
            if (this.gifWhitePageEnable == null) {
                this.gifWhitePageEnable = DefaultParameterFactory.getInstance().getGifWhitePageEnable();
            }
            return this.gifWhitePageEnable;
        }

        public IHttpDnsDependency getHttpDnsDependency() {
            if (this.httpDnsDependency == null) {
                this.httpDnsDependency = DefaultParameterFactory.getInstance().getDefaultHttpDnsDenpendency();
            }
            return this.httpDnsDependency;
        }

        public IImageController getImageControllerImpl() {
            if (this.imageControllerImpl == null) {
                this.imageControllerImpl = DefaultParameterFactory.getInstance().getImageControllerImpl();
            }
            return this.imageControllerImpl;
        }

        public int getLoggingLevel() {
            return this.loggingLevel;
        }

        public DiskCacheConfig getMainDiskCacheConfig() {
            if (this.mainDiskCacheConfig == null) {
                this.mainDiskCacheConfig = DiskCacheConfig.newBuilder(this.context).build();
            }
            return this.mainDiskCacheConfig;
        }

        public INetStatReporter getNetStatReporter() {
            return this.netStatReporter;
        }

        public INetworkParameter getNetworkParamSupplier() {
            if (this.networkParamSupplier == null) {
                this.networkParamSupplier = DefaultParameterFactory.getInstance().getNetworkParamSupplier();
            }
            return this.networkParamSupplier;
        }

        public IOtherDependency getOtherDependencyImpl() {
            return this.otherDependencyImpl;
        }

        public IImagePerformanceReporter getPerformanceReporter() {
            if (this.performanceReporter == null) {
                this.performanceReporter = DefaultParameterFactory.getInstance().getPerformanceReporter();
            }
            return this.performanceReporter;
        }

        public void init() {
            this.cdnRouteSelector = new CDNRouteSelector();
            ConnectivityChangeObserver connectivityChangeObserver = new ConnectivityChangeObserver(this.context);
            this.connectivityChangeObserver = connectivityChangeObserver;
            connectivityChangeObserver.addEventListener(this.cdnRouteSelector);
        }
    }

    public static Context getContext() {
        return sConfigEngine.getApplicationContext();
    }

    public static ImageConfigEngine getEngine() {
        return sConfigEngine;
    }

    public static File getMainDiskCacheRootDir() {
        DiskCacheConfig mainDiskCacheConfig = sConfigEngine.getMainDiskCacheConfig();
        return new File(mainDiskCacheConfig.getBaseDirectoryPathSupplier().get(), mainDiskCacheConfig.getBaseDirectoryName());
    }

    public static void initialize(Context context) {
        initialize(newBuilder(context).build());
    }

    public static void initialize(ImageConfigEngine imageConfigEngine) {
        sConfigEngine = imageConfigEngine;
        imageConfigEngine.init();
        com.jd.mobile.image.a.c.a.a(imageConfigEngine);
    }

    public static ImageConfigEngine.Builder newBuilder(Context context) {
        return new ImageConfigEngine.Builder(context);
    }
}
