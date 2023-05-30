package com.jdpaysdk.freechargesdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.jdpay.system.SystemInfo;
import com.jdpaysdk.freechargesdk.entity.DefaultCardParam;
import com.jdpaysdk.freechargesdk.entity.InitParam;
import com.jdpaysdk.freechargesdk.entity.IssueCardParam;
import com.jdpaysdk.freechargesdk.entity.PreInfo;
import com.jdpaysdk.freechargesdk.entity.QueryCardInfoParam;
import com.jdpaysdk.freechargesdk.entity.QueryTrafficCardInfoBean;
import com.jdpaysdk.freechargesdk.entity.Request;
import com.jdpaysdk.freechargesdk.entity.ResponBaseBean;
import com.jdpaysdk.freechargesdk.entity.ResponTrafficcardBean;
import com.jdpaysdk.freechargesdk.entity.SEInfoBean;
import com.jdpaysdk.freechargesdk.entity.TopupParam;
import com.jingdong.sdk.platform.business.personal.R2;
import com.laser.open.nfc.NfcOpenUtils;
import com.laser.open.nfc.model.entity.BaseResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardInfoResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardsResp;
import com.laser.open.nfc.model.entity.SEInfoResp;
import com.laser.open.nfc.model.entity.TrafficCardEntity;
import j.a;
import j.b;

/* loaded from: classes18.dex */
public class FreeRechargeUtil {
    private static FreeRechargeUtil sFreeRechargeUtil;
    private a callback;
    private Context mContext;
    private Gson gson = new Gson();
    private final String INIT = "0";
    private final String GETTRAFFICCARDS = "1";
    private final String ISSUECARD = "2";
    private final String TOPUP = "3";
    private final String QUERYCARDINFO = "4";
    private final String SETDEFAULTCARD = "5";
    private final String GETSEINFO = "6";
    private final String CHECKISSUECARDCONDITION = "7";
    private final String CHECKTOPUPCONDITION = "8";
    private final String DELETDCARD = "9";
    private final String PREINFO = "10";
    private final String JUMPTONFCSETTING = "11";

    private FreeRechargeUtil(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private String dispatch(Request request) {
        String name = request.getName();
        name.hashCode();
        name.hashCode();
        char c2 = '\uffff';
        switch (name.hashCode()) {
            case 48:
                if (name.equals("0")) {
                    c2 = 0;
                    break;
                }
                break;
            case 49:
                if (name.equals("1")) {
                    c2 = 1;
                    break;
                }
                break;
            case 50:
                if (name.equals("2")) {
                    c2 = 2;
                    break;
                }
                break;
            case 51:
                if (name.equals("3")) {
                    c2 = 3;
                    break;
                }
                break;
            case 52:
                if (name.equals("4")) {
                    c2 = 4;
                    break;
                }
                break;
            case 53:
                if (name.equals("5")) {
                    c2 = 5;
                    break;
                }
                break;
            case 54:
                if (name.equals("6")) {
                    c2 = 6;
                    break;
                }
                break;
            case 55:
                if (name.equals("7")) {
                    c2 = 7;
                    break;
                }
                break;
            case 56:
                if (name.equals("8")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 57:
                if (name.equals("9")) {
                    c2 = '\t';
                    break;
                }
                break;
            case R2.attr.progress_cancel /* 1567 */:
                if (name.equals("10")) {
                    c2 = '\n';
                    break;
                }
                break;
            case R2.attr.prospectNum /* 1568 */:
                if (name.equals("11")) {
                    c2 = 11;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                InitParam initParam = (InitParam) this.gson.fromJson(request.getRequest(), InitParam.class);
                return init(initParam.getPartnerId(), initParam.getPhoneNum(), initParam.getRequestUrl());
            case 1:
                return getTrafficCards();
            case 2:
                IssueCardParam issueCardParam = (IssueCardParam) this.gson.fromJson(request.getRequest(), IssueCardParam.class);
                return issueCard(issueCardParam.getCardDetail(), issueCardParam.getOrderNo());
            case 3:
                TopupParam topupParam = (TopupParam) this.gson.fromJson(request.getRequest(), TopupParam.class);
                return topUp(topupParam.getCardDetail(), topupParam.getOrderNo());
            case 4:
                QueryCardInfoParam queryCardInfoParam = (QueryCardInfoParam) this.gson.fromJson(request.getRequest(), QueryCardInfoParam.class);
                return queryCardInfo(queryCardInfoParam.getCardDetail(), queryCardInfoParam.getDataType());
            case 5:
                DefaultCardParam defaultCardParam = (DefaultCardParam) this.gson.fromJson(request.getRequest(), DefaultCardParam.class);
                return setDefaultCard(defaultCardParam.getCardDetail(), defaultCardParam.getModel());
            case 6:
                return getSEInfo();
            case 7:
                return checkIssueCardCondition((TrafficCardEntity) this.gson.fromJson(request.getRequest(), TrafficCardEntity.class));
            case '\b':
                return checkTopUpCondition((TrafficCardEntity) this.gson.fromJson(request.getRequest(), TrafficCardEntity.class));
            case '\t':
                return deleteCard((TrafficCardEntity) this.gson.fromJson(request.getRequest(), TrafficCardEntity.class));
            case '\n':
                return preInfo();
            case 11:
                return jumoToNFCSetting();
            default:
                return "param filed name is wrong : " + request.getName();
        }
    }

    public static FreeRechargeUtil getInstance(Context context) {
        if (sFreeRechargeUtil == null) {
            sFreeRechargeUtil = new FreeRechargeUtil(context);
        }
        return sFreeRechargeUtil;
    }

    public String checkIssueCardCondition(TrafficCardEntity trafficCardEntity) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp checkIssueCardCondition = NfcOpenUtils.getInstance().checkIssueCardCondition(trafficCardEntity);
            this.gson.toJson(checkIssueCardCondition, BaseResp.class);
            responBaseBean.setName("7");
            responBaseBean.setResponse(checkIssueCardCondition);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp = new BaseResp();
            baseResp.setStatus(1);
            baseResp.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("7");
            responBaseBean.setResponse(baseResp);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String checkTopUpCondition(TrafficCardEntity trafficCardEntity) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp checkTopUpCondition = NfcOpenUtils.getInstance().checkTopUpCondition(trafficCardEntity);
            this.gson.toJson(checkTopUpCondition, BaseResp.class);
            responBaseBean.setName("8");
            responBaseBean.setResponse(checkTopUpCondition);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp = new BaseResp();
            baseResp.setStatus(1);
            baseResp.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("8");
            responBaseBean.setResponse(baseResp);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String deleteCard(TrafficCardEntity trafficCardEntity) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp deleteCard = NfcOpenUtils.getInstance().deleteCard(trafficCardEntity);
            this.gson.toJson(deleteCard, BaseResp.class);
            responBaseBean.setName("9");
            responBaseBean.setResponse(deleteCard);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp = new BaseResp();
            baseResp.setStatus(1);
            baseResp.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("9");
            responBaseBean.setResponse(baseResp);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String entrance(String str) {
        return dispatch((Request) this.gson.fromJson(str, Request.class));
    }

    public String getSEInfo() {
        SEInfoBean sEInfoBean = new SEInfoBean();
        try {
            SEInfoResp sEInfo = NfcOpenUtils.getInstance().getSEInfo();
            this.gson.toJson(sEInfo, SEInfoResp.class);
            sEInfoBean.setName("6");
            sEInfoBean.setResponse(sEInfo);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            SEInfoResp sEInfoResp = new SEInfoResp();
            sEInfoResp.setStatus(1);
            sEInfoResp.setDesc(th.getLocalizedMessage());
            sEInfoBean.setName("6");
            sEInfoBean.setResponse(sEInfoResp);
        }
        return this.gson.toJson(sEInfoBean, SEInfoBean.class);
    }

    public String getTrafficCards() {
        ResponTrafficcardBean responTrafficcardBean = new ResponTrafficcardBean();
        try {
            QueryTrafficCardsResp trafficCards = NfcOpenUtils.getInstance().getTrafficCards();
            this.gson.toJson(trafficCards, QueryTrafficCardsResp.class);
            responTrafficcardBean.setName("1");
            responTrafficcardBean.setResponse(trafficCards);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            QueryTrafficCardsResp queryTrafficCardsResp = new QueryTrafficCardsResp();
            queryTrafficCardsResp.setStatus(1);
            queryTrafficCardsResp.setDesc(th.getLocalizedMessage());
            responTrafficcardBean.setName("1");
            responTrafficcardBean.setResponse(queryTrafficCardsResp);
        }
        return this.gson.toJson(responTrafficcardBean, ResponTrafficcardBean.class);
    }

    public String init(String str, String str2, String str3) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp initNfcOpenSDK = NfcOpenUtils.getInstance().initNfcOpenSDK(this.mContext, str, str2, str3);
            this.gson.toJson(initNfcOpenSDK, BaseResp.class);
            responBaseBean.setName("0");
            responBaseBean.setResponse(initNfcOpenSDK);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp = new BaseResp();
            baseResp.setStatus(1);
            baseResp.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("0");
            responBaseBean.setResponse(baseResp);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public boolean isInited() {
        return NfcOpenUtils.getInstance().isInit();
    }

    public String issueCard(TrafficCardEntity trafficCardEntity, String str) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp issueCard = NfcOpenUtils.getInstance().issueCard(trafficCardEntity, str);
            this.gson.toJson(issueCard, BaseResp.class);
            responBaseBean.setName("2");
            responBaseBean.setResponse(issueCard);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp = new BaseResp();
            baseResp.setStatus(1);
            baseResp.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("2");
            responBaseBean.setResponse(baseResp);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String jumoToNFCSetting() {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        responBaseBean.setName("11");
        BaseResp baseResp = new BaseResp();
        try {
            baseResp.setStatus(0);
            baseResp.setDesc("SUCCESS");
            Intent intent = new Intent("android.settings.NFC_SETTINGS");
            intent.setFlags(268435456);
            this.mContext.startActivity(intent);
        } catch (Exception e2) {
            baseResp.setStatus(1);
            baseResp.setDesc(e2.getLocalizedMessage());
        }
        responBaseBean.setResponse(baseResp);
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String preInfo() {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        PreInfo preInfo = new PreInfo();
        try {
            preInfo.setApp_name(this.mContext.getPackageName());
            preInfo.setMobile_type(SystemInfo.getModel());
            preInfo.setAndroid_version(SystemInfo.getDisplay());
            preInfo.setSdk_version("2.1.3");
            Context context = this.mContext;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(TextUtils.isEmpty("com.huawei.wallet") ? context.getPackageName() : "com.huawei.wallet", 0);
            preInfo.setWallet_version(packageInfo != null ? packageInfo.versionName : "");
            preInfo.setApp_version(SystemInfo.getClientVersion());
            preInfo.setStatus(0);
            preInfo.setHaveNFC(b.a(this.mContext));
            preInfo.setOpenNFC(b.b(this.mContext));
            responBaseBean.setName("10");
            responBaseBean.setResponse(preInfo);
        } catch (Exception e2) {
            PreInfo preInfo2 = new PreInfo();
            preInfo2.setStatus(1);
            preInfo2.setDesc(e2.getLocalizedMessage());
            responBaseBean.setName("10");
            responBaseBean.setResponse(preInfo2);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String queryCardInfo(TrafficCardEntity trafficCardEntity, int i2) {
        QueryTrafficCardInfoBean queryTrafficCardInfoBean = new QueryTrafficCardInfoBean();
        try {
            QueryTrafficCardInfoResp queryCardInfo = NfcOpenUtils.getInstance().queryCardInfo(trafficCardEntity, i2);
            this.gson.toJson(queryCardInfo, QueryTrafficCardInfoResp.class);
            queryTrafficCardInfoBean.setName("4");
            queryTrafficCardInfoBean.setResponse(queryCardInfo);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            QueryTrafficCardInfoResp queryTrafficCardInfoResp = new QueryTrafficCardInfoResp();
            queryTrafficCardInfoResp.setStatus(1);
            queryTrafficCardInfoResp.setDesc(th.getLocalizedMessage());
            queryTrafficCardInfoBean.setName("4");
            queryTrafficCardInfoBean.setResponse(queryTrafficCardInfoResp);
        }
        return this.gson.toJson(queryTrafficCardInfoBean, QueryTrafficCardInfoBean.class);
    }

    public String setDefaultCard(TrafficCardEntity trafficCardEntity, String str) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp defaultCard = NfcOpenUtils.getInstance().setDefaultCard(trafficCardEntity, str);
            this.gson.toJson(defaultCard, BaseResp.class);
            responBaseBean.setName("5");
            responBaseBean.setResponse(defaultCard);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp = new BaseResp();
            baseResp.setStatus(1);
            baseResp.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("5");
            responBaseBean.setResponse(baseResp);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }

    public String topUp(TrafficCardEntity trafficCardEntity, String str) {
        ResponBaseBean responBaseBean = new ResponBaseBean();
        try {
            BaseResp baseResp = NfcOpenUtils.getInstance().topUp(trafficCardEntity, str);
            this.gson.toJson(baseResp, BaseResp.class);
            responBaseBean.setName("3");
            responBaseBean.setResponse(baseResp);
        } catch (Throwable th) {
            th.getLocalizedMessage();
            BaseResp baseResp2 = new BaseResp();
            baseResp2.setStatus(1);
            baseResp2.setDesc(th.getLocalizedMessage());
            responBaseBean.setName("3");
            responBaseBean.setResponse(baseResp2);
        }
        return this.gson.toJson(responBaseBean, ResponBaseBean.class);
    }
}
