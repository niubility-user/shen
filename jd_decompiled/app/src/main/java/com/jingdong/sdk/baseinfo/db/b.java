package com.jingdong.sdk.baseinfo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
/* loaded from: classes.dex */
public interface b {
    @Query("SELECT * FROM privacy_info LIMIT :offsetStart, 500")
    List<PrivacyInfo> a(int i2);

    @Query("SELECT * FROM privacy_info WHERE timestamp >= :timestampStart AND timestamp < :timestampEnd LIMIT :offsetStart, 500")
    List<PrivacyInfo> a(long j2, long j3, int i2);

    @Insert
    long[] a(PrivacyInfo... privacyInfoArr);

    @Delete
    int b(PrivacyInfo... privacyInfoArr);
}
