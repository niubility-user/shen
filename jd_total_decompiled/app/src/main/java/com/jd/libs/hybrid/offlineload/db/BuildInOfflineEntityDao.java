package com.jd.libs.hybrid.offlineload.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity;
import java.util.List;

@Dao
/* loaded from: classes16.dex */
public interface BuildInOfflineEntityDao {
    @Delete
    void delete(BuildInOfflineEntity buildInOfflineEntity);

    @Delete
    void delete(List<BuildInOfflineEntity> list);

    @Query("DELETE FROM HybridBuildInOfflineEntity")
    void deleteAll();

    @Query("SELECT * FROM HybridBuildInOfflineEntity")
    List<BuildInOfflineEntity> getAll();

    @Query("SELECT COUNT(*) FROM HybridBuildInOfflineEntity")
    int getAllCount();

    @Query("SELECT * FROM HybridBuildInOfflineEntity WHERE appid=:id")
    BuildInOfflineEntity getById(String str);

    @Query("SELECT * FROM HybridBuildInOfflineEntity WHERE documentUrl=:url OR (originalUrl=:url AND originalUrlType='1') LIMIT 1")
    BuildInOfflineEntity getByUrl(String str);

    @Query("SELECT * FROM HybridBuildInOfflineEntity WHERE (documentUrl=:url OR (originalUrl=:url AND originalUrlType='1')) AND file_versionCode= :version LIMIT 1")
    BuildInOfflineEntity getByUrl(String str, int i2);

    @Query("SELECT * FROM HybridBuildInOfflineEntity WHERE (documentUrl=:url OR originalUrl=:url) AND available = 1 LIMIT 1")
    BuildInOfflineEntity getOneAvailableByUrl(String str);

    @Query("SELECT * FROM HybridBuildInOfflineEntity WHERE (documentUrl=:url OR originalUrl=:url) AND file_versionCode= :version AND available = 1 LIMIT 1")
    BuildInOfflineEntity getOneAvailableByUrl(String str, int i2);

    @Insert(onConflict = 1)
    void save(List<BuildInOfflineEntity> list);

    @Insert(onConflict = 1)
    void save(BuildInOfflineEntity... buildInOfflineEntityArr);

    @Update
    void update(BuildInOfflineEntity buildInOfflineEntity);

    @Update
    void update(List<BuildInOfflineEntity> list);

    @Query("UPDATE HybridBuildInOfflineEntity SET minFileVer=:minFileVer WHERE appid=:id")
    void updateMinFileVer(String str, String str2);
}
