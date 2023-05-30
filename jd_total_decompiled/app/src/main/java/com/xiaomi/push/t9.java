package com.xiaomi.push;

import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class t9 {
    private static final HashMap<String, String> a;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        a = hashMap;
        hashMap.put("FFD8FF", "jpg");
        hashMap.put("89504E47", "png");
        hashMap.put("47494638", WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF);
        hashMap.put("474946", WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF);
        hashMap.put("424D", "bmp");
    }

    public static long a(File file) {
        long j2 = 0;
        try {
            File[] listFiles = file.listFiles();
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                j2 += listFiles[i2].isDirectory() ? a(listFiles[i2]) : listFiles[i2].length();
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
        return j2;
    }
}
