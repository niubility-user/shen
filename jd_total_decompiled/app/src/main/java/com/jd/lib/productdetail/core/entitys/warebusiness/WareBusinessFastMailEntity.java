package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessFastMailEntity {
    public String fastMailTitle;
    public String jumpUrl;
    public List<ServiceListEntity> serviceList;

    /* loaded from: classes15.dex */
    public static class ServiceListEntity {
        public String deliveryMode;
        public String freightText;
        public int selected;
        public String serviceDesc;
    }
}
