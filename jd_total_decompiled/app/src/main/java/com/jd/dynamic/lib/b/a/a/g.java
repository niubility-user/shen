package com.jd.dynamic.lib.b.a.a;

import android.app.Activity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.RequestEntity;
import com.jd.dynamic.lib.b.a.a.g;
import java.util.Map;
import org.json.JSONObject;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class g extends e1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements INetWorkRequest.ResponseCallBack {
        final /* synthetic */ DynamicTemplateEngine a;
        final /* synthetic */ JSONObject b;

        a(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
            this.a = dynamicTemplateEngine;
            this.b = jSONObject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(DynamicTemplateEngine dynamicTemplateEngine, String str) {
            com.jd.dynamic.lib.utils.s.b(str, null, dynamicTemplateEngine, g.this.mTargetView);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(Throwable th) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(JSONObject jSONObject, DynamicTemplateEngine dynamicTemplateEngine, String str) {
            com.jd.dynamic.lib.utils.s.b(str, jSONObject, dynamicTemplateEngine, g.this.mTargetView);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void d(Throwable th) {
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onError(INetWorkRequest.ErrorResponse errorResponse) {
            Observable from = Observable.from(com.jd.dynamic.lib.utils.s.i(this.b.optString("error")));
            final DynamicTemplateEngine dynamicTemplateEngine = this.a;
            from.forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.z
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    g.a.this.a(dynamicTemplateEngine, (String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.a0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    g.a.b((Throwable) obj);
                }
            });
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onStart() {
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onSuccess(final JSONObject jSONObject) {
            String optString = this.b.optString("success");
            if (com.jd.dynamic.b.a.b.o().e()) {
                g.this.addObjCache(this.b.optString("cacheKey"), jSONObject);
            } else {
                g.this.addCache(this.b.optString("cacheKey"), jSONObject.toString());
            }
            Observable from = Observable.from(com.jd.dynamic.lib.utils.s.i(optString));
            final DynamicTemplateEngine dynamicTemplateEngine = this.a;
            from.forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.x
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    g.a.this.c(jSONObject, dynamicTemplateEngine, (String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.y
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    g.a.d((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements INetWorkRequest.ResponseCallBack {
        final /* synthetic */ com.jd.dynamic.a.f a;
        final /* synthetic */ com.jd.dynamic.a.f b;

        b(g gVar, DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject, com.jd.dynamic.a.f fVar, com.jd.dynamic.a.f fVar2) {
            this.a = fVar;
            this.b = fVar2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(INetWorkRequest.ErrorResponse errorResponse, com.jd.dynamic.a.f fVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("message", errorResponse.toString());
                if (fVar != null) {
                    fVar.a(jSONObject);
                    fVar.q();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(com.jd.dynamic.a.f fVar, JSONObject jSONObject) {
            if (fVar != null) {
                try {
                    fVar.a(jSONObject);
                    fVar.q();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onError(final INetWorkRequest.ErrorResponse errorResponse) {
            Scheduler.Worker createWorker = AndroidSchedulers.mainThread().createWorker();
            final com.jd.dynamic.a.f fVar = this.b;
            createWorker.schedule(new Action0() { // from class: com.jd.dynamic.lib.b.a.a.b0
                @Override // rx.functions.Action0
                public final void call() {
                    g.b.a(INetWorkRequest.ErrorResponse.this, fVar);
                }
            });
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onStart() {
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onSuccess(final JSONObject jSONObject) {
            Scheduler.Worker createWorker = AndroidSchedulers.mainThread().createWorker();
            final com.jd.dynamic.a.f fVar = this.a;
            createWorker.schedule(new Action0() { // from class: com.jd.dynamic.lib.b.a.a.c0
                @Override // rx.functions.Action0
                public final void call() {
                    g.b.b(com.jd.dynamic.a.f.this, jSONObject);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class c implements INetWorkRequest.ResponseCallBack {
        final /* synthetic */ com.jd.dynamic.a.f a;
        final /* synthetic */ com.jd.dynamic.a.f b;

        c(g gVar, com.jd.dynamic.a.f fVar, com.jd.dynamic.a.f fVar2) {
            this.a = fVar;
            this.b = fVar2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(INetWorkRequest.ErrorResponse errorResponse, com.jd.dynamic.a.f fVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("message", errorResponse.toString());
                if (fVar != null) {
                    fVar.a(jSONObject);
                    fVar.q();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(com.jd.dynamic.a.f fVar, JSONObject jSONObject) {
            if (fVar != null) {
                try {
                    fVar.a(jSONObject);
                    fVar.q();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onError(final INetWorkRequest.ErrorResponse errorResponse) {
            Scheduler.Worker createWorker = AndroidSchedulers.mainThread().createWorker();
            final com.jd.dynamic.a.f fVar = this.b;
            createWorker.schedule(new Action0() { // from class: com.jd.dynamic.lib.b.a.a.d0
                @Override // rx.functions.Action0
                public final void call() {
                    g.c.a(INetWorkRequest.ErrorResponse.this, fVar);
                }
            });
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onStart() {
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
        public void onSuccess(final JSONObject jSONObject) {
            Scheduler.Worker createWorker = AndroidSchedulers.mainThread().createWorker();
            final com.jd.dynamic.a.f fVar = this.a;
            createWorker.schedule(new Action0() { // from class: com.jd.dynamic.lib.b.a.a.e0
                @Override // rx.functions.Action0
                public final void call() {
                    g.c.b(com.jd.dynamic.a.f.this, jSONObject);
                }
            });
        }
    }

    private void b(RequestEntity requestEntity, JSONObject jSONObject) {
        if (requestEntity == null || jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.has("header") && !TextUtils.isEmpty(jSONObject.optString("header"))) {
                requestEntity.setHeaders((Map) new Gson().fromJson(jSONObject.optString("header"), new TypeToken<Map<String, Object>>(this) { // from class: com.jd.dynamic.lib.b.a.a.g.3
                }.getType()));
            }
        } catch (Exception unused) {
        }
        if (jSONObject.has("url") && !TextUtils.isEmpty(jSONObject.optString("url"))) {
            requestEntity.setUrl(jSONObject.optString("url"));
        }
        if (jSONObject.has("functionId") && !TextUtils.isEmpty(jSONObject.optString("functionId"))) {
            requestEntity.setFunctionId(jSONObject.optString("functionId"));
        }
        if (jSONObject.has("server") && !TextUtils.isEmpty(jSONObject.optString("server"))) {
            requestEntity.setHost(jSONObject.optString("server"));
        }
        if (jSONObject.has("method") && !TextUtils.isEmpty(jSONObject.optString("method"))) {
            requestEntity.setMethod(jSONObject.optString("method"));
        }
        if (jSONObject.has("body") && !TextUtils.isEmpty(jSONObject.optString("body"))) {
            requestEntity.setBody(jSONObject.optString("body"));
        }
        if (jSONObject.has("businessType") && !TextUtils.isEmpty(jSONObject.optString("businessType"))) {
            requestEntity.setBusinessType(jSONObject.optString("businessType"));
        }
        if (!jSONObject.has(DYConstants.DY_REQUEST_SHOWLADING) || TextUtils.isEmpty(jSONObject.optString(DYConstants.DY_REQUEST_SHOWLADING))) {
            return;
        }
        requestEntity.setShowLoading(jSONObject.optString(DYConstants.DY_REQUEST_SHOWLADING));
    }

    public Object a(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject, com.jd.dynamic.a.f fVar, com.jd.dynamic.a.f fVar2) {
        if (jSONObject != null && DynamicSdk.getEngine().getRequest() != null) {
            Activity activity = dynamicTemplateEngine != null ? dynamicTemplateEngine.getActivity() : null;
            RequestEntity requestEntity = new RequestEntity();
            b(requestEntity, jSONObject);
            DynamicSdk.getEngine().getRequest().requestWithFunctionId(activity, requestEntity, new b(this, dynamicTemplateEngine, jSONObject, fVar, fVar2));
        }
        return null;
    }

    public Object e(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject, com.jd.dynamic.a.f fVar, com.jd.dynamic.a.f fVar2) {
        if (jSONObject != null && DynamicSdk.getEngine().getRequest() != null) {
            Activity activity = dynamicTemplateEngine != null ? dynamicTemplateEngine.getActivity() : null;
            RequestEntity requestEntity = new RequestEntity();
            b(requestEntity, jSONObject);
            DynamicSdk.getEngine().getRequest().sendRequest(activity, requestEntity, new c(this, fVar, fVar2));
        }
        return null;
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        if (jSONObject != null && DynamicSdk.getEngine().getRequest() != null) {
            Activity activity = dynamicTemplateEngine != null ? dynamicTemplateEngine.getActivity() : null;
            RequestEntity requestEntity = new RequestEntity();
            b(requestEntity, jSONObject);
            DynamicSdk.getEngine().getRequest().requestWithFunctionId(activity, requestEntity, new a(dynamicTemplateEngine, jSONObject));
        }
        return null;
    }
}
