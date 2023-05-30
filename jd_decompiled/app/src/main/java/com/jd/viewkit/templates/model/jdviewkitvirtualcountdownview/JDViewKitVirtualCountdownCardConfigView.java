package com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview;

import com.jd.dynamic.DYConstants;
import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualCountdownCardConfigView extends JDViewKitVirtualTextView {
    private int cardConfigType;
    private int cardSpace;
    private int formatType;

    public JDViewKitVirtualCountdownCardConfigView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, JDViewKitVirtualCountdownView.cardConfigViewKey);
        setCardConfigType(JSONTool.getInt(jSONObject2, "type"));
        setWidth(JSONTool.getInt(jSONObject2, "width"));
        setHeigh(JSONTool.getInt(jSONObject2, "height"));
        setCardSpace(JSONTool.getInt(jSONObject2, "cardSpace"));
        setFormatType(JSONTool.getInt(jSONObject2, "formatType"));
        setBackgroundColor(JSONTool.getString(jSONObject2, JDViewKitVirtualView.backgroundColorKey));
        if (!StringTool.isEmpty(getBackgroundColor())) {
            setBackgroundColorInt(ColorTool.parseColor(getBackgroundColor(), 0));
        }
        setBackgroundImage(JSONTool.getString(jSONObject2, JDViewKitVirtualView.backgroundImageKey));
        setBorderColor(JSONTool.getString(jSONObject2, JDViewKitVirtualView.borderColorKey));
        if (!StringTool.isEmpty(getBorderColor())) {
            setBorderColorInt(ColorTool.parseColor(getBorderColor(), 0));
        }
        setBorderWidth(JSONTool.getInt(jSONObject2, JDViewKitVirtualView.borderWidthKey));
        setBorderRadius(JSONTool.getInt(jSONObject2, JDViewKitVirtualView.borderRadiusKey));
        int i2 = JSONTool.getInt(jSONObject2, JDViewKitVirtualTextView.fontSizeKey);
        i2 = i2 == 0 ? getHeigh() : i2;
        double heigh = getHeigh();
        Double.isNaN(heigh);
        if (i2 > heigh * 0.7d) {
            double heigh2 = getHeigh();
            Double.isNaN(heigh2);
            i2 = (int) (heigh2 * 0.7d);
        }
        setFontSize(i2);
        setColor(JSONTool.getString(jSONObject2, JDViewKitVirtualTextView.colorKey));
        setBold(JSONTool.getInt(jSONObject2, JDViewKitVirtualTextView.boldKey));
        setTextDecoration(JSONTool.getString(jSONObject2, JDViewKitVirtualTextView.textDecorationKey));
        setOrgX(0);
        setOrgY(0);
        setLines(1);
        setTextAlign(DYConstants.DY_CENTER);
        setVerticalAlign(DYConstants.DY_CENTER);
        setVirtualEventList(null);
    }

    public int getCardConfigType() {
        return this.cardConfigType;
    }

    public int getCardSpace() {
        return this.cardSpace;
    }

    public int getFormatType() {
        return this.formatType;
    }

    public void setCardConfigType(int i2) {
        this.cardConfigType = i2;
    }

    public void setCardSpace(int i2) {
        this.cardSpace = i2;
    }

    public void setFormatType(int i2) {
        this.formatType = i2;
    }
}
