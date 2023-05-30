package com.jd.mobile.image.config;

import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.common.httpdns.IpModel;
import java.net.URL;
import java.util.HashMap;

/* loaded from: classes17.dex */
public class DefaultParameterFactory {
    private static DefaultParameterFactory instance;
    private IAVIFWhitePageEnable avifWhitePageEnable;
    private IGIFWhitePageEnable gifWhitePageEnable;
    private IHttpDnsDependency httpDnsDependency;
    private INetworkParameter iNetworkParameter;
    private IImageController imageController;
    private IExceptionReportHandler reportHandler;

    public static synchronized DefaultParameterFactory getInstance() {
        DefaultParameterFactory defaultParameterFactory;
        synchronized (DefaultParameterFactory.class) {
            if (instance == null) {
                instance = new DefaultParameterFactory();
            }
            defaultParameterFactory = instance;
        }
        return defaultParameterFactory;
    }

    public IAVIFWhitePageEnable getAvifWhitePageEnable() {
        if (this.avifWhitePageEnable == null) {
            this.avifWhitePageEnable = new IAVIFWhitePageEnable() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.6
                @Override // com.jd.mobile.image.config.IAVIFWhitePageEnable
                public boolean isAVIFWhitePageEnable() {
                    return false;
                }
            };
        }
        return this.avifWhitePageEnable;
    }

    public ICDNDomainResolver getCDNDomainResolver() {
        return new ICDNDomainResolver() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.8
            @Override // com.jd.mobile.image.config.ICDNDomainResolver
            public String getConfig() {
                return "";
            }
        };
    }

    public IHttpDnsDependency getDefaultHttpDnsDenpendency() {
        if (this.httpDnsDependency == null) {
            this.httpDnsDependency = new IHttpDnsDependency() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.3
                @Override // com.jd.mobile.image.config.IHttpDnsDependency
                public IpModel getIpModelByHost(String str, boolean z) {
                    return null;
                }
            };
        }
        return this.httpDnsDependency;
    }

    public IGIFWhitePageEnable getGifWhitePageEnable() {
        if (this.gifWhitePageEnable == null) {
            this.gifWhitePageEnable = new IGIFWhitePageEnable() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.7
                @Override // com.jd.mobile.image.config.IGIFWhitePageEnable
                public boolean isGIFWhitePageEnable() {
                    return false;
                }
            };
        }
        return this.gifWhitePageEnable;
    }

    public IImageController getImageControllerImpl() {
        if (this.imageController == null) {
            this.imageController = new IImageController() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.1
                @Override // com.jd.mobile.image.config.IImageController
                public String getThisPageInfo() {
                    return "";
                }

                @Override // com.jd.mobile.image.config.IImageController
                public boolean needNoImage() {
                    return false;
                }
            };
        }
        return this.imageController;
    }

    public INetworkParameter getNetworkParamSupplier() {
        if (this.iNetworkParameter == null) {
            this.iNetworkParameter = new INetworkParameter() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.2
                @Override // com.jd.mobile.image.config.INetworkParameter
                public String getCustomUseAgent() {
                    return null;
                }

                @Override // com.jd.mobile.image.config.INetworkParameter
                public boolean isForce2HttpFlag() {
                    return false;
                }

                @Override // com.jd.mobile.image.config.INetworkParameter
                public boolean isUseDomainFlag() {
                    return false;
                }

                @Override // com.jd.mobile.image.config.INetworkParameter
                public boolean isUseHttps() {
                    return true;
                }

                @Override // com.jd.mobile.image.config.INetworkParameter
                public boolean isUseOKHttp() {
                    return false;
                }

                @Override // com.jd.mobile.image.config.INetworkParameter
                public boolean needReferer() {
                    return false;
                }
            };
        }
        return this.iNetworkParameter;
    }

    public IImagePerformanceReporter getPerformanceReporter() {
        return new IImagePerformanceReporter() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.4
            @Override // com.jd.mobile.image.config.IImagePerformanceReporter
            public String generateRequestIdentity(URL url, HashMap<String, String> hashMap) {
                return "";
            }

            @Override // com.jd.mobile.image.config.IImagePerformanceReporter
            public boolean isReportPerformaceData() {
                return false;
            }

            @Override // com.jd.mobile.image.config.IImagePerformanceReporter
            public void report(HashMap<String, String> hashMap) {
            }

            @Override // com.jd.mobile.image.config.IImagePerformanceReporter
            public void reportException(HashMap<String, String> hashMap) {
            }
        };
    }

    public IExceptionReportHandler getReportHandlerImpl() {
        if (this.reportHandler == null) {
            this.reportHandler = new IExceptionReportHandler() { // from class: com.jd.mobile.image.config.DefaultParameterFactory.5
                @Override // com.jd.mobile.image.config.IExceptionReportHandler
                public void reportBitmapException(String str, JDFailReason jDFailReason, String str2, int i2) {
                }

                @Override // com.jd.mobile.image.config.IExceptionReportHandler
                public void reportCDNDowngradeData(String str, String str2, String str3, boolean z, int i2, String str4) {
                }

                @Override // com.jd.mobile.image.config.IExceptionReportHandler
                public void reportClearTextRequest(String str) {
                }

                @Override // com.jd.mobile.image.config.IExceptionReportHandler
                public void reportDpgPicMta(String str) {
                }
            };
        }
        return this.reportHandler;
    }
}
