package com.jingdong.sdk.jdroom.a;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "JD_ReminderNewTable")
/* loaded from: classes7.dex */
public class c {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "_id")
    public long a;
    @ColumnInfo(name = "businessType")
    public String b;
    @ColumnInfo(name = "reminderShowTag")

    /* renamed from: c  reason: collision with root package name */
    public String f14966c;
    @ColumnInfo(name = "identificationId")
    public String d;
    @ColumnInfo(name = "reminderTitle")

    /* renamed from: e  reason: collision with root package name */
    public String f14967e;
    @ColumnInfo(name = "reminderImgUrl")

    /* renamed from: f  reason: collision with root package name */
    public String f14968f;
    @ColumnInfo(name = "startTimeMillis")

    /* renamed from: g  reason: collision with root package name */
    public double f14969g;
    @ColumnInfo(name = "notificationTimeMillis")

    /* renamed from: h  reason: collision with root package name */
    public double f14970h;
    @ColumnInfo(name = "insertTime")

    /* renamed from: i  reason: collision with root package name */
    public double f14971i;
    @ColumnInfo(name = "jump")

    /* renamed from: j  reason: collision with root package name */
    public String f14972j;
    @ColumnInfo(name = "extra")

    /* renamed from: k  reason: collision with root package name */
    public String f14973k;
    @ColumnInfo(name = "more")

    /* renamed from: l  reason: collision with root package name */
    public String f14974l;
    @ColumnInfo(name = "requestCode")

    /* renamed from: m  reason: collision with root package name */
    public int f14975m = 0;
}
