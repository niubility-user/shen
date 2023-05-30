package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ServerIcon {
    private String helpLink;
    private String imageUrl;
    private String name;
    private String tip;

    public ServerIcon(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.imageUrl = jDJSONObject.getString("imageUrl");
            this.tip = jDJSONObject.getString("tip");
            this.name = jDJSONObject.getString("name");
            this.helpLink = jDJSONObject.getString("helpLink");
        }
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
                if (Log.D) {
                    Log.d("ServerIcon", e2.getMessage());
                }
            }
        }
        return arrayList;
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
}
