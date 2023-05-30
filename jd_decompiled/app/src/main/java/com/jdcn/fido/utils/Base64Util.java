package com.jdcn.fido.utils;

import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes18.dex */
public class Base64Util {
    private static final String TAG = "Base64Util";

    public static void decodeBase64File(String str, String str2) {
        byte[] decode = Base64.decode(str, 2);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        fileOutputStream.write(decode);
        fileOutputStream.close();
    }

    public static String decodeBase64String(String str) {
        try {
            return new String(Base64.decode(str.getBytes(), 2), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String encodeBase64File(String str) {
        File file = new File(str);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[(int) file.length()];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return Base64.encodeToString(bArr, 2);
    }

    public static String encodeBase64String(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }
}
