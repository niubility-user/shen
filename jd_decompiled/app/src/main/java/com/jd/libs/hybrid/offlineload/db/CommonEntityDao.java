package com.jd.libs.hybrid.offlineload.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import java.util.List;

@Dao
/* loaded from: classes16.dex */
public interface CommonEntityDao {
    @Delete
    void delete(CommonEntity commonEntity);

    @Delete
    void delete(List<CommonEntity> list);

    @Query("DELETE FROM HybridOfflineCommonEntity")
    void deleteAll();

    @Query("SELECT * FROM HybridOfflineCommonEntity")
    List<CommonEntity> getAll();

    @Query("SELECT * FROM HybridOfflineCommonEntity WHERE id=:id")
    CommonEntity getById(String str);

    @Query("SELECT * FROM HybridOfflineCommonEntity WHERE url=:url LIMIT 1")
    CommonEntity getByUrl(String str);

    @Query("SELECT * FROM HybridOfflineCommonEntity WHERE url=:url AND versionCode=:version LIMIT 1")
    CommonEntity getByUrl(String str, int i2);

    @Query("SELECT * FROM HybridOfflineCommonEntity WHERE url=:url AND available = 1 LIMIT 1")
    CommonEntity getOneAvailableByUrl(String str);

    @Query("SELECT * FROM HybridOfflineCommonEntity WHERE url=:url AND versionCode=:version AND available = 1 LIMIT 1")
    CommonEntity getOneAvailableByUrl(String str, int i2);

    @Insert(onConflict = 1)
    void save(List<CommonEntity> list);

    @Insert(onConflict = 1)
    void save(CommonEntity... commonEntityArr);

    @Update
    void update(CommonEntity commonEntity);

    @Update
    void update(List<CommonEntity> list);
}
