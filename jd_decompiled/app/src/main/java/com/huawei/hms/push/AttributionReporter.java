package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.task.AttributionReportTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.AttributionReportReq;
import com.huawei.hms.support.api.entity.push.PushNaming;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.ui.SafeBundle;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes12.dex */
public class AttributionReporter {
    public static final String APP_VERSION = "appVersion";
    public static final String SYSTEM_PERMISSION = "permission";
    private HuaweiApi<Api.ApiOptions.NoOptions> a;
    private Context b;

    private AttributionReporter(Context context) {
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

    private g.e.c.a.f<Void> a(AttributionEvent attributionEvent, Bundle bundle) {
        g.e.c.a.g gVar;
        int externalCode;
        String reportEntry = PushBiUtil.reportEntry(this.b, PushNaming.PUSH_ANALYSIS_REPORT);
        if (bundle != null && attributionEvent != null) {
            try {
                if (c.d(this.b)) {
                    long j2 = new PushPreferences(this.b, "hwpush_local_config").getLong("analysis_last_failed_time");
                    if (j2 > 0 && System.currentTimeMillis() - j2 < 86400000) {
                        throw ErrorEnum.ERROR_NOT_IN_SERVICE.toApiException();
                    }
                    return this.a.doWrite(new AttributionReportTask(PushNaming.PUSH_ANALYSIS_REPORT, JsonUtil.createJsonString(a(attributionEvent, new SafeBundle(bundle))), reportEntry));
                }
                throw ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException();
            } catch (ApiException e2) {
                g.e.c.a.g gVar2 = new g.e.c.a.g();
                gVar2.c(e2);
                externalCode = e2.getStatusCode();
                gVar = gVar2;
                PushBiUtil.reportExit(this.b, PushNaming.PUSH_ANALYSIS_REPORT, reportEntry, externalCode);
                return gVar.b();
            } catch (Exception unused) {
                gVar = new g.e.c.a.g();
                ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
                gVar.c(errorEnum.toApiException());
                externalCode = errorEnum.getExternalCode();
                PushBiUtil.reportExit(this.b, PushNaming.PUSH_ANALYSIS_REPORT, reportEntry, externalCode);
                return gVar.b();
            }
        }
        PushBiUtil.reportExit(this.b, PushNaming.PUSH_ANALYSIS_REPORT, reportEntry, ErrorEnum.ERROR_ARGUMENTS_INVALID);
        HMSLog.e("AttributionReporter", "Invalid argument: argument should not be null");
        throw new IllegalArgumentException("Invalid argument: argument should not be null");
    }

    public static AttributionReporter getInstance(Context context) {
        return new AttributionReporter(context);
    }

    public g.e.c.a.f<Void> report(AttributionEvent attributionEvent, Bundle bundle) {
        return a(attributionEvent, bundle);
    }

    private AttributionReportReq a(AttributionEvent attributionEvent, SafeBundle safeBundle) throws ApiException {
        Bundle bundle = safeBundle.getBundle().getBundle("analysisExt");
        if (bundle != null && !bundle.isEmpty()) {
            String string = bundle.getString("msgId");
            if (!TextUtils.isEmpty(string)) {
                String string2 = bundle.getString("hsId");
                if (!TextUtils.isEmpty(string2)) {
                    AttributionReportReq attributionReportReq = new AttributionReportReq();
                    attributionReportReq.setCampaignId(bundle.getString("cid"));
                    attributionReportReq.setMsgId(string);
                    attributionReportReq.setHaStorageId(string2);
                    attributionReportReq.setEventId(attributionEvent.getEventId());
                    attributionReportReq.setPkgName(this.b.getPackageName());
                    if (attributionEvent.equals(AttributionEvent.PERMISSION_GRANTED) || attributionEvent.equals(AttributionEvent.PERMISSION_DENIED)) {
                        String string3 = safeBundle.getString(SYSTEM_PERMISSION);
                        if (!TextUtils.isEmpty(string3) && string3.startsWith("android.permission")) {
                            attributionReportReq.setReqPermission(string3);
                        } else {
                            throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
                        }
                    }
                    attributionReportReq.setTimeStamp(System.currentTimeMillis());
                    attributionReportReq.setAppVersion(safeBundle.getString("appVersion"));
                    return attributionReportReq;
                }
                throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
            }
            throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }
}
