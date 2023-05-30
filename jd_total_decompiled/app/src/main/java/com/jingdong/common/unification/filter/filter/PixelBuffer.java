package com.jingdong.common.unification.filter.filter;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Build;
import com.jingdong.common.UnLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes6.dex */
public class PixelBuffer {
    static final boolean LIST_CONFIGS = false;
    static final String TAG = "PixelBuffer";
    Bitmap mBitmap;
    EGL10 mEGL;
    EGLConfig mEGLConfig;
    EGLConfig[] mEGLConfigs;
    EGLContext mEGLContext;
    EGLDisplay mEGLDisplay;
    EGLSurface mEGLSurface;
    GL10 mGL;
    int mHeight;
    GLSurfaceView.Renderer mRenderer;
    String mThreadOwner;
    int mWidth;

    public PixelBuffer(int i2, int i3) {
        this.mWidth = i2;
        this.mHeight = i3;
        int[] iArr = {R2.id.appbrand_action_multiple_header_view3, i2, R2.id.appbrand_action_multiple_header_view2, i3, R2.id.app_name_tv};
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.mEGL = egl10;
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.mEGLDisplay = eglGetDisplay;
        this.mEGL.eglInitialize(eglGetDisplay, new int[2]);
        EGLConfig chooseConfig = chooseConfig();
        this.mEGLConfig = chooseConfig;
        this.mEGLContext = this.mEGL.eglCreateContext(this.mEGLDisplay, chooseConfig, EGL10.EGL_NO_CONTEXT, new int[]{R2.id.back_button, 2, R2.id.app_name_tv});
        EGLSurface eglCreatePbufferSurface = this.mEGL.eglCreatePbufferSurface(this.mEGLDisplay, this.mEGLConfig, iArr);
        this.mEGLSurface = eglCreatePbufferSurface;
        this.mEGL.eglMakeCurrent(this.mEGLDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, this.mEGLContext);
        this.mGL = (GL10) this.mEGLContext.getGL();
        this.mThreadOwner = Thread.currentThread().getName();
    }

    private EGLConfig chooseConfig() {
        int[] iArr = {R2.id.app_brand_remote_debug_quit_tv, 0, R2.id.app_brand_remote_debug_server_dot, 0, R2.id.app_brand_remote_debug_op_layout, 8, R2.id.app_brand_remote_debug_info_tv, 8, R2.id.app_brand_remote_debug_expand_tv, 8, R2.id.app_brand_remote_debug_error_tv, 8, R2.id.app_video_box, 4, R2.id.app_name_tv};
        int[] iArr2 = new int[1];
        this.mEGL.eglChooseConfig(this.mEGLDisplay, iArr, null, 0, iArr2);
        int i2 = iArr2[0];
        EGLConfig[] eGLConfigArr = new EGLConfig[i2];
        this.mEGLConfigs = eGLConfigArr;
        this.mEGL.eglChooseConfig(this.mEGLDisplay, iArr, eGLConfigArr, i2, iArr2);
        return this.mEGLConfigs[0];
    }

    private void convertToBitmap() {
        int i2;
        Bitmap.Config config;
        int i3 = this.mWidth;
        int i4 = this.mHeight;
        int[] iArr = new int[i3 * i4];
        IntBuffer allocate = IntBuffer.allocate(i3 * i4);
        this.mGL.glReadPixels(0, 0, this.mWidth, this.mHeight, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, allocate);
        int[] array = allocate.array();
        int i5 = 0;
        while (true) {
            i2 = this.mHeight;
            if (i5 >= i2) {
                break;
            }
            int i6 = 0;
            while (true) {
                int i7 = this.mWidth;
                if (i6 < i7) {
                    iArr[(((this.mHeight - i5) - 1) * i7) + i6] = array[(i7 * i5) + i6];
                    i6++;
                }
            }
            i5++;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            config = Bitmap.Config.ARGB_4444;
        } else {
            config = Bitmap.Config.ARGB_8888;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(this.mWidth, i2, config);
            this.mBitmap = createBitmap;
            createBitmap.copyPixelsFromBuffer(IntBuffer.wrap(iArr));
        } catch (Exception e2) {
            if (UnLog.E) {
                e2.printStackTrace();
            }
        }
    }

    private int getConfigAttrib(EGLConfig eGLConfig, int i2) {
        int[] iArr = new int[1];
        if (this.mEGL.eglGetConfigAttrib(this.mEGLDisplay, eGLConfig, i2, iArr)) {
            return iArr[0];
        }
        return 0;
    }

    private void listConfig() {
        for (EGLConfig eGLConfig : this.mEGLConfigs) {
            getConfigAttrib(eGLConfig, R2.id.app_brand_remote_debug_quit_tv);
            getConfigAttrib(eGLConfig, R2.id.app_brand_remote_debug_server_dot);
            getConfigAttrib(eGLConfig, R2.id.app_brand_remote_debug_op_layout);
            getConfigAttrib(eGLConfig, R2.id.app_brand_remote_debug_info_tv);
            getConfigAttrib(eGLConfig, R2.id.app_brand_remote_debug_expand_tv);
            getConfigAttrib(eGLConfig, R2.id.app_brand_remote_debug_error_tv);
        }
    }

    public void destroy() {
        this.mRenderer.onDrawFrame(this.mGL);
        this.mRenderer.onDrawFrame(this.mGL);
        EGL10 egl10 = this.mEGL;
        EGLDisplay eGLDisplay = this.mEGLDisplay;
        EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
        egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
        this.mEGL.eglDestroySurface(this.mEGLDisplay, this.mEGLSurface);
        this.mEGL.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
        this.mEGL.eglTerminate(this.mEGLDisplay);
    }

    public Bitmap getBitmap() {
        if (this.mRenderer == null) {
            if (UnLog.E) {
                UnLog.e(TAG, "getBitmap: Renderer was not set.");
            }
            return null;
        } else if (!Thread.currentThread().getName().equals(this.mThreadOwner)) {
            if (UnLog.E) {
                UnLog.e(TAG, "getBitmap: This thread does not own the OpenGL context.");
            }
            return null;
        } else {
            this.mRenderer.onDrawFrame(this.mGL);
            this.mRenderer.onDrawFrame(this.mGL);
            convertToBitmap();
            return this.mBitmap;
        }
    }

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        this.mRenderer = renderer;
        if (!Thread.currentThread().getName().equals(this.mThreadOwner)) {
            if (UnLog.E) {
                UnLog.e(TAG, "setRenderer: This thread does not own the OpenGL context.");
                return;
            }
            return;
        }
        this.mRenderer.onSurfaceCreated(this.mGL, this.mEGLConfig);
        this.mRenderer.onSurfaceChanged(this.mGL, this.mWidth, this.mHeight);
    }
}
