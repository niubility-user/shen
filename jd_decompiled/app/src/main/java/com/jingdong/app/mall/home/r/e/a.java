package com.jingdong.app.mall.home.r.e;

import android.graphics.Rect;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.s;

/* loaded from: classes4.dex */
public class a extends b {
    public boolean isCacheData;
    public boolean isNeedRefresh;
    public int mFloorHeight;
    public Rect mPaddingRect;
    public h mParentModel;
    public int mSubPosition;
    public int mTopParent;
    public boolean showFloor;
    public boolean useRoundBg;

    public a(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.showFloor = true;
    }

    public long getAsyncTime() {
        h hVar = this.mParentModel;
        if (hVar != null) {
            return hVar.U;
        }
        return 0L;
    }

    public int getFloorHeight() {
        return this.mFloorHeight;
    }

    public String getFloorId() {
        h hVar = this.mParentModel;
        return hVar == null ? "" : hVar.A;
    }

    public long getParseTime() {
        h hVar = this.mParentModel;
        if (hVar == null) {
            return 0L;
        }
        return hVar.Y;
    }

    public boolean isAsyncFloor() {
        h hVar = this.mParentModel;
        return hVar != null && hVar.N;
    }

    public boolean isLastData() {
        return getParseTime() == s.f9357c;
    }

    public boolean isShowFloor() {
        h hVar;
        return this.showFloor && ((hVar = this.mParentModel) == null || hVar.c0);
    }

    public boolean isSubFirst() {
        return this.mSubPosition == 1;
    }

    public boolean isSubLast() {
        h hVar = this.mParentModel;
        return hVar != null && hVar.G == this.mSubPosition;
    }

    public void unUsedDivider() {
        h hVar = this.mParentModel;
        if (hVar != null) {
            hVar.f10694f = 0;
            hVar.f10695g = 0;
        }
    }
}
