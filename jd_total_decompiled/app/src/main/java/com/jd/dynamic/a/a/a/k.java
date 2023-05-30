package com.jd.dynamic.a.a.a;

import com.jd.dynamic.lib.utils.t;

/* loaded from: classes13.dex */
public class k implements h {
    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 107332:
                if (str.equals("log")) {
                    c2 = 0;
                    break;
                }
                break;
            case 3237038:
                if (str.equals("info")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3641990:
                if (str.equals("warn")) {
                    c2 = 2;
                    break;
                }
                break;
            case 95458899:
                if (str.equals("debug")) {
                    c2 = 3;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 3:
                t.g("DynJSLog", String.valueOf(objArr[0]));
                return null;
            case 2:
                t.f("DynJSLog", String.valueOf(objArr[0]));
                return null;
            case 4:
                t.e(String.valueOf(objArr[0]));
                return null;
            default:
                return null;
        }
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "JSCLog";
    }
}
