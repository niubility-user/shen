package tv.danmaku.ijk.media.alpha;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.alpha.mask.MaskConfig;
import tv.danmaku.ijk.media.alpha.scale.IScaleType;
import tv.danmaku.ijk.media.ext.mta.bean.AlphaReportInfoEntity;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public abstract class IAlphaVideoView extends IJDVideoPlayer {

    /* loaded from: classes11.dex */
    public static class AlphaOptions {
        protected String playTypeId = "181";
        protected boolean enableReport = false;
        protected boolean isLoop = false;
        protected boolean enableMta = false;
        protected boolean stayLastFrame = false;
        protected IScaleType.ScaleType scaleType = IScaleType.ScaleType.FIT_XY;
        protected int renderType = 3;
        protected AlphaReportInfoEntity alphaReportInfoEntity = null;

        public AlphaOptions enableMta(boolean z) {
            this.enableMta = z;
            return this;
        }

        public AlphaOptions enableReport(boolean z) {
            this.enableReport = z;
            return this;
        }

        public AlphaReportInfoEntity getAlphaReportInfoEntity() {
            return this.alphaReportInfoEntity;
        }

        public String getPlayTypeId() {
            return this.playTypeId;
        }

        public int getRenderType() {
            return this.renderType;
        }

        public IScaleType.ScaleType getScaleType() {
            return this.scaleType;
        }

        public boolean isEnableMta() {
            return this.enableMta;
        }

        public boolean isEnableReport() {
            return this.enableReport;
        }

        public boolean isLoop() {
            return this.isLoop;
        }

        public boolean isStayLastFrame() {
            return this.stayLastFrame;
        }

        public AlphaOptions setAlphaReportInfoEntity(AlphaReportInfoEntity alphaReportInfoEntity) {
            this.alphaReportInfoEntity = alphaReportInfoEntity;
            return this;
        }

        public AlphaOptions setLoop(boolean z) {
            this.isLoop = z;
            return this;
        }

        public AlphaOptions setPlayTypeId(String str) {
            this.playTypeId = str;
            return this;
        }

        public AlphaOptions setScaleType(IScaleType.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        public AlphaOptions setStayLastFrame(boolean z) {
            this.stayLastFrame = z;
            return this;
        }
    }

    public IAlphaVideoView(@NonNull Context context) {
        super(context);
    }

    protected abstract AlphaOptions getAlphaOptions();

    protected abstract Pair<Integer, Integer> getRealSize();

    protected abstract SurfaceTexture getSurfaceTexture();

    protected abstract void prepareTextureView();

    protected abstract void setAlphaListener(IAlphaListener iAlphaListener);

    protected abstract void setAlphaOption(AlphaOptions alphaOptions);

    protected abstract void setEventListener(IMediaPlayer.OnPlayerEventListener onPlayerEventListener);

    protected abstract void setFetchResources(IFetchResource iFetchResource);

    protected abstract void setMute(boolean z);

    protected abstract void setOnResourceClickListener(OnResourceClickListener onResourceClickListener);

    protected abstract void supportMask(boolean z, boolean z2);

    protected abstract void updateMaskConfig(MaskConfig maskConfig);

    public IAlphaVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IAlphaVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public IAlphaVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
