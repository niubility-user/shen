package com.jingdong.app.mall.bundle.smile.utils;

import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public class MD5Utils {
    public static String checkMd5(File file) {
        try {
            return Md5EncryptUtil.getFileMD5String(file);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
