package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class v8 extends y8 {
    private a L;
    private long M;

    /* loaded from: classes9.dex */
    public interface a {
        void a(v8 v8Var, long j2, long j3);
    }

    public v8() {
        super(null);
        this.M = -1L;
    }

    @Override // com.tencent.mapsdk.internal.y8
    public void a(float f2) {
    }

    public void a(a aVar) {
        this.L = aVar;
    }

    @Override // com.tencent.mapsdk.internal.y8
    public boolean c(long j2) {
        if (this.f17495i == 0) {
            this.f17495i = 1;
            long j3 = this.f17490c;
            if (j3 < 0) {
                this.b = j2;
            } else {
                this.b = j2 - j3;
                this.f17490c = -1L;
            }
        }
        a aVar = this.L;
        if (aVar != null) {
            long j4 = j2 - this.b;
            long j5 = this.M;
            long j6 = j5 >= 0 ? j2 - j5 : 0L;
            this.M = j2;
            aVar.a(this, j4, j6);
            return false;
        }
        return false;
    }
}
