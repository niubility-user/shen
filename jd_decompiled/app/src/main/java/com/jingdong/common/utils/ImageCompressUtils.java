package com.jingdong.common.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes6.dex */
public class ImageCompressUtils {
    static final int LONG_LIMIT = 1280;
    static final int SHORT_LIMIT = 128;
    private static final String TAG = "ImageCompressUtils";

    /* loaded from: classes6.dex */
    public static class TargetImageInfo {
        public byte[] data;
        public int targetHeight;
        public int targetWidth;
        public boolean valid;
    }

    private static TargetImageInfo calculateInSampleSize(BitmapFactory.Options options) {
        int i2;
        TargetImageInfo targetImageInfo = new TargetImageInfo();
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        targetImageInfo.targetWidth = i3;
        targetImageInfo.targetHeight = i4;
        if (i3 > i4) {
            i2 = i3;
        } else {
            i2 = i4;
            i4 = i3;
        }
        if (i4 < 128) {
            targetImageInfo.valid = false;
        } else if (i2 > 1280) {
            float f2 = i2 / 1280.0f;
            if (i2 == i3) {
                targetImageInfo.targetWidth = 1280;
                int i5 = (int) (i4 / f2);
                targetImageInfo.targetHeight = i5;
                if (i5 < 128) {
                    targetImageInfo.valid = false;
                }
            } else {
                targetImageInfo.targetHeight = 1280;
                int i6 = (int) (i4 / f2);
                targetImageInfo.targetWidth = i6;
                if (i6 < 128) {
                    targetImageInfo.valid = false;
                }
            }
        }
        targetImageInfo.valid = true;
        return targetImageInfo;
    }

    @RequiresApi(api = 24)
    public static TargetImageInfo compressImage(FileDescriptor fileDescriptor) {
        Bitmap decodeSampledBitmapFromFileDescriptor;
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        TargetImageInfo calculateInSampleSize = calculateInSampleSize(options);
        if (!calculateInSampleSize.valid || (decodeSampledBitmapFromFileDescriptor = decodeSampledBitmapFromFileDescriptor(options, fileDescriptor, calculateInSampleSize.targetWidth, calculateInSampleSize.targetHeight)) == null) {
            return null;
        }
        calculateInSampleSize.targetWidth = decodeSampledBitmapFromFileDescriptor.getWidth();
        int height = decodeSampledBitmapFromFileDescriptor.getHeight();
        calculateInSampleSize.targetHeight = height;
        int i2 = calculateInSampleSize.targetWidth;
        if (i2 <= height) {
            height = i2;
        }
        if (height < 128) {
            return null;
        }
        int readPictureDegree = readPictureDegree(fileDescriptor);
        if (readPictureDegree != 0) {
            Matrix matrix = new Matrix();
            matrix.setRotate(readPictureDegree);
            decodeSampledBitmapFromFileDescriptor = Bitmap.createBitmap(decodeSampledBitmapFromFileDescriptor, 0, 0, decodeSampledBitmapFromFileDescriptor.getWidth(), decodeSampledBitmapFromFileDescriptor.getHeight(), matrix, true);
        }
        int parseInt = Integer.parseInt(Configuration.getProperty(Configuration.DISCUSSUPLOADIMAGE_QUALITY));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeSampledBitmapFromFileDescriptor.compress(Bitmap.CompressFormat.JPEG, parseInt, byteArrayOutputStream);
        calculateInSampleSize.data = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            if (decodeSampledBitmapFromFileDescriptor != null && !decodeSampledBitmapFromFileDescriptor.isRecycled()) {
                decodeSampledBitmapFromFileDescriptor.recycle();
            }
        } catch (IOException e2) {
            OKLog.e("ImageCompressUtils", e2);
        }
        return calculateInSampleSize;
    }

    private static Bitmap decodeSampledBitmapFromFile(BitmapFactory.Options options, String str, int i2, int i3) {
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private static Bitmap decodeSampledBitmapFromFileDescriptor(BitmapFactory.Options options, FileDescriptor fileDescriptor, int i2, int i3) {
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    private static Bitmap decodeSampledBitmapFromUri(BitmapFactory.Options options, InputStream inputStream, int i2, int i3) {
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    private static ExifInterface getExifInterface(String str) {
        try {
            return new ExifInterface(str);
        } catch (IOException e2) {
            OKLog.e("ImageCompressUtils", e2);
            return null;
        }
    }

    private static int getExitDegree(ExifInterface exifInterface) {
        if (exifInterface != null) {
            int attributeInt = exifInterface.getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt == 8) {
                return 270;
            }
        }
        return 0;
    }

    private static int readPictureDegree(String str) {
        return getExitDegree(getExifInterface(str));
    }

    @RequiresApi(api = 24)
    private static ExifInterface getExifInterface(FileDescriptor fileDescriptor) {
        try {
            return new ExifInterface(fileDescriptor);
        } catch (IOException e2) {
            OKLog.e("ImageCompressUtils", e2);
            return null;
        }
    }

    @RequiresApi(api = 24)
    private static int readPictureDegree(FileDescriptor fileDescriptor) {
        return getExitDegree(getExifInterface(fileDescriptor));
    }

    private static int readPictureDegree(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, new String[]{MBaseKeyNames.KEY_ORIENTATION}, null, null, null);
        if (query != null) {
            r6 = query.moveToFirst() ? query.getInt(0) : 0;
            query.close();
        }
        return r6;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i2, int i3) {
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i3 || i5 > i2) {
            int ceil = (int) Math.ceil(i4 / i3);
            int ceil2 = (int) Math.ceil(i5 / i2);
            if (ceil >= ceil2) {
                ceil2 = ceil;
            }
            while (((ceil2 - 1) & ceil2) != 0) {
                ceil2++;
            }
            return ceil2;
        }
        return 1;
    }

    public static TargetImageInfo compressImage(String str) {
        Bitmap decodeSampledBitmapFromFile;
        if (!TextUtils.isEmpty(str)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            TargetImageInfo calculateInSampleSize = calculateInSampleSize(options);
            if (!calculateInSampleSize.valid || (decodeSampledBitmapFromFile = decodeSampledBitmapFromFile(options, str, calculateInSampleSize.targetWidth, calculateInSampleSize.targetHeight)) == null) {
                return null;
            }
            calculateInSampleSize.targetWidth = decodeSampledBitmapFromFile.getWidth();
            int height = decodeSampledBitmapFromFile.getHeight();
            calculateInSampleSize.targetHeight = height;
            int i2 = calculateInSampleSize.targetWidth;
            if (i2 <= height) {
                height = i2;
            }
            if (height < 128) {
                return null;
            }
            int readPictureDegree = readPictureDegree(str);
            if (readPictureDegree != 0) {
                Matrix matrix = new Matrix();
                matrix.setRotate(readPictureDegree);
                decodeSampledBitmapFromFile = Bitmap.createBitmap(decodeSampledBitmapFromFile, 0, 0, decodeSampledBitmapFromFile.getWidth(), decodeSampledBitmapFromFile.getHeight(), matrix, true);
            }
            int parseInt = Integer.parseInt(Configuration.getProperty(Configuration.DISCUSSUPLOADIMAGE_QUALITY));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeSampledBitmapFromFile.compress(Bitmap.CompressFormat.JPEG, parseInt, byteArrayOutputStream);
            calculateInSampleSize.data = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                if (decodeSampledBitmapFromFile != null && !decodeSampledBitmapFromFile.isRecycled()) {
                    decodeSampledBitmapFromFile.recycle();
                }
            } catch (IOException e2) {
                OKLog.e("ImageCompressUtils", e2);
            }
            return calculateInSampleSize;
        }
        return null;
    }

    public static TargetImageInfo compressImage(Context context, Uri uri) {
        InputStream inputStream;
        Bitmap decodeSampledBitmapFromUri;
        if (uri != null && context != null) {
            try {
                inputStream = context.getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                inputStream = null;
            }
            if (inputStream == null) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            TargetImageInfo calculateInSampleSize = calculateInSampleSize(options);
            if (!calculateInSampleSize.valid || (decodeSampledBitmapFromUri = decodeSampledBitmapFromUri(options, inputStream, calculateInSampleSize.targetWidth, calculateInSampleSize.targetHeight)) == null) {
                return null;
            }
            calculateInSampleSize.targetWidth = decodeSampledBitmapFromUri.getWidth();
            int height = decodeSampledBitmapFromUri.getHeight();
            calculateInSampleSize.targetHeight = height;
            int i2 = calculateInSampleSize.targetWidth;
            if (i2 <= height) {
                height = i2;
            }
            if (height < 128) {
                return null;
            }
            int readPictureDegree = readPictureDegree(context, uri);
            if (readPictureDegree != 0) {
                Matrix matrix = new Matrix();
                matrix.setRotate(readPictureDegree);
                decodeSampledBitmapFromUri = Bitmap.createBitmap(decodeSampledBitmapFromUri, 0, 0, decodeSampledBitmapFromUri.getWidth(), decodeSampledBitmapFromUri.getHeight(), matrix, true);
            }
            int parseInt = Integer.parseInt(Configuration.getProperty(Configuration.DISCUSSUPLOADIMAGE_QUALITY));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeSampledBitmapFromUri.compress(Bitmap.CompressFormat.JPEG, parseInt, byteArrayOutputStream);
            calculateInSampleSize.data = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                if (decodeSampledBitmapFromUri != null && !decodeSampledBitmapFromUri.isRecycled()) {
                    decodeSampledBitmapFromUri.recycle();
                }
            } catch (IOException e3) {
                OKLog.e("ImageCompressUtils", e3);
            }
            return calculateInSampleSize;
        }
        return null;
    }
}
