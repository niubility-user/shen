package com.jingdong.manto.m.p0.f.f.c;

/* loaded from: classes15.dex */
public interface c {

    /* loaded from: classes15.dex */
    public static class a implements c {
        private final int a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private final int f13547c;
        private final int d;

        public a(int i2, int i3, int i4, int i5) {
            this.a = i2;
            this.d = i3;
            this.b = i4;
            this.f13547c = i5;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.c
        public int a() {
            return this.b;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.c
        public byte b() {
            int i2 = this.d;
            return (i2 != 2 && i2 == 3) ? (byte) 8 : (byte) 16;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.c
        public int c() {
            return this.a;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.c
        public int d() {
            return this.d;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.c
        public int e() {
            return this.f13547c;
        }
    }

    int a();

    byte b();

    int c();

    int d();

    int e();
}
