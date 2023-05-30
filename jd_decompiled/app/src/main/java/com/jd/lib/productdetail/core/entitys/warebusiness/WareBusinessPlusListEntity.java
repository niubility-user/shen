package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.lib.productdetail.core.entitys.PDLocStoreEntity;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessPlusListEntity {
    public String _loc_2_nearby_shop_id = "";
    public PDLocStoreEntity _loc_2_shop_info;
    public String categoryCode;
    public String categoryDesc;
    public String categoryDescColor;
    public String discountDesc;
    public boolean mChoice;
    public String minPrice;
    public int productType;
    public List<WareBusinessJdServerProduct> products;
    public String scIconUrl;
    public String scIdStr;
    public String scName;
    public int scOrder;

    public boolean isLocService() {
        return this.productType == 1;
    }
}
