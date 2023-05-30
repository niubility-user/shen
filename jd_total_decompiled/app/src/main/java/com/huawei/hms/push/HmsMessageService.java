package com.huawei.hms.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.utils.BaseUtils;
import com.huawei.hms.adapter.internal.CommonCode;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.e;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.PushNaming;
import com.huawei.hms.support.log.HMSLog;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.PushMessageTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class HmsMessageService extends Service {
    public static final String PROXY_TYPE = "proxy_type";
    public static final String SUBJECT_ID = "subject_id";
    private final Messenger a = new Messenger(new e(new b()));

    /* loaded from: classes12.dex */
    private class b implements e.a {
        private b() {
        }

        @Override // com.huawei.hms.push.e.a
        public void a(Message message) {
            if (message == null) {
                HMSLog.e("HmsMessageService", "receive message is null");
                return;
            }
            HMSLog.i("HmsMessageService", "handle message start...");
            Bundle data = Message.obtain(message).getData();
            if (data != null) {
                Intent intent = new Intent();
                intent.putExtras(data);
                intent.putExtra("inputType", data.getInt("inputType", -1));
                HmsMessageService.this.handleIntentMessage(intent);
            }
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Context applicationContext = getApplicationContext();
        if (!str.equals(BaseUtils.getLocalToken(applicationContext, str2))) {
            HMSLog.i("HmsMessageService", "receive a new token, refresh the local token");
            BaseUtils.saveToken(applicationContext, str2, str);
        }
        if (TextUtils.isEmpty(str2)) {
            BaseUtils.reportAaidToken(applicationContext, str);
        }
    }

    private void b(Intent intent) {
        HMSLog.i("HmsMessageService", "parse batch response.");
        String stringExtra = intent.getStringExtra("batchMsgbody");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(stringExtra);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String optString = jSONObject.optString("transactionId");
                String optString2 = jSONObject.optString("msgId");
                int optInt = jSONObject.optInt("ret", ErrorEnum.ERROR_UNKNOWN.getInternalCode());
                if (ErrorEnum.SUCCESS.getInternalCode() == optInt) {
                    b(optString, optString2);
                } else {
                    b(optString, optString2, optInt);
                }
            }
        } catch (JSONException unused) {
            HMSLog.w("HmsMessageService", "parse batch response failed.");
        }
    }

    protected void doMsgReceived(Intent intent) {
        onMessageReceived(new RemoteMessage(a(intent)));
    }

    public void handleIntentMessage(Intent intent) {
        if (intent == null) {
            HMSLog.e("HmsMessageService", "receive message is null");
            return;
        }
        try {
            String stringExtra = intent.getStringExtra(PushMessageTable.TB_CLOUMN_MESSAGE_ID);
            String stringExtra2 = intent.getStringExtra(RemoteMessageConst.MSGTYPE);
            String stringExtra3 = intent.getStringExtra(CommonCode.MapKey.TRANSACTION_ID);
            if ("new_token".equals(stringExtra2)) {
                HMSLog.i("HmsMessageService", "onNewToken");
                a(intent, stringExtra3);
            } else if ("received_message".equals(stringExtra2)) {
                HMSLog.i("HmsMessageService", "onMessageReceived, message id:" + stringExtra);
                a(PushNaming.RECEIVE_MSG_RSP, stringExtra, ErrorEnum.SUCCESS.getInternalCode());
                doMsgReceived(intent);
            } else if ("sent_message".equals(stringExtra2)) {
                b(stringExtra3, stringExtra);
            } else if ("send_error".equals(stringExtra2)) {
                b(stringExtra3, stringExtra, intent.getIntExtra("error", ErrorEnum.ERROR_UNKNOWN.getInternalCode()));
            } else if ("delivery".equals(stringExtra2)) {
                int intExtra = intent.getIntExtra("error", ErrorEnum.ERROR_APP_SERVER_NOT_ONLINE.getInternalCode());
                HMSLog.i("HmsMessageService", "onMessageDelivery, message id:" + stringExtra + ", status:" + intExtra + ", transactionId: " + stringExtra3);
                a(PushNaming.UPSEND_RECEIPT, stringExtra3, intExtra);
                onMessageDelivered(stringExtra, new SendException(intExtra));
            } else if ("server_deleted_message".equals(stringExtra2)) {
                HMSLog.i("HmsMessageService", "delete message, message id:" + stringExtra);
                onDeletedMessages();
            } else if ("batchSent".equals(stringExtra2)) {
                b(intent);
            } else {
                HMSLog.e("HmsMessageService", "Receive unknown message: " + stringExtra2);
            }
        } catch (RuntimeException e2) {
            HMSLog.e("HmsMessageService", "handle intent RuntimeException: " + e2.getMessage());
        } catch (Exception e3) {
            HMSLog.e("HmsMessageService", "handle intent exception: " + e3.getMessage());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        HMSLog.i("HmsMessageService", "start to bind");
        return this.a.getBinder();
    }

    public void onDeletedMessages() {
    }

    @Override // android.app.Service
    public void onDestroy() {
        HMSLog.i("HmsMessageService", "start to destroy");
        super.onDestroy();
    }

    public void onMessageDelivered(String str, Exception exc) {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onNewToken(String str, Bundle bundle) {
    }

    public void onSendError(String str, Exception exc) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        HMSLog.i("HmsMessageService", "start to command , startId = " + i3);
        handleIntentMessage(intent);
        return 2;
    }

    public void onTokenError(Exception exc) {
    }

    public void onTokenError(Exception exc, Bundle bundle) {
    }

    private Bundle a(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putString(PushMessageTable.TB_CLOUMN_MESSAGE_ID, intent.getStringExtra(PushMessageTable.TB_CLOUMN_MESSAGE_ID));
        bundle.putByteArray(RemoteMessageConst.MSGBODY, intent.getByteArrayExtra(RemoteMessageConst.MSGBODY));
        bundle.putString(RemoteMessageConst.DEVICE_TOKEN, intent.getStringExtra(RemoteMessageConst.DEVICE_TOKEN));
        if (intent.getIntExtra("inputType", -1) == 1) {
            bundle.putInt("inputType", 1);
        }
        return bundle;
    }

    private void b(String str, String str2) {
        HMSLog.i("HmsMessageService", "onMessageSent, message id:" + str2 + ", transactionId: " + str);
        a(PushNaming.UPSEND_MSG_ASYNC_RSP, str, ErrorEnum.SUCCESS.getInternalCode());
        onMessageSent(str2);
    }

    private void a(Intent intent, String str) {
        ErrorEnum errorEnum = ErrorEnum.SUCCESS;
        int intExtra = intent.getIntExtra("error", errorEnum.getInternalCode());
        a(PushNaming.GETTOKEN_ASYNC_RSP, str, intExtra);
        String stringExtra = intent.getStringExtra("subjectId");
        String stringExtra2 = intent.getStringExtra("message_proxy_type");
        HMSLog.i("HmsMessageService", "doOnNewToken:transactionId = " + str + " , internalCode = " + intExtra + ",subjectId:" + stringExtra + ",proxyType:" + stringExtra2);
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(stringExtra)) {
            bundle.putString(SUBJECT_ID, stringExtra);
        }
        if (!TextUtils.isEmpty(stringExtra2)) {
            bundle.putString(PROXY_TYPE, stringExtra2);
        }
        if (intExtra == errorEnum.getInternalCode()) {
            HMSLog.i("HmsMessageService", "Apply token OnNewToken, subId: " + stringExtra);
            a(intent, bundle, stringExtra);
            return;
        }
        HMSLog.i("HmsMessageService", "Apply token failed, subId: " + stringExtra);
        a(intent, bundle, stringExtra, intExtra);
    }

    private void b(String str, String str2, int i2) {
        HMSLog.i("HmsMessageService", "onSendError, message id:" + str2 + " error:" + i2 + ", transactionId: " + str);
        a(PushNaming.UPSEND_MSG_ASYNC_RSP, str, i2);
        onSendError(str2, new SendException(i2));
    }

    private synchronized void a(Intent intent, Bundle bundle, String str, int i2) {
        Context applicationContext = getApplicationContext();
        boolean z = !TextUtils.isEmpty(BaseUtils.getCacheData(applicationContext, applicationContext.getPackageName(), false));
        if (bundle.isEmpty() && z) {
            HMSLog.i("HmsMessageService", "onTokenError to host app.");
            onTokenError(new BaseException(i2));
            BaseUtils.deleteCacheData(applicationContext, applicationContext.getPackageName());
        }
        if (TextUtils.isEmpty(str)) {
            String[] subjectIds = BaseUtils.getSubjectIds(applicationContext);
            if (subjectIds != null && subjectIds.length != 0) {
                for (int i3 = 0; i3 < subjectIds.length; i3++) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(SUBJECT_ID, subjectIds[i3]);
                    HMSLog.i("HmsMessageService", "onTokenError to sub app, subjectId:" + subjectIds[i3]);
                    onTokenError(new BaseException(i2), bundle2);
                }
                BaseUtils.clearSubjectIds(applicationContext);
            }
            HMSLog.i("HmsMessageService", "onTokenError to host app with bundle.");
            onTokenError(new BaseException(i2), bundle);
            return;
        }
        HMSLog.i("HmsMessageService", "onTokenError to sub app, subjectId:" + str);
        onTokenError(new BaseException(i2), bundle);
    }

    private synchronized void a(Intent intent, Bundle bundle, String str) {
        String stringExtra = intent.getStringExtra(RemoteMessageConst.DEVICE_TOKEN);
        a(stringExtra, str);
        Context applicationContext = getApplicationContext();
        boolean z = !TextUtils.isEmpty(BaseUtils.getCacheData(applicationContext, applicationContext.getPackageName(), false));
        if (bundle.isEmpty() && z) {
            HMSLog.i("HmsMessageService", "onNewToken to host app.");
            onNewToken(stringExtra);
            BaseUtils.deleteCacheData(applicationContext, applicationContext.getPackageName());
        }
        if (TextUtils.isEmpty(str)) {
            String[] subjectIds = BaseUtils.getSubjectIds(applicationContext);
            if (subjectIds != null && subjectIds.length != 0) {
                for (int i2 = 0; i2 < subjectIds.length; i2++) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(SUBJECT_ID, subjectIds[i2]);
                    HMSLog.i("HmsMessageService", "onNewToken to sub app, subjectId:" + subjectIds[i2]);
                    onNewToken(stringExtra, bundle2);
                    a(stringExtra, subjectIds[i2]);
                }
                BaseUtils.clearSubjectIds(applicationContext);
            }
            HMSLog.i("HmsMessageService", "onNewToken to host app with bundle.");
            bundle.putString("belongId", intent.getStringExtra("belongId"));
            onNewToken(stringExtra, bundle);
            return;
        }
        HMSLog.i("HmsMessageService", "onNewToken to sub app, subjectId:" + str);
        onNewToken(stringExtra, bundle);
    }

    private void a(String str, String str2, int i2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = DYConstants.DY_NULL_STR;
        }
        PushBiUtil.reportExit(getApplicationContext(), str, str2, i2);
    }
}
