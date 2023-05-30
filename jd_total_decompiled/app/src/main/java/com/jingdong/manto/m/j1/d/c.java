package com.jingdong.manto.m.j1.d;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.d;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.e;
import com.unionpay.tsmservice.mi.data.Constant;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends AbstractMantoViewManager {

    /* loaded from: classes15.dex */
    class a implements View.OnClickListener {
        final /* synthetic */ Reference a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13421c;
        final /* synthetic */ int d;

        a(Reference reference, String str, String str2, int i2) {
            this.a = reference;
            this.b = str;
            this.f13421c = str2;
            this.d = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoCore mantoCore = (MantoCore) this.a.get();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", this.b);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if ("webview".equals(this.f13421c)) {
                c cVar = c.this;
                cVar.dispatchEventToPage(mantoCore, new C0580c(cVar).getJsApiName(), jSONObject, new int[]{this.d});
                return;
            }
            c cVar2 = c.this;
            cVar2.dispatchEvent(mantoCore, new C0580c(cVar2).getJsApiName(), jSONObject, this.d);
        }
    }

    /* loaded from: classes15.dex */
    class b extends AnimatorListenerAdapter {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        b(c cVar, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = mantoResultCallBack;
            this.b = bundle;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.a.onSuccess(this.b);
        }
    }

    /* renamed from: com.jingdong.manto.m.j1.d.c$c  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0580c extends d {
        C0580c(c cVar) {
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onTextViewClick";
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        Context g2 = com.jingdong.a.g();
        return new CoverViewContainer(g2, new e(g2));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "TextView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final String getViewName() {
        return "TextView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        bundle.putBoolean("abg", false);
        bundle.putBoolean("enableLongClick", false);
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c7, code lost:
        if (r5 == 1) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c9, code lost:
        if (r5 == 2) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00cc, code lost:
        r13 = new android.view.animation.DecelerateInterpolator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d2, code lost:
        r13 = new android.view.animation.AccelerateInterpolator();
     */
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        TimeInterpolator timeInterpolator = null;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        Bundle bundle2 = new Bundle();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("finalStyle");
            float convertToDeviceSize2 = MantoDensityUtils.convertToDeviceSize2(jSONObject2, "left", MantoDensityUtils.pixel2dip(view.getX()));
            float convertToDeviceSize22 = MantoDensityUtils.convertToDeviceSize2(jSONObject2, "top", MantoDensityUtils.pixel2dip(view.getY()));
            float optDouble = (float) jSONObject2.optDouble(ViewProps.OPACITY, view.getAlpha());
            int optInt = jSONObject.optInt("duration", 300);
            String optString = jSONObject.optString("easing", "linear");
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, JshopConst.JSHOP_PROMOTIO_X, view.getX(), convertToDeviceSize2);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, JshopConst.JSHOP_PROMOTIO_Y, view.getY(), convertToDeviceSize22);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), optDouble);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(optInt);
            if (optString != null) {
                char c2 = '\uffff';
                int hashCode = optString.hashCode();
                if (hashCode != -1965120668) {
                    if (hashCode != -789192465) {
                        if (hashCode == 3105774 && optString.equals("ease")) {
                            c2 = 0;
                        }
                    } else if (optString.equals("ease-out")) {
                        c2 = 2;
                    }
                } else if (optString.equals("ease-in")) {
                    c2 = 1;
                }
                timeInterpolator = new AccelerateDecelerateInterpolator();
            }
            if (timeInterpolator == null) {
                timeInterpolator = new LinearInterpolator();
            }
            animatorSet.setInterpolator(timeInterpolator);
            animatorSet.addListener(new b(this, mantoResultCallBack, bundle2));
            animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
            animatorSet.start();
        } catch (Throwable th) {
            MantoLog.w("TextView", String.format("get finalStyle error : %s", MantoLog.getStackTraceString(th)));
            bundle2.putString("message", "missing finalStyle");
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("viewId"));
        bundle.putBoolean("abg", false);
        bundle.putBoolean("abi", false);
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("animateCoverView", 5));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        JSONObject jSONObject;
        bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("json");
        int i2 = bundle.getInt("hashCode");
        try {
            jSONObject = new JSONObject(string);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        e eVar = (e) ((CoverViewContainer) view).convertTo(e.class);
        boolean optBoolean = jSONObject.optBoolean(JDPureVideoManager.SourceKey.CLICKABLE);
        jSONObject.optBoolean("transEvt");
        String optString = jSONObject.optString("sendTo", "appservice");
        String optString2 = jSONObject.optString("data", "");
        com.jingdong.manto.jsapi.coverview.a.a(eVar, jSONObject.optJSONObject(Constant.KEY_PROMOTION_LABEL));
        com.jingdong.manto.jsapi.container.b.a(view, jSONObject.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE));
        SoftReference softReference = new SoftReference(mantoCore);
        if (eVar == null || !optBoolean) {
            return true;
        }
        eVar.setOnClickListener(new a(softReference, optString2, optString, i2));
        eVar.setClickable(optBoolean);
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        JSONObject jSONObject;
        int i2 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("json");
        try {
            jSONObject = new JSONObject(string);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        MantoLog.d("TextView", String.format("onUpdateView(viewId : %s, %s)", Integer.valueOf(i2), string));
        if (!(view instanceof CoverViewContainer)) {
            MantoLog.w("TextView", String.format("the view(%s) is not a instance of CoverViewContainer", Integer.valueOf(i2)));
            return false;
        }
        e eVar = (e) ((CoverViewContainer) view).convertTo(e.class);
        if (eVar == null) {
            MantoLog.w("TextView", String.format("the target view(%s) is null", Integer.valueOf(i2)));
        }
        com.jingdong.manto.jsapi.coverview.a.a(eVar, jSONObject.optJSONObject(Constant.KEY_PROMOTION_LABEL));
        com.jingdong.manto.jsapi.container.b.a(view, jSONObject.optJSONObject(DeeplinkProductDetailHelper.LAYER_STYLE));
        return true;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
