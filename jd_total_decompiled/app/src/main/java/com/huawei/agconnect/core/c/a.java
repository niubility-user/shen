package com.huawei.agconnect.core.c;

import g.e.a.a;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes12.dex */
public class a extends g.e.a.a {
    private static final List<a.InterfaceC0832a> a = new CopyOnWriteArrayList();

    public static void a() {
        Iterator<a.InterfaceC0832a> it = a.iterator();
        while (it.hasNext()) {
            it.next().onFinish();
        }
    }
}
