package com.jingdong.app.mall.widget.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
public class WidgetPromotionInfo implements Serializable {
    public String jumpUrl;
    public String mainTitle;
    public List<TemplateSkuInfo> templateList;

    /* loaded from: classes4.dex */
    public class TemplateSkuInfo implements Serializable {
        public String image;
        public String jumpUrl;
        public String pprice;

        public TemplateSkuInfo() {
        }
    }
}
