package com.huawei.hms.common;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.Api.ApiOptions;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.BindResolveClients;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.RequestManager;
import com.huawei.hms.common.internal.ResolveClientBean;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.common.internal.TaskApiCallWrapper;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.core.aidl.IAIDLInvoke;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.hianalytics.HiAnalyticsInnerClient;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSBIInitializer;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import g.e.c.a.f;
import g.e.c.a.g;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class HuaweiApi<TOption extends Api.ApiOptions> {
    private Context a;
    private TOption b;

    /* renamed from: c */
    private Context f1236c;
    private AbstractClientBuilder<?, TOption> d;

    /* renamed from: e */
    private String f1237e;

    /* renamed from: f */
    private String f1238f;

    /* renamed from: g */
    private SubAppInfo f1239g;

    /* renamed from: h */
    private WeakReference<Activity> f1240h;

    /* renamed from: i */
    private int f1241i;

    /* renamed from: j */
    private int f1242j = 1;

    /* renamed from: k */
    private boolean f1243k = false;

    /* renamed from: l */
    private String f1244l;

    /* renamed from: m */
    private boolean f1245m;

    /* renamed from: n */
    private RequestManager f1246n;

    /* loaded from: classes12.dex */
    public static class RequestHandler<OptionsT extends Api.ApiOptions> implements BaseHmsClient.ConnectionCallbacks, BaseHmsClient.OnConnectionFailedListener {
        private final AnyClient b;
        private final HuaweiApi<OptionsT> d;

        /* renamed from: e */
        private ResolveClientBean f1249e;
        public final Queue<TaskApiCallbackWrapper> callbackWaitQueue = new LinkedList();
        private final Queue<TaskApiCallbackWrapper> a = new LinkedList();

        /* renamed from: c */
        private ConnectionResult f1248c = null;

        RequestHandler(HuaweiApi<OptionsT> huaweiApi) {
            this.d = huaweiApi;
            this.b = huaweiApi.getClient(RequestManager.getHandler().getLooper(), this);
        }

        public AnyClient getClient() {
            return this.b;
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnected() {
            HMSLog.i("HuaweiApi", "onConnected");
            BindResolveClients.getInstance().unRegister(this.f1249e);
            this.f1249e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.4
                {
                    RequestHandler.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.b();
                }
            });
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.OnConnectionFailedListener
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            HMSLog.i("HuaweiApi", "onConnectionFailed");
            BindResolveClients.getInstance().unRegister(this.f1249e);
            this.f1249e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.3
                {
                    RequestHandler.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.b(connectionResult);
                }
            });
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnectionSuspended(int i2) {
            HMSLog.i("HuaweiApi", "onConnectionSuspended");
            BindResolveClients.getInstance().unRegister(this.f1249e);
            this.f1249e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.5
                {
                    RequestHandler.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.c();
                }
            });
        }

        public void postMessage(final TaskApiCallbackWrapper taskApiCallbackWrapper) {
            RequestManager.addToConnectedReqMap(taskApiCallbackWrapper.a().getTaskApiCall().getTransactionId(), this);
            this.a.add(taskApiCallbackWrapper);
            String uri = taskApiCallbackWrapper.a().getTaskApiCall().getUri();
            String packageName = (((HuaweiApi) this.d).f1236c == null ? this.d.getContext() : ((HuaweiApi) this.d).f1236c).getPackageName();
            if (((HuaweiApi) this.d).f1236c != null) {
                HuaweiApi<OptionsT> huaweiApi = this.d;
                huaweiApi.b(((HuaweiApi) huaweiApi).f1236c);
            }
            final RequestHeader requestHeader = new RequestHeader();
            requestHeader.setSrvName(uri.split("\\.")[0]);
            requestHeader.setApiName(uri);
            requestHeader.setAppID(this.d.getAppID() + "|" + this.d.getSubAppID());
            requestHeader.setPkgName(packageName);
            requestHeader.setSessionId(this.b.getSessionId());
            TaskApiCall taskApiCall = taskApiCallbackWrapper.a().getTaskApiCall();
            requestHeader.setTransactionId(a(taskApiCall.getTransactionId(), uri));
            requestHeader.setParcelable(taskApiCall.getParcelable());
            requestHeader.setKitSdkVersion(this.d.getKitSdkVersion());
            requestHeader.setApiLevel(Math.max(this.d.getApiLevel(), taskApiCall.getApiLevel()));
            this.b.post(requestHeader, taskApiCall.getRequestJson(), new AnyClient.CallBack() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.2
                {
                    RequestHandler.this = this;
                }

                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    AnyClient.CallBack b = taskApiCallbackWrapper.b();
                    if (b != null) {
                        b.onCallback(iMessageEntity, str);
                    }
                    RequestManager.removeReqByTransId(requestHeader.getTransactionId());
                    RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.2.1
                        {
                            AnonymousClass2.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            RequestHandler.this.a.remove(taskApiCallbackWrapper);
                        }
                    });
                }
            });
        }

        private TaskApiCallbackWrapper b(final TaskApiCallWrapper taskApiCallWrapper) {
            return new TaskApiCallbackWrapper(taskApiCallWrapper, new AnyClient.CallBack() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.1
                private AtomicBoolean a = new AtomicBoolean(true);

                {
                    RequestHandler.this = this;
                }

                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    if (!(iMessageEntity instanceof ResponseHeader)) {
                        HMSLog.e("HuaweiApi", "header is not instance of ResponseHeader");
                        return;
                    }
                    ResponseHeader responseHeader = (ResponseHeader) iMessageEntity;
                    if (responseHeader.getErrorCode() == 11) {
                        RequestHandler.this.a();
                        HMSLog.i("HuaweiApi", "unbind service");
                    }
                    if (!TextUtils.isEmpty(responseHeader.getResolution())) {
                        HMSLog.e("HuaweiApi", "Response has resolution: " + responseHeader.getResolution());
                    }
                    if (this.a.compareAndSet(true, false)) {
                        HiAnalyticsInnerClient.reportEntryExit(RequestHandler.this.d.getContext(), responseHeader, String.valueOf(RequestHandler.this.d.getKitSdkVersion()));
                    }
                    taskApiCallWrapper.getTaskApiCall().onResponse(RequestHandler.this.b, responseHeader, str, taskApiCallWrapper.getTaskCompletionSource());
                }
            });
        }

        public void c() {
            HMSLog.i("HuaweiApi", "wait queue size = " + this.callbackWaitQueue.size());
            HMSLog.i("HuaweiApi", "run queue size = " + this.a.size());
            Iterator<TaskApiCallbackWrapper> it = this.callbackWaitQueue.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Iterator<TaskApiCallbackWrapper> it2 = this.a.iterator();
            while (it2.hasNext()) {
                a(it2.next());
            }
            this.callbackWaitQueue.clear();
            this.a.clear();
            this.f1248c = null;
            this.b.disconnect();
        }

        public void b(ConnectionResult connectionResult) {
            this.f1248c = connectionResult;
            Iterator<TaskApiCallbackWrapper> it = this.callbackWaitQueue.iterator();
            boolean z = true;
            while (it.hasNext()) {
                TaskApiCallWrapper a = it.next().a();
                ResponseHeader responseHeader = new ResponseHeader(1, CommonCode.ErrorCode.CLIENT_API_INVALID, "Connection Failed:" + a(connectionResult) + "(" + connectionResult.getErrorCode() + ")");
                responseHeader.setTransactionId(a.getTaskApiCall().getTransactionId());
                HiAnalyticsInnerClient.reportEntryExit(this.d.getContext(), responseHeader, String.valueOf(this.d.getKitSdkVersion()));
                if (this.f1248c.getResolution() != null && z) {
                    responseHeader.setParcelable(this.f1248c.getResolution());
                    z = false;
                    if (Util.isAvailableLibExist(this.d.getContext()) && this.f1248c.getErrorCode() == 26) {
                        responseHeader.setResolution(CommonCode.Resolution.HAS_RESOLUTION);
                    }
                }
                int errorCode = this.f1248c.getErrorCode();
                if (errorCode == 30 || errorCode == 31) {
                    responseHeader.setErrorCode(errorCode);
                }
                a.getTaskApiCall().onResponse(this.b, responseHeader, null, a.getTaskCompletionSource());
            }
            this.callbackWaitQueue.clear();
            this.a.clear();
            this.f1248c = null;
            this.b.disconnect();
        }

        void a(TaskApiCallWrapper taskApiCallWrapper) {
            HMSLog.i("HuaweiApi", "sendRequest");
            TaskApiCallbackWrapper b = b(taskApiCallWrapper);
            int hmsVersionCode = HMSPackageManager.getInstance(((HuaweiApi) this.d).a).getHmsVersionCode();
            if ((hmsVersionCode < 40000000 && hmsVersionCode > 0) && this.b.isConnected() && !((HuaweiApi) this.d).f1245m && ((BaseHmsClient) this.b).getAdapter().getServiceAction().equals("com.huawei.hms.core.aidlservice")) {
                int requestHmsVersionCode = this.b.getRequestHmsVersionCode();
                if (requestHmsVersionCode <= taskApiCallWrapper.getTaskApiCall().getMinApkVersion()) {
                    requestHmsVersionCode = taskApiCallWrapper.getTaskApiCall().getMinApkVersion();
                }
                if (requestHmsVersionCode > hmsVersionCode) {
                    this.b.disconnect();
                }
            }
            if (this.b.isConnected()) {
                HMSLog.i("HuaweiApi", "isConnected:true.");
                BinderAdapter adapter = ((BaseHmsClient) this.b).getAdapter();
                adapter.updateDelayTask();
                ((HmsClient) this.b).setService(IAIDLInvoke.Stub.asInterface(adapter.getServiceBinder()));
                postMessage(b);
                return;
            }
            HMSLog.i("HuaweiApi", "isConnected:false.");
            this.callbackWaitQueue.add(b);
            ConnectionResult connectionResult = this.f1248c;
            if (connectionResult != null && connectionResult.getErrorCode() != 0) {
                HMSLog.i("HuaweiApi", "onConnectionFailed, ErrorCode:" + this.f1248c.getErrorCode());
                onConnectionFailed(this.f1248c);
                return;
            }
            RequestManager.addRequestToQueue(this);
            AnyClient anyClient = this.b;
            if (anyClient instanceof BaseHmsClient) {
                ((BaseHmsClient) anyClient).setInternalRequest(this);
            }
            a(taskApiCallWrapper.getTaskApiCall().getMinApkVersion(), b);
        }

        public void b() {
            this.f1248c = null;
            this.a.clear();
            Iterator<TaskApiCallbackWrapper> it = this.callbackWaitQueue.iterator();
            while (it.hasNext()) {
                postMessage(it.next());
            }
            this.callbackWaitQueue.clear();
        }

        private String a(String str, String str2) {
            return TextUtils.isEmpty(str) ? TransactionIdCreater.getId(this.d.getAppID(), str2) : str;
        }

        synchronized void a(int i2, TaskApiCallbackWrapper taskApiCallbackWrapper) {
            if (this.b.isConnected()) {
                HMSLog.d("HuaweiApi", "client is connected");
            } else if (this.b.isConnecting()) {
                HMSLog.d("HuaweiApi", "client is isConnecting");
            } else {
                if (this.d.getActivity() != null) {
                    if (this.f1249e == null) {
                        this.f1249e = new ResolveClientBean(this.b, i2);
                    }
                    if (BindResolveClients.getInstance().isClientRegistered(this.f1249e)) {
                        HMSLog.i("HuaweiApi", "mResolveClientBean has already register, return!");
                        return;
                    }
                    BindResolveClients.getInstance().register(this.f1249e);
                }
                this.b.connect(i2);
            }
        }

        void a() {
            this.b.disconnect();
        }

        private void a(TaskApiCallbackWrapper taskApiCallbackWrapper) {
            TaskApiCallWrapper a = taskApiCallbackWrapper.a();
            ResponseHeader responseHeader = new ResponseHeader(1, CommonCode.ErrorCode.CLIENT_API_INVALID, "Connection Suspended");
            responseHeader.setTransactionId(a.getTaskApiCall().getTransactionId());
            a.getTaskApiCall().onResponse(this.b, responseHeader, null, a.getTaskCompletionSource());
        }

        private String a(ConnectionResult connectionResult) {
            if (Util.isAvailableLibExist(this.d.getContext())) {
                int errorCode = connectionResult.getErrorCode();
                if (errorCode != -1) {
                    if (errorCode != 3) {
                        if (errorCode != 8) {
                            if (errorCode != 10) {
                                if (errorCode != 13) {
                                    if (errorCode != 21) {
                                        switch (errorCode) {
                                            case 25:
                                                return "failed to get update result";
                                            case 26:
                                                return "update failed, because no activity incoming, can't pop update page";
                                            case 27:
                                                return "there is already an update popup at the front desk, but it hasn't been clicked or it is not effective for a while";
                                            default:
                                                return "unknown errorReason";
                                        }
                                    }
                                    return "device is too old to be support";
                                }
                                return "update cancelled";
                            }
                            return "application configuration error, please developer check configuration";
                        }
                        return VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_INTERNAL_ERROR;
                    }
                    return "HuaWei Mobile Service is disabled";
                }
                return "get update result, but has other error codes";
            }
            int errorCode2 = connectionResult.getErrorCode();
            if (errorCode2 != -1) {
                if (errorCode2 != 8) {
                    if (errorCode2 != 10) {
                        return "unknown errorReason";
                    }
                    return "application configuration error, please developer check configuration";
                }
                return VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_INTERNAL_ERROR;
            }
            return "get update result, but has other error codes";
        }
    }

    /* loaded from: classes12.dex */
    public static class TaskApiCallbackWrapper {
        private final TaskApiCallWrapper a;
        private final AnyClient.CallBack b;

        TaskApiCallbackWrapper(TaskApiCallWrapper taskApiCallWrapper, AnyClient.CallBack callBack) {
            this.a = taskApiCallWrapper;
            this.b = callBack;
        }

        TaskApiCallWrapper a() {
            return this.a;
        }

        AnyClient.CallBack b() {
            return this.b;
        }
    }

    /* loaded from: classes12.dex */
    public static class a<OptionsT extends Api.ApiOptions> implements Runnable {
        private final HuaweiApi<OptionsT> a;
        private final TaskApiCallWrapper b;

        public a(HuaweiApi<OptionsT> huaweiApi, TaskApiCallWrapper taskApiCallWrapper) {
            this.a = huaweiApi;
            this.b = taskApiCallWrapper;
        }

        /* JADX WARN: Removed duplicated region for block: B:108:0x0056 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:116:0x0066  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void a(RequestHandler requestHandler, Throwable th) {
            Throwable th2;
            AnyClient anyClient;
            String str;
            ResponseHeader responseHeader;
            g gVar;
            TaskApiCall taskApiCall = null;
            try {
                anyClient = requestHandler.getClient();
            } catch (Throwable th3) {
                th2 = th3;
                anyClient = null;
            }
            try {
                responseHeader = new ResponseHeader(1, CommonCode.ErrorCode.INTERNAL_ERROR, th.getMessage());
                try {
                    str = new JSONObject().toString();
                    try {
                        gVar = this.b.getTaskCompletionSource();
                    } catch (Throwable th4) {
                        th2 = th4;
                        gVar = null;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    str = null;
                    gVar = null;
                }
                try {
                    taskApiCall = this.b.getTaskApiCall();
                } catch (Throwable th6) {
                    th2 = th6;
                    HMSLog.e("HuaweiApi", "<notifyCpException> " + th2.getMessage());
                    if ((anyClient != null || responseHeader == null || str == null || gVar == null || taskApiCall == null) ? false : true) {
                    }
                }
            } catch (Throwable th7) {
                th2 = th7;
                str = null;
                responseHeader = null;
                gVar = null;
                HMSLog.e("HuaweiApi", "<notifyCpException> " + th2.getMessage());
                if ((anyClient != null || responseHeader == null || str == null || gVar == null || taskApiCall == null) ? false : true) {
                }
            }
            if ((anyClient != null || responseHeader == null || str == null || gVar == null || taskApiCall == null) ? false : true) {
                taskApiCall.onResponse(anyClient, responseHeader, str, gVar);
            } else {
                HMSLog.e("HuaweiApi", "<notifyCpException> isNotify is false, Can not notify CP.");
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            RequestHandler requestHandler = new RequestHandler(this.a);
            try {
                requestHandler.a(this.b);
            } catch (Throwable th) {
                a(requestHandler, th);
            }
        }
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i2) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.f1240h = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, i2, null);
    }

    @Deprecated
    public f<Boolean> disconnectService() {
        final g gVar = new g();
        RequestManager.getInstance();
        RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.1
            {
                HuaweiApi.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                HuaweiApi.this.a(this, gVar);
            }
        });
        return gVar.b();
    }

    public <TResult, TClient extends AnyClient> f<TResult> doWrite(TaskApiCall<TClient, TResult> taskApiCall) {
        this.f1243k = true;
        if (taskApiCall == null) {
            HMSLog.e("HuaweiApi", "in doWrite:taskApiCall is null");
            g gVar = new g();
            gVar.c(new ApiException(Status.FAILURE));
            return gVar.b();
        }
        HiAnalyticsInnerClient.reportEntryClient(this.a, taskApiCall.getUri(), TextUtils.isEmpty(this.f1239g.getSubAppID()) ? this.f1238f : this.f1239g.getSubAppID(), taskApiCall.getTransactionId(), String.valueOf(getKitSdkVersion()));
        if (this.f1246n == null) {
            this.f1246n = RequestManager.getInstance();
        }
        return a(taskApiCall);
    }

    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.f1240h;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getApiLevel() {
        return this.f1242j;
    }

    public String getAppID() {
        return this.f1238f;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.huawei.hms.common.internal.AnyClient] */
    public AnyClient getClient(Looper looper, RequestHandler requestHandler) {
        return this.d.buildClient(this.a, getClientSetting(), requestHandler, requestHandler);
    }

    protected ClientSettings getClientSetting() {
        ClientSettings clientSettings = new ClientSettings(this.a.getPackageName(), this.a.getClass().getName(), getScopes(), this.f1237e, null, this.f1239g);
        if (!this.f1245m) {
            this.f1244l = HMSPackageManager.getInstance(this.a).getHMSPackageNameForMultiService();
            HMSLog.i("HuaweiApi", "No setInnerHms, hms pkg name is " + this.f1244l);
        }
        clientSettings.setInnerHmsPkg(this.f1244l);
        clientSettings.setUseInnerHms(this.f1245m);
        WeakReference<Activity> weakReference = this.f1240h;
        if (weakReference != null) {
            clientSettings.setCpActivity(weakReference.get());
        }
        return clientSettings;
    }

    public Context getContext() {
        return this.a;
    }

    public int getKitSdkVersion() {
        return this.f1241i;
    }

    public TOption getOption() {
        return this.b;
    }

    protected List<Scope> getScopes() {
        return Collections.emptyList();
    }

    public String getSubAppID() {
        return this.f1239g.getSubAppID();
    }

    public void setApiLevel(int i2) {
        this.f1242j = i2;
    }

    public void setHostContext(Context context) {
        this.f1236c = context;
    }

    public void setInnerHms() {
        this.f1244l = this.a.getPackageName();
        this.f1245m = true;
        HMSLog.i("HuaweiApi", "<setInnerHms> init inner hms pkg info:" + this.f1244l);
    }

    public void setKitSdkVersion(int i2) {
        this.f1241i = i2;
    }

    public void setSubAppId(String str) throws ApiException {
        if (!setSubAppInfo(new SubAppInfo(str))) {
            throw new ApiException(Status.FAILURE);
        }
    }

    @Deprecated
    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        HMSLog.i("HuaweiApi", "Enter setSubAppInfo");
        SubAppInfo subAppInfo2 = this.f1239g;
        if (subAppInfo2 != null && !TextUtils.isEmpty(subAppInfo2.getSubAppID())) {
            HMSLog.e("HuaweiApi", "subAppInfo is already set");
            return false;
        } else if (subAppInfo == null) {
            HMSLog.e("HuaweiApi", "subAppInfo is null");
            return false;
        } else {
            String subAppID = subAppInfo.getSubAppID();
            if (TextUtils.isEmpty(subAppID)) {
                HMSLog.e("HuaweiApi", "subAppId is empty");
                return false;
            } else if (subAppID.equals(this.f1237e)) {
                HMSLog.e("HuaweiApi", "subAppId is host appid");
                return false;
            } else if (this.f1243k) {
                HMSLog.e("HuaweiApi", "Client has sent request to Huawei Mobile Services, setting subAppId is not allowed");
                return false;
            } else {
                this.f1239g = new SubAppInfo(subAppInfo);
                return true;
            }
        }
    }

    public void b(Context context) {
        String appId = Util.getAppId(context);
        this.f1237e = appId;
        this.f1238f = appId;
    }

    private void a(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i2, String str) {
        this.a = context.getApplicationContext();
        this.b = toption;
        this.d = abstractClientBuilder;
        b(context);
        this.f1239g = new SubAppInfo("");
        this.f1241i = i2;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(this.f1237e)) {
                HMSLog.e("HuaweiApi", "subAppId is host appid");
            } else {
                HMSLog.i("HuaweiApi", "subAppId is " + str);
                this.f1239g = new SubAppInfo(str);
            }
        }
        a(context);
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i2, String str) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.f1240h = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, i2, str);
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.f1240h = new WeakReference<>(activity);
        a(activity, api, toption, abstractClientBuilder, 0, null);
    }

    private void a(Context context) {
        HMSBIInitializer.getInstance(context).initBI();
    }

    public void a(HuaweiApi<?> huaweiApi, g<Boolean> gVar) {
        HMSLog.i("HuaweiApi", "innerDisconnect.");
        try {
            huaweiApi.getClient(RequestManager.getHandler().getLooper(), null).disconnect();
            gVar.d(Boolean.TRUE);
        } catch (Exception e2) {
            HMSLog.w("HuaweiApi", "disconnect the binder failed for:" + e2.getMessage());
        }
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i2) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, i2, null);
    }

    private <TResult, TClient extends AnyClient> f<TResult> a(TaskApiCall<TClient, TResult> taskApiCall) {
        g gVar;
        if (taskApiCall.getToken() == null) {
            gVar = new g();
        } else {
            gVar = new g(taskApiCall.getToken());
        }
        RequestManager.getHandler().post(new a(this, new TaskApiCallWrapper(taskApiCall, gVar)));
        return gVar.b();
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i2, String str) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, i2, str);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        a(context, api, toption, abstractClientBuilder, 0, null);
    }
}
