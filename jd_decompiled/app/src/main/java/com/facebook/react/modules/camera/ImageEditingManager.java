package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import cn.com.union.fido.common.MIMEType;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import tv.danmaku.ijk.media.player.IMediaPlayer;

@ReactModule(name = ImageEditingManager.NAME)
/* loaded from: classes12.dex */
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    protected static final String NAME = "ImageEditingManager";
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList("file://", "content://");
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = {"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};

    /* loaded from: classes12.dex */
    private static class CleanTask extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;

        private void cleanDirectory(File file) {
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.facebook.react.modules.camera.ImageEditingManager.CleanTask.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.startsWith(ImageEditingManager.TEMP_FILE_PREFIX);
                }
            });
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    file2.delete();
                }
            }
        }

        private CleanTask(ReactContext reactContext) {
            super(reactContext);
            this.mContext = reactContext;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            cleanDirectory(this.mContext.getCacheDir());
            File externalCacheDir = this.mContext.getExternalCacheDir();
            if (externalCacheDir != null) {
                cleanDirectory(externalCacheDir);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class CropTask extends GuardedAsyncTask<Void, Void> {
        final Context mContext;
        final Callback mError;
        final int mHeight;
        final Callback mSuccess;
        int mTargetHeight;
        int mTargetWidth;
        final String mUri;
        final int mWidth;
        final int mX;
        final int mY;

        private Bitmap crop(BitmapFactory.Options options) throws IOException {
            InputStream openBitmapInputStream = openBitmapInputStream();
            BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(openBitmapInputStream, false);
            try {
                int i2 = this.mX;
                int i3 = this.mY;
                return newInstance.decodeRegion(new Rect(i2, i3, this.mWidth + i2, this.mHeight + i3), options);
            } finally {
                if (openBitmapInputStream != null) {
                    openBitmapInputStream.close();
                }
                newInstance.recycle();
            }
        }

        private Bitmap cropAndResize(int i2, int i3, BitmapFactory.Options options) throws IOException {
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            Assertions.assertNotNull(options);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            InputStream openBitmapInputStream = openBitmapInputStream();
            try {
                BitmapFactory.decodeStream(openBitmapInputStream, null, options2);
                if (openBitmapInputStream != null) {
                    openBitmapInputStream.close();
                }
                int i4 = this.mWidth;
                int i5 = this.mHeight;
                float f7 = i2;
                float f8 = i3;
                float f9 = f7 / f8;
                if (i4 / i5 > f9) {
                    f2 = i5 * f9;
                    f6 = i5;
                    f3 = this.mX + ((i4 - f2) / 2.0f);
                    f4 = this.mY;
                    f5 = f8 / i5;
                } else {
                    f2 = i4;
                    float f10 = i4 / f9;
                    f3 = this.mX;
                    f4 = this.mY + ((i5 - f10) / 2.0f);
                    f5 = f7 / i4;
                    f6 = f10;
                }
                options.inSampleSize = ImageEditingManager.getDecodeSampleSize(i4, i5, i2, i3);
                options2.inJustDecodeBounds = false;
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(openBitmapInputStream(), null, options);
                    if (decodeStream != null) {
                        float f11 = f5 * options.inSampleSize;
                        Matrix matrix = new Matrix();
                        matrix.setScale(f11, f11);
                        return Bitmap.createBitmap(decodeStream, (int) Math.floor(f3 / options.inSampleSize), (int) Math.floor(f4 / options.inSampleSize), (int) Math.floor(f2 / options.inSampleSize), (int) Math.floor(f6 / options.inSampleSize), matrix, true);
                    }
                    throw new IOException("Cannot decode bitmap: " + this.mUri);
                } finally {
                }
            } finally {
                if (openBitmapInputStream != null) {
                    openBitmapInputStream.close();
                }
            }
        }

        private InputStream openBitmapInputStream() throws IOException {
            InputStream inputStream;
            if (ImageEditingManager.isLocalUri(this.mUri)) {
                inputStream = this.mContext.getContentResolver().openInputStream(Uri.parse(this.mUri));
            } else {
                inputStream = new URL(this.mUri).openConnection().getInputStream();
            }
            if (inputStream != null) {
                return inputStream;
            }
            throw new IOException("Cannot open bitmap: " + this.mUri);
        }

        public void setTargetSize(int i2, int i3) {
            if (i2 > 0 && i3 > 0) {
                this.mTargetWidth = i2;
                this.mTargetHeight = i3;
                return;
            }
            throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", Integer.valueOf(i2), Integer.valueOf(i3)));
        }

        private CropTask(ReactContext reactContext, String str, int i2, int i3, int i4, int i5, Callback callback, Callback callback2) {
            super(reactContext);
            this.mTargetWidth = 0;
            this.mTargetHeight = 0;
            if (i2 >= 0 && i3 >= 0 && i4 > 0 && i5 > 0) {
                this.mContext = reactContext;
                this.mUri = str;
                this.mX = i2;
                this.mY = i3;
                this.mWidth = i4;
                this.mHeight = i5;
                this.mSuccess = callback;
                this.mError = callback2;
                return;
            }
            throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            Bitmap crop;
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i2 = this.mTargetWidth;
                if (i2 > 0 && this.mTargetHeight > 0) {
                    crop = cropAndResize(i2, this.mTargetHeight, options);
                } else {
                    crop = crop(options);
                }
                String str = options.outMimeType;
                if (str != null && !str.isEmpty()) {
                    File createTempFile = ImageEditingManager.createTempFile(this.mContext, str);
                    ImageEditingManager.writeCompressedBitmapToFile(crop, str, createTempFile);
                    if (str.equals(MIMEType.MIME_TYPE_JPEG)) {
                        ImageEditingManager.copyExif(this.mContext, Uri.parse(this.mUri), createTempFile);
                    }
                    this.mSuccess.invoke(Uri.fromFile(createTempFile).toString());
                    return;
                }
                throw new IOException("Could not determine MIME type");
            } catch (Exception e2) {
                this.mError.invoke(e2.getMessage());
            }
        }
    }

    public ImageEditingManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void copyExif(Context context, Uri uri, File file) throws IOException {
        File fileFromUri = getFileFromUri(context, uri);
        if (fileFromUri == null) {
            FLog.w(ReactConstants.TAG, "Couldn't get real path for uri: " + uri);
            return;
        }
        ExifInterface exifInterface = new ExifInterface(fileFromUri.getAbsolutePath());
        ExifInterface exifInterface2 = new ExifInterface(file.getAbsolutePath());
        for (String str : EXIF_ATTRIBUTES) {
            String attribute = exifInterface.getAttribute(str);
            if (attribute != null) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.saveAttributes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File createTempFile(Context context, @Nullable String str) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File cacheDir = context.getCacheDir();
        if (externalCacheDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        }
        if (externalCacheDir == null || (cacheDir != null && externalCacheDir.getFreeSpace() <= cacheDir.getFreeSpace())) {
            externalCacheDir = cacheDir;
        }
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(str), externalCacheDir);
    }

    private static Bitmap.CompressFormat getCompressFormatForType(String str) {
        if (MIMEType.MIME_TYPE_PNG.equals(str)) {
            return Bitmap.CompressFormat.PNG;
        }
        if ("image/webp".equals(str)) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDecodeSampleSize(int i2, int i3, int i4, int i5) {
        int i6 = 1;
        if (i3 > i4 || i2 > i5) {
            int i7 = i3 / 2;
            int i8 = i2 / 2;
            while (i8 / i6 >= i4 && i7 / i6 >= i5) {
                i6 *= 2;
            }
        }
        return i6;
    }

    private static String getFileExtensionForType(@Nullable String str) {
        return MIMEType.MIME_TYPE_PNG.equals(str) ? ".png" : "image/webp".equals(str) ? ".webp" : ".jpg";
    }

    @Nullable
    private static File getFileFromUri(Context context, Uri uri) {
        Cursor query;
        if (uri.getScheme().equals("file")) {
            return new File(uri.getPath());
        }
        if (!uri.getScheme().equals("content") || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        try {
            if (query.moveToFirst()) {
                String string = query.getString(0);
                if (!TextUtils.isEmpty(string)) {
                    return new File(string);
                }
            }
            return null;
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isLocalUri(String str) {
        Iterator<String> it = LOCAL_URI_PREFIXES.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeCompressedBitmapToFile(Bitmap bitmap, String str, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(getCompressFormatForType(str), 90, fileOutputStream);
        } finally {
            fileOutputStream.close();
        }
    }

    @ReactMethod
    public void cropImage(String str, ReadableMap readableMap, Callback callback, Callback callback2) {
        ReadableMap map = readableMap.hasKey(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET) ? readableMap.getMap(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET) : null;
        ReadableMap map2 = readableMap.hasKey(ApkDownloadTable.FIELD_SIZE) ? readableMap.getMap(ApkDownloadTable.FIELD_SIZE) : null;
        if (map != null && map2 != null && map.hasKey(JshopConst.JSHOP_PROMOTIO_X) && map.hasKey(JshopConst.JSHOP_PROMOTIO_Y) && map2.hasKey("width") && map2.hasKey("height")) {
            if (str != null && !str.isEmpty()) {
                CropTask cropTask = new CropTask(getReactApplicationContext(), str, (int) map.getDouble(JshopConst.JSHOP_PROMOTIO_X), (int) map.getDouble(JshopConst.JSHOP_PROMOTIO_Y), (int) map2.getDouble("width"), (int) map2.getDouble("height"), callback, callback2);
                if (readableMap.hasKey("displaySize")) {
                    ReadableMap map3 = readableMap.getMap("displaySize");
                    cropTask.setTargetSize((int) map3.getDouble("width"), (int) map3.getDouble("height"));
                }
                cropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Please specify a URI");
        }
        throw new JSApplicationIllegalArgumentException("Please specify offset and size");
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
