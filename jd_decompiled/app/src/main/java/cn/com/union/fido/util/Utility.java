package cn.com.union.fido.util;

import android.content.Context;
import androidx.core.view.MotionEventCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class Utility {
    public static byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static int byteToInt(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr.length < i3 || (i4 = i2 + 3) >= i3) {
            return 0;
        }
        return (bArr[i2] & 255) + ((bArr[i2 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + ((bArr[i2 + 2] << 16) & 16711680) + ((bArr[i4] << 24) & (-16777216));
    }

    public static long byteToLong(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr.length < i3 || (i4 = i2 + 7) >= i3) {
            return 0L;
        }
        return ((bArr[i2 + 0] & 255) << 0) | ((bArr[i4] & 255) << 56) | ((bArr[i2 + 6] & 255) << 48) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8);
    }

    public static int byteToShort(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr.length < i3 || (i4 = i2 + 1) >= i3) {
            return 0;
        }
        return (bArr[i2] & 255) + ((bArr[i4] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    public static String byteToStr(byte[] bArr) {
        int length = bArr.length;
        if (bArr.length >= length) {
            int i2 = length + 0;
            byte[] bArr2 = new byte[i2];
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                bArr2[i4] = 0;
                i3++;
                i4++;
            }
            int length2 = bArr.length;
            int i5 = 0;
            for (int i6 = 0; i6 < length2; i6++) {
                if (i5 >= 0 && i5 < length) {
                    bArr2[i5 + 0] = bArr[i5];
                } else if (i5 >= length) {
                    break;
                }
                i5++;
            }
            return new String(bArr2);
        }
        return null;
    }

    public static String byteToStr(byte[] bArr, int i2, int i3) {
        if (bArr.length >= i3) {
            int i4 = i3 - i2;
            byte[] bArr2 = new byte[i4];
            int i5 = 0;
            int i6 = 0;
            while (i5 < i4) {
                bArr2[i6] = 0;
                i5++;
                i6++;
            }
            int length = bArr.length;
            int i7 = 0;
            for (int i8 = 0; i8 < length; i8++) {
                if (i7 >= i2 && i7 < i3) {
                    bArr2[i7 - i2] = bArr[i7];
                } else if (i7 >= i3) {
                    break;
                }
                i7++;
            }
            return new String(bArr2);
        }
        return null;
    }

    public static int byteToTiny(byte[] bArr, int i2, int i3) {
        if (bArr.length < i3 || i2 >= i3) {
            return 0;
        }
        return bArr[i2] & 255;
    }

    public static String bytes2HexString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0".concat(String.valueOf(hexString));
            }
            str = str + hexString.toUpperCase();
        }
        return str;
    }

    public static String bytes2String(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0".concat(String.valueOf(hexString));
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static void intToByte(byte[] bArr, int i2, int i3, int i4) {
        bArr[i2] = (byte) (i4 & 255);
        bArr[i2 + 1] = (byte) ((65280 & i4) >> 8);
        bArr[i2 + 2] = (byte) ((16711680 & i4) >> 16);
        bArr[i2 + 3] = (byte) (((-16777216) & i4) >> 24);
    }

    public static void longToByte(byte[] bArr, int i2, int i3, long j2) {
        bArr[i2] = (byte) (j2 >> 0);
        bArr[i2 + 1] = (byte) (j2 >> 8);
        bArr[i2 + 2] = (byte) (j2 >> 16);
        bArr[i2 + 3] = (byte) (j2 >> 24);
        bArr[i2 + 4] = (byte) (j2 >> 32);
        bArr[i2 + 5] = (byte) (j2 >> 40);
        bArr[i2 + 6] = (byte) (j2 >> 48);
        bArr[i2 + 7] = (byte) (j2 >> 56);
    }

    public static void readAssestToSD(Context context, String str) {
        try {
            if (new File(str).exists()) {
                return;
            }
            InputStream open = context.getResources().getAssets().open("fidoTable");
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[512];
            while (true) {
                int read = open.read(bArr);
                if (read <= 0) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    open.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void shortToByte(byte[] bArr, int i2, int i3, int i4) {
        bArr[i2] = (byte) (i4 & 255);
        bArr[i2 + 1] = (byte) ((65280 & i4) >> 8);
    }

    public static void strToByte(byte[] bArr, int i2, int i3, String str) {
        byte[] bytes = StringTools.isValidateString(str) ? str.getBytes() : null;
        if (bytes != null) {
            for (byte b : bytes) {
                bArr[i2] = b;
                i2++;
                if (i2 >= i3) {
                    return;
                }
            }
        }
    }

    public static byte[] strToByte(String str) {
        if (StringTools.isValidateString(str)) {
            return str.getBytes();
        }
        return null;
    }

    public static void tinyToByte(byte[] bArr, int i2, int i3, int i4) {
        bArr[i2] = (byte) (i4 & 255);
        bArr[i2 + 1] = (byte) ((65280 & i4) >> 8);
    }
}
