package com.jingdong.manto.m.u1.g;

import android.util.Pair;

/* loaded from: classes15.dex */
public class a {
    private c a = new d(new e(new b(null)));

    public String a(String str, int i2, String str2) {
        c cVar = this.a;
        if (cVar == null) {
            return "";
        }
        while (cVar != null) {
            Pair<Boolean, String> a = cVar.a(str, i2, str2);
            if (((Boolean) a.first).booleanValue()) {
                return (String) a.second;
            }
            cVar = cVar.a();
        }
        return "";
    }
}
