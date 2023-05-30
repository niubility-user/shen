package com.babel.example.floor.entity;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jingdong.jdsdk.res.StringUtil;

/* loaded from: classes.dex */
public class ProductModel {
    public String image;
    public BabelJumpEntity jump;
    public String name;
    public String pPrice;
    public String pcpPrice;
    public String skuId;

    public String getPPrice(Context context) {
        return TextUtils.isEmpty(this.pPrice) ? StringUtil.no_price : com.jd.lib.babel.ifloor.utils.StringUtil.getYangJiaoPrice(context, this.pPrice);
    }

    public String getPcpPrice(Context context) {
        return (TextUtils.isEmpty(this.pcpPrice) || this.pcpPrice.equals(this.pPrice)) ? "" : com.jd.lib.babel.ifloor.utils.StringUtil.getYangJiaoPrice(context, this.pcpPrice);
    }
}
