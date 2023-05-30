package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import java.util.List;

/* loaded from: classes14.dex */
public class BaiTiaoPayPlanBizData {
    public AgreementServiceMapMap agreementServiceMap;
    public String baiTiaoHighlightTip;
    public String baiTiaoTip;
    public BaiTiaoExtraTip extraTip;
    public String investorDoc;
    public String mianxiHighlight;
    public List<PlanFeeEntity> planFeeList;
    public PlanServiceMap serviceMap;
    public boolean useInvestorDoc;

    public BaiTiaoPayPlanBizData(AgreementServiceMapMap agreementServiceMapMap, PlanServiceMap planServiceMap, BaiTiaoExtraTip baiTiaoExtraTip, String str, List<PlanFeeEntity> list) {
        this.baiTiaoTip = "";
        this.baiTiaoHighlightTip = "";
        this.agreementServiceMap = agreementServiceMapMap;
        this.serviceMap = planServiceMap;
        this.extraTip = baiTiaoExtraTip;
        this.mianxiHighlight = str;
        this.planFeeList = list;
        this.useInvestorDoc = false;
    }

    public BaiTiaoPayPlanBizData(AgreementServiceMapMap agreementServiceMapMap, String str, String str2, String str3, PlanServiceMap planServiceMap, BaiTiaoExtraTip baiTiaoExtraTip, String str4, List<PlanFeeEntity> list) {
        this.baiTiaoTip = "";
        this.baiTiaoHighlightTip = "";
        this.agreementServiceMap = agreementServiceMapMap;
        this.baiTiaoTip = str;
        this.baiTiaoHighlightTip = str2;
        this.investorDoc = str3;
        this.serviceMap = planServiceMap;
        this.extraTip = baiTiaoExtraTip;
        this.mianxiHighlight = str4;
        this.planFeeList = list;
        this.useInvestorDoc = true;
    }
}
