package tv.danmaku.ijk.media.alpha.mask;

import android.opengl.GLES20;
import tv.danmaku.ijk.media.alpha.util.ShaderUtil;

/* loaded from: classes11.dex */
public class MaskShader {
    private static final String A_POSITION = "vPosition";
    private static final String A_TEXTURE_MASK_COORDINATES = "vTexCoordinateAlphaMask";
    private static final String FRAGMENT_BLUR_EDGE = "precision mediump float;\nuniform sampler2D uTextureAlphaMask;\nvarying vec2 v_TexCoordinateAlphaMask;\nmat3 weight = mat3(0.0625,0.125,0.0625,0.125,0.25,0.125,0.0625,0.125,0.0625);\n int coreSize=3;\nfloat texelOffset = .01;\n\nvoid main() {\n   float alphaResult = 0.;\n   for(int y = 0; y < coreSize; y++) {\n       for(int x = 0;x < coreSize; x++) {\n           alphaResult += texture2D(uTextureAlphaMask, vec2(v_TexCoordinateAlphaMask.x + (-1.0 + float(x)) * texelOffset,v_TexCoordinateAlphaMask.y + (-1.0 + float(y)) * texelOffset)).a * weight[x][y];\n       }\n    }\n    gl_FragColor = vec4(0, 0, 0, alphaResult);\n}";
    private static final String FRAGMENT_NO_BLUR_EDGE = "precision mediump float;\nuniform sampler2D uTextureAlphaMask;\nvarying vec2 v_TexCoordinateAlphaMask;\n\nvoid main () {\n    vec4 alphaMaskColor = texture2D(uTextureAlphaMask, v_TexCoordinateAlphaMask);\n    gl_FragColor = vec4(0, 0, 0, alphaMaskColor.a);\n}";
    private static final String FRAGMENT_ROW = "precision mediump float;\nuniform sampler2D uTextureAlphaMask;\nvarying vec2 v_TexCoordinateAlphaMask;\nvec3 weight = vec3(0.4026,0.2442,0.0545);\n \nvoid main() {\n   float texelOffset = .01;\n   vec2 uv[5];\n   uv[0]= v_TexCoordinateAlphaMask;\n   uv[1]=vec2(uv[0].x+texelOffset*1.0,  uv[0].y);\n   uv[2]=vec2(uv[0].x-texelOffset*1.0,  uv[0].y);\n   uv[3]=vec2(uv[0].x+texelOffset*2.0,  uv[0].y);\n   uv[4]=vec2(uv[0].x-texelOffset*2.0,  uv[0].y);\n   float alphaResult = texture2D(uTextureAlphaMask, uv[0]).a * weight[0];\n   for(int i = 1; i < 3; ++i) {\n       alphaResult += texture2D(uTextureAlphaMask, uv[2*i-1]).a * weight[i];\n       alphaResult += texture2D(uTextureAlphaMask, uv[2*i]).a * weight[i];\n    }\n    gl_FragColor = vec4(0, 0, 0, alphaResult);\n}";
    private static final String TAG = "MaskShader";
    private static final String U_TEXTURE_ALPHA_MASK_UNIT = "uTextureAlphaMask";
    private static final String VERTEX = "attribute vec4 vPosition;\nattribute vec4 vTexCoordinateAlphaMask;\nvarying vec2 v_TexCoordinateAlphaMask;\n\nvoid main() {\n    v_TexCoordinateAlphaMask = vec2(vTexCoordinateAlphaMask.x, vTexCoordinateAlphaMask.y);\n    gl_Position = vPosition;\n}";
    private int aPositionLocation;
    private int aTextureMaskCoordinatesLocation;
    private int program;
    private int uTextureMaskUnitLocation;

    public MaskShader(boolean z) {
        int createProgram = ShaderUtil.createProgram(VERTEX, z ? FRAGMENT_BLUR_EDGE : FRAGMENT_NO_BLUR_EDGE);
        this.program = createProgram;
        this.uTextureMaskUnitLocation = GLES20.glGetUniformLocation(createProgram, U_TEXTURE_ALPHA_MASK_UNIT);
        this.aPositionLocation = GLES20.glGetAttribLocation(this.program, A_POSITION);
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

    public int getuTextureMaskUnitLocation() {
        return this.uTextureMaskUnitLocation;
    }

    public void useProgram() {
        GLES20.glUseProgram(this.program);
    }
}
