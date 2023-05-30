package com.jingdong.manto.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.jsapi.auth.tools.AuthorizeManager;
import com.jingdong.manto.k.a;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.network.mantorequests.MantoReqUpdateOtherAuthSetting;
import com.jingdong.manto.network.mantorequests.b0;
import com.jingdong.manto.network.mantorequests.e0;
import com.jingdong.manto.network.mantorequests.x;
import com.jingdong.manto.network.mantorequests.y;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoSettingActivity extends MantoBaseActivity implements View.OnClickListener, a.b {
    private int a;

    /* renamed from: c */
    private String f14263c;
    private String d;

    /* renamed from: e */
    private TextView f14264e;

    /* renamed from: f */
    private TextView f14265f;

    /* renamed from: g */
    private RecyclerView f14266g;

    /* renamed from: h */
    private ImageView f14267h;

    /* renamed from: i */
    private ImageView f14268i;

    /* renamed from: j */
    private View f14269j;

    /* renamed from: k */
    private f f14270k;
    private AuthInfo p;
    private boolean b = false;

    /* renamed from: l */
    private List<AuthInfo> f14271l = new ArrayList();

    /* renamed from: m */
    private List<AuthInfo> f14272m = new ArrayList();

    /* renamed from: n */
    private List<AuthInfo> f14273n = new ArrayList();
    private List<AuthInfo> o = new ArrayList();

    /* loaded from: classes16.dex */
    public class a extends IMantoHttpListener {
        a() {
            MantoSettingActivity.this = r1;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject;
            if (jSONObject.optInt("code", -1) == 0 && (optJSONObject = jSONObject.optJSONObject("result")) != null) {
                MantoSettingActivity.this.p = new AuthInfo("", "", "\u63a5\u6536\u901a\u77e5", "\u63a5\u6536\u901a\u77e5", optJSONObject.optBoolean("status", false) ? AuthorizeManager.State.ACCEPT : AuthorizeManager.State.REJECT);
                MantoSettingActivity.this.f14270k.a(MantoSettingActivity.this.p);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Runnable {
        b() {
            MantoSettingActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MantoSettingActivity.this.isFinishing()) {
                return;
            }
            MantoSettingActivity.this.o = com.jingdong.manto.b.k().b(MantoSettingActivity.this.f14263c);
            MantoSettingActivity.this.f14270k.a(MantoSettingActivity.this.o);
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Runnable {
        c() {
            MantoSettingActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MantoSettingActivity.this.isFinishing()) {
                return;
            }
            MantoSettingActivity.this.f14273n = com.jingdong.manto.b.k().a(MantoSettingActivity.this.f14263c);
            MantoSettingActivity.this.f14270k.a(MantoSettingActivity.this.f14273n);
        }
    }

    /* loaded from: classes16.dex */
    public class d extends IMantoHttpListener {
        d() {
            MantoSettingActivity.this = r1;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject;
            JSONArray optJSONArray;
            List list;
            AuthInfo authInfo;
            if (jSONObject == null || MantoSettingActivity.this.isFinishing()) {
                return;
            }
            String optString = jSONObject.optString("code");
            if (MantoStringUtils.isEmpty(optString) || !"0".equals(optString) || (optJSONObject = jSONObject.optJSONObject("data")) == null || (optJSONArray = optJSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) == null) {
                return;
            }
            MantoSettingActivity.this.f14271l.clear();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i2);
                if (jSONObject2 != null) {
                    String str = "scope." + jSONObject2.optString("key");
                    String optString2 = jSONObject2.optString("key");
                    String optString3 = jSONObject2.optString("title");
                    String optString4 = jSONObject2.optString(Constants.PARAM_SCOPE);
                    String optString5 = jSONObject2.optString("word");
                    if ("-1".equals(optString4)) {
                        list = MantoSettingActivity.this.f14271l;
                        authInfo = new AuthInfo(str, optString2, optString3, optString5, AuthorizeManager.State.REJECT);
                    } else if ("1".equals(optString4)) {
                        list = MantoSettingActivity.this.f14271l;
                        authInfo = new AuthInfo(str, optString2, optString3, optString5, AuthorizeManager.State.ACCEPT);
                    }
                    list.add(authInfo);
                }
            }
            if (MantoSettingActivity.this.isFinishing()) {
                return;
            }
            MantoSettingActivity.this.f14270k.a(MantoSettingActivity.this.f14271l);
        }
    }

    /* loaded from: classes16.dex */
    public class e extends IMantoHttpListener {
        e() {
            MantoSettingActivity.this = r1;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONArray optJSONArray;
            if (jSONObject == null || MantoSettingActivity.this.isFinishing()) {
                return;
            }
            String optString = jSONObject.optString("code");
            if (MantoStringUtils.isEmpty(optString) || !"0".equals(optString) || (optJSONArray = jSONObject.optJSONArray("data")) == null || optJSONArray.length() <= 0) {
                return;
            }
            MantoSettingActivity.this.f14272m.clear();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i2);
                if (jSONObject2 != null) {
                    String optString2 = jSONObject2.optString("authName");
                    String str = "scope." + jSONObject2.optString("authCode");
                    String optString3 = jSONObject2.optString("authCode");
                    String optString4 = jSONObject2.optString("isAuth");
                    if (!"-1".equals(optString4) && "1".equals(optString4)) {
                        MantoSettingActivity.this.f14272m.add(new AuthInfo(str, optString3, optString2, optString2, AuthorizeManager.State.ACCEPT));
                    }
                }
            }
            if (MantoSettingActivity.this.isFinishing()) {
                return;
            }
            MantoSettingActivity.this.f14270k.a(MantoSettingActivity.this.f14272m);
        }
    }

    /* loaded from: classes16.dex */
    public class f extends RecyclerView.Adapter<d> {
        private List<AuthInfo> a;
        private CopyOnWriteArrayList<a.b> b;

        /* loaded from: classes16.dex */
        public class a implements Runnable {
            a() {
                f.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.notifyDataSetChanged();
            }
        }

        /* loaded from: classes16.dex */
        public class b implements Runnable {
            b() {
                f.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.notifyDataSetChanged();
            }
        }

        /* loaded from: classes16.dex */
        public class c implements a.b {
            final /* synthetic */ d a;

            c(d dVar) {
                f.this = r1;
                this.a = dVar;
            }

            @Override // com.jingdong.manto.k.a.b
            public void onDeepModeChanged(int i2) {
                Switch r4;
                Resources resources;
                int i3;
                if (i2 == 0) {
                    this.a.a.setBackgroundColor(MantoSettingActivity.this.getResources().getColor(R.color.manto_white));
                    this.a.b.setTextColor(MantoSettingActivity.this.getResources().getColor(R.color.manto_day_text_weight));
                    if (Build.VERSION.SDK_INT < 16) {
                        return;
                    }
                    this.a.f14275c.setThumbDrawable(MantoSettingActivity.this.getResources().getDrawable(R.drawable.manto_switch_thumb_selecter));
                    r4 = this.a.f14275c;
                    resources = MantoSettingActivity.this.getResources();
                    i3 = R.drawable.manto_switch_track_selecter;
                } else {
                    this.a.a.setBackgroundColor(MantoSettingActivity.this.getResources().getColor(R.color.manto_dark_background_light));
                    this.a.b.setTextColor(MantoSettingActivity.this.getResources().getColor(R.color.manto_dark_text_weight));
                    if (Build.VERSION.SDK_INT < 16) {
                        return;
                    }
                    this.a.f14275c.setThumbDrawable(MantoSettingActivity.this.getResources().getDrawable(R.drawable.manto_switch_thumb_selecter_dark));
                    r4 = this.a.f14275c;
                    resources = MantoSettingActivity.this.getResources();
                    i3 = R.drawable.manto_switch_track_selecter_dark;
                }
                r4.setTrackDrawable(resources.getDrawable(i3));
            }
        }

        /* loaded from: classes16.dex */
        public class d extends RecyclerView.ViewHolder {
            View a;
            TextView b;

            /* renamed from: c */
            Switch f14275c;
            private CompoundButton.OnCheckedChangeListener d;

            /* loaded from: classes16.dex */
            public class a implements CompoundButton.OnCheckedChangeListener {

                /* renamed from: com.jingdong.manto.ui.MantoSettingActivity$f$d$a$a */
                /* loaded from: classes16.dex */
                public class C0681a extends IMantoHttpListener {
                    final /* synthetic */ AuthorizeManager.State a;
                    final /* synthetic */ boolean b;

                    /* renamed from: com.jingdong.manto.ui.MantoSettingActivity$f$d$a$a$a */
                    /* loaded from: classes16.dex */
                    class RunnableC0682a implements Runnable {
                        RunnableC0682a() {
                            C0681a.this = r1;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.f14275c.setOnCheckedChangeListener(null);
                            d.this.f14275c.setChecked(!r0.b);
                            d dVar = d.this;
                            dVar.f14275c.setOnCheckedChangeListener(dVar.d);
                            if (MantoUtils.isConnected(MantoSettingActivity.this.getApplicationContext())) {
                                return;
                            }
                            Toast.makeText(MantoSettingActivity.this.getApplicationContext(), R.string.manto_net_error, 0).show();
                        }
                    }

                    C0681a(AuthorizeManager.State state, boolean z) {
                        a.this = r1;
                        this.a = state;
                        this.b = z;
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onError(JSONObject jSONObject, Throwable th) {
                        super.onError(jSONObject, th);
                        f.this.a(new RunnableC0682a());
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onSuccess(JSONObject jSONObject) {
                        AuthorizeManager.State state = this.a;
                        ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).state = state;
                        if (AuthorizeManager.State.REJECT.equals(state)) {
                            com.jingdong.manto.m.i1.c.a(MantoSettingActivity.this.f14263c);
                        }
                    }
                }

                /* loaded from: classes16.dex */
                class b implements Runnable {
                    final /* synthetic */ AuthorizeManager.State a;
                    final /* synthetic */ AuthInfo b;

                    b(AuthorizeManager.State state, AuthInfo authInfo) {
                        a.this = r1;
                        this.a = state;
                        this.b = authInfo;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).state = this.a;
                        try {
                            com.jingdong.manto.b.k().a(MantoSettingActivity.this.f14263c, this.b);
                        } catch (Exception e2) {
                            MantoLog.e("dbError", e2);
                        }
                    }
                }

                /* loaded from: classes16.dex */
                public class c extends IMantoHttpListener {
                    final /* synthetic */ AuthorizeManager.State a;
                    final /* synthetic */ boolean b;

                    /* renamed from: com.jingdong.manto.ui.MantoSettingActivity$f$d$a$c$a */
                    /* loaded from: classes16.dex */
                    class RunnableC0683a implements Runnable {
                        RunnableC0683a() {
                            c.this = r1;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.f14275c.setOnCheckedChangeListener(null);
                            d.this.f14275c.setChecked(!r0.b);
                            d dVar = d.this;
                            dVar.f14275c.setOnCheckedChangeListener(dVar.d);
                            if (MantoUtils.isConnected(MantoSettingActivity.this.getApplicationContext())) {
                                return;
                            }
                            Toast.makeText(MantoSettingActivity.this.getApplicationContext(), R.string.manto_net_error, 0).show();
                        }
                    }

                    c(AuthorizeManager.State state, boolean z) {
                        a.this = r1;
                        this.a = state;
                        this.b = z;
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onError(JSONObject jSONObject, Throwable th) {
                        super.onError(jSONObject, th);
                        f.this.a(new RunnableC0683a());
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onSuccess(JSONObject jSONObject) {
                        ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).state = this.a;
                    }
                }

                /* renamed from: com.jingdong.manto.ui.MantoSettingActivity$f$d$a$d */
                /* loaded from: classes16.dex */
                public class C0684d extends IMantoHttpListener {
                    final /* synthetic */ AuthorizeManager.State a;
                    final /* synthetic */ boolean b;

                    /* renamed from: com.jingdong.manto.ui.MantoSettingActivity$f$d$a$d$a */
                    /* loaded from: classes16.dex */
                    class RunnableC0685a implements Runnable {
                        RunnableC0685a() {
                            C0684d.this = r1;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.f14275c.setOnCheckedChangeListener(null);
                            d.this.f14275c.setChecked(!r0.b);
                            d dVar = d.this;
                            dVar.f14275c.setOnCheckedChangeListener(dVar.d);
                            if (MantoUtils.isConnected(MantoSettingActivity.this.getApplicationContext())) {
                                return;
                            }
                            Toast.makeText(MantoSettingActivity.this.getApplicationContext(), R.string.manto_net_error, 0).show();
                        }
                    }

                    C0684d(AuthorizeManager.State state, boolean z) {
                        a.this = r1;
                        this.a = state;
                        this.b = z;
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onError(JSONObject jSONObject, Throwable th) {
                        super.onError(jSONObject, th);
                        f.this.a(new RunnableC0685a());
                    }

                    @Override // com.jingdong.manto.network.common.IMantoHttpListener
                    public void onSuccess(JSONObject jSONObject) {
                        AuthorizeManager.State state = this.a;
                        ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).state = state;
                        if (state == AuthorizeManager.State.ACCEPT) {
                            com.jingdong.manto.b.k().a(MantoSettingActivity.this.f14263c, ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).scope);
                        } else {
                            com.jingdong.manto.b.k().b(MantoSettingActivity.this.f14263c, (AuthInfo) f.this.a.get(d.this.getAdapterPosition()));
                        }
                    }
                }

                a() {
                    d.this = r1;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    MantoBaseRequest mantoReqUpdateOtherAuthSetting;
                    IMantoHttpListener c0684d;
                    int adapterPosition = d.this.getAdapterPosition();
                    if (adapterPosition < 0 || adapterPosition >= f.this.a.size() || !compoundButton.isPressed()) {
                        return;
                    }
                    AuthorizeManager.State state = z ? AuthorizeManager.State.ACCEPT : AuthorizeManager.State.REJECT;
                    AuthInfo authInfo = (AuthInfo) f.this.a.get(d.this.getAdapterPosition());
                    if (MantoSettingActivity.this.p != null && MantoSettingActivity.this.p == authInfo) {
                        MantoJDHttpHandler.commit(new x(MantoSettingActivity.this.f14263c, AuthorizeManager.State.ACCEPT.equals(state)), new C0681a(state, z));
                    } else if (MantoSettingActivity.this.f14273n.contains(authInfo)) {
                        com.jingdong.manto.b.d().diskIO().execute(new b(state, authInfo));
                    } else {
                        if (MantoSettingActivity.this.f14271l.contains(authInfo)) {
                            mantoReqUpdateOtherAuthSetting = new e0(MantoSettingActivity.this.f14263c, ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).permission, state);
                            c0684d = new c(state, z);
                        } else if (!MantoSettingActivity.this.f14272m.contains(authInfo) && !MantoSettingActivity.this.o.contains(authInfo)) {
                            return;
                        } else {
                            mantoReqUpdateOtherAuthSetting = new MantoReqUpdateOtherAuthSetting(MantoSettingActivity.this.f14263c, ((AuthInfo) f.this.a.get(d.this.getAdapterPosition())).permission, state.value());
                            c0684d = new C0684d(state, z);
                        }
                        MantoJDHttpHandler.commit(mantoReqUpdateOtherAuthSetting, c0684d);
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            d(View view) {
                super(view);
                f.this = r1;
                this.d = new a();
                this.a = view;
                this.f14275c = (Switch) view.findViewById(R.id.tb_switch);
                this.b = (TextView) view.findViewById(R.id.tv_setting);
                this.f14275c.setOnCheckedChangeListener(this.d);
            }
        }

        private f() {
            MantoSettingActivity.this = r1;
            this.a = new ArrayList();
            this.b = new CopyOnWriteArrayList<>();
        }

        /* synthetic */ f(MantoSettingActivity mantoSettingActivity, a aVar) {
            this();
        }

        public void a(Runnable runnable) {
            MantoSettingActivity mantoSettingActivity = MantoSettingActivity.this;
            if (mantoSettingActivity == null || mantoSettingActivity.isFinishing()) {
                return;
            }
            MantoSettingActivity.this.runOnUiThread(runnable);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a */
        public d onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manto_setting_item, viewGroup, false));
        }

        public void a() {
            Iterator<a.b> it = this.b.iterator();
            while (it.hasNext()) {
                com.jingdong.manto.k.a.b().b(it.next());
            }
        }

        public synchronized void a(AuthInfo authInfo) {
            this.a.add(authInfo);
            MantoSettingActivity.this.f14266g.post(new b());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a */
        public void onBindViewHolder(d dVar, int i2) {
            dVar.b.setText(this.a.get(i2).title);
            dVar.f14275c.setChecked(this.a.get(i2).state == AuthorizeManager.State.ACCEPT);
            c cVar = new c(dVar);
            com.jingdong.manto.k.a.b().a(cVar);
            this.b.add(cVar);
        }

        public synchronized void a(List<AuthInfo> list) {
            this.a.addAll(list);
            MantoSettingActivity.this.f14266g.post(new a());
        }

        public List<AuthInfo> b() {
            return this.a;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.a.size();
        }
    }

    private void a() {
        if (this.f14273n.isEmpty()) {
            com.jingdong.manto.b.d().diskIO().execute(new c());
        }
    }

    private void a(int i2) {
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (i2 != 0) {
            int color3 = getResources().getColor(R.color.manto_dark_text_light);
            int color4 = getResources().getColor(R.color.manto_dark_text_weight);
            int color5 = getResources().getColor(R.color.manto_dark_background_light);
            int color6 = getResources().getColor(R.color.manto_dark_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, color5, false);
            this.f14264e.setTextColor(color4);
            this.f14268i.setColorFilter(color);
            this.f14269j.setBackgroundColor(color5);
            this.f14267h.setColorFilter(color);
            this.f14265f.setBackgroundColor(color6);
            this.f14265f.setTextColor(color3);
            return;
        }
        Resources resources = getResources();
        int i3 = R.color.manto_day_text_weight;
        int color7 = resources.getColor(i3);
        int color8 = getResources().getColor(i3);
        int color9 = getResources().getColor(R.color.manto_day_background_light);
        int color10 = getResources().getColor(R.color.manto_day_background_weight);
        MantoStatusBarUtil.setStatusBarColor(this, -1, true);
        this.f14264e.setTextColor(color7);
        this.f14268i.setColorFilter(color2);
        this.f14269j.setBackgroundColor(color9);
        this.f14267h.setColorFilter(color2);
        this.f14265f.setBackgroundColor(color10);
        this.f14265f.setTextColor(color8);
    }

    public static void a(Activity activity, String str, String str2, String str3, int i2) {
        Intent intent = new Intent(activity, MantoSettingActivity.class);
        if (MantoStringUtils.isEmpty(str) || MantoStringUtils.isEmpty(str2)) {
            return;
        }
        intent.putExtra("key_app_id", str);
        intent.putExtra("key_app_name", str2);
        intent.putExtra("version", str3);
        intent.putExtra("key_is_for_result", true);
        intent.putExtra(Constants.KEY_REQUEST_CODE, i2);
        activity.startActivityForResult(intent, i2);
    }

    public static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, MantoSettingActivity.class);
        if (MantoStringUtils.isEmpty(str) || MantoStringUtils.isEmpty(str2)) {
            return;
        }
        intent.putExtra("key_app_id", str);
        intent.putExtra("key_app_name", str2);
        intent.putExtra("version", str3);
        context.startActivity(intent);
    }

    private void b() {
        if (this.f14272m.isEmpty()) {
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.e(this.f14263c), new e());
        }
    }

    private void c() {
        if (this.o.isEmpty()) {
            com.jingdong.manto.b.d().diskIO().execute(new b());
        }
    }

    private void d() {
        if (this.p == null && !isFinishing() && com.jingdong.manto.m.i1.c.d(this.f14263c)) {
            MantoJDHttpHandler.commit(new y(this.f14263c, null), new a());
        }
    }

    private void e() {
        if (this.f14271l.isEmpty()) {
            MantoJDHttpHandler.commit(new b0(this.f14263c), new d());
        }
    }

    private void g() {
        if (this.b) {
            JSONArray jSONArray = new JSONArray();
            for (AuthInfo authInfo : this.f14270k.b()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    String str = authInfo.scope;
                    if (str != null && str.length() > 8) {
                        jSONObject.put(Constants.PARAM_SCOPE, authInfo.scope);
                        jSONObject.put(XView2Constants.STATE, authInfo.state.value());
                        jSONObject.put("desc", authInfo.description);
                        jSONArray.put(jSONObject);
                    }
                } catch (Throwable th) {
                    MantoLog.e("MantoSettingUI", th.getMessage());
                }
            }
            Intent intent = new Intent();
            intent.putExtra("key_app_authorize_state", jSONArray.toString());
            setResult(this.a, intent);
        }
    }

    void f() {
        TextView textView;
        String format;
        TextView textView2 = (TextView) findViewById(R.id.manto_ui_nav_title);
        this.f14264e = textView2;
        textView2.setText("\u8bbe\u7f6e");
        this.f14269j = findViewById(R.id.manto_ui_actionbar);
        this.f14267h = (ImageView) findViewById(R.id.manto_ui_nav_option);
        ImageView imageView = (ImageView) findViewById(R.id.manto_ui_nav_back);
        this.f14268i = imageView;
        imageView.setOnClickListener(this);
        this.f14267h.setVisibility(4);
        this.f14265f = (TextView) findViewById(R.id.tv_note);
        if (MantoStringUtils.isEmpty(this.d)) {
            textView = this.f14265f;
            format = String.format(textView.getText().toString(), "\u5c0f\u7a0b\u5e8f");
        } else {
            textView = this.f14265f;
            format = String.format(textView.getText().toString(), this.d);
        }
        textView.setText(format);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_settings);
        this.f14266g = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        f fVar = new f(this, null);
        this.f14270k = fVar;
        this.f14266g.setAdapter(fVar);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity
    public int getLayoutId() {
        return R.layout.manto_setting_layout;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        g();
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.manto_ui_nav_back) {
            g();
            finish();
        }
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        f();
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        this.f14263c = intent.getStringExtra("key_app_id");
        this.d = intent.getStringExtra("key_app_name");
        this.b = intent.getBooleanExtra("key_is_for_result", false);
        this.a = intent.getIntExtra(Constants.KEY_REQUEST_CODE, 1);
        if (TextUtils.isEmpty(this.f14263c) || TextUtils.isEmpty(this.d)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", this.f14263c);
            jSONObject.put("appName", this.d);
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendPagePv(com.jingdong.manto.c.a(), "\u8bbe\u7f6e", jSONObject.toString(), "applets_pages", null);
        com.jingdong.manto.k.a.b().a(this);
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }

    @Override // com.jingdong.manto.ui.MantoBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jingdong.manto.k.a.b().b(this);
        this.f14270k.a();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e();
        b();
        a();
        c();
        d();
    }
}
