package com.jingdong.common.phonecharge.game;

import com.jingdong.jdsdk.secure.Base64;

/* loaded from: classes5.dex */
public class DesUtil {
    private byte[] desKey;

    public DesUtil(String str) {
        this.desKey = str.getBytes();
    }

    public String decrypt(String str) throws Exception {
        Base64.decode(str.getBytes("GBK"));
        return "";
    }

    public String decryptUTF8(String str) throws Exception {
        Base64.decode(str.getBytes("UTF-8"));
        return "";
    }

    public byte[] desDecrypt(byte[] bArr) throws Exception {
        return null;
    }

    public byte[] desEncrypt(byte[] bArr) throws Exception {
        return null;
    }

    public String encrypt(String str) throws Exception {
        return "";
    }
}
