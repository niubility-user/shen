package k.a.a.f.o;

/* loaded from: classes11.dex */
public enum a {
    KEY_STRENGTH_128(1, 8, 16, 16),
    KEY_STRENGTH_192(2, 12, 24, 24),
    KEY_STRENGTH_256(3, 16, 32, 32);
    
    private int keyLength;
    private int macLength;
    private int rawCode;
    private int saltLength;

    a(int i2, int i3, int i4, int i5) {
        this.rawCode = i2;
        this.saltLength = i3;
        this.macLength = i4;
        this.keyLength = i5;
    }

    public static a getAesKeyStrengthFromRawCode(int i2) {
        for (a aVar : values()) {
            if (aVar.getRawCode() == i2) {
                return aVar;
            }
        }
        return null;
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    public int getMacLength() {
        return this.macLength;
    }

    public int getRawCode() {
        return this.rawCode;
    }

    public int getSaltLength() {
        return this.saltLength;
    }
}
