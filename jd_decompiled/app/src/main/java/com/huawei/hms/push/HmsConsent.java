package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.task.ConsentTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.EnableConsentReq;
import com.huawei.hms.support.api.entity.push.PushNaming;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes12.dex */
public class HmsConsent {
    private HuaweiApi<Api.ApiOptions.NoOptions> a;
    private Context b;

    private HmsConsent(Context context) {
        Preconditions.checkNotNull(context);
        this.b = context;
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.a = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.a = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.a.setKitSdkVersion(60900300);
    }

    private g.e.c.a.f<Void> a(boolean z) {
        g.e.c.a.g gVar;
        int externalCode;
        String reportEntry = PushBiUtil.reportEntry(this.b, PushNaming.PUSH_CONSENT);
        try {
            if (c.d(this.b)) {
                EnableConsentReq enableConsentReq = new EnableConsentReq();
                enableConsentReq.setPackageName(this.b.getPackageName());
                enableConsentReq.setEnable(z);
                return this.a.doWrite(new ConsentTask(PushNaming.PUSH_CONSENT, JsonUtil.createJsonString(enableConsentReq), reportEntry));
            }
            throw ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException();
        } catch (ApiException e2) {
            g.e.c.a.g gVar2 = new g.e.c.a.g();
            gVar2.c(e2);
            externalCode = e2.getStatusCode();
            gVar = gVar2;
            PushBiUtil.reportExit(this.b, PushNaming.PUSH_CONSENT, reportEntry, externalCode);
            return gVar.b();
        } catch (Exception unused) {
            gVar = new g.e.c.a.g();
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            gVar.c(errorEnum.toApiException());
            externalCode = errorEnum.getExternalCode();
            PushBiUtil.reportExit(this.b, PushNaming.PUSH_CONSENT, reportEntry, externalCode);
            return gVar.b();
        }
    }

    public static HmsConsent getInstance(Context context) {
        return new HmsConsent(context);
    }

    public g.e.c.a.f<Void> consentOff() {
        return a(false);
    }

    public g.e.c.a.f<Void> consentOn() {
        return a(true);
    }
}
