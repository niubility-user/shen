package com.jingdong.manto.jsengine;

import java.nio.ByteBuffer;

/* loaded from: classes15.dex */
public interface a extends IMantoBaseInterface {
    boolean canUseNativeBuffer();

    ByteBuffer getNativeBuffer(int i2);

    int getNativeBufferId();

    void setNativeBuffer(int i2, ByteBuffer byteBuffer);
}
