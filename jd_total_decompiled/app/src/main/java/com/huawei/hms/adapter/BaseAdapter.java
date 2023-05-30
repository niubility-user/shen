package com.huawei.hms.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.adapter.internal.CommonCode;
import com.huawei.hms.adapter.sysobs.ApkResolutionFailedManager;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.adapter.sysobs.SystemObserver;
import com.huawei.hms.adapter.ui.BaseResolutionAdapter;
import com.huawei.hms.adapter.ui.UpdateAdapter;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.ResponseWrap;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.kpms.KpmsConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IntentUtil;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class BaseAdapter {
    private WeakReference<ApiClient> a;
    private WeakReference<Activity> b;

    /* renamed from: c */
    private BaseCallBack f1183c;
    private String d;

    /* renamed from: e */
    private String f1184e;

    /* renamed from: f */
    private Parcelable f1185f;

    /* renamed from: g */
    private BaseCallBack f1186g;

    /* renamed from: h */
    private String f1187h;

    /* renamed from: i */
    private Context f1188i;

    /* renamed from: j */
    private RequestHeader f1189j = new RequestHeader();

    /* renamed from: k */
    private ResponseHeader f1190k = new ResponseHeader();

    /* renamed from: l */
    private SystemObserver f1191l;

    /* loaded from: classes12.dex */
    public interface BaseCallBack {
        void onComplete(String str, String str2, Parcelable parcelable);

        void onError(String str);
    }

    /* loaded from: classes12.dex */
    public class BaseRequestResultCallback implements ResultCallback<ResolveResult<CoreBaseResponse>> {
        private AtomicBoolean a = new AtomicBoolean(true);

        public BaseRequestResultCallback() {
            BaseAdapter.this = r2;
        }

        private void a(String str, BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse, int i2) {
            if (CommonCode.Resolution.HAS_RESOLUTION_FROM_APK.equals(str)) {
                Activity c2 = BaseAdapter.this.c();
                HMSLog.i("BaseAdapter", "activity is: " + c2);
                if (c2 != null && !c2.isFinishing()) {
                    PendingIntent pendingIntent = coreBaseResponse.getPendingIntent();
                    if (pendingIntent != null) {
                        if (!Util.isAvailableLibExist(BaseAdapter.this.f1188i)) {
                            baseCallBack.onError(BaseAdapter.this.b(-9));
                            return;
                        } else {
                            BaseAdapter.this.a(c2, pendingIntent, coreBaseResponse);
                            return;
                        }
                    }
                    Intent intent = coreBaseResponse.getIntent();
                    if (intent != null) {
                        if (!Util.isAvailableLibExist(BaseAdapter.this.f1188i)) {
                            baseCallBack.onError(BaseAdapter.this.b(-9));
                            return;
                        } else {
                            BaseAdapter.this.a(c2, intent, coreBaseResponse);
                            return;
                        }
                    } else if (i2 == 2) {
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseCallBack.onError(baseAdapter.b(baseAdapter.f1190k.getErrorCode()));
                        return;
                    } else {
                        HMSLog.e("BaseAdapter", "hasResolution is true but NO_SOLUTION");
                        baseCallBack.onError(BaseAdapter.this.b(-4));
                        return;
                    }
                }
                HMSLog.e("BaseAdapter", "activity null");
                BaseAdapter.this.a(baseCallBack, coreBaseResponse);
            } else if (!"installHMS".equals(str)) {
                BaseAdapter.this.a(baseCallBack, coreBaseResponse);
            } else {
                HMSLog.i("BaseAdapter", "has resolutin: installHMS");
                a(baseCallBack, coreBaseResponse);
            }
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        public void onResult(ResolveResult<CoreBaseResponse> resolveResult) {
            BaseCallBack b = BaseAdapter.this.b();
            if (b == null) {
                HMSLog.e("BaseAdapter", "onResult baseCallBack null");
            } else if (resolveResult == null) {
                HMSLog.e("BaseAdapter", "result null");
                b.onError(BaseAdapter.this.b(-1));
            } else {
                CoreBaseResponse value = resolveResult.getValue();
                if (value == null) {
                    HMSLog.e("BaseAdapter", "response null");
                    b.onError(BaseAdapter.this.b(-1));
                } else if (!TextUtils.isEmpty(value.getJsonHeader())) {
                    JsonUtil.jsonToEntity(value.getJsonHeader(), BaseAdapter.this.f1190k);
                    if (this.a.compareAndSet(true, false)) {
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseAdapter.a(baseAdapter.f1188i, BaseAdapter.this.f1190k);
                    }
                    String resolution = BaseAdapter.this.f1190k.getResolution();
                    int statusCode = BaseAdapter.this.f1190k.getStatusCode();
                    HMSLog.i("BaseAdapter", "api is: " + BaseAdapter.this.f1190k.getApiName() + ", resolution: " + resolution + ", status_code: " + statusCode);
                    a(resolution, b, value, statusCode);
                } else {
                    HMSLog.e("BaseAdapter", "jsonHeader null");
                    b.onError(BaseAdapter.this.b(-1));
                }
            }
        }

        private void a(final BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse) {
            if (Util.isAvailableLibExist(BaseAdapter.this.f1188i)) {
                Activity c2 = BaseAdapter.this.c();
                if (c2 != null && !c2.isFinishing()) {
                    HMSLog.i("BaseAdapter", "start handleSolutionForHMS");
                    AvailableAdapter availableAdapter = new AvailableAdapter(ExceptionCode.CRASH_EXCEPTION);
                    availableAdapter.setCalledBySolutionInstallHms(true);
                    availableAdapter.startResolution(c2, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.adapter.BaseAdapter.BaseRequestResultCallback.1
                        {
                            BaseRequestResultCallback.this = this;
                        }

                        @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                        public void onComplete(int i2) {
                            HMSLog.i("BaseAdapter", "complete handleSolutionForHMS, result: " + i2);
                            if (i2 == 0) {
                                HMSPackageManager.getInstance(BaseAdapter.this.f1188i).resetMultiServiceState();
                                BaseAdapter baseAdapter = BaseAdapter.this;
                                baseCallBack.onError(baseAdapter.a(11, baseAdapter.a(11)).toJson());
                                BaseAdapter.this.i();
                                return;
                            }
                            BaseAdapter baseAdapter2 = BaseAdapter.this;
                            baseCallBack.onError(baseAdapter2.a(i2, baseAdapter2.a(i2)).toJson());
                        }
                    });
                    return;
                }
                HMSLog.e("BaseAdapter", "activity is null");
                try {
                    if (BaseAdapter.this.f1188i != null && AvailableUtil.isInstallerLibExist(BaseAdapter.this.f1188i)) {
                        HMSLog.i("BaseAdapter", "pass installHMS intent");
                        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(BaseAdapter.this.f1188i, UpdateAdapter.class.getName());
                        intentStartBridgeActivity.putExtra(CommonCode.MapKey.UPDATE_VERSION, ExceptionCode.CRASH_EXCEPTION);
                        intentStartBridgeActivity.putExtra("installHMS", "installHMS");
                        coreBaseResponse.setIntent(intentStartBridgeActivity);
                        BaseAdapter.this.a(baseCallBack, coreBaseResponse);
                    } else {
                        HMSLog.i("BaseAdapter", "pass ACTIVITY_NULL error");
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseCallBack.onError(baseAdapter.a(-3, baseAdapter.a(-3)).toJson());
                    }
                    return;
                } catch (RuntimeException unused) {
                    HMSLog.e("BaseAdapter", "handleSolutionForHms pass result failed");
                    return;
                }
            }
            HMSLog.i("BaseAdapter", "handleSolutionForHms: no Available lib exist");
            baseCallBack.onError(BaseAdapter.this.b(-9));
        }
    }

    /* loaded from: classes12.dex */
    public static class a extends PendingResultImpl<ResolveResult<CoreBaseResponse>, CoreBaseResponse> {
        public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a */
        public ResolveResult<CoreBaseResponse> onComplete(CoreBaseResponse coreBaseResponse) {
            ResolveResult<CoreBaseResponse> resolveResult = new ResolveResult<>(coreBaseResponse);
            resolveResult.setStatus(Status.SUCCESS);
            return resolveResult;
        }
    }

    public BaseAdapter(ApiClient apiClient) {
        this.a = new WeakReference<>(apiClient);
        this.f1188i = apiClient.getContext().getApplicationContext();
        HMSLog.i("BaseAdapter", "In constructor, WeakReference is " + this.a);
    }

    private void h() {
        if (this.d == null || this.f1186g == null) {
            return;
        }
        this.f1190k = null;
        this.f1190k = new ResponseHeader();
        baseRequest(d(), e(), f(), a());
    }

    public void i() {
        if (this.f1188i == null) {
            HMSLog.e("BaseAdapter", "sendBroadcastAfterResolutionHms, context is null");
            return;
        }
        Intent intent = new Intent("com.huawei.hms.core.action.SESSION_INVALID");
        try {
            intent.setPackage(this.f1188i.getPackageName());
            this.f1188i.sendBroadcast(intent);
        } catch (IllegalArgumentException unused) {
            HMSLog.e("BaseAdapter", "IllegalArgumentException when sendBroadcastAfterResolutionHms intent.setPackage");
        }
    }

    public void baseRequest(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        a(str, str2, parcelable, baseCallBack);
        if (this.a == null) {
            HMSLog.e("BaseAdapter", "client is null");
            baseCallBack.onError(b(-2));
            return;
        }
        this.f1183c = baseCallBack;
        JsonUtil.jsonToEntity(str, this.f1189j);
        CoreBaseRequest coreBaseRequest = new CoreBaseRequest();
        coreBaseRequest.setJsonObject(str2);
        coreBaseRequest.setJsonHeader(str);
        coreBaseRequest.setParcelable(parcelable);
        String apiName = this.f1189j.getApiName();
        if (TextUtils.isEmpty(apiName)) {
            HMSLog.e("BaseAdapter", "get uri null");
            baseCallBack.onError(b(-5));
            return;
        }
        String transactionId = this.f1189j.getTransactionId();
        this.f1187h = transactionId;
        if (TextUtils.isEmpty(transactionId)) {
            HMSLog.e("BaseAdapter", "get transactionId null");
            baseCallBack.onError(b(-6));
            return;
        }
        HMSLog.i("BaseAdapter", "in baseRequest + uri is :" + apiName + ", transactionId is : " + this.f1187h);
        a(this.f1188i, this.f1189j);
        a(this.a.get(), apiName, coreBaseRequest).setResultCallback(new BaseRequestResultCallback());
    }

    private String d() {
        return this.d;
    }

    private String e() {
        return this.f1184e;
    }

    private Parcelable f() {
        return this.f1185f;
    }

    private void g() {
        this.f1191l = new SystemObserver() { // from class: com.huawei.hms.adapter.BaseAdapter.2
            {
                BaseAdapter.this = this;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onNoticeResult(int i2) {
                return false;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onSolutionResult(Intent intent, String str) {
                if (!TextUtils.isEmpty(str)) {
                    if (str.equals(BaseAdapter.this.f1187h)) {
                        HMSLog.i("BaseAdapter", "onSolutionResult + id is :" + str);
                        BaseCallBack b = BaseAdapter.this.b();
                        if (b == null) {
                            HMSLog.e("BaseAdapter", "onResult baseCallBack null");
                            return true;
                        } else if (intent != null) {
                            if (BaseAdapter.this.b(intent, b) || BaseAdapter.this.a(intent, b)) {
                                return true;
                            }
                            HMSLog.e("BaseAdapter", "onComplete for on activity result");
                            BaseAdapter.this.c(intent, b);
                            return true;
                        } else {
                            HMSLog.e("BaseAdapter", "onSolutionResult but data is null");
                            String b2 = BaseAdapter.this.b(-7);
                            BaseAdapter baseAdapter = BaseAdapter.this;
                            baseAdapter.a(baseAdapter.f1188i, BaseAdapter.this.f1190k, 0L);
                            b.onError(b2);
                            return true;
                        }
                    }
                    return false;
                }
                HMSLog.e("BaseAdapter", "onSolutionResult but id is null");
                BaseCallBack b3 = BaseAdapter.this.b();
                if (b3 != null) {
                    b3.onError(BaseAdapter.this.b(-6));
                    return true;
                }
                HMSLog.e("BaseAdapter", "onSolutionResult baseCallBack null");
                return true;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onUpdateResult(int i2) {
                return false;
            }
        };
    }

    public Activity c() {
        if (this.b == null) {
            HMSLog.i("BaseAdapter", "activityWeakReference is " + this.b);
            return null;
        }
        ApiClient apiClient = this.a.get();
        if (apiClient == null) {
            HMSLog.i("BaseAdapter", "tmpApi is null");
            return null;
        }
        HMSLog.i("BaseAdapter", "activityWeakReference has " + this.b.get());
        return Util.getActiveActivity(this.b.get(), apiClient.getContext());
    }

    public BaseCallBack b() {
        BaseCallBack baseCallBack = this.f1183c;
        if (baseCallBack == null) {
            HMSLog.e("BaseAdapter", "callback null");
            return null;
        }
        return baseCallBack;
    }

    private void b(Context context, RequestHeader requestHeader) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader);
        mapFromRequestHeader.put("direction", HiAnalyticsConstant.Direction.REQUEST);
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_START_RESOLUTION, mapFromRequestHeader);
    }

    public BaseAdapter(ApiClient apiClient, Activity activity) {
        this.a = new WeakReference<>(apiClient);
        this.b = new WeakReference<>(activity);
        this.f1188i = activity.getApplicationContext();
        HMSLog.i("BaseAdapter", "In constructor, activityWeakReference is " + this.b + ", activity is " + this.b.get());
    }

    private PendingResult<ResolveResult<CoreBaseResponse>> a(ApiClient apiClient, String str, CoreBaseRequest coreBaseRequest) {
        return new a(apiClient, str, coreBaseRequest);
    }

    private void c(int i2) {
        this.f1190k.setTransactionId(this.f1189j.getTransactionId());
        this.f1190k.setAppID(this.f1189j.getAppID());
        this.f1190k.setApiName(this.f1189j.getApiName());
        this.f1190k.setSrvName(this.f1189j.getSrvName());
        this.f1190k.setPkgName(this.f1189j.getPkgName());
        this.f1190k.setStatusCode(1);
        this.f1190k.setErrorCode(i2);
        this.f1190k.setErrorReason("Core error");
    }

    public void a(BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse) {
        HMSLog.i("BaseAdapter", "baseCallBack.onComplete");
        PendingIntent pendingIntent = coreBaseResponse.getPendingIntent();
        if (pendingIntent != null) {
            baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), pendingIntent);
            return;
        }
        Intent modifyIntentBehaviorsSafe = IntentUtil.modifyIntentBehaviorsSafe(coreBaseResponse.getIntent());
        if (modifyIntentBehaviorsSafe != null) {
            baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), modifyIntentBehaviorsSafe);
        } else {
            baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), null);
        }
    }

    public String b(int i2) {
        c(i2);
        return this.f1190k.toJson();
    }

    private void b(String str) {
        this.f1184e = str;
    }

    public boolean b(Intent intent, BaseCallBack baseCallBack) {
        if (intent.hasExtra(KpmsConstant.KIT_UPDATE_RESULT)) {
            int intExtra = intent.getIntExtra(KpmsConstant.KIT_UPDATE_RESULT, 0);
            HMSLog.i("BaseAdapter", "kit_update_result is " + intExtra);
            if (intExtra == 1) {
                HMSLog.e("BaseAdapter", "kit update success,replay request");
                h();
            } else {
                HMSLog.e("BaseAdapter", "kit update failed");
                baseCallBack.onError(a(-10, a(intExtra)).toJson());
            }
            return true;
        }
        return false;
    }

    public void a(Activity activity, Parcelable parcelable, CoreBaseResponse coreBaseResponse) {
        HMSLog.i("BaseAdapter", "startResolution");
        RequestHeader requestHeader = this.f1189j;
        if (requestHeader != null) {
            b(this.f1188i, requestHeader);
        }
        if (this.f1191l == null) {
            g();
        }
        SystemManager.getSystemNotifier().registerObserver(this.f1191l);
        if (Build.VERSION.SDK_INT >= 29) {
            a(coreBaseResponse);
        }
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, BaseResolutionAdapter.class.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable(CommonCode.MapKey.HAS_RESOLUTION, parcelable);
        intentStartBridgeActivity.putExtras(bundle);
        intentStartBridgeActivity.putExtra(CommonCode.MapKey.TRANSACTION_ID, this.f1187h);
        activity.startActivity(intentStartBridgeActivity);
    }

    public void c(Intent intent, BaseCallBack baseCallBack) {
        long j2;
        String stringExtra = intent.getStringExtra(CommonCode.MapKey.JSON_HEADER);
        String stringExtra2 = intent.getStringExtra(CommonCode.MapKey.JSON_BODY);
        Object infoFromJsonobject = JsonUtil.getInfoFromJsonobject(stringExtra, "status_code");
        Object infoFromJsonobject2 = JsonUtil.getInfoFromJsonobject(stringExtra, "error_code");
        if (intent.hasExtra(CommonCode.MapKey.HMS_FOREGROUND_RES_UI)) {
            Object infoFromJsonobject3 = JsonUtil.getInfoFromJsonobject(intent.getStringExtra(CommonCode.MapKey.HMS_FOREGROUND_RES_UI), "uiDuration");
            if (infoFromJsonobject3 instanceof Long) {
                j2 = ((Long) infoFromJsonobject3).longValue();
                if (!(infoFromJsonobject instanceof Integer) && (infoFromJsonobject2 instanceof Integer)) {
                    int intValue = ((Integer) infoFromJsonobject).intValue();
                    b(((Integer) infoFromJsonobject2).intValue());
                    this.f1190k.setStatusCode(intValue);
                    a(this.f1188i, this.f1190k, j2);
                } else {
                    b(-8);
                    a(this.f1188i, this.f1190k, j2);
                }
                baseCallBack.onComplete(stringExtra, stringExtra2, null);
            }
        }
        j2 = 0;
        if (!(infoFromJsonobject instanceof Integer)) {
        }
        b(-8);
        a(this.f1188i, this.f1190k, j2);
        baseCallBack.onComplete(stringExtra, stringExtra2, null);
    }

    private void a(final CoreBaseResponse coreBaseResponse) {
        HMSLog.i("BaseAdapter", "postResolutionTimeoutHandle");
        ApkResolutionFailedManager.getInstance().postTask(this.f1187h, new Runnable() { // from class: com.huawei.hms.adapter.BaseAdapter.1
            {
                BaseAdapter.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                HMSLog.i("BaseAdapter", "postResolutionTimeoutHandle handle");
                SystemManager.getSystemNotifier().unRegisterObserver(BaseAdapter.this.f1191l);
                ApkResolutionFailedManager.getInstance().removeValueOnly(BaseAdapter.this.f1187h);
                BaseCallBack b = BaseAdapter.this.b();
                if (b == null) {
                    HMSLog.e("BaseAdapter", "timeoutRunnable callBack is null");
                } else {
                    BaseAdapter.this.a(b, coreBaseResponse);
                }
            }
        });
    }

    private void a(Context context, RequestHeader requestHeader) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader);
        mapFromRequestHeader.put("direction", HiAnalyticsConstant.Direction.REQUEST);
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_CALL_AIDL, mapFromRequestHeader);
    }

    public void a(Context context, ResponseHeader responseHeader) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put("direction", HiAnalyticsConstant.Direction.RESPONSE);
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f1189j.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_CALL_AIDL, mapFromRequestHeader);
    }

    public void a(Context context, ResponseHeader responseHeader, long j2) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put("direction", HiAnalyticsConstant.Direction.RESPONSE);
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_WAITTIME, String.valueOf(j2));
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f1189j.getKitSdkVersion())));
        mapFromRequestHeader.put(HiAnalyticsConstant.HaKey.BI_KEY_PHONETYPE, Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, HiAnalyticsConstant.HMS_SDK_BASE_START_RESOLUTION, mapFromRequestHeader);
    }

    public ResponseWrap a(int i2, String str) {
        c(i2);
        ResponseWrap responseWrap = new ResponseWrap(this.f1190k);
        responseWrap.setBody(str);
        return responseWrap;
    }

    public String a(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i2);
        } catch (JSONException e2) {
            HMSLog.e("BaseAdapter", "buildBodyStr failed: " + e2.getMessage());
        }
        return jSONObject.toString();
    }

    private void a(String str) {
        this.d = str;
    }

    private void a(Parcelable parcelable) {
        this.f1185f = parcelable;
    }

    private BaseCallBack a() {
        return this.f1186g;
    }

    private void a(BaseCallBack baseCallBack) {
        this.f1186g = baseCallBack;
    }

    private void a(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        a(str);
        b(str2);
        a(parcelable);
        a(baseCallBack);
    }

    public boolean a(Intent intent, BaseCallBack baseCallBack) {
        if (intent.hasExtra(CommonCode.MapKey.PRIVACY_STATEMENT_CONFIRM_RESULT)) {
            int intExtra = intent.getIntExtra(CommonCode.MapKey.PRIVACY_STATEMENT_CONFIRM_RESULT, 1001);
            if (intExtra == 1001) {
                HMSLog.i("BaseAdapter", "privacy_statement_confirm_result agreed: " + intExtra + ", replay request");
                h();
                return true;
            }
            HMSLog.i("BaseAdapter", "privacy_statement_confirm_result rejected: " + intExtra);
            baseCallBack.onError(a(CommonCode.BusInterceptor.PRIVACY_CNCEL_ERROR_CODE, a(CommonCode.BusInterceptor.PRIVACY_CNCEL_ERROR_CODE)).toJson());
            return true;
        }
        return false;
    }
}
