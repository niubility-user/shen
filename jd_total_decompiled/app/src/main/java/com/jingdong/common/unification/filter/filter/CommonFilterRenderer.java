package com.jingdong.common.unification.filter.filter;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.Surface;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.filter.filter.FilterImage;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(11)
/* loaded from: classes6.dex */
public class CommonFilterRenderer implements GLSurfaceView.Renderer, Camera.PreviewCallback {
    static final float[] CUBE = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    public static final int NO_IMAGE = -1;
    private static final String TAG = "CommonFilterRenderer";
    private int mAddedPadding;
    private float mBackgroundBlue;
    private float mBackgroundGreen;
    private float mBackgroundRed;
    private CommonFilter mFilter;
    private boolean mFlipHorizontal;
    private boolean mFlipVertical;
    private final FloatBuffer mGLCubeBuffer;
    private IntBuffer mGLRgbBuffer;
    private final FloatBuffer mGLTextureBuffer;
    private int mGLTextureId;
    private int mImageHeight;
    private int mImageWidth;
    private int mOutputHeight;
    private int mOutputWidth;
    private Rotation mRotation;
    private final Queue<Runnable> mRunOnDraw;
    private final Queue<Runnable> mRunOnDrawEnd;
    private FilterImage.ScaleType mScaleType;
    public final Object mSurfaceChangedWaiter;
    private SurfaceTexture mSurfaceTexture;
    boolean transformSetted;
    private float[] videoTextureTransform;

    public CommonFilterRenderer(CommonFilter commonFilter) {
        this.mSurfaceChangedWaiter = new Object();
        this.mGLTextureId = -1;
        this.mSurfaceTexture = null;
        this.mImageWidth = 480;
        this.mImageHeight = R2.attr.additionBottom;
        this.mScaleType = FilterImage.ScaleType.CENTER_INSIDE;
        this.mBackgroundRed = 0.0f;
        this.mBackgroundGreen = 0.0f;
        this.mBackgroundBlue = 0.0f;
        this.videoTextureTransform = new float[16];
        this.transformSetted = false;
        this.mFilter = commonFilter;
        this.mRunOnDraw = new LinkedList();
        this.mRunOnDrawEnd = new LinkedList();
        float[] fArr = CUBE;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        float[] fArr2 = TextureRotationUtil.TEXTURE_NO_ROTATION;
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(fArr2).position(0);
        setRotation(Rotation.NORMAL, false, false);
    }

    private float addDistance(float f2, float f3) {
        return f2 == 0.0f ? f3 : 1.0f - f3;
    }

    public void adjustImageScaling() {
        int i2 = this.mOutputWidth;
        float f2 = i2;
        int i3 = this.mOutputHeight;
        float f3 = i3;
        Rotation rotation = this.mRotation;
        if (rotation == Rotation.ROTATION_270 || rotation == Rotation.ROTATION_90) {
            f2 = i3;
            f3 = i2;
        }
        float max = Math.max(f2 / this.mImageWidth, f3 / this.mImageHeight);
        float round = Math.round(this.mImageWidth * max) / f2;
        float round2 = Math.round(this.mImageHeight * max) / f3;
        float[] fArr = CUBE;
        float[] rotation2 = TextureRotationUtil.getRotation(this.mRotation, this.mFlipHorizontal, this.mFlipVertical);
        if (this.mScaleType == FilterImage.ScaleType.CENTER_CROP) {
            float f4 = (1.0f - (1.0f / round)) / 2.0f;
            float f5 = (1.0f - (1.0f / round2)) / 2.0f;
            rotation2 = new float[]{addDistance(rotation2[0], f4), addDistance(rotation2[1], f5), addDistance(rotation2[2], f4), addDistance(rotation2[3], f5), addDistance(rotation2[4], f4), addDistance(rotation2[5], f5), addDistance(rotation2[6], f4), addDistance(rotation2[7], f5)};
        } else {
            fArr = new float[]{fArr[0] / round2, fArr[1] / round, fArr[2] / round2, fArr[3] / round, fArr[4] / round2, fArr[5] / round, fArr[6] / round2, fArr[7] / round};
        }
        this.mGLCubeBuffer.clear();
        this.mGLCubeBuffer.put(fArr).position(0);
        this.mGLTextureBuffer.clear();
        this.mGLTextureBuffer.put(rotation2).position(0);
    }

    private void runAll(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                queue.poll().run();
            }
        }
    }

    public void checkGlError(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            if (UnLog.E) {
                UnLog.e("GPUImageRenderer", str + ": glError " + glGetError);
            }
            throw new RuntimeException(str + ": glError " + glGetError);
        }
    }

    public void deleteImage() {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilterRenderer.4
            {
                CommonFilterRenderer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                GLES20.glDeleteTextures(1, new int[]{CommonFilterRenderer.this.mGLTextureId}, 0);
                CommonFilterRenderer.this.mGLTextureId = -1;
            }
        });
    }

    public int getFrameHeight() {
        return this.mOutputHeight;
    }

    public int getFrameWidth() {
        return this.mOutputWidth;
    }

    public Rotation getRotation() {
        return this.mRotation;
    }

    public boolean isFlippedHorizontally() {
        return this.mFlipHorizontal;
    }

    public boolean isFlippedVertically() {
        return this.mFlipVertical;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(R2.id.tradein_inform_title);
        runAll(this.mRunOnDraw);
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.updateTexImage();
            this.mSurfaceTexture.getTransformMatrix(this.videoTextureTransform);
            float[] fArr = this.videoTextureTransform;
            if (fArr[0] == 1.0f && fArr[5] == -1.0f) {
                setRotation(Rotation.NORMAL, false, false);
            } else if (fArr[1] == 1.0f && fArr[4] == 1.0f) {
                setRotation(Rotation.ROTATION_270, false, false);
            } else if (fArr[1] == -1.0f && fArr[4] == -1.0f) {
                setRotation(Rotation.ROTATION_90, false, false);
            } else if (fArr[0] == -1.0f && fArr[5] == 1.0f) {
                setRotation(Rotation.ROTATION_180, false, false);
            }
        }
        this.mFilter.onDraw(this.mGLTextureId, this.mGLCubeBuffer, this.mGLTextureBuffer);
        runAll(this.mRunOnDrawEnd);
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (this.mGLRgbBuffer == null) {
            this.mGLRgbBuffer = IntBuffer.allocate(previewSize.width * previewSize.height);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
        this.mOutputWidth = i2;
        this.mOutputHeight = i3;
        GLES20.glViewport(0, 0, i2, i3);
        GLES20.glUseProgram(this.mFilter.getProgram());
        this.mFilter.onOutputSizeChanged(i2, i3);
        adjustImageScaling();
        synchronized (this.mSurfaceChangedWaiter) {
            this.mSurfaceChangedWaiter.notifyAll();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(this.mBackgroundRed, this.mBackgroundGreen, this.mBackgroundBlue, 1.0f);
        GLES20.glDisable(R2.color.Red_190);
        this.mFilter.init();
    }

    protected void runOnDraw(Runnable runnable) {
        synchronized (this.mRunOnDraw) {
            this.mRunOnDraw.add(runnable);
        }
    }

    public void runOnDrawEnd(Runnable runnable) {
        synchronized (this.mRunOnDrawEnd) {
            this.mRunOnDrawEnd.add(runnable);
        }
    }

    public void setBackgroundColor(float f2, float f3, float f4) {
        this.mBackgroundRed = f2;
        this.mBackgroundGreen = f3;
        this.mBackgroundBlue = f4;
    }

    public void setFilter(final CommonFilter commonFilter) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilterRenderer.3
            {
                CommonFilterRenderer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                CommonFilter commonFilter2 = CommonFilterRenderer.this.mFilter;
                CommonFilterRenderer.this.mFilter = commonFilter;
                if (commonFilter2 != null) {
                    commonFilter2.destroy();
                }
                CommonFilterRenderer.this.mFilter.init();
                GLES20.glUseProgram(CommonFilterRenderer.this.mFilter.getProgram());
                CommonFilterRenderer.this.mFilter.onOutputSizeChanged(CommonFilterRenderer.this.mOutputWidth, CommonFilterRenderer.this.mOutputHeight);
            }
        });
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageBitmap(bitmap, true);
    }

    public void setRotation(Rotation rotation) {
        this.mRotation = rotation;
        adjustImageScaling();
    }

    public void setRotationCamera(Rotation rotation, boolean z, boolean z2) {
        setRotation(rotation, z2, z);
    }

    public void setScaleType(FilterImage.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public void setSourceSize(int i2, int i3) {
        this.mImageWidth = i2;
        this.mImageHeight = i3;
    }

    public void setUpSurfaceTexture(final Camera camera) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilterRenderer.1
            {
                CommonFilterRenderer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                CommonFilterRenderer.this.mSurfaceTexture = new SurfaceTexture(iArr[0]);
                try {
                    camera.setPreviewTexture(CommonFilterRenderer.this.mSurfaceTexture);
                    camera.setPreviewCallback(CommonFilterRenderer.this);
                    camera.startPreview();
                } catch (IOException e2) {
                    UnLog.e(CommonFilterRenderer.TAG, e2.toString());
                }
            }
        });
    }

    public void setImageBitmap(final Bitmap bitmap, final boolean z) {
        if (bitmap == null) {
            return;
        }
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilterRenderer.5
            {
                CommonFilterRenderer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmap2 = null;
                if (bitmap.getWidth() % 2 != 1) {
                    CommonFilterRenderer.this.mAddedPadding = 0;
                } else {
                    Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + 1, bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(createBitmap);
                    canvas.drawARGB(0, 0, 0, 0);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                    CommonFilterRenderer.this.mAddedPadding = 1;
                    bitmap2 = createBitmap;
                }
                CommonFilterRenderer commonFilterRenderer = CommonFilterRenderer.this;
                commonFilterRenderer.mGLTextureId = OpenGlUtils.loadTexture(bitmap2 != null ? bitmap2 : bitmap, commonFilterRenderer.mGLTextureId, z);
                if (bitmap2 != null) {
                    bitmap2.recycle();
                }
                CommonFilterRenderer.this.mImageWidth = bitmap.getWidth();
                CommonFilterRenderer.this.mImageHeight = bitmap.getHeight();
                CommonFilterRenderer.this.adjustImageScaling();
            }
        });
    }

    public void setUpSurfaceTexture(final MediaPlayer mediaPlayer) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilterRenderer.2
            {
                CommonFilterRenderer.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                GLES20.glBindTexture(36197, iArr[0]);
                CommonFilterRenderer.this.checkGlError("glBindTexture mTextureID");
                GLES20.glTexParameterf(36197, R2.drawable.main_bottom_tab_personal_normal, 9728.0f);
                GLES20.glTexParameterf(36197, 10240, 9729.0f);
                GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_personal_normal_dark, 33071);
                GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_search_focus, 33071);
                CommonFilterRenderer.this.checkGlError("glTexParameter");
                CommonFilterRenderer.this.mSurfaceTexture = new SurfaceTexture(iArr[0]);
                try {
                    mediaPlayer.setSurface(new Surface(CommonFilterRenderer.this.mSurfaceTexture));
                } catch (Exception e2) {
                    UnLog.e(CommonFilterRenderer.TAG, e2.toString());
                }
            }
        });
    }

    public void setRotation(Rotation rotation, boolean z, boolean z2) {
        this.mFlipHorizontal = z;
        this.mFlipVertical = z2;
        setRotation(rotation);
    }

    public CommonFilterRenderer(CommonFilter commonFilter, int i2) {
        this.mSurfaceChangedWaiter = new Object();
        this.mGLTextureId = -1;
        this.mSurfaceTexture = null;
        this.mImageWidth = 480;
        this.mImageHeight = R2.attr.additionBottom;
        this.mScaleType = FilterImage.ScaleType.CENTER_INSIDE;
        this.mBackgroundRed = 0.0f;
        this.mBackgroundGreen = 0.0f;
        this.mBackgroundBlue = 0.0f;
        this.videoTextureTransform = new float[16];
        this.transformSetted = false;
        this.mFilter = commonFilter;
        this.mRunOnDraw = new LinkedList();
        this.mRunOnDrawEnd = new LinkedList();
        float[] fArr = CUBE;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        Rotation fromInt = Rotation.fromInt(i2);
        float[] rotation = TextureRotationUtil.getRotation(fromInt, false, false);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(rotation.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(rotation).position(0);
        setRotation(fromInt, false, false);
    }
}
