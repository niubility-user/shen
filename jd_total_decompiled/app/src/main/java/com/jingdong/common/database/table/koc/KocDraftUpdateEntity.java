package com.jingdong.common.database.table.koc;

import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;

/* loaded from: classes5.dex */
public class KocDraftUpdateEntity {
    public String data;
    public long id;
    public long lastModifyTime;

    public boolean check() {
        if (!LoginUserBase.hasLogin() || TextUtils.isEmpty(this.data)) {
            return false;
        }
        this.lastModifyTime = System.currentTimeMillis();
        return true;
    }
}
