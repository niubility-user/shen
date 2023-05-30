package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jingdong.app.mall.home.r.c.b;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LinearWithCenterIconFloorEntity extends FloorEntity {
    public static final String CLOSE_ID = "CLOSE_CENTER_ID";
    private String clickUrl;
    private String expo;
    private b expoMaiDianJson;
    private String expoUrl;
    private boolean showClose;

    public String getClickUrl() {
        return this.clickUrl;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (TextUtils.isEmpty(this.expo)) {
            return super.getExpoData();
        }
        this.mExposData.clear();
        this.mExposData.add(this.expo);
        return this.mExposData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        if (this.expoMaiDianJson == null) {
            return super.getExpoJson();
        }
        this.mJsonExposData.clear();
        this.mJsonExposData.add(this.expoMaiDianJson);
        return this.mJsonExposData;
    }

    public String getExpoUrl() {
        return this.expoUrl;
    }

    public boolean isShowClose() {
        return this.showClose;
    }

    public void setClickUrl(String str) {
        this.clickUrl = str;
    }

    public void setExpo(String str) {
        this.expo = str;
    }

    public void setExpoJson(String str) {
        this.expoMaiDianJson = b.c(str);
    }

    public void setExpoUrl(String str) {
        this.expoUrl = str;
    }

    public void setShowClose(boolean z) {
        this.showClose = z;
    }
}
