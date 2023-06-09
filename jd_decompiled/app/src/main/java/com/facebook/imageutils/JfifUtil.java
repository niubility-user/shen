package com.facebook.imageutils;

import com.facebook.common.internal.Preconditions;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private JfifUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int i2) {
        return TiffUtil.getAutoRotateAngleFromOrientation(i2);
    }

    public static int getOrientation(InputStream inputStream) {
        try {
            int moveToAPP1EXIF = moveToAPP1EXIF(inputStream);
            if (moveToAPP1EXIF == 0) {
                return 0;
            }
            return TiffUtil.readOrientationFromTIFF(inputStream, moveToAPP1EXIF);
        } catch (IOException unused) {
            return 0;
        }
    }

    public static int getOrientation(byte[] bArr) {
        return getOrientation(new ByteArrayInputStream(bArr));
    }

    private static boolean isSOFn(int i2) {
        switch (i2) {
            case 192:
            case R2.anim.slide_out_from_left /* 193 */:
            case R2.anim.slide_out_to_bottom /* 194 */:
            case R2.anim.slide_out_to_bottom_medium_time /* 195 */:
            case R2.anim.slide_out_to_top /* 197 */:
            case R2.anim.slide_out_to_top_medium_time /* 198 */:
            case R2.anim.slide_out_top_dongdong /* 199 */:
            case 201:
            case 202:
            case 203:
            case 205:
            case 206:
            case 207:
                return true;
            case R2.anim.slide_out_to_right /* 196 */:
            case 200:
            case 204:
            default:
                return false;
        }
    }

    private static int moveToAPP1EXIF(InputStream inputStream) {
        int readPackedInt;
        if (moveToMarker(inputStream, 225) && (readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, false) - 2) > 6) {
            int readPackedInt2 = StreamProcessor.readPackedInt(inputStream, 4, false);
            int readPackedInt3 = StreamProcessor.readPackedInt(inputStream, 2, false);
            int i2 = (readPackedInt - 4) - 2;
            if (readPackedInt2 == 1165519206 && readPackedInt3 == 0) {
                return i2;
            }
        }
        return 0;
    }

    public static boolean moveToMarker(InputStream inputStream, int i2) {
        Preconditions.checkNotNull(inputStream);
        while (StreamProcessor.readPackedInt(inputStream, 1, false) == 255) {
            int i3 = 255;
            while (i3 == 255) {
                i3 = StreamProcessor.readPackedInt(inputStream, 1, false);
            }
            if ((i2 != 192 || !isSOFn(i3)) && i3 != i2) {
                if (i3 != 216 && i3 != 1) {
                    if (i3 == 217 || i3 == 218) {
                        break;
                    }
                    inputStream.skip(StreamProcessor.readPackedInt(inputStream, 2, false) - 2);
                }
            } else {
                return true;
            }
        }
        return false;
    }
}
