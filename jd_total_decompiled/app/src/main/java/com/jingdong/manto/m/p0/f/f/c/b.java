package com.jingdong.manto.m.p0.f.f.c;

/* loaded from: classes15.dex */
public interface b {

    /* loaded from: classes15.dex */
    public static final class a implements b {
        private final byte[] a;
        private int b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(byte[] bArr) {
            this.a = bArr;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.b
        public int a() {
            return this.b;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.b
        public void a(int i2) {
            this.b = i2;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.b
        public byte[] b() {
            return this.a;
        }
    }

    int a();

    void a(int i2);

    byte[] b();
}
