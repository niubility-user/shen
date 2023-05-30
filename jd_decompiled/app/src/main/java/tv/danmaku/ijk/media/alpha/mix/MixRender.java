package tv.danmaku.ijk.media.alpha.mix;

import android.opengl.GLES20;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.decoder.Decoder;
import tv.danmaku.ijk.media.alpha.mix.Src;
import tv.danmaku.ijk.media.alpha.util.GlFloatArray;
import tv.danmaku.ijk.media.alpha.util.TexCoordsUtil;
import tv.danmaku.ijk.media.alpha.util.TextureLoadUtil;
import tv.danmaku.ijk.media.alpha.util.VertexUtil;

/* loaded from: classes11.dex */
public class MixRender {
    private static final String TAG = "MixRender";
    private MixAlphaPlugin mixAlphaPlugin;
    private MixShader shader;
    private GlFloatArray vertexArray = new GlFloatArray();
    private GlFloatArray srcArray = new GlFloatArray();
    private GlFloatArray maskArray = new GlFloatArray();

    public MixRender(MixAlphaPlugin mixAlphaPlugin) {
        this.mixAlphaPlugin = mixAlphaPlugin;
    }

    private float[] genSrcCoordsArray(float[] fArr, int i2, int i3, int i4, int i5, Src.FitType fitType) {
        AlphaConfig.PointRect pointRect;
        if (fitType == Src.FitType.CENTER_FULL) {
            if (i2 <= i4 && i3 <= i5) {
                return TexCoordsUtil.create(i4, i5, new AlphaConfig.PointRect((i4 - i2) / 2, (i5 - i3) / 2, i2, i3), fArr);
            }
            float f2 = (i2 * 1.0f) / i3;
            float f3 = i4;
            float f4 = i5;
            if (f2 > (1.0f * f3) / f4) {
                int i6 = (int) (f3 / f2);
                pointRect = new AlphaConfig.PointRect(0, (i5 - i6) / 2, i4, i6);
            } else {
                int i7 = (int) (f4 * f2);
                pointRect = new AlphaConfig.PointRect((i4 - i7) / 2, 0, i7, i5);
            }
            return TexCoordsUtil.create(i4, i5, pointRect, fArr);
        }
        return TexCoordsUtil.create(i2, i3, new AlphaConfig.PointRect(0, 0, i2, i3), fArr);
    }

    private float[] transColor(int i2) {
        return new float[]{((i2 >>> 24) & 255) / 255.0f, ((i2 >>> 16) & 255) / 255.0f, ((i2 >>> 8) & 255) / 255.0f, (i2 & 255) / 255.0f};
    }

    public void init() {
        this.shader = new MixShader();
        GLES20.glDisable(R2.color.Red_190);
        if (this.mixAlphaPlugin.getSrcMap() == null || this.mixAlphaPlugin.getSrcMap().getMap() == null) {
            return;
        }
        for (Src src : this.mixAlphaPlugin.getSrcMap().getMap().values()) {
            if (src != null) {
                String str = "init srcId = " + src.getSrcId();
                src.setSrcTextureId(TextureLoadUtil.loadTexture(src.getBitmap()));
                String str2 = "textureProgram = " + this.shader.getProgram() + ", textureId = " + src.getSrcTextureId();
            }
        }
    }

    public void release(int i2) {
        if (i2 != 0) {
            GLES20.glDeleteTextures(1, new int[]{i2}, 0);
        }
    }

    public void renderFrame(AlphaConfig alphaConfig, Frame frame, Src src) {
        Decoder decoder;
        int externalTexture;
        MixShader mixShader;
        MixAlphaPlugin mixAlphaPlugin = this.mixAlphaPlugin;
        if (mixAlphaPlugin == null || mixAlphaPlugin.getPlayer() == null || (decoder = this.mixAlphaPlugin.getPlayer().getDecoder()) == null || decoder.getRender() == null || (externalTexture = decoder.getRender().getExternalTexture()) <= 0 || (mixShader = this.shader) == null) {
            return;
        }
        mixShader.useProgram();
        this.vertexArray.setArray(VertexUtil.create(alphaConfig.width, alphaConfig.height, frame.getFrame(), this.vertexArray.floatArray));
        this.vertexArray.setVertexAttribPointer(this.shader.getaPositionLocation());
        GlFloatArray glFloatArray = this.srcArray;
        glFloatArray.setArray(genSrcCoordsArray(glFloatArray.floatArray, frame.getFrame().w, frame.getFrame().f20500h, src.getDrawWidth(), src.getDrawHeight(), src.getFitType()));
        this.srcArray.setVertexAttribPointer(this.shader.getaTextureSrcCoordinatesLocation());
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(R2.color.c_FF0017, src.getSrcTextureId());
        GLES20.glUniform1i(this.shader.getuTextureSrcUnitLocation(), 0);
        this.maskArray.setArray(TexCoordsUtil.create(alphaConfig.videoWidth, alphaConfig.videoHeight, frame.getFrame(), this.maskArray.floatArray));
        if (frame.getMt() == 90) {
            GlFloatArray glFloatArray2 = this.maskArray;
            glFloatArray2.setArray(TexCoordsUtil.rotate90(glFloatArray2.floatArray));
        }
        this.maskArray.setVertexAttribPointer(this.shader.getaTextureMaskCoordinatesLocation());
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(36197, externalTexture);
        GLES20.glUniform1i(this.shader.getuTextureMaskUnitLocation(), 1);
        if (src.getSrcType() == Src.SrcType.TXT && this.mixAlphaPlugin.isAutoTxtColorFill()) {
            GLES20.glUniform1i(this.shader.getuIsFillLocation(), 1);
            float[] transColor = transColor(src.getColor());
            GLES20.glUniform4f(this.shader.getuColorLocation(), transColor[1], transColor[2], transColor[3], transColor[0]);
        } else {
            GLES20.glUniform1i(this.shader.getuIsFillLocation(), 0);
            GLES20.glUniform4f(this.shader.getuColorLocation(), 0.0f, 0.0f, 0.0f, 0.0f);
        }
        GLES20.glEnable(R2.color.bankcard_jdmall_dialog_style_color);
        GLES20.glBlendFuncSeparate(R2.attr.dividerHorizontal, R2.attr.dividerPadding, 1, R2.attr.dividerPadding);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisable(R2.color.bankcard_jdmall_dialog_style_color);
    }
}
