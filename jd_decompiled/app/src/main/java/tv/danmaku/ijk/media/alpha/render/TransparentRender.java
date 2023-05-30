package tv.danmaku.ijk.media.alpha.render;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.util.EGLUtil;
import tv.danmaku.ijk.media.alpha.util.GlFloatArray;
import tv.danmaku.ijk.media.alpha.util.ShaderUtil;
import tv.danmaku.ijk.media.alpha.util.TexCoordsUtil;
import tv.danmaku.ijk.media.alpha.util.VertexUtil;

/* loaded from: classes11.dex */
public class TransparentRender {
    private static final String TAG = "TransparentRender";
    private int aPositionLocation;
    private int aTextureAlphaLocation;
    private int aTextureRgbLocation;
    private final EGLUtil eglUtil;
    private final int[] genTexture;
    private int shaderProgram;
    private int surfaceHeight;
    private int surfaceWidth;
    private int uTextureLocation;
    private final GlFloatArray vertexArray = new GlFloatArray();
    private final GlFloatArray alphaArray = new GlFloatArray();
    private final GlFloatArray rgbArray = new GlFloatArray();
    private boolean surfaceSizeChanged = false;

    public TransparentRender(SurfaceTexture surfaceTexture) {
        EGLUtil eGLUtil = new EGLUtil();
        this.eglUtil = eGLUtil;
        this.shaderProgram = 0;
        this.genTexture = new int[1];
        eGLUtil.start(surfaceTexture);
        initGL();
    }

    private void compileShader() {
        int createProgram = ShaderUtil.createProgram(RenderConstant.VERTEX_SHADER, RenderConstant.FRAGMENT_SHADER);
        this.shaderProgram = createProgram;
        this.uTextureLocation = GLES20.glGetUniformLocation(createProgram, "texture");
        this.aPositionLocation = GLES20.glGetAttribLocation(this.shaderProgram, "vPosition");
        this.aTextureAlphaLocation = GLES20.glGetAttribLocation(this.shaderProgram, "vTexCoordinateAlpha");
        this.aTextureRgbLocation = GLES20.glGetAttribLocation(this.shaderProgram, "vTexCoordinateRgb");
    }

    private void draw() {
        GLES20.glUseProgram(this.shaderProgram);
        this.vertexArray.setVertexAttribPointer(this.aPositionLocation);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.genTexture[0]);
        GLES20.glUniform1i(this.uTextureLocation, 0);
        this.alphaArray.setVertexAttribPointer(this.aTextureAlphaLocation);
        this.rgbArray.setVertexAttribPointer(this.aTextureRgbLocation);
        GLES20.glDrawArrays(5, 0, 4);
    }

    private void initGL() {
        compileShader();
    }

    private void setTexCoords(AlphaConfig alphaConfig) {
        float[] create = TexCoordsUtil.create(alphaConfig.videoWidth, alphaConfig.videoHeight, alphaConfig.alphaPointRect, this.alphaArray.floatArray);
        float[] create2 = TexCoordsUtil.create(alphaConfig.videoWidth, alphaConfig.videoHeight, alphaConfig.rgbPointRect, this.rgbArray.floatArray);
        this.alphaArray.setArray(create);
        this.rgbArray.setArray(create2);
    }

    private void setVertexBuf(AlphaConfig alphaConfig) {
        GlFloatArray glFloatArray = this.vertexArray;
        int i2 = alphaConfig.width;
        int i3 = alphaConfig.height;
        glFloatArray.setArray(VertexUtil.create(i2, i3, new AlphaConfig.PointRect(0, 0, i2, i3), this.vertexArray.floatArray));
    }

    public void clearFrame() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        this.eglUtil.swapBuffers();
    }

    public void createTexture() {
        int[] iArr = this.genTexture;
        GLES20.glGenTextures(iArr.length, iArr, 0);
        GLES20.glBindTexture(36197, this.genTexture[0]);
        GLES20.glTexParameterf(36197, R2.drawable.main_bottom_tab_personal_normal, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_personal_normal_dark, 33071);
        GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_search_focus, 33071);
    }

    public void destroy() {
        releaseTexture();
        this.eglUtil.release();
    }

    public int getExternalTexture() {
        return this.genTexture[0];
    }

    public void releaseTexture() {
        int[] iArr = this.genTexture;
        GLES20.glDeleteTextures(iArr.length, iArr, 0);
    }

    public void renderFrame(AlphaConfig alphaConfig) {
        int i2;
        int i3;
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        if (this.surfaceSizeChanged && (i2 = this.surfaceWidth) > 0 && (i3 = this.surfaceHeight) > 0) {
            this.surfaceSizeChanged = true;
            GLES20.glViewport(0, 0, i2, i3);
        }
        draw();
    }

    public void setAnimConfig(AlphaConfig alphaConfig) {
        setVertexBuf(alphaConfig);
        setTexCoords(alphaConfig);
    }

    public void swapBuffers() {
        this.eglUtil.swapBuffers();
    }

    public void updateViewPort(int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.surfaceSizeChanged = true;
        this.surfaceWidth = i2;
        this.surfaceHeight = i3;
    }
}
