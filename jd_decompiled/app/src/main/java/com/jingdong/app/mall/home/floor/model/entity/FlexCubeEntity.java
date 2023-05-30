package com.jingdong.app.mall.home.floor.model.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.r.e.b;

/* loaded from: classes4.dex */
public class FlexCubeEntity extends FloorEntity {
    FlexCubeModel flexCubeModel;
    private boolean showTime;
    private long timeEnd;
    private String timeLayout;
    private long timeRemain;

    public FlexCubeModel getFlexCubeModel() {
        return this.flexCubeModel;
    }

    public long getTimeEnd() {
        return this.timeEnd;
    }

    public String getTimeLayout() {
        return this.timeLayout;
    }

    public long getTimeRemain() {
        return this.timeRemain;
    }

    public boolean isShowTime() {
        return this.showTime;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return this.flexCubeModel != null;
    }

    public void setFlexCubeModel(FlexCubeModel flexCubeModel) {
        if (flexCubeModel.handleData()) {
            flexCubeModel.setFlexCubeWidth(d.f9279g);
            this.flexCubeModel = flexCubeModel;
        }
    }

    public void setFloorInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.timeLayout = b.getJsonString(jDJSONObject, "timeLayout", "");
        this.showTime = TextUtils.equals("1", b.getJsonString(jDJSONObject, "isShowTime", "0"));
        this.timeRemain = b.getJsonLong(jDJSONObject, "timeRemain", 0L);
        this.timeEnd = b.getJsonLong(jDJSONObject, "timeEnd", 0L);
    }
}
