package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class JdVirtualOrder implements Serializable {
    private static final String TAG = "JdVirtualOrder";
    private static final long serialVersionUID = 1;
    public String downloadUrl;
    public Integer fileSize;
    public Integer foldFlag;
    public String functionId;
    public String icon;
    public String title;
    public Integer type;
    public String url;

    public JdVirtualOrder() {
    }

    public static ArrayList<JdVirtualOrder> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<JdVirtualOrder> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                JSONObjectProxy jSONObject = jSONArrayPoxy.getJSONObject(i2);
                if (jSONObject != null) {
                    arrayList.add(new JdVirtualOrder(jSONObject));
                }
            } catch (JSONException e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return arrayList;
    }

    public String getDownloadUrl() {
        String str = this.downloadUrl;
        return str == null ? "" : str;
    }

    public Integer getFileSize() {
        Integer num = this.fileSize;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public int getFoldFlag() {
        Integer num = this.foldFlag;
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public String getFunctionId() {
        return TextUtils.isEmpty(this.functionId) ? "" : this.functionId;
    }

    public String getIcon() {
        return TextUtils.isEmpty(this.icon) ? "" : this.icon;
    }

    public String getTitle() {
        return TextUtils.isEmpty(this.title) ? "" : this.title;
    }

    public int getType() {
        Integer num = this.type;
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public String getUrl() {
        return TextUtils.isEmpty(this.url) ? "" : this.url;
    }

    public JdVirtualOrder(JSONObjectProxy jSONObjectProxy) {
        this.icon = jSONObjectProxy.getStringOrNull("icon");
        this.functionId = jSONObjectProxy.getStringOrNull("functionId");
        this.type = jSONObjectProxy.getIntOrNull("type");
        this.foldFlag = jSONObjectProxy.getIntOrNull("foldFlag");
        this.url = jSONObjectProxy.getStringOrNull("url");
        this.title = jSONObjectProxy.getStringOrNull("title");
        this.downloadUrl = jSONObjectProxy.getStringOrNull("downloadUrl");
        this.fileSize = jSONObjectProxy.getIntOrNull("fileSize");
    }
}
