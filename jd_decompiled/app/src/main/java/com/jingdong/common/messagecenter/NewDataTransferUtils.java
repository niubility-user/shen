package com.jingdong.common.messagecenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.database.table.MessageIdTable;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.MessageId;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NewDataTransferUtils {
    private static final String TAG = "NewDataTransferUtils";

    public static void parsePushMsg(Context context, JSONObject jSONObject, final int i2) {
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
                    JDImageUtils.loadImage(str4, new JDDisplayImageOptions(), new JDImageLoadingListener() { // from class: com.jingdong.common.messagecenter.NewDataTransferUtils.1
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str5, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str5, View view, Bitmap bitmap) {
                            if (bitmap != null) {
                                try {
                                    MessageCenterNotificationUtils.takeNoticeOfPictures(NotificationMessageSummary.this, true, bitmap, i2);
                                } catch (Exception e2) {
                                    OKLog.e(NewDataTransferUtils.TAG, e2);
                                }
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str5, View view, JDFailReason jDFailReason) {
                            try {
                                MessageCenterNotificationUtils.notTakeNoticeOfPictures(NotificationMessageSummary.this, true, i2);
                            } catch (Exception e2) {
                                OKLog.e(NewDataTransferUtils.TAG, e2);
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str5, View view) {
                        }
                    });
                    return;
                }
                try {
                    MessageCenterNotificationUtils.notTakeNoticeOfPictures(notificationMessageSummary, true, i2);
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
            } catch (Exception unused) {
            }
        }
    }
}
