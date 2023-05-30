package com.jd.lib.flexcube.layout.entity;

import android.text.TextUtils;
import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.FlexLinearFloor;
import com.jd.lib.flexcube.layout.FlexLinearNineFloor;
import com.jd.lib.flexcube.layout.floor.banner.full.FlexFullBannerFloor;
import com.jd.lib.flexcube.pool.a;
import com.jd.lib.flexcube.widgets.entity.SubViewEntity;
import com.jd.lib.flexcube.widgets.entity.VideoEntity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes14.dex */
public class FlexCubeModel extends BaseFloorModel {
    public CanvasConfig canvasConfig;
    public String elementList;
    public int flexClickTimes;
    private int flexCubeWidth;
    public FloorConfig floorConfig;
    public boolean hasSubView;
    public boolean hasVideo;
    public List<MaterialGroup> materialGroupList;
    public int scrollXNoSerialize;
    public int stopStatusNoSerialize;
    private boolean useUuid;
    public List<BaseWidgetEntity> widgetList;
    private int mTotalCount = 1;
    private int mColumns = 0;
    private float mMultiple = -1.0f;
    private float mCanvasMultiple = -1.0f;
    private int mAllCanvasWidth = -1;

    private void parseElmentList(JSONArray jSONArray) {
        BaseWidgetEntity baseWidgetEntity;
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        this.widgetList = new ArrayList();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            try {
                String optString = jSONArray.optJSONObject(i2).optString("type", "");
                if (!TextUtils.isEmpty(optString) && (baseWidgetEntity = (BaseWidgetEntity) CommonServiceUtil.parseObject(jSONArray.optString(i2), a.b().a(optString).getWidgetEntityClass())) != null) {
                    if (baseWidgetEntity instanceof SubViewEntity) {
                        ((SubViewEntity) baseWidgetEntity).parseFlexCubeModel(jSONArray.optString(i2));
                    }
                    if (baseWidgetEntity.getBaseConfig() != null) {
                        this.widgetList.add(baseWidgetEntity);
                        if (baseWidgetEntity instanceof VideoEntity) {
                            this.hasVideo = true;
                        }
                        if (baseWidgetEntity instanceof SubViewEntity) {
                            this.hasSubView = true;
                            if (((SubViewEntity) baseWidgetEntity).hasVideo()) {
                                this.hasVideo = true;
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public int getAllCanvasWidth() {
        int i2 = this.mAllCanvasWidth;
        if (i2 >= 0) {
            return i2;
        }
        if (FlexCube.GRIDVIEW.equals(FlexCube.getFloorId(getId()))) {
            int c2 = FlexLinearNineFloor.c(this.floorConfig, this.canvasConfig, getMultiple(), getColumns());
            this.mAllCanvasWidth = c2;
            return c2;
        }
        int c3 = FlexLinearFloor.c(this.floorConfig, this.canvasConfig, getMultiple(), getColumns());
        this.mAllCanvasWidth = c3;
        return c3;
    }

    public float getCanvasMultiple() {
        float f2 = this.mCanvasMultiple;
        if (f2 >= 0.0f) {
            return f2;
        }
        if (FlexCube.FLATVIEW.equals(FlexCube.getFloorId(getId()))) {
            this.mCanvasMultiple = FlexLinearFloor.d(this.floorConfig, this.canvasConfig, getAllCanvasWidth(), getColumns());
        } else if ("banner".equals(FlexCube.getFloorId(getId()))) {
            this.mCanvasMultiple = FlexFullBannerFloor.c(this.floorConfig, this.canvasConfig, getMultiple());
        } else if (FlexCube.GRIDVIEW.equals(FlexCube.getFloorId(getId()))) {
            this.mCanvasMultiple = FlexLinearNineFloor.d(this.floorConfig, this.canvasConfig, getAllCanvasWidth(), getColumns());
        }
        return this.mCanvasMultiple;
    }

    public int getColumns() {
        int i2 = this.mColumns;
        if (i2 > 0) {
            return i2;
        }
        this.mColumns = 1;
        FloorConfig floorConfig = this.floorConfig;
        if (floorConfig != null) {
            int i3 = floorConfig.columns;
            this.mColumns = i3 != 0 ? i3 : 1;
        }
        return this.mColumns;
    }

    public int getFlexCubeWidth() {
        return this.flexCubeWidth;
    }

    public String getId() {
        FloorConfig floorConfig = this.floorConfig;
        if (floorConfig == null || this.canvasConfig == null || TextUtils.isEmpty(floorConfig.style)) {
            return "";
        }
        String str = this.floorConfig.style;
        char c2 = '\uffff';
        int i2 = 0;
        switch (str.hashCode()) {
            case -1930067700:
                if (str.equals(FlexCube.BANNER_FOCUS)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1396342996:
                if (str.equals("banner")) {
                    c2 = 5;
                    break;
                }
                break;
            case -907680051:
                if (str.equals("scroll")) {
                    c2 = 4;
                    break;
                }
                break;
            case -575783845:
                if (str.equals(FlexCube.BANNER_STACKED)) {
                    c2 = 6;
                    break;
                }
                break;
            case 318121739:
                if (str.equals(FlexCube.GRIDVIEW)) {
                    c2 = 1;
                    break;
                }
                break;
            case 338714306:
                if (str.equals(FlexCube.BANNER_VERTICAL)) {
                    c2 = 3;
                    break;
                }
                break;
            case 1626920350:
                if (str.equals(FlexCube.FLATVIEW)) {
                    c2 = 0;
                    break;
                }
                break;
        }
        if (c2 == 0) {
            if (!this.hasSubView && !this.useUuid) {
                return this.floorConfig.style + FlexCube.ASTERISK + this.floorConfig.columns;
            }
            return this.floorConfig.style + FlexCube.ASTERISK + this.floorConfig.columns + FlexCube.ASTERISK + this.canvasConfig.getUuid();
        } else if (c2 == 1) {
            List<MaterialGroup> list = this.materialGroupList;
            if (list != null && list.get(0) != null) {
                if ((FlexCube.ASTERISK + this.materialGroupList.get(0).groupInfoList) != null) {
                    if (!this.hasSubView && !this.useUuid) {
                        return this.floorConfig.style + FlexCube.ASTERISK + this.floorConfig.columns + FlexCube.ASTERISK + this.materialGroupList.get(0).groupInfoList.size();
                    }
                    return this.floorConfig.style + FlexCube.ASTERISK + this.floorConfig.columns + FlexCube.ASTERISK + this.materialGroupList.get(0).groupInfoList.size() + FlexCube.ASTERISK + this.canvasConfig.getUuid();
                }
            }
            return this.floorConfig.style + FlexCube.ASTERISK + this.floorConfig.columns + FlexCube.ASTERISK + this.canvasConfig.getUuid();
        } else if (c2 != 2) {
            if (c2 != 3) {
                return this.floorConfig.style + FlexCube.ASTERISK + this.canvasConfig.getUuid();
            }
            return this.floorConfig.style + FlexCube.ASTERISK + this.floorConfig.columns + FlexCube.ASTERISK + this.canvasConfig.getUuid();
        } else {
            int i3 = this.floorConfig.w;
            CanvasConfig canvasConfig = this.canvasConfig;
            int i4 = canvasConfig.w;
            float e2 = b.e(canvasConfig.scalingRatio, 1);
            if (i4 > 0 && e2 > 0.0f) {
                float f2 = i3;
                i2 = (int) (f2 / (i4 * e2));
                if (i2 * e2 < f2) {
                    i2 = i2 % 2 == 1 ? i2 + 2 : i2 + 1;
                }
            }
            int i5 = i2 >= 3 ? i2 : 3;
            if (i5 > 11) {
                i5 = 11;
            }
            return this.floorConfig.style + FlexCube.ASTERISK + i5 + FlexCube.ASTERISK + this.canvasConfig.getUuid();
        }
    }

    public List<MaterialModel> getMaterialListByFloorNum(int i2) {
        if (FlexCube.FLATVIEW.equals(FlexCube.getFloorId(getId()))) {
            return FlexLinearFloor.e(this.floorConfig, this.materialGroupList, i2);
        }
        if ("scroll".equals(FlexCube.getFloorId(getId()))) {
            List<MaterialGroup> list = this.materialGroupList;
            if (list == null || list.get(0) == null) {
                return null;
            }
            return this.materialGroupList.get(0).groupInfoList;
        }
        List<MaterialGroup> list2 = this.materialGroupList;
        if (list2 == null || list2.get(0) == null) {
            return null;
        }
        return this.materialGroupList.get(0).groupInfoList;
    }

    public float getMultiple() {
        int i2;
        float f2 = this.mMultiple;
        if (f2 >= 0.0f) {
            return f2;
        }
        this.mMultiple = 0.0f;
        FloorConfig floorConfig = this.floorConfig;
        if (floorConfig != null && (i2 = floorConfig.w) > 0) {
            this.mMultiple = (this.flexCubeWidth * 1.0f) / i2;
        }
        return this.mMultiple;
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public String getStyle(int i2) {
        return this.floorConfig == null ? "" : getId();
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public int getTotalCount() {
        boolean equals = FlexCube.FLATVIEW.equals(FlexCube.getFloorId(getId()));
        int i2 = this.mTotalCount;
        if (i2 <= 1 || equals) {
            this.mTotalCount = 1;
            if (equals) {
                this.mTotalCount = FlexLinearFloor.f(this, this.floorConfig, this.materialGroupList, getColumns());
            } else if ("scroll".equals(FlexCube.getFloorId(getId()))) {
                this.mTotalCount = 1;
            } else if (FlexCube.GRIDVIEW.equals(FlexCube.getFloorId(getId()))) {
                this.mTotalCount = 1;
            }
            return this.mTotalCount;
        }
        return i2;
    }

    public int getTotalRow() {
        if (FlexCube.GRIDVIEW.equals(FlexCube.getFloorId(getId()))) {
            return FlexLinearNineFloor.e(this, this.floorConfig, this.materialGroupList, getColumns());
        }
        return 1;
    }

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        FloorConfig floorConfig;
        if (!TextUtils.isEmpty(this.elementList)) {
            try {
                parseElmentList(new JSONArray(this.elementList));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        List<MaterialGroup> list = this.materialGroupList;
        return list != null && list.size() > 0 && this.materialGroupList.get(0).hasData() && (floorConfig = this.floorConfig) != null && floorConfig.w > 0;
    }

    public void setFlexCubeWidth(int i2) {
        if (this.flexCubeWidth != i2) {
            this.flexCubeWidth = i2;
            this.mMultiple = -1.0f;
            this.mCanvasMultiple = -1.0f;
            this.mAllCanvasWidth = -1;
        }
    }

    public void setUseUuid(boolean z) {
        this.useUuid = z;
    }
}
