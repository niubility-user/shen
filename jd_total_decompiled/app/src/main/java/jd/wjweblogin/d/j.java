package jd.wjweblogin.d;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.security.MessageDigest;
import java.security.SecureRandom;
import jd.wjweblogin.model.WJErrorResult;
import jd.wjweblogin.model.WJFailResult;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes11.dex */
public class j {
    private static final String a = "WJWebLogin.Util";
    protected static char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: c  reason: collision with root package name */
    private static String f20067c = "@w#a$q&ejuak";

    private static String a(byte[] bArr, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(i3 * 2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            char[] cArr = b;
            char c2 = cArr[(bArr[i2] & 240) >> 4];
            char c3 = cArr[bArr[i2] & 15];
            stringBuffer.append(c2);
            stringBuffer.append(c3);
            i2++;
        }
        return stringBuffer.toString();
    }

    public static String b(String str) {
        try {
            return c(str).substring(8, 24);
        } catch (Exception e2) {
            e2.printStackTrace();
            return a();
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b2 : MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes())) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            SecureRandom secureRandom = new SecureRandom();
            for (int i2 = 0; i2 < 9; i2++) {
                sb.append((char) ((secureRandom.nextInt(2) % 2 == 0 ? 65 : 97) + secureRandom.nextInt(26)));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "hykebyIld";
        }
    }

    public static WJErrorResult a(int i2, String str, Exception exc) {
        if (299 == i2) {
            return new WJErrorResult(i2, str, exc);
        }
        if (exc != null) {
            g.a(a, "exception =" + exc);
        }
        return new WJErrorResult(i2, str, exc);
    }

    public static WJFailResult a(int i2, int i3, String str) {
        WJFailResult wJFailResult = new WJFailResult();
        wJFailResult.setHttpCode(i2);
        wJFailResult.setErrorCode(i3);
        wJFailResult.setErrorMessage(str);
        return wJFailResult;
    }

    public static String a() {
        return f20067c;
    }

    public static String a(String str) {
        g.b(a, "get0neDomain strat host=" + str);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("\\.");
        if (split != null && split.length >= 2) {
            str = split[split.length - 2] + OrderISVUtil.MONEY_DECIMAL + split[split.length - 1];
        }
        g.b(a, "get0neDomain host=" + str);
        return str;
    }
}
