package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.lib.productdetail.core.entitys.loc.LocInfo;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class WareBusinessMenDianEntity {
    public String bgImage;
    public BuyStep buyStep;
    public LocInfo locInfo;

    /* loaded from: classes15.dex */
    public static class BuyStep {
        public String iconLink;
        public ArrayList<Step> iconList;

        /* loaded from: classes15.dex */
        public static class Step {
            public String icon;
            public String text;
        }
    }
}
