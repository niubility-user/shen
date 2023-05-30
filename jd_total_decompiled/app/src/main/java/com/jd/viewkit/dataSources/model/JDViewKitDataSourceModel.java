package com.jd.viewkit.dataSources.model;

import com.jd.viewkit.helper.JDViewKitFloorAcrossListener;
import com.jd.viewkit.helper.JDViewKitViewListener;
import com.jd.viewkit.thirdinterface.model.JDViewKitParamsModel;
import com.jd.viewkit.tool.DateTool;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitDataSourceModel {
    public static String dataKey = "data";
    public static String dslIdKey = "dslId";
    public static String jumpMapKey = "jumpMap";
    public static String mtaKey = "srv";
    public static String propertyNameKey = "propertyName";
    public static String typeKey = "type";
    private Map<String, Object> dataMap;
    private String dslId;
    private JDViewKitFloorAcrossListener floorAcrossListener;
    public int p_position;
    private String pageId;
    private JDViewKitParamsModel paramsModel;
    private String propertyName;
    private String type;
    private JDViewKitViewListener viewListener;
    public Long timeStame = DateTool.getTime();
    public Long longTimeStame = Long.valueOf(System.currentTimeMillis());
    private int dataState = 0;
    private int longDataState = 0;

    public JDViewKitDataSourceModel(JSONObject jSONObject, String str) {
        setPageId(str);
        setDslId(JSONTool.getString(jSONObject, dslIdKey));
        setType(JSONTool.getString(jSONObject, typeKey));
        setPropertyName(JSONTool.getString(jSONObject, propertyNameKey));
    }

    public Map<String, Object> getDataMap() {
        return this.dataMap;
    }

    public int getDataState() {
        return this.dataState;
    }

    public String getDslId() {
        return this.dslId;
    }

    public JDViewKitFloorAcrossListener getFloorAcrossListener() {
        return this.floorAcrossListener;
    }

    public Map<String, Object> getJumpParams(String str) {
        Object obj;
        Object obj2 = getDataMap().get(jumpMapKey);
        if (obj2 == null || (obj = ((Map) obj2).get(str)) == null) {
            return null;
        }
        return (Map) obj;
    }

    public int getLongDataState() {
        return this.longDataState;
    }

    public Long getLongTimeStame() {
        return this.longTimeStame;
    }

    public String getMtaString(String str) {
        Object obj;
        Map<String, Object> jumpParams = getJumpParams(str);
        if (jumpParams == null || (obj = jumpParams.get(mtaKey)) == null) {
            return null;
        }
        return obj.toString();
    }

    public String getPageId() {
        return this.pageId;
    }

    public JDViewKitParamsModel getParamsModel() {
        return this.paramsModel;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public Long getTimeStame() {
        return this.timeStame;
    }

    public String getType() {
        return this.type;
    }

    public JDViewKitViewListener getViewListener() {
        return this.viewListener;
    }

    public void setDataMap(Map<String, Object> map) {
        this.dataMap = map;
    }

    public void setDataState(int i2) {
        this.dataState = i2;
    }

    public void setDslId(String str) {
        this.dslId = str;
    }

    public void setFloorAcrossListener(JDViewKitFloorAcrossListener jDViewKitFloorAcrossListener) {
        this.floorAcrossListener = jDViewKitFloorAcrossListener;
    }

    public void setLongDataState(int i2) {
        this.longDataState = i2;
    }

    public void setLongTimeStame(Long l2) {
        this.longTimeStame = l2;
    }

    public void setPageId(String str) {
        this.pageId = str;
    }

    public void setParamsModel(JDViewKitParamsModel jDViewKitParamsModel) {
        this.paramsModel = jDViewKitParamsModel;
    }

    public void setPropertyName(String str) {
        this.propertyName = str;
    }

    public void setTimeStame(Long l2) {
        this.timeStame = l2;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setViewListener(JDViewKitViewListener jDViewKitViewListener) {
        this.viewListener = jDViewKitViewListener;
    }

    public JDViewKitDataSourceModel(JSONObject jSONObject) {
        setDslId(JSONTool.getString(jSONObject, dslIdKey));
        setType(JSONTool.getString(jSONObject, typeKey));
        setPropertyName(JSONTool.getString(jSONObject, propertyNameKey));
    }

    public JDViewKitDataSourceModel(Map<String, Object> map) {
        setDslId(map.get(dslIdKey) == null ? null : (String) map.get(dslIdKey));
        setType(map.get(typeKey) == null ? null : (String) map.get(typeKey));
        setPropertyName(map.get(propertyNameKey) != null ? (String) map.get(propertyNameKey) : null);
        setDataMap(map);
    }
}
