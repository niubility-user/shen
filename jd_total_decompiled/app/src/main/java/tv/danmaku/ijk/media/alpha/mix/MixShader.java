package tv.danmaku.ijk.media.alpha.mix;

import android.opengl.GLES20;
import tv.danmaku.ijk.media.alpha.util.ShaderUtil;

/* loaded from: classes11.dex */
public class MixShader {
    private static final String A_POSITION = "a_Position";
    private static final String A_TEXTURE_MASK_COORDINATES = "a_TextureMaskCoordinates";
    private static final String A_TEXTURE_SRC_COORDINATES = "a_TextureSrcCoordinates";
    private static final String FRAGMENT = "#extension GL_OES_EGL_image_external : require\nprecision mediump float; \nuniform sampler2D u_TextureSrcUnit;\nuniform samplerExternalOES u_TextureMaskUnit;\nuniform int u_isFill;\nuniform vec4 u_Color;\nvarying vec2 v_TextureSrcCoordinates;\nvarying vec2 v_TextureMaskCoordinates;\nvoid main()\n{\n    vec4 srcRgba = texture2D(u_TextureSrcUnit, v_TextureSrcCoordinates);\n    vec4 maskRgba = texture2D(u_TextureMaskUnit, v_TextureMaskCoordinates);\n    float isFill = step(0.5, float(u_isFill));\n    vec4 srcRgbaCal = isFill * vec4(u_Color.r, u_Color.g, u_Color.b, srcRgba.a) + (1.0 - isFill) * srcRgba;\n    gl_FragColor = vec4(srcRgbaCal.r, srcRgbaCal.g, srcRgbaCal.b, srcRgba.a * maskRgba.r);\n}";
    private static final String TAG = "MixShader";
    private static final String U_COLOR = "u_Color";
    private static final String U_IS_FILL = "u_isFill";
    private static final String U_TEXTURE_MASK_UNIT = "u_TextureMaskUnit";
    private static final String U_TEXTURE_SRC_UNIT = "u_TextureSrcUnit";
    private static final String VERTEX = "attribute vec4 a_Position;  \nattribute vec2 a_TextureSrcCoordinates;\nattribute vec2 a_TextureMaskCoordinates;\nvarying vec2 v_TextureSrcCoordinates;\nvarying vec2 v_TextureMaskCoordinates;\nvoid main()\n{\n    v_TextureSrcCoordinates = a_TextureSrcCoordinates;\n    v_TextureMaskCoordinates = a_TextureMaskCoordinates;\n    gl_Position = a_Position;\n}";
    private int aPositionLocation;
    private int aTextureMaskCoordinatesLocation;
    private int aTextureSrcCoordinatesLocation;
    private int program;
    private int uColorLocation;
    private int uIsFillLocation;
    private int uTextureMaskUnitLocation;
    private int uTextureSrcUnitLocation;

    public MixShader() {
        int createProgram = ShaderUtil.createProgram(VERTEX, FRAGMENT);
        this.program = createProgram;
        this.uTextureSrcUnitLocation = GLES20.glGetUniformLocation(createProgram, U_TEXTURE_SRC_UNIT);
        this.uTextureMaskUnitLocation = GLES20.glGetUniformLocation(this.program, U_TEXTURE_MASK_UNIT);
        this.uIsFillLocation = GLES20.glGetUniformLocation(this.program, U_IS_FILL);
        this.uColorLocation = GLES20.glGetUniformLocation(this.program, U_COLOR);
        this.aPositionLocation = GLES20.glGetAttribLocation(this.program, A_POSITION);
        this.aTextureSrcCoordinatesLocation = GLES20.glGetAttribLocation(this.program, A_TEXTURE_SRC_COORDINATES);
        this.aTextureMaskCoordinatesLocation = GLES20.glGetAttribLocation(this.program, A_TEXTURE_MASK_COORDINATES);
    }

    public int getProgram() {
        return this.program;
    }

    public int getaPositionLocation() {
        return this.aPositionLocation;
    }

    public int getaTextureMaskCoordinatesLocation() {
        return this.aTextureMaskCoordinatesLocation;
    }

    public int getaTextureSrcCoordinatesLocation() {
        return this.aTextureSrcCoordinatesLocation;
    }

    public int getuColorLocation() {
        return this.uColorLocation;
    }

    public int getuIsFillLocation() {
        return this.uIsFillLocation;
    }

    public int getuTextureMaskUnitLocation() {
        return this.uTextureMaskUnitLocation;
    }

    public int getuTextureSrcUnitLocation() {
        return this.uTextureSrcUnitLocation;
    }

    public void useProgram() {
        GLES20.glUseProgram(this.program);
    }
}
