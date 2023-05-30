package com.jd.viewkit.templates.model;

import android.text.TextUtils;
import android.widget.ImageView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualImageView extends JDViewKitVirtualView {
    public static String adaptiveHeightKey = "adaptiveHeight";
    public static String adaptiveTypeKey = "adaptiveType";
    public static String adaptiveWidthKey = "adaptiveWidth";
    public static String scaleTypeKey = "scaleType";
    private String adaptiveHeightRefer;
    private int adaptiveType;
    private String adaptiveWidthRefer;
    private String scaleType;

    public JDViewKitVirtualImageView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        setAdaptiveType(JSONTool.getInt(jSONObject, adaptiveTypeKey));
        setAdaptiveWidthRefer(JSONTool.getString(jSONObject, adaptiveWidthKey));
        setAdaptiveHeightRefer(JSONTool.getString(jSONObject, adaptiveHeightKey));
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, JDViewKitVirtualView.styleKey);
        if (jSONObject2 != null) {
            setScaleType(JSONTool.getString(jSONObject2, scaleTypeKey));
        }
    }

    public String getAdaptiveHeightRefer() {
        return this.adaptiveHeightRefer;
    }

    public int getAdaptiveType() {
        return this.adaptiveType;
    }

    public String getAdaptiveWidthRefer() {
        return this.adaptiveWidthRefer;
    }

    public ImageView.ScaleType getScaleType() {
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
        if (TextUtils.isEmpty(this.scaleType)) {
            return scaleType;
        }
        if (JDViewKitVirtualView.ImgSTfitCenter.equals(this.scaleType)) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        return JDViewKitVirtualView.ImgSTfitXY.equals(this.scaleType) ? ImageView.ScaleType.FIT_XY : scaleType;
    }

    public String getScaleTypeStr() {
        return this.scaleType;
    }

    public void setAdaptiveHeightRefer(String str) {
        this.adaptiveHeightRefer = str;
    }

    public void setAdaptiveType(int i2) {
        this.adaptiveType = i2;
    }

    public void setAdaptiveWidthRefer(String str) {
        this.adaptiveWidthRefer = str;
    }

    public void setScaleType(String str) {
        this.scaleType = str;
    }
}
