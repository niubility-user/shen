package com.huawei.hms.push.task;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClient;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.p;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.jingdong.common.database.table.PushMessageTable;
import g.e.c.a.g;

/* loaded from: classes12.dex */
public class SendUpStreamTask extends TaskApiCall<PushClient, BaseVoidTask> {
    private String a;
    private String b;

    public SendUpStreamTask(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3);
        this.a = str4;
        this.b = str5;
    }

    private void a(PushClient pushClient, ResponseErrorCode responseErrorCode) {
        HMSLog.i("SendUpStreamTask", "receive upstream, msgId :" + this.b + " , packageName = " + this.a + " , errorCode = " + responseErrorCode.getErrorCode());
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(this.a);
        Bundle bundle = new Bundle();
        bundle.putString(PushMessageTable.TB_CLOUMN_MESSAGE_ID, this.b);
        bundle.putInt("error", responseErrorCode.getErrorCode());
        if (ErrorEnum.SUCCESS.getInternalCode() == responseErrorCode.getErrorCode()) {
            bundle.putString(RemoteMessageConst.MSGTYPE, "sent_message");
        } else {
            bundle.putString(RemoteMessageConst.MSGTYPE, "send_error");
        }
        if (new p().a(pushClient.getContext(), bundle, intent)) {
            HMSLog.i("SendUpStreamTask", "receive upstream, start service success");
            PushBiUtil.reportExit(pushClient.getContext(), getUri(), responseErrorCode);
            return;
        }
        HMSLog.w("SendUpStreamTask", "receive upstream, start service failed");
        PushBiUtil.reportExit(pushClient.getContext(), getUri(), responseErrorCode.getTransactionId(), ErrorEnum.ERROR_BIND_SERVICE_SELF_MAPPING);
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 40003000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(PushClient pushClient, ResponseErrorCode responseErrorCode, String str, g<BaseVoidTask> gVar) {
        if (responseErrorCode.getErrorCode() == 0) {
            HMSLog.i("SendUpStreamTask", "send up stream task,Operate succeed");
            gVar.d(null);
        } else {
            HMSLog.e("SendUpStreamTask", "send up stream task,Operate failed with ret=" + responseErrorCode.getErrorCode());
            ErrorEnum fromCode = ErrorEnum.fromCode(responseErrorCode.getErrorCode());
            if (fromCode != ErrorEnum.ERROR_UNKNOWN) {
                gVar.c(fromCode.toApiException());
            } else {
                gVar.c(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        }
        a(pushClient, responseErrorCode);
    }
}
