package com.jd.viewkit.templates.model.jdviewkitvirtualscrollview;

import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualScrollConfig {
    public static String autoPlayKey = "autoPlay";
    public static String isLoopKey = "isLoop";
    public static String itemSpacingKey = "itemSpace";
    public static String leftRightMarginKey = "leftRightMargin";
    public static String lineSpacingKey = "lineSpacing";
    public static String paddingTopKey = "paddingTop";
    public static String playDelayKey = "playDelay";
    public static String playItemsKey = "playItems";
    private boolean autoPlay;
    private boolean isLoop;
    private int itemSpace;
    private int leftRightMargin;
    private int lineSpacing;
    private int paddingTop;
    private int playDelay;
    private int playItems;

    public JDViewKitVirtualScrollConfig(JSONObject jSONObject) {
        setItemSpace(JSONTool.getInt(jSONObject, itemSpacingKey));
        setPaddingTop(NumberTool.valueOfInt(JSONTool.getString(jSONObject, paddingTopKey)));
        setAutoPlay(JSONTool.getBoolean(jSONObject, autoPlayKey));
        setLoop(JSONTool.getBoolean(jSONObject, isLoopKey));
        setPlayItems(NumberTool.valueOfInt(JSONTool.getString(jSONObject, playItemsKey)));
        setPlayDelay(NumberTool.valueOfInt(JSONTool.getString(jSONObject, playDelayKey)));
        setPaddingTop(JSONTool.getInt(jSONObject, paddingTopKey));
        setLeftRightMargin(JSONTool.getInt(jSONObject, leftRightMarginKey));
    }

    public int getItemSpace() {
        return this.itemSpace;
    }

    public int getLeftRightMargin() {
        return this.leftRightMargin;
    }

    public int getLineSpacing() {
        return this.lineSpacing;
    }

    public int getPaddingTop() {
        return this.paddingTop;
    }

    public int getPlayDelay() {
        return this.playDelay;
    }

    public int getPlayItems() {
        return this.playItems;
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public boolean isLoop() {
        return this.isLoop;
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = z;
    }

    public void setItemSpace(int i2) {
        this.itemSpace = i2;
    }

    public void setLeftRightMargin(int i2) {
        this.leftRightMargin = i2;
    }

    public void setLineSpacing(int i2) {
        this.lineSpacing = i2;
    }

    public void setLoop(boolean z) {
        this.isLoop = z;
    }

    public void setPaddingTop(int i2) {
        this.paddingTop = i2;
    }

    public void setPlayDelay(int i2) {
        this.playDelay = i2;
    }

    public void setPlayItems(int i2) {
        this.playItems = i2;
    }
}
