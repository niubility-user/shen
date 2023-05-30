package com.jingdong.manto.l;

import android.text.TextUtils;
import com.jingdong.manto.utils.MantoMd5Utils;

/* loaded from: classes15.dex */
class n {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return MantoMd5Utils.md5OfBytes(str.getBytes());
    }

    public static String a(String str, i iVar, g gVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (iVar != null) {
            sb.append("|transformation:");
            sb.append(iVar.b());
        }
        if (gVar != null) {
            sb.append("|decoder:");
            sb.append(gVar.b());
        }
        return sb.toString();
    }

    public static String a(String str, String str2) {
        return str + str2;
    }
}
