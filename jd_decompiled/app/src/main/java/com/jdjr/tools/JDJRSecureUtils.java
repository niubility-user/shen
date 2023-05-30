package com.jdjr.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class JDJRSecureUtils {
    public static byte[] getErrorCode(byte[] bArr) {
        byte[] bArr2 = new byte[5];
        System.arraycopy(bArr, 0, bArr2, 0, 5);
        return bArr2;
    }

    public static byte[] getRetData(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length - 5];
        System.arraycopy(bArr, 5, bArr2, 0, bArr.length - 5);
        return bArr2;
    }

    public static String readTxtFile(String str) {
        String str2 = "";
        try {
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str2 = str2 + readLine;
                }
                inputStreamReader.close();
            } else {
                System.out.println("\u627e\u4e0d\u5230\u6307\u5b9a\u7684\u6587\u4ef6");
            }
        } catch (Exception e2) {
            System.out.println("\u8bfb\u53d6\u6587\u4ef6\u5185\u5bb9\u51fa\u9519");
            e2.printStackTrace();
        }
        return str2;
    }
}
