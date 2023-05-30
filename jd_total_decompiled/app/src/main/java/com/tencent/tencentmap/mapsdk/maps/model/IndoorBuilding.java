package com.tencent.tencentmap.mapsdk.maps.model;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public final class IndoorBuilding implements Cloneable {
    private int mActiveLevelIndex;
    private String mBuidlingId;
    private String mBuildingName;
    private LatLng mLatLng;
    private List<IndoorLevel> mLevels;

    public IndoorBuilding(String str, String str2, LatLng latLng, List<IndoorLevel> list, int i2) {
        this.mBuidlingId = str;
        this.mBuildingName = str2;
        this.mLatLng = latLng;
        this.mLevels = list;
        this.mActiveLevelIndex = i2;
    }

    public Object clone() {
        IndoorBuilding indoorBuilding = (IndoorBuilding) super.clone();
        if (this.mLevels != null) {
            indoorBuilding.mLevels = new ArrayList(this.mLevels.size());
            for (int i2 = 0; i2 < this.mLevels.size(); i2++) {
                indoorBuilding.mLevels.add(new IndoorLevel(this.mLevels.get(i2).getName()));
            }
        }
        if (this.mLatLng != null) {
            LatLng latLng = this.mLatLng;
            indoorBuilding.mLatLng = new LatLng(latLng.latitude, latLng.longitude);
        }
        return indoorBuilding;
    }

    public int getActiveLevelIndex() {
        return this.mActiveLevelIndex;
    }

    public String getBuidlingId() {
        return this.mBuidlingId;
    }

    public LatLng getBuildingLatLng() {
        return this.mLatLng;
    }

    public String getBuildingName() {
        return this.mBuildingName;
    }

    public List<IndoorLevel> getLevels() {
        return this.mLevels;
    }

    public String toString() {
        List<IndoorLevel> list;
        if (this.mBuidlingId == null || (list = this.mLevels) == null || list.size() <= this.mActiveLevelIndex) {
            return "";
        }
        return this.mBuidlingId + CartConstant.KEY_YB_INFO_LINK + this.mLevels.get(this.mActiveLevelIndex).getName();
    }
}
