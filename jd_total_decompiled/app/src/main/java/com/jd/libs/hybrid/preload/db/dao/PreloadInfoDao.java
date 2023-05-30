package com.jd.libs.hybrid.preload.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import java.util.List;

@Dao
/* loaded from: classes16.dex */
public interface PreloadInfoDao {
    @Delete
    void delete(PreloadInfoEntity preloadInfoEntity);

    @Delete
    void delete(List<PreloadInfoEntity> list);

    @Query("DELETE FROM hybridpreloadinfo")
    void deleteAll();

    @Query("SELECT * FROM HybridPreloadInfo")
    List<PreloadInfoEntity> getAll();

    @Query("SELECT * FROM HybridPreloadInfo WHERE originalUrlType='2' LIMIT 1")
    List<PreloadInfoEntity> getAllByUrlRegexp();

    @Query("SELECT * FROM HybridPreloadInfo WHERE appid=:id")
    PreloadInfoEntity getById(String str);

    @Query("SELECT * FROM HybridPreloadInfo WHERE url=:url OR (originalUrl=:url AND originalUrlType='1') LIMIT 1")
    PreloadInfoEntity getOneByUrl(String str);

    @Insert(onConflict = 1)
    void save(List<PreloadInfoEntity> list);

    @Update
    void update(PreloadInfoEntity preloadInfoEntity);
}
