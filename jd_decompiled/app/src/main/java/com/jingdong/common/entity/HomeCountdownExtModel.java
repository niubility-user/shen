package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class HomeCountdownExtModel {
    public long activityEndTime;
    public long activityStartTime;
    public String afterCountdownImg;
    public String inCountdownDigitColor;
    public String inCountdownImg;
    public String inCountdownText;
    public String inCountdownTextColor;
    public JumpEntity jump;
    public int showClock;
    public long timeRemain;
    public String xviewUrl;

    public HomeCountdownExtModel(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public static ArrayList<HomeCountdownExtModel> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<HomeCountdownExtModel> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<HomeCountdownExtModel> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    if (jSONArrayPoxy.getJSONObject(i2) != null) {
                        arrayList2.add(new HomeCountdownExtModel(jSONArrayPoxy.getJSONObject(i2)));
                    }
                } catch (JSONException unused) {
                    arrayList = arrayList2;
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException unused2) {
        }
    }

    private void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.activityStartTime = JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "activityStartTime", 0L);
        this.activityEndTime = JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "activityEndTime", 0L);
        this.timeRemain = JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "timeRemain", 0L);
        this.xviewUrl = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "xviewUrl", "");
        this.afterCountdownImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "afterCountdownImg", "");
        this.inCountdownImg = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "inCountdownImg", "");
        this.inCountdownText = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "inCountdownText", "");
        this.inCountdownTextColor = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "inCountdownTextColor", "");
        this.inCountdownDigitColor = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "inCountdownDigitColor", "");
        this.showClock = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "showClock", 0);
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("jump");
        if (jSONObject != null) {
            this.jump = (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
        }
    }

    public HomeCountdownExtModel(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }

    public static ArrayList<HomeCountdownExtModel> toList(JDJSONArray jDJSONArray) {
        ArrayList<HomeCountdownExtModel> arrayList = null;
        if (jDJSONArray == null) {
            return null;
        }
        try {
            ArrayList<HomeCountdownExtModel> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                try {
                    if (jDJSONArray.getJSONObject(i2) != null) {
                        arrayList2.add(new HomeCountdownExtModel(jDJSONArray.getJSONObject(i2)));
                    }
                } catch (Exception unused) {
                    arrayList = arrayList2;
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception unused2) {
        }
    }
}
