package com.jdpay.membercode.f;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;

/* loaded from: classes18.dex */
public class b {
    private static int a(int[] iArr, int i2, BitMatrix bitMatrix, boolean z, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        if (iArr != null) {
            if (iArr.length != 0) {
                if (i2 > 0) {
                    if (bitMatrix != null) {
                        if (bitMatrix.getWidth() == bitMatrix.getHeight()) {
                            BitMatrix bitMatrix2 = !z ? bitMatrix : new BitMatrix(261, 261);
                            int width = bitMatrix.getWidth();
                            if (bitMatrix2 != bitMatrix) {
                                int width2 = bitMatrix.getWidth() + (i3 * 2);
                                int width3 = (bitMatrix2.getWidth() - width2) / 2;
                                int i11 = width3 + width2;
                                for (int i12 = 1; i12 <= i3; i12++) {
                                    int i13 = (width3 + i12) - 1;
                                    int i14 = i13;
                                    while (true) {
                                        i10 = i11 - i12;
                                        if (i14 > i10) {
                                            break;
                                        }
                                        bitMatrix2.set(i13, i14);
                                        bitMatrix2.set(i10, i14);
                                        i14++;
                                    }
                                    for (int i15 = i13; i15 <= i10; i15++) {
                                        bitMatrix2.set(i15, i13);
                                        bitMatrix2.set(i15, i10);
                                    }
                                }
                                int i16 = width3 + i3;
                                int i17 = i16;
                                while (true) {
                                    int i18 = i11 - i3;
                                    if (i17 >= i18) {
                                        break;
                                    }
                                    for (int i19 = i16; i19 < i18; i19++) {
                                        bitMatrix2.set(i19, i17);
                                    }
                                    i17++;
                                }
                                i4 = width2;
                                i6 = width3;
                                i7 = i6;
                                i5 = i11;
                            } else {
                                i4 = width;
                                i5 = i4;
                                i6 = 0;
                                i7 = 0;
                            }
                            int width4 = bitMatrix2.getWidth();
                            int i20 = (i2 - (z ? i3 * 2 : 0)) / width4;
                            int i21 = iArr[0];
                            int i22 = (i2 - (width4 * i20)) / 2;
                            if (bitMatrix2 == bitMatrix) {
                                int i23 = i22;
                                int i24 = 0;
                                while (i24 < width4) {
                                    int i25 = i22;
                                    int i26 = 0;
                                    while (i26 < width4) {
                                        if (bitMatrix2.get(i26, i24)) {
                                            g(iArr, i2, i25, i23, i20, i20);
                                        }
                                        i26++;
                                        i25 += i20;
                                    }
                                    i24++;
                                    i23 += i20;
                                }
                            } else {
                                int i27 = i22;
                                int i28 = 0;
                                while (i28 < width4) {
                                    int i29 = i22;
                                    int i30 = 0;
                                    while (i30 < width4) {
                                        if (i30 < i6 || i30 >= i5 || i28 < i7 || i28 >= i5) {
                                            i8 = i30;
                                            i9 = i28;
                                            if (bitMatrix2.get(i8, i9)) {
                                                h(iArr, i2, i29, i27, i20, i20, true, i21);
                                            }
                                        } else if (bitMatrix2.get(i30, i28)) {
                                            i8 = i30;
                                            i9 = i28;
                                            h(iArr, i2, i29, i27, i20, i20, false, i21);
                                        } else {
                                            i8 = i30;
                                            i9 = i28;
                                            i(iArr, i2, i29, i27, i20, i20);
                                        }
                                        i30 = i8 + 1;
                                        i29 += i20;
                                        i28 = i9;
                                    }
                                    i28++;
                                    i27 += i20;
                                }
                            }
                            return i4 * i20;
                        }
                        throw new RuntimeException("corematrix's width must equal to height");
                    }
                    throw new IllegalArgumentException("qrmatrix must not be null");
                }
                throw new IllegalArgumentException("size of background must be greater than 0");
            }
            throw new IllegalArgumentException("length of background must be longer than 0");
        }
        throw new IllegalArgumentException("background must not be null");
    }

    public static Bitmap b(String str, int i2, int i3) {
        return d(str, BarcodeFormat.CODE_128, i2, i3);
    }

    public static Bitmap c(String str, int i2, int i3, Bitmap bitmap, Bitmap bitmap2, int i4) {
        BitMatrix e2 = e(str, i2, i3, i4);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.RGB_565);
        createBitmap.eraseColor(-1);
        int height = createBitmap.getHeight();
        int width = createBitmap.getWidth();
        int[] iArr = new int[height * width];
        createBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int a = a(iArr, width, e2, false, 40);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        if (bitmap2 != null) {
            f(createBitmap, bitmap2, (a >> 4) * 3);
        }
        return createBitmap;
    }

    protected static Bitmap d(String str, BarcodeFormat barcodeFormat, int i2, int i3) {
        BitMatrix encode = new MultiFormatWriter().encode(str, barcodeFormat, i2, i3, null);
        int width = encode.getWidth();
        int height = encode.getHeight();
        int[] iArr = new int[width * height];
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = i4 * width;
            for (int i6 = 0; i6 < width; i6++) {
                iArr[i5 + i6] = encode.get(i6, i4) ? -16777216 : -1;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    private static BitMatrix e(String str, int i2, int i3, int i4) {
        QRCodeWriter qRCodeWriter = new QRCodeWriter();
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix encode = qRCodeWriter.encode(str, BarcodeFormat.QR_CODE, i2, i3, hashMap);
        if (encode.getWidth() == encode.getHeight()) {
            return encode;
        }
        throw new RuntimeException("corematrix's width must equal to height");
    }

    private static void f(Bitmap bitmap, Bitmap bitmap2, int i2) {
        if (bitmap == null || bitmap2 == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        int abs = Math.abs(width - i2) / 2;
        int abs2 = Math.abs(height - i2) / 2;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Canvas canvas = new Canvas(bitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.drawBitmap(bitmap2, new Rect(0, 0, width2, height2), new Rect(abs, abs2, abs + i2, i2 + abs2), paint);
        canvas.save();
        canvas.restore();
    }

    private static void g(int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i5 + i3;
        int i8 = i6 + i4;
        while (i4 < i8) {
            for (int i9 = i3; i9 < i7; i9++) {
                iArr[(i4 * i2) + i9] = -16777216;
            }
            i4++;
        }
    }

    private static void h(int[] iArr, int i2, int i3, int i4, int i5, int i6, boolean z, int i7) {
        int i8 = i5 + i3;
        int i9 = i6 + i4;
        if (z) {
            boolean z2 = true;
            for (int i10 = i4; i10 < i9; i10++) {
                int i11 = i3;
                while (true) {
                    if (i11 >= i8) {
                        break;
                    } else if (i7 != iArr[(i10 * i2) + i11]) {
                        z2 = false;
                        break;
                    } else {
                        i11++;
                    }
                }
            }
            if (z2) {
                return;
            }
        }
        while (i4 < i9) {
            for (int i12 = i3; i12 < i8; i12++) {
                int i13 = (i4 * i2) + i12;
                int i14 = iArr[i13];
                if (((i14 >> 16) & 255) + ((i14 >> 8) & 255) + (i14 & 255) > 192) {
                    iArr[i13] = -16777216;
                }
            }
            i4++;
        }
    }

    private static void i(int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i5 + i3;
        int i8 = i6 + i4;
        while (i4 < i8) {
            for (int i9 = i3; i9 < i7; i9++) {
                int i10 = (i4 * i2) + i9;
                int i11 = iArr[i10];
                if (((i11 >> 16) & 255) + ((i11 >> 8) & 255) + (i11 & 255) < 384) {
                    iArr[i10] = -1;
                }
            }
            i4++;
        }
    }
}
