package com.jd.lib.babel.task.model;

import org.json.JSONObject;

/* loaded from: classes14.dex */
public class ComponentConfig {
    public BaseConfig baseSetting;
    public boolean dragEnable;

    /* loaded from: classes14.dex */
    public class BaseConfig {
        public String align;
        public int top;

        public BaseConfig(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.top = jSONObject.optInt("top", -1);
                this.align = jSONObject.optString("align");
            }
        }
    }

    public ComponentConfig(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.dragEnable = jSONObject.optBoolean("dragEnable");
        this.baseSetting = new BaseConfig(jSONObject.optJSONObject("baseSetting"));
    }
}
