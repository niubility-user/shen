package com.jingdong.app.mall.bundle.styleinfoview.entitys.detailcomment;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.List;
import jpbury.t;

/* loaded from: classes3.dex */
public class PdCommentItemInfo {
    public String commentData;
    public long commentId;
    public String commentOfficerUserImgSwitch;
    public String commentScore;
    public String guid;
    public List<PdCommentIconListInfo> iconList;
    public String isShowUserLevel;
    public String jsonString;
    public int mCurPosition;
    public String mProductAttr;
    public List<PdCommentPictureInfo> pictureInfoList;
    public String plusAddress;
    public String plusAvailable;
    public String plusLogoId;
    public String testId;
    public String userImgUrl;
    public String userLevel;
    public String userNickName;

    public void getProductAttribute(JDJSONObject jDJSONObject) {
        StringBuilder sb = new StringBuilder();
        try {
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("wareAttribute");
            if (jSONArray == null) {
                return;
            }
            int size = jSONArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                for (Object obj : jSONArray.getJSONObject(i2).values().toArray()) {
                    sb.append(obj);
                    sb.append(" , ");
                }
            }
            if (sb.length() > 3) {
                this.mProductAttr = sb.toString().substring(0, sb.length() - 3);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }
}
