package com.jd.viewkit.templates.model.jdviewkitvirtualscrollview;

import com.jd.viewkit.tool.JSONTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualScrollMoreConfig {
    public static String dslIdKey = "dslId";
    private String dslId;

    public JDViewKitVirtualScrollMoreConfig(JSONObject jSONObject) {
        setDslId(JSONTool.getString(jSONObject, dslIdKey));
    }

    public String getDslId() {
        return this.dslId;
    }

    public void setDslId(String str) {
        this.dslId = str;
    }
}
