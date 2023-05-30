package k.a.a.f.o;

/* loaded from: classes11.dex */
public enum b {
    ONE(1),
    TWO(2);
    
    private int versionNumber;

    b(int i2) {
        this.versionNumber = i2;
    }

    public static b getFromVersionNumber(int i2) {
        for (b bVar : values()) {
            if (bVar.versionNumber == i2) {
                return bVar;
            }
        }
        throw new IllegalArgumentException("Unsupported Aes version");
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }
}
