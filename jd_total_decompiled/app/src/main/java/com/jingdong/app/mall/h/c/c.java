package com.jingdong.app.mall.h.c;

import android.net.Uri;
import android.text.TextUtils;
import com.jd.hybrid.downloader.j;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;

/* loaded from: classes3.dex */
public class c {
    public static long a;

    public static String a(String str, String str2) {
        int indexOf;
        int i2;
        com.jd.hybrid.downloader.m.b k2;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int indexOf2 = str2.indexOf("jfs/");
            int lastIndexOf = str2.lastIndexOf("/");
            if (indexOf2 >= 0 && lastIndexOf >= 0) {
                String substring = str2.substring(lastIndexOf);
                if (TextUtils.isEmpty(substring) || (indexOf = substring.indexOf(OrderISVUtil.MONEY_DECIMAL)) < 0 || indexOf2 > (i2 = lastIndexOf + indexOf)) {
                    return str2;
                }
                String substring2 = str2.substring(indexOf2 + 4, i2);
                if (TextUtils.isEmpty(substring2)) {
                    return str2;
                }
                String md5 = Md5Encrypt.md5(substring2);
                if (!TextUtils.isEmpty(md5) && (k2 = j.l().k("ximage", str)) != null && !TextUtils.isEmpty(k2.getFilePath())) {
                    File file = new File(k2.getFilePath() + File.separator + md5);
                    if (file.exists()) {
                        a++;
                        return Uri.fromFile(file).toString();
                    }
                }
            }
        }
        return str2;
    }
}
