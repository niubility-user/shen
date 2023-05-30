package com.jd.lib.flexcube.widgets.entity;

import android.text.TextUtils;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.pool.a;
import com.jd.lib.flexcube.widgets.entity.flexbox.BoxPath;
import com.jd.lib.flexcube.widgets.entity.flexbox.FlexBoxConfig;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public class FlexBoxEntity extends BaseWidgetEntity {
    public CanvasConfig canvasConfig;
    public FlexBoxConfig config;
    public BoxPath dataPath;
    public String elementList;
    public FloorConfig floorConfig;
    public List<BaseWidgetEntity> widgetList;

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
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }

    public void parseElementList() {
        if (TextUtils.isEmpty(this.elementList)) {
            return;
        }
        try {
            parseElmentList(new JSONArray(this.elementList));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
