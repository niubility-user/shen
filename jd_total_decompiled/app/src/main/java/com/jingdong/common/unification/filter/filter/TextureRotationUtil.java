package com.jingdong.common.unification.filter.filter;

/* loaded from: classes6.dex */
public class TextureRotationUtil {
    public static final float[] TEXTURE_NO_ROTATION = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    public static final float[] TEXTURE_ROTATED_90 = {1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
    public static final float[] TEXTURE_ROTATED_180 = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    public static final float[] TEXTURE_ROTATED_270 = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    public static final float[] CUBE = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: com.jingdong.common.unification.filter.filter.TextureRotationUtil$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation;

        static {
            int[] iArr = new int[Rotation.values().length];
            $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation = iArr;
            try {
                iArr[Rotation.ROTATION_90.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[Rotation.ROTATION_180.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[Rotation.ROTATION_270.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[Rotation.NORMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private TextureRotationUtil() {
    }

    private static float flip(float f2) {
        return f2 == 0.0f ? 1.0f : 0.0f;
    }

    public static float[] getRotation(Rotation rotation, boolean z, boolean z2) {
        float[] fArr;
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$common$unification$filter$filter$Rotation[rotation.ordinal()];
        if (i2 == 1) {
            fArr = TEXTURE_ROTATED_90;
        } else if (i2 == 2) {
            fArr = TEXTURE_ROTATED_180;
        } else if (i2 != 3) {
            fArr = TEXTURE_NO_ROTATION;
        } else {
            fArr = TEXTURE_ROTATED_270;
        }
        if (z) {
            fArr = new float[]{flip(fArr[0]), fArr[1], flip(fArr[2]), fArr[3], flip(fArr[4]), fArr[5], flip(fArr[6]), fArr[7]};
        }
        return z2 ? new float[]{fArr[0], flip(fArr[1]), fArr[2], flip(fArr[3]), fArr[4], flip(fArr[5]), fArr[6], flip(fArr[7])} : fArr;
    }
}
