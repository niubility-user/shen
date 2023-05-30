package com.jingdong.common.video.cache;

import com.jingdong.common.video.cache.VideoImmersionEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
class VideoCacheOprateProxy implements VideoCacheOprate {
    private Map<String, List<VideoImmersionEntity.ItemEntity>> saveListMap = new HashMap();
    private Map<String, VideoImmersionEntity> saveVideoImmersionMap = new HashMap();

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean clearItemList(String str) {
        if (this.saveListMap.containsKey(str)) {
            this.saveListMap.remove(str);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean clearVideoImmersionEntity(String str) {
        if (this.saveVideoImmersionMap.containsKey(str)) {
            this.saveVideoImmersionMap.remove(str);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean isItemListExist(String str) {
        return this.saveListMap.containsKey(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public boolean isVideoImmersionEntityExist(String str) {
        return this.saveVideoImmersionMap.containsKey(str);
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public synchronized List<VideoImmersionEntity.ItemEntity> queryItemListByAuthorId(String str) {
        if (this.saveListMap.containsKey(str)) {
            return this.saveListMap.get(str);
        }
        return new ArrayList();
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public synchronized int queryPosition(String str, String str2) {
        if (this.saveListMap.containsKey(str)) {
            List<VideoImmersionEntity.ItemEntity> list = this.saveListMap.get(str);
            if (list == null) {
                return -1;
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list.get(i2).playInfo != null && list.get(i2).playInfo.vid.equals(str2)) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public synchronized VideoImmersionEntity queryVideoImmersionEntityById(String str) {
        if (this.saveListMap.containsKey(str)) {
            return this.saveVideoImmersionMap.get(str);
        }
        return null;
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public synchronized boolean saveQueryItemList(String str, List<VideoImmersionEntity.ItemEntity> list) {
        if (this.saveListMap.containsKey(str)) {
            List<VideoImmersionEntity.ItemEntity> list2 = this.saveListMap.get(str);
            if (list2 == null) {
                return false;
            }
            list2.addAll(list);
            this.saveListMap.put(str, list2);
        } else {
            this.saveListMap.put(str, list);
        }
        return true;
    }

    @Override // com.jingdong.common.video.cache.VideoCacheOprate
    public synchronized boolean saveVideoImmersion(String str, VideoImmersionEntity videoImmersionEntity) {
        this.saveVideoImmersionMap.put(str, videoImmersionEntity);
        return true;
    }
}
