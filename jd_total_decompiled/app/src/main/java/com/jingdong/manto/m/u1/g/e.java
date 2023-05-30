package com.jingdong.manto.m.u1.g;

import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.m.u1.f;

/* loaded from: classes15.dex */
class e extends c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public e(c cVar) {
        super(cVar);
    }

    @Override // com.jingdong.manto.m.u1.g.c
    public Pair<Boolean, String> a(String str, int i2, String str2) {
        boolean z;
        String str3 = null;
        String str4 = str2.indexOf(";105,") <= 0 ? str2.startsWith("105,") ? "105," : null : ";105,";
        boolean z2 = false;
        if (str4 != null) {
            int indexOf = str2.indexOf(str4);
            int indexOf2 = str2.indexOf(";", str4.length() + indexOf);
            if (-1 == indexOf2 || (str2.substring(indexOf, indexOf2).contains("data:image") && -1 == (indexOf2 = str2.substring(indexOf2).indexOf(";", indexOf2)))) {
                indexOf2 = str2.length();
                z = false;
            } else {
                z = true;
            }
            if (indexOf2 > indexOf) {
                String substring = str2.substring(indexOf, indexOf2).substring(str4.length());
                int indexOf3 = substring.indexOf("data:image");
                if (indexOf3 != -1) {
                    str3 = substring.substring(indexOf3);
                    substring = substring.substring(0, indexOf3 + 1);
                }
                String[] split = substring.split(DYConstants.DY_REGEX_COMMA);
                if (split.length == 8) {
                    f.a(str).a(i2, str2.substring(0, indexOf + 1));
                    String str5 = split[0];
                    int parseInt = Integer.parseInt(split[1]);
                    int parseInt2 = Integer.parseInt(split[2]);
                    int parseInt3 = Integer.parseInt(split[3]);
                    int parseInt4 = Integer.parseInt(split[4]);
                    int parseInt5 = Integer.parseInt(split[5]);
                    int parseInt6 = Integer.parseInt(split[6]);
                    if (str3 == null) {
                        str3 = split[7];
                    }
                    f.a(str).a(str5, parseInt, parseInt2, parseInt3, parseInt4, parseInt5, parseInt6, str3);
                    if (z) {
                        f.a(str).a(i2, str2.substring(indexOf2 + 1));
                    }
                    z2 = true;
                }
            }
        }
        return new Pair<>(Boolean.valueOf(z2), "");
    }
}
