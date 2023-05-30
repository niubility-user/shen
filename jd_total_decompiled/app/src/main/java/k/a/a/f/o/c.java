package k.a.a.f.o;

import k.a.a.c.a;

/* loaded from: classes11.dex */
public enum c {
    STORE(0),
    DEFLATE(8),
    AES_INTERNAL_ONLY(99);
    
    private int code;

    c(int i2) {
        this.code = i2;
    }

    public static c getCompressionMethodFromCode(int i2) throws k.a.a.c.a {
        for (c cVar : values()) {
            if (cVar.getCode() == i2) {
                return cVar;
            }
        }
        throw new k.a.a.c.a("Unknown compression method", a.EnumC0855a.UNKNOWN_COMPRESSION_METHOD);
    }

    public int getCode() {
        return this.code;
    }
}
