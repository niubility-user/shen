package com.hihonor.push.sdk;

import android.os.Bundle;
import android.os.Handler;
import com.hihonor.push.framework.aidl.DataBuffer;
import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.IPushCallback;
import com.hihonor.push.framework.aidl.MessageCodec;
import com.hihonor.push.framework.aidl.entity.ResponseHeader;
import com.hihonor.push.sdk.c1;
import com.hihonor.push.sdk.common.data.ApiException;

/* loaded from: classes12.dex */
public class y extends IPushCallback.Stub {
    public final Object a;
    public final a0 b;

    public y(Object obj, a0 a0Var) {
        this.a = obj;
        this.b = a0Var;
    }

    @Override // com.hihonor.push.framework.aidl.IPushCallback
    public void onResult(DataBuffer dataBuffer) {
        Bundle header = dataBuffer.getHeader();
        Bundle body = dataBuffer.getBody();
        ResponseHeader responseHeader = new ResponseHeader();
        MessageCodec.parseMessageEntity(header, responseHeader);
        Object obj = this.a;
        if (obj instanceof IMessageEntity) {
            MessageCodec.parseMessageEntity(body, (IMessageEntity) obj);
        }
        a0 a0Var = this.b;
        ApiException apiException = new ApiException(responseHeader.getStatusCode(), responseHeader.getStatusMessage());
        Object obj2 = this.a;
        c1.b bVar = (c1.b) a0Var;
        bVar.getClass();
        c1 c1Var = c1.f1081i;
        u<?> uVar = bVar.a;
        c1Var.getClass();
        Handler handler = c1Var.f1082g;
        handler.sendMessage(handler.obtainMessage(2, uVar));
        bVar.a.b(apiException, obj2);
    }
}
