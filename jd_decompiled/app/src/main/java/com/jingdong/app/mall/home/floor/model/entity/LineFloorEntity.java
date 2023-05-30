package com.jingdong.app.mall.home.floor.model.entity;

import com.jingdong.app.mall.home.floor.view.linefloor.base.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class LineFloorEntity extends FloorEntity {
    public static final String SITE_TYPE_DEFAULT = "0";
    public static final String SITE_TYPE_HISTORY = "3";
    public static final String SITE_TYPE_ID = "1";
    public static final String SITE_TYPE_LBS = "2";
    private String asynSwitch;
    private String clkLog;
    private String expoLog;
    private boolean isAsyncCoreFloor;
    private d mElements;
    private h mFloorModel;
    private int mLbsDistance;
    private ArrayList<a> mLineList = new ArrayList<>();
    private String mSiteId;
    private com.jingdong.app.mall.home.floor.view.b.g.a mTitleInfo;
    private String mTypeTag;
    private int mWeightSum;
    private String siteType;

    public void addLineItem(a aVar) {
        this.mLineList.add(aVar);
    }

    public void clearLineList() {
        this.mLineList.clear();
    }

    public String getClkLog() {
        return this.clkLog;
    }

    public d getElements() {
        return this.mElements;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (this.mLineList == null) {
            return super.getExpoData();
        }
        this.mExposData.clear();
        for (int i2 = 0; i2 < this.mLineList.size(); i2++) {
            this.mLineList.get(i2).G(this.mExposData);
        }
        return this.mExposData;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        if (this.mLineList == null) {
            return super.getExpoJson();
        }
        this.mJsonExposData.clear();
        for (int i2 = 0; i2 < this.mLineList.size(); i2++) {
            this.mLineList.get(i2).H(this.mJsonExposData);
        }
        return this.mJsonExposData;
    }

    public String getExpoLog() {
        return this.expoLog;
    }

    public h getFloorModel() {
        return this.mFloorModel;
    }

    public int getLbsDistance() {
        return this.mLbsDistance;
    }

    public List<a> getLineList() {
        return this.mLineList;
    }

    public int getSelfIndex() {
        return this.mElements.j();
    }

    public String getSiteId() {
        return this.mSiteId;
    }

    public String getSiteType() {
        return this.siteType;
    }

    public com.jingdong.app.mall.home.floor.view.b.g.a getTitleInfo() {
        return this.mTitleInfo;
    }

    public int getTopOverlay() {
        return -com.jingdong.app.mall.home.floor.common.d.d(24);
    }

    public String getTypeTag() {
        return this.mTypeTag;
    }

    public boolean hasAsync() {
        return this.asynSwitch.equals("1");
    }

    public boolean isAsyncCoreFloor() {
        return this.isAsyncCoreFloor;
    }

    public boolean isFirstLineFloor() {
        return this.mElements.m();
    }

    public boolean isLastLineFloor() {
        return this.mElements.n();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return this.mWeightSum == 4 && this.mLineList.size() > 0;
    }

    public boolean needAsyncRequestNow() {
        return (!hasAsync() || "1".equals(this.siteType) || "2".equals(this.siteType)) ? false : true;
    }

    public void setAsynSwitch(String str) {
        this.asynSwitch = str;
    }

    public void setAsyncCoreFloor(boolean z) {
        this.isAsyncCoreFloor = z;
    }

    public void setClkLog(String str) {
        this.clkLog = str;
    }

    public void setExpoLog(String str) {
        this.expoLog = str;
    }

    public void setLbsDistance(int i2) {
        this.mLbsDistance = i2;
    }

    public void setLineInfo(String str, int i2, d dVar, h hVar) {
        this.mTypeTag = str;
        this.mWeightSum = i2;
        this.mFloorModel = hVar;
        this.mElements = dVar;
        dVar.w(dVar.p() && isValid());
        int size = this.mLineList.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mLineList.get(i3).I(i3, size);
        }
    }

    public void setSiteId(String str) {
        this.mSiteId = str;
    }

    public void setSiteType(String str) {
        this.siteType = str;
    }

    public void setTitleInfo(com.jingdong.app.mall.home.floor.view.b.g.a aVar) {
        this.mTitleInfo = aVar;
    }
}
