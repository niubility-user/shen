package com.jingdong.common.entity.personal;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ExtUserInfoResponse {
    public int code;
    public int enc;
    public ArrayList<ExtUserInfo> extUserInfoList;
    public List<FloorInfo> floorInfoList;
    public boolean guanZhuSwitch;
    public boolean isWalletExpoGray;
    public boolean qianbaoDegradeStatus;
    public String showBiz;
    public boolean userXiaoJinKu;

    public ExtUserInfo getExtUserInfo(String str) {
        ArrayList<ExtUserInfo> arrayList = this.extUserInfoList;
        if (arrayList != null && !arrayList.isEmpty() && !TextUtils.isEmpty(str)) {
            Iterator<ExtUserInfo> it = this.extUserInfoList.iterator();
            while (it.hasNext()) {
                ExtUserInfo next = it.next();
                if (!TextUtils.isEmpty(next.functionId) && TextUtils.equals(next.functionId, str)) {
                    return next;
                }
            }
        }
        return null;
    }
}
