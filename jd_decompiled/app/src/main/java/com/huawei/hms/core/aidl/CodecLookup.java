package com.huawei.hms.core.aidl;

/* loaded from: classes12.dex */
public final class CodecLookup {
    private CodecLookup() {
    }

    public static MessageCodec find(int i2) {
        if (i2 == 2) {
            return new MessageCodecV2();
        }
        return new MessageCodec();
    }
}
