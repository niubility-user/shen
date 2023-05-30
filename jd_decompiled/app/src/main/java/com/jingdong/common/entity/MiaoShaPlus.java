package com.jingdong.common.entity;

import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MiaoShaPlus {
    private static final String TAG = "MiaoShaPlus";
    public String imageUrl;
    public String jdPrice;
    public String mPlusProductImg;
    public String mPlusProductTitle;
    public String plusPrice;
    public int position;
    public long promotionId;
    public String sourceValue;
    public String spuId;
    public String wareId;
    public String wname;

    public MiaoShaPlus() {
    }

    public static ArrayList<MiaoShaPlus> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<MiaoShaPlus> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<MiaoShaPlus> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    if (jSONArrayPoxy.getJSONObject(i2) != null) {
                        arrayList2.add(new MiaoShaPlus(jSONArrayPoxy.getJSONObject(i2)));
                    }
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
        }
    }

    public String getJdPrice() {
        Double valueOf;
        try {
            String str = this.jdPrice;
            if (str != null && !"".equals(str) && !LangUtils.SINGLE_SPACE.equals(this.jdPrice) && (valueOf = Double.valueOf(this.jdPrice)) != null && valueOf.doubleValue() >= 0.0d) {
                return new DecimalFormat("#.##").format(valueOf);
            }
            return LangUtils.SINGLE_SPACE;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return LangUtils.SINGLE_SPACE;
        }
    }

    public String getPlusName() {
        String str = this.wname;
        return str == null ? "" : str;
    }

    public String getPlusPrice() {
        Double valueOf;
        try {
            String str = this.plusPrice;
            if (str != null && !"".equals(str) && !LangUtils.SINGLE_SPACE.equals(this.plusPrice) && (valueOf = Double.valueOf(this.plusPrice)) != null && valueOf.doubleValue() >= 0.0d) {
                return new DecimalFormat("#.##").format(valueOf);
            }
            return LangUtils.SINGLE_SPACE;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return LangUtils.SINGLE_SPACE;
        }
    }

    public MiaoShaPlus(JSONObjectProxy jSONObjectProxy) {
        this.promotionId = jSONObjectProxy.optLong("promotionId");
        this.wname = jSONObjectProxy.optString("wname");
        this.position = jSONObjectProxy.optInt("position");
        this.jdPrice = jSONObjectProxy.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.plusPrice = jSONObjectProxy.optString(CartConstant.KEY_PLUSPRICE);
        this.imageUrl = jSONObjectProxy.optString("imageUrl");
        this.spuId = jSONObjectProxy.optString("spuId");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
        this.wareId = jSONObjectProxy.optString("wareId");
    }
}
