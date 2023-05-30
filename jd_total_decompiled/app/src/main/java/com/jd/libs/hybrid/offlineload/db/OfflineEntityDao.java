package com.jd.libs.hybrid.offlineload.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntity;
import java.util.List;

@Dao
/* loaded from: classes16.dex */
public interface OfflineEntityDao {
    @Delete
    void delete(OfflineEntity offlineEntity);

    @Delete
    void delete(List<OfflineEntity> list);

    @Query("DELETE FROM HybridOfflineEntity")
    void deleteAll();

    @Query("SELECT * FROM HybridOfflineEntity")
    List<OfflineEntity> getAll();

    @Query("SELECT COUNT(*) FROM HybridOfflineEntity")
    int getAllCount();

    @Query("SELECT * FROM HybridOfflineEntity WHERE type='2'")
    List<OfflineEntity> getAllSSrBiz();

    @Query("SELECT * FROM HybridOfflineEntity WHERE type='2' AND file_versionCode=:version")
    List<OfflineEntity> getAllSSrBizByVersion(int i2);

    @Query("SELECT * FROM HybridOfflineEntity WHERE appid=:id")
    OfflineEntity getById(String str);

    @Query("SELECT * FROM HybridOfflineEntity WHERE documentUrl=:url OR (originalUrl=:url AND originalUrlType='1') LIMIT 1")
    OfflineEntity getByUrl(String str);

    @Query("SELECT * FROM HybridOfflineEntity WHERE (documentUrl=:url OR (originalUrl=:url AND originalUrlType='1')) AND file_versionCode= :version LIMIT 1")
    OfflineEntity getByUrl(String str, int i2);

    @Query("SELECT * FROM HybridOfflineEntity WHERE (documentUrl=:url OR originalUrl=:url) AND available = 1 LIMIT 1")
    OfflineEntity getOneAvailableByUrl(String str);

    @Query("SELECT * FROM HybridOfflineEntity WHERE (documentUrl=:url OR originalUrl=:url) AND file_versionCode= :version AND available = 1 LIMIT 1")
    OfflineEntity getOneAvailableByUrl(String str, int i2);

    @Insert(onConflict = 1)
    void save(List<OfflineEntity> list);

    @Insert(onConflict = 1)
    void save(OfflineEntity... offlineEntityArr);

    @Update
    void update(OfflineEntity offlineEntity);

    @Update
    void update(List<OfflineEntity> list);
}
