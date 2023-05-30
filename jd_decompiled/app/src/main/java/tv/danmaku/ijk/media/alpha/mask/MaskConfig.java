package tv.danmaku.ijk.media.alpha.mask;

import android.graphics.Bitmap;
import android.util.Pair;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.util.TextureLoadUtil;

/* loaded from: classes11.dex */
public class MaskConfig {
    private static final String TAG = "MaskConfig";
    private Bitmap alphaMaskBitmap;
    private Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> maskPositionPair;
    private int maskTexId = 0;
    private Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> maskTexPair;

    public MaskConfig() {
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof MaskConfig) {
            MaskConfig maskConfig = (MaskConfig) obj;
            if (this.alphaMaskBitmap != maskConfig.alphaMaskBitmap) {
                Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair = this.maskPositionPair;
                Object obj2 = pair.first;
                Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair2 = maskConfig.maskPositionPair;
                if (obj2 != pair2.first && pair.second != pair2.second) {
                    Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair3 = this.maskTexPair;
                    Object obj3 = pair3.first;
                    Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair4 = maskConfig.maskTexPair;
                    if (obj3 != pair4.first && pair3.second != pair4.second) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Bitmap getAlphaMaskBitmap() {
        return this.alphaMaskBitmap;
    }

    public Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> getMaskPositionPair() {
        return this.maskPositionPair;
    }

    public int getMaskTexId() {
        return this.maskTexId;
    }

    public Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> getMaskTexPair() {
        return this.maskTexPair;
    }

    public void release() {
        this.alphaMaskBitmap = null;
        this.maskTexPair = null;
        this.maskPositionPair = null;
    }

    public void safeSetMaskBitmapAndReleasePre(Bitmap bitmap) {
        int i2 = this.maskTexId;
        if (i2 > 0) {
            TextureLoadUtil.releaseTexture(i2);
            this.maskTexId = 0;
        }
        this.alphaMaskBitmap = bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.alphaMaskBitmap = bitmap;
    }

    public void setMaskPositionPair(Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair) {
        this.maskPositionPair = pair;
    }

    public void setMaskTexPair(Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair) {
        this.maskTexPair = pair;
    }

    public int updateMaskTex() {
        int loadTexture = TextureLoadUtil.loadTexture(this.alphaMaskBitmap);
        this.maskTexId = loadTexture;
        return loadTexture;
    }

    public MaskConfig(Bitmap bitmap, Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair, Pair<AlphaConfig.PointRect, AlphaConfig.RefVec2> pair2) {
        this.maskTexPair = pair2;
        this.maskPositionPair = pair;
        this.alphaMaskBitmap = bitmap;
    }
}
