package com.jingdong.common.unification.filter.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.filter.filter.FilterImage;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import java.util.concurrent.Semaphore;

/* loaded from: classes6.dex */
public class FilterImageView extends FrameLayout {
    private static final String TAG = "FilterImageView";
    private CommonFilter mFilter;
    public Size mForceSize;
    private GLSurfaceView mGLSurfaceView;
    private FilterImage mGPUImage;
    private float mRatio;

    /* loaded from: classes6.dex */
    public interface OnPictureSavedListener {
        void onPictureSaved(Uri uri);
    }

    /* loaded from: classes6.dex */
    private class SaveTask extends AsyncTask<Void, Void, Void> {
        private final String mFileName;
        private final String mFolderName;
        private final Handler mHandler;
        private final int mHeight;
        private final OnPictureSavedListener mListener;
        private final int mWidth;

        public SaveTask(FilterImageView filterImageView, String str, String str2, OnPictureSavedListener onPictureSavedListener) {
            this(str, str2, 0, 0, onPictureSavedListener);
        }

        private void saveImage(String str, String str2, Bitmap bitmap) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), str + "/" + str2);
            try {
                file.getParentFile().mkdirs();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(file));
                MediaScannerConnection.scanFile(FilterImageView.this.getContext(), new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.SaveTask.1
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public void onScanCompleted(String str3, final Uri uri) {
                        if (SaveTask.this.mListener != null) {
                            SaveTask.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.SaveTask.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    SaveTask.this.mListener.onPictureSaved(uri);
                                }
                            });
                        }
                    }
                });
            } catch (FileNotFoundException e2) {
                UnLog.e(FilterImageView.TAG, e2.toString());
            }
        }

        public SaveTask(String str, String str2, int i2, int i3, OnPictureSavedListener onPictureSavedListener) {
            this.mFolderName = str;
            this.mFileName = str2;
            this.mWidth = i2;
            this.mHeight = i3;
            this.mListener = onPictureSavedListener;
            this.mHandler = new Handler();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            try {
                int i2 = this.mWidth;
                saveImage(this.mFolderName, this.mFileName, i2 != 0 ? FilterImageView.this.capture(i2, this.mHeight) : FilterImageView.this.capture());
                return null;
            } catch (InterruptedException e2) {
                UnLog.e(FilterImageView.TAG, e2.toString());
                return null;
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class Size {
        int height;
        int width;

        public Size(int i2, int i3) {
            this.width = i2;
            this.height = i3;
        }
    }

    public FilterImageView(Context context) {
        super(context);
        this.mForceSize = null;
        this.mRatio = 0.0f;
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        GPUImageGLSurfaceView gPUImageGLSurfaceView = new GPUImageGLSurfaceView(context, attributeSet);
        this.mGLSurfaceView = gPUImageGLSurfaceView;
        addView(gPUImageGLSurfaceView);
        FilterImage filterImage = new FilterImage(getContext());
        this.mGPUImage = filterImage;
        filterImage.setGLSurfaceView(this.mGLSurfaceView);
    }

    public Bitmap capture(int i2, int i3) throws InterruptedException {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.mForceSize = new Size(i2, i3);
            final Semaphore semaphore = new Semaphore(0);
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < 16) {
                        FilterImageView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        FilterImageView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    semaphore.release();
                }
            });
            post(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.2
                @Override // java.lang.Runnable
                public void run() {
                    FilterImageView filterImageView = FilterImageView.this;
                    FilterImageView filterImageView2 = FilterImageView.this;
                    filterImageView.addView(new LoadingView(filterImageView2.getContext()));
                    FilterImageView.this.mGLSurfaceView.requestLayout();
                }
            });
            semaphore.acquire();
            this.mGPUImage.runOnGLThread(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.3
                @Override // java.lang.Runnable
                public void run() {
                    semaphore.release();
                }
            });
            requestRender();
            semaphore.acquire();
            Bitmap capture = capture();
            this.mForceSize = null;
            post(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.4
                @Override // java.lang.Runnable
                public void run() {
                    FilterImageView.this.mGLSurfaceView.requestLayout();
                }
            });
            requestRender();
            postDelayed(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.5
                @Override // java.lang.Runnable
                public void run() {
                    FilterImageView.this.removeViewAt(1);
                }
            }, 300L);
            return capture;
        }
        throw new IllegalStateException("Do not call this method from the UI thread!");
    }

    public CommonFilter getFilter() {
        return this.mFilter;
    }

    public FilterImage getGPUImage() {
        return this.mGPUImage;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.mRatio != 0.0f) {
            int size = View.MeasureSpec.getSize(i2);
            int size2 = View.MeasureSpec.getSize(i3);
            float f2 = size;
            float f3 = this.mRatio;
            float f4 = size2;
            if (f2 / f3 < f4) {
                size2 = Math.round(f2 / f3);
            } else {
                size = Math.round(f4 * f3);
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
            return;
        }
        super.onMeasure(i2, i3);
    }

    public void onPause() {
        this.mGLSurfaceView.onPause();
    }

    public void onResume() {
        this.mGLSurfaceView.onResume();
    }

    public void requestRender() {
        this.mGLSurfaceView.requestRender();
    }

    public void saveToPictures(String str, String str2, OnPictureSavedListener onPictureSavedListener) {
        new SaveTask(this, str, str2, onPictureSavedListener).execute(new Void[0]);
    }

    public void setBackgroundColor(float f2, float f3, float f4) {
        this.mGPUImage.setBackgroundColor(f2, f3, f4);
    }

    public void setFilter(CommonFilter commonFilter) {
        this.mFilter = commonFilter;
        this.mGPUImage.setFilter(commonFilter);
        requestRender();
    }

    public void setImage(Bitmap bitmap) {
        this.mGPUImage.setImage(bitmap);
    }

    public void setRatio(float f2) {
        this.mRatio = f2;
        this.mGLSurfaceView.requestLayout();
        this.mGPUImage.deleteImage();
    }

    public void setRotation(Rotation rotation) {
        this.mGPUImage.setRotation(rotation);
        requestRender();
    }

    public void setScaleType(FilterImage.ScaleType scaleType) {
        this.mGPUImage.setScaleType(scaleType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class GPUImageGLSurfaceView extends GLSurfaceView {
        public GPUImageGLSurfaceView(Context context) {
            super(context);
        }

        @Override // android.view.SurfaceView, android.view.View
        protected void onMeasure(int i2, int i3) {
            Size size = FilterImageView.this.mForceSize;
            if (size != null) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(size.width, 1073741824), View.MeasureSpec.makeMeasureSpec(FilterImageView.this.mForceSize.height, 1073741824));
            } else {
                super.onMeasure(i2, i3);
            }
        }

        public GPUImageGLSurfaceView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public void saveToPictures(String str, String str2, int i2, int i3, OnPictureSavedListener onPictureSavedListener) {
        new SaveTask(str, str2, i2, i3, onPictureSavedListener).execute(new Void[0]);
    }

    public void setImage(Uri uri) {
        this.mGPUImage.setImage(uri);
    }

    /* loaded from: classes6.dex */
    private class LoadingView extends FrameLayout {
        public LoadingView(Context context) {
            super(context);
            init();
        }

        private void init() {
            ProgressBar progressBar = new ProgressBar(getContext());
            progressBar.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
            addView(progressBar);
            setBackgroundColor(-16777216);
        }

        public LoadingView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            init();
        }

        public LoadingView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            init();
        }
    }

    public void setImage(File file) {
        this.mGPUImage.setImage(file);
    }

    public FilterImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mForceSize = null;
        this.mRatio = 0.0f;
        init(context, attributeSet);
    }

    public Bitmap capture() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(0);
        final int measuredWidth = this.mGLSurfaceView.getMeasuredWidth();
        final int measuredHeight = this.mGLSurfaceView.getMeasuredHeight();
        final int[] iArr = new int[measuredWidth * measuredHeight];
        this.mGPUImage.runOnGLThread(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.FilterImageView.6
            @Override // java.lang.Runnable
            public void run() {
                IntBuffer allocate = IntBuffer.allocate(measuredWidth * measuredHeight);
                GLES20.glReadPixels(0, 0, measuredWidth, measuredHeight, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, allocate);
                int[] array = allocate.array();
                for (int i2 = 0; i2 < measuredHeight; i2++) {
                    int i3 = 0;
                    while (true) {
                        int i4 = measuredWidth;
                        if (i3 < i4) {
                            iArr[(((measuredHeight - i2) - 1) * i4) + i3] = array[(i4 * i2) + i3];
                            i3++;
                        }
                    }
                }
                semaphore.release();
            }
        });
        requestRender();
        semaphore.acquire();
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(IntBuffer.wrap(iArr));
        return createBitmap;
    }
}
