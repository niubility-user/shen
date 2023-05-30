package com.jd.manto.center;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.manto.center.model.MiniProgramListBean;
import com.jd.manto.center.widget.MantoSearchEditText;
import com.jd.manto.center.widget.MiniProgramAdapter;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MiniProgramListActivity extends BaseMantoCenterActivity implements com.jd.manto.center.g.a {

    /* renamed from: h  reason: collision with root package name */
    private RecyclerView f6446h;

    /* renamed from: i  reason: collision with root package name */
    private ViewStub f6447i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayout f6448j;

    /* renamed from: k  reason: collision with root package name */
    private MiniProgramAdapter f6449k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayoutManager f6450l;
    private ImageView o;
    private MantoSearchEditText p;
    private LinearLayout q;
    private ImageView r;
    private ImageView s;
    private String u;
    private FrameLayout v;

    /* renamed from: m  reason: collision with root package name */
    private int f6451m = 1;

    /* renamed from: n  reason: collision with root package name */
    private String f6452n = "";
    private List<MiniProgramListBean.MiniProgramBean> t = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MiniProgramListActivity.this, "J_Applets_Top_Close", "J_Applets_SearchResult");
            MiniProgramListActivity.this.sendBroadcast(new Intent("action.exit"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MiniProgramListActivity.this.setResult(com.jd.manto.center.k.a.b, new Intent());
            MiniProgramListActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MiniProgramListActivity.this, "J_Applets_Top_More", "J_Applets_SearchResult");
            Intent intent = new Intent(MiniProgramListActivity.this, MantoCenterAboutActivity.class);
            intent.putExtra("tinyAppIntroduction", MiniProgramListActivity.this.u);
            MiniProgramListActivity.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MiniProgramListActivity.this.p.setText("");
            MiniProgramListActivity.this.G("");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.manto.center.h.b.q(MiniProgramListActivity.this, "J_Applets_Top_Search", "J_Applets_SearchResult");
            MiniProgramListActivity miniProgramListActivity = MiniProgramListActivity.this;
            miniProgramListActivity.G(miniProgramListActivity.p.getText().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f implements TextWatcher {
        f() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (TextUtils.isEmpty(charSequence)) {
                com.jd.manto.center.k.h.b(MiniProgramListActivity.this.o);
            } else {
                com.jd.manto.center.k.h.l(MiniProgramListActivity.this.o);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class g implements HttpGroup.OnCommonListener {
        g() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                MiniProgramListActivity.this.showError();
                return;
            }
            try {
                MiniProgramListBean miniProgramListBean = (MiniProgramListBean) JDJSON.parseObject(httpResponse.getFastJsonObject().toString(), MiniProgramListBean.class);
                if (miniProgramListBean == null) {
                    MiniProgramListActivity.this.showError();
                }
                MiniProgramListActivity.this.L(miniProgramListBean);
            } catch (Exception e2) {
                e2.printStackTrace();
                MiniProgramListActivity.this.showError();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MiniProgramListActivity.this.showError();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class h implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MiniProgramListBean f6460g;

        h(MiniProgramListBean miniProgramListBean) {
            this.f6460g = miniProgramListBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            MiniProgramListBean miniProgramListBean = this.f6460g;
            if (miniProgramListBean == null || !TextUtils.equals(miniProgramListBean.code, "0")) {
                MiniProgramListActivity.this.showError();
                return;
            }
            MiniProgramListBean miniProgramListBean2 = this.f6460g;
            int i2 = miniProgramListBean2.totalPage;
            List<MiniProgramListBean.MiniProgramBean> list = miniProgramListBean2.appList;
            if (list != null && list.size() > 0) {
                Iterator<MiniProgramListBean.MiniProgramBean> it = this.f6460g.appList.iterator();
                while (it.hasNext()) {
                    it.next().itemType = 16;
                }
                MiniProgramListActivity.this.t.addAll(this.f6460g.appList);
            }
            if (MiniProgramListActivity.this.t.size() == 0) {
                MiniProgramListActivity.this.showEmptyView();
                return;
            }
            MiniProgramListActivity.this.f6449k.notifyDataSetChanged();
            if (MiniProgramListActivity.this.f6451m < i2) {
                MiniProgramListActivity.this.f6449k.loadMoreComplete();
                MiniProgramListActivity.F(MiniProgramListActivity.this);
                return;
            }
            MiniProgramListActivity.this.f6449k.loadMoreEnd(true);
            if (MiniProgramListActivity.this.t.size() > 8) {
                MiniProgramListBean.MiniProgramBean miniProgramBean = new MiniProgramListBean.MiniProgramBean();
                miniProgramBean.itemType = 5;
                MiniProgramListActivity.this.t.add(miniProgramBean);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MiniProgramListActivity.this.f6449k != null) {
                MiniProgramListActivity.this.f6449k.loadMoreFail();
            }
            if (MiniProgramListActivity.this.t.size() == 0) {
                MiniProgramListActivity.this.i();
            } else {
                MiniProgramListActivity.this.f6449k.notifyDataSetChanged();
            }
        }
    }

    static /* synthetic */ int F(MiniProgramListActivity miniProgramListActivity) {
        int i2 = miniProgramListActivity.f6451m;
        miniProgramListActivity.f6451m = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(String str) {
        Intent intent = new Intent();
        intent.putExtra("keyword", str);
        setResult(com.jd.manto.center.k.a.a, intent);
        finish();
    }

    private void I(String str) {
        HttpSetting K = K();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("keyword", str);
            jSONObject.put("page", String.valueOf(this.f6451m));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        K.setJsonParams(jSONObject);
        K.setUseFastJsonParser(true);
        K.setListener(new g());
        HttpGroupUtils.getHttpGroupaAsynPool().add(K);
    }

    private void J() {
        this.r.setOnClickListener(new a());
        this.q.setOnClickListener(new b());
        this.s.setOnClickListener(new c());
        this.o.setOnClickListener(new d());
        this.p.setOnClickListener(new e());
        this.p.addTextChangedListener(new f());
    }

    private static HttpSetting K() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("tinyAppSearch");
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setEffect(0);
        return httpSetting;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(MiniProgramListBean miniProgramListBean) {
        runOnUiThread(new h(miniProgramListBean));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ViewStub viewStub = this.f6447i;
        if (viewStub != null && this.f6448j == null) {
            this.f6448j = (LinearLayout) viewStub.inflate();
        }
        LinearLayout linearLayout = this.f6448j;
        if (linearLayout != null) {
            com.jd.manto.center.k.h.h((TextView) linearLayout.findViewById(R.id.txt_miniprogram_list_tip), R.string.manto_center_miniprogram_network_error);
        }
    }

    private void initView() {
        H();
        this.f6446h = (RecyclerView) findViewById(R.id.rv_miniprogram);
        this.f6447i = (ViewStub) findViewById(R.id.viewstub_miniprogram_list_tip_view);
        if (this.f6450l == null) {
            this.f6450l = new LinearLayoutManager(this, 1, false);
        }
        if (this.f6449k == null) {
            this.f6449k = new MiniProgramAdapter(this, this.t);
        }
        this.f6446h.setAdapter(this.f6449k);
        this.f6446h.setLayoutManager(this.f6450l);
        this.f6449k.m(this);
        this.o = (ImageView) findViewById(R.id.iv_clearall);
        this.q = (LinearLayout) findViewById(R.id.back);
        MantoSearchEditText mantoSearchEditText = (MantoSearchEditText) findViewById(R.id.et_search);
        this.p = mantoSearchEditText;
        mantoSearchEditText.requestFocus();
        this.p.setText(this.f6452n);
        Selection.setSelection(this.p.getText(), this.p.length());
        this.r = (ImageView) findViewById(R.id.manto_actionbar_home);
        this.s = (ImageView) findViewById(R.id.manto_actionbar_option);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView() {
        ViewStub viewStub = this.f6447i;
        if (viewStub != null && this.f6448j == null) {
            this.f6448j = (LinearLayout) viewStub.inflate();
        }
        com.jd.manto.center.k.h.h((TextView) this.f6448j.findViewById(R.id.txt_miniprogram_list_tip), R.string.manto_center_nomore_miniprogram);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showError() {
        runOnUiThread(new i());
    }

    public void H() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.common_title_background);
        this.v = frameLayout;
        com.jd.manto.center.k.d.e(frameLayout, "2833");
    }

    @Override // com.jd.manto.center.g.a
    public void b(MiniProgramListBean.MiniProgramBean miniProgramBean) {
        com.jd.manto.center.c.d(this, miniProgramBean.appId, miniProgramBean.appType, "1004");
    }

    @Override // com.jd.manto.center.g.a
    public void k() {
        I(this.f6452n);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.manto.center.BaseMantoCenterActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        com.jd.manto.center.h.b.p(this, "J_Applets_SearchResult");
        if (getIntent() != null) {
            this.f6452n = getIntent().getStringExtra("searchKeyword");
            this.u = getIntent().getStringExtra("tinyAppIntroduction");
        }
        setContentView(R.layout.manto_center_mini_program_list);
        initView();
        J();
        I(this.f6452n);
    }
}
