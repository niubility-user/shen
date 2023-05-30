package tv.danmaku.ijk.media.alpha.mask;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.jingdong.sdk.platform.business.personal.R2;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.util.GlFloatArray;
import tv.danmaku.ijk.media.alpha.util.TexCoordsUtil;
import tv.danmaku.ijk.media.alpha.util.VertexUtil;

/* loaded from: classes11.dex */
public class MaskRender {
    private static final String TAG = "MaskRender";
    private MaskAlphaPlugin maskAlphaPlugin;
    private GlFloatArray maskArray;
    private MaskShader maskShader;
    private GlFloatArray vertexArray;

    public MaskRender(MaskAlphaPlugin maskAlphaPlugin) {
        this.maskAlphaPlugin = maskAlphaPlugin;
    }

    public void initMaskShader(boolean z) {
        this.maskShader = new MaskShader(z);
        GLES20.glDisable(R2.color.Red_190);
    }

    public void renderFrame(AlphaConfig alphaConfig) {
        MaskShader maskShader;
        MaskConfig maskConfig;
        if (this.maskAlphaPlugin.getPlayer().getDecoder() == null || this.maskAlphaPlugin.getPlayer().getDecoder().getRender() == null || this.maskAlphaPlugin.getPlayer().getDecoder().getRender().getExternalTexture() <= 0 || (maskShader = this.maskShader) == null || alphaConfig == null || (maskConfig = alphaConfig.maskConfig) == null) {
            return;
        }
        int maskTexId = maskConfig.getMaskTexId();
        MaskConfig maskConfig2 = alphaConfig.maskConfig;
        if (maskConfig2 == null || maskConfig2.getAlphaMaskBitmap() == null) {
            return;
        }
        Bitmap alphaMaskBitmap = alphaConfig.maskConfig.getAlphaMaskBitmap();
        if (alphaConfig.maskConfig.getMaskTexPair() == null) {
            return;
        }
        AlphaConfig.PointRect pointRect = (AlphaConfig.PointRect) alphaConfig.maskConfig.getMaskTexPair().first;
        AlphaConfig.RefVec2 refVec2 = (AlphaConfig.RefVec2) alphaConfig.maskConfig.getMaskTexPair().second;
        if (pointRect == null || refVec2 == null || alphaConfig.maskConfig.getMaskPositionPair() == null) {
            return;
        }
        AlphaConfig.PointRect pointRect2 = (AlphaConfig.PointRect) alphaConfig.maskConfig.getMaskPositionPair().first;
        if (pointRect2 == null) {
            pointRect2 = new AlphaConfig.PointRect(0, 0, alphaConfig.width, alphaConfig.height);
        }
        AlphaConfig.RefVec2 refVec22 = (AlphaConfig.RefVec2) alphaConfig.maskConfig.getMaskPositionPair().second;
        if (refVec22 == null) {
            refVec22 = new AlphaConfig.RefVec2(alphaConfig.width, alphaConfig.height);
        }
        maskShader.useProgram();
        GlFloatArray glFloatArray = this.vertexArray;
        glFloatArray.setArray(VertexUtil.create(refVec22.w, refVec22.f20501h, pointRect2, glFloatArray.floatArray));
        this.vertexArray.setVertexAttribPointer(maskShader.getaPositionLocation());
        if (maskTexId <= 0 && !alphaMaskBitmap.isRecycled()) {
            maskTexId = alphaConfig.maskConfig.updateMaskTex();
        }
        if (maskTexId > 0) {
            GlFloatArray glFloatArray2 = this.maskArray;
            glFloatArray2.setArray(TexCoordsUtil.create(refVec2.w, refVec2.f20501h, pointRect, glFloatArray2.floatArray));
            this.maskArray.setVertexAttribPointer(maskShader.getaTextureMaskCoordinatesLocation());
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(R2.color.c_FF0017, maskTexId);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal, 9728.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, 10240, 9729.0f);
            GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_personal_normal_dark, 33071);
            GLES20.glTexParameteri(36197, R2.drawable.main_bottom_tab_search_focus, 33071);
            GLES20.glUniform1i(maskShader.getuTextureMaskUnitLocation(), 0);
            GLES20.glEnable(R2.color.bankcard_jdmall_dialog_style_color);
            GLES20.glBlendFuncSeparate(1, R2.attr.dividerHorizontal, 0, R2.attr.dividerHorizontal);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisable(R2.color.bankcard_jdmall_dialog_style_color);
        }
    }
}
