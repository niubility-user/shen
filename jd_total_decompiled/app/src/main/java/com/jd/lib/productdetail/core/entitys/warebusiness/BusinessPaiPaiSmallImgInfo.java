package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class BusinessPaiPaiSmallImgInfo {
    public String floorName;
    public ArrayList<BusinessPaiPaiSmallImgItem> littleImageList;
    public int selectPosition = -1;

    /* loaded from: classes15.dex */
    public class BusinessPaiPaiSmallImgItem {
        public String inspectSkuId;
        public String price;
        public String smallImage;
        public boolean stockStatus = false;

        public BusinessPaiPaiSmallImgItem() {
        }

        public boolean isOutOfStock() {
            return this.stockStatus;
        }
    }

    public boolean isEffective() {
        ArrayList<BusinessPaiPaiSmallImgItem> arrayList = this.littleImageList;
        return (arrayList == null || arrayList.size() <= 0 || TextUtils.isEmpty(this.floorName)) ? false : true;
    }
}
