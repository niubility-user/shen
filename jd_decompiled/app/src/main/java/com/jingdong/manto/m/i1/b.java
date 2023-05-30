package com.jingdong.manto.m.i1;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.ui.base.AuthorizeItemListView;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class b extends Dialog implements View.OnClickListener, a.b {
    private Context a;
    private View b;

    /* renamed from: c  reason: collision with root package name */
    private View f13373c;
    private CheckBox d;

    /* renamed from: e  reason: collision with root package name */
    private ImageView f13374e;

    /* renamed from: f  reason: collision with root package name */
    private TextView f13375f;

    /* renamed from: g  reason: collision with root package name */
    private TextView f13376g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f13377h;

    /* renamed from: i  reason: collision with root package name */
    private AuthorizeItemListView f13378i;

    /* renamed from: j  reason: collision with root package name */
    private Button f13379j;

    /* renamed from: k  reason: collision with root package name */
    private Button f13380k;

    /* renamed from: l  reason: collision with root package name */
    private List<d> f13381l;

    /* renamed from: m  reason: collision with root package name */
    private String f13382m;

    /* renamed from: n  reason: collision with root package name */
    private String f13383n;
    private InterfaceC0571b o;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public class a extends BaseAdapter {
        private LayoutInflater a;

        /* renamed from: com.jingdong.manto.m.i1.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0569a implements CompoundButton.OnCheckedChangeListener {
            final /* synthetic */ int a;

            C0569a(int i2) {
                this.a = i2;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ((d) b.this.f13381l.get(this.a)).f13384c = z;
            }
        }

        /* renamed from: com.jingdong.manto.m.i1.b$a$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0570b {
            TextView a;
            CheckBox b;

            C0570b(a aVar) {
            }
        }

        public a() {
            this.a = LayoutInflater.from(b.this.a);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return b.this.f13381l.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return b.this.f13381l.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            C0570b c0570b;
            TextView textView;
            Resources resources;
            int i3;
            d dVar = (d) b.this.f13381l.get(i2);
            if (view == null) {
                view = this.a.inflate(R.layout.manto_push_auth_dialog_item, (ViewGroup) null);
                c0570b = new C0570b(this);
                c0570b.a = (TextView) view.findViewById(R.id.tv_scope);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.item_check_box);
                c0570b.b = checkBox;
                checkBox.setOnCheckedChangeListener(new C0569a(i2));
                view.setTag(c0570b);
            } else {
                c0570b = (C0570b) view.getTag();
            }
            c0570b.a.setText(dVar.a);
            c0570b.b.setChecked(dVar.f13384c);
            if (com.jingdong.manto.k.a.b().a() == 0) {
                textView = c0570b.a;
                resources = com.jingdong.manto.c.a().getResources();
                i3 = R.color.manto_auth_item_day;
            } else {
                textView = c0570b.a;
                resources = com.jingdong.manto.c.a().getResources();
                i3 = R.color.manto_dark_text_weight;
            }
            textView.setTextColor(resources.getColor(i3));
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            return false;
        }
    }

    /* renamed from: com.jingdong.manto.m.i1.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public interface InterfaceC0571b {
        void a(boolean z, List<d> list);

        void b(boolean z, List<d> list);

        void onCancel();
    }

    public b(@NonNull Context context, List<d> list, String str, String str2, @NonNull InterfaceC0571b interfaceC0571b) {
        super(context);
        this.a = context;
        this.f13381l = list;
        this.f13382m = str;
        this.f13383n = str2;
        this.o = interfaceC0571b;
    }

    private void a(int i2) {
        Button button;
        Resources resources;
        int i3;
        if (i2 == 0) {
            int color = getContext().getResources().getColor(R.color.manto_day_text_weight);
            this.b.setBackgroundResource(R.drawable.manto_dialog_auth_background);
            this.f13375f.setTextColor(color);
            this.f13376g.setTextColor(color);
            this.f13377h.setTextColor(color);
            this.f13379j.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_reject_btn));
            this.f13379j.setTextColor(getContext().getResources().getColor(R.color.manto_auth_reject_btn_text_day));
            this.f13380k.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_ok_btn));
            button = this.f13380k;
            resources = getContext().getResources();
            i3 = R.color.manto_auth_ok_btn_text_day;
        } else {
            int color2 = getContext().getResources().getColor(R.color.manto_dark_text_weight);
            this.b.setBackgroundResource(R.drawable.manto_dialog_auth_background_dark);
            this.f13375f.setTextColor(color2);
            this.f13376g.setTextColor(color2);
            this.f13377h.setTextColor(color2);
            this.f13379j.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_reject_btn_dark));
            this.f13379j.setTextColor(getContext().getResources().getColor(R.color.manto_auth_reject_btn_text_dark));
            this.f13380k.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_ok_btn_dark));
            button = this.f13380k;
            resources = getContext().getResources();
            i3 = R.color.manto_auth_ok_btn_text_dark;
        }
        button.setTextColor(resources.getColor(i3));
    }

    void a() {
        IImageLoader iImageLoader;
        this.f13376g = (TextView) this.b.findViewById(R.id.push_msg_tip);
        this.f13377h = (TextView) this.b.findViewById(R.id.push_not_ask_tip);
        this.d = (CheckBox) this.b.findViewById(R.id.cb_not_ask);
        View findViewById = this.b.findViewById(R.id.btn_close);
        this.f13373c = findViewById;
        findViewById.setOnClickListener(this);
        ImageView imageView = (ImageView) this.b.findViewById(R.id.iv_app_icon);
        this.f13374e = imageView;
        if (imageView != null && !MantoStringUtils.isEmpty(this.f13383n) && (iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class)) != null) {
            iImageLoader.loadImage(this.f13374e, this.f13383n);
        }
        TextView textView = (TextView) this.b.findViewById(R.id.tv_app_name);
        this.f13375f = textView;
        textView.setText(this.f13382m);
        AuthorizeItemListView authorizeItemListView = (AuthorizeItemListView) this.b.findViewById(R.id.lv_request_permission);
        this.f13378i = authorizeItemListView;
        authorizeItemListView.setAdapter((ListAdapter) new a());
        if (this.f13381l.size() > 5) {
            this.f13378i.a = this.f13381l.size();
            ViewGroup.LayoutParams layoutParams = this.f13378i.getLayoutParams();
            layoutParams.height = MantoDensityUtils.dip2pixel(getContext(), 100);
            this.f13378i.setLayoutParams(layoutParams);
        }
        Button button = (Button) this.b.findViewById(R.id.bt_reject);
        this.f13379j = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.b.findViewById(R.id.bt_accept);
        this.f13380k = button2;
        button2.setOnClickListener(this);
        com.jingdong.manto.k.a.b().a(this);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        InterfaceC0571b interfaceC0571b = this.o;
        if (interfaceC0571b != null) {
            interfaceC0571b.onCancel();
        }
        com.jingdong.manto.k.a.b().b(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_accept) {
            this.o.a(this.d.isChecked(), this.f13381l);
        } else if (id != R.id.btn_close && id == R.id.bt_reject) {
            this.o.b(this.d.isChecked(), this.f13381l);
        } else {
            this.o.onCancel();
        }
        com.jingdong.manto.k.a.b().b(this);
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        this.b = View.inflate(getContext(), R.layout.manto_push_auth_dialog_layout, null);
        if (window != null) {
            window.requestFeature(1);
        }
        setContentView(this.b);
        if (window != null) {
            window.setLayout(-1, -2);
            window.setGravity(80);
            window.setBackgroundDrawableResource(17170445);
        }
        setCanceledOnTouchOutside(false);
        a();
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }
}
