package com.jingdong.sdk.baseinfo.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.jd.aips.verify.tracker.VerifyTracker;

@Entity(tableName = "privacy_info")
/* loaded from: classes.dex */
public class PrivacyInfo {
    @ColumnInfo(name = "key")
    public String key;
    @ColumnInfo(name = "name")
    public String name;
    @PrimaryKey(autoGenerate = true)
    public long pid;
    @ColumnInfo(defaultValue = "", name = "pin")
    public String pin;
    @ColumnInfo(name = VerifyTracker.KEY_TIMESTAMP)
    public long timestamp;
    @ColumnInfo(defaultValue = "", name = "value")
    public String value;
}
