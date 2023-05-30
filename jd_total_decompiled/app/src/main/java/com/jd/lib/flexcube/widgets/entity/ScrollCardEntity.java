package com.jd.lib.flexcube.widgets.entity;

import android.text.TextUtils;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.pool.a;
import com.jd.lib.flexcube.widgets.entity.text.ScrollCardConfig;
import com.jd.lib.flexcube.widgets.entity.text.ScrollCardDataPath;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public class ScrollCardEntity extends BaseWidgetEntity {
    public String bgColor;
    public ScrollCardConfig config;
    public ScrollCardDataPath dataPath;
    public List<BaseWidgetEntity> subElementList;
    public String subElements;

    private void parseElmentList(JSONArray jSONArray) {
        BaseWidgetEntity baseWidgetEntity;
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        this.subElementList = new ArrayList();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            try {
                String optString = jSONArray.optJSONObject(i2).optString("type", "");
                if (!TextUtils.isEmpty(optString) && (baseWidgetEntity = (BaseWidgetEntity) CommonServiceUtil.parseObject(jSONArray.optString(i2), a.b().a(optString).getWidgetEntityClass())) != null) {
                    if (baseWidgetEntity instanceof SubViewEntity) {
                        ((SubViewEntity) baseWidgetEntity).parseFlexCubeModel(jSONArray.optString(i2));
                    }
                    if (baseWidgetEntity.getBaseConfig() != null) {
                        this.subElementList.add(baseWidgetEntity);
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
        if (TextUtils.isEmpty(this.subElements)) {
            return;
        }
        try {
            parseElmentList(new JSONArray(this.subElements));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
