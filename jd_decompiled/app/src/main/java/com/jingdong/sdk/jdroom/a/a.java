package com.jingdong.sdk.jdroom.a;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
/* loaded from: classes7.dex */
public interface a {
    @Query("SELECT _id,requestCode FROM JD_ReminderNewTable WHERE notificationTimeMillis = :notificationTime")
    Cursor a(long j2);

    @Insert(onConflict = 1)
    long[] b(c[] cVarArr);

    @Query("SELECT * FROM JD_ReminderNewTable WHERE businessType = :businessType AND startTimeMillis >= :timePeriodBegin AND startTimeMillis < :timePeriodEnd")
    Cursor c(String str, long j2, long j3);

    @Query("DELETE FROM JD_ReminderNewTable WHERE startTimeMillis < :targetTime")
    int d(long j2);

    @Query("SELECT startTimeMillis FROM JD_ReminderNewTable WHERE startTimeMillis >= :timePeriodBegin AND startTimeMillis < :timePeriodEnd  ORDER BY startTimeMillis , insertTime ASC")
    Cursor e(long j2, long j3);

    @Insert(onConflict = 1)
    long f(c cVar);

    @Query("SELECT * FROM JD_ReminderNewTable WHERE notificationTimeMillis = :notificationTime  ORDER BY startTimeMillis , insertTime ASC")
    Cursor g(long j2);

    @Query("DELETE FROM JD_ReminderNewTable WHERE businessType = :businessType AND startTimeMillis = :startTimeMillis AND identificationId = :identificationId")
    int h(String str, String str2, long j2);

    @Query("SELECT * FROM JD_ReminderNewTable WHERE startTimeMillis >= :targetTime  ORDER BY startTimeMillis , insertTime ASC")
    Cursor i(long j2);

    @Query("SELECT * FROM JD_ReminderNewTable WHERE businessType = :businessType AND startTimeMillis >= :targetTime ")
    Cursor j(String str, long j2);

    @Query("SELECT * FROM JD_ReminderNewTable ORDER BY requestCode DESC LIMIT 0,1")
    Cursor k();

    @Query("SELECT COUNT(*) FROM JD_ReminderNewTable")
    int l();

    @Query("SELECT startTimeMillis,requestCode FROM JD_ReminderNewTable WHERE startTimeMillis >= :nowTime  GROUP BY startTimeMillis")
    Cursor m(long j2);

    @Query("SELECT notificationTimeMillis,requestCode FROM JD_ReminderNewTable WHERE notificationTimeMillis >= :nowTime  GROUP BY notificationTimeMillis")
    Cursor n(long j2);

    @Query("SELECT _id FROM JD_ReminderNewTable WHERE businessType = :businessType AND startTimeMillis = :startTimeMillis AND identificationId = :identificationId")
    Cursor o(String str, String str2, long j2);

    @Query("SELECT * FROM JD_ReminderNewTable WHERE businessType = :businessType AND reminderShowTag = :reminderShowTag AND identificationId = :identificationId AND reminderTitle = :reminderTitle AND reminderImgUrl = :reminderImgUrl AND startTimeMillis = :startTimeMillis AND notificationTimeMillis = :notificationTimeMillis AND jump = :jump AND extra = :extra AND more = :more")
    Cursor p(String str, String str2, String str3, String str4, String str5, double d, double d2, String str6, String str7, String str8);

    @Query("SELECT * FROM JD_ReminderNewTable WHERE startTimeMillis >= :timePeriodBegin AND startTimeMillis < :timePeriodEnd  ORDER BY startTimeMillis , insertTime ASC")
    Cursor q(long j2, long j3);
}
