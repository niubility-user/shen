package com.jingdong.app.mall.home.floor.view.view.module;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class MallFloorBannerItem extends b {
    public String abt;
    public String clickUrl;
    public String clkLog;
    public String closeLog;
    public String expoLog;
    public String exposalUrl;
    public String extension_id;
    public String horizontalImg;
    public String id;
    public Boolean isCache;
    private JumpEntity jump;
    public String labelImg;
    public String labelText;
    public String labelTextColor;
    private String maskUrl;
    public boolean rightSku;
    public String showLabel;
    public String skuImg;
    public String sourceTag;
    public String title;
    public String transitionImg;
    private boolean useMask;
    public String verticalImg;
    public String videoId;
    public int videoLimit;

    public MallFloorBannerItem(String str, JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.id = getJsonString("activityId");
        this.title = getJsonString("title");
        this.horizontalImg = getJsonString("horizontalImag");
        this.transitionImg = getJsonString("transitionImg");
        this.verticalImg = getJsonString("verticalImage");
        this.skuImg = getJsonString("skuImg");
        this.rightSku = 1 != getJsonInt("seat");
        this.videoId = getJsonString("videoId");
        this.sourceTag = getJsonString(AddressConstant.ADDRESS_LIST_SOURCE_TAG_KEY);
        this.showLabel = getJsonString("showLabel");
        this.labelImg = getJsonString("labelImg");
        this.labelText = getJsonString("labelText");
        this.labelTextColor = getJsonString("labelTextColor");
        this.videoLimit = getJsonInt("videoLimit", 0);
        this.clickUrl = getJsonString("clickUrl");
        this.exposalUrl = getJsonString("exposalUrl");
        this.abt = getJsonString(JDMtaUtils.ABTKEY);
        this.extension_id = getJsonString("extension_id");
        this.expoLog = getJsonString("expoLog");
        this.clkLog = getJsonString("clkLog");
        this.closeLog = getJsonString("closeLog");
        this.maskUrl = str;
        this.useMask = 1 == getJsonInt("isShowTag");
        try {
            JDJSONObject jSONObject = this.srcJson.getJSONObject("jump");
            if (jSONObject != null) {
                this.jump = (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
            }
        } catch (Exception unused) {
        }
    }

    public static ArrayList<MallFloorBannerItem> toList(h hVar) {
        return toList(hVar.getJsonString("markedImg"), hVar.getJsonArr("content"));
    }

    public JumpEntity getJump() {
        return this.jump;
    }

    public String getMaskUrl() {
        return this.maskUrl;
    }

    public boolean isUseMask() {
        return this.useMask && !TextUtils.isEmpty(this.maskUrl) && TextUtils.isEmpty(this.videoId);
    }

    public static ArrayList<MallFloorBannerItem> toList(String str, JDJSONArray jDJSONArray) {
        if (jDJSONArray == null) {
            return null;
        }
        ArrayList<MallFloorBannerItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                arrayList.add(new MallFloorBannerItem(str, jDJSONArray.getJSONObject(i2)));
            } catch (Exception e2) {
                if (OKLog.V) {
                    OKLog.v("Ware", e2.getMessage());
                }
            }
        }
        return arrayList;
    }
}
