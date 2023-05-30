package com.jingdong.jdsdk.network.dependency;

import java.net.URI;
import java.util.Map;

/* loaded from: classes.dex */
public interface IJDGuardPlugin {
    Map<String, String> genSign(URI uri, byte[] bArr, String str, String str2, boolean z);

    boolean isEnable();

    boolean isInWhiteList(String str);
}
