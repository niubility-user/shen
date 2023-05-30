package com.tencent.mapsdk.internal;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class mb {
    private List<String> a = new ArrayList();
    private List<String> b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private final int f16876c = 300;

    private void a(List<String> list) {
        if (list != null && list.size() > 300) {
            list.remove(0);
        }
    }

    public synchronized void a() {
        this.a.clear();
        this.b.clear();
    }

    public synchronized void a(String str) {
        if (str != null) {
            if (str.trim().length() != 0) {
                if (this.b.contains(str)) {
                    this.b.remove(str);
                    this.b.add(str);
                    return;
                }
                if (this.a.contains(str)) {
                    a(this.b);
                    this.b.add(str);
                    this.a.remove(str);
                } else {
                    a(this.a);
                    this.a.add(str);
                }
            }
        }
    }

    public synchronized boolean b(String str) {
        if (str != null) {
            if (str.trim().length() != 0) {
                return !this.b.contains(str);
            }
        }
        return false;
    }
}
