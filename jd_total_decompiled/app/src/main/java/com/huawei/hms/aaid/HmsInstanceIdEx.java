package com.huawei.hms.aaid;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.opendevice.b;
import com.huawei.hms.opendevice.h;
import com.huawei.hms.support.log.HMSLog;
import g.e.c.a.f;
import g.e.c.a.g;
import java.util.UUID;

@Deprecated
/* loaded from: classes12.dex */
public class HmsInstanceIdEx {
    public static final String TAG = "HmsInstanceIdEx";
    private Context a;
    private PushPreferences b;

    /* renamed from: c  reason: collision with root package name */
    private HuaweiApi<Api.ApiOptions.NoOptions> f1173c;

    private HmsInstanceIdEx(Context context) {
        this.b = null;
        this.a = context;
        this.b = new PushPreferences(context, "aaid");
        Api api = new Api(HuaweiApiAvailability.HMS_API_NAME_PUSH);
        if (context instanceof Activity) {
            this.f1173c = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f1173c = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f1173c.setKitSdkVersion(60900300);
    }

    private String a(String str) {
        return "creationTime" + str;
    }

    public static HmsInstanceIdEx getInstance(Context context) {
        Preconditions.checkNotNull(context);
        return new HmsInstanceIdEx(context);
    }

    public void deleteAAID(String str) throws ApiException {
        if (str != null) {
            try {
                if (this.b.containsKey(str)) {
                    this.b.removeKey(str);
                    this.b.removeKey(a(str));
                    return;
                }
                return;
            } catch (RuntimeException unused) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            } catch (Exception unused2) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            }
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public String getAAId(String str) throws ApiException {
        if (str != null) {
            try {
                if (this.b.containsKey(str)) {
                    return this.b.getString(str);
                }
                String uuid = UUID.randomUUID().toString();
                this.b.saveString(str, uuid);
                this.b.saveLong(a(str), Long.valueOf(System.currentTimeMillis()));
                return uuid;
            } catch (RuntimeException unused) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            } catch (Exception unused2) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            }
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public long getCreationTime(String str) throws ApiException {
        if (str != null) {
            try {
                if (!this.b.containsKey(a(str))) {
                    getAAId(str);
                }
                return this.b.getLong(a(str));
            } catch (RuntimeException unused) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            } catch (Exception unused2) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            }
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public f<TokenResult> getToken() {
        if (ProxyCenter.getProxy() != null) {
            try {
                HMSLog.i(TAG, "use proxy get token, please check HmsMessageService.onNewToken receive result.");
                ProxyCenter.getProxy().getToken(this.a, null, null);
                g gVar = new g();
                gVar.d(new TokenResult());
                return gVar.b();
            } catch (ApiException e2) {
                return a(e2);
            } catch (Exception unused) {
                return a(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
            }
        }
        String a = h.a(this.a, "push.gettoken");
        try {
            TokenReq b = b.b(this.a, null, null);
            b.setAaid(HmsInstanceId.getInstance(this.a).getId());
            return this.f1173c.doWrite(new com.huawei.hms.opendevice.f("push.gettoken", b, this.a, a));
        } catch (RuntimeException unused2) {
            Context context = this.a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            h.a(context, "push.gettoken", a, errorEnum);
            return a(errorEnum.toApiException());
        } catch (Exception unused3) {
            Context context2 = this.a;
            ErrorEnum errorEnum2 = ErrorEnum.ERROR_INTERNAL_ERROR;
            h.a(context2, "push.gettoken", a, errorEnum2);
            return a(errorEnum2.toApiException());
        }
    }

    private f<TokenResult> a(Exception exc) {
        g gVar = new g();
        gVar.c(exc);
        return gVar.b();
    }
}
