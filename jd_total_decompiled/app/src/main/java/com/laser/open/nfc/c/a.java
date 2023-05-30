package com.laser.open.nfc.c;

import android.text.TextUtils;
import com.laser.open.nfc.hw.entity.BaseResponse;
import com.laser.open.nfc.hw.entity.CardDataResponse;
import com.laser.open.nfc.hw.entity.CplcDataResponse;
import com.laser.open.nfc.hw.entity.HwSEInfoEntity;
import com.laser.open.nfc.hw.entity.HwTrafficCardDataEntity;
import com.laser.open.nfc.model.entity.BaseResp;
import com.laser.open.nfc.model.entity.GetDeleteSignInfoResp;
import com.laser.open.nfc.model.entity.QueryIssueCardInfoResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardInfoResp;
import com.laser.open.nfc.model.entity.SEInfoResp;
import com.laser.utils.common.LogUtil;

/* loaded from: classes12.dex */
public final class a implements com.laser.open.nfc.c.e.a {
    private final String a = a.class.getSimpleName();

    public int a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            if (Integer.parseInt(str) == 0) {
                return 0;
            }
            return com.laser.open.nfc.b.d.getStatusCode(Integer.parseInt(str));
        }
        return Integer.parseInt(str2);
    }

    @Override // com.laser.open.nfc.c.e.a
    public BaseResp b(QueryIssueCardInfoResp queryIssueCardInfoResp, int i2) {
        try {
            BaseResponse a = com.laser.open.nfc.b.b.d().a(queryIssueCardInfoResp.getIssueCardId(), i2);
            int a2 = a(a.getResultCd(), a.getResultCode());
            com.laser.open.nfc.b.c cVar = com.laser.open.nfc.b.c.SUCCESS;
            if (a2 != cVar.getStatus()) {
                return new BaseResp(a2, a.getResultMsg());
            }
            BaseResponse a3 = com.laser.open.nfc.b.b.d().a(queryIssueCardInfoResp.getIssueCardId());
            int a4 = a(a3.getResultCd(), a3.getResultCode());
            if (a4 != cVar.getStatus()) {
                return new BaseResp(a4, a3.getResultMsg());
            }
            d dVar = d.SUCCESS;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        } catch (Exception e2) {
            LogUtil.e(this.a, "checkIssueCardCondition exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_LOCAL_ERROR;
            return new BaseResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, String str, int i2) {
        try {
            BaseResponse a = com.laser.open.nfc.b.b.d().a(queryIssueCardInfoResp.getIssueCardId(), i2);
            int a2 = a(a.getResultCd(), a.getResultCode());
            com.laser.open.nfc.b.c cVar = com.laser.open.nfc.b.c.SUCCESS;
            if (a2 != cVar.getStatus()) {
                return new BaseResp(a2, a.getResultMsg());
            }
            BaseResponse a3 = com.laser.open.nfc.b.b.d().a(queryIssueCardInfoResp.getIssueCardId());
            int a4 = a(a3.getResultCd(), a3.getResultCode());
            if (a4 != cVar.getStatus()) {
                return new BaseResp(a4, a3.getResultMsg());
            }
            BaseResponse b = com.laser.open.nfc.b.b.d().b(queryIssueCardInfoResp.getSeIssuerChannelId(), queryIssueCardInfoResp.getIssueCardId(), str);
            int a5 = a(b.getResultCd(), b.getResultCode());
            if (a5 != cVar.getStatus()) {
                return new BaseResp(a5, b.getResultMsg());
            }
            BaseResponse a6 = com.laser.open.nfc.b.b.d().a(queryIssueCardInfoResp.getSeIssuerChannelId(), queryIssueCardInfoResp.getIssueCardId(), str);
            int a7 = a(a6.getResultCd(), a6.getResultCode());
            if (a7 != cVar.getStatus()) {
                return new BaseResp(a7, a6.getResultMsg());
            }
            d dVar = d.SUCCESS;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        } catch (Exception e2) {
            LogUtil.e(this.a, "issueCard exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_LOCAL_ERROR;
            return new BaseResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp) {
        try {
            BaseResponse b = com.laser.open.nfc.b.b.d().b(queryIssueCardInfoResp.getIssueCardId());
            int a = a(b.getResultCd(), b.getResultCode());
            if (a != com.laser.open.nfc.b.c.SUCCESS.getStatus()) {
                return new BaseResp(a, b.getResultMsg());
            }
            d dVar = d.SUCCESS;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        } catch (Exception e2) {
            LogUtil.e(this.a, "checkTopUpCondition exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_LOCAL_ERROR;
            return new BaseResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, String str, int i2, String str2) {
        try {
            BaseResponse b = com.laser.open.nfc.b.b.d().b(queryIssueCardInfoResp.getIssueCardId());
            int a = a(b.getResultCd(), b.getResultCode());
            com.laser.open.nfc.b.c cVar = com.laser.open.nfc.b.c.SUCCESS;
            if (a != cVar.getStatus()) {
                return new BaseResp(a, b.getResultMsg());
            }
            BaseResponse b2 = com.laser.open.nfc.b.b.d().b(queryIssueCardInfoResp.getSeIssuerChannelId(), queryIssueCardInfoResp.getIssueCardId(), str, str2);
            int a2 = a(b2.getResultCd(), b2.getResultCode());
            if (a2 != cVar.getStatus()) {
                return new BaseResp(a2, b2.getResultMsg());
            }
            d dVar = d.SUCCESS;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        } catch (Exception e2) {
            LogUtil.e(this.a, "topUp exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_LOCAL_ERROR;
            return new BaseResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public QueryTrafficCardInfoResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, int i2) {
        try {
            CardDataResponse b = com.laser.open.nfc.b.b.d().b(queryIssueCardInfoResp.getIssueCardId(), i2);
            int a = a(b.getResultCd(), b.getResultCode());
            if (a != com.laser.open.nfc.b.c.SUCCESS.getStatus()) {
                return new QueryTrafficCardInfoResp(a, b.getResultMsg());
            }
            QueryTrafficCardInfoResp queryTrafficCardInfoResp = new QueryTrafficCardInfoResp();
            d dVar = d.SUCCESS;
            queryTrafficCardInfoResp.setStatus(dVar.getStatus());
            queryTrafficCardInfoResp.setDesc(dVar.getMsg());
            HwTrafficCardDataEntity data = b.getData();
            queryTrafficCardInfoResp.setCardNo(data.getCardNo());
            queryTrafficCardInfoResp.setBalance(data.getBalance());
            queryTrafficCardInfoResp.setDefault(data.isDefault());
            queryTrafficCardInfoResp.setLogicNo(data.getLogicCardNo());
            queryTrafficCardInfoResp.setValidateDate(data.getValidateDate());
            queryTrafficCardInfoResp.setTransRecords(data.getTransRecords());
            queryTrafficCardInfoResp.setCardArt(data.getCardArt());
            queryTrafficCardInfoResp.setMetroStatus(data.getMetroStatus());
            queryTrafficCardInfoResp.setTimesRecords(data.getTimesRecords());
            return queryTrafficCardInfoResp;
        } catch (Exception e2) {
            LogUtil.e(this.a, "queryCardInfo exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_LOCAL_ERROR;
            return new QueryTrafficCardInfoResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public SEInfoResp a() {
        try {
            CplcDataResponse c2 = com.laser.open.nfc.b.b.d().c();
            int a = a(c2.getResultCd(), c2.getResultCode());
            if (a != com.laser.open.nfc.b.c.SUCCESS.getStatus()) {
                return new SEInfoResp(a, c2.getResultMsg());
            }
            SEInfoResp sEInfoResp = new SEInfoResp();
            HwSEInfoEntity data = c2.getData();
            d dVar = d.SUCCESS;
            sEInfoResp.setStatus(dVar.getStatus());
            sEInfoResp.setDesc(dVar.getMsg());
            sEInfoResp.setCplc(data.getCplc());
            sEInfoResp.setSeid(data.getSeid());
            sEInfoResp.setWalletVersionCode(data.getWalletVersionCode());
            return sEInfoResp;
        } catch (Exception e2) {
            LogUtil.e(this.a, "getSEInfo exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_LOCAL_ERROR;
            return new SEInfoResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, String str) {
        try {
            BaseResponse c2 = com.laser.open.nfc.b.b.d().c(queryIssueCardInfoResp.getSeIssuerChannelId(), queryIssueCardInfoResp.getIssueCardId(), str);
            return new BaseResp(a(c2.getResultCd(), c2.getResultCode()), c2.getResultMsg());
        } catch (Exception e2) {
            LogUtil.e(this.a, "setDefaultCard exception:" + e2.getMessage());
            d dVar = d.BASE_STATUS_LOCAL_ERROR;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        }
    }

    @Override // com.laser.open.nfc.c.e.a
    public BaseResp a(QueryIssueCardInfoResp queryIssueCardInfoResp, GetDeleteSignInfoResp getDeleteSignInfoResp) {
        try {
            BaseResponse a = com.laser.open.nfc.b.b.d().a(queryIssueCardInfoResp.getSeIssuerChannelId(), queryIssueCardInfoResp.getIssueCardId(), getDeleteSignInfoResp.getSignData(), getDeleteSignInfoResp.getTimestamp());
            return new BaseResp(a(a.getResultCd(), a.getResultCode()), a.getResultMsg());
        } catch (Exception e2) {
            LogUtil.e(this.a, "deleteCard exception:" + e2.getMessage());
            d dVar = d.BASE_STATUS_LOCAL_ERROR;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        }
    }
}
