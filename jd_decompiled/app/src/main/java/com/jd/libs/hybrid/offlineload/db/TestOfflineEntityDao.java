package com.jd.libs.hybrid.offlineload.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import java.util.List;

@Dao
/* loaded from: classes16.dex */
public interface TestOfflineEntityDao {
    @Delete
    void delete(TestOfflineEntity testOfflineEntity);

    @Delete
    void delete(List<TestOfflineEntity> list);

    @Query("DELETE FROM HybridTestOfflineEntity")
    void deleteAll();

    @Query("SELECT * FROM HybridTestOfflineEntity")
    List<TestOfflineEntity> getAll();

    @Query("SELECT COUNT(*) FROM HybridTestOfflineEntity")
    int getAllCount();

    @Query("SELECT * FROM HybridTestOfflineEntity WHERE appid=:id")
    TestOfflineEntity getById(String str);

    @Query("SELECT * FROM HybridTestOfflineEntity WHERE documentUrl=:url OR (originalUrl=:url AND originalUrlType='1') LIMIT 1")
    TestOfflineEntity getByUrl(String str);

    @Query("SELECT * FROM HybridTestOfflineEntity WHERE (documentUrl=:url OR (originalUrl=:url AND originalUrlType='1')) AND file_versionCode= :version LIMIT 1")
    TestOfflineEntity getByUrl(String str, int i2);

    @Query("SELECT * FROM HybridTestOfflineEntity WHERE (documentUrl=:url OR originalUrl=:url) AND available = 1 LIMIT 1")
    TestOfflineEntity getOneAvailableByUrl(String str);

    @Query("SELECT * FROM HybridTestOfflineEntity WHERE (documentUrl=:url OR originalUrl=:url) AND file_versionCode= :version AND available = 1 LIMIT 1")
    TestOfflineEntity getOneAvailableByUrl(String str, int i2);

    @Insert(onConflict = 1)
    void save(List<TestOfflineEntity> list);

    @Insert(onConflict = 1)
    void save(TestOfflineEntity... testOfflineEntityArr);

    @Update
    void update(TestOfflineEntity testOfflineEntity);

    @Update
    void update(List<TestOfflineEntity> list);
}
