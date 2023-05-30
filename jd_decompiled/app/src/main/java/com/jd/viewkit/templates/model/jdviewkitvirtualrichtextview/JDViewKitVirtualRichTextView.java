package com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview;

import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualRichTextView extends JDViewKitVirtualTextView {
    public static String horizontalAlignKey = "horizontalAlign";
    public static String itemSpaceKey = "itemSpace";
    private String horizontalAlign;
    private String itemSpace;
    protected List<JDViewKitVirtualTextView> richTextViewList;

    public JDViewKitVirtualRichTextView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.itemSpace = JSONTool.getString(jSONObject, itemSpaceKey);
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, JDViewKitVirtualView.styleKey);
        if (jSONObject2 != null) {
            setHorizontalAlign(JSONTool.getString(jSONObject2, horizontalAlignKey));
        }
        if (jSONObject.isNull(JDViewKitVirtualView.childrenKey)) {
            return;
        }
        this.richTextViewList = new ArrayList();
        JSONArray jSONArray = JSONTool.getJSONArray(jSONObject, JDViewKitVirtualView.childrenKey);
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject3 = JSONTool.getJSONObject(jSONArray, i2);
            String string = JSONTool.getString(jSONObject3, JDViewKitVirtualView.viewType);
            if (StringTool.isNotEmpty(string)) {
                if (string.equals(JDViewKitVirtualRichTextTagView.viewTypeTag)) {
                    this.richTextViewList.add(new JDViewKitVirtualRichTextTagView(jSONObject3, jDViewKitVirtualServiceModel, map));
                } else if (string.equals(JDViewKitVirtualRichTextStringView.viewTypeString)) {
                    this.richTextViewList.add(new JDViewKitVirtualRichTextStringView(jSONObject3, jDViewKitVirtualServiceModel, map));
                } else if (string.equals(JDViewKitVirtualRichTextImgView.viewTypeRichImage)) {
                    JDViewKitVirtualRichTextImgView jDViewKitVirtualRichTextImgView = new JDViewKitVirtualRichTextImgView(jSONObject3, jDViewKitVirtualServiceModel, map);
                    if (jDViewKitVirtualRichTextImgView.getWidth() > 0 && jDViewKitVirtualRichTextImgView.getHeigh() > 0) {
                        this.richTextViewList.add(jDViewKitVirtualRichTextImgView);
                    }
                }
            }
        }
    }

    public String getHorizontalAlign() {
        return this.horizontalAlign;
    }

    public String getItemSpace() {
        return this.itemSpace;
    }

    public List<JDViewKitVirtualTextView> getRichTextViewList() {
        return this.richTextViewList;
    }

    public void setHorizontalAlign(String str) {
        this.horizontalAlign = str;
    }
}
