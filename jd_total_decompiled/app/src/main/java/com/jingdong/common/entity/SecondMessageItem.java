package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class SecondMessageItem extends BaseMessage implements Serializable {
    private static final String COMMON_KEY = "common";
    private static final String CONTENT_KEY = "content";
    private static final String CREATEED_KEY = "created";
    private static final String ORDERID_KEY = "orderId";
    private static final String OTHERS_KEY = "others";
    private static final String TAG = "SecondMessageItem";
    private static final String TITLE_KEY = "title";
    private static final String TYPE_KEY = "type";
    private static final String UNREADCOUNT_KEY = "unReadCount";
    public static final int UN_KNOW_STATUS = -1;
    public static final int UN_KNOW_TYPE = -1;
    private static final long serialVersionUID = 2620477969487778663L;
    private JSONObjectProxy common;
    public String content;
    public String created;
    public String orderId;
    private JSONObjectProxy others;
    public String title;
    public Integer type;
    public Integer unReadCount;

    public SecondMessageItem(JSONObjectProxy jSONObjectProxy) {
        try {
            setCommon(jSONObjectProxy.getJSONObject(COMMON_KEY));
            setOthers(jSONObjectProxy.getJSONObject(OTHERS_KEY));
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static ArrayList<SecondMessageItem> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<SecondMessageItem> arrayList = new ArrayList<>();
        if (jSONArrayPoxy != null && jSONArrayPoxy.length() >= 1) {
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
                if (jSONObjectOrNull != null && jSONObjectOrNull.length() > 0) {
                    arrayList.add(new SecondMessageItem(jSONObjectOrNull));
                }
            }
        }
        return arrayList;
    }

    public JSONObjectProxy getCommon() {
        return this.common;
    }

    public String getContent() {
        String str = this.content;
        return str == null ? "" : str;
    }

    public String getCreated() {
        String str = this.created;
        return str == null ? "" : str;
    }

    public String getOrderId() {
        String str = this.orderId;
        return str == null ? "" : str;
    }

    public JSONObjectProxy getOthers() {
        return this.others;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public Integer getType() {
        Integer num = this.type;
        if (num == null) {
            return -1;
        }
        return num;
    }

    public Integer getUnReadCount() {
        Integer num = this.unReadCount;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public void setCommon(JSONObjectProxy jSONObjectProxy) {
        this.common = jSONObjectProxy;
        this.content = jSONObjectProxy.getStringOrNull("content");
        this.created = jSONObjectProxy.getStringOrNull(CREATEED_KEY);
        setMsgId(jSONObjectProxy.getStringOrNull("msgId"));
        setStatus(jSONObjectProxy.getIntOrNull("status"));
        this.title = jSONObjectProxy.getStringOrNull("title");
        this.type = jSONObjectProxy.getIntOrNull("type");
        this.unReadCount = jSONObjectProxy.getIntOrNull(UNREADCOUNT_KEY);
    }

    public void setOthers(JSONObjectProxy jSONObjectProxy) {
        this.others = jSONObjectProxy;
        this.orderId = jSONObjectProxy.getStringOrNull("orderId");
    }

    public SecondMessageItem() {
    }
}
