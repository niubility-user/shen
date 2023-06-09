package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import java.io.InputStream;

/* loaded from: classes.dex */
class TiffUtil {
    private static final Class<?> TAG = TiffUtil.class;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TiffHeader {
        int byteOrder;
        int firstIfdOffset;
        boolean isLittleEndian;

        private TiffHeader() {
        }
    }

    TiffUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int i2) {
        if (i2 != 3) {
            if (i2 != 6) {
                return i2 != 8 ? 0 : 270;
            }
            return 90;
        }
        return 180;
    }

    private static int getOrientationFromTiffEntry(InputStream inputStream, int i2, boolean z) {
        if (i2 >= 10 && StreamProcessor.readPackedInt(inputStream, 2, z) == 3 && StreamProcessor.readPackedInt(inputStream, 4, z) == 1) {
            int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, z);
            StreamProcessor.readPackedInt(inputStream, 2, z);
            return readPackedInt;
        }
        return 0;
    }

    private static int moveToTiffEntryWithTag(InputStream inputStream, int i2, boolean z, int i3) {
        if (i2 < 14) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, z);
        int i4 = i2 - 2;
        while (true) {
            int i5 = readPackedInt - 1;
            if (readPackedInt <= 0 || i4 < 12) {
                break;
            }
            int i6 = i4 - 2;
            if (StreamProcessor.readPackedInt(inputStream, 2, z) == i3) {
                return i6;
            }
            inputStream.skip(10L);
            i4 = i6 - 10;
            readPackedInt = i5;
        }
        return 0;
    }

    public static int readOrientationFromTIFF(InputStream inputStream, int i2) {
        TiffHeader tiffHeader = new TiffHeader();
        int readTiffHeader = readTiffHeader(inputStream, i2, tiffHeader);
        int i3 = tiffHeader.firstIfdOffset - 8;
        if (readTiffHeader == 0 || i3 > readTiffHeader) {
            return 0;
        }
        inputStream.skip(i3);
        return getOrientationFromTiffEntry(inputStream, moveToTiffEntryWithTag(inputStream, readTiffHeader - i3, tiffHeader.isLittleEndian, 274), tiffHeader.isLittleEndian);
    }

    private static int readTiffHeader(InputStream inputStream, int i2, TiffHeader tiffHeader) {
        Class<?> cls;
        String str;
        if (i2 <= 8) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(inputStream, 4, false);
        tiffHeader.byteOrder = readPackedInt;
        int i3 = i2 - 4;
        if (readPackedInt == 1229531648 || readPackedInt == 1296891946) {
            boolean z = readPackedInt == 1229531648;
            tiffHeader.isLittleEndian = z;
            int readPackedInt2 = StreamProcessor.readPackedInt(inputStream, 4, z);
            tiffHeader.firstIfdOffset = readPackedInt2;
            int i4 = i3 - 4;
            if (readPackedInt2 >= 8 && readPackedInt2 - 8 <= i4) {
                return i4;
            }
            cls = TAG;
            str = "Invalid offset";
        } else {
            cls = TAG;
            str = "Invalid TIFF header";
        }
        FLog.e(cls, str);
        return 0;
    }
}
