package com.jingdong.common.entity;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class Commercial implements Serializable {
    public static final int FOCUSACTIVITY = 0;
    public static final int SELECTACTIVITY = 111;
    public static final int ZHIDEMAIACTIVITY = 112;
    private static final long serialVersionUID = 1;
    public String abt;
    public String action;
    public String clickUrl;
    public String clkUrl;
    public String closeUrl;
    private Drawable drawable;
    public String expoUrl;
    public String exposalUrl;
    public String extension_id;
    public String feature;
    public String horizontalImg;
    public String id;
    public Boolean isCache;
    private JumpEntity jump;
    public String labelImg;
    public String labelText;
    public String labelTextColor;
    public String noSkinImg;
    public String param;
    public String shareAvatar;
    public String shareContent;
    public String shareTitle;
    public String shareUrl;
    public String showLabel;
    public String sourceTag;
    private String sourceValue;
    public String title;
    public String transitionImg;
    public int type;
    public String verticalImg;
    public String videoId;
    public int videoLimit;
    public String viewType;
    public int wareDisplayType;
    public String wareIds;
    public Integer ynShare;

    public Commercial() {
    }

    public static boolean isAdd(Commercial commercial) {
        return !TextUtils.isEmpty(commercial.id);
    }

    private void parserJumpEntity(JDJSONObject jDJSONObject) {
        try {
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("jump");
            if (jSONObject != null) {
                this.jump = (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
            }
        } catch (Exception unused) {
        }
    }

    public static ArrayList<Commercial> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<Commercial> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<Commercial> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    Commercial commercial = new Commercial(jSONArrayPoxy.getJSONObject(i3), i2);
                    if (isAdd(commercial)) {
                        arrayList2.add(commercial);
                    } else if (i2 == 0) {
                        arrayList2.add(commercial);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("Ware", e.getMessage());
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    private void updateSelect(JDJSONObject jDJSONObject) {
        this.id = "Select_Recommend_CarouselFigure";
        this.horizontalImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "img", "");
        this.action = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "url", "");
        this.type = 3;
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("share");
        if (jSONObject != null) {
            this.ynShare = 1;
            this.title = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jSONObject, "title", "");
            this.verticalImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jSONObject, "img", "");
            this.sourceValue = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jSONObject, "summary", "");
        }
    }

    private void updateZhidemai(JDJSONObject jDJSONObject) {
        this.id = jDJSONObject.getString("id");
        this.horizontalImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "img", "");
        this.action = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, CartConstant.KEY_JUMPURL, "");
        this.type = 3;
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("share");
        if (jSONObject != null) {
            this.ynShare = 1;
            this.title = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jSONObject, "title", "");
            this.verticalImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jSONObject, "img", "");
            this.sourceValue = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jSONObject, "summary", "");
        }
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public JumpEntity getJump() {
        return this.jump;
    }

    public String getSourceValue() {
        String str = this.sourceValue;
        return str == null ? "" : str;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void update(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        try {
            update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
        } catch (Exception unused) {
        }
    }

    public Commercial(JSONObjectProxy jSONObjectProxy, int i2) {
        update(jSONObjectProxy, i2);
    }

    public void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 == 111) {
            updateSelect(jDJSONObject);
        } else if (i2 == 112) {
            updateZhidemai(jDJSONObject);
        } else {
            this.id = jDJSONObject.getString("activityId");
            this.title = jDJSONObject.getString("title");
            this.horizontalImg = jDJSONObject.getString("horizontalImag");
            this.transitionImg = jDJSONObject.getString("transitionImg");
            this.noSkinImg = jDJSONObject.getString("noSkinImg");
            this.verticalImg = jDJSONObject.getString("verticalImage");
            this.sourceValue = jDJSONObject.getString("sourceValue");
            this.ynShare = jDJSONObject.getInteger("ynShare");
            this.videoId = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "videoId", "");
            this.sourceTag = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, AddressConstant.ADDRESS_LIST_SOURCE_TAG_KEY, "");
            this.showLabel = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "showLabel", "");
            this.labelImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "labelImg", "");
            this.labelText = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "labelText", "");
            this.labelTextColor = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "labelTextColor", "");
            this.videoLimit = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "videoLimit", 0);
            this.type = jDJSONObject.getIntValue("type");
            this.action = jDJSONObject.getString("action");
            this.shareAvatar = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "shareAvatar", "");
            this.shareContent = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "shareContent", "");
            this.shareTitle = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, MBaseKeyNames.SHARE_TITLE, "");
            this.shareUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "shareUrl", "");
            this.viewType = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "viewType", "");
            this.param = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "param", "");
            this.clickUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "clickUrl", "");
            this.exposalUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "exposalUrl", "");
            this.abt = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, JDMtaUtils.ABTKEY, "");
            this.extension_id = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "extension_id", "");
            this.expoUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "expoUrl", "");
            this.clkUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "clkUrl", "");
            this.closeUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "closeUrl", "");
            if (this.type == 0) {
                try {
                    this.wareDisplayType = jDJSONObject.getIntValue("wareDisplayType");
                } catch (Exception unused) {
                }
            }
            parserJumpEntity(jDJSONObject);
        }
    }

    public Commercial(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }

    public static ArrayList<Commercial> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<Commercial> arrayList = null;
        if (jDJSONArray == null) {
            return null;
        }
        try {
            ArrayList<Commercial> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                try {
                    Commercial commercial = new Commercial(jDJSONArray.getJSONObject(i3), i2);
                    if (isAdd(commercial)) {
                        arrayList2.add(commercial);
                    } else if (i2 == 0) {
                        arrayList2.add(commercial);
                    }
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("Ware", e.getMessage());
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
        }
    }
}
