package com.jingdong.app.mall.bundle.jdbrotli;

import com.jingdong.sdk.platform.business.personal.R2;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int transformDictionaryWord(byte[] r21, int r22, java.nio.ByteBuffer r23, int r24, int r25, com.jingdong.app.mall.bundle.jdbrotli.Transform.Transforms r26, int r27) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.jdbrotli.Transform.transformDictionaryWord(byte[], int, java.nio.ByteBuffer, int, int, com.jingdong.app.mall.bundle.jdbrotli.Transform$Transforms, int):int");
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
