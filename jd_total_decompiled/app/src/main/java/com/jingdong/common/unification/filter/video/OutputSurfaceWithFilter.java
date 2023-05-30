package com.jingdong.common.unification.filter.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.view.Surface;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.filter.FilterTools;
import com.jingdong.common.unification.filter.filter.CommonFilter;
import com.jingdong.common.unification.filter.filter.CommonFilterGroup;
import com.jingdong.common.unification.filter.filter.Rotation;
import com.jingdong.common.unification.filter.filter.TextureRotationUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* loaded from: classes6.dex */
class OutputSurfaceWithFilter implements SurfaceTexture.OnFrameAvailableListener {
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final String TAG = "OutputSurface";
    private CommonFilterGroup gpuImageFilter;
    private EGL10 mEGL;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private boolean mFrameAvailable;
    private final FloatBuffer mGLCubeBuffer;
    private final FloatBuffer mGLTextureBuffer;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private Object mFrameSyncObject = new Object();
    int genTextureID = -1;
    float[] mSTMatrix = new float[16];

    public OutputSurfaceWithFilter(Context context, FilterTools.FilterType filterType, int i2, int i3, int i4, int i5) {
        float[] fArr = TextureRotationUtil.CUBE;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        float[] rotation = TextureRotationUtil.getRotation(Rotation.fromInt(i5), false, false);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(rotation.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(rotation).position(0);
        setup(context, filterType, i2, i3, i4, i5);
    }

    private void checkEglError(String str) {
        boolean z = false;
        while (true) {
            int eglGetError = this.mEGL.eglGetError();
            if (eglGetError == 12288) {
                break;
            }
            UnLog.e(TAG, str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
            z = true;
        }
        if (z) {
            throw new RuntimeException("EGL error encountered (see log)");
        }
    }

    private void setup(Context context, FilterTools.FilterType filterType, int i2, int i3, int i4, int i5) {
        CommonFilterGroup commonFilterGroup = new CommonFilterGroup();
        this.gpuImageFilter = commonFilterGroup;
        commonFilterGroup.addFilter(new CommonExtTexFilter());
        CommonFilter createFilterForType = FilterTools.createFilterForType(filterType, i2);
        if (UnLog.D) {
            UnLog.d("OutputSurfaceWithFilter", "setup filter:" + createFilterForType);
            UnLog.d("OutputSurfaceWithFilter", "setup filterProgress:" + i2);
        }
        this.gpuImageFilter.addFilter(createFilterForType);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDisable(R2.color.Red_190);
        this.gpuImageFilter.init();
        GLES20.glUseProgram(this.gpuImageFilter.getProgram());
        this.gpuImageFilter.onOutputSizeChanged(i3, i4);
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameterf(36197, R2.drawable.main_bottom_tab_personal_normal, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_personal_normal_dark, 33071);
        GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_search_focus, 33071);
        this.mSurfaceTexture = new SurfaceTexture(iArr[0]);
        if (UnLog.D) {
            UnLog.d(TAG, "textureID=" + iArr[0]);
        }
        SurfaceTexture surfaceTexture = new SurfaceTexture(iArr[0]);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mSurface = new Surface(this.mSurfaceTexture);
    }

    public void awaitNewImage() {
        synchronized (this.mFrameSyncObject) {
            do {
                if (!this.mFrameAvailable) {
                    try {
                        this.mFrameSyncObject.wait(600L);
                    } catch (InterruptedException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    this.mFrameAvailable = false;
                }
            } while (this.mFrameAvailable);
            throw new RuntimeException("Surface frame wait timed out");
        }
        this.mSurfaceTexture.updateTexImage();
        this.mSurfaceTexture.getTransformMatrix(this.mSTMatrix);
    }

    public void checkGlError(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError == 0) {
            return;
        }
        UnLog.e(TAG, str + ": glError " + glGetError);
        throw new RuntimeException(str + ": glError " + glGetError);
    }

    public void drawImage() {
        GLES20.glClear(R2.id.tradein_inform_title);
        this.gpuImageFilter.onDraw(this.genTextureID, this.mGLCubeBuffer, this.mGLTextureBuffer);
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void makeCurrent() {
        if (this.mEGL != null) {
            checkEglError("before makeCurrent");
            EGL10 egl10 = this.mEGL;
            EGLDisplay eGLDisplay = this.mEGLDisplay;
            EGLSurface eGLSurface = this.mEGLSurface;
            if (!egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.mEGLContext)) {
                throw new RuntimeException("eglMakeCurrent failed");
            }
            return;
        }
        throw new RuntimeException("not configured for makeCurrent");
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (UnLog.D) {
            UnLog.d(TAG, "new frame available");
        }
        synchronized (this.mFrameSyncObject) {
            if (!this.mFrameAvailable) {
                this.mFrameAvailable = true;
                this.mFrameSyncObject.notifyAll();
            } else {
                throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
            }
        }
    }

    public void release() {
        EGL10 egl10 = this.mEGL;
        if (egl10 != null) {
            if (egl10.eglGetCurrentContext().equals(this.mEGLContext)) {
                EGL10 egl102 = this.mEGL;
                EGLDisplay eGLDisplay = this.mEGLDisplay;
                EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
                egl102.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            }
            this.mEGL.eglDestroySurface(this.mEGLDisplay, this.mEGLSurface);
            this.mEGL.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
        }
        this.mSurface.release();
        this.gpuImageFilter.destroy();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mEGL = null;
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }
}
