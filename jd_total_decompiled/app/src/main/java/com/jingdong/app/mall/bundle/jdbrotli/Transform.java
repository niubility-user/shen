package com.jingdong.app.mall.bundle.jdbrotli;

import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes2.dex */
public class Transform {
    private static final int IDENTITY = 0;
    static final int NUM_RFC_TRANSFORMS = 121;
    private static final int OMIT_FIRST_BASE = 11;
    private static final int OMIT_FIRST_LAST_LIMIT = 9;
    private static final int OMIT_LAST_BASE = 0;
    private static final String PREFIX_SUFFIX_SRC = "# #s #, #e #.# the #.com/#\u00c2\u00a0# of # and # in # to #\"#\">#\n#]# for # a # that #. # with #'# from # by #. The # on # as # is #ing #\n\t#:#ed #(# at #ly #=\"# of the #. This #,# not #er #al #='#ful #ive #less #est #ize #ous #";
    static final Transforms RFC_TRANSFORMS;
    private static final int SHIFT_ALL = 22;
    private static final int SHIFT_FIRST = 21;
    private static final String TRANSFORMS_SRC = "     !! ! ,  *!  &!  \" !  ) *   * -  ! # !  #!*!  +  ,$ !  -  %  .  / #   0  1 .  \"   2  3!*   4%  ! # /   5  6  7  8 0  1 &   $   9 +   :  ;  < '  !=  >  ?! 4  @ 4  2  &   A *# (   B  C& ) %  ) !*# *-% A +! *.  D! %'  & E *6  F  G% ! *A *%  H! D  I!+!  J!+   K +- *4! A  L!*4  M  N +6  O!*% +.! K *G  P +%(  ! G *D +D  Q +# *K!*G!+D!+# +G +A +4!+% +K!+4!*D!+K!*K";
    private static final int UPPERCASE_ALL = 11;
    private static final int UPPERCASE_FIRST = 10;

    /* loaded from: classes2.dex */
    static final class Transforms {
        final int numTransforms;
        final short[] params;
        final int[] prefixSuffixHeads;
        final byte[] prefixSuffixStorage;
        final int[] triplets;

        Transforms(int i2, int i3, int i4) {
            this.numTransforms = i2;
            this.triplets = new int[i2 * 3];
            this.params = new short[i2];
            this.prefixSuffixStorage = new byte[i3];
            this.prefixSuffixHeads = new int[i4 + 1];
        }
    }

    static {
        Transforms transforms = new Transforms(121, R2.anim.pop_left_top_in, 50);
        RFC_TRANSFORMS = transforms;
        unpackTransforms(transforms.prefixSuffixStorage, transforms.prefixSuffixHeads, transforms.triplets, PREFIX_SUFFIX_SRC, TRANSFORMS_SRC);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int transformDictionaryWord(byte[] bArr, int i2, ByteBuffer byteBuffer, int i3, int i4, Transforms transforms, int i5) {
        int i6;
        int i7;
        int[] iArr = transforms.triplets;
        byte[] bArr2 = transforms.prefixSuffixStorage;
        int[] iArr2 = transforms.prefixSuffixHeads;
        int i8 = i5 * 3;
        int i9 = iArr[i8];
        int i10 = iArr[i8 + 1];
        int i11 = 2;
        int i12 = iArr[i8 + 2];
        int i13 = iArr2[i9 + 1];
        int i14 = iArr2[i12 + 1];
        int i15 = i10 - 11;
        int i16 = i10 + 0;
        if (i15 < 1 || i15 > 9) {
            i15 = 0;
        }
        if (i16 < 1 || i16 > 9) {
            i6 = i2;
            i16 = 0;
        } else {
            i6 = i2;
        }
        for (int i17 = iArr2[i9]; i17 != i13; i17++) {
            bArr[i6] = bArr2[i17];
            i6++;
        }
        if (i15 > i4) {
            i15 = i4;
        }
        int i18 = i3 + i15;
        int i19 = (i4 - i15) - i16;
        int i20 = i19;
        while (i20 > 0) {
            bArr[i6] = byteBuffer.get(i18);
            i20--;
            i6++;
            i18++;
        }
        int i21 = 224;
        if (i10 == 10 || i10 == 11) {
            int i22 = i6 - i19;
            int i23 = i10 == 10 ? 1 : i19;
            while (i23 > 0) {
                int i24 = bArr[i22] & 255;
                if (i24 < 192) {
                    if (i24 >= 97 && i24 <= 122) {
                        bArr[i22] = (byte) (bArr[i22] ^ 32);
                    }
                    i22++;
                    i23--;
                } else if (i24 < 224) {
                    int i25 = i22 + 1;
                    bArr[i25] = (byte) (bArr[i25] ^ 32);
                    i22 += 2;
                    i23 -= 2;
                } else {
                    int i26 = i22 + 2;
                    bArr[i26] = (byte) (bArr[i26] ^ 5);
                    i22 += 3;
                    i23 -= 3;
                }
            }
        } else if (i10 == 21 || i10 == 22) {
            int i27 = i6 - i19;
            short s = transforms.params[i5];
            int i28 = (s & ShortCompanionObject.MAX_VALUE) + (16777216 - (s & ShortCompanionObject.MIN_VALUE));
            while (i19 > 0) {
                int i29 = bArr[i27] & 255;
                if (i29 < 128) {
                    i28 += i29;
                    bArr[i27] = (byte) (i28 & 127);
                } else if (i29 >= 192) {
                    if (i29 < i21) {
                        if (i19 >= i11) {
                            int i30 = i27 + 1;
                            byte b = bArr[i30];
                            i28 += ((i29 & 31) << 6) | (b & 63);
                            bArr[i27] = (byte) (((i28 >> 6) & 31) | 192);
                            bArr[i30] = (byte) ((b & 192) | (i28 & 63));
                            i7 = 2;
                        }
                        i7 = i19;
                    } else if (i29 < 240) {
                        if (i19 >= 3) {
                            int i31 = i27 + 1;
                            byte b2 = bArr[i31];
                            int i32 = i27 + 2;
                            byte b3 = bArr[i32];
                            i28 += (b3 & 63) | ((b2 & 63) << 6) | ((i29 & 15) << 12);
                            bArr[i27] = (byte) (((i28 >> 12) & 15) | i21);
                            bArr[i31] = (byte) ((b2 & 192) | ((i28 >> 6) & 63));
                            bArr[i32] = (byte) ((b3 & 192) | (i28 & 63));
                            i7 = 3;
                        }
                        i7 = i19;
                    } else if (i29 < 248) {
                        if (i19 >= 4) {
                            int i33 = i27 + 1;
                            byte b4 = bArr[i33];
                            int i34 = i27 + 2;
                            byte b5 = bArr[i34];
                            int i35 = i27 + 3;
                            byte b6 = bArr[i35];
                            i28 += (b6 & 63) | ((b5 & 63) << 6) | ((b4 & 63) << 12) | ((i29 & 7) << 18);
                            bArr[i27] = (byte) (((i28 >> 18) & 7) | 240);
                            bArr[i33] = (byte) ((b4 & 192) | ((i28 >> 12) & 63));
                            bArr[i34] = (byte) ((b5 & 192) | ((i28 >> 6) & 63));
                            bArr[i35] = (byte) ((b6 & 192) | (i28 & 63));
                            i7 = 4;
                        }
                        i7 = i19;
                    }
                    i27 += i7;
                    i19 -= i7;
                    if (i10 != 21) {
                        i19 = 0;
                    }
                    i21 = 224;
                    i11 = 2;
                }
                i7 = 1;
                i27 += i7;
                i19 -= i7;
                if (i10 != 21) {
                }
                i21 = 224;
                i11 = 2;
            }
        }
        for (int i36 = iArr2[i12]; i36 != i14; i36++) {
            bArr[i6] = bArr2[i36];
            i6++;
        }
        return i6 - i2;
    }

    private static void unpackTransforms(byte[] bArr, int[] iArr, int[] iArr2, String str, String str2) {
        int length = str.length();
        int i2 = 1;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt == '#') {
                iArr[i2] = i3;
                i2++;
            } else {
                bArr[i3] = (byte) charAt;
                i3++;
            }
        }
        for (int i5 = 0; i5 < 363; i5++) {
            iArr2[i5] = str2.charAt(i5) - ' ';
        }
    }
}
