package com.jingdong.manto.m.j1;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.widget.dialog.MantoDialog;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ Bundle a;
        final /* synthetic */ MantoResultCallBack b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoCore f13404c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13405e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13406f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f13407g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f13408h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ boolean f13409i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f13410j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ boolean f13411k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f13412l;

        /* renamed from: com.jingdong.manto.m.j1.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0576a implements MantoDialog.d {
            C0576a() {
            }

            @Override // com.jingdong.manto.widget.dialog.MantoDialog.d
            public void a(DialogInterface dialogInterface, int i2, String str) {
                a.this.a.putBoolean("confirm", true);
                a.this.a.putBoolean("cancel", false);
                a.this.a.putString("content", str);
                a aVar = a.this;
                aVar.b.onSuccess(aVar.a);
            }

            @Override // com.jingdong.manto.widget.dialog.MantoDialog.d, android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                a.this.a.putBoolean("confirm", true);
                a.this.a.putBoolean("cancel", false);
                a aVar = a.this;
                aVar.b.onSuccess(aVar.a);
            }
        }

        /* loaded from: classes15.dex */
        class b implements DialogInterface.OnClickListener {
            b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                a.this.a.putBoolean("confirm", false);
                a.this.a.putBoolean("cancel", true);
                a aVar = a.this;
                aVar.b.onSuccess(aVar.a);
            }
        }

        /* renamed from: com.jingdong.manto.m.j1.c$a$c  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class DialogInterfaceOnCancelListenerC0577c implements DialogInterface.OnCancelListener {
            DialogInterfaceOnCancelListenerC0577c() {
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                a.this.a.putBoolean("confirm", false);
                a.this.a.putBoolean("cancel", false);
                a aVar = a.this;
                aVar.b.onSuccess(aVar.a);
            }
        }

        a(Bundle bundle, MantoResultCallBack mantoResultCallBack, MantoCore mantoCore, String str, String str2, String str3, String str4, String str5, boolean z, String str6, boolean z2, String str7) {
            this.a = bundle;
            this.b = mantoResultCallBack;
            this.f13404c = mantoCore;
            this.d = str;
            this.f13405e = str2;
            this.f13406f = str3;
            this.f13407g = str4;
            this.f13408h = str5;
            this.f13409i = z;
            this.f13410j = str6;
            this.f13411k = z2;
            this.f13412l = str7;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.a(this.f13404c.getActivity(), this.d, this.f13405e, this.f13406f, this.f13407g, this.f13408h, this.f13409i, this.f13410j, new C0576a(), new b(), new DialogInterfaceOnCancelListenerC0577c(), this.f13411k, this.f13412l).show();
        }
    }

    private final int a(String str, int i2) {
        return MantoDensityUtils.parseColor(str, i2);
    }

    public Dialog a(Activity activity, String str, String str2, String str3, String str4, String str5, boolean z, String str6, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener, boolean z2, String str7) {
        MantoDialog mantoDialog = new MantoDialog(activity);
        mantoDialog.setEditText(str7, z2);
        if (!TextUtils.isEmpty(str2)) {
            mantoDialog.setTitle(str2);
        }
        mantoDialog.setMessage(str);
        if (TextUtils.isEmpty(str5)) {
            str5 = "#f0250f";
        }
        mantoDialog.setConfirmTextColor(a(str5, Color.parseColor("#f0250f")));
        mantoDialog.setPositiveBtn(str3, onClickListener);
        if (z) {
            if (TextUtils.isEmpty(str6)) {
                str6 = "#848484";
            }
            mantoDialog.setCancelTextColor(a(str6, Color.parseColor("#848484")));
            mantoDialog.setNegativeBtn(str4, onClickListener2);
        }
        mantoDialog.setOnCancelListener(onCancelListener);
        return mantoDialog;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "ShowModal";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        Bundle bundle2 = new Bundle();
        String optString = jSONObject.optString("title");
        String optString2 = jSONObject.optString("confirmText", "\u786e\u8ba4");
        String optString3 = jSONObject.optString(XView2Constants.XVIEW2_DIALOG_ACTION_CANCELTEXT, "\u53d6\u6d88");
        String optString4 = jSONObject.optString("cancelColor");
        MantoThreadUtils.runOnUIThread(new a(bundle2, mantoResultCallBack, mantoCore, jSONObject.optString("content"), optString, optString2, optString3, jSONObject.optString("confirmColor"), jSONObject.optBoolean("showCancel", true), optString4, jSONObject.optBoolean("editable", false), jSONObject.optString("placeholderText")));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("showModal", 1));
    }
}
