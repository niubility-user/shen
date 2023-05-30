package com.jingdong.common.entity.productdetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class PDStylePropertyEntity implements Serializable {
    public String btnImage;
    public String bybtFlag;
    public String bybtFlagImg;
    public boolean hasBubble;
    public boolean hasService;
    public String iconInfo;
    public String imageUrl;
    public boolean isDash;
    public boolean isSelect;
    public String ktyf;
    public String mtaSkuId;
    public int position;
    public String saleAttrValuePrice;
    public String selectImg;
    public String selectText;
    public String serviceInfr;
    public String serviceInfrUrl;
    public String serviceText;
    public Map<String, String> skuBybtImgList;
    public String skuId;
    public Map<String, String> skuImgList;
    public int status;
    public String stock;
    public String text;
    public String title;
    public List<String> skuList = new ArrayList();
    public boolean isContainsSopSku = true;
}
