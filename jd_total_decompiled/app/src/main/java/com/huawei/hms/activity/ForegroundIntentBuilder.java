package com.huawei.hms.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.ForegroundBusResponseMgr;
import com.huawei.hms.activity.internal.ForegroundInnerHeader;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.support.api.entity.core.CoreNaming;
import com.huawei.hms.utils.Util;

/* loaded from: classes12.dex */
public class ForegroundIntentBuilder {
    private Activity a;
    private RequestHeader b;

    /* renamed from: c  reason: collision with root package name */
    private String f1177c;
    private ForegroundInnerHeader d;

    /* renamed from: e  reason: collision with root package name */
    private String f1178e;

    /* renamed from: f  reason: collision with root package name */
    private Context f1179f;

    public ForegroundIntentBuilder(Activity activity) throws IllegalArgumentException {
        if (activity != null) {
            this.a = activity;
            RequestHeader requestHeader = new RequestHeader();
            this.b = requestHeader;
            requestHeader.setSdkVersion(60800300);
            this.f1177c = "";
            ForegroundInnerHeader foregroundInnerHeader = new ForegroundInnerHeader();
            this.d = foregroundInnerHeader;
            foregroundInnerHeader.setApkVersion(30000000);
            return;
        }
        throw new IllegalArgumentException("listener must not be null.");
    }

    public static void registerResponseCallback(String str, BusResponseCallback busResponseCallback) {
        ForegroundBusResponseMgr.getInstance().registerObserver(str, busResponseCallback);
    }

    public static void unregisterResponseCallback(String str) {
        ForegroundBusResponseMgr.getInstance().unRegisterObserver(str);
    }

    public Intent build() {
        String packageName;
        String appId;
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(this.a, ForegroundBusDelegate.class.getName());
        Context context = this.f1179f;
        if (context != null) {
            packageName = context.getPackageName();
            appId = Util.getAppId(this.f1179f);
        } else {
            packageName = this.a.getPackageName();
            appId = Util.getAppId(this.a);
        }
        if (this.b.getAppID() == null) {
            this.b.setAppID(appId + "|");
        } else {
            this.b.setAppID(appId + "|" + this.b.getAppID());
        }
        if (TextUtils.isEmpty(this.b.getTransactionId())) {
            RequestHeader requestHeader = this.b;
            requestHeader.setTransactionId(TransactionIdCreater.getId(requestHeader.getAppID(), CoreNaming.HUBREQUEST));
        }
        this.b.setPkgName(packageName);
        intentStartBridgeActivity.putExtra(ForegroundBusDelegate.HMS_FOREGROUND_REQ_HEADER, this.b.toJson());
        intentStartBridgeActivity.putExtra(ForegroundBusDelegate.HMS_FOREGROUND_REQ_BODY, this.f1177c);
        intentStartBridgeActivity.putExtra(ForegroundBusDelegate.HMS_FOREGROUND_REQ_INNER, this.d.toJson());
        if (!TextUtils.isEmpty(this.f1178e)) {
            intentStartBridgeActivity.putExtra(ForegroundBusDelegate.INNER_PKG_NAME, this.f1178e);
        }
        return intentStartBridgeActivity;
    }

    public ForegroundIntentBuilder setAction(String str) {
        this.b.setApiName(str);
        return this;
    }

    public ForegroundIntentBuilder setApiLevel(int i2) {
        this.b.setApiLevel(i2);
        return this;
    }

    public ForegroundIntentBuilder setApplicationContext(Context context) {
        this.f1179f = context;
        return this;
    }

    public ForegroundIntentBuilder setInnerHms() {
        this.f1178e = this.a.getPackageName();
        return this;
    }

    public ForegroundIntentBuilder setKitSdkVersion(int i2) {
        this.b.setKitSdkVersion(i2);
        return this;
    }

    public ForegroundIntentBuilder setMinApkVersion(int i2) {
        this.d.setApkVersion(i2);
        return this;
    }

    public ForegroundIntentBuilder setRequestBody(String str) {
        this.f1177c = str;
        return this;
    }

    public ForegroundIntentBuilder setResponseCallback(String str, BusResponseCallback busResponseCallback) {
        this.d.setResponseCallbackKey(str);
        ForegroundBusResponseMgr.getInstance().registerObserver(str, busResponseCallback);
        return this;
    }

    public ForegroundIntentBuilder setServiceName(String str) {
        this.b.setSrvName(str);
        return this;
    }

    public ForegroundIntentBuilder setSubAppId(String str) {
        this.b.setAppID(str);
        return this;
    }

    public ForegroundIntentBuilder setTransactionId(String str) {
        this.b.setTransactionId(str);
        return this;
    }

    public ForegroundIntentBuilder setResponseCallback(String str) {
        this.d.setResponseCallbackKey(str);
        return this;
    }
}
