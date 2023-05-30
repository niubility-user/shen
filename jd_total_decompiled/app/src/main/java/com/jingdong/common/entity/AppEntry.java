package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AppEntry {
    public static final String APP_CODE_CAIPIAO = "caipiao";
    public static final String APP_CODE_CHONGZHI = "chongzhi";
    public static final String APP_CODE_COUPON_CENTER = "couponcenter";
    public static final String APP_CODE_DIANYINGPIAO = "dianyingpiao";
    public static final String APP_CODE_GENERICCHANNEL = "genericChannel";
    public static final String APP_CODE_JIPIAO = "jipiao";
    public static final String APP_CODE_LIULIANGCHONGZHI = "liuliangchongzhi";
    public static final String APP_CODE_QQCHONGZHI = "qqchongzhi";
    public static final String APP_CODE_SAO_A_SAO = "saoasao";
    public static final String APP_CODE_SHENGHUOQUAN = "shenghuoquan";
    public static final String APP_CODE_YOUXICHONGZHI = "youxichongzhi";
    public Integer actionType;
    public String appCode;
    private String channelId;
    public String cornerIcon;
    public String functionId;
    public String icon;
    public String icon2;
    public String id;
    private String isSub;
    private JumpEntity jump;
    public String name;
    public String nativeJumpType;
    public String needLogin = "0";
    public String noSkinIcon;
    public String operateIconId;
    public Integer order;
    public String param;
    public Integer redDot;
    public String redirectURL;
    public String share_desc;
    public Integer share_enable;
    public String share_icon;
    public String share_name;
    public String share_url;
    public String slogan;
    public String sourceValue;
    public static final Integer ACTION_TYPE_MPAGE = 1;
    public static final Integer ACTION_TYPE_NATIVE = 2;
    public static final Integer ACTION_TYPE_FUNCTIONID = 3;

    public AppEntry() {
    }

    public static List<AppEntry> toList(JSONArrayPoxy jSONArrayPoxy) {
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.id = jDJSONObject.getString("id");
        this.operateIconId = jDJSONObject.getString("operateIconId");
        this.appCode = jDJSONObject.getString("appCode");
        this.name = jDJSONObject.getString("name");
        this.icon = jDJSONObject.getString("icon");
        this.icon2 = jDJSONObject.getString("icon2");
        this.slogan = jDJSONObject.getString("slogan");
        this.cornerIcon = jDJSONObject.getString("cornerIcon");
        this.order = Integer.valueOf(jDJSONObject.getIntValue("order"));
        this.actionType = Integer.valueOf(jDJSONObject.getIntValue("actionType"));
        this.redirectURL = jDJSONObject.getString("redirectURL");
        this.nativeJumpType = jDJSONObject.getString("nativeJumpType");
        this.functionId = jDJSONObject.getString("functionId");
        this.needLogin = jDJSONObject.getString(VKEventUtil.JUMP_NEEDLOGIN);
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("share");
        if (jSONObject != null) {
            this.share_enable = Integer.valueOf(jSONObject.getIntValue("enable"));
            this.share_name = jSONObject.getString("name");
            this.share_url = jSONObject.getString("url");
            this.share_icon = jSONObject.getString("icon");
            this.share_desc = jSONObject.getString("desc");
        }
        this.sourceValue = jDJSONObject.getString("sourceValue");
        this.redDot = Integer.valueOf(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "notificationEnable", 0));
        this.noSkinIcon = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "noSkinIcon", "");
        this.param = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "param", "");
        this.isSub = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "isSub", "0");
        this.channelId = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "channelId", "");
        try {
            JDJSONObject jSONObject2 = jDJSONObject.getJSONObject("jump");
            if (jSONObject2 != null) {
                this.jump = (JumpEntity) jSONObject2.toJavaObject(JumpEntity.class);
            }
        } catch (Exception unused) {
        }
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getIsSub() {
        return this.isSub;
    }

    public JumpEntity getJump() {
        return this.jump;
    }

    public void setJump(JumpEntity jumpEntity) {
        this.jump = jumpEntity;
    }

    public AppEntry(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public static List<AppEntry> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray != null && jDJSONArray.size() != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    arrayList.add(new AppEntry(jSONObject));
                }
            }
            return arrayList;
        }
        return new ArrayList();
    }

    public AppEntry(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
