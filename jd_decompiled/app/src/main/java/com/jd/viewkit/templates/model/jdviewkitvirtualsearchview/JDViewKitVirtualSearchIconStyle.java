package com.jd.viewkit.templates.model.jdviewkitvirtualsearchview;

import com.jd.dynamic.DYConstants;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualSearchIconStyle extends JDViewKitVirtualView {
    public int marginLeft;

    public JDViewKitVirtualSearchIconStyle(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.marginLeft = NumberTool.valueOfInt(JSONTool.getString(jSONObject, "marginLeft"));
        String string = JSONTool.getString(jSONObject, "frame");
        if (StringTool.isEmpty(string)) {
            return;
        }
        String[] split = string.split(DYConstants.DY_REGEX_COMMA);
        if (split.length == 4) {
            setOrgX(0);
            setOrgY(0);
            setWidth(NumberTool.valueOfInt(split[2]));
            setHeigh(NumberTool.valueOfInt(split[3]));
        }
    }

    public int getMarginLeft() {
        return this.marginLeft;
    }

    public void setMarginLeft(int i2) {
        this.marginLeft = i2;
    }
}
