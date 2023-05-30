package com.jd.lib.productdetail.core.entitys;

import com.jd.lib.productdetail.core.entitys.plusmember.PdUnitedPLUSExpansion;
import java.util.List;

/* loaded from: classes15.dex */
public class PdLxFloorEntity {
    public PdDataMap dataMap;
    public String type;

    /* loaded from: classes15.dex */
    public static class PdAfterBox {
        public String aftArrowJumpType;
        public String aftArrowJumpUrl;
        public String aftArrowUrl;
        public String aftSpecialStr;
        public String aftSpecialStrColor;
        public String aftText;
        public String aftTextColor;
    }

    /* loaded from: classes15.dex */
    public static class PdDataMap {
        public PdAfterBox afterBox;
        public String bgurl;
        public PdOther other;
        public PdPreBox preBox;
        public String type;
    }

    /* loaded from: classes15.dex */
    public static class PdOther {
        public String days;
        public boolean display;
        public String eventTrackingInfo;
        public List<String> expflag;
        public String ext;
        public String flag;
        public String focusText;
        public boolean isFocus;
        public String materialCoding;
        public String price;
        public String renewBuryPoint;
        public boolean samCard;
        public boolean samMember;
        public boolean shieldLanded;
        public PdUnitedPLUSExpansion unitedPLUSExpansion;
        public int vip_status;
    }

    /* loaded from: classes15.dex */
    public static class PdPreBox {
        public String preArrowJumpType;
        public String preArrowJumpUrl;
        public String preArrowUrl;
        public String preSpecialStr;
        public String preSpecialStrColor;
        public String preText;
        public String preTextColor;
        public List<PdPreBoxSpecialList> specialList;
    }

    /* loaded from: classes15.dex */
    public static class PdPreBoxSpecialList {
        public boolean isBold;
        public String specColor;
        public int startIndex;
        public int textLength;
    }
}
