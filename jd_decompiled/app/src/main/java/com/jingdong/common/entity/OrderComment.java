package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class OrderComment implements Serializable {
    public static final int DETAIL_REPLY = 2;
    public static final int ORDER_COMMENT = 0;
    public static final int ORDER_COMMENT_DETAIL = 1;
    private static final String TAG = "OrderComment";
    private String content;
    private String creationTime;
    private String id;
    private List<Image> imageList;
    private String replyCount;
    private String title;
    private Integer totalCount;
    private String userId;
    private Integer viewCount;

    public OrderComment() {
        this.imageList = new LinkedList();
    }

    public static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static ArrayList<OrderComment> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        ArrayList<OrderComment> arrayList = null;
        try {
            ArrayList<OrderComment> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new OrderComment(jSONArrayPoxy.getJSONObject(i3), i2));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("Comment", "JSONException -->> ", e);
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public String getCreationTime() {
        String str = this.creationTime;
        return str != null ? str : "\u6682\u65e0\u6652\u5355\u65f6\u95f4";
    }

    public List<Image> getImageList() {
        return this.imageList;
    }

    public String getReplyCount() {
        String str = this.replyCount;
        return str != null ? str : "0\u56de\u590d";
    }

    public String getTitle() {
        String str = this.title;
        return str != null ? str : "\u6682\u65e0\u6807\u9898";
    }

    public String getUserId() {
        String str = this.userId;
        return str != null ? str : "\u6682\u65e0\u4f5c\u8005\u540d";
    }

    public Integer getViewCount() {
        Integer num = this.viewCount;
        return Integer.valueOf(num != null ? num.intValue() : 0);
    }

    public void setImageList(List<Image> list) {
        this.imageList = list;
    }

    public void setReplyCount(Integer num) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(num);
        stringBuffer.append("\u56de\u590d");
        this.replyCount = stringBuffer.toString();
    }

    public OrderComment(JSONObjectProxy jSONObjectProxy, JSONArrayPoxy jSONArrayPoxy, int i2) {
        this.imageList = new LinkedList();
        if (i2 != 0) {
            if (i2 == 1) {
                this.content = jSONObjectProxy.getStringOrNull("content");
                this.title = jSONObjectProxy.getStringOrNull("title");
                setReplyCount(jSONObjectProxy.getIntOrNull("replyCount"));
                this.viewCount = jSONObjectProxy.getIntOrNull("viewCount");
                this.userId = jSONObjectProxy.getStringOrNull("userId");
                this.creationTime = jSONObjectProxy.getStringOrNull("creationTime");
                for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                    try {
                        this.imageList.add(new Image(jSONArrayPoxy.getJSONObject(i3), 0));
                    } catch (JSONException e2) {
                        OKLog.e(TAG, e2);
                    }
                }
            } else if (i2 != 2) {
                return;
            }
            this.content = jSONObjectProxy.getStringOrNull("content");
            this.creationTime = jSONObjectProxy.getStringOrNull("creationTime");
            setReplyCount(jSONObjectProxy.getIntOrNull("replyCount"));
            this.totalCount = jSONObjectProxy.getIntOrNull("totalCount");
            this.userId = jSONObjectProxy.getStringOrNull("userId");
            this.viewCount = jSONObjectProxy.getIntOrNull("viewCount");
            return;
        }
        this.title = jSONObjectProxy.getStringOrNull("title");
        setReplyCount(jSONObjectProxy.getIntOrNull("replyCount"));
        this.viewCount = jSONObjectProxy.getIntOrNull("viewCount");
        this.userId = jSONObjectProxy.getStringOrNull("userId");
        this.creationTime = jSONObjectProxy.getStringOrNull("creationTime");
        this.id = jSONObjectProxy.getStringOrNull("id");
    }

    public OrderComment(JSONObjectProxy jSONObjectProxy, int i2) {
        this(jSONObjectProxy, null, i2);
    }
}
