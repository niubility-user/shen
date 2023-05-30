package com.jingdong.common.XView2.layer.video;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.common.XView2OutlineProvider;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;

/* loaded from: classes5.dex */
public class VideoPlayerLayer extends BaseLayer {
    private AtomicBoolean mIsCompleted;
    private AtomicBoolean mIsHasVideoUrl;
    private XView2VideoView mPlayer;

    public VideoPlayerLayer(Activity activity, Object obj, XView2 xView2) {
        super(activity, obj, xView2);
        this.mIsHasVideoUrl = new AtomicBoolean(false);
        this.mIsCompleted = new AtomicBoolean(false);
    }

    private boolean realStartPlay() {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null || this.mPlayer == null || TextUtils.isEmpty(renderDataEntity.url)) {
            return false;
        }
        IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
        playerOptions.setPlayTypeId(XView2Constants.PLAY_TYPE);
        playerOptions.setCouldMediaCodec(true);
        playerOptions.setEnableAccurateSeek(true);
        playerOptions.setReconnectCount(2);
        playerOptions.setUseCache(true);
        playerOptions.setStartOnPrepared(false);
        if ("1".equals(this.layersEntity.renderData.contentMode)) {
            playerOptions.setAspectRatio(0);
        } else if ("2".equals(this.layersEntity.renderData.contentMode)) {
            playerOptions.setAspectRatio(3);
        } else {
            playerOptions.setAspectRatio(1);
        }
        playerOptions.setLoop(XView2Utils.isConvertBool(this.layersEntity.renderData.loop));
        playerOptions.setVolume(0.0f);
        playerOptions.setIsRequestAudioFocus(false);
        playerOptions.setPlayerReportInfoEntity(new PlayerReportInfoEntity(this.layersEntity.layerId, XView2Constants.PLAY_TYPE));
        this.mPlayer.setPlayerOptions(playerOptions);
        this.mPlayer.setBackgroundColor(0);
        this.mPlayer.setOnPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.XView2.layer.video.VideoPlayerLayer.1
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onCompletion ---- ");
                VideoPlayerLayer.this.mIsCompleted.set(true);
                VideoPlayerLayer videoPlayerLayer = VideoPlayerLayer.this;
                if (videoPlayerLayer.mCallBack == null || XView2Utils.isConvertBool(videoPlayerLayer.layersEntity.renderData.disableCloseAfterEnd)) {
                    return;
                }
                VideoPlayerLayer videoPlayerLayer2 = VideoPlayerLayer.this;
                videoPlayerLayer2.mCallBack.onLayerClosed(videoPlayerLayer2.layersEntity.layerId, 6);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onCreatePlayer ---- ");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "video_onError ---- i " + i2 + "i1 " + i3);
                XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                XViewConfigEntity.LayersEntity layersEntity2 = VideoPlayerLayer.this.layersEntity;
                manager.notifyLayerShowError(layersEntity2.layerId, layersEntity2.logicKey);
                VideoPlayerLayer videoPlayerLayer = VideoPlayerLayer.this;
                ILayerCallBack iLayerCallBack = videoPlayerLayer.mCallBack;
                if (iLayerCallBack != null) {
                    iLayerCallBack.onLayerClosed(videoPlayerLayer.layersEntity.layerId, 7);
                }
                String str = VideoPlayerLayer.this.layersEntity.renderData.url;
                String str2 = i2 + "|" + i3;
                XViewConfigEntity.LayersEntity layersEntity3 = VideoPlayerLayer.this.layersEntity;
                XView2Utils.reportXView2Error("videoPlayErr", "NXViewException", str, str2, layersEntity3.layerId, layersEntity3.name, "");
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                VideoPlayerLayer videoPlayerLayer;
                ILayerCallBack iLayerCallBack;
                XViewConfigEntity.LayersEntity layersEntity2;
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onInfo ---- " + i2 + "|" + i3);
                if (i2 == 3 && (iLayerCallBack = (videoPlayerLayer = VideoPlayerLayer.this).mCallBack) != null && (layersEntity2 = videoPlayerLayer.layersEntity) != null) {
                    iLayerCallBack.onLayerDisplayed(layersEntity2.layerId);
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onInfo");
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                XViewConfigEntity.LayersEntity layersEntity2;
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onPrepared ---- l " + j2);
                VideoPlayerLayer videoPlayerLayer = VideoPlayerLayer.this;
                ILayerCallBack iLayerCallBack = videoPlayerLayer.mCallBack;
                if (iLayerCallBack == null || (layersEntity2 = videoPlayerLayer.layersEntity) == null) {
                    return;
                }
                iLayerCallBack.onLayerLoading(layersEntity2.layerId);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        });
        XViewConfigEntity.RuleEntity ruleEntity = this.layersEntity.rule;
        if (ruleEntity != null && XView2Utils.isConvertBool(ruleEntity.loadRequiredBefore)) {
            String md5 = Md5Encrypt.md5(this.layersEntity.layerId + this.layersEntity.renderData.url);
            String videoPathNameById = XView2VideoDownloadCommonUtil.getVideoPathNameById(PreDownLoadManager.VIDEO_SKU_BG_DIR, PreDownLoadManager.VIDEO_SKU_SUFFIX, md5);
            if (!TextUtils.isEmpty(videoPathNameById)) {
                File file = new File(videoPathNameById);
                this.mPlayer.configLayer(this.layersEntity, null);
                if (TextUtils.isEmpty(PreDownLoadManager.isLegalFile(file, md5, PreDownLoadManager.VIDEO_SKU_PATH, PreDownLoadManager.VIDEO_SKU_SIZE, PreDownLoadManager.VIDEO_ID, PreDownLoadManager.VIDEO_URL))) {
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u672c\u5730\u4e0d\u6587\u4ef6\u5408\u6cd5");
                    if (this.mCallBack != null) {
                        XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                        XViewConfigEntity.LayersEntity layersEntity2 = this.layersEntity;
                        manager.notifyLayerShowError(layersEntity2.layerId, layersEntity2.logicKey);
                        this.mCallBack.onLayerClosed(this.layersEntity.layerId, 0);
                    }
                    return false;
                }
                this.mPlayer.setVideoPath(videoPathNameById);
            } else {
                if (this.mCallBack != null) {
                    XView2LayerObservableManager manager2 = XView2LayerObservableManager.getManager();
                    XViewConfigEntity.LayersEntity layersEntity3 = this.layersEntity;
                    manager2.notifyLayerShowError(layersEntity3.layerId, layersEntity3.logicKey);
                    this.mCallBack.onLayerClosed(this.layersEntity.layerId, 0);
                }
                return false;
            }
        } else {
            this.mPlayer.setVideoPath(this.layersEntity.renderData.url);
        }
        this.mIsHasVideoUrl.set(true);
        this.mPlayer.start();
        return true;
    }

    public void applyPlayerRadius(XView2VideoView xView2VideoView) {
        XViewConfigEntity.LayersEntity layersEntity;
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (xView2VideoView == null || (layersEntity = this.layersEntity) == null || (renderDataEntity = layersEntity.renderData) == null || (layoutEntity = renderDataEntity.layout) == null || layoutEntity.radius <= 0 || Build.VERSION.SDK_INT < 21) {
            return;
        }
        xView2VideoView.setOutlineProvider(new XView2OutlineProvider(XView2Utils.getSizeBy750((int) this.layersEntity.renderData.layout.radius)));
        xView2VideoView.setClipToOutline(true);
        xView2VideoView.requestLayout();
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        XView2VideoView xView2VideoView = this.mPlayer;
        if (xView2VideoView != null) {
            xView2VideoView.release();
            this.mPlayer = null;
        }
        super.closeLayer(str, i2);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer
    public View createLayer(XView2Container xView2Container, Object obj, ILayerCallBack iLayerCallBack) {
        XViewConfigEntity.LayersEntity layersEntity;
        if (this.mContext == null || (layersEntity = this.layersEntity) == null || layersEntity.renderData == null) {
            return null;
        }
        this.mCallBack = iLayerCallBack;
        this.mLayerState = BaseLayer.LAYERESTATE.CREATE;
        if (this.mPlayer == null) {
            XView2VideoView xView2VideoView = new XView2VideoView(this.mContext);
            this.mPlayer = xView2VideoView;
            xView2VideoView.setClickable(false);
        }
        applyPlayerRadius(this.mPlayer);
        return this.mPlayer;
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        XView2VideoView xView2VideoView = this.mPlayer;
        if (xView2VideoView != null) {
            xView2VideoView.release();
            this.mPlayer = null;
        }
        super.destroyLayer();
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        super.onPause();
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if ((layersEntity == null || (renderDataEntity = layersEntity.renderData) == null || !XView2Utils.isConvertBool(renderDataEntity.loop) || !this.mIsCompleted.get()) && this.mPlayer != null) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "pause");
            this.mPlayer.pause();
            this.mHasResume.set(false);
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        super.onResume();
        if (!this.mIsHasVideoUrl.get()) {
            realStartPlay();
        } else if ((XView2Utils.isConvertBool(this.layersEntity.renderData.loop) && this.mIsCompleted.get()) || this.mPlayer == null || this.mHasResume.get()) {
        } else {
            this.mPlayer.start();
            this.mHasResume.set(true);
        }
    }
}
