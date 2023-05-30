package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessYanBaoGroupEntity {
    public String categoryCode;
    public String detailUrl;
    public String imgurl;
    public long productId;
    public List<WareBusinessYanBaoItemEntity> products;
    public String sortName;
    public boolean expendOn = false;
    @Deprecated
    public boolean expanded = false;
    public int height = 0;
}
