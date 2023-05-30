package com.xiaomi.push;

import java.util.LinkedList;

/* loaded from: classes11.dex */
public class l0 {
    private LinkedList<a> a = new LinkedList<>();

    /* loaded from: classes11.dex */
    public static class a {
        private static final l0 d = new l0();
        public int a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public Object f18817c;

        a(int i2, Object obj) {
            this.a = i2;
            this.f18817c = obj;
        }
    }

    public static l0 b() {
        return a.d;
    }

    private void d() {
        if (this.a.size() > 100) {
            this.a.removeFirst();
        }
    }

    public synchronized int a() {
        return this.a.size();
    }

    public synchronized LinkedList<a> c() {
        LinkedList<a> linkedList;
        linkedList = this.a;
        this.a = new LinkedList<>();
        return linkedList;
    }

    public synchronized void e(Object obj) {
        this.a.add(new a(0, obj));
        d();
    }
}
