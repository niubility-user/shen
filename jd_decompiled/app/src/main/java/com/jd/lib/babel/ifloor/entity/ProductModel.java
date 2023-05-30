package com.jd.lib.babel.ifloor.entity;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.babel.ifloor.utils.StringUtil;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;

/* loaded from: classes13.dex */
public class ProductModel {
    public String brandName;
    public String commentCount;
    public String copyWriting;
    public String copyWritingDown;
    public String goodRate;
    public String image;
    public BabelJumpEntity jump;
    public String linePrice;
    public String naFlagImgM;
    public String name;
    public String nameCn;
    public String pPrice;
    public String pcpPrice;
    public String plusPrice;
    public String promoTags;
    public String purchasePrice;
    public String skuId;
    public String tag;

    public String getPrice(Context context, String str) {
        return getPrice(context, str, "");
    }

    public String getPrice(Context context, String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : StringUtil.getYangJiaoPrice(context, str);
    }
}
