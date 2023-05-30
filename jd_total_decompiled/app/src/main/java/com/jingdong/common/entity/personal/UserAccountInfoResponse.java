package com.jingdong.common.entity.personal;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class UserAccountInfoResponse {
    public String code;
    public ArrayList<UserAccountInfo> userAccountInfoList;

    public UserAccountInfo getUserInfo(String str) {
        ArrayList<UserAccountInfo> arrayList = this.userAccountInfoList;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<UserAccountInfo> it = this.userAccountInfoList.iterator();
            while (it.hasNext()) {
                UserAccountInfo next = it.next();
                if (TextUtils.equals(next.functionId, str)) {
                    return next;
                }
            }
        }
        return null;
    }

    public String toString() {
        return "UserAccountInfoResponse{userAccountInfos=" + this.userAccountInfoList + ", code=" + this.code + '}';
    }
}
