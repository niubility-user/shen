package com.jingdong.jdsdk.network.dependency;

import java.util.Map;

/* loaded from: classes.dex */
public interface IGatewayRespHeaderListener {
    void onRespHeaderReceived(String str, Map<String, String> map);
}
