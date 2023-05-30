package com.tencent.mapsdk.internal;

import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class s7 extends q7 {

    /* renamed from: j  reason: collision with root package name */
    private ArrayList<q7> f17238j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s7(q7... q7VarArr) {
        super(0L);
        long j2 = 0;
        this.f17238j = new ArrayList<>();
        for (int i2 = 0; i2 < q7VarArr.length; i2++) {
            this.f17238j.add(q7VarArr[i2]);
            j2 = Math.max(j2, q7VarArr[i2].a());
        }
        this.a = j2;
    }

    @Override // com.tencent.mapsdk.internal.q7
    public void a(GL10 gl10, long j2) {
        ArrayList<q7> arrayList = this.f17238j;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<q7> it = this.f17238j.iterator();
        while (it.hasNext()) {
            q7 next = it.next();
            if (!next.c()) {
                next.a(gl10);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.q7
    public void d() {
        super.d();
        Iterator<q7> it = this.f17238j.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
    }

    @Override // com.tencent.mapsdk.internal.q7
    public void e() {
        super.e();
        Iterator<q7> it = this.f17238j.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
    }
}
