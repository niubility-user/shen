package com.jingdong.common.messagecenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.database.table.MessageIdTable;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.common.utils.MessageId;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class PushMessageHandler {
    private static final String TAG = "PushMessageHelper";

    private static void dataTransferMethod(Context context, NotificationMessageSummary notificationMessageSummary, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "dataTransferMethod  from =" + i2);
        }
        String config = JDMobileConfig.getInstance().getConfig("JDPush", "switch", "useOpenApp", "1");
        if (OKLog.D) {
            OKLog.d(TAG, "\u8df3\u8f6c\u4f7f\u7528openApp\u534f\u8bae\uff1a" + config);
        }
        if (TextUtils.equals(config, "1")) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("category", (Object) "jump");
            jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_NOTIFICATION_HANDLER);
            jDJSONObject.put("summary", (Object) JDJSON.toJSONString(notificationMessageSummary));
            jDJSONObject.put("messageFlag", (Object) Integer.valueOf(i2));
            String str = "openApp.jdMobile://virtual?params=" + jDJSONObject;
            if (OKLog.D) {
                OKLog.d(TAG, str);
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(337641472);
            context.startActivity(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(context, "com.jingdong.app.mall.open.MessageNotificationActivity"));
        intent2.putExtra("summary", JDJSON.toJSONString(notificationMessageSummary));
        intent2.putExtra("messageFlag", i2);
        intent2.setFlags(337641472);
        context.startActivity(intent2);
    }

    public static void jumpDefault(Context context) {
        jumpFirstBox(context);
    }

    private static void jumpFirstBox(Context context) {
        try {
            JumpMessageActivityUtil.productDetailjumpToMessageCenter(context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void parseJDChannePushMsg(Context context, JSONObject jSONObject, final int i2) {
        final NotificationMessageSummary notificationMessageSummary = new NotificationMessageSummary(new JSONObjectProxy(jSONObject));
        String userPin = LoginUserBase.getUserPin();
        String str = notificationMessageSummary.msgId;
        String str2 = notificationMessageSummary.title;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (TextUtils.isEmpty(userPin) && !LoginUserBase.hasLogin() && TextUtils.isEmpty(notificationMessageSummary.bcFlag)) {
            return;
        }
        if (TextUtils.isEmpty(notificationMessageSummary.bcFlag) || CommonBase.getBooleanFromPreference("isPush", Boolean.TRUE).booleanValue()) {
            if ("25".equals(notificationMessageSummary.landPageId)) {
                Bundle bundle = new Bundle();
                bundle.putString("key", notificationMessageSummary.key);
                DeepLinkLoginHelper.startScanLoginActivity(context, bundle, 67108864);
                return;
            }
            try {
                if (MessageIdTable.checkRepeated(new MessageId(str))) {
                    return;
                }
                String str3 = notificationMessageSummary.notifyTemplateId;
                String str4 = notificationMessageSummary.imgPath;
                if (Build.VERSION.SDK_INT >= 16 && "2".equals(str3) && !TextUtils.isEmpty(str4)) {
                    JDImageUtils.loadImage(str4, new JDDisplayImageOptions(), new JDImageLoadingListener() { // from class: com.jingdong.common.messagecenter.PushMessageHandler.1
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str5, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str5, View view, Bitmap bitmap) {
                            if (bitmap != null) {
                                try {
                                    MessageCenterNotificationUtils.takeNoticeOfPictures(NotificationMessageSummary.this, true, bitmap, i2);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str5, View view, JDFailReason jDFailReason) {
                            try {
                                MessageCenterNotificationUtils.notTakeNoticeOfPictures(NotificationMessageSummary.this, true, i2);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str5, View view) {
                        }
                    });
                    return;
                }
                try {
                    if (OKLog.D) {
                        OKLog.d(TAG, "notTakeNoticeOfPictures 345");
                    }
                    MessageCenterNotificationUtils.notTakeNoticeOfPictures(notificationMessageSummary, true, i2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void parseManufacturerPushMsg(Context context, JSONObject jSONObject, int i2, int i3) {
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
            if (i3 == 1) {
                parseJDChannePushMsg(context, jSONObject2, i2);
            } else if (i3 == 2) {
                dataTransferMethod(context, new NotificationMessageSummary(new JSONObjectProxy(jSONObject2)), i2);
            }
        } catch (Exception e2) {
            String str = "\u89e3\u6790\u5f02\u5e38:" + e2.toString();
            if (i3 == 2) {
                jumpFirstBox(context);
            }
        }
    }
}
