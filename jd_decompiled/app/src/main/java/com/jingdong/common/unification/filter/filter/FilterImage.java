package com.jingdong.common.unification.filter.filter;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.view.WindowManager;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.UnLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/* loaded from: classes6.dex */
public class FilterImage {
    private static final String TAG = "FilterImage";
    private final Context mContext;
    private Bitmap mCurrentBitmap;
    private CommonFilter mFilter;
    private GLSurfaceView mGlSurfaceView;
    private final CommonFilterRenderer mRenderer;
    private ScaleType mScaleType = ScaleType.CENTER_INSIDE;

    /* loaded from: classes6.dex */
    private class LoadImageFileTask extends LoadImageTask {
        private final File mImageFile;

        public LoadImageFileTask(FilterImage filterImage, File file) {
            super(filterImage);
            this.mImageFile = file;
        }

        @Override // com.jingdong.common.unification.filter.filter.FilterImage.LoadImageTask
        protected Bitmap decode(BitmapFactory.Options options) {
            return BitmapFactory.decodeFile(this.mImageFile.getAbsolutePath(), options);
        }

        @Override // com.jingdong.common.unification.filter.filter.FilterImage.LoadImageTask
        protected int getImageOrientation() throws IOException {
            int attributeInt = new ExifInterface(this.mImageFile.getAbsolutePath()).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        }
    }

    /* loaded from: classes6.dex */
    private abstract class LoadImageTask extends AsyncTask<Void, Void, Bitmap> {
        private final FilterImage mGPUImage;
        private int mOutputHeight;
        private int mOutputWidth;

        public LoadImageTask(FilterImage filterImage) {
            this.mGPUImage = filterImage;
        }

        private boolean checkSize(boolean z, boolean z2) {
            return FilterImage.this.mScaleType == ScaleType.CENTER_CROP ? z && z2 : z || z2;
        }

        private int[] getScaleSize(int i2, int i3) {
            float f2;
            float f3;
            float f4 = i2;
            float f5 = f4 / this.mOutputWidth;
            float f6 = i3;
            float f7 = f6 / this.mOutputHeight;
            if (FilterImage.this.mScaleType != ScaleType.CENTER_CROP ? f5 < f7 : f5 > f7) {
                f3 = this.mOutputHeight;
                f2 = (f3 / f6) * f4;
            } else {
                float f8 = this.mOutputWidth;
                float f9 = (f8 / f4) * f6;
                f2 = f8;
                f3 = f9;
            }
            return new int[]{Math.round(f2), Math.round(f3)};
        }

        private Bitmap loadResizedImage() {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            decode(options);
            int i2 = 1;
            while (true) {
                if (!checkSize(options.outWidth / i2 > this.mOutputWidth, options.outHeight / i2 > this.mOutputHeight)) {
                    break;
                }
                i2++;
            }
            int i3 = i2 - 1;
            if (i3 < 1) {
                i3 = 1;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i3;
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
            options2.inPurgeable = true;
            options2.inTempStorage = new byte[32768];
            Bitmap decode = decode(options2);
            if (decode == null) {
                return null;
            }
            return scaleBitmap(rotateImage(decode));
        }

        private Bitmap rotateImage(Bitmap bitmap) {
            Bitmap bitmap2;
            IOException e2;
            int imageOrientation;
            if (bitmap == null) {
                return null;
            }
            try {
                imageOrientation = getImageOrientation();
            } catch (IOException e3) {
                bitmap2 = bitmap;
                e2 = e3;
            }
            if (imageOrientation != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(imageOrientation);
                bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                try {
                    bitmap.recycle();
                } catch (IOException e4) {
                    e2 = e4;
                    UnLog.e(FilterImage.TAG, e2.toString());
                    return bitmap2;
                }
                return bitmap2;
            }
            return bitmap;
        }

        private Bitmap scaleBitmap(Bitmap bitmap) {
            int[] scaleSize = getScaleSize(bitmap.getWidth(), bitmap.getHeight());
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, scaleSize[0], scaleSize[1], true);
            if (createScaledBitmap != bitmap) {
                bitmap.recycle();
                System.gc();
                bitmap = createScaledBitmap;
            }
            if (FilterImage.this.mScaleType == ScaleType.CENTER_CROP) {
                int i2 = scaleSize[0] - this.mOutputWidth;
                int i3 = scaleSize[1] - this.mOutputHeight;
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, i2 / 2, i3 / 2, scaleSize[0] - i2, scaleSize[1] - i3);
                if (createBitmap != bitmap) {
                    bitmap.recycle();
                    return createBitmap;
                }
                return bitmap;
            }
            return bitmap;
        }

        protected abstract Bitmap decode(BitmapFactory.Options options);

        protected abstract int getImageOrientation() throws IOException;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... voidArr) {
            if (FilterImage.this.mRenderer != null && FilterImage.this.mRenderer.getFrameWidth() == 0) {
                try {
                    synchronized (FilterImage.this.mRenderer.mSurfaceChangedWaiter) {
                        FilterImage.this.mRenderer.mSurfaceChangedWaiter.wait(3000L);
                    }
                } catch (InterruptedException e2) {
                    UnLog.e(FilterImage.TAG, e2.toString());
                }
            }
            this.mOutputWidth = FilterImage.this.getOutputWidth();
            this.mOutputHeight = FilterImage.this.getOutputHeight();
            return loadResizedImage();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute((LoadImageTask) bitmap);
            this.mGPUImage.deleteImage();
            this.mGPUImage.setImage(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class LoadImageUriTask extends LoadImageTask {
        private final Uri mUri;

        public LoadImageUriTask(FilterImage filterImage, Uri uri) {
            super(filterImage);
            this.mUri = uri;
        }

        @Override // com.jingdong.common.unification.filter.filter.FilterImage.LoadImageTask
        protected Bitmap decode(BitmapFactory.Options options) {
            InputStream openStream;
            try {
                if (!this.mUri.getScheme().startsWith("http") && !this.mUri.getScheme().startsWith("https")) {
                    openStream = FilterImage.this.mContext.getContentResolver().openInputStream(this.mUri);
                    return BitmapFactory.decodeStream(openStream, null, options);
                }
                openStream = new URL(this.mUri.toString()).openStream();
                return BitmapFactory.decodeStream(openStream, null, options);
            } catch (Exception e2) {
                UnLog.e(FilterImage.TAG, e2.toString());
                return null;
            }
        }

        @Override // com.jingdong.common.unification.filter.filter.FilterImage.LoadImageTask
        protected int getImageOrientation() throws IOException {
            Cursor query = FilterImage.this.mContext.getContentResolver().query(this.mUri, new String[]{MBaseKeyNames.KEY_ORIENTATION}, null, null, null);
            if (query == null || query.getCount() != 1) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            query.moveToFirst();
            int i2 = query.getInt(0);
            query.close();
            return i2;
        }
    }

    /* loaded from: classes6.dex */
    public interface OnPictureSavedListener {
        void onPictureSaved(Uri uri);
    }

    /* loaded from: classes6.dex */
    public interface ResponseListener<T> {
        void response(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    /* loaded from: classes6.dex */
    public class SaveTask extends AsyncTask<Void, Void, Void> {
        private final Bitmap mBitmap;
        private final String mFileName;
        private final String mFolderName;
        private final Handler mHandler = new Handler();
        private final OnPictureSavedListener mListener;

        public SaveTask(Bitmap bitmap, String str, String str2, OnPictureSavedListener onPictureSavedListener) {
            this.mBitmap = bitmap;
            this.mFolderName = str;
            this.mFileName = str2;
            this.mListener = onPictureSavedListener;
        }

        private void saveImage(String str, String str2, Bitmap bitmap) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), str + "/" + str2);
            try {
                file.getParentFile().mkdirs();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(file));
                MediaScannerConnection.scanFile(FilterImage.this.mContext, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.jingdong.common.unification.filter.filter.FilterImage.SaveTask.1
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public void onScanCompleted(String str3, final Uri uri) {
                        if (SaveTask.this.mListener != null) {
                            SaveTask.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImage.SaveTask.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    SaveTask.this.mListener.onPictureSaved(uri);
                                }
                            });
                        }
                    }
                });
            } catch (FileNotFoundException e2) {
                UnLog.e(FilterImage.TAG, e2.toString());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            saveImage(this.mFolderName, this.mFileName, FilterImage.this.getBitmapWithFilterApplied(this.mBitmap));
            return null;
        }
    }

    /* loaded from: classes6.dex */
    public enum ScaleType {
        CENTER_INSIDE,
        CENTER_CROP
    }

    public FilterImage(Context context) {
        if (supportsOpenGLES2(context)) {
            this.mContext = context;
            this.mFilter = new CommonFilter();
            this.mRenderer = new CommonFilterRenderer(this.mFilter);
            return;
        }
        throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
    }

    public static void getBitmapForMultipleFilters(Bitmap bitmap, List<CommonFilter> list, ResponseListener<Bitmap> responseListener) {
        if (list.isEmpty()) {
            return;
        }
        CommonFilterRenderer commonFilterRenderer = new CommonFilterRenderer(list.get(0));
        commonFilterRenderer.setImageBitmap(bitmap, false);
        PixelBuffer pixelBuffer = new PixelBuffer(bitmap.getWidth(), bitmap.getHeight());
        pixelBuffer.setRenderer(commonFilterRenderer);
        for (CommonFilter commonFilter : list) {
            commonFilterRenderer.setFilter(commonFilter);
            responseListener.response(pixelBuffer.getBitmap());
            commonFilter.destroy();
        }
        commonFilterRenderer.deleteImage();
        pixelBuffer.destroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getOutputHeight() {
        CommonFilterRenderer commonFilterRenderer = this.mRenderer;
        if (commonFilterRenderer != null && commonFilterRenderer.getFrameHeight() != 0) {
            return this.mRenderer.getFrameHeight();
        }
        Bitmap bitmap = this.mCurrentBitmap;
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getOutputWidth() {
        CommonFilterRenderer commonFilterRenderer = this.mRenderer;
        if (commonFilterRenderer != null && commonFilterRenderer.getFrameWidth() != 0) {
            return this.mRenderer.getFrameWidth();
        }
        Bitmap bitmap = this.mCurrentBitmap;
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    private String getPath(Uri uri) {
        Cursor query = this.mContext.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (query != null) {
            r0 = query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow("_data")) : null;
            query.close();
        }
        return r0;
    }

    @TargetApi(11)
    private void setUpCameraGingerbread(Camera camera) {
        this.mRenderer.setUpSurfaceTexture(camera);
    }

    private boolean supportsOpenGLES2(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public void deleteImage() {
        this.mRenderer.deleteImage();
        this.mCurrentBitmap = null;
        requestRender();
    }

    public Bitmap getBitmapWithFilterApplied() {
        return getBitmapWithFilterApplied(this.mCurrentBitmap);
    }

    public void requestRender() {
        GLSurfaceView gLSurfaceView = this.mGlSurfaceView;
        if (gLSurfaceView != null) {
            gLSurfaceView.requestRender();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void runOnGLThread(Runnable runnable) {
        this.mRenderer.runOnDrawEnd(runnable);
    }

    @Deprecated
    public void saveToPictures(String str, String str2, OnPictureSavedListener onPictureSavedListener) {
        saveToPictures(this.mCurrentBitmap, str, str2, onPictureSavedListener);
    }

    public void setBackgroundColor(float f2, float f3, float f4) {
        this.mRenderer.setBackgroundColor(f2, f3, f4);
    }

    public void setFilter(CommonFilter commonFilter) {
        this.mFilter = commonFilter;
        this.mRenderer.setFilter(commonFilter);
        requestRender();
    }

    public void setGLSurfaceView(GLSurfaceView gLSurfaceView) {
        this.mGlSurfaceView = gLSurfaceView;
        gLSurfaceView.setEGLContextClientVersion(2);
        this.mGlSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.mGlSurfaceView.getHolder().setFormat(1);
        this.mGlSurfaceView.setRenderer(this.mRenderer);
        this.mGlSurfaceView.setRenderMode(0);
        this.mGlSurfaceView.requestRender();
    }

    public void setImage(Bitmap bitmap) {
        this.mCurrentBitmap = bitmap;
        this.mRenderer.setImageBitmap(bitmap, false);
        requestRender();
    }

    public void setRotation(Rotation rotation) {
        this.mRenderer.setRotation(rotation);
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mRenderer.setScaleType(scaleType);
        this.mRenderer.deleteImage();
        this.mCurrentBitmap = null;
        requestRender();
    }

    public void setUpCamera(Camera camera) {
        setUpCamera(camera, 0, false, false);
    }

    public Bitmap getBitmapWithFilterApplied(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        CommonFilterRenderer commonFilterRenderer = new CommonFilterRenderer(this.mFilter);
        commonFilterRenderer.setRotation(Rotation.NORMAL, this.mRenderer.isFlippedHorizontally(), this.mRenderer.isFlippedVertically());
        commonFilterRenderer.setScaleType(this.mScaleType);
        PixelBuffer pixelBuffer = new PixelBuffer(bitmap.getWidth(), bitmap.getHeight());
        pixelBuffer.setRenderer(commonFilterRenderer);
        commonFilterRenderer.setImageBitmap(bitmap, false);
        Bitmap bitmap2 = pixelBuffer.getBitmap();
        this.mFilter.destroy();
        commonFilterRenderer.deleteImage();
        pixelBuffer.destroy();
        return bitmap2;
    }

    @Deprecated
    public void saveToPictures(Bitmap bitmap, String str, String str2, OnPictureSavedListener onPictureSavedListener) {
        new SaveTask(bitmap, str, str2, onPictureSavedListener).execute(new Void[0]);
    }

    public void setRotation(Rotation rotation, boolean z, boolean z2) {
        this.mRenderer.setRotation(rotation, z, z2);
    }

    public void setUpCamera(Camera camera, int i2, boolean z, boolean z2) {
        this.mGlSurfaceView.setRenderMode(1);
        if (Build.VERSION.SDK_INT > 10) {
            setUpCameraGingerbread(camera);
        } else {
            camera.setPreviewCallback(this.mRenderer);
            camera.startPreview();
        }
        Rotation rotation = Rotation.NORMAL;
        if (i2 == 90) {
            rotation = Rotation.ROTATION_90;
        } else if (i2 == 180) {
            rotation = Rotation.ROTATION_180;
        } else if (i2 == 270) {
            rotation = Rotation.ROTATION_270;
        }
        this.mRenderer.setRotationCamera(rotation, z, z2);
    }

    public void setImage(Uri uri) {
        new LoadImageUriTask(this, uri).execute(new Void[0]);
    }

    public void setImage(File file) {
        new LoadImageFileTask(this, file).execute(new Void[0]);
    }
}
