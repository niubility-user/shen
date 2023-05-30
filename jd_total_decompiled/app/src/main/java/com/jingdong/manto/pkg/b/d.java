package com.jingdong.manto.pkg.b;

import com.jingdong.manto.pkg.b.i;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes16.dex */
public class d {
    private i a;
    private String b;

    public d(String str, i iVar) {
        this.b = str;
        this.a = iVar;
    }

    public InputStream a(String str) {
        String str2 = this.b;
        String str3 = File.separator;
        if (str2.endsWith(str3)) {
            String str4 = this.b;
            this.b = str4.substring(0, str4.length() - 1);
        }
        if (str.startsWith(str3)) {
            str = str.substring(1);
        }
        i iVar = this.a;
        if (iVar instanceof i.d) {
            return iVar.a(str);
        }
        return iVar.a(this.b + str3 + str);
    }
}
