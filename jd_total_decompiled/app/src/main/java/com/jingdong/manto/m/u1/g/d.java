package com.jingdong.manto.m.u1.g;

import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.m.u1.f;

/* loaded from: classes15.dex */
class d extends c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
        super(cVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b0, code lost:
        if (r8 != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0105, code lost:
        if (r8 != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0107, code lost:
        com.jingdong.manto.m.u1.f.a(r26).a(r27, r28.substring(r7 + 1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0113, code lost:
        r3 = true;
     */
    @Override // com.jingdong.manto.m.u1.g.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Pair<Boolean, String> a(String str, int i2, String str2) {
        boolean z;
        String str3 = null;
        String str4 = str2.indexOf(";102,") <= 0 ? str2.startsWith("102,") ? "102," : null : ";102,";
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
                if (split.length == 6) {
                    f.a(str).a(i2, str2.substring(0, indexOf + 1));
                    int parseInt = Integer.parseInt(split[0]);
                    int parseInt2 = Integer.parseInt(split[1]);
                    int parseInt3 = Integer.parseInt(split[2]);
                    int parseInt4 = Integer.parseInt(split[3]);
                    int parseInt5 = Integer.parseInt(split[4]);
                    if (str3 == null) {
                        str3 = split[5];
                    }
                    f.a(str).a(parseInt, parseInt2, parseInt3, parseInt4, parseInt5, str3);
                } else if (split.length == 9) {
                    f.a(str).a(i2, str2.substring(0, indexOf + 1));
                    int parseInt6 = Integer.parseInt(split[0]);
                    int parseInt7 = Integer.parseInt(split[1]);
                    int parseInt8 = Integer.parseInt(split[2]);
                    int parseInt9 = Integer.parseInt(split[3]);
                    int parseInt10 = Integer.parseInt(split[4]);
                    int parseInt11 = Integer.parseInt(split[5]);
                    int parseInt12 = Integer.parseInt(split[6]);
                    int parseInt13 = Integer.parseInt(split[7]);
                    if (str3 == null) {
                        str3 = split[8];
                    }
                    f.a(str).a(parseInt9, parseInt10, parseInt11, parseInt6, parseInt7, parseInt8, parseInt12, parseInt13, str3);
                }
            }
        }
        return new Pair<>(Boolean.valueOf(z2), "");
    }
}
