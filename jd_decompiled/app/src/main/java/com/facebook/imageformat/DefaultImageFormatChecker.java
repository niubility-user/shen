package com.facebook.imageformat;

import com.coremedia.iso.boxes.FileTypeBox;
import com.facebook.common.internal.Ints;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.ImageFormat;
import javax.annotation.Nullable;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes.dex */
public class DefaultImageFormatChecker implements ImageFormat.FormatChecker {
    private static final byte[] BMP_HEADER;
    private static final int BMP_HEADER_LENGTH;
    private static final byte[] DNG_HEADER_II;
    private static final int DNG_HEADER_LENGTH;
    private static final byte[] DNG_HEADER_MM;
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int GIF_HEADER_LENGTH = 6;
    private static final int HEIF_HEADER_LENGTH = 12;
    private static final byte[] HEIF_HEADER_PREFIX;
    private static final byte[][] HEIF_HEADER_SUFFIXES;
    private static final byte[] ICO_HEADER;
    private static final int ICO_HEADER_LENGTH;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    final int MAX_HEADER_LENGTH = Ints.max(21, 20, JPEG_HEADER_LENGTH, PNG_HEADER_LENGTH, 6, BMP_HEADER_LENGTH, ICO_HEADER_LENGTH, 12);
    private static final byte[] JPEG_HEADER = {-1, -40, -1};
    private static final int JPEG_HEADER_LENGTH = 3;
    private static final byte[] PNG_HEADER = {ReplyCode.reply0x89, 80, 78, 71, 13, 10, 26, 10};
    private static final int PNG_HEADER_LENGTH = 8;
    private static final byte[] GIF_HEADER_87A = ImageFormatCheckerUtils.asciiBytes("GIF87a");
    private static final byte[] GIF_HEADER_89A = ImageFormatCheckerUtils.asciiBytes("GIF89a");

    static {
        byte[] asciiBytes = ImageFormatCheckerUtils.asciiBytes("BM");
        BMP_HEADER = asciiBytes;
        BMP_HEADER_LENGTH = asciiBytes.length;
        ICO_HEADER = new byte[]{0, 0, 1, 0};
        ICO_HEADER_LENGTH = 4;
        HEIF_HEADER_PREFIX = ImageFormatCheckerUtils.asciiBytes(FileTypeBox.TYPE);
        HEIF_HEADER_SUFFIXES = new byte[][]{ImageFormatCheckerUtils.asciiBytes("heic"), ImageFormatCheckerUtils.asciiBytes("heix"), ImageFormatCheckerUtils.asciiBytes("hevc"), ImageFormatCheckerUtils.asciiBytes("hevx"), ImageFormatCheckerUtils.asciiBytes("mif1"), ImageFormatCheckerUtils.asciiBytes("msf1")};
        DNG_HEADER_II = new byte[]{73, 73, 42, 0};
        DNG_HEADER_MM = new byte[]{77, 77, 0, 42};
        DNG_HEADER_LENGTH = 4;
    }

    private static ImageFormat getWebpFormat(byte[] bArr, int i2) {
        Preconditions.checkArgument(WebpSupportStatus.isWebpHeader(bArr, 0, i2));
        return WebpSupportStatus.isSimpleWebpHeader(bArr, 0) ? DefaultImageFormats.WEBP_SIMPLE : WebpSupportStatus.isLosslessWebpHeader(bArr, 0) ? DefaultImageFormats.WEBP_LOSSLESS : WebpSupportStatus.isExtendedWebpHeader(bArr, 0, i2) ? WebpSupportStatus.isAnimatedWebpHeader(bArr, 0) ? DefaultImageFormats.WEBP_ANIMATED : WebpSupportStatus.isExtendedWebpHeaderWithAlpha(bArr, 0) ? DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA : DefaultImageFormats.WEBP_EXTENDED : ImageFormat.UNKNOWN;
    }

    private static boolean isBmpHeader(byte[] bArr, int i2) {
        byte[] bArr2 = BMP_HEADER;
        if (i2 < bArr2.length) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isDngHeader(byte[] bArr, int i2) {
        return i2 >= DNG_HEADER_LENGTH && (ImageFormatCheckerUtils.startsWithPattern(bArr, DNG_HEADER_II) || ImageFormatCheckerUtils.startsWithPattern(bArr, DNG_HEADER_MM));
    }

    private static boolean isGifHeader(byte[] bArr, int i2) {
        if (i2 < 6) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(bArr, GIF_HEADER_87A) || ImageFormatCheckerUtils.startsWithPattern(bArr, GIF_HEADER_89A);
    }

    private static boolean isHeifHeader(byte[] bArr, int i2) {
        if (i2 >= 12 && bArr[3] >= 8 && ImageFormatCheckerUtils.hasPatternAt(bArr, HEIF_HEADER_PREFIX, 4)) {
            for (byte[] bArr2 : HEIF_HEADER_SUFFIXES) {
                if (ImageFormatCheckerUtils.hasPatternAt(bArr, bArr2, 8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean isIcoHeader(byte[] bArr, int i2) {
        byte[] bArr2 = ICO_HEADER;
        if (i2 < bArr2.length) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isJpegHeader(byte[] bArr, int i2) {
        byte[] bArr2 = JPEG_HEADER;
        return i2 >= bArr2.length && ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isPngHeader(byte[] bArr, int i2) {
        byte[] bArr2 = PNG_HEADER;
        return i2 >= bArr2.length && ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    @Nullable
    public final ImageFormat determineFormat(byte[] bArr, int i2) {
        Preconditions.checkNotNull(bArr);
        return WebpSupportStatus.isWebpHeader(bArr, 0, i2) ? getWebpFormat(bArr, i2) : isJpegHeader(bArr, i2) ? DefaultImageFormats.JPEG : isPngHeader(bArr, i2) ? DefaultImageFormats.PNG : isGifHeader(bArr, i2) ? DefaultImageFormats.GIF : isBmpHeader(bArr, i2) ? DefaultImageFormats.BMP : isIcoHeader(bArr, i2) ? DefaultImageFormats.ICO : isHeifHeader(bArr, i2) ? DefaultImageFormats.HEIF : isDngHeader(bArr, i2) ? DefaultImageFormats.DNG : ImageFormat.UNKNOWN;
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    public int getHeaderSize() {
        return this.MAX_HEADER_LENGTH;
    }
}
