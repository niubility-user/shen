package com.jingdong.common.database.table.koc;

import android.text.TextUtils;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.jingdong.common.login.LoginUserBase;

@Entity
/* loaded from: classes5.dex */
public class KocDraftEntity {
    public String bId;
    public String bType;
    public long createTime;
    public String data;
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long lastModifyTime;
    public String pin;

    public KocDraftEntity() {
    }

    @Ignore
    public boolean check() {
        if (TextUtils.isEmpty(this.pin) || TextUtils.isEmpty(this.data)) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.lastModifyTime = currentTimeMillis;
        if (this.createTime <= 0) {
            this.createTime = currentTimeMillis;
            return true;
        }
        return true;
    }

    public String toString() {
        return "KocDraftEntity{id=" + this.id + ", createTime=" + this.createTime + ", lastModifyTime=" + this.lastModifyTime + ", pin='" + this.pin + "', data='" + this.data + "', bId='" + this.bId + "', bType='" + this.bType + "'}";
    }

    @Ignore
    public KocDraftEntity(String str, String str2, String str3) {
        this.data = str;
        this.pin = LoginUserBase.getUserPin();
        this.createTime = System.currentTimeMillis();
        this.bType = str2;
        this.bId = str3;
    }
}
