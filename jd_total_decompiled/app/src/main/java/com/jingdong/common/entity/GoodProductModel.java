package com.jingdong.common.entity;

import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes5.dex */
public class GoodProductModel {
    private static final String TAG = "GoodProductModel";
    public static Integer sFloorNumber = 0;
    public String author;
    public String authorIcon;
    public Integer commentCnt;
    public String coupon;
    public String couponUrl;
    public Integer floorNumber;
    public Integer hasLiked;
    public Integer id;
    public String img;
    public Integer likeCnt;
    public String likeParam;
    public String name;
    public Long offset;
    public String p;
    public String pText;
    public String pType;
    public String shareImg;
    public String shareUrl;
    public Long sku;
    public String slogan;
    public String sourceValue;
    public String time;

    private static String appendSpaceChar(String str) {
        if (str == null) {
            return null;
        }
        try {
            Matcher matcher = Pattern.compile("([^a-zA-Z0-9\uff08\uff09\\(\\) ])([a-zA-Z\uff08\\(])|([^ ])([\uff08\\(])|([\uff08\\(])([^ ])|([A-Z0-9])(\\-)|(\\-)([A-Z0-9])|([0-9]*[A-Z]+[0-9]*)([^a-zA-Z0-9\uff08\uff09\\(\\) ])").matcher(str);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int i2 = 1;
                while (true) {
                    if (i2 > matcher.groupCount()) {
                        break;
                    } else if (matcher.group(i2) != null) {
                        stringBuffer2.append(matcher.group(i2));
                        stringBuffer2.append(LangUtils.SINGLE_SPACE);
                        stringBuffer2.append(matcher.group(i2 + 1));
                        break;
                    } else {
                        i2++;
                    }
                }
                if (OKLog.D) {
                    OKLog.d("Temp", "name -->> " + str);
                    OKLog.d("Temp", "stringBuffer.toString() -->> " + stringBuffer.toString());
                    OKLog.d("Temp", "sb.toString() -->> " + stringBuffer2.toString());
                }
                matcher.appendReplacement(stringBuffer, stringBuffer2.toString());
            }
            matcher.appendTail(stringBuffer);
            stringBuffer.append(LangUtils.SINGLE_SPACE);
            return stringBuffer.toString();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return str;
        }
    }

    public static ArrayList<GoodProductModel> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<GoodProductModel> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() == 0) {
            return arrayList;
        }
        ArrayList<GoodProductModel> arrayList2 = null;
        try {
            ArrayList<GoodProductModel> arrayList3 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    JSONObjectProxy jSONObject = jSONArrayPoxy.getJSONObject(i2);
                    GoodProductModel goodProductModel = new GoodProductModel();
                    goodProductModel.author = jSONObject.optString("author");
                    goodProductModel.authorIcon = jSONObject.optString("authorIcon");
                    goodProductModel.coupon = jSONObject.optString("coupon");
                    goodProductModel.couponUrl = jSONObject.optString("couponUrl");
                    goodProductModel.hasLiked = Integer.valueOf(jSONObject.optInt("hasLiked"));
                    goodProductModel.id = Integer.valueOf(jSONObject.optInt("id"));
                    goodProductModel.img = jSONObject.optString("img");
                    goodProductModel.likeCnt = Integer.valueOf(jSONObject.optInt("likeCnt"));
                    goodProductModel.commentCnt = Integer.valueOf(jSONObject.optInt("commentCnt"));
                    goodProductModel.likeParam = jSONObject.optString("likeParam");
                    goodProductModel.name = jSONObject.optString("name");
                    goodProductModel.offset = Long.valueOf(jSONObject.optLong(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET));
                    goodProductModel.p = jSONObject.optString("p");
                    goodProductModel.pType = jSONObject.optString("pType");
                    goodProductModel.pText = jSONObject.optString("pText");
                    goodProductModel.shareImg = jSONObject.optString("shareImg");
                    goodProductModel.shareUrl = jSONObject.optString("shareUrl");
                    goodProductModel.sku = Long.valueOf(jSONObject.optLong("sku"));
                    goodProductModel.slogan = jSONObject.optString("slogan");
                    goodProductModel.sourceValue = jSONObject.optString("sourceValue");
                    Integer num = sFloorNumber;
                    goodProductModel.floorNumber = num;
                    sFloorNumber = Integer.valueOf(num.intValue() + 1);
                    goodProductModel.time = jSONObject.optString("time");
                    goodProductModel.name = appendSpaceChar(goodProductModel.name);
                    arrayList3.add(goodProductModel);
                } catch (JSONException e2) {
                    e = e2;
                    arrayList2 = arrayList3;
                    if (OKLog.E) {
                        OKLog.e(TAG, e);
                    }
                    return arrayList2;
                }
            }
            return arrayList3;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public String getFormattedPrice() {
        Double valueOf;
        try {
            String str = this.p;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d && valueOf.compareTo(Double.valueOf(9999999.99d)) <= 0) {
                return new DecimalFormat("#.00").format(valueOf);
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public int getLikeType() {
        try {
            return Integer.parseInt(this.likeParam.split(ContainerUtils.KEY_VALUE_DELIMITER)[1]);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }
}
