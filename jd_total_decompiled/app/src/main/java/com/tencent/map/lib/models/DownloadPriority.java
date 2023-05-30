package com.tencent.map.lib.models;

/* loaded from: classes9.dex */
public enum DownloadPriority {
    NONE(-1),
    HIGH(0),
    MIDDLE(1),
    LOW(2);
    
    private final int mValue;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            DownloadPriority.values();
            int[] iArr = new int[4];
            a = iArr;
            try {
                DownloadPriority downloadPriority = DownloadPriority.HIGH;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                DownloadPriority downloadPriority2 = DownloadPriority.LOW;
                iArr2[3] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                DownloadPriority downloadPriority3 = DownloadPriority.MIDDLE;
                iArr3[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    DownloadPriority(int i2) {
        this.mValue = i2;
    }

    public static DownloadPriority get(int i2) {
        DownloadPriority[] values = values();
        for (int i3 = 0; i3 < 4; i3++) {
            DownloadPriority downloadPriority = values[i3];
            if (downloadPriority.mValue == i2) {
                return downloadPriority;
            }
        }
        return NONE;
    }

    public static int getThreadPriority(int i2) {
        int ordinal = get(i2).ordinal();
        if (ordinal != 1) {
            return ordinal != 3 ? 5 : 1;
        }
        return 10;
    }

    public int getValue() {
        return this.mValue;
    }
}
