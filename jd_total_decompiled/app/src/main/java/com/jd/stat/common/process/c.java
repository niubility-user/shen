package com.jd.stat.common.process;

import java.io.File;

/* loaded from: classes18.dex */
public class c {
    public static String a() {
        File file = new File("/dev/socket");
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < file.list().length; i2++) {
                sb.append(file.list()[i2]);
                if (i2 < file.list().length - 1) {
                    sb.append(";");
                }
            }
            return sb.toString();
        }
        return "";
    }
}
