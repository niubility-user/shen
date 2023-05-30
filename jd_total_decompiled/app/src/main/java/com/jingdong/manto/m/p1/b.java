package com.jingdong.manto.m.p1;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModuleGen2;
import com.jingdong.manto.jsapi.openmodule.ApiWorker;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.l.f;
import com.jingdong.manto.sdk.d;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: classes15.dex */
class b extends ApiWorker {
    private LinearLayout a;
    private com.jingdong.manto.sdk.d b;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ MantoCore b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Bundle f13555c;
        final /* synthetic */ MantoResultCallBack d;

        a(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = mantoCore;
            this.f13555c = bundle;
            this.d = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a.equals("showToast")) {
                    b.this.a(this.b, new JSONObject(this.f13555c.getString("params")), this.d);
                } else if (this.a.equals("hideToast")) {
                    b.this.a(this.d);
                } else {
                    this.d.onFailed(null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                MantoResultCallBack mantoResultCallBack = this.d;
                if (mantoResultCallBack != null) {
                    mantoResultCallBack.onFailed(null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.p1.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0603b implements d.a {
        C0603b() {
        }

        @Override // com.jingdong.manto.sdk.d.a
        public boolean a() {
            b.this.a(false);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements View.OnTouchListener {
        c(b bVar) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements com.jingdong.manto.jsapi.coverview.b {
        d(b bVar) {
        }

        @Override // com.jingdong.manto.jsapi.coverview.b
        public InputStream a(MantoCore mantoCore, String str) {
            return AbstractMantoModule.readFile(mantoCore, str);
        }
    }

    public b(AbstractMantoModuleGen2 abstractMantoModuleGen2, String str) {
        super(abstractMantoModuleGen2, str);
    }

    private Drawable a(MantoCore mantoCore, View view, String str) {
        if (TextUtils.isEmpty(str)) {
            return view.getResources().getDrawable(R.drawable.manto_toast_ok);
        }
        Bitmap a2 = f.a(this.appId, new d(this), str, mantoCore);
        if (a2 == null || a2.isRecycled()) {
            return null;
        }
        return new BitmapDrawable(view.getResources(), a2);
    }

    private LinearLayout a() {
        return (LinearLayout) LayoutInflater.from(com.jingdong.manto.c.a()).inflate(R.layout.manto_toast, (ViewGroup) null);
    }

    private void a(View view) {
        if (view != null) {
            view.setVisibility(8);
            if (ViewGroup.class.isInstance(view.getParent())) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
    }

    private void a(MantoCore mantoCore, String str, boolean z, boolean z2, String str2, String str3) {
        ImageView imageView;
        TextView textView;
        Drawable a2;
        if (z2) {
            this.a.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.a.findViewById(R.id.toast_root).getBackground().setColorFilter(Color.parseColor("#88000000"), PorterDuff.Mode.SRC_ATOP);
            this.a.setOnTouchListener(new c(this));
        }
        TextView textView2 = null;
        ImageView imageView2 = null;
        if (z) {
            this.a.findViewById(R.id.ll_loading).setVisibility(8);
            this.a.findViewById(R.id.ll_success).setVisibility(8);
            textView = (TextView) this.a.findViewById(R.id.toast_title);
        } else {
            if (TextUtils.isEmpty(str2)) {
                imageView = null;
            } else {
                this.a.findViewById(R.id.toast_title).setVisibility(8);
                if ("loading".equalsIgnoreCase(str2)) {
                    this.a.findViewById(R.id.ll_loading).setVisibility(0);
                    this.a.findViewById(R.id.ll_success).setVisibility(8);
                    textView2 = (TextView) this.a.findViewById(R.id.toast_loading_title);
                    imageView = null;
                } else {
                    this.a.findViewById(R.id.ll_loading).setVisibility(8);
                    this.a.findViewById(R.id.ll_success).setVisibility(0);
                    textView2 = (TextView) this.a.findViewById(R.id.toast_success_title);
                    imageView = (ImageView) this.a.findViewById(R.id.toast_icon);
                }
            }
            if (TextUtils.isEmpty(str3)) {
                TextView textView3 = textView2;
                imageView2 = imageView;
                textView = textView3;
            } else {
                this.a.findViewById(R.id.ll_loading).setVisibility(8);
                this.a.findViewById(R.id.ll_success).setVisibility(0);
                this.a.findViewById(R.id.toast_title).setVisibility(8);
                textView = (TextView) this.a.findViewById(R.id.toast_success_title);
                imageView2 = (ImageView) this.a.findViewById(R.id.toast_icon);
            }
        }
        if (textView != null) {
            if (TextUtils.isEmpty(str)) {
                textView.setVisibility(4);
            } else {
                textView.setMaxWidth(MantoDensityUtils.getDMWidthPixels() - MantoDensityUtils.dip2pixel(textView.getContext(), 50));
                textView.setText(str);
                textView.setVisibility(0);
            }
        }
        if (imageView2 == null || (a2 = a(mantoCore, this.a, str3)) == null) {
            return;
        }
        imageView2.setImageDrawable(a2);
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MantoCore mantoCore, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        int optInt = jSONObject.optInt("duration", 1500);
        String optString = jSONObject.optString("title");
        String optString2 = jSONObject.optString("icon", "success");
        String optString3 = jSONObject.optString("image");
        boolean optBoolean = jSONObject.optBoolean("mask");
        boolean z = TextUtils.isEmpty(optString2) && TextUtils.isEmpty(optString3);
        LinearLayout linearLayout = this.a;
        if (linearLayout != null) {
            a(linearLayout);
        }
        com.jingdong.manto.sdk.d dVar = this.b;
        if (dVar != null) {
            dVar.b();
        }
        try {
            this.a = a();
            a(mantoCore, optString, z, optBoolean, optString2, optString3);
            AbstractMantoModule.getPageInnerContentView(mantoCore).addView(this.a);
            com.jingdong.manto.sdk.d dVar2 = new com.jingdong.manto.sdk.d(new C0603b(), false);
            this.b = dVar2;
            dVar2.a(optInt);
            mantoResultCallBack.onSuccess(new Bundle());
        } catch (Throwable th) {
            th.printStackTrace();
            mantoResultCallBack.onFailed(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MantoResultCallBack mantoResultCallBack) {
        com.jingdong.manto.sdk.d dVar = this.b;
        if (dVar != null) {
            dVar.b();
        }
        this.b = null;
        LinearLayout linearLayout = this.a;
        if (linearLayout == null) {
            mantoResultCallBack.onFailed(null);
            return;
        }
        a(linearLayout);
        mantoResultCallBack.onSuccess(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        a(this.a);
        if (z) {
            this.a = null;
        }
        com.jingdong.manto.sdk.d dVar = this.b;
        if (dVar != null) {
            dVar.b();
        }
        this.b = null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.ApiWorker
    public void doMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoThreadUtils.runOnUIThread(new a(str, mantoCore, bundle, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.ApiWorker
    public Bundle doMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.ApiWorker, com.jingdong.manto.AppLifeCycle.Listener
    public void onAppDestroy() {
        super.onAppDestroy();
        a(true);
        onTaskEnd();
    }
}
