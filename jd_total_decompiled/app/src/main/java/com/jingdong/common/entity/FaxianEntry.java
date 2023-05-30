package com.jingdong.common.entity;

import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class FaxianEntry {
    public static final String ICON_STYLE_PIC = "pic";
    public static final String ICON_STYLE_POINT = "point";
    public static final String JD_ENTRY_APPCENTER = "appcenter";
    public static final String JD_ENTRY_GOODSTUFF = "nicegoods";
    public static final String JD_ENTRY_GUANGGUANG = "guangguang";
    public static final String JD_ENTRY_GUSHI = "story";
    public static final String JD_ENTRY_HUODONG = "activity";
    public static final String JD_ENTRY_INVENTORY = "inventory";
    public static final String JD_ENTRY_JSHOP = "storetrend";
    public static final String JD_ENTRY_NEARBY = "lifecircle";
    public static final String JD_ENTRY_PHOTOBUY = "photobuy";
    public static final String JD_ENTRY_SAOYISAO = "scan";
    public static final String JD_ENTRY_SELECT = "jingxuan";
    public static final String JD_ENTRY_XIAOBING = "littlebing";
    public static final String JD_ENTRY_XUNMI = "niceGoods";
    public static final String TAG = "FaxianEntry";
    public int actionType;
    public String channel;
    public int endOfFLoor;
    public int floorNo;
    public String icon;
    public String nativeJumpType;
    public int needLogin;
    public int notification;
    public String notificationIcon;
    public String redirectURL;
    public String slogan;
    public int startOfFloor;
    public String title;

    public FaxianEntry(JSONObjectProxy jSONObjectProxy) {
        this.channel = jSONObjectProxy.optString("channelName");
        this.title = jSONObjectProxy.optString("title");
        this.slogan = jSONObjectProxy.optString("slogan");
        this.icon = jSONObjectProxy.optString("icon");
        this.floorNo = jSONObjectProxy.optInt("floorId");
        this.startOfFloor = 0;
        this.endOfFLoor = 0;
        this.actionType = jSONObjectProxy.optInt("actionType");
        this.redirectURL = jSONObjectProxy.optString("redirectURL");
        this.nativeJumpType = jSONObjectProxy.optString("nativeJumpType");
        this.needLogin = jSONObjectProxy.optInt(VKEventUtil.JUMP_NEEDLOGIN);
    }

    public static ArrayList<Object> toList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() == 0) {
            return null;
        }
        ArrayList<Object> arrayList = new ArrayList<>();
        int i2 = -1;
        for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i3);
            if (OKLog.D) {
                OKLog.d(TAG, " toList -->> jsonObjectItem : " + jSONObjectOrNull);
            }
            if (jSONObjectOrNull != null) {
                FaxianEntry faxianEntry = new FaxianEntry(jSONObjectOrNull);
                int i4 = faxianEntry.floorNo;
                if (i2 < i4) {
                    faxianEntry.startOfFloor = 1;
                    arrayList.add("title");
                    i2 = i4;
                }
                arrayList.add(faxianEntry);
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, " toList -->> list size : " + arrayList.size());
        }
        return arrayList;
    }

    public FaxianEntry() {
    }
}
