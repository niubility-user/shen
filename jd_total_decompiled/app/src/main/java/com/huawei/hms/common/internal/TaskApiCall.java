package com.huawei.hms.common.internal;

import android.os.Parcelable;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.support.log.HMSLog;
import g.e.c.a.a;
import g.e.c.a.g;

/* loaded from: classes12.dex */
public abstract class TaskApiCall<ClientT extends AnyClient, ResultT> {
    private final String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private Parcelable f1275c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private a f1276e;

    /* renamed from: f  reason: collision with root package name */
    private int f1277f;

    @Deprecated
    public TaskApiCall(String str, String str2) {
        this.f1277f = 1;
        this.a = str;
        this.b = str2;
        this.f1275c = null;
        this.d = null;
    }

    protected abstract void doExecute(ClientT clientt, ResponseErrorCode responseErrorCode, String str, g<ResultT> gVar);

    public int getApiLevel() {
        return this.f1277f;
    }

    @Deprecated
    public int getMinApkVersion() {
        return 30000000;
    }

    public Parcelable getParcelable() {
        return this.f1275c;
    }

    public String getRequestJson() {
        return this.b;
    }

    public a getToken() {
        return this.f1276e;
    }

    public String getTransactionId() {
        return this.d;
    }

    public String getUri() {
        return this.a;
    }

    public final void onResponse(ClientT clientt, ResponseErrorCode responseErrorCode, String str, g<ResultT> gVar) {
        a aVar = this.f1276e;
        if (aVar != null && aVar.a()) {
            HMSLog.i("TaskApiCall", "This Task has been canceled, uri:" + this.a + ", transactionId:" + this.d);
            return;
        }
        HMSLog.i("TaskApiCall", "doExecute, uri:" + this.a + ", errorCode:" + responseErrorCode.getErrorCode() + ", transactionId:" + this.d);
        doExecute(clientt, responseErrorCode, str, gVar);
    }

    public void setApiLevel(int i2) {
        this.f1277f = i2;
    }

    public void setParcelable(Parcelable parcelable) {
        this.f1275c = parcelable;
    }

    public void setToken(a aVar) {
    }

    public void setTransactionId(String str) {
        this.d = str;
    }

    public TaskApiCall(String str, String str2, String str3) {
        this.f1277f = 1;
        this.a = str;
        this.b = str2;
        this.f1275c = null;
        this.d = str3;
    }

    public TaskApiCall(String str, String str2, String str3, int i2) {
        this.a = str;
        this.b = str2;
        this.f1275c = null;
        this.d = str3;
        this.f1277f = i2;
    }
}
