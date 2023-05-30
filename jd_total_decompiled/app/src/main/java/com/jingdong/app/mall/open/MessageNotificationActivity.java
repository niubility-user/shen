package com.jingdong.app.mall.open;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.mylib.entity.LandPageInfo;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.common.messagecenter.MessageCenterNotificationUtils;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.messagecenter.NewBadgeUtil;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.messagecenter.RomUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.MessageCenterLandPageUtils;
import com.jingdong.common.utils.inter.JDInternationalUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MessageNotificationActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private boolean f11439g = true;

    /* renamed from: h  reason: collision with root package name */
    private String f11440h = "0";

    /* renamed from: i  reason: collision with root package name */
    private int f11441i = 0;

    /* renamed from: j  reason: collision with root package name */
    private int f11442j = 0;

    private void A() {
        try {
            Resources resources = getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            if (BaseInfo.getDisplayMetricsObject() != null) {
                resources.updateConfiguration(configuration, BaseInfo.getDisplayMetricsObject());
            }
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
    }

    private void B(NotificationMessageSummary notificationMessageSummary, int i2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (OKLog.D) {
            OKLog.d("MessageNotificationActivity", String.format("from=%s,summary=%s", Integer.valueOf(i2), notificationMessageSummary));
        }
        if (notificationMessageSummary != null) {
            if (i2 != 12) {
                if (i2 != 100) {
                    String str7 = "";
                    switch (i2) {
                        case 0:
                            MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 0, notificationMessageSummary.msgSeq, notificationMessageSummary.echo, 1, null, null);
                            w(notificationMessageSummary, "JDPUSH");
                            break;
                        case 1:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 1)) && notificationMessageSummary.echo != null) {
                                if (TextUtils.isEmpty(notificationMessageSummary.msgSeq) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str2 = MessageCommonUtils.getMIRegId(this, 1) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                } else {
                                    str2 = notificationMessageSummary.msgSeq;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 1, str2, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(this, 1));
                                w(notificationMessageSummary, "\u5c0f\u7c73PUSH");
                                break;
                            }
                            break;
                        case 2:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 2)) && notificationMessageSummary.echo != null) {
                                if (TextUtils.isEmpty(notificationMessageSummary.msgSeq) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str3 = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 2) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                } else {
                                    str3 = notificationMessageSummary.msgSeq;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 2, str3, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(this, 2));
                                w(notificationMessageSummary, "\u534e\u4e3aPUSH");
                                break;
                            }
                            break;
                        case 3:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 3)) && notificationMessageSummary.echo != null) {
                                if (TextUtils.isEmpty(notificationMessageSummary.msgSeq) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str4 = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 3) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                } else {
                                    str4 = notificationMessageSummary.msgSeq;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 3, str4, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(this, 3));
                                w(notificationMessageSummary, "\u9b45\u65cfPUSH");
                                break;
                            }
                            break;
                        case 4:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 4)) && notificationMessageSummary.echo != null) {
                                if (TextUtils.isEmpty(notificationMessageSummary.msgSeq) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str5 = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 4) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                } else {
                                    str5 = notificationMessageSummary.msgSeq;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 4, str5, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(this, 4));
                                w(notificationMessageSummary, "\u4fe1\u9e3dPUSH");
                                break;
                            }
                            break;
                        case 5:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 5)) && notificationMessageSummary.echo != null) {
                                if (TextUtils.isEmpty(notificationMessageSummary.msgSeq) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str6 = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 5) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                } else {
                                    str6 = notificationMessageSummary.msgSeq;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 5, str6, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(this, 5));
                                w(notificationMessageSummary, "\u4e2a\u63a8PUSH");
                                break;
                            }
                            break;
                        case 6:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 6)) && notificationMessageSummary.echo != null) {
                                if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 6)) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str7 = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 6) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 6, str7, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 6));
                                w(notificationMessageSummary, "OPPOPUSH");
                                break;
                            }
                            break;
                        case 7:
                        case 8:
                            if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 8)) && notificationMessageSummary.echo != null) {
                                if (!TextUtils.isEmpty(notificationMessageSummary.msgSeq)) {
                                    str7 = notificationMessageSummary.msgSeq;
                                } else if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 8)) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                                    str7 = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 8) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                                }
                                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 8, str7, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 8));
                                w(notificationMessageSummary, "VIVOPUSH");
                                break;
                            }
                            break;
                    }
                } else {
                    w(notificationMessageSummary, "\u9501\u5c4f\u5f39\u7a97");
                }
            } else if (!TextUtils.isEmpty(MessageCommonUtils.getMIRegId(this, 12)) && notificationMessageSummary.echo != null) {
                if (TextUtils.isEmpty(notificationMessageSummary.msgSeq) && !TextUtils.isEmpty(notificationMessageSummary.idMsg)) {
                    str = MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 12) + CartConstant.KEY_YB_INFO_LINK + notificationMessageSummary.idMsg;
                } else {
                    str = notificationMessageSummary.msgSeq;
                }
                MessageCommonUtils.recordOpenPushInfo(JdSdk.getInstance().getApplication().getApplicationContext(), 12, str, notificationMessageSummary.echo, 1, notificationMessageSummary.idMsg, MessageCommonUtils.getMIRegId(JdSdk.getInstance().getApplication().getApplicationContext(), 12));
                w(notificationMessageSummary, "\u8363\u8000PUSH");
            }
            if (!TextUtils.isEmpty(notificationMessageSummary.landPageId) && y(notificationMessageSummary.landPageId)) {
                int notifcationTypeById = LandPageInfo.LandPageEnum.getNotifcationTypeById(Integer.parseInt(notificationMessageSummary.landPageId));
                if (!TextUtils.isEmpty(notificationMessageSummary.grayHashStart)) {
                    try {
                        this.f11441i = Integer.parseInt(notificationMessageSummary.grayHashStart);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        this.f11441i = 0;
                    }
                } else {
                    this.f11441i = 0;
                }
                if (!TextUtils.isEmpty(notificationMessageSummary.grayHashEnd)) {
                    try {
                        this.f11442j = Integer.parseInt(notificationMessageSummary.grayHashEnd);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        this.f11442j = 0;
                    }
                } else {
                    this.f11442j = 0;
                }
                try {
                    if (BaseFrameUtil.getInstance().getMainFrameActivity() == null && !MessageCenterLandPageUtils.isOpenMainFrameActivity() && MessageCenterLandPageUtils.isMockValidInHashRange(this.f11441i, this.f11442j)) {
                        e.b().startMainFrameActivity(this);
                        MessageCenterLandPageUtils.setIsOpenMainFrameActivity(true);
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                switch (notifcationTypeById) {
                    case 0:
                        MessageCenterLandPageUtils.firstBoxLandPage(this);
                        return;
                    case 1:
                        if (TextUtils.isEmpty(notificationMessageSummary.landPageUrl)) {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        } else {
                            MessageCenterLandPageUtils.forwardWebActivity(this, notificationMessageSummary.landPageUrl, this.f11439g, "1".equals(notificationMessageSummary.isFeedBack), notificationMessageSummary.msgId, notificationMessageSummary.businessType);
                            return;
                        }
                    case 2:
                        MessageCenterLandPageUtils.spikeLandingPage(this, notificationMessageSummary.activityId, notificationMessageSummary.wareId, this.f11440h);
                        return;
                    case 3:
                        if (TextUtils.isEmpty(notificationMessageSummary.shopId)) {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        } else {
                            MessageCenterLandPageUtils.shopHomePage(this, notificationMessageSummary.shopId, notificationMessageSummary.vendorId, this.f11440h);
                            return;
                        }
                    case 4:
                        if (!TextUtils.isEmpty(notificationMessageSummary.wareId) && !TextUtils.isEmpty(notificationMessageSummary.msgId)) {
                            MessageCenterLandPageUtils.commodityLandingPage(this, notificationMessageSummary.wareId, notificationMessageSummary.msgId, this.f11440h);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 5:
                        MessageCenterLandPageUtils.mobileChannelLandPage(this, this.f11440h);
                        return;
                    case 6:
                        MessageCenterLandPageUtils.orderListLandingPage(this, this.f11440h);
                        return;
                    case 7:
                        MessageCenterLandPageUtils.shoppingCartLandingPage(this, notificationMessageSummary.wareId, notificationMessageSummary.price);
                        return;
                    case 8:
                        if (!TextUtils.isEmpty(notificationMessageSummary.orderId) && !TextUtils.isEmpty(notificationMessageSummary.msgId)) {
                            MessageCenterLandPageUtils.orderDetailsLandingPage(this, notificationMessageSummary.orderId, notificationMessageSummary.msgId, this.f11440h);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 9:
                        if (!TextUtils.isEmpty(notificationMessageSummary.orderId) && !TextUtils.isEmpty(notificationMessageSummary.msgId)) {
                            MessageCenterLandPageUtils.evaluationLandingPage(this, notificationMessageSummary.orderId, notificationMessageSummary.msgId, this.f11440h);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 10:
                        if (!TextUtils.isEmpty(notificationMessageSummary.orderId) && !TextUtils.isEmpty(notificationMessageSummary.msgId)) {
                            MessageCenterLandPageUtils.logisticsTrackingLandingPage(this, notificationMessageSummary.orderId, notificationMessageSummary.msgId, this.f11440h);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 11:
                        if (!TextUtils.isEmpty(notificationMessageSummary.msgId) && !TextUtils.isEmpty(notificationMessageSummary.couponsFlag)) {
                            MessageCenterLandPageUtils.couponsLandingPage(this, notificationMessageSummary.msgId, notificationMessageSummary.couponsFlag, notificationMessageSummary.couponsId);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 12:
                        if (!TextUtils.isEmpty(notificationMessageSummary.containerType) && !TextUtils.isEmpty(notificationMessageSummary.secondListTitle)) {
                            MessageCenterLandPageUtils.messageCenterSecondPage(this, notificationMessageSummary.containerType, notificationMessageSummary.secondListTitle, this.f11440h);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 13:
                        if (TextUtils.isEmpty(notificationMessageSummary.activityId)) {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        } else {
                            MessageCenterLandPageUtils.jumpToBabelActivity(this, notificationMessageSummary.activityId, this.f11440h);
                            return;
                        }
                    case 14:
                        if (!TextUtils.isEmpty(notificationMessageSummary.orderId) && !TextUtils.isEmpty(notificationMessageSummary.landPageFlag)) {
                            MessageCenterLandPageUtils.virtualBusinessLandingPage(this, notificationMessageSummary.orderId, notificationMessageSummary.landPageFlag);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 15:
                        MessageCenterLandPageUtils.jumpToCouponMainActivity(this);
                        return;
                    case 16:
                        MessageCenterLandPageUtils.jumpToSearchActivity(this, notificationMessageSummary.keyWord);
                        return;
                    case 17:
                        v(notificationMessageSummary);
                        MessageCenterLandPageUtils.jumpDongDong(this, notificationMessageSummary.transmission);
                        return;
                    case 18:
                        MessageCenterLandPageUtils.jumpToCalendarPage(this);
                        return;
                    case 19:
                        if (!TextUtils.isEmpty(notificationMessageSummary.wareId) && !TextUtils.isEmpty(notificationMessageSummary.questionId)) {
                            MessageCenterLandPageUtils.jumpToQuestionAndAnswerPage(this, notificationMessageSummary.wareId, notificationMessageSummary.questionId);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 20:
                        if (!TextUtils.isEmpty(notificationMessageSummary.landPageUrl)) {
                            MessageCenterLandPageUtils.jumpToOpenApp(this, notificationMessageSummary.landPageUrl, this.f11439g, "1".equals(notificationMessageSummary.isFeedBack), notificationMessageSummary.msgId, notificationMessageSummary.businessType);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 21:
                        if (!TextUtils.isEmpty(notificationMessageSummary.tab) && y(notificationMessageSummary.tab)) {
                            MessageCenterLandPageUtils.jumpCouponCenter(this, notificationMessageSummary.tab);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 22:
                    default:
                        MessageCenterLandPageUtils.firstBoxLandPage(this);
                        return;
                    case 23:
                        if (!TextUtils.isEmpty(notificationMessageSummary.landPageUrl)) {
                            MessageCenterLandPageUtils.urgentPush(this, notificationMessageSummary.landPageUrl);
                            return;
                        } else {
                            MessageCenterLandPageUtils.urgentPush(this, "https://m.jd.com");
                            return;
                        }
                    case 24:
                        if (LoginUserBase.hasLogin()) {
                            MessageCenterLandPageUtils.jumpCouponTrade(this);
                            return;
                        } else {
                            MessageCenterLandPageUtils.firstBoxLandPage(this);
                            return;
                        }
                    case 25:
                        MessageCenterLandPageUtils.jumpScanLogin(this, notificationMessageSummary.key);
                        return;
                }
            }
            MessageCenterLandPageUtils.firstBoxLandPage(this);
            return;
        }
        MessageCenterLandPageUtils.firstBoxLandPage(this);
    }

    private void u(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("updateUnReadedCountV7012");
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NEW_MSG_CENTER_HOST));
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("msgId", str);
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void v(NotificationMessageSummary notificationMessageSummary) {
        try {
            if (RomUtil.getDevice() != 0) {
                MessageCenterNotificationUtils.clearAllNotices(this);
            } else if (TextUtils.isEmpty(notificationMessageSummary.venderId)) {
            } else {
                MessageCenterNotificationUtils.clearDDNotices(this, notificationMessageSummary.venderId);
            }
        } catch (Exception e2) {
            OKLog.e("MessageNotificationActivity", e2);
        }
    }

    private void w(NotificationMessageSummary notificationMessageSummary, String str) {
        if (TextUtils.isEmpty(notificationMessageSummary.msgId)) {
            return;
        }
        u(notificationMessageSummary.msgId);
        String str2 = TextUtils.isEmpty(notificationMessageSummary.alert) ? "none" : notificationMessageSummary.alert;
        String str3 = TextUtils.isEmpty(notificationMessageSummary.shopId) ? "none" : notificationMessageSummary.shopId;
        String str4 = TextUtils.isEmpty(notificationMessageSummary.wareId) ? "none" : notificationMessageSummary.wareId;
        String str5 = TextUtils.isEmpty(notificationMessageSummary.taskId) ? "none" : notificationMessageSummary.taskId;
        String str6 = TextUtils.isEmpty(notificationMessageSummary.subTaskId) ? "none" : notificationMessageSummary.subTaskId;
        String str7 = TextUtils.isEmpty(notificationMessageSummary.pushTextId) ? "none" : notificationMessageSummary.pushTextId;
        String str8 = TextUtils.isEmpty(notificationMessageSummary.source) ? "none" : notificationMessageSummary.source;
        JDMtaUtils.sendClickDataWithExt(this, "PushMessage_OpenMessage", "new_" + notificationMessageSummary.msgId + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + str3 + CartConstant.KEY_YB_INFO_LINK + str4 + CartConstant.KEY_YB_INFO_LINK + str5 + CartConstant.KEY_YB_INFO_LINK + str6 + CartConstant.KEY_YB_INFO_LINK + str + CartConstant.KEY_YB_INFO_LINK + str7 + CartConstant.KEY_YB_INFO_LINK + str8, "", "", getClass().getName(), "", "", x(notificationMessageSummary.msgId, str2, str3, str4, str5, str6, str, str7, str8, TextUtils.isEmpty(notificationMessageSummary.testid) ? "-100" : notificationMessageSummary.testid, TextUtils.isEmpty(notificationMessageSummary.btype) ? "-100" : notificationMessageSummary.btype, TextUtils.isEmpty(notificationMessageSummary.bcontent) ? "-100" : notificationMessageSummary.bcontent).toString(), null);
    }

    private JSONObject x(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msgid", str);
            jSONObject.put("writerid", str3);
            jSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, str4);
            jSONObject.put("superactivityid", str5);
            jSONObject.put("subactivityid", str6);
            jSONObject.put("pushplant", str7);
            jSONObject.put("textid", str8);
            jSONObject.put(com.jingdong.jdsdk.config.Configuration.PARTNER, str9);
            jSONObject.put("testid", str10);
            jSONObject.put(NotificationMessageSummary.B_TYPE, str11);
            jSONObject.put(NotificationMessageSummary.B_CONTENT, str12);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private boolean y(String str) {
        try {
            Integer.valueOf(str).intValue();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void z(JSONObject jSONObject, int i2) {
        try {
            MIPushMsg mIPushMsg = new MIPushMsg();
            mIPushMsg.setDevType(jSONObject.optInt(MIPushMsg.DEVTYPE));
            mIPushMsg.setEcho(jSONObject.optString(MIPushMsg.ECHO));
            mIPushMsg.setMsgType(jSONObject.optInt(MIPushMsg.MSGTYPE));
            mIPushMsg.setMsgSeq(jSONObject.optString(MIPushMsg.MSG_SEQ));
            mIPushMsg.setMsg(jSONObject.optString(MIPushMsg.MSG_BODY));
            mIPushMsg.setMsgId(jSONObject.optString(MIPushMsg.MSG_ID));
            mIPushMsg.setAppId(jSONObject.optInt(MIPushMsg.APP_ID));
            mIPushMsg.setSetId(jSONObject.optInt(MIPushMsg.SET_ID));
            mIPushMsg.setSerialNo(jSONObject.optInt(MIPushMsg.SERIAL_NO));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(NotificationMessageSummary.MSG_SEQ, mIPushMsg.getMsgSeq());
            jSONObject2.put(NotificationMessageSummary.DEVTYPE, mIPushMsg.getDevType());
            jSONObject2.put(NotificationMessageSummary.ECHO, mIPushMsg.getEcho());
            jSONObject2.put(NotificationMessageSummary.MSGTYPE, mIPushMsg.getMsgType());
            jSONObject2.put(NotificationMessageSummary.MSG_BODY, mIPushMsg.getMsg());
            jSONObject2.put(NotificationMessageSummary.ID_MSG, mIPushMsg.getMsgId());
            jSONObject2.put("appId", mIPushMsg.getAppId());
            jSONObject2.put(NotificationMessageSummary.SET_ID, mIPushMsg.getSetId());
            jSONObject2.put(NotificationMessageSummary.SERIAL_NO, mIPushMsg.getSerialNo());
            B(new NotificationMessageSummary(new JSONObjectProxy(jSONObject2)), i2);
        } catch (Exception unused) {
            MessageCenterLandPageUtils.firstBoxLandPage(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setUseBasePV(false);
            String currentMicrosecond = JDMtaUtils.getCurrentMicrosecond();
            JDMtaUtils.updateJdv(this, "0|kong|t_1000170135|tuiguang|notset|" + currentMicrosecond);
            AdvertUtils.changeJdvToMParam("0|kong|t_1000170135|tuiguang|notset|" + currentMicrosecond);
        } catch (Exception unused) {
            MessageCenterLandPageUtils.firstBoxLandPage(this);
        } catch (OutOfMemoryError unused2) {
            MessageCenterLandPageUtils.firstBoxLandPage(this);
        }
        if (getIntent() == null) {
            return;
        }
        if (JDInternationalUtil.isInInterSite()) {
            finish();
        }
        int intExtra = getIntent().getIntExtra("messageFlag", -1);
        String stringExtra = getIntent().getStringExtra("oppoMessageFlag");
        int intExtra2 = getIntent().getIntExtra("fromInternalActivity", 0);
        if (OKLog.D) {
            OKLog.d("MessageNotificationActivity", String.format("onCreate : flag=%s\noppoFlag=%s\nfromInternalActivity=%d\nsummary=%s\n", Integer.valueOf(intExtra), stringExtra, Integer.valueOf(intExtra2), getIntent().getStringExtra("summary")));
        }
        A();
        if (!TextUtils.isEmpty(stringExtra)) {
            if ("6".equals(stringExtra)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(MIPushMsg.MSG_BODY, getIntent().getStringExtra(MIPushMsg.MSG_BODY));
                jSONObject.put(MIPushMsg.ECHO, getIntent().getStringExtra(MIPushMsg.ECHO));
                jSONObject.put(MIPushMsg.MSG_ID, getIntent().getStringExtra(MIPushMsg.MSG_ID));
                z(jSONObject, Integer.valueOf(stringExtra).intValue());
            } else {
                MessageCenterLandPageUtils.firstBoxLandPage(this);
            }
        } else {
            if (2 != intExtra && intExtra2 != 1) {
                String stringExtra2 = getIntent().getStringExtra("summary");
                NotificationMessageSummary notificationMessageSummary = TextUtils.isEmpty(stringExtra2) ? null : new NotificationMessageSummary(JDJSON.parseObject(stringExtra2));
                if (notificationMessageSummary != null) {
                    OKLog.d("MessageNotificationActivity", "summary:" + JDJSON.toJSONString(notificationMessageSummary));
                }
                B(notificationMessageSummary, intExtra);
            }
            z(new JSONObject(getIntent().getStringExtra("summary")), intExtra);
        }
        NewBadgeUtil.clearPushBadge();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        finish();
    }
}
