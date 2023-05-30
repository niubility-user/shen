package com.jingdong.common.network.quicpro;

import java.nio.ByteBuffer;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface QPEventListener {
    void onClosed();

    void onFailure();

    void onRecvBody(ByteBuffer byteBuffer);

    void onRecvData(ByteBuffer byteBuffer);

    void onRecvHeader(int i2, int i3, HashMap<String, String> hashMap);

    void onSucceeded();
}
