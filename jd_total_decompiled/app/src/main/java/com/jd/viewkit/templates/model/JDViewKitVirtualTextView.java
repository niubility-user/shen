package com.jd.viewkit.templates.model;

import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualTextView extends JDViewKitVirtualView {
    public static String boldKey = "bold";
    public static String colorKey = "color";
    public static String ellipsizeKey = "ellipsize";
    public static String fontSizeKey = "fontSize";
    public static String italicKey = "italic";
    public static String linesKey = "lines";
    public static String maxLinesKey = "maxLines";
    public static String textAlignKey = "textAlign";
    public static String textDecorationKey = "textDecoration";
    public static String verticalAlignKey = "verticalAlign";
    private int bold;
    private String color;
    private String ellipsize;
    private int fontSize;
    private int italic;
    private int lines;
    private int maxLines;
    private String textAlign;
    private String textDecoration;
    private String verticalAlign;

    public JDViewKitVirtualTextView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.lines = 1;
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, JDViewKitVirtualView.styleKey);
        if (jSONObject2 != null) {
            int i2 = JSONTool.getInt(jSONObject2, fontSizeKey);
            setFontSize(i2 == 0 ? 42 : i2);
            if (JSONTool.get(jSONObject2, linesKey) != null) {
                setLines(JSONTool.getInt(jSONObject2, linesKey));
            }
            setColor(JSONTool.getString(jSONObject2, colorKey));
            int i3 = JSONTool.getInt(jSONObject2, maxLinesKey);
            if (i3 > 0) {
                setMaxLines(i3);
            } else {
                setMaxLines(Integer.MAX_VALUE);
            }
            setEllipsize(JSONTool.getString(jSONObject2, ellipsizeKey));
            setTextAlign(JSONTool.getString(jSONObject2, textAlignKey));
            setVerticalAlign(JSONTool.getString(jSONObject2, verticalAlignKey));
            setBold(JSONTool.getInt(jSONObject2, boldKey));
            setItalic(JSONTool.getInt(jSONObject2, italicKey));
            setTextDecoration(JSONTool.getString(jSONObject2, textDecorationKey));
        }
    }

    public int getBold() {
        return this.bold;
    }

    public String getColor() {
        return this.color;
    }

    public String getEllipsize() {
        return this.ellipsize;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getItalic() {
        return this.italic;
    }

    public int getLines() {
        return this.lines;
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    public String getTextAlign() {
        return this.textAlign;
    }

    public String getTextDecoration() {
        return this.textDecoration;
    }

    public String getVerticalAlign() {
        return this.verticalAlign;
    }

    public void setBold(int i2) {
        this.bold = i2;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public void setEllipsize(String str) {
        this.ellipsize = str;
    }

    public void setFontSize(int i2) {
        this.fontSize = i2;
    }

    public void setItalic(int i2) {
        this.italic = i2;
    }

    public void setLines(int i2) {
        this.lines = i2;
    }

    public void setMaxLines(int i2) {
        this.maxLines = i2;
    }

    public void setTextAlign(String str) {
        this.textAlign = str;
    }

    public void setTextDecoration(String str) {
        this.textDecoration = str;
    }

    public void setVerticalAlign(String str) {
        this.verticalAlign = str;
    }
}
