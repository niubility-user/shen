package com.jd.dynamic.lib.b.a.a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwner;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CachePool;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IAppRouter;
import com.jd.dynamic.base.interfaces.IRouterCallBackListener;
import com.jd.dynamic.lib.b.a.a.m1;
import com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend;
import com.jd.dynamic.lib.views.CollectionView;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.unification.dialog.UnBaseDialog;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.common.Constants;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
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
    */
    public Object exec(final DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        Dialog p;
        IAppRouter appRouter;
        IAppRouter appRouter2;
        CachePool cachePool;
        String obj;
        CachePool cachePool2;
        JSONArray jSONArray;
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine == null) {
            return null;
        }
        Activity activity = dynamicTemplateEngine.getActivity();
        String str = (String) jSONObject.remove("fun");
        str.hashCode();
        char c2 = '\uffff';
        int i2 = 0;
        switch (str.hashCode()) {
            case -1843426658:
                if (str.equals("hideSoftInput")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1814793473:
                if (str.equals("getIntentValue")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1588806437:
                if (str.equals("getMbaValue")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1421760852:
                if (str.equals("dismissPopView")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1332085432:
                if (str.equals(XView2Constants.XVIEW2_ACTION_DIALOG)) {
                    c2 = 4;
                    break;
                }
                break;
            case -1263222921:
                if (str.equals("openApp")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1263203643:
                if (str.equals("openUrl")) {
                    c2 = 6;
                    break;
                }
                break;
            case -1010580297:
                if (str.equals("openH5")) {
                    c2 = 7;
                    break;
                }
                break;
            case -691974485:
                if (str.equals("addCacheData")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -547159877:
                if (str.equals("addScopedCacheData")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -394866538:
                if (str.equals("popView")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -256832398:
                if (str.equals("dismissDialog")) {
                    c2 = 11;
                    break;
                }
                break;
            case 107332:
                if (str.equals("log")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 873657:
                if (str.equals("handData")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 108704329:
                if (str.equals("route")) {
                    c2 = 14;
                    break;
                }
                break;
            case 110532135:
                if (str.equals(XView2Constants.XVIEW2_ACTION_TOAST)) {
                    c2 = 15;
                    break;
                }
                break;
            case 243355426:
                if (str.equals("getResultIntentValue")) {
                    c2 = 16;
                    break;
                }
                break;
            case 357530693:
                if (str.equals("clearScopedCache")) {
                    c2 = 17;
                    break;
                }
                break;
            case 772299971:
                if (str.equals("openAppWithParams")) {
                    c2 = 18;
                    break;
                }
                break;
            case 946127772:
                if (str.equals("preParseHolder")) {
                    c2 = 19;
                    break;
                }
                break;
            case 1098537456:
                if (str.equals("removeSelf")) {
                    c2 = 20;
                    break;
                }
                break;
            case 1151394242:
                if (str.equals("finishPage")) {
                    c2 = 21;
                    break;
                }
                break;
            case 1561120579:
                if (str.equals("showSoftInput")) {
                    c2 = 22;
                    break;
                }
                break;
            case 1778729330:
                if (str.equals("getNetWorkType")) {
                    c2 = 23;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                b();
                return null;
            case 1:
                if (activity != null && activity.getIntent() != null && activity.getIntent().getExtras() != null) {
                    return activity.getIntent().getExtras().get(jSONObject.optString("key"));
                }
                return null;
            case 2:
                if (DynamicSdk.getEngine().getDynamicMta() != null) {
                    return DynamicSdk.getEngine().getDynamicMta().getMtaValue(jSONObject.optString("key"));
                }
                return null;
            case 3:
                DynamicTemplateEngine dynamicTemplateEngine2 = this.mEngine;
                if (dynamicTemplateEngine2 != null && dynamicTemplateEngine2.getDialog() != null) {
                    Dialog dialog = this.mEngine.getDialog();
                    if (dialog instanceof UnBaseDialog) {
                        break;
                    }
                    this.mEngine.getDialog().dismiss();
                }
                return null;
            case 4:
                p = p(jSONObject);
                if (p != null) {
                    break;
                }
                return null;
            case 5:
                String optString = jSONObject.optString("url");
                com.jd.dynamic.lib.utils.t.e("UtilFunction", "openApp url = " + optString);
                if (!TextUtils.isEmpty(optString) && !DynamicUtils.isElOrKnownSymbol(optString) && (appRouter = DynamicSdk.getEngine().getAppRouter()) != null) {
                    appRouter.jumpWithUrl(this.mEngine.getActivity(), optString);
                }
                return null;
            case 6:
                String optString2 = jSONObject.optString("url");
                com.jd.dynamic.lib.utils.t.e("UtilFunction", "openUrl url = " + optString2);
                IAppRouter appRouter3 = DynamicSdk.getEngine().getAppRouter();
                if (!TextUtils.isEmpty(optString2) && !DynamicUtils.isElOrKnownSymbol(optString2) && appRouter3 != null) {
                    try {
                        String scheme = Uri.parse(optString2).getScheme();
                        if (!TextUtils.isEmpty(scheme)) {
                            if (scheme.startsWith("http")) {
                                com.jd.dynamic.lib.utils.t.e("UtilFunction", "openUrl jumpH5");
                                appRouter3.jumpToH5(this.mEngine.getActivity(), optString2);
                            } else {
                                com.jd.dynamic.lib.utils.t.e("UtilFunction", "openUrl jumpOpenApp");
                                appRouter3.jumpWithUrl(this.mEngine.getActivity(), optString2);
                            }
                        }
                    } catch (Exception e2) {
                        com.jd.dynamic.lib.utils.t.a(e2);
                    }
                }
                return null;
            case 7:
                String optString3 = jSONObject.optString("url");
                com.jd.dynamic.lib.utils.t.e("UtilFunction", "openH5 url = " + optString3);
                if (!TextUtils.isEmpty(optString3) && !DynamicUtils.isElOrKnownSymbol(optString3) && (appRouter2 = DynamicSdk.getEngine().getAppRouter()) != null) {
                    appRouter2.jumpToH5(this.mEngine.getActivity(), optString3);
                }
                return null;
            case '\b':
                x(jSONObject);
                return null;
            case '\t':
                h(jSONObject);
                return null;
            case '\n':
                p = t(jSONObject);
                if (p != null) {
                    break;
                }
                return null;
            case 11:
                DynamicTemplateEngine dynamicTemplateEngine3 = this.mEngine;
                if (dynamicTemplateEngine3 != null && dynamicTemplateEngine3.getDialog() != null) {
                    Dialog dialog2 = this.mEngine.getDialog();
                    if (dialog2 instanceof UnBaseDialog) {
                        break;
                    }
                    this.mEngine.getDialog().dismiss();
                }
                return null;
            case '\f':
                com.jd.dynamic.lib.utils.t.e("UtilFunction", jSONObject.optString("msg"));
                return null;
            case '\r':
                String optString4 = jSONObject.optString("type");
                String optString5 = jSONObject.optString("value");
                String optString6 = jSONObject.optString("cacheKey");
                String optString7 = jSONObject.optString("onFinish");
                if (TextUtils.equals(optString4, "append")) {
                    Object dataObj = com.jd.dynamic.b.a.b.o().e() ? this.mEngine.getCachePool().getDataObj(optString6) : this.mEngine.getCachePool().getData(optString6);
                    try {
                        if (dataObj == null) {
                            Object nextValue = new JSONTokener(optString5).nextValue();
                            if (nextValue instanceof JSONObject) {
                                JSONArray jSONArray2 = new JSONArray();
                                jSONArray2.put(nextValue);
                                optString5 = jSONArray2.toString();
                            }
                            if (com.jd.dynamic.b.a.b.o().e()) {
                                this.mEngine.getCachePool().putObjData(optString6, optString5);
                            } else {
                                this.mEngine.getCachePool().putData(optString6, optString5);
                            }
                            Observable.from(com.jd.dynamic.lib.utils.s.i(optString7)).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.n0
                                @Override // rx.functions.Action1
                                public final void call(Object obj2) {
                                    m1.this.k(dynamicTemplateEngine, (String) obj2);
                                }
                            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.o0
                                @Override // rx.functions.Action1
                                public final void call(Object obj2) {
                                    m1.v((Throwable) obj2);
                                }
                            });
                            return null;
                        } else if (dataObj instanceof String) {
                            Object nextValue2 = new JSONTokener((String) dataObj).nextValue();
                            Object nextValue3 = new JSONTokener(optString5).nextValue();
                            if (nextValue2 instanceof JSONArray) {
                                JSONArray jSONArray3 = (JSONArray) nextValue2;
                                if (nextValue3 instanceof JSONArray) {
                                    JSONArray jSONArray4 = (JSONArray) nextValue3;
                                    while (i2 < jSONArray4.length()) {
                                        jSONArray3.put(jSONArray4.opt(i2));
                                        i2++;
                                    }
                                } else if (nextValue3 instanceof JSONObject) {
                                    jSONArray3.put(nextValue3);
                                }
                                if (com.jd.dynamic.b.a.b.o().e()) {
                                    cachePool2 = this.mEngine.getCachePool();
                                    jSONArray = jSONArray3;
                                    cachePool2.putObjData(optString6, jSONArray);
                                } else {
                                    cachePool = this.mEngine.getCachePool();
                                    obj = jSONArray3.toString();
                                    cachePool.putData(optString6, obj);
                                }
                            }
                        } else if (dataObj instanceof JSONArray) {
                            Object nextValue4 = new JSONTokener(optString5).nextValue();
                            if (nextValue4 instanceof JSONArray) {
                                JSONArray jSONArray5 = (JSONArray) nextValue4;
                                while (i2 < jSONArray5.length()) {
                                    ((JSONArray) dataObj).put(jSONArray5.opt(i2));
                                    i2++;
                                }
                            } else if (nextValue4 instanceof JSONObject) {
                                ((JSONArray) dataObj).put(nextValue4);
                            }
                            if (com.jd.dynamic.b.a.b.o().e()) {
                                cachePool2 = this.mEngine.getCachePool();
                                jSONArray = dataObj;
                                cachePool2.putObjData(optString6, jSONArray);
                            } else {
                                cachePool = this.mEngine.getCachePool();
                                obj = dataObj.toString();
                                cachePool.putData(optString6, obj);
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } else if (TextUtils.equals(optString4, "replace")) {
                    if (com.jd.dynamic.b.a.b.o().e()) {
                        this.mEngine.getCachePool().putObjData(optString6, optString5);
                    } else {
                        this.mEngine.getCachePool().putData(optString6, optString5);
                    }
                }
                Observable.from(com.jd.dynamic.lib.utils.s.i(optString7)).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.l0
                    @Override // rx.functions.Action1
                    public final void call(Object obj2) {
                        m1.this.d(dynamicTemplateEngine, (String) obj2);
                    }
                }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.x0
                    @Override // rx.functions.Action1
                    public final void call(Object obj2) {
                        m1.u((Throwable) obj2);
                    }
                });
                return null;
            case 14:
                String optString8 = jSONObject.optString("url");
                StringBuilder sb = new StringBuilder(optString8);
                IAppRouter appRouter4 = DynamicSdk.getEngine().getAppRouter();
                com.jd.dynamic.lib.utils.t.e("UtilFunction", "openUrl exec router");
                if (!TextUtils.isEmpty(optString8) && !DynamicUtils.isElOrKnownSymbol(optString8) && appRouter4 != null) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("args");
                    String optString9 = jSONObject.optString("cacheKey");
                    String optString10 = jSONObject.optString("callback");
                    if (optJSONObject != null) {
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            String optString11 = optJSONObject.optString(next);
                            if (!TextUtils.isEmpty(optString11)) {
                                sb.append(TextUtils.isEmpty(Uri.parse(sb.toString()).getQuery()) ? "?" : ContainerUtils.FIELD_DELIMITER);
                                sb.append(next);
                                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                                sb.append(optString11);
                            }
                        }
                    }
                    com.jd.dynamic.lib.utils.t.e("UtilFunction", "openUrl url = " + sb.toString());
                    appRouter4.openJDRouter(sb.toString(), new a(optString9, optString10, dynamicTemplateEngine));
                }
                return null;
            case 15:
                w(jSONObject);
                return null;
            case 16:
                if (this.mEngine.getActivity() instanceof LifecycleOwnerExtend) {
                    String optString12 = jSONObject.optString("key");
                    Intent resultIntent = ((LifecycleOwnerExtend) this.mEngine.getActivity()).getResultIntent();
                    if (resultIntent != null) {
                        return resultIntent.getStringExtra(optString12);
                    }
                }
                return null;
            case 17:
                m(jSONObject);
                return null;
            case 18:
                JSONObject optJSONObject2 = jSONObject.optJSONObject("params");
                IAppRouter appRouter5 = DynamicSdk.getEngine().getAppRouter();
                if (appRouter5 != null) {
                    appRouter5.jumpWithParams(this.mEngine.getActivity(), optJSONObject2);
                }
                return null;
            case 19:
                z(jSONObject);
                return null;
            case 20:
                if (this.mEngine.getmTemplateContainer() != null) {
                    FrameLayout frameLayout = this.mEngine.getmTemplateContainer();
                    if (frameLayout.getParent() instanceof ViewGroup) {
                        ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
                    }
                }
                return null;
            case 21:
                if (activity != null) {
                    activity.finish();
                }
                return null;
            case 22:
                y(jSONObject);
                return null;
            case 23:
                if (DynamicSdk.getEngine().getRequest() != null) {
                    return DynamicSdk.getEngine().getRequest().getNetworkType();
                }
                return null;
            default:
                return null;
        }
    }
}
