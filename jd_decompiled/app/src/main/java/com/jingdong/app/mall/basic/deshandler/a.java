package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.basic.deshandler.JumpToCartb;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.open.BaseEntryActivity;
import com.jingdong.app.mall.open.InterfaceActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.kepler.KeplerDataCallBack;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.common.login.ICancelLogin;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.utils.ActivityUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.CommonMHybridHelper;
import com.jingdong.common.web.JDWebViewBlackUrlDialog;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.util.M2NativeHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes19.dex */
public abstract class a {
    protected final String a = a.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.basic.deshandler.a$a */
    /* loaded from: classes19.dex */
    public class C0242a implements ShoppingBaseController.ShoppingListener {
        final /* synthetic */ JumpToCartb.c a;
        final /* synthetic */ ArrayList b;

        /* renamed from: c */
        final /* synthetic */ BaseActivity f8071c;

        /* renamed from: com.jingdong.app.mall.basic.deshandler.a$a$a */
        /* loaded from: classes19.dex */
        class RunnableC0243a implements Runnable {
            RunnableC0243a() {
                C0242a.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < C0242a.this.b.size(); i2++) {
                    C0242a c0242a = C0242a.this;
                    JDMtaUtils.sendCommonData(c0242a.f8071c, "MShopcartShare_AddtoMyShopcartProduct_auto", ((CartSkuSummary) c0242a.b.get(i2)).getSkuId(), "", C0242a.this.f8071c, "", "", "");
                    JDMtaUtils.onSaveProductOrderPage(((CartSkuSummary) C0242a.this.b.get(i2)).getSkuId());
                }
            }
        }

        C0242a(a aVar, JumpToCartb.c cVar, ArrayList arrayList, BaseActivity baseActivity) {
            this.a = cVar;
            this.b = arrayList;
            this.f8071c = baseActivity;
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
            JumpToCartb.c cVar = this.a;
            if (cVar != null) {
                cVar.a();
            }
            if (cartResponse.getResultCode() == 0) {
                new Thread(new RunnableC0243a()).start();
            }
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
            JumpToCartb.c cVar = this.a;
            if (cVar != null) {
                cVar.a();
            }
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements ShoppingBaseController.ShoppingListener {
        final /* synthetic */ JumpToCartb.c a;
        final /* synthetic */ ArrayList b;

        /* renamed from: c */
        final /* synthetic */ BaseActivity f8073c;

        /* renamed from: com.jingdong.app.mall.basic.deshandler.a$b$a */
        /* loaded from: classes19.dex */
        class RunnableC0244a implements Runnable {
            RunnableC0244a() {
                b.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < b.this.b.size(); i2++) {
                    b bVar = b.this;
                    JDMtaUtils.sendCommonData(bVar.f8073c, "MShopcartShare_AddtoMyShopcartProduct_auto", ((CartSkuSummary) bVar.b.get(i2)).getSkuId(), "", b.this.f8073c, "", "", "");
                    JDMtaUtils.onSaveProductOrderPage(((CartSkuSummary) b.this.b.get(i2)).getSkuId());
                }
            }
        }

        b(a aVar, JumpToCartb.c cVar, ArrayList arrayList, BaseActivity baseActivity) {
            this.a = cVar;
            this.b = arrayList;
            this.f8073c = baseActivity;
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
            JumpToCartb.c cVar = this.a;
            if (cVar != null) {
                cVar.a();
            }
            if (cartResponse.getResultCode() == 0) {
                new Thread(new RunnableC0244a()).start();
            }
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
            JumpToCartb.c cVar = this.a;
            if (cVar != null) {
                cVar.a();
            }
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Bundle f8075g;

        /* renamed from: h */
        final /* synthetic */ Context f8076h;

        c(Bundle bundle, Context context) {
            a.this = r1;
            this.f8075g = bundle;
            this.f8076h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = this.f8075g.getString("action");
            String string2 = this.f8075g.getString("url");
            String string3 = this.f8075g.getString("des");
            if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, false)) {
                if (WebUtils.checkUrlInIllegalList(string2)) {
                    ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
                    return;
                } else if (WebUtils.checkUrlInBlackList(string2)) {
                    new JDWebViewBlackUrlDialog(this.f8076h, string2).show();
                    ExceptionReporter.reportWebViewCommonError(SwitchQueryFetcher.WEB_BLACK_LIST_LOGIC, "", "JDWebView black list error", "");
                    return;
                }
            }
            String addCustomParams = WebUtils.addCustomParams(string2, this.f8075g);
            this.f8075g.putString("url", addCustomParams);
            boolean sendClickEvent = XRender.getInstance().sendClickEvent(addCustomParams);
            if (M2NativeHelper.checkM2Native(this.f8076h, addCustomParams, this.f8075g, false, true)) {
                a.this.d(this.f8076h, this.f8075g);
                return;
            }
            if (Log.D) {
                Log.d(a.this.a, " -->> url : " + addCustomParams);
                Log.d(a.this.a, " -->> action : " + string);
            }
            if (TextUtils.isEmpty(string)) {
                string = RemoteMessageConst.TO;
            }
            if (JumpUtil.VAULE_DES_JDTHIRDLOGIN.equals(string3)) {
                addCustomParams = LoginUserHelper.addAppUpTypeToUrl(addCustomParams);
            }
            URLParamMap uRLParamMap = new URLParamMap();
            if (!TextUtils.isEmpty(addCustomParams)) {
                uRLParamMap.put(RemoteMessageConst.TO, addCustomParams);
            }
            if (this.f8076h != null) {
                Intent intent = new Intent(this.f8076h, WebActivity.class);
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                intent.putExtra("urlParamMap", serializableContainer);
                intent.putExtra("urlAction", string);
                if (WebHybridUtils.hybridEnable && !sendClickEvent) {
                    WebOfflineLoaderManager.createOfflineLoaderForBundle(addCustomParams, this.f8075g, CommonMHybridHelper.sOfflineParamGetter);
                    WebPreLoadHelper.startPreLoadForBundle(addCustomParams, this.f8075g, CommonMHybridHelper.sPreloadParamGetter);
                }
                intent.putExtras(this.f8075g);
                OpenLinkTimeManager.getInstance().enterM(this.f8075g.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0));
                this.f8076h.startActivity(intent);
            }
            a.this.c(this.f8076h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class d implements ICancelLogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        /* renamed from: c */
        final /* synthetic */ Runnable f8078c;

        d(Context context, Bundle bundle, Runnable runnable) {
            a.this = r1;
            this.a = context;
            this.b = bundle;
            this.f8078c = runnable;
        }

        @Override // com.jingdong.common.login.ICancelLogin
        public void onCancel(String str) {
            if ("forwardM".equals(str)) {
                a.this.d(this.a, this.b);
            }
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardM".equals(str)) {
                this.f8078c.run();
            }
        }
    }

    /* loaded from: classes19.dex */
    public class e implements KeplerDataCallBack {
        final /* synthetic */ Context a;

        e(Context context) {
            a.this = r1;
            this.a = context;
        }

        @Override // com.jingdong.common.kepler.KeplerDataCallBack
        public void onDataFail() {
        }

        @Override // com.jingdong.common.kepler.KeplerDataCallBack
        public void onDataSuccess() {
            String config = JDMobileConfig.getInstance().getConfig("JDDetentionShow", "JDDetentionSwitch", "switch", "0");
            if (OKLog.D) {
                OKLog.d(a.this.a, "JDDetentionShow---JDDetentionSwitch---switchConfig : " + config);
            }
            if (TextUtils.equals(config, "1")) {
                KeplerJumpUtils.tryGetRetainInfo(((BaseActivity) this.a).getHttpGroupaAsynPool());
            } else {
                KeplerJumpUtils.showKeplerDragView();
            }
        }
    }

    public static Intent i(Context context, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Intent intent = new Intent(context, InterfaceActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    private void l(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || !(context instanceof Activity)) {
            return;
        }
        if (SDKUtils.isSDKVersionMoreThan16()) {
            ActivityUtils.setOverridePendingTransition((Activity) context, 0, 0);
        }
        forward(context, extras);
    }

    private ArrayList<CartPackSummary> o(String str) {
        ArrayList<CartPackSummary> arrayList = new ArrayList<>();
        try {
            if (Log.D) {
                Log.d(this.a, " forwardShoppingCart ---> packList : " + str);
            }
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (length > 0) {
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("packId");
                        int optInt = optJSONObject.optInt("num", 1);
                        JSONArray optJSONArray = optJSONObject.optJSONArray("skuList");
                        if (!TextUtils.isEmpty(optString) && optJSONArray != null && optJSONArray.length() != 0) {
                            CartPackSummary cartPackSummary = new CartPackSummary(optString, Integer.valueOf(optInt));
                            int length2 = optJSONArray.length();
                            for (int i3 = 0; i3 < length2; i3++) {
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                                if (optJSONObject2 != null) {
                                    cartPackSummary.addSku(new CartSkuSummary(optJSONObject2.optString("sku"), 1));
                                }
                            }
                            arrayList.add(cartPackSummary);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private ArrayList<CartSkuSummary> p(String str) {
        ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
        try {
            if (Log.D) {
                Log.d(this.a, " forwardShoppingCart ---> wareList : " + str);
            }
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (length > 0) {
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    String optString = optJSONObject.optString("sku");
                    int optInt = optJSONObject.optInt("num", 1);
                    String optString2 = optJSONObject.optString("storeId", "");
                    if (!TextUtils.isEmpty(optString)) {
                        arrayList.add(new CartSkuSummary(optString, optInt, optString2));
                    }
                }
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private void r(Context context, Intent intent) {
        if (context instanceof BaseActivity) {
            if (Log.D) {
                Log.d(this.a, "tryGotKeplerData  KeplerJumpUtils.keplerID:" + KeplerJumpUtils.keplerID);
            }
            KeplerJumpUtils.tryGetKeplerData(((BaseActivity) context).getHttpGroupaAsynPool(), intent, new e(context));
        }
    }

    public void a(BaseActivity baseActivity, String str, JumpToCartb.c cVar) {
        ArrayList arrayList = new ArrayList();
        try {
            if (Log.D) {
                Log.d(this.a, " forwardShoppingCart ---> skuIds : " + str);
            }
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    String optString = jSONArray.optString(i2);
                    if (!TextUtils.isEmpty(optString)) {
                        arrayList.add(new CartSkuSummary(optString, 1));
                    }
                }
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
        if (baseActivity == null || arrayList.size() <= 0) {
            return;
        }
        ShoppingBaseController.addMultiProductToCart(baseActivity, arrayList, null, new C0242a(this, cVar, arrayList, baseActivity), true);
    }

    public void b(BaseActivity baseActivity, String str, String str2, JumpToCartb.c cVar) {
        boolean z = (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) ? false : true;
        if (baseActivity == null || !z) {
            return;
        }
        ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
        ArrayList<CartPackSummary> arrayList2 = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            arrayList = p(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList2 = o(str2);
        }
        if (arrayList.size() > 0 || arrayList2.size() > 0) {
            ShoppingBaseController.addMultiProductToCart(baseActivity, arrayList, arrayList2, new b(this, cVar, arrayList, baseActivity), true);
        }
    }

    public void c(Context context) {
        if (context instanceof BaseEntryActivity) {
            ((Activity) context).finish();
        }
    }

    public void d(Context context, Bundle bundle) {
        if (context instanceof BaseEntryActivity) {
            if (bundle == null || bundle.getBoolean(OpenAppConstant.KEY_CLOSEACTIVITY, true)) {
                ((Activity) context).finish();
            }
        }
    }

    public void e(Context context, int i2) {
        if (i2 == 0) {
            DeepLinkChargeHelper.startQQChargeActivity(context);
        } else if (i2 == 1) {
            DeepLinkChargeHelper.startGameChargeActivity(context);
        }
        c(context);
    }

    public void f(Context context, Bundle bundle) {
        c cVar = new c(bundle, context);
        String string = bundle.getString(VKEventUtil.JUMP_NEEDLOGIN);
        if (!TextUtils.isEmpty(string) && !"0".equals(string)) {
            DeepLinkLoginHelper.startLoginActivity(context, null, new d(context, bundle, cVar), "forwardM");
            d(context, bundle);
            return;
        }
        cVar.run();
        d(context, bundle);
    }

    public abstract void forward(Context context, Bundle bundle);

    public void g(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "forwardMainFrame() bundle = " + bundle);
        }
        Intent intent = new Intent(context, MainFrameActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
        c(context);
    }

    public BaseActivity h() {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity instanceof BaseActivity) {
            return (BaseActivity) currentMyActivity;
        }
        return null;
    }

    public void handleDesJumpRequest(Context context, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.getBoolean("isFromWidget", false);
        boolean z = bundle.getBoolean(KeplerJumpUtils.KEY_IS_JUMP_FROM, false);
        if (SDKUtils.isSDKVersionMoreThan16() && (context instanceof Activity)) {
            ActivityUtils.setOverridePendingTransition((Activity) context, 0, 0);
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (z && !TextUtils.isEmpty(KeplerJumpUtils.keplerID)) {
            r(context, intent);
        }
        l(context, intent);
    }

    public void j(Context context, String str, Bundle bundle) {
        if (n(bundle)) {
            String string = bundle.getString("sourceType");
            if (!TextUtils.isEmpty(string)) {
                str = str + CartConstant.KEY_YB_INFO_LINK + string;
            }
            JDMtaUtils.sendCommonData(context, "QrcodeScan_OpenClient", str, "", context, "", "", "");
        }
    }

    public void k(Context context, Bundle bundle) {
        q(context, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x002c A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m(android.os.Bundle r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.Object r2 = r2.get(r3)
            r0.append(r2)
            java.lang.String r2 = ""
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L28
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Exception -> L20
            goto L29
        L20:
            r2 = move-exception
            boolean r3 = com.jingdong.corelib.utils.Log.E
            if (r3 == 0) goto L28
            r2.printStackTrace()
        L28:
            r2 = 0
        L29:
            r3 = 1
            if (r2 >= r3) goto L2d
            r2 = 1
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.basic.deshandler.a.m(android.os.Bundle, java.lang.String):int");
    }

    protected boolean n(Bundle bundle) {
        String string = bundle.getString("sourceValue");
        return !TextUtils.isEmpty(string) && "scan".equals(string);
    }

    protected void q(Context context, boolean z) {
        DeepLinkCartHelper.startCartMain(context, null);
        c(context);
    }
}
