package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdPaiPaiReplaceDataEntity {
    public String code;
    public DataBean data;
    public String page;

    /* loaded from: classes3.dex */
    public class DataBean {
        public ClearVoBean clearVo;
        public InquiryOptionBean inquiryOption;
        public MineInfoBean mineInfo;
        public OldForNewFlowVo oldForNewFlowVo;
        public OldForNewVoBean oldForNewVo;
        public String oldProductId;

        /* loaded from: classes3.dex */
        public class InquiryOptionBean {
            public ArrayList<InquiryItemBasePropListBean> inquiryItemBasePropList;
            public ArrayList<InquiryItemBasePropListBean> inquiryItemUsePropList;
            public ArrayList<Integer> unRecyclablePvIds;
            public ArrayList<ArrayList<Integer>> validItemPvIdGroup;

            /* loaded from: classes3.dex */
            public class InquiryItemBasePropListBean {
                public String attrId;
                public String attrName;
                public int attrType;
                public String imageUrls;
                public ArrayList<InquiryItemBasePropValListBean> inquiryItemBasePropValList;
                public ArrayList<InquiryItemBasePropValListBean> inquiryItemUsePropValList;
                public boolean isEnable = true;
                public boolean isSelect;
                public int multi;
                public int sortNum;
                public String textTip;

                /* loaded from: classes3.dex */
                public class InquiryItemBasePropValListBean {
                    public String attrName;
                    public int attrValId;
                    public int id;
                    public String imageUrls;
                    public boolean isSelect;
                    public int sortNum;
                    public String textTip;
                    public int valType;
                    public String valueName;
                    public boolean isUnRecycleEnable = true;
                    public boolean isEnable = true;

                    public InquiryItemBasePropValListBean() {
                    }
                }

                public InquiryItemBasePropListBean() {
                }
            }

            public InquiryOptionBean() {
            }
        }

        /* loaded from: classes3.dex */
        public class MineInfoBean {
            public String img;
            public String text1;
            public String text2;

            public MineInfoBean() {
            }
        }

        /* loaded from: classes3.dex */
        public class OldForNewVoBean {
            public OldForNewFlowVo flowVo;

            public OldForNewVoBean() {
            }
        }

        public DataBean() {
        }
    }
}
