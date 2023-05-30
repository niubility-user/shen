package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessSmartDispatchDataEntity {
    public FactoryShipPopEntity factoryShipPop;
    public FactoryShipTipEntity factoryShipTip;
    public FareEntity fare;
    public String icon;
    public String iconPop;
    public PromiseEntity promise;
    public List<WareBusinessServiceIconEntity> serviceIconList;
    public SubtitleEntity subtitle;
    public TitleEntity title;

    /* loaded from: classes15.dex */
    public static class FactoryShipPopEntity {
        public String labelDesc;
        public String labelIcon;
    }

    /* loaded from: classes15.dex */
    public static class FactoryShipTipEntity {
        public List<FormatEntity> format;
        public String text;
    }

    /* loaded from: classes15.dex */
    public static class FareEntity {
        public List<FormatEntity> format;
        public String text;
    }

    /* loaded from: classes15.dex */
    public static class FormatEntity {
        public String color;
        public int length;
        public int startIndex;
    }

    /* loaded from: classes15.dex */
    public static class PromiseEntity {
        public List<FormatEntity> format;
        public String text;
    }

    /* loaded from: classes15.dex */
    public static class SubtitleEntity {
        public List<FormatEntity> format;
        public String text;
    }

    /* loaded from: classes15.dex */
    public static class TitleEntity {
        public List<FormatEntity> format;
        public String text;
    }
}
