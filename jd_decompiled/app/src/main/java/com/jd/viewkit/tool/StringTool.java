package com.jd.viewkit.tool;

import android.text.TextPaint;
import com.jd.dynamic.DYConstants;
import java.security.MessageDigest;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes18.dex */
public class StringTool {
    private static Pattern patternIsInt = Pattern.compile("^[\\d]*$");
    private static Pattern patternIsDou = Pattern.compile("^[-\\+]?[.\\d]*$");

    public static final String encrypt(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static float getStringWidth(String str, float f2, boolean z) {
        if (isEmpty(str)) {
            return 0.0f;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f2);
        textPaint.setFakeBoldText(z);
        return textPaint.measureText(str);
    }

    public static boolean isBegin(String str, String str2) {
        return isNotEmpty(str) && isNotEmpty(str2) && str.length() >= str2.length() && str.substring(0, str2.length()).equals(str2);
    }

    public static boolean isBoolean(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.equals(DYConstants.DY_TRUE) || str.equals(DYConstants.DY_FALSE);
    }

    public static boolean isDouble(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return patternIsDou.matcher(str).matches();
    }

    public static boolean isEmpty(String str) {
        return str == null || !str.getClass().getName().equals("java.lang.String") || str.isEmpty();
    }

    public static boolean isInteger(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return patternIsInt.matcher(str).matches();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static void main(String[] strArr) {
        System.out.println(isBegin("hahah", "$"));
        System.out.println(isInteger("123"));
        System.out.println(isInteger("1a"));
    }
}
