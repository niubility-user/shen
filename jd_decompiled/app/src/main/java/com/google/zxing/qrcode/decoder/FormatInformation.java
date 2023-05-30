package com.google.zxing.qrcode.decoder;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
final class FormatInformation {
    private static final int FORMAT_INFO_MASK_QR = 21522;
    private final byte dataMask;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private static final int[][] FORMAT_INFO_DECODE_LOOKUP = {new int[]{21522, 0}, new int[]{R2.string.uni_coo_image_no_pic, 1}, new int[]{R2.styleable.MultiTagLayout_tag_right_margin, 2}, new int[]{R2.styleable.DragSortListView_collapsed_height, 3}, new int[]{R2.layout.lib_pd_core_giftshopping_mainsku_numedit_layout, 4}, new int[]{R2.id.touch_record_layer, 5}, new int[]{R2.string.pull_to_refresh_header_hint_pull, 6}, new int[]{R2.string.coupon_unit_hour, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{R2.styleable.lib_remote_view_ttf_path, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{R2.dimen.dp_244, 16}, new int[]{R2.dimen.DefaultListItemHeight, 17}, new int[]{R2.dimen.text_size_23, 18}, new int[]{R2.dimen.libpaipaireplacemodel_paipai_space_width_225, 19}, new int[]{R2.attr.stl_indicatorCornerRadius, 20}, new int[]{R2.attr.circleCrop, 21}, new int[]{R2.color.c_8F000000, 22}, new int[]{R2.attr.title_color, 23}, new int[]{R2.id.included, 24}, new int[]{R2.id.attr_tag_container, 25}, new int[]{R2.id.share_login_title, 26}, new int[]{R2.id.mtrl_picker_header_toggle, 27}, new int[]{R2.drawable.label_06, 28}, new int[]{R2.drawable.common_scan_selector, 29}, new int[]{R2.drawable.wxa_progress_large_holo, 30}, new int[]{R2.drawable.ssl_dialog_bg, 31}};
    private static final int[] BITS_SET_IN_HALF_BYTE = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    private FormatInformation(int i2) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits((i2 >> 3) & 3);
        this.dataMask = (byte) (i2 & 7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormatInformation decodeFormatInformation(int i2, int i3) {
        FormatInformation doDecodeFormatInformation = doDecodeFormatInformation(i2, i3);
        return doDecodeFormatInformation != null ? doDecodeFormatInformation : doDecodeFormatInformation(i2 ^ 21522, i3 ^ 21522);
    }

    private static FormatInformation doDecodeFormatInformation(int i2, int i3) {
        int numBitsDiffering;
        int i4 = Integer.MAX_VALUE;
        int i5 = 0;
        for (int[] iArr : FORMAT_INFO_DECODE_LOOKUP) {
            int i6 = iArr[0];
            if (i6 != i2 && i6 != i3) {
                int numBitsDiffering2 = numBitsDiffering(i2, i6);
                if (numBitsDiffering2 < i4) {
                    i5 = iArr[1];
                    i4 = numBitsDiffering2;
                }
                if (i2 != i3 && (numBitsDiffering = numBitsDiffering(i3, i6)) < i4) {
                    i5 = iArr[1];
                    i4 = numBitsDiffering;
                }
            } else {
                return new FormatInformation(iArr[1]);
            }
        }
        if (i4 <= 3) {
            return new FormatInformation(i5);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int numBitsDiffering(int i2, int i3) {
        int i4 = i2 ^ i3;
        int[] iArr = BITS_SET_IN_HALF_BYTE;
        return iArr[i4 & 15] + iArr[(i4 >>> 4) & 15] + iArr[(i4 >>> 8) & 15] + iArr[(i4 >>> 12) & 15] + iArr[(i4 >>> 16) & 15] + iArr[(i4 >>> 20) & 15] + iArr[(i4 >>> 24) & 15] + iArr[(i4 >>> 28) & 15];
    }

    public boolean equals(Object obj) {
        if (obj instanceof FormatInformation) {
            FormatInformation formatInformation = (FormatInformation) obj;
            return this.errorCorrectionLevel == formatInformation.errorCorrectionLevel && this.dataMask == formatInformation.dataMask;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte getDataMask() {
        return this.dataMask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public int hashCode() {
        return (this.errorCorrectionLevel.ordinal() << 3) | this.dataMask;
    }
}
