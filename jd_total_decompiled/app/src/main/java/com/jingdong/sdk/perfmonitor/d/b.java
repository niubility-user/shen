package com.jingdong.sdk.perfmonitor.d;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class b {
    boolean a;
    Map<String, String> b;

    /* renamed from: c  reason: collision with root package name */
    List<String> f15410c;

    public boolean a(String str, String str2) {
        Map<String, String> map;
        String str3;
        if (str == null || str2 == null || (map = this.b) == null || (str3 = map.get(str)) == null) {
            return false;
        }
        return str3.equals(str2);
    }

    public boolean b(String str) {
        Map<String, String> map;
        if (str == null) {
            return false;
        }
        List<String> list = this.f15410c;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next())) {
                    return false;
                }
            }
        }
        return this.a || ((map = this.b) != null && map.containsValue(str));
    }
}
