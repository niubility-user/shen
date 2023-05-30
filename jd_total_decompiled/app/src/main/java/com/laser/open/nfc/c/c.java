package com.laser.open.nfc.c;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.laser.open.nfc.model.entity.AppletBaseReq;
import com.laser.open.nfc.model.entity.BaseResp;
import com.laser.open.nfc.model.entity.GetDeleteSignInfoResp;
import com.laser.open.nfc.model.entity.QueryIssueCardInfoResp;
import com.laser.open.nfc.model.entity.QueryIssuerInfoResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardInfoResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardsResp;
import com.laser.open.nfc.model.entity.SEInfoResp;
import com.laser.open.nfc.model.entity.TrafficCardEntity;
import com.laser.open.nfc.model.http.OkHttpUtil;
import com.laser.utils.app.AppUtil;
import com.laser.utils.common.LogUtil;
import java.io.IOException;
import okhttp3.Response;

/* loaded from: classes12.dex */
public class c {
    private com.laser.open.nfc.c.e.a a;

    /* renamed from: c  reason: collision with root package name */
    private String f15677c;
    private final String b = c.class.getSimpleName();
    private final String d = "get/trafficCards.action";

    /* renamed from: e  reason: collision with root package name */
    private final String f15678e = "get/issueCardInfo.action";

    /* renamed from: f  reason: collision with root package name */
    private final String f15679f = "get/issuerInfo.action";

    /* renamed from: g  reason: collision with root package name */
    private final String f15680g = "get/deleteSignInfo.action";

    public BaseResp a(Context context, String str, String str2, String str3) {
        b.c().a(str, str2);
        this.f15677c = str3;
        OkHttpUtil.getInstance().init(AppUtil.getDomain(this.f15677c));
        QueryIssuerInfoResp b = b();
        int status = b.getStatus();
        d dVar = d.SUCCESS;
        if (status != dVar.getStatus()) {
            return new BaseResp(b.getStatus(), b.getDesc());
        }
        c(b.getSeIssuerId());
        if (this.a == null) {
            d dVar2 = d.BASE_STATUS_SE_ISSUER_UNSUPPORT;
            return new BaseResp(dVar2.getStatus(), dVar2.getMsg());
        }
        return new QueryIssuerInfoResp(dVar.getStatus(), dVar.getMsg());
    }

    public QueryTrafficCardsResp b(String str) {
        if (a().getStatus() != d.SUCCESS.getStatus()) {
            return new QueryTrafficCardsResp(a().getStatus(), a().getDesc());
        }
        QueryTrafficCardsResp queryTrafficCardsResp = new QueryTrafficCardsResp();
        try {
            AppletBaseReq appletBaseReq = new AppletBaseReq();
            appletBaseReq.setAppletAid(str);
            String json = new Gson().toJson(appletBaseReq);
            Response postSync = OkHttpUtil.getInstance().postSync(this.f15677c + "get/trafficCards.action", json);
            if (!postSync.isSuccessful()) {
                d dVar = d.BASE_STATUS_REQUEST_SERVER_FAIL;
                queryTrafficCardsResp.setStatus(dVar.getStatus());
                queryTrafficCardsResp.setDesc(dVar.getMsg() + ":" + postSync.code());
                return queryTrafficCardsResp;
            }
            return (QueryTrafficCardsResp) new Gson().fromJson(postSync.body().string(), QueryTrafficCardsResp.class);
        } catch (IOException e2) {
            LogUtil.e(this.b, "getIssuerTrafficCards exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_REQUEST_SERVER_FAIL;
            queryTrafficCardsResp.setStatus(dVar2.getStatus());
            queryTrafficCardsResp.setDesc(dVar2.getMsg());
            return queryTrafficCardsResp;
        }
    }

    public boolean c() {
        return this.a != null;
    }

    public SEInfoResp d() {
        return this.a.a();
    }

    public com.laser.open.nfc.c.e.a c(String str) {
        if (this.a == null) {
            str.hashCode();
            if (str.equals("80086000020947869")) {
                this.a = new a();
            }
        }
        return this.a;
    }

    public BaseResp c(TrafficCardEntity trafficCardEntity) {
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        int status = a.getStatus();
        d dVar = d.SUCCESS;
        if (status != dVar.getStatus()) {
            return new BaseResp(a.getStatus(), a.getDesc());
        }
        try {
            AppletBaseReq appletBaseReq = new AppletBaseReq();
            appletBaseReq.setAppletAid(trafficCardEntity.getAppletAid());
            String json = new Gson().toJson(appletBaseReq);
            Response postSync = OkHttpUtil.getInstance().postSync(this.f15677c + "get/deleteSignInfo.action", json);
            if (!postSync.isSuccessful()) {
                d dVar2 = d.BASE_STATUS_REQUEST_SERVER_FAIL;
                return new BaseResp(dVar2.getStatus(), dVar2.getMsg() + ":" + postSync.code());
            }
            GetDeleteSignInfoResp getDeleteSignInfoResp = (GetDeleteSignInfoResp) new Gson().fromJson(postSync.body().string(), GetDeleteSignInfoResp.class);
            if (getDeleteSignInfoResp.getStatus() != dVar.getStatus()) {
                return new BaseResp(getDeleteSignInfoResp.getStatus(), getDeleteSignInfoResp.getDesc());
            }
            return this.a.a(a, getDeleteSignInfoResp);
        } catch (IOException e2) {
            LogUtil.e(this.b, "requestDeleteCard exception:" + e2.getMessage());
            d dVar3 = d.BASE_STATUS_REQUEST_SERVER_FAIL;
            return new BaseResp(dVar3.getStatus(), dVar3.getMsg());
        }
    }

    public BaseResp a(TrafficCardEntity trafficCardEntity) {
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        if (a.getStatus() != d.SUCCESS.getStatus()) {
            return new BaseResp(a.getStatus(), a.getDesc());
        }
        return this.a.b(a, Integer.parseInt(b.c().b().getAppVersion()));
    }

    public BaseResp b(TrafficCardEntity trafficCardEntity) {
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        if (a.getStatus() != d.SUCCESS.getStatus()) {
            return new BaseResp(a.getStatus(), a.getDesc());
        }
        return this.a.a(a);
    }

    public BaseResp a(TrafficCardEntity trafficCardEntity, String str) {
        if (trafficCardEntity.getCardStatus() == 1) {
            d dVar = d.BASE_STATUS_CARD_DOWNLOADED;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        }
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        if (a.getStatus() != d.SUCCESS.getStatus()) {
            return new BaseResp(a.getStatus(), a.getDesc());
        }
        return this.a.a(a, str, Integer.parseInt(b.c().b().getAppVersion()));
    }

    public BaseResp b(TrafficCardEntity trafficCardEntity, String str) {
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        if (a.getStatus() != d.SUCCESS.getStatus()) {
            return new QueryTrafficCardInfoResp(a.getStatus(), a.getDesc());
        }
        return this.a.a(a, str);
    }

    private QueryIssuerInfoResp b() {
        try {
            Response postSync = OkHttpUtil.getInstance().postSync(this.f15677c + "get/issuerInfo.action");
            if (!postSync.isSuccessful()) {
                d dVar = d.BASE_STATUS_REQUEST_SERVER_FAIL;
                return new QueryIssuerInfoResp(dVar.getStatus(), dVar.getMsg() + ":" + postSync.code());
            }
            return (QueryIssuerInfoResp) new Gson().fromJson(postSync.body().string(), QueryIssuerInfoResp.class);
        } catch (IOException e2) {
            LogUtil.e(this.b, "getSEIssuerInfo exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_REQUEST_SERVER_FAIL;
            return new QueryIssuerInfoResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }

    public BaseResp a(TrafficCardEntity trafficCardEntity, String str, String str2) {
        if (trafficCardEntity.getCardStatus() == 0) {
            d dVar = d.BASE_STATUS_CARD_NOT_DOWNLOAD;
            return new BaseResp(dVar.getStatus(), dVar.getMsg());
        }
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        if (a.getStatus() != d.SUCCESS.getStatus()) {
            return new BaseResp(a.getStatus(), a.getDesc());
        }
        return this.a.a(a, str, Integer.parseInt(b.c().b().getAppVersion()), str2);
    }

    public QueryTrafficCardInfoResp a(TrafficCardEntity trafficCardEntity, int i2) {
        QueryIssueCardInfoResp a = a(trafficCardEntity.getAppletAid());
        if (a.getStatus() != d.SUCCESS.getStatus()) {
            return new QueryTrafficCardInfoResp(a.getStatus(), a.getDesc());
        }
        return this.a.a(a, i2);
    }

    private BaseResp a() {
        if (TextUtils.isEmpty(b.c().b().getCplc())) {
            SEInfoResp a = this.a.a();
            if (a.getStatus() == d.SUCCESS.getStatus()) {
                b.c().b().setCplc(a.getCplc());
                b.c().b().setSeid(a.getSeid());
                b.c().b().setAppVersion(a.getWalletVersionCode() + "");
            }
            return new BaseResp(a.getStatus(), a.getDesc());
        }
        d dVar = d.SUCCESS;
        return new BaseResp(dVar.getStatus(), dVar.getMsg());
    }

    private QueryIssueCardInfoResp a(String str) {
        if (a().getStatus() != d.SUCCESS.getStatus()) {
            return new QueryIssueCardInfoResp(a().getStatus(), a().getDesc());
        }
        AppletBaseReq appletBaseReq = new AppletBaseReq();
        appletBaseReq.setAppletAid(str);
        String json = new Gson().toJson(appletBaseReq);
        try {
            Response postSync = OkHttpUtil.getInstance().postSync(this.f15677c + "get/issueCardInfo.action", json);
            if (!postSync.isSuccessful()) {
                d dVar = d.BASE_STATUS_REQUEST_SERVER_FAIL;
                return new QueryIssueCardInfoResp(dVar.getStatus(), dVar.getMsg() + ":" + postSync.code());
            }
            return (QueryIssueCardInfoResp) new Gson().fromJson(postSync.body().string(), QueryIssueCardInfoResp.class);
        } catch (IOException e2) {
            LogUtil.e(this.b, "getIssuerCardInfo exception:" + e2.getMessage());
            d dVar2 = d.BASE_STATUS_REQUEST_SERVER_FAIL;
            return new QueryIssueCardInfoResp(dVar2.getStatus(), dVar2.getMsg());
        }
    }
}
