package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class MiaoShaBrand {
    public String brandIdOld;
    public String brandImg;
    public int brandImgHeight;
    public int brandImgWidth;
    public String brandName;
    public String brandOrder;
    public String discountImg;
    public String endTime;
    public long endTimeRemain;
    public List<MiaoShaBrandProduct> goodsDetail;
    public List<MiaoShaBrandProduct> goodsList;
    public long id;
    public boolean isPrebuyBanner = false;
    public String itemColor;
    public JumpEntity jump;
    public String newBrandBgImg;
    public int position;
    public String sourceValue;
    public String startTime;
    public long startTimeRemain;
    public String startTimeShow;
    public String subTitle;
    public String titile;
    public String title;

    public MiaoShaBrand() {
    }

    public static ArrayList<MiaoShaBrand> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<MiaoShaBrand> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<MiaoShaBrand> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    if (jSONArrayPoxy.getJSONObject(i2) != null) {
                        arrayList2.add(new MiaoShaBrand(jSONArrayPoxy.getJSONObject(i2)));
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

    public String getBrandName() {
        String str = this.brandName;
        return str == null ? "" : str;
    }

    public String getSubTitle() {
        String str = this.subTitle;
        return str == null ? "" : str;
    }

    public String getTitile() {
        String str = this.titile;
        return str == null ? "" : str;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public MiaoShaBrand(JSONObjectProxy jSONObjectProxy) {
        this.id = jSONObjectProxy.optLong("id");
        this.brandName = jSONObjectProxy.optString("brandName");
        this.title = jSONObjectProxy.optString("title");
        this.titile = jSONObjectProxy.optString("titile");
        this.subTitle = jSONObjectProxy.optString("subTitle");
        this.brandImg = jSONObjectProxy.optString("brandImg");
        this.brandImgHeight = jSONObjectProxy.optInt("brandImgHeight");
        this.brandImgWidth = jSONObjectProxy.optInt("brandImgWidth");
        this.brandOrder = jSONObjectProxy.optString("brandOrder");
        this.itemColor = jSONObjectProxy.optString("itemColor");
        this.startTime = jSONObjectProxy.optString("startTime");
        this.endTime = jSONObjectProxy.optString("endTime");
        this.startTimeRemain = jSONObjectProxy.optLong("startTimeRemain");
        this.endTimeRemain = jSONObjectProxy.optLong("endTimeRemain");
        this.position = jSONObjectProxy.optInt("position");
        this.sourceValue = jSONObjectProxy.optString("sourceValue", "");
        this.brandIdOld = jSONObjectProxy.optString("brandIdOld", "");
        this.newBrandBgImg = jSONObjectProxy.optString("newBrandBgImg", "");
        if (jSONObjectProxy.optJSONObject("jump") != null) {
            this.jump = (JumpEntity) JDJSON.parseObject(jSONObjectProxy.optJSONObject("jump").toString(), JumpEntity.class);
        }
        JSONArrayPoxy jSONArrayOrNull = jSONObjectProxy.getJSONArrayOrNull("goodsList");
        if (jSONArrayOrNull != null) {
            this.goodsList = JDJSON.parseArray(jSONArrayOrNull.toString(), MiaoShaBrandProduct.class);
        }
        this.discountImg = jSONObjectProxy.optString("discountImg");
        JSONArrayPoxy jSONArrayOrNull2 = jSONObjectProxy.getJSONArrayOrNull("goodsDetail");
        if (jSONArrayOrNull2 != null) {
            this.goodsDetail = JDJSON.parseArray(jSONArrayOrNull2.toString(), MiaoShaBrandProduct.class);
        }
        this.startTimeShow = jSONObjectProxy.optString("startTimeShow", "");
    }
}
