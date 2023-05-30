package com.jd.lib.productdetail.core.entitys.topimagerecommend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdRankEntity implements Serializable {
    public String channelEntryTitle;
    public String rankId;
    public String rankType;
    public List<PdSkuInfoListEntity> skuInfoList = new ArrayList();
}
