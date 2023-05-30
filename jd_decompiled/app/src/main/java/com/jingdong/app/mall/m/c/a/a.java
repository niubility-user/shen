package com.jingdong.app.mall.m.c.a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.widget.toast.JDCustomToastUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class a extends Dialog {

    /* renamed from: g */
    private BaseActivity f11205g;

    /* renamed from: h */
    private GridView f11206h;

    /* renamed from: i */
    private TextView f11207i;

    /* renamed from: j */
    private EditText f11208j;

    /* renamed from: k */
    private ImageView f11209k;

    /* renamed from: l */
    private TextView f11210l;

    /* renamed from: m */
    private PopupWindow f11211m;

    /* renamed from: n */
    private com.jingdong.app.mall.m.a.a f11212n;
    private List<com.jingdong.app.mall.m.b.a> o;
    private String p;
    private String q;

    /* renamed from: com.jingdong.app.mall.m.c.a.a$a */
    /* loaded from: classes4.dex */
    public class ViewOnClickListenerC0347a implements View.OnClickListener {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.m.c.a.a$a$a */
        /* loaded from: classes4.dex */
        public class C0348a implements HttpGroup.OnCommonListener {

            /* renamed from: com.jingdong.app.mall.m.c.a.a$a$a$a */
            /* loaded from: classes4.dex */
            class RunnableC0349a implements Runnable {
                RunnableC0349a() {
                    C0348a.this = r1;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.dismiss();
                    a.this.A(1);
                }
            }

            /* renamed from: com.jingdong.app.mall.m.c.a.a$a$a$b */
            /* loaded from: classes4.dex */
            class b implements Runnable {

                /* renamed from: g */
                final /* synthetic */ HttpResponse f11216g;

                b(HttpResponse httpResponse) {
                    C0348a.this = r1;
                    this.f11216g = httpResponse;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.dismiss();
                    a.this.A(this.f11216g.getCode());
                }
            }

            C0348a() {
                ViewOnClickListenerC0347a.this = r1;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                a.this.f11205g.post(new b(httpResponse));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                a.this.f11205g.post(new RunnableC0349a());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        }

        ViewOnClickListenerC0347a() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDMtaUtils.onClickWithPageId(a.this.f11205g, "Message_FeedbackConfirm", ViewOnClickListenerC0347a.class.getName(), "", "", "MessageCenter_Feedback");
            com.jingdong.app.mall.messagecenter.view.manager.a.b(a.this.q, a.this.p, a.this.s(), null, new C0348a());
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDMtaUtils.onClickWithPageId(a.this.f11205g, "Message_FeedbackClose", b.class.getName(), "", "", "MessageCenter_Feedback");
            a.this.dismiss();
        }
    }

    /* loaded from: classes4.dex */
    public class c implements AdapterView.OnItemClickListener {

        /* renamed from: g */
        final /* synthetic */ Button f11219g;

        c(Button button) {
            a.this = r1;
            this.f11219g = button;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            for (int i3 = 0; i3 < a.this.o.size(); i3++) {
                if (i2 == i3) {
                    if (((com.jingdong.app.mall.m.b.a) a.this.o.get(i3)).isSelect()) {
                        ((com.jingdong.app.mall.m.b.a) a.this.o.get(i3)).setSelect(false);
                    } else {
                        ((com.jingdong.app.mall.m.b.a) a.this.o.get(i3)).setSelect(true);
                        JDMtaUtils.onClickWithPageId(a.this.f11205g, "Message_FeedbackOptions", c.class.getName(), ((com.jingdong.app.mall.m.b.a) a.this.o.get(i3)).getId(), "", "MessageCenter_Feedback");
                    }
                }
            }
            if (a.this.u()) {
                this.f11219g.setEnabled(true);
                this.f11219g.setClickable(true);
            } else {
                this.f11219g.setEnabled(false);
                this.f11219g.setClickable(false);
            }
            a.this.f11212n.notifyDataSetChanged();
        }
    }

    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {

        /* renamed from: com.jingdong.app.mall.m.c.a.a$d$a */
        /* loaded from: classes4.dex */
        class C0350a implements PopupWindow.OnDismissListener {
            C0350a() {
                d.this = r1;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                a.this.r(Float.valueOf(1.0f));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class b implements View.OnClickListener {

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.jingdong.app.mall.m.c.a.a$d$b$a */
            /* loaded from: classes4.dex */
            public class C0351a implements HttpGroup.OnCommonListener {

                /* renamed from: com.jingdong.app.mall.m.c.a.a$d$b$a$a */
                /* loaded from: classes4.dex */
                class RunnableC0352a implements Runnable {
                    RunnableC0352a() {
                        C0351a.this = r1;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.f11211m.dismiss();
                        a.this.A(1);
                    }
                }

                /* renamed from: com.jingdong.app.mall.m.c.a.a$d$b$a$b */
                /* loaded from: classes4.dex */
                class RunnableC0353b implements Runnable {

                    /* renamed from: g */
                    final /* synthetic */ HttpResponse f11226g;

                    RunnableC0353b(HttpResponse httpResponse) {
                        C0351a.this = r1;
                        this.f11226g = httpResponse;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.f11211m.dismiss();
                        a.this.A(this.f11226g.getCode());
                    }
                }

                C0351a() {
                    b.this = r1;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    a.this.f11205g.post(new RunnableC0353b(httpResponse));
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    a.this.f11205g.post(new RunnableC0352a());
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            }

            b() {
                d.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDMtaUtils.onClickWithPageId(a.this.f11205g, "Message_FeedbackInputPublic", b.class.getName(), "", "", "MessageCenter_Feedback");
                com.jingdong.app.mall.messagecenter.view.manager.a.b(a.this.q, a.this.p, a.this.s(), a.this.f11208j.getText().toString(), new C0351a());
            }
        }

        /* loaded from: classes4.dex */
        class c implements View.OnClickListener {
            c() {
                d.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDMtaUtils.onClickWithPageId(a.this.f11205g, "Message_FeedbackInputClose", c.class.getName(), "", "", "MessageCenter_Feedback");
                a.this.f11211m.dismiss();
            }
        }

        /* renamed from: com.jingdong.app.mall.m.c.a.a$d$d */
        /* loaded from: classes4.dex */
        class C0354d implements TextWatcher {
            C0354d() {
                d.this = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                String str = "s:" + ((Object) charSequence) + " start:" + i2 + " count:" + i3 + " after:" + i4;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                String str = "s:" + ((Object) charSequence) + " start:" + i2 + " count:" + i4 + " before:" + i3;
                if (charSequence.length() > 0) {
                    a.this.f11210l.setEnabled(true);
                    a.this.f11210l.setClickable(true);
                    a.this.f11210l.setTextColor(Color.parseColor("#F23030"));
                    return;
                }
                a.this.f11210l.setEnabled(false);
                a.this.f11210l.setClickable(false);
                a.this.f11210l.setTextColor(Color.parseColor(JDDarkUtil.COLOR_CCCCCC));
            }
        }

        d() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDMtaUtils.onClickWithPageId(a.this.f11205g, "Message_FeedbackInputs", d.class.getName(), "", "", "MessageCenter_Feedback");
            View inflate = a.this.getLayoutInflater().inflate(R.layout.feedback_msg_pop_edit, (ViewGroup) null);
            a.this.f11208j = (EditText) inflate.findViewById(R.id.edit_feedback);
            a.this.f11209k = (ImageView) inflate.findViewById(R.id.img_cancel);
            a.this.f11210l = (TextView) inflate.findViewById(R.id.txt_publish);
            a.this.f11211m = new PopupWindow(inflate, -1, -2, false);
            a.this.f11211m.setFocusable(true);
            a.this.f11211m.setBackgroundDrawable(new ColorDrawable());
            a.this.f11211m.setSoftInputMode(16);
            a.this.f11211m.setBackgroundDrawable(new ColorDrawable());
            a.this.f11211m.setOnDismissListener(new C0350a());
            a.this.f11211m.showAtLocation(inflate, 80, 0, 0);
            a.this.dismiss();
            a aVar = a.this;
            aVar.z(aVar.f11208j);
            a.this.f11210l.setOnClickListener(new b());
            a.this.f11209k.setOnClickListener(new c());
            a.this.f11208j.addTextChangedListener(new C0354d());
        }
    }

    /* loaded from: classes4.dex */
    public class e implements DialogInterface.OnDismissListener {
        e() {
            a.this = r1;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (a.this.f11211m == null || !a.this.f11211m.isShowing()) {
                a.this.r(Float.valueOf(1.0f));
            }
        }
    }

    /* loaded from: classes4.dex */
    public class f implements Runnable {

        /* renamed from: g */
        final /* synthetic */ EditText f11231g;

        f(a aVar, EditText editText) {
            this.f11231g = editText;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((InputMethodManager) this.f11231g.getContext().getSystemService("input_method")).showSoftInput(this.f11231g, 0);
        }
    }

    public a(Context context, int i2) {
        super(context, i2);
        this.f11205g = (BaseActivity) context;
    }

    public void A(int i2) {
        if (i2 == 0) {
            JDCustomToastUtils.showToastInCenter((Activity) this.f11205g, (byte) 2, "\u53cd\u9988\u6210\u529f", 0);
        } else {
            JDCustomToastUtils.showToastInCenter((Activity) this.f11205g, (byte) 1, "\u53cd\u9988\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u63d0\u4ea4", 0);
        }
    }

    public void r(Float f2) {
        WindowManager.LayoutParams attributes = this.f11205g.getWindow().getAttributes();
        attributes.alpha = f2.floatValue();
        this.f11205g.getWindow().addFlags(2);
        this.f11205g.getWindow().setAttributes(attributes);
    }

    public String s() {
        String str = "";
        for (com.jingdong.app.mall.m.b.a aVar : this.o) {
            if (aVar.isSelect()) {
                str = str.equals("") ? str + aVar.getId() : str + DYConstants.DY_REGEX_COMMA + aVar.getId();
            }
        }
        return str;
    }

    public static int t(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public boolean u() {
        Iterator<com.jingdong.app.mall.m.b.a> it = this.o.iterator();
        while (it.hasNext()) {
            if (it.next().isSelect()) {
                return true;
            }
        }
        return false;
    }

    private void x() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.feedback_msg_dialog, (ViewGroup) null);
        Button button = (Button) inflate.findViewById(R.id.btn_sure);
        this.f11206h = (GridView) inflate.findViewById(R.id.gv_feedback);
        this.f11207i = (TextView) inflate.findViewById(R.id.txt_edit_feedback);
        com.jingdong.app.mall.m.a.a aVar = new com.jingdong.app.mall.m.a.a(getContext(), this.o);
        this.f11212n = aVar;
        this.f11206h.setAdapter((ListAdapter) aVar);
        button.setOnClickListener(new ViewOnClickListenerC0347a());
        ((ImageView) inflate.findViewById(R.id.zd)).setOnClickListener(new b());
        this.f11206h.setOnItemClickListener(new c(button));
        this.f11207i.setOnClickListener(new d());
        r(Float.valueOf(0.8f));
        setOnDismissListener(new e());
        setContentView(inflate);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        double t = t(getContext());
        Double.isNaN(t);
        attributes.width = (int) (t * 0.8d);
        getWindow().setAttributes(attributes);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        x();
    }

    public void v(String str, String str2, String str3) {
        this.q = str3;
        this.p = str;
    }

    public void w() {
        this.f11212n.notifyDataSetChanged();
    }

    public void y(List<com.jingdong.app.mall.m.b.a> list) {
        this.o = list;
    }

    public void z(EditText editText) {
        Handler handler = new Handler();
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        handler.post(new f(this, editText));
    }
}
