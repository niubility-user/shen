package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;

/* loaded from: classes9.dex */
public class wg implements Comparable<wg> {
    public final int a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f17437c;

    public wg(int i2, int i3, int i4) {
        this.a = i2;
        this.b = i3;
        this.f17437c = i4;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull wg wgVar) {
        return this.a - wgVar.a;
    }
}
