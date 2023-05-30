package com.jingdong.common.xSupermarket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes12.dex */
public class ImageCompressUtils {
    public static final int DEFAULT_WIDTH = 500;
    public static final int MAX_QUALITY = 100;
    public static final int QUALITY_STEP = 10;
    private static int SHORT_LIMIT = 0;
    public static final String TAG = "ImageCompressUtils";
    private static volatile ImageCompressUtils mInstance;
    private byte[] bmpBytes;

    public static ImageCompressUtils getInstance() {
        if (mInstance == null) {
            synchronized (ImageCompressUtils.class) {
                if (mInstance == null) {
                    mInstance = new ImageCompressUtils();
                }
            }
        }
        return mInstance;
    }

    private float getSampleSize(int i2, int i3, int i4, int i5) {
        if (i2 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("the origin width or height should above 0 butwidth=" + i2 + "height=" + i3);
        } else if (i4 > 0 && i5 > 0) {
            if (i4 >= i2 || i5 >= i3) {
                return 1.0f;
            }
            float f2 = i3 / i5;
            float f3 = i2 / i4;
            OKLog.d(TAG, "getSampleSize()**heightRatio=" + f2 + "**widthRatio" + f3);
            return Math.max(f2, f3);
        } else if (i4 >= 0 || i5 >= 0) {
            if (i4 < 0) {
                float f4 = i3 / i5;
                OKLog.d(TAG, "getSampleSize()**heightRatio=" + f4);
                return Math.max(1.0f, f4);
            } else if (i5 < 0) {
                float f5 = i2 / i4;
                OKLog.d(TAG, "getSampleSize()**widthRatio" + f5);
                return Math.max(1.0f, f5);
            } else {
                return 1.0f;
            }
        } else {
            return 1.0f;
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int i2, int i3) {
        return (int) getSampleSize(options.outWidth, options.outHeight, i2, i3);
    }

    public void clearBytes() {
        this.bmpBytes = null;
    }

    public Bitmap compressAsBitmap(byte[] bArr, int i2) {
        return compressAsBitmap(bArr, i2, 500, -1);
    }

    public byte[] compressAsImage(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            return null;
        }
        Bitmap compressAsBitmap = compressAsBitmap(bArr, i2, 500, -1);
        this.bmpBytes = compressAsImage(compressAsBitmap, i3);
        if (!compressAsBitmap.isRecycled()) {
            compressAsBitmap.recycle();
        }
        return this.bmpBytes;
    }

    public byte[] compressAsImageAndSave(Context context, byte[] bArr, int i2, int i3) {
        byte[] compressAsImage = compressAsImage(bArr, i2, i3);
        ImageSaver.saveAsJpgImage(context, compressAsImage);
        return compressAsImage;
    }

    public String getBase64Bitmap() {
        byte[] bArr = this.bmpBytes;
        return Base64.encodeBytes(this.bmpBytes);
    }

    public Bitmap compressAsBitmap(byte[] bArr, int i2, int i3, int i4) {
        int i5;
        int i6;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        options.inSampleSize = calculateInSampleSize(options, i4, i3);
        String str = "inSampleSize=" + options.inSampleSize;
        options.inJustDecodeBounds = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int width = decodeByteArray.getWidth();
        int height = decodeByteArray.getHeight();
        Matrix matrix = new Matrix();
        if (i2 == 1) {
            matrix.setRotate(270.0f);
        } else {
            matrix.setRotate(90.0f);
        }
        if (height > width) {
            i6 = (height - width) / 2;
            i5 = 0;
        } else {
            i5 = (width - height) / 2;
            i6 = 0;
        }
        String str2 = "bmpHeight=" + height + "bmpWidth=" + width + "dy=" + i6 + "dx=" + i5;
        return Bitmap.createBitmap(decodeByteArray, i5, i6, width - (i5 * 2), height - (i6 * 2), matrix, true);
    }

    public byte[] compressAsImage(Bitmap bitmap, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (i2 <= 0) {
            return byteArray;
        }
        while (byteArrayOutputStream.toByteArray().length / 1024 > i2) {
            byteArrayOutputStream.reset();
            i3 -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        }
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.bmpBytes = byteArray;
        return byteArray;
    }

    /* loaded from: classes12.dex */
    static class ImageSaver {
        private static final String PIC_DIR = "pictures";
        public static final String TAG = "ImageSaver";

        ImageSaver() {
        }

        private static File createFile(File file, String str, String str2) {
            return new File(file, str + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + str2);
        }

        public static File getPictureFolder() {
            File file = new File(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jd"), PIC_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }

        public static String saveAsJpgImage(Context context, Bitmap bitmap, int i2) {
            File createFile = createFile(getPictureFolder(), "QUA_" + String.valueOf(i2) + "_IMG_", ".jpg");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createFile);
                boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(createFile)));
                if (compress) {
                    return createFile.getAbsolutePath();
                }
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static String saveAsJpgImage(Context context, byte[] bArr) {
            File createFile = createFile(getPictureFolder(), "_IMG_", ".jpg");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createFile);
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(createFile)));
                return createFile.getAbsolutePath();
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}
