package com.google.zxing.datamatrix.encoder;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, R2.anim.settlement_dialog_right_exit, R2.anim.pop_left_bottom_out, 223, 248, 116, 255, 110, 61}, new int[]{R2.anim.popup_anim_feedback, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, 205, 12, R2.anim.slide_out_to_bottom, R2.anim.pop_left_top_out, 39, 245, 60, 97, 120}, new int[]{41, 153, R2.anim.pickerview_dialog_scale_in, 91, 61, 42, R2.anim.manto_translate_dialog_in, 213, 97, 178, 100, 242}, new int[]{R2.anim.out_to_bottom_slow, 97, 192, 252, 95, 9, R2.anim.out_to_right, 119, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, 45, 18, R2.anim.slide_down, 83, R2.anim.settlement_dialog_right_exit}, new int[]{83, R2.anim.slide_out_to_bottom_medium_time, 100, 39, R2.anim.slide_in_from_bottom_medium_time, 75, 66, 61, 241, 213, 109, 129, 94, 254, 225, 48, 90, R2.anim.slide_in_from_bottom_medium_time}, new int[]{15, R2.anim.slide_out_to_bottom_medium_time, 244, 9, 233, 71, R2.anim.pop_left_top_out, 2, R2.anim.slide_in_from_bottom_medium_time, 160, 153, R2.anim.message_center_dialog_out, 253, 79, 108, 82, 27, R2.anim.popdown_anim_feedback, R2.anim.slide_down, R2.anim.pop_right_top_in}, new int[]{52, R2.anim.slide_in_from_top, 88, 205, 109, 39, R2.anim.popup_center_enter, 21, 155, R2.anim.slide_out_to_top, 251, 223, 155, 21, 5, R2.anim.pop_right_top_in, 254, 124, 12, R2.anim.push_right_out, R2.anim.settlement_dialog_right_enter, 96, 50, R2.anim.slide_out_from_left}, new int[]{211, 231, 43, 97, 71, 96, 103, R2.anim.popdown_anim_feedback, 37, 151, 170, 53, 75, 34, 249, 121, 17, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, 110, 213, R2.anim.manto_push_right_in, R2.anim.lib_cashier_sdk_fragment_right_out, 120, 151, 233, R2.anim.pop_left_top_out, 93, 255}, new int[]{245, 127, 242, 218, 130, 250, 162, R2.anim.push_right_out, 102, 120, 84, R2.anim.popwin_anim_alpha_out, 220, 251, 80, R2.anim.settlement_dialog_bottom_enter, 229, 18, 2, 4, 68, 33, 101, R2.anim.lib_cashier_sdk_pop_in_animation_bottom, 95, 119, 115, 44, R2.anim.popup_anim_feedback, R2.anim.settlement_dialog_right_enter, 59, 25, 225, 98, 81, 112}, new int[]{77, R2.anim.slide_out_from_left, R2.anim.lib_cashier_sdk_pop_in_animation_bottom, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, 133, 242, 8, R2.anim.popup_anim_feedback, 95, 100, 9, R2.anim.pop_left_top_in, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 9, 5}, new int[]{245, 132, R2.anim.pop_right_top_in, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, R2.anim.slide_in_from_bottom_medium_time, 237, 87, R2.anim.slide_in_from_top_medium_time, 106, 16, R2.anim.miaosha_dropdown_out, 118, 23, 37, 90, 170, 205, 131, 88, 120, 100, 66, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, R2.anim.slide_down, 240, 82, 44, R2.anim.popup_center_enter, 87, R2.anim.slide_in_from_bottom, R2.anim.miaosha_dropdown_out, 160, R2.anim.popup_anim_feedback, 69, 213, 92, 253, 225, 19}, new int[]{R2.anim.popup_anim_feedback, 9, 223, 238, 12, 17, 220, 208, 100, 29, R2.anim.popup_anim_feedback, 170, 230, 192, 215, 235, 150, R2.anim.pickerview_dialog_scale_out, 36, 223, 38, 200, 132, 54, 228, R2.anim.miaosha_dropdown_in, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, 201, 117, 62, 207, R2.anim.pop_in, 13, R2.anim.lib_cashier_sdk_pop_in_animation_bottom, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, R2.anim.manto_translate_dialog_out, 46}, new int[]{242, 93, 169, 50, 144, 210, 39, 118, 202, R2.anim.slide_in_from_bottom_medium_time, 201, R2.anim.slide_in_from_left, R2.anim.manto_translate_dialog_out, 108, R2.anim.slide_out_to_right, 37, R2.anim.settlement_dialog_right_exit, 112, 134, 230, 245, 63, R2.anim.slide_out_to_top, R2.anim.slide_in_from_top, 250, 106, R2.anim.settlement_dialog_right_exit, 221, R2.anim.popup_anim_feedback, 64, 114, 71, 161, 44, R2.anim.miaosha_dropdown_out, 6, 27, 218, 51, 63, 87, 10, 40, 130, R2.anim.slide_in_from_bottom_medium_time, 17, R2.anim.pop_center_out, 31, R2.anim.popup_center_enter, 170, 4, 107, 232, 7, 94, R2.anim.pop_left_bottom_out, 224, 124, 86, 47, 11, 204}, new int[]{220, 228, R2.anim.pop_right_top_out, 89, 251, R2.anim.mtrl_bottom_sheet_slide_out, R2.anim.pickerview_dialog_scale_out, 56, 89, 33, R2.anim.miaosha_dropdown_out, 244, 154, 36, 73, 127, 213, R2.anim.lib_cashier_sdk_fragment_right_out, 248, 180, 234, R2.anim.slide_out_to_top, R2.anim.pickerview_dialog_scale_in, 177, 68, 122, 93, 213, 15, 160, 227, 236, 66, R2.anim.live_pop_rotate_amin, 153, R2.anim.settlement_dialog_right_exit, 202, R2.anim.pop_left_top_in, R2.anim.popwin_anim_alpha_out, 25, 220, 232, 96, 210, 231, R2.anim.lib_cashier_sdk_fragment_right_out, 223, 239, R2.anim.push_right_out, 241, 59, 52, R2.anim.pop_right_top_in, 25, 49, 232, 211, R2.anim.slide_in_from_left, 64, 54, 108, 153, 132, 63, 96, 103, 82, R2.anim.slide_down}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int i2 = 1;
        for (int i3 = 0; i3 < 255; i3++) {
            ALOG[i3] = i2;
            LOG[i2] = i3;
            i2 *= 2;
            if (i2 >= 256) {
                i2 ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    private static String createECCBlock(CharSequence charSequence, int i2) {
        return createECCBlock(charSequence, 0, charSequence.length(), i2);
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() == symbolInfo.getDataCapacity()) {
            StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
            sb.append(str);
            int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
            if (interleavedBlockCount == 1) {
                sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
            } else {
                sb.setLength(sb.capacity());
                int[] iArr = new int[interleavedBlockCount];
                int[] iArr2 = new int[interleavedBlockCount];
                int[] iArr3 = new int[interleavedBlockCount];
                int i2 = 0;
                while (i2 < interleavedBlockCount) {
                    int i3 = i2 + 1;
                    iArr[i2] = symbolInfo.getDataLengthForInterleavedBlock(i3);
                    iArr2[i2] = symbolInfo.getErrorLengthForInterleavedBlock(i3);
                    iArr3[i2] = 0;
                    if (i2 > 0) {
                        iArr3[i2] = iArr3[i2 - 1] + iArr[i2];
                    }
                    i2 = i3;
                }
                for (int i4 = 0; i4 < interleavedBlockCount; i4++) {
                    StringBuilder sb2 = new StringBuilder(iArr[i4]);
                    for (int i5 = i4; i5 < symbolInfo.getDataCapacity(); i5 += interleavedBlockCount) {
                        sb2.append(str.charAt(i5));
                    }
                    String createECCBlock = createECCBlock(sb2.toString(), iArr2[i4]);
                    int i6 = i4;
                    int i7 = 0;
                    while (i6 < iArr2[i4] * interleavedBlockCount) {
                        sb.setCharAt(symbolInfo.getDataCapacity() + i6, createECCBlock.charAt(i7));
                        i6 += interleavedBlockCount;
                        i7++;
                    }
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }

    private static String createECCBlock(CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i5 >= iArr.length) {
                i5 = -1;
                break;
            } else if (iArr[i5] == i4) {
                break;
            } else {
                i5++;
            }
        }
        if (i5 >= 0) {
            int[] iArr2 = FACTORS[i5];
            char[] cArr = new char[i4];
            for (int i6 = 0; i6 < i4; i6++) {
                cArr[i6] = 0;
            }
            for (int i7 = i2; i7 < i2 + i3; i7++) {
                int i8 = i4 - 1;
                int charAt = cArr[i8] ^ charSequence.charAt(i7);
                while (i8 > 0) {
                    if (charAt != 0 && iArr2[i8] != 0) {
                        char c2 = cArr[i8 - 1];
                        int[] iArr3 = ALOG;
                        int[] iArr4 = LOG;
                        cArr[i8] = (char) (c2 ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i8]]) % 255]);
                    } else {
                        cArr[i8] = cArr[i8 - 1];
                    }
                    i8--;
                }
                if (charAt != 0 && iArr2[0] != 0) {
                    int[] iArr5 = ALOG;
                    int[] iArr6 = LOG;
                    cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
                } else {
                    cArr[0] = 0;
                }
            }
            char[] cArr2 = new char[i4];
            for (int i9 = 0; i9 < i4; i9++) {
                cArr2[i9] = cArr[(i4 - i9) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i4);
    }
}
