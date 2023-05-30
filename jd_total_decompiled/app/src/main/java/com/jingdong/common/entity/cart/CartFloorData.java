package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.util.List;

/* loaded from: classes5.dex */
public class CartFloorData {
    public static final String FLOOR_TYPE_MCUBE = "mcube";
    public static final String FLOOR_TYPE_NATIVE = "native";
    public static final String FROM_APP = "native";
    public static final String FROM_SERVER = "server";
    public static final String SLIDING_MODE_SCROLL = "scroll";
    public static final String SLIDING_MODE_TOP = "top";
    private String anchorKey;
    private JDJSONObject floorData;
    private String floorDataSource;
    private String floorKey;
    private String floorName;
    private String floorType;
    private List<String> injectDataKeyList;
    private String slidingMode;
    private String templateId;

    public String getAnchorKey() {
        return this.anchorKey;
    }

    public JDJSONObject getFloorData() {
        return this.floorData;
    }

    public String getFloorDataSource() {
        return this.floorDataSource;
    }

    public String getFloorKey() {
        return this.floorKey;
    }

    public String getFloorName() {
        return this.floorName;
    }

    public String getFloorType() {
        return this.floorType;
    }

    public List<String> getInjectDataKeyList() {
        return this.injectDataKeyList;
    }

    public String getSlidingMode() {
        return this.slidingMode;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public void setAnchorKey(String str) {
        this.anchorKey = str;
    }

    public void setFloorData(JDJSONObject jDJSONObject) {
        this.floorData = jDJSONObject;
    }

    public void setFloorDataSource(String str) {
        this.floorDataSource = str;
    }

    public void setFloorKey(String str) {
        this.floorKey = str;
    }

    public void setFloorName(String str) {
        this.floorName = str;
    }

    public void setFloorType(String str) {
        this.floorType = str;
    }

    public void setInjectDataKeyList(List<String> list) {
        this.injectDataKeyList = list;
    }

    public void setSlidingMode(String str) {
        this.slidingMode = str;
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }
}
