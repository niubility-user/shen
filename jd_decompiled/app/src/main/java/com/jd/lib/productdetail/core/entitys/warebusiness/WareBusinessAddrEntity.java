package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessAddrEntity {
    public List<DJDeliveryText> agingInfos;
    public WareBusinessServiceIconNormalEntity basic;
    public WareBusinessDefaultAddrEntity defaultAddr;
    public String deliveryAgingInfo;
    public int deliveryOpenMark;
    public ExtendFactoryEntity extend;
    public String imgForAddr;
    public String imgForAddrText;
    public int imgType;
    public boolean jdService;
    public String jdServiceIcon;
    public String riskLevelColor;
    public String riskLevelStock;
    public boolean scfFlag;
    public String serviceType;
    public SmartDeliveryData smartDeliveryData;
    public boolean smartDeliveryStyle;
    public WareBusinessSmartDispatchDataEntity smartDispatchData;
    public boolean smartDispatchStyle;
    public String stock;
    public StockStyle stockStyle;
    public String tagType;

    /* loaded from: classes15.dex */
    public static class ClientText {
        public List<ShowFormat> format;
        public String text;
    }

    /* loaded from: classes15.dex */
    public static class DJDeliveryText {
        public String color;
        public String text;
    }

    /* loaded from: classes15.dex */
    public static class ExtendFactoryEntity {
        public String explainDesc;
        public String labelDesc;
        public String labelDescColor;
        public String labelIcon;
        public boolean showLabelIcon;
        public String type;
    }

    /* loaded from: classes15.dex */
    public static class ShowFormat {
        public String color;
        public int length;
        public int startIndex;
    }

    /* loaded from: classes15.dex */
    public static class SmartDeliveryData {
        public WareBusinessDefaultAddrEntity defaultAddr;
        public ExtendFactoryEntity factoryShipPop;
        public ClientText factoryShipTip;
        public ClientText fare;
        public boolean fareNewLine;
        public String icon;
        public String iconPop;
        public ClientText promise;
        public List<WareBusinessServiceIconEntity> serviceIconList;
        public ClientText subtitle;
        public ClientText title;
    }

    /* loaded from: classes15.dex */
    public static class StockStyle {
        public int length;
        public int location;
        public String textColor;
    }
}
