package com.jingdong.common.recommend;

import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.IPauseListener;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.common.recommend.ui.video.RecommendVideoPlayState;
import com.jingdong.common.recommend.ui.video.RecommendVideoWidget;
import com.jingdong.common.utils.JDSettingUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendVideoManagerNew implements IResumeListener, IPauseListener, IDestroyListener {
    private int firstVisible;
    private final RecyclerView mRecommendView;
    private RecommendVideoWidget mVideoPlayView;
    private final IMyActivity myActivity;
    private final int pageFrom;
    private final RecommendProductManager productManager;
    private int mScrollState = 0;
    private ArrayList<RecommendVideoWidget> cacheVideo = new ArrayList<>();
    private boolean isDestroy = false;
    public boolean isResume = true;

    public RecommendVideoManagerNew(RecyclerView recyclerView, IMyActivity iMyActivity, int i2, RecommendProductManager recommendProductManager) {
        this.myActivity = iMyActivity;
        this.mRecommendView = recyclerView;
        this.productManager = recommendProductManager;
        this.pageFrom = i2;
        if (iMyActivity != null) {
            iMyActivity.addPauseListener(this);
            iMyActivity.addResumeListener(this);
            iMyActivity.addDestroyListener(this);
        }
    }

    private void collectVideoView(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        this.firstVisible = getFirstVisibleItem(recyclerView);
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            View findViewById = recyclerView.getChildAt(i2).findViewById(R.id.recommend_widget_new);
            if (findViewById instanceof RecommendVideoWidget) {
                RecommendVideoWidget recommendVideoWidget = (RecommendVideoWidget) findViewById;
                if (recommendVideoWidget.hasVideoData()) {
                    recommendVideoWidget.setTag(R.id.recommend_vp_left, Integer.valueOf(this.firstVisible + i2));
                    recommendVideoWidget.rootParentView = recyclerView.getChildAt(i2);
                    if (inScreen(recommendVideoWidget) && recommendVideoWidget.canPlayVideo()) {
                        this.cacheVideo.add(recommendVideoWidget);
                    }
                }
            }
        }
        RecommendVideoWidget recommendVideoWidget2 = this.mVideoPlayView;
        if (recommendVideoWidget2 != null) {
            recommendVideoWidget2.setLoop(false);
        }
    }

    private int getFirstVisibleItem(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] iArr = new int[2];
            ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr);
            return Math.min(iArr[0], iArr[1]);
        } else if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            if (layoutManager instanceof LinearLayoutManager) {
                return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }
            return -1;
        }
    }

    private RecommendVideoWidget getVideoAutoPlayPosition() {
        if (this.cacheVideo.size() != 0) {
            RecommendVideoWidget recommendVideoWidget = this.cacheVideo.get(0);
            this.cacheVideo.remove(0);
            if (recommendVideoWidget.enableLoop()) {
                recommendVideoWidget.setLoop(this.cacheVideo.size() == 0);
            }
            return recommendVideoWidget;
        }
        return null;
    }

    private String getVideoPlayExpoParam(RecommendVideo recommendVideo, boolean z) {
        String str;
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        String str2 = recommendVideo.wareId;
        String str3 = recommendVideo.reqsig;
        String valueOf2 = String.valueOf(recommendVideo.videoDuration);
        RecommendVideoWidget recommendVideoWidget = this.mVideoPlayView;
        if (recommendVideoWidget != null) {
            str = String.valueOf((z ? recommendVideoWidget.getDuration() : recommendVideoWidget.getCurrentPosition()) / 1000);
        } else {
            str = "-100";
        }
        return valueOf + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + str3 + CartConstant.KEY_YB_INFO_LINK + valueOf2 + CartConstant.KEY_YB_INFO_LINK + str + CartConstant.KEY_YB_INFO_LINK + recommendVideo.brokerInfo + CartConstant.KEY_YB_INFO_LINK + (z ? 1 : 0);
    }

    private boolean inScreen(RecommendVideoWidget recommendVideoWidget) {
        View view;
        if (!this.isResume || recommendVideoWidget == null || (view = recommendVideoWidget.rootParentView) == null || view.getParent() == null) {
            return false;
        }
        int[] iArr = new int[2];
        recommendVideoWidget.getLocationInWindow(iArr);
        return iArr[1] <= 5 ? this.pageFrom == 24 ? RecommendViewUtil.getCurrentExpoPercent(recommendVideoWidget.rootParentView) > 0.5d : RecommendViewUtil.getCurrentExpoPercent(recommendVideoWidget.rootParentView) > 0.3d : RecommendViewUtil.getCurrentExpoPercent(recommendVideoWidget.rootParentView) >= 0.1d;
    }

    private void playVideo(final RecommendVideoWidget recommendVideoWidget) {
        if (recommendVideoWidget != null) {
            try {
                final int intValue = ((Integer) recommendVideoWidget.getTag(R.id.recommend_vp_left)).intValue();
                setVideoPlayLogoVisibile(intValue, false);
                recommendVideoWidget.toPlayVideo(new RecommendVideoPlayState() { // from class: com.jingdong.common.recommend.RecommendVideoManagerNew.2
                    @Override // com.jingdong.common.recommend.ui.video.RecommendVideoPlayState, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                    public void onCompletion() {
                        super.onCompletion();
                        RecommendVideoManagerNew.this.sendVideoPlayTimeExpoMat(true);
                        RecommendVideoManagerNew.this.setVideoPlayLogoVisibile(intValue, true);
                        if (RecommendVideoManagerNew.this.cacheVideo.isEmpty()) {
                            return;
                        }
                        RecommendVideoManagerNew.this.videoAutoPlay();
                    }

                    @Override // com.jingdong.common.recommend.ui.video.RecommendVideoPlayState
                    public void onDetach() {
                        super.onDetach();
                        try {
                            if (recommendVideoWidget.isPlaying()) {
                                RecommendVideoManagerNew.this.sendVideoPlayTimeExpoMat(false);
                                recommendVideoWidget.releaseVideo();
                            }
                        } catch (Exception e2) {
                            if (OKLog.D) {
                                e2.printStackTrace();
                            }
                        }
                    }

                    @Override // com.jingdong.common.recommend.ui.video.RecommendVideoPlayState, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                    public boolean onError(int i2, int i3) {
                        RecommendVideoManagerNew.this.setVideoPlayLogoVisibile(intValue, true);
                        if (!RecommendVideoManagerNew.this.cacheVideo.isEmpty()) {
                            RecommendVideoManagerNew.this.videoAutoPlay();
                        }
                        return super.onError(i2, i3);
                    }

                    @Override // com.jingdong.common.recommend.ui.video.RecommendVideoPlayState, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                    public boolean onInfo(int i2, int i3) {
                        if (i2 == 30003) {
                            RecommendVideoManagerNew.this.sendVideoPlayTimeExpoMat(true);
                        }
                        return super.onInfo(i2, i3);
                    }

                    @Override // com.jingdong.common.recommend.ui.video.RecommendVideoPlayState, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                    public void onPrepared(long j2) {
                        super.onPrepared(j2);
                        RecommendVideoManagerNew.this.setVideoPlayLogoVisibile(intValue, false);
                    }
                }, false);
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendVideoPlayTimeExpoMat(boolean z) {
        RecommendVideoWidget recommendVideoWidget = this.mVideoPlayView;
        if (recommendVideoWidget != null && (recommendVideoWidget.getTag() instanceof RecommendVideo)) {
            RecommendVideo recommendVideo = (RecommendVideo) this.mVideoPlayView.getTag();
            if (recommendVideo.isProduct == 1) {
                RecommendMtaUtils.videoPlayTimeExpoMat(this.myActivity.getThisActivity(), getVideoPlayExpoParam(recommendVideo, z), RecommendUtils.getVideoPlayExpoJsonParam(recommendVideo, this.mVideoPlayView.getCurrentPosition(), z).toString(), this.pageFrom);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoPlayLogoVisibile(int i2, boolean z) {
        RecommendProductManager recommendProductManager = this.productManager;
        if (recommendProductManager != null) {
            if (z) {
                recommendProductManager.refreshListDataChanged(i2, 1, Boolean.TRUE);
            } else {
                recommendProductManager.refreshListDataChanged(i2, 1, Boolean.FALSE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoPlayState(RecyclerView recyclerView) {
        if (recyclerView.getParent() == null || !JDSettingUtils.isWifiVideoAutoPlay() || this.productManager.notPlay) {
            return;
        }
        this.cacheVideo.clear();
        collectVideoView(recyclerView);
        videoAutoPlay();
    }

    @Override // com.jingdong.common.frame.IDestroyListener
    public void onDestroy() {
        this.isDestroy = true;
        reset();
    }

    @Override // com.jingdong.common.frame.IPauseListener
    public void onPause() {
        this.isResume = false;
        RecommendVideoWidget recommendVideoWidget = this.mVideoPlayView;
        if (recommendVideoWidget != null) {
            recommendVideoWidget.pause();
        }
    }

    @Override // com.jingdong.common.frame.IResumeListener
    public void onResume() {
        this.isResume = true;
        if (inScreen(this.mVideoPlayView)) {
            this.mVideoPlayView.resume();
        }
    }

    public void onScrollStateChange(int i2) {
        this.cacheVideo.clear();
        this.mScrollState = i2;
        if (i2 != 0) {
            return;
        }
        if (this.mRecommendView.isComputingLayout()) {
            this.mRecommendView.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendVideoManagerNew.1
                @Override // java.lang.Runnable
                public void run() {
                    if (RecommendVideoManagerNew.this.isDestroy) {
                        return;
                    }
                    RecommendVideoManagerNew recommendVideoManagerNew = RecommendVideoManagerNew.this;
                    recommendVideoManagerNew.setVideoPlayState(recommendVideoManagerNew.mRecommendView);
                }
            });
        } else {
            setVideoPlayState(this.mRecommendView);
        }
    }

    public void onViewHidden() {
        RecommendVideoWidget recommendVideoWidget = this.mVideoPlayView;
        if (recommendVideoWidget != null) {
            setVideoPlayLogoVisibile(((Integer) recommendVideoWidget.getTag(R.id.recommend_vp_left)).intValue(), true);
            this.mVideoPlayView.release();
        }
    }

    public void reset() {
        RecommendVideoWidget recommendVideoWidget = this.mVideoPlayView;
        if (recommendVideoWidget != null) {
            if (recommendVideoWidget.isPlaying()) {
                sendVideoPlayTimeExpoMat(false);
            }
            this.mVideoPlayView.release();
        }
        this.cacheVideo.clear();
    }

    public void videoAutoPlay() {
        if (this.mScrollState != 0) {
            return;
        }
        RecommendVideoWidget recommendVideoWidget = this.mVideoPlayView;
        if (recommendVideoWidget != null && recommendVideoWidget.isPlaying() && inScreen(this.mVideoPlayView)) {
            return;
        }
        RecommendVideoWidget recommendVideoWidget2 = this.mVideoPlayView;
        if (recommendVideoWidget2 != null) {
            recommendVideoWidget2.release();
        }
        RecommendVideoWidget videoAutoPlayPosition = getVideoAutoPlayPosition();
        if (videoAutoPlayPosition != null) {
            this.mVideoPlayView = videoAutoPlayPosition;
            if (videoAutoPlayPosition != null) {
                if (videoAutoPlayPosition.isCanPlayWithNoWifi() || NetUtils.isWifi()) {
                    playVideo(this.mVideoPlayView);
                }
            }
        }
    }
}
