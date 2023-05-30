package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.task.ProfileTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.ProfileReq;
import com.huawei.hms.support.api.entity.push.PushNaming;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: classes12.dex */
public class HmsProfile {
    public static final int CUSTOM_PROFILE = 2;
    public static final int HUAWEI_PROFILE = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final String f1444c = "HmsProfile";
    private Context a;
    private HuaweiApi<Api.ApiOptions.NoOptions> b;

    private HmsProfile(Context context) {
        this.a = null;
        Preconditions.checkNotNull(context);
        this.a = context;
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.b = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.b = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.b.setKitSdkVersion(60900300);
    }

    private g.e.c.a.f<Void> a(int i2, String str, int i3, String str2) {
        if (!isSupportProfile()) {
            g.e.c.a.g gVar = new g.e.c.a.g();
            gVar.c(ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException());
            return gVar.b();
        }
        if (!TextUtils.isEmpty(str)) {
            String a = a(this.a);
            if (TextUtils.isEmpty(a)) {
                HMSLog.i(f1444c, "agc connect services config missing project id.");
                g.e.c.a.g gVar2 = new g.e.c.a.g();
                gVar2.c(ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException());
                return gVar2.b();
            } else if (str.equals(a)) {
                str = "";
            }
        }
        ProfileReq profileReq = new ProfileReq();
        if (i2 == 0) {
            profileReq.setOperation(0);
            profileReq.setType(i3);
        } else {
            profileReq.setOperation(1);
        }
        String reportEntry = PushBiUtil.reportEntry(this.a, PushNaming.PUSH_PROFILE);
        try {
            profileReq.setSubjectId(str);
            profileReq.setProfileId(com.huawei.secure.android.common.encrypt.b.b.b(str2));
            profileReq.setPkgName(this.a.getPackageName());
            return this.b.doWrite(new ProfileTask(PushNaming.PUSH_PROFILE, JsonUtil.createJsonString(profileReq), reportEntry));
        } catch (Exception e2) {
            if (e2.getCause() instanceof ApiException) {
                g.e.c.a.g gVar3 = new g.e.c.a.g();
                ApiException apiException = (ApiException) e2.getCause();
                gVar3.c(apiException);
                PushBiUtil.reportExit(this.a, PushNaming.PUSH_PROFILE, reportEntry, apiException.getStatusCode());
                return gVar3.b();
            }
            g.e.c.a.g gVar4 = new g.e.c.a.g();
            Context context = this.a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            PushBiUtil.reportExit(context, PushNaming.PUSH_PROFILE, reportEntry, errorEnum);
            gVar4.c(errorEnum.toApiException());
            return gVar4.b();
        }
    }

    private boolean b(Context context) {
        return c.b(context) >= 110001400;
    }

    public static HmsProfile getInstance(Context context) {
        return new HmsProfile(context);
    }

    public g.e.c.a.f<Void> addProfile(int i2, String str) {
        return addProfile("", i2, str);
    }

    public g.e.c.a.f<Void> deleteProfile(String str) {
        return deleteProfile("", str);
    }

    public boolean isSupportProfile() {
        if (c.d(this.a)) {
            if (c.c()) {
                HMSLog.i(f1444c, "current EMUI version below 9.1, not support profile operation.");
                return false;
            } else if (b(this.a)) {
                return true;
            } else {
                HMSLog.i(f1444c, "current HwPushService.apk version below 11.0.1.400,please upgrade your HwPushService.apk version.");
                return false;
            }
        }
        return true;
    }

    public g.e.c.a.f<Void> addProfile(String str, int i2, String str2) {
        if (i2 != 1 && i2 != 2) {
            HMSLog.i(f1444c, "add profile type undefined.");
            g.e.c.a.g gVar = new g.e.c.a.g();
            gVar.c(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return gVar.b();
        } else if (TextUtils.isEmpty(str2)) {
            HMSLog.i(f1444c, "add profile params is empty.");
            g.e.c.a.g gVar2 = new g.e.c.a.g();
            gVar2.c(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return gVar2.b();
        } else {
            return a(0, str, i2, str2);
        }
    }

    public g.e.c.a.f<Void> deleteProfile(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            HMSLog.e(f1444c, "del profile params is empty.");
            g.e.c.a.g gVar = new g.e.c.a.g();
            gVar.c(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return gVar.b();
        }
        return a(1, str, -1, str2);
    }

    private static String a(Context context) {
        return g.e.a.h.a.b(context).getString("client/project_id");
    }
}
