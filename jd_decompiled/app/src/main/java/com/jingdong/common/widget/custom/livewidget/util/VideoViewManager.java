package com.jingdong.common.widget.custom.livewidget.util;

import com.jingdong.common.widget.custom.livewidget.playerview.VideoViewLayout;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class VideoViewManager {
    private HashMap<String, Object> mPlayerViewManager;
    private HashMap<String, VideoViewLayout> mVideoViewLayoutMap;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class SingletonHolder {
        private static final VideoViewManager INSTANCE = new VideoViewManager();

        private SingletonHolder() {
        }
    }

    public static final VideoViewManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addPlayer(String str, Object obj) {
        this.mPlayerViewManager.put(str, obj);
    }

    public void addVideoViewLayout(String str, VideoViewLayout videoViewLayout) {
        this.mVideoViewLayoutMap.put(str, videoViewLayout);
    }

    public void destoryVideoViewLayoutPlayer(String str) {
        VideoViewLayout videoViewLayout = this.mVideoViewLayoutMap.get(str);
        if (videoViewLayout != null) {
            videoViewLayout.stop();
        }
    }

    public Object getPlayer(String str) {
        return this.mPlayerViewManager.get(str);
    }

    public VideoViewLayout getVideoViewLayout(String str) {
        return this.mVideoViewLayoutMap.get(str);
    }

    public void playVideoViewLayoutPlayer(String str) {
        VideoViewLayout videoViewLayout = this.mVideoViewLayoutMap.get(str);
        if (videoViewLayout != null) {
            videoViewLayout.play();
        }
    }

    public void removePlayer(String str) {
        this.mPlayerViewManager.remove(str);
    }

    public void removeVideoViewLayout(String str) {
        this.mVideoViewLayoutMap.remove(str);
    }

    private VideoViewManager() {
        this.mVideoViewLayoutMap = new HashMap<>();
        this.mPlayerViewManager = new HashMap<>();
    }
}
