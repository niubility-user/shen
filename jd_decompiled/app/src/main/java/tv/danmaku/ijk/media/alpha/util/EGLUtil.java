package tv.danmaku.ijk.media.alpha.util;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.jingdong.sdk.platform.business.personal.R2;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* loaded from: classes11.dex */
public class EGLUtil {
    private static final String TAG = "EGLUtil";
    private EGL10 egl;
    private EGLDisplay eglDisplay = EGL10.EGL_NO_DISPLAY;
    private EGLSurface eglSurface = EGL10.EGL_NO_SURFACE;
    private EGLContext eglContext = EGL10.EGL_NO_CONTEXT;

    private EGLConfig chooseConfig() {
        int[] iArr = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] attributes = getAttributes();
        EGL10 egl10 = this.egl;
        if (egl10 == null || !egl10.eglChooseConfig(this.eglDisplay, attributes, eGLConfigArr, 1, iArr)) {
            return null;
        }
        return eGLConfigArr[0];
    }

    private EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{R2.id.back_button, 2, R2.id.app_name_tv});
    }

    private int[] getAttributes() {
        return new int[]{R2.id.app_video_box, 4, R2.id.app_brand_remote_debug_op_layout, 8, R2.id.app_brand_remote_debug_info_tv, 8, R2.id.app_brand_remote_debug_expand_tv, 8, R2.id.app_brand_remote_debug_error_tv, 8, R2.id.app_brand_remote_debug_quit_tv, 0, R2.id.app_brand_remote_debug_server_dot, 0, R2.id.app_name_tv};
    }

    public void release() {
        EGL10 egl10 = this.egl;
        if (egl10 == null) {
            return;
        }
        EGLDisplay eGLDisplay = this.eglDisplay;
        EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
        egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
        this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface);
        this.egl.eglDestroyContext(this.eglDisplay, this.eglContext);
        this.egl.eglTerminate(this.eglDisplay);
    }

    public void start(SurfaceTexture surfaceTexture) {
        try {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.egl = egl10;
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.eglDisplay = eglGetDisplay;
            this.egl.eglInitialize(eglGetDisplay, new int[2]);
            EGLConfig chooseConfig = chooseConfig();
            this.eglSurface = this.egl.eglCreateWindowSurface(this.eglDisplay, chooseConfig, new Surface(surfaceTexture), null);
            EGLContext createContext = createContext(this.egl, this.eglDisplay, chooseConfig);
            this.eglContext = createContext;
            EGLSurface eGLSurface = this.eglSurface;
            if (eGLSurface != null && eGLSurface != EGL10.EGL_NO_SURFACE) {
                EGL10 egl102 = this.egl;
                if (egl102 == null || egl102.eglMakeCurrent(this.eglDisplay, eGLSurface, eGLSurface, createContext)) {
                    return;
                }
                String str = "[start] make current error:" + Integer.toHexString(this.egl.eglGetError());
                return;
            }
            EGL10 egl103 = this.egl;
            String str2 = "[start] error:" + Integer.toHexString(egl103 != null ? egl103.eglGetError() : 0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void swapBuffers() {
        EGLSurface eGLSurface;
        EGLDisplay eGLDisplay = this.eglDisplay;
        if (eGLDisplay == null || (eGLSurface = this.eglSurface) == null) {
            return;
        }
        this.egl.eglSwapBuffers(eGLDisplay, eGLSurface);
    }
}
