package com.jd.libs.hybrid.preload;

import androidx.annotation.Keep;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public class CustomParamProvider {
    static final String DEFINE_APP_VERSION = "appVer";
    static final String DEFINE_AREA = "area";
    static final String DEFINE_BUILD_NUM = "build";
    static final String DEFINE_DEVICE_ID = "deviceId";
    static final String DEFINE_LAT = "lat";
    static final String DEFINE_LNG = "lng";
    static final String DEFINE_OS_VERSION = "osVer";
    private static IParamGetter sParamGetter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public interface IParamGetter {
        String getAppVer();

        String getArea();

        String getBuildNumber();

        String getDeviceId();

        Map<String, String> getHeader();

        String getLat();

        String getLng();

        String getOsVer();
    }

    @Keep
    /* loaded from: classes16.dex */
    public static class ParamGetter implements IParamGetter {
        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getAppVer() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getArea() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getBuildNumber() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getDeviceId() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public Map<String, String> getHeader() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getLat() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getLng() {
            return null;
        }

        @Override // com.jd.libs.hybrid.preload.CustomParamProvider.IParamGetter
        public String getOsVer() {
            return null;
        }
    }

    private CustomParamProvider() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> getPreloadHeader() {
        IParamGetter iParamGetter = sParamGetter;
        if (iParamGetter == null) {
            return null;
        }
        return iParamGetter.getHeader();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getValueByDefine(String str) {
        if (sParamGetter == null) {
            return null;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1411082814:
                if (str.equals(DEFINE_APP_VERSION)) {
                    c2 = 0;
                    break;
                }
                break;
            case 106911:
                if (str.equals("lat")) {
                    c2 = 1;
                    break;
                }
                break;
            case 107301:
                if (str.equals("lng")) {
                    c2 = 2;
                    break;
                }
                break;
            case 3002509:
                if (str.equals("area")) {
                    c2 = 3;
                    break;
                }
                break;
            case 94094958:
                if (str.equals("build")) {
                    c2 = 4;
                    break;
                }
                break;
            case 106022687:
                if (str.equals(DEFINE_OS_VERSION)) {
                    c2 = 5;
                    break;
                }
                break;
            case 1109191185:
                if (str.equals("deviceId")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return sParamGetter.getAppVer();
            case 1:
                return sParamGetter.getLat();
            case 2:
                return sParamGetter.getLng();
            case 3:
                return sParamGetter.getArea();
            case 4:
                return sParamGetter.getBuildNumber();
            case 5:
                return sParamGetter.getOsVer();
            case 6:
                return sParamGetter.getDeviceId();
            default:
                return null;
        }
    }

    public static void setParamGetter(IParamGetter iParamGetter) {
        sParamGetter = iParamGetter;
    }
}
