package com.jingdong.app.mall.im.business;

import com.jingdong.service.impl.IMRegex;

/* loaded from: classes4.dex */
public class v extends IMRegex {
    @Override // com.jingdong.service.impl.IMRegex, com.jingdong.service.service.RegexService
    public String[] productRegex() {
        return new String[]{"^http(?:s)?:\\/\\/(?:item\\.m\\.jd\\.com\\/product|m\\.jd\\.com\\/product|item\\.jd\\.com)\\/(\\d+).html", "^http(?:s)?:\\/\\/(?:item\\.m\\.jd\\.com\\/product|m\\.jd\\.com\\/product|item\\.jd\\.com)\\/(\\d+)", "^http(?:s)?:\\/\\/(?:item\\.)?m\\.jd\\.com\\/ware\\/view\\.action\\?(?:.+&)*wareId=(\\d+)", "^http(?:s)?:\\/\\/(?:mitem\\.jd\\.hk|m\\.yiyaojd\\.com)\\/product\\/(\\d+)\\.html", "^http(?:s)?:\\/\\/(?:mitem\\.jd\\.hk|m\\.yiyaojd\\.com)\\/ware\\/view\\.action\\?(?:.+&)*wareId=(\\d+)", "^http(?:s)?:\\/\\/(?:wqitem\\.jd\\.com|wqs\\.jd\\.com|wq\\.jd\\.com|wqitem\\.jd\\.hk)\\/item\\/view\\?(?:.+&)*sku=(\\d+)", "^http(?:s)?:\\/\\/(?:m\\.paipai\\.com)\\/direct\\/goodsDetail\\?(?:.+&)*skuId=(\\d+)", "^ *http(?:s)?:\\/\\/(?:m\\.jingxi\\.com)\\/item\\/view\\?(?:.+&)*(?:sku|skuid|skuId|wareId|)=(\\d+)", "^ *http(?:s)?:\\/\\/(?:m\\.jingxi\\.com)\\/item\\/jxview\\?(?:.+&)*(?:sku|skuid|skuId|wareId|)=(\\d+)", "^ *http(?:s)?:\\/\\/(?:item-pro\\.m\\.yhd\\.com\\/product\\/index.html\\?|item-pro\\.m\\.yhd\\.com\\/production\\/index.html\\?)*wareId=(\\d+)", "^ *http(?:s)?:\\/\\/(?:item-pro\\.jd\\.hk\\/product\\/index.html\\?|item-pro\\.jd\\.hk\\/production\\/index.html\\?)*wareId=(\\d+)"};
    }
}
