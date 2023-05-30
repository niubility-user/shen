package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ServerIcon {
    private String helpLink;
    private String imageUrl;
    private String name;
    private String tip;

    public ServerIcon(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public static ArrayList<ServerIcon> toList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.imageUrl = jDJSONObject.getString("imageUrl");
            this.tip = jDJSONObject.getString("tip");
            this.name = jDJSONObject.getString("name");
            this.helpLink = jDJSONObject.getString("helpLink");
        }
    }

    public String getHelpLink() {
        return TextUtils.isEmpty(this.helpLink) ? "" : this.helpLink;
    }

    public String getImageUrl() {
        return TextUtils.isEmpty(this.imageUrl) ? "" : this.imageUrl;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public String getTip() {
        return TextUtils.isEmpty(this.tip) ? "" : this.tip;
    }

    public static ArrayList<ServerIcon> toList(JDJSONArray jDJSONArray) {
        ArrayList<ServerIcon> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                ServerIcon serverIcon = new ServerIcon(jDJSONArray.getJSONObject(i2));
                if (!TextUtils.isEmpty(serverIcon.getName())) {
                    arrayList.add(serverIcon);
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e("ServerIcon", e2.getMessage());
                }
            }
        }
        return arrayList;
    }

    public ServerIcon(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
