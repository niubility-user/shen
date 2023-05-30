package com.jingdong.common.unification.filter.filter;

/* loaded from: classes6.dex */
public enum Rotation {
    NORMAL,
    ROTATION_90,
    ROTATION_180,
    ROTATION_270;

    /* renamed from: com.jingdong.common.unification.filter.filter.Rotation$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation;

        static {
            int[] iArr = new int[Rotation.values().length];
            $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation = iArr;
            try {
                iArr[Rotation.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[Rotation.ROTATION_90.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[Rotation.ROTATION_180.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[Rotation.ROTATION_270.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static Rotation fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 90) {
                if (i2 != 180) {
                    if (i2 != 270) {
                        if (i2 == 360) {
                            return NORMAL;
                        }
                        throw new IllegalStateException(i2 + " is an unknown rotation. Needs to be either 0, 90, 180 or 270!");
                    }
                    return ROTATION_270;
                }
                return ROTATION_180;
            }
            return ROTATION_90;
        }
        return NORMAL;
    }

    public int asInt() {
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4) {
                        return 270;
                    }
                    throw new IllegalStateException("Unknown Rotation!");
                }
                return 180;
            }
            return 90;
        }
        return 0;
    }
}
