package com.jd.viewkit.templates.model.jdviewkitdynamicbanner;

import com.jd.viewkit.tool.JSONTool;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualDynamicBannerConfig {
    private static String columnNumberKey = "columnNumber";
    private static String edgeSpaceKey = "edgeSpace";
    private static String itemSpaceKey = "itemSpace";
    private static final String lineNumberKey = "lineNumber";
    private static String lineSpaceKey = "lineSpace";
    private static String topPaddingsKey = "topPaddings";
    private int columnNumber;
    private int edgeSpace;
    private int itemSpace;
    private int lineNumber;
    private int lineSpace;
    private List<Integer> topPaddings;

    public JDViewKitVirtualDynamicBannerConfig(JSONObject jSONObject) {
        this.lineNumber = JSONTool.getInt(jSONObject, "lineNumber");
        this.columnNumber = JSONTool.getInt(jSONObject, columnNumberKey);
        this.lineSpace = JSONTool.getInt(jSONObject, lineSpaceKey);
        this.edgeSpace = JSONTool.getInt(jSONObject, edgeSpaceKey);
        this.itemSpace = JSONTool.getInt(jSONObject, itemSpaceKey);
        JSONArray jSONArray = JSONTool.getJSONArray(jSONObject, topPaddingsKey);
        if (jSONArray != null) {
            this.topPaddings = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.topPaddings.add(Integer.valueOf(JSONTool.getInt(jSONArray, i2)));
            }
        }
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public int getEdgeSpace() {
        return this.edgeSpace;
    }

    public int getItemSpace() {
        return this.itemSpace;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getLineSpace() {
        return this.lineSpace;
    }

    public List<Integer> getTopPaddings() {
        return this.topPaddings;
    }

    public void setColumnNumber(int i2) {
        this.columnNumber = i2;
    }

    public void setEdgeSpace(int i2) {
        this.edgeSpace = i2;
    }

    public void setItemSpace(int i2) {
        this.itemSpace = i2;
    }

    public void setLineNumber(int i2) {
        this.lineNumber = i2;
    }

    public void setLineSpace(int i2) {
        this.lineSpace = i2;
    }

    public void setTopPaddings(List<Integer> list) {
        this.topPaddings = list;
    }
}
