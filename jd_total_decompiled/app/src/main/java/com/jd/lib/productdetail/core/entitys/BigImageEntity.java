package com.jd.lib.productdetail.core.entitys;

import com.jd.lib.productdetail.core.PdOneToNPriceVo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDpgSmallInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdMainSku;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageQaEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class BigImageEntity implements Serializable {
    public List<String> anchorTypeList;
    public int askPosition;
    public WareImageRecommendRankEntity bangDanInfo;
    public String buyersIcon;
    public String commentDefaultUrl;
    public boolean commentPriorityFlagNew;
    public int commentZcxPosition;
    public ArrayList<PdDpgSmallInfo> dpgDetails;
    public PdDrugInfo drugInfo;
    public String mCategroyId1;
    public String mCategroyId2;
    public String mCategroyId3;
    public int magicHeadPicType;
    public PdMainSku mainProduct;
    public List<PdOneToNPriceVo> oneToNPriceVoList;
    public String rankDefaultUrl;
    public PdPreferentialRecommendProductListInfo recommendProductListInfo;
    public String storeId;
    public String suitAnchorType;
    public ArrayList<PdDpgSmallInfo> suitDetails;
    public int suitPosition;
    public WareImageQaEntity wareImageQaEntity;
    public WareImageRecommendEntity wareImageRecommendEntity;
    public int ypsmsPosition;
    public int recommendPosition = -1;
    public int recommendRankPosition = -1;
}
