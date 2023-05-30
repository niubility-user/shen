package tv.danmaku.ijk.media.alpha.mask;

import android.view.MotionEvent;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.IAlphaPlugin;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;

/* loaded from: classes11.dex */
public class MaskAlphaPlugin implements IAlphaPlugin {
    private static final String TAG = "MaskAlphaPlugin";
    private AlphaConfig alphaConfig;
    private MaskRender maskRender;
    private AlphaPlayer player;

    public MaskAlphaPlugin(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
    }

    private void destroy() {
        MaskConfig maskConfig;
        AlphaConfig alphaConfig = this.alphaConfig;
        if (alphaConfig == null || (maskConfig = alphaConfig.maskConfig) == null) {
            return;
        }
        maskConfig.release();
    }

    public AlphaPlayer getPlayer() {
        return this.player;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public int onConfigCreate(AlphaConfig alphaConfig) {
        return 0;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onDecoding(int i2) {
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onDestroy() {
        destroy();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public boolean onDispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onRelease() {
        destroy();
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onRenderCreate() {
        if (this.player.isSupportMaskBoolean()) {
            MaskRender maskRender = new MaskRender(this);
            this.maskRender = maskRender;
            maskRender.initMaskShader(this.player.isMaskEdgeBlurBoolean());
        }
    }

    @Override // tv.danmaku.ijk.media.alpha.IAlphaPlugin
    public void onRendering(int i2) {
        AlphaConfig alphaConfig;
        if (!this.player.isSupportMaskBoolean() || (alphaConfig = this.player.configManager.config) == null) {
            return;
        }
        this.alphaConfig = alphaConfig;
        this.maskRender.renderFrame(alphaConfig);
    }
}
