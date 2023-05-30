package com.jingdong.common.database.table.koc;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
/* loaded from: classes5.dex */
interface KocDraftDao {
    @Delete
    int delete(KocDraftEntity kocDraftEntity);

    @Insert
    long insert(KocDraftEntity kocDraftEntity);

    @Query("select * from KocDraftEntity where pin = :pin and id = :id")
    KocDraftEntity query(String str, long j2);

    @Query("select * from KocDraftEntity where pin = :pin and bType = :bType and bId = :bId")
    KocDraftEntity query(String str, String str2, String str3);

    @Query("select * from KocDraftEntity where pin = :pin order by lastModifyTime DESC")
    List<KocDraftEntity> query(String str);

    @Query("select COUNT(*) from KocDraftEntity where pin = :pin")
    int queryCount(String str);

    @Update(entity = KocDraftEntity.class)
    int update(KocDraftUpdateEntity kocDraftUpdateEntity);
}
