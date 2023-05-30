package com.jd.dynamic.lib.b.a.a;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwner;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IAppRouter;
import com.jd.dynamic.base.interfaces.IRouterCallBackListener;
import com.jd.dynamic.lib.b.a.a.m1;
import com.jd.dynamic.lib.views.CollectionView;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class m1 extends e1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements IRouterCallBackListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ DynamicTemplateEngine f2172c;

        a(String str, String str2, DynamicTemplateEngine dynamicTemplateEngine) {
            this.a = str;
            this.b = str2;
            this.f2172c = dynamicTemplateEngine;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(DynamicTemplateEngine dynamicTemplateEngine, String str) {
            View view = m1.this.mTargetView;
            com.jd.dynamic.lib.utils.s.b(str, view, dynamicTemplateEngine, view);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(Throwable th) {
        }

        @Override // com.jd.dynamic.base.interfaces.IRouterCallBackListener
        public void onComplete(Object obj) {
            com.jd.dynamic.lib.utils.t.e("UtilFunction", "router onComplete " + obj);
            if (obj != null) {
                if (com.jd.dynamic.b.a.b.o().e()) {
                    m1.this.addObjCache(this.a, obj);
                } else {
                    m1.this.addCache(this.a, obj.toString());
                }
                if (TextUtils.isEmpty(this.b)) {
                    return;
                }
                Observable from = Observable.from(com.jd.dynamic.lib.utils.s.i(this.b));
                final DynamicTemplateEngine dynamicTemplateEngine = this.f2172c;
                from.forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.j0
                    @Override // rx.functions.Action1
                    public final void call(Object obj2) {
                        m1.a.this.a(dynamicTemplateEngine, (String) obj2);
                    }
                }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.i0
                    @Override // rx.functions.Action1
                    public final void call(Object obj2) {
                        m1.a.b((Throwable) obj2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements IRouterCallBackListener {
        final /* synthetic */ com.jd.dynamic.a.f a;

        b(m1 m1Var, com.jd.dynamic.a.f fVar) {
            this.a = fVar;
        }

        @Override // com.jd.dynamic.base.interfaces.IRouterCallBackListener
        public void onComplete(Object obj) {
            com.jd.dynamic.a.f fVar = this.a;
            if (fVar != null) {
                fVar.a(obj);
                this.a.q();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(JSONObject jSONObject) {
        Toast.makeText(this.mEngine.getActivity(), jSONObject.optString("msg"), 0).show();
    }

    private void b() {
        Activity activity = this.mEngine.getActivity();
        if (activity != null) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) this.mEngine.getActivity().getApplication().getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) this.mEngine.getActivity().getApplication().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, null, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(Throwable th) {
    }

    private void h(JSONObject jSONObject) {
        Object opt;
        String optString = jSONObject.optString(Constants.PARAM_SCOPE);
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(CartConstant.KEY_ITEMS);
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                Object obj = jSONArray.get(i2);
                if (obj instanceof JSONObject) {
                    String optString2 = ((JSONObject) obj).optString("key");
                    if (!TextUtils.isEmpty(optString2) && (opt = ((JSONObject) obj).opt("value")) != null) {
                        com.jd.dynamic.b.m.d.a.b.c(optString, optString2, opt);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(final JSONObject jSONObject, View view) {
        Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("bottomClick"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.u0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m1.this.j(jSONObject, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.p0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m1.g((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(JSONObject jSONObject, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONObject, this.mEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, null, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void l(Throwable th) {
    }

    private void m(JSONObject jSONObject) {
        String optString = jSONObject.optString(Constants.PARAM_SCOPE);
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        String optString2 = jSONObject.optString("key");
        if (TextUtils.isEmpty(optString2)) {
            com.jd.dynamic.b.m.d.a.b.b(optString);
        } else {
            com.jd.dynamic.b.m.d.a.b.d(optString, optString2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(final JSONObject jSONObject, View view) {
        Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("cancelCallback"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.r0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m1.this.o(jSONObject, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.q0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m1.l((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(JSONObject jSONObject, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONObject, this.mEngine, this.mTargetView);
    }

    private Dialog p(final JSONObject jSONObject) {
        IAppRouter appRouter = DynamicSdk.getEngine().getAppRouter();
        if (appRouter != null) {
            IAppRouter.DialogConfig dialogConfig = new IAppRouter.DialogConfig();
            dialogConfig.titleText = jSONObject.optString("title");
            dialogConfig.contentText = jSONObject.optString("content");
            dialogConfig.cancelText = jSONObject.optString("cancel");
            dialogConfig.confirmText = jSONObject.optString("confirm");
            dialogConfig.onConfirmClick = new View.OnClickListener() { // from class: com.jd.dynamic.lib.b.a.a.t0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    m1.this.r(jSONObject, view);
                }
            };
            dialogConfig.onCancelClick = new View.OnClickListener() { // from class: com.jd.dynamic.lib.b.a.a.k0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    m1.this.n(jSONObject, view);
                }
            };
            dialogConfig.systemCode = jSONObject.optString(DYConstants.DYN_PRV_SYSCODE_KEY);
            dialogConfig.bizField = jSONObject.optString("bizField");
            try {
                dialogConfig.businessData = new JSONObject(jSONObject.optString("businessData"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (jSONObject.has("canCanceledOnTouchOutside")) {
                dialogConfig.canCanceledOnTouchOutside = jSONObject.optBoolean("canCanceledOnTouchOutside");
            }
            DynamicTemplateEngine dynamicTemplateEngine = this.mEngine;
            if (dynamicTemplateEngine != null) {
                dialogConfig.functionFactory = dynamicTemplateEngine.getCustomFactory();
            }
            return appRouter.showDialog(this.mEngine.getActivity(), dialogConfig);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(final JSONObject jSONObject, View view) {
        String optString = jSONObject.optString("confirmCallback");
        if (TextUtils.isEmpty(optString)) {
            optString = jSONObject.optString("success");
        }
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        Observable.from(com.jd.dynamic.lib.utils.s.i(optString)).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.y0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m1.this.s(jSONObject, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.s0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m1.q((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(JSONObject jSONObject, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONObject, this.mEngine, this.mTargetView);
    }

    private Dialog t(final JSONObject jSONObject) {
        IAppRouter appRouter = DynamicSdk.getEngine().getAppRouter();
        if (appRouter != null) {
            IAppRouter.PopViewConfig popViewConfig = new IAppRouter.PopViewConfig();
            popViewConfig.titleText = jSONObject.optString("title");
            popViewConfig.contentText = jSONObject.optString("content");
            popViewConfig.systemCode = jSONObject.optString(DYConstants.DYN_PRV_SYSCODE_KEY);
            popViewConfig.bizField = jSONObject.optString("bizField");
            try {
                popViewConfig.businessData = new JSONObject(jSONObject.optString("businessData"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            popViewConfig.direction = jSONObject.optString("direction");
            popViewConfig.duration = jSONObject.optInt("duration");
            popViewConfig.animEffect = jSONObject.optString("animEffect");
            if (jSONObject.has("bgTransparent")) {
                popViewConfig.bgTransparent = jSONObject.optBoolean("bgTransparent");
            }
            if (jSONObject.has("canCanceledOnTouchOutside")) {
                popViewConfig.canCanceledOnTouchOutside = jSONObject.optBoolean("canCanceledOnTouchOutside");
            }
            popViewConfig.bottom = jSONObject.optString("bottom");
            if (jSONObject.has("heightPercent")) {
                popViewConfig.heightPercent = (float) jSONObject.optDouble("heightPercent");
            }
            DynamicTemplateEngine dynamicTemplateEngine = this.mEngine;
            if (dynamicTemplateEngine != null) {
                popViewConfig.functionFactory = dynamicTemplateEngine.getCustomFactory();
            }
            popViewConfig.bottomClick = new View.OnClickListener() { // from class: com.jd.dynamic.lib.b.a.a.w0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    m1.this.i(jSONObject, view);
                }
            };
            return appRouter.showPopView(this.mEngine.getActivity(), popViewConfig);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void v(Throwable th) {
    }

    private void w(final JSONObject jSONObject) {
        String optString = jSONObject.optString("type");
        if (TextUtils.isEmpty(optString) || !TextUtils.equals(optString, "centerMsg")) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() { // from class: com.jd.dynamic.lib.b.a.a.m0
                @Override // rx.functions.Action0
                public final void call() {
                    m1.this.A(jSONObject);
                }
            });
            return;
        }
        IAppRouter appRouter = DynamicSdk.getEngine().getAppRouter();
        if (appRouter != null) {
            appRouter.showCustomToast(this.mEngine.getActivity(), jSONObject);
        }
    }

    private void x(JSONObject jSONObject) {
        String optString = jSONObject.optString("key");
        String optString2 = jSONObject.optString("value");
        if (this.mEngine != null) {
            if (com.jd.dynamic.b.a.b.o().e()) {
                this.mEngine.getCachePool().putObjData(optString, optString2);
            } else {
                this.mEngine.getCachePool().putData(optString, optString2);
            }
        }
    }

    private void y(JSONObject jSONObject) {
        try {
            final View pendingView = getPendingView(com.jd.dynamic.lib.dynamic.parser.h.a(this.mEngine, String.valueOf(jSONObject.optInt("layoutId"))));
            if (pendingView != null) {
                pendingView.postDelayed(new Runnable() { // from class: com.jd.dynamic.lib.b.a.a.v0
                    @Override // java.lang.Runnable
                    public final void run() {
                        m1.this.c(pendingView);
                    }
                }, 500L);
            }
        } catch (Exception unused) {
        }
    }

    private void z(JSONObject jSONObject) {
        DynamicTemplateEngine dynamicTemplateEngine = this.mEngine;
        if (dynamicTemplateEngine == null || dynamicTemplateEngine.currentData == null) {
            return;
        }
        int a2 = com.jd.dynamic.lib.dynamic.parser.h.a(this.mEngine, String.valueOf(jSONObject.optInt("layoutId")));
        View pendingView = getPendingView(a2);
        if ((pendingView instanceof CollectionView) && com.jd.dynamic.lib.utils.m.J(this.mEngine.getUnbindMap())) {
            com.jd.dynamic.lib.j.d dVar = new com.jd.dynamic.lib.j.d(this.mEngine, a2);
            dVar.execute(new Void[0]);
            if (pendingView.getContext() instanceof LifecycleOwner) {
                ((LifecycleOwner) pendingView.getContext()).getLifecycle().addObserver(dVar);
            }
        }
    }

    public Object a(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject, com.jd.dynamic.a.f fVar) {
        IAppRouter appRouter;
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine == null) {
            return null;
        }
        String str = (String) jSONObject.remove("fun");
        str.hashCode();
        if (str.equals("route")) {
            String optString = jSONObject.optString("url");
            com.jd.dynamic.lib.utils.t.e("Util", "js route", optString);
            if (!TextUtils.isEmpty(optString) && (appRouter = DynamicSdk.getEngine().getAppRouter()) != null) {
                appRouter.openJDRouter(optString, new b(this, fVar));
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:218:0x043b, code lost:
        if ((r0 instanceof com.jingdong.common.ui.JDDialog) == false) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0450, code lost:
        if (r17.mEngine != null) goto L259;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0562, code lost:
        if (r17.mEngine != null) goto L259;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x0564, code lost:
        r18.setDialog(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x057e, code lost:
        if ((r0 instanceof com.jingdong.common.ui.JDBottomDialog) == false) goto L281;
     */
    @Override // com.jd.dynamic.base.CommFunction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object exec(final com.jd.dynamic.base.DynamicTemplateEngine r18, org.json.JSONObject r19) {
        /*
            Method dump skipped, instructions count: 1628
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.b.a.a.m1.exec(com.jd.dynamic.base.DynamicTemplateEngine, org.json.JSONObject):java.lang.Object");
    }
}
