package com.jingdong.jdpush_new.d;

import java.util.ArrayList;

/* loaded from: classes12.dex */
public class b {
    private static ArrayList<Short> a;
    private static ArrayList<Short> b;

    static {
        ArrayList<Short> arrayList = new ArrayList<>();
        a = arrayList;
        arrayList.add((short) 0);
        a.add((short) 2100);
        a.add((short) 2102);
        a.add((short) 2104);
        a.add((short) 2106);
        a.add((short) 2108);
        a.add((short) 2110);
        a.add((short) 2112);
        a.add((short) 2115);
        a.add((short) 2116);
        a.add((short) 2118);
        a.add((short) 2122);
        a.add((short) 2124);
        ArrayList<Short> arrayList2 = new ArrayList<>();
        b = arrayList2;
        arrayList2.add((short) 1);
        b.add((short) 2101);
        b.add((short) 2103);
        b.add((short) 2105);
        b.add((short) 2107);
        b.add((short) 2109);
        b.add((short) 2111);
        b.add((short) 2113);
        b.add((short) 2114);
        b.add((short) 2117);
        b.add((short) 2119);
        b.add((short) 2121);
        b.add((short) 2123);
        b.add((short) 2125);
    }

    public static boolean a(short s) {
        return b.contains(Short.valueOf(s));
    }

    public static boolean b(short s) {
        return a.contains(Short.valueOf(s));
    }
}
