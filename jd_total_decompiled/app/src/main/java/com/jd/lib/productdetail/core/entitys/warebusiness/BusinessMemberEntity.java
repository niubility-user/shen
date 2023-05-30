package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.lib.productdetail.core.entitys.plusmember.PdUnitedPLUSExpansion;
import java.util.List;

/* loaded from: classes15.dex */
public class BusinessMemberEntity {
    public MemberAfterBox afterBox;
    public String bgurl;
    public MemberOther other;
    public MemberPreBox preBox;
    public String type;

    /* loaded from: classes15.dex */
    public static class MemberAfterBox {
        public String aftArrowJumpType;
        public String aftArrowJumpUrl;
        public String aftArrowUrl;
        public String aftIconUrl;
        public String aftSpecialStr;
        public String aftSpecialStrColor;
        public String aftText;
        public String aftTextColor;
    }

    /* loaded from: classes15.dex */
    public static class MemberOther {
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
    public static class MemberPreBox {
        public String preArrowJumpType;
        public String preArrowJumpUrl;
        public String preArrowUrl;
        public String preIconUrl;
        public String preSpecialStr;
        public String preSpecialStrColor;
        public String preText;
        public String preTextColor;
    }
}
