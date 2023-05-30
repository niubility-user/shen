package com.jingdong.manto.sdk.e;

import java.io.File;

/* loaded from: classes16.dex */
public class a {
    public static boolean a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            file.mkdirs();
            File file2 = new File(str + ".nomedia");
            if (file2.exists()) {
                return true;
            }
            file2.createNewFile();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
