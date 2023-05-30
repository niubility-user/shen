package com.jingdong.common.XView2.layer.flexcube.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.hybrid.downloader.m.b;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IKnowWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.VideoEntity;
import com.jd.lib.flexcube.widgets.entity.video.VideoDataPath;
import com.jd.lib.flexcube.widgets.entity.video.VideoInfo;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.IBaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.IAlphaVideoView;
import tv.danmaku.ijk.media.alpha.scale.IScaleType;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;

/* loaded from: classes5.dex */
public class XViewVideoView extends FrameLayout implements IWidget<VideoEntity>, IKnowWidget, IBaseLayer {
    private String layerId;
    private String logicKey;
    private boolean loop;
    public ILayerCallBack mCallBack;
    public AtomicBoolean mHasResume;
    private boolean mIsAlpha;
    private AtomicBoolean mIsCompleted;
    private AtomicBoolean mIsHasVideoUrl;
    private JDPlayerView mPlayer;
    private VideoEntity mVideoEntity;
    private float multiple;
    private String name;
    private String playUrl;

    public XViewVideoView(Context context) {
        super(context);
        this.mIsHasVideoUrl = new AtomicBoolean(false);
        this.mIsCompleted = new AtomicBoolean(false);
        this.mHasResume = new AtomicBoolean(false);
        this.mIsAlpha = false;
        JDPlayerView jDPlayerView = new JDPlayerView(context);
        this.mPlayer = jDPlayerView;
        jDPlayerView.setClickable(false);
        addView(this.mPlayer, new ViewGroup.LayoutParams(-1, -1));
    }

    private boolean realStartPlay() {
        VideoDataPath videoDataPath;
        if (TextUtils.isEmpty(this.playUrl) || this.mPlayer == null) {
            return false;
        }
        this.loop = XView2Utils.isConvertBool(this.mVideoEntity.config.wifiPlayRule);
        if (this.mIsAlpha) {
            this.mPlayer.setPlayerOptions(new IPlayerControl.PlayerOptions(false).useAlpha(true).setAlphaOption(new IAlphaVideoView.AlphaOptions().setPlayTypeId(XView2Constants.PLAY_TYPE).enableMta(true).setStayLastFrame(true).setScaleType(IScaleType.ScaleType.CENTER_CROP).setLoop(this.loop)));
        } else {
            IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
            playerOptions.setPlayTypeId(XView2Constants.PLAY_TYPE);
            playerOptions.setCouldMediaCodec(true);
            playerOptions.setEnableAccurateSeek(true);
            playerOptions.showControllerOnStart(false);
            playerOptions.setReconnectCount(2);
            playerOptions.setUseCache(true);
            playerOptions.setStartOnPrepared(true);
            if ("2".equals(this.mVideoEntity.config.videoAutoFitType)) {
                playerOptions.setAspectRatio(0);
            } else if ("1".equals(this.mVideoEntity.config.videoAutoFitType)) {
                playerOptions.setAspectRatio(3);
            } else {
                playerOptions.setAspectRatio(1);
            }
            playerOptions.setLoop(this.loop);
            playerOptions.setVolume(0.0f);
            playerOptions.setIsRequestAudioFocus(false);
            playerOptions.setPlayerReportInfoEntity(new PlayerReportInfoEntity(this.layerId, XView2Constants.PLAY_TYPE));
            this.mPlayer.setPlayerOptions(playerOptions, new JDControllerOptions.Builder().fullMode(JDControllerOptions.FullMode.FULL_PORT).manualControlVisible(true).enableLoading(false).build());
        }
        VideoInfo videoInfo = this.mVideoEntity.config.videoInfo;
        this.mPlayer.setBackgroundColor(videoInfo != null ? com.jd.lib.flexcube.iwidget.b.a.a(videoInfo.bgColor, 0) : 0);
        this.mPlayer.setPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.XView2.layer.flexcube.view.XViewVideoView.1
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onCompletion ---- ");
                XViewVideoView.this.mIsCompleted.set(true);
                XViewVideoView xViewVideoView = XViewVideoView.this;
                if (xViewVideoView.mCallBack == null || !XView2Utils.isConvertBool(xViewVideoView.mVideoEntity.config.extern_autoClose)) {
                    return;
                }
                XViewVideoView xViewVideoView2 = XViewVideoView.this;
                xViewVideoView2.mCallBack.onLayerClosed(xViewVideoView2.layerId, 6);
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCreatePlayer() {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onCreatePlayer ---- ");
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "video_onError ---- i " + i2 + "i1 " + i3);
                XView2LayerObservableManager.getManager().notifyLayerShowError(XViewVideoView.this.layerId, XViewVideoView.this.logicKey);
                XViewVideoView xViewVideoView = XViewVideoView.this;
                ILayerCallBack iLayerCallBack = xViewVideoView.mCallBack;
                if (iLayerCallBack != null) {
                    iLayerCallBack.onLayerClosed(xViewVideoView.layerId, 7);
                }
                XView2Utils.reportXView2Error("videoPlayErr", "NXViewException", XViewVideoView.this.playUrl, i2 + "|" + i3, XViewVideoView.this.layerId, XViewVideoView.this.name, "");
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                XViewVideoView xViewVideoView;
                ILayerCallBack iLayerCallBack;
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onInfo ---- " + i2 + "|" + i3);
                if (i2 == 3 && (iLayerCallBack = (xViewVideoView = XViewVideoView.this).mCallBack) != null) {
                    iLayerCallBack.onLayerDisplayed(xViewVideoView.layerId);
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onInfo");
                }
                return false;
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onPrepared ---- l " + j2);
                XViewVideoView xViewVideoView = XViewVideoView.this;
                ILayerCallBack iLayerCallBack = xViewVideoView.mCallBack;
                if (iLayerCallBack != null) {
                    iLayerCallBack.onLayerLoading(xViewVideoView.layerId);
                }
            }

            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onSeekComplete() {
            }
        });
        VideoEntity videoEntity = this.mVideoEntity;
        if (videoEntity != null && (videoDataPath = videoEntity.dataPath) != null && !TextUtils.isEmpty(videoDataPath.videoUrl) && SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false)) {
            String finalLayerId = XView2Utils.getFinalLayerId(this.layerId);
            b files = XViewCache.getInstance().getFiles(finalLayerId + this.mVideoEntity.dataPath.videoUrl);
            if (files != null && !TextUtils.isEmpty(files.getFilePath())) {
                this.playUrl = files.getFilePath();
            }
        }
        this.mPlayer.setVideoPath(this.playUrl, JDPlayerView.PlayMode.AUTO_PLAY);
        this.mIsHasVideoUrl.set(true);
        if (!this.mIsAlpha) {
            this.mPlayer.start();
        }
        return true;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void changeLayoutCallBack(String str, String str2, int i2, String str3) {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        setVisibility(8);
        setOnClickListener(null);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        JDPlayerView jDPlayerView = this.mPlayer;
        if (jDPlayerView != null) {
            jDPlayerView.release();
            this.mPlayer = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void closeXView2Anim() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configControlBtn() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void configCurrentLayer(XViewConfigEntity.TargetsEntity targetsEntity, View view) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        JDPlayerView jDPlayerView = this.mPlayer;
        if (jDPlayerView != null) {
            jDPlayerView.release();
            this.mPlayer = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public BaseLayerDelegate getBaseLayerDelegate() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public View getContainer() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public String getLayerId() {
        return null;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        VideoEntity videoEntity = this.mVideoEntity;
        if (videoEntity != null) {
            return videoEntity.getBaseConfig().getH(this.multiple);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        VideoEntity videoEntity = this.mVideoEntity;
        if (videoEntity != null) {
            return videoEntity.getBaseConfig().getW(this.multiple);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IKnowWidget
    public BaseWidgetEntity getWidgetEntity() {
        return this.mVideoEntity;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void initParamsBeforeCreateLayer(JSONObject jSONObject) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public XView2Container initXViewContainer() {
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isFullScreen() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isJumpClose() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterImmediatelyPop() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isLayerEnterPop() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public boolean isRenderSuccess() {
        return false;
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLayerDisplayed(String str, String str2) {
        if (this.mIsHasVideoUrl.get()) {
            return;
        }
        realStartPlay();
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onLoadingLayer(String str, String str2) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        if ((this.loop && this.mIsCompleted.get()) || this.mPlayer == null) {
            return;
        }
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "pause");
        this.mPlayer.pause();
        this.mHasResume.set(false);
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        if (!this.mIsHasVideoUrl.get()) {
            realStartPlay();
        } else if ((this.loop && this.mIsCompleted.get()) || this.mPlayer == null || this.mHasResume.get()) {
        } else {
            if (!this.mIsAlpha) {
                this.mPlayer.start();
            }
            this.mHasResume.set(true);
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void releaseLayer() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setCurrentLayer(View view) {
    }

    protected void setOnClick(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ClickEvent a = com.jd.lib.flexcube.widgets.b.b.a(map, this.mVideoEntity.dataPath.clickEvent);
        if (a != null) {
            a.b bVar = new a.b(getContext(), a);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
        }
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void setTargetInfo(XViewConfigEntity.TargetsEntity targetsEntity) {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void showPrepared() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startTimeCountTimer() {
    }

    @Override // com.jingdong.common.XView2.layer.IBaseLayer
    public void startXView2popUpAnim() {
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        VideoEntity videoEntity = this.mVideoEntity;
        if (videoEntity != null && videoEntity.dataPath != null && videoEntity.config != null) {
            setVisibility(0);
            if (iWidgetCommunication != null && iWidgetCommunication.getBabelScope() != null) {
                IXViewWithFlexCube iXViewWithFlexCube = (IXViewWithFlexCube) iWidgetCommunication.getBabelScope().getService(IXViewWithFlexCube.class);
                this.layerId = iXViewWithFlexCube != null ? iXViewWithFlexCube.getLayerId() : "";
                this.logicKey = iXViewWithFlexCube != null ? iXViewWithFlexCube.getLogicKey() : "";
                this.name = iXViewWithFlexCube != null ? iXViewWithFlexCube.getLayerName() : "";
                this.mCallBack = iXViewWithFlexCube != null ? iXViewWithFlexCube.getLayerCallBack() : null;
            }
            this.playUrl = com.jd.lib.flexcube.widgets.b.b.d(map, this.mVideoEntity.dataPath.videoUrl);
            VideoEntity videoEntity2 = this.mVideoEntity;
            if (videoEntity2 != null && !TextUtils.isEmpty(videoEntity2.customConfig)) {
                try {
                    this.mIsAlpha = new JSONObject(this.mVideoEntity.customConfig).optBoolean(XView2Constants.IS_ALPHA);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (TextUtils.isEmpty(this.mVideoEntity.dataPath.clickEvent)) {
                setClickable(true);
                return;
            } else {
                setOnClick(map, iWidgetCommunication);
                return;
            }
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateStyle(@NonNull VideoEntity videoEntity, float f2) {
        this.mVideoEntity = videoEntity;
        this.multiple = f2;
    }
}
