package com.jd.viewkit.templates.model.jdviewkitvirtualsearchview;

import com.jd.dynamic.DYConstants;
import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualSearchShowWordStyle extends JDViewKitVirtualTextView {
    private int marginLeft;

    public JDViewKitVirtualSearchShowWordStyle(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.marginLeft = NumberTool.valueOfInt(JSONTool.getString(jSONObject, "marginLeft"));
        int i2 = JSONTool.getInt(jSONObject, JDViewKitVirtualTextView.fontSizeKey);
        setFontSize(i2 == 0 ? 42 : i2);
        setColor(JSONTool.getString(jSONObject, JDViewKitVirtualTextView.colorKey));
        setBackgroundColor(JSONTool.getString(jSONObject, JDViewKitVirtualView.backgroundColorKey));
        if (!StringTool.isEmpty(getBackgroundColor())) {
            setBackgroundColorInt(ColorTool.parseColor(getBackgroundColor(), 0));
        }
        setVerticalAlign(DYConstants.DY_CENTER);
    }

    public int getMarginLeft() {
        return this.marginLeft;
    }

    public void setMarginLeft(int i2) {
        this.marginLeft = i2;
    }
}
