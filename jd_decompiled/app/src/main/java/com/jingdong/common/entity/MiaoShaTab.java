package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class MiaoShaTab {
    private static final String TAG = "MiaoShaTab";
    public String displayTime;
    public String functionId;
    public int gid;
    public String groupTime;
    public String jumpUrl;
    public List<MiaoShaListBannerEntity> miaoShaHeadFloorEntityList;
    public List<MiaoShaTabPicEntity> miaoShaTabPicEntityList;
    public String name;
    public String operateImg;
    public String sourceValue;
    public long startTimeMills;
    public long timeRemain;

    public MiaoShaTab() {
    }

    public static ArrayList<MiaoShaTab> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<MiaoShaTab> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<MiaoShaTab> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    arrayList2.add(new MiaoShaTab(jSONArrayPoxy.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public String getGroupTime() {
        String str = this.groupTime;
        return str == null ? "" : str;
    }

    public MiaoShaTab(JSONObjectProxy jSONObjectProxy) {
        this.gid = jSONObjectProxy.optInt(MiaoShaPublicConstants.KEY_GID);
        this.name = jSONObjectProxy.getStringOrNull("name");
        this.displayTime = jSONObjectProxy.getStringOrNull("displayTime");
        this.functionId = jSONObjectProxy.getStringOrNull("functionId");
        this.timeRemain = jSONObjectProxy.optLong("timeRemain");
        this.operateImg = jSONObjectProxy.getStringOrNull("operateImg");
        this.jumpUrl = jSONObjectProxy.getStringOrNull(CartConstant.KEY_JUMPURL);
        this.groupTime = jSONObjectProxy.optString("groupTime");
        this.startTimeMills = jSONObjectProxy.optLong("startTimeMills");
        try {
            this.miaoShaHeadFloorEntityList = JDJSON.parseArray(jSONObjectProxy.getJSONArray("picList").toString(), MiaoShaListBannerEntity.class);
        } catch (Exception unused) {
        }
        this.sourceValue = jSONObjectProxy.optString("sourceValue", "");
    }

    public static ArrayList<MiaoShaTab> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<MiaoShaTab> arrayList;
        ArrayList<MiaoShaTab> arrayList2 = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            arrayList = new ArrayList<>();
        } catch (JSONException e2) {
            e = e2;
        }
        try {
            int length = jSONArrayPoxy.length();
            if (length <= i2) {
                i2 = length;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList.add(new MiaoShaTab(jSONArrayPoxy.getJSONObject(i3)));
            }
            return arrayList;
        } catch (JSONException e3) {
            e = e3;
            arrayList2 = arrayList;
            OKLog.e(TAG, e);
            return arrayList2;
        }
    }
}
