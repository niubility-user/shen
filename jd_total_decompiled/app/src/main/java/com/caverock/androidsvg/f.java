package com.caverock.androidsvg;

import com.jingdong.common.utils.LangUtils;

/* loaded from: classes.dex */
public class f {

    /* renamed from: c  reason: collision with root package name */
    public static final f f843c = new f(a.none, null);
    public static final f d = new f(a.xMidYMid, b.meet);
    private a a;
    private b b;

    /* loaded from: classes.dex */
    public enum a {
        none,
        xMinYMin,
        xMidYMin,
        xMaxYMin,
        xMinYMid,
        xMidYMid,
        xMaxYMid,
        xMinYMax,
        xMidYMax,
        xMaxYMax
    }

    /* loaded from: classes.dex */
    public enum b {
        meet,
        slice
    }

    static {
        a aVar = a.xMinYMin;
        a aVar2 = a.xMaxYMax;
        a aVar3 = a.xMidYMin;
        a aVar4 = a.xMidYMax;
        b bVar = b.slice;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(a aVar, b bVar) {
        this.a = aVar;
        this.b = bVar;
    }

    public a a() {
        return this.a;
    }

    public b b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && f.class == obj.getClass()) {
            f fVar = (f) obj;
            return this.a == fVar.a && this.b == fVar.b;
        }
        return false;
    }

    public String toString() {
        return this.a + LangUtils.SINGLE_SPACE + this.b;
    }
}
