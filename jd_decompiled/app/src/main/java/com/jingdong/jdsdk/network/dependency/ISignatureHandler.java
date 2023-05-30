package com.jingdong.jdsdk.network.dependency;

import android.content.Context;

/* loaded from: classes.dex */
public interface ISignatureHandler {
    byte[] decodeFromJni(byte[] bArr);

    void networkSettingsPreSignature();

    String signature(Context context, String str, String str2, String str3, String str4, String str5);
}
