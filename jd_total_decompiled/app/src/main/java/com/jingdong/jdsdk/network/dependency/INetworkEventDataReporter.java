package com.jingdong.jdsdk.network.dependency;

import java.net.URL;
import java.util.HashMap;

/* loaded from: classes14.dex */
public interface INetworkEventDataReporter {
    boolean enable();

    String generateRequestIdentity(URL url, HashMap<String, String> hashMap);

    void report(HashMap<String, String> hashMap);

    void reportException(HashMap<String, String> hashMap);
}
