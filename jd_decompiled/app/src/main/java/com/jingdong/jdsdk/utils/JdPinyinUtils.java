package com.jingdong.jdsdk.utils;

import android.os.Build;
import android.text.TextUtils;
import com.jingdong.jdsdk.utils.HanziToPinyin;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes14.dex */
public final class JdPinyinUtils {
    private static final String EMPTY_STR = "";

    private JdPinyinUtils() {
    }

    public static String convertToPinyin(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                return f.c(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ArrayList<HanziToPinyin.a> arrayList = HanziToPinyin.getInstance().get(str);
        if (arrayList == null || arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<HanziToPinyin.a> it = arrayList.iterator();
        while (it.hasNext()) {
            HanziToPinyin.a next = it.next();
            if (2 == next.a) {
                sb.append(next.f12930c);
            } else {
                sb.append(next.b);
            }
        }
        return sb.toString();
    }

    public String convertToPinyin(String str, boolean z) {
        String convertToPinyin = convertToPinyin(str);
        return z ? convertToPinyin.toUpperCase() : convertToPinyin;
    }
}
