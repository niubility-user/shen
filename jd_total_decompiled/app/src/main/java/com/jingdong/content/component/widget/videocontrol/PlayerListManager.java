package com.jingdong.content.component.widget.videocontrol;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

/* loaded from: classes12.dex */
public class PlayerListManager {
    public static final String TAG = "PlayerListManager";
    private static volatile Map<String, PlayerListManager> instances = new ConcurrentHashMap();
    private boolean isVoiceOn;
    private IPlayerVideoHolder lastVideoHolde;
    private boolean loopPlay;
    public ContentCustomVideoView mCustomVideoView;
    private boolean mVideoSharePlayer;
    private String pageId;
    private int playIndex = 0;
    private ArrayList<IPlayerVideoHolder> mVideoList = new ArrayList<>();
    private ArrayList<IPlayerVideoHolder> lastVideoList = new ArrayList<>();
    IPlayerControl.OnPlayerStateListener mOnPlayerStateListener = new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.content.component.widget.videocontrol.PlayerListManager.1
        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            PlayerListManager.this.playNext();
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            if (PlayerListManager.this.loopPlay) {
                PlayerListManager.this.playNext();
                return false;
            }
            PlayerListManager.this.detachPlayer();
            return false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            return false;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
        }
    };

    private void addVideoToContainer(FrameLayout frameLayout) {
        ContentCustomVideoView contentCustomVideoView;
        if (frameLayout == null || (contentCustomVideoView = this.mCustomVideoView) == null || frameLayout == contentCustomVideoView.getParent()) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        this.mCustomVideoView.setFocusable(false);
        detachPlayer();
        frameLayout.addView(this.mCustomVideoView, new ViewGroup.LayoutParams(-1, -1));
        frameLayout.requestLayout();
    }

    private void creatVideoView(Context context) {
        if (context != null && this.mCustomVideoView == null) {
            this.mCustomVideoView = new ContentCustomVideoView(context);
        }
    }

    public static void destroyInstance(String str) {
        if (instances != null && instances.containsKey(str)) {
            instances.get(str).detachPlayer();
        }
        instances.remove(str);
    }

    public static void destroyInstances() {
        instances.clear();
    }

    private void destroyVideoView() {
        detachPlayer();
        if (this.mCustomVideoView != null) {
            this.mCustomVideoView = null;
        }
    }

    public static PlayerListManager getInstance(String str) {
        if (!instances.containsKey(str)) {
            synchronized (PlayerListManager.class) {
                if (!instances.containsKey(str)) {
                    PlayerListManager playerListManager = new PlayerListManager();
                    playerListManager.pageId = str;
                    instances.put(str, playerListManager);
                }
            }
        }
        return instances.get(str);
    }

    public static Map<String, PlayerListManager> getInstances() {
        return instances;
    }

    private boolean isVideoHolderListSame(List<IPlayerVideoHolder> list, List<IPlayerVideoHolder> list2) {
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!isVideoHolderSame(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private boolean isVideoHolderSame(IPlayerVideoHolder iPlayerVideoHolder, IPlayerVideoHolder iPlayerVideoHolder2) {
        if (iPlayerVideoHolder == null || iPlayerVideoHolder2 == null || iPlayerVideoHolder.getVideoHolderFl() != iPlayerVideoHolder2.getVideoHolderFl()) {
            return false;
        }
        return (TextUtils.isEmpty(iPlayerVideoHolder2.getVideoUrl()) || !iPlayerVideoHolder2.getVideoUrl().equals(iPlayerVideoHolder.getVideoUrl())) && iPlayerVideoHolder.equals(iPlayerVideoHolder2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playNext() {
        this.playIndex++;
        ArrayList<IPlayerVideoHolder> arrayList = this.mVideoList;
        if (arrayList != null && arrayList.size() > 0) {
            if (!this.loopPlay && this.playIndex >= this.mVideoList.size()) {
                detachPlayer();
                return;
            }
            if (this.playIndex < 0) {
                this.playIndex = 0;
            }
            playVideo();
            return;
        }
        detachPlayer();
    }

    private void removePlayerFromParent(ContentCustomVideoView contentCustomVideoView) {
        if (contentCustomVideoView == null) {
            return;
        }
        contentCustomVideoView.releaseInThread();
        ViewParent parent = contentCustomVideoView.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(contentCustomVideoView);
        }
    }

    public void attachVideoView(FrameLayout frameLayout, IPlayerVideoHolder iPlayerVideoHolder) {
        if (frameLayout == null || iPlayerVideoHolder == null || TextUtils.isEmpty(iPlayerVideoHolder.getVideoUrl())) {
            return;
        }
        reset();
        creatVideoView(frameLayout.getContext());
        addVideoToContainer(frameLayout);
        ContentCustomVideoView contentCustomVideoView = this.mCustomVideoView;
        if (contentCustomVideoView != null) {
            contentCustomVideoView.configVideoView(iPlayerVideoHolder.getContentId(), iPlayerVideoHolder.isVoiceOn(), iPlayerVideoHolder.isVoiceOn(), iPlayerVideoHolder.getCornerRadius(), this.mVideoSharePlayer);
            this.mCustomVideoView.setVideoUrl(iPlayerVideoHolder.getVideoUrl());
        }
        setPlayerListener();
    }

    public void detachPlayer() {
        ContentCustomVideoView contentCustomVideoView = this.mCustomVideoView;
        if (contentCustomVideoView == null) {
            return;
        }
        removePlayerFromParent(contentCustomVideoView);
        this.lastVideoHolde = null;
        ArrayList<IPlayerVideoHolder> arrayList = this.lastVideoList;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public JDVideoView getRealVideoView() {
        ContentCustomVideoView contentCustomVideoView = this.mCustomVideoView;
        if (contentCustomVideoView != null) {
            return contentCustomVideoView.getVideoView();
        }
        return null;
    }

    public void pausePlayer() {
        ContentCustomVideoView contentCustomVideoView = this.mCustomVideoView;
        if (contentCustomVideoView != null) {
            contentCustomVideoView.pauseVideo();
        }
    }

    public void playVideo() {
        IPlayerVideoHolder iPlayerVideoHolder;
        int i2 = this.playIndex;
        if (i2 < 0 || i2 >= this.mVideoList.size() || (iPlayerVideoHolder = this.mVideoList.get(this.playIndex)) == null) {
            return;
        }
        if (TextUtils.isEmpty(iPlayerVideoHolder.getVideoUrl())) {
            this.playIndex--;
            this.mVideoList.remove(iPlayerVideoHolder);
            playNext();
            return;
        }
        attachVideoView(iPlayerVideoHolder.getVideoHolderFl(), iPlayerVideoHolder);
        this.lastVideoHolde = iPlayerVideoHolder;
    }

    public void playVideoList(List<IPlayerVideoHolder> list) {
        if (list != null && !list.isEmpty()) {
            if (isVideoHolderListSame(list, this.lastVideoList)) {
                return;
            }
            this.mVideoList.clear();
            if (list.isEmpty()) {
                return;
            }
            this.mVideoList.addAll(list);
            this.playIndex = 0;
            playVideo();
            this.lastVideoList.clear();
            this.lastVideoList.addAll(this.mVideoList);
            return;
        }
        this.mVideoList.clear();
        detachPlayer();
    }

    public void reset() {
    }

    public void setLoopPlay(boolean z) {
        this.loopPlay = z;
    }

    public void setPlayerListener() {
        JDVideoView videoView;
        IPlayerControl.OnPlayerStateListener onPlayerStateListener;
        ContentCustomVideoView contentCustomVideoView = this.mCustomVideoView;
        if (contentCustomVideoView == null || (videoView = contentCustomVideoView.getVideoView()) == null || (onPlayerStateListener = this.mOnPlayerStateListener) == null) {
            return;
        }
        videoView.setOnPlayerStateListener(onPlayerStateListener);
    }

    public void setmVideoSharePlayer(boolean z) {
        this.mVideoSharePlayer = z;
    }
}
