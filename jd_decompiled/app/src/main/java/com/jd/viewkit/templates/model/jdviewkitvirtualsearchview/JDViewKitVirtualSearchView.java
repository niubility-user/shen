package com.jd.viewkit.templates.model.jdviewkitvirtualsearchview;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualSearchView extends JDViewKitVirtualView {
    private JDViewKitVirtualSearchIconStyle searchIconStyle;
    private JDViewKitVirtualSearchShowWordStyle searchShowWordStyle;

    public JDViewKitVirtualSearchView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        setSearchIconStyle(new JDViewKitVirtualSearchIconStyle(JSONTool.getJSONObject(jSONObject, "searchIconStyle"), jDViewKitVirtualServiceModel, map));
        JDViewKitVirtualSearchShowWordStyle jDViewKitVirtualSearchShowWordStyle = new JDViewKitVirtualSearchShowWordStyle(JSONTool.getJSONObject(jSONObject, "showWordStyle"), jDViewKitVirtualServiceModel, map);
        jDViewKitVirtualSearchShowWordStyle.setHeigh(getHeigh());
        jDViewKitVirtualSearchShowWordStyle.setOrgX(jDViewKitVirtualSearchShowWordStyle.getMarginLeft() + getSearchIconStyle().getMarginLeft() + getSearchIconStyle().getWidth());
        jDViewKitVirtualSearchShowWordStyle.setWidth((getWidth() - jDViewKitVirtualSearchShowWordStyle.getOrgX()) - getBorderRadius());
        setSearchShowWordStyle(jDViewKitVirtualSearchShowWordStyle);
    }

    public JDViewKitVirtualSearchIconStyle getSearchIconStyle() {
        return this.searchIconStyle;
    }

    public JDViewKitVirtualSearchShowWordStyle getSearchShowWordStyle() {
        return this.searchShowWordStyle;
    }

    public void setSearchIconStyle(JDViewKitVirtualSearchIconStyle jDViewKitVirtualSearchIconStyle) {
        this.searchIconStyle = jDViewKitVirtualSearchIconStyle;
    }

    public void setSearchShowWordStyle(JDViewKitVirtualSearchShowWordStyle jDViewKitVirtualSearchShowWordStyle) {
        this.searchShowWordStyle = jDViewKitVirtualSearchShowWordStyle;
    }
}
