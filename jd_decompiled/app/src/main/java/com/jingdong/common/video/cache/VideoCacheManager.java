package com.jingdong.common.video.cache;

import com.jingdong.common.video.cache.VideoImmersionEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class VideoCacheManager implements VideoCacheOprate {
    private static volatile VideoCacheManager instance;
    public String videoPlayId = "";
    public int videoPlayPos = -1;
    public String RecomendPlayId = "";
    public int recommendPlayPos = -1;
    public int collectChoosePos = -1;
    public boolean isCancelCollected = false;
    private Map<String, List<VideoImmersionEntity>> saveMap = new HashMap();
    private VideoCacheOprate mVideoCacheOprate = new VideoCacheOprateProxy();

    public static synchronized VideoCacheManager getInstance() {
        VideoCacheManager videoCacheManager;
        synchronized (VideoCacheManager.class) {
            if (instance == null) {
                synchronized (VideoCacheManager.class) {
                    if (instance == null) {
                        instance = new VideoCacheManager();
                    }
                }
            }
            videoCacheManager = instance;
        }
        return videoCacheManager;
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean clearItemList(String str) {
        return this.mVideoCacheOprate.clearItemList(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean clearVideoImmersionEntity(String str) {
        return this.mVideoCacheOprate.clearVideoImmersionEntity(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean isItemListExist(String str) {
        return this.mVideoCacheOprate.isItemListExist(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean isVideoImmersionEntityExist(String str) {
        return this.mVideoCacheOprate.isVideoImmersionEntityExist(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public List<VideoImmersionEntity.ItemEntity> queryItemListByAuthorId(String str) {
        return this.mVideoCacheOprate.queryItemListByAuthorId(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public int queryPosition(String str, String str2) {
        return this.mVideoCacheOprate.queryPosition(str, str2);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public VideoImmersionEntity queryVideoImmersionEntityById(String str) {
        return this.mVideoCacheOprate.queryVideoImmersionEntityById(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean saveQueryItemList(String str, List<VideoImmersionEntity.ItemEntity> list) {
        return this.mVideoCacheOprate.saveQueryItemList(str, list);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean saveVideoImmersion(String str, VideoImmersionEntity videoImmersionEntity) {
        return this.mVideoCacheOprate.saveVideoImmersion(str, videoImmersionEntity);
    }
}
