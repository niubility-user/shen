package com.jingdong.common.video.cache;

import com.jingdong.common.video.cache.VideoImmersionEntity;
import java.util.List;

/* loaded from: classes6.dex */
interface VideoCacheOprate {
    boolean clearItemList(String str);

    boolean clearVideoImmersionEntity(String str);

    boolean isItemListExist(String str);

    boolean isVideoImmersionEntityExist(String str);

    List<VideoImmersionEntity.ItemEntity> queryItemListByAuthorId(String str);

    int queryPosition(String str, String str2);

    VideoImmersionEntity queryVideoImmersionEntityById(String str);

    boolean saveQueryItemList(String str, List<VideoImmersionEntity.ItemEntity> list);

    boolean saveVideoImmersion(String str, VideoImmersionEntity videoImmersionEntity);
}
