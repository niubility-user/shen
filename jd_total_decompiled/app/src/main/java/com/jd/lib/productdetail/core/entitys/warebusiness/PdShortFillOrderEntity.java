package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes15.dex */
public class PdShortFillOrderEntity implements Serializable {
    public static final String CHECK_OUT_ENABLE = "1";
    public static final String CHECK_OUT_NO_ENABLE = "0";
    public static final String LAYER_TYPE_3 = "3";
    public static final String TAP_CLOSE_VIEW = "0";
    public static final String TAP_OPEN_VIEW = "1";
    public List<PdShortFillOrderButtonEntity> bottomButtons;
    public String checkoutEnable;
    public WareBusinessStyleEntity colorSizeInfo;
    public String fetchDataCode;
    public boolean isElderMode;
    public boolean isSop;
    public boolean newStyle;
    public String skuId;
}
