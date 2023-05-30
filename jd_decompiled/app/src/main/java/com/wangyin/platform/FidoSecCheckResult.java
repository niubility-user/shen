package com.wangyin.platform;

/* loaded from: classes11.dex */
public class FidoSecCheckResult {
    public final byte[] extraData;
    public final byte[] signedData;

    public FidoSecCheckResult(byte[] bArr, byte[] bArr2) {
        this.extraData = bArr;
        this.signedData = bArr2;
    }
}
