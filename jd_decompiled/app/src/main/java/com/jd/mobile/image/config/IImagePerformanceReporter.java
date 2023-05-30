package com.jd.mobile.image.config;

import java.net.URL;
import java.util.HashMap;

/* loaded from: classes17.dex */
public interface IImagePerformanceReporter {
    String generateRequestIdentity(URL url, HashMap<String, String> hashMap);

    boolean isReportPerformaceData();

    void report(HashMap<String, String> hashMap);

    void reportException(HashMap<String, String> hashMap);
}
