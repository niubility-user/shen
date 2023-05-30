package com.xiaomi.push;

import android.util.Pair;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class n2 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f18874g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Throwable f18875h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ m2 f18876i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n2(m2 m2Var, String str, Throwable th) {
        this.f18876i = m2Var;
        this.f18874g = str;
        this.f18875h = th;
    }

    @Override // java.lang.Runnable
    public void run() {
        List list;
        SimpleDateFormat simpleDateFormat;
        String str;
        List list2;
        List list3;
        List list4;
        SimpleDateFormat simpleDateFormat2;
        String str2;
        List list5;
        List list6;
        String unused;
        String unused2;
        list = m2.f18854g;
        simpleDateFormat = m2.f18852e;
        str = this.f18876i.a;
        list.add(new Pair(String.format("%1$s %2$s %3$s ", simpleDateFormat.format(new Date()), str, this.f18874g), this.f18875h));
        list2 = m2.f18854g;
        if (list2.size() > 20000) {
            list3 = m2.f18854g;
            int size = (list3.size() - 20000) + 50;
            for (int i2 = 0; i2 < size; i2++) {
                try {
                    list5 = m2.f18854g;
                    if (list5.size() > 0) {
                        list6 = m2.f18854g;
                        list6.remove(0);
                    }
                } catch (IndexOutOfBoundsException unused3) {
                }
            }
            list4 = m2.f18854g;
            simpleDateFormat2 = m2.f18852e;
            str2 = this.f18876i.a;
            list4.add(new Pair(String.format("%1$s %2$s %3$s ", simpleDateFormat2.format(new Date()), str2, "flush " + size + " lines logs."), null));
        }
        try {
            if (w9.e()) {
                this.f18876i.f();
            } else {
                unused = this.f18876i.a;
            }
        } catch (Exception unused4) {
            unused2 = this.f18876i.a;
        }
    }
}
