package jd.wjlogin_sdk.util;

import android.content.Context;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class j {
    private static Random d = null;

    /* renamed from: e  reason: collision with root package name */
    private static SecureRandom f19973e = null;

    /* renamed from: f  reason: collision with root package name */
    private static final int f19974f = 16;

    /* renamed from: g  reason: collision with root package name */
    private static final int f19975g = 255;
    private String a = "";
    private String b = "";

    /* renamed from: c  reason: collision with root package name */
    private Context f19976c;

    static {
        SecureRandom secureRandom = new SecureRandom();
        f19973e = secureRandom;
        d = new Random(secureRandom.nextLong());
    }

    public j(Context context) {
        this.f19976c = context;
        a(false);
    }

    public void a(boolean z) {
        MessageDigest messageDigest;
        StringBuffer stringBuffer = new StringBuffer(128);
        try {
            messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            messageDigest = null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long nextLong = z ? f19973e.nextLong() : d.nextLong();
            stringBuffer.append(new h(this.f19976c).a().toString());
            stringBuffer.append(":");
            stringBuffer.append(Long.toString(currentTimeMillis));
            stringBuffer.append(":");
            stringBuffer.append(Long.toString(nextLong));
            String stringBuffer2 = stringBuffer.toString();
            this.a = stringBuffer2;
            messageDigest.update(stringBuffer2.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer3 = new StringBuffer(32);
            for (byte b : digest) {
                int i2 = b & 255;
                if (i2 < 16) {
                    stringBuffer3.append('0');
                }
                stringBuffer3.append(Integer.toHexString(i2));
            }
            this.b = stringBuffer3.toString();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public String toString() {
        if (TextUtils.isEmpty(this.b)) {
            return "";
        }
        String upperCase = this.b.toUpperCase();
        StringBuffer stringBuffer = new StringBuffer(64);
        stringBuffer.append(upperCase.substring(0, 8));
        stringBuffer.append(upperCase.substring(8, 12));
        stringBuffer.append(upperCase.substring(12, 16));
        stringBuffer.append(upperCase.substring(16, 20));
        stringBuffer.append(upperCase.substring(20));
        return stringBuffer.toString();
    }

    public j(boolean z, Context context) {
        this.f19976c = context;
        a(z);
    }
}
