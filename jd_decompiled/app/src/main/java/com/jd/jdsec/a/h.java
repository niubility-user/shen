package com.jd.jdsec.a;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes13.dex */
public class h {
    public static String a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes());
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                sb.append(String.format("%02X", new Integer(b & 255)));
            }
            return sb.toString().toLowerCase();
        } catch (Exception unused) {
            return "";
        }
    }
}
