package com.jingdong.common.unification.router.builderimpl;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.jdsdk.constant.PDConstant;

/* loaded from: classes6.dex */
public class ProductDetailBuilder extends RouterBuilder<ProductDetailBuilder, ProductDetailRouterEntry> implements PDConstant {

    /* loaded from: classes6.dex */
    public class ProductDetailRouterEntry extends RouterEntry<ProductDetailRouterEntry> {
        public String testData;

        public ProductDetailRouterEntry() {
        }

        public void setProductDetail(String str) {
            this.testData = str;
        }
    }

    public ProductDetailBuilder() {
        super("JDProductDetailModule", "show");
    }

    public ProductDetailBuilder addCartTime(String str) {
        putString("addCartTime", str);
        return this;
    }

    public ProductDetailBuilder clickUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            putString("clickUrl", str);
        }
        return this;
    }

    public ProductDetailBuilder flt(String str) {
        if (!TextUtils.isEmpty(str)) {
            putString("EXTRA_FLT", str);
        }
        return this;
    }

    public ProductDetailBuilder fromCar() {
        putString(PDConstant.EXTRA_FROM_CAR, DYConstants.DY_TRUE);
        return this;
    }

    public ProductDetailBuilder fromSource(String str) {
        if (!TextUtils.isEmpty(str)) {
            putString("fromSource", str);
        }
        return this;
    }

    public ProductDetailBuilder imageTitlePrice(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            putString("image", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            putString("title", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            putString("price", str3);
        }
        return this;
    }

    public ProductDetailBuilder personas() {
        return personas(PDConstant.EXTRA_PERSONAS);
    }

    public ProductDetailBuilder recommendExt(String str) {
        putString("recommend_ext", str);
        return this;
    }

    public ProductDetailBuilder searchParam(String str) {
        if (!TextUtils.isEmpty(str)) {
            putString(PDConstant.EXTRA_SEARCH_PARAM, str);
        }
        return this;
    }

    public ProductDetailBuilder secKillParams(String str) {
        putString("secKillParams", str);
        return this;
    }

    public ProductDetailBuilder skuId(String str) {
        putString("skuId", str);
        return this;
    }

    public ProductDetailBuilder sop(boolean z) {
        if (z) {
            putString(PDConstant.EXTRA_SOP, DYConstants.DY_TRUE);
        }
        return this;
    }

    public ProductDetailBuilder sourceEntity(SourceEntity sourceEntity) {
        if (sourceEntity != null) {
            putString("sourceType", sourceEntity.getSourceType());
            putString("sourceValue", sourceEntity.getSourceValue());
        }
        return this;
    }

    public ProductDetailBuilder storeId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mBundle.putString("storeId", str);
        }
        return this;
    }

    public ProductDetailBuilder targetUrl(String str) {
        return clickUrl(str);
    }

    public ProductDetailBuilder testData(String str) {
        ((ProductDetailRouterEntry) this.mRouterEntry).testData = str;
        return this;
    }

    public ProductDetailBuilder toTabComment() {
        putString(PDConstant.EXTRA_TO_TAB, PDConstant.TAB_COMMENT);
        return this;
    }

    public ProductDetailBuilder toTabDetail() {
        putString(PDConstant.EXTRA_TO_TAB, PDConstant.TAB_DETAIL);
        return this;
    }

    public ProductDetailBuilder toTabMian() {
        putString(PDConstant.EXTRA_TO_TAB, PDConstant.TAB_MAIN);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    public ProductDetailRouterEntry initRouterEntry() {
        return new ProductDetailRouterEntry();
    }

    public ProductDetailBuilder personas(String str) {
        if (!TextUtils.isEmpty(str)) {
            putString(PDConstant.EXTRA_PERSONAS, str);
        }
        return this;
    }
}
